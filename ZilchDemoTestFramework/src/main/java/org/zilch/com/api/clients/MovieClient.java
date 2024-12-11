package org.zilch.com.api.clients;

import io.restassured.response.Response;
import org.zilch.com.api.utilities.HeadersUtil;
import org.zilch.com.utilities.ConfigurationManager;

public class MovieClient{

    private BaseClient client;
    private ConfigurationManager configManager = new ConfigurationManager("movies");

    public MovieClient() {
        client = new BaseClient(configManager.getConfigProperty("BASE_URL"));
    }

    public Response getPopularMovies(){
        return client.get(createEndpoint("movie/popular"), HeadersUtil.getDefaultHeaders());
    }

    public Response getMovieById(String movieId){
            return client.get(createEndpoint("movie/" + movieId), HeadersUtil.getDefaultHeaders());
    }
    public Response createMovieList(String name, String description, String language){
        String payload = "{\"name\": \"" + name + "\", \"description\": \"" + description + "\", \"language\": \"" + language + "\"}";
        return createMovie(payload);
    }

    //use this just once and then approve in the website
    public String createToken(){
        Response response = client.get(createEndpoint("authentication/token/new"), HeadersUtil.getDefaultHeaders());
        String token = response.jsonPath().getString("request_token");
        return token;
    }

    public String getSessionId(){
        String endpoint = createEndpoint("authentication/session/new" )+"&request_token=" + configManager.getConfigProperty("REQUEST_TOKEN");
       Response response = client.get(createEndpoint("authentication/session/new" )+"&request_token=" + configManager.getConfigProperty("REQUEST_TOKEN"), HeadersUtil.getDefaultHeaders());
        String sessionId = response.jsonPath().getString("session_id"); // for debug and getting session values. to be stored in the movie config properties file
        return sessionId;
    }

    private Response createMovie(String payload){
        Response response = client.post(createEndpointWithSessionId("list"), payload, HeadersUtil.getDefaultHeaders());
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.prettyPrint());

        return response;
    }

    public Response rateAMovieWithUpdateMethod(String movieId, String payload){
        Response response = client.post(createEndpointWithSessionId("movie/" + movieId+"/rating"), payload, HeadersUtil.getDefaultHeaders()); // using post in place of put

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.prettyPrint());
        return response;
    }

    public Response rateAMovieWithoutSession(String movieId, String payload){
        Response response = client.post(createEndpoint("movie/" + movieId+"/rating"), payload, HeadersUtil.getDefaultHeaders()); // using post in place of put

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.prettyPrint());
        return response;
    }

    public Response deleteAMovieRating(String movieId){
        return client.delete(createEndpointWithSessionId("movie/" + movieId+"/rating"), HeadersUtil.getDefaultHeaders());
    }

    private String createEndpoint(String partialEndpoint){
        return partialEndpoint +  "?api_key="+ configManager.getConfigProperty("API_KEY");
    }

    private String createEndpointWithSessionId(String partialEndpoint){
        return partialEndpoint +  "?api_key="+ configManager.getConfigProperty("API_KEY")+ "&session_id=" +  configManager.getConfigProperty("SESSION_ID");
    }

}
