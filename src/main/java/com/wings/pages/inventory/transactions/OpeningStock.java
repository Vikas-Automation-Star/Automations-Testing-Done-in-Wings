package com.wings.pages.inventory.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.pages.Transaction;
import com.wings.utils.Common;

import java.awt.*;
import java.io.IOException;

public class OpeningStock extends Transaction {
        WindowsDriver driver;
        Common common;
        String dataFile;

        public OpeningStock(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
            dataFile=file;
        }

        public void stockOpen() throws InterruptedException, AWTException, IOException, ParseException {
            navigateToOpeningStockMenu();
            lastTransactionName();
            common.clickElement("xpath", "//Edit[@Name='Branch *']");
            selectAndValidateData(common.getData(dataFile,"branch"), "xpath", "//Edit[@Name='Branch *']");
            common.clickElement("xpath", "//Edit[@Name='Location *']");
            selectAndValidateData(common.getData(dataFile,"Location"),"xpath", "//Edit[@Name='Location *']");
            common.clickElement("xpath","//Edit[@Name='Opening Stock Account *']");
            selectAndValidateData(common.getData(dataFile,"stockAccount"),"xpath","//Edit[@Name='Opening Stock Account *']");
            common.clickElement("xpath","//Edit[@Name='Opening Stock Account Asset *']");
            selectAndValidateData(common.getData(dataFile,"stockAsset"),"xpath","//Edit[@Name='Opening Stock Account Asset *']");
            common.clickElement("xpath", "//Edit[@Name='Price List']");
            selectAndValidateData(common.getData(dataFile,"priceList"),"xpath", "//Edit[@Name='Price List']");
            common.clickElement("xpath", "//Edit[@Name='Executive *']");
            selectAndValidateData(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
            //F3-Items
            enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']",dataFile, "productCode");
            enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']",dataFile,"Quantity");
            common.clickElement("xpath","//CheckBox[@Name='Apply Price List Row 0']");
            //save
            Thread.sleep(2500);
            transactionSave();
            lastTransactionName();
        }
    }
