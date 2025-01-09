package com.wings.pages.inventory.reports.masterInfo;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

import java.awt.*;

public class ProductBatches extends Transaction {
    WindowsDriver driver;
    Common common;

    public ProductBatches(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void productBatch() throws InterruptedException, AWTException {
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Master Info");
        common.clickElement("name","Product Batches");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
//        Thread.sleep(1500);
        super.bulkVerifyReport("Product Batch246");
        super.closeReport("Product Batches");
    }

}
