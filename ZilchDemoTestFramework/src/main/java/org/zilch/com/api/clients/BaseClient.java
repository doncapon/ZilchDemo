package org.zilch.com.api.clients;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseClient {

    private final String baseUrl;

    public BaseClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private Response performGetRequest(String fullUrl, Map<String, String> headers) {
        return given()
                .headers(headers != null ? headers : Map.of())
                .when()
                .get(fullUrl)
                .then()
                .extract()
                .response();
    }

    private Response performPostRequest(String fullUrl, String payload, Map<String, String> headers) {
        return given()
                .headers(headers != null ? headers : Map.of())
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post(fullUrl)
                .then()
                .extract()
                .response();
    }

    private Response performPutRequest(String fullUrl, String payload, Map<String, String> headers) {
        return given()
                .headers(headers != null ? headers : Map.of())
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .put(fullUrl)
                .then()
                .extract()
                .response();
    }

    private Response performDeleteRequest(String fullUrl, Map<String, String> headers) {
        return given()
                .headers(headers != null ? headers : Map.of())
                .when()
                .delete(fullUrl)
                .then()
                .extract()
                .response();
    }

    public Response get(String endpoint, Map<String, String> headers) {
        return performGetRequest(baseUrl + endpoint, headers);
    }

    public Response post(String endpoint, String payload, Map<String, String> headers) {
        return performPostRequest(baseUrl + endpoint, payload, headers);
    }

    public Response put(String endpoint, String payload, Map<String, String> headers) {
        return performPutRequest(baseUrl + endpoint, payload, headers);
    }

    public Response delete(String endpoint, Map<String, String> headers) {
        return performDeleteRequest(baseUrl + endpoint, headers);
    }
}
