package com.wings.pages.finance.transactions.OpeningBalances;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;

public class OpeningBalance extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public OpeningBalance(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void openingBalance() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToOpeningBalancesMenu();
        Thread.sleep(1000);
        lastTransactionName();
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectMaster(common.getData(dataFile, "branch"));
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectMaster(common.getData(dataFile, "transaction"));
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectMaster(common.getData(dataFile, "executive"));
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");

        //can select any1- based on scenario

        //f7-accounts
        navigateToAccountsTab();
        common.inputText("xpath", "//Edit[@Name='Account Code Row 0, Not sorted.']", common.getData(dataFile, "accountCode"));
        common.clickElement("xpath", "//Edit[@Name='Account * Row 0, Not sorted.']");
        enterData("xpath", "//Edit[@Name='Credit Row 0, Not sorted.']", dataFile, "credit");

        //f5- Bank
        navigateToBankTab();
        common.inputText("xpath", "//Edit[@Name='Bank A/c Code Row 0, Not sorted.']", common.getData(dataFile, "bankAccount"));
        common.clickElement("xpath", "//Edit[@Name='Bank Account * Row 0, Not sorted.']");
        enterData("xpath", "//Edit[@Name='Debit Row 0, Not sorted.']", dataFile, "bankDebit");

        //f3-cash
        navigateToCashTab();
        enterData("xpath", "//Edit[@Name='Cash Account Code Row 0, Not sorted.']", dataFile, "cashAccount");
//        common.inputText("xpath", "//Edit[@Name='Cash Account Code Row 0, Not sorted.']", common.getData(dataFile, "cashAccount"));
        enterData("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']", dataFile, "cashAmount");

        //save
        transactionSave();
        lastTransactionName();
    }
}
