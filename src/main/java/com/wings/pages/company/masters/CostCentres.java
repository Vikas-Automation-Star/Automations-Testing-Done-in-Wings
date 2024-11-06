package com.wings.pages.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;


import java.io.IOException;

public class CostCentres extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CostCentres(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void costCentres() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Company");
        common.clickElement("name", "Cost Centres");
        Thread.sleep(1000);
        super.createMaster("xpath","//TreeItem[@Name='Cost Centres']/TreeItem[@Name='All Cost Centres']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Cost Centre *']", common.getData(dataFile, "newCostCentres") + common.getRandom());
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        super.saveAfterMasterCreate();
        super.closeMaster("Cost Centres");
        Thread.sleep(1500);

    }
}
