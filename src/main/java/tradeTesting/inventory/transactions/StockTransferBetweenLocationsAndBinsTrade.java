package tradeTesting.inventory.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;

public class StockTransferBetweenLocationsAndBinsTrade extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean isFromStoragebinClicked =false,isToStorageBinClicked=false;

    public StockTransferBetweenLocationsAndBinsTrade(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }
    public String stockTransferBetweenLocationsAndBinsTrade(String tempAPIBodyUpdate, String apiResponse, String outputFile) throws Exception {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Inventory","Inventory","Stock Transfer Between Locations And Bins");
        long generalInfoStart = System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile, "GeneralInformation", "TransactionCurrency");
        EnterData("//Edit[@Name='Stock Type *']",dataFile,"GeneralInformation","StockType");
        EnterData("//Edit[@Name='From Location *']",dataFile,"GeneralInformation","FromLocation");
        EnterData("//Edit[@Name='From Storage Bin *']",dataFile,"GeneralInformation","FromStorageBin");
        EnterData("//Edit[@Name='To Location *']",dataFile,"GeneralInformation","ToLocation");
        EnterData("//Edit[@Name='To Storage Bin *']",dataFile,"GeneralInformation","ToStorageBin");
        EnterData("//Edit[@Name='Executive']",dataFile,"GeneralInformation","Executive");
        EnterData("//Edit[@Name='Division']",dataFile,"GeneralInformation","Division");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Stock Transfer Between Locations And Bins Trade Gen Info:- ", generalInfoEndTime);

        long addProductStart = System.nanoTime();
        addProduct();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("Stock Transfer Between Locations And Bins Trade Add Products:- ", addProductEnd);

        long otherInfoTabStart = System.nanoTime();
        common.clickElement("xpath","//TabItem[@Name='  OtherInfo  ']");
        otherInfo();
        long otherInfoTabEnd = System.nanoTime() - otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Stock Transfer Between Locations And Bins Trade Other Info:- ", otherInfoTabEnd);

        //saving and IO generating
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //API
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"OpeningStock");

        long openingStockEnd = System.nanoTime() - start;
        FileUtil.writeTimeLogInMinutes("Stock Transfer Between Locations And Bins Trade ended at:- ", openingStockEnd);
        deleteTransactionUsingVoucherNumber(newVoucherID);

        return newVoucherID;
    }
    public void addProduct() throws Exception {
        List<String> productCode=readExcelData(dataFile,"Items","ProductCode");
        for (int i = 0; i < productCode.size() ; i++)   {
            addData("xpath","//Edit[@Name='Product Code Row "+i+", Not sorted.']",dataFile,"Items","ProductCode",i);
        }
        List<WebElement> productBatch = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product Batch Row ')]");

        if(!isFromStoragebinClicked && !isToStorageBinClicked) {
            common.clickElement("xpath", "//Header[@Name='From Storage Bin *']");
            common.clickElement("xpath","//Header[@Name='To Storage Bin *']");
            isToStorageBinClicked =true;
            isFromStoragebinClicked=true;
        }

        List<WebElement> fromStockType = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'From Stock Type * Row ')]");
        List<WebElement> fromLocation = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'From Location * Row ')]");
        List<WebElement> fromStorageBin = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'From Storage Bin * Row ')]");
        List<WebElement> toStockType = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'To Stock Type * Row ')]");
        List<WebElement> toLocation = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'To Location * Row ')]");
        List<WebElement> toStorageBin = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'To Storage Bin * Row ')]");
        List<WebElement> baseQuantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Qty In Base Unit * Row ')]");
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");

        for (int i = 0; i < productCode.size(); i++) {
            enterListData(productBatch.get(i), dataFile, "Items", "ProductBatchId", i);
            enterListData(fromStockType.get(i),dataFile,"Items","FromStockType",i);
            enterListData(fromLocation.get(i),dataFile,"Items","FromLocation",i);
            enterListData(fromStorageBin.get(i),dataFile,"Items","FromStorageBin",i);
            enterListData(toStockType.get(i),dataFile,"Items","ToStockType",i);
            enterListData(toLocation.get(i),dataFile,"Items","ToLocation",i);
            enterListData(toStorageBin.get(i),dataFile,"Items","ToStorageBin",i);
            enterListData(baseQuantity.get(i),dataFile,"Items","QtyInBaseUnit",i);
            enterListData(Department.get(i),dataFile,"Items","Department",i);
            enterListData(ProfitCentre.get(i),dataFile,"Items","ProfitCentre",i);
            enterListData(Project.get(i),dataFile,"Items","Project",i);
            enterListData(CostCentre.get(i),dataFile,"Items","CostCentre",i);
            enterListData(Comments.get(i),dataFile,"Items","Comments",i);
        }
    }
    public void otherInfo() throws InterruptedException, IOException {
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(5000);
//    common.clickElement("xpath","//Window/Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }

}