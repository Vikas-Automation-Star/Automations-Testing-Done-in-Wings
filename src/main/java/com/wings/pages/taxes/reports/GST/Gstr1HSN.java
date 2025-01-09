package com.wings.pages.taxes.reports.GST;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class Gstr1HSN extends Transaction {
    WindowsDriver driver;
    Common common;

    public Gstr1HSN(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void gstr1HSN() throws InterruptedException {
        common.clickElement("name", "Taxes");
        common.clickElement("name", "GST");
        common.clickElement("name", "GSTR1");
        common.clickElement("name", "GSTR1 HSN");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("Cars HSN number given");
        super.closeReport("GSTR1 HSN");
    }

}
