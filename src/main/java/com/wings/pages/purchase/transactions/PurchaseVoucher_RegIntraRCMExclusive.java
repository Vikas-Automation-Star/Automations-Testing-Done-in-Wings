package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.StringUtil;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;

public class PurchaseVoucher_RegIntraRCMExclusive extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;
    boolean rstAmountClicked = false;
    double mrp, grossAmount, unitRate, quantity,gstValue,taxableAmountCalculated,cessValue,netAmount,expectedGSTAmount,calculatedNet;


    public PurchaseVoucher_RegIntraRCMExclusive(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String IntraState_PV_RCMGST_TCS_Exclusive() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps(common.getData(dataFile,"Purchase Voucher","menu"),common.getData(dataFile,"Purchase Voucher","menuItem"), common.getData(dataFile,"Purchase Voucher","subMenuItem"));
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
//        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
//        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"Purchase Voucher","branch");
//        enterInput("xpath","//Edit[@Name='Location *']",dataFile,"location");
//        enterInput("xpath","//Edit[@Name='Trans Currency *']",dataFile,"currency");
        enterInput("xpath", "//Edit[@Name='Cash/Party Code']",dataFile,"Purchase Voucher", "partyCode");
        Thread.sleep(2000);
        gstTransactionType("Intra State Purchase from Registered Dealers with RCM");
        Thread.sleep(1000);
        enableCheckboxSelection("//CheckBox[@Name='Apply RCM']");
        enterInput("xpath", "//Edit[@Name='Purchase A/c Code']",dataFile,"Purchase Voucher", "purchaseAccount");
        inputTextWithValidation("xpath", "//Edit[@Name='Supplier Bill No *']", common.getData(dataFile,"Purchase Voucher", "supplierCode") +common.getRandom());
        inputTextWithValidation("xpath", "//Edit[@Name='Supplier Bill Date *']", common.getData(dataFile, "Purchase Voucher","date") + Time.timeStamp());
        enterInput("xpath", "//Edit[@Name='Batch Policy']",dataFile,"Purchase Voucher", "batchPolicy");
        enableCheckboxSelection("//CheckBox[@Name='Apply TCS']");
        enterInput("xpath","//Edit[@Name='TCS Trans Nature']", dataFile,"Purchase Voucher","tcsNature");
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile,"Purchase Voucher", "priceList");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile,"Purchase Voucher", "executive");
//        //F3-Items
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"Purchase Voucher", "productCount")); i++) {
            addProduct(i);
        }

        double itemsNetValue = Double.parseDouble(common.findWebElement("xpath", "//Edit[@AutomationId='NetAmount']").getText().replace(",", ""));
        tcsCalculations(itemsNetValue);

        validateRCMCGSTAmountTabIsNotEmpty();
        validateRCMSGSTAmountTabIsNotEmpty();
        validateRCMCESSAmountTabIsNotEmpty();
        navigateToBillsReceivablesTab();
        common.deleteInvalidRows();
        //verify all the fields in summary are fetching data
        navigateToTcs();
        for (int j = 0; j <=7; j++) {
            Robot robot=new Robot();
            robot.keyPress(KeyEvent.VK_RIGHT);
            robot.keyRelease(KeyEvent.VK_RIGHT);
        }
        quantityPresentInSummary();
        freeQuantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        RCMSGSTPresentInSummary();
        RCMCGSTPresentInSummary();
        cessPresentInSummary();
        netAmountPresentInSummary();
        tcsAmountPresentInSummary();
        tcsTaxableValuePresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();

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
        verifyReport(newVoucherID,dataFile,"Purchase Voucher");
