package com.wings.pages.taxes.masters.GST;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.io.IOException;

public class EcommerceOperator extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public EcommerceOperator(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void ecommerceOperator() throws InterruptedException, IOException, ParseException, AWTException {

        common.clickElement("name", "Taxes");
        common.clickElement("name", "GST");
        common.clickElement("name", "ECommerce Operators");
        WebElement element = common.findWebElement("xpath", "//TreeItem[@Name='ECommerce Operators']/TreeItem[@Name='All ECommerce Operators']");
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New E-Commerce Operator *']", common.getData(dataFile, "newECommerceOperator") + common.getRandom());
        Thread.sleep(2000);
        common.clickElement("xpath", "//Pane[@Name='ECommerce Operator Details']/Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='E-Commerce Operator GSTIN *']", common.getData(dataFile, "EcommerceOperatorGSTIN"));
        Thread.sleep(2000);
        common.clickElement("xpath", "//Button[@Name='Ok']");
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        common.clickElement("xpath", "//Button[@Name='Close']");
        Thread.sleep(1000);
        super.closeMaster("ECommerce Operators");
        Thread.sleep(2000);
    }
}
