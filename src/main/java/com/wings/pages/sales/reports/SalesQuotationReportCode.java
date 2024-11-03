package com.wings.pages.sales.reports;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

public class SalesQuotationReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public SalesQuotationReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void quotationReport() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name","Quotations");
        common.clickElement("xpath","//MenuItem[@Name='Sales Quotations'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(3000);
        super.bulkVerifyReport("SQ 3");
        super.closeReport("Sales Quotations");
    }
}
