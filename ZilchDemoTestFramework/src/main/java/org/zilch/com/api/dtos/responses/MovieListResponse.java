package org.zilch.com.api.dtos.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieListResponse {

    @JsonProperty("errors")
    private List<String> errors;

    @JsonProperty("status_code")
    private int statusCode;

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("list_id")
    private String listId;

    @JsonProperty("status_message")
    private String statusMessage;

    // Default constructor
    public MovieListResponse() {}

    // Parameterized constructor
    public MovieListResponse(String statusMessage, int statusCode, boolean success, String listId, List<String> errors) {
        this.errors = errors;
        this.statusCode = statusCode;
        this.success = success;
        this.listId = listId;
        this.statusMessage = statusMessage;
    }

    // Getters and Setters
    public List<String> getErrors() {
        return errors;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void setErrors(List<String> erros) {
        this.errors = erros;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    @Override
    public String toString() {
        return "MovieListResponse{" +
                ", statusMessage=" + statusMessage+
                ", statusCode=" + statusCode +
                ", success=" + success +
                ", listId='" + listId + '\'' +
                '}';
    }
}

