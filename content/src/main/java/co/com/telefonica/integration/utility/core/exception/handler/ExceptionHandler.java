package co.com.telefonica.integration.utility.core.exception.handler;

import co.com.telefonica.integration.utility.model.fault.legacy.ExceptionDTO;
import co.com.telefonica.integration.utility.model.fault.legacy.ResultExceptionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static co.com.telefonica.integration.utility.core.constants.Consts.*;


@Setter
@Getter
@Component
@PropertySource("classpath:utility.properties")
@ConfigurationProperties(value = "service")
public class ExceptionHandler {

    /**
     * Errors information
     */
    private Map<String, String> errors;

    private Map<String, Map<String, Object>> errorsMapInformation;

    @PostConstruct
    public void setUpErrorMap() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        errorsMapInformation = new HashMap<>();
        for (String err : errors.keySet()) {
            String errInfo = errors.get(err);
            Map<String, Object> errInfoMap = mapper.readValue(errInfo, Map.class);
            errorsMapInformation.put(err, errInfoMap);
        }
    }

    /**
     * Metodo manejador de Excepciones generadas durante la ejecucion de la ruta camel
     *
     * @param exchange
     * @return
     */
    @Handler
    public ExceptionDTO buildResponseError(Exchange exchange) {
        return this.buildMessage(exchange);
    }

    public ExceptionDTO buildMessage(Exchange exchange) {

        Exception e = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
        String error = exchange.getProperty(ERROR_ID, String.class);

        ExceptionDTO result = null;

        if (e.getMessage() != null && errorsMapInformation != null && errorsMapInformation.containsKey(e.getMessage())) {

            result = getExceptionDTO(exchange, e, e.getMessage());

        } else if (error != null && errorsMapInformation != null && errorsMapInformation.containsKey(error)) {

            result = getExceptionDTO(exchange, e, error);

        }

        return result;
    }

    private ExceptionDTO getExceptionDTO(Exchange exchange, Exception e, String error) {

        String path = String.valueOf(exchange.getIn().getHeader(Exchange.HTTP_URL));
        String component = String.valueOf(exchange.getProperty(Exchange.FAILURE_ROUTE_ID));
        String codeLegacy = String.valueOf(exchange.getProperty(CODE_LEGACY));
        String messageLegacy = String.valueOf(exchange.getProperty(MESSAGE_LEGACY));

        HashMap<String, Object> errorInformation = (HashMap<String, Object>) errorsMapInformation.get(error);

        List<Object> args = new ArrayList<>();
        args.add(errorInformation.get(ERROR_DETAIL));

        if (e.getCause() != null) {
            args.add(e.getCause().toString());
        }

        ResultExceptionDTO.ExceptionDetails exceptionDetails = new ResultExceptionDTO.ExceptionDetails();
        exceptionDetails.setCode(String.valueOf(errorInformation.get(ERROR_CODE)));
        exceptionDetails.setMessage(String.valueOf(errorInformation.get(ERROR_MESSAGE)));
        exceptionDetails.setEndpoint(path);
        exceptionDetails.setComponent(component);
        exceptionDetails.setCodeLegacy(codeLegacy);
        exceptionDetails.setMessageLegacy(messageLegacy);
        exceptionDetails.setArguments(args);

        ResultExceptionDTO resultExceptionDTO = new ResultExceptionDTO();

        resultExceptionDTO.setCode(String.valueOf(errorInformation.get(ERROR_HTTP_CODE)));
        resultExceptionDTO.setMessage(String.valueOf(errorInformation.get(ERROR_HTTP_DESCRIPTION)));
        resultExceptionDTO.setDetails(exceptionDetails);

        ExceptionDTO resultDTO = new ExceptionDTO();
        //resultDTO.setResult(resultExceptionDTO);

        //exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, resultDTO.getResult().getCode());
        //exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, resultDTO.getResult().getCode());

        return resultDTO;
    }
}
