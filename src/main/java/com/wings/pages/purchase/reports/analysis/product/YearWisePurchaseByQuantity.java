package com.wings.pages.purchase.reports.analysis.product;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class YearWisePurchaseByQuantity extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public YearWisePurchaseByQuantity(WindowsDriver driver,String file) {
        super(driver);
        this.driver = driver;
        dataFile=file;
        common = new Common(this.driver);
    }

    public void yearWisePurchaseByQuantity() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen4Steps("Purchase","Analysis","Product","Year Wise Purchase By Quantity");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        analysisReport(dataFile,"yearWisePurchaseByQuantity");
        closeReport("Year Wise Purchase By Quantity");
    }
}
