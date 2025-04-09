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

public class SalesReturnFreeQuantity_UnRegInterInclusive extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;

    public SalesReturnFreeQuantity_UnRegInterInclusive(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void unRegInterInclusive(String voucherNum) throws InterruptedException, IOException, ParseException, AWTException {
        System.out.println("startTime1 :" + System.currentTimeMillis());
        navigateToSalesReturnMenu();
        Thread.sleep(1000);
        String oldVoucherID = oldTTransactionID();
        System.out.println("oldID: " + oldVoucherID);
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "SalesReturn", "branch");
        enterInput("xpath", "//Edit[@Name='Location *']", dataFile, "SalesReturn", "location");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Branch *']"), common.getData(dataFile, "SalesReturn", "branch"), "Branch is not validated");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Location *']"), common.getData(dataFile, "SalesReturn", "location"), "Location is not validated");
        inputTextWithValidation("xpath", "//Edit[@Name='Sales Invoice No']", voucherNum);
        inputTextWithValidation("xpath", "//Edit[@Name='Sales Invoice Date']", Time.timeStamp());
        enterInput("xpath", "//Edit[@Name='Cash/Party Code']", dataFile, "SalesReturn", "partyCode");
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Cash/Party *']"),common.getData(dataFile,"SalesReturn","partyName"));
        enterInput("xpath","//Edit[@Name='Sales Return A/c Code']",dataFile,"SalesReturn","salesReturnAccountCode");
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Sales Return A/c Code']"),common.getData(dataFile,"SalesReturn","salesReturnAccountCode"),"Sales Return Account Code is not validated");
        generalInfoSliderHandle(200);
        enterInput("xpath", "//Edit[@Name='TCS Trans Nature']", dataFile,"SalesReturn", "tcsTransactionNature");
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='TCS Trans Nature']"),common.getData(dataFile,"SalesReturn","tcsTransactionNature"),"TCS Nature is not validated");
        enterInput("xpath", "//Edit[@Name='Batch Policy']", dataFile, "SalesReturn","batchPolicy");
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Batch Policy']"),common.getData(dataFile,"SalesReturn","batchPolicy"),"Batch Policy isn't validated");
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile,"SalesReturn", "priceList");
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Price List']"),common.getData(dataFile,"SalesReturn","priceList"),"Price List isn't validated");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "SalesReturn","executive");
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Executive *']"),common.getData(dataFile,"SalesReturn","executive"),"Executive isn't validated");
        generalInfoSliderHandle(-300);

        System.out.println("EndTime1 :" + System.currentTimeMillis());
        System.out.println("startTime2 :" + System.currentTimeMillis());
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"salesInvoice", "productCount")); i++) {
            addProduct(i);
        }
        System.out.println("EndTime2 :" + System.currentTimeMillis());

        double itemsNetValue = Double.parseDouble(common.findWebElement("xpath", "//Edit[@AutomationId='NetAmount']").getText().replace(",", ""));
        tcsCalculations(itemsNetValue);

        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        navigateToBillsReceivablesTab();
        common.deleteInvalidRows();

        //validate Invoice Details
        System.out.println("dataFile: "+common.getData(dataFile,"salesInvoice","column28").replace(",",""));
        common.clickElement("xpath","//TabItem[contains(@Name,'Invoice Details')]");
        Assert.assertEquals(String.format("%.1f",Double.parseDouble(common.getData(dataFile,"salesInvoice","column28").replace(",",""))),
                String.format("%.1f",Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Invoice Value *']").getText().replace(",",""))));
        System.out.println("getText: "+common.findWebElement("xpath","//Edit[@Name='Invoice Value *']").getText().replace(",",""));
        //verify all the fields in summary are fetching data
     navigateToOtherInfoTab();
        for (int j = 0; j < 2; j++) {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_RIGHT);
            robot.keyRelease(KeyEvent.VK_RIGHT);
        }
        quantityPresentInSummary();
        freeQuantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        cessPresentInSummary();
        netAmountPresentInSummary();
        tcsAmountPresentInSummary();
        tcsTaxableValuePresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        //save
        transactionSave();
        String newVoucherID = newTransactionID(oldVoucherID).replace(" ", "");
        System.out.println("newID: " + newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID, "both ID's should not Equal when we perform transaction");
        Thread.sleep(1000);
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Returns'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID, dataFile,"SalesReturn");
    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile, "salesInvoice","productType" + i).equals("general")) {
            generalProduct(dataFile,"salesInvoice", "productCode" + i, "quantity" + i, "freeQuantity" + i, i);
        } else if (common.getData(dataFile,"salesInvoice", "productType" + i).equals("multiBatch")) {
            multiBatchProductPurchase(dataFile,"salesInvoice", "productCode" + i, "quantity" + i, "freeQuantity" + i, i);
        } else if (common.getData(dataFile,"salesInvoice", "productType" + i).equals("serial")) {
            serialNumberProductInPurchase(dataFile,"salesInvoice", "productCode" + i, "serialText", "quantity" + i, "freeQuantity" + i, i);
        }

        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 650, 0);
        //MRP and gross Amount
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='MRP Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"SalesReturn", "mrpAmount" + i),"MRP Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "SalesReturn","grossAmount" + i),"Gross Amount mismatch");
        //discount
        enterData("xpath", "//Edit[@Name='Voucher Disc % Row " + i + ", Not sorted.']", dataFile,"salesInvoice", "voucherDiscount" + i);
        enterData("xpath", "//Edit[@Name='Party Disc % Row " + i + ", Not sorted.']", dataFile,"salesInvoice", "partyDiscount" + i);
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Voucher Disc Row " + i +", Not sorted.']"),common.getData(dataFile,"SalesReturn","voucherDiscAmount"+i),"Voucher discount value mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Party Disc Row "+ i +", Not sorted.']"),common.getData(dataFile,"SalesReturn","partyDiscAmount"+i),"Party discount value mismatch");

        enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile,"salesInvoice", "HSNCode");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 250, 0);

        //taxable
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']"),common.getData(dataFile,"SalesReturn","taxableValue"+i),"Taxable value mismatch");

        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }

        //check tax and net Amount
            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']"), common.getData(dataFile, "SalesReturn","igstAmount" + i),"IGST mismatch");
            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']"), common.getData(dataFile,"SalesReturn", "cessAmount" +i),"CESS mismatch");
            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"SalesReturn", "expectedGStInclusive" +i), "GST Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "SalesReturn","netAmount" +i), "Net Amount mismatch");
    }
}