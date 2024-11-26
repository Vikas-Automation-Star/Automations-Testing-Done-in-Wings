package com.wings.pages.sales.reports.analysis;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class Profitability extends Report {
    WindowsDriver driver;
    Common common;

    public Profitability(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void profitabilityReport() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Analysis");
        common.clickElement("xpath", "//MenuItem[@Name='Profitability']");
        Thread.sleep(2000);
        common.clickElement("xpath","//CheckBox[@Name='Detailed']");
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Profitability");
    }
}
