package com.wings.pages.production.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class AssignStandardRates extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public AssignStandardRates(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void assignStandardRates() throws InterruptedException, IOException, ParseException {

        common.clickElement("name", "Production");
        common.clickElement("name", "Assign Standard Rates");
        Thread.sleep(3000);
        oldTTransaction();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectMasterWithValidation(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateDataNew(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        enterData("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']", dataFile, "pCode");
        enterData("xpath", "//Edit[@Name='Rate * Row 0, Not sorted.']", dataFile, "uRate");

//        WebElement uRate= common.findWebElement("xpath","//Edit[@Name='Rate * Row 0, Not sorted.']");
//        uRate.click();
//        uRate.sendKeys(common.getData(dataFile,"urate"),Keys.ENTER);

        transactionSave();
        Thread.sleep(1000);
        newTransaction();
        closeTransaction("Assign Standard Rates");
        Thread.sleep(4000);

    }
}
