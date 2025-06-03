package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import java.awt.*;
import java.io.IOException;

public class SalesEnquiry extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked=false;

    public SalesEnquiry(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String salesEnquiry() throws InterruptedException, IOException, ParseException, AWTException {
        long start = System.nanoTime();
        System.out.println("Sales Enquiry: " +start);

        navigateToSalesEnquiryMenu();
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        //branch selection
        enterInput("xpath", "//Edit[@Name='Branch *']",dataFile, "salesEnquiry","branch");
        enterInput("xpath", "//Edit[@Name='Party Code']",dataFile, "salesEnquiry","partyCode");
        Thread.sleep(2500);
        gstTransactionType("Inter State Sales to Registered Dealers");
        Thread.sleep(1000);
        enterInput("xpath", "//Edit[@Name='Price List']",dataFile,"salesEnquiry", "priceList");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "salesEnquiry","executive");
        //F3-Items
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"salesEnquiry", "productCount")); i++) {
            addProduct(i);
        }
        enterChargesAndDeductions(dataFile,"salesEnquiry");
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();

        enterOtherInfo();
        //verify all the fields in summary are fetching data
        common.clickElement("xpath","//TabItem[@Name='  Ctrl-F5 Summary  ']");
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        cessPresentInSummary();
        iGSTPresentInSummary();
        netAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();

        termsAndConditions(dataFile,"salesEnquiry");
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        Thread.sleep(1000);
        common.clickElement("name", "Sales");
        common.clickElement("name", "Enquiries");
        common.clickElement("xpath", "//Menu[@Name='Enquiries']/MenuItem[@Name='Sales Enquiries'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID,dataFile,"salesEnquiry");

        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Sales Enquiry",duration/1000000000);

        return newVoucherID;
    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile,"salesEnquiry", "productType" + i).equals("general")) {
            generalProduct_New(dataFile,"salesEnquiry", "productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        } else if (common.getData(dataFile,"salesEnquiry", "productType" + i).equals("multiBatch")) {
            serialNumProductDirectQuantity(dataFile, "salesEnquiry","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        }else if (common.getData(dataFile,"salesEnquiry", "productType" + i).equals("serial")) {
            serialNumProductDirectQuantity(dataFile,"salesEnquiry", "productCode" + i,"quantity" + i,"freeQuantity" + i, i);
        }

        //validate
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='MRP Amount Row "+i+", Not sorted.']"), common.getData(dataFile,"salesEnquiry", "mrpAmount" + i),"MRP Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "salesEnquiry","grossAmount" + i),"Gross Amount mismatch");
        enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile,"salesEnquiry", "HSNCode");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);

        //taxable
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']"),common.getData(dataFile,"salesEnquiry","taxableValue"+i),"Taxable value mismatch");

        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        //check tax and net Amount
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']"), common.getData(dataFile, "salesEnquiry","igstAmount" + i),"IGST mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']"), common.getData(dataFile,"salesEnquiry", "cessAmount" +i),"CESS mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"salesEnquiry", "expectedGStExclusive" +i), "GST Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "salesEnquiry","netAmount" +i), "Net Amount mismatch");
    }
}