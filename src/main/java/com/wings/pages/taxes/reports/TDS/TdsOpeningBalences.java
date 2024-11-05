package com.wings.pages.taxes.reports.TDS;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class TdsOpeningBalences extends Report {
    WindowsDriver driver;
    Common common;


    public TdsOpeningBalences(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void tdsOpeningBalences() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Taxes");
        common.clickElement("name", "TDS");
        common.clickElement("xpath", "//MenuItem[@Name='TDS Opening Balances'][2]");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("TOP 2");
        super.closeReport("TDS Opening Balances");
    }
}
