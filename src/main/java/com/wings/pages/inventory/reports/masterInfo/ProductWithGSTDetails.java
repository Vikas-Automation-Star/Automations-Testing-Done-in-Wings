package com.wings.pages.inventory.reports.masterInfo;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class ProductWithGSTDetails extends Report {
    WindowsDriver driver;
    Common common;

    public ProductWithGSTDetails(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void productGST() throws InterruptedException, AWTException {
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Master Info");
        common.clickElement("name","Product with GST Details");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
//        Thread.sleep(1500);
//        super.bulkVerifyReport("SI 3");
        super.closeReport("Product with GST Details");
    }
}
