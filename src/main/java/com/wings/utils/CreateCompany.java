package com.wings.utils;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.IOException;
import java.net.URL;

public class CreateCompany {
    WindowsDriver driver, logindriver;
    public static String CompanyName = "Wings_Dummy", Password = "Wings@123",currencyText,stockValuationText,registrationNum = "998921", panNum = "AEKPE1471P";

    public void login() throws IOException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "C:\\Program Files (x86)\\Wings Infonet\\Wings Books 24D\\Wings.exe");
        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability("deviceName", "WindowsPC");
        driver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), capabilities);
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.findElementByXPath("//Pane[@Name='Manage companies']/Text[@Name='Create a new company']/*[@Name='Create a new company']").click();
        Thread.sleep(1500);
        System.out.println("Legacy Accessible State Back :" + driver.findElementByXPath("//Button[@Name='< Back']").getAttribute("LegacyState"));
        System.out.println("Legacy Accessible State Create :" + driver.findElementByXPath("//Button[@Name='Create']").getAttribute("LegacyState"));
        driver.findElementByXPath("//Button[@Name='Create']").click();
        Thread.sleep(1500);
        System.out.println("Legacy Accessible State :" + driver.findElementByXPath("//Button[@Name='Next']").getAttribute("LegacyState"));
        driver.findElementByXPath("//Button[@Name='Next']").click();
        Thread.sleep(3000);

        driver.findElementByXPath("//Edit[@Name='Login Name']").sendKeys(CompanyName);
        driver.findElementByXPath("//Edit[@Name='Company Name']").sendKeys(CompanyName);
        System.out.println("Legacy Accessible State :" + driver.findElementByXPath("//Button[@Name='Next']").getAttribute("LegacyState"));
        driver.findElementByXPath("//Edit[@Name='Password']").sendKeys(Password);
        driver.findElementByXPath("//Edit[@Name='Confirm Password']").sendKeys(Password);
        driver.findElementByName("Advanced Options").click();
        System.out.println("Legacy Accessible State :" + driver.findElementByXPath("//Button[@Name='Next']").getAttribute("LegacyState"));

        driver.findElementByXPath("//Button[@Name='Next']").click();
        driver.findElementByName("OK").click();
        Thread.sleep(1500);
        driver.findElementByXPath("//Edit[@Name='Database ServerRequestFunctions']").sendKeys("Madhuri");
        driver.findElementByXPath("//Button[@Name='Next']").click();

        Thread.sleep(200000);
        WebElement currency = driver.findElementByXPath("//Group[@Name='Specify Country and Currency Prefix']/Pane/Pane/Edit[@Name='Company Currency']");
        currencyText = currency.getText();
        System.out.println("currencyText :- "+currencyText);
        driver.findElementByXPath("//Button[@Name='Next']").click();
        Thread.sleep(1500);
        WebElement stockValuation = driver.findElementByXPath("//ComboBox[@Name='Stock Valuation Method']");
        stockValuationText = stockValuation.getText();
        System.out.println("StockValuationTest :- "+stockValuationText);
        System.out.println("DB creation successful for " + CompanyName + " Company");
        Thread.sleep(1500);
        driver.findElementByXPath("//Button[@Name='Next']").click();
        Thread.sleep(1500);
        driver.findElementByXPath("//Button[@Name='Finish']").click();
        Thread.sleep(1500);
        driver.findElementByXPath("//Button[@Name='OK']").click();
        System.out.println("Company creation with name " + CompanyName + " is successful ");

        DesiredCapabilities logincapabilities = new DesiredCapabilities();
        logincapabilities.setCapability("app", "C:\\Program Files (x86)\\Wings Infonet\\Wings Books 24D\\Wings.exe");
        logincapabilities.setCapability("platformName", "Windows");
        logincapabilities.setCapability("deviceName", "WindowsPC");
        logindriver = new WindowsDriver(new URL("http://127.0.0.1:4723"), logincapabilities);
        logindriver.manage().window().maximize();
        Thread.sleep(3000);
        logindriver.findElementByName("Wings 24");
        logindriver.findElementByXPath("//Pane[@Name='Choose a recent company']/Text[@Name='> " + CompanyName + "']/*[@Name='> " + CompanyName + "']").click();
        Thread.sleep(3000);
        logindriver.findElementByXPath("//Edit[@Name='Password']").sendKeys(Password);
        logindriver.findElementByXPath("//Button[@Name='Submit']").click();
        Thread.sleep(5000);
        logindriver.findElement(By.name("OK")).click();
        Thread.sleep(2000);
        System.out.println("Super User Login for " + CompanyName + " company is successful " + new String(Character.toChars(0x2705)));


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
        WebElement country = logindriver.findElementByXPath("//Edit[@Name='Country']/Button[@Name='Open']");
        country.click();
        country.sendKeys("india", Keys.ENTER);
        logindriver.findElementByXPath("//Edit[@Name='Zip']").sendKeys("500001");
        logindriver.findElementByXPath("//Edit[@Name='Telephones 1']").sendKeys("9989211079");
        logindriver.findElementByXPath("//Edit[@Name='Telephones 2']").sendKeys("7968768787");
        logindriver.findElementByXPath("//Edit[@Name='Telephones 3']").sendKeys("9654123458");
        logindriver.findElementByXPath("//Edit[@Name='Telephones 4']").sendKeys("9632145874");
        logindriver.findElementByXPath("//Edit[@Name='Fax']").sendKeys("QuickFax");
        logindriver.findElementByXPath("//Edit[@Name='Email']").sendKeys("Wingsinfo.net@gmail.com");
        logindriver.findElementByXPath("//Edit[@Name='Website']").sendKeys("https://www.wingsinfo.net/", Keys.TAB, Keys.ENTER);
        Thread.sleep(1000);
        String actualCurrency = logindriver.findElementByXPath("//Edit[@Name='Currency']").getText();
        System.out.println("actual Currency: " + actualCurrency);
        System.out.println("Expected Currency: "+ currencyText);
        Assert.assertEquals(actualCurrency, currencyText, "both currencies must be match if not validation failed");
        System.out.println("Currency verified");
        Thread.sleep(1000);
        String actualStock = logindriver.findElementByXPath("//Edit[@Name='Stock Valuation Method']").getText();
        System.out.println("Actual Stock: "+ actualStock);
        System.out.println("Expected Stock: "+ stockValuationText);
        Assert.assertEquals(actualStock, stockValuationText, "both stock valuation must be match if not validation failed");
        System.out.println("Stock valuation Method verified");

        logindriver.findElementByXPath("//Edit[@Name='Short Code']").sendKeys("998921");
        logindriver.findElementByXPath("//Button[@Name='Save']").click();
        logindriver.findElementByXPath("//Button[@Name='OK']").click();
        System.out.println("Overall Company creation test script ran successfully without any issues! " + new String(Character.toChars(0x1F349)));
    }

    public void logout() {

        logindriver.findElementByXPath("//MenuItem[@Name='File']").click();
        logindriver.findElementByXPath("//MenuItem[@Name='Exit']").click();
        logindriver.findElementByXPath("//Button[@Name='Yes']").click();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        CreateCompany launch = new CreateCompany();
        launch.login();
        launch.logout();
    }
}
