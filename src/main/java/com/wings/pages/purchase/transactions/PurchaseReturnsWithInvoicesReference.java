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

public class PurchaseReturnsWithInvoicesReference extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchaseReturnsWithInvoicesReference(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchaseReturnsWithInvoicesReference(String voucherNum) throws InterruptedException, IOException, ParseException {

        long start = System.nanoTime();

        navigateToMastersWhen3Steps("Purchase","Invoices","Purchase Returns with Invoice Reference");
        Thread.sleep(3000);
        String oldVoucherID = oldTTransactionID();
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "PurchaseVouchersAgainstOrders", "branch");
//        enterInput("xpath", "//Edit[@Name='Location *']",dataFile,"PurchaseReturnsWithInvoiceReference(Orders)");
//        enterInput("xpath", "//Edit[@Name='Trans Currency *']",dataFile,"PurchaseReturnsWithInvoiceReference(Orders)");
        inputTextWithValidation("xpath", "//Edit[@Name='Purchase VNo *']",voucherNum);
        common.clickElement("xpath", "//Edit[@Name='Cash/Party *']");
        Thread.sleep(2000);
        common.clickElement("xpath","//Button[@Name='OK']");
        enterInput("xpath", "//Edit[@Name='Purchase Return A/c Code']", dataFile, "PurchaseReturnsWithInvoiceReference(Orders)", "PurchaseReturnAccCode");
        enableCheckboxSelection("//CheckBox[@Name='Apply TCS']");
        enterInput("xpath","//Edit[@Name='TCS Trans Nature']", dataFile,"PurchaseReturnsWithInvoiceReference(Orders)","tcsNature");
//        enterInput("xpath","//Edit[@Name='priceList']", dataFile,"PurchaseVouchersAgainstOrders","tcsNature");
//        enterInput("xpath","//Edit[@Name='executive']", dataFile,"PurchaseVouchersAgainstOrders","tcsNature");


        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("purchaseReturns generalInformation", duration /1000000000);

        long start1 = System.nanoTime();


        List<WebElement> items = common.findWebElements("xpath", "//Pane[@Name='  F3 Items  ']/Pane/Pane/Pane/Table[@Name='Items']/*[starts-with(@Name,'Row')]");
        System.out.println(items.size());
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 400, 0);
        for (int i = 0; i<items.size(); i++) {
            WebElement productList = items.get(i);
            String value = productList.getAttribute("LegacyValue");
            if (!value.equals("(null)")){
                String pendingQty = common.findWebElement("xpath", "//Edit[@Name='Pending Quantity In SKU Row "+i+", Not sorted.']").getText();
                WebElement quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity Row "+i+", Not sorted.']");
                quantity.click();
                quantity.sendKeys(pendingQty, Keys.TAB);
            }
        }


        long duration1 = System.nanoTime() - start1;
        FileUtil.writeTimeLog("purchaseReturns Products Enter", duration1 /1000000000);


        long start2 = System.nanoTime();

        chargesAndDeductionsCalculations1(dataFile,"PurchaseReturnsWithInvoiceReference(Orders)", "charges","deductions","chargesAcc","deductionsAcc", "chargesAmount", "deductionsAmount", "chargesRowCount");
        enterOtherCharges(dataFile,"PurchaseReturnsWithInvoiceReference(Orders)");
        navigateToBillsPayablesTab();
        common.deleteInvalidRows();
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        validateTCS(dataFile,"PurchaseReturnsWithInvoiceReference(Orders)");
        enterCash(dataFile,"PurchaseReturnsWithInvoiceReference(Orders)");
        enterChequesInPRWIR(dataFile,"PurchaseReturnsWithInvoiceReference(Orders)");
        enterPostDatedChequesInPRWIR(dataFile,"PurchaseReturnsWithInvoiceReference(Orders)");
        enterChequesPDCInPRWIR(dataFile,"PurchaseReturnsWithInvoiceReference(Orders)");
        navigateToOtherInfoTab();
        inputTextWithValidation("xpath", "//Edit[@Name='Reference Bill Date']", common.getData(dataFile,"PurchaseReturnsWithInvoiceReference(Orders)","billRefDate")+ Time.timeStamp());
        termsAndConditions(dataFile,"PurchaseReturnsWithInvoiceReference(Orders)");
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
        tcsTaxableValuePresentInSummary();
        tcsAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        cashPresentInSummary();
        chequesPresentInSummary();
        postDatedChequesPresentInSummary();
        chequesPDCPresentInSummary();
        receiptsValuePresentInSummary();
        receivableMountPresentInSummary();

        long duration2 = System.nanoTime() - start2;
        FileUtil.writeTimeLog("purchaseReturns validating Tab Items UpTo summary", duration2 /1000000000);

        long start3 = System.nanoTime();

        transactionSave();
        String transactionId = newTransactionID(oldVoucherID);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Returns with Invoice Reference'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        verifyReport(transactionId,dataFile,"PurchaseReturnsWithInvoiceReference(Orders)");

        long duration3 = System.nanoTime() - start3;
        FileUtil.writeTimeLog("purchaseReturns SaveAndVerify", duration3 /1000000000);
    }
}
