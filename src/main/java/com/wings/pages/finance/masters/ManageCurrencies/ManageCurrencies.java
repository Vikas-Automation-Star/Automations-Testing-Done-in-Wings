package com.wings.pages.finance.masters.ManageCurrencies;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ManageCurrencies {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public ManageCurrencies(WindowsDriver driver, String file) {
        super();
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void manageCurrency() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Manage Currencies");
        common.clickElement("xpath", "//MenuItem[@Name='Manage Currencies']");
        Thread.sleep(3000);

//        super.clickMaster(common.getData(dataFile,"currency"));

        common.clickElement("name", "Submit");
        common.clickElement("name", "OK");
    }
}
