package com.wings.pages.purchase.reports.analysis.product;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        navigateToMastersWhen4Steps("Purchase","Analysis","Product","Month Wise Purchase By Value");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        List<String> products = Arrays.asList("AT_Multi batch Product 1","AT_Product 1","AT_Product With SN 2");
        verifyAnalysisReportProductWise( dataFile,"monthWisePurchaseByValueMultiBatch", Collections.singletonList(products.get(0)));
        verifyAnalysisReportProductWise(dataFile,"monthWisePurchaseByValueGeneral", Collections.singletonList(products.get(1)));
        verifyAnalysisReportProductWise( dataFile,"monthWisePurchaseByValueSerialNum",Collections.singletonList(products.get(2)));
        closeReport("Month Wise Purchase By Value");
    }
}
