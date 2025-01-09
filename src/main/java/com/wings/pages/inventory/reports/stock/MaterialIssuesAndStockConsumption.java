package com.wings.pages.inventory.reports.stock;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class MaterialIssuesAndStockConsumption extends Transaction {
    WindowsDriver driver;
    Common common;

    public MaterialIssuesAndStockConsumption(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void materialConsumption() throws InterruptedException, AWTException {
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Stock");
        common.clickElement("name", "Material Issues And Stock Consumption");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
//        Thread.sleep(1500);
//        super.bulkVerifyReport("SI 3");
        super.closeReport("Material Issues And Stock Consumption");
    }
}
