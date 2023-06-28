package co.com.telefonica.integration.utility.service.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ContentProcessor implements Processor {
	 @Override
	    public void process(Exchange exchange) throws Exception {
	 
	        String payload = exchange.getIn().getBody(String.class);
	        if (payload.endsWith("\n")) {
	            payload = payload.substring(0, payload.length() - 1);
	        }
	 
	        exchange.getIn().setBody(payload.getBytes());
	    }
}
