package com.wings.pages.production.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

public class MaterialIssuesAndReceiptsFromProduction extends Transaction {
    WindowsDriver driver;
    Common common;

    public MaterialIssuesAndReceiptsFromProduction(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void materialIssuesAndReceiptsFromProduction() throws InterruptedException {
        common.clickElement("name", "Production");
        common.clickElement("name", "Simple");
        common.clickElement("xpath", "//MenuItem[@Name='Material Issues and Receipts from Production'][2]");
        Thread.sleep(1500);
//        common.clickElement("xpath", "//CheckBox[@Name='Detailed']");
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Material Issues and Receipts from Production");
        Thread.sleep(2000);
    }
}
