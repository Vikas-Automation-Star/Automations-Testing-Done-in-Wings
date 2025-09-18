package com.wings.Technical;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUsersTest {
//    public static void main(String[] args) {
//
//        RestAssured.baseURI = "https://reqres.in";
//
//        // Send request and store response
//        Response response =
//                given().
//                        queryParam("page", 2).
//                        when().
//                        get("/api/users").
//                        then().
//                        statusCode(200).             // Validate status code
//                        body("page", equalTo(2))     // Validate "page" value
//                        .extract().response();       // Extract the response
//
//        // Print status and body
//        System.out.println("Status Code: " + response.getStatusCode());
//        System.out.println("Response Body:\n" + response.asPrettyString());
//    }

        public static void main(String[] args) {
            // Base URI
            RestAssured.baseURI = "https://reqres.in";

            // Send GET request
            Response response = RestAssured
                    .given()
                    .get("/api/users?page=2");

            // Print status code and body
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Body:");
            System.out.println(response.getBody().asString());
        }
    }