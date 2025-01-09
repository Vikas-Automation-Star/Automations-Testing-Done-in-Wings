package com.wings.pages.production.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class BillOfMaterial extends Transaction {
    WindowsDriver driver;
    Common common;

    public BillOfMaterial(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void billOfMaterial() throws InterruptedException, AWTException {

        common.clickElement("name", "Production");
        common.clickElement("name", "Bill of Material");
        common.clickElement("xpath", "//MenuItem[@Name='Bill of Materials']");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
//        super.bulkVerifyReport("PVAMR 1");
        super.closeReport("Bill of Materials");
    }
}
