package com.wings.pages.finance.reports.Banking;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

public class PendingDepositPostDatedChequesReportCode extends Report {
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
        common.clickElement("xpath","//MenuItem[@Name='Pending Deposit Post Dated Cheques']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
//        super.bulkVerifyReport("CE 2");
        super.closeReport("Pending Deposit Post Dated Cheques");
    }
}
