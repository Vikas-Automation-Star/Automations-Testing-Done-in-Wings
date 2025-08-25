package com.wings.pages.sales.transactions;

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

public class SalesPrices extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesPrices(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String salesPrices(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long start = System.nanoTime();
        navigateToSalesPricesMenu();
        Thread.sleep(3000);
        long generalInfoStart = System.nanoTime();
        Thread.sleep(3000);
        String oldVoucherID = oldTTransactionID();
        enterVoucherType(dataFile, "GeneralInformation", "VoucherType");
        EnterDate("//Edit[@Name='Date *']", dataFile, "GeneralInformation", "Date");
        enterBranch(dataFile, "GeneralInformation", "Branch");
        enterCurrency(dataFile, "GeneralInformation", "TransactionCurrency");
        enterMasterType(dataFile, "Items", "MasterType");
        EnterDate("//Edit[@Name='With Effect From *']",dataFile,"GeneralInformation", "WithEffectFrom");
        enterPriceList(dataFile, "GeneralInformation", "PriceList");
        enterBasisSalesPrice(dataFile, "GeneralInformation", "Basis");
        enterAmountSalesPrice(dataFile, "GeneralInformation", "Amount");
        enterExecutive(dataFile, "GeneralInformation", "Executive");
        enterRemarks(dataFile, "GeneralInformation", "Remarks");

        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Sales Price General Information End:- ", generalInfoEndTime);

        long addProductStart = System.nanoTime();
        addProduct();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("Sales Price Items:- ", addProductEnd);


        //saving and IO generating
        transactionSave();
        String newVoucherID = newTransactionID(oldVoucherID);
        System.out.println("newID: " + newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID, "Voucher Numbers are same. Check Transaction.");
        //api
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"salesPrice");

        return newVoucherID;


    }

    public void addProduct() throws Exception {
        List<String> productCode = readExcelData(dataFile, "Items", "ProductCode");
        System.out.println("productCodes :" + productCode.size());

        List<WebElement> uom = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
        List<WebElement> minimumRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Minimum Rate * Row ')]");
        List<WebElement> maximunRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Maximum Rate * Row ')]");
        List<WebElement> rate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Rate * Row ')]");
        List<WebElement> mrp = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'MRP Row ')]");;
        List<WebElement> DiscountBasis1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Basis 1 Row ')]");
        List<WebElement> Discount1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount 1 Row ')]");
        List<WebElement> DiscountBasis2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Basis 2 Row ')]");
        List<WebElement> Discount2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount 2 Row ')]");
        List<WebElement> DiscountBasis3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Basis 3 Row ')]");
        List<WebElement> Discount3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount 3 Row ')]");
        for (int i = 0; i < productCode.size(); i++) {
            enterListData(uom.get(i), dataFile, "Items", "UOM", i);
            enterListData(minimumRate.get(i),dataFile,"Items","MinimumRate",i);
            enterListData(maximunRate.get(i),dataFile,"Items","MaximumRate",i);
            enterListData(rate.get(i), dataFile, "Items", "UnitRate", i);
            enterListData(mrp.get(i), dataFile, "Items", "MRP", i);
            enterListData(DiscountBasis1.get(i), dataFile, "Items", "DiscountBasis1", i);
            enterListData(Discount1.get(i), dataFile, "Items", "Discount1", i);
            enterListData(DiscountBasis2.get(i), dataFile, "Items", "DiscountBasis2", i);
            enterListData(Discount2.get(i), dataFile, "Items", "Discount2", i);
            enterListData(DiscountBasis3.get(i), dataFile, "Items", "DiscountBasis3", i);
            enterListData(Discount3.get(i), dataFile, "Items", "Discount3", i);
        }
    }
}
