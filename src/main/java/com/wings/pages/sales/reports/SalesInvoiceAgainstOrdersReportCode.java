package com.wings.pages.sales.reports;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

public class SalesInvoiceAgainstOrdersReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public SalesInvoiceAgainstOrdersReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void salesInvoiceAgainstorders() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Invoices against Orders'][2]");
        Thread.sleep(1500);
        common.clickElement("xpath","//CheckBox[@Name='Detailed']");
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("SIAO 1");
    }
}
