package com.wings.pages.finance.reports.Banking;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class PendingDepositPostDatedChequesReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public PendingDepositPostDatedChequesReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void pendingDepositPostDatedCheques() throws InterruptedException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Pending Deposit Post Dated Cheques']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
//        super.bulkVerifyReport("CE 2");
        closeReport("Pending Deposit Post Dated Cheques");
    }
}
