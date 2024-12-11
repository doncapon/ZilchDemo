package org.zilch.com.api.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionRequest {

    @JsonProperty("request_token")
    private String requestToken;

    // Default constructor
    public SessionRequest() {}

    // Parameterized constructor
    public SessionRequest(String requestToken) {
        this.requestToken = requestToken;
    }

    // Getter and Setter
    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

    @Override
    public String toString() {
        return "SessionRequest{" +
                "requestToken='" + requestToken + '\'' +
                '}';
    }
}

