package tradeTesting.inventory.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;

public class StockReplacementTrade extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public StockReplacementTrade(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }
    public String stockReplacementTrade(String tempAPIBodyUpdate, String apiResponse, String outputFile) throws Exception {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Inventory","Inventory","Stock Replacement");
        long generalInfoStart = System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        EnterData("//Edit[@Name='Route']",dataFile,"GeneralInformation","Route");
        EnterData("//Edit[@Name='Location *']",dataFile,"GeneralInformation","Location");
        EnterData("//Edit[@Name='Stock Type *']",dataFile,"GeneralInformation","StockType");
        EnterData("//Edit[@Name='Storage Bin *']",dataFile,"GeneralInformation","StorageBin");
        enterPriceList(dataFile,"GeneralInformation","PriceList");
        EnterData("//Edit[@Name='Price Type']",dataFile,"GeneralInformation","PriceType");
        EnterData("//Edit[@Name='Division']",dataFile,"GeneralInformation","Division");
        EnterData("//Edit[@Name='Executive']",dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Stock Replacement Gen Info:- ", generalInfoEndTime);

        long addProductStart = System.nanoTime();
        addProduct();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("Stock Replacement Add Products:- ", addProductEnd);

        long otherInfoTabStart = System.nanoTime();
        common.clickElement("xpath","//TabItem[@Name='  OtherInfo  ']");
        otherInfo();
        long otherInfoTabEnd = System.nanoTime() - otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Stock Replacement Other Info:- ", otherInfoTabEnd);

        //saving and IO generating
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //API
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"OpeningStock");

        long openingStockEnd = System.nanoTime() - start;
        FileUtil.writeTimeLogInMinutes("Stock Replacement ended at:- ", openingStockEnd);
        deleteTransactionUsingVoucherNumber(newVoucherID);

        return newVoucherID;
    }
    public void addProduct() throws Exception {
        List<String> productCode=readExcelData(dataFile,"Items","ProductCode");
        for (int i = 0; i < productCode.size() ; i++)   {
            addData("xpath","//Edit[@Name='Product Code Row "+i+", Not sorted.']",dataFile,"Items","ProductCode",i);
        }
        List<WebElement> productBatch = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product Batch Row ')]");
        List<WebElement> priceType = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Price Type Row ')]");
        List<WebElement> baseUnitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Base Unit Rate * Row ')]");
        List<WebElement> receivedQtyInBaseUnit = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Received Qty In Base Unit * Row ')]");
        List<WebElement> mrp = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'MRP Row ')]");
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Executive Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");

        for (int i = 0; i < productCode.size(); i++) {
            enterListData(productBatch.get(i), dataFile, "Items", "ProductBatchId", i);
            enterListData(priceType.get(i), dataFile, "Items", "PriceType", i);
            enterListData(baseUnitRate.get(i),dataFile,"Items","BaseUnitRate",i);
            enterListData(receivedQtyInBaseUnit.get(i),dataFile,"Items","ReplacedQtyInBaseUnit",i);
            enterListData(mrp.get(i),dataFile,"Items","MRP",i);
//            enterListData(executive.get(i),dataFile,"Items","ToStockType",i);
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