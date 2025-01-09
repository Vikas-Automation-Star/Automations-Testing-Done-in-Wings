package com.wings.pages.sales.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class DeliveriesReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public DeliveriesReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void deliveries() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Deliveries");
        common.clickElement("xpath", "//MenuItem[@Name='Deliveries'][2]");
        Thread.sleep(4000);
        common.clickElement("xpath", "//CheckBox[@Name='Detailed']");
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("DELO 1");
    }
}
