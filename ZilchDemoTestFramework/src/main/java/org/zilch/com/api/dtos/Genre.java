package org.zilch.com.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Genre {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    // Default constructor
    public Genre() {}

    // Parameterized constructor
    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
