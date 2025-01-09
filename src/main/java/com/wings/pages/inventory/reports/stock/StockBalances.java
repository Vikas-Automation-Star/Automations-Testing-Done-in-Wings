package com.wings.pages.inventory.reports.stock;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class StockBalances extends Transaction {
    WindowsDriver driver;
    Common common;

    public StockBalances(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void stckBalance() throws InterruptedException, AWTException {
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Stock");
        common.clickElement("name", "Stock Balances");
        Thread.sleep(1000);
        common.clickElement("xpath", "//CheckBox[@Name='Detailed']");
        common.clickElement("xpath", "//CheckBox[@Name='Show  Zero  Balances']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
//        super.bulkVerifyReport("SI 3");
        super.closeReport("Stock Balances");
    }
}
