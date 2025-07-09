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
public class PurchaseVoucher extends TransactionsBaseClass {
    WindowsDriver driver, rootDriver;
    Common common;
    String dataFile;

    int servicesInclusive = 0;
    int otherChargesInclusive = 0;
    int otherDeductionsInclusive = 0;
    boolean IsAmountHeaderClicked = false;
    boolean otherChargesGSTCheckBox = false;
    boolean gstAmountClicked = false;
    boolean discountIsClicked = false;
    boolean otherDeductionsGSTCheckBox = false;


    public PurchaseVoucher(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String purchaseVoucher() throws InterruptedException, IOException, AWTException, ParseException {
        navigateToMastersWhen3Steps("Purchase", "Invoices", "Purchase Vouchers");
        Thread.sleep(5000);
        String oldVoucherID = oldTTransactionID();

        long start1 = System.nanoTime();

        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        enterDate();
        enterBranchName(dataFile,"GeneralInformation","Branch");
        enterLocation(dataFile,"GeneralInformation","Location");
//        enterCurrency(dataFile,"GeneralInformation","currency");
        enterCashOrParty(dataFile,"GeneralInformation","PartyAccountCode");
        Thread.sleep(1000);
        enterConsigner(dataFile,"GeneralInformation","Consignor");
        enterCreditPeriod(dataFile,"GeneralInformation","CreditPeriod");
        enterPurchaseAccountCode(dataFile,"GeneralInformation","PurchaseAccount");
        enterSuppliersBillNumber();
        enterSuppliersBillDate();
        enterBatchPolicy(dataFile,"GeneralInformation","BatchPolicy");
        enableCheckboxSelection("//CheckBox[@Name='Apply TCS']");
//        enterTcsTransNature(dataFile,"GenInfo","tcsNature");
        enableCheckboxSelection("//CheckBox[@Name='Deduct TDS']");
//        enterTdsTransNature(dataFile,"GenInfo","tdsNature");
        enterPriceList(dataFile,"GeneralInformation","PriceList");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

//        enterVoucherType(dataFile,"PurchaseVoucher","voucherType");
//        enterDate();
//        enterBranchName(dataFile,"PurchaseVoucher","branch");
//        enterLocation(dataFile,"PurchaseVoucher","location");
//        enterCurrency(dataFile,"PurchaseVoucher","currency");
//        enterCashOrParty(dataFile,"PurchaseVoucher","partyCode");
//        Thread.sleep(1000);
//        enterConsigner(dataFile,"PurchaseVoucher","Consigner");
//        enterCreditPeriod(dataFile,"PurchaseVoucher","creditPeriod");
//        enterPurchaseAccountCode(dataFile,"PurchaseVoucher","PurchaseAccCode");
//        enterSuppliersBillNumber();
//        enterSuppliersBillDate();
//        enterBatchPolicy(dataFile,"PurchaseVoucher","batchPolicy");
//        enableCheckboxSelection("//CheckBox[@Name='Apply TCS']");
//        enterTcsTransNature(dataFile,"PurchaseVoucher","tcsNature");
//        enableCheckboxSelection("//CheckBox[@Name='Deduct TDS']");
//        enterTdsTransNature(dataFile,"PurchaseVoucher","tdsNature");
//        enterPriceList(dataFile,"PurchaseVoucher","priceList");
//        enterExecutive(dataFile,"PurchaseVoucher","executive");
//        enterRemarks(dataFile,"PurchaseVoucher","remarks");

        long duration1 = System.nanoTime() - start1;
        FileUtil.writeTimeLog("General information PV", duration1 /1000000000);

        long start = System.nanoTime();
        addProduct();
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Enter Products include Assertions", duration /1000000000);

        common.clickElement("xpath","//TabItem[contains(@Name,'Services')]");
        long start2 = System.nanoTime();
        addServices();
        long duration2 = System.nanoTime() - start2;
        FileUtil.writeTimeLog("Enter services include Assertions", duration2 /1000000000);

        long start3 = System.nanoTime();
        addChargesAndDeductions();
        long duration3 = System.nanoTime() - start3;
        FileUtil.writeTimeLog("Enter Charges And Deductions include Assertions", duration3 /1000000000);

        long start4 = System.nanoTime();
        addOtherCharges();
        long duration4 = System.nanoTime() - start4;
        FileUtil.writeTimeLog("Enter Other Charges include Assertions", duration4 /1000000000);

        long start5 = System.nanoTime();
        navigateToOtherDeductionsTab();
        addOtherDeductions();
        long duration5 = System.nanoTime() - start5;
        FileUtil.writeTimeLog("Enter Other Deductions include Assertions", duration5 /1000000000);

        navigateToBillsReceivablesTab();
        common.deleteInvalidRows();
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        validateTDS(dataFile,"PurchaseVoucher");
        validateTCS(dataFile,"PurchaseVoucher");


        long start6 = System.nanoTime();
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Other Costs ')]");
        elements.get(0).click();
        addOtherCosts();
        long duration6 = System.nanoTime() - start6;
        FileUtil.writeTimeLog("Enter Other Costs include Assertions", duration6 /1000000000);
        elements.get(0).click();
        moveToRight(10);
        navigateToItemsOtherCosts(dataFile,"PurchaseVoucher");

        long start7 = System.nanoTime();
        addCash();
        long duration7 = System.nanoTime() - start7;
        FileUtil.writeTimeLog("Enter Cash include Assertions", duration7 /1000000000);

        long start8 = System.nanoTime();
        addCheques();
        long duration8 = System.nanoTime() - start8;
        FileUtil.writeTimeLog("Enter Cheque include Assertions", duration8 /1000000000);

        long start9 = System.nanoTime();
        addPostDatedCheques();
        long duration9 = System.nanoTime() - start9;
        FileUtil.writeTimeLog("Enter Post Dated Cheques include Assertions", duration9 /1000000000);

        long start10 = System.nanoTime();
        addChequesPDC();
        long duration10 = System.nanoTime() - start10;
        FileUtil.writeTimeLog("Enter Cheque PDC include Assertions", duration10 /1000000000);

        long start13 = System.nanoTime();
        enterOtherInfo(dataFile,"PurchaseVoucher");
        long duration13 = System.nanoTime() - start13;
        FileUtil.writeTimeLog("Enter Other Info", duration13 /1000000000);

        long start14 = System.nanoTime();
        enterAdditionalInfo(dataFile,"PurchaseVoucher");
        long duration14 = System.nanoTime() - start14;
        FileUtil.writeTimeLog("Enter Additional Information", duration14 /1000000000);

        long start11 = System.nanoTime();
        common.clickElement("xpath","//TabItem[contains(@Name,'Terms And Conditions')]");
        addTermsAndConditions();
        long duration11 = System.nanoTime() - start11;
        FileUtil.writeTimeLog("Enter Terms And Conditions", duration11 /1000000000);


        long start12 = System.nanoTime();
        addAllocations();
        long duration12 = System.nanoTime() - start12;
        FileUtil.writeTimeLog("Enter Allocations", duration12 /1000000000);

        long startSum = System.nanoTime();

        navigateToSummaryTab();
        List<WebElement> summary = common.findWebElements("xpath", "//Pane[@Name='Summary']//Edit/*");
        System.out.println("Size " + summary.size());
//        for (WebElement list : summary) {
//            System.out.println(list.getAttribute("Name"));
//        }
        assertSummaryFields(summary,"Quantity",common.getData(dataFile,"PurchaseVoucher","expectedQuantity"));
        assertSummaryFields(summary,"Free Quantity",common.getData(dataFile,"PurchaseVoucher","expectedFreeQuantity"));
        assertSummaryFields(summary,"Service Quantity",common.getData(dataFile,"PurchaseVoucher","expectedServiceQuantity"));
        assertSummaryFields(summary,"Gross Amount",common.getData(dataFile,"PurchaseVoucher","expectedGrossAmount"));
        assertSummaryFields(summary,"Service Amount",common.getData(dataFile,"PurchaseVoucher","expectedServiceAmount"));
        assertSummaryFields(summary,"Discount",common.getData(dataFile,"PurchaseVoucher","expectedDiscount"));
        assertSummaryFields(summary,"Gross - Disc",common.getData(dataFile,"PurchaseVoucher","expectedGrossMinusDiscount"));
        assertSummaryFields(summary,"IGST",common.getData(dataFile,"PurchaseVoucher","expectedIGST"));
        assertSummaryFields(summary,"CESS",common.getData(dataFile,"PurchaseVoucher","expectedCESS"));
        assertSummaryFields(summary,"Services IGST",common.getData(dataFile,"PurchaseVoucher","expectedServicesIGST"));
        assertSummaryFields(summary,"Services CESS",common.getData(dataFile,"PurchaseVoucher","expectedServicesCESS"));
        assertSummaryFields(summary,"Net Amount",common.getData(dataFile,"PurchaseVoucher","expectedNetAmount"));
        assertSummaryFields(summary,"Charges",common.getData(dataFile,"PurchaseVoucher","expectedChargesAmount"));
        assertSummaryFields(summary,"Deductions",common.getData(dataFile,"PurchaseVoucher","expectedDeductionsAmount"));
        assertSummaryFields(summary,"Other Charges",common.getData(dataFile,"PurchaseVoucher","expectedOtherChargesAmount"));
        assertSummaryFields(summary,"Other Charges IGST",common.getData(dataFile,"PurchaseVoucher","expectedOtherChargesIGST"));
        assertSummaryFields(summary,"Other Charges CESS",common.getData(dataFile,"PurchaseVoucher","expectedOtherChargesCESS"));
        assertSummaryFields(summary,"Other Deductions",common.getData(dataFile,"PurchaseVoucher","expectedOtherDeductionsAmount"));
        assertSummaryFields(summary,"Other Deductions IGST",common.getData(dataFile,"PurchaseVoucher","expectedOtherDeductionsIGST"));
        assertSummaryFields(summary,"Other Deductions CESS",common.getData(dataFile,"PurchaseVoucher","expectedOtherDeductionsCESS"));
        assertSummaryFields(summary,"Other Cost Amount",common.getData(dataFile,"PurchaseVoucher","expectedOtherCostAmount"));
        assertSummaryFields(summary,"TCS Taxable Value",common.getData(dataFile,"PurchaseVoucher","expectedTCSTaxableValue"));
        assertSummaryFields(summary,"TCS Amount",common.getData(dataFile,"PurchaseVoucher","expectedTCSAmount"));
        assertSummaryFields(summary,"TDS Amount",common.getData(dataFile,"PurchaseVoucher","expectedTDSAmount"));
        assertSummaryFields(summary,"Total Value After TDS",common.getData(dataFile,"PurchaseVoucher","expectedTotalValueAfterTDS"));
        assertSummaryFields(summary,"Total Value",common.getData(dataFile,"PurchaseVoucher","expectedTotalValueAmount"));
        assertSummaryFields(summary,"Total Value In Company Currency",common.getData(dataFile,"PurchaseVoucher","expectedTotalValueInCompanyCurrency"));
        assertSummaryFields(summary,"Cash",common.getData(dataFile,"PurchaseVoucher","expectedCashAmount"));
        assertSummaryFields(summary,"Cheques",common.getData(dataFile,"PurchaseVoucher","expectedChequeAmount"));
        assertSummaryFields(summary,"Post Dated Cheques",common.getData(dataFile,"PurchaseVoucher","expectedPostDatedChequesAmount"));
        assertSummaryFields(summary,"Cheques [PDC]",common.getData(dataFile,"PurchaseVoucher","expectedChequesPDCAmount"));
        assertSummaryFields(summary,"Payments Value",common.getData(dataFile,"PurchaseVoucher","expectedPaymentValue"));
        assertSummaryFields(summary,"Payable Amount",common.getData(dataFile,"PurchaseVoucher","expectedPayableAmount"));

        long durationSum = System.nanoTime() - startSum;
        FileUtil.writeTimeLog("Purchase Voucher Summary",durationSum/1000000000);

        transactionSave();
        String newVoucherID = newTransactionID(oldVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
//        String prefix = newVoucherID.replaceAll("\\d", "");String number = newVoucherID.replaceAll("\\D", "");
//        Thread.sleep(2000);
//        navigateToMastersWhen3Steps("Tools","Automated Testing","Generate Input File");
//        rootDriver=common.initializeDriver("Root");
//        Thread.sleep(3000);
//        common.findWebElement("xpath", "//Window[@Name='Export Transaction Postings']/Pane/Edit[@Name='Voucher Series']").sendKeys(prefix);
//        common.findWebElement("xpath","//Window[@Name='Export Transaction Postings']/Pane/Edit[@Name='Voucher Number']").sendKeys(number);
//        common.clickElement("xpath","//Button[@Name='OK']");
//        Thread.sleep(1500);
//        if (common.findWebElement("xpath","//Text").getText().equals("Data Exported successfully!")) {
//            common.clickElement("xpath", "//Button[@Name='OK']");
//        }
//        else if(common.findWebElement("xpath","//Text").getText().equals("Transactionno doesnot exist.")){
//          Assert.fail("Transaction does not exists");
//          common.clickElement("xpath", "//Button[@Name='OK']");
//        }
//
//        Thread.sleep(2000);
//        navigateToMastersWhen3Steps("Tools","Automated Testing","Generate Output File");
//        rootDriver=common.initializeDriver("Root");
//        Thread.sleep(3000);
//        common.findWebElement("xpath", "//Window[@Name='Export Transaction Postings']/Pane/Edit[@Name='Voucher Series']").sendKeys(prefix);
//        common.findWebElement("xpath","//Window[@Name='Export Transaction Postings']/Pane/Edit[@Name='Voucher Number']").sendKeys(number);
//        common.clickElement("xpath","//Button[@Name='OK']");
//        Thread.sleep(1500);
//        if (common.findWebElement("xpath","//Text").getText().equals("Data Exported successfully!")) {
//            common.clickElement("xpath", "//Button[@Name='OK']");
//        }
//        else if(common.findWebElement("xpath","//Text").getText().equals("Transactionno doesnot exist.")){
//            Assert.fail("Transaction does not exists");
//            common.clickElement("xpath", "//Button[@Name='OK']");
//        }
        return newVoucherID;
    }


    public void addProduct() throws InterruptedException, IOException, ParseException, AWTException {
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseVoucher", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Product Code Row " + v + ", Not sorted.']", dataFile, "PurchaseVoucher", "productCode" + v);
        }
        List<WebElement> productAccRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Purchase Account * Row')]");
        List<WebElement> productUOMRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row')]");
        List<WebElement> productQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity Row ')]");
        List<WebElement> freeQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Free Quantity Row')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        List<WebElement> numOfPacksRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'No Of Packs Row ')]");
        List<WebElement> mrpRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'MRP Row ')]");
        List<WebElement> mrpAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'MRP Amount Row ')]");
        List<WebElement> unitRateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Unit Rate Row ')]");
        List<WebElement> editableGrossAmountList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Editable Gross Amount Row ')]");
        List<WebElement> grossAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Gross Amount Row ')]");


        List<WebElement> voucherDiscountList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Voucher Disc % Row ')]");
        List<WebElement> voucherDiscountAmountList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Voucher Disc Row ')]");
        if (!discountIsClicked) {
            common.clickElement("xpath", "//Header[@Name='Disc Amount 1']");
            common.clickElement("xpath", "//Header[@Name='Disc Amount 2']");
            common.clickElement("xpath", "//Header[@Name='Disc Amount 3']");
            discountIsClicked = true;
        }
        List<WebElement> discountBasis1RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 1 Row ')]");
        List<WebElement> disount1RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 1 Row ')]");
        List<WebElement> disountAmount1RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Amount 1 Row ')]");
        List<WebElement> disount2BasisRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 2 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 430, 0);
        List<WebElement> disount2RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 2 Row ')]");
        List<WebElement> disountAmount2RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Amount 2 Row ')]");
        List<WebElement> disount3BasisRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 3 Row ')]");
        List<WebElement> disount3RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 3 Row ')]");
        List<WebElement> disountAmount3RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Amount 3 Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> gstProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
        List<WebElement> taxableValueRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Taxable Value Row ')]");
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
        List<WebElement> igstAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'IGST Row ')]");
        List<WebElement> cessAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Row ')]");
        List<WebElement> gstAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Amount Row ')]");
        List<WebElement> netAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount Row ')]");
        List<WebElement> netInCompnayCurrencyAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount In Company Currency Row ')]");
        List<WebElement> tcsTaxableAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TCS Taxable Value Row ')]");
        List<WebElement> totalItemOtherCostRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Total Item Other Cost In Company Currency Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        List<WebElement> infoRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 1 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 50, 0);
        List<WebElement> valueRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 1 Row ')]");
