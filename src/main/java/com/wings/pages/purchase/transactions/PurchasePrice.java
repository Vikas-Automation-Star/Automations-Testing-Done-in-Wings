package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class PurchasePrice extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchasePrice(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchasePrice() throws InterruptedException, IOException, ParseException {
        navigateToPurchasePrice();
        Thread.sleep(3000);
        oldTTransaction();
//        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
//        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectMasterWithValidation(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectMasterWithValidation(common.getData(dataFile, "currency"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Master Type']");
        selectAndValidateDataNew(common.getData(dataFile, "masterType"), "xpath", "//Edit[@Name='Master Type']");
        ;
        common.clickElement("xpath", "//Edit[@Name='Node']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Price List *']");
        selectMasterWithValidation(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List *']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectMasterWithValidation(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        Thread.sleep(1000);
//        common.clickElement("xpath","//Edit[@Name='Remarks']");
//        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        enterData("xpath", "//Edit[@Name='Rate * Row 0, Not sorted.']", dataFile, "URate");
        enterData("xpath", "//Edit[@Name='MRP Row 0, Not sorted.']", dataFile, "mrp");
        Thread.sleep(1000);
        common.deleteInvalidRows();
        transactionSave();
        Thread.sleep(1500);
        oldTTransaction();
        Thread.sleep(1000);
        closeTransaction("Purchase Prices");
        Thread.sleep(2000);
        Allure.step("PurchasePrices Transaction");

    }
}
