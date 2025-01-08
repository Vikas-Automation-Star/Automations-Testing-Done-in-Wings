package com.wings.pages.production.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class MaterialissuestoProduction extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public MaterialissuestoProduction(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void materialIssuesToProduction() throws InterruptedException, IOException, ParseException {

        common.clickElement("name", "Production");
        common.clickElement("name", "Standard");
        common.clickElement("name", "Material Issues to Production");
        Thread.sleep(3000);
        oldTTransaction();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectMasterWithValidation(common.getData(dataFile,"branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectAndValidateData(common.getData(dataFile,"location"),"xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        selectMasterWithValidation(common.getData(dataFile,"currency"),"xpath", "//Edit[@Name='Transaction Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Finished Product Code']");
        selectAndValidateDataNew(common.getData(dataFile,"fProduct"),"xpath", "//Edit[@Name='Finished Product Code']");
        common.clickElement("xpath", "//Edit[@Name='Finished Product *']");
        common.clickElement("xpath","//CheckBox[@Name='Select Row 0']");
        common.clickElement("xpath", "//Window[@Name='Open Transactions']/Pane/Button[@Name='Ok']");
        common.clickElement("xpath","//Edit[@Name='Executive *']");
        selectMasterWithValidation(common.getData(dataFile,"executive"),"xpath","//Edit[@Name='Executive *']" );
        common.clickElement("xpath","//Edit[@Name='Department']");
        selectAndValidateDataNew(common.getData(dataFile,"department"),"xpath","//Edit[@Name='Department']");
        enterData("xpath","//Edit[@Name='Quantity * Row 0, Not sorted.']",dataFile,"quantity");
        transactionSave();
        Thread.sleep(1000);
        newTransaction();
        closeTransaction("Material Issues to Production");
        Thread.sleep(2000);

    }
}
