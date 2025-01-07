package com.wings.pages.inventory.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;

public class StockCreation extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public StockCreation(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile=file;
    }

    public void stockCreation() throws InterruptedException, AWTException, IOException, ParseException {
        navigateToStockCreationMenu();
        lastTransactionName();
        Thread.sleep(1500);
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectAndValidateData(common.getData(dataFile,"Location"),"xpath", "//Edit[@Name='Location *']");
        Thread.sleep(10000);
        common.clickElement("xpath", "//Edit[@Name='Stock Account *']");
        selectAndValidateDataNew(common.getData(dataFile, "stockAcc"), "xpath", "//Edit[@Name='Stock Account *']");
        common.clickElement("xpath","//Edit[@Name='Batch Policy']");
        selectMasterWithValidation(common.getData(dataFile,"batchPolicy"),"xpath", "//Edit[@Name='Batch Policy']");
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectAndValidateData(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        //f3-items
        common.clickElement("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        selectAndValidateData(common.getData(dataFile,"productCode"),"xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        enterData("xpath", "//Edit[@Name='Goods Received Account * Row 0, Not sorted.']",dataFile,"goods");
        enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']",dataFile,"Quantity");
        common.sliderHandling("name","Position",300,0);
        //save
        transactionSave();
        lastTransactionName();
    }
}
