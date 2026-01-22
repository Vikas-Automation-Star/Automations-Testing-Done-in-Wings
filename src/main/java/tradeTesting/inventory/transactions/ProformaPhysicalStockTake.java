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

public class ProformaPhysicalStockTake extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public ProformaPhysicalStockTake(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String proformaPhysicalStockTake() throws Exception {
        long start=System.nanoTime();
        navigateToMastersWhen3Steps("Inventory", "Inventory","Physical Stock-Proforma");
        long generalInfoStart = System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        EnterData("//Edit[@Name='Division']",dataFile,"GeneralInformation","Division");
        enterLocation(dataFile,"GeneralInformation","Location");
        enterPriceList(dataFile,"GeneralInformation","PriceList");
        EnterData("//Edit[@Name='Price Type']",dataFile,"GeneralInformation","PriceType");
        EnterData("//Edit[@Name='Storage Bin *']",dataFile,"GeneralInformation","StorageBin");
        EnterData("//Edit[@Name='Master Type']",dataFile,"GeneralInformation","MasterType");
        WebElement stockType = common.findWebElement("xpath", "//Edit[@Name='Node']");
        stockType.clear();
        stockType.sendKeys("Testing", Keys.ENTER);
        common.clickElement("xpath","//Button[@Name='Get Stocks']");
        common.clickElement("xpath","//CheckBox[@Name='Select Row 0']");
        common.clickElement("xpath","//Button[@Name='OK']");
        EnterData("//Edit[@Name='Executive']",dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Physical stock take Gen Info:- ", generalInfoEndTime);

        long addProductStart = System.nanoTime();
        addStockDetails();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("Physical Stock Take Add Products:- ", addProductEnd);

        long otherInfoTabStart = System.nanoTime();
        otherInfo();
        long otherInfoTabEnd = System.nanoTime() - otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Physical Stock Take Other Info:- ", otherInfoTabEnd);

        //saving and IO generating
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");

        //api
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"physicalStockTake");

        long physicalStockTakeEnd = System.nanoTime() - start;
        FileUtil.writeTimeLogInMinutes("Physical Stock Take Ended at:- ", physicalStockTakeEnd);
        deleteTransactionUsingVoucherNumber(newVoucherID);
        return newVoucherID;
    }

    public void addStockDetails() throws Exception {
        List<WebElement>  products = common.findWebElements("xpath", "//Table[@Name='Invoice']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product * Row ')]");
        List<WebElement> productBatch = common.findWebElements("xpath", "//Table[@Name='Invoice']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product Batch Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Invoice']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        List<WebElement> priceType = common.findWebElements("xpath", "//Table[@Name='Invoice']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Price Type Row ')]");
        List<WebElement> unitRate = common.findWebElements("xpath", "//Table[@Name='Invoice']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Unit Rate Row ')]");
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Invoice']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Qty Row ')]");
        List<WebElement> baseUnitRate = common.findWebElements("xpath", "//Table[@Name='Invoice']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Base Unit Rate Row ')]");
        List<WebElement> quantityInBaseUNitRate = common.findWebElements("xpath", "//Table[@Name='Invoice']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Qty In Base Unit Row ')]");
        List<WebElement> rate = common.findWebElements("xpath", "//Table[@Name='Invoice']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Rate Row ')]");
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Invoice']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Invoice']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Invoice']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Invoice']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Invoice']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");

        for (int i = 0; i < products.size(); i++) {
            enterListData(products.get(i),dataFile,"Invoice","Products",i);
            enterListData(productBatch.get(i),dataFile,"Invoice","ProductBatchId",i);
            enterListData(storageBin.get(i),dataFile,"Invoice","StorageBin",i);
            enterListData(priceType.get(i),dataFile,"Invoice","PriceType",i);
            enterListData(unitRate.get(i),dataFile,"Invoice","UnitRate",i);
            enterListData(quantity.get(i),dataFile,"Invoice","Qty",i);
            enterListData(baseUnitRate.get(i),dataFile,"Invoice","BaseUnitRate",i);
            enterListData(quantityInBaseUNitRate.get(i),dataFile,"Inputs","QtyInBaseUnit",i);
            enterListData(rate.get(i), dataFile, "Invoice", "Rate",i);
            enterListData(Department.get(i),dataFile,"Invoice","Department",i);
            enterListData(Project.get(i),dataFile,"Invoice","Project",i);
            enterListData(ProfitCentre.get(i),dataFile,"Invoice","ProfitCentre",i);
            enterListData(CostCentre.get(i),dataFile,"Invoice","CostCentre",i);
            enterListData(Comments.get(i),dataFile,"Invoice","Comments",i);
        }
    }

    public void otherInfo() throws InterruptedException, IOException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'  OtherInfo  ')]");
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }

}
