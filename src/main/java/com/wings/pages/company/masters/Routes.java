package com.wings.pages.company.masters;


import com.wings.pages.Masters;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class Routes extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Routes(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createRoutes() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Company","Routes");
        Thread.sleep(1000);
        super.createMaster("xpath", "//TreeItem[@Name='Routes']/TreeItem[@Name='All Routes']");
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='New Route *']", common.getData(dataFile, "route") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Route *']").getText();
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        super.saveAfterMasterCreate();
        validateMastersAndInactive("Routes",master);
        Thread.sleep(1500);
    }
}
