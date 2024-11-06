package com.wings.pages.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;

public class TermType extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public TermType(WindowsDriver driver, String file) {
        super(driver);
        this.driver=driver;
        common=new Common(this.driver);
        dataFile=file;
    }

    public void termType() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name","Company");
        common.clickElement("name","Terms");
        common.clickElement("xpath","//MenuItem[@Name='Term Types']");
        Thread.sleep(1000);
        super.createMaster("xpath", "//TreeItem[@Name='Term Types']/TreeItem[@Name='All Term Types']");
        Thread.sleep(1000);
        common.inputText("xpath","//Edit[@Name='New Term Type *']",common.getData(dataFile,"newTermType")+common.getRandom());
        Thread.sleep(2000);
        common.inputText("xpath","//Edit[@Name='Description']", common.getData(dataFile,"description"));
        Thread.sleep(1000);
        super.saveAfterMasterCreate();
        super.closeMaster("Term Types");
        Thread.sleep(1000);
    }
}
