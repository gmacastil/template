package co.com.telefonica.integration.utility.service.mapper;

import co.com.telefonica.integration.utility.model.exception.MessageFaultDTO;
import co.com.telefonica.integration.utility.model.fault.legacy.*;
import co.com.telefonica.integration.utility.model.fault.wsdl.AppDetailType;
import co.com.telefonica.integration.utility.model.fault.wsdl.OperationFaultType;
import co.com.telefonica.integration.utility.model.header.HeaderOutType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

import static co.com.telefonica.integration.utility.core.constants.Consts.*;
import static co.com.telefonica.integration.utility.core.utils.SoapDataUtil.*;


@Component
public class SoapExceptionMapper {

    public LegacyFaultSOAP getSoapFault(ResultExceptionDTO fault, Map<String, String> exchange) throws IOException {

        String faultCode = fault.getDetails().getCode();
        String category = getExceptionCategory(faultCode);
        String message = fault.getDetails().getMessage();
        String legacyCode = fault.getDetails().getCodeLegacy();
        String legacyMessage = fault.getDetails().getMessageLegacy();
        String faultString = getFaultString(category, faultCode, message);
        String arguments = null;

        if (fault.getDetails().getArguments() != null) {
            arguments = fault.getDetails().getArguments().toString();
        }

        AppDetailType appDetailType = getAppDetailType(legacyCode, legacyMessage, arguments);
        OperationFaultType operationFaultType = getOperationFaultType(faultCode, category, message, appDetailType);
        Fault faultLegacy = getFault(faultCode, faultString, getDetail(operationFaultType));
        LegacyFaultBody body = getLegacyFaultBody(faultLegacy);

        LegacyFaultHeader header = getLegacyFaultHeader(exchange);

        return getLegacyFaultSOAP(body, header);
    }

    private LegacyFaultHeader getLegacyFaultHeader(Map<String, String> exchange) {
        HeaderOutType headerOutType = new HeaderOutType();
        headerOutType.setOriginator(exchange.get(HEADER_ORIGINATOR));
        headerOutType.setDestination(exchange.get(HEADER_DESTINATION));
        headerOutType.setExecId(exchange.get(HEADER_EXECID));
        headerOutType.setMsgType(exchange.get(HEADER_MSGID));
        headerOutType.setTimestamp(exchange.get(HEADER_TIMESTAMP));
        headerOutType.setMsgType(exchange.get(HEADER_MSGTYPE));

        return getLegacyFaultHeader(headerOutType);
    }

    private LegacyFaultHeader getLegacyFaultHeader(HeaderOutType headerOutType) {
        LegacyFaultHeader header = new LegacyFaultHeader();
        header.setHeader(headerOutType);
        return header;
    }

    private MessageFaultDTO getMessageFaultDTO(String rspBody) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(rspBody, MessageFaultDTO.class);
    }

    private LegacyFaultSOAP getLegacyFaultSOAP(LegacyFaultBody body, LegacyFaultHeader header) {
        LegacyFaultSOAP soapFault = new LegacyFaultSOAP();
        soapFault.setBody(body);
        soapFault.setHeader(header);
        return soapFault;
    }

    private LegacyFaultBody getLegacyFaultBody(Fault faultLegacy) {
        LegacyFaultBody body = new LegacyFaultBody();
        body.setFault(faultLegacy);

        return body;
    }

    private Fault getFault(String faultCode, String faultString, Detail detail) {
        Fault faultLegacy = new Fault();
        faultLegacy.setFaultCode(getFaultCode(faultCode));
        faultLegacy.setFaultString(faultString);
        faultLegacy.setDetail(detail);
        return faultLegacy;
    }

    private Detail getDetail(OperationFaultType operationFaultType) {
        Detail detail = new Detail();
        detail.setMessageFault(operationFaultType);
        return detail;
    }

    private OperationFaultType getOperationFaultType(String faultCode, String category, String message, AppDetailType appDetailType) {
        OperationFaultType operationFaultType = new OperationFaultType();
        operationFaultType.setExceptionCategory(category);
        operationFaultType.setExceptionCode(faultCode);
        operationFaultType.setExceptionMessage(message);
        operationFaultType.setExceptionDetail(null);
        operationFaultType.setExceptionSeverity("E");
        operationFaultType.setAppDetail(appDetailType);
        return operationFaultType;
    }

    private AppDetailType getAppDetailType(String legacyCode, String legacyMessage, String arguments) {
        AppDetailType appDetailType = new AppDetailType();
        appDetailType.setExceptionAppCode(legacyCode);
        appDetailType.setExceptionAppMessage(legacyMessage);
        appDetailType.setExceptionAppLocation(null);
        appDetailType.setExceptionAppCause(arguments);
        return appDetailType;
    }
}
