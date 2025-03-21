package com.wings.pages.production.masters;


import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ProductionBatchPolicie extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public ProductionBatchPolicie(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void productionBatchPolicie() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps("Production","Bill of Material","Production Batch Policies");
        Thread.sleep(1500);
        super.createMaster("xpath", "//TreeItem[@Name='Production Batch Policies']/TreeItem[@Name='All Production Batch Policies']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Production Batch Policy *']", common.getData(dataFile, "newProductionBatchPolicy") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Production Batch Policy *']").getText();
        common.clickElement("xpath", "//Edit[@Name='Batch Policy Type *']/Button[@Name='Open']");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.clickElement("xpath", "//Text[@Name='Production Batch Policy']/following-sibling::Button[@Name='...']");
        Thread.sleep(2000);
        WebElement element1 = common.findWebElement("xpath", "//Edit[@Name='Include Option * Row 0, Not sorted.']");
        element1.click();
        element1.sendKeys(common.getData(dataFile, "branch"));
        WebElement ele = common.findWebElement("xpath", "//Edit[@Name='Policy Sequence * Row 0, Not sorted.']");
        ele.click();
        ele.sendKeys(common.getData(dataFile, "sequence"));
        WebElement element2 = common.findWebElement("xpath", "//Edit[@Name='Policy Option Format * Row 0, Not sorted.']");
        element2.click();
        element2.sendKeys(common.getData(dataFile, "pof"));
        common.clickElement("xpath", "//Button[@Name='Ok']");
        super.saveAfterMasterCreate();
        validateMastersAndInactive("Production Batch Policies",master);
        Thread.sleep(1000);
    }
}
