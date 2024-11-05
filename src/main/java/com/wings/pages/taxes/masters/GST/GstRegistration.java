package com.wings.pages.taxes.masters.GST;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GstRegistration extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public GstRegistration(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void gstRegistration() throws InterruptedException, IOException, ParseException, AWTException {

        common.clickElement("name", "Taxes");
        common.clickElement("name", "GST");
        common.clickElement("name", "GST Registration");
        WebElement element = common.findWebElement("xpath", "//TreeItem[@Name='GST Registration']/TreeItem[@Name='All GST Registration']");
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New GST Registration *']", common.getData(dataFile, "newGst") + common.getRandom());
        Thread.sleep(2000);
        common.clickElement("xpath", "//Edit[@Name='Registration Type *']/Button[@Name='Open']");
        Thread.sleep(300);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.inputText("xpath", "//Edit[@Name='GSTIN']", common.getData(dataFile, "GstNo"));
        common.clickElement("xpath","//Edit[@Name='PAN']");
        common.inputText("xpath", "//Edit[@Name='Legal Name *']", common.getData(dataFile, "legalName"));
        common.inputText("xpath", "//Edit[@Name='GST User Name *']", common.getData(dataFile, "GstUserName"));

        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        common.clickElement("xpath", "//Button[@Name='Close']");
        Thread.sleep(1000);
        super.closeMaster("GST Registration");
        Thread.sleep(2000);

    }
}
