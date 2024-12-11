package org.zilch.com.api.dtos.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.zilch.com.api.dtos.Genre;
import org.zilch.com.api.dtos.ProductionCompany;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResponse {

    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("runtime")
    private int runtime;

    @JsonProperty("popularity")
    private double popularity;

    @JsonProperty("vote_average")
    private double voteAverage;

    @JsonProperty("vote_count")
    private int voteCount;

    @JsonProperty("genres")
    private List<Genre> genres;

    @JsonProperty("production_companies")
    private List<ProductionCompany> productionCompanies;

    // Default constructor
    public MovieResponse() {
    }

    // Parameterized constructor
    public MovieResponse(int id, String title, String overview, String releaseDate, int runtime, double voteAverage, double populairty, int voteCount, List<Genre> genres, List<ProductionCompany> productionCompanies) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.popularity = populairty;
        this.genres = genres;
        this.productionCompanies = productionCompanies;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
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

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
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
        return "MovieResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", popularity='" + popularity + '\'' +
                ", runtime=" + runtime +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                ", genres=" + genres +
                ", productionCompanies=" + productionCompanies +
                '}';
    }
}