package com.wings.pages.production.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class MaterialReturnsFromProduction extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public MaterialReturnsFromProduction(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void materialReturnsFromProduction() throws InterruptedException, IOException, ParseException {

        common.clickElement("name", "Production");
        common.clickElement("name", "Standard");
        common.clickElement("name", "Material Returns from Production");
        Thread.sleep(3000);
        oldTTransaction();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectMasterWithValidation(common.getData(dataFile,"branch"),"xpath","//Edit[@Name='Branch *']" );
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectAndValidateData(common.getData(dataFile,"location"),"xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectMasterWithValidation(common.getData(dataFile,"currency"),"xpath", "//Edit[@Name='Trans Currency *']");
        WebElement code = common.findWebElement("xpath", "//Edit[@Name='Material Issue No']");
        code.click();
        code.sendKeys(common.getData(dataFile, "MaterialIssueNo"));
        enterData("xpath","//Edit[@Name='Quantity * Row 0, Not sorted.']",dataFile,"quantity");
        common.clickElement("xpath", "//Edit[@Name='Unit Rate Row 0, Not sorted.']");
        transactionSave();
        Thread.sleep(1000);
        newTransaction();
        closeTransaction("Material Returns from Production");
        Thread.sleep(2000);

    }
}

