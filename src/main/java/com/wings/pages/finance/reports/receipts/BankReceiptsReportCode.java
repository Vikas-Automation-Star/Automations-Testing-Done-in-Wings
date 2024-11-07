package com.wings.pages.finance.reports.receipts;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class BankReceiptsReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public BankReceiptsReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void bankReceipt() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath","//MenuItem[@Name='Bank Receipts'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//CheckBox[@Name='Detailed']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        bulkVerifyReport("BR 1");
        closeReport("Bank Receipts");
    }
}
