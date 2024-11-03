package com.wings.pages.finance.transactions.PartyAdjustments;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.wings.pages.Transaction;
import com.wings.utils.Common;

import java.awt.*;
import java.io.IOException;

public class CreditNoteAdjustPartyBills extends Transaction{

        WindowsDriver driver;
        Common common;
        String dataFile;

        public CreditNoteAdjustPartyBills(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public void creditNoteAdjustBills() throws InterruptedException, IOException, ParseException, AWTException {
            common.clickElement("name", "Finance");
            common.clickElement("name", "Party Adjustments");
            common.clickElement("xpath", "//MenuItem[@Name='Credit Note']");
            Thread.sleep(1000);
            super.lastTransactionName();
            common.clickElement("xpath", "//Edit[@Name='Branch *']");
            super.selectAndValidateData(common.getData(dataFile, "branch"),"xpath","//Edit[@Name='Branch *']");
            common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
            super.selectAndValidateData(common.getData(dataFile, "transaction"),"xpath","//Edit[@Name='Trans Currency *']");

            common.clickElement("xpath", "//Edit[@Name='Party Code']");
            super.selectAndValidateData(common.getData(dataFile, "partyCode"),"xpath","//Edit[@Name='Party Code']");
            common.clickElement("xpath", "//Edit[@Name='Party Account *']");
            Thread.sleep(2000);
            common.clickElement("xpath","//Edit[@Name='Invoice Type']/Button[@Name='Open']");
            super.selectDropDown("Regular");
            Thread.sleep(10000);
            common.clickElement("xpath", "//Edit[@Name='Executive *']");
            super.selectAndValidateData(common.getData(dataFile, "executive"),"xpath","//Edit[@Name='Executive *']");

            //f3-accounts
            super.enterData("xpath","//Edit[@Name='Account Code Row 0, Not sorted.']",dataFile,"accountCode");
            super.enterData("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,"amount");
            //validate account code here
            super.validateElements("xpath","//Edit[@Name='Account Code Row 0, Not sorted.']", common.getData(dataFile,"accountCode"));
            common.clickElement("xpath","//CheckBox[@Name='Deduct TDS Row 0']");
            super.enterDataAndValidate("xpath","//Edit[@Name='TDS Transaction Nature Row 0, Not sorted.']",dataFile,"TDSNature");

            //f5-bills receivable
            common.clickElement("xpath","//TabItem[@Name='  F5 Bills Receivable  ']");
            WebElement element= common.findWebElement("xpath","//Edit[@Name=' Row 0, Not sorted.']");
            Actions actions=new Actions(driver);
            actions.contextClick(element).perform();
            common.clickElement("xpath","//MenuItem[@Name='Delete Invalid Rows']");
//            super.checkBoxSelection("xpath","//Table[@Name='BillsReceivable']/*[starts-with(@Name,'Row')]","//Edit[starts-with(@Name,'Towards VNo *')]","//CheckBox[starts-with(@Name,'Adjust Row')]");
            //save
            super.transactionSave();
            super.lastTransactionName();
//            super.transactionClose(common.getData(dataFile,"close"));
        }
    }
