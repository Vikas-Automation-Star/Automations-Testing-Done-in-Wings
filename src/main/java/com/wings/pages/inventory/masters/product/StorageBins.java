package com.wings.pages.inventory.masters.product;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class StorageBins extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public StorageBins(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(driver);
        dataFile = file;
    }

    public void storageBincreation() throws InterruptedException, AWTException, IOException, ParseException {
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Product");
        common.clickElement("name", "Storage Bins");
        super.actionsMaster("xpath", "//TreeItem[@Name='Storage Bins']/TreeItem[@Name='All Storage Bins']", "//MenuItem[@Name='New Master']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Storage Bin *']", common.getData(dataFile, "StorageBin") + common.getRandom());
        common.clickElement("xpath", "//Edit[@Name='Location *']/Button[@Name='Open']");
        super.selectDropDownMaster("Andhra Default Location");
        common.clickElement("xpath", "//Edit[@Name='Storage Bin Type *']/Button[@Name='Open']");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        super.saveMaster();
        super.closeMaster("Storage Bins");
        System.out.println("Storage Bins created successfully");
    }
}
