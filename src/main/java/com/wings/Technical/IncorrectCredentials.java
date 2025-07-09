package com.wings.Technical;

import com.wings.pages.AppLogin;
import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class IncorrectCredentials extends TransactionsBaseClass {
    WindowsDriver driver,rootDriver,loginDriver;
    Common common;
    String dataFile;

    public IncorrectCredentials(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void incorrectPassword() throws IOException, InterruptedException, ParseException {
        driver = common.initializeDriver(common.getProperty("multiUserApp"));
        String currentwindowHandle = driver.getWindowHandle();
        System.out.println("Window 1 -" + currentwindowHandle);
        driver.findElement(By.name("24D Books Automation")).click();
        Thread.sleep(4000);
        rootDriver = common.initializeDriver("Root");
        Thread.sleep(4000);
        WebElement login = rootDriver.findElement(By.name("Wings 24 - Web Client"));
        String nativeWindow = login.getAttribute("NativeWindowHandle");
        String hexLoginId = Integer.toHexString(Integer.parseInt(nativeWindow));
        System.out.println("window id: " + hexLoginId);
        loginDriver = common.navigateToAppWindow(hexLoginId);
        common = new Common(loginDriver);

        common.inputText("xpath", "//Edit[@Name='Password']", common.getProperty("incorrectPassword"));
        System.out.println("Password TagName " + common.getTagName("name", "Password"));
        common.clickElement("name", "Submit");
        Thread.sleep(10000);
        String actualErrorText =common.getText("xpath","//Window[@Name='Wings 24 - Web Client']/*/*/Text");
        System.out.println("error text: "+ actualErrorText);
        Assert.assertEquals(actualErrorText, common.getData(dataFile,"incorrectCredentials","errorText"), "Error message mismatch!");
        common.clickElement("xpath","//Button[@Name='OK']");
        System.out.println("Test passed: Password is incorrect and error message appeared.");
        driver.quit();
        rootDriver.quit();
        loginDriver.quit();
    }

    public void incorrectUserName() throws IOException, InterruptedException, ParseException {
        driver = common.initializeDriver(common.getProperty("multiUserApp"));
        String currentwindowHandle = driver.getWindowHandle();
        System.out.println("Window 1 -" + currentwindowHandle);
        driver.findElement(By.name("24D Books Automation")).click();
        Thread.sleep(4000);
        rootDriver = common.initializeDriver("Root");
        Thread.sleep(4000);
        WebElement login = rootDriver.findElement(By.name("Wings 24 - Web Client"));
        String nativeWindow = login.getAttribute("NativeWindowHandle");
        String hexLoginId = Integer.toHexString(Integer.parseInt(nativeWindow));
        System.out.println("window id: " + hexLoginId);
        loginDriver = common.navigateToAppWindow(hexLoginId);
        common = new Common(loginDriver);

        common.inputText("xpath", "//Edit[@Name='User name']", common.getData(dataFile,"incorrectCredentials","wrongUserName"));
        common.inputText("xpath", "//Edit[@Name='Password']", common.getData(dataFile,"incorrectCredentials","validPassword"));
        System.out.println("Password TagName " + common.getTagName("name", "Password"));
        common.clickElement("name", "Submit");
        Thread.sleep(10000);
        String actualErrorText =common.getText("xpath","//Window[@Name='Wings 24 - Web Client']/*/*/Text");
        System.out.println("error text: "+ actualErrorText);
        Assert.assertEquals(actualErrorText, common.getData(dataFile,"incorrectCredentials","errorText"), "Error message mismatch!");
        common.clickElement("xpath","//Button[@Name='OK']");
        System.out.println("Test passed: UserName is incorrect and error message appeared.");
        driver.quit();
        rootDriver.quit();
        loginDriver.quit();
    }
}