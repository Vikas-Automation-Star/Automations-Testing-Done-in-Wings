package com.wings.pages.purchase.reports.analysis.party;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class YearWisePurchaseByQuantity extends Transaction {
    WindowsDriver driver;
    Common common;

    public YearWisePurchaseByQuantity(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void yearWisePurchaseByQuantity() throws InterruptedException {
        navigateToMastersWhen4Steps("Purchase","Analysis","Party","Year Wise Purchase By Quantity");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Year Wise Purchase By Quantity");
    }
}
