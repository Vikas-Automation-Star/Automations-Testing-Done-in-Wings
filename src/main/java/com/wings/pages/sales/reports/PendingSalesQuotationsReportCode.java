package com.wings.pages.sales.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class PendingSalesQuotationsReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public PendingSalesQuotationsReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void pendingQuotationReport() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Quotations");
        common.clickElement("name", "Pending Sales Quotations");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1000);

        super.bulkVerifyReport("SQAE 1");
        super.closeReport("Pending Sales Quotations");
    }
}
