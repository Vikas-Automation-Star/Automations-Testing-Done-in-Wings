package com.wings.pages.finance.transactions.Banking;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;

public class OpeningUnclearedBankEntries extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public OpeningUnclearedBankEntries(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void unclearedBankEntries() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToOpeningUnclearedBankEntriesMenu();
        Thread.sleep(1000);
        lastTransactionName();
        //enter data
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Transaction Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Bank Code']");
        selectAndValidateData(common.getData(dataFile, "bankCode"), "xpath", "//Edit[@Name='Bank Code']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        //f3 - uncleared receipts
        enterDataAndValidate("xpath", "//Edit[@Name='Account Code Row 0, Not sorted.']", dataFile, "receiptAccount");
        common.inputText("xpath", "//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']", String.valueOf(common.getRandom()));
        enterDataAndValidate("xpath", "//Edit[@Name='Drawn On Bank * Row 0, Not sorted.']", dataFile, "drawnOnBank");
        enterData("xpath", "//Edit[@Name='Receipt Amount * Row 0, Not sorted.']", dataFile, "receipt");
        //f5 - uncleared payments
        Thread.sleep(1200);
        navigateToUnclearedPayments();
        enterDataAndValidate("xpath", "//Edit[@Name='Account Code Row 0, Not sorted.']", dataFile, "issuedAccount");
        common.inputText("xpath", "//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']", String.valueOf(common.getRandom()));
        enterDataAndValidate("xpath", "//Edit[@Name='Drawn On Bank * Row 0, Not sorted.']", dataFile, "drawnOnBank");
        enterData("xpath", "//Edit[@Name='Issued Amount * Row 0, Not sorted.']", dataFile, "issued");
        //f7 summary
        navigateToSummaryTab();
        //save
        transactionSave();
        lastTransactionName();
    }
}
