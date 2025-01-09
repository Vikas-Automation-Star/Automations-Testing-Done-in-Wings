package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class PurchaseOrder extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchaseOrder(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchaseOrder() throws InterruptedException, IOException, ParseException {
        navigateToPurchaseOrders();
        Thread.sleep(3000);
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectMasterWithValidation(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectOptionalMaster(common.getData(dataFile, "location"), "xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectMasterWithValidation(common.getData(dataFile, "currency"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        selectAndValidateDataNew(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Party Code']");
        Thread.sleep(1000);
        gstSelectionWhenBothRegisteredDealers();
        Thread.sleep(2000);
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectMasterWithValidation(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectMasterWithValidation(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        Thread.sleep(500);
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        Thread.sleep(1000);
        enterData("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']", dataFile, "product");
        common.clickElement("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", common.getData(dataFile, "quantity"));
        common.clickElement("xpath", "//Edit[@Name='MRP Row 0, Not sorted.']");
        sliderHandle();
        Thread.sleep(1500);
        transactionSave();
        Thread.sleep(1500);
        newTransaction();
        closeTransaction("Purchase Orders");
        Thread.sleep(2000);
        Allure.step("PurchaseOrder Transaction");


    }

}
