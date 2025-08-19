package com.wings.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;


public class APIClient {
    public static String userID = null;
    public static String sessionId = null;
    public static Map<String, String> headers = new HashMap<>();

    private static final String LOGIN_FILE = "./src/test/resources/APILogin.json";
    private static final String POSTING_DATA_FILE = "./src/test/resources/APIGetPostingData.json";


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


    public static void validateAPIWithExcel(String updatedTransactionNum,String tempAPIBodyUpdate,String writeAPIResponse,String outputFile,String diffFile) throws IOException {
        String voucherSeries = updatedTransactionNum.replaceAll("\\d", "");
        System.out.println("VoucherString :"+voucherSeries);
        String voucherNumber = updatedTransactionNum .replaceAll("\\D", "");
        System.out.println("VoucherNumber :"+voucherNumber);

        // Authenticate once and store credentials for reuse
        Response loginResponse = APIClient.APIAuth(LOGIN_FILE);
        assertEquals(loginResponse.getStatusCode(), 200, "Login failed!");
        // Verify mandatory fields in login response
        Assert.assertNotNull(APIClient.userID, "UserId is null in login response");
        Assert.assertNotNull(APIClient.sessionId, "SessionId is null in login response");

        String jsonTemplate = new String(Files.readAllBytes(Paths.get(POSTING_DATA_FILE)));
        // Update sessionId, userId and String list dynamically
        List<String> updatedList = Arrays.asList(voucherSeries, voucherNumber);
        System.out.println("updated StringList "+updatedList);// Replace with your desired list

        Map<String, Object> updateMap = Map.of(
                "$.userId", APIClient.userID,
                "$.sessionId", APIClient.sessionId,
                "$.stringList", updatedList
        );

        String updatedJson = JsonUpdater.updateJson(jsonTemplate, updateMap);
        // Save updated JSON temporarily (optional if APIClient can accept raw JSON)
        Files.write(Paths.get(tempAPIBodyUpdate), updatedJson.getBytes());

        Response response = APIClient.callApi("POST", "http://10.10.10.90:8080/WingsApi/api/Process/Handler",tempAPIBodyUpdate);
        assertStatusCode(response, 200);
//        System.out.println(response.asString());
        Files.write(Paths.get(writeAPIResponse), response.asString().getBytes());
        AdvancedJsonExcelComparatorNew.JsonExcelComparator(writeAPIResponse,outputFile,diffFile);

    }

    private static void assertStatusCode(Response response, int expected) {
        assertEquals(response.getStatusCode(), expected, "Unexpected status code: " + response.getStatusCode());
    }

}
