package com.wings.pages.sales.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class SalesInvoiceAgainstDeliveriesReportCode extends Transaction {
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
        common.clickElement("xpath", "//CheckBox[@Name='Detailed']");
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("SIAD 1");
        Thread.sleep(3000);
//        super.closeReport("Sales Invoices against Deliveries");
    }
}
