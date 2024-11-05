package com.wings.pages.taxes.masters.TCS;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.io.IOException;

public class TcsTransactionNature extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public TcsTransactionNature(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void tcsTransactionNature() throws InterruptedException, IOException, ParseException, AWTException {

        common.clickElement("name", "Taxes");
        common.clickElement("name", "TCS");
        common.clickElement("name", "TCS Transaction Natures");
        WebElement element = common.findWebElement("xpath", "//TreeItem[@Name='TCS Transaction Natures']/TreeItem[@Name='All TCS Transaction Natures']");
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='NewTCS Transaction Nature *']", common.getData(dataFile, "newTcsTransactionNature") + common.getRandom());
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "desription"));
        common.clickElement("xpath", "//Text[@Name='TCS Transaction Nature']/following-sibling::Button[@Name='...']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Edit[@Name='TCS Assessee Type * Row 0, Not sorted.']");
        WebElement element1 = common.findWebElement("xpath", "//Edit[@Name='TCS Assessee Type * Row 0, Not sorted.']");
        element1.sendKeys(common.getData(dataFile, "tdsAssesseType"), Keys.ENTER);
        WebElement element2 = common.findWebElement("xpath", "//Edit[@Name='TCS Sub Type * Row 0, Not sorted.']");
        element2.sendKeys(common.getData(dataFile, "subTcsTax"), Keys.ENTER);
        common.inputText("xpath", "//Edit[@Name='Level Row 0, Not sorted.']", common.getData(dataFile, "level"));
        common.clickElement("xpath", "//Edit[@Name='TCS Rate Row 0, Not sorted.']");
        WebElement tcsRate=common.findWebElement("xpath", "//Edit[@Name='TCS Rate Row 0, Not sorted.']");
        tcsRate.clear();
        tcsRate.sendKeys(common.getData(dataFile, "tcsRate"));
        WebElement minValue= common.findWebElement("xpath","//Edit[@Name='Minimum Value * Row 0, Not sorted.']");
        minValue.click();
        minValue.clear();
        minValue.sendKeys(common.getData(dataFile,"minValue"));
        common.clickElement("xpath", "//Button[@Name='Ok']");

        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        common.clickElement("xpath", "//Button[@Name='Close']");
        Thread.sleep(1000);
        super.closeMaster("TCS Transaction Natures");
        Thread.sleep(2000);
    }
}
