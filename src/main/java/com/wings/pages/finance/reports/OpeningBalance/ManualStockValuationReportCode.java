package com.wings.pages.finance.reports.OpeningBalance;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class ManualStockValuationReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public ManualStockValuationReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void manualStockValuationReport() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Opening Balances");
        common.clickElement("xpath","//MenuItem[@Name='Manual Stock Valuation'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        super.bulkVerifyReport("MSV 1");
        super.closeReport("Manual Stock Valuation");
    }
}
