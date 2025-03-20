package com.wings.pages;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

public class Masters {
    WindowsDriver driver;
    Common common;

    public Masters(WindowsDriver driver) {
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void selectMasterItem(String transaction) {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().contains(transaction)) {
                i.click();
                i.sendKeys(Keys.TAB);
            }
        }
    }

    public void selectDropDownMaster(String element) throws InterruptedException {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/*/*[contains(@Name,'MasterName row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().contains(element)) {
                i.click();
                break;
            }
        }
    }

    public void actionsMaster(String locatorType, String itemsLocator, String masterLocator) {
        WebElement Allitems = common.findWebElement(locatorType, itemsLocator);
        Actions actions = new Actions(driver);
        actions.contextClick(Allitems).perform();
        common.clickElement(locatorType, masterLocator);
    }

    public void saveMaster() throws InterruptedException {
        common.clickElement("name", "Save");
        Thread.sleep(2000);
        common.clickElement("name", "OK");
        common.clickElement("xpath", "//Button[@Name='Close']");
    }

    public void closeMaster(String masterName) {
        common.clickElement("xpath", "//TabItem[@Name='" + masterName + "']/Button[@Name='Close']");
    }

    public void inputTextWithValidation(String locatorType, String locator, String inputText) {
        WebElement element = common.findWebElement(locatorType, locator);
        element.sendKeys(inputText);
        System.out.println(element.getText());
        if (element.getText().equals(inputText)) {
            System.out.println("entered currect Input :" + element.getText());
        } else {
            Assert.fail("wrong input");
        }
    }

    public void createMaster(String locatorType, String locator) {
        WebElement element = common.findWebElement(locatorType, locator);
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        common.clickElement("name", "New Master");
    }

    public void saveAfterMasterCreate() throws InterruptedException {
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Button[@Name='Close']");
    }

    public void vericleSliderHandle() {
        int offset = 400;
        WebElement slider = common.findWebElement("xpath", "//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']");
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(0, offset).release().perform();
    }
}
