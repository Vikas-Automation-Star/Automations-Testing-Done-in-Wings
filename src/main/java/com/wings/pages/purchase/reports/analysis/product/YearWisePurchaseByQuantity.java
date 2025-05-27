package com.wings.pages.purchase.reports.analysis.product;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        List<String> products = Arrays.asList("AT_Multi batch Product 1","AT_Product 1","AT_Product With SN 2");
        verifyAnalysisReportProductWise( dataFile,"yearWisePurchaseByQuantityMultiBatch",Collections.singletonList(products.get(0)));
        verifyAnalysisReportProductWise(dataFile,"yearWisePurchaseByQuantityGeneral", Collections.singletonList(products.get(1)));
        verifyAnalysisReportProductWise( dataFile,"yearWisePurchaseByQuantitySerialNum",Collections.singletonList(products.get(2)));
        closeReport("Year Wise Purchase By Quantity");
    }
}
