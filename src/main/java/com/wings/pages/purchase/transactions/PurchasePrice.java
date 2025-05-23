package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PurchasePrice extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchasePrice(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchasePrice() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Purchase","Price", "Purchase Prices");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"PurchasePrice","branch");
//        enterInput("xpath","//Edit[@Name='Location *']",dataFile,"location");
//        enterInput("xpath","//Edit[@Name='Trans Currency *']",dataFile,"currency");
        enterInput("xpath","//Edit[@Name='Master Type']",dataFile,"PurchasePrice","MasterType");
        enterInput("xpath", "//Edit[@Name='Price List *']", dataFile, "PurchasePrice","priceList");
        enterInput("xpath", "//Edit[@Name='Basis']", dataFile, "PurchasePrice","basis");
        enterInput("xpath", "//Edit[@Name='Amount']", dataFile, "PurchasePrice","amount");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "PurchasePrice","executive");
//        enterInput("xpath", "//Edit[@Name='Remarks']", dataFile, "PurchasePrice","remarks");

        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"PurchasePrice", "productCount")); i++) {
            enterInput("xpath", "//Edit[@Name='Rate * Row "+i+", Not sorted.']", dataFile, "PurchasePrice","unitRate"+i);
            enterInput("xpath", "//Edit[@Name='MRP Row "+i+", Not sorted.']", dataFile, "PurchasePrice","mrp"+i);
            enterInput("xpath", "//Edit[@Name='Disc Basis 1 Row "+i+", Not sorted.']", dataFile, "PurchasePrice","DiscountBasis"+i);
            enterInput("xpath", "//Edit[@Name='Disc 1 Row "+i+", Not sorted.']", dataFile, "PurchasePrice","DiscountBasisAmount"+i);
            enterInput("xpath", "//Edit[@Name='Disc Basis 2 Row "+i+", Not sorted.']", dataFile, "PurchasePrice","DiscountBasis"+i);
            enterInput("xpath", "//Edit[@Name='Disc 2 Row "+i+", Not sorted.']", dataFile, "PurchasePrice","DiscountBasisAmount"+i);
            common.clickElement("xpath","//Edit[@Name='Disc Basis 3 Row "+i+", Not sorted.']");
            enterInput("xpath", "//Edit[@Name='Disc Basis 3 Row "+i+" Not sorted.']", dataFile, "PurchasePrice","DiscountBasis"+i);
            enterInput("xpath", "//Edit[@Name='Disc 3 Row "+i+", Not sorted.']", dataFile, "PurchasePrice","DiscountBasisAmount"+i);

        }
        common.deleteInvalidRows();
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        String originalID =newTransactionID(oldVoucherID).replace(" ","");
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
        Thread.sleep(1000);
        navigateToMastersWhen3Steps("Purchase","Price","Purchase Price Updation");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        List<String> products = Arrays.asList("AT_Product 1","AT_Product 2","AT_Product 3","AT_Product 4");
        verifyReportProductWise(newVoucherID,dataFile,"PurchasePrice", Collections.singletonList(products.get(0)));
        verifyReportProductWise(newVoucherID,dataFile,"PurchasePrice1", Collections.singletonList(products.get(1)));
        verifyReportProductWise(newVoucherID,dataFile,"PurchasePrice2",Collections.singletonList(products.get(2)));
        verifyReportProductWise(newVoucherID,dataFile,"PurchasePrice3",Collections.singletonList(products.get(3)));
        System.out.println("All reports are verified");
        deleteSingleTransaction(originalID);

    }
}
