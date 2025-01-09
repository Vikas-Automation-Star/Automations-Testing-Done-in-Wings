package com.wings.pages.finance.reports.Analysis;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class TrendAnalysis extends Transaction {
    WindowsDriver driver;
    Common common;

    public TrendAnalysis(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void trendingAnalysis() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Analysis");
        common.clickElement("name", "Trend Analysis");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
//        Thread.sleep(1500);
//        super.bulkVerifyReport("ILT 1");
        closeReport("Trend Analysis");
    }
}
