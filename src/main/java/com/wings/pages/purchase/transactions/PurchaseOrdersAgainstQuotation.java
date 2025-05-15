package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
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
        long start = System.nanoTime();

        navigateToMastersWhen3Steps("Purchase","Orders","Purchase Orders against Quotations");
        Thread.sleep(2000);
        String oldVoucherID = oldTTransactionID();
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "PurchaseOrdersAgainstEnquiries", "branch");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "PurchaseOrdersAgainstEnquiries", "partyCode");
        selectPendingPurchaseOrder(voucherNum,common.getData(dataFile,"PurchaseOrdersAgainstEnquiries","FYear"));
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");

        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("purchaseOrdersAgainstEnquiry generalInformation",duration/1000000000);

        long start1 = System.nanoTime();

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

        long duration1 = System.nanoTime() - start1;
        FileUtil.writeTimeLog("PurchaseOrdersAgainstQuotations Enter Products:", duration1 /1000000000);


        long start2 = System.nanoTime();

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

        long duration2 = System.nanoTime() - start2;
        FileUtil.writeTimeLog("PurchaseOrdersAgainstQuotations validating Tab Items UpTo summary",duration2/1000000000);

        long start3 = System.nanoTime();

        transactionSave();
        String transactionId = newTransactionID(oldVoucherID);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Orders against Quotations'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        verifyReport(transactionId,dataFile,"PurchaseOrdersAgainstEnquiries");

        long duration3 = System.nanoTime() - start3;
        FileUtil.writeTimeLog("PurchaseOrdersAgainstQuotations SaveAndVerify Report", duration3 /1000000000);



        return transactionId;
    }
}
