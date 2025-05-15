package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;

public class PurchaseVoucher extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked=false;

    public PurchaseVoucher(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String purchaseVoucher() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps("Purchase","Invoices", "Purchase Vouchers");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"PurchaseVoucher","branch");
//        enterInput("xpath","//Edit[@Name='Location *']",dataFile,"location");
//        enterInput("xpath","//Edit[@Name='Trans Currency *']",dataFile,"currency");
        enterInput("xpath", "//Edit[@Name='Cash/Party Code']",dataFile,"PurchaseVoucher", "partyCode");
        Thread.sleep(1000);
        enterInput("xpath", "//Edit[@Name='Purchase A/c Code']",dataFile,"PurchaseVoucher", "PurchaseAccCode");
        inputTextWithValidation("xpath", "//Edit[@Name='Supplier Bill No *']", String.valueOf(common.getRandom()));
        inputTextWithValidation("xpath", "//Edit[@Name='Supplier Bill Date *']",Time.timeStamp());
        enterInput("xpath", "//Edit[@Name='Batch Policy']",dataFile,"PurchaseVoucher", "batchPolicy");
        enableCheckboxSelection("//CheckBox[@Name='Apply TCS']");
        enterInput("xpath","//Edit[@Name='TCS Trans Nature']", dataFile,"PurchaseVoucher","tcsNature");
        enableCheckboxSelection("//CheckBox[@Name='Deduct TDS']");
        enterInput("xpath","//Edit[@Name='TDS Trans Nature']", dataFile,"PurchaseVoucher","tdsNature");
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, "PurchaseVoucher","priceList");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "PurchaseVoucher","executive");
//        //F3-Items
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"PurchaseVoucher", "productCount")); i++) {
            addProduct(i);
        }
        enterServices(dataFile,"PurchaseVoucher");
        chargesAndDeductionsCalculations1(dataFile,"PurchaseVoucher", "charges","deductions","chargesAcc","deductionsAcc", "chargesAmount", "deductionsAmount", "chargesRowCount");
        enterOtherCharges(dataFile,"PurchaseVoucher");
        enterOtherDeductions(dataFile,"PurchaseVoucher");
        navigateToBillsReceivablesTab();
        common.deleteInvalidRows();
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        validateTDS(dataFile,"PurchaseVoucher");
        validateTCS(dataFile,"PurchaseVoucher");
        moveToRight(10);
        enterOtherCosts(dataFile,"PurchaseVoucher");
        navigateToItemsOtherCosts();
        enterCash(dataFile,"PurchaseVoucher");
        enterChequesInPurchase(dataFile,"PurchaseVoucher");
        enterPostDatedChequesInPurchase(dataFile,"PurchaseVoucher");
        enterChequesPDCInPurchase(dataFile,"PurchaseVoucher");
        enterOtherInfo(dataFile,dataFile);
        termsAndConditions(dataFile,"PurchaseVoucher");
        navigateToSummaryTab();
        quantityPresentInSummary();
        servicesAmountPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        iGSTPresentInSummary();
        cessPresentInSummary();
        servicesIGSTPresentInSummary();
        servicesCESSPresentInSummary();
        netAmountPresentInSummary();
        chargesPresentInSummary();
        deductionsPresentInSummary();
        otherChargesPresentInSummary();
        otherChargesIGSTPresentInSummary();
        otherChargesCESSPresentInSummary();
        otherDeductionsPresentInSummary();
        otherDeductionsSGSTPresentInSummary();
        otherDeductionsCGSTPresentInSummary();
        otherCostsAmountPresentInSummary();
        tcsTaxableValuePresentInSummary();
        tcsAmountPresentInSummary();
        tdsAmountPresentInSummary();
        totalValueAfterTdsPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        cashPresentInSummary();
        chequesPresentInSummary();
        postDatedChequesPresentInSummary();
        chequesPDCPresentInSummary();
        paymentsValuePresentInSummary();
        payableAMountPresentInSummary();

        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID).replace(" ","");
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
        Thread.sleep(1000);
        navigateToMastersWhen3Steps("Purchase","Invoices","Purchase Book");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
//        verifyReport(newVoucherID,dataFile,"PurchaseVoucher");
//        deleteSingleTransaction(newVoucherID,dataFile);
        return newVoucherID;
    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile,"PurchaseVoucher", "productType" + i).equals("general")) {
            generalProduct(dataFile,"PurchaseVoucher", "productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        } else if (common.getData(dataFile,"PurchaseVoucher", "productType" + i).equals("multiBatch")) {
            multiBatchProductPurchase(dataFile, "PurchaseVoucher","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        }else if (common.getData(dataFile,"PurchaseVoucher", "productType" + i).equals("serial")) {
            serialNumberProductInPurchase(dataFile,"PurchaseVoucher","productCode"+i ,"seriesText","quantity" + i,"freeQuantity" + i, i);
        }

        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='MRP Amount Row "+i+", Not sorted.']"), common.getData(dataFile,"PurchaseVoucher", "mrpAmount" + i),"MRP Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "PurchaseVoucher","grossAmount" + i),"Gross Amount mismatch");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile,"PurchaseVoucher", "HSNCode");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']"),common.getData(dataFile,"PurchaseVoucher","taxableValue"+i),"Taxable value mismatch");
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']"), common.getData(dataFile, "PurchaseVoucher","igstAmount" + i),"IGST mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']"), common.getData(dataFile,"PurchaseVoucher", "cessAmount" +i),"CESS mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"PurchaseVoucher", "expectedGStExclusive" +i), "GST Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "PurchaseVoucher","netAmount" +i), "Net Amount mismatch");
        enterItemsOtherCosts(dataFile,"PurchaseVoucher",i);
    }
}
