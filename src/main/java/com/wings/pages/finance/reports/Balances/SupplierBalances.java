package com.wings.pages.finance.reports.Balances;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;

import java.awt.*;

public class SupplierBalances extends Transaction {
    WindowsDriver driver;
    Common common;

    public SupplierBalances(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void supplierBalanceReport() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Balances");
        common.clickElement("name", "Supplier Balances");
        Thread.sleep(1000);
        common.clickElement("xpath", "//CheckBox[@Name='Show  Accounts  With  Zero  Balance']");
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
//        Thread.sleep(1500);
//        super.bulkVerifyReport("ILT 1");
        closeReport("Supplier Balances");
        Allure.step("Verify Supplier balance report");
    }
}
