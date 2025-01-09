package com.wings.pages.inventory.reports;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class OpeningStockReprt extends Transaction {
    WindowsDriver driver;
    Common common;

    public OpeningStockReprt(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void openStockReport() throws InterruptedException, AWTException {
        common.clickElement("name", "Inventory");
        common.clickElement("xpath", "//MenuItem[@Name='Opening Stock'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//CheckBox[@Name='Detailed']");
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(2500);
//        super.bulkVerifyReport("OS 6");
        super.closeReport("Opening Stock");
    }
}