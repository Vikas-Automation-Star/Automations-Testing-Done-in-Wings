package com.wings.pages.purchase.reports;

import com.wings.pages.Report;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;

import java.awt.*;

public class PurchaseVouchersAgainstReceipts extends Transaction {
    WindowsDriver driver;
    Common common;

    public PurchaseVouchersAgainstReceipts(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void purchaseVouchersAgainstReceipt() throws InterruptedException, AWTException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Vouchers against Receipts'][2]");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Purchase Vouchers against Receipts");
        Allure.step("Validating PurchaseVouchersAgainstReceipts Report");
    }
}
