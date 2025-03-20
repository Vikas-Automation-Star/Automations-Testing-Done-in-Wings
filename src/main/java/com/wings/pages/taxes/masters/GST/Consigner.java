package com.wings.pages.taxes.masters.GST;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Consigner extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Consigner(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void consigner() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps("Taxes","GST","Consignor");
        WebElement element = common.findWebElement("xpath", "//TreeItem[@Name='Consignor']/TreeItem[@Name='All Consignor']");
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Consignor *']", common.getData(dataFile, "newConsigner") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Consignor *']").getText();
        common.inputText("xpath", "//Edit[@Name='New Consignor Code']", common.getData(dataFile, "newConsignerCode") + common.getRandom());
        common.clickElement("xpath", "//Pane[@Name='Registration']/Button[@Name='...']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Edit[@Name='Party Reg Type *']/Button[@Name='Open']");
        Thread.sleep(2000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.inputText("xpath", "//Edit[@Name='GSTIN']", common.getData(dataFile, "GstNo"));
        common.clickElement("xpath", "//Edit[@Name='PAN']");
        WebElement element1 = common.findWebElement("xpath", "//Button[@Name='Verify GSTIN']");
        element1.click();
        element1.sendKeys(Keys.ESCAPE);
        Thread.sleep(200);
        common.clickElement("xpath", "//Button[@Name='Ok']");
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        common.clickElement("xpath", "//Button[@Name='Close']");
        Thread.sleep(1000);
        validateMastersAndInactive("Consignor","All Consignor",master,"Consignor");
        Thread.sleep(2000);
    }
}

