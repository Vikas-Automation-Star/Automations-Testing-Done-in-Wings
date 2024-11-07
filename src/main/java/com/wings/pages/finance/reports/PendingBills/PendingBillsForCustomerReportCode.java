package com.wings.pages.finance.reports.PendingBills;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class PendingBillsForCustomerReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public PendingBillsForCustomerReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void pendingBillsForCustomer() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Pending Bills");
        common.clickElement("xpath","//MenuItem[@Name='Pending Bills For Customer']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Edit[@Name='Customers']/Button[@Name='Open']");
        Thread.sleep(1000);
//        super.selectDropDownReport("Customer7");

        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1000);
//        super.bulkVerifyReport("JE 3");
        closeReport("Pending Bills For Customer");
    }

}
