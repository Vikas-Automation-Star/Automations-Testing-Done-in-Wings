package tradeTesting.sales.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class SalesOrdersTrade extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false, isUnitRateHeaderClicked = false, isBaseUnitRateHeaderclicked = false, discountIsClicked = false;


    public SalesOrdersTrade(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String salesOrderTrade() throws InterruptedException, IOException, ParseException {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Sales", "Orders", "Sales Orders");
        Thread.sleep(1000);
        long genInfoStart=System.nanoTime();
        String oldVoucherID=oldTTransactionID();
        enterVoucherType(dataFile, "GeneralInformation", "VoucherType");
        EnterDate("//Edit[@Name='Date *']", dataFile, "GeneralInformation", "Date");
        enterBranch(dataFile, "GeneralInformation", "Branch");
        EnterData("//Edit[@Name='Location']",dataFile,"GeneralInformation","Location");
        EnterData("//Edit[@Name='Route']",dataFile,"GeneralInformation","Route");
        EnterData("//Edit[@Name='Cash / Party Code']",dataFile,"GeneralInformation","CustomerCode");
        enterPartyAcc(dataFile,"GeneralInformation","PartyAccount");
        enterCreditPeriod(dataFile,"GeneralInformation","CreditPeriod");
        EnterData("//Edit[@Name='Sales Executive *']",dataFile,"GeneralInformation","SalesExecutive");
        common.clickElement("xpath","//CheckBox[@Name='Apply Simple Scheme']");
        common.clickElement("xpath","//CheckBox[@Name='Apply Combo Scheme']");
        enterPriceList(dataFile,"GeneralInformation","PriceList");
        EnterData("//Edit[@Name='Price Type']",dataFile,"GeneralInformation","PriceType");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        //items
        long addProductStart = System.nanoTime();
        addProduct();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("SO Add Products:- ", addProductEnd);
        return "";
    }

    public void addProduct() throws IOException, ParseException {
        List<String> productCode = readExcelData(dataFile, "Items", "ProductCode");
        System.out.println("productCodes :" + productCode.size());
        for (int i = 0; i < productCode.size(); i++) {
            addData("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", dataFile, "Items", "ProductCode", i);
        }
        List<WebElement> product = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product Row ')]");
        List<WebElement> productBatch = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product Batch Row ')]");
        List<WebElement> priceType = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Price Type Row ')]");
        if (!isUnitRateHeaderClicked) {
            common.clickElement("xpath", "//Header[@Name='Unit Rate']");
            isUnitRateHeaderClicked = true;
        }
        if (!isBaseUnitRateHeaderclicked) {
            common.clickElement("xpath", "//Header[@Name='Base Unit Rate']");
            isUnitRateHeaderClicked = true;
        }
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Qty Row ')]");
        List<WebElement> unitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Unit Rate Row ')]");
        List<WebElement> quantityInBaseUnit = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Qty In Base Unit Row ')]");
        List<WebElement> baseUnitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Base Unit Rate Row ')]");
        List<WebElement> freeQuantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Free Qty Row ')]");
        List<WebElement> freeQuantityInBaseUnit = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Free Qty In Base Unit Row ')]");
        List<WebElement> mrp = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'MRP Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 800, 0);
        List<WebElement> simpleSchemeDiscount = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Simple Scheme Disc Row ')]");
        List<WebElement> combinationalSchemeDiscount = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Combination Scheme Disc Row ')]");
        if (!discountIsClicked) {
            common.clickElement("xpath", "//Header[@Name='Discount Amount1']");
            common.clickElement("xpath", "//Header[@Name='Discount Amount2']");
            common.clickElement("xpath", "//Header[@Name='Discount Amount3']");
            discountIsClicked = true;
        }
        List<WebElement> discountAccount1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Account1 Row ')]");
        List<WebElement> discountBasis1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Basis1 Row ')]");
        List<WebElement> discount1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount1 Row ')]"
        );
        List<WebElement> discountAccount2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Account2 Row ')]");
        List<WebElement> discountBasis2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Basis2 Row ')]");
        List<WebElement> discount2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount2 Row ')]");

        List<WebElement> discountAccount3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Account3 Row ')]");
        List<WebElement> discountBasis3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Basis3 Row ')]");
        List<WebElement> discount3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        List<WebElement> HSNCode = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'HSN Row ')]");
        List<WebElement> GSTProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'GST Product Category Row ')]");
        List<WebElement> CESSProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'CESS Product Category Row ')]");
        List<WebElement> executives = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Executive Row ')]");

        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);

