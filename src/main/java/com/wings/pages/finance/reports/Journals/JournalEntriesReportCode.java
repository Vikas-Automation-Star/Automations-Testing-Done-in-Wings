package com.wings.pages.finance.reports.Journals;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class JournalEntriesReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public JournalEntriesReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void journalEntry() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Journals");
        common.clickElement("xpath","//MenuItem[@Name='Journal Entries'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1000);
        bulkVerifyReport("JE 3");
        closeReport("Journal Entries");
    }
}
