package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class PurchaseQuotation extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchaseQuotation(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchaseQuotation() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Quotations");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Quotations']");
        Thread.sleep(3000);
        super.oldTTransaction();
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMasterWithValidation(common.getData(dataFile,"branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        super.selectMasterWithValidation(common.getData(dataFile,"currency"),"xpath", "//Edit[@Name='Transaction Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        super.selectMasterWithValidation(common.getData(dataFile,"partyCode"),"xpath", "//Edit[@Name='Party Code']");
        common.clickElement("xpath","//Edit[@Name='Party Account *']");
        super.gstSelectionWhenBothRegisteredDealers();
        common.clickElement("xpath","//Edit[@Name='Price List']");
        super.selectMasterWithValidation(common.getData(dataFile,"priceList"),"xpath","//Edit[@Name='Price List']");
        Thread.sleep(2000);
        common.clickElement("xpath","//Edit[@Name='Executive *']");
        super.selectMasterWithValidation(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        common.clickElement("xpath","//Edit[@Name='Product Code Row 0, Not sorted.']");
        Thread.sleep(2000);
        super.selectMaster(common.getData(dataFile,"product"));
        common.clickElement("xpath","//Edit[@Name='Quantity * Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Quantity * Row 0, Not sorted.']", common.getData(dataFile,"quantity"));
        common.clickElement("xpath","//Edit[@Name='MRP Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Quantity * Row 0, Not sorted.']", common.getData(dataFile,"mrp"));
        common.clickElement("xpath","//Edit[@Name='Unit Rate Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Unit Rate Row 0, Not sorted.']", common.getData(dataFile,"unitRate"));
        Thread.sleep(1000);
        super.saveTransaction();
        Thread.sleep(1500);
        super.newTransaction();
        super.closeTransaction("Purchase Quotations");
        Thread.sleep(2000);

    }
}
