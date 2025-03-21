package com.wings.pages.inventory.masters.product;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.pages.Masters;
import com.wings.utils.Common;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class NonInventoryItems extends Masters {
    WindowsDriver driver;
    Common common;
    String filepath;

    public NonInventoryItems(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(driver);
        filepath = file;
    }

    public void nonInventoryItemcreation() throws InterruptedException, AWTException, IOException, ParseException {
        navigateToMastersWhen3Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"subMenu") );
        createMaster("xpath", "//TreeItem[@Name='Non Inventory Items']/TreeItem[@Name='All Non Inventory Items']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Non Inventory Item *']", common.getData(filepath, "newAccount") + common.getRandom());
        common.inputText("xpath", "//Edit[@Name='Non Inventory Item Code']", String.valueOf(common.getRandom()));
        common.clickElement("xpath", "//Edit[@Name='SKU *']/Button[@Name='Open']");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.clickElement("xpath", "//Edit[@Name='HSN Code']/Button[@Name='Open']");
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Sales Account']/Button[@Name='Open']");
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        saveMaster();
        closeMaster(common.getData(filepath,"menuItem"));
        refresh();
        //validate
        navigateToMastersWhen3Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"subMenu") );
        validateAndInactivate(common.getData(filepath,"menuItem"), common.getData(filepath,"newAccount") );
    }
}