package com.wings.pages.sales.transactions;

import com.wings.pages.SalesOrdersBaseClass;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class SalesOrdersAgainstQuotations extends SalesOrdersBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesOrdersAgainstQuotations(WindowsDriver driver, String file) {
        super(driver, file);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void salesOrderAgnstQuote() throws InterruptedException, IOException, ParseException {
        navigateToSalesOrderAgainstQuotationsMenu();
        Thread.sleep(1000);
        lastTransactionName();
        voucherType();
        branch_baseClass();
        transCurrency();
        partyCodes();
//        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
//        super.selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
//        common.clickElement("xpath", "//Edit[@Name='Branch *']");
//        super.selectAndValidateData(common.getData(dataFile,"branch"), "xpath", "//Edit[@Name='Branch *']");
//        common.clickElement("xpath","//Edit[@Name='Trans Currency *']");
//        super.selectAndValidateData(common.getData(dataFile,"transaction"), "xpath","//Edit[@Name='Trans Currency *']");
//        common.clickElement("xpath","//Edit[@Name='Party Code']");
//        super.selectAndValidateData(common.getData(dataFile,"partyCode"),"xpath","//Edit[@Name='Party Code']" );
//        common.clickElement("xpath","//Edit[@Name='Party Account *']");
        Thread.sleep(3500);
        gstTransactionType(common.getData(dataFile, "gstType"));
        Thread.sleep(2000);
        common.clickElement("xpath", "//CheckBox[@Name='Select Row 1']");
        common.clickElement("name", "Ok");
        remarks_baseClass();
//        common.clickElement("xpath","//Edit[@Name='Remarks']");
//        super.selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        //items
        enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", dataFile, "Quantity");
        //save
        transactionSave();
        lastTransactionName();
    }
}