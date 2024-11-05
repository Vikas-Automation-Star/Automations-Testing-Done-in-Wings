package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class MaterialReceipt extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public MaterialReceipt(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void materialReceipt() throws InterruptedException, IOException, ParseException {

        common.clickElement("name", "Purchase");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Material Receipts']");
        Thread.sleep(3000);
        super.oldTTransaction();
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMasterWithValidation(common.getData(dataFile,"branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        super.selectMasterWithValidation(common.getData(dataFile,"currency"),"xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        super.selectMasterWithValidation(common.getData(dataFile,"partyCode"),"xpath", "//Edit[@Name='Party Code']");
        Thread.sleep(1000);
        super.gstSelectionWhenBothRegisteredDealers();
        Thread.sleep(2000);
        common.clickElement("xpath","//Edit[@Name='Consignor']");
        super.selectMasterWithValidation(common.getData(dataFile,"consigner"),"xpath", "//Edit[@Name='Consignor']");
        common.clickElement("xpath","//Edit[@Name='Batch Policy']");
        super.selectMasterWithValidation(common.getData(dataFile,"batchPolicy"),"xpath", "//Edit[@Name='Batch Policy']");
        common.clickElement("xpath","//Edit[@Name='Executive *']");
        super.selectMasterWithValidation(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
        Thread.sleep(500);
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        common.clickElement("xpath","//Edit[@Name='Product Code Row 0, Not sorted.']");
        super.selectMasterWithValidation(common.getData(dataFile,"Product"),"xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");

        common.clickElement("xpath","//Edit[@Name='Quantity Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Quantity Row 0, Not sorted.']", common.getData(dataFile,"quantity"));
        super.sliderHandle();
        Thread.sleep(2000);
        common.clickElement("xpath","//Edit[@Name='MRP Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='MRP Row 0, Not sorted.']", common.getData(dataFile,"mrp"));
        common.clickElement("xpath","//Edit[@Name='Unit Rate Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Unit Rate Row 0, Not sorted.']", common.getData(dataFile,"unitRate"));
        super.sliderHandle();
        common.clickElement("xpath","//Edit[@Name='GST Product Category Row 0, Not sorted.']");
        Thread.sleep(1000);
        common.clickElement("xpath","//TabItem[@Name='  Ctrl-F8 Summary  ']");
        super.saveTransaction();
        Thread.sleep(1500);
        super.newTransaction();
        super.closeTransaction("Material Receipts");
        Thread.sleep(2000);
    }
}
