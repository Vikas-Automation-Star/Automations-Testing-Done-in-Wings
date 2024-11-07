package com.wings.pages.finance.reports.Books;

import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CashBookDayBalanceReportCode extends Report {
    WindowsDriver driver;
    Common common;

    public CashBookDayBalanceReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void cashBookDayBalance() throws InterruptedException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Books");
        common.clickElement("xpath","//MenuItem[@Name='Cash Book [Day Balance]']");
        Thread.sleep(1200);
        common.clickElement("xpath","//Button[@Name='Open']");
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_ENTER);
        //change it to method

        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
//        super.bulkVerifyReport("ORFCC 2");
        closeReport("Cash Book [Day Balance]");
    }
}
