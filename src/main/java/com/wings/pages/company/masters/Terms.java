package com.wings.pages.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Terms extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Terms(WindowsDriver driver, String file) {
        super(driver);
        this.driver=driver;
        common=new Common(this.driver);
        dataFile=file;
    }

    public void terms() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name","Company");
        common.clickElement("name","Terms");
        common.clickElement("xpath","//MenuItem[@Name='Terms']");
        Thread.sleep(1000);
        super.createMaster("xpath", "//TreeItem[@Name='Terms']/TreeItem[@Name='All Terms']");
        Thread.sleep(1000);
        super.inputTextWithValidation("xpath","//Edit[@Name='New Term *']",common.getData(dataFile,"newTerm")+common.getRandom());
        common.clickElement("xpath","//Edit[@Name='Term Type *']/Button[@Name='Open']");
        Thread.sleep(1000);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        super.inputTextWithValidation("xpath","//Edit[@Name='Description']", common.getData(dataFile,"description"));
        Thread.sleep(1000);
        super.saveAfterMasterCreate();
        super.closeMaster("Terms");
        Thread.sleep(1000);

    }
}
