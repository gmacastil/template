package co.com.telefonica.integration.utility.core.constants;

import java.util.Arrays;
import java.util.List;

/**
 * The type Customer detail const.
 */
public class Consts {

    /**
     * Headers
     */
    public static final String REQUEST_SOAP = "request-soap";
    public static final String SERVICE_HEALTH = "direct:service-health";
    public static final String CONTENT_TYPE_SOAP = "text/xml;charset=UTF-8";
    public static final String MESSAGE_HEADER = "Header ";
    public static final String MESSAGE_INVALID_FORMAT = " has an invalid format";
    public static final String HEADER_AUTORIZATION = "authorization";
    public static final String HEADER_SYSTEM = "system";
    public static final String HEADER_OPERATION = "operation";
    public static final String HEADER_EXECID = "execId";
    public static final String HEADER_TIMESTAMP = "timestamp";
    public static final String HEADER_MSGID = "msgId";
    public static final String HEADER_MSGTYPE = "msgType";
    public static final String HEADER_VARARG = "varArg";
    public static final String HEADER_DESTINATION = "destination";
    public static final String HEADER_ORIGINATOR = "originator";
    public static final String HEADER_PID = "pid";
    public static final String CODE_LEGACY = "code_legacy";
    public static final String MESSAGE_LEGACY = "message_legacy";
    public static final String HEADER_COUNTRY = "country";
    public static final String HEADER_LANG = "lang";
    public static final String HEADER_ENTITY = "entity";
    public static final String HEADER_SUBSYSTEM = "subsystem";
    public static final String HEADER_SENDER = "sender";
    public static final String HEADER_USERID = "userId";
    public static final String HEADER_WSID = "wsId";
    public static final String HEADER_WSIP = "wsIp";
    public static final String HEADER_WSIPV6 = "wsIpv6";
    public static final String PATTERN_TIMESTAMP = "yyyy-MM-dd'T'HH:mm:ssXXX";

    /**
     * Value Headers
     */
    public static final String VALUE_HEADER_COUNTRY = "CO";
    public static final String VALUE_HEADER_LANG = "ES";
    public static final String VALUE_HEADER_ENTITY = "TEF";
    public static final String VALUE_HEADER_SYSTEM = "FUSE";
    public static final String VALUE_HEADER_SUBSYSTEM = "FUSE";
    public static final String VALUE_HEADER_ORIGINATOR = "co:TEF:FUSE:FUSE";
    public static final String VALUE_HEADER_USERID = "userFUSE";
    public static final String VALUE_HEADER_DESTINATION = "co:TEF:FUSE:FUSE";

    /**
     * Timer external
     */
    public static final String BEFORE_PROPERTY = "before_external_service";
    public static final String AFTER_PROPERTY = "after_external_service";
    public static final String TIME_PROPERTY = "time_external_service";

    /**
     * Timer service
     */
    public static final String PROPERTY_START_SERVICE = "start_date_service";
    public static final String PROPERTY_FINISH_SERVICE = "finish_date_service";
    public static final String PROPERTY_TIME_SERVICE = "time_service";

    /**
     * Timer Methods
     */
    public static final String SPEL_NEW_DATE = "#{new java.util.Date()}";
    public static final String SPEL_CALCULATE_SERVICE = "#{T(co.com.telefonica.utility.core.utils.DateUtil).calculateTime(properties[start_date_service], properties[finish_date_service])}";
    public static final String SPEL_CALCULATE_EXTERNAL_SERVICE = "#{T(co.com.telefonica.utility.core.utils.DateUtil).calculateTime(properties[before_external_service], properties[after_external_service])}";

    /**
     * Messages for the service log
     */
    public static final String LOG_START_SERVICE = "Iniciando servicio";
    public static final String LOG_FINISH_SERVICE = "Finalizando servicio | Duration: ${property.time_service}";
    public static final String VALIDATE_HEADERS = "Validando Headers";

