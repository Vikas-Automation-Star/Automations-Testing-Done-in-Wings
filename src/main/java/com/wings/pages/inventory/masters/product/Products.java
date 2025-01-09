package com.wings.pages.inventory.masters.product;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Products extends Masters {
    WindowsDriver driver;
    Common common;
    String filepath;

    public Products(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(driver);
        filepath = file;
    }

    public void productMastercreation() throws InterruptedException, AWTException, IOException, ParseException {
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Product");
        common.clickElement("name", "Products");
        super.actionsMaster("xpath", "//TreeItem[@Name='Products']/TreeItem[@Name='All Products']", "//MenuItem[@Name='New Master']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Product *']", common.getData(filepath, "newProduct") + common.getRandom());
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
        //slider
        common.sliderHandling("name", "Position", 0, 100);
        super.saveMaster();
        super.closeMaster("Products");
        System.out.println("product created successfully");
    }
}