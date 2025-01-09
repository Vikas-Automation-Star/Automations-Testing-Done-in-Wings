package com.wings.pages.finance.reports.partyAdjustments;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class CreditNoteFromSuppliersReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public CreditNoteFromSuppliersReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void creditNotefromSupplierReport() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Party Adjustments");
        common.clickElement("xpath", "//MenuItem[@Name='Credit Note from Suppliers'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//CheckBox[@Name='Detailed']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        bulkVerifyReport("CNFS 1");
        closeReport("Credit Note from Suppliers");
    }
}
