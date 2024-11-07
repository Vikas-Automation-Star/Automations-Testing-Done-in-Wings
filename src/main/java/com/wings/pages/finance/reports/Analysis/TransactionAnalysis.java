package com.wings.pages.finance.reports.Analysis;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class TransactionAnalysis extends Report {
    WindowsDriver driver;
    Common common;

    public TransactionAnalysis(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void transactionAnalysis() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name","Analysis");
        common.clickElement("name","Transaction Analysis");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
//        Thread.sleep(1500);
//        super.bulkVerifyReport("ILT 1");
        closeReport("Transaction Analysis");
    }
}
