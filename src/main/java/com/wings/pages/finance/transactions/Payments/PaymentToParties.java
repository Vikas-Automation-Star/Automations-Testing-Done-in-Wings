package com.wings.pages.finance.transactions.Payments;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PaymentToParties extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PaymentToParties(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void paymentToParty() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps("Finance", "Payments", "Payments to Parties");
//        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
//        selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
        Thread.sleep(1000);
        String oldVoucherID = oldTTransactionID();
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "paymentsToParties", "branch");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "paymentsToParties", "partyCode");
        enterInput("xpath", "//Edit[@Name='Discount Account']", dataFile, "paymentsToParties", "discAct");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "paymentsToParties", "executive");
//        enterInput("xpath", "//Edit[@Name='Remarks']", dataFile, "paymentsToParties","remarks");

    }
}
