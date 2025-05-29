package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SalesPrices extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesPrices(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void salesPrices() throws InterruptedException, IOException, ParseException {
        navigateToSalesPricesMenu();
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"salesPrices","branch");

        enterInput("xpath","//Edit[@Name='Master Type']",dataFile,"salesPrices","MasterType");
        enterInput("xpath", "//Edit[@Name='Price List *']", dataFile, "salesPrices","priceList");
        enterInput("xpath", "//Edit[@Name='Basis']", dataFile, "salesPrices","basis");
        enterInput("xpath", "//Edit[@Name='Amount']", dataFile, "salesPrices","amount");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "salesPrices","executive");

        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"salesPrices", "productCount")); i++) {
            enterInput("xpath", "//Edit[@Name='Minimum Rate * Row "+i+", Not sorted.']", dataFile, "salesPrices","minimumRate");
            enterInput("xpath", "//Edit[@Name='Maximum Rate * Row "+i+", Not sorted.']", dataFile, "salesPrices","maximumRate");

            enterInput("xpath", "//Edit[@Name='Rate * Row "+i+", Not sorted.']", dataFile, "salesPrices","unitRate");
            enterInput("xpath", "//Edit[@Name='MRP Row "+i+", Not sorted.']", dataFile, "salesPrices","mrp");
            enterInput("xpath", "//Edit[@Name='Discount Basis 1 Row "+i+", Not sorted.']", dataFile, "salesPrices","DiscountBasis0");
            enterInput("xpath", "//Edit[@Name='Discount 1 Row "+i+", Not sorted.']", dataFile, "salesPrices","DiscountBasisAmount0");
            enterInput("xpath", "//Edit[@Name='Discount Basis 2 Row "+i+", Not sorted.']", dataFile, "salesPrices","DiscountBasis1");
            enterInput("xpath", "//Edit[@Name='Discount 2 Row "+i+", Not sorted.']", dataFile, "salesPrices","DiscountBasisAmount1");
            common.clickElement("xpath","//Edit[@Name='Discount Basis 3 Row "+i+", Not sorted.']");
            enterInput("xpath", "//Edit[@Name='Discount Basis 3 Row "+i+", Not sorted.']", dataFile, "salesPrices","DiscountBasis2");
            enterInput("xpath", "//Edit[@Name='Discount 3 Row "+i+", Not sorted.']", dataFile, "salesPrices","DiscountBasisAmount2");

        }
        common.deleteInvalidRows();
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        String originalID =newTransactionID(oldVoucherID).replace(" ","");
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
        Thread.sleep(1000);
//        navigateToMastersWhen3Steps("Sales","Prices and Discounts", "Sales Price Updation");
//        Thread.sleep(1000);
//        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
//        Thread.sleep(1500);
//        List<String> products = Arrays.asList("AT_Product 1","AT_Product 2","AT_Product 3","AT_Product 4");
//        verifyReportProductWise(newVoucherID,dataFile,"salesPrices", Collections.singletonList(products.get(0)));
//        verifyReportProductWise(newVoucherID,dataFile,"PurchasePrice1", Collections.singletonList(products.get(1)));
//        verifyReportProductWise(newVoucherID,dataFile,"PurchasePrice2",Collections.singletonList(products.get(2)));
//        verifyReportProductWise(newVoucherID,dataFile,"PurchasePrice3",Collections.singletonList(products.get(3)));
//        System.out.println("All reports are verified");
//        deleteSingleTransaction(originalID);

    }
}
