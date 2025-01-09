package com.wings.pages.production.reports;

import com.wings.pages.Report;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class CloseProductionOrder extends Transaction {
    WindowsDriver driver;
    Common common;

    public CloseProductionOrder(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void closeProductionOrder() throws InterruptedException {
        common.clickElement("name", "Production");
        common.clickElement("name", "Standard");
        common.clickElement("xpath", "//MenuItem[@Name='Close Production Order'][2]");
        Thread.sleep(1500);
//        common.clickElement("xpath", "//CheckBox[@Name='Detailed']");
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Close Production Order");
    }
}
