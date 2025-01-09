package com.wings.pages.sales.transactions;

import com.wings.pages.SalesOrdersBaseClass;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class SalesOrderCancellation extends SalesOrdersBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesOrderCancellation(WindowsDriver driver, String file) {
        super(driver, file);
        this.driver = driver;
        common = new Common(driver);
        dataFile = file;
    }

    public void salesOrderCancellations() throws InterruptedException, IOException, ParseException {
        navigateToSalesOrderCancellaltionMenu();
        lastTransactionName();
        Thread.sleep(3000);
        voucherType();
        branch_baseClass();
        transCurrency();
        partyCodes();
        common.clickElement("xpath", "//CheckBox[@Name='Select Row 1']");
        common.clickElement("xpath", "//Button[@Name='Ok']");
        remarks_baseClass();

//            common.clickElement("xpath","//Edit[@Name='Voucher Type']");
//            super.selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
//            common.clickElement("xpath", "//Edit[@Name='Branch *']");
//            super.selectAndValidateData(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
//            common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
//            super.selectAndValidateData(common.getData(dataFile,"transaction"),"xpath", "//Edit[@Name='Trans Currency *']");
//            common.clickElement("xpath", "//Edit[@Name='Party Code']");
//            super.selectMaster(common.getData(dataFile, "partyCode"));
//            common.clickElement("xpath","//CheckBox[@Name='Select Row 1']");
//            common.clickElement("xpath","//Button[@Name='Ok']");
//            common.clickElement("xpath","//Edit[@Name='Remarks']");
//            super.selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
//            //items
        enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", dataFile, "quantity");
//            //save
        transactionSave();
        lastTransactionName();
    }
}
