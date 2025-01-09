package com.wings.utils;

import java.time.Instant;
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

    public String timeStamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }
}