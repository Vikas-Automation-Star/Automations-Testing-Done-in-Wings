package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class MaterialReturn extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public MaterialReturn(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void materialReturn(String receiptsNum) throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Purchase","Receipts", "Material Returns");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"MaterialReturns","branch");
        common.findWebElement("xpath","//Edit[@Name='Receipt No']").sendKeys(receiptsNum, Keys.TAB);
        common.clickElement("xpath","//Button[@Name='OK']");

        List<WebElement> items = common.findWebElements("xpath", "//Pane[@Name='  F3 Items  ']/Pane/Pane/Pane/Table[@Name='Items']/*[starts-with(@Name,'Row')]");
        System.out.println(items.size());
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 400, 0);
        for (int i = 0; i<items.size(); i++) {
            WebElement productList = items.get(i);
            String value = productList.getAttribute("LegacyValue");
            if (!value.equals("null")&&!value.equals("(Create New)")){
                String pendingQty = common.findWebElement("xpath", "//Edit[@Name='PendingQuantity In SKU * Row "+i+", Not sorted.']").getText();
                WebElement quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity Row "+i+", Not sorted.']");
                quantity.click();
                quantity.sendKeys(pendingQty, Keys.TAB);
            }
        }
        chargesAndDeductionsCalculations1(dataFile,"MaterialReceipts", "charges","deductions","chargesAcc","deductionsAcc", "chargesAmount", "deductionsAmount", "chargesRowCount");
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        enterOtherCosts(dataFile,"MaterialReceipts");
        enterOtherInfo(dataFile,"MaterialReceipts");
        termsAndConditions(dataFile,"MaterialReceipts");
        navigateToSummaryTab();
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        iGSTPresentInSummary();
        cessPresentInSummary();
        netAmountPresentInSummary();
        chargesPresentInSummary();
        deductionsPresentInSummary();
        otherCostsAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
        Thread.sleep(1000);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Material Returns'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID,dataFile,"MaterialReturns");
    }
}