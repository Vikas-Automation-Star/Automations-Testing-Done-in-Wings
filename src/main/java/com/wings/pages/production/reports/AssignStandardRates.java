package com.wings.pages.production.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class AssignStandardRates extends Transaction {
        WindowsDriver driver;
        Common common;

       public AssignStandardRates(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
       }

    public void assignStandardRatesReport() throws InterruptedException {
        common.clickElement("name", "Production");
        common.clickElement("xpath", "//MenuItem[@Name='Assign Standard Rates'][2]");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Assign Standard Rates");
    }
}