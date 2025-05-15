package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PurchaseVouchersAgainstReceipt extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchaseVouchersAgainstReceipt(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String purchaseVouchersAgainstReceipt(String voucherNum) throws InterruptedException, IOException, ParseException, AWTException {
        long start = System.nanoTime();

        navigateToMastersWhen3Steps("Purchase","Invoices","Purchase Vouchers against Receipts");
        Thread.sleep(3000);
        String oldVoucherID = oldTTransactionID();
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "PurchaseVoucherAgainstReceipts", "branch");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "PurchaseVoucherAgainstReceipts", "partyCode");
        selectPendingsSalesOrder(voucherNum,common.getData(dataFile,"PurchaseVoucherAgainstReceipts","FYear"));
        common.clickElement("xpath","//Button[@Name='OK']");
        enterInput("xpath", "//Edit[@Name='Purchase A/C Code']", dataFile, "PurchaseVoucherAgainstReceipts", "PurchaseAccCode");
        enableCheckboxSelection("//CheckBox[@Name='Apply TCS']");
        enterInput("xpath","//Edit[@Name='TCS Trans Nature']", dataFile,"PurchaseVoucherAgainstReceipts","tcsNature");
        enableCheckboxSelection("//CheckBox[@Name='Deduct TDS']");
        enterInput("xpath","//Edit[@Name='TDS Trans Nature']", dataFile,"PurchaseVoucherAgainstReceipts","tdsNature");
        inputTextWithValidation("xpath", "//Edit[@Name='Supplier Bill No *']",String.valueOf(common.getRandom()));
        inputTextWithValidation("xpath", "//Edit[@Name='Supplier Bill Date *']", Time.timeStamp());
        enterInput("xpath", "//Edit[@Name='Batch Policy']",dataFile,"PurchaseVoucherAgainstReceipts", "batchPolicy");


        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("PurchaseVoucherAgainstReceipts GeneralInformation",duration/1000000000);

        long start1 = System.nanoTime();

        List<WebElement> items = common.findWebElements("xpath", "//Pane[@Name='  F3 Items  ']/Pane/Pane/Pane/Table[@Name='Items']/*[starts-with(@Name,'Row')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 400, 0);
        for (int i = 0; i <= 1; i++) {
            WebElement productList = items.get(i);
            String value = productList.getAttribute("LegacyValue");
            if (!value.equals("(null)")){
                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 800, 0);
                enterItemsOtherCosts(dataFile,"PurchaseVoucherAgainstReceipts",i);
                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -800, 0);
            }
        }

        long duration1 = System.nanoTime() - start1;
        FileUtil.writeTimeLog("PurchaseVoucherAgainstReceipts Enter Products",duration1/1000000000);

        long start2 = System.nanoTime();

        common.clickElement("xpath","//Button[@Name='Serial No Row 2']");
        common.clickElement("xpath","//Button[@Name='OK']");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 850, 0);
        enterItemsOtherCosts(dataFile,"PurchaseVoucherAgainstReceipts",2);
        enterServices(dataFile,"PurchaseVoucherAgainstReceipts");
        chargesAndDeductionsCalculations1(dataFile,"PurchaseVoucherAgainstReceipts", "charges","deductions","chargesAcc","deductionsAcc", "chargesAmount", "deductionsAmount", "chargesRowCount");
        enterOtherCharges(dataFile,"PurchaseVoucherAgainstReceipts");
        navigateToBillsReceivablesTab();
        common.deleteInvalidRows();
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        validateTDS(dataFile,"PurchaseVoucherAgainstReceipts");
        validateTCS(dataFile,"PurchaseVoucherAgainstReceipts");
        enterOtherCosts(dataFile,"PurchaseVoucherAgainstReceipts");
        navigateToItemsOtherCosts();
        moveToRight(7);
        enterCash(dataFile,"PurchaseVoucherAgainstReceipts");
        enterChequesInPurchase(dataFile,"PurchaseVoucherAgainstReceipts");
        enterPostDatedChequesInPurchase(dataFile,"PurchaseVoucherAgainstReceipts");
        enterChequesPDCInPurchase(dataFile,"PurchaseVoucherAgainstReceipts");
        navigateToOtherInfoTab();
        inputTextWithValidation("xpath", "//Edit[@Name='Reference Bill Date']", common.getData(dataFile,"PurchaseVoucherAgainstReceipts","billRefDate")+ Time.timeStamp());
        termsAndConditions(dataFile,"PurchaseVoucherAgainstReceipts");
        navigateToSummaryTab();quantityPresentInSummary();
        grossAmountPresentInSummary();
        servicesAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        iGSTPresentInSummary();
        cessPresentInSummary();
        servicesIGSTPresentInSummary();
        servicesCESSPresentInSummary();
        netAmountPresentInSummary();
        chargesPresentInSummary();
        deductionsPresentInSummary();
        otherChargesPresentInSummary();
        otherChargesIGSTPresentInSummary();
        otherChargesCESSPresentInSummary();
        otherCostsAmountPresentInSummary();
        tcsTaxableValuePresentInSummary();
        tcsAmountPresentInSummary();
        tdsAmountPresentInSummary();
        payableAfterTdsPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        cashPresentInSummary();
        chequesPresentInSummary();
        postDatedChequesPresentInSummary();
        PDCPresentInSummary();
        paymentsValuePresentInSummary();
        payableAMountPresentInSummary();

        long duration2 = System.nanoTime() - start2;
        FileUtil.writeTimeLog("PurchaseVoucherAgainstReceipts validating Tab Items UpTo summary",duration2/1000000000);


        long start3 = System.nanoTime();

        transactionSave();
        String transactionId = newTransactionID(oldVoucherID);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Vouchers against Receipts'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        verifyReport(transactionId,dataFile,"PurchaseVoucherAgainstReceipts");

        long duration3 = System.nanoTime() - start3;
        FileUtil.writeTimeLog("PurchaseVoucherAgainstReceipts SaveAndVerify Report", duration3 /1000000000);

        return transactionId;
    }
}
