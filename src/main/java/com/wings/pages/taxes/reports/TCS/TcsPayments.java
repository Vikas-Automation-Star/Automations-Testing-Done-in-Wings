package com.wings.pages.taxes.reports.TCS;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class TcsPayments extends Transaction {
    WindowsDriver driver;
    Common common;

    public TcsPayments(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void tcsPayments() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Taxes");
        common.clickElement("name", "TCS");
        common.clickElement("xpath", "//MenuItem[@Name='TCS Payments'][2]");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("TCS Payments");
    }
}