//        deleteSingleTransaction(newVoucherID);
        return newVoucherID;
    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile,"Purchase Voucher", "productType" + i).equals("general")) {
            generalProduct(dataFile, "Purchase Voucher", "productCode"+ i,"quantity" + i,"freeQuantity" +i, i);
        } else if (common.getData(dataFile,"Purchase Voucher", "productType" + i).equals("multiBatch")) {
            multiBatchProductPurchase(dataFile, "Purchase Voucher" , "productCode" + i,"quantity"+i,"freeQuantity"+i, i);
        } else if (common.getData(dataFile,"Purchase Voucher", "productType" + i).equals("serial")) {
            serialNumberProductInPurchase(dataFile, "Purchase Voucher","productCode"+i,"serialText","quantity"+i,"freeQuantity"+i, i);
        }
        quantity = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']").getText());

        WebElement element = common.findWebElement("xpath", "//Edit[@Name='MRP Row " + i + ", Not sorted.']");
        mrp = Double.parseDouble(element.getText().replace(",", ""));
        System.out.println("mrp:-" + mrp);

        WebElement mrpAmount = common.findWebElement("xpath", "//Edit[@Name='MRP Amount Row " + i + ", Not sorted.']");
        String actualMrpAmountText = mrpAmount.getText().replace(",", "");
        double actualMrpAmount = Double.parseDouble(actualMrpAmountText);

        double expectedMrpAmount = quantity * mrp;
        System.out.println("actual:- " + actualMrpAmount + " -expectedMrp-" + expectedMrpAmount);
        Assert.assertEquals(actualMrpAmount, expectedMrpAmount, "Mismatch in MRP Amount");

        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);

        unitRate = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Unit Rate Row " + i + ", Not sorted.']").getText());
        System.out.println("unitRate:-" + unitRate);

        WebElement gross = common.findWebElement("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']");
        String grossAmountText = gross.getText().replace(",", "");
        grossAmount = Double.parseDouble(grossAmountText);
        System.out.println("gross Amount:- " + grossAmount);

        double grossExpected = unitRate * quantity;
        System.out.println("gross expected:-" + grossExpected);
        Assert.assertEquals(grossAmount, grossExpected, "Mismatch in Gross Amount");

        enterInput("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile, "Purchase Voucher","HSNCode");

        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 450, 0);

        taxableAmountCalculated = Double.parseDouble((common.findWebElement("xpath", "//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']").getText().replace(",", "")));
        System.out.println("taxable: " + taxableAmountCalculated);
        Assert.assertEquals(taxableAmountCalculated, grossAmount);

        //GST
        WebElement gst = common.findWebElement("xpath", "//Edit[@Name='GST Product Category Row " + i + ", Not sorted.']");
        gstValue = StringUtil.extractNumber(gst.getText());
        System.out.println("gst percentage:- " + gstValue);

        WebElement cess = common.findWebElement("xpath", "//Edit[@Name='CESS Product Category Row " + i + ", Not sorted.']");
        cessValue = StringUtil.extractNumber(cess.getText());
        System.out.println("cess percentage:- " + cessValue);

        DecimalFormat decimalFormat = new DecimalFormat("#.###");

        double totalValue = gstValue + cessValue;
        System.out.println("total:-" + totalValue);

//        if (!gstAmountClicked) {
//            common.clickElement("xpath", "//Header[@Name='GST Amount']");
//            gstAmountClicked = true;
//        }
        if (!rstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST RCM Amount']");
            rstAmountClicked = true;
        }

        String gstTransType = common.findWebElement("xpath", "//Edit[@Name='GST Trans Type *']").getText().trim();
        if (gstTransType.equalsIgnoreCase("Intra State Purchase from Registered Dealers with RCM")) {
            double expectedRCMCGST = Double.parseDouble(decimalFormat.format(((grossAmount * gstValue) / 100) / 2));
            double actualRCMCGST = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='RCM CGST Row "+i+", Not sorted.']").getText().replace(",", ""));
            System.out.println("Actual RCMCGST: " + actualRCMCGST);
            System.out.println("Expected RCMCGST: " + expectedRCMCGST);
            Assert.assertEquals(actualRCMCGST, expectedRCMCGST, "mismatch");

            double expectedRCMSGST = Double.parseDouble(decimalFormat.format(((grossAmount * gstValue) / 100) / 2));
            double actualSGST = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='RCM SGST Row "+i+", Not sorted.']").getText().replace(",", ""));
            System.out.println("Actual RCMSGST: " + actualSGST);
            System.out.println("Expected RCMSGST: " + expectedRCMSGST);
            Assert.assertEquals(actualSGST, expectedRCMSGST,"mismatch");

            double expectedRCMCESS = Double.parseDouble(decimalFormat.format((grossAmount * cessValue) / 100));
            double actualCESS = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='RCM CESS Row "+i+", Not sorted.']").getText().replace(",", ""));
            System.out.println("Actual RCMCESS: " + actualCESS);
            System.out.println("Expected RCMCESS: " + expectedRCMCESS);
            Assert.assertEquals(actualCESS, expectedRCMCESS,"mismatch");

            expectedGSTAmount = expectedRCMCGST + expectedRCMSGST + expectedRCMCESS;
            double actualGSTAmount = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='GST RCM Amount Row "+i+", Not sorted.']").getText().replace(",", ""));
            System.out.println("Expected RCMGST Amount: " + decimalFormat.format(expectedGSTAmount));
            System.out.println("Actual GST Amount: " + decimalFormat.format(actualGSTAmount));
            Assert.assertEquals(decimalFormat.format(actualGSTAmount), decimalFormat.format(expectedGSTAmount));
        } else {
            System.out.println("Gst TransactionType Mismatch");
        }
        calculatedNet=grossAmount+expectedGSTAmount;
        WebElement net = common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']");
        String netAmountText = net.getText().replace(",", "");
        netAmount = Double.parseDouble(netAmountText);
        Assert.assertEquals(netAmount,calculatedNet, "check calculations once");
        System.out.println("Net Amount:- " + netAmount);
        String netInCompanyCurrency = common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']").getText().replace(",", "");
        Assert.assertEquals(netInCompanyCurrency,netAmountText, "check calculations once");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
    }
}
