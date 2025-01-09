package com.wings.pages.purchase.reports;


import com.wings.pages.Report;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;

public class MaterialReturns extends Transaction {
    WindowsDriver driver;
    Common common;

    public MaterialReturns(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void materialReturn() throws InterruptedException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Material Returns'][2]");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Material Returns");
        Allure.step("Validating MaterialReturns Report");
    }

}
