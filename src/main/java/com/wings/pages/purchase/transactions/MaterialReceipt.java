package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;

public class MaterialReceipt extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked=false;

    public MaterialReceipt(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String materialReceipt() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps("Purchase","Receipts", "Material Receipts");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"MaterialReceipts","branch");
//        enterInput("xpath","//Edit[@Name='Location *']",dataFile,"location");
//        enterInput("xpath","//Edit[@Name='Trans Currency *']",dataFile,"currency");
        enterInput("xpath", "//Edit[@Name='Party Code']",dataFile,"MaterialReceipts", "partyCode");
        Thread.sleep(1000);
        enterInput("xpath", "//Edit[@Name='Batch Policy']",dataFile,"MaterialReceipts", "batchPolicy");
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, "MaterialReceipts","priceList");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "MaterialReceipts","executive");

        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"MaterialReceipts", "productCount")); i++) {
            addProduct(i);
        }
        chargesAndDeductionsCalculations1(dataFile,"MaterialReceipts", "charges","deductions","chargesAcc","deductionsAcc", "chargesAmount", "deductionsAmount", "chargesRowCount");
        enterOtherCharges(dataFile,"MaterialReceipts");
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        enterOtherCosts(dataFile,"MaterialReceipts");
        enterOtherInfo(dataFile,"MaterialReceipts");
        termsAndConditions(dataFile,"MaterialReceipts");
        navigateToSummaryTab();
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        iGSTPresentInSummary();
        cessPresentInSummary();
        netAmountPresentInSummary();
        chargesPresentInSummary();
        deductionsPresentInSummary();
        otherChargesPresentInSummary();
        otherChargesIGSTPresentInSummary();
        otherChargesCESSPresentInSummary();
        otherCostsAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
        Thread.sleep(1000);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Material Receipts'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID,dataFile,"MaterialReceipts");
//        deleteSingleTransaction(newVoucherID,dataFile);
        return newVoucherID;
    }

    public void addProduct(int i) throws IOException, ParseException, InterruptedException, AWTException {
        if (common.getData(dataFile,"MaterialReceipts", "productType" + i).equals("general")) {
            generalProduct(dataFile,"MaterialReceipts", "productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        } else if (common.getData(dataFile,"MaterialReceipts", "productType" + i).equals("multiBatch")) {
            multiBatchProductPurchase(dataFile, "MaterialReceipts","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        }else if (common.getData(dataFile,"MaterialReceipts", "productType" + i).equals("serial")) {
            serialNumberProductInPurchase(dataFile,"MaterialReceipts","productCode"+i ,"seriesText","quantity" + i,"freeQuantity" + i, i);
        }

        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='MRP Amount Row "+i+", Not sorted.']"), common.getData(dataFile,"MaterialReceipts", "mrpAmount" + i),"MRP Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "MaterialReceipts","grossAmount" + i),"Gross Amount mismatch");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile,"MaterialReceipts", "HSNCode");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']"),common.getData(dataFile,"MaterialReceipts","taxableValue"+i),"Taxable value mismatch");
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']"), common.getData(dataFile, "MaterialReceipts","igstAmount" + i),"IGST mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']"), common.getData(dataFile,"MaterialReceipts", "cessAmount" +i),"CESS mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"MaterialReceipts", "expectedGStExclusive" +i), "GST Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "MaterialReceipts","netAmount" +i), "Net Amount mismatch");
    }

}
