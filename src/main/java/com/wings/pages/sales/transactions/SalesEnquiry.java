package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
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
        navigateToSalesEnquiryMenu();

        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        //branch selection
        enterInput("xpath", "//Edit[@Name='Branch *']",dataFile, "salesEnquiry","branch");

//            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Branch *']"), common.getData(dataFile, "salesEnquiry", "branch"), "Branch is not validated");
        enterInput("xpath", "//Edit[@Name='Party Code']",dataFile, "salesEnquiry","partyCode");
        Thread.sleep(2500);
        gstTransactionType("Inter State Sales to Registered Dealers");
        Thread.sleep(1000);
//            Assert.assertEquals(common.getText("xpath","//Edit[@Name='Party Account *']"),common.getData(dataFile,"salesEnquiry","partyName"));
        enterInput("xpath", "//Edit[@Name='Price List']",dataFile,"salesEnquiry", "priceList");
//            Assert.assertEquals(common.getText("xpath","//Edit[@Name='Price List']"),common.getData(dataFile,"salesEnquiry","priceList"),"Price List isn't validated");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "salesEnquiry","executive");
//            Assert.assertEquals(common.getText("xpath","//Edit[@Name='Executive *']"),common.getData(dataFile,"salesEnquiry","executive"),"Executive isn't validated");
        //F3-Items
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"salesEnquiry", "productCount")); i++) {
            addProduct(i);
        }
        navigateToChargesAndDeductionsTab();
        enterData("xpath", "//Edit[@Name='Charges Or Deductions * Row 0, Not sorted.']", dataFile,"salesEnquiry", "chargesDeductions");
        enterData("xpath", "//Edit[@Name='Account Code Row 0, Not sorted.']", dataFile,"salesEnquiry", "chargesAccount");
        enterData("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']", dataFile,"salesEnquiry", "chargesAmount");

        validateIGSTAmountTabIsNotEmpty();;
        validateCESSAmountTabIsNotEmpty();
        //verify all the fields in summary are fetching data
        common.clickElement("xpath","//TabItem[@Name='  Ctrl-F5 Summary  ']");
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        cessPresentInSummary();
        iGSTPresentInSummary();
        netAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
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
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 450, 0);
        //MRP and gross Amount
//        System.out.println("text at row"+i+common.getText("xpath", "//Edit[@Name='MRP Amount Row "+i+", Not sorted.']"));
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