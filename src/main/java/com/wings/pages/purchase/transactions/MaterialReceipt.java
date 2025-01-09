package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class MaterialReceipt extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public MaterialReceipt(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void materialReceipt() throws InterruptedException, IOException, ParseException {
        navigateToMaterialReceipts();
        Thread.sleep(3000);
        oldTTransaction();
//        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
//        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectMasterWithValidation(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectMasterWithValidation(common.getData(dataFile, "currency"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectOptionalMaster(common.getData(dataFile, "location"), "xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        selectAndValidateDataNew(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Party Code']");
        Thread.sleep(1000);
        gstSelectionWhenBothRegisteredDealers();
        Thread.sleep(2000);
        common.clickElement("xpath", "//Edit[@Name='Consignor']");
        selectMasterWithValidation(common.getData(dataFile, "consignor"), "xpath", "//Edit[@Name='Consignor']");
        common.clickElement("xpath", "//Edit[@Name='Batch Policy']");
        selectMasterWithValidation(common.getData(dataFile, "batchPolicy"), "xpath", "//Edit[@Name='Batch Policy']");
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectMasterWithValidation(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectMasterWithValidation(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        Thread.sleep(500);
//        common.clickElement("xpath","//Edit[@Name='Remarks']");
//        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        common.clickElement("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        enterData("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']", dataFile, "Product");
        sliderHandle();
        enterData("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']", dataFile, "quantity");
        Thread.sleep(2000);
        sliderHandle();
        common.clickElement("xpath", "//Edit[@Name='GST Product Category Row 0, Not sorted.']");
        transactionSave();
        Thread.sleep(1500);
        newTransaction();
        closeTransaction("Material Receipts");
        Thread.sleep(2000);
        Allure.step("MaterialReceipts Transaction");

    }
}
