package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.StringUtil;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.text.DecimalFormat;

public class UnReg_PO_GSTExclusive extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;
    double mrp, grossAmount, unitRate, quantity, gstValue, taxableAmountCalculated, cessValue, netAmount, expectedGSTAmount, calculatedNet;

    public UnReg_PO_GSTExclusive(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void unReg_PO_GSTExclusive() throws InterruptedException, IOException, ParseException {
        Time.currentDateAndTime();
        navigateToMastersWhen3Steps("Purchase", "Orders", "Purchase Orders");
        String oldVoucherID = oldTTransactionID();
        System.out.println("oldID: " + oldVoucherID);
//        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
//        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "Purchase Order", "branch");
//        enterInput("xpath","//Edit[@Name='Location *']",dataFile,"location");
//        enterInput("xpath","//Edit[@Name='Trans Currency *']",dataFile,"currency");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "Purchase Order", "partyCode");
        gstTransactionType("");
        Thread.sleep(2000);
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, "Purchase Order", "priceList");
//        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "Purchase Order","executive");
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "Purchase Order", "productCount")); i++) {
            addProduct(i);
        }
        navigateToSummaryTab();
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        netAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        //save
        transactionSave();
        String newVoucherID = newTransactionID(oldVoucherID).replace(" ", "");
        System.out.println("newID: " + newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID, "both ID's should not Equal when we perform transaction");
        Thread.sleep(1000);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Orders'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID,dataFile,"Purchase Order");
//        deleteSingleTransaction(newVoucherID);
    }

    public void addProduct(int i) throws IOException, ParseException {
        Time.currentDateAndTime();
        if (common.getData(dataFile, "Purchase Order", "productType" + i).equals("general")) {
            generalProduct_New(dataFile, "Purchase Order", "productCode" + i, "quantity" + i, "freeQuantity" + i, i);
        } else if (common.getData(dataFile, "Purchase Order", "productType" + i).equals("multiBatch")) {
            multiBatchProduct_New(dataFile, "Purchase Order", "transType", "productCode" + i, "quantity" + i, "freeQuantity" + i, i);
        } else if (common.getData(dataFile, "Purchase Order", "productType" + i).equals("serial")) {
            serialNumProduct_New(dataFile, "Purchase Order", "transType", "productCode" + i, "quantity" + i, "freeQuantity" + i, i);
        }

        quantity = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Quantity * Row " + i + ", Not sorted.']").getText());

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

        enterInput("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile, "Purchase Order", "HSNCode");

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

        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        WebElement net = common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']");
        String netAmountText = net.getText().replace(",", "");
        netAmount = Double.parseDouble(netAmountText);
        Assert.assertEquals(grossAmount, netAmount, "check calculations once");
        System.out.println("Net Amount:- " + netAmount);
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
        Time.currentDateAndTime();
    }
}
