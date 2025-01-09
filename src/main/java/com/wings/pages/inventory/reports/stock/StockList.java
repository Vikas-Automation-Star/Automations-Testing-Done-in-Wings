package com.wings.pages.inventory.reports.stock;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class StockList extends Transaction {
    WindowsDriver driver;
    Common common;

    public StockList(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void stckList() throws InterruptedException, AWTException {
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Stock");
        common.clickElement("name","Stock List");
        Thread.sleep(1000);
        common.clickElement("xpath","//CheckBox[@Name='Detail']");
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
//        Thread.sleep(1500);
        super.bulkVerifyReport("SI 3");
        super.closeReport("Stock List");
    }
}
