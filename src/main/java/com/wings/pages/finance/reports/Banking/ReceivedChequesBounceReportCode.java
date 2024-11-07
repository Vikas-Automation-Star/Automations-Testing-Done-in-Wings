package com.wings.pages.finance.reports.Banking;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

import java.awt.*;

public class ReceivedChequesBounceReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public ReceivedChequesBounceReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void chequeBounce() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath","//MenuItem[@Name='Received Cheques Bounce'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
//        super.bulkVerifyReport("PREC 2");
        closeReport("Received Cheques Bounce");
    }
}
