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
            navigateToBookingOfOtherCosts();
            Thread.sleep(1000);
            lastTransactionName();
            common.clickElement("xpath","//Edit[@Name='Voucher Type']");
            selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
            common.clickElement("xpath", "//Edit[@Name='Branch *']");
            selectAndValidateData(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
            common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
            selectAndValidateData(common.getData(dataFile, "transaction"),"xpath", "//Edit[@Name='Trans Currency *']");
            common.clickElement("xpath","//Edit[@Name='Account Code']");
            selectAndValidateData(common.getData(dataFile,"accountCode"),"xpath","//Edit[@Name='Account Code']");
            common.clickElement("xpath","//Edit[@Name='Account *']");

            gstTransactionType("Registered Dealers");


//        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/*/*[contains(@Name,'GST Transaction Type row')]");
//        System.out.println("Size :" + elementList.size());
//        for (WebElement i : elementList) {
//            System.out.println(i.getText());
//            if (i.getText().contains("Registered Dealers")) {
//                i.click();
//                i.sendKeys(Keys.LEFT, Keys.SPACE,Keys.ENTER,Keys.ENTER);
//            }
//        }
            common.inputText("xpath","//Edit[@Name='Supplier Bill No *']", common.getData(dataFile,"supplierBill"));
            common.inputText("xpath","//Edit[@Name='Supplier Bill Date *']", common.getData(dataFile,"billDate"));

            common.clickElement("xpath", "//Edit[@Name='Executive *']");
            selectAndValidateData(common.getData(dataFile, "executive"),"xpath", "//Edit[@Name='Executive *']");
            common.clickElement("xpath","//Edit[@Name='Remarks']");
            selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
            //f3- accounts
            enterData("xpath","//Edit[@Name='Account Code Row 0, Not sorted.']",dataFile,"accountCode2");
            //f12-bills Receivable
            navigateToBillsReceivablesTab();
            finalAmount= super.billsReceivable("xpath","//Table[@Name='BillsReceivable']/*[starts-with(@Name,'Row')]","//Edit[starts-with(@Name,'Towards VNo *')]","//CheckBox[starts-with(@Name,'Adjust Row')]","//Edit[starts-with(@Name,'Pending Amount * Row')]");

            //navigate back to accounts and enter data
            navigateToAccountsTab();
            List<WebElement> amount = common.findWebElements("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']");
            System.out.println("Size :" + amount.size());
            for (WebElement i : amount) {
                i.click();
                i.sendKeys(String.valueOf(finalAmount), Keys.TAB);
            }
            //save
            transactionSave();
            lastTransactionName();
//            super.transactionClose(common.getData(dataFile,"close"));
        }
    }
