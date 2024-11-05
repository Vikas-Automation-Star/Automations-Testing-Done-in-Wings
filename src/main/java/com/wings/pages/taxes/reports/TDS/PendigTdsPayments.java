package com.wings.pages.taxes.reports.TDS;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class PendigTdsPayments extends Report {
    WindowsDriver driver;
    Common common;


    public PendigTdsPayments(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void pendigTdsPayments() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Taxes");
        common.clickElement("name", "TDS");
        common.clickElement("xpath", "//MenuItem[@Name='Pending TDS Payments']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Pending TDS Payments");
    }
}
