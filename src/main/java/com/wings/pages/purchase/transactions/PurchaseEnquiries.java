package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class PurchaseEnquiries extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public PurchaseEnquiries(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void PurchaseEnquires() throws InterruptedException, IOException, ParseException {
        navigateToPurchaseEnquiries();
        Thread.sleep(3000);
        oldTTransaction();
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectMasterWithValidation(common.getData(dataFile,"branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        selectMasterWithValidation(common.getData(dataFile,"currency"),"xpath", "//Edit[@Name='Transaction Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        selectAndValidateDataNew(common.getData(dataFile,"partyCode"),"xpath","//Edit[@Name='Party Code']" );
        common.clickElement("xpath","//Edit[@Name='Price List']");
        selectMasterWithValidation(common.getData(dataFile,"priceList"),"xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath","//Edit[@Name='Executive *']");
        selectMasterWithValidation(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath","//Edit[@Name='Product Code Row 0, Not sorted.']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        Thread.sleep(2000);
        enterData("xpath","//Edit[@Name='Product Code Row 0, Not sorted.']",dataFile,"productCode");
        common.clickElement("xpath","//Edit[@Name='Quantity * Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Quantity * Row 0, Not sorted.']", common.getData(dataFile,"quantity"));
        Thread.sleep(1000);
        transactionSave();
        Thread.sleep(1500);
        newTransaction();
        closeTransaction("Purchase Enquiries");
        Thread.sleep(2000);
        Allure.step("Purchase Enquiries Transaction");
    }
}
