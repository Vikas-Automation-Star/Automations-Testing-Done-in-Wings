package com.wings.pages.inventory.masters.product.batches;

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

public class ProductBatches extends Masters {
    WindowsDriver driver;
    Common common;
    String filepath;

    public ProductBatches(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(driver);
        filepath = file;
    }

    public void productBatch() throws InterruptedException, AWTException, IOException, ParseException {
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Product");
        common.clickElement("name", "Batches");
        common.clickElement("name","Product Batches");
        super.actionsMaster("xpath","//TreeItem[@Name='Product Batches']/TreeItem[@Name='All Product Batches']","//MenuItem[@Name='New Master']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Product Batch *']", common.getData(filepath, "productBatch") + common.getRandom());
        common.clickElement("xpath", "//Edit[@Name='Assign Product *']/Button[@Name='Open']");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.inputText("xpath","//Edit[@Name='Expiry Date']", common.getData(filepath,"expiryDate"));
        super.saveMaster();
        super.closeMaster("Product Batches");
        System.out.println("Product Batches created successfully");
    }
}
