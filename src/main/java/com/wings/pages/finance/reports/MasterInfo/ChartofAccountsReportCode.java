package com.wings.pages.finance.reports.MasterInfo;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class ChartofAccountsReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public ChartofAccountsReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void chartOfAccountsReport() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Master Info");
        common.clickElement("xpath", "//MenuItem[@Name='Chart of Accounts']");
        Thread.sleep(1500);
//        super.bulkVerifyReport("ORFCC 2");
        closeReport("Chart of Accounts");
    }
}
