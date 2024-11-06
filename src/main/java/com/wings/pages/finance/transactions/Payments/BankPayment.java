package com.wings.pages.finance.transactions.Payments;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class BankPayment extends Transaction {
        WindowsDriver driver;
        Common common;
        String dataFile;
        double finalAmount=0.0;

        public BankPayment(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public void bankPayment() throws InterruptedException, IOException, ParseException, AWTException {
            common.clickElement("name", "Finance");
            common.clickElement("name", "Payments");
            common.clickElement("xpath", "//MenuItem[@Name='Bank Payments']");
            Thread.sleep(1000);
            lastTransactionName();
            //enter data
            common.clickElement("xpath","//Edit[@Name='Branch *']");
            selectAndValidateData(common.getData(dataFile,"branch"),"xpath","//Edit[@Name='Branch *']");
            common.clickElement("xpath","//Edit[@Name='Trans Currency *']");
            selectAndValidateData(common.getData(dataFile,"transaction"),"xpath","//Edit[@Name='Trans Currency *']");
            common.clickElement("xpath", "//Edit[@Name='Bank A/c Code']");
            common.clickElement("xpath", "//Edit[@Name='Executive *']");
            selectAndValidateData(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
            //f3-items
            Thread.sleep(5000);
            enterData("xpath","//Edit[@Name='Account Code Row 0, Not sorted.']",dataFile,"accountCode");
            Thread.sleep(1000);
            enterData("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']",dataFile,"cheque");
            Thread.sleep(1000);
            //check for bills receivable
            navigateToBillsPayablesTab();
            finalAmount=singleCheckBoxSelection("xpath","//Table[@Name='BillsPayable']/*[starts-with(@Name,'Row')]","//Edit[starts-with(@Name,'Towards VNo *')]");
            //navigate back to parties
            navigateToPartiesTab();
            List<WebElement> amount = common.findWebElements("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']");
            for (WebElement i : amount) {
                i.click();
                i.sendKeys(String.valueOf(finalAmount), Keys.TAB);
            }
            //check summary
            navigateToSummaryTab();
            //save
            transactionSave();
            lastTransactionName();
//            transactionClose(common.getData(dataFile,"close"));
        }
    }
