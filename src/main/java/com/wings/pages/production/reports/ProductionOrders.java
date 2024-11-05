package com.wings.pages.production.reports;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class ProductionOrders extends Report {
    WindowsDriver driver;
    Common common;

    public ProductionOrders(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void productionOrders() throws InterruptedException {

        common.clickElement("name", "Production");
        common.clickElement("name", "Standard");

        common.clickElement("xpath", "//MenuItem[@Name='Production Orders'][2]");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("PRO 3");
        super.closeReport("Production Orders");
    }
}
