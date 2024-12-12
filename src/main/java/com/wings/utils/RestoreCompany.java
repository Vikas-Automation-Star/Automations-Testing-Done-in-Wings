package com.wings.utils;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class RestoreCompany {
    static WindowsDriver driver;
    static  String companyName="Dup-Company";
    public static void restoreCompany() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "C:\\Program Files (x86)\\Wings Infonet\\Wings Accounting 24DNP\\Wings.exe");
        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability("deviceName", "WindowsPC");
        driver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), capabilities);
        driver.manage().window().maximize();
        driver.findElementByXPath("//Text[@Name='Restore a company']/*[@Name='Restore a company']").click();
        Thread.sleep(2000);
        driver.findElementByXPath("//Button[@Name='Next >']").click();
        driver.findElementByXPath("//Edit[@Name='Company Name']").sendKeys(companyName,Keys.TAB,Keys.TAB,Keys.ENTER);
        driver.findElementByXPath("//TreeItem[@Name='New Volume (E:)']").click();
        WebElement doubleClick=driver.findElementByXPath("//ListItem[@Name='backup']");
        Actions actions =new Actions(driver);
        actions.doubleClick(doubleClick).perform();
        WebElement doubleClick2= driver.findElementByXPath("//ListItem[@Name='Dup_Company.bak']");
        Actions actionss =new Actions(driver);
        actionss.doubleClick(doubleClick2).perform();
        WebElement element=driver.findElementByXPath("//Edit[@Name='Restore in Folder']");
        element.click();
        element.sendKeys(Keys.TAB,Keys.ENTER);
        driver.findElementByXPath("//TreeItem[@Name='New Volume (E:)']").click();
        WebElement doubleClick1=driver.findElementByXPath("//ListItem[@Name='restore']");
        Actions actions1 =new Actions(driver);
        actions1.doubleClick(doubleClick1).perform();
        driver.findElementByXPath("//Button[@Name='Select Folder']").click();
        driver.findElementByXPath("//Button[@Name='Next >']").click();
        driver.findElementByXPath("//Button[@Name='Finish']").click();
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        restoreCompany();
    }
}
