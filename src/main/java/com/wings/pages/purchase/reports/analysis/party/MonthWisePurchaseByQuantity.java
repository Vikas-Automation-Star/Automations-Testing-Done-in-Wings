package com.wings.pages.purchase.reports.analysis.party;

import com.wings.pages.Report;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class MonthWisePurchaseByQuantity extends Transaction {
    WindowsDriver driver;
    Common common;

    public MonthWisePurchaseByQuantity(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }
    public void monthWisePurchaseByQuantity() throws InterruptedException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Analysis");
        common.clickElement("xpath", "//MenuItem[@Name='Party']");
        common.clickElement("xpath", "//MenuItem[@Name='Month Wise Purchase By Quantity']");
        Thread.sleep(2000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Month Wise Purchase By Quantity");
    }
}
