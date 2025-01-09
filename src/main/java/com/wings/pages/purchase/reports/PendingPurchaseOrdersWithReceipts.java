package com.wings.pages.purchase.reports;

import com.wings.pages.Report;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;


public class PendingPurchaseOrdersWithReceipts extends Transaction {
    WindowsDriver driver;
    Common common;

    public PendingPurchaseOrdersWithReceipts(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void pendingPurchaseOrdersWithReceipt() throws InterruptedException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Pending Purchase Orders with Receipts']");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Pending Purchase Orders with Receipts");
        Allure.step("Validating PendingPurchaseOrdersWithReceipts Report");
    }
}
