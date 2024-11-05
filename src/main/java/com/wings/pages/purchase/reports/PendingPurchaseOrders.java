package com.wings.pages.purchase.reports;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class PendingPurchaseOrders extends Report {
    WindowsDriver driver;
    Common common;

    public PendingPurchaseOrders(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void pendingPurchaseOrder() throws InterruptedException, AWTException {

        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Pending Purchase Orders']");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Pending Purchase Orders");
    }
}
