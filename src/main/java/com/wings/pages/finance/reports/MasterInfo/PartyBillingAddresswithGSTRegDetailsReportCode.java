package com.wings.pages.finance.reports.MasterInfo;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class PartyBillingAddresswithGSTRegDetailsReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public PartyBillingAddresswithGSTRegDetailsReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void billingAdresswithGSTdetails() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Master Info");
        common.clickElement("xpath","//MenuItem[@Name='Party Billing Address with GST Reg Details']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
//        super.bulkVerifyReport("ORFCC 2");
        closeReport("Party Billing Address with GST Reg Details");
    }
}
