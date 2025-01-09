package com.wings.pages.purchase.reports;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

public class PendingPurchaseOrder extends Transaction {
    WindowsDriver driver;
    Common common;

    public PendingPurchaseOrder(WindowsDriver driver){
        super(driver);
        this.driver=driver;
        common=new Common(this.driver);
    }
    public void pendingOrderReport() throws InterruptedException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("name", "Pending Purchase Orders");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("PO 2");
        super.closeReport("Pending Purchase Orders");
    }
}
//Purchase Orders Cancellation