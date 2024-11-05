package com.wings.pages.purchase.reports;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class PurchaseQuotations extends Report {
    WindowsDriver driver;
    Common common;

    public PurchaseQuotations(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void purchaseQuotation() throws InterruptedException, AWTException {

        common.clickElement("name", "Purchase");
        common.clickElement("name", "Quotations");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Quotations'][2]");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Purchase Quotations");
    }
}
