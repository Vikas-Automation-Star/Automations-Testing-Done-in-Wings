package com.wings.pages.inventory.masters.product.batches;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ProductMultiBatch extends Masters {
    WindowsDriver driver;
    Common common;
    String filepath;

    public ProductMultiBatch(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(driver);
        filepath = file;
    }

    public void productMultiBatch() throws InterruptedException, AWTException, IOException, ParseException {
        navigateToMastersWhen4Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"thirdMenu"), common.getData(filepath,"fourthMenu") );
        createMaster("xpath", "//TreeItem[@Name='Products - MultiBatch']/TreeItem[@Name='All Products - MultiBatch']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Product *']", common.getData(filepath, "newAccount") + common.getRandom());
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
        saveMaster();
        closeMaster(common.getData(filepath,"menuItem"));
        refresh();
        //validate
        navigateToMastersWhen4Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"thirdMenu"), common.getData(filepath,"fourthMenu") );
        validateAndInactivate(common.getData(filepath,"menuItem"), common.getData(filepath,"newAccount") );
    }
}