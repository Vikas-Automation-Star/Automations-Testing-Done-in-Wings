package com.wings.pages.finance.reports.PendingBills;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

import java.awt.*;

public class PendingBillsForSupplierReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public PendingBillsForSupplierReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void pendingBillsForSupplier() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Pending Bills");
        common.clickElement("xpath","//MenuItem[@Name='Pending Bills For Supplier']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Edit[@Name='Suppliers']/Button[@Name='Open']");
        Thread.sleep(1000);
//        super.selectDropDownReport("Customer7");

        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1000);
//        super.bulkVerifyReport("JE 3");
        super.closeReport("Pending Bills For Supplier");
    }
}
