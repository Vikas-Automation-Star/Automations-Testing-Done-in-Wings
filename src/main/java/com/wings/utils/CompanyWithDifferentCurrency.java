package com.wings.utils;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

public class CompanyWithDifferentCurrency {
    static WindowsDriver driver,logindriver;
    static String currencyText,stockValuationText;
    public static String CompanyName="WingsAutomationTestingBooks", Password="Welcome@123",registrationNum="998921",panNum="AAACW7753P";

    public static void login() throws IOException, InterruptedException, AWTException {
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
        driver.findElementByXPath("//Edit[@Name='Database ServerRequestFunctions']").sendKeys("Vikas");
        driver.findElementByXPath("//Button[@Name='Next']").click();

        Thread.sleep(240000);
        driver.findElementByXPath("//Group[@Name='Specify Country and Currency Prefix']/Pane/Pane/ComboBox[@Name='Country']").click();
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2500);
        WebElement currency=driver.findElementByXPath("//Group[@Name='Specify Country and Currency Prefix']/Pane/Pane/Edit[@Name='Company Currency']");
        currencyText=currency.getText();
        System.out.println(currencyText);

        driver.findElementByXPath("//Button[@Name='Next']").click();
        Thread.sleep(3000);
        driver.findElementByXPath("//ComboBox[@Name='Stock Valuation Method']").click();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2500);
        WebElement stockValuation =driver.findElementByXPath("//ComboBox[@Name='Stock Valuation Method']");
        stockValuationText= stockValuation.getText();
        System.out.println(stockValuationText);
        Thread.sleep(2000);
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

        //validate
        logindriver.findElement(By.name("Company")).click();
        logindriver.findElementByXPath("//MenuItem[@Name='Company Properties']").click();
        Thread.sleep(2000);
        logindriver.findElementByXPath("//Edit[@Name='Registration Certificate No']").sendKeys(registrationNum);
        logindriver.findElementByXPath("//Edit[@Name='PAN No']").sendKeys(panNum);
        logindriver.findElementByXPath("//Button[@Name='...']").click();
        Thread.sleep(1000);
        logindriver.findElementByXPath("//Edit[@Name='Company']").sendKeys(CompanyName);
        logindriver.findElementByXPath("//Edit[@Name='Address 1']").sendKeys("HimayathNagar");
        logindriver.findElementByXPath("//Edit[@Name='Address 2']").sendKeys("PaigaPlaza");
        logindriver.findElementByXPath("//Edit[@Name='Address 3']").sendKeys("BasheerBagh");
        logindriver.findElementByXPath("//Edit[@Name='City']").sendKeys("Hyderabad");
        WebElement country= logindriver.findElementByXPath("//Edit[@Name='Country']/Button[@Name='Open']");
        country.click();
        country.sendKeys("india", Keys.ENTER);
        logindriver.findElementByXPath("//Edit[@Name='Zip']").sendKeys("500001");
        logindriver.findElementByXPath("//Edit[@Name='Telephones 1']").sendKeys("9989211079");
        logindriver.findElementByXPath("//Edit[@Name='Telephones 2']").sendKeys("7968768787");
        logindriver.findElementByXPath("//Edit[@Name='Telephones 3']").sendKeys("9654123458");
        logindriver.findElementByXPath("//Edit[@Name='Telephones 4']").sendKeys("9632145874");
        logindriver.findElementByXPath("//Edit[@Name='Fax']").sendKeys("QuickFax");
        logindriver.findElementByXPath("//Edit[@Name='Email']").sendKeys("Wingsinfo.net@gmail.com");
        logindriver.findElementByXPath("//Edit[@Name='Website']").sendKeys("https://www.wingsinfo.net/",Keys.TAB,Keys.ENTER);
        Thread.sleep(1000);
//        logindriver.findElementByXPath("//Button[@Name='Ok']");
        WebElement currency1=logindriver.findElementByXPath("//Edit[@Name='Currency']");
        String currency2=currency1.getText();
        Assert.assertEquals(currency2,currencyText,"both currencies must be match if not validation failed");
        System.out.println("Currency verified");

        Thread.sleep(1000);
        WebElement stock1=logindriver.findElementByXPath("//Edit[@Name='Stock Valuation Method']");
        String stock2=stock1.getText();
        Assert.assertEquals(stock2,stockValuationText,"both stock valuation must be match if not validation failed");
        System.out.println("Stock valuation Method verified");

        logindriver.findElementByXPath("//Edit[@Name='Short Code']").sendKeys("998921");
        logindriver.findElementByXPath("//Button[@Name='Save']").click();
        logindriver.findElementByXPath("//Button[@Name='OK']").click();
    }
    public static void logout(){
        logindriver.findElementByXPath("//MenuItem[@Name='File']").click();
        logindriver.findElementByXPath("//MenuItem[@Name='Exit']").click();
        logindriver.findElementByXPath("//Button[@Name='Yes']").click();
    }

    public static void main(String[] args) throws IOException, InterruptedException, AWTException {
        CompanyWithDifferentCurrency.login();
        CompanyWithDifferentCurrency.logout();
    }
}
