package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class MaterialReturn extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public MaterialReturn(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void materialReturn() throws InterruptedException, IOException, ParseException {
        navigateToMaterialReturns();
        Thread.sleep(3000);
        super.oldTTransaction();
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMasterWithValidation(common.getData(dataFile,"branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        super.selectMasterWithValidation(common.getData(dataFile,"currency"),"xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Receipt No']");
        super.inputTextWithValidation("xpath","//Edit[@Name='Receipt No']", common.getData(dataFile,"ReceiptNo"));
        common.clickElement("xpath", "//Edit[@Name='Party Account *']");
        common.clickElement("xpath","//Edit[@Name='Executive *']");
        super.selectMasterWithValidation(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
        Thread.sleep(500);
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        super.sliderHandle();
        common.clickElement("xpath","//Edit[@Name='Quantity Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Quantity Row 0, Not sorted.']", common.getData(dataFile,"quantity"));
        common.clickElement("xpath","//Edit[@Name='MRP Row 0, Not sorted.']");
        Thread.sleep(1500);
        common.clickElement("xpath","//TabItem[@Name='  Ctrl-F5 Summary  ']");
        super.saveTransaction();
        Thread.sleep(1500);
        super.newTransaction();
        super.closeTransaction("Material Returns");
        Thread.sleep(2000);
        Allure.step("MaterialReturns Transaction");


    }
}