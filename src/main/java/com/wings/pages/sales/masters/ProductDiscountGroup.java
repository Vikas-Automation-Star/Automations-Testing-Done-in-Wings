package com.wings.pages.sales.masters;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ProductDiscountGroup extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public ProductDiscountGroup(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void productGroup() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToProductDiscountGroup();
        Thread.sleep(1000);
        createMaster("xpath", "//TreeItem[@Name='Product Discount Groups']/TreeItem[@Name='All Product Discount Groups']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Edit[@Name='New Product Discount Group *']");
        common.inputText("xpath", "//Edit[@Name='New Product Discount Group *']", common.getData(dataFile, "newAccount") + common.getRandom());
        common.inputAndVerify("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        common.clickElement("xpath", "//Text[@Name='Applicable Product Nodes']/following-sibling::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='Master Type Row 0, Not sorted.']", common.getData(dataFile, "masterType"));
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.clickElement("name", "Ok");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Text[@Name='Applicable Products']/following-sibling::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='Applicable Products Row 0, Not sorted.']", common.getData(dataFile, "applicable"));
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.clickElement("name", "Ok");
        //save
        saveMaster();
        closeTransaction(common.getData(dataFile,"menuItem"));
        refresh();
        //validate
        navigateToProductDiscountGroup();
        validateAndInactivate(common.getData(dataFile,"menuItem"), common.getData(dataFile,"newAccount") );
    }
}