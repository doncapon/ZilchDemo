package org.zilch.com.api.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieRatingRequest {

    @JsonProperty("value")
    private double value;

    // Default constructor
    public MovieRatingRequest() {}

    // Parameterized constructor
    public MovieRatingRequest(double value) {
        this.value = value;
    }

    // Getters and Setters
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MovieRatingRequest{" +
                "value=" + value +
                '}';
    }
}

