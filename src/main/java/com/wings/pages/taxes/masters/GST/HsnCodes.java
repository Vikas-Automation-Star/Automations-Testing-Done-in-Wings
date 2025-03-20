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

public class HsnCodes extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public HsnCodes(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void hsnCodes() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps("Taxes","GST","HSN Codes");
        WebElement element = common.findWebElement("xpath", "//TreeItem[@Name='HSN Codes']/TreeItem[@Name='All HSN Codes']");
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New HSNCode *']", common.getData(dataFile, "hsnName") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New HSNCode *']").getText();
        common.clickElement("xpath", "//Button[@Name='Open']/ancestor::Edit[@Name='Goods/Service *']");
        Thread.sleep(4000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        WebElement element1 = common.findWebElement("xpath", "//Edit[@Name='Basis For Taxable Value *']");
        element1.clear();
        element1.sendKeys(common.getData(dataFile, "basisTaxValue"), Keys.ENTER);
        common.inputText("xpath", "//Edit[@Name='HSN Code *']", common.getData(dataFile, "hsnCode"));

        common.clickElement("xpath", "//Text[@Name='GST']/following-sibling::Button[@Name='...']");
        WebElement gst = common.findWebElement("xpath", "//Edit[@Name='GST Product Category Row 0, Not sorted.']");
        gst.click();
        gst.sendKeys(common.getData(dataFile, "gstPercentage"), Keys.ENTER);
        common.clickElement("xpath", "//Button[@Name='Ok']");

        common.clickElement("xpath", "//Text[@Name='GST CESS']/following-sibling::Button[@Name='...']");
        WebElement cess = common.findWebElement("xpath", "//Edit[@Name='CESS Product Category Row 0, Not sorted.']");
        cess.click();
        Thread.sleep(2000);
        cess.sendKeys(common.getData(dataFile, "cessPercentage"), Keys.ENTER);
        common.clickElement("xpath", "//Button[@Name='Ok']");

        common.clickElement("xpath", "//Text[@Name='Slab wise GST Rates']/following-sibling::Button[@Name='...']");
        WebElement slabGST = common.findWebElement("xpath", "//Edit[@Name='GST Product Category Row 0, Not sorted.']");
        slabGST.click();
        slabGST.sendKeys(common.getData(dataFile, "slabGstPercentage"), Keys.ENTER);
        common.clickElement("xpath", "//Edit[@Name='wef Date Row 0, Not sorted.']");
        common.clickElement("xpath", "//Edit[@Name='Minimum Value Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='Minimum Value Row 0, Not sorted.']", common.getData(dataFile, "minValue"));
        common.clickElement("xpath", "//Edit[@Name='Maximum Value Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='Maximum Value Row 0, Not sorted.']", common.getData(dataFile, "maxValue"));
        common.clickElement("xpath", "//Button[@Name='Ok']");
        saveAfterMasterCreate();
        Thread.sleep(1000);
        validateMastersAndInactive("HSN Codes","All HSN Codes",master,"HSN Codes");
        Thread.sleep(1000);
    }
}
