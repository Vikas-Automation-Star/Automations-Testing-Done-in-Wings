package com.wings.pages.inventory.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;

public class PhysicalStockTake extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PhysicalStockTake(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void physicalStockTake() throws InterruptedException, AWTException, IOException, ParseException {
        navigateToMastersWhen2Steps("Inventory", "Physical Stock Take");
        Thread.sleep(1000);
        String oldVoucherID = oldTTransactionID();
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "physicalStockTake", "branch");
        enterInput("xpath", "//Edit[@Name='Location *']", dataFile, "physicalStockTake", "location");
        enterInput("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']", dataFile, "physicalStockTake", "productCode");

        WebElement product=common.findWebElement("xpath","//Edit[@Name='Product * Row 0, Not sorted.']");
        Actions actions=new Actions(driver);
        actions.contextClick(product).perform();
        common.clickElement("xpath","//MenuItem[@Name='Reports']");
        common.clickElement("xpath","//MenuItem[@Name='Stock Ledger-AC']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        verifyReport("IST1",dataFile,"stockLedgerReportInPhysicalStock");
        closeReport("Stock Ledger-AC");
        Thread.sleep(1000);
        common.clickElement("xpath","//CheckBox[@Name='Audit * Row 0']");
        enterInput("xpath", "//Edit[@Name='Physical Stock Row 0, Not sorted.']", dataFile, "physicalStockTake", "ProductQuantity");

        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Variance Row 0, Not sorted.']"), common.getData(dataFile,"physicalStockTake", "variance" ),"variance Mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Excess Row 0, Not sorted.']"), common.getData(dataFile,"physicalStockTake", "excess" ),"excess Mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Shortage Row 0, Not sorted.']"), common.getData(dataFile,"physicalStockTake", "shortage" ),"shortage Mismatch");


        enterOtherInfo(dataFile, "physicalStockTake");
        navigateToSummaryTab();
        physicalStockQuantityPresentInSummary();

        transactionSave();
        String newVoucherID = newTransactionID(oldVoucherID);
        System.out.println("newID: " + newVoucherID);
        String originalID = newTransactionID(oldVoucherID).replace(" ", "");
        Assert.assertNotEquals(newVoucherID, oldVoucherID, "both ID's should not Equal when we perform transaction");
        Thread.sleep(1000);
        common.clickElement("name", "Inventory");
        common.clickElement("xpath", "//MenuItem[@Name='Physical Stock Take'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID,dataFile,"physicalStockTake");
        deleteSingleTransaction(originalID);
        common.clickElement("name", "Inventory");
        common.clickElement("xpath", "//MenuItem[@Name='Initiate Stock Take'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        deleteSingleTransaction("IST1");

    }
}
