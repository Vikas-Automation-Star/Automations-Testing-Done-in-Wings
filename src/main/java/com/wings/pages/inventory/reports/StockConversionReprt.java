package com.wings.pages.inventory.reports;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class StockConversionReprt extends Report {
    WindowsDriver driver;
    Common common;

    public StockConversionReprt(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void stockConversion() throws InterruptedException, AWTException {
        common.clickElement("name", "Inventory");
        common.clickElement("xpath", "//MenuItem[@Name='Stock Conversion'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//CheckBox[@Name='Detailed']");
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
//        Thread.sleep(1500);
        super.bulkVerifyReport("SC 7");
        super.closeReport("Stock Conversion");
    }
}
