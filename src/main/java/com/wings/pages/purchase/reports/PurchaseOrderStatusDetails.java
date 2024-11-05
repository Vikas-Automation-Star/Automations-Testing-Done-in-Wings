package com.wings.pages.purchase.reports;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

//Purchase Order Status Details
public class PurchaseOrderStatusDetails extends Report {
    WindowsDriver driver;
    Common common;

    public PurchaseOrderStatusDetails(WindowsDriver driver){
        super(driver);
        this.driver=driver;
        common=new Common(this.driver);
    }
    public void purchaseOrderStatusReport() throws InterruptedException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("name", "Purchase Order Status Details");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("PO 2");
        super.closeReport("Purchase Order Status Details");
    }
}
