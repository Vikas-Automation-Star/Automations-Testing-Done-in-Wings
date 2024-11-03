package com.wings.pages.finance.reports.partyAdjustments;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class DebitNoteOnCustomersReportCode  extends Report {
    WindowsDriver driver;
    Common common;

    public DebitNoteOnCustomersReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void debitNoteonCustomerReport() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Party Adjustments");
        common.clickElement("xpath","//MenuItem[@Name='Debit Note on Customers'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//CheckBox[@Name='Detailed']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        super.bulkVerifyReport("DNOC 3");
        super.closeReport("Debit Note on Customers");
    }
}
