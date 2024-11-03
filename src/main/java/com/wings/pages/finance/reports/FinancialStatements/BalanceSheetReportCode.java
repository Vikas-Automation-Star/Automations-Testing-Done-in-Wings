package com.wings.pages.finance.reports.FinancialStatements;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class BalanceSheetReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public BalanceSheetReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void balanceSheet() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Financial Statements");
        common.clickElement("xpath","//MenuItem[@Name='Balance Sheet']");
        Thread.sleep(1200);
        common.clickElement("xpath","//CheckBox[@Name='Show Zero Balances Account']");
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
//        super.bulkVerifyReport("ORFCC 2");
        super.closeReport("Balance Sheet");
    }
}
