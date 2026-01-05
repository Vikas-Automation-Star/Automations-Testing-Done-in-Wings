package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateProductBatches extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateProductBatches(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createProductBatches() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen4Steps("Inventory","Product","Batches","Product Batches");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Product Batches']/TreeItem[@Name='All Product Batches']");
        Thread.sleep(3000);
        sendData("xpath", "//Edit[@Name='Product *']", dataFile, "product" );
//        String master=common.findWebElement("xpath","//Edit[@Name='Product *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='MRP']", common.getData(dataFile, "mrp"));
//        inputTextWithValidation("xpath", "//Edit[@Name='Batch *']", common.getData(dataFile, "batch"));
        common.clickElement("xpath","//Edit[@Name='Batch *']");
        String batch=common.findWebElement("xpath","//Edit[@Name='Batch *']").getText();
        common.clickElement("xpath","//*[@Name='Batch Node *']/following-sibling ::Button[@Name='...']");
        common.clickElement("xpath","//Button[@Name='Ok']");
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        inputTextWithValidation("xpath", "//Edit[@Name='Mfg. Batch No']", common.getData(dataFile, "mfgBatchNum"));
        inputTextWithValidation("xpath", "//Edit[@Name='Mfg. Date']", common.getData(dataFile, "mfgDate"));
        sendData("xpath", "//Edit[@Name='Mfg. Month']", dataFile, "mfgMonth");
        inputTextWithValidation("xpath", "//Edit[@Name='Expiry Date']", common.getData(dataFile, "expiryDate"));
        sendData("xpath", "//Edit[@Name='Expiry Month']", dataFile, "expiryMonth");
        common.clickElement("xpath","//*[@Name='Batch Sales Price List']/following-sibling ::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='Price List * Row 0, Not sorted.']", common.getData(dataFile, "priceList"));
        common.inputText("xpath", "//Edit[@Name='Price Type * Row 0, Not sorted.']", common.getData(dataFile, "PriceType"));
        common.inputText("xpath", "//Edit[@Name='With Effect From * Row 0, Not sorted.']", common.getData(dataFile, "wef"));
        common.inputText("xpath", "//Edit[@Name='Sale Unit Rate * Row 0, Not sorted.']", common.getData(dataFile, "rateInSalesUnit"));
        common.inputText("xpath", "//Edit[@Name='Base Unit Rate * Row 0, Not sorted.']", common.getData(dataFile, "RateInBaseUnit"));
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.clickElement("xpath","//*[@Name='Batch Purchase Price List']/following-sibling ::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='Price List * Row 0, Not sorted.']", common.getData(dataFile, "purchasePriceList"));
        common.inputText("xpath", "//Edit[@Name='Price Type * Row 0, Not sorted.']", common.getData(dataFile, "PriceType"));
        common.inputText("xpath", "//Edit[@Name='With Effect From * Row 0, Not sorted.']", common.getData(dataFile, "wef"));
        common.inputText("xpath", "//Edit[@Name='Rate In Purchase Unit * Row 0, Not sorted.']", common.getData(dataFile, "rateInSalesUnit"));
        common.inputText("xpath", "//Edit[@Name='Base Unit Rate * Row 0, Not sorted.']", common.getData(dataFile, "RateInBaseUnit"));
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.clickElement("xpath","//*[@Name='Sales Price List']/following-sibling ::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='With Effective From Row 0, Not sorted.']", common.getData(dataFile, "wef"));
        common.inputText("xpath", "//Edit[@Name='Price List Row 0, Not sorted.']", common.getData(dataFile, "priceList"));
        common.inputText("xpath", "//Edit[@Name='Price Type Row 0, Not sorted.']", common.getData(dataFile, "PriceType"));
        common.inputText("xpath", "//Edit[@Name='Sale Unit Rate Row 0, Not sorted.']", common.getData(dataFile, "rateInSalesUnit"));
        common.inputText("xpath", "//Edit[@Name='Sales Rate In Base Unit Row 0, Not sorted.']", common.getData(dataFile, "RateInBaseUnit"));
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.clickElement("xpath","//*[@Name='Set Purchase Price List']/following-sibling ::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='With Effective From Row 0, Not sorted.']", common.getData(dataFile, "wef"));
        common.inputText("xpath", "//Edit[@Name='Price List Row 0, Not sorted.']", common.getData(dataFile, "purchasePriceList"));
        common.inputText("xpath", "//Edit[@Name='Price Type Row 0, Not sorted.']", common.getData(dataFile, "PriceType"));
        common.inputText("xpath", "//Edit[@Name='Purchase Unit Rate Row 0, Not sorted.']", common.getData(dataFile, "rateInSalesUnit"));
        common.inputText("xpath", "//Edit[@Name='Purchase Rate In Base Unit Row 0, Not sorted.']", common.getData(dataFile, "RateInBaseUnit"));
        common.clickElement("xpath","//Button[@Name='Ok']");


//        saveAfterMasterCreate();
//        validateMastersAndInactive("Product Batches",batch);
//        Thread.sleep(1000);
    }
}
