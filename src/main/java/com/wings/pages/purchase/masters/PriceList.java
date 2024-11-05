package com.wings.pages.purchase.masters;

import com.wings.pages.Masters;;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;;

import java.awt.*;
import java.io.IOException;

public class PriceList extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PriceList(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createpriceList() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Price List");
        common.clickElement("name", "Purchase Price Lists");
        Thread.sleep(2500);
        super.createMaster("xpath", "//TreeItem[@Name='Purchase Price Lists']/TreeItem[@Name='All Purchase Price Lists']");
        Thread.sleep(2000);
        super.inputTextWithValidation("xpath", "//Edit[@Name='New Purchase Price List *']", common.getData(dataFile,"newPurchasePriceList")+common.getRandom());
        super.inputTextWithValidation("xpath","//Edit[@Name='Description']", common.getData(dataFile,"description"));
        super.saveAfterMasterCreate();
        super.closeMaster("Purchase Price Lists");
    }
}
