package org.zilch.com.api.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class JsonUtil {

    static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String serialize(T object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }

    public static <T> T deserialize(Response response, Class<T> myClass) throws Exception {
        return objectMapper.readValue(response.asString(), myClass);
    }

}
