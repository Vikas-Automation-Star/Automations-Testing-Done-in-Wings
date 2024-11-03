package com.wings.pages.finance.reports.OpeningBalance;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class OpeningReceiptsfromCreditCardCompaniesReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public OpeningReceiptsfromCreditCardCompaniesReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void openingReceiptsfromCreditCardCompany() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Opening Balances");
        common.clickElement("xpath","//MenuItem[@Name='Opening Receipts from Credit Card Companies'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//CheckBox[@Name='Detailed']");
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        super.bulkVerifyReport("ORFCC 2");
        super.closeReport("Opening Receipts from Credit Card Companies");
    }
}
