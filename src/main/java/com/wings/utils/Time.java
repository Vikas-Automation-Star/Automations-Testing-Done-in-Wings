package com.wings.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Time {
    public Long getEpoch() {
        Long currentTimestamp = Instant.now().getEpochSecond();
        System.out.println("Current epoch timestamp in millis: " + currentTimestamp);
        return currentTimestamp;
    }

    public String getLocalDateTime(long epochTimeMillis) {
        // Example epoch time in milliseconds
        Instant instant = Instant.ofEpochMilli(epochTimeMillis);
        ZoneId zoneId = ZoneId.systemDefault();
        // Use the system default time zone
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);
        System.out.println(formattedDateTime);
        return formattedDateTime;
    }

    public static String timeStamp() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return now.format(formatter);
    }

    public static String getFormattedFutureDate(int daysToAdd) {
        LocalDate futureDate = LocalDate.now().plusDays(daysToAdd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return futureDate.format(formatter);
    }

    public static String currentDateAndTime(){
        // Get current time in milliseconds
        long currentMillis = System.currentTimeMillis();
        // Convert milliseconds to LocalDateTime
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(currentMillis), ZoneId.systemDefault());
        // Format the time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy-hh-mm-ss_a");
        return dateTime.format(formatter);
    }

//    public static void main(String[] args) throws Exception {
//        try {
//            // Step 1: Create the folder (if it doesn't exist)
//            Path folderPath = Paths.get("C:/example/newFolder");
//            Files.createDirectories(folderPath);
//            // Step 2: Create the text file inside the folder
//            Path filePath = folderPath.resolve("sample.txt"); // "C:/example/newFolder/sample.txt"
//            // Step 3: Write some content to the file
//            String content = "This is a test file.\nHello from Java!.\nknowing the creation flow. \nknowing the moving flow";
//            Files.write(filePath, content.getBytes(StandardCharsets.UTF_8));
//            System.out.println("Text file created at: " + filePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Path sourceDir = Paths.get("C:/example/newFolder");
//        Path targetDir = Paths.get("C:\\vicks"); // <-- Note this change
//
//        Files.move(sourceDir, targetDir, StandardCopyOption.REPLACE_EXISTING);
//        Files.deleteIfExists(Path.of("C:/example/newFolder"));
//        System.out.println("Folder and its contents moved successfully!");
//    }

    public static void main(String[] args) {
        System.out.println(getFormattedFutureDate(3));
    }
}