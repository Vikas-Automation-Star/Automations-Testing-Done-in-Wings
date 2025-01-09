package com.wings.pages.inventory.reports.stock;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;
import java.awt.event.KeyEvent;

public class StockLedger extends Transaction {
    WindowsDriver driver;
    Common common;

    public StockLedger(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void stckLedger() throws InterruptedException, AWTException {
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Stock");
        common.clickElement("name","Stock Ledger");
        Thread.sleep(1000);
        common.clickElement("xpath","//Edit[@Name='Product']/Button[@Name='Open']");
        Thread.sleep(1000);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
//        Thread.sleep(1500);
        super.bulkVerifyReport("SI 3");
        super.closeReport("Stock Ledger");
    }
}
