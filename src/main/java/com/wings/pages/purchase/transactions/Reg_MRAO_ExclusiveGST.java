package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import com.wings.utils.Time;
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

public class Reg_MRAO_ExclusiveGST extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Reg_MRAO_ExclusiveGST(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void MRAO_ExclusiveGST() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps(common.getData(dataFile,"Material Receipts","menu"),common.getData(dataFile,"Material Receipts","menuItem"), common.getData(dataFile,"Material Receipts","subMenuItem"));
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"Purchase Order","branch");
//        enterInput("xpath","//Edit[@Name='Location *']",dataFile,"location");
//        enterInput("xpath","//Edit[@Name='Trans Currency *']",dataFile,"currency")
        enterInput("xpath","//Edit[@Name='Party Code']",dataFile,"Purchase Order","partyCode");
        gstTransactionType("Intra State Purchase from Registered Dealers");
        enterInput("xpath","//Edit[@Name='Batch Policy']",dataFile,"Purchase Order","batchPolicy");
//        common.clickElement("xpath","//*//CheckBox[@Name='Select Row 0']");
//        common.clickElement("xpath","//Button[@Name='Ok']");

        // Product codes to search for
        String productCode1 = common.getData(dataFile, "Purchase Order", "productCode0");
        String productCode2 = common.getData(dataFile, "Purchase Order", "productCode1");
        List<String> productCodes = Arrays.asList(productCode1, productCode2);
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
                        quantity.sendKeys(pendingQty,Keys.TAB);
                    }
                    else {
                        // Second matched product - pending quantity - 1 and free quantity - 1
                        String pendingQty = common.findWebElement("xpath", "//Edit[@Name='Pending Quantity In SKU Row " + i + ", Not sorted.']").getText();
                        double actualPendingQuantity = Double.parseDouble(pendingQty);
                        WebElement quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']");
                        quantity.click();
                        quantity.sendKeys(String.valueOf(actualPendingQuantity), Keys.TAB);
                        serialNumberForMRAO(dataFile,"Material Receipts","serialText","serialQuantity","");
                    }
                    processedCodes.add(productCode);  // Mark as processed
                    break;  // Move to the next item after processing the current one
                }
            }

        }

        common.deleteInvalidRows();
        validateCGSTAmountTabIsNotEmpty();
        validateSGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        navigateToSummaryTab();
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        sgstPresentInSummary();
        sgstPresentInSummary();
        cessPresentInSummary();
        netAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID).replace(" ","");
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
        Thread.sleep(1000);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Material Receipts against Orders'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID,dataFile,"Material Receipts");
        deleteTransactionBasedOnYear(newVoucherID);
    }
}
