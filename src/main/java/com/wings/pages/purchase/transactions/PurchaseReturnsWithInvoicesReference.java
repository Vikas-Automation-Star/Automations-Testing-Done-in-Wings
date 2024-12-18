package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;

import java.io.IOException;

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
        super.oldTTransaction();
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMasterWithValidation(common.getData(dataFile,"branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        super.selectMasterWithValidation(common.getData(dataFile,"currency"),"xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath","//Edit[@Name='Purchase VNo *']");
        super.inputTextWithValidation("xpath","//Edit[@Name='Purchase VNo *']", common.getData(dataFile,"purchaseVoucherNo"));
        common.clickElement("xpath", "//Edit[@Name='Cash/Party *']");
        common.clickElement("xpath", "//Edit[@Name='Consignor']");
        super.selectMasterWithValidation(common.getData(dataFile,"consigner"),"xpath", "//Edit[@Name='Consignor']");
        common.clickElement("xpath", "//Edit[@Name='Purchase Return A/c Code']");
        super.selectMasterWithValidation(common.getData(dataFile,"PurchaseReturnA/cCode"),"xpath", "//Edit[@Name='Purchase Return A/c Code']");
        common.clickElement("xpath", "//Edit[@Name='Purchase Return A/c']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        super.selectMasterWithValidation(common.getData(dataFile,"pricelist"),"xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        super.selectMasterWithValidation(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        common.clickElement("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']", common.getData(dataFile,"quantity"));
        common.findWebElements("xpath", "//Edit[@Name='Quantity In SKU Row 0, Not sorted.']");
        Thread.sleep(500);
        super.sliderHandle();
        Thread.sleep(1000);
        common.findWebElements("xpath", "//Edit[@Name='MRP Row 0, Not sorted.']");
        super.sliderHandle();
        transactionSave();
        Thread.sleep(1500);
        super.newTransaction();
        super.closeTransaction("Purchase Returns with Invoice Reference");
        Thread.sleep(2000);
        Allure.step("PurchaseReturnsWithInvoicesReferences Transaction");

    }
}
