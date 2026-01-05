package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class CreateProducts extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateProducts(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createProducts() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Inventory","Product","Products");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Products']/TreeItem[@Name='All Products']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='Product *']", common.getData(dataFile, "product") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='Product *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Product Code']", common.getData(dataFile, "productCode")+common.getRandom());
        String itemNum=common.getText("xpath","//Edit[@Name='Item No']");
        System.out.println(itemNum);
        common.clickElement("xpath","//Button[@Name='Refresh Item No']");
        String itemNumAfterRefresh=common.getText("xpath","//Edit[@Name='Item No']");
        System.out.println(itemNumAfterRefresh);
        if (itemNum.equals(itemNumAfterRefresh)) Assert.fail("bothItemNumbers are Sames not refreshed");
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        sendData("xpath","//Edit[@Name='Division *']",dataFile,"division");
        sendData("xpath","//Edit[@Name='HSN Code']",dataFile,"hsnCode");
        sendData("xpath","//Edit[@Name='Product Batch Type *']",dataFile,"productBatch");
        sendData("xpath","//Edit[@Name='MRP']",dataFile,"mrp");
        sendData("xpath","//Edit[@Name='Batch Master Group *']",dataFile,"batchMasterGrp");
        sendData("xpath","//Edit[@Name='Distribution']",dataFile,"distributions");
        sendData("xpath","//Edit[@Name='Category']",dataFile,"category");
        sendData("xpath","//Edit[@Name='Product Type']",dataFile,"productType");
        sendData("xpath","//Edit[@Name='Brand']",dataFile,"brand");
        sendData("xpath","//Edit[@Name='Extension']",dataFile,"extension");
        sendData("xpath","//Edit[@Name='Variant']",dataFile,"variant");
        sendData("xpath","//Edit[@Name='Pack Size']",dataFile,"packSize");
        sendData("xpath","//Edit[@Name='Pack Format']",dataFile,"packFormat");
        sendData("xpath","//Edit[@Name='Base Unit *']",dataFile,"baseUnit");
        sendData("xpath","//Edit[@Name='Sales Unit *']",dataFile,"salesUnit");
        sendData("xpath","//Edit[@Name='Pur. Unit *']",dataFile,"purUnit");
        sendData("xpath", "//Edit[@Name='= *']", dataFile, "=*");
        sendData("xpath", "//Edit[@Name='= *']", dataFile, "=*1");
        common.sliderHandling("name", "Position", 0, 130);
        Thread.sleep(2000);
        common.clickElement("xpath","//CheckBox[@Name='Maintain Shelf Life']");
        inputTextWithValidation("xpath", "//Edit[@Name='Shelf Life In Days']", common.getData(dataFile, "shelfLifeInDays"));
        inputTextWithValidation("xpath", "//Edit[@Name='Shelf Life In Months']", common.getData(dataFile, "shelfLifeInMonths"));
        inputTextWithValidation("xpath", "//Edit[@Name='Weight In Grms']", common.getData(dataFile, "weightGrams"));
        common.clickElement("xpath","//CheckBox[@Name='Measured In Volume']");
        inputTextWithValidation("xpath", "//Edit[@Name='Vol In MLtrs']", common.getData(dataFile, "volumeInMilliLiters"));
        sendData("xpath", "//Edit[@Name='Info1']", dataFile, "Info1");
        sendData("xpath", "//Edit[@Name='Info2']", dataFile, "Info2");
        sendData("xpath", "//Edit[@Name='Info3']", dataFile, "Info3");
        sendData("xpath", "//Edit[@Name='Info4']", dataFile, "Info4");
        sendData("xpath", "//Edit[@Name='Info5']", dataFile, "Info5");
        sendData("xpath", "//Edit[@Name='Info6']", dataFile, "Info6");
        sendData("xpath", "//Edit[@Name='Info7']", dataFile, "Info7");
        sendData("xpath", "//Edit[@Name='Info8']", dataFile, "Info8");
        sendData("xpath", "//Edit[@Name='Info9']", dataFile, "Info9");
        sendData("xpath", "//Edit[@Name='Info10']", dataFile, "Info10");
        sendData("xpath", "//Edit[@Name='Value1']", dataFile, "Value1");
        sendData("xpath", "//Edit[@Name='Value2']", dataFile, "Value2");
        sendData("xpath", "//Edit[@Name='Value3']", dataFile, "Value3");
        sendData("xpath", "//Edit[@Name='Value4']", dataFile, "Value4");
        sendData("xpath", "//Edit[@Name='Value5']", dataFile, "Value5");
        sendData("xpath", "//Edit[@Name='Value6']", dataFile, "Value6");
        sendData("xpath", "//Edit[@Name='Value7']", dataFile, "Value7");
        sendData("xpath", "//Edit[@Name='Value8']", dataFile, "Value8");
        sendData("xpath", "//Edit[@Name='Value9']", dataFile, "Value9");
        sendData("xpath", "//Edit[@Name='Value10']", dataFile, "Value10");
        sendData("xpath", "//Edit[@Name='HSN Code']", dataFile, "hsnCode");
        sendData("xpath", "//Edit[@Name='Division']", dataFile, "division");
        sendData("xpath", "//Edit[@Name='Barcode / Base Code']", dataFile, "barCode");
        sendData("xpath", "//Edit[@Name='Sales Return Account']", dataFile, "salesReturnAcc");
