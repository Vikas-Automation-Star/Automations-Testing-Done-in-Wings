package com.wings.pages.sales.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class PartyAndProductWiseDiscountReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public PartyAndProductWiseDiscountReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void partyDiscountReport() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("xpath", "//MenuItem[@Name='Prices and Discounts'][2]");
        common.clickElement("xpath", "//MenuItem[@Name='Party and Product wise Discounts'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1000);
        super.bulkVerifyReport("PWD 1");
        super.closeReport("Party and Product wise Discounts");
    }
}
