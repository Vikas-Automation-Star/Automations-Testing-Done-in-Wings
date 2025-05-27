package com.wings.pages.inventory.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;

public class StockCreation extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public StockCreation(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void stockCreation() throws InterruptedException, AWTException, IOException, ParseException {
        navigateToMastersWhen2Steps("Inventory","Stock Creation");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"stockCreation","branch");
        enterInput("xpath", "//Edit[@Name='Stock Account']",dataFile,"stockCreation", "stockAccount");
        enterInput("xpath", "//Edit[@Name='Batch Policy']",dataFile,"stockCreation", "batchPolicy");
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, "stockCreation","priceList");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "stockCreation","executive");


        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"stockCreation", "productCount")); i++) {
            addProduct(i);
        }
        enterOtherInfo(dataFile,"stockCreation");
        navigateToSummaryTab();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        String originalID =newTransactionID(oldVoucherID).replace(" ","");
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
        Thread.sleep(1000);
        common.clickElement("name", "Inventory");
        common.clickElement("xpath", "//MenuItem[@Name='Stock Creation'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID,dataFile,"stockCreation");
        deleteSingleTransaction(originalID);
    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile,"stockCreation", "productType" + i).equals("general")) {
            enterProductInOpeningStock(dataFile,"stockCreation", "productCode" + i, "quantity"+i,"freeQuantity" + i, i);
        } else if (common.getData(dataFile,"stockCreation", "productType" + i).equals("multiBatch")) {
            multiBatchProductInOpeningStock(dataFile, "stockCreation","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        }else if (common.getData(dataFile,"stockCreation", "productType" + i).equals("serial")) {
            serialNumberProductInOpeningStock(dataFile,"stockCreation", "productCode" + i,"serialNumText","freeQuantity"+i,"quantity"+i,i);
        }
        Thread.sleep(2000);
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);

        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='MRP Amount Row "+i+", Not sorted.']"), common.getData(dataFile, "stockCreation","mrpAmount" + i),"MRP Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "stockCreation","grossAmount" + i),"Gross Amount mismatch");
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Net Amount In Company Currency Row "+i+", Not sorted.']"),common.getData(dataFile,"stockCreation","netInCompanyCurrency"+i),"netInCompanyCurrency mismatch");
    }
}
