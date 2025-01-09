package com.wings.pages.audit.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class VoidTransactionesReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public VoidTransactionesReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void voidTransactions() throws InterruptedException, AWTException {
        common.clickElement("name", "Audit");
        common.clickElement("name", "Void Transactiones");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
//        super.bulkVerifyReport("MSV 1");
        super.closeReport("Void Transactiones");
    }

}
