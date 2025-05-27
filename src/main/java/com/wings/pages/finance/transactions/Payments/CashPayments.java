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

        public CashPayments(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public void cashPayment() throws InterruptedException, IOException, ParseException, AWTException {
            navigateToMastersWhen3Steps("Finance", "Payments", "Cash Payments");
//        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
//        selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
            Thread.sleep(1000);
            String oldVoucherID = oldTTransactionID();
            enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "cashPayments", "branch");
            enterInput("xpath", "//Edit[@Name='Cash A/c Code']", dataFile, "cashPayments", "cashActCode");
            enterInput("xpath", "//Edit[@Name='Discount Account']", dataFile, "cashPayments", "discAct");
            enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "cashPayments", "executive");
//        enterInput("xpath", "//Edit[@Name='Remarks']", dataFile, "paymentsToParties","remarks");
        }
    }
