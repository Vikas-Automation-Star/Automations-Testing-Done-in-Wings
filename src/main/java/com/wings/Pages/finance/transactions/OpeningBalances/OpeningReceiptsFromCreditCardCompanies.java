package com.wings.pages.finance.transactions.OpeningBalances;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;

public class OpeningReceiptsFromCreditCardCompanies extends Transaction{
        WindowsDriver driver;
        Common common;
        String dataFile;


        public OpeningReceiptsFromCreditCardCompanies(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public void openingReceipts() throws InterruptedException, IOException, ParseException, AWTException {
            common.clickElement("name", "Finance");
            common.clickElement("name", "Opening Balances");
            common.clickElement("xpath", "//MenuItem[@Name='Opening Receipts from Credit Card Companies']");
            Thread.sleep(1000);
            lastTransactionName();
            common.clickElement("xpath","//Edit[@Name='Voucher Type']");
            selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
            common.clickElement("xpath", "//Edit[@Name='Branch *']");
            selectMaster(common.getData(dataFile, "branch"));
            common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
            selectMaster(common.getData(dataFile, "transaction"));
            common.clickElement("xpath", "//Edit[@Name='Executive *']");
            selectMaster(common.getData(dataFile, "executive"));
            common.clickElement("xpath","//Edit[@Name='Remarks']");
            selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
            Thread.sleep(2500);
            //f3-accounts
            common.inputText("xpath","//Edit[@Name='Account Code Row 0, Not sorted.']", common.getData(dataFile,"accountCode"));
            common.clickElement("xpath","//Edit[@Name='Account * Row 0, Not sorted.']");
            enterData("xpath","//Edit[@Name='Swipe Machine Type * Row 0, Not sorted.']", dataFile,"swipeMachineType");
            enterData("xpath","//Edit[@Name='Swipe Type * Row 0, Not sorted.']", dataFile,"swipeType");
            enterData("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']", dataFile,"amount");
            enterData("xpath","//Edit[@Name='Approval No * Row 0, Not sorted.']", dataFile,"approvalNo");
            //save
            transactionSave();
            lastTransactionName();
        }
    }
