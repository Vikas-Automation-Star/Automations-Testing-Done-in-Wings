package com.wings.pages.audit.reports;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class VoucherHistoryReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public VoucherHistoryReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void voucherHistory() throws InterruptedException, AWTException {
        common.clickElement("name", "Audit");
        common.clickElement("name", "Voucher History");

        //search
        common.inputText("xpath","//Edit[@Name='Voucher Series']","SI");
        common.inputText("xpath","//Edit[@Name='Voucher Number']","11");
        common.clickElement("xpath","//Button[@Name='Search']");
        Thread.sleep(1200);

        //tabclose
        super.closeReport("Voucher History");
    }
}
