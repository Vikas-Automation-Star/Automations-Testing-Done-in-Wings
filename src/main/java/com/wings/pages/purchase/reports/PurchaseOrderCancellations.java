package com.wings.pages.purchase.reports;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class PurchaseOrderCancellations extends Report {
    WindowsDriver driver;
    Common common;

    public PurchaseOrderCancellations(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void purchaseOrderCancellation() throws InterruptedException, AWTException {

        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Orders Cancellation'][2]");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("POC 1");
        super.closeReport("Purchase Orders Cancellation");
    }
}
