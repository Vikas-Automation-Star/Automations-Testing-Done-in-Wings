package com.wings.pages.taxes.reports.GST;

import com.wings.pages.Report;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class Gstr1EXEMP extends Transaction {
    WindowsDriver driver;
    Common common;

    public Gstr1EXEMP(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }
    public void gstr1EXEMP() throws InterruptedException {
        common.clickElement("name", "Taxes");
        common.clickElement("name", "GST");
        common.clickElement("name", "GSTR1");
        common.clickElement("name", "GSTR1 EXEMP");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("GSTR1 EXEMP");
    }
}
