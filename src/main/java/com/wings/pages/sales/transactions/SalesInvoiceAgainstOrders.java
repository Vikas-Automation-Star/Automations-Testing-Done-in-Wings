package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class SalesInvoiceAgainstOrders extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesInvoiceAgainstOrders(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(driver);
        dataFile = file;
    }

    public void salesInvoicesAgainstOrders() throws InterruptedException, IOException, ParseException {
        navigateToSalesInvoiceAgainstOrdersMenu();
        lastTransactionName();
        Thread.sleep(3000);
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectAndValidateData(common.getData(dataFile, "location"), "xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        selectMaster(common.getData(dataFile, "partyCode"));
        gstTransactionType(common.getData(dataFile, "gstType"));
        Thread.sleep(1500);
//        common.clickElement("xpath", "//CheckBox[@Name='Select Row 1']");
//        common.clickElement("xpath", "//Button[@Name='Ok']");
        common.clickElement("xpath", "//Edit[@Name='Customer Email']");
        selectOptionalMaster(common.getData(dataFile, "CustEmail"), "xpath", "//Edit[@Name='Customer Email']");
        common.clickElement("xpath", "//Edit[@Name='Customer Mobile Number']");
        selectOptionalMaster(common.getData(dataFile, "mobileNum"), "xpath", "//Edit[@Name='Customer Mobile Number']");
        common.clickElement("xpath", "//Edit[@Name='Sales A/c Code']");
        common.clickElement("xpath", "//CheckBox[@Name='Apply TCS']");
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        //items --optional

//        super.enterData("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']", dataFile, "quantity");
        //save
        transactionSave();
        lastTransactionName();
    }
}
