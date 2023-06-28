package co.com.telefonica.integration.service.mapper;

import co.com.telefonica.integration.model.wsdl.legacy.LegacyRequestBody;
import co.com.telefonica.integration.model.wsdl.legacy.LegacyRequestSOAP;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper {

    public LegacyRequestSOAP getLegacyRequestSOAP() {

        LegacyRequestBody legacyResponseBody = new LegacyRequestBody();

        LegacyRequestSOAP legacyResponseSOAP = new LegacyRequestSOAP();
   
        return legacyResponseSOAP;
    }
}
