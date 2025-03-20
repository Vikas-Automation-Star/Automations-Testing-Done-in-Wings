package com.wings.pages.taxes.masters.TDS;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.io.IOException;

public class TdsSubTypes extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public TdsSubTypes(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void tdsSubTypes() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps("Taxes","TDS","TDS Sub Types");
        WebElement element = common.findWebElement("xpath", "//TreeItem[@Name='TDS Sub Types']/TreeItem[@Name='All TDS Sub Types']");
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New TDS Sub Type *']", common.getData(dataFile, "newTdsSubType") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New TDS Sub Type *']").getText();
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        common.clickElement("xpath", "//Edit[@Name='TDS Paid Account']/Button[@Name='Open']");
        common.clickElement("xpath", "//Edit[@Name='TDS Collected Account']/Button[@Name='Open']");
        saveAfterMasterCreate();
        Thread.sleep(1000);
        validateMastersAndInactive("TDS Sub Types","All TDS Sub Types",master,"TDS Sub Types");
        Thread.sleep(1000);
    }
}
