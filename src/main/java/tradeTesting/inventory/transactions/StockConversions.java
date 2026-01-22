package tradeTesting.inventory.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StockConversions extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public StockConversions(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String stockConversion() throws Exception {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Inventory","Inventory","Stock Conversion");
        Thread.sleep(1000);
        long generalInfoStart = System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterLocation(dataFile,"GeneralInformation","Location");
        EnterData("//Edit[@Name='Division']",dataFile,"GeneralInformation","Division");
        enterBatchPolicy(dataFile,"GeneralInformation","BatchPolicy");
        EnterData("//Edit[@Name='Price Type']",dataFile,"GeneralInformation","PriceType");
        enterPriceList(dataFile,"GeneralInformation","PriceList");
        EnterData("//Edit[@Name='Executive']",dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Stock Conversion Gen Info:- ", generalInfoEndTime);

        long addProductStart = System.nanoTime();
        addInputProduct();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("Stock Conversion Inputs:- ", addProductEnd);

        long addOutputsStart = System.nanoTime();
        common.clickElement("xpath","//TabItem[contains(@Name,'  Outputs  ')]");
        addOutputs();
        long addOutputsEnd = System.nanoTime() - addOutputsStart;
        FileUtil.writeTimeLogInMinutes("Stock Conversion Outputs:- ", addOutputsEnd);

        long otherInfoTabStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  OtherInfo  ')]");
        otherInfo();
        long otherInfoTabEnd = System.nanoTime() - otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Stock Conversion Other Info:- ", otherInfoTabEnd);

        long allocationsTabStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  Allocations  ')]");
        addAllocations();
        long allocationsTabEnd = System.nanoTime() - allocationsTabStart;
        FileUtil.writeTimeLogInMinutes("Stock Conversion Allocations:- ", allocationsTabEnd);

        //saving and IO generating
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");

//        API
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"Stock Conversion");
        long stockConversionEnd = System.nanoTime() - start;
        FileUtil.writeTimeLogInMinutes("Stock Conversion End:- ", stockConversionEnd);
        deleteTransactionUsingVoucherNumber(newVoucherID);

        return newVoucherID;
    }

    public void addInputProduct() throws Exception {
        List<String> productCode=readExcelData(dataFile,"Inputs","ProductCode");
        List<String> masterType=readExcelData(dataFile,"Inputs","MasterType");
        for (int i = 0; i < productCode.size() ; i++)   {
//            addData("xpath","//Edit[@Name='Product Row "+i+", Not sorted.']",dataFile,"Inputs","Product",i);
            addData("xpath","//Edit[@Name='Product Code Row "+i+", Not sorted.']",dataFile,"Inputs","ProductCode",i);
            if (i==0){
                Thread.sleep(1000);
                common.clickElement("xpath","//Button[@Name='Ok']");
                common.clickElement("xpath","//CheckBox[@Name='Select Row 1']");
                common.clickElement("xpath","//Button[@Name='Ok']");
            }
            else if(i>=1){
                Thread.sleep(1000);
                common.clickElement("xpath","//CheckBox[@Name='Select Row 1']");
                common.clickElement("xpath","//Button[@Name='Ok']");
            }
        }
        List<WebElement> productBatch = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product Batch Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        List<WebElement> priceType = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Price Type Row ')]");
        common.clickElement("xpath","//Header[@Name='Qty']");
        List<WebElement> unitRate = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Unit Rate Row ')]");
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Qty Row ')]");
        common.clickElement("xpath","//Header[@Name='Qty In Base Unit']");
        List<WebElement> baseUnitRate = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Base Unit Rate Row ')]");
        List<WebElement> quantityInBaseUNitRate = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Qty In Base Unit Row ')]");
        List<WebElement> atCost = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/CheckBox[starts-with(@Name,'At Cost Row ')]");
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> division = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division * Row ')]");
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Inputs']/*/Thumb[@Name='Position']", -500, 0);

        for (int i = 0; i < productCode.size(); i++) {
            enterListData(productBatch.get(i),dataFile,"Inputs","ProductBatchId",i);
            enterListData(storageBin.get(i),dataFile,"Inputs","StorageBin",i);
            enterListData(priceType.get(i),dataFile,"Inputs","PriceType",i);
            enterListData(unitRate.get(i),dataFile,"Inputs","UnitRate",i);
            enterListData(quantity.get(i),dataFile,"Inputs","Qty",i);
            enterListData(baseUnitRate.get(i),dataFile,"Inputs","BaseUnitRate",i);
            enterListData(quantityInBaseUNitRate.get(i),dataFile,"Inputs","QtyInBaseUnit",i);
            clickListData(atCost.get(i));
            enterListData(executive.get(i), dataFile, "Items", "Executive",i);
            enterListData(division.get(i), dataFile, "Items", "Division",i);
            enterListData(Department.get(i),dataFile,"Inputs","Department",i);
            enterListData(Project.get(i),dataFile,"Inputs","Project",i);
            enterListData(ProfitCentre.get(i),dataFile,"Inputs","ProfitCentre",i);
            enterListData(CostCentre.get(i),dataFile,"Inputs","CostCentre",i);
            enterListData(Comments.get(i),dataFile,"Inputs","Comments",i);
            common.sliderHandling("xpath", "//Table[@Name='Inputs']/*/Thumb[@Name='Position']", -450, 0);
        }
    }

    public void addOutputs() throws Exception {
        List<String> productCode=readExcelData(dataFile,"Outputs","Product");
        System.out.println(productCode.size());
        for (int i = 0; i < productCode.size() ; i++)   {
            addData("xpath","//Edit[@Name='Product Code Row "+i+", Not sorted.']",dataFile,"Outputs","Product",i);
        }
        List<WebElement> productBatch = common.findWebElements("xpath", "//Table[@Name='Outputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product Batch Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Outputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        common.clickElement("xpath","//Header[@Name='Qty']");
        List<WebElement> unitRate = common.findWebElements("xpath", "//Table[@Name='Outputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Unit Rate Row ')]");
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Outputs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Qty Row ')]");
        common.clickElement("xpath","//Header[@Name='Qty In Base Unit']");
        List<WebElement> baseUnitRate = common.findWebElements("xpath", "//Table[@Name='Outputs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Base Unit Rate Row ')]");
        List<WebElement> quantityInBaseUNitRate = common.findWebElements("xpath", "//Table[@Name='Outputs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Qty In Base Unit Row ')]");
        List<WebElement> atCost = common.findWebElements("xpath", "//Table[@Name='Outputs']/*[contains(@Name,'Row ')]/CheckBox[starts-with(@Name,'At Cost Row ')]");
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='Outputs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> division = common.findWebElements("xpath", "//Table[@Name='Outputs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division * Row ')]");
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Outputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Outputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Outputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Outputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Outputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        for (int i = 0; i < productCode.size(); i++) {
            enterListData(productBatch.get(i),dataFile,"Outputs","ProductBatchId",i);
            enterListData(storageBin.get(i),dataFile,"Outputs","StorageBin",i);
            enterListData(unitRate.get(i),dataFile,"Outputs","UnitRate",i);
            enterListData(quantity.get(i),dataFile,"Outputs","Qty",i);
            enterListData(baseUnitRate.get(i),dataFile,"Outputs","BaseUnitRate",i);
            enterListData(quantityInBaseUNitRate.get(i),dataFile,"Outputs","QtyInBaseUnit",i);
            clickListData(atCost.get(i));
            enterListData(executive.get(i), dataFile, "Outputs", "Executive",i);
            enterListData(division.get(i), dataFile, "Outputs", "Division",i);
            enterListData(Department.get(i),dataFile,"Outputs","Department",i);
            enterListData(Project.get(i),dataFile,"Outputs","Project",i);
            enterListData(ProfitCentre.get(i),dataFile,"Outputs","ProfitCentre",i);
            enterListData(CostCentre.get(i),dataFile,"Outputs","CostCentre",i);
            enterListData(Comments.get(i),dataFile,"Outputs","Comments",i);
            common.sliderHandling("xpath", "//Table[@Name='Outputs']/*/Thumb[@Name='Position']", -450, 0);
        }

    }

    public void otherInfo() throws InterruptedException, IOException {
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }

    public void addAllocations() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Allocations')]");
        EnterData( "//Edit[@Name='Department']", dataFile, "Allocations", "Department");
        EnterData( "//Edit[@Name='Project']", dataFile, "Allocations", "Project");
        EnterData("//Edit[@Name='Profit Centre']", dataFile, "Allocations", "ProfitCentre");
        EnterData( "//Edit[@Name='Cost Centre']", dataFile, "Allocations", "CostCentre");
    }

}
