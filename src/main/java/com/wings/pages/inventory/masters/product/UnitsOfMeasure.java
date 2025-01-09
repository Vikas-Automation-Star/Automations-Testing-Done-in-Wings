package com.wings.pages.inventory.masters.product;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.wings.pages.Masters;
import com.wings.pages.Transaction;
import com.wings.utils.Common;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class UnitsOfMeasure extends Masters {
    WindowsDriver driver;
    Common common;
    String filepath;

    public UnitsOfMeasure(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(driver);
        filepath = file;
    }

    public void unitsOfMeasure() throws InterruptedException, AWTException, IOException, ParseException {
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Product");
        common.clickElement("name", "Units of Measure");
        super.actionsMaster("xpath", "//TreeItem[@Name='Units of Measure']/TreeItem[@Name='All Units of Measure']", "//MenuItem[@Name='New Master']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Units Of Measure *']", common.getData(filepath, "uom") + common.getRandom());
        Thread.sleep(2500);
        common.inputText("xpath", "//Edit[@Name='UQC *']", common.getData(filepath, "uom"));
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.inputText("xpath", "//Edit[@Name='Conversion Factor']", common.getData(filepath, "conversionFactor"));

        super.saveMaster();
        System.out.println("Units of Measure created successfully");
    }
}
