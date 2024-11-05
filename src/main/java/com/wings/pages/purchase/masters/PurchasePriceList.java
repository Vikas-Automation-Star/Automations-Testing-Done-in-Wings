package com.wings.pages.purchase.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.wings.utils.Common;

import java.io.IOException;

public class PurchasePriceList {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchasePriceList(WindowsDriver driver, String file) {
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchasePriceList() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Price List");
        common.clickElement("xpath", "//Menu[@Name='Price List']/MenuItem[@Name='Purchase Price Lists']");
        Thread.sleep(1000);
        WebElement allPriceList = common.findWebElement("xpath", "//TreeItem[@Name='Purchase Price Lists']/TreeItem[@Name='All Purchase Price Lists']");
        Actions actions = new Actions(driver);
        actions.contextClick(allPriceList).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='New Purchase Price List *']", common.getData(dataFile, "purchaseList") + common.getRandom());
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Cancel']");

        common.clickElement("xpath", "//TabItem[@Name='Purchase Price Lists']/Button[@Name='Close']");
    }
}
