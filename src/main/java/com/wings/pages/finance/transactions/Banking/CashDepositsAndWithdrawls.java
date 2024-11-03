package com.wings.pages.finance.transactions.Banking;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
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

        public void depositAndWithdrawl() throws InterruptedException, IOException, ParseException, AWTException {
            common.clickElement("name", "Finance");
            common.clickElement("name", "Banking");
            common.clickElement("xpath", "//MenuItem[@Name='Cash Deposits and withdrawals']");
            Thread.sleep(1000);
            super.lastTransactionName();
            //enter data
            common.clickElement("xpath","//Edit[@Name='Voucher Type']");
            super.selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
            common.clickElement("xpath", "//Edit[@Name='Branch *']");
            super.selectMaster(common.getData(dataFile, "branch"));
            common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
            super.selectMaster(common.getData(dataFile, "transaction"));
            common.clickElement("xpath", "//Edit[@Name='Bank A/c Code']");
            common.clickElement("xpath", "//Edit[@Name='Bank Account *']");
            common.clickElement("xpath", "//Edit[@Name='Executive *']");
            super.selectMaster(common.getData(dataFile,"executive"));
            common.clickElement("xpath","//Edit[@Name='Remarks']");
            super.selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
            //f3-deposits
            Thread.sleep(5000);
            super.enterData("xpath","//Edit[@Name='Cash Account * Row 0, Not sorted.']",dataFile,"deposit");
            Thread.sleep(5000);
            super.enterData("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,"credit");
            Thread.sleep(1200);
            //check for withdrawl
            Thread.sleep(5000);
            common.clickElement("xpath","//TabItem[@Name='  F5 Withdrawal  ']");
            super.enterData("xpath","//Edit[@Name='Cash Account * Row 0, Not sorted.']",dataFile,"withdraw");
            Thread.sleep(5000);
            super.enterData("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,"debit");
            Thread.sleep(1200);
            super.enterData("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']",dataFile,"cheque");

            //check summary
            common.clickElement("xpath","//TabItem[@Name='  F8 Summary  ']");


            //save
            super.transactionSave();
            super.lastTransactionName();
            super.transactionClose(common.getData(dataFile,"close"));
        }
    }
