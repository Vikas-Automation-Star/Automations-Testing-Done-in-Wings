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
            navigateToBankPaymentMenu();
            Thread.sleep(1000);
            lastTransactionName();
            //enter data
            common.clickElement("xpath","//Edit[@Name='Branch *']");
            selectAndValidateData(common.getData(dataFile,"branch"),"xpath","//Edit[@Name='Branch *']");
            common.clickElement("xpath","//Edit[@Name='Trans Currency *']");
            selectAndValidateData(common.getData(dataFile,"transaction"),"xpath","//Edit[@Name='Trans Currency *']");
            common.clickElement("xpath", "//Edit[@Name='Bank A/c Code']");
            selectAndValidateData(common.getData(dataFile,"bankAccount"),"xpath","//Edit[@Name='Bank A/c Code']");
            common.clickElement("xpath","//Edit[@Name='Discount Account']");
            selectOptionalMaster(common.getData(dataFile,"discountAccount"),"xpath","//Edit[@Name='Discount Account']");
            common.clickElement("xpath", "//Edit[@Name='Executive *']");
            selectMaster(common.getData(dataFile,"executive"));
            //parties
            Thread.sleep(5000);
            enterData("xpath","//Edit[@Name='Account Code Row 0, Not sorted.']",dataFile,"accountCode");
            Thread.sleep(1000);
            List<WebElement> elementList = common.findWebElements("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']");
            for (WebElement i : elementList) {
                i.click();
                i.sendKeys(String.valueOf(common.getRandom()), Keys.TAB);
            }
            Thread.sleep(1000);
            //check for bills receivable
            navigateToBillsPayablesTab();
            enterData("xpath","//Edit[@Name='Amount Adjusted * Row 0, Not sorted.']",dataFile,"adjustedAmount");
            common.deleteInvalidRows();

//            finalAmount=singleCheckBoxSelection("xpath","//Table[@Name='BillsPayable']/*[starts-with(@Name,'Row')]","//Edit[starts-with(@Name,'Towards VNo *')]");
            //navigate back to parties
            navigateToPartiesTab();
            List<WebElement> amount = common.findWebElements("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']");
            for (WebElement i : amount) {
                i.click();
                i.sendKeys(common.getData(dataFile,"adjustedAmount"), Keys.TAB);
            }

            common.clickElement("xpath", "//TabItem[contains(@Name,'Summary')]");
            //save
            transactionSave();
            lastTransactionName();
        }
    }
