package com.wings.pages.purchase.reports.analysis.party;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class MonthWisePurchaseByValue extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public MonthWisePurchaseByValue(WindowsDriver driver,String file) {
        super(driver);
        this.driver = driver;
        dataFile=file;
        common = new Common(this.driver);
    }

    public void monthWisePurchaseByValue() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen4Steps("Purchase","Analysis","Party","Month Wise Purchase By Value");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        analysisReport(dataFile,"monthWisePurchaseByValue");
        closeReport("Month Wise Purchase By Value");
    }
}
