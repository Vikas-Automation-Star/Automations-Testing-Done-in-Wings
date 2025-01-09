package com.wings.pages.finance.reports.payments;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class CashTransfersReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public CashTransfersReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void cashTransfer() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Payments");
        common.clickElement("xpath", "//MenuItem[@Name='Cash Transfers'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        bulkVerifyReport("CT 2");
        closeReport("Cash Transfers");
    }

}
