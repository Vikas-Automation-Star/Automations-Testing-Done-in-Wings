package com.wings.pages.finance.transactions.Journals;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;

public class JournalEntries extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public JournalEntries(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void journalEntires() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToJournalEntriesMenu();
        Thread.sleep(1000);
        lastTransactionName();
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        //f3-accounts
        enterDataAndValidate("xpath", "//Edit[@Name='Debit Account Code Row 0, Not sorted.']", dataFile, "debitAccount");
        enterDataAndValidate("xpath", "//Edit[@Name='Credit Account Code Row 0, Not sorted.']", dataFile, "creditAccount");
        enterData("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']", dataFile, "amount");
        //save
        transactionSave();
        lastTransactionName();
//        transactionClose(common.getData(dataFile,"close"));
    }
}
