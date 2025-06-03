package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import java.awt.*;
import java.io.IOException;

public class ProformaSalesInvoice extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;

    public ProformaSalesInvoice(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void proformaSalesInvoice() throws InterruptedException, IOException, ParseException, AWTException {
        long start = System.nanoTime();
        System.out.println("proforma Sales Invoice startTime executed in :" + start);
        navigateToProformaSalesInvoiceMenu();
        Thread.sleep(2000);

        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        //branch selection
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "proformaSalesInvoice", "branch");
        enterInput("xpath", "//Edit[@Name='Location *']", dataFile, "proformaSalesInvoice", "location");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "proformaSalesInvoice", "partyCode");
        Thread.sleep(1500);
        gstTransactionType("Inter State Sales to Registered Dealers");
        Thread.sleep(1000);
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile,"proformaSalesInvoice", "priceList");
        generalInfoSliderHandle(400);
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "proformaSalesInvoice","executive");
        generalInfoSliderHandle(-500);
        //F3-Items
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"proformaSalesInvoice", "productCount")); i++) {
            addProduct(i);
        }
        enterChargesAndDeductions(dataFile, "proformaSalesInvoice");
        enterOtherCharges(dataFile, "proformaSalesInvoice");
        enterOtherInfo(dataFile,"proformaSalesInvoice");
        //summary
        common.clickElement("xpath","//TabItem[@Name='  Ctrl-F7 Summary  ']");
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        netAmountPresentInSummary();
        cessPresentInSummary();
        iGSTPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        //terms
        termsAndConditions(dataFile, "proformaSalesInvoice");
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        Thread.sleep(1000);
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Proforma Sales Invoices'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport("PSI1",dataFile,"proformaSalesInvoice");

        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Proforma Sales Invoice against Deliveries", duration / 1000000000);
    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile,"proformaSalesInvoice", "productType" + i).equals("general")) {
            generalProduct(dataFile, "proformaSalesInvoice","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        } else if (common.getData(dataFile,"proformaSalesInvoice", "productType" + i).equals("multiBatch")) {
            multiBatchProductPurchase(dataFile,"proformaSalesInvoice", "productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        }else if (common.getData(dataFile,"proformaSalesInvoice", "productType" + i).equals("serial")) {
            serialNumProductInProformaPurchaseVouchers(dataFile,"proformaSalesInvoice", "productCode" + i, "quantity" + i,"freeQuantity" + i,i);
        }
        //validate
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 650, 0);
        //MRP and gross Amount
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='MRP Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"proformaSalesInvoice", "mrpAmount" + i),"MRP Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "proformaSalesInvoice","grossAmount" + i),"Gross Amount mismatch");
        enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile,"proformaSalesInvoice", "HSNCode");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 250, 0);
        //taxable
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']"),common.getData(dataFile,"proformaSalesInvoice","taxableValue"+i),"Taxable value mismatch");

        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        //check tax and net Amount
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']"), common.getData(dataFile, "proformaSalesInvoice","igstAmount" + i),"IGST mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']"), common.getData(dataFile,"proformaSalesInvoice", "cessAmount" +i),"CESS mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"proformaSalesInvoice", "expectedGStExclusive" +i), "GST Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "proformaSalesInvoice","netAmount" +i), "Net Amount mismatch");
    }
}