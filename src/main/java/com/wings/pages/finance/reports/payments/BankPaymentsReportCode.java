package com.wings.pages.finance.reports.payments;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class BankPaymentsReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public BankPaymentsReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void bankPayment() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Payments");
        common.clickElement("xpath", "//MenuItem[@Name='Bank Payments'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//CheckBox[@Name='Detailed']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        bulkVerifyReport("BP 1");
        closeReport("Bank Payments");
    }
}
