package com.wings.pages.finance.reports.Banking;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class BankReconciliationReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public BankReconciliationReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void reconciliation() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath","//MenuItem[@Name='Bank Reconciliation'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//CheckBox[@Name='Cleared  Cheques']");
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
//        super.bulkVerifyReport("PREC 2");
        closeReport("Bank Reconciliation");
    }
}
