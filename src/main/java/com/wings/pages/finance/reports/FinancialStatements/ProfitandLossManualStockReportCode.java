package com.wings.pages.finance.reports.FinancialStatements;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

import java.awt.*;

public class ProfitandLossManualStockReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public ProfitandLossManualStockReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void profitLossManualStock() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Financial Statements");
        common.clickElement("xpath","//MenuItem[@Name='Profit and Loss Manual Stock']");
        Thread.sleep(1000);
        common.clickElement("xpath","//CheckBox[@Name='Show Zero Balance Accounts']");
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1000);
//        super.bulkVerifyReport("ORFCC 2");
        closeReport("Profit and Loss Manual Stock");
    }
}
