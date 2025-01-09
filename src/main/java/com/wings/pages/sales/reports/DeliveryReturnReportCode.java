package com.wings.pages.sales.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class DeliveryReturnReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public DeliveryReturnReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void DeliveryReturns() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Deliveries");
        common.clickElement("xpath", "//MenuItem[@Name='Delivery Returns'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//CheckBox[@Name='Detailed']");
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("DR 1");
        super.closeReport("Delivery Returns");
    }
}
