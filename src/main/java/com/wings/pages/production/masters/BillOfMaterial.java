package com.wings.pages.production.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class BillOfMaterial extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public BillOfMaterial(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void billOfMaterial() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps("Production","Bill of Material","Bill Of Material");
        Thread.sleep(1500);
        super.createMaster("xpath", "//TreeItem[@Name='Bill Of Material']/TreeItem[@Name='All Bill Of Material']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Bill Of Material *']", common.getData(dataFile, "newBillMaterial") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Bill Of Material *']").getText();
        common.clickElement("xpath", "//Edit[@Name='Output Product *']/Button[@Name='Open']");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        super.inputTextWithValidation("xpath", "//Edit[@Name='Quantity']", common.getData(dataFile, "quantity"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        common.clickElement("xpath", "//Text[@Name='Applicable Inputs']/following-sibling::Button[@Name='...']");
        Thread.sleep(2000);
        WebElement element1 = common.findWebElement("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        element1.click();
        element1.sendKeys(common.getData(dataFile, "pCode"));
        WebElement element2 = common.findWebElement("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']");
        element2.click();
        element2.sendKeys(common.getData(dataFile, "quantity"));
        common.clickElement("xpath", "//Button[@Name='Ok']");
        super.saveAfterMasterCreate();
        validateMastersAndInactive("Bill Of Material",master);

    }
}
