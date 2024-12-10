package com.wings.utils;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class DeatchCompany {
    static WindowsDriver driver;
    static String companyName="wingsautode1",userName ="Super User",password="Welcome@123";

    public static void detachCompany() throws MalformedURLException, InterruptedException, AWTException {
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("app","C:\\Program Files (x86)\\Wings Infonet\\Wings Accounting 24DNP\\Wings.exe");
        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability("deviceName", "WindowsPC");
        driver=new WindowsDriver<>(new URL("http://127.0.0.1:4723/"),capabilities);
        driver.manage().window().maximize();
        driver.findElementByXPath("//Text/*[@Name='Detach a company']").click();
        driver.findElementByXPath("//Button[@Name='Next >']").click();
//        driver.findElementByXPath("//ComboBox[@Name='Company Name']/*[@Name='Open']").click();
//        Robot robot=new Robot();
//        robot.keyPress(KeyEvent.VK_DOWN);
//        robot.keyRelease(KeyEvent.VK_DOWN);
//        robot.keyPress(KeyEvent.VK_DOWN);
//        robot.keyRelease(KeyEvent.VK_DOWN);
//        robot.keyPress(KeyEvent.VK_DOWN);
//        robot.keyRelease(KeyEvent.VK_DOWN);
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
        driver.findElementByXPath("//Button[@Name='Next >']").click();
        driver.findElementByXPath("//Button[@Name='Finish']").click();
        driver.findElementByXPath("//Text/*[@Name='Login to another company']").click();
        driver.findElementByXPath("//Edit[@Name='Company']").sendKeys(companyName);
        driver.findElementByXPath("//Edit[@Name='User name']").sendKeys(userName);
        driver.findElementByXPath("//Edit[@Name='Password']").sendKeys(password);
        driver.findElementByXPath("//Button[@Name='Submit']").click();
        WebElement ErrorPopup=driver.findElementByXPath("//Window[@Name='Error']");
        String errorMessage=ErrorPopup.getText();
        System.out.println(errorMessage);
        Assert.assertEquals("Error",errorMessage);
        driver.findElementByXPath("//Button[@Name='OK']").click();
        driver.findElementByXPath("//Button[@Name='Close']");
    }

    public static void main(String args[]) throws MalformedURLException, InterruptedException, AWTException {
        detachCompany();
    }
}
