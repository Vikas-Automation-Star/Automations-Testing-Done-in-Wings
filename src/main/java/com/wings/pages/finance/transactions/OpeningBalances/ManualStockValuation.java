package com.wings.pages.finance.transactions.OpeningBalances;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;

public class ManualStockValuation extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public ManualStockValuation(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void manualStockValuation() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToManulStockVerificationMenu();
        Thread.sleep(1000);
        lastTransactionName();
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Transaction Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Debit Account Code']");
        selectAndValidateData(common.getData(dataFile, "debitCode"), "xpath", "//Edit[@Name='Debit Account Code']");
        common.clickElement("xpath", "//Edit[@Name='Credit Account Code']");
        selectAndValidateData(common.getData(dataFile, "creditCode"), "xpath", "//Edit[@Name='Credit Account Code']");
        common.clickElement("xpath", "//Edit[@Name='Opening Stock Account *']");
        selectAndValidateData(common.getData(dataFile, "stockAccount"), "xpath", "//Edit[@Name='Opening Stock Account *']");
        Thread.sleep(2500);
        common.inputText("xpath", "//Edit[@Name='Amount *']", common.getData(dataFile, "amount"));
        Thread.sleep(2500);
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectMaster(common.getData(dataFile, "executive"));
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        //save
        transactionSave();
        lastTransactionName();
    }
}