//        common.clickElement("xpath", "//Header[@Name='Info5']");
//        List<WebElement> Info1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info1 Row ')]");
//        List<WebElement> Info2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info2 Row ')]");
//        List<WebElement> Info3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info3 Row ')]");
//        List<WebElement> Info4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info4 Row ')]");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
//        List<WebElement> Info5 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info5 Row ')]");
//        common.clickElement("xpath", "//Header[@Name='Value5']");
//        List<WebElement> Value1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value1 Row  ')]");
//        List<WebElement> Value2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value2 Row ')]");
//        List<WebElement> Value3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value3 Row ')]");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
//        List<WebElement> Value4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value4 Row ')]");
//        List<WebElement> Value5 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value5 Row ')]");
//        common.clickElement("xpath", "//Header[@Name='Date3']");
//        List<WebElement> Date1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date1 Row ')]");
//        List<WebElement> Date2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date2 Row ')]");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
//        List<WebElement> Date3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date3 Row ')]");
//        common.clickElement("xpath", "//Header[@Name='Bool3']");
//        List<WebElement> Bool1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 1 Row ')]");
//        List<WebElement> Bool2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 2 Row ')]");
//        List<WebElement> Bool3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 3 Row ')]");

        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1900, 0);

        for (int i = 0; i < productCode.size(); i++) {
            enterListData(product.get(i), dataFile, "Items", "Product", i);
            enterListData(productBatch.get(i), dataFile, "Items", "ProductBatchId", i);
            enterListDate(priceType.get(i), dataFile, "Items", "PriceType", i);
            enterListData(quantity.get(i), dataFile, "Items", "Qty", i);
            enterListData(unitRate.get(i),dataFile,"Items","UnitRate",i );
            enterListData(quantityInBaseUnit.get(i),dataFile,"Items","QtyInBaseUnit",i );
            enterListData(baseUnitRate.get(i), dataFile, "Items", "BaseUnitRate", i);
            enterListData(freeQuantity.get(i), dataFile, "Items", "FreeQty", i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 250, 0);
            enterListData(freeQuantityInBaseUnit.get(i),dataFile,"Items","FreeQtyInBaseUnit",i );
            enterListData(mrp.get(i), dataFile, "Items", "MRP", i);
            enterListData(simpleSchemeDiscount.get(i), dataFile, "Items", "SimpleSchemeQty", i);
            enterListData(combinationalSchemeDiscount.get(i), dataFile, "Items", "CombinationSchemeQty", i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
            enterListData(discountAccount1.get(i), dataFile, "Items", "DiscountAccount1", i);
            enterListData(discountBasis1.get(i), dataFile, "Items", "DiscountBasis1", i);
            enterListData(discount1.get(i), dataFile, "Items", "Discount1", i);

            enterListData(discountAccount2.get(i), dataFile, "Items", "DiscountAccount2", i);
            enterListData(discountBasis2.get(i), dataFile, "Items", "DiscountBasis2", i);
            enterListData(discount2.get(i), dataFile, "Items", "Discount2", i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
            enterListData(discountAccount3.get(i), dataFile, "Items", "DiscountAccount3", i);
            enterListData(discountBasis3.get(i), dataFile, "Items", "DiscountBasis3", i);
            enterListData(discount3.get(i), dataFile, "Items", "Discount3", i);
            enterListData(HSNCode.get(i), dataFile, "Items", "HSN", i);
            enterListData(GSTProductCategory.get(i), dataFile, "Items", "GSTProductCategory", i);
            enterListData(CESSProductCategory.get(i), dataFile, "Items", "CESSProductCategory", i);
            enterListData(executives.get(i), dataFile, "Items", "Executive", i);
            enterListData(Department.get(i), dataFile, "Items", "Department", i);
            enterListData(Project.get(i), dataFile, "Items", "Project", i);
            enterListData(ProfitCentre.get(i), dataFile, "Items", "ProfitCentre", i);
            enterListData(CostCentre.get(i), dataFile, "Items", "CostCentre", i);
            enterListData(Comments.get(i), dataFile, "Items", "Comments", i);
//            enterListData(Info1.get(i), dataFile, "Items", "Info1", i);
//            enterListData(Info2.get(i), dataFile, "Items", "Info2", i);
//            enterListData(Info3.get(i), dataFile, "Items", "Info3", i);
//            enterListData(Info4.get(i), dataFile, "Items", "Info4", i);
//            enterListData(Info5.get(i), dataFile, "Items", "Info5", i);
//            enterListData(Value1.get(i), dataFile, "Items", "Value1", i);
//            enterListData(Value2.get(i), dataFile, "Items", "Value2", i);
//            enterListData(Value3.get(i), dataFile, "Items", "Value3", i);
//            enterListData(Value4.get(i), dataFile, "Items", "Value4", i);
//            enterListData(Value5.get(i), dataFile, "Items", "Value5", i);
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 150, 0);
//            enterListDate(Date1.get(i), dataFile, "Items", "Date1", i);
//            enterListDate(Date2.get(i), dataFile, "Items", "Date2", i);
//            enterListDate(Date3.get(i), dataFile, "Items", "Date3", i);
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 50, 0);
//            clickListData(Bool1.get(i));
//            clickListData(Bool2.get(i));
//            clickListData(Bool3.get(i));
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1550, 0);
        }
    }


}