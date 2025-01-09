package com.wings.pages.sales.reports;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

public class SalesQuotationCancellationReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public SalesQuotationCancellationReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void quotationCancelReport() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name","Quotations");
        common.clickElement("xpath","//MenuItem[@Name='Sales Quotations Cancellations'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("SQC 1");
        super.closeReport("Sales Quotations Cancellations");
    }
}
