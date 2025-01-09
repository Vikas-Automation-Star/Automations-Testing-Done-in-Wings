package com.wings.pages.company.masters;


import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Projects extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Projects(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void project() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Company");
        common.clickElement("name", "Projects");
        Thread.sleep(1000);
        super.createMaster("xpath", "//TreeItem[@Name='Projects']/TreeItem[@Name='All Projects']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Project *']", common.getData(dataFile, "newProject") + common.getRandom());
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        Thread.sleep(1000);
        super.saveAfterMasterCreate();
        Thread.sleep(2000);
        super.closeMaster("Projects");
    }

}
