package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.IOException;

public class PurchaseOrder_UnRegInterInclusive extends Transaction {
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;

    public PurchaseOrder_UnRegInterInclusive(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String[] unReg_PO_GSTInclusive() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Purchase", "Orders", "Purchase Orders");
        String oldVoucherID = oldTTransactionID();
        System.out.println("oldID: " + oldVoucherID);
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "Purchase Order", "branch");
//        enterInput("xpath","//Edit[@Name='Location *']",dataFile,"location");
//        enterInput("xpath","//Edit[@Name='Trans Currency *']",dataFile,"currency");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "Purchase Order", "partyCode");
        gstTransactionType("");
        Thread.sleep(2000);
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, "Purchase Order", "priceList");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "Purchase Order", "executive");
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
        String originalID=newTransactionID(oldVoucherID);
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
        verifyReport(newVoucherID, dataFile, "Purchase Order");
//        deleteSingleTransaction(newVoucherID);
        return new String[]{newVoucherID,originalID};
    }

    public void addProduct(int i) throws IOException, ParseException {
        if (common.getData(dataFile, "Purchase Order", "productType" + i).equals("general")) {
            generalProduct_New(dataFile, "Purchase Order", "productCode" + i, "quantity" + i, "freeQuantity" + i, i);
        } else if (common.getData(dataFile, "Purchase Order", "productType" + i).equals("multiBatch")) {
            multiBatchProductDirectQuantity(dataFile, "Purchase Order", "productCode" + i, "quantity" + i, "freeQuantity" + i, i);
        } else if (common.getData(dataFile, "Purchase Order", "productType" + i).equals("serial")) {
            serialNumProductDirectQuantity(dataFile, "Purchase Order", "productCode" + i, "quantity" + i, "freeQuantity" + i, i);
        }
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        String mrpAmount = common.findWebElement("xpath", "//Edit[@Name='MRP Amount Row " + i + ", Not sorted.']").getText();
        Assert.assertEquals(mrpAmount, common.getData(dataFile, "Purchase Order", "MrpAmount"));
        String grossAmount = common.findWebElement("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']").getText();
        Assert.assertEquals(grossAmount, common.getData(dataFile, "Purchase Order", "grossAmount"));
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 250, 0);

        enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile, "Purchase Order", "HSNCode");
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }

        String gstTransType = common.findWebElement("xpath", "//Edit[@Name='GST Trans Type *']").getText().trim();

        if (common.getData(dataFile, "Purchase Order", "priceList").contains("Inclusive")) {
            String taxableValue = common.findWebElement("xpath", "//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']").getText();
            Assert.assertEquals(taxableValue, common.getData(dataFile, "Purchase Order", "taxableValue"), "calculations mismatch");
            String netAmount = common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']").getText();
            Assert.assertEquals(netAmount, grossAmount, "calculations mismatch");
        } else if (common.getData(dataFile, "Purchase Order", "priceList").contains("Exclusive")) {
            String taxableValue = common.findWebElement("xpath", "//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']").getText();
            Assert.assertEquals(taxableValue, grossAmount, "calculations mismatch");
            String netAmount = common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']").getText();
            Assert.assertEquals(netAmount, common.getData(dataFile, "Purchase Order", "netAmount"), "calculations mismatch");
        } else {
            throw new IllegalArgumentException("Invalid GST Trans Type: " + gstTransType);
        }
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
    }
}
