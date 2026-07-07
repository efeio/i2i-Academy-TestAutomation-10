package com.i2i.academy;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiAutomationTest {

    @BeforeAll
    public static void setup() {
        // Point tests to local Nginx mock server to avoid corporate proxy/firewall 401 blocks
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    public void testGetDummyEndpoint() {
        given()
            .when()
                .get("/api/dummy")
            .then()
                .statusCode(200) // Verification: HTTP Status Code is 200 OK
                .time(lessThan(3000L)) // Verification: Response time < 3000ms
                .contentType(ContentType.JSON)
                .body("status", equalTo("success")) // Verification: Specific body payload value
                .body("message", equalTo("Nginx stress test API is active"));
    }

    @Test
    public void testPostDummyEndpoint() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "leader");

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/api/dummy")
        .then()
            .statusCode(200) // Nginx mock returns 200 for POST
            .time(lessThan(3000L))
            .contentType(ContentType.JSON)
            .body("status", equalTo("success"))
            .body("message", notNullValue());
    }
}
