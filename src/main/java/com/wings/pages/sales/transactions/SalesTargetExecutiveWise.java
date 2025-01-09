package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SalesTargetExecutiveWise extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesTargetExecutiveWise(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void salesTarget() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToSalesTargetExecutiveWiseMenu();
        Thread.sleep(1000);
        lastTransactionName();
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        //month
//        common.clickElement("xpath","//Edit[@Name='Month *']/Button[@Name='Open']");
//        Thread.sleep(7000);
//        super.selectDropDown(common.getData(dataFile,"month"));
        common.inputText("xpath", "//Edit[@Name='Month *']", common.getData(dataFile, "month"));
        Thread.sleep(1000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        //year
//        common.clickElement("xpath","//Edit[@Name='Year *']/Button[@Name='Open']");
//        Thread.sleep(7000);
//        super.selectDropDown(common.getData(dataFile,"year"));
        common.inputText("xpath", "//Edit[@Name='Year *']", common.getData(dataFile, "year"));
        Thread.sleep(1000);
        Robot robo = new Robot();
        robo.keyPress(KeyEvent.VK_DOWN);
        robo.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robo.keyPress(KeyEvent.VK_ENTER);
        robo.keyRelease(KeyEvent.VK_ENTER);
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        //f3-items
        enterDataAndValidate("xpath", "//Edit[@Name='Sales Executive * Row 0, Not sorted.']", dataFile, "salesExec");
        enterDataAndValidate("xpath", "//Edit[@Name='Product Sales Target Group * Row 0, Not sorted.']", dataFile, "TargetGroup");
        enterData("xpath", "//Edit[@Name='Target Quantity Row 0, Not sorted.']", dataFile, "TargetQuantity");
        enterData("xpath", "//Edit[@Name='Amount Row 0, Not sorted.']", dataFile, "Amount");
        //save
        transactionSave();
        lastTransactionName();
    }
}