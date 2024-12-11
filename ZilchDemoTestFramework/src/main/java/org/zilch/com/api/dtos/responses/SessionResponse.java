package org.zilch.com.api.dtos.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionResponse {

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("session_id")
    private String sessionId;

    // Default constructor
    public SessionResponse() {}

    // Parameterized constructor
    public SessionResponse(boolean success, String sessionId) {
        this.success = success;
        this.sessionId = sessionId;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "SessionResponse{" +
                "success=" + success +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}

