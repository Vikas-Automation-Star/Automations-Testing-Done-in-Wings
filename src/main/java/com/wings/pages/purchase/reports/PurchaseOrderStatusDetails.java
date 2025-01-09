package com.wings.pages.purchase.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;

//Purchase Order Status Details
public class PurchaseOrderStatusDetails extends Transaction {
    WindowsDriver driver;
    Common common;

    public PurchaseOrderStatusDetails(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void purchaseOrderStatusReport() throws InterruptedException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("name", "Purchase Order Status Details");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Purchase Order Status Details");
        Allure.step("Validating PurchaseOrdersStatusDetails Report");
    }
}
