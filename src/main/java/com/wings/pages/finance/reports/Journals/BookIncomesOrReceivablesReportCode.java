package com.wings.pages.finance.reports.Journals;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class BookIncomesOrReceivablesReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public BookIncomesOrReceivablesReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void receivablesReport() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Journals");
        common.clickElement("xpath","//MenuItem[@Name='Book Incomes or Receivables'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//CheckBox[@Name='Detailed']");
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1000);
        bulkVerifyReport("BIR 1");
        closeReport("Book Incomes or Receivables");
    }
}
