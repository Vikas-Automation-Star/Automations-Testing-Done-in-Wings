package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import java.awt.*;
import java.io.IOException;

public class SalesQuotations extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked=false;

    public SalesQuotations(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String salesQuotation() throws InterruptedException, IOException, ParseException, AWTException {
        long start = System.nanoTime();
        System.out.println("Sales Quotations startTime executed in :"+start);

        navigateToSalesQuotationsMenu();

        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        //branch selection
        enterInput("xpath", "//Edit[@Name='Branch *']",dataFile, "salesQuotation","branch");
        enterInput("xpath", "//Edit[@Name='Party Code']",dataFile, "salesQuotation","partyCode");
        Thread.sleep(2500);
        gstTransactionType("Inter State Sales to Registered Dealers");
        Thread.sleep(1000);
        enterInput("xpath", "//Edit[@Name='Price List']",dataFile,"salesQuotation", "priceList");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "salesQuotation","executive");

        //F3-Items
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"salesQuotation", "productCount")); i++) {
            addProduct(i);
        }
        enterChargesAndDeductions(dataFile,"salesQuotation");
        enterOtherCharges(dataFile,"salesQuotation");
        enterOtherInfo();

        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        //verify all the fields in summary are fetching data
        common.clickElement("xpath","//TabItem[@Name='  Ctrl-F7 Summary  ']");
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        cessPresentInSummary();
        iGSTPresentInSummary();
        netAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        termsAndConditions(dataFile,"salesQuotation");
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        Thread.sleep(1000);
        common.clickElement("name", "Sales");
        common.clickElement("name", "Quotations");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Quotations'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID,dataFile,"salesQuotation");

        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Sales Quotations",duration/1000000000);
        return newVoucherID;
    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile,"salesQuotation", "productType" + i).equals("general")) {
            generalProduct_New(dataFile,"salesQuotation", "productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        } else if (common.getData(dataFile,"salesQuotation", "productType" + i).equals("multiBatch")) {
            multiBatchProductDirectQuantity(dataFile, "salesQuotation","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        }else if (common.getData(dataFile,"salesQuotation", "productType" + i).equals("serial")) {
            serialNumProductDirectQuantity(dataFile,"salesQuotation", "productCode" + i,"quantity" + i,"freeQuantity" + i, i);
        }
        //MRP and gross Amount
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='MRP Amount Row "+i+", Not sorted.']"), common.getData(dataFile,"salesQuotation", "mrpAmount" + i),"MRP Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "salesQuotation","grossAmount" + i),"Gross Amount mismatch");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile,"salesQuotation", "HSNCode");

        //taxable
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']"),common.getData(dataFile,"salesQuotation","taxableValue"+i),"Taxable value mismatch");
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }

        //check tax and net Amount
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']"), common.getData(dataFile, "salesQuotation","igstAmount" + i),"IGST mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']"), common.getData(dataFile,"salesQuotation", "cessAmount" +i),"CESS mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"salesQuotation", "expectedGStExclusive" +i), "GST Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "salesQuotation","netAmount" +i), "Net Amount mismatch");
    }
}