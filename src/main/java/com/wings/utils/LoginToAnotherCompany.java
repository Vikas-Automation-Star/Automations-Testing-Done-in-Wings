package com.wings.utils;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginToAnotherCompany {
    static WindowsDriver driver;
    public static String companyName ="WingsAutomatio24DNP", password="Welcome@123",userName="Super User";

    public static void loginToAnotherCompany() throws MalformedURLException {
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("app","C:\\Program Files (x86)\\Wings Infonet\\Wings Accounting 24DNP\\Wings.exe");
        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability("deviceName", "WindowsPC");
        driver=new WindowsDriver<>(new URL("http://127.0.0.1:4723/"),capabilities);
        driver.manage().window().maximize();
        driver.findElementByXPath("//Text/*[@Name='Login to another company']").click();
        driver.findElementByXPath("//Edit[@Name='Company']").sendKeys(companyName);
        driver.findElementByXPath("//Edit[@Name='User name']").sendKeys(userName);
        driver.findElementByXPath("//Edit[@Name='Password']").sendKeys(password);
        driver.findElementByXPath("//Button[@Name='Submit']").click();
        driver.findElementByXPath("//Button[@Name='OK']").click();
        String title=driver.getTitle();
        System.out.println(title);
        Assert.assertEquals("Wings Accounting 24DNP - PRO [ WingsAutomatio24DNP ; 01-04-2024 To 31-03-2025 ; Super User ]",title);
        System.out.println("Successfully Logged into new Company");
        driver.findElementByName("Close").click();
        driver.findElementByName("Yes").click();
    }

    public static void main(String args[]) throws MalformedURLException {
        loginToAnotherCompany();
    }
}

