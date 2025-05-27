package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;

public class ProformaPurchaseVouchers extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked=false;

    public ProformaPurchaseVouchers(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String proformaPurchaseVouchers() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps("Purchase","Invoices", "Proforma Purchase Vouchers");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"proformaPurchaseVoucher","branch");
        enterInput("xpath", "//Edit[@Name='Party Code']",dataFile,"proformaPurchaseVoucher", "partyCode");
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, "proformaPurchaseVoucher","priceList");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "proformaPurchaseVoucher","executive");
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"proformaPurchaseVoucher", "productCount")); i++) {
            addProduct(i);
        }
        chargesAndDeductionsCalculations1(dataFile,"proformaPurchaseVoucher", "charges","deductions","chargesAcc","deductionsAcc", "chargesAmount", "deductionsAmount", "chargesRowCount");
        enterOtherCharges(dataFile,"proformaPurchaseVoucher");
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        enterOtherCosts(dataFile,"proformaPurchaseVoucher");
        enterOtherInfo(dataFile,dataFile);
        termsAndConditions(dataFile,"proformaPurchaseVoucher");
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
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        otherCostsPresentInSummary();

        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        String originalID =newTransactionID(oldVoucherID).replace(" ","");
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
        Thread.sleep(1000);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Proforma Purchase Vouchers'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID,dataFile,"proformaPurchaseVoucher");
        deleteSingleTransaction(originalID);
        return newVoucherID;
    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile,"proformaPurchaseVoucher", "productType" + i).equals("general")) {
            generalProduct(dataFile,"proformaPurchaseVoucher", "productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        } else if (common.getData(dataFile,"proformaPurchaseVoucher", "productType" + i).equals("multiBatch")) {
            multiBatchProductPurchase(dataFile, "proformaPurchaseVoucher","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        }else if (common.getData(dataFile,"proformaPurchaseVoucher", "productType" + i).equals("serial")) {
            serialNumProductInProformaPurchaseVouchers(dataFile,"proformaPurchaseVoucher","productCode"+i ,"quantity"+i,"freeQuantity"+i, i);
        }

        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='MRP Amount Row "+i+", Not sorted.']"), common.getData(dataFile,"proformaPurchaseVoucher", "mrpAmount" + i),"MRP Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "proformaPurchaseVoucher","grossAmount" + i),"Gross Amount mismatch");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile,"proformaPurchaseVoucher", "HSNCode");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']"),common.getData(dataFile,"proformaPurchaseVoucher","taxableValue"+i),"Taxable value mismatch");
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']"), common.getData(dataFile, "proformaPurchaseVoucher","igstAmount" + i),"IGST mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']"), common.getData(dataFile,"proformaPurchaseVoucher", "cessAmount" +i),"CESS mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"proformaPurchaseVoucher", "expectedGStExclusive" +i), "GST Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "proformaPurchaseVoucher","netAmount" +i), "Net Amount mismatch");
    }

}
