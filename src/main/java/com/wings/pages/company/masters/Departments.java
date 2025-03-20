package com.wings.pages.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class Departments extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Departments(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void departments() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Company","Departments");
        Thread.sleep(1000);
        super.createMaster("xpath", "//TreeItem[@Name='Departments']/TreeItem[@Name='All Departments']");
        Thread.sleep(1500);
        common.inputText("xpath", "//Edit[@Name='New Department *']", common.getData(dataFile, "newDepartment") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Department *']").getText();
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        super.saveAfterMasterCreate();
        validateMastersAndInactive("Departments","All Departments",master,"Departments");
        Thread.sleep(2000);
    }

}
