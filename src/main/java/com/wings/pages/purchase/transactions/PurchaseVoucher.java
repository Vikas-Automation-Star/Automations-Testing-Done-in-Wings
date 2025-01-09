package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class PurchaseVoucher extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchaseVoucher(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchaseVoucher() throws InterruptedException, IOException, ParseException {
        navigateToPurchaseVouchers();
        Thread.sleep(3000);
        oldTTransaction();
//        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
//        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectMasterWithValidation(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectOptionalMaster(common.getData(dataFile, "location"), "xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectMasterWithValidation(common.getData(dataFile, "currency"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Cash/Party Code']");
        selectAndValidateDataNew(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Cash/Party Code']");
        gstSelectionWhenBothRegisteredDealers();
        Thread.sleep(1500);
        common.clickElement("xpath", "//Edit[@Name='Consignor']");
        selectMasterWithValidation(common.getData(dataFile, "Consignor"), "xpath", "//Edit[@Name='Consignor']");
        common.clickElement("xpath", "//Edit[@Name='Purchase A/c Code']");
        selectMasterWithValidation(common.getData(dataFile, "PurchaseA/cCode"), "xpath", "//Edit[@Name='Purchase A/c Code']");
        common.clickElement("xpath", "//Edit[@Name='Purchase Account']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Supplier Bill No *']");
        common.inputText("xpath", "//Edit[@Name='Supplier Bill No *']", common.getData(dataFile, "billNo") + common.getRandom());
        common.clickElement("xpath", "//Edit[@Name='Supplier Bill Date *']");
        common.inputText("xpath", "//Edit[@Name='Supplier Bill Date *']", common.getData(dataFile, "billDate"));
        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Batch Policy']");
        selectMasterWithValidation(common.getData(dataFile, "batchPolicy"), "xpath", "//Edit[@Name='Batch Policy']");
//        common.clickElement("xpath","//CheckBox[@Name='Apply TCS']");
//        Thread.sleep(1500);
//        common.clickElement("xpath","//Edit[@Name='TCS Trans Nature']");
//        super.selectMasterWithValidation(common.getData(dataFile,"tcsTranNature"),"xpath", "//Edit[@Name='TCS Trans Nature']" );
//        common.clickElement("xpath","//CheckBox[@Name='Deduct TDS']");
//        Thread.sleep(1500);
//        common.clickElement("xpath","//Edit[@Name='TDS Trans Nature']");
//        super.selectMasterWithValidation(common.getData(dataFile,"tdsTranNature"),"xpath", "//Edit[@Name='TDS Trans Nature']" );
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectMasterWithValidation(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectMasterWithValidation(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        Thread.sleep(1000);
//        common.clickElement("xpath","//Edit[@Name='Remarks']");
//        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        enterData("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']", dataFile, "productCode");
        Thread.sleep(1000);
        enterData("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']", dataFile, "quantity");
        sliderHandle();
        Thread.sleep(500);
        sliderHandle();
        transactionSave();
        Thread.sleep(1500);
        newTransaction();
        closeTransaction("Purchase Vouchers");
        Thread.sleep(3000);
        Allure.step("PurchaseVouchers Transaction");
    }
}