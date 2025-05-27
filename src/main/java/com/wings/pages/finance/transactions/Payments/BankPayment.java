package com.wings.pages.finance.transactions.Payments;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class BankPayment extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    double finalAmount = 0.0;

    public BankPayment(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void bankPayment() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps("Finance", "Payments", "Bank Payments");
//        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
//        selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
        Thread.sleep(1000);
        String oldVoucherID = oldTTransactionID();
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "bankPayments", "branch");
        enterInput("xpath", "//Edit[@Name='Bank A/c Code']", dataFile, "bankPayments", "bankActCode");
        enterInput("xpath", "//Edit[@Name='Discount Account']", dataFile, "bankPayments", "discAct");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "bankPayments", "executive");
//        enterInput("xpath", "//Edit[@Name='Remarks']", dataFile, "paymentsToParties","remarks");
    }
}
