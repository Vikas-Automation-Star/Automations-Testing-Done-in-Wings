package com.wings.pages.finance.transactions.Banking;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;

public class CashDepositsAndWithdrawls extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public CashDepositsAndWithdrawls(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void depositAndWithdrawal() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToCashDepositsAndWithdrawalsMenu();
        Thread.sleep(1000);
        lastTransactionName();
        //enter data
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Bank A/c Code']");
        common.clickElement("xpath", "//Edit[@Name='Bank Account *']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        //f3-deposits
        Thread.sleep(5000);
        enterData("xpath", "//Edit[@Name='Cash Account * Row 0, Not sorted.']", dataFile, "deposit");
        Thread.sleep(5000);
        enterData("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']", dataFile, "credit");
        Thread.sleep(1200);
        //check for withdrawl
        Thread.sleep(5000);
//            navigateToWithdrawalsTab();
        enterData("xpath", "//Edit[@Name='Cash Account * Row 0, Not sorted.']", dataFile, "withdraw");
        Thread.sleep(5000);
        enterData("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']", dataFile, "debit");
        Thread.sleep(1200);
        enterData("xpath", "//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']", dataFile, "cheque");
        //check summary
        navigateToSummaryTab();
        //save
        transactionSave();
        lastTransactionName();
    }
}
