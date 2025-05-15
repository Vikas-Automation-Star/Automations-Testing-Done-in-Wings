package com.wings.pages.purchase.transactions;


import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class MaterialReceiptsAgainstOrder extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public MaterialReceiptsAgainstOrder(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String materialReceiptsAgainstOrder(String voucherNum) throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Purchase","Receipts","Material Receipts against Orders");
        Thread.sleep(2000);
        String oldVoucherID = oldTTransactionID();
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "MaterialReceiptsAgainstOrders", "branch");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "MaterialReceiptsAgainstOrders", "partyCode");
        selectPendingsSalesOrder(voucherNum,common.getData(dataFile,"MaterialReceiptsAgainstOrders","FYear"));
        common.clickElement("xpath","//Button[@Name='OK']");
        enterInput("xpath", "//Edit[@Name='Batch Policy']", dataFile, "MaterialReceiptsAgainstOrders", "BatchPolicy");

        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 400, 0);
        List<WebElement> items = common.findWebElements("xpath", "//Pane[@Name='  F3 Items  ']/Pane/Pane/Pane/Table[@Name='Items']/*[starts-with(@Name,'Row')]");
        System.out.println("items size: "+items.size());
        for (int i = 0; i < items.size(); i++) {
            WebElement productList = items.get(i);
            String value = productList.getAttribute("LegacyValue");
            if (!"(null)".equals(value) && !"(Create New)".equals(value)){
                String pendingQty = common.findWebElement("xpath", "//Edit[@Name='Pending Quantity Row "+i+", Not sorted.']").getText();
                WebElement quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity Row "+i+", Not sorted.']");
                quantity.click();
                quantity.sendKeys(pendingQty, Keys.TAB);
            }
        }
        serialNumberForMRAO(dataFile,"MaterialReceiptsAgainstOrders","serialText","serialQuantity","");
        chargesAndDeductionsCalculations1(dataFile,"MaterialReceiptsAgainstOrders", "charges","deductions","chargesAcc","deductionsAcc", "chargesAmount", "deductionsAmount", "chargesRowCount");
        enterOtherCharges(dataFile,"MaterialReceiptsAgainstOrders");
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        enterOtherCosts(dataFile,"MaterialReceiptsAgainstOrders");
        navigateToOtherInfoTab();
        inputTextWithValidation("xpath", "//Edit[@Name='Reference Bill Date']", common.getData(dataFile,"PurchaseOrdersCancellation","billRefDate")+ Time.timeStamp());
        termsAndConditions(dataFile,"MaterialReceiptsAgainstOrders");
        navigateToSummaryTab();
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        iGSTPresentInSummary();
        cessPresentInSummary();
        netAmountPresentInSummary();
        chargesPresentInSummary();
        deductionsPresentInSummary();
        otherChargesPresentInSummary();
        otherChargesIGSTPresentInSummary();
        otherChargesCESSPresentInSummary();
        otherCostsAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        transactionSave();
        String transactionId = newTransactionID(oldVoucherID);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Material Receipts against Orders'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        verifyReport(transactionId,dataFile,"MaterialReceiptsAgainstOrders");
        return transactionId;
    }
}

