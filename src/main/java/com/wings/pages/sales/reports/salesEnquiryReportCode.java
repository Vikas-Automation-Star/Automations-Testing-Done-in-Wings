package com.wings.pages.sales.reports;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

public class salesEnquiryReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public salesEnquiryReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void enquiryReport() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Enquiries");
        common.clickElement("xpath", "//Menu[@Name='Enquiries']/MenuItem[@Name='Sales Enquiries'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1000);
        super.bulkVerifyReport("SE 1");
        super.closeReport("Sales Enquiries");
    }
}
