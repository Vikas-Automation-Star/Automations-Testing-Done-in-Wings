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

public class CashPayments extends Transaction {
        WindowsDriver driver;
        Common common;
        String dataFile;
        double finalAmount=0.0;

        public CashPayments(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public void cashPayment() throws InterruptedException, IOException, ParseException, AWTException {
            navigateToCashPaymentsMenu();
            Thread.sleep(1000);
            lastTransactionName();
            //enter data
            common.clickElement("xpath","//Edit[@Name='Voucher Type']");
            selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
            common.clickElement("xpath","//Edit[@Name='Branch *']");
            selectAndValidateData(common.getData(dataFile,"branch"),"xpath","//Edit[@Name='Branch *']");
            common.clickElement("xpath","//Edit[@Name='Trans Currency *']");
            selectMaster(common.getData(dataFile,"transaction"));
            common.clickElement("xpath", "//Edit[@Name='Cash A/c Code']");
            common.clickElement("xpath","//Edit[@Name='Discount Account']");
            selectOptionalMaster(common.getData(dataFile,"discountAccount"),"xpath","//Edit[@Name='Discount Account']");
            common.clickElement("xpath", "//Edit[@Name='Executive *']");
            selectMaster(common.getData(dataFile,"executive"));
            common.clickElement("xpath","//Edit[@Name='Remarks']");
            selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
            //f3-items
            Thread.sleep(5000);
            enterData("xpath","//Edit[@Name='Account Code Row 0, Not sorted.']",dataFile,"accountCode");
            Thread.sleep(1000);
            //check for bills receivable
            Thread.sleep(5000);
            navigateToBillsPayablesTab();
            finalAmount=singleCheckBoxSelection("xpath","//Table[@Name='BillsPayable']/*[starts-with(@Name,'Row')]","//Edit[starts-with(@Name,'Towards VNo *')]");
            //navigate back to Parties
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
        }
    }
