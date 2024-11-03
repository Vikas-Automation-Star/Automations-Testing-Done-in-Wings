package com.wings.pages.inventory.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;

public class InterLocationTransfers extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public InterLocationTransfers(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }
    public void locationTransfer() throws InterruptedException, AWTException, IOException, ParseException {
        common.clickElement("name", "Inventory");
        common.clickElement("xpath", "//MenuItem[@Name='Inter Location Transfers']");
        lastTransactionName();
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile,"branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath","//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile,"transaction"), "xpath","//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectAndValidateData(common.getData(dataFile,"fromLocation"),"xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath","//Edit[@Name='To Location *']");
        selectAndValidateData(common.getData(dataFile,"toLocation"),"xpath","//Edit[@Name='To Location *']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile, "executive"),"xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        //F3-Items
        common.clickElement("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        selectAndValidateData(common.getData(dataFile, "productCode"),"xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        Thread.sleep(2500);
        enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']",dataFile,"quantity");
        Thread.sleep(1000);
        common.sliderHandling("name","Position",500,0);
        //save
        transactionSave();
        lastTransactionName();
    }
}
