package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;


public class PurchaseOrdersAgainstQuotation extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchaseOrdersAgainstQuotation(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchaseOrdersAgainstQuotation() throws InterruptedException, IOException, ParseException {

        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Orders against Quotations']");
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
        Thread.sleep(1000);
        super.gstSelectionWhenBothRegisteredDealers();
        Thread.sleep(2000);
        common.clickElement("xpath","//CheckBox[@Name='Select Row 1']");
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.clickElement("xpath","//Edit[@Name='Executive *']");
        super.selectMasterWithValidation(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
        Thread.sleep(500);
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        common.clickElement("xpath","//Edit[@Name='Quantity * Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Quantity * Row 0, Not sorted.']", common.getData(dataFile,"quantity"));
        common.clickElement("xpath","//Edit[@Name='MRP Row 0, Not sorted.']");
        super.sliderHandle();
        super.saveTransaction();
        Thread.sleep(1500);
        super.newTransaction();
        super.closeTransaction("Purchase Orders against Quotations");
        Thread.sleep(2000);

    }
}
