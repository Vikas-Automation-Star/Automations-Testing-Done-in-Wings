package tradeTesting.sales.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class BatchSalesPriceTrade extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public BatchSalesPriceTrade(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String batchSalesPriceTrade() throws InterruptedException, IOException, ParseException {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Sales", "Prices", "Batch Sales Prices");
        Thread.sleep(1000);
        long genInfoStart = System.nanoTime();
        String oldVoucherID = oldTTransactionID();
        enterVoucherType(dataFile, "GeneralInformation", "VoucherType");
        EnterDate("//Edit[@Name='Date *']", dataFile, "GeneralInformation", "Date");
        enterBranch(dataFile, "GeneralInformation", "Branch");
        EnterDate("//Edit[@Name='With Effect From *']",dataFile,"GeneralInformation", "WithEffectFrom");
        enterPriceList(dataFile, "GeneralInformation", "PriceList");
        EnterData("//Edit[@Name='Price Type *']",dataFile,"GeneralInformation","PriceType");
        EnterData("//Edit[@Name='Executive']",dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile, "GeneralInformation", "Remarks");

        long generalInfoEndTime = System.nanoTime() - genInfoStart;
        FileUtil.writeTimeLogInMinutes("Batch Sales Price Gen Info End:- ", generalInfoEndTime);

        long addProductstart=System.nanoTime();
        addProduct();
        long addProductsEnd=System.nanoTime() - addProductstart;
        FileUtil.writeTimeLogInMinutes("Batch Sales Price Items:- ", addProductsEnd);
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long batchSalesPriceEnd = System.nanoTime() - start ;
        FileUtil.writeTimeLogInMinutes("Batch Sales Price ended at:- ", batchSalesPriceEnd);
        //API
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"proformaSalesInvoice");
        deleteTransactionUsingVoucherNumber(newVoucherID);

        return newVoucherID;
    }

    public void addProduct() throws IOException {
        List<String> productCode = readExcelData(dataFile, "Items", "Product");
        System.out.println("productCodes :" + productCode.size());
        for (int i = 0; i < productCode.size(); i++) {
            addData("xpath", "//Edit[@Name='Product * Row "+i+", Not sorted.']", dataFile, "Items", "Product", i);
        }
        List<WebElement> productBatch = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product Batch * Row ')]");
        List<WebElement> salesUnitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Sales Unit Rate Row ')]");
        List<WebElement> saleSUnitRateInBaseUnit = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Sales Rate In Base Unit Row ')]");

        for (int i = 0; i < productCode.size(); i++) {
            enterListData(productBatch.get(i),dataFile,"Items","ProductBatch",i);
            enterListData(salesUnitRate.get(i),dataFile,"Items","SalesUnitRate1",i);
            enterListData(saleSUnitRateInBaseUnit.get(i), dataFile, "Items", "SalesRateInBaseUnit", i);
        }
    }
}
