package com.wings.pages.finance.reports.receipts;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class PendingCreditCardReceiptsReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public PendingCreditCardReceiptsReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void pendingCardReceipt() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Pending Credit Card Receipts']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
//        super.bulkVerifyReport("CR 1");
        closeReport("Pending Credit Card Receipts");
    }
}
