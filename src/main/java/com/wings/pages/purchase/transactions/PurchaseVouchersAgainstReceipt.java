package com.wings.pages.purchase.transactions;

import com.wings.pages.TransactionsBaseClass;
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
import java.util.List;

public class PurchaseVouchersAgainstReceipt extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    int otherChargesInclusive = 0;
    boolean IsAmountHeaderClicked = false;
    boolean otherChargesGSTCheckBox = false;
    boolean gstAmountClicked = false;
    boolean discountIsClicked = false;

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
        enterVoucherType(dataFile,"PurchaseVoucherAgainstReceipts","voucherType");
        enterDate();
        enterBranchName(dataFile,"PurchaseVoucherAgainstReceipts","branch");
        enterLocation(dataFile,"PurchaseVoucherAgainstReceipts","location");
        enterCurrency(dataFile,"PurchaseVoucherAgainstReceipts","currency");
        enterPartyCode(dataFile,"PurchaseVoucherAgainstReceipts","partyCode");
        selectPendingsSalesOrder(voucherNum,common.getData(dataFile,"PurchaseVoucherAgainstReceipts","FYear"));
        Thread.sleep(10000);
        common.clickElement("xpath","//Button[@Name='OK']");
//        common.clickElement("xpath","//Window[contains(@Name,'Wings Finance - PRO ')]/*/Button[@Name='OK']");
        enterCreditPeriod(dataFile,"PurchaseVoucherAgainstReceipts","creditPeriod");
        enterPurchaseAccountCODE(dataFile,"PurchaseVoucherAgainstReceipts","PurchaseAccCode");
        enableCheckboxSelection("//CheckBox[@Name='Apply TCS']");
        enterTcsTransNature(dataFile,"PurchaseVoucherAgainstReceipts","tcsNature");
        enableCheckboxSelection("//CheckBox[@Name='Deduct TDS']");
        enterTdsTransNature(dataFile,"PurchaseVoucherAgainstReceipts","tdsNature");
        enterSuppliersBillNumber();
        enterSuppliersBillDate();
        enterPriceList(dataFile,"PurchaseVoucherAgainstReceipts","priceList");
        enterExecutive(dataFile,"PurchaseVoucherAgainstReceipts","executive");
        enterRemarks(dataFile,"PurchaseVoucherAgainstReceipts","remarks");

        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("PurchaseVoucherAgainstReceipts GeneralInformation",duration/1000000000);

        long start1 = System.nanoTime();

        long start2 = System.nanoTime();
        enterPendings();
        long duration1 = System.nanoTime() - start2;
        FileUtil.writeTimeLog("Enter Pendings in MRAO include Assertions", duration1 /1000000000);

