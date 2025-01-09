package com.wings.utils;

import java.time.Instant;

public class TimeUtil {

    public static void main(String[] args) {

        Instant instant = Instant.now();
        long epoch = instant.getEpochSecond();
        System.out.println("Epoch time :" + epoch);

    }
}