//        List<WebElement> dateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Date 1 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1200, 0);

        for (int j = 0; j < 6 && j < productAccRowList.size(); j++) {
            enterData(productAccRowList.get(j), dataFile, "PurchaseVoucher", "purchaseAccount" + j);
            enterData(productUOMRowList.get(j), dataFile, "PurchaseVoucher", "uom" + j);
            if (j < 4) {
                enterData(productQuantityRowList.get(j), dataFile, "PurchaseVoucher", "quantity" + j);
            } else {
                common.clickElement("xpath", "//Button[@Name='Serial Nos Row " + j + "']");
                WebElement increment = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
                increment.sendKeys(Keys.TAB, common.getData(dataFile, "PurchaseVoucher", "serialText" + j) + Common.getRandomChar(), Keys.TAB, common.getData(dataFile, "PurchaseVoucher", "quantity" + j), Keys.ENTER);
                if (Boolean.parseBoolean(common.getData(dataFile, "PurchaseVoucher", "enableFreeQuantity"))) {
                    WebElement freeQ = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
                    freeQ.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, common.getData(dataFile, "PurchaseVoucher", "freeQuantity" + j), Keys.ENTER);
                }
                common.clickElement("xpath", "//Button[@Name='OK']");
            }
            enterData(freeQuantityRowList.get(j), dataFile, "PurchaseVoucher", "freeQuantity" + j);
            enterData(numOfPacksRowList.get(j), dataFile, "PurchaseVoucher", "noOfPacks" + j);
            enterData(mrpRowList.get(j), dataFile, "PurchaseVoucher", "mrpAmount" + j);
            Assert.assertEquals(mrpAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedMrpAmount" + j), "MRP Amount mismatch");
            enterData(unitRateRowList.get(j), dataFile, "PurchaseVoucher", "unitRate" + j);
            enterData(editableGrossAmountList.get(j), dataFile, "PurchaseVoucher", "editableGrossAmount" + j);
            Assert.assertEquals(grossAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedGrossAmount" + j), "Gross Amount mismatch");
            enterData(voucherDiscountList.get(j), dataFile, "PurchaseVoucher", "voucherDiscount" + j);
            Assert.assertEquals(voucherDiscountAmountList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedVoucherDiscount" + j), "Voucher discount Amount mismatch");
            enterData(discountBasis1RowList.get(j), dataFile, "PurchaseVoucher", "discount1Basis" + j);
            enterData(disount1RowList.get(j), dataFile, "PurchaseVoucher", "discount1Value" + j);
            Assert.assertEquals(disountAmount1RowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedDiscount1Value" + j), "Disc 1 amount is mismatch");

            enterData(disount2BasisRowList.get(j), dataFile, "PurchaseVoucher", "discount2Basis" + j);
            enterData(disount2RowList.get(j), dataFile, "PurchaseVoucher", "discount2Value" + j);
            Assert.assertEquals(disountAmount2RowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedDiscount2Value" + j), "Disc 2 amount is mismatch");

            enterData(disount3BasisRowList.get(j), dataFile, "PurchaseVoucher", "discount3Basis" + j);
            enterData(disount3RowList.get(j), dataFile, "PurchaseVoucher", "discount3Value" + j);
            Assert.assertEquals(disountAmount3RowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedDiscount3Value" + j), "Disc 3 amount is mismatch");

            enterData(hsnCodeRowList.get(j), dataFile, "PurchaseVoucher", "HSNCode" + j);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", Integer.parseInt(common.getData(dataFile, "PurchaseVoucher", "slideHandleSecond" + j)), 0);
            enterData(gstProductCategoryRowList.get(j), dataFile, "PurchaseVoucher", "GSTPercentage" + j);
            enterData(cessProductCategoryRowList.get(j), dataFile, "PurchaseVoucher", "CESSPercentage" + j);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 430, 0);
            Assert.assertEquals(taxableValueRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedTaxableValue" + j), "Taxable value mismatch");
            Assert.assertEquals(igstAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedIGSTAmount" + j), "IGST mismatch");
            Assert.assertEquals(cessAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedCESSAmount" + j), "CESS mismatch");
            Assert.assertEquals(gstAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedGSTAmount" + j), "GST Amount mismatch");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 700, 0);
            Assert.assertEquals(netAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedNetAmount" + j), "Net Amount mismatch");
            Assert.assertEquals(netInCompnayCurrencyAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedNetAmountInCompanyCurrency" + j), "Net Amount in Company currency mismatch");
            Assert.assertEquals(tcsTaxableAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedTCSTaxableValue" + j), "Tcs Taxable value mismatch");
            enterItemsOtherCosts(dataFile, "PurchaseVoucher", j);
            Thread.sleep(1500);
            Assert.assertEquals(totalItemOtherCostRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedItemsOtherCosts" + j), "Items other costs mismatch");
            enterData(departmentRowList.get(j), dataFile, "PurchaseVoucher", "department" + j);
            enterData(projectRowList.get(j), dataFile, "PurchaseVoucher", "project" + j);
            enterData(profitCentreRowList.get(j), dataFile, "PurchaseVoucher", "profitCentre" + j);
            enterData(costCentreRowList.get(j), dataFile, "PurchaseVoucher", "costCentre" + j);
            enterData(commentsRowList.get(j), dataFile, "PurchaseVoucher", "comments" + j);
            enterData(infoRowList.get(j), dataFile, "PurchaseVoucher", "info" + j);
            enterData(valueRowList.get(j), dataFile, "PurchaseVoucher", "value" + j);
            common.findWebElement("xpath", "//Edit[@Name='Date 1 Row " + j + ", Not sorted.']").sendKeys(Time.timeStamp());
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1400, 0);
        }

//        if (common.getData(dataFile,"PurchaseVoucher", "productType" + i).equals("general")) {
//            generalProductPurchaseVoucherNew(dataFile,"PurchaseVoucher",+i);
//        }
//        else if (common.getData(dataFile,"PurchaseVoucher", "productType" + i).equals("multiBatch")) {
//            multiBatchProductPurchaseVoucherNew(dataFile, "PurchaseVoucher",+i);
//        }
//        else if (common.getData(dataFile,"PurchaseVoucher", "productType" + i).equals("serial")) {
//            serialNumberProductInPurchaseNew(dataFile,"PurchaseVoucher",+ i);
//        }

    }

    public void addServices() throws IOException, ParseException {
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseVoucher", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Service Code Row " + v + ", Not sorted.']", dataFile, "PurchaseVoucher", "servicesCode" + v);
        }

        List<WebElement> servicesRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Purchase Account * Row')]");
        List<WebElement> quantityRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity Row ')]");
        List<WebElement> unitRateRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Rate Row ')]");
        List<WebElement> inclusiveRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Inclusive Amount * Row ')]");
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");

        List<WebElement> hsnRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> gstProdctCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
        List<WebElement> taxableValueRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Taxable Value Row ')]");

        if (!servicesGSTCheckBox) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            servicesGSTCheckBox = true;
        }
        common.sliderHandling("xpath", "//Table[@Name='Services']/*/Thumb[@Name='Position']", 650, 0);
        List<WebElement> igstAmountRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'IGST Row ')]");
        List<WebElement> cessAmountRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Row ')]");
        List<WebElement> gstAmountRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Amount Row ')]");
        List<WebElement> netAmountRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount Row ')]");
        List<WebElement> netInCompnayCurrencyAmountRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount In Company Currency Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        List<WebElement> infoRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 1 Row ')]");
        List<WebElement> valueRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 1 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Services']/*/Thumb[@Name='Position']", -1400, 0);

        for (int i = 0; i < 6; i++) {
            enterData(servicesRowList.get(i), dataFile, "PurchaseVoucher", "purchaseAccount" + i);
            enterData(quantityRowList.get(i), dataFile, "PurchaseVoucher", "ServicesQuantity" + i);
            enterData(unitRateRowList.get(i), dataFile, "PurchaseVoucher", "unitRate" + i);
            if (servicesInclusive < 3) {
                common.clickElement("xpath", "//CheckBox[@Name='Inclusive Tax Row " + i + "']");
                servicesInclusive++;
            }
            enterData(hsnRowList.get(i), dataFile, "PurchaseVoucher", "HSNCode" + i);
            enterData(gstProdctCategoryRowList.get(i), dataFile, "PurchaseVoucher", "GSTPercentage" + i);
            enterData(cessProductCategoryRowList.get(i), dataFile, "PurchaseVoucher", "CESSPercentage" + i);
            Assert.assertEquals(inclusiveRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedInclusiveAmount" + i), "Inclusive Amount mismatch");
            Assert.assertEquals(amountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedServicesAmount" + i), "Amount mismatch");
            Assert.assertEquals(taxableValueRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedServicesTaxableValue" + i), "taxable value mismatch");
            Assert.assertEquals(igstAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedServicesIGSTAmount" + i), "IGST mismatch");
            Assert.assertEquals(cessAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedServicesCESSAmount" + i), "CESS mismatch");
            Assert.assertEquals(gstAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedServicesGSTAmount" + i), "GST Amount mismatch");
            Assert.assertEquals(netAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedServicesNetAmount" + i), "Net Amount mismatch");
            Assert.assertEquals(netInCompnayCurrencyAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedServicesNetAmountInCompanyCurrency" + i), "Net Amount in Company currency mismatch");
            enterData(departmentRowList.get(i), dataFile, "PurchaseVoucher", "department" + i);
            enterData(projectRowList.get(i), dataFile, "PurchaseVoucher", "project" + i);
            enterData(profitCentreRowList.get(i), dataFile, "PurchaseVoucher", "profitCentre" + i);
            enterData(costCentreRowList.get(i), dataFile, "PurchaseVoucher", "costCentre" + i);
            enterData(commentsRowList.get(i), dataFile, "PurchaseVoucher", "comments" + i);
            enterData(infoRowList.get(i), dataFile, "PurchaseVoucher", "info" + i);
            enterData(valueRowList.get(i), dataFile, "PurchaseVoucher", "value" + i);
            common.findWebElement("xpath", "//Edit[@Name='Date 1 Row " + i + ", Not sorted.']").sendKeys(Time.timeStamp());
            common.sliderHandling("xpath", "//Table[@Name='Services']/*/Thumb[@Name='Position']", -1400, 0);
        }

    }

    public void addChargesAndDeductions() throws IOException, ParseException {
        navigateToChargesAndDeductionsTab();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseVoucher", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Charges Or Deductions * Row " + v + ", Not sorted.']", dataFile, "PurchaseVoucher", "chargesOrDeductions" + v);
        }
        List<WebElement> accCodeRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Account Code Row ')]");
        if (!IsAmountHeaderClicked) {
            common.clickElement("xpath", "//Header[@Name='Amount *']");
            IsAmountHeaderClicked = true;
        }
        List<WebElement> basisRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Basis Row ')]");
        List<WebElement> percentageRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Percentage Row ')]");
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chargesRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Row ')]");
        List<WebElement> deductionsRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Deductions Row ')]");

        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < 6; i++) {
            enterData(accCodeRowList.get(i), dataFile, "PurchaseVoucher", "chargesOrDeductionsAcc" + i);
            enterData(basisRowList.get(i), dataFile, "PurchaseVoucher", "chargesOrDeductionsBasis" + i);
            enterData(percentageRowList.get(i), dataFile, "PurchaseVoucher", "chargesOrDeductionsPercentage" + i);
            Assert.assertTrue(amountRowList.get(i).getText().equals(chargesRowList.get(i).getText()) || amountRowList.get(i).getText().equals(deductionsRowList.get(i).getText()), "Amount doesn't match Charges or Deductions for row " + i + ". Actual: " + amountRowList.get(i).getText() + ", Charges: " + chargesRowList.get(i).getText() + ", Deductions: " + deductionsRowList.get(i).getText());
            enterData(departmentRowList.get(i), dataFile, "PurchaseVoucher", "department" + i);
            enterData(projectRowList.get(i), dataFile, "PurchaseVoucher", "project" + i);
            enterData(profitCentreRowList.get(i), dataFile, "PurchaseVoucher", "profitCentre" + i);
            enterData(costCentreRowList.get(i), dataFile, "PurchaseVoucher", "costCentre" + i);
            enterData(commentsRowList.get(i), dataFile, "PurchaseVoucher", "comments" + i);
        }
    }

    public void addOtherCharges() throws IOException, ParseException {
        navigateToOtherChargesTab();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseVoucher", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Account Code Row " + v + ", Not sorted.']", dataFile, "PurchaseVoucher", "otherChargesAccount" + v);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> gstProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
        List<WebElement> taxableValueRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Taxable Value Row ')]");
        if (!otherChargesGSTCheckBox) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            otherChargesGSTCheckBox = true;
        }
        common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", 400, 0);
        List<WebElement> igstAmountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'IGST Row ')]");
        List<WebElement> cessAmountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Row ')]");
        List<WebElement> gstAmountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Amount Row ')]");
        List<WebElement> netAmountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount Row ')]");
        List<WebElement> netInCompnayCurrencyAmountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount In Company Currency Row ')]");
        List<WebElement> tcsTaxableAmountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TCS Taxable Value Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < 6; i++) {
            if (otherChargesInclusive < 3) {
                common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", -400, 0);
                common.clickElement("xpath", "//CheckBox[@Name='Inclusive Tax Row " + i + "']");
                otherChargesInclusive++;
            }
            enterData(amountRowList.get(i), dataFile, "PurchaseVoucher", "otherChargesAmount" + i);
            enterData(hsnCodeRowList.get(i), dataFile, "PurchaseVoucher", "HSNCode" + i);
            enterData(gstProductCategoryRowList.get(i), dataFile, "PurchaseVoucher", "GSTPercentage" + i);
            enterData(cessProductCategoryRowList.get(i), dataFile, "PurchaseVoucher", "CESSPercentage" + i);
            Assert.assertEquals(taxableValueRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedOtherChargesTaxableValue" + i), "taxable value mismatch");
            Assert.assertEquals(igstAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedOtherChargesIGSTAmount" + i), "IGST mismatch");
            Assert.assertEquals(cessAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedOtherChargesCESSAmount" + i), "CESS mismatch");
            Assert.assertEquals(gstAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedOtherChargesGSTAmount" + i), "GST Amount mismatch");
            Assert.assertEquals(netAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedOtherChargesNetAmount" + i), "Net Amount mismatch");
            Assert.assertEquals(netInCompnayCurrencyAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedOtherChargesNetAmountInCompanyCurrency" + i), "Net Amount in Company currency mismatch");
            Assert.assertEquals(tcsTaxableAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedOtherChargesTCSTaxableValue" + i), "Tcs Taxable value mismatch");
            enterData(departmentRowList.get(i), dataFile, "PurchaseVoucher", "department" + i);
            enterData(projectRowList.get(i), dataFile, "PurchaseVoucher", "project" + i);
            enterData(profitCentreRowList.get(i), dataFile, "PurchaseVoucher", "profitCentre" + i);
            enterData(costCentreRowList.get(i), dataFile, "PurchaseVoucher", "costCentre" + i);
            enterData(commentsRowList.get(i), dataFile, "PurchaseVoucher", "comments" + i);
            common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", -550, 0);
        }
    }

    public void addOtherDeductions() throws IOException, ParseException {
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseVoucher", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Account Code Row " + v + ", Not sorted.']", dataFile, "PurchaseVoucher", "otherDeductionsAccount" + v);
        }

        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='OtherDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='OtherDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> gstProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='OtherDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='OtherDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
        List<WebElement> taxableValueRowList = common.findWebElements("xpath", "//Table[@Name='OtherDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Taxable Value Row ')]");
        if (!otherDeductionsGSTCheckBox) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            otherDeductionsGSTCheckBox = true;
        }
        common.sliderHandling("xpath", "//Table[@Name='OtherDeductions']/*/Thumb[@Name='Position']", 400, 0);
        List<WebElement> igstAmountRowList = common.findWebElements("xpath", "//Table[@Name='OtherDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'IGST Row ')]");
        List<WebElement> cessAmountRowList = common.findWebElements("xpath", "//Table[@Name='OtherDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Row ')]");
        List<WebElement> gstAmountRowList = common.findWebElements("xpath", "//Table[@Name='OtherDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Amount Row ')]");
        List<WebElement> netAmountRowList = common.findWebElements("xpath", "//Table[@Name='OtherDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount Row ')]");
        List<WebElement> netInCompnayCurrencyAmountRowList = common.findWebElements("xpath", "//Table[@Name='OtherDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount In Company Currency Row ')]");
        List<WebElement> tcsTaxableAmountRowList = common.findWebElements("xpath", "//Table[@Name='OtherDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TCS Taxable Value Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='OtherDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='OtherDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='OtherDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < 6; i++) {
            if (otherDeductionsInclusive < 3) {
                common.sliderHandling("xpath", "//Table[@Name='OtherDeductions']/*/Thumb[@Name='Position']", -500, 0);
                common.clickElement("xpath", "//CheckBox[@Name='Inclusive Tax Row " + i + "']");
                otherDeductionsInclusive++;
            }
            enterData(amountRowList.get(i), dataFile, "PurchaseVoucher", "otherDeductionsAmount" + i);
            enterData(hsnCodeRowList.get(i), dataFile, "PurchaseVoucher", "HSNCode" + i);
            enterData(gstProductCategoryRowList.get(i), dataFile, "PurchaseVoucher", "GSTPercentage" + i);
            enterData(cessProductCategoryRowList.get(i), dataFile, "PurchaseVoucher", "CESSPercentage" + i);
            Assert.assertEquals(taxableValueRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedDeductionsTaxableValue" + i), "taxable value mismatch");
            Assert.assertEquals(igstAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedOtherDeductionsIGSTAmount" + i), "IGST mismatch");
            Assert.assertEquals(cessAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedOtherDeductionsCESSAmount" + i), "CESS mismatch");
            Assert.assertEquals(gstAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedOtherDeductionsGSTAmount" + i), "GST Amount mismatch");
            Assert.assertEquals(netAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedOtherDeductionsNetAmount" + i), "Net Amount mismatch");
            Assert.assertEquals(netInCompnayCurrencyAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedOtherDeductionsNetAmountInCompanyCurrency" + i), "Net Amount in Company currency mismatch");
            Assert.assertEquals(tcsTaxableAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedOtherDeductionsTCSTaxableValue" + i), "Tcs Taxable value mismatch");
            enterData(departmentRowList.get(i), dataFile, "PurchaseVoucher", "department" + i);
            enterData(projectRowList.get(i), dataFile, "PurchaseVoucher", "project" + i);
            enterData(profitCentreRowList.get(i), dataFile, "PurchaseVoucher", "profitCentre" + i);
            enterData(costCentreRowList.get(i), dataFile, "PurchaseVoucher", "costCentre" + i);
            enterData(commentsRowList.get(i), dataFile, "PurchaseVoucher", "comments" + i);
            common.sliderHandling("xpath", "//Table[@Name='OtherDeductions']/*/Thumb[@Name='Position']", -550, 0);
        }
    }

    public void addOtherCosts() throws IOException, ParseException {
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseVoucher", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Expense Type Code Row " + v + ", Not sorted.']", dataFile, "PurchaseVoucher", "expenseCode" + v);
        }
        List<WebElement> vendorCodeList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Vendor Code Row ')]");
        List<WebElement> currencyList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Currency Row ')]");
        List<WebElement> amountList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Other Cost * Row ')]");
        List<WebElement> otherCostsInCompnayCurrencyList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Other Cost In Company Currency Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < 6; i++) {
            enterData(vendorCodeList.get(i), dataFile, "PurchaseVoucher", "vendor");
            enterData(currencyList.get(i), dataFile, "PurchaseVoucher", "otherCostCurrency");
            enterData(amountList.get(i), dataFile, "PurchaseVoucher", "otherCostAmount" + i);
            Assert.assertEquals(otherCostsInCompnayCurrencyList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedOtherCostsInCompanyCurrency" + i), "expectedOtherCostsInCompanyCurrency mismatch");
            enterData(departmentRowList.get(i), dataFile, "PurchaseVoucher", "department" + i);
            enterData(projectRowList.get(i), dataFile, "PurchaseVoucher", "project" + i);
            enterData(profitCentreRowList.get(i), dataFile, "PurchaseVoucher", "profitCentre" + i);
            enterData(costCentreRowList.get(i), dataFile, "PurchaseVoucher", "costCentre" + i);
            enterData(commentsRowList.get(i), dataFile, "PurchaseVoucher", "comments" + i);
        }
    }

    public void addCash() throws IOException, ParseException {
        navigateToCashTab();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseVoucher", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Cash Account Code Row " + v + ", Not sorted.']", dataFile, "PurchaseVoucher", "cashAccount" + v);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> amountInCompanyCurrencyRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount In Company Currency Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < 6; i++) {
            enterData(amountRowList.get(i), dataFile, "PurchaseVoucher", "cashAmount" + i);
            Assert.assertEquals(amountInCompanyCurrencyRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedCashAmountInCompanyCurrency" + i), "expectedOtherCostsInCompanyCurrency mismatch");
            enterData(departmentRowList.get(i), dataFile, "PurchaseVoucher", "department" + i);
            enterData(projectRowList.get(i), dataFile, "PurchaseVoucher", "project" + i);
            enterData(profitCentreRowList.get(i), dataFile, "PurchaseVoucher", "profitCentre" + i);
            enterData(costCentreRowList.get(i), dataFile, "PurchaseVoucher", "costCentre" + i);
            enterData(commentsRowList.get(i), dataFile, "PurchaseVoucher", "comments" + i);
        }
    }

    public void addCheques() throws IOException, ParseException {
        List<WebElement> element=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        element.get(0).click();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseVoucher", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Bank Account Code Row " + v + ", Not sorted.']", dataFile, "PurchaseVoucher", "bankAccountCode" + v);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chargesAccRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Account Code Row ')]");
        List<WebElement> chargesAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Row ')]");
        List<WebElement> netAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount Row ')]");
        List<WebElement> netAmountINCompanyCurrencyRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount In Company Currency Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < 6; i++) {
            enterData(amountRowList.get(i), dataFile, "PurchaseVoucher", "chequeAmount" + i);
            common.findWebElement("xpath", "//Edit[@Name='Cheque/EFT No * Row " + i + ", Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
            enterData(chargesAccRowList.get(i), dataFile, "PurchaseVoucher", "chargesAcc" + i);
            enterData(chargesAmountRowList.get(i), dataFile, "PurchaseVoucher", "chargesAmount" + i);
            Assert.assertEquals(netAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedChequeNetAmount" + i), "expectedChequeAmount mismatch");
            Assert.assertEquals(netAmountINCompanyCurrencyRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedChequeAmountInCompanyCurrency" + i), "expectedChequeAmountInCompanyCurrency mismatch");
            enterData(departmentRowList.get(i), dataFile, "PurchaseVoucher", "department" + i);
            enterData(projectRowList.get(i), dataFile, "PurchaseVoucher", "project" + i);
            enterData(profitCentreRowList.get(i), dataFile, "PurchaseVoucher", "profitCentre" + i);
            enterData(costCentreRowList.get(i), dataFile, "PurchaseVoucher", "costCentre" + i);
            enterData(commentsRowList.get(i), dataFile, "PurchaseVoucher", "comments" + i);
        }

    }

    public void addPostDatedCheques() throws IOException, ParseException {
        List<WebElement> elementsss=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        elementsss.get(1).click();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseVoucher", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Bank Account Code Row " + v + ", Not sorted.']", dataFile, "PurchaseVoucher", "bankAccountCode" + v);
        }
        List<WebElement> pdcAccRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'PDC Account * Row ')]");
        List<WebElement> amoutRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> amoutInCompanyCurrencyRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount In Company Currency Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < 6; i++) {
            enterData(pdcAccRowList.get(i), dataFile, "PurchaseVoucher", "pdcAcc");
            enterData(amoutRowList.get(i), dataFile, "PurchaseVoucher", "chequeAmount" + i);
            Assert.assertEquals(amoutInCompanyCurrencyRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedPostDatedChequeAmount" + i), "expectedChequeAmountInCompanyCurrency mismatch");
            common.findWebElement("xpath", "//Edit[@Name='Cheque/EFT No * Row " + i + ", Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
            enterData(departmentRowList.get(i), dataFile, "PurchaseVoucher", "department" + i);
            enterData(projectRowList.get(i), dataFile, "PurchaseVoucher", "project" + i);
            enterData(profitCentreRowList.get(i), dataFile, "PurchaseVoucher", "profitCentre" + i);
            enterData(costCentreRowList.get(i), dataFile, "PurchaseVoucher", "costCentre" + i);
            enterData(commentsRowList.get(i), dataFile, "PurchaseVoucher", "comments" + i);
        }

    }

    public void addChequesPDC() throws IOException, ParseException {
        List<WebElement> elem=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        elem.get(2).click();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseVoucher", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Bank Account Code Row " + v + ", Not sorted.']", dataFile, "PurchaseVoucher", "bankAccountCode" + v);

        }
        List<WebElement> chequeAmountRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> amountInCompnayCurrencyRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount In Company Currency Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < 6; i++) {
            enterData(chequeAmountRowList.get(i), dataFile, "PurchaseVoucher", "chequeAmount" + i);
            Assert.assertEquals(amountInCompnayCurrencyRowList.get(i).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedPostDatedChequeAmount" + i), "expectedChequeAmountInCompanyCurrency mismatch");
            common.findWebElement("xpath", "//Edit[@Name='Cheque/EFT No * Row " + i + ", Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
            enterData(departmentRowList.get(i), dataFile, "PurchaseVoucher", "department" + i);
            enterData(projectRowList.get(i), dataFile, "PurchaseVoucher", "project" + i);
            enterData(profitCentreRowList.get(i), dataFile, "PurchaseVoucher", "profitCentre" + i);
            enterData(costCentreRowList.get(i), dataFile, "PurchaseVoucher", "costCentre" + i);
            enterData(commentsRowList.get(i), dataFile, "PurchaseVoucher", "comments" + i);
        }
    }

    public void addTermsAndConditions() throws IOException, ParseException {
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseVoucher", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Term Type * Row " + v + ", Not sorted.']", dataFile, "PurchaseVoucher", "termType" + v);
        }
        List<WebElement> termRowList = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Term * Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < 6; i++) {
            enterData(termRowList.get(i), dataFile, "PurchaseVoucher", "term" + i);
            enterData(commentsRowList.get(i), dataFile, "PurchaseVoucher", "comments" + i);
        }
    }

    public void addAllocations() throws IOException, ParseException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Allocations')]");
        enterInput("xpath", "//Edit[@Name='Department']", dataFile, "PurchaseVoucher", "department0");
        enterInput("xpath", "//Edit[@Name='Project']", dataFile, "PurchaseVoucher", "project0");
        enterInput("xpath", "//Edit[@Name='Profit Centre']", dataFile, "PurchaseVoucher", "profitCentre0");
        enterInput("xpath", "//Edit[@Name='Cost Centre']", dataFile, "PurchaseVoucher", "costCentre0");
    }

}
