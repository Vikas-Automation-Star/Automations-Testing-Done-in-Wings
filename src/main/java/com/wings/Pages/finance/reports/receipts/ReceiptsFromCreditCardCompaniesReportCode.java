package com.wings.pages.finance.reports.receipts;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class ReceiptsFromCreditCardCompaniesReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public ReceiptsFromCreditCardCompaniesReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void creditCardCompany() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath","//MenuItem[@Name='Receipts from Credit Card Companies'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//CheckBox[@Name='Detailed']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
//        super.bulkVerifyReport("CR 1");
        super.closeReport("Receipts from Credit Card Companies");
    }
}
