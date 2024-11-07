package com.wings.pages.finance.reports.PendingBills;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class PendingBillsForCustomersReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public PendingBillsForCustomersReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void pendingBillsForCustomers() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Pending Bills");
        common.clickElement("xpath","//MenuItem[@Name='Pending Bills For Customers']");
        Thread.sleep(1000);
        common.clickElement("xpath","//CheckBox[@Name='Detailed']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1000);
//        super.bulkVerifyReport("JE 3");
        closeReport("Pending Bills For Customers");
    }

}
