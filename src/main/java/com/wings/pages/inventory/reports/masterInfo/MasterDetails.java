package com.wings.pages.inventory.reports.masterInfo;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MasterDetails extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    public MasterDetails(WindowsDriver driver,String file) {
        super(driver);
        this.driver = driver;
        dataFile=file;
        common = new Common(this.driver);
    }

    public void masterDetails() throws InterruptedException, AWTException, IOException, ParseException {
        navigateToMastersWhen3Steps("Inventory","Master Info","Master Details");
        Thread.sleep(1000);
        enterInput("xpath","//Edit[@Name='Master  Type']",dataFile,"masterDetails","master");
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");

        List<String> products = Arrays.asList("SCI1","SCI2","SEI1","SEI2","SRI2","SRI1","SSI1","SUI2","SUI1");
        verifyAnalysisReportProductWise( dataFile,"supplierCompositeInter", Collections.singletonList(products.get(0)));
        verifyAnalysisReportProductWise(dataFile,"supplierCompositeIntra", Collections.singletonList(products.get(1)));
        verifyAnalysisReportProductWise( dataFile,"supplierExemptedInter",Collections.singletonList(products.get(2)));
        verifyAnalysisReportProductWise( dataFile,"supplierExemptedIntra", Collections.singletonList(products.get(3)));
        verifyAnalysisReportProductWise(dataFile,"supplierRegInter", Collections.singletonList(products.get(4)));
        verifyAnalysisReportProductWise( dataFile,"supplierRegIntra",Collections.singletonList(products.get(5)));
        verifyAnalysisReportProductWise( dataFile,"supplierSEZInter", Collections.singletonList(products.get(6)));
        verifyAnalysisReportProductWise(dataFile,"supplierUnRegInter", Collections.singletonList(products.get(7)));
        verifyAnalysisReportProductWise( dataFile,"supplierUnRegIntra",Collections.singletonList(products.get(8)));

        closeReport("Master Details");
    }
}
