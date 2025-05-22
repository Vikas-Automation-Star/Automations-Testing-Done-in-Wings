package com.wings.pages.purchase.reports.analysis.product;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class YearWisePurchaseByValue extends Transaction {
    WindowsDriver driver;
    Common common;

    public YearWisePurchaseByValue(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void yearWisePurchaseByValue() throws InterruptedException {
        navigateToMastersWhen4Steps("Purchase","Analysis","Product","Year Wise Purchase By Value");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Year Wise Purchase By Value");
    }
}
