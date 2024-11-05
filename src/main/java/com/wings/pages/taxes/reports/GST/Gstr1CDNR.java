package com.wings.pages.taxes.reports.GST;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class Gstr1CDNR extends Report {
    WindowsDriver driver;
    Common common;

    public Gstr1CDNR(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }
    public void gstr1CDNR() throws InterruptedException {
        common.clickElement("name", "Taxes");
        common.clickElement("name", "GST");
        common.clickElement("name", "GSTR1");
        common.clickElement("name", "GSTR1 CDNR");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("SRWIR2");
        super.closeReport("GSTR1 CDNR");
    }
}
