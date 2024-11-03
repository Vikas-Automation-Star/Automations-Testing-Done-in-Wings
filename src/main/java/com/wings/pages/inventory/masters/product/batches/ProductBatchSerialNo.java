package com.wings.pages.inventory.masters.product.batches;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.pages.Masters;
import com.wings.utils.Common;
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
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Product");
        common.clickElement("name", "Batches");
        common.clickElement("name","Products - Batches and Serial No");
        super.actionsMaster("xpath","//TreeItem[@Name='Products - Batches and Serial No']/TreeItem[@Name='All Products - Batches and Serial No']","//MenuItem[@Name='New Master']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='Serial No Product *']", common.getData(filepath, "product") + common.getRandom());
        common.inputText("xpath", "//Edit[@Name='Product Code']", String.valueOf(common.getRandom()));
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
        super.saveMaster();
        super.closeMaster("Products - Batches and Serial No");
        System.out.println("Products - Batches and Serial No created successfully");
    }
}
