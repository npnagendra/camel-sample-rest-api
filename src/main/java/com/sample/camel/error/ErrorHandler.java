package com.sample.camel.error;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;

@Slf4j
public class ErrorHandler {

    public void handleError(Exchange exchange) {
        Exception caughtException = exchange.getProperty("CamelExceptionCaught", Exception.class);
        log.error("Exception caught", caughtException);
        exchange.getIn().setBody("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<response>" +
                "\t<result>failed</result>\n" +
                "</response>");
    }
}
