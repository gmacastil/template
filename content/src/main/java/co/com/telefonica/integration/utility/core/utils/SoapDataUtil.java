package co.com.telefonica.integration.utility.core.utils;

import org.springframework.stereotype.Component;

@Component
public class SoapDataUtil {

    public static String getFaultCode(String exceptionCode) {
        String faultCode = null;
        long code = Long.parseLong(exceptionCode);

        if (code >= 1000 && code <= 3999) {
            faultCode = "soapenv:Client";
        } else if (code >= 4000 && code <= 4999) {
            faultCode = "soapenv:Server";
        }

        return faultCode;
    }

    public static String getExceptionCategory(String exceptionCode) {
        String exceptionCategory = null;
        long code = Long.parseLong(exceptionCode);

        if (code >= 1000 && code <= 1999) {
            exceptionCategory = "SVC";
        } else if (code >= 2000 && code <= 2999) {
            exceptionCategory = "POL";
        } else if (code >= 3000 && code <= 3999) {
            exceptionCategory = "SEC";
        } else if (code >= 4000 && code <= 4999) {
            exceptionCategory = "SRV";
        }

        return exceptionCategory;
    }

    public static String getFaultString(String category, String code, String message) {
        return category + code + ":" + message;
    }
}
