package org.zilch.com.api.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.zilch.com.api.dtos.Genre;
import org.zilch.com.api.dtos.ProductionCompany;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieRequest {

    @JsonProperty("title")
    private String title;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("runtime")
    private int runtime;

    @JsonProperty("genres")
    private List<Genre> genres; // List of genre IDs

    @JsonProperty("production_companies")
    private List<ProductionCompany> productionCompanies; // List of production company IDs

    // Default constructor
    public MovieRequest() {}

    // Parameterized constructor
    public MovieRequest(String title, String overview, String releaseDate, int runtime, List<Genre> genres, List<ProductionCompany> productionCompanies) {
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.genres = genres;
        this.productionCompanies = productionCompanies;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    @Override
    public String toString() {
        return "MovieRequest{" +
                "title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", runtime=" + runtime +
                ", genres=" + genres +
                ", productionCompanies=" + productionCompanies +
                '}';
    }
}

