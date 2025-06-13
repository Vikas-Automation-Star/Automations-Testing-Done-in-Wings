package com.wings.pages.inventory.masters.product.batches;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ProductBatchSerialNo extends Masters {
    WindowsDriver driver;
    Common common;
    String filepath;

    public ProductBatchSerialNo(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(driver);
        filepath = file;
    }

    public void productSerialNo() throws InterruptedException, AWTException, IOException, ParseException {
        navigateToMastersWhen4Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"thirdMenu"), common.getData(filepath,"fourthMenu") );
        createMaster("xpath", "//TreeItem[@Name='Products - Batches and Serial No']/TreeItem[@Name='All Products - Batches and Serial No']");
        Thread.sleep(3000);
        common.inputText("xpath", "//Edit[@Name='Serial No Product *']", common.getData(filepath, "newAccount") + common.getRandom());
        common.inputText("xpath", "//Edit[@Name='Product Code']", String.valueOf(common.getRandom()));
        common.clickElement("xpath", "//Edit[@Name='SKU *']/Button[@Name='Open']");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.inputText("xpath","//Edit[@Name='Description']", common.getData(filepath,"description"));
        common.clickElement("xpath", "//Edit[@Name='HSN Code']/Button[@Name='Open']");
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane[@Name='Attributes']/Button[@Name='...']");
        common.clickElement("xpath","//Edit[@Name='Category']");
        common.clickElement("xpath","//Edit[@Name='Sub Category']");
        common.clickElement("xpath","//Edit[@Name='Product Type']");
        common.clickElement("xpath","//Edit[@Name='Brand']");
        common.clickElement("xpath","//Edit[@Name='Class']");
        common.clickElement("xpath","//Edit[@Name='Sub Class']");
        common.clickElement("xpath","//Edit[@Name='Season']");
        common.clickElement("xpath","//Edit[@Name='Style']");
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.clickElement("xpath","//Pane[@Name='Additional Info']/Button[@Name='...']");
        common.inputText("xpath","//Edit[@Name='Info 1']", common.getData(filepath,"info1"));
        common.inputText("xpath","//Edit[@Name='Info 2']", common.getData(filepath,"info2"));
        common.inputText("xpath","//Edit[@Name='Info 3']", common.getData(filepath,"info3"));
        common.inputText("xpath","//Edit[@Name='Info 4']", common.getData(filepath,"info4"));
        common.inputText("xpath","//Edit[@Name='Info 5']", common.getData(filepath,"info5"));
        common.inputText("xpath","//Edit[@Name='Info 6']", common.getData(filepath,"info6"));
        common.inputText("xpath","//Edit[@Name='Info 7']", common.getData(filepath,"info7"));
        common.inputText("xpath","//Edit[@Name='Info 8']", common.getData(filepath,"info8"));
        common.inputText("xpath","//Edit[@Name='Info 9']", common.getData(filepath,"info9"));
        common.inputText("xpath","//Edit[@Name='Info 10']", common.getData(filepath,"info10"));
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.clickElement("xpath","//Pane[@Name='Additional Values']/Button[@Name='...']");
        common.inputText("xpath","//Edit[@Name='Value 1']", common.getData(filepath,"value1"));
        common.inputText("xpath","//Edit[@Name='Value 2']", common.getData(filepath,"value2"));
        common.inputText("xpath","//Edit[@Name='Value 3']", common.getData(filepath,"value3"));
        common.inputText("xpath","//Edit[@Name='Value 4']", common.getData(filepath,"value4"));
        common.inputText("xpath","//Edit[@Name='Value 5']", common.getData(filepath,"value5"));
        common.inputText("xpath","//Edit[@Name='Value 6']", common.getData(filepath,"value6"));
        common.inputText("xpath","//Edit[@Name='Value 7']", common.getData(filepath,"value7"));
        common.inputText("xpath","//Edit[@Name='Value 8']", common.getData(filepath,"value8"));
        common.inputText("xpath","//Edit[@Name='Value 9']", common.getData(filepath,"value9"));
        common.inputText("xpath","//Edit[@Name='Value 10']",common.getData(filepath,"value10"));
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.sliderHandling("xpath", "//Pane[@Name='Transaction']//ScrollBar[@Name='Vertical']//Thumb[@Name='Position']", 0, 300);
        common.clickElement("xpath", "//Edit[@Name='Sales Account']/Button[@Name='Open']");
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
//        saveMaster();
//        closeMaster(common.getData(filepath,"menuItem"));
//        //validate
//        navigateToMastersWhen4Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"thirdMenu"), common.getData(filepath,"fourthMenu") );
//        validateAndInactivate(common.getData(filepath,"menuItem"), common.getData(filepath,"newAccount") );
    }
}