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

public class InitiateStockTake extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public InitiateStockTake(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void initiateStockTake() throws InterruptedException, AWTException, IOException, ParseException {
        navigateToMastersWhen2Steps("Inventory", "Initiate Stock Take");
        Thread.sleep(1000);
        String oldVoucherID = oldTTransactionID();
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "initiateStockTake", "branch");
        enterInput("xpath", "//Edit[@Name='Location *']", dataFile, "initiateStockTake", "location");
        common.clickElement("xpath","//Button[@Name='Get Stock']");
        common.clickElement("xpath","//ListItem/*[@Name='Select row 1']");
        common.clickElement("xpath","//Button[@Name='Ok']");


        WebElement product=common.findWebElement("xpath","//Edit[@Name='Product * Row 0, Not sorted.']");
        Actions actions=new Actions(driver);
        actions.contextClick(product).perform();
        common.clickElement("xpath","//MenuItem[@Name='Reports']");
        common.clickElement("xpath","//MenuItem[@Name='Stock Ledger-AC']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        verifyReport("SCR12",dataFile,"stockLedgerReportInInitiateStock");
        closeReport("Stock Ledger-AC");
        Thread.sleep(1000);
        enterOtherInfo(dataFile, "initiateStockTake");
        navigateToSummaryTab();
        initiateStockQuantityPresentInSummary();

        transactionSave();
        String newVoucherID = newTransactionID(oldVoucherID);
        System.out.println("newID: " + newVoucherID);
        String originalID = newTransactionID(oldVoucherID).replace(" ", "");
        Assert.assertNotEquals(newVoucherID, oldVoucherID, "both ID's should not Equal when we perform transaction");
        Thread.sleep(1000);
        common.clickElement("name", "Inventory");
        common.clickElement("xpath", "//MenuItem[@Name='Initiate Stock Take'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID,dataFile,"initiateStockTake");
//        deleteSingleTransaction(originalID);
    }
}
