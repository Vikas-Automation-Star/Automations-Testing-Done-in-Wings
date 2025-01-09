package com.wings.pages.inventory.reports;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class StockConsumptionReprt extends Transaction {
    WindowsDriver driver;
    Common common;

    public StockConsumptionReprt(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void stockConsumption() throws InterruptedException, AWTException {
        common.clickElement("name", "Inventory");
        common.clickElement("xpath", "//MenuItem[@Name='Stock Consumption'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
//        Thread.sleep(1500);
        super.bulkVerifyReport("SCN 1");
        super.closeReport("Stock Consumption");
    }
}
