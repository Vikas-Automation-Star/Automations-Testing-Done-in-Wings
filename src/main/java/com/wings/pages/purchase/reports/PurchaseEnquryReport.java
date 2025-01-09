package com.wings.pages.purchase.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class PurchaseEnquryReport extends Transaction {
    WindowsDriver driver;
    Common common;

    public PurchaseEnquryReport(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void enquiryReport() throws InterruptedException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Enquiries");
        common.clickElement("xpath", "//Menu[@Name='Enquiries']/MenuItem[@Name='Purchase Enquiries'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("PE 1");
        super.closeReport("Purchase Enquiries");
    }
}
