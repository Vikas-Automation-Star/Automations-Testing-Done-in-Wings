package com.wings.pages.sales.reports.analysis.product;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class YearWiseSalesByValue extends Report {
    WindowsDriver driver;
    Common common;

    public YearWiseSalesByValue(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }
    public void yearWiseSalesByValue() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Analysis");
        common.clickElement("xpath", "//MenuItem[@Name='Product']");
        common.clickElement("xpath", "//MenuItem[@Name='Year Wise Sales By Value']");
        Thread.sleep(2000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Year Wise Sales By Value");
    }
}
