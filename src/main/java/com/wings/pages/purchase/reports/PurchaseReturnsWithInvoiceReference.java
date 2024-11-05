package com.wings.pages.purchase.reports;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class PurchaseReturnsWithInvoiceReference extends Report {
    WindowsDriver driver;
    Common common;

    public PurchaseReturnsWithInvoiceReference(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void purchaseReturnsWithInvoiceReference() throws InterruptedException, AWTException {

        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Returns with Invoice Reference'][2]");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Purchase Returns with Invoice Reference");
    }

}
