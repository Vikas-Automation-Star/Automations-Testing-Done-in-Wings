package com.wings.utils;

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

    public static String currentDateAndTime(){
        // Get current time in milliseconds
        long currentMillis = System.currentTimeMillis();
        // Convert milliseconds to LocalDateTime
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(currentMillis), ZoneId.systemDefault());
        // Format the time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy-hh-mm-ss_a");
        return dateTime.format(formatter);
    }

    public static void main(String[] args) {
        System.out.println(currentDateAndTime());
    }
}