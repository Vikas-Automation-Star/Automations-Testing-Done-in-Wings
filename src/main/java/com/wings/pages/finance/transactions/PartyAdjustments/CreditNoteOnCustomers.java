package com.wings.pages.finance.transactions.PartyAdjustments;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;

public class CreditNoteOnCustomers extends Transaction {
        WindowsDriver driver;
        Common common;
        String dataFile;

        public CreditNoteOnCustomers(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public void creditNoteOnCustomer() throws InterruptedException, IOException, ParseException, AWTException {
            navigateToCreditNoteOnCustomerMenu();
            Thread.sleep(1000);
            lastTransactionName();
            common.clickElement("xpath","//Edit[@Name='Voucher Type']");
            selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
            common.clickElement("xpath", "//Edit[@Name='Branch *']");
            selectAndValidateData(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
            common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
            selectAndValidateData(common.getData(dataFile, "transaction"),"xpath", "//Edit[@Name='Trans Currency *']");
            common.inputText("xpath","//Edit[@Name='Sales Invoice No *']", common.getData(dataFile,"invoiceNo"));
            common.clickElement("xpath", "//Edit[@Name='Party Code']");
            selectAndValidateData(common.getData(dataFile, "partyCode"),"xpath", "//Edit[@Name='Party Code']");
//            Thread.sleep(20000);
            common.clickElement("xpath","//Edit[@Name='Invoice Type']/Button[@Name='Open']");
            Thread.sleep(7000);
            selectDropDown("Regular");
            Thread.sleep(30000);
            common.clickElement("xpath", "//Edit[@Name='Executive *']");
            selectAndValidateData(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
//            //f3-accounts
            enterData("xpath","//Edit[@Name='Account Code Row 0, Not sorted.']",dataFile,"accountCode");
            common.clickElement("xpath","//CheckBox[@Name='Inclusive Tax Row 0']");
            enterData("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,"amount");
            //f5-bills receivable
            navigateToBillsReceivablesTab();
            checkBoxSelection("xpath","//Table[@Name='BillsReceivable']/*[starts-with(@Name,'Row')]","//Edit[starts-with(@Name,'Towards VNo *')]","//CheckBox[starts-with(@Name,'Adjust Row')]");
            //f8- summary
            navigateToSummaryTab();
            //check net and payable amount --net>payable
            WebElement netAmount= common.findWebElement("xpath","//Edit[@Name='Net Amount']");
            String net=netAmount.getText();
            System.out.println(net);
            WebElement receivable = common.findWebElement("xpath","//Edit[@Name='Bills Receivable Adj']");
            String payableAmount= receivable.getText();
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
            transactionSave();
            lastTransactionName();
        }
    }