//        List<WebElement> items = common.findWebElements("xpath", "//Pane[@Name='  F3 Items  ']/Pane/Pane/Pane/Table[@Name='Items']/*[starts-with(@Name,'Row')]");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 400, 0);
//        for (int i = 0; i <= 1; i++) {
//            WebElement productList = items.get(i);
//            String value = productList.getAttribute("LegacyValue");
//            if (!value.equals("(null)")){
//                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 800, 0);
//                enterItemsOtherCosts(dataFile,"PurchaseVoucherAgainstReceipts",i);
//                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -800, 0);
//            }
//        }
//        long duration1 = System.nanoTime() - start1;
//        FileUtil.writeTimeLog("PurchaseVoucherAgainstReceipts Enter Products",duration1/1000000000);
//
//        long start2 = System.nanoTime();
//
//        common.clickElement("xpath","//Button[@Name='Serial No Row 2']");
//        common.clickElement("xpath","//Button[@Name='OK']");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 850, 0);
//        enterItemsOtherCosts(dataFile,"PurchaseVoucherAgainstReceipts",2);
//        enterServices(dataFile,"PurchaseVoucherAgainstReceipts");
//        chargesAndDeductionsCalculations1(dataFile,"PurchaseVoucherAgainstReceipts", "charges","deductions","chargesAcc","deductionsAcc", "chargesAmount", "deductionsAmount", "chargesRowCount");
//        enterOtherCharges(dataFile,"PurchaseVoucherAgainstReceipts");
//        navigateToBillsReceivablesTab();
//        common.deleteInvalidRows();
//        validateIGSTAmountTabIsNotEmpty();
//        validateCESSAmountTabIsNotEmpty();
//        validateTDS(dataFile,"PurchaseVoucherAgainstReceipts");
//        validateTCS(dataFile,"PurchaseVoucherAgainstReceipts");
//        enterOtherCosts(dataFile,"PurchaseVoucherAgainstReceipts");
//        navigateToItemsOtherCosts();
//        moveToRight(7);
//        enterCash(dataFile,"PurchaseVoucherAgainstReceipts");
//        enterChequesInPurchase(dataFile,"PurchaseVoucherAgainstReceipts");
//        enterPostDatedChequesInPurchase(dataFile,"PurchaseVoucherAgainstReceipts");
//        enterChequesPDCInPurchase(dataFile,"PurchaseVoucherAgainstReceipts");
//        navigateToOtherInfoTab();
//        inputTextWithValidation("xpath", "//Edit[@Name='Reference Bill Date']", common.getData(dataFile,"PurchaseVoucherAgainstReceipts","billRefDate")+ Time.timeStamp());
//        termsAndConditions(dataFile,"PurchaseVoucherAgainstReceipts");
//        navigateToSummaryTab();quantityPresentInSummary();
//        grossAmountPresentInSummary();
//        servicesAmountPresentInSummary();
//        grossMinusDiscountPresentInSummary();
//        iGSTPresentInSummary();
//        cessPresentInSummary();
//        servicesIGSTPresentInSummary();
//        servicesCESSPresentInSummary();
//        netAmountPresentInSummary();
//        chargesPresentInSummary();
//        deductionsPresentInSummary();
//        otherChargesPresentInSummary();
//        otherChargesIGSTPresentInSummary();
//        otherChargesCESSPresentInSummary();
//        otherCostsAmountPresentInSummary();
//        tcsTaxableValuePresentInSummary();
//        tcsAmountPresentInSummary();
//        tdsAmountPresentInSummary();
//        payableAfterTdsPresentInSummary();
//        totalValuePresentInSummary();
//        totalValueInCompanyCurrenyPresentInSummary();
//        cashPresentInSummary();
//        chequesPresentInSummary();
//        postDatedChequesPresentInSummary();
//        PDCPresentInSummary();
//        paymentsValuePresentInSummary();
//        payableAMountPresentInSummary();
//
//        long duration2 = System.nanoTime() - start2;
//        FileUtil.writeTimeLog("PurchaseVoucherAgainstReceipts validating Tab Items UpTo summary",duration2/1000000000);
//
//
//        long start3 = System.nanoTime();
//
//        transactionSave();
        String transactionId = newTransactionID(oldVoucherID);
