package com.wings.pages.sales.reports.analysis.party;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class MonthWiseSalesByValue extends Report {
    WindowsDriver driver;
    Common common;

    public MonthWiseSalesByValue(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }
    public void monthWiseSalesByValue() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Analysis");
        common.clickElement("xpath", "//MenuItem[@Name='Party']");
        common.clickElement("xpath", "//MenuItem[@Name='Month Wise Sales By Value']");
        Thread.sleep(2000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Month Wise Sales By Value");
    }
}
