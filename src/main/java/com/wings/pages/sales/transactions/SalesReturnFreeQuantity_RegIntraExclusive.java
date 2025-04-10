package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SalesReturnFreeQuantity_RegIntraExclusive extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;

    public SalesReturnFreeQuantity_RegIntraExclusive(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void regIntraExclusiveSalesReturn(String voucherID) throws InterruptedException, IOException, ParseException, AWTException {
        navigateToSalesReturnMenu();
        Thread.sleep(2000);
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        //branch selection
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "salesReturn", "branch");
        enterInput("xpath", "//Edit[@Name='Location *']", dataFile, "salesReturn", "location");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Branch *']"), common.getData(dataFile, "salesReturn", "branch"), "Branch is not validated");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Location *']"), common.getData(dataFile, "salesReturn", "location"), "Location is not validated");
        inputTextWithValidation("xpath", "//Edit[@Name='Sales Invoice No']", voucherID);
        inputTextWithValidation("xpath", "//Edit[@Name='Sales Invoice Date']", Time.timeStamp());
        enterInput("xpath", "//Edit[@Name='Cash/Party Code']", dataFile, "salesReturn", "partyCode");
        Thread.sleep(1500);
        gstTransactionType("Intra State Sales Returns from Registered Dealers");
        Thread.sleep(1000);
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Cash/Party *']"), common.getData(dataFile, "salesReturn", "partyName"));
        enterInput("xpath", "//Edit[@Name='Sales Return A/c Code']", dataFile, "salesReturn", "salesReturnAccountCode");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Sales Return A/c Code']"), common.getData(dataFile, "salesReturn", "salesReturnAccountCode"), "Sales Return Account Code is not validated");
        generalInfoSliderHandle(200);
        enterInput("xpath", "//Edit[@Name='TCS Trans Nature']", dataFile, "salesReturn", "tcsTransactionNature");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='TCS Trans Nature']"), common.getData(dataFile, "salesReturn", "tcsTransactionNature"), "TCS Nature is not validated");
        enterInput("xpath", "//Edit[@Name='Batch Policy']", dataFile, "salesReturn", "batchPolicy");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Batch Policy']"), common.getData(dataFile, "salesReturn", "batchPolicy"), "Batch Policy isn't validated");
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, "salesReturn", "priceList");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Price List']"), common.getData(dataFile, "salesReturn", "priceList"), "Price List isn't validated");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "salesReturn", "executive");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Executive *']"), common.getData(dataFile, "salesReturn", "executive"), "Executive isn't validated");
        generalInfoSliderHandle(-300);
        //F3-Items
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice","productCount")); i++) {
            addProduct(i);
        }
        double itemsNetValue = Double.parseDouble(common.findWebElement("xpath", "//Edit[@AutomationId='NetAmount']").getText().replace(",", ""));
        tcsCalculations(itemsNetValue);

        validateCGSTAmountTabIsNotEmpty();
        validateSGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        navigateToBillsReceivablesTab();
        common.deleteInvalidRows();

        //check for Sales Invoice total value
        System.out.println("dataFile: "+common.getData(dataFile,"salesInvoice","column28").replace(",",""));
        common.clickElement("xpath","//TabItem[contains(@Name,'Invoice Details')]");
        Assert.assertEquals(String.format("%.1f",Double.parseDouble(common.getData(dataFile,"salesInvoice","column28").replace(",",""))),
                String.format("%.1f",Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Invoice Value *']").getText().replace(",",""))));
        System.out.println("getText: "+common.findWebElement("xpath","//Edit[@Name='Invoice Value *']").getText().replace(",",""));

        //verify all the fields in summary are fetching data
        navigateToOtherInfoTab();
        for (int j = 0; j < 2; j++) {
            Robot robot=new Robot();
            robot.keyPress(KeyEvent.VK_RIGHT);
            robot.keyRelease(KeyEvent.VK_RIGHT);
        }
        quantityPresentInSummary();
        freeQuantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        netAmountPresentInSummary();
        cessPresentInSummary();
        tcsAmountPresentInSummary();
        tcsTaxableValuePresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID).replace(" ","");
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
//        Thread.sleep(2500);
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Returns'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID,dataFile,"salesReturn");

    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile, "salesInvoice","productType" + i).equals("general")) {
            generalProduct(dataFile, "salesInvoice","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        } else if (common.getData(dataFile,"salesInvoice", "productType" + i).equals("multiBatch")) {
            multiBatchProductPurchase(dataFile,"salesInvoice", "productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        }else if (common.getData(dataFile, "salesInvoice","productType" + i).equals("serial")) {
            serialNumberProductInPurchase(dataFile, "salesInvoice","productCode" + i,"serialText","quantity" + i,"freeQuantity" + i, i);
        }

        //validate
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 650, 0);
        //MRP and gross Amount
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='MRP Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"salesInvoice", "mrpAmount" + i),"MRP Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "salesInvoice","grossAmount" + i),"Gross Amount mismatch");
        //discount
        enterData("xpath", "//Edit[@Name='Voucher Disc % Row " + i + ", Not sorted.']", dataFile,"salesInvoice", "voucherDiscount" + i);
        enterData("xpath", "//Edit[@Name='Party Disc % Row " + i + ", Not sorted.']", dataFile,"salesInvoice", "partyDiscount" + i);
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Voucher Disc Row " + i +", Not sorted.']"),common.getData(dataFile,"salesInvoice","voucherDiscAmount"+i),"Voucher discount value mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Party Disc Row "+ i +", Not sorted.']"),common.getData(dataFile,"salesInvoice","partyDiscAmount"+i),"Party discount value mismatch");

        enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile,"salesInvoice", "HSNCode");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 250, 0);

        //taxable
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']"),common.getData(dataFile,"salesInvoice","taxableValue"+i),"Taxable value mismatch");

        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }

        //check tax and net Amount
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='CGST Row " + i + ", Not sorted.']"), common.getData(dataFile, "salesInvoice","cgstAmount" + i),"SGST mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='SGST Row " + i + ", Not sorted.']"), common.getData(dataFile, "salesInvoice","sgstAmount" + i),"SGST mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']"), common.getData(dataFile,"salesInvoice", "cessAmount" +i),"CESS mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"salesInvoice", "expectedGStExclusive" +i), "GST Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "salesInvoice","netAmount" +i), "Net Amount mismatch");
    }
}