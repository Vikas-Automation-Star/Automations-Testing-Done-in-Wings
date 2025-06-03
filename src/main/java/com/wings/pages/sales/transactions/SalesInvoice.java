package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SalesInvoice extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;

    public SalesInvoice(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String salesInvoice() throws InterruptedException, IOException, ParseException, AWTException {
        long start = System.nanoTime();
        System.out.println("Sales Invoice startTime executed in :" + start);
        navigateToSalesInvoiceMenu();
        Thread.sleep(2000);

        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        //branch selection
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "salesInvoice", "branch");
        enterInput("xpath", "//Edit[@Name='Location *']", dataFile, "salesInvoice", "location");
        enterInput("xpath", "//Edit[@Name='Cash/Party Code']", dataFile, "salesInvoice", "partyCode");
        Thread.sleep(1500);
        gstTransactionType("Inter State Sales to Registered Dealers");
        Thread.sleep(1000);
        enterInput("xpath","//Edit[@Name='Sales A/c Code']",dataFile,"salesInvoice","salesAccountCode");
        generalInfoSliderHandle(250);
        enterInput("xpath", "//Edit[@Name='TCS Trans Nature']", dataFile,"salesInvoice", "tcsTransactionNature");
        common.inputText("xpath", "//Edit[@Name='Invoice Type']", common.getData(dataFile,"salesInvoice", "invoice"));
        Thread.sleep(1000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile,"salesInvoice", "priceList");
        generalInfoSliderHandle(400);
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "salesInvoice","executive");
        generalInfoSliderHandle(-500);
        //F3-Items
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"salesInvoice", "productCount")); i++) {
            addProduct(i);
        }
        enterChargesAndDeductions(dataFile, "salesInvoice");
        enterOtherCharges(dataFile, "salesInvoice");
        navigateToBillsPayablesTab();
        common.deleteInvalidRows();
        //collections
        enterCashinSIAO(dataFile, "salesInvoice");
        enterChequesinSIAO(dataFile, "salesInvoice");
        enterPostDatedChequesinSIAO(dataFile, "salesInvoice");
        enterChequesPDCinSIAO(dataFile, "salesInvoice");
        enterCreditCardinSIAO(dataFile, "salesInvoice");
        //verify all the fields in summary are fetching data
        navigateToPaytymTab();
        for (int j = 0; j < 2; j++) {
            robot.keyPress(KeyEvent.VK_RIGHT);
            robot.keyRelease(KeyEvent.VK_RIGHT);
        }
        enterOtherInfo(dataFile,"salesInvoice");
        //summary
        navigateToOtherInfoTab();
        for (int i = 0; i < 5; i++) {
            robot.keyPress(KeyEvent.VK_RIGHT);
            robot.keyRelease(KeyEvent.VK_RIGHT);
        }
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        netAmountPresentInSummary();
        cessPresentInSummary();
        iGSTPresentInSummary();
        tcsAmountPresentInSummary();
        tcsTaxableValuePresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        Thread.sleep(1000);
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("name", "Sales Book");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID,dataFile,"salesInvoice");

        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Sales Invoice", duration / 1000000000);

        return newVoucherID;
    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile,"salesInvoice", "productType" + i).equals("general")) {
            generalProduct(dataFile, "salesInvoice","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        } else if (common.getData(dataFile,"salesInvoice", "productType" + i).equals("multiBatch")) {
            multiBatchProduct(dataFile,"salesInvoice", "productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        }else if (common.getData(dataFile,"salesInvoice", "productType" + i).equals("serial")) {
            serialNumberProduct(dataFile,"salesInvoice", "productCode" + i, i);
        }

        //validate
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 650, 0);
        //MRP and gross Amount
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='MRP Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"salesInvoice", "mrpAmount" + i),"MRP Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "salesInvoice","grossAmount" + i),"Gross Amount mismatch");

        enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile,"salesInvoice", "HSNCode");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 250, 0);

        //taxable
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']"),common.getData(dataFile,"salesInvoice","taxableValue"+i),"Taxable value mismatch");

        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }

        //check tax and net Amount
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']"), common.getData(dataFile, "salesInvoice","igstAmount" + i),"IGST mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']"), common.getData(dataFile,"salesInvoice", "cessAmount" +i),"CESS mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"salesInvoice", "expectedGStExclusive" +i), "GST Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "salesInvoice","netAmount" +i), "Net Amount mismatch");
    }
}