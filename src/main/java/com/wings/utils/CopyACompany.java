package com.wings.utils;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class CopyACompany {
    static WindowsDriver driver;
    public static String companyName ="WingsAutomatio24DNP", password="Welcome@123",userName="Super User";

    public static void copyCompany() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("app","C:\\Program Files (x86)\\Wings Infonet\\Wings Accounting 24DNP\\Wings.exe");
        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability("deviceName", "WindowsPC");
        driver=new WindowsDriver<>(new URL("http://127.0.0.1:4723/"),capabilities);
        driver.manage().window().maximize();
        driver.findElementByXPath("//Text/*[@Name='Copy a company']").click();
        driver.findElementByXPath("//Button[@Name='Next >']").click();
        WebElement element=driver.findElementByXPath("//ComboBox[@Name='Company to Copy']/Button[@Name='Open']");
        element.click();
        element.sendKeys(Keys.DOWN,Keys.DOWN,Keys.ENTER);
        driver.findElementByXPath("//Edit[@Name='Copy As (New Company Name)']").sendKeys("WingsAutomation25DNP");
        driver.findElementByXPath("//Button[@Name='....']").click();
        driver.findElementByXPath("//TreeItem[@Name='Documents (pinned)']").click();
        WebElement doubleClick=driver.findElementByXPath("//ListItem[@Name='copyAndRestore']");
        Actions actions=new Actions(driver);
        actions.doubleClick(doubleClick).perform();
        driver.findElementByXPath("//Button[@Name='Select Folder']").click();

        WebElement moove=driver.findElementByXPath("//Edit[@Name='Folder to use for backup']");
        moove.click();
        moove.sendKeys(Keys.TAB,Keys.ENTER);
        driver.findElementByXPath("//TreeItem[@Name='Documents (pinned)']").click();
        WebElement doubleClick1=driver.findElementByXPath("//ListItem[@Name='copyAndRestore']");
        Actions actions1 =new Actions(driver);
        actions1.doubleClick(doubleClick1).perform();
        driver.findElementByXPath("//Button[@Name='Select Folder']").click();
        driver.findElementByXPath("//Button[@Name='Next >']").click();
        driver.findElementByXPath("//Button[@Name='Finish']").click();
        Assert.assertTrue(true);
        driver.findElementByName("Close").click();
        driver.findElementByName("Yes").click();
    }

    public static void main(String args[]) throws MalformedURLException, InterruptedException {
        copyCompany();
    }
}
