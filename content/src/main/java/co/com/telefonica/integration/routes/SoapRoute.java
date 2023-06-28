package co.com.telefonica.integration.routes;

import co.com.telefonica.integration.core.exception.handler.ValidateHeadersHandler;
import co.com.telefonica.integration.utility.core.exception.ValidationException;
import co.com.telefonica.integration.utility.core.exception.handler.ExceptionHandler;
import co.com.telefonica.integration.utility.core.exception.handler.LoggerHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.TypeConversionException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.processor.validation.SchemaValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXParseException;

import javax.net.ssl.SSLPeerUnverifiedException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import static co.com.telefonica.integration.core.constants.Consts.*;
import static co.com.telefonica.integration.utility.core.constants.Consts.*;
import static org.apache.camel.LoggingLevel.*;

/**
 * The type Soap route.
 */
@Slf4j
@Component
public class SoapRoute extends RouteBuilder {

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private LoggerHandler loggerHandler;

    @Value("${service.operation}")
    private String operationWebService;

    @Override
    public void configure() throws Exception {
        camelContext.setUseMDCLogging(Boolean.TRUE);

        exceptionConfiguration();

        from(ROUTE).routeId(SERVICE_PRODUCER).streamCaching()
                .setProperty(PROPERTY_START_SERVICE).spel(SPEL_NEW_DATE)
                .log(INFO, LOG_START_SERVICE)
                .log(DEBUG, log, loggerHandler.buildLog(SERVICE, VALIDATE_HEADERS))
                .bean(ValidateHeadersHandler.class)

                .choice().when().constant(operationWebService != null)
                .choice().when().constant(operationWebService.trim().equals(""))
                .log(DEBUG, NO_ACTION_SOAP)
                .otherwise()
                .log(DEBUG, operationWebService)
                .setHeader(Exchange.SOAP_ACTION, constant(operationWebService)).endChoice()
                .otherwise()
                .log(DEBUG, NO_ACTION_SOAP_PARAMETRIZED)
                .end()

                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, simple(CONTENT_TYPE_SOAP))

                .process("requestProcessor")
                .convertBodyTo(String.class)
                .process("contentProcessor")
                .removeHeader("CamelHttpUri")

                .log(DEBUG, log, loggerHandler.buildLog(SERVICE, CONSUME_SOAP))

                .setProperty(BEFORE_PROPERTY).spel(SPEL_NEW_DATE)
                .toD("{{service.producer.url}}")

                .setProperty(AFTER_PROPERTY).spel(SPEL_NEW_DATE)
                .setProperty(TIME_PROPERTY).spel(SPEL_CALCULATE_EXTERNAL_SERVICE)

                .log(INFO, SUCCESS_CONSUME_SOAP)

                .setBody().xpath("//*[local-name()='Envelope']/*[local-name()='Body']/*[local-name()='QueryCustomerRspMsg']")
                .convertBodyTo(String.class)
                .process("responseSoapProcessor")
                .marshal().json(JsonLibrary.Jackson)

                .setProperty(PROPERTY_FINISH_SERVICE).spel(SPEL_NEW_DATE)
                .setProperty(PROPERTY_TIME_SERVICE).spel(SPEL_CALCULATE_SERVICE)

                .log(INFO, LOG_FINISH_SERVICE)

