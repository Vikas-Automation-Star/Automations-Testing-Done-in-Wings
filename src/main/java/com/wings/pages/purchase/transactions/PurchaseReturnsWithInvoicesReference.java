package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.List;

public class PurchaseReturnsWithInvoicesReference extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchaseReturnsWithInvoicesReference(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchaseReturnsWithInvoicesReference() throws InterruptedException, IOException, ParseException {
       navigateToPurchaseVouchersWithInvoicesReference();
        Thread.sleep(3000);
        oldTTransaction();
//        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
//        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectMasterWithValidation(common.getData(dataFile,"branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectOptionalMaster(common.getData(dataFile,"location"), "xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectMasterWithValidation(common.getData(dataFile,"currency"),"xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath","//Edit[@Name='Purchase VNo *']");
        inputTextWithValidation("xpath","//Edit[@Name='Purchase VNo *']", common.getData(dataFile,"purchaseVoucherNo"));
        common.clickElement("xpath", "//Edit[@Name='Cash/Party *']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Consignor']");
        selectMasterWithValidation(common.getData(dataFile,"consignor"),"xpath", "//Edit[@Name='Consignor']");
        common.clickElement("xpath", "//Edit[@Name='Purchase Return A/c Code']");
        selectMasterWithValidation(common.getData(dataFile,"PurchaseReturnA/cCode"),"xpath", "//Edit[@Name='Purchase Return A/c Code']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectMasterWithValidation(common.getData(dataFile,"priceList"),"xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectMasterWithValidation(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
        Thread.sleep(1000);
//        common.clickElement("xpath","//Edit[@Name='Remarks']");
//        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        enterData("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']",dataFile,"quantity");
        common.findWebElements("xpath", "//Edit[@Name='Quantity In SKU Row 0, Not sorted.']");
        Thread.sleep(500);
        sliderHandle();
        billsPayable("xpath","//Edit[@Name='Amount Adjusted * Row 0, Not sorted.']",dataFile,"billsPayableAmount");
        transactionSave();
        Thread.sleep(1500);
        newTransaction();
        closeTransaction("Purchase Returns with Invoice Reference");
        Thread.sleep(2000);
        Allure.step("PurchaseReturnsWithInvoicesReferences Transaction");
    }
}
