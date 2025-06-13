package com.wings.pages.inventory.masters.product.batches;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
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
        navigateToMastersWhen4Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"thirdMenu"), common.getData(filepath,"fourthMenu") );
        createMaster("xpath", "//TreeItem[@Name='Product Batches']/TreeItem[@Name='All Product Batches']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Product Batch *']", common.getData(filepath, "newAccount") + common.getRandom());
        common.clickElement("xpath", "//Edit[@Name='Assign Product *']/Button[@Name='Open']");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.inputText("xpath","//Edit[@Name='Description']", common.getData(filepath,"description"));
        common.inputText("xpath", "//Edit[@Name='Expiry Date']", common.getData(filepath, "expiryDate"));
        common.inputText("xpath","//Edit[@Name='Batch Text']", common.getData(filepath,"batchText"));
        saveMaster();
        closeMaster(common.getData(filepath,"menuItem"));
        refresh();
        //validate
//        navigateToMastersWhen4Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"thirdMenu"), common.getData(filepath,"fourthMenu") );
//        validateAndInactivate(common.getData(filepath,"menuItem"), common.getData(filepath,"newAccount") );
    }
}