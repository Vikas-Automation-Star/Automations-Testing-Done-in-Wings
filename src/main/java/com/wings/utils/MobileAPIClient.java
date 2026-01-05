package com.wings.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MobileAPIClient {

    public static String userId = null;
    public static String sessionId = null;

    // Common headers for mobile APIs
    public static Map<String, String> headers = new HashMap<>();

    // Login JSON file path (mobile login request)
    private static final String MOBILE_LOGIN_FILE = "./src/test/resources/mobileAPILogin.json";

    public static Response callApi(String method, String endpoint, String jsonBodyFilePath) {
        RestAssured.useRelaxedHTTPSValidation();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        // If logged in, attach mobile session + userId
        if (userId != null && sessionId != null) {
            headers.put("userId", userId);
            headers.put("sessionId", sessionId);
        }

        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON).accept(ContentType.JSON);
        request.headers(headers);

        // Attach request body for POST/PATCH
        if (jsonBodyFilePath != null && !jsonBodyFilePath.isEmpty()) {
            File file = new File(jsonBodyFilePath);
            request.body(file);
        }

        switch (method.toUpperCase()) {
            case "POST": return request.post(endpoint).then().extract().response();
            case "GET": return request.get(endpoint).then().extract().response();
            case "PATCH": return request.patch(endpoint).then().extract().response();
            case "DELETE": return request.delete(endpoint).then().extract().response();
            default: throw new IllegalArgumentException("Invalid method: " + method);
        }
    }

    public static Response mobileLogin() throws IOException {
        String loginUrl = "https://10.10.10.90:8083/WingsApi/api/Login/SignIn";
        Response response = callApi("POST", loginUrl, MOBILE_LOGIN_FILE);

        String raw = response.asString();
        System.out.println("RAW LOGIN RESPONSE: " + raw);
        String cleaned = cleanJsonString(raw);
        JsonPath json = new JsonPath(cleaned);

        userId = json.getString("UserId");
        sessionId = json.getString("SessionId");

        System.out.println("Mobile API Login → UserId: " + userId);
        System.out.println("Mobile API Login → SessionId: " + sessionId);

        Assert.assertNotNull(userId, "Login failed: userId is null");
        Assert.assertNotNull(sessionId, "Login failed: sessionId is null");

        return response;

    }

    public static void testGetMenu(String filePath,String apiResponsePath) throws Exception {
        RestAssured.useRelaxedHTTPSValidation();
        MobileAPIClient.mobileLogin();
        String getTransactionBody = new String(Files.readAllBytes(Paths.get(filePath)));
        String updatedGetTransactionBody = updateBody(getTransactionBody, userId, sessionId);

        Response transactionResponse = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("userId", userId)
                .header("sessionId", sessionId)
                .body(updatedGetTransactionBody)
                .post("https://10.10.10.90:8083/WingsApi/api/Process/Handler");


        String basePath = apiResponsePath.replace(".json", "");
        String jsonFilePath = basePath + "_" + Time.timeStamp() + ".json";
        // Parse response string into a JSON object
        String rawResponse = transactionResponse.asString();
        ObjectMapper mapper = new ObjectMapper();
        String cleanedResponse = cleanJsonString(rawResponse);
        Object jsonObject = mapper.readValue(cleanedResponse, Object.class);

        // Save pretty-printed JSON to file
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFilePath), jsonObject);
        System.out.println("JSON response saved to: " + jsonFilePath);
        Assert.assertEquals(transactionResponse.getStatusCode(), 200);
    }

    public static void testTransactionFlow(String getTransactionFile, String getTransactionDetailsFile,String apiResponsePath,String transactionDetailsPath) throws Exception {
        RestAssured.useRelaxedHTTPSValidation();
        MobileAPIClient.mobileLogin();
        String getTransactionBody = new String(Files.readAllBytes(Paths.get(getTransactionFile)));
        String updatedGetTransactionBody = updateBody(getTransactionBody, userId, sessionId);

        Response transactionResponse = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("userId", userId)
                .header("sessionId", sessionId)
                .body(updatedGetTransactionBody)
                .post("https://10.10.10.90:8083/WingsApi/api/Process/Handler");


        String basePath = apiResponsePath.replace(".json", "");
        String jsonFilePath = basePath + "_" + Time.timeStamp() + ".json";
        // Parse response string into a JSON object
        String rawResponse = transactionResponse.asString();
        ObjectMapper mapper = new ObjectMapper();
        String cleanedResponse = cleanJsonString(rawResponse);
        Object jsonObject = mapper.readValue(cleanedResponse, Object.class);

        // Save pretty-printed JSON to file
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFilePath), jsonObject);
        System.out.println("JSON response saved to: " + jsonFilePath);
        Assert.assertEquals(transactionResponse.getStatusCode(), 200);


        // Step 3: Call getTransactionDetails
        String getTransactionDetailsBody = new String(Files.readAllBytes(Paths.get(getTransactionDetailsFile)));
        String updatedDetailsBody = updateBody(getTransactionDetailsBody, userId, sessionId);

        Response transactionDetailsResponse = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("userId", userId)
                .header("sessionId", sessionId)
                .body(updatedDetailsBody)
                .post("https://10.10.10.90:8083/WingsApi/api/Process/Handler");


        String basePath1 = transactionDetailsPath.replace(".json", "");
        String jsonFilePath1 = basePath1 + "_" + Time.timeStamp() + ".json";
        // Parse response string into a JSON object
        String raw = transactionDetailsResponse.asString();
        ObjectMapper mapper1 = new ObjectMapper();

        if (raw.startsWith("\"{")) {
            raw = raw.substring(1, raw.length() - 1);     // remove outer quotes
            raw = mapper.readValue("\"" + raw + "\"", String.class); // unescape JSON
        }

        // Save exactly as JSON
        Files.write(Paths.get(jsonFilePath1), raw.getBytes(StandardCharsets.UTF_8));
        System.out.println("JSON saved at: " + jsonFilePath1);
        Assert.assertEquals(transactionDetailsResponse.getStatusCode(), 200);
    }

    public static String updateBody(String json, String userId, String sessionId) {
        return json
                .replaceAll("\"userId\"\\s*:\\s*\"[^\"]*\"", "\"userId\": \"" + userId + "\"")
                .replaceAll("\"sessionId\"\\s*:\\s*\"[^\"]*\"", "\"sessionId\": \"" + sessionId + "\"");
    }

    public static String cleanJsonString(String raw) {
        if (raw.startsWith("\"") && raw.endsWith("\"")) {
            raw = raw.substring(1, raw.length() - 1);
        }
        return raw.replace("\\\"", "\"");
    }


}