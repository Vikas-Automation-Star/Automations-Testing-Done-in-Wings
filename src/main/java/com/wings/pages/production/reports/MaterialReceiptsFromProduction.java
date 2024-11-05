package com.wings.pages.production.reports;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class MaterialReceiptsFromProduction extends Report {
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
        super.bulkVerifyReport("MRFP 1");
        super.closeReport("Material Receipts from Production");
    }
}
