package org.zilch.com.api.tests;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.zilch.com.api.dtos.Genre;
import org.zilch.com.api.dtos.MovieResult;
import org.zilch.com.api.dtos.requests.MovieRatingRequest;
import org.zilch.com.api.dtos.responses.MovieListResponse;
import org.zilch.com.api.dtos.responses.MovieResponse;
import org.zilch.com.api.dtos.responses.PopularMoviesResponse;
import org.zilch.com.api.utilities.JsonUtil;
import org.zilch.com.base.ApiBaseTest;
import org.zilch.com.api.clients.MovieClient;
import org.zilch.com.utilities.RandomStringGenerator;

import java.io.File;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.*;

public class IdmbAPITests extends ApiBaseTest {

    private MovieClient client = new MovieClient();


    @Test
    public void HowToGenerateSession(){

//        https://www.themoviedb.org/authenticate/656e12f26320033984f958023acfde32c44fa2b8
//        client.createToken();

        client.getSessionId();
    }
    @Test
    public void canRetrieveAMovieById(){
        Response response = client.getMovieById("12");
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        try{
            MovieResponse actualMovie = JsonUtil.deserialize(response, MovieResponse.class);
            assertEquals(actualMovie.getId(), 12);
            assertEquals(actualMovie.getTitle(), "Finding Nemo");

            assertEquals(actualMovie.getReleaseDate(), "2003-05-30","Release date should match '2003-05-30'");
            assertEquals(actualMovie.getRuntime(), 100, "Runtime should be 100 minutes");

            List<Genre> genres = actualMovie.getGenres();
            assertTrue(genres.toString().contains("Animation"),  "Genres should include 'Animation'");
            assertTrue(actualMovie.getProductionCompanies().toString().contains("Pixar"),  "Genres should include 'Animation'");
            assertTrue(actualMovie.getVoteCount() > 1000,  "Vote count should be greater than 19,000");
            assertTrue(actualMovie.getPopularity() > 80,  "Popularity should be greater than 80  received ");
            System.out.println("getPopularity " + actualMovie.getPopularity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void canRetrievePopularMoviesIsNotEmpty(){
        Response response = client.getPopularMovies();
        assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        try{
            PopularMoviesResponse actualPopularMovie = JsonUtil.deserialize(response, PopularMoviesResponse.class);
            assertFalse(actualPopularMovie.getResults().size() == 0, "Popular movies list should not be empty");
            for(MovieResult result: actualPopularMovie.getResults()){
                assertNotNull(result.getId(), "Movie ID should not be null");
                assertNotNull(result.getTitle(), "Movie title should not be null");
                assertNotNull(result.getReleaseDate(),  "Release date should not be null");
                assertNotNull(result.getOverview(),  "Overview should not be null");

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void verifyResponseSchema(){
        Response response = client.getPopularMovies();
        File schemaFile = new File("/../testData/popular-movies-schema.json");
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("api/popular-movies-schema.json"));

    }

    @Test
    public void verifyTotalPopularMoviesGreaterThanZero(){
        Response response = client.getPopularMovies();
        try {
            PopularMoviesResponse actualPopularMovie = JsonUtil.deserialize(response, PopularMoviesResponse.class);
            assertTrue(actualPopularMovie.getTotalResults() > 0,  "Total results should be greater than 0");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void canCreateMovieList(){
        Random rand = new Random();
        try{
            Response response = client.createMovieList(RandomStringGenerator.generateRandomString(rand.nextInt(6,15)), RandomStringGenerator.generateRandomString(rand.nextInt(10,30)), "en");
            MovieListResponse actualMovieLIst = JsonUtil.deserialize(response, MovieListResponse.class);
            assertTrue(actualMovieLIst.isSuccess(),  "Expected success to be true");
            assertEquals(actualMovieLIst.getStatusMessage(), "Success.");
            assertEquals(actualMovieLIst.getStatusCode(), 1, "Status code should be 201");

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void canDetectMissingFieldFromMovieListCreation(){
        Random rand = new Random();
        try{
            Response response = client.createMovieList("", RandomStringGenerator.generateRandomString(rand.nextInt(10,30)), "en");
            MovieListResponse actualMovieLIst = JsonUtil.deserialize(response, MovieListResponse.class);
            assertEquals(actualMovieLIst.getStatusCode(), 0,  "Expected status code 1 for missing required field");
            assertEquals(actualMovieLIst.getErrors().get(0),  "Name is required");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void canValidateMovieRating(){
        MovieRatingRequest ratingRequest = new MovieRatingRequest(8.5);
        try{

            String payload = JsonUtil.serialize(ratingRequest);
            Response response = client.rateAMovieWithUpdateMethod("550", payload);
            assertEquals(response.getStatusCode(), 201);
            assertTrue(response.jsonPath().getString("status_message").toLowerCase().contains("success"));

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void canInvalidateAMovieRating(){
        MovieRatingRequest ratingRequest = new MovieRatingRequest(12.0);
        try{

            String payload = JsonUtil.serialize(ratingRequest);
            Response response = client.rateAMovieWithUpdateMethod("550", payload);
            assertEquals(response.getStatusCode(), 400);
            assertEquals(response.jsonPath().getString("status_message"), "Value too high: Value must be less than, or equal to 10.0.");

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void invalidateMissingAuthentication(){
        MovieRatingRequest ratingRequest = new MovieRatingRequest(9);
        try{

            String payload = JsonUtil.serialize(ratingRequest);
            Response response = client.rateAMovieWithoutSession("550", payload);
            assertEquals(response.getStatusCode(), 401);
            assertEquals(response.jsonPath().getString("status_message"), "Authentication failed: You do not have permissions to access the service.");

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testInvalidPayload(){
        MovieRatingRequest ratingRequest = new MovieRatingRequest(8.0);
        try{

            String payload = JsonUtil.serialize(ratingRequest);
            Response response = client.rateAMovieWithUpdateMethod("9999999", payload);
            assertEquals(response.getStatusCode(), 404);
            assertEquals(response.jsonPath().getString("status_message"), "The resource you requested could not be found.");

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void estDeleteMovieRating(){
        MovieRatingRequest ratingRequest = new MovieRatingRequest(8.5);
        try{

            String payload = JsonUtil.serialize(ratingRequest);
            client.rateAMovieWithUpdateMethod("550", payload);
            Response response =  client.deleteAMovieRating("550");
            assertEquals(response.getStatusCode(), 200);
            assertEquals(response.jsonPath().getString("status_message"), "The item/record was deleted successfully.");


        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void tesDeleteInvalidMovieRating(){
        MovieRatingRequest ratingRequest = new MovieRatingRequest(8.5);
        try{
            Response response =  client.deleteAMovieRating("-1");
            assertEquals(response.getStatusCode(), 404);
            assertEquals(response.jsonPath().getString("status_message"), "Invalid id: The pre-requisite id is invalid or not found.");


        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
