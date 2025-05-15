package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;

public class PurchaseReturns extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked=false;

    public PurchaseReturns(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String purchaseReturns() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps("Purchase","Invoices", "Purchase Returns");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"PurchaseReturns","branch");
//        enterInput("xpath","//Edit[@Name='Location *']",dataFile,"location");
//        enterInput("xpath","//Edit[@Name='Trans Currency *']",dataFile,"currency");
        enterInput("xpath", "//Edit[@Name='Cash/Party Code']",dataFile,"PurchaseReturns", "partyCode");
        Thread.sleep(1000);
        enterInput("xpath", "//Edit[@Name='Purchase A/c Code']",dataFile,"PurchaseReturns", "PurchaseReturnAccCode");
        enableCheckboxSelection("//CheckBox[@Name='ApplyTCS']");
        enterInput("xpath","//Edit[@Name='TCS Trans Nature']", dataFile,"PurchaseReturns","tcsNature");
        inputTextWithValidation("xpath", "//Edit[@Name='Supplier Bill No *']", String.valueOf(common.getRandom()));
        inputTextWithValidation("xpath", "//Edit[@Name='Supplier Bill Date *']", Time.timeStamp());
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, "PurchaseReturns","priceList");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "PurchaseReturns","executive");
//        //F3-Items
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"PurchaseReturns", "productCount")); i++) {
            addProduct(i);
        }
        chargesAndDeductionsCalculations1(dataFile,"PurchaseReturns", "charges","deductions","chargesAcc","deductionsAcc", "chargesAmount", "deductionsAmount", "chargesRowCount");
        enterOtherCharges(dataFile,"PurchaseReturns");
        navigateToBillsPayablesTab();
        common.deleteInvalidRows();
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        validateTCS(dataFile,"PurchaseReturns");
        enterCash(dataFile,"PurchaseReturns");
        enterCheques(dataFile,"PurchaseReturns");
        enterPostDatedCheques(dataFile,"PurchaseReturns");
        enterChequesPDCInPurchaseReturns(dataFile,"PurchaseReturns");
        enterOtherInfo(dataFile,dataFile);
        termsAndConditions(dataFile,"PurchaseReturns");
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
        tcsTaxableValuePresentInSummary();
        tcsAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        cashPresentInSummary();
        chequesPresentInSummary();
        postDatedChequesPresentInSummary();
        chequesPDCPresentInSummary();
        receiptsValuePresentInSummary();
        receiptsValuePresentInSummary();

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
        verifyReport(newVoucherID,dataFile,"PurchaseReturns");
//        deleteSingleTransaction(newVoucherID,dataFile);
        return newVoucherID;
    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile,"PurchaseReturns", "productType" + i).equals("general")) {
            generalProduct(dataFile,"PurchaseReturns", "productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        } else if (common.getData(dataFile,"PurchaseReturns", "productType" + i).equals("multiBatch")) {
            multiBatchProduct(dataFile, "PurchaseReturns","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        }else if (common.getData(dataFile,"PurchaseReturns", "productType" + i).equals("serial")) {
            serialNumberProduct(dataFile,"PurchaseReturns","productCode"+i , i);
        }

        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='MRP Amount Row "+i+", Not sorted.']"), common.getData(dataFile,"PurchaseReturns", "mrpAmount" + i),"MRP Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "PurchaseReturns","grossAmount" + i),"Gross Amount mismatch");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile,"PurchaseReturns", "HSNCode");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']"),common.getData(dataFile,"PurchaseReturns","taxableValue"+i),"Taxable value mismatch");
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']"), common.getData(dataFile, "PurchaseReturns","igstAmount" + i),"IGST mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']"), common.getData(dataFile,"PurchaseReturns", "cessAmount" +i),"CESS mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"PurchaseReturns", "expectedGStExclusive" +i), "GST Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "PurchaseReturns","netAmount" +i), "Net Amount mismatch");
    }
}
