package com.sample.camel.route;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AutomobileRouteTest {

    private static final String VALID_REQUEST = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
            "<automobile>\n" +
            "  <id>1</id>\n" +
            "  <make>bmw</make>\n" +
            "  <model>x1</model>\n" +
            "</automobile>";

    private static final String INVALID_REQUEST = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
            "<automobile>\n" +
            "  <id>xxx</assetId>\n" +
            "  <make>bmw</make>\n" +
            "  <model>x1</model>\n" +
            "</automobile>";

    private static final String SUCCESS_RESPONSE = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
            "<response>" +
            "\t<result>success</result>" +
            "</response>";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void happyEndToEndFlow() {
        ResponseEntity<String> response = restTemplate.postForEntity("/camel/automobile", VALID_REQUEST, String.class);
        assertEquals("HTTP status code invalid", HttpStatus.OK, response.getStatusCode());
        String body = response.getBody();
        assertNotNull("response body is null", body);
        assertEquals("response is invalid", SUCCESS_RESPONSE, body);
    }

    @Test
    public void unhappyEndToEndFlow() {
        ResponseEntity<String> response = restTemplate.postForEntity("/camel/automobile", INVALID_REQUEST, String.class);
        assertEquals("HTTP status code invalid", HttpStatus.BAD_REQUEST, response.getStatusCode());
        String body = response.getBody();
        assertNotNull("response body is null", body);
        assertTrue("response invalid", body.contains("<result>failed</result>"));
    }
}
