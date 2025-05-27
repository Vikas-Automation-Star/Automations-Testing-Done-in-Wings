package com.wings.pages.inventory.reports.masterInfo;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductBatches extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    public ProductBatches(WindowsDriver driver,String file) {
        super(driver);
        this.driver = driver;
        dataFile=file;
        common = new Common(this.driver);
    }
    public void productBatches() throws IOException, ParseException, InterruptedException {
        navigateToMastersWhen3Steps("Inventory","Master Info","Product Batches");
        Thread.sleep(1500);
        common.clickElement("xpath","//Tab[@Name='Product Batches']/Pane[@Name='Product  Batch']/Pane/Pane/List[@Name=' All ']/RadioButton[@Name=' One ']");
        enterInput("xpath","//Tab[@Name='Product Batches']/Pane[@Name='Product  Batch']/Pane/Pane/Pane/Edit",dataFile,"productBatches","product");
        common.clickElement("xpath","//Button[@Name='Submit']");
        List<String> products = Arrays.asList("AT_Product Batch 1");
        verifyAnalysisReportProductWise(dataFile,"productBatches", Collections.singletonList(products.get(0)));
        
        closeReport("Product Batches");
    }

}
