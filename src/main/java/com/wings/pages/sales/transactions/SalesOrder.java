package com.wings.pages.sales.transactions;

import com.wings.pages.SalesOrdersBaseClass;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

//public class SalesOrder extends SalesOrdersBaseClass {
//        WindowsDriver driver;
//        Common common;
//        String dataFile;
//
//        public SalesOrder(WindowsDriver driver, String file) {
//            super(driver,file);
//            common = new Common(this.driver = driver);
//            dataFile = file;
//        }
public class SalesOrder extends SalesOrdersBaseClass {

    public SalesOrder(WindowsDriver driver, String file) {
        super(driver, file);
    }

    public void salesOrder() throws InterruptedException, IOException, ParseException {
        navigateToSalesOrderMenu();
        Thread.sleep(1000);
        lastTransactionName();
        voucherType();
        branch_baseClass();
//            super.lastTransactionName();
//            common.clickElement("xpath","//Edit[@Name='Voucher Type']");
//            super.selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
//
//            common.clickElement("xpath", "//Edit[@Name='Branch *']");
//            super.selectAndValidateData(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectAndValidateData(common.getData(dataFile, "location"), "xpath", "//Edit[@Name='Location *']");
        transCurrency();
        partyCodes();
//            common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
//            super.selectAndValidateData(common.getData(dataFile, "transaction"),"xpath", "//Edit[@Name='Trans Currency *']");
//            common.clickElement("xpath", "//Edit[@Name='Party Code']");
//            super.selectAndValidateData(common.getData(dataFile, "partyCode"),"xpath", "//Edit[@Name='Party Code']");
//            common.clickElement("xpath", "//Edit[@Name='Party Account *']");
        Thread.sleep(2000);
        gstTransactionType("Registered");

        common.clickElement("xpath", "//Edit[@Name='Customer Email']");
        selectOptionalMaster(common.getData(dataFile, "CustEmail"), "xpath", "//Edit[@Name='Customer Email']");
        common.clickElement("xpath", "//Edit[@Name='Customer Mobile Number']");
        selectOptionalMaster(common.getData(dataFile, "mobileNum"), "xpath", "//Edit[@Name='Customer Mobile Number']");
        priceList_baseClass();
        executives();
        remarks_baseClass();
        common.clickElement("xpath", "//CheckBox[@Name='Advance Receipts']");

//            common.clickElement("xpath", "//Edit[@Name='Price List']");
//            super.selectAndValidateData(common.getData(dataFile,"priceList"),"xpath", "//Edit[@Name='Price List']");
//            common.clickElement("xpath", "//Edit[@Name='Executive *']");
//            super.selectAndValidateData(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
//            common.clickElement("xpath","//Edit[@Name='Remarks']");
//            super.selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        //f3-items
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']", dataFile, "productCode");

//        common.clickElement("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
//        selectAndValidateDataNew(common.getData(dataFile,"productCode"),"xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", dataFile, "quantity");
        //save
        transactionSave();
        lastTransactionName();
    }
}
