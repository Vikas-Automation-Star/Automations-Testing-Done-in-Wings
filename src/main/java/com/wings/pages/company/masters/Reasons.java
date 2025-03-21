package com.wings.pages.company.masters;


import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;


import java.awt.*;
import java.io.IOException;

public class Reasons extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Reasons(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createTransporters() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen2Steps("Company","Reasons");
        Thread.sleep(1000);
        super.createMaster("xpath", "//TreeItem[@Name='Reasons']/TreeItem[@Name='All Reasons']");
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='New Reason *']", common.getData(dataFile, "reason") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Reason *']").getText();
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        Thread.sleep(1000);
        super.saveAfterMasterCreate();
        validateMastersAndInactive("Reasons",master);
        Thread.sleep(1000);
    }
}
