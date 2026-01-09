package tradeTesting.inventory.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;

public class StockTransferToNonSaleableStockTypeBinTrade extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public StockTransferToNonSaleableStockTypeBinTrade(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }
    public String stockTransferToNonSaleableStockTypeBinTrade(String tempAPIBodyUpdate, String apiResponse, String outputFile) throws Exception {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Inventory","Inventory","Stock Transfer To Non-Saleable Stock Type Bin");
        long generalInfoStart = System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        EnterData("//Edit[@Name='Location *']",dataFile,"GeneralInformation","Location");
        EnterData("//Edit[@Name='Storage Bin *']",dataFile,"GeneralInformation","StorageBin");
        EnterData("//Edit[@Name='Executive']",dataFile,"GeneralInformation","Executive");
        EnterData("//Edit[@Name='Division']",dataFile,"GeneralInformation","Division");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Stock Transfer To Non-Saleable Stock Type Bin Trade Gen Info:- ", generalInfoEndTime);

        long addProductStart = System.nanoTime();
        addProduct();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("Stock Transfer To Non-Saleable Stock Type Bin Trade Add Products:- ", addProductEnd);

        long otherInfoTabStart = System.nanoTime();
        common.clickElement("xpath","//TabItem[@Name='  OtherInfo  ']");
        otherInfo();
        long otherInfoTabEnd = System.nanoTime() - otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Stock Transfer To Non-Saleable Stock Type Bin Trade Other Info:- ", otherInfoTabEnd);

        //saving and IO generating
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //API
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"OpeningStock");

        long openingStockEnd = System.nanoTime() - start;
        FileUtil.writeTimeLogInMinutes("Stock Transfer To Non-Saleable Stock Type Bin Trade ended at:- ", openingStockEnd);
        deleteTransactionUsingVoucherNumber(newVoucherID);

        return newVoucherID;
    }
    public void addProduct() throws Exception {
        List<String> productCode=readExcelData(dataFile,"Items","ProductCode");
        for (int i = 0; i < productCode.size() ; i++)   {
            addData("xpath","//Edit[@Name='Product Code Row "+i+", Not sorted.']",dataFile,"Items","ProductCode",i);
            if((common.findWebElements("xpath", "//Window[@Name='Product Batch Selection']").size() > 0) ){
                common.clickElement("xpath","//Edit[@Name='Product Batch Row 0, Not sorted.']");
                common.clickElement("xpath","//Button[@Name='Ok']");
                Thread.sleep(1000);
                common.clickElement("xpath","//Edit[@Name='Product Code Row 0, Not sorted.']");

            }
        }
        List<WebElement> productBatch = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product Batch Row ')]");
        List<WebElement> baseUnitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Base Unit Rate * Row ')]");
        List<WebElement> baseQuantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Qty In Base Unit * Row ')]");
        List<WebElement> executives = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");

        for (int i = 0; i < productCode.size(); i++) {
            enterListData(productBatch.get(i), dataFile, "Items", "ProductBatchId", i);
            enterListData(baseUnitRate.get(i),dataFile,"Items","BaseUnitRate",i);
            enterListData(baseQuantity.get(i),dataFile,"Items","QtyInBaseUnit",i);
            enterListData(executives.get(i),dataFile,"Items","Executive",i);
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