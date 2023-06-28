package co.com.telefonica.integration.core.exception.handler;

import co.com.telefonica.integration.utility.core.exception.ValidationException;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

import static co.com.telefonica.integration.utility.core.constants.Consts.*;
import static co.com.telefonica.integration.utility.core.utils.ValidateUtil.validateDate;
import static co.com.telefonica.integration.utility.core.utils.ValidateUtil.validateString;


@Component
public class ValidateHeadersHandler {

    @Handler
    public void validateInputHeaders(Exchange exchange) throws ValidationException {
        validateString(String.valueOf(exchange.getIn().getHeader(HEADER_AUTORIZATION)), HEADER_AUTORIZATION);
        validateString(String.valueOf(exchange.getIn().getHeader(HEADER_SYSTEM)), HEADER_SYSTEM);
        validateString(String.valueOf(exchange.getIn().getHeader(HEADER_OPERATION)), HEADER_OPERATION);
        validateString(String.valueOf(exchange.getIn().getHeader(HEADER_EXECID)), HEADER_EXECID);
        validateDate(HEADER_TIMESTAMP, String.valueOf(exchange.getIn().getHeader(HEADER_TIMESTAMP)));

    }
}
