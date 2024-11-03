package com.wings.pages.audit.reports;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class DeletedTransactionsReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public DeletedTransactionsReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void deletedTransactions() throws InterruptedException, AWTException {
        common.clickElement("name", "Audit");
        common.clickElement("name", "Deleted Transactions");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
//        super.bulkVerifyReport("MSV 1");
        super.closeReport("Deleted Transactions");
    }
}
