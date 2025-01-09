package com.wings.pages.finance.transactions.Payments;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;

public class CashTransfer extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CashTransfer(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void cashTransfer() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToCashTransfersMenu();
        Thread.sleep(1000);
        lastTransactionName();
        //enter data
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"),"xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Cash A/c Code']");
        selectMaster(common.getData(dataFile, "from"));
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile, "executive"),"xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        //f3-items
        Thread.sleep(1000);
        enterData("xpath", "//Edit[@Name='Cash A/c Code Row 0, Not sorted.']", dataFile, "to");
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']", common.getData(dataFile, "amount"));
        Thread.sleep(1000);
//        common.clickElement("xpath","//Edit[@Name='Department Row 0, Not sorted.']");
        //navigate to Summary
        common.clickElement("xpath", "//TabItem[contains(@Name,'Summary')]");
       //save
        transactionSave();
        lastTransactionName();
//        transactionClose(common.getData(dataFile,"close"));
    }
}
