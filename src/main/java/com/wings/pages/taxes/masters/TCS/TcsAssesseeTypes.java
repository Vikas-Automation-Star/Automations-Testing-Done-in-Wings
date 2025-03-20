package com.wings.pages.taxes.masters.TCS;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.io.IOException;

public class TcsAssesseeTypes extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public TcsAssesseeTypes(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void tcsAssesseeTypes() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps("Taxes","TCS","TCS Assessee Types");
        WebElement element = common.findWebElement("xpath", "//TreeItem[@Name='TCS Assessee Types']/TreeItem[@Name='All TCS Assessee Types']");
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='NewTCS Assessee Type *']", common.getData(dataFile, "newTcsType") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='NewTCS Assessee Type *']").getText();
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        saveAfterMasterCreate();
        Thread.sleep(1000);
        validateMastersAndInactive("TCS Assessee Types","All TCS Assessee Types",master,"TCS Assessee Types");
        Thread.sleep(1000);
    }
}
