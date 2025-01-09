package com.wings.pages.finance.reports.OpeningBalance;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

import java.awt.*;

public class PartyOpeningBalanceReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public PartyOpeningBalanceReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void partyBalanceReport() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Opening Balances");
        common.clickElement("xpath","//MenuItem[@Name='Party Opening Balances'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        bulkVerifyReport("POB 3");
        closeReport("Party Opening Balances");
    }

}
