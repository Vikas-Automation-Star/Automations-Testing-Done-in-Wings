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

public class ProductndPartyDiscount extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public ProductndPartyDiscount(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String productDiscount(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long start = System.nanoTime();
        System.out.println("Party and Product Discount started in :"+start);
        Thread.sleep(1000);
        long generalInfoStart=System.nanoTime();
        navigateToPartyProductwiseDiscountMenu();
        Thread.sleep(2500);
        String oldVoucherID =oldTTransactionID();
        //branch selection
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Party and Product Discount General Information End:- ", generalInfoEndTime);

        long addProductStart = System.nanoTime();
        addProduct();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("Party and Product Discount Add Products:- ", addProductEnd);

        //saving and IO generating
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
       //API
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"partyProductDiscount");
        long productDiscountend=System.nanoTime()-start;
        FileUtil.writeTimeLogInMinutes("Party and Product Discount Ended at:- ",productDiscountend);
        deleteTransactionUsingVoucherNumber(newVoucherID);

        return newVoucherID;
    }

    public void addProduct() throws Exception {
        List<String> partyDiscountGroup =readExcelData(dataFile,"Items","PartyDiscountGroup");
        for (int i = 0; i < partyDiscountGroup.size() ; i++)   {
            addData("xpath","//Edit[@Name='Party Discount Group * Row "+i+", Not sorted.']",dataFile,"Items","PartyDiscountGroup",i);
        }
        List<WebElement> productDiscountGroup = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product Discount Group * Row ')]");
        List<WebElement> discountRow = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc % * Row ')]");
        List<WebElement> effectiveFrom = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'With Effect From * Row ')]");
        for (int i = 0; i < partyDiscountGroup.size() ; i++) {
            enterListData(productDiscountGroup.get(i), dataFile, "Items", "ProductDiscountGroup", i);
            enterListData(discountRow.get(i), dataFile, "Items", "DiscountPercentage", i);
            enterListDate(effectiveFrom.get(i), dataFile, "Items", "WithEffectFrom", i);
        }
    }
}