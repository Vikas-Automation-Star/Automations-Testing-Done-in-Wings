package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class PurchasePrice extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchasePrice(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchasePrice() throws InterruptedException, IOException, ParseException {
        navigateToPurchasePrice();
        Thread.sleep(3000);
        super.oldTTransaction();
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMasterWithValidation(common.getData(dataFile,"branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        super.selectMasterWithValidation(common.getData(dataFile,"currency"),"xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Master Type']");
        super.selectMasterWithValidation(common.getData(dataFile,"masterType"),"xpath", "//Edit[@Name='Master Type']") ;
        common.clickElement("xpath", "//Edit[@Name='Node']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Price List *']");
        super.selectMasterWithValidation(common.getData(dataFile,"pricelist"),"xpath", "//Edit[@Name='Price List *']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        super.selectAndValidateDataNew(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        common.clickElement("xpath","//Edit[@Name='Rate * Row 2, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Rate * Row 2, Not sorted.']", common.getData(dataFile,"Urate"));
        common.clickElement("xpath","//Edit[@Name='MRP Row 2, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='MRP Row 2, Not sorted.']", common.getData(dataFile,"mrp"));
        Thread.sleep(2000);
        common.deleteInvalidRows();
        super.saveTransaction();
        Thread.sleep(1500);
        super.oldTTransaction();
        Thread.sleep(1500);
        super.closeTransaction("Purchase Prices");
        Thread.sleep(2000);
        Allure.step("PurchasePrices Transaction");

    }
}
