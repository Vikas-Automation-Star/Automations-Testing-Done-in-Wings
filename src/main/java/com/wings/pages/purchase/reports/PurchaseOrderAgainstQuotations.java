package com.wings.pages.purchase.reports;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class PurchaseOrderAgainstQuotations extends Report {
    WindowsDriver driver;
    Common common;

    public PurchaseOrderAgainstQuotations(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void purchaseOrderAgainstQuotation() throws InterruptedException, AWTException {

        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Orders against Quotations'][2]");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("POAQ 1");
        super.closeReport("Purchase Orders against Quotations");
    }
}


