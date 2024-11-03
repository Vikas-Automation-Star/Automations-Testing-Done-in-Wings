package com.wings.pages.finance.transactions.Banking;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
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
            common.clickElement("name", "Finance");
            common.clickElement("name", "Banking");
            common.clickElement("xpath", "//MenuItem[@Name='Opening Uncleared Bank Entries']");
            Thread.sleep(1000);
            super.lastTransactionName();
            //enter data
            common.clickElement("xpath","//Edit[@Name='Voucher Type']");
            super.selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
            common.clickElement("xpath", "//Edit[@Name='Branch *']");
            super.selectAndValidateData(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
            common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
            super.selectAndValidateData(common.getData(dataFile, "transaction"),"xpath", "//Edit[@Name='Transaction Currency *']");
            common.clickElement("xpath", "//Edit[@Name='Bank Code']");
            super.selectAndValidateData(common.getData(dataFile,"bankCode"),"xpath", "//Edit[@Name='Bank Code']" );
            common.clickElement("xpath", "//Edit[@Name='Executive *']");
            super.selectAndValidateData(common.getData(dataFile, "executive"),"xpath", "//Edit[@Name='Executive *']");
            common.clickElement("xpath","//Edit[@Name='Remarks']");
            super.selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
            //f3 - uncleared receipts
            super.enterDataAndValidate("xpath","//Edit[@Name='Account Code Row 0, Not sorted.']",dataFile,"receiptAccount");
            common.inputText("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']",String.valueOf(common.getRandom()));
            super.enterDataAndValidate("xpath", "//Edit[@Name='Drawn On Bank * Row 0, Not sorted.']",dataFile, "drawnOnBank");
            super.enterData("xpath","//Edit[@Name='Receipt Amount * Row 0, Not sorted.']",dataFile,"receipt");
            //f5 - uncleared payments
            Thread.sleep(1200);
            common.clickElement("xpath","//TabItem[@Name='  F5 Uncleared Payments  ']");
            super.enterDataAndValidate("xpath","//Edit[@Name='Account Code Row 0, Not sorted.']",dataFile,"issuedAccount");
            common.inputText("xpath", "//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']",String.valueOf(common.getRandom()));
            super.enterDataAndValidate("xpath", "//Edit[@Name='Drawn On Bank * Row 0, Not sorted.']",dataFile, "drawnOnBank");
            super.enterData("xpath","//Edit[@Name='Issued Amount * Row 0, Not sorted.']",dataFile,"issued");
            //f7 summary
            common.clickElement("xpath","//TabItem[@Name='  F8 Summary  ']");

            //save
            super.transactionSave();
            super.lastTransactionName();
            super.transactionClose(common.getData(dataFile,"close"));
        }
    }