//        common.clickElement("name", "Purchase");
//        common.clickElement("name", "Invoices");
//        common.clickElement("xpath", "//MenuItem[@Name='Purchase Vouchers against Receipts'][2]");
//        Thread.sleep(1000);
//        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
//        verifyReport(transactionId,dataFile,"PurchaseVoucherAgainstReceipts");
//
//        long duration3 = System.nanoTime() - start3;
//        FileUtil.writeTimeLog("PurchaseVoucherAgainstReceipts SaveAndVerify Report", duration3 /1000000000);

        return transactionId;
    }

    public void enterPendings() throws IOException, ParseException, InterruptedException {
//        List<WebElement> productUOMRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row')]");
        List<WebElement> productCodeRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Product Code Row')]");
        List<WebElement> productTypeRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Product Type Row')]");
//        List<WebElement> productPendingQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Pending Quantity Row')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
        List<WebElement> productQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity Row ')]");
        List<WebElement> freeQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Free Quantity Row')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -100, 0);
//        List<WebElement> mrpRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'MRP Row ')]");
//        List<WebElement> mrpAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'MRP Amount Row ')]");
//        List<WebElement> unitRateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Unit Rate Row ')]");
//        List<WebElement> grossAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Gross Amount Row ')]");
//
//        if (!discountIsClicked) {
//            common.clickElement("xpath", "//Header[@Name='Disc Amount 1']");
//            common.clickElement("xpath", "//Header[@Name='Disc Amount 2']");
//            common.clickElement("xpath", "//Header[@Name='Disct Amount 3']");
//            discountIsClicked = true;
//        }
//
//        List<WebElement> discountBasis1RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 1 Row ')]");
//        List<WebElement> disount1RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 1 Row ')]");
//        List<WebElement> disountAmount1RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Amount 1 Row ')]");
//        List<WebElement> disount2BasisRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 2 Row ')]");
//        List<WebElement> disount2RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 2 Row ')]");
//        List<WebElement> disountAmount2RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Amount 2 Row ')]");
//        List<WebElement> disount3BasisRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 3 Row ')]");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 550, 0);
//        List<WebElement> disount3RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 3 Row ')]");
//        List<WebElement> disountAmount3RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disct Amount 3 Row ')]");
//        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
//        List<WebElement> gstProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
//        List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
//        List<WebElement> taxableValueRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Taxable Value Row ')]");
//        if (!gstAmountClicked) {
//            common.clickElement("xpath", "//Header[@Name='GST Amount']");
//            gstAmountClicked = true;
//        }
//        List<WebElement> igstAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'IGST Row ')]");
//        List<WebElement> cessAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Row ')]");
//        List<WebElement> gstAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Amount Row ')]");
//        List<WebElement> netAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount Row ')]");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 250, 0);
//        List<WebElement> netInCompnayCurrencyAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount In Company Currency Row ')]");
//        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
//        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
//        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
//        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
//        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
//        List<WebElement> infoRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 1 Row ')]");
//        List<WebElement> valueRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 1 Row ')]");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1200, 0);
//


        for (int j = 0; j < Integer.parseInt(common.getData(dataFile, "MaterialReceiptsAgainstOrders", "productCount")); j++) {
//            enterData(productTypeRowList.get(j), dataFile, "PurchaseVoucherAgainstReceipts", "uom" + j);
            if (!productCodeRowList.get(j).getText().contains(common.getData(dataFile,"PurchaseVoucherAgainstReceipts","productType")) && productTypeRowList.get(j).getAttribute("LegacyValue").equals("Billed")) {
//                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
                enterData(productQuantityRowList.get(j), dataFile, "PurchaseVoucherAgainstReceipts", "quantity" + j);
                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -100, 0);
            } else if (productCodeRowList.get(j).getText().contains(common.getData(dataFile,"PurchaseVoucherAgainstReceipts","productType"))&& productTypeRowList.get(j).getAttribute("LegacyValue").equals("Billed")) {
//                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
                Thread.sleep(1500);
                common.clickElement("xpath", "//Button[@Name='Serial No Row " + j + "']");
                common.clickElement("xpath", "//Button[@Name='OK']");
//                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -100, 0);
            }
            else if (productTypeRowList.get(j).getAttribute("LegacyValue").equals("Free")){
                enterData(freeQuantityRowList.get(j), dataFile, "PurchaseVoucherAgainstReceipts", "freeQuantity" + j);
            }
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 550, 0);
//            enterData(freeQuantityRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "freeQuantity" + j);
//            enterData(mrpRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "mrpAmount" + j);
//            Assert.assertEquals(mrpAmountRowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedMrpAmount" + j), "MRP Amount mismatch");
//            enterData(unitRateRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "unitRate" + j);
//            Assert.assertEquals(grossAmountRowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedGrossAmount" + j), "Gross Amount mismatch");
//            enterData(discountBasis1RowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "discount1Basis" + j);
//            enterData(disount1RowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "discount1Value" + j);
//            Assert.assertEquals(disountAmount1RowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedDiscount1Value" + j), "Disc 1 amount is mismatch");
//
//            enterData(disount2BasisRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "discount2Basis" + j);
//            enterData(disount2RowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "discount2Value" + j);
//            Assert.assertEquals(disountAmount2RowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedDiscount2Value" + j), "Disc 2 amount is mismatch");
//
//            enterData(disount3BasisRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "discount3Basis" + j);
//            enterData(disount3RowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "discount3Value" + j);
//            Assert.assertEquals(disountAmount3RowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedDiscount3Value" + j), "Disc 3 amount is mismatch");
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", Integer.parseInt(common.getData(dataFile, "MaterialReceiptsAgainstOrders", "slideHandleFirst" + j)), 0);
//            enterData(hsnCodeRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "HSNCode" + j);
//            enterData(gstProductCategoryRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "GSTPercentage" + j);
//            enterData(cessProductCategoryRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "CESSPercentage" + j);
//            Assert.assertEquals(taxableValueRowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedTaxableValue" + j), "Taxable value mismatch");
//            Assert.assertEquals(igstAmountRowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedIGSTAmount" + j), "IGST mismatch");
//            Thread.sleep(2000);
//            Assert.assertEquals(cessAmountRowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedCESSAmount" + j), "CESS mismatch");
//            Assert.assertEquals(gstAmountRowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedGSTAmount" + j), "GST Amount mismatch");
//            Assert.assertEquals(netAmountRowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedNetAmount" + j), "Net Amount mismatch");
//            Assert.assertEquals(netInCompnayCurrencyAmountRowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedNetAmountInCompanyCurrency" + j), "Net Amount in Company currency mismatch");
//            enterData(departmentRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "department" + j);
//            enterData(projectRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "project" + j);
////            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
//            enterData(profitCentreRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "profitCentre" + j);
//            enterData(costCentreRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "costCentre" + j);
//            enterData(commentsRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "comments" + j);
//            enterData(infoRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "info" + j);
//            enterData(valueRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "value" + j);
//            common.findWebElement("xpath", "//Edit[@Name='Date 1 Row " + j + ", Not sorted.']").sendKeys(Time.timeStamp());
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1400, 0);

        }
    }

}
