package com.wings.pages.production.reports;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class PendingMaterialIssuesToProduction extends Report {
    WindowsDriver driver;
    Common common;

    public PendingMaterialIssuesToProduction(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void pendingMaterialIssuesToProduction() throws InterruptedException {

        common.clickElement("name", "Production");
        common.clickElement("name", "Standard");
        common.clickElement("xpath", "//MenuItem[@Name='Pending Material Issues to Production']");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("PRO 3");
        super.closeReport("Pending Material Issues to Production");
    }
}
