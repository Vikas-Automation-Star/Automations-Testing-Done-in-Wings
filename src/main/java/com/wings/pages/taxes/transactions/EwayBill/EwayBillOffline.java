package com.wings.pages.taxes.transactions.EwayBill;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class EwayBillOffline extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public EwayBillOffline(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void ewayBillOffline() throws InterruptedException, IOException, ParseException, AWTException {

        common.clickElement("name", "Taxes");
        common.clickElement("name", "E-Way Bill");
        common.clickElement("name", "E-Way Bill Offline");
        Thread.sleep(1000);
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMaster(common.getData(dataFile, "branch"));
        common.clickElement("xpath", "//Edit[@Name='Vehicle Type']");
        Thread.sleep(2000);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.clickElement("xpath", "//Edit[@Name='Executive']");
        super.selectMaster(common.getData(dataFile, "executive"));
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        transactionSave();
        Thread.sleep(1000);
        super.closeTransaction("E-Way Bill Offline");
        Thread.sleep(2000);
    }
}
