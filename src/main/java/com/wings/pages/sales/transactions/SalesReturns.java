package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class SalesReturns extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesReturns(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(driver);
        dataFile = file;
    }

    public void salesReturns() throws InterruptedException, IOException, ParseException {
        navigateToSalesReturnMenu();
        Thread.sleep(3000);
        lastTransactionName();
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectAndValidateData(common.getData(dataFile, "location"), "xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Trans Currency *']");
//        common.clickElement("xpath", "//Edit[@Name='Sales Invoice No']");
        common.inputText("xpath", "//Edit[@Name='Sales Invoice No']", common.getData(dataFile, "invoice"));
//        common.clickElement("xpath", "//Edit[@Name='Sales Invoice Date']");
        common.inputText("xpath", "//Edit[@Name='Sales Invoice Date']", common.getData(dataFile, "invoideDate"));
        Thread.sleep(2000);
        common.clickElement("xpath", "//Edit[@Name='Cash/Party Code']");
        selectMaster(common.getData(dataFile, "partyCode"));
        gstTransactionType(common.getData(dataFile, "gstType"));
        common.clickElement("xpath", "//Edit[@Name='Sales Return A/c']");
        common.clickElement("xpath", "//CheckBox[@Name='Apply TCS']");
        common.clickElement("xpath", "//Edit[@Name='Batch Policy']");
        selectAndValidateData(common.getData(dataFile, "batchPolicy"), "xpath", "//Edit[@Name='Batch Policy']");
        Thread.sleep(3000);
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectAndValidateData(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        //items
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']", dataFile, "productCode");
        enterData("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']", dataFile, "quantity");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 700, 0);
        enterDataAndValidate("xpath", "//Edit[@Name='HSN Row 0, Not sorted.']", dataFile, "HSNCode");
        navigateToBillsReceivablesTab();
        common.deleteInvalidRows();
        //save
        transactionSave();
        lastTransactionName();
    }
}
