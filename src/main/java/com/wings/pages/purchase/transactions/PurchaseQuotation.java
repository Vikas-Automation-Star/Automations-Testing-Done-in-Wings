package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;

public class PurchaseQuotation extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked=false;

    public PurchaseQuotation(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String  purchaseQuotation() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps("Purchase","Quotations","Purchase Quotations");
        Thread.sleep(3000);
        String oldVoucherID = oldTTransactionID();
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "Purchase Quotations", "branch");
//        enterInput("xpath", "//Edit[@Name='Transaction Currency *']", dataFile, "Purchase Enquiries", "currency");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "Purchase Quotations", "partyCode");
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, "Purchase Quotations", "priceList");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "Purchase Quotations", "executive");
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "Purchase Quotations", "productCount")); i++) {
            addProduct(i);
        }

        chargesAndDeductionsCalculations1(dataFile,"Purchase Quotations", "charges","deductions","chargesAcc","deductionsAcc", "chargesAmount", "deductionsAmount", "chargesRowCount");
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        enterOtherInfo(dataFile,"Purchase Quotations");
        termsAndConditions(dataFile,"Purchase Quotations");
        navigateToSummaryTab();
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossAmountMinusDiscountPresentInSummary();
        iGSTPresentInSummary();
        cessPresentInSummary();
        chargesPresentInSummary();
        deductionsPresentInSummary();
        netAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        transactionSave();
        String transactionId = newTransactionID(oldVoucherID);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Quotations");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Quotations'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        verifyReport(transactionId,dataFile,"Purchase Quotations");
        return transactionId;
    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile,"Purchase Quotations", "productType" + i).equals("general")) {
            generalProduct_New(dataFile,"Purchase Quotations", "productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        } else if (common.getData(dataFile,"Purchase Quotations", "productType" + i).equals("multiBatch")) {
            serialNumProductDirectQuantity(dataFile, "Purchase Quotations","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        }else if (common.getData(dataFile,"Purchase Quotations", "productType" + i).equals("serial")) {
            serialNumProductDirectQuantity(dataFile,"Purchase Quotations", "productCode" + i,"quantity" + i,"freeQuantity" + i, i);
        }

        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='MRP Amount Row "+i+", Not sorted.']"), common.getData(dataFile,"Purchase Quotations", "mrpAmount" + i),"MRP Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "Purchase Quotations","grossAmount" + i),"Gross Amount mismatch");
        enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile,"Purchase Quotations", "HSNCode");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        //taxable
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']"),common.getData(dataFile,"Purchase Quotations","taxableValue"+i),"Taxable value mismatch");
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        //check tax and net Amount
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']"), common.getData(dataFile, "Purchase Quotations","igstAmount" + i),"IGST mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']"), common.getData(dataFile,"Purchase Quotations", "cessAmount" +i),"CESS mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"Purchase Quotations", "expectedGStExclusive" +i), "GST Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "Purchase Quotations","netAmount" +i), "Net Amount mismatch");
    }
}
