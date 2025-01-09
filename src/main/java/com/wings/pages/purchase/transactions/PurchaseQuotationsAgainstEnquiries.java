package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class PurchaseQuotationsAgainstEnquiries extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchaseQuotationsAgainstEnquiries(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchaseQuotationsAgainstEnquiry() throws InterruptedException, IOException, ParseException {
        navigateToPurchaseQuotationsAgainstEnquiries();
        Thread.sleep(3000);
        oldTTransaction();
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectMasterWithValidation(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        selectMasterWithValidation(common.getData(dataFile, "currency"), "xpath", "//Edit[@Name='Transaction Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        selectAndValidateDataNew(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Party Code']");
        Thread.sleep(1000);
        gstSelectionWhenBothRegisteredDealers();
        Thread.sleep(2000);
        common.clickElement("xpath", "//CheckBox[@Name='Select Row 0']");
        common.clickElement("xpath", "//Button[@Name='Ok']");

        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectMasterWithValidation(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");

        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectMasterWithValidation(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        Thread.sleep(500);
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        common.clickElement("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", common.getData(dataFile, "quantity"));
        sliderHandle();
        Thread.sleep(1000);
        transactionSave();
        Thread.sleep(1500);
        newTransaction();
        closeTransaction("Purchase Quotations against Enquiries");
        Thread.sleep(2000);
        Allure.step("PurchaseQuotationsAgainstEnquiries Transaction");
    }
}
