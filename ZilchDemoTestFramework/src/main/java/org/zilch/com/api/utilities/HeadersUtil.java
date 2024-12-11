package org.zilch.com.api.utilities;

import java.util.HashMap;
import java.util.Map;

public class HeadersUtil {

    public static Map<String, String> getDefaultHeaders() {
        Map<String, String> headers = new HashMap<>();
//        headers.put("Authorization", "Bearer "); // Replace with actual token or key
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("User-Agent", "RestAssured/Java");
        headers.put("Cache-Control", "no-cache");
        return headers;
    }

    public static Map<String, String> getCustomHeaders(Map<String, String> customHeaders) {
        Map<String, String> headers = getDefaultHeaders();
        if (customHeaders != null) {
            headers.putAll(customHeaders); // Add or override headers with custom values
        }
        return headers;
    }
}

