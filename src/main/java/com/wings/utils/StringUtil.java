package com.wings.utils;

public class StringUtil {

    /***
     *  Return -1 if no number is found(implementation below in calling class)
     *  if (extractedNumber != -1) {
     *      System.out.println("Extracted number: " + extractedNumber);
     *  } else {
     *      System.out.println("No number found in the input string.");
     *  }
     * @param input
     * @return
     */
    public static double extractNumber(String input) {

        String regex = "\\d+(\\.\\d+)?"; // Matches integers or decimals
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String number = matcher.group(); // Extract the matched number
            return Double.parseDouble(number); // Convert to double
        }

        return -1;
    }

}
