package com.wings.pages.inventory.reports.masterInfo;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductWithGSTDetails extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    public ProductWithGSTDetails(WindowsDriver driver,String file) {
        super(driver);
        this.driver = driver;
        dataFile=file;
        common = new Common(this.driver);
    }

    public void productWithGST() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Inventory","Master Info","Product with GST Details");
        Thread.sleep(2000);
        common.clickElement("xpath","//Tab[@Name='Product with GST Details']/Pane[@Name='Product']/Pane/Pane/List[@Name=' All ']/RadioButton[@Name=' One ']");
        enterInput("xpath","//Tab[@Name='Product with GST Details']/Pane[@Name='Product']/Pane/Pane/Pane/Edit",dataFile,"productWithGST","product");
        common.clickElement("xpath","//Button[@Name='Submit']");
        List<String> products = Arrays.asList("SGST/CGST/IGST","CESS");
        verifyReportProductWise("", dataFile,"productWithGST", Collections.singletonList(products.get(0)));
        verifyReportProductWise("", dataFile,"productWithGSTCESS", Collections.singletonList(products.get(1)));
        closeReport("Product with GST Details");
    }
}
