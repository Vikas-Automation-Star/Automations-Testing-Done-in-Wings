package com.wings.pages.finance.reports.Journals;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class BookExpensesOrPayablesReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public BookExpensesOrPayablesReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void payablesReport() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Journals");
        common.clickElement("xpath","//MenuItem[@Name='Book Expenses or Payables'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//CheckBox[@Name='Detailed']");
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1000);
        bulkVerifyReport("BEP 1");
        closeReport("Book Expenses or Payables");
    }
}
