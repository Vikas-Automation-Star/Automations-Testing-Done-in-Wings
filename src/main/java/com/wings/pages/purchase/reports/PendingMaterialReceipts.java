package com.wings.pages.purchase.reports;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class PendingMaterialReceipts extends Report {
    WindowsDriver driver;
    Common common;

    public PendingMaterialReceipts(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void pendingMaterialReceipt() throws InterruptedException {

        common.clickElement("name", "Purchase");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Pending Material Receipts']");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Pending Material Receipts");
    }
}
