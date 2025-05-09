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


public class PurchaseOrdersAgainstQuotation extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchaseOrdersAgainstQuotation(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String purchaseOrdersAgainstQuotation(String voucherNum) throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Purchase","Orders","Purchase Orders against Quotations");
        Thread.sleep(3000);
        String oldVoucherID = oldTTransactionID();
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "PurchaseOrdersAgainstEnquiries", "branch");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "PurchaseOrdersAgainstEnquiries", "partyCode");
        selectPendingPurchaseOrder(voucherNum,common.getData(dataFile,"PurchaseOrdersAgainstEnquiries","FYear"));
        common.clickElement("xpath","//Button[@Name='OK']");
        List<WebElement> items = common.findWebElements("xpath", "//Pane[@Name='  F3 Items  ']/Pane/Pane/Pane/Pane/Table[@Name='Items']/*[starts-with(@Name,'Row')]");
        for (int i = 0; i < items.size(); i++) {
            WebElement productList = items.get(i);
            String value = productList.getAttribute("LegacyValue");
            if (!"(null)".equals(value) && !"(Create New)".equals(value)){
                String pendingQty = common.findWebElement("xpath", "//Edit[@Name='Quotation Quantity * Row "+i+", Not sorted.']").getText();
                WebElement quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']");
                quantity.click();
                quantity.sendKeys(pendingQty, Keys.TAB);
                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
                enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile,"PurchaseOrdersAgainstEnquiries", "HSNCode");
                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -600, 0);
            }
        }
        chargesAndDeductionsCalculations1(dataFile,"PurchaseOrdersAgainstEnquiries", "charges","deductions","chargesAcc","deductionsAcc", "chargesAmount", "deductionsAmount", "chargesRowCount");
        enterOtherCharges(dataFile,"PurchaseOrdersAgainstEnquiries");
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        enterCash(dataFile,"PurchaseOrdersAgainstEnquiries");
        enterChequesInPurchase(dataFile,"PurchaseOrdersAgainstEnquiries");
        enterPostDatedChequesInPurchase(dataFile,"PurchaseOrdersAgainstEnquiries");
        enterChequesPDCInPurchase(dataFile,"PurchaseOrdersAgainstEnquiries");
        navigateToOtherInfoTab();
        inputTextWithValidation("xpath", "//Edit[@Name='Reference Bill Date']", common.getData(dataFile,"PurchaseOrdersAgainstEnquiries","billRefDate")+ Time.timeStamp());
        termsAndConditions(dataFile,"PurchaseOrdersAgainstEnquiries");
        navigateToSummaryTab();
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossAmountMinusDiscountPresentInSummary();
        iGSTPresentInSummary();
        cessPresentInSummary();
        netAmountPresentInSummary();
        chargesPresentInSummary();
        deductionsPresentInSummary();
        taxableOtherChargesPresentInSummary();
        otherChargesIGSTPresentInSummary();
        otherChargesSGSTPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        cashPresentInSummary();
        postDatedChequesPresentInSummary();
        paymentsPresentInSummary();

        transactionSave();
        String transactionId = newTransactionID(oldVoucherID);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Orders against Quotations'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        verifyReport(transactionId,dataFile,"PurchaseOrdersAgainstEnquiries");
        return transactionId;
    }
}
