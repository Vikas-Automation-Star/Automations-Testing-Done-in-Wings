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

public class PurchaseQuotationsAgainstEnquiries extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchaseQuotationsAgainstEnquiries(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String purchaseQuotationsAgainstEnquiry(String voucherNum) throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Purchase","Quotations","Purchase Quotations against Enquiries");
        Thread.sleep(3000);
        String oldVoucherID = oldTTransactionID();
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "PurchaseQuotationsAgainstEnquiries", "branch");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "PurchaseQuotationsAgainstEnquiries", "partyCode");
        selectPendingsSalesOrder(voucherNum,common.getData(dataFile,"PurchaseQuotationsAgainstEnquiries","FYear"));

        long start = System.nanoTime();

        List<WebElement> items = common.findWebElements("xpath", "//Pane[@Name='  F3 Items  ']/Pane/Pane/Pane/Pane/Table[@Name='Items']/*[starts-with(@Name,'Row')]");
        System.out.println("items size: "+items.size());
        for (int i = 0; i < items.size(); i++) {
            WebElement productList = items.get(i);
            String value = productList.getAttribute("LegacyValue");
            if (!"(null)".equals(value) && !"(Create New)".equals(value)){
                String pendingQty = common.findWebElement("xpath", "//Edit[@Name='Pending Quantity * Row "+i+", Not sorted.']").getText();
                WebElement quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']");
                quantity.click();
                quantity.sendKeys(pendingQty, Keys.TAB);
                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
                enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile,"PurchaseQuotationsAgainstEnquiries", "HSNCode");
                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -600, 0);
            }
        }

        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("purchaseQuotationsAgainstEnquiry Enter Products:",duration/1000000000);

        long start1 = System.nanoTime();

        chargesAndDeductionsCalculations1(dataFile,"PurchaseQuotationsAgainstEnquiries", "charges","deductions","chargesAcc","deductionsAcc", "chargesAmount", "deductionsAmount", "chargesRowCount");
        navigateToOtherInfoTab();
        inputTextWithValidation("xpath", "//Edit[@Name='Reference Bill Date']", common.getData(dataFile,"PurchaseQuotationsAgainstEnquiries","billRefDate")+ Time.timeStamp());
        termsAndConditions(dataFile,"PurchaseQuotationsAgainstEnquiries");
        navigateToSummaryTab();
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossAmountMinusDiscountPresentInSummary();
        iGSTPresentInSummary();
        cessPresentInSummary();
        chargesPresentInSummary();
        deductionsPresentInSummary();
        netAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        transactionSave();
        String transactionId = newTransactionID(oldVoucherID);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Quotations");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Quotations against Enquiries'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        verifyReport(transactionId,dataFile,"PurchaseQuotationsAgainstEnquiries");

        long duration1 = System.nanoTime() - start1;
        FileUtil.writeTimeLog("purchaseQuotationsAgainstEnquiry validating Tab Items UpTo summary",duration1/1000000000);

        return transactionId;
    }
}
