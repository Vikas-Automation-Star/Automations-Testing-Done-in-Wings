package com.wings.pages.finance.reports.FinancialStatements;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class BalanceSheetTFormReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public BalanceSheetTFormReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void balanceSheetTform() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Financial Statements");
        common.clickElement("xpath", "//MenuItem[@Name='Balance Sheet T-Form']");
        Thread.sleep(1200);
        common.clickElement("xpath", "//CheckBox[@Name='Show Zero Balances Account']");
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
//        super.bulkVerifyReport("ORFCC 2");
        closeReport("Balance Sheet T-Form");
    }
}
