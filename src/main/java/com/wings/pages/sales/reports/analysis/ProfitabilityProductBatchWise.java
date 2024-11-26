package com.wings.pages.sales.reports.analysis;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class ProfitabilityProductBatchWise extends Report {
    WindowsDriver driver;
    Common common;

    public ProfitabilityProductBatchWise(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void profitabilityProductBatchWise() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Analysis");
        common.clickElement("xpath", "//MenuItem[@Name='Profitability ProductBatch Wise']");
        Thread.sleep(2000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Profitability ProductBatch Wise");
    }
}
