package com.wings.pages.sales.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class SalesOrderAgainstQuotationReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public SalesOrderAgainstQuotationReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void orderAgnstQuotation() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Orders against Quotations'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1000);
        super.bulkVerifyReport("SOAQ 1");
        super.closeReport("Sales Orders against Quotations");
    }
}
