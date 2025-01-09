package com.wings.pages.production.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class ProductOrders extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public ProductOrders(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void productOrders() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Production");
        common.clickElement("name", "Standard");
        common.clickElement("name", "Production Orders");
        Thread.sleep(3000);
        oldTTransaction();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectAndValidateData(common.getData(dataFile, "location"), "xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        selectMasterWithValidation(common.getData(dataFile, "cuurency"), "xpath", "//Edit[@Name='Transaction Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Finished Product *']");
        selectMasterWithValidation(common.getData(dataFile, "fProduct"), "xpath", "//Edit[@Name='Finished Product *']");
        common.clickElement("xpath", "//Edit[@Name='BOM *']");
        selectAndValidateData(common.getData(dataFile, "BillOfMaterial"), "xpath", "//Edit[@Name='BOM *']");
        WebElement clear = common.findWebElement("xpath", "//Edit[@Name='Order Quantity *']");
        clear.clear();
        clear.sendKeys(common.getData(dataFile, "quantity"));
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectMasterWithValidation(common.getData(dataFile, "Executive"), "xpath", "//Edit[@Name='Executive *']");
        transactionSave();
        Thread.sleep(1000);
        newTransaction();
        closeTransaction("Production Orders");
        Thread.sleep(2000);

    }
}
