package com.wings.pages.purchase.reports;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class MaterialReceiptsAgainstOrders extends Report {
    WindowsDriver driver;
    Common common;

    public MaterialReceiptsAgainstOrders(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void materialReceiptsAgainstOrder() throws InterruptedException {

        common.clickElement("name", "Purchase");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Material Receipts against Orders'][2]");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Material Receipts against Orders");
    }
}
