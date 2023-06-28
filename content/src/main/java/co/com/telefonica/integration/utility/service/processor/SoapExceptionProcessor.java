package co.com.telefonica.integration.utility.service.processor;

import co.com.telefonica.integration.utility.model.exception.MessageFaultDTO;
import co.com.telefonica.integration.utility.model.exception.ResultApiExceptionDTO;
import co.com.telefonica.integration.utility.model.fault.legacy.LegacyFaultSOAP;
import co.com.telefonica.integration.utility.model.fault.legacy.ResultExceptionDTO;
import co.com.telefonica.integration.utility.model.header.HeaderInType;
import co.com.telefonica.integration.utility.service.mapper.SoapExceptionMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.http.common.HttpOperationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static co.com.telefonica.integration.utility.core.constants.Consts.*;


@Service
@Slf4j
public class SoapExceptionProcessor implements Processor {

    @Autowired
    private SoapExceptionMapper soapExceptionMapper;

    @Override
    public void process(Exchange exchange) throws Exception {

        ResultExceptionDTO.ExceptionDetails details = new ResultExceptionDTO.ExceptionDetails();

        ObjectMapper mapper = new ObjectMapper();

        Object exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT);

        JAXBContext contextObj = JAXBContext.newInstance(LegacyFaultSOAP.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        String rspBody = null;
        Map<String, String> responseHeaders = new HashMap<>();

        try {
            rspBody = ((HttpOperationFailedException) exception).getResponseBody();
            responseHeaders = ((HttpOperationFailedException) exception).getResponseHeaders();

            MessageFaultDTO fault = mapper.readValue(rspBody, MessageFaultDTO.class);

            getBodyFault(exchange, marshallerObj, responseHeaders, fault.getResult());

        } catch (UnrecognizedPropertyException e) {

            ResultApiExceptionDTO resultApiExceptionDTO = mapper.readValue(rspBody, ResultApiExceptionDTO.class);

            ResultApiExceptionDTO.ApiExceptionDetails apiExceptionDetails = null;
            if (resultApiExceptionDTO.getExceptionDetails() != null && !resultApiExceptionDTO.getExceptionDetails().isEmpty()) {
                apiExceptionDetails = resultApiExceptionDTO.getExceptionDetails().get(0);
            }

            if (apiExceptionDetails != null) {
                if (ERROR_CODE_LIST.contains(apiExceptionDetails.getCode())) {
                    details.setCode(apiExceptionDetails.getCode());
                } else {
                    details.setCode(ERROR_BUSINESS_ERROR);
                }
                details.setComponent(apiExceptionDetails.getComponent());
                details.setArguments(Collections.singletonList(apiExceptionDetails.getMessage()));
                details.setEndpoint(apiExceptionDetails.getEndpoint());
            } else {
                details.setCode(ERROR_BUSINESS_ERROR);
            }
            details.setMessage(resultApiExceptionDTO.getMessage());
            ResultExceptionDTO resultExceptionDTO = new ResultExceptionDTO();
            resultExceptionDTO.setDetails(details);

            MessageFaultDTO fault = new MessageFaultDTO();
            fault.setResult(resultExceptionDTO);

            getBodyFault(exchange, marshallerObj, responseHeaders, fault.getResult());

        } catch (RuntimeException e) {
            HeaderInType headers = (HeaderInType) exchange.getProperty(REQUEST_SOAP);

            Map<String, String> header = new HashMap<>();
            header.put(HEADER_ORIGINATOR, headers.getOriginator());
            header.put(HEADER_DESTINATION, headers.getDestination());
            header.put(HEADER_EXECID, headers.getExecId());
            header.put(HEADER_MSGID, headers.getMsgType());
            header.put(HEADER_TIMESTAMP, headers.getTimestamp());
            header.put(HEADER_MSGTYPE, headers.getMsgType());

            details.setCode(ERROR_BUSINESS_ERROR);
            details.setMessage("Bad request");

            ResultExceptionDTO resultExceptionDTO = new ResultExceptionDTO();
            resultExceptionDTO.setDetails(details);

            MessageFaultDTO fault = new MessageFaultDTO();
            fault.setResult(resultExceptionDTO);

            getBodyFault(exchange, marshallerObj, header, resultExceptionDTO);

        } catch (Exception e) {
            HeaderInType headers = (HeaderInType) exchange.getProperty(REQUEST_SOAP);

            Map<String, String> header = new HashMap<>();
            header.put(HEADER_ORIGINATOR, headers.getOriginator());
            header.put(HEADER_DESTINATION, headers.getDestination());
            header.put(HEADER_EXECID, headers.getExecId());
            header.put(HEADER_MSGID, headers.getMsgType());
            header.put(HEADER_TIMESTAMP, headers.getTimestamp());
            header.put(HEADER_MSGTYPE, headers.getMsgType());

            details.setCode(ERROR_REQUEST_TIMEOUT);
            details.setMessage("Timeout processing request");

            ResultExceptionDTO resultExceptionDTO = new ResultExceptionDTO();
            resultExceptionDTO.setDetails(details);

            MessageFaultDTO fault = new MessageFaultDTO();
            fault.setResult(resultExceptionDTO);

            getBodyFault(exchange, marshallerObj, header, resultExceptionDTO);
        }


    }

    private void getBodyFault(Exchange exchange, Marshaller marshallerObj, Map<String, String> header, ResultExceptionDTO fault) throws IOException, JAXBException {
        String xmlString;
        LegacyFaultSOAP soapFault = soapExceptionMapper.getSoapFault(fault, header);

        StringWriter sw = new StringWriter();
        marshallerObj.marshal(soapFault, sw);
        xmlString = sw.toString();

        exchange.getIn().setBody(xmlString);
        exchange.getIn().getHeaders().clear();
        exchange.getIn().setFault(true);
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, CONTENT_TYPE_SOAP);
    }
}
