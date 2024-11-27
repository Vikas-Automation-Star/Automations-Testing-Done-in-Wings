package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class PurchaseVouchersAgainstOrder extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchaseVouchersAgainstOrder(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchaseVouchersAgainstOrder() throws InterruptedException, IOException, ParseException {
        navigateToPurchaseVouchersAgainstOrders();
        Thread.sleep(3000);
        super.oldTTransaction();
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMasterWithValidation(common.getData(dataFile,"branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        super.selectMasterWithValidation(common.getData(dataFile,"currency"),"xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        super.selectMasterWithValidation(common.getData(dataFile,"PartyCode"),"xpath", "//Edit[@Name='Party Code']");
        super.gstSelectionWhenBothRegisteredDealers();
        Thread.sleep(1500);
        common.clickElement("xpath", "//CheckBox[@Name='Select Row 0']");
        common.clickElement("xpath", "//Pane/Button[@Name='Ok']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Purchase A/C Code']");
        super.selectMasterWithValidation(common.getData(dataFile,"purchase A/C Code"),"xpath", "//Edit[@Name='Purchase A/C Code']");
        common.clickElement("xpath", "//Edit[@Name='Purchase Account']");
        Thread.sleep(500);
        common.clickElement("xpath","//Edit[@Name='Supplier Bill No *']");
        common.inputText("xpath", "//Edit[@Name='Supplier Bill No *']", common.getData(dataFile, "billNo")+common.getRandom());
        common.clickElement("xpath","//Edit[@Name='Supplier Bill Date *']");
        common.inputText("xpath", "//Edit[@Name='Supplier Bill Date *']", common.getData(dataFile, "billDate"));

        common.clickElement("xpath", "//Edit[@Name='Price List']");
        super.selectMasterWithValidation(common.getData(dataFile,"pricelist"),"xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        super.selectMasterWithValidation(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
        Thread.sleep(2000);
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        super.sliderHandle();
        Thread.sleep(500);
        common.clickElement("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']", common.getData(dataFile,"quantity"));
        common.findWebElements("xpath", "//Edit[@Name='MRP Row 0, Not sorted.']");
        super.sliderHandle();
        super.saveTransaction();
        Thread.sleep(1500);
        super.newTransaction();
        super.closeTransaction("Purchase Vouchers against Orders");
        Thread.sleep(2000);
        Allure.step("PurchaseVouchersAgainstOrders Transaction");


    }
    }


