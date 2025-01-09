package com.wings.pages.inventory.reports.stock;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

import java.awt.*;

public class StockAgeing extends Transaction {
    WindowsDriver driver;
    Common common;

    public StockAgeing(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void stckAgeing() throws InterruptedException, AWTException {
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Stock");
        common.clickElement("name","Stock Ageing");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
//        Thread.sleep(1500);
//        super.bulkVerifyReport("SI 3");
        super.closeReport("Stock Ageing");
    }
}
