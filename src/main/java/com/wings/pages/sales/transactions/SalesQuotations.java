package com.wings.pages.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.pages.Transaction;
import com.wings.utils.Common;

import java.io.IOException;

public class SalesQuotations extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesQuotations(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void salesQuotation() throws InterruptedException, IOException, ParseException {
        navigateToSalesQuotationsMenu();
        Thread.sleep(1000);
        lastTransactionName();
        //enter data
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        selectAndValidateData(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Party Code']");
//        common.clickElement("xpath", "//Edit[@Name='Party Account *']");
        Thread.sleep(3500);
        gstTransactionType(common.getData(dataFile, "gstType"));
        Thread.sleep(1500);
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectAndValidateData(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        //items
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']", dataFile, "productCode");
        enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", dataFile, "Quantity");
        //save
        transactionSave();
        lastTransactionName();
    }
}
