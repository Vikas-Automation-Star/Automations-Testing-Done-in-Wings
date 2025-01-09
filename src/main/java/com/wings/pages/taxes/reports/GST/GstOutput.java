package com.wings.pages.taxes.reports.GST;

import com.wings.pages.Report;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class GstOutput extends Transaction {
    WindowsDriver driver;
    Common common;

    public GstOutput(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }
    public void gstOutput() throws InterruptedException {
        common.clickElement("name", "Taxes");
        common.clickElement("name", "GST");
        common.clickElement("name", "GSTR1");
        common.clickElement("name", "GST Output Report");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("SI 7");
        super.closeReport("GST Output Report");
    }
}
