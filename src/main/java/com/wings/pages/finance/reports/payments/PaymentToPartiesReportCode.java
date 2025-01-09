package com.wings.pages.finance.reports.payments;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class PaymentToPartiesReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public PaymentToPartiesReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void paymentToParty() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Payments");
        common.clickElement("xpath","//MenuItem[@Name='Payments to Parties'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath","//CheckBox[@Name='Detailed']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        bulkVerifyReport("PPAY 2");
        closeReport("Payments to Parties");
    }
}
