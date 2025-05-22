package com.wings.pages.inventory.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StockConversion extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public StockConversion(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void stockConversion() throws InterruptedException, AWTException, IOException, ParseException {
        navigateToMastersWhen2Steps("Inventory","Stock Conversion");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"stockConversation","branch");
        enterInput("xpath", "//Edit[@Name='Batch Policy']",dataFile,"stockConversation", "batchPolicy");
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, "stockConversation","priceList");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "stockConversation","executive");

        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"stockConversation", "productCount")); i++) {
            addProduct(i);
        }
        common.clickElement("xpath","//TabItem[contains(@Name,'Out puts')]");
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"stockConversation", "productCount")); i++) {
            addOutPutProduct(i);
        }
        enterOtherInfo(dataFile,"stockConversation");
        validateBatchDetailsTabIsNotEmpty();
        navigateToSummaryTab();
        inputQuantityPresentInSummary();

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        String originalID =newTransactionID(oldVoucherID).replace(" ","");
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
        Thread.sleep(1000);
        common.clickElement("name", "Inventory");
        common.clickElement("xpath", "//MenuItem[@Name='Stock Conversion'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        List<String> products = Arrays.asList("AT_Product 1", "AT_Multi batch Product 2", "AT_Product With SN 3");
        verifyReportProductWise(newVoucherID,dataFile,"stockConsumption", Collections.singletonList(products.get(0)));
        verifyReportProductWise(newVoucherID,dataFile,"stockConsumptionMultiBatch", Collections.singletonList(products.get(1)));
        verifyReportProductWise(newVoucherID,dataFile,"stockConsumptionSerialNum", Collections.singletonList(products.get(2)));
        System.out.println("All reports are verified");
//        deleteSingleTransaction(originalID);
    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile,"stockConversation", "productType" + i).equals("general")) {
            enterProductInOpeningStock(dataFile,"stockConversation", "productCode" + i, "quantity"+i,"freeQuantity" + i, i);
        } else if (common.getData(dataFile,"stockConversation", "productType" + i).equals("multiBatch")) {
            multiBatchProductInStockConsumption(dataFile, "stockConversation","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        }else if (common.getData(dataFile,"stockConversation", "productType" + i).equals("serial")) {
            serialNumberProductInStockConsumption(dataFile,"stockConversation","productCode"+i,i);
        }
        Thread.sleep(1000);
        common.clickElement("xpath","//CheckBox[@Name='Apply Cost Per Unit Row "+i+"']");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "stockConversation","grossAmount" + i),"Gross Amount mismatch");
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Gross Amount In Company Currency Row "+i+", Not sorted.']"),common.getData(dataFile,"stockConversation","grossInCompanyCurrency"+i),"netAmount mismatch");
    }

    public void addOutPutProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile,"stockConversation", "productType" + i).equals("general")) {
            enterProductInOpeningStock(dataFile,"stockConversation", "productCode" + i, "quantity"+i,"freeQuantity" + i, i);
        } else if (common.getData(dataFile,"stockConversation", "productType" + i).equals("multiBatch")) {
            multiBatchProductDirectQuantity(dataFile, "stockConversation","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
            common.clickElement("xpath","//Edit[@Name='Product Code Row 2, Not sorted.']");
            common.findWebElement("xpath","//Edit[@Name='Product Code Row 2, Not sorted.']").sendKeys(common.getData(dataFile,"stockConversation","productCode2"));
        }else if (common.getData(dataFile,"stockConversation", "productType" + i).equals("serial")) {
            serialNumberForMRAO(dataFile,"stockConversation","serialText","quantity"+i,"freeQuantity"+i);
        }
        Thread.sleep(1000);
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "stockConversation","grossAmount" + i),"Gross Amount mismatch");
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Gross Amount In Company Currency Row "+i+", Not sorted.']"),common.getData(dataFile,"stockConversation","grossInCompanyCurrency"+i),"netAmount mismatch");
    }
}
