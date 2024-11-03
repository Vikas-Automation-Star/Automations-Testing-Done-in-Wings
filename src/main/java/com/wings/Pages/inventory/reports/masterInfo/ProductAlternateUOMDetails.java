package com.wings.pages.inventory.reports.masterInfo;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

import java.awt.*;

public class ProductAlternateUOMDetails extends Report {
    WindowsDriver driver;
    Common common;

    public ProductAlternateUOMDetails(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void masterDetails() throws InterruptedException, AWTException {
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Master Info");
        common.clickElement("name","Product Alternate UOM Details");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
//       here, you need to select the master. while doing actual transaction, write a line of code for that
//        super.bulkVerifyReport("SI 3");
        super.closeReport("Product Alternate UOM Details");
    }
}
