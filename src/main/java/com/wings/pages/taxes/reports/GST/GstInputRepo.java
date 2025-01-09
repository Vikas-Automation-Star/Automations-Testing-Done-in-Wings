package com.wings.pages.taxes.reports.GST;

import com.wings.pages.Report;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class GstInputRepo extends Transaction {
    WindowsDriver driver;
    Common common;

    public GstInputRepo(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }
    public void gstInputRepo() throws InterruptedException {
        common.clickElement("name", "Taxes");
        common.clickElement("name", "GST");
        common.clickElement("name", "GSTR2");
        common.clickElement("name", "GST Input Report");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("PVAMR 1");
        super.closeReport("GST Input Report");
    }
}
