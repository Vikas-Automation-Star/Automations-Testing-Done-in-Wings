package com.wings.pages.sales.reports;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

public class PendingSalesOrderReportCode extends Report {
        WindowsDriver driver;
        Common common;

        public PendingSalesOrderReportCode(WindowsDriver driver) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
        }

        public void PendingsalesOrderReport() throws InterruptedException {

            common.clickElement("name", "Sales");
            common.clickElement("name", "Orders");
            common.clickElement("xpath", "//MenuItem[@Name='Pending Sales Orders']");
            Thread.sleep(1500);
            common.clickElement("xpath","//Pane/Button[@Name='Submit']");
            Thread.sleep(1000);
            super.bulkVerifyReport("SO 8");
            super.closeReport("Pending Sales Orders");
        }
    }
