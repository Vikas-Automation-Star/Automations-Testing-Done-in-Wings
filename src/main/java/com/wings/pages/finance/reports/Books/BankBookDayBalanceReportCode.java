package com.wings.pages.finance.reports.Books;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

public class BankBookDayBalanceReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public BankBookDayBalanceReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void bankBookDayBalance() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Books");
        common.clickElement("xpath", "//MenuItem[@Name='Bank Book [Day Balance]']");
        Thread.sleep(1200);
        common.clickElement("xpath", "//Button[@Name='Open']");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_ENTER);
        //change it to method

        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
//        super.bulkVerifyReport("ORFCC 2");
        closeReport("Bank Book [Day Balance]");
    }
}
