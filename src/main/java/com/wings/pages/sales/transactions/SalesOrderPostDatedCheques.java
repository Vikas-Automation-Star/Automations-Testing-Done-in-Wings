package com.wings.pages.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.pages.Transaction;
import com.wings.utils.Common;

import java.io.IOException;

public class SalesOrderPostDatedCheques extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesOrderPostDatedCheques(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void salesOrder() throws InterruptedException, IOException, ParseException {
        navigateToSalesOrderMenu();
        Thread.sleep(1000);
        super.lastTransactionName();
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        super.selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        super.selectAndValidateData(common.getData(dataFile, "location"), "xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        super.selectAndValidateData(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        super.selectAndValidateData(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Party Code']");
        common.clickElement("xpath", "//Edit[@Name='Party Account *']");
        Thread.sleep(2000);
        super.gstTransactionType("Registered");
        Thread.sleep(1000);
        common.clickElement("xpath","//Edit[@Name='Customer Email']");
        super.selectOptionalMaster(common.getData(dataFile,"CustEmail"),"xpath","//Edit[@Name='Customer Email']");
        common.clickElement("xpath","//Edit[@Name='Customer Mobile Number']");
        super.selectOptionalMaster(common.getData(dataFile,"mobileNum"),"xpath","//Edit[@Name='Customer Mobile Number']");
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        super.selectAndValidateData(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        super.selectAndValidateData(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        //f3-items
        common.clickElement("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        super.selectAndValidateData(common.getData(dataFile, "productCode"), "xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        super.enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", dataFile, "quantity");
        //f7-post dated cheques
        common.clickElement("xpath", "//TabItem[@Name='  Ctrl-F7 Post Dated Cheques  ']");
        common.clickElement("xpath", "//Edit[@Name='PDC Account Code Row 0, Not sorted.']");
        super.selectMaster(common.getData(dataFile, "pdcAccount"));
        common.clickElement("name", "Continue");
        super.enterData("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']", dataFile, "amount");
        common.inputText("xpath", "//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']", String.valueOf(common.getRandom()));
//            super.enterData("xpath", "//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']", dataFile, "cheque");
        super.enterData("xpath", "//Edit[@Name='Cheque Date * Row 0, Not sorted.']", dataFile, "chequeDate");
        Thread.sleep(1200);
        super.enterData("xpath", "//Edit[@Name='Drawn On Bank * Row 0, Not sorted.']", dataFile, "drawnOn");
        //save
        super.transactionSave();
        super.lastTransactionName();
//            super.transactionClose(common.getData(dataFile,"close"));

    }
}
