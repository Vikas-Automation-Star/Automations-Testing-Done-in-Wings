package com.wings.pages.finance.transactions.Journals;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class BookingOfOtherCosts extends Transaction {
        WindowsDriver driver;
        Common common;
        String dataFile;
        double finalAmount = 0.0;

        public BookingOfOtherCosts(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public void otherBookingCosts() throws InterruptedException, IOException, ParseException, AWTException {
            common.clickElement("name", "Finance");
            common.clickElement("name", "Journals");
            common.clickElement("xpath", "//MenuItem[@Name='Booking Of Other Costs']");
            Thread.sleep(1000);
            super.lastTransactionName();
            common.clickElement("xpath", "//Edit[@Name='Branch *']");
            super.selectMaster(common.getData(dataFile, "branch"));
            common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
            super.selectMaster(common.getData(dataFile, "transaction"));
            common.clickElement("xpath","//Edit[@Name='Account Code']");
            super.selectMaster(common.getData(dataFile,"accountCode"));
            common.clickElement("xpath","//Edit[@Name='Account *']");


        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/*/*[contains(@Name,'GST Transaction Type row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().contains("Registered Dealers")) {
                i.click();
                i.sendKeys(Keys.LEFT, Keys.SPACE,Keys.ENTER,Keys.ENTER);
            }
        }
            common.inputText("xpath","//Edit[@Name='Supplier Bill No *']", common.getData(dataFile,"supplierBill"));
            common.inputText("xpath","//Edit[@Name='Supplier Bill Date *']", common.getData(dataFile,"billDate"));

            common.clickElement("xpath", "//Edit[@Name='Executive *']");
            super.selectMaster(common.getData(dataFile, "executive"));
            //f3- accounts
            super.enterData("xpath","//Edit[@Name='Account Code Row 0, Not sorted.']",dataFile,"accountCode2");
            //f12-bills Payable
            common.clickElement("xpath","//TabItem[@Name='  Ctrl-F7 Bills Receivable  ']");
            finalAmount= super.billsReceivable("xpath","//Table[@Name='BillsReceivable']/*[starts-with(@Name,'Row')]","//Edit[starts-with(@Name,'Towards VNo *')]","//CheckBox[starts-with(@Name,'Adjust Row')]","//Edit[starts-with(@Name,'Pending Amount * Row')]");

            //navigate back to accounts and enter data
            common.clickElement("xpath","//TabItem[@Name='  F3 Accounts  ']");
            List<WebElement> amount = common.findWebElements("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']");
            System.out.println("Size :" + amount.size());
            for (WebElement i : amount) {
                i.click();
                i.sendKeys(String.valueOf(finalAmount), Keys.TAB);
            }

            //save
            super.transactionSave();
            super.lastTransactionName();
            super.transactionClose(common.getData(dataFile,"close"));
        }
    }
