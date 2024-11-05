package com.wings.pages.production.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

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
        super.oldTTransaction();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMasterWithValidation(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        common.clickElement("xpath","//Edit[@Name='Executive *']");
        super.selectMasterWithValidation(common.getData(dataFile,"executive"),"xpath","//Edit[@Name='Executive *']");
        common.clickElement("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        super.selectMaster(common.getData(dataFile,"pCode"));
        WebElement uRate= common.findWebElement("xpath","//Edit[@Name='Rate * Row 0, Not sorted.']");
        uRate.click();
        uRate.sendKeys(common.getData(dataFile,"urate"),Keys.ENTER);
        super.saveTransaction();
        Thread.sleep(1000);
        super.newTransaction();
        super.closeTransaction("Assign Standard Rates");
        Thread.sleep(4000);

        }
    }
