package com.wings.pages.purchases.reports;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

public class PurchaseOrdrCancellationReport extends Report {
    WindowsDriver driver;
    Common common;

    public PurchaseOrdrCancellationReport(WindowsDriver driver){
        super(driver);
        this.driver=driver;
        common=new Common(this.driver);
    }
    public void orderCancelReport() throws InterruptedException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Orders Cancellation'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("PO 2");
        super.closeReport("Pending Purchase Orders");
    }
}
