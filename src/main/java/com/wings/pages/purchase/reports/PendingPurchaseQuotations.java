package com.wings.pages.purchase.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class PendingPurchaseQuotations extends Transaction {
    WindowsDriver driver;
    Common common;

    public PendingPurchaseQuotations(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void pendingPurchaseQuotation() throws InterruptedException, AWTException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Quotations");
        common.clickElement("xpath", "//MenuItem[@Name='Pending Purchase Quotations']");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Pending Purchase Quotations");
    }
}
