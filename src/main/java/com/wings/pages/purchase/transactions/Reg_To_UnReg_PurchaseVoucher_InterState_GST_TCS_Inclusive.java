package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Reg_To_UnReg_PurchaseVoucher_InterState_GST_TCS_Inclusive extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;

    public Reg_To_UnReg_PurchaseVoucher_InterState_GST_TCS_Inclusive(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String Reg_To_UnReg_PurchaseVoucher_InterState_Inclusive() throws InterruptedException, IOException, ParseException, AWTException {
        Time.currentDateAndTime();
        navigateToMastersWhen3Steps(common.getData(dataFile,"menu"),common.getData(dataFile,"menuItem"), common.getData(dataFile,"subMenuItem"));
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
//        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
//        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"branch");
//        enterInput("xpath","//Edit[@Name='Location *']",dataFile,"location");
//        enterInput("xpath","//Edit[@Name='Trans Currency *']",dataFile,"currency");
        enterInput("xpath", "//Edit[@Name='Cash/Party Code']",dataFile, "partyCode");
        Thread.sleep(1000);
        enterInput("xpath", "//Edit[@Name='Purchase A/c Code']",dataFile, "purchaseAccount");
        inputTextWithValidation("xpath", "//Edit[@Name='Supplier Bill No *']", common.getData(dataFile, "supplierCode") +common.getRandom());
        inputTextWithValidation("xpath", "//Edit[@Name='Supplier Bill Date *']", common.getData(dataFile, "date") + Time.timeStamp());
        enterInput("xpath", "//Edit[@Name='Batch Policy']",dataFile, "batchPolicy");
        enableCheckboxSelection("//CheckBox[@Name='Apply TCS']");
        enterInput("xpath","//Edit[@Name='TCS Trans Nature']", dataFile,"tcsNature");
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, "priceList");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "executive");
        Time.currentDateAndTime();
//        generalInfoSliderHandle(-500);
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "productCount")); i++) {
            addProduct(i);
        }
        Time.currentDateAndTime();

        double itemsNetValue = Double.parseDouble(common.findWebElement("xpath", "//Edit[@AutomationId='NetAmount']").getText().replace(",", ""));tcsCalculations(itemsNetValue);

        validateIGSTAmountTabIsEmpty();validateCESSAmountTabIsEmpty();navigateToBillsReceivablesTab();common.deleteInvalidRows();
        //verify all the fields in summary are fetching data
        navigateToTcs();
        for (int j = 0; j <=7; j++) {
            Robot robot=new Robot();
            robot.keyPress(KeyEvent.VK_RIGHT);
            robot.keyRelease(KeyEvent.VK_RIGHT);
        }
        quantityPresentInSummary();freeQuantityPresentInSummary();grossAmountPresentInSummary();grossMinusDiscountPresentInSummary();netAmountPresentInSummary();tcsAmountPresentInSummary();tcsTaxableValuePresentInSummary();totalValuePresentInSummary();totalValueInCompanyCurrenyPresentInSummary();
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID).replace(" ","");System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
        Thread.sleep(1000);
        navigateToMastersWhen3Steps("Purchase","Invoices","Purchase Book");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID,dataFile);
        deleteSingleTransaction(newVoucherID);
        return newVoucherID;
    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile, "productType" + i).equals("general")) {
            generalProduct(dataFile, "productCode" + i, "quantity" + i, "freeQuantity" + i, i);
        } else if (common.getData(dataFile, "productType" + i).equals("multiBatch")) {
            multiBatchProductPurchase(dataFile, "productCode" + i, "quantity" + i, "freeQuantity" + i, i);
        } else if (common.getData(dataFile, "productType" + i).equals("serial")) {
            serialNumberProductInPurchase(dataFile, "productCode" + i, "serialText", "quantity" + i, "freeQuantity" + i, i);
        }

        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        String mrpAmount = common.findWebElement("xpath", "//Edit[@Name='MRP Amount Row " + i + ", Not sorted.']").getText();
        Assert.assertEquals(mrpAmount, common.getData(dataFile, "MrpAmount"));
        String grossAmount = common.findWebElement("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']").getText();
        Assert.assertEquals(grossAmount, common.getData(dataFile, "grossAmount"));
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 250, 0);

        enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile, "HSNCode");
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }

        if (common.getData(dataFile, "priceList").contains("Inclusive")) {
            String taxableValue = common.findWebElement("xpath", "//Edit[@Name='Taxable Value Row "+i+", Not sorted.']").getText();
            Assert.assertEquals(taxableValue, common.getData(dataFile, "taxableValue"), "calculations mismatch");
            String netAmount = common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']").getText();
            Assert.assertEquals(netAmount, grossAmount, "calculations mismatch");
        } else if (common.getData(dataFile, "priceList").contains("Exclusive")) {
            String taxableValue = common.findWebElement("xpath", "//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']").getText();
            Assert.assertEquals(taxableValue, grossAmount, "calculations mismatch");
            String netAmount = common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']").getText();
            Assert.assertEquals(netAmount,common.getData(dataFile,"netAmount"), "calculations mismatch");
        } else {
            throw new IllegalArgumentException("Invalid GST Trans Type: ");
        }
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
    }
}
