package com.wings.pages.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;


import java.io.IOException;

public class ProfitCentres extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public ProfitCentres(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void profitCentres() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Company","Profit Centres");
        Thread.sleep(1000);
        super.createMaster("xpath", "//TreeItem[@Name='Profit Centres']/TreeItem[@Name='All Profit Centres']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Profit Centre *']", common.getData(dataFile, "newProfitCentres") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Profit Centre *']").getText();
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        super.saveAfterMasterCreate();
        validateMastersAndInactive("Profit Centres","All Profit Centres",master,"Profit Centres");
        Thread.sleep(1500);
    }

}
