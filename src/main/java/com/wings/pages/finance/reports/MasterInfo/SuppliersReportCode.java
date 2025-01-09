package com.wings.pages.finance.reports.MasterInfo;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

public class SuppliersReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public SuppliersReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void supplierReport() throws InterruptedException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Master Info");
        common.clickElement("xpath","//MenuItem[@Name='Suppliers']");
        Thread.sleep(1200);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
//        super.bulkVerifyReport("ORFCC 2");
        closeReport("Suppliers");
    }
}
