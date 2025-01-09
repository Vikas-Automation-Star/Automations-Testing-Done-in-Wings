package com.wings.pages.sales.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class PendingDeliveriesReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public PendingDeliveriesReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void pendingSalesDeliveries() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Deliveries");
        common.clickElement("xpath", "//MenuItem[@Name='Pending Deliveries']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("SO 1");
        super.closeReport("Pending Deliveries");
    }
}
