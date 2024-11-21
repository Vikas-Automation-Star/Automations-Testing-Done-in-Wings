package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
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

        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Vouchers']");
        Thread.sleep(3000);
        super.oldTTransaction();
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMasterWithValidation(common.getData(dataFile,"branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        super.selectMasterWithValidation(common.getData(dataFile,"currency"),"xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Cash/Party Code']");
        super.selectMasterWithValidation(common.getData(dataFile,"partyCode"),"xpath", "//Edit[@Name='Cash/Party Code']" );
        super.gstSelectionWhenBothRegisteredDealers();
        Thread.sleep(1500);
        common.clickElement("xpath", "//Edit[@Name='Consignor']");
        super.selectMasterWithValidation(common.getData(dataFile,"Consignor"),"xpath", "//Edit[@Name='Consignor']" );
        common.clickElement("xpath","//Edit[@Name='Purchase A/c Code']");
        super.selectMasterWithValidation(common.getData(dataFile,"PurchaseA/cCode"),"xpath", "//Edit[@Name='Purchase A/c Code']" );
        common.clickElement("xpath","//Edit[@Name='Purchase Account']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Edit[@Name='Supplier Bill No *']");
        common.inputText("xpath","//Edit[@Name='Supplier Bill No *']", common.getData(dataFile,"billNo")+common.getRandom());
        common.clickElement("xpath","//Edit[@Name='Supplier Bill Date *']");
        common.inputText("xpath","//Edit[@Name='Supplier Bill Date *']", common.getData(dataFile,"billDate"));
        Thread.sleep(1000);
        common.clickElement("xpath","//Edit[@Name='Batch Policy']");
        super.selectMasterWithValidation(common.getData(dataFile,"batchPolicy"),"xpath", "//Edit[@Name='Batch Policy']" );
//        common.clickElement("xpath","//CheckBox[@Name='Apply TCS']");
//        Thread.sleep(1500);
//        common.clickElement("xpath","//Edit[@Name='TCS Trans Nature']");
//        super.selectMasterWithValidation(common.getData(dataFile,"tcsTranNature"),"xpath", "//Edit[@Name='TCS Trans Nature']" );
//        common.clickElement("xpath","//CheckBox[@Name='Deduct TDS']");
//        Thread.sleep(1500);
//        common.clickElement("xpath","//Edit[@Name='TDS Trans Nature']");
//        super.selectMasterWithValidation(common.getData(dataFile,"tdsTranNature"),"xpath", "//Edit[@Name='TDS Trans Nature']" );
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        super.selectMasterWithValidation(common.getData(dataFile,"priceList"), "xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        super.selectMasterWithValidation(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']" );
        Thread.sleep(1000);
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        common.clickElement("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        super.selectAndValidateDataNew(common.getData(dataFile,"productCode"), "xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Edit[@Name='Quantity Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Quantity Row 0, Not sorted.']", common.getData(dataFile,"quantity"));
        super.sliderHandle();
        Thread.sleep(500);
        common.clickElement("xpath","//Edit[@Name='MRP Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='MRP Row 0, Not sorted.']",common.getData(dataFile,"mrpRate"));
        common.clickElement("xpath","//Edit[@Name='Unit Rate Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Unit Rate Row 0, Not sorted.']", common.getData(dataFile,"unitRate"));
        common.clickElement("xpath","//Edit[@Name='Editable Gross Amount Row 0, Not sorted.']");
        super.sliderHandle();
//        super.saveTransaction();
//        Thread.sleep(1500);
//        super.newTransaction();
//        super.closeTransaction("Purchase Vouchers");
//        Thread.sleep(3000);
    }
}