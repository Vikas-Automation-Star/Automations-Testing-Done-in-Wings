package com.wings.pages.audit.reports;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class TransactionActivityLogReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public TransactionActivityLogReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void transactionActivitylog() throws InterruptedException, AWTException {
        common.clickElement("name", "Audit");
        common.clickElement("name", "Transaction Activity Log");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
//        super.bulkVerifyReport("MSV 1");
        super.closeReport("Transaction Activity Log");
    }
}
