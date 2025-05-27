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

public class ProductAlternateUOMDetails extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    public ProductAlternateUOMDetails(WindowsDriver driver,String file) {
        super(driver);
        this.driver = driver;
        dataFile=file;
        common = new Common(this.driver);
    }

    public void productAlternateUOMDetails() throws InterruptedException, AWTException, IOException, ParseException {
        navigateToMastersWhen3Steps("Inventory","Master Info","Product Alternate UOM Details");
        Thread.sleep(1000);
        common.clickElement("xpath","//Tab[@Name='Product Alternate UOM Details']/Pane[@Name='Product']/Pane/Pane/List[@Name=' All ']/RadioButton[@Name=' One ']");
        enterInput("xpath","//Tab[@Name='Product Alternate UOM Details']/Pane[@Name='Product']/Pane/Pane/Pane/Edit",dataFile,"productUOMDetails","product");
        common.clickElement("xpath","//Button[@Name='Submit']");
        List<String> products = Arrays.asList("AT_Box");
        verifyAnalysisReportProductWise( dataFile,"AT_BoxUOM", Collections.singletonList(products.get(0)));
        closeReport("Product Alternate UOM Details");
    }
}
