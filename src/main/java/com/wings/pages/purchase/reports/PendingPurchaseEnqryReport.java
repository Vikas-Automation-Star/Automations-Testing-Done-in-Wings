package com.wings.pages.purchase.reports;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

public class PendingPurchaseEnqryReport extends Transaction {
    WindowsDriver driver;
    Common common;

    public PendingPurchaseEnqryReport(WindowsDriver driver){
        super(driver);
        this.driver=driver;
        common=new Common(this.driver);
    }
    public void pendingEnquiryReport() throws InterruptedException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Enquiries");
        common.clickElement("name", "Pending Purchase Enquiries");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
//        super.bulkVerifyReport("SE 17");
        super.closeReport("Pending Purchase Enquiries");
    }
}
