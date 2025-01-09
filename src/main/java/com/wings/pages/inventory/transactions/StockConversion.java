package com.wings.pages.inventory.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;

public class StockConversion extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public StockConversion(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void stockConversion() throws InterruptedException, AWTException, IOException, ParseException {
        navigateToStockConversionMenu();
        lastTransactionName();
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectAndValidateData(common.getData(dataFile, "fromLocation"), "xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Batch Policy']");
        selectAndValidateData(common.getData(dataFile, "batchPolicy"), "xpath", "//Edit[@Name='Batch Policy']");
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectAndValidateData(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        Thread.sleep(2500);
        //F3-Items
        common.clickElement("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        selectAndValidateData(common.getData(dataFile, "productCode"), "xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", dataFile, "quantity");
        common.sliderHandling("name", "Position", 500, 0);
        //output quantity
        Thread.sleep(1500);
        navigateToOutputsTab();
        common.clickElement("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        selectAndValidateData(common.getData(dataFile, "productCode"), "xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", dataFile, "quantity1");
        Thread.sleep(1000);
        common.sliderHandling("name", "Position", 500, 0);
        //save
        transactionSave();
        lastTransactionName();
    }
}
