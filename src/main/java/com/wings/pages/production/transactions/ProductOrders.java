package com.wings.pages.production.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

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
        super.oldTTransaction();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMasterWithValidation(common.getData(dataFile,"branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        super.selectMasterWithValidation(common.getData(dataFile,"cuurency"),"xpath", "//Edit[@Name='Transaction Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Finished Product *']");
        super.selectMasterWithValidation(common.getData(dataFile,"fProduct"),"xpath", "//Edit[@Name='Finished Product *']");
        common.clickElement("xpath","//Edit[@Name='SKU *']");
        common.clickElement("xpath","//Edit[@Name='BOM *']");
        super.selectMasterWithValidation(common.getData(dataFile,"New Bill of Material61"),"xpath","//Edit[@Name='BOM *']");
        common.clickElement("xpath","//Edit[@Name='Order Quantity *']");
        super.inputTextWithValidation("xpath","//Edit[@Name='Order Quantity *']", common.getData(dataFile,"quantity"));
        common.clickElement("xpath","//Edit[@Name='Executive *']");
        super.selectMasterWithValidation(common.getData(dataFile,"Executive"), "xpath","//Edit[@Name='Executive *']");
        transactionSave();
        Thread.sleep(1000);
        super.newTransaction();
        super.closeTransaction("Production Orders");
        Thread.sleep(2000);

    }
}
