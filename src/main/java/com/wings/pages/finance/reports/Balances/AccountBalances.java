package com.wings.pages.finance.reports.Balances;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

import java.awt.*;

public class AccountBalances extends Report {
    WindowsDriver driver;
    Common common;

    public AccountBalances(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void accountBalanceReport() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name","Balances");
        common.clickElement("name","Account Balances");
        Thread.sleep(1000);
        common.clickElement("xpath","//CheckBox[@Name='Detailed']");
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
//        Thread.sleep(1500);
//        super.bulkVerifyReport("ILT 1");
        super.closeReport("Account Balances");
    }
}
