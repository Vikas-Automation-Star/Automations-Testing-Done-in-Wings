package com.wings.pages.inventory.reports.stock;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class StockTransactionAnalysis extends Transaction {
    WindowsDriver driver;
    Common common;

    public StockTransactionAnalysis(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void stckTransactionAnalysis() throws InterruptedException, AWTException {
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Stock");
        common.clickElement("name", "Stock Transaction Analysis");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
//        Thread.sleep(1500);
//        super.bulkVerifyReport("SI 3");
        super.closeReport("Stock Transaction Analysis");
    }
}
