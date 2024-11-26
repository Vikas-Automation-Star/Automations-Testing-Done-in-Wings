package com.wings.pages.purchase.reports.analysis.party;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class YearWisePurchaseByValue extends Report {
    WindowsDriver driver;
    Common common;

    public YearWisePurchaseByValue(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }
    public void yearWisePurchaseByValue() throws InterruptedException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Analysis");
        common.clickElement("xpath", "//MenuItem[@Name='Party']");
        common.clickElement("xpath", "//MenuItem[@Name='Year Wise Purchase By Value']");
        Thread.sleep(2000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Year Wise Purchase By Value");
    }
}
