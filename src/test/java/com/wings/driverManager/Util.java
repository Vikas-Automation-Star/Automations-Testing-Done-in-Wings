package com.wings.driverManager;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Util {

    WindowsDriver driver;

    public Util(WindowsDriver logindriver) {
        driver = logindriver;
    }

    public void clickElement(String locatorType, String locator) {
        findElement(locatorType, locator).click();
    }

    public WebElement findElement(String locatorType, String locator) {
        By by = null;
        if (locatorType.equals("xpath")) {
            by = By.xpath(locator);
        } else if (locatorType.equals("name")) {
            by = By.name(locator);
        }
        return driver.findElement(by);
    }


}
