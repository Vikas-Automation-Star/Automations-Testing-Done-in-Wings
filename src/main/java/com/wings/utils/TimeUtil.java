package com.wings.utils;

import java.time.Instant;

public class TimeUtil {


    public long getEpocTime() {

        Instant instant = Instant.now();
        long epoch = instant.getEpochSecond();
        System.out.println("Epoch time :" + epoch);
        return epoch;

    }

    public  String convertTime(long milliseconds) {
        String exeTime;
        if (milliseconds < 60000) {
            exeTime=String.valueOf((int)(milliseconds / 1000.0))+" sec";
        } else {
            exeTime=String.valueOf((int)(milliseconds / (1000.0*60)))+" min";
        }
        return exeTime;
    }
}
