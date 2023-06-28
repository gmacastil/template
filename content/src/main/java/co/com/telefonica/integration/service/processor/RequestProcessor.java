package co.com.telefonica.integration.service.processor;

import co.com.telefonica.integration.model.wsdl.legacy.LegacyRequestSOAP;
import co.com.telefonica.integration.service.mapper.RequestMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestProcessor implements Processor {

    @Autowired
    private RequestMapper requestMapper;

    @Override
    public void process(Exchange exchange) throws Exception {

        LegacyRequestSOAP legacyRequestSOAP = requestMapper.getLegacyRequestSOAP();

        exchange.getIn().setBody(legacyRequestSOAP);

    }


}
