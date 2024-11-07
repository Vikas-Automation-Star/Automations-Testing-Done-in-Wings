package com.wings.pages.finance.reports.partyAdjustments;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

import java.awt.*;

public class DebitNoteReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public DebitNoteReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void debitNoteReport() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Party Adjustments");
        common.clickElement("xpath","//MenuItem[@Name='Debit Note'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//CheckBox[@Name='Detailed']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        bulkVerifyReport("DN 4");
        closeReport("Debit Note");
    }
}
