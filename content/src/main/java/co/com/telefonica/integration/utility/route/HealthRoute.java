package co.com.telefonica.integration.utility.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static co.com.telefonica.integration.utility.core.constants.Consts.SERVICE_HEALTH;


@Component
public class HealthRoute extends RouteBuilder{
	@Override
	public void configure() throws Exception {

		from(SERVICE_HEALTH)
			.id("service-health")
			.setBody().constant("{\"result\": \"OK\"}")
			.setHeader(Exchange.CONTENT_TYPE, simple("application/json"))
		.end();
	}
}