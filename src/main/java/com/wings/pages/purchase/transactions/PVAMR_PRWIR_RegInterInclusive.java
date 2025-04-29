package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PVAMR_PRWIR_RegInterInclusive extends Transaction {

    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked=false;
    public PVAMR_PRWIR_RegInterInclusive(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void regInterInclusiveWithInvoiceRef_PVAMR(String voucherNum) throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps(common.getData(dataFile,"PVAMR-PRWIR RegInter","menu"),common.getData(dataFile,"PVAMR-PRWIR RegInter","menuItem"), common.getData(dataFile,"PVAMR-PRWIR RegInter","subMenuItem"));
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
//        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
//        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"Purchase Order","branch");
//        enterInput("xpath","//Edit[@Name='Location *']",dataFile,"location");
//        enterInput("xpath","//Edit[@Name='Trans Currency *']",dataFile,"currency")
        inputTextWithValidation("xpath", "//Edit[@Name='Purchase VNo *']",voucherNum);
        enterInput("xpath","//Edit[@Name='Purchase Return A/c Code']",dataFile,"PVAMR-PRWIR RegInter","purchaseReturnActCode");
        enableCheckboxSelection("//CheckBox[@Name='Apply TCS']");
        enterInput("xpath","//Edit[@Name='TCS Trans Nature']", dataFile,"PVAMR-PRWIR RegInter","tcsNature");

        String productCode1 = common.getData(dataFile, "Purchase Order", "productCode0");
        String productCode2 = common.getData(dataFile, "Purchase Order", "productCode1");
        java.util.List<String> productCodes = Arrays.asList(productCode1, productCode2);
        List<WebElement> items = common.findWebElements("xpath", "//Pane[@Name='  F3 Items  ']/Pane/Pane/Pane/Table[@Name='Items']/*[starts-with(@Name,'Row')]");
        System.out.println("size :"+items);
        Set<String> processedCodes = new HashSet<>();
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 400, 0);
        for (int i = 0; i < items.size(); i++) {
            WebElement productList = items.get(i);
            String value = productList.getAttribute("LegacyValue");
            for (String productCode : productCodes) {
                if (value != null && value.contains(productCode) && !processedCodes.contains(productCode)) {
                    if (processedCodes.isEmpty()) {
                        // First matched product - full quantity and free quantity
                        String pendingQty = common.findWebElement("xpath", "//Edit[@Name='Pending Quantity In SKU Row " + i + ", Not sorted.']").getText();
                        WebElement quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']");
                        quantity.click();
                        quantity.sendKeys(pendingQty, Keys.TAB);
                        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 630, 0);
                        if (!gstAmountClicked) {
                            common.clickElement("xpath", "//Header[@Name='GST Amount']");
                            gstAmountClicked = true;
                        }
                        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -630, 0);
                    }
                    else {
                        // Second matched product - pending quantity - 1 and free quantity - 1
                        String pendingQty = common.findWebElement("xpath", "//Edit[@Name='Pending Quantity In SKU Row " + i + ", Not sorted.']").getText();
                        double actualPendingQuantity = Double.parseDouble(pendingQty);
                        WebElement quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']");
                        quantity.click();
                        quantity.sendKeys(String.valueOf(actualPendingQuantity), Keys.TAB);
                        Thread.sleep(1000);
                        enterData("xpath","//Edit[@Name='Quantity Row 2, Not sorted.']",dataFile,"PVAMR RegInter","serialQuantity");
                    }
                    processedCodes.add(productCode);
                    break;
                }
            }
        }
        double itemsNetValue = Double.parseDouble(common.findWebElement("xpath", "//Edit[@AutomationId='NetAmount']").getText().replace(",", ""));
        System.out.println("Items NetAmount :- " + itemsNetValue);
        common.deleteInvalidRows();
        tcsCalculations(itemsNetValue);

        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        navigateToBillsPayablesTab();
        common.deleteInvalidRows();
        navigateToSummaryTab();
        quantityPresentInSummary();
        freeQuantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        iGSTPresentInSummary();
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
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Returns with Invoice Reference'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID,dataFile,"PVAMR-PRWIR RegInter");
//        deleteSingleTransaction(newVoucherID);
    }
}