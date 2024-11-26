package com.wings.pages.sales.reports.analysis.executives;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class YearWiseSalesByQuantity extends Report {
    WindowsDriver driver;
    Common common;

    public YearWiseSalesByQuantity(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }
    public void yearWiseSalesByQuantity() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Analysis");
        common.clickElement("xpath", "//MenuItem[@Name='Executive']");
        common.clickElement("xpath", "//MenuItem[@Name='Year Wise Sales By Quantity']");
        Thread.sleep(2000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Year Wise Sales By Quantity");
    }
}
