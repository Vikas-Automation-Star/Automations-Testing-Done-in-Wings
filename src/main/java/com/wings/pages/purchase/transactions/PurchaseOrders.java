package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;

public class PurchaseOrders extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked=false;

    public PurchaseOrders(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String  purchaseOrders() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps("Purchase","Orders","Purchase Orders");
        Thread.sleep(3000);
        String oldVoucherID = oldTTransactionID();
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "PurchaseOrders", "branch");
//        enterInput("xpath", "//Edit[@Name='Transaction Currency *']", dataFile, "Purchase Enquiries", "currency");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "PurchaseOrders", "partyCode");
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, "PurchaseOrders", "priceList");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "PurchaseOrders", "executive");
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "PurchaseOrders", "productCount")); i++) {
            addProduct(i);
        }

        chargesAndDeductionsCalculations1(dataFile,"PurchaseOrders", "charges","deductions","chargesAcc","deductionsAcc", "chargesAmount", "deductionsAmount", "chargesRowCount");
        enterOtherCharges(dataFile,"PurchaseOrders");
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        enterCash(dataFile,"PurchaseOrders");
        enterChequesInPurchase(dataFile,"PurchaseOrders");
        enterPostDatedChequesInPurchase(dataFile,"PurchaseOrders");
        enterChequesPDCInPurchase(dataFile,"PurchaseOrders");
        enterOtherInfo(dataFile,"PurchaseOrders");
        termsAndConditions(dataFile,"PurchaseOrders");
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
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        cashPresentInSummary();
        chequesPresentInSummary();
        postDatedChequesPresentInSummary();
        chequesPDCPresentInSummary();
        paymentValuePresentInSummary();
        transactionSave();
        String transactionId = newTransactionID(oldVoucherID);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Orders'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        verifyReport(transactionId,dataFile,"PurchaseOrders");
        return transactionId;
    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile,"PurchaseOrders", "productType" + i).equals("general")) {
            generalProduct_New(dataFile,"PurchaseOrders", "productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        } else if (common.getData(dataFile,"PurchaseOrders", "productType" + i).equals("multiBatch")) {
            serialNumProductDirectQuantity(dataFile, "PurchaseOrders","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        }else if (common.getData(dataFile,"PurchaseOrders", "productType" + i).equals("serial")) {
            serialNumProductDirectQuantity(dataFile,"PurchaseOrders", "productCode" + i,"quantity" + i,"freeQuantity" + i, i);
        }

        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='MRP Amount Row "+i+", Not sorted.']"), common.getData(dataFile,"PurchaseOrders", "mrpAmount" + i),"MRP Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "PurchaseOrders","grossAmount" + i),"Gross Amount mismatch");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile,"PurchaseOrders", "HSNCode");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        //taxable
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']"),common.getData(dataFile,"PurchaseOrders","taxableValue"+i),"Taxable value mismatch");
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        //check tax and net Amount
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']"), common.getData(dataFile, "PurchaseOrders","igstAmount" + i),"IGST mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']"), common.getData(dataFile,"PurchaseOrders", "cessAmount" +i),"CESS mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"PurchaseOrders", "expectedGStExclusive" +i), "GST Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "PurchaseOrders","netAmount" +i), "Net Amount mismatch");
    }
}
