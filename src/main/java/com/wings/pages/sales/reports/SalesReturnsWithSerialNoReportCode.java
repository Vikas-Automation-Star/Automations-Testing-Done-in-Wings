package com.wings.pages.sales.reports;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

public class SalesReturnsWithSerialNoReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public SalesReturnsWithSerialNoReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void salesReturnwithSerialNoReport() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sale Returns with SerialNo']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("SRT 1");
        super.closeReport("Sale Returns with SerialNo");
    }
}
