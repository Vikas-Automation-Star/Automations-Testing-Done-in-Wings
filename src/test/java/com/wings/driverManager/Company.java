package com.wings.driverManager;

import com.wings.Utils.WInUtility;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;


public class Company {
    public static String CompanyName="WingsAutoDemo7";
    public static String Password="Welcome@123";


    public static void main(String[] args) throws IOException, InterruptedException {

        Instant instant= Instant.now();
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("app","C:\\Program Files (x86)\\Wings Infonet\\Wings Books 24D\\Wings.exe");
        capabilities.setCapability("platformName","Windows");
        capabilities.setCapability("deviceName","WindowsPC");
        WindowsDriver driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);

        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.findElementByName("Wings 24");
        driver.findElementByXPath("//Pane[@Name='Manage companies']/Text[@Name='Create a new company']/*[@Name='Create a new company']").click();
        //driver.findElementByXPath("//Hyperlink[@Name='Create a new company']").click();
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
        Thread.sleep(3000);
        System.out.println("Legacy Accessible State :"+driver.findElementByXPath("//Button[@Name='Next']").getAttribute("LegacyState"));
        driver.findElementByXPath("//Button[@Name='Next']").click();

        //Legacy Accessible State :1
        //Legacy Accessible State :3145728
        Thread.sleep(240000);
        driver.findElementByXPath("//Button[@Name='Next']").click();
        System.out.println("DB creation successful for "+CompanyName+" Company");
        Thread.sleep(3000);
        driver.findElementByXPath("//Button[@Name='Next']").click();
        Thread.sleep(3000);
        driver.findElementByXPath("//Button[@Name='Finish']").click();
        Thread.sleep(3000);
        driver.findElementByXPath("//Button[@Name='OK']").click();
        System.out.println("Company creation with name "+CompanyName+" is successful");

        DesiredCapabilities logincapabilities=new DesiredCapabilities();
        logincapabilities.setCapability("app","C:\\Program Files (x86)\\Wings Infonet\\Wings Books 24D\\Wings.exe");
        logincapabilities.setCapability("platformName","Windows");
        logincapabilities.setCapability("deviceName","WindowsPC");
        WindowsDriver logindriver = new WindowsDriver(new URL("http://127.0.0.1:4723"), logincapabilities);

        logindriver.manage().window().maximize();
        Thread.sleep(3000);
        logindriver.findElementByName("Wings 24");
        logindriver.findElementByXPath("//Pane[@Name='Choose a recent company']/Text[@Name='> "+CompanyName+"']/*[@Name='> "+CompanyName+"']").click();
        Thread.sleep(3000);
        logindriver.findElementByXPath("//Edit[@Name='Password']").sendKeys(Password);
        logindriver.findElementByXPath("//Button[@Name='Submit']").click();
        Thread.sleep(10000);
        logindriver.findElement(By.name("OK")).click();
        Thread.sleep(2000);
        System.out.println("Super User Login for "+CompanyName+" company is successful");
        logindriver.findElement(By.name("File")).click();
        logindriver.findElement(By.name("Company")).click();
        logindriver.findElement(By.name("Sales")).click();
        logindriver.findElement(By.xpath("//TabItem[@Name='Configure']")).click();
        Thread.sleep(2000);
        System.out.println("Menu navigation for Super User is successful for company: "+CompanyName);
        logindriver.findElement(By.name("Purchase")).click();
        Thread.sleep(2000);
        logindriver.findElement(By.xpath("//MenuItem[@Name='Suppliers']")).click();
        Thread.sleep(3000);
        System.out.println("Overall Company creation test script ran successfully without any issues! "+new String(Character.toChars(0x1F349)));

    }

}
