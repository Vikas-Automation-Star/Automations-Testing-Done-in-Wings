package com.wings.pages.taxes.masters.TCS;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.io.IOException;

public class TcsSubTypes extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public TcsSubTypes(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void tcsSubTypes() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps("Taxes","TCS","TCS Sub Types");
        WebElement element = common.findWebElement("xpath", "//TreeItem[@Name='TCS Sub Types']/TreeItem[@Name='All TCS Sub Types']");
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='NewTCS Sub Type *']", common.getData(dataFile, "newTcsSubType") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='NewTCS Sub Type *']").getText();
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        common.clickElement("xpath", "//Edit[@Name='TCS Paid Account']/Button[@Name='Open']");
        common.clickElement("xpath", "//Edit[@Name='TCS Collected Account']/Button[@Name='Open']");
        saveAfterMasterCreate();
        Thread.sleep(1000);
        validateMastersAndInactive("TCS Sub Types",master);
        Thread.sleep(1000);
    }
}
