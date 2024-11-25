package com.wings.utils;
import java.io.File;
import java.io.IOException;
public class AllureReportsBackup {

    // Path to your Allure results and backup folder
    // Set your Allure results path here
//  private static final String allureResultsPath = "./allure-results";
    // Set your backup folder path here
    private static final String backupFolderPath = "./backup";

//        public static void main(String[] args) {
//            try {
//                //  Check if the Allure results folder exists
//                File allureResultsDir = new File(allureResultsPath);
//                if (allureResultsDir.exists() && allureResultsDir.isDirectory()) {
//                    //  Move old Allure results to the backup folder
//                    moveAllureResultsToBackup(allureResultsDir);
//                } else {
//                    System.out.println("No Allure results found.");
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

    public static void moveAllureResultsToBackup(File allureResultsDir) throws IOException {
        // Check if the backup folder exists, if not, create it
        File backupDir = new File(backupFolderPath);
        if (!backupDir.exists()) {
            backupDir.mkdirs();
        }
        // Get the list of files in the Allure results directory
        File[] files = allureResultsDir.listFiles();
        if (files != null && files.length > 0) {
            //  Move each file to the backup folder
            for (File file : files) {
                File backupFile = new File(backupFolderPath + File.separator + file.getName());
                if (file.renameTo(backupFile)) {
                    System.out.println("Moved: " + file.getName() + " to backup folder.");
                } else {
                    System.out.println("Failed to move: " + file.getName());
                }
            }
        } else {
            System.out.println("No files found in the Allure results directory.");

        }
        // Optionally, delete the Allure results directory after moving the files
        allureResultsDir.delete();
    }
}

