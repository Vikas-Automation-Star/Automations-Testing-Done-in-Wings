package com.wings.utils;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;

public class createCompany {
    WindowsDriver driver,logindriver;
    public static String CompanyName="Dup_Company", Password="Wings@123";
    public void login() throws IOException, InterruptedException {
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("app","C:\\Program Files (x86)\\Wings Infonet\\Wings Accounting 24DNP\\Wings.exe");
        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability("deviceName", "WindowsPC");
        driver=new WindowsDriver<>(new URL("http://127.0.0.1:4723/"),capabilities);
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.findElementByXPath("//Pane[@Name='Manage companies']/Text[@Name='Create a new company']/*[@Name='Create a new company']").click();
        Thread.sleep(3000);
        System.out.println("Legacy Accessible State Back :"+driver.findElementByXPath("//Button[@Name='< Back']").getAttribute("LegacyState"));
        System.out.println("Legacy Accessible State Create :"+driver.findElementByXPath("//Button[@Name='Create']").getAttribute("LegacyState"));
        driver.findElementByXPath("//Button[@Name='Create']").click();
        Thread.sleep(3000);
        System.out.println("Legacy Accessible State :"+driver.findElementByXPath("//Button[@Name='Next']").getAttribute("LegacyState"));
        driver.findElementByXPath("//Button[@Name='Next']").click();
        Thread.sleep(3000);

        driver.findElementByXPath("//Edit[@Name='Login Name']").sendKeys(CompanyName);
        driver.findElementByXPath("//Edit[@Name='Company Name']").sendKeys(CompanyName);
        System.out.println("Legacy Accessible State :"+driver.findElementByXPath("//Button[@Name='Next']").getAttribute("LegacyState"));
        driver.findElementByXPath("//Edit[@Name='Password']").sendKeys(Password);
        driver.findElementByXPath("//Edit[@Name='Confirm Password']").sendKeys(Password);
        driver.findElementByName("Advanced Options").click();
        System.out.println("Legacy Accessible State :"+driver.findElementByXPath("//Button[@Name='Next']").getAttribute("LegacyState"));

        driver.findElementByXPath("//Button[@Name='Next']").click();
        driver.findElementByName("OK").click();
        Thread.sleep(1500);
        driver.findElementByXPath("//Edit[@Name='Database ServerRequestFunctions']").sendKeys("Madhuri");

        //Legacy Accessible State :1
        //Legacy Accessible State :3145728
        driver.findElementByXPath("//Button[@Name='Next']").click();

        Thread.sleep(240000);
        driver.findElementByXPath("//Button[@Name='Next']").click();
        Thread.sleep(3000);
        System.out.println("DB creation successful for "+CompanyName+" Company");
        Thread.sleep(3000);
        driver.findElementByXPath("//Button[@Name='Next']").click();
        Thread.sleep(3000);
        driver.findElementByXPath("//Button[@Name='Finish']").click();
        Thread.sleep(3000);
        driver.findElementByXPath("//Button[@Name='OK']").click();
        System.out.println("Company creation with name "+CompanyName+" is successful ");

        DesiredCapabilities logincapabilities=new DesiredCapabilities();
        logincapabilities.setCapability("app","C:\\Program Files (x86)\\Wings Infonet\\Wings Accounting 24DNP\\Wings.exe");
        logincapabilities.setCapability("platformName","Windows");
        logincapabilities.setCapability("deviceName","WindowsPC");
        logindriver = new WindowsDriver(new URL("http://127.0.0.1:4723"), logincapabilities);
        logindriver.manage().window().maximize();
        Thread.sleep(3000);
        logindriver.findElementByName("Wings 24");
        logindriver.findElementByXPath("//Pane[@Name='Choose a recent company']/Text[@Name='> "+CompanyName+"']/*[@Name='> "+CompanyName+"']").click();
        Thread.sleep(3000);
        logindriver.findElementByXPath("//Edit[@Name='Password']").sendKeys(Password);
        logindriver.findElementByXPath("//Button[@Name='Submit']").click();
        Thread.sleep(5000);
        logindriver.findElement(By.name("OK")).click();
        Thread.sleep(2000);
        System.out.println("Super User Login for "+CompanyName+" company is successful " +new String(Character.toChars(0x2705)));


        logindriver.findElement(By.name("File")).click();
        logindriver.findElement(By.name("Company")).click();
        logindriver.findElement(By.name("Sales")).click();
        logindriver.findElement(By.xpath("//TabItem[@Name='Configure']")).click();
        Thread.sleep(2000);
        System.out.println("Menu navigation for Super User is successful for company: "+CompanyName);
        logindriver.findElement(By.name("Purchase")).click();
        Thread.sleep(2000);
        System.out.println("Overall Company creation test script ran successfully without any issues! "+new String(Character.toChars(0x1F349)));
  }
  public void logout(){
        logindriver.findElementByName("Close").click();
        logindriver.findElementByName("Yes").click();
  }

    public static void main(String[] args) throws IOException, InterruptedException {
        createCompany launch=new createCompany();
        launch.login();
        launch.logout();
    }
}
