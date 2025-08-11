package com.wings.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.File;
import java.util.HashMap;
import java.util.Map;



public class APIClient {
    public static String userID = null;
    public static String sessionId = null;
    public static Map<String, String> headers = new HashMap<>();

    /**
     * Calls an API endpoint with optional body (JSON file path or raw JSON string).
     *
     * @param method       HTTP method (GET, POST, PATCH, DELETE)
     * @param endpoint     API endpoint URL
     * @param bodyPathOrJson Optional: JSON file path or raw JSON string for POST/PATCH
     * @param isRawJson    true if bodyPathOrJson is raw JSON, false if it's a file path
     * @return Response object
     */
    public static Response callApi(String method, String endpoint, String bodyPathOrJson, boolean isRawJson) {
        headers.put("Content-Type", "application/json");

        // Attach stored session headers if available
        if (userID != null && sessionId != null) {
            headers.put("userId", userID);
            headers.put("sessionId", sessionId);
        }

        RequestSpecification request = RestAssured.given();

        if (!headers.isEmpty()) {
            request.headers(headers);
        }

        // Attach body for POST/PATCH if present
        if (bodyPathOrJson != null && !bodyPathOrJson.isEmpty()) {
            if (isRawJson) {
                request.body(bodyPathOrJson);
            } else {
                File bodyFile = new File(bodyPathOrJson);
                request.body(bodyFile);
            }
        }

        switch (method.toUpperCase()) {
            case "GET":
                return request.get(endpoint).then().extract().response();
            case "POST":
                return request.post(endpoint).then().extract().response();
            case "PATCH":
                return request.patch(endpoint).then().extract().response();
            case "DELETE":
                return request.delete(endpoint).then().extract().response();
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
    }

    /** Overloaded method for backward compatibility (file path only) */
    public static Response callApi(String method, String endpoint, String bodyPath) {
        return callApi(method, endpoint, bodyPath, false);
    }

    /** Performs API authentication and stores UserId & SessionId */
    public static Response APIAuth(String loginFile) {
        var response = callApi("POST", "http://10.10.10.90:8080/WingsApi/api/Login/SignIn", loginFile, false);
        userID = response.getBody().jsonPath().getString("UserId");
        sessionId = response.getBody().jsonPath().getString("SessionId");
        System.out.println("UserId :" + userID);
        System.out.println("SessionId :" + sessionId);
        Assert.assertTrue(response.getStatusCode() < 300, "API returned error: " + response.getStatusLine());
        return response;
    }

}
