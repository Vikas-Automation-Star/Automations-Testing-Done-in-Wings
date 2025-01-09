package com.wings.pages.production.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class MaterialReceiptsFromProduction extends Transaction {
    WindowsDriver driver;
    Common common;

    public MaterialReceiptsFromProduction(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void materialReceiptsFromProduction() throws InterruptedException {
        common.clickElement("name", "Production");
        common.clickElement("name", "Standard");
        common.clickElement("xpath", "//MenuItem[@Name='Material Receipts from Production'][2]");
        Thread.sleep(1500);
        common.clickElement("xpath", "//CheckBox[@Name='Detailed']");
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Material Receipts from Production");
    }
}
