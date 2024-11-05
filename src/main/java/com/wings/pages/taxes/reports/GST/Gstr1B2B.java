package com.wings.pages.taxes.reports.GST;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class Gstr1B2B extends Report {
    WindowsDriver driver;
    Common common;

    public Gstr1B2B(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void Gstr1B2B() throws InterruptedException {

        common.clickElement("name", "Taxes");
        common.clickElement("name", "GST");
        common.clickElement("name", "GSTR1");
        common.clickElement("name", "GSTR1 B2B");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("vikas35");
        super.closeReport("GSTR1 B2B");
    }
}
