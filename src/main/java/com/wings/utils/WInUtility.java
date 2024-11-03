package com.wings.utils;

import java.io.IOException;

public class WInUtility {

    public static void runExe(String exeLocation) throws IOException {

        Runtime.getRuntime().exec(exeLocation);

    }
}
