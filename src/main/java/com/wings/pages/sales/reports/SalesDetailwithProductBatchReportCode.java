package com.wings.pages.sales.reports;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

public class SalesDetailwithProductBatchReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public SalesDetailwithProductBatchReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void salesDetail() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("name","Sales Detailed with Product Batch");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(500);
        super.bulkVerifyReport("SI 1");
        super.closeReport("Sales Detailed with Product Batch");
    }

}
