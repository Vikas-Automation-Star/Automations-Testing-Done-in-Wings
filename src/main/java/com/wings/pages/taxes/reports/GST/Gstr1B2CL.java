package com.wings.pages.taxes.reports.GST;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class Gstr1B2CL extends Transaction {
    WindowsDriver driver;
    Common common;

    public Gstr1B2CL(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void gstr1B2CL() throws InterruptedException {
        common.clickElement("name", "Taxes");
        common.clickElement("name", "GST");
        common.clickElement("name", "GSTR1");
        common.clickElement("name", "GSTR1 B2CL");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("vikas35");
        super.closeReport("GSTR1 B2CL");
    }
}
