package com.sample.camel.route;

import com.sample.camel.error.ErrorHandler;
import com.sample.camel.service.MockService;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.processor.validation.SchemaValidationException;
import org.springframework.stereotype.Component;

@Component
public class AutomobileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet");

        rest("/automobile")
                .id("rest-automobile")
                .post()
                .to("direct:frontend");

        from("direct:frontend")
                .id("frontend")
                .doTry()
                .convertBodyTo(java.lang.String.class, "UTF-8")
                .log("front end validate")
                .to("validator:automobile.xsd")
                .to("direct:backend")
                .doCatch(SchemaValidationException.class)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("400"))
                .bean(ErrorHandler.class);

        from("direct:backend")
                .id("backend")
                .doTry()
                .log("backend transform and validate")
                .to("xslt:automobile-to-vehicle.xslt")
                .to("validator:vehicle.xsd")
                .log("${body}")
                .bean(MockService.class)
                .doCatch(Exception.class)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("500"))
                .bean(ErrorHandler.class)
                .end();
        ;
    }
}
