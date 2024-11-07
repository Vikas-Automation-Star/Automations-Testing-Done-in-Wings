package com.wings.pages.finance.reports.payments;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class CashTransfersReportCode extends Report {
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
        common.clickElement("xpath","//MenuItem[@Name='Cash Transfers'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        bulkVerifyReport("CT 2");
        closeReport("Cash Transfers");
    }

}
