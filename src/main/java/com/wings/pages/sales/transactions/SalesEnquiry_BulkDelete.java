package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class SalesEnquiry_BulkDelete extends Transaction {
        WindowsDriver driver;
        Common common;
        String dataFile;

        public SalesEnquiry_BulkDelete(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public String salesEnquiry_bulkDelete() throws InterruptedException, IOException, ParseException {
            navigateToSalesEnquiryMenu();
            Thread.sleep(2000);
            lastTransactionName();
            //enter data
            common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
            selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
//            common.clickElement("xpath", "//Edit[@Name='Branch *']");
            enterInput("xpath", "//Edit[@Name='Branch *']",dataFile, "branch");
//            common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
            enterInput("xpath", "//Edit[@Name='Trans Currency *']",dataFile, "transaction");
//            common.clickElement("xpath", "//Edit[@Name='Party Code']");
            enterInput("xpath", "//Edit[@Name='Party Code']",dataFile, "partyCode");
            common.clickElement("xpath", "//Edit[@Name='Party Account *']");
            Thread.sleep(1000);
            gstTransactionType(common.getData(dataFile, "gstType"));
            Thread.sleep(1500);
            common.clickElement("xpath", "//Edit[@Name='Price List']");
            enterInput("xpath", "//Edit[@Name='Price List']",dataFile, "priceList");
            common.clickElement("xpath", "//Edit[@Name='Executive *']");
            selectAndValidateData(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
            common.clickElement("xpath", "//Edit[@Name='Remarks']");
            selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
            //items
            enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']", dataFile, "productCode");
            enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", dataFile, "Quantity");
            //save
//            transactionSave();
            common.clickElement("xpath", "//Button[@Name='Save']");
            Thread.sleep(2000);
            common.clickElement("xpath", "//Button[@Name='OK']");
            //close it and open again
            closeTransaction("Sales Enquiries");
            //navigate again
            navigateToSalesEnquiryMenu();
            String voucherName=common.findWebElement("xpath", "//Text[@Name='Last Saved :']/following-sibling::Text").getAttribute("Name");
//            closeTransaction("Sales Enquiries");
            return voucherName;
            }
        }