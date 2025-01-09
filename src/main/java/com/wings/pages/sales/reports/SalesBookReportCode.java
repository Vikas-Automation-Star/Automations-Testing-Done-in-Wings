package com.wings.pages.sales.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class SalesBookReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public SalesBookReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void salesBookReport(String transaction, String dataFile) throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("name", "Sales Book");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        super.bulkVerifyReport(transaction, dataFile);
        super.closeReport("Sales Book");
    }
}