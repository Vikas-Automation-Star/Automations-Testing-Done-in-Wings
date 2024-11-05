package com.wings.pages.taxes.reports.TDS;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class TdsDeductedByOthers extends Report {
    WindowsDriver driver;
    Common common;


    public TdsDeductedByOthers(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void tdsDeductedByOthers() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Taxes");
        common.clickElement("name", "TDS");
        common.clickElement("xpath", "//MenuItem[@Name='TDS Deducted By Others']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("TDS Deducted By Others");
    }
}