//        sendData("xpath", "//Edit[@Name='Distribution']", dataFile, "distributions");
        common.sliderHandling("name", "Position", 0, 50);
        common.clickElement("xpath","//*[@Name='Sales Price List']/following-sibling ::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='With Effect From * Row 0, Not sorted.']", common.getData(dataFile, "wef"));
        common.inputText("xpath", "//Edit[@Name='Price List * Row 0, Not sorted.']", common.getData(dataFile, "priceList"));
        common.inputText("xpath", "//Edit[@Name='Price Type * Row 0, Not sorted.']", common.getData(dataFile, "PriceType"));
        common.inputText("xpath", "//Edit[@Name='Rate In Sale Unit * Row 0, Not sorted.']", common.getData(dataFile, "rateInSalesUnit"));
        common.inputText("xpath", "//Edit[@Name='Rate In Base Unit * Row 0, Not sorted.']", common.getData(dataFile, "RateInBaseUnit"));
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.clickElement("xpath","//*[@Name='Purchase Price List']/following-sibling ::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='With Effect From * Row 0, Not sorted.']", common.getData(dataFile, "wef"));
        common.inputText("xpath", "//Edit[@Name='Price List * Row 0, Not sorted.']", common.getData(dataFile, "purchasePriceList"));
        common.inputText("xpath", "//Edit[@Name='Price Type * Row 0, Not sorted.']", common.getData(dataFile, "PriceType"));
        common.inputText("xpath", "//Edit[@Name='Rate In Purchase Unit * Row 0, Not sorted.']", common.getData(dataFile, "rateInSalesUnit"));
        common.inputText("xpath", "//Edit[@Name='Rate In Base Unit * Row 0, Not sorted.']", common.getData(dataFile, "RateInBaseUnit"));
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.clickElement("xpath","//*[@Name='Default Storage Bin']/following-sibling ::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='Location * Row 0, Not sorted.']", common.getData(dataFile, "location"));
        common.inputText("xpath", "//Edit[@Name='Storage Bin * Row 0, Not sorted.']", common.getData(dataFile, "storageBin"));
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.clickElement("xpath","//*[@Name='Min And Max Discount Percent']/following-sibling ::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='With Effective From Row 0, Not sorted.']", common.getData(dataFile, "wef"));
        common.inputText("xpath", "//Edit[@Name='Min Discount Percent Row 0, Not sorted.']", common.getData(dataFile, "minDiscount"));
        common.inputText("xpath", "//Edit[@Name='Max Discount Percent Row 0, Not sorted.']", common.getData(dataFile, "maxDiscount"));
        common.clickElement("xpath","//Button[@Name='Ok']");


        saveAfterMasterCreate();
        validateMastersAndInactive("Products",master);
        Thread.sleep(1000);
    }
}
