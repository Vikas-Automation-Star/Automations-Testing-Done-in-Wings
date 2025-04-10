package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class PurchaseOrderCancellation extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchaseOrderCancellation(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchaseOrderCancellation() throws InterruptedException, IOException, ParseException {
        navigateToPurchaseOrdersaCancellation();
        Thread.sleep(3000);
        oldTTransaction();
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectMasterWithValidation(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectMasterWithValidation(common.getData(dataFile, "currency"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        selectAndValidateDataNew(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Party Code']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//CheckBox[@Name='Select Row 0']");
        common.clickElement("xpath", "//Button[@Name='Ok']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectMasterWithValidation(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        Thread.sleep(500);
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        common.clickElement("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", common.getData(dataFile, "quantity"));
        sliderHandle();
        common.clickElement("xpath", "//Edit[@Name='Unit Rate Row 0, Not sorted.']");
        Thread.sleep(1000);
        transactionSave();
        Thread.sleep(1500);
        newTransaction();
        closeTransaction("Purchase Orders Cancellation");
        Thread.sleep(2000);
    }
}

