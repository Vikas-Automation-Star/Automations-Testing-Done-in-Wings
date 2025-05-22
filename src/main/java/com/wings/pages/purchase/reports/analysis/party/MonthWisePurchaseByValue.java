package com.wings.pages.purchase.reports.analysis.party;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class MonthWisePurchaseByValue extends Transaction {
    WindowsDriver driver;
    Common common;

    public MonthWisePurchaseByValue(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void monthWisePurchaseByValue() throws InterruptedException {
        navigateToMastersWhen4Steps("Purchase","Analysis","Party","Month Wise Purchase By Value");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Month Wise Purchase By Value");
    }
}
