package tradeTesting.purchase.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class BatchPurchasePrices extends TransactionsBaseClass {
    WindowsDriver driver,rootDriver;
    Common common;
    String dataFile;

    public BatchPurchasePrices(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void batchPurchasePrices() throws Exception {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Purchase","Prices","Batch Purchase Prices");
        Thread.sleep(3000);
        String oldVoucherID = oldTTransactionID();

        long generalInfoStart = System.nanoTime();

        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        EnterDate("//Edit[@Name='With Effect From *']",dataFile,"GeneralInformation","WithEffectFrom");
        enterPriceList(dataFile, "GeneralInformation", "PriceList");
        EnterData("//Edit[@Name='Price Type *']",dataFile,"GeneralInformation","PriceType");
        EnterData("//Edit[@Name='Executive']",dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Batch Purchase Prices General information End:- ", generalInfoEndTime);

        long addProductStart=System.nanoTime();
        addProducts();
        long addProductEnd=System.nanoTime()-addProductStart;
        FileUtil.writeTimeLogInMinutes("Batch Purchase Prices Add Products:- ",addProductEnd);

        transactionSave();
        String transactionId = newTransactionID(oldVoucherID);
        System.out.println("transactionNumber :"+transactionId);
//        APIClient.validateAPIWithExcel(transactionId,tempAPIBodyUpdate,apiResponse,outputFile,"PurchaseEnquiries");

        long batchPurchasePrices = System.nanoTime() - start;
        FileUtil.writeTimeLogInMinutes("Batch Purchase Prices end at:- ", batchPurchasePrices);

    }

    public void addProducts() throws  IOException {
        List<String> productCode=readExcelData(dataFile,"Items","Products");
        for (int i = 0; i < productCode.size() ; i++)   {
            addData("xpath","//Edit[@Name='Products * Row "+i+", Not sorted.']",dataFile,"Items","Products",i);
        }

        List<WebElement> productBatch = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product Batch * Row')]");
        List<WebElement> purchaseUnitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Purchase Unit Rate Row')]");
        List<WebElement> purchaseRateInBaseUnit = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Purchase Rate In Base Unit Row ')]");

        for (int j = 0; j < productCode.size() ; j++) {
            enterListData(productBatch.get(j), dataFile, "Items", "ProductBatch",j);
            enterListData(purchaseUnitRate.get(j), dataFile, "Items", "PurchaseUnitRate1",j);
            enterListData(purchaseRateInBaseUnit.get(j), dataFile, "Items", "PurchaseRateInBaseUnit",j);
        }
    }


}
