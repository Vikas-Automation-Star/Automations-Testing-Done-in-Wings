package com.wings.pages.taxes.reports.TCS;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class TcsPurchaseRepo extends Transaction {
    WindowsDriver driver;
    Common common;

    public TcsPurchaseRepo(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void tcsPurchaseRepo() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Taxes");
        common.clickElement("name", "TCS");
        common.clickElement("xpath", "//MenuItem[@Name='TCS Purchases Report']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("TCS Purchases Report");
    }
}
