package com.wings.pages.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.wings.pages.Transaction;
import com.wings.utils.Common;

import java.io.IOException;

public class SalesPrices extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesPrices(WindowsDriver driver, String file){
        super(driver);
        common=new Common(this.driver=driver);
        dataFile=file;
    }

    public void salesPrices() throws InterruptedException, IOException, ParseException {
        navigateToSalesPricesMenu();
        Thread.sleep(1000);
        lastTransactionName();
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"),"xpath","//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"),"xpath","//Edit[@Name='Transaction Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Master Type']");
        selectAndValidateData(common.getData(dataFile, "MasterType"),"xpath","//Edit[@Name='Master Type']");
        common.clickElement("xpath", "//Edit[@Name='Price List *']");
        selectAndValidateData(common.getData(dataFile,"priceList"),"xpath", "//Edit[@Name='Price List *']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        //product-1
        enterData("xpath","//Edit[@Name='Minimum Rate * Row 0, Not sorted.']",dataFile,"minimum");
        enterData("xpath","//Edit[@Name='Maximum Rate * Row 0, Not sorted.']",dataFile,"maximum");
        enterData("xpath","//Edit[@Name='Rate * Row 0, Not sorted.']",dataFile,"rate");
        enterData("xpath","//Edit[@Name='MRP Row 0, Not sorted.']",dataFile,"MRP");
        Thread.sleep(1500);
        common.deleteInvalidRows();
        //save
        transactionSave();
        lastTransactionName();
    }
}
