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

    public void storageBinCreation() throws InterruptedException, AWTException, IOException, ParseException {
        navigateToMastersWhen3Steps(common.getData(dataFile,"menu"), common.getData(dataFile,"secondMenu"), common.getData(dataFile,"subMenu") );
        createMaster("xpath", "//TreeItem[@Name='Storage Bins']/TreeItem[@Name='All Storage Bins']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Storage Bin *']", common.getData(dataFile, "newAccount") + common.getRandom());
        common.clickElement("xpath", "//Edit[@Name='Location *']/Button[@Name='Open']");
        selectDropDownMaster("Andhra Default Location");
        common.clickElement("xpath", "//Edit[@Name='Storage Bin Type *']/Button[@Name='Open']");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        saveMaster();
        closeMaster(common.getData(dataFile,"menuItem"));
        refresh();
        //validate
        navigateToMastersWhen3Steps(common.getData(dataFile,"menu"), common.getData(dataFile,"secondMenu"), common.getData(dataFile,"subMenu") );
        validateAndInactivate(common.getData(dataFile,"menuItem"), common.getData(dataFile,"newAccount") );
    }
}