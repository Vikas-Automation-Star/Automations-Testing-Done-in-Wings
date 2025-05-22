package com.wings.pages.inventory.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StockConsumption extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    public StockConsumption(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void stockConsumption() throws InterruptedException, AWTException, IOException, ParseException {
        navigateToMastersWhen2Steps("Inventory","Stock Consumption");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"stockConsumption","branch");
        gstTransactionType("Intra State Sales to Registered Dealers");
        enterInput("xpath", "//Edit[@Name='Stock Consumption Account']",dataFile,"stockConsumption", "consumptionAccount");
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, "stockConsumption","priceList");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "stockConsumption","executive");

        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"stockConsumption", "productCount")); i++) {
            addProduct(i);
        }
        validateCGSTAmountTabIsNotEmpty();
        validateSGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        enterOtherInfo(dataFile,"stockConsumption");
        navigateToSummaryTab();
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        cgstPresentInSummary();
        sgstPresentInSummary();
        cessPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        String originalID =newTransactionID(oldVoucherID).replace(" ","");
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
        Thread.sleep(1000);
        common.clickElement("name", "Inventory");
        common.clickElement("xpath", "//MenuItem[@Name='Stock Consumption'][2]");
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
        if (common.getData(dataFile,"stockConsumption", "productType" + i).equals("general")) {
            enterProductInOpeningStock(dataFile,"stockConsumption", "productCode" + i, "quantity"+i,"freeQuantity" + i, i);
        } else if (common.getData(dataFile,"stockConsumption", "productType" + i).equals("multiBatch")) {
            multiBatchProductInStockConsumption(dataFile, "stockConsumption","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        }else if (common.getData(dataFile,"stockConsumption", "productType" + i).equals("serial")) {
            serialNumberProductInStockConsumption(dataFile,"stockConsumption","productCode"+i,i);
        }
        Thread.sleep(1000);
        common.clickElement("xpath","//CheckBox[@Name='Apply Cost Per Unit Row "+i+"']");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 800, 0);
        enterData("xpath", "//Edit[@Name='HSN Row " +i+", Not sorted.']", dataFile,"stockConsumption", "HSNCode");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='MRP Amount Row "+i+", Not sorted.']"), common.getData(dataFile, "stockConsumption","mrpAmount" + i),"MRP Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "stockConsumption","grossAmount" + i),"Gross Amount mismatch");
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Net Amount Row "+i+", Not sorted.']"),common.getData(dataFile,"stockConsumption","netAmount"+i),"netAmount mismatch");
    }
}
