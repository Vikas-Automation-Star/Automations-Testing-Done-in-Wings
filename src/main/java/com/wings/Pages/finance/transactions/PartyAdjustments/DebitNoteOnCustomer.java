package com.wings.pages.finance.transactions.PartyAdjustments;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;

public class DebitNoteOnCustomer extends Transaction {
        WindowsDriver driver;
        Common common;
        String dataFile;

        public DebitNoteOnCustomer(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public void debitNoteOnCustomer() throws InterruptedException, IOException, ParseException, AWTException {
            common.clickElement("name", "Finance");
            common.clickElement("name", "Party Adjustments");
            common.clickElement("xpath", "//MenuItem[@Name='Debit Note on Customers']");
            Thread.sleep(1000);
            super.lastTransactionName();
            common.clickElement("xpath", "//Edit[@Name='Branch *']");
            super.selectAndValidateData(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
            common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
            super.selectAndValidateData(common.getData(dataFile, "transaction"),"xpath", "//Edit[@Name='Trans Currency *']");
            common.inputText("xpath","//Edit[@Name='Sales Invoice No *']", common.getData(dataFile,"invoiceNo"));
            common.clickElement("xpath", "//Edit[@Name='Party Code']");
            super.selectAndValidateData(common.getData(dataFile, "partyCode"),"xpath", "//Edit[@Name='Party Code']");
//            Thread.sleep(20000);
            common.clickElement("xpath","//Edit[@Name='Invoice Type']/Button[@Name='Open']");
            Thread.sleep(7000);
            super.selectDropDown("Regular");
            Thread.sleep(30000);
            common.clickElement("xpath", "//Edit[@Name='Executive *']");
            super.selectAndValidateData(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
//            //f3-accounts
            super.enterData("xpath","//Edit[@Name='Account Code Row 0, Not sorted.']",dataFile,"accountCode");
            common.clickElement("xpath","//CheckBox[@Name='Inclusive Tax Row 0']");
            super.enterData("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,"amount");
            //f5-bills payable
            common.clickElement("xpath","//TabItem[@Name='  F11 Bills Payable  ']");
            super.checkBoxSelection("xpath","//Table[@Name='BillsPayable']/*[starts-with(@Name,'Row')]","//Edit[starts-with(@Name,'Towards VNo *')]","//CheckBox[starts-with(@Name,'Adjust Row')]");
            //f8- summary
            common.clickElement("xpath","//TabItem[@Name='  Ctrl-F7 Summary  ']");
            //check net and payable amount --net>payable
            WebElement netAmount= common.findWebElement("xpath","//Edit[@Name='Net Amount']");
            String net=netAmount.getText();
            System.out.println(net);
            WebElement payable= common.findWebElement("xpath","//Edit[@Name='Bills Payable Adj']");
            String payableAmount= payable.getText();
            System.out.println(payableAmount);
            try {
                double netValue = Double.parseDouble(net);
                double payableValue = Double.parseDouble(payableAmount);

                if (netValue < payableValue) {
                    Assert.fail("Net amount is less than pending amount, enter correct amount");
                }
            }catch (NumberFormatException e) {
                System.out.println("Error parsing amounts: " + e.getMessage());
            }

            //save
            super.transactionSave();
            super.lastTransactionName();
            super.transactionClose(common.getData(dataFile,"close"));
        }
    }
