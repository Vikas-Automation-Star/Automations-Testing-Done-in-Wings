package com.wings.pages.production.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class MaterialReceiptFromProduction extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public MaterialReceiptFromProduction(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void materialReceiptFromProduction() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Production");
        common.clickElement("name", "Standard");
        common.clickElement("name", "Material Receipts from Production");
        Thread.sleep(3000);
        oldTTransaction();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectMasterWithValidation(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectAndValidateData(common.getData(dataFile, "location"), "xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        selectMasterWithValidation(common.getData(dataFile, "currency"), "xpath", "//Edit[@Name='Transaction Currency *']");
        WebElement code = common.findWebElement("xpath", "//Edit[@Name='Finished Product Code']");
        code.click();
        selectAndValidateDataNew(common.getData(dataFile, "finishedProductCode"), "xpath", "//Edit[@Name='Finished Product Code']");
        common.clickElement("xpath", "//Edit[@Name='Finished Product *']");
        common.clickElement("xpath", "//CheckBox[@Name='Select Row 0']");
        common.clickElement("xpath", "//Window[@Name='Open Transactions']/Pane/Button[@Name='Ok']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateDataNew(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        sliderHandle();
        enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", dataFile, "quantity");
        common.clickElement("xpath", "//Edit[@Name='Gross Amount Row 0, Not sorted.']");
        transactionSave();
        Thread.sleep(1000);
        newTransaction();
        closeTransaction("Material Receipts from Production");
        Thread.sleep(2000);
    }
}
