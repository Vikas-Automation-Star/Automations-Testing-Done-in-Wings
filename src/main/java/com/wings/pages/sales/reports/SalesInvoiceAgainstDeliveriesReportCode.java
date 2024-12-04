package com.wings.pages.sales.reports;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

public class SalesInvoiceAgainstDeliveriesReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public SalesInvoiceAgainstDeliveriesReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void salesInvoiceAgainstDeliveries() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Invoices against Deliveries'][2]");
        Thread.sleep(1500);
        common.clickElement("xpath","//CheckBox[@Name='Detailed']");
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("SIAD 2");
        Thread.sleep(3000);
//        super.closeReport("Sales Invoices against Deliveries");
    }
}
