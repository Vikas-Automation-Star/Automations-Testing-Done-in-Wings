package com.wings.pages.finance.transactions.OpeningBalances;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;

public class PartyOpeningBalances extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public PartyOpeningBalances(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void partyOpeningBalance() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToPartyOpeningBalancesMenu();
        Thread.sleep(1000);
        lastTransactionName();
//        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
//        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
//        common.clickElement("xpath", "//Edit[@Name='Remarks']");
//        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        //f3-accounts
        common.inputText("xpath", "//Edit[@Name='Account Code Row 0, Not sorted.']", common.getData(dataFile, "accountCode"));
        common.clickElement("xpath", "//Edit[@Name='Account * Row 0, Not sorted.']");
        enterData("xpath", "//Edit[@Name='Credit Row 0, Not sorted.']", dataFile, "credit");
        //save
        transactionSave();
        lastTransactionName();
    }
}
