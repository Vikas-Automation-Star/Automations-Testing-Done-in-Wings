package com.wings.pages.production.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class MaterialIssuesToProduction extends Transaction {
    WindowsDriver driver;
    Common common;

    public MaterialIssuesToProduction(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void MaterialIssuesToProduction() throws InterruptedException {
        common.clickElement("name", "Production");
        common.clickElement("name", "Standard");
        common.clickElement("xpath", "//MenuItem[@Name='Material Issues to Production'][2]");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Material Issues to Production");
        Thread.sleep(2000);
    }
}
