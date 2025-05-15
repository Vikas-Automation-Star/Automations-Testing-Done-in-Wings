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

public class PurchaseVoucherAgainstPurchaseOrders extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchaseVoucherAgainstPurchaseOrders(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }
    public String purchaseVoucherAgainstPurchaseOrders(String voucherNum) throws InterruptedException, IOException, ParseException, AWTException {
        long start = System.nanoTime();

        navigateToMastersWhen3Steps("Purchase","Invoices","Purchase Vouchers against Orders");
        Thread.sleep(2000);
        String oldVoucherID = oldTTransactionID();
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "PurchaseVouchersAgainstOrders", "branch");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "PurchaseVouchersAgainstOrders", "partyCode");
        selectPendingsSalesOrder(voucherNum,common.getData(dataFile,"PurchaseVouchersAgainstOrders","FYear"));
        common.clickElement("xpath","//Button[@Name='OK']");
        enterInput("xpath", "//Edit[@Name='Purchase A/C Code']", dataFile, "PurchaseVouchersAgainstOrders", "PurchaseAccCode");
        enableCheckboxSelection("//CheckBox[@Name='Apply TCS']");
        enterInput("xpath","//Edit[@Name='TCS Trans Nature']", dataFile,"PurchaseVouchersAgainstOrders","tcsNature");
        enableCheckboxSelection("//CheckBox[@Name='Deduct TDS']");
        enterInput("xpath","//Edit[@Name='TDS Trans Nature']", dataFile,"PurchaseVouchersAgainstOrders","tdsNature");
        inputTextWithValidation("xpath", "//Edit[@Name='Supplier Bill No *']",String.valueOf(common.getRandom()));
        inputTextWithValidation("xpath", "//Edit[@Name='Supplier Bill Date *']",Time.timeStamp());
        enterInput("xpath", "//Edit[@Name='Batch Policy']",dataFile,"PurchaseVouchersAgainstOrders", "batchPolicy");


        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("purchaseVoucherAgainstPurchaseOrders GeneralInformation",duration/1000000000);

        long start1 = System.nanoTime();

        List<WebElement> items = common.findWebElements("xpath", "//Pane[@Name='  F3 Items  ']/Pane/Pane/Pane/Table[@Name='Items']/*[starts-with(@Name,'Row')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 400, 0);
        for (int i = 0; i <= 1; i++) {
            WebElement productList = items.get(i);
            String value = productList.getAttribute("LegacyValue");
            if (!value.equals("(null)")){
                String pendingQty = common.findWebElement("xpath", "//Edit[@Name='Pending Quantity In SKU Row "+i+", Not sorted.']").getText();
                WebElement quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity Row "+i+", Not sorted.']");
                quantity.click();
                quantity.sendKeys(pendingQty, Keys.TAB);
                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 800, 0);
                enterItemsOtherCosts(dataFile,"PurchaseVouchersAgainstOrders",i);
                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -800, 0);
            }
        }

        long duration1 = System.nanoTime() - start1;
        FileUtil.writeTimeLog("purchaseVoucherAgainstPurchaseOrders Enter Products",duration1/1000000000);

        long start2 = System.nanoTime();

        serialNumberForMRAO(dataFile,"PurchaseVouchersAgainstOrders","serialText","serialQuantity","");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 800, 0);
        enterItemsOtherCosts(dataFile,"PurchaseVouchersAgainstOrders",2);
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -800, 0);
        enterServices(dataFile,"PurchaseVouchersAgainstOrders");
        chargesAndDeductionsCalculations1(dataFile,"PurchaseVouchersAgainstOrders", "charges","deductions","chargesAcc","deductionsAcc", "chargesAmount", "deductionsAmount", "chargesRowCount");
        enterOtherCharges(dataFile,"PurchaseVouchersAgainstOrders");
        navigateToBillsReceivablesTab();
        common.deleteInvalidRows();
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        validateTDS(dataFile,"PurchaseVouchersAgainstOrders");
        validateTCS(dataFile,"PurchaseVouchersAgainstOrders");
        enterOtherCosts(dataFile,"PurchaseVouchersAgainstOrders");
        navigateToItemsOtherCosts();
        moveToRight(7);
        enterCash(dataFile,"PurchaseVouchersAgainstOrders");
        enterChequesInPurchase(dataFile,"PurchaseVouchersAgainstOrders");
        enterPostDatedChequesInPurchase(dataFile,"PurchaseVouchersAgainstOrders");
        enterChequesPDCInPurchase(dataFile,"PurchaseVouchersAgainstOrders");
        navigateToOtherInfoTab();
        inputTextWithValidation("xpath", "//Edit[@Name='Reference Bill Date']", common.getData(dataFile,"PurchaseOrdersAgainstEnquiries","billRefDate")+ Time.timeStamp());
        termsAndConditions(dataFile,"PurchaseVouchersAgainstOrders");

        long duration2 = System.nanoTime() - start2;
        FileUtil.writeTimeLog("purchaseVoucherAgainstPurchaseOrders validating Tab Items before Summary",duration2/1000000000);

        long start5 = System.nanoTime();

        navigateToSummaryTab();quantityPresentInSummary();grossAmountPresentInSummary();servicesAmountPresentInSummary();grossMinusDiscountPresentInSummary();iGSTPresentInSummary();cessPresentInSummary();servicesIGSTPresentInSummary();servicesCESSPresentInSummary();netAmountPresentInSummary();chargesPresentInSummary();deductionsPresentInSummary();otherChargesPresentInSummary();otherChargesIGSTPresentInSummary();otherChargesCESSPresentInSummary();otherCostsAmountPresentInSummary();tcsTaxableValuePresentInSummary();tcsAmountPresentInSummary();tdsAmountPresentInSummary();payableAfterTdsPresentInSummary();totalValuePresentInSummary();totalValueInCompanyCurrenyPresentInSummary();cashPresentInSummary();chequesPresentInSummary();postDatedChequesPresentInSummary();chequesPDCPresentInSummary();paymentsValuePresentInSummary();payableAMountPresentInSummary();

        long duration5 = System.nanoTime() - start5;
        FileUtil.writeTimeLog("purchaseVoucherAgainstPurchaseOrders only summary",duration5/1000000000);

        long start3 = System.nanoTime();

        transactionSave();
        String transactionId = newTransactionID(oldVoucherID);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Vouchers against Orders'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        verifyReport(transactionId,dataFile,"PurchaseVouchersAgainstOrders");

        long duration3 = System.nanoTime() - start3;
        FileUtil.writeTimeLog("purchaseVoucherAgainstPurchaseOrders SaveAndVerify Report", duration3 /1000000000);

        return transactionId;
    }
}
