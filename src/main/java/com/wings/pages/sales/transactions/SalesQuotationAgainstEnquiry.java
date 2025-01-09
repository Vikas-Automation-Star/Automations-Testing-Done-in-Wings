package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class SalesQuotationAgainstEnquiry extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesQuotationAgainstEnquiry(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void quotationAgainstEnquiry() throws InterruptedException, IOException, ParseException {
        navigateToSalesQuotationAgainstEnquiryMenu();
        Thread.sleep(2000);
        lastTransactionName();
        //data
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        selectAndValidateDataNew(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Party Code']");
        common.clickElement("xpath", "//Edit[@Name='Party Account *']");
        Thread.sleep(3500);
        gstTransactionType(common.getData(dataFile, "gstType"));
        Thread.sleep(2000);
        common.clickElement("xpath", "//CheckBox[@Name='Select Row 0']");
        common.clickElement("name", "Ok");
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        //items
        enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", dataFile, "Quantity");
        //save
        transactionSave();
        lastTransactionName();
    }
}
