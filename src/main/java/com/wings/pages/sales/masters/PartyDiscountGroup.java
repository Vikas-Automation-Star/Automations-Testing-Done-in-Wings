package com.wings.pages.sales.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class PartyDiscountGroup extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PartyDiscountGroup(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void partyGroup() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps(common.getData(dataFile,"menu"), common.getData(dataFile,"secondMenu"), common.getData(dataFile,"subMenu") );
        Thread.sleep(1000);
        createMaster("xpath", "//TreeItem[@Name='Party Discount Groups']/TreeItem[@Name='All Party Discount Groups']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Edit[@Name='New Party Discount Group *']");
        common.inputText("xpath", "//Edit[@Name='New Party Discount Group *']", common.getData(dataFile, "newAccount") + common.getRandom());
        common.inputAndVerify("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        common.clickElement("xpath", "//Text[@Name='Applicable Party Nodes']/following-sibling::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='Applicable Node Row 0, Not sorted.']", common.getData(dataFile, "applicable"));
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.clickElement("name", "Ok");
        saveMaster();
        closeMaster(common.getData(dataFile,"menuItem"));
        refresh();
        //validate
        navigateToMastersWhen3Steps(common.getData(dataFile,"menu"), common.getData(dataFile,"secondMenu"), common.getData(dataFile,"subMenu") );
        validateAndInactivate(common.getData(dataFile,"menuItem"), common.getData(dataFile,"newAccount") );
    }
}