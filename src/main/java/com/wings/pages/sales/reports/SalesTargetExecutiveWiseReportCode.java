package com.wings.pages.sales.reports;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

public class SalesTargetExecutiveWiseReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public SalesTargetExecutiveWiseReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void executiveWiseReport() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Targets");
        common.clickElement("xpath", "//MenuItem[@Name='Define Sales Targets-Executive Wise'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1000);
        super.bulkVerifyReport("ST 1");
        super.closeReport("Define Sales Targets-Executive Wise");
    }
}
