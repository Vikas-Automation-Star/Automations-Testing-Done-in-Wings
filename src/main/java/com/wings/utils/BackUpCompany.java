package com.wings.utils;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class BackUpCompany {

    WindowsDriver driver;

    public void backUp() throws IOException, InterruptedException, AWTException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "C:\\Program Files (x86)\\Wings Infonet\\Wings Accounting 24DNP\\Wings.exe");
        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability("deviceName", "WindowsPC");
        driver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), capabilities);
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.findElementByXPath("//Pane[@Name='Manage companies']/Text[@Name='Backup a company']/*[@Name='Backup a company']").click();
        Thread.sleep(3000);
        driver.findElementByXPath("//Button[@Name='Next >']").click();
        Thread.sleep(3000);
        driver.findElementByXPath("//Pane/ComboBox[@Name='Company']/Button[@Name='Open']").click();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        WebElement name = driver.findElementByXPath("//Edit[@Name='Company']");
        String backup = name.getText();
        System.out.println(backup);
        driver.findElementByXPath("//Pane/Button[@Name='...']").click();
        driver.findElementByXPath("//TreeItem[@Name='New Volume (E:)']").click();
        driver.findElementByXPath("//ListItem[@Name='backup']").click();
        driver.findElementByXPath("//Button[@Name='Select Folder']").click();
        Thread.sleep(2500);
        driver.findElementByXPath("//Button[@Name='Next >']").click();
        driver.findElementByXPath("//Button[@Name='Finish']").click();
        //verify
        String backupName = backup.trim() + ".bak";
        System.out.println("Expected Backup File Name: " + backupName);

        String systemBackupFolderPath = "E:\\backup";
        File backupFolder = new File(systemBackupFolderPath);

        if (backupFolder.exists() && backupFolder.isDirectory()) {
            System.out.println("Backup folder exists: " + systemBackupFolderPath);

            // Check if backupName exists in the folder
            File[] files = backupFolder.listFiles();
            boolean isBackupPresent = false;

            if (files != null && files.length > 0) {
                System.out.println("Files in the backup folder:");
                for (File file : files) {
                    System.out.println(" - " + file.getName()); // Log file names
                    if (file.isFile() && file.getName().equalsIgnoreCase(backupName)) {
                        isBackupPresent = true;
                        System.out.println("Backup file found: " + file.getName());
                        break;
                    }
                }
            }

            if (isBackupPresent) {
                System.out.println("Backup file verified successfully!");
            } else {
                System.out.println("Backup file does not exist in the specified backup folder.");
            }
        } else {
            System.out.println("Backup folder does not exist.");
        }
    }

        public static void main (String[]args) throws IOException, InterruptedException, AWTException {
            BackUpCompany backUpCompany=new BackUpCompany();
            backUpCompany.backUp();
        }
    }

