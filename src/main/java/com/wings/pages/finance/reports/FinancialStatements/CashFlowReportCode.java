package com.wings.pages.finance.reports.FinancialStatements;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class CashFlowReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public CashFlowReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void cashFlow() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Financial Statements");
        common.clickElement("xpath","//MenuItem[@Name='Cash Flow']");
        Thread.sleep(1200);
        common.clickElement("xpath","//CheckBox[@Name='Detailed']");
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
//        super.bulkVerifyReport("ORFCC 2");
        closeReport("Cash Flow");
    }
}
