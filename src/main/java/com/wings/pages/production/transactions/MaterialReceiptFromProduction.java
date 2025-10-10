package com.wings.pages.production.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;


public class MaterialReceiptFromProduction extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public MaterialReceiptFromProduction(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void materialReceiptFromProduction(String ordersNum,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        navigateToMastersWhen3Steps("Production","Standard","Material Receipts from Production");
        Thread.sleep(3000);
        String oldVoucherID =oldTTransactionID();

        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        EnterData("//Edit[@Name='Finished Product Code']",dataFile,"GeneralInformation","FinishedProductCode");
        Thread.sleep(1000);
        selectPendingsSalesOrder(ordersNum,"20250401");
        enterBatchPolicy(dataFile,"GeneralInformation","BatchPolicy");
        common.clickElement("xpath","//Edit[@Name='Executive *']");
        List<WebElement> editables=common.findWebElements("xpath","//Window[@Name='Select Existing Or Create New Batch']//Pane/Edit");
        System.out.println(editables.size()+"PopUpEdits");
        editables.get(3).sendKeys("test"+common.getRandom());
        editables.get(4).sendKeys("test"+common.getRandom());
        common.clickElement("xpath","//CheckBox[@Name='Check Availability']");
        common.clickElement("xpath","//Button[@Name='Create Batch']");
        Thread.sleep(1500);
        EnterData("//Edit[@Name='Executive *']",dataFile,"GeneralInformation","Executive");
        EnterData("//Edit[@Name='Remarks']",dataFile,"GeneralInformation","Remarks");

        items();
        byProducts();
        overHeads();
        otherInfo();

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"MaterialReceiptsFromProduction");
        deleteTransactionUsingVoucherNumber(newVoucherID);
    }

    public void items() throws IOException, InterruptedException {
        List<WebElement> productBatch = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product Batch * Row')]");
        List<WebElement> manufactureDate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Mfg Date Row')]");
        List<WebElement> expiryDates = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Expiry Date Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        List<WebElement> uom = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 700, 0);
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Quantity * Row ')]");
        List<WebElement> unitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Unit Rate Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1000, 0);
        for (int i = 0; i < productBatch.size()-1; i++) {
            enterListData(productBatch.get(i),dataFile,"Items","ProductBatch",i);
            enterListDate(manufactureDate.get(i),dataFile,"Items","MfgDate",i);
            enterListDate(expiryDates.get(i),dataFile,"Items","ExpiryDate",i);
            enterListData(storageBin.get(i),dataFile,"Items","StorageBin",i);
            enterListData(uom.get(i),dataFile,"Items","UOM",i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 800, 0);
            enterListData(quantity.get(i),dataFile,"Items","Quantity",i);;
            enterListData(unitRate.get(i),dataFile,"Items","UnitRate",i);
            enterListData(Department.get(i),dataFile,"Items","Department",i);
            enterListData(Project.get(i),dataFile,"Items","Project",i);
            enterListData(ProfitCentre.get(i),dataFile,"Items","ProfitCentre",i);
            enterListData(CostCentre.get(i),dataFile,"Items","CostCentre",i);
            enterListData(Comments.get(i),dataFile,"Items","Comments",i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1000, 0);
        }
    }
    public void byProducts() throws InterruptedException, IOException {
        Thread.sleep(1000);
        common.clickElement("xpath","//TabItem[contains(@Name,'By Products  ')]");
        List<String> productCode=readExcelData(dataFile,"ByProducts","ProductCode");
        System.out.println("OverHeads Size :"+productCode.size());
        for (int i = 0; i < productCode.size() ; i++) {
            addData("xpath","//Edit[@Name='Product Code Row "+i+", Not sorted.']",dataFile,"ByProducts","ProductCode",i);
        }
        List<WebElement> uom = common.findWebElements("xpath", "//Table[@Name='ByProducts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='ByProducts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='ByProducts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Quantity * Row ')]");
        List<WebElement> unitRate = common.findWebElements("xpath", "//Table[@Name='ByProducts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Unit Rate Row ')]");
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='ByProducts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='ByProducts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='ByProducts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='ByProducts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='ByProducts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        for (int i = 0; i < productCode.size(); i++) {
            enterListData(uom.get(i),dataFile,"ByProducts","UOM",i);
            enterListData(storageBin.get(i),dataFile,"ByProducts","StorageBin",i);
            enterListData(quantity.get(i),dataFile,"ByProducts","Quantity",i);
            Thread.sleep(1000);
            enterListData(unitRate.get(i),dataFile,"ByProducts","UnitRate",i);
            enterListData(Department.get(i),dataFile,"ByProducts","Department",i);
            enterListData(Project.get(i),dataFile,"ByProducts","Project",i);
            enterListData(ProfitCentre.get(i),dataFile,"ByProducts","ProfitCentre",i);
            enterListData(CostCentre.get(i),dataFile,"ByProducts","CostCentre",i);
            enterListData(Comments.get(i),dataFile,"ByProducts","Comments",i);
        }
    }
    public void overHeads() throws InterruptedException, IOException {
        Thread.sleep(1000);
        common.clickElement("xpath","//TabItem[contains(@Name,'Over Heads  ')]");
        List<String> productCode=readExcelData(dataFile,"OverHeads","OverHeadTypeCode");
        System.out.println("OverHeads Size :"+productCode.size());
        for (int i = 0; i < productCode.size() ; i++) {
            addData("xpath","//Edit[@Name='Over Head Type Code Row "+i+", Not sorted.']",dataFile,"OverHeads","OverHeadTypeCode",i);
        }
        List<WebElement> amount = common.findWebElements("xpath", "//Table[@Name='OverHeads']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Amount * Row ')]");

        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='OverHeads']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='OverHeads']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='OverHeads']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='OverHeads']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='OverHeads']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        for (int i = 0; i < productCode.size(); i++) {
            enterListData(amount.get(i),dataFile,"OverHeads","Amount",i);
            enterListData(Department.get(i),dataFile,"OverHeads","Department",i);
            enterListData(Project.get(i),dataFile,"OverHeads","Project",i);
            enterListData(ProfitCentre.get(i),dataFile,"OverHeads","ProfitCentre",i);
            enterListData(CostCentre.get(i),dataFile,"OverHeads","CostCentre",i);
            enterListData(Comments.get(i),dataFile,"OverHeads","Comments",i);

        }
    }
    public void otherInfo() throws InterruptedException, IOException {
        navigateToOtherInfoTab();
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(5000);
        common.clickElement("xpath","//Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }

}
