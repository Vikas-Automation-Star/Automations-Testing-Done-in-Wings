package com.wings.pages.generalScripts;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.IOException;
import java.net.URL;

public class CreateCompany {
    WindowsDriver driver, loginDriver;
    public static String CompanyName = "BooksJuly1", Password = "Wings@123", currencyText,stockValuationText,registrationNum = "998921", panNum = "AEKPE1471P";

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
        Thread.sleep(1500);

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
        driver.findElementByXPath("//Edit[@Name='Database Server']").sendKeys("Vikas");
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
        loginDriver = new WindowsDriver(new URL("http://127.0.0.1:4723"), logincapabilities);
        loginDriver.manage().window().maximize();
        Thread.sleep(3000);
        loginDriver.findElementByName("Wings 24");
        loginDriver.findElementByXPath("//Pane[@Name='Choose a recent company']/Text[@Name='> " + CompanyName + "']/*[@Name='> " + CompanyName + "']").click();
        Thread.sleep(3000);
        loginDriver.findElementByXPath("//Edit[@Name='Password']").sendKeys(Password);
        loginDriver.findElementByXPath("//Button[@Name='Submit']").click();
        Thread.sleep(5000);
        loginDriver.findElement(By.name("OK")).click();
        Thread.sleep(2000);
        System.out.println("Super User Login for " + CompanyName + " company is successful " + new String(Character.toChars(0x2705)));


        loginDriver.findElement(By.name("Company")).click();
        loginDriver.findElementByXPath("//MenuItem[@Name='Company Properties']").click();
        Thread.sleep(2000);
        loginDriver.findElementByXPath("//Edit[@Name='Registration Certificate No']").sendKeys(registrationNum);
        loginDriver.findElementByXPath("//Edit[@Name='PAN No']").sendKeys(panNum);
        loginDriver.findElementByXPath("//Button[@Name='...']").click();
        Thread.sleep(1000);
        loginDriver.findElementByXPath("//Edit[@Name='Company']").sendKeys(CompanyName);
        loginDriver.findElementByXPath("//Edit[@Name='Address 1']").sendKeys("HimayathNagar");
        loginDriver.findElementByXPath("//Edit[@Name='Address 2']").sendKeys("PaigaPlaza");
        loginDriver.findElementByXPath("//Edit[@Name='Address 3']").sendKeys("BasheerBagh");
        loginDriver.findElementByXPath("//Edit[@Name='City']").sendKeys("Hyderabad");
        WebElement country = loginDriver.findElementByXPath("//Edit[@Name='Country']/Button[@Name='Open']");
        country.click();
        country.sendKeys("india", Keys.ENTER);
        loginDriver.findElementByXPath("//Edit[@Name='Zip']").sendKeys("500001");
        loginDriver.findElementByXPath("//Edit[@Name='Telephones 1']").sendKeys("9989211079");
        loginDriver.findElementByXPath("//Edit[@Name='Telephones 2']").sendKeys("7968768787");
        loginDriver.findElementByXPath("//Edit[@Name='Telephones 3']").sendKeys("9654123458");
        loginDriver.findElementByXPath("//Edit[@Name='Telephones 4']").sendKeys("9632145874");
        loginDriver.findElementByXPath("//Edit[@Name='Fax']").sendKeys("QuickFax");
        loginDriver.findElementByXPath("//Edit[@Name='Email']").sendKeys("Wingsinfo.net@gmail.com");
        loginDriver.findElementByXPath("//Edit[@Name='Website']").sendKeys("https://www.wingsinfo.net/", Keys.TAB, Keys.ENTER);
        Thread.sleep(1000);
        String actualCurrency = loginDriver.findElementByXPath("//Edit[@Name='Currency']").getText();
        System.out.println("actual Currency: " + actualCurrency);
        System.out.println("Expected Currency: "+ currencyText);
        Assert.assertEquals(actualCurrency, currencyText, "both currencies must be match if not validation failed");
        System.out.println("Currency verified");
        Thread.sleep(1000);
        String actualStock = loginDriver.findElementByXPath("//Edit[@Name='Stock Valuation Method']").getText();
        System.out.println("Actual Stock: "+ actualStock);
        System.out.println("Expected Stock: "+ stockValuationText);
        Assert.assertEquals(actualStock, stockValuationText, "both stock valuation must be match if not validation failed");
        System.out.println("Stock valuation Method verified");

        loginDriver.findElementByXPath("//Edit[@Name='Short Code']").sendKeys("998921");
        loginDriver.findElementByXPath("//Button[@Name='Save']").click();
        Thread.sleep(2500);
        loginDriver.findElementByXPath("//Button[@Name='OK']").click();
        System.out.println("Overall Company creation test script ran successfully without any issues! " + new String(Character.toChars(0x1F349)));
    }

    public void logout() {
        loginDriver.findElementByXPath("//MenuItem[@Name='File']").click();
        loginDriver.findElementByXPath("//MenuItem[@Name='Exit']").click();
        loginDriver.findElementByXPath("//Button[@Name='Yes']").click();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        CreateCompany launch = new CreateCompany();
        launch.login();
        launch.logout();
    }
}
