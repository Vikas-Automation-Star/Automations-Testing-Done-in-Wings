package com.wings.pages.finance.reports.OpeningBalance;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class OpeningBalancesReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public OpeningBalancesReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void openingBalance() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Opening Balances");
        common.clickElement("xpath", "//MenuItem[@Name='Opening Balances'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        bulkVerifyReport("OB 1");
        closeReport("Opening Balances");
    }
}
