package com.wings.utils;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.IOException;
import java.net.URL;

public class CreateADuplicateCompany {
    WindowsDriver driver;
    public static String CompanyName = "Dup_Company", Password = "Wings@123";

    public void login() throws IOException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "C:\\Program Files (x86)\\Wings Infonet\\Wings Accounting 24DNP\\Wings.exe");
        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability("deviceName", "WindowsPC");
        driver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), capabilities);
        driver.manage().window().maximize();
        Thread.sleep(1000);
        driver.findElementByXPath("//Pane[@Name='Manage companies']/Text[@Name='Create a new company']/*[@Name='Create a new company']").click();
        Thread.sleep(500);
        System.out.println("Legacy Accessible State Back :" + driver.findElementByXPath("//Button[@Name='< Back']").getAttribute("LegacyState"));
        System.out.println("Legacy Accessible State Create :" + driver.findElementByXPath("//Button[@Name='Create']").getAttribute("LegacyState"));
        driver.findElementByXPath("//Button[@Name='Create']").click();
        Thread.sleep(500);
        System.out.println("Legacy Accessible State :" + driver.findElementByXPath("//Button[@Name='Next']").getAttribute("LegacyState"));
        driver.findElementByXPath("//Button[@Name='Next']").click();
        Thread.sleep(500);

        driver.findElementByXPath("//Edit[@Name='Login Name']").sendKeys(CompanyName);
        driver.findElementByXPath("//Edit[@Name='Company Name']").sendKeys(CompanyName);
        System.out.println("Legacy Accessible State :" + driver.findElementByXPath("//Button[@Name='Next']").getAttribute("LegacyState"));
        driver.findElementByXPath("//Edit[@Name='Password']").sendKeys(Password);
        driver.findElementByXPath("//Edit[@Name='Confirm Password']").sendKeys(Password);
        driver.findElementByName("Advanced Options").click();
//        System.out.println("Legacy Accessible State :" + driver.findElementByXPath("//Button[@Name='Next']").getAttribute("LegacyState"));

        driver.findElementByXPath("//Button[@Name='Next']").click();
        driver.findElementByName("OK").click();
        Thread.sleep(500);
        driver.findElementByXPath("//Edit[@Name='Database ServerRequestFunctions']").sendKeys("Madhuri");
        driver.findElementByXPath("//Button[@Name='Next']").click();
        driver.findElementByXPath("//Window[@Name='Error']/Button[@Name='OK']"); //failed error msg window should be displayed here.(for now do in this way)
        System.out.println("Company Already Exists - Failed");
        driver.findElementByXPath("//Button[@Name='OK']").click();
        driver.findElementByXPath("//Button[@Name='Cancel']").click();
        driver.findElementByXPath("//Button[@Name='Yes']").click();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        CreateADuplicateCompany duplicateCompany=new CreateADuplicateCompany();
        duplicateCompany.login();

    }
}


