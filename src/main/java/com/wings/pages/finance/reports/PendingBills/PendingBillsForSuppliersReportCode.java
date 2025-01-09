package com.wings.pages.finance.reports.PendingBills;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class PendingBillsForSuppliersReportCode extends Transaction {
        WindowsDriver driver;
        Common common;

        public PendingBillsForSuppliersReportCode(WindowsDriver driver) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
        }

        public void pendingBillsForSuppliers() throws InterruptedException, AWTException {
            common.clickElement("name", "Finance");
            common.clickElement("name", "Pending Bills");
            common.clickElement("xpath","//MenuItem[@Name='Pending Bills For Suppliers']");
            Thread.sleep(1000);
            common.clickElement("xpath","//CheckBox[@Name='Detailed']");
            Thread.sleep(1000);
            common.clickElement("xpath","//Pane/Button[@Name='Submit']");
            Thread.sleep(1000);
//        super.bulkVerifyReport("JE 3");
            closeReport("Pending Bills For Suppliers");
        }
}