    /**
     * Messages for web service (SOAP)
     */
    public static final String NO_ACTION_SOAP = "No se envía SOAPAction.";
    public static final String NO_ACTION_SOAP_PARAMETRIZED = "No se encuentra la variable parametrizada.";
    public static final String CONSUME_SOAP = "Consumiendo WS | Headers: ${headers} | Body: ${body}";
    public static final String SUCCESS_CONSUME_SOAP = "Consumo Exitoso de WS | Duration: ${property.time_external_service} | Headers: ${headers} | Body: ${body}";
    public static final String ERROR_SERVICE = "Error en el servicio | Exception: ${exception.class} - ${exception.message}";

    /**
     * Errors
     */
    public static final String ERROR_ID = "ERROR-ID";
    public static final String ERROR_HTTP_CODE = "http-code";
    public static final String ERROR_HTTP_DESCRIPTION = "http-description";
    public static final String ERROR_CODE = "code";
    public static final String ERROR_MESSAGE = "message";
    public static final String ERROR_DETAIL = "detail";
    public static final String LOG_TRACE = "StackTrace: ${exception.stacktrace}";
    public static final String LOG_CLASS_NAME = "ExceptionClassName: ${exchangeProperty.CamelExceptionCaught.class.name}";
    public static final String LOG_CLASS = "ExceptionClass: ${exchangeProperty.CamelExceptionCaught.class}";
    public static final String LOG_MESSAGE_HOST = "El host de destino no ha sido alcanzado presenta errores de conexion en la ruta ${routeId}";
    public static final String LOG_MESSAGE_TIMEOUT = "El host de destino no ha sido alcanzado presenta errores de conexion en la ruta ${routeId}";
    public static final String LOG_MESSAGE_UNKNOWN_HOST = "El host de destino es desconocido presenta errores de comunicacion en la ruta ${routeId}";
    public static final String LOG_MESSAGE_PARSE = "El mensaje xml recibido presenta errores de codificacion en la ruta ${routeId}";
    public static final String LOG_MESSAGE_SSL = "El mensaje a procesar requiere del certificado provisto por SAP, presenta errores en la ruta ${routeId}";

    /**
     * Code
     */
    public static final String ERROR_GENERIC = "1000";
    public static final String ERROR_MISSING_PARAMS = "1001";
    public static final String ERROR_CONTENT_BAD_FORMED = "1002";
    public static final String ERROR_METHOD_NOT_ALLOWED = "1003";
    public static final String ERROR_DEPRECATED_API = "1004";
    public static final String ERROR_NOTIFICATION_NOT_SUPPORT = "1005";
    public static final String ERROR_BUSINESS_ERROR = "1050";
    public static final String ERROR_GENERIC_POLICY_ERROR = "2000";
    public static final String ERROR_RETRY_NOT_ALLOWED = "2001";
    public static final String ERROR_MEDIA_NOT_SUPPORTED = "2002";
    public static final String ERROR_EMPTY_MESSAGES = "2003";
    public static final String ERROR_DATES_NOT_ALLOWED = "2004";
    public static final String ERROR_MAX_CONCURRENT_REQUEST = "2020";
    public static final String ERROR_MAX_REQUEST = "2021";
    public static final String ERROR_MAX_RESPONSE_TIME = "2022";
    public static final String ERROR_GENERIC_SECURITY = "3000";
    public static final String ERROR_INTERNAL_SERVER_ERROR = "4000";
    public static final String ERROR_NOT_IMPLEMENTED = "4001";
    public static final String ERROR_SERVICE_UNAVAILABLE = "4002";
    public static final String ERROR_REQUEST_TIMEOUT = "4003";
    public static final String ERROR_TECHNICAL_ERROR = "4050";

    /**
     * Code List
     */
    public static final List<String> ERROR_CODE_LIST = Arrays
            .asList("1000", "1001", "1002", "1003", "1004", "1005",
                    "1050", "2000", "2001", "2002", "2003", "2004",
                    "2020", "2021", "2022", "3000", "4000", "4001",
                    "4002", "4003", "4050");

}
