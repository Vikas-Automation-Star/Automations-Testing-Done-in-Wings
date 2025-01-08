package com.wings.pages.production.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class MaterialIssuesAndReceiptsFromProduction extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public MaterialIssuesAndReceiptsFromProduction(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void materialIssuesAndReceiptsFromProduction() throws InterruptedException, IOException, ParseException {

        common.clickElement("name", "Production");
        common.clickElement("name", "Simple");
        common.clickElement("name", "Material Issues and Receipts from Production");
        Thread.sleep(3000);
        oldTTransaction();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectMasterWithValidation(common.getData(dataFile,"branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectAndValidateData(common.getData(dataFile,"location"),"xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectMasterWithValidation(common.getData(dataFile,"currency"), "xpath", "//Edit[@Name='Trans Currency *']");
        WebElement code = common.findWebElement("xpath", "//Edit[@Name='Finished Product Code']");
        code.click();
        selectAndValidateDataNew(common.getData(dataFile,"finishedProductCode"),"xpath", "//Edit[@Name='Finished Product Code']");
        common.clickElement("xpath", "//Edit[@Name='Finished Product *']");
        common.clickElement("xpath","//Edit[@Name='Bill Of Material']");
        selectMasterWithValidation(common.getData(dataFile,"billOfMaterial"),"xpath","//Edit[@Name='Bill Of Material']" );
        common.clickElement("xpath", "//Edit[@Name='Batch Policy']");
        selectMasterWithValidation(common.getData(dataFile,"batchPolicy"),"xpath", "//Edit[@Name='Batch Policy']");
        inputTextWithValidation("xpath", "//Edit[@Name='Quantity *']", common.getData(dataFile,"quantity"));
        common.clickElement("xpath","//Edit[@Name='Executive *']");
        selectAndValidateDataNew(common.getData(dataFile,"executive"),"xpath","//Edit[@Name='Executive *']");
        common.clickElement("xpath","//Edit[@Name='Department']");
        selectAndValidateDataNew(common.getData(dataFile,"department"),"xpath","//Edit[@Name='Department']");

        common.clickElement("xpath","//TabItem[@Name='  F5 Receipts From Production  ']");
        common.clickElement("xpath","//Edit[@Name='Finished Product Quantity * Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Finished Product Quantity * Row 0, Not sorted.']", common.getData(dataFile,"finishedProductquantity"));
        sliderHandle();
        common.clickElement("xpath","//Edit[@Name='Gross Amount Row 0, Not sorted.']");
        transactionSave();
        Thread.sleep(1000);
        newTransaction();
        Thread.sleep(2000);
        closeTransaction("Material Issues and Receipts from Production");
        Thread.sleep(2000);
    }
}