                .end();
    }

    private void exceptionConfiguration() {
        onException(Exception.class).handled(true)
                .maximumRedeliveries(3)
                .redeliveryDelay(200)
                .setProperty(ERROR_ID, constant(ERROR_INTERNAL_SERVER_ERROR))
                .bean(ExceptionHandler.class)
                .marshal().json(JsonLibrary.Jackson)
                .log(ERROR, LOG_MESSAGE_HOST)
                .log(ERROR, LOG_CLASS)
                .log(ERROR, LOG_CLASS_NAME)
                .log(ERROR, LOG_TRACE)
                .end();
        onException(ValidationException.class).handled(true)
                .maximumRedeliveries(3)
                .redeliveryDelay(200)
                .setProperty(ERROR_ID, constant(ERROR_MISSING_PARAMS))
                .bean(ExceptionHandler.class)
                .marshal().json(JsonLibrary.Jackson)
                .log(ERROR, LOG_MESSAGE_HOST)
                .log(ERROR, LOG_CLASS)
                .log(ERROR, LOG_CLASS_NAME)
                .log(ERROR, LOG_TRACE)
                .end();
        onException(TypeConversionException.class).handled(true)
                .maximumRedeliveries(3)
                .redeliveryDelay(200)
                .setProperty(ERROR_ID, constant(ERROR_REQUEST_TIMEOUT))
                .bean(ExceptionHandler.class)
                .marshal().json(JsonLibrary.Jackson)
                .log(ERROR, LOG_MESSAGE_HOST)
                .log(ERROR, LOG_CLASS)
                .log(ERROR, LOG_CLASS_NAME)
                .log(ERROR, LOG_TRACE)
                .end();
        onException(SocketTimeoutException.class).handled(true)
                .setProperty(ERROR_ID, constant(ERROR_REQUEST_TIMEOUT))
                .maximumRedeliveries(3)
                .redeliveryDelay(200)
                .log(ERROR, LOG_MESSAGE_TIMEOUT)
                .log(ERROR, LOG_CLASS)
                .log(ERROR, LOG_CLASS_NAME)
                .log(ERROR, LOG_TRACE)
                .end();
        onException(TypeConversionException.class).handled(true)
                .setProperty(ERROR_ID, constant(ERROR_CONTENT_BAD_FORMED))
                .maximumRedeliveries(3)
                .redeliveryDelay(200)
                .log(ERROR, LOG_MESSAGE_HOST)
                .log(ERROR, LOG_CLASS)
                .log(ERROR, LOG_CLASS_NAME)
                .log(ERROR, LOG_TRACE)
                .end();
        onException(NoRouteToHostException.class).handled(true)
                .setProperty(ERROR_ID, constant(ERROR_TECHNICAL_ERROR))
                .maximumRedeliveries(3)
                .redeliveryDelay(200)
                .log(ERROR, LOG_MESSAGE_HOST)
                .log(ERROR, LOG_CLASS)
                .log(ERROR, LOG_CLASS_NAME)
                .log(ERROR, LOG_TRACE)
                .end();
        onException(UnknownHostException.class).handled(true)
                .maximumRedeliveries(3)
                .redeliveryDelay(200)
                .setProperty(ERROR_ID, constant(ERROR_REQUEST_TIMEOUT))
                .bean(ExceptionHandler.class)
                .marshal().json(JsonLibrary.Jackson)
                .log(ERROR, LOG_MESSAGE_UNKNOWN_HOST)
                .log(ERROR, LOG_CLASS)
                .log(ERROR, LOG_CLASS_NAME)
                .log(ERROR, LOG_TRACE)
                .end();
        onException(SAXParseException.class).handled(true)
                .setProperty(ERROR_ID, constant(ERROR_REQUEST_TIMEOUT))
                .bean(ExceptionHandler.class)
                .marshal().json(JsonLibrary.Jackson)
                .log(ERROR, LOG_MESSAGE_PARSE)
                .log(ERROR, LOG_CLASS)
                .log(ERROR, LOG_CLASS_NAME)
                .log(ERROR, LOG_TRACE)
                .end();
        onException(SchemaValidationException.class).handled(true)
                .setProperty(ERROR_ID, constant(ERROR_CONTENT_BAD_FORMED))
                .log(ERROR, LOG_MESSAGE_PARSE)
                .log(ERROR, LOG_CLASS)
                .log(ERROR, LOG_CLASS_NAME)
                .log(ERROR, LOG_TRACE)
                .end();
        onException(SSLPeerUnverifiedException.class).handled(true)
                .setProperty(ERROR_ID, constant(ERROR_TECHNICAL_ERROR))
                .log(ERROR, LOG_MESSAGE_SSL)
                .log(ERROR, LOG_CLASS)
                .log(ERROR, LOG_CLASS_NAME)
                .log(ERROR, LOG_TRACE)
                .end();
        onException(ConnectException.class).handled(true)
                .maximumRedeliveries(3)
                .redeliveryDelay(200)
                .setProperty(ERROR_ID, constant(ERROR_REQUEST_TIMEOUT))
                .bean(ExceptionHandler.class)
                .marshal().json(JsonLibrary.Jackson)
                .log(ERROR, LOG_MESSAGE_HOST)
                .log(ERROR, LOG_CLASS)
                .log(ERROR, LOG_CLASS_NAME)
                .log(ERROR, LOG_TRACE)
                .end();
    }
}