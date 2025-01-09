package com.wings.pages.inventory.reports.masterInfo;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;

public class MasterDetails extends Transaction {
    WindowsDriver driver;
    Common common;

    public MasterDetails(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void masterDetails() throws InterruptedException, AWTException {
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Master Info");
        common.clickElement("name", "Master Details");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
//       here, you need to select the master. while doing actual transaction, write a line of code for that
//        super.bulkVerifyReport("SI 3");
        super.closeReport("Master Details");
    }
}
