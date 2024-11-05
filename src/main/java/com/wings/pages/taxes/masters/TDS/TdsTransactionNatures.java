package com.wings.pages.taxes.masters.TDS;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.io.IOException;

public class TdsTransactionNatures extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public TdsTransactionNatures(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void tdsTransactionNatures() throws InterruptedException, IOException, ParseException, AWTException {

        common.clickElement("name", "Taxes");
        common.clickElement("name", "TDS");
        common.clickElement("name", "TDS Transaction Natures");
        WebElement element = common.findWebElement("xpath", "//TreeItem[@Name='TDS Transaction Natures']/TreeItem[@Name='All TDS Transaction Natures']");
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New TDS Transaction Nature *']", common.getData(dataFile, "newTdsTransactionNature") + common.getRandom());
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "desription"));
        common.clickElement("xpath","//Text[@Name='TDS Transaction Nature']/following-sibling::Button[@Name='...']");
        Thread.sleep(2000);
        common.clickElement("xpath","//Edit[@Name='TDS Assessee Type * Row 0, Not sorted.']");
        WebElement element1=common.findWebElement("xpath","//Edit[@Name='TDS Assessee Type * Row 0, Not sorted.']");
        element1.sendKeys(common.getData(dataFile,"tdsAssesseType"), Keys.ENTER);
        WebElement element2 =common.findWebElement("xpath","//Edit[@Name='TDS Sub Type * Row 0, Not sorted.']");
        element2.sendKeys(common.getData(dataFile,"subTdsTax"), Keys.ENTER);
        common.inputText("xpath","//Edit[@Name='Level * Row 0, Not sorted.']", common.getData(dataFile,"level"));
        common.clickElement("xpath","//Edit[@Name='TDS Rate Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='TDS Rate Row 0, Not sorted.']", common.getData(dataFile,"tdsRate"));
        common.clickElement("xpath","//Button[@Name='Ok']");

        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        common.clickElement("xpath", "//Button[@Name='Close']");
        Thread.sleep(1000);
        super.closeMaster("TDS Transaction Natures");
        Thread.sleep(2000);
    }
}
