package com.wings.pages.finance.reports.Banking;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class OpeningUnclearedBankEntriesReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public OpeningUnclearedBankEntriesReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void openingUnclearedBankEntries() throws InterruptedException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Opening Uncleared Bank Entries'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        bulkVerifyReport("OUCBE 2");
        closeReport("Opening Uncleared Bank Entries");
    }
}
