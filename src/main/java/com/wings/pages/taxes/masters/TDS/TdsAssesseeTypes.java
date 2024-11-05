package com.wings.pages.taxes.masters.TDS;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.io.IOException;

public class TdsAssesseeTypes extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public TdsAssesseeTypes(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void tdsAssesseeTypes() throws InterruptedException, IOException, ParseException, AWTException {

        common.clickElement("name", "Taxes");
        common.clickElement("name", "TDS");
        common.clickElement("name", "TDS Assessee Types");
        WebElement element = common.findWebElement("xpath", "//TreeItem[@Name='TDS Assessee Types']/TreeItem[@Name='All TDS Assessee Types']");
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New TDS Assessee Type *']", common.getData(dataFile, "newTdsType")+common.getRandom());
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "desription"));


        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        common.clickElement("xpath", "//Button[@Name='Close']");
        Thread.sleep(1000);
        super.closeMaster("TDS Assessee Types");
        Thread.sleep(2000);

    }
}
