package com.wings.pages.production.reports;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class MaterialReturnsFromProduction extends Report {
    WindowsDriver driver;
    Common common;

    public MaterialReturnsFromProduction(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void materialReturnsFromProduction() throws InterruptedException {
        common.clickElement("name", "Production");
        common.clickElement("name", "Standard");
        common.clickElement("xpath", "//MenuItem[@Name='Material Returns from Production'][2]");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Material Returns from Production");
        Thread.sleep(2000);
    }
}
