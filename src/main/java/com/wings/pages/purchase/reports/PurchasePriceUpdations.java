package com.wings.pages.purchase.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;

import java.awt.*;

public class PurchasePriceUpdations extends Transaction {
    WindowsDriver driver;
    Common common;

    public PurchasePriceUpdations(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void PurchasePriceUpdation() throws InterruptedException, AWTException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Price");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Price Updation']");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("Purchase Price Updation");
        Allure.step("Validating PurchasePriceUpdations Report");
    }
}
