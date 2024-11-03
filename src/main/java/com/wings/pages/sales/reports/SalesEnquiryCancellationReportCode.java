package com.wings.pages.sales.reports;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

public class SalesEnquiryCancellationReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public SalesEnquiryCancellationReportCode(WindowsDriver driver){
        super(driver);
        this.driver=driver;
        common=new Common(this.driver);
    }
    public void enquiryCancelReport() throws InterruptedException {
        common.clickElement("name","Sales");
        common.clickElement("name","Enquiries");
        common.clickElement("xpath","//MenuItem[@Name='Sales Enquiry Cancellations']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1000);
        super.bulkVerifyReport("SEC 7");
        super.closeReport("Sales Enquiry Cancellations");
    }
}
