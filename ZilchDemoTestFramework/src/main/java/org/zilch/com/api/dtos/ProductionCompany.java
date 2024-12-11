package org.zilch.com.api.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// Nested ProductionCompany class
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductionCompany {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("origin_country")
    private String originCountry;

    // Default constructor
    public ProductionCompany() {}

    // Parameterized constructor
    public ProductionCompany(int id, String name, String originCountry) {
        this.id = id;
        this.name = name;
        this.originCountry = originCountry;
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

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    @Override
    public String toString() {
        return "ProductionCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", originCountry='" + originCountry + '\'' +
                '}';
    }
}
