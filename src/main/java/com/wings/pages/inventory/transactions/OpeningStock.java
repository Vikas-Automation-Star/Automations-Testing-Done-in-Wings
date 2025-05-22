package com.wings.pages.inventory.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;

public class OpeningStock extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public OpeningStock(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void openingStock() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen2Steps("Inventory","Opening Stock");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"openingStock","branch");
        enterInput("xpath", "//Edit[@Name='Opening Stock Account *']",dataFile,"openingStock", "stockAccount");
        enterInput("xpath", "//Edit[@Name='Opening Stock Account Asset *']",dataFile,"openingStock", "stockAsset");
        enterInput("xpath", "//Edit[@Name='Batch Policy']",dataFile,"openingStock", "batchPolicy");
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, "openingStock","priceList");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "openingStock","executive");


        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"openingStock", "productCount")); i++) {
            addProduct(i);
        }
        enterOtherInfo(dataFile,"openingStock");
        navigateToSummaryTab();
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossAmountInCompanyCurrencyPresentInSummary();
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
        Thread.sleep(1000);
        common.clickElement("name", "Inventory");
        common.clickElement("xpath", "//MenuItem[@Name='Opening Stock'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID,dataFile,"openingStock");

    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile,"openingStock", "productType" + i).equals("general")) {
            enterProductInOpeningStock(dataFile,"openingStock", "productCode" + i, "quantity"+i,"freeQuantity" + i, i);
        } else if (common.getData(dataFile,"openingStock", "productType" + i).equals("multiBatch")) {
            multiBatchProductInOpeningStock(dataFile, "openingStock","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        }else if (common.getData(dataFile,"openingStock", "productType" + i).equals("serial")) {
            serialNumberProductInOpeningStock(dataFile,"openingStock", "productCode" + i,"serialNumText","freeQuantity"+i,"quantity"+i,i);
        }
        Thread.sleep(2000);
        common.clickElement("xpath","//CheckBox[@Name='Apply Price List Row "+i+"']");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "openingStock","grossAmount" + i),"Gross Amount mismatch");
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Gross Amount In Company Currency Row "+i+", Not sorted.']"),common.getData(dataFile,"openingStock","grossInCompanyCurrency"+i),"grossInCompanyCurrency mismatch");
    }

}
