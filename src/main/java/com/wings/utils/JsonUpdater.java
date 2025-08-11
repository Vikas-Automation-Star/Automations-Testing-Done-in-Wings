package com.wings.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class JsonUpdater {

    public static String updateJson(String json, String jsonPathExpr, Object newValue) {
        Configuration conf = Configuration.defaultConfiguration();
        DocumentContext context = JsonPath.using(conf).parse(json);
        context.set(jsonPathExpr, newValue);
        return context.jsonString();
    }

    public static String updateJson(String json, Map<String,Object> map) {
        Configuration conf = Configuration.defaultConfiguration();
        DocumentContext context = JsonPath.using(conf).parse(json);
        for (Map.Entry<String,Object> entry :map.entrySet()){
            context.set(entry.getKey(), entry.getValue());
        }
        return context.jsonString();
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Load JSON from file
        File jsonFile = new File("src/main/resources/sample.json");
        ObjectMapper mapper = new ObjectMapper();
        Object jsonObject = mapper.readValue(jsonFile, Object.class);
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);

        System.out.println("Original JSON:\n" + jsonString);

        // Ask for inputs
        System.out.print("Enter JSONPath to update (e.g., $.user.name): ");
        String jsonPath = scanner.nextLine();

        System.out.print("Enter new value: ");
        String newValue = scanner.nextLine();

        // Update the JSON
        String updatedJson = updateJson(jsonString, jsonPath, newValue);

        System.out.println("\nUpdated JSON:\n" + updatedJson);
    }

}
