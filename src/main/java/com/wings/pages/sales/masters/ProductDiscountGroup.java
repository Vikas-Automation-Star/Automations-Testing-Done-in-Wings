package com.wings.pages.sales.masters;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ProductDiscountGroup {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public ProductDiscountGroup(WindowsDriver driver, String file) {
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void productGroup() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Prices and Discounts");
        common.clickElement("xpath", "//Menu[@Name='Prices and Discounts']/MenuItem[@Name='Product Discount Groups']");
        Thread.sleep(1000);
        WebElement allProductDiscount = common.findWebElement("xpath", "//TreeItem[@Name='Product Discount Groups']/TreeItem[@Name='All Product Discount Groups']");
        Actions actions = new Actions(driver);
        actions.contextClick(allProductDiscount).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(2500);
        common.clickElement("xpath", "//Edit[@Name='New Product Discount Group *']");
        common.inputText("xpath", "//Edit[@Name='New Product Discount Group *']", common.getData(dataFile, "productGroup") + common.getRandom());
        common.inputAndVerify("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        common.clickElement("xpath", "//Text[@Name='Applicable Product Nodes']/following-sibling::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='Master Type Row 0, Not sorted.']", common.getData(dataFile, "masterType"));
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2500);
        common.inputText("xpath", "//Edit[@Name='Applicable Node Row 0, Not sorted.']", common.getData(dataFile, "applicable"));
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.clickElement("name", "Ok");
        common.clickElement("xpath", "//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Cancel']");
        common.clickElement("xpath", "//TabItem[@Name='Product Discount Groups']/Button[@Name='Close']");
    }
}
