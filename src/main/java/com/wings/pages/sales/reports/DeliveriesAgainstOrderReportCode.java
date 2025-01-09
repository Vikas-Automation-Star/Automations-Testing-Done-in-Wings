package com.wings.pages.sales.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class DeliveriesAgainstOrderReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public DeliveriesAgainstOrderReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void deliveriesAgainstOrders() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Deliveries");
        common.clickElement("xpath", "//MenuItem[@Name='Deliveries against Orders'][2]");
        Thread.sleep(4000);
        common.clickElement("xpath", "//CheckBox[@Name='Detailed']");
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("DELO 1");
        super.closeReport("Deliveries against Orders");
    }
}
