package com.wings.pages.production.transactions;

import com.wings.pages.Transaction;
import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class MaterialIssuesToProduction extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public MaterialIssuesToProduction(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }
    public String materialIssuesToProduction(String productionOrderVoucher,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        navigateToMastersWhen3Steps("Production","Standard","Material Issues to Production");
        Thread.sleep(2000);
        String oldVoucherID =oldTTransactionID();

        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        EnterData("//Edit[@Name='Finished Product Code']",dataFile,"GeneralInformation","FinishedProduct");
        Thread.sleep(1500);
        selectPendingsSalesOrder(productionOrderVoucher,"20250401");
        EnterData("//Edit[@Name='Executive *']",dataFile,"GeneralInformation","Executive");
        EnterData("//Edit[@Name='Department']",dataFile,"GeneralInformation","Department");
        EnterData("//Edit[@Name='Remarks']",dataFile,"GeneralInformation","Remarks");

        items();
        itemsNonPlannedIssues();
        overHeads();
        otherInfo();


        transactionSave();
        Thread.sleep(1000);
        String newVoucherID =newTransactionID(oldVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"MaterialIssuesToProduction");
        return newVoucherID;
    }
    public void items() throws IOException, InterruptedException {
        List<String> masterType=readExcelData(dataFile,"Items","MasterType");
        List<WebElement> uom = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 800, 0);
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Quantity * Row ')]");
        List<WebElement> costPerUnit = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Apply Cost Per Unit Row ')]");
        List<WebElement> unitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Unit Rate Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1000, 0);
        for (int i = 0; i < storageBin.size(); i++) {
            enterListData(uom.get(i),dataFile,"Items","UOM",i);
            enterListData(storageBin.get(i),dataFile,"Items","StorageBin",i);
            if (masterType.get(i).equals("Products")){
                enterListData(quantity.get(i),dataFile,"Items","Quantity",i);
            } else if (masterType.get(i).equals("Products - MultiBatch")) {
                common.clickElement("xpath", "//Button[@Name='Stock Details Row "+i+"']");
                Thread.sleep(500);
                EnterData("//Table[@Name='Batch Details']/*[@Name='Data Panel']/*[@Name='Row 1']/*[@Name='Quantity row 1']",dataFile,"Items","Quantity",i);
//                EnterData("//Table[@Name='Batch Details']/*[@Name='Data Panel']/*[@Name='Row 1']/*[@Name='Free Qty row 1']",dataFile,"Items","FreeQuantity",i);
                Thread.sleep(1000);
                common.clickElement("xpath", "//Button[@Name='OK']");
            }else if (masterType.get(i).equals("Products - Batches and Serial No")){
                Thread.sleep(1000);
                common.clickElement("xpath", "//Button[@Name='Stock Details Row "+i+"']");
                Thread.sleep(1000);
                List<WebElement> editfields=common.findWebElements("xpath","//Table[@Name='Serial Numbers List']/*[@Name='Data Panel']/*[contains(@Name,'Row')]/*[contains(@Name,'Select row')]");
                System.out.println("Serial number edit fields :"+editfields.size());
                editfields.get(0).click();
//                editfields.get(1).click();
//                editfields.get(2).click();
//                editfields.get(3).click();
//                editfields.get(4).click();
                common.clickElement("xpath", "//Button[@Name='OK']");
//                Thread.sleep(2000);
//                List<WebElement> freeQuantity=common.findWebElements("xpath","//Table[@Name='Selected Serial Numbers']/*[@Name='Data Panel']/*[contains(@Name,'Row')]/*[contains(@Name,'FreeQuantity row ')]");
//                System.out.println("free  Size :"+freeQuantity.size());
//                freeQuantity.get(1).click();
            }else {
                Assert.fail("No product present");
            }
//            enterListData(quantity.get(i),dataFile,"Items","Quantity",i);
            Thread.sleep(1000);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 700, 0);
            clickListData(costPerUnit.get(i));
            enterListData(unitRate.get(i),dataFile,"Items","UnitRate",i);
            enterListData(Department.get(i),dataFile,"Items","Department",i);
            enterListData(Project.get(i),dataFile,"Items","Project",i);
            enterListData(ProfitCentre.get(i),dataFile,"Items","ProfitCentre",i);
            enterListData(CostCentre.get(i),dataFile,"Items","CostCentre",i);
            enterListData(Comments.get(i),dataFile,"Items","Comments",i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1000, 0);
        }
    }

    public void itemsNonPlannedIssues() throws IOException, InterruptedException {
        Thread.sleep(1000);
        List<String> masterType=readExcelData(dataFile,"ItemsNonPlannedIssues","MasterType");
        common.clickElement("xpath","//TabItem[contains(@Name,'Items Non Planned Issues ')]");
        List<String> productCode=readExcelData(dataFile,"ItemsNonPlannedIssues","ProductCode");
        System.out.println("productCodes :"+productCode.size());
        for (int i = 0; i < productCode.size() ; i++) {
            addData("xpath","//Edit[@Name='Product Code Row "+i+", Not sorted.']",dataFile,"ItemsNonPlannedIssues","ProductCode",i);
        }
        List<WebElement> ProductCode = common.findWebElements("xpath", "//Table[@Name='ItemsNonPlannedIssues']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product Code Row ')]");
        List<WebElement> uom = common.findWebElements("xpath", "//Table[@Name='ItemsNonPlannedIssues']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='ItemsNonPlannedIssues']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='ItemsNonPlannedIssues']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Quantity * Row ')]");
        List<WebElement> costPerUnit = common.findWebElements("xpath", "//Table[@Name='ItemsNonPlannedIssues']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Apply Cost Per Unit Row ')]");
        List<WebElement> unitRate = common.findWebElements("xpath", "//Table[@Name='ItemsNonPlannedIssues']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Unit Rate Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='ItemsNonPlannedIssues']/*/Thumb[@Name='Position']", 700, 0);
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='ItemsNonPlannedIssues']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Executive Row ')]");
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='ItemsNonPlannedIssues']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='ItemsNonPlannedIssues']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='ItemsNonPlannedIssues']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='ItemsNonPlannedIssues']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='ItemsNonPlannedIssues']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        for (int i = 0; i < ProductCode.size(); i++) {
            enterListData(ProductCode.get(i),dataFile,"ItemsNonPlannedIssues","ProductCode",i);
            enterListData(uom.get(i),dataFile,"ItemsNonPlannedIssues","UOM",i);
            enterListData(storageBin.get(i),dataFile,"ItemsNonPlannedIssues","StorageBin",i);
            if (masterType.get(i).equals("Products")){
                enterListData(quantity.get(i),dataFile,"ItemsNonPlannedIssues","Quantity",i);
            } else if (masterType.get(i).equals("Products - MultiBatch")) {
                common.clickElement("xpath", "//Button[@Name='Stock Details Row "+i+"']");
                Thread.sleep(1000);
                EnterData("//Table[@Name='Batch Details']/*[@Name='Data Panel']/*[@Name='Row 1']/*[@Name='Quantity row 1']",dataFile,"ItemsNonPlannedIssues","Quantity",i);
//                EnterData("//Table[@Name='Batch Details']/*[@Name='Data Panel']/*[@Name='Row 1']/*[@Name='Free Qty row 1']",dataFile,"Items","FreeQuantity",i);
                Thread.sleep(1000);
                common.clickElement("xpath", "//Button[@Name='OK']");
            }else if (masterType.get(i).equals("Products - Batches and Serial No")){
                Thread.sleep(1000);
                common.clickElement("xpath", "//Button[@Name='Stock Details Row "+i+"']");
                Thread.sleep(1000);
                List<WebElement> editfields=common.findWebElements("xpath","//Table[@Name='Serial Numbers List']/*[@Name='Data Panel']/*[contains(@Name,'Row')]/*[contains(@Name,'Select row')]");
                System.out.println("Serial number edit fields :"+editfields.size());
                editfields.get(0).click();
//                editfields.get(1).click();
//                editfields.get(2).click();
//                editfields.get(3).click();
//                editfields.get(4).click();
                common.clickElement("xpath", "//Button[@Name='OK']");
            }else {
                Assert.fail("No product present");
            }
            Thread.sleep(500);
            clickListData(costPerUnit.get(i));
            enterListData(unitRate.get(i),dataFile,"ItemsNonPlannedIssues","UnitRate",i);
            enterListData(executive.get(i),dataFile,"ItemsNonPlannedIssues","Executive",i);
            enterListData(Department.get(i),dataFile,"ItemsNonPlannedIssues","Department",i);
            enterListData(Project.get(i),dataFile,"ItemsNonPlannedIssues","Project",i);
            enterListData(ProfitCentre.get(i),dataFile,"ItemsNonPlannedIssues","ProfitCentre",i);
            enterListData(CostCentre.get(i),dataFile,"ItemsNonPlannedIssues","CostCentre",i);
            enterListData(Comments.get(i),dataFile,"ItemsNonPlannedIssues","Comments",i);
            if (i== 4) break;
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
