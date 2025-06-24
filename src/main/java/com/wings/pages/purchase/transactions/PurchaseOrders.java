package com.wings.pages.purchase.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PurchaseOrders extends TransactionsBaseClass {
    WindowsDriver driver,rootDriver;
    Common common;
    String dataFile;
    boolean gstAmountClicked=false;
    boolean discountIsClicked=false;
    boolean IsAmountHeaderClicked = false;
    boolean otherChargesGSTCheckBox = false;
    int otherChargesInclusive = 0;

    public PurchaseOrders(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String  purchaseOrders() throws InterruptedException, IOException, ParseException, AWTException
    {
        long start1 = System.nanoTime();
        navigateToMastersWhen3Steps("Purchase","Orders","Purchase Orders");
        Thread.sleep(1000);
        String oldVoucherID = oldTTransactionID();
        enterVoucherType(dataFile,"PurchaseOrders","voucherType");
        enterDate();
        enterBranchName(dataFile,"PurchaseOrders","branch");
        enterLocation(dataFile,"PurchaseOrders","location");
        enterCurrency(dataFile,"PurchaseOrders","currency");
        enterPartyCode(dataFile,"PurchaseOrders","partyCode");
        enterCreditPeriod(dataFile,"PurchaseOrders","creditPeriod");
        enterPriceList(dataFile,"PurchaseOrders","priceList");
        enterExecutive(dataFile,"PurchaseOrders","executive");
        enterRemarks(dataFile,"PurchaseOrders","remarks");
        long duration1 = System.nanoTime() - start1;
        FileUtil.writeTimeLog("General information", duration1 /1000000000);

        long start = System.nanoTime();
        addProduct();
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Enter Products include Assertions", duration /1000000000);

        long start3 = System.nanoTime();
        navigateToChargesAndDeductionsTab();
        addChargesAndDeductions();
        long duration3 = System.nanoTime() - start3;
        FileUtil.writeTimeLog("Enter Charges And Deductions include Assertions", duration3 /1000000000);

        long start4 = System.nanoTime();
        navigateToOtherChargesTab();
        addOtherCharges();
        long duration4 = System.nanoTime() - start4;
        FileUtil.writeTimeLog("Enter Other Charges include Assertions", duration4 /1000000000);

        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();

        long start7 = System.nanoTime();
        navigateToCashTab();
        addCash();
        long duration7 = System.nanoTime() - start7;
        FileUtil.writeTimeLog("Enter Cash include Assertions", duration7 /1000000000);

        long start8 = System.nanoTime();
        List<WebElement> element=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        element.get(0).click();
        addCheques();
        long duration8 = System.nanoTime() - start8;
        FileUtil.writeTimeLog("Enter Cheque include Assertions", duration8 /1000000000);

        long start9 = System.nanoTime();
        List<WebElement> elementsss=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        elementsss.get(1).click();
        addPostDatedCheques();
        long duration9 = System.nanoTime() - start9;
        FileUtil.writeTimeLog("Enter Post Dated Cheques include Assertions", duration9 /1000000000);

        long start10 = System.nanoTime();
        List<WebElement> elem=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        elem.get(2).click();
        addChequesPDC();
        long duration10 = System.nanoTime() - start10;
        FileUtil.writeTimeLog("Enter Cheque PDC include Assertions", duration10 /1000000000);

        long start13 = System.nanoTime();
        enterOtherInfo(dataFile,"PurchaseOrders");
        long duration13 = System.nanoTime() - start13;
        FileUtil.writeTimeLog("Enter Other Info", duration13 /1000000000);

        long start14 = System.nanoTime();
        enterAdditionalInfo(dataFile,"PurchaseOrders");
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

        assertSummaryFields(summary,"Quantity",common.getData(dataFile,"PurchaseVoucher","expectedQuantity"));
        assertSummaryFields(summary,"Gross Amount",common.getData(dataFile,"PurchaseVoucher","expectedGrossAmount"));
        assertSummaryFields(summary,"Discount",common.getData(dataFile,"PurchaseVoucher","expectedDiscount"));
        assertSummaryFields(summary,"Gross - Disc",common.getData(dataFile,"PurchaseVoucher","expectedGrossMinusDiscount"));
        assertSummaryFields(summary,"IGST",common.getData(dataFile,"PurchaseVoucher","expectedIGST"));
        assertSummaryFields(summary,"CESS",common.getData(dataFile,"PurchaseVoucher","expectedCESS"));
        assertSummaryFields(summary,"Net Amount",common.getData(dataFile,"PurchaseVoucher","expectedNetAmount"));
        assertSummaryFields(summary,"Charges",common.getData(dataFile,"PurchaseVoucher","expectedChargesAmount"));
        assertSummaryFields(summary,"Deductions",common.getData(dataFile,"PurchaseVoucher","expectedDeductionsAmount"));
        assertSummaryFields(summary,"Other Charges",common.getData(dataFile,"PurchaseVoucher","expectedOtherChargesAmount"));
        assertSummaryFields(summary,"Other Charges IGST",common.getData(dataFile,"PurchaseVoucher","expectedOtherChargesIGST"));
        assertSummaryFields(summary,"Other Charges CESS",common.getData(dataFile,"PurchaseVoucher","expectedOtherChargesCESS"));
        assertSummaryFields(summary,"Total Value",common.getData(dataFile,"PurchaseVoucher","expectedTotalValueAmount"));
        assertSummaryFields(summary,"Total Value In Company Currency",common.getData(dataFile,"PurchaseVoucher","expectedTotalValueInCompanyCurrency"));
        assertSummaryFields(summary,"Cash",common.getData(dataFile,"PurchaseVoucher","expectedCashAmount"));
        assertSummaryFields(summary,"Cheques",common.getData(dataFile,"PurchaseVoucher","expectedChequeAmount"));
        assertSummaryFields(summary,"Post Dated Cheques",common.getData(dataFile,"PurchaseVoucher","expectedPostDatedChequesAmount"));
        assertSummaryFields(summary,"Cheques [PDC]",common.getData(dataFile,"PurchaseVoucher","expectedChequesPDCAmount"));
        assertSummaryFields(summary,"Payments Value",common.getData(dataFile,"PurchaseVoucher","expectedPaymentValue"));

        long durationSum = System.nanoTime() - startSum;
        FileUtil.writeTimeLog("Purchase Orders Summary",durationSum/1000000000);

        transactionSave();
        String newVoucherID = newTransactionID(oldVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
        String prefix = newVoucherID.replaceAll("\\d", "");String number = newVoucherID.replaceAll("\\D", "");
        Thread.sleep(2000);
        navigateToMastersWhen3Steps("Tools","Automated Testing","Generate Input File");
        rootDriver=common.initializeDriver("Root");
        Thread.sleep(3000);
        common.findWebElement("xpath", "//Window[@Name='Export Transaction Postings']/Pane/Edit[@Name='Voucher Series']").sendKeys(prefix);
        common.findWebElement("xpath","//Window[@Name='Export Transaction Postings']/Pane/Edit[@Name='Voucher Number']").sendKeys(number);
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        if (common.findWebElement("xpath","//Text").getText().equals("Data Exported successfully!")) {
            common.clickElement("xpath", "//Button[@Name='OK']");
        }
        else if(common.findWebElement("xpath","//Text").getText().equals("Transactionno doesnot exist.")){
            Assert.fail("Transaction does not exists");
            common.clickElement("xpath", "//Button[@Name='OK']");
        }

        Thread.sleep(2000);
        navigateToMastersWhen3Steps("Tools","Automated Testing","Generate Output File");
        rootDriver=common.initializeDriver("Root");
        Thread.sleep(3000);
        common.findWebElement("xpath", "//Window[@Name='Export Transaction Postings']/Pane/Edit[@Name='Voucher Series']").sendKeys(prefix);
        common.findWebElement("xpath","//Window[@Name='Export Transaction Postings']/Pane/Edit[@Name='Voucher Number']").sendKeys(number);
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        if (common.findWebElement("xpath","//Text").getText().equals("Data Exported successfully!")) {
            common.clickElement("xpath", "//Button[@Name='OK']");
        }
        else if(common.findWebElement("xpath","//Text").getText().equals("Transactionno doesnot exist.")){
            Assert.fail("Transaction does not exists");
            common.clickElement("xpath", "//Button[@Name='OK']");
        }
        return newVoucherID;
    }

    public void addProduct() throws InterruptedException, IOException, ParseException, AWTException {
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseOrders", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Product Code Row " + v + ", Not sorted.']", dataFile, "PurchaseOrders", "productCode" + v);
        }
        List<WebElement> productUOMRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row')]");
        List<WebElement> productQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity * Row')]");
        List<WebElement> mrpRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'MRP Row ')]");
        List<WebElement> mrpAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'MRP Amount Row ')]");
        List<WebElement> unitRateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Unit Rate Row ')]");
        List<WebElement> editableGrossAmountList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Editable Gross Amount Row ')]");
        List<WebElement> grossAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Gross Amount Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 430, 0);
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
        List<WebElement> disount2RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 2 Row ')]");
        List<WebElement> disountAmount2RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Amount 2 Row ')]");
        List<WebElement> disount3BasisRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 3 Row ')]");
        List<WebElement> disount3RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 3 Row ')]");
        List<WebElement> disountAmount3RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Amount 3 Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        List<WebElement> gstProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
        List<WebElement> taxableValueRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Taxable Value Row ')]");
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        List<WebElement> igstAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'IGST Row ')]");
        List<WebElement> cessAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Row ')]");
        List<WebElement> gstAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Amount Row ')]");
        List<WebElement> netAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount Row ')]");
        List<WebElement> netInCompnayCurrencyAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount In Company Currency Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 70, 0);
        List<WebElement> infoRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 1 Row ')]");
        List<WebElement> valueRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 1 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1200, 0);

        for (int j = 0; j < 6 && j < productUOMRowList.size(); j++) {
            enterData(productUOMRowList.get(j), dataFile, "PurchaseOrders", "uom" + j);
            enterData(productQuantityRowList.get(j), dataFile, "PurchaseOrders", "quantity" + j);

            enterData(mrpRowList.get(j), dataFile, "PurchaseOrders", "mrpAmount" + j);
            Assert.assertEquals(mrpAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseOrders", "expectedMrpAmount" + j), "MRP Amount mismatch");
            enterData(unitRateRowList.get(j), dataFile, "PurchaseOrders", "unitRate" + j);
            enterData(editableGrossAmountList.get(j), dataFile, "PurchaseOrders", "editableGrossAmount" + j);
            Assert.assertEquals(grossAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseOrders", "expectedGrossAmount" + j), "Gross Amount mismatch");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 430, 0);

            enterData(discountBasis1RowList.get(j), dataFile, "PurchaseOrders", "discount1Basis" + j);
            enterData(disount1RowList.get(j), dataFile, "PurchaseOrders", "discount1Value" + j);
            Assert.assertEquals(disountAmount1RowList.get(j).getText(), common.getData(dataFile, "PurchaseOrders", "expectedDiscount1Value" + j), "Disc 1 amount is mismatch");

            enterData(disount2BasisRowList.get(j), dataFile, "PurchaseOrders", "discount2Basis" + j);
            enterData(disount2RowList.get(j), dataFile, "PurchaseOrders", "discount2Value" + j);
            Assert.assertEquals(disountAmount2RowList.get(j).getText(), common.getData(dataFile, "PurchaseOrders", "expectedDiscount2Value" + j), "Disc 2 amount is mismatch");

            enterData(disount3BasisRowList.get(j), dataFile, "PurchaseOrders", "discount3Basis" + j);
            enterData(disount3RowList.get(j), dataFile, "PurchaseOrders", "discount3Value" + j);
            Assert.assertEquals(disountAmount3RowList.get(j).getText(), common.getData(dataFile, "PurchaseOrders", "expectedDiscount3Value" + j), "Disc 3 amount is mismatch");

            enterData(hsnCodeRowList.get(j), dataFile, "PurchaseOrders", "HSNCode" + j);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
            enterData(gstProductCategoryRowList.get(j), dataFile, "PurchaseOrders", "GSTPercentage" + j);
            enterData(cessProductCategoryRowList.get(j), dataFile, "PurchaseOrders", "CESSPercentage" + j);
            Assert.assertEquals(taxableValueRowList.get(j).getText(), common.getData(dataFile, "PurchaseOrders", "expectedTaxableValue" + j), "Taxable value mismatch");
            Assert.assertEquals(igstAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseOrders", "expectedIGSTAmount" + j), "IGST mismatch");
            Assert.assertEquals(cessAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseOrders", "expectedCESSAmount" + j), "CESS mismatch");
            Assert.assertEquals(gstAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseOrders", "expectedGSTAmount" + j), "GST Amount mismatch");
            Assert.assertEquals(netAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseOrders", "expectedNetAmount" + j), "Net Amount mismatch");
            Assert.assertEquals(netInCompnayCurrencyAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseOrders", "expectedNetAmountInCompanyCurrency" + j), "Net Amount in Company currency mismatch");
            enterData(departmentRowList.get(j), dataFile, "PurchaseOrders", "department" + j);
            enterData(projectRowList.get(j), dataFile, "PurchaseOrders", "project" + j);
            enterData(profitCentreRowList.get(j), dataFile, "PurchaseOrders", "profitCentre" + j);
            enterData(costCentreRowList.get(j), dataFile, "PurchaseOrders", "costCentre" + j);
            enterData(commentsRowList.get(j), dataFile, "PurchaseOrders", "comments" + j);
            enterData(infoRowList.get(j), dataFile, "PurchaseOrders", "info" + j);
            enterData(valueRowList.get(j), dataFile, "PurchaseOrders", "value" + j);
            common.findWebElement("xpath", "//Edit[@Name='Date 1 Row " + j + ", Not sorted.']").sendKeys(Time.timeStamp());
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1400, 0);
        }

    }

    public void addChargesAndDeductions() throws IOException, ParseException {
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseOrders", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Charges Or Deductions * Row " + v + ", Not sorted.']", dataFile, "PurchaseOrders", "chargesOrDeductions" + v);
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
            enterData(accCodeRowList.get(i), dataFile, "PurchaseOrders", "chargesOrDeductionsAcc" + i);
            enterData(basisRowList.get(i), dataFile, "PurchaseOrders", "chargesOrDeductionsBasis" + i);
            enterData(percentageRowList.get(i), dataFile, "PurchaseOrders", "chargesOrDeductionsPercentage" + i);
            Assert.assertTrue(amountRowList.get(i).getText().equals(chargesRowList.get(i).getText()) || amountRowList.get(i).getText().equals(deductionsRowList.get(i).getText()), "Amount doesn't match Charges or Deductions for row " + i + ". Actual: " + amountRowList.get(i).getText() + ", Charges: " + chargesRowList.get(i).getText() + ", Deductions: " + deductionsRowList.get(i).getText());
            enterData(departmentRowList.get(i), dataFile, "PurchaseOrders", "department" + i);
            enterData(projectRowList.get(i), dataFile, "PurchaseOrders", "project" + i);
            enterData(profitCentreRowList.get(i), dataFile, "PurchaseOrders", "profitCentre" + i);
            enterData(costCentreRowList.get(i), dataFile, "PurchaseOrders", "costCentre" + i);
            enterData(commentsRowList.get(i), dataFile, "PurchaseOrders", "comments" + i);
        }
    }

    public void addOtherCharges() throws IOException, ParseException {
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseOrders", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Account Code Row " + v + ", Not sorted.']", dataFile, "PurchaseOrders", "otherChargesAccount" + v);
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
            enterData(amountRowList.get(i), dataFile, "PurchaseOrders", "otherChargesAmount" + i);
            enterData(hsnCodeRowList.get(i), dataFile, "PurchaseOrders", "HSNCode" + i);
            enterData(gstProductCategoryRowList.get(i), dataFile, "PurchaseOrders", "GSTPercentage" + i);
            enterData(cessProductCategoryRowList.get(i), dataFile, "PurchaseOrders", "CESSPercentage" + i);
            Assert.assertEquals(taxableValueRowList.get(i).getText(), common.getData(dataFile, "PurchaseOrders", "expectedOtherChargesTaxableValue" + i), "taxable value mismatch");
            Assert.assertEquals(igstAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseOrders", "expectedOtherChargesIGSTAmount" + i), "IGST mismatch");
            Assert.assertEquals(cessAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseOrders", "expectedOtherChargesCESSAmount" + i), "CESS mismatch");
            Assert.assertEquals(gstAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseOrders", "expectedOtherChargesGSTAmount" + i), "GST Amount mismatch");
            Assert.assertEquals(netAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseOrders", "expectedOtherChargesNetAmount" + i), "Net Amount mismatch");
            Assert.assertEquals(netInCompnayCurrencyAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseOrders", "expectedOtherChargesNetAmountInCompanyCurrency" + i), "Net Amount in Company currency mismatch");
            enterData(departmentRowList.get(i), dataFile, "PurchaseOrders", "department" + i);
            enterData(projectRowList.get(i), dataFile, "PurchaseOrders", "project" + i);
            enterData(profitCentreRowList.get(i), dataFile, "PurchaseOrders", "profitCentre" + i);
            enterData(costCentreRowList.get(i), dataFile, "PurchaseOrders", "costCentre" + i);
            enterData(commentsRowList.get(i), dataFile, "PurchaseOrders", "comments" + i);
            common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", -550, 0);
        }
    }

    public void addCash() throws IOException, ParseException {
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseOrders", "productCount")); v++) {
            enterInput("xpath", "//Edit[@Name='Cash Account Code Row " + v + ", Not sorted.']", dataFile, "PurchaseOrders", "cashAccount" + v);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> amountInCompanyCurrencyRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount In Company Currency Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < 6; i++) {
            enterData(amountRowList.get(i), dataFile, "PurchaseOrders", "cashAmount" + i);
            Assert.assertEquals(amountInCompanyCurrencyRowList.get(i).getText(), common.getData(dataFile, "PurchaseOrders", "expectedCashAmountInCompanyCurrency" + i), "expectedOtherCostsInCompanyCurrency mismatch");
            enterData(departmentRowList.get(i), dataFile, "PurchaseOrders", "department" + i);
            enterData(projectRowList.get(i), dataFile, "PurchaseOrders", "project" + i);
            enterData(profitCentreRowList.get(i), dataFile, "PurchaseOrders", "profitCentre" + i);
            enterData(costCentreRowList.get(i), dataFile, "PurchaseOrders", "costCentre" + i);
            enterData(commentsRowList.get(i), dataFile, "PurchaseOrders", "comments" + i);
        }
    }

    public void addCheques() throws IOException, ParseException {
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseOrders", "productCount")); v++) {
            enterInput("xpath", "//Edit[@Name='Bank Account Code Row " + v + ", Not sorted.']", dataFile, "PurchaseOrders", "bankAccountCode" + v);
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
            enterData(amountRowList.get(i), dataFile, "PurchaseOrders", "chequeAmount" + i);
            common.findWebElement("xpath", "//Edit[@Name='Cheque/EFT No * Row " + i + ", Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
            enterData(chargesAccRowList.get(i), dataFile, "PurchaseOrders", "chargesAcc" + i);
            enterData(chargesAmountRowList.get(i), dataFile, "PurchaseOrders", "chargesAmount" + i);
            Assert.assertEquals(netAmountRowList.get(i).getText(), common.getData(dataFile, "PurchaseOrders", "expectedChequeNetAmount" + i), "expectedChequeAmount mismatch");
            Assert.assertEquals(netAmountINCompanyCurrencyRowList.get(i).getText(), common.getData(dataFile, "PurchaseOrders", "expectedChequeAmountInCompanyCurrency" + i), "expectedChequeAmountInCompanyCurrency mismatch");
            enterData(departmentRowList.get(i), dataFile, "PurchaseOrders", "department" + i);
            enterData(projectRowList.get(i), dataFile, "PurchaseOrders", "project" + i);
            enterData(profitCentreRowList.get(i), dataFile, "PurchaseOrders", "profitCentre" + i);
            enterData(costCentreRowList.get(i), dataFile, "PurchaseOrders", "costCentre" + i);
            enterData(commentsRowList.get(i), dataFile, "PurchaseOrders", "comments" + i);
        }

    }

    public void addPostDatedCheques() throws IOException, ParseException {
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseOrders", "productCount")); v++) {
            enterInput("xpath", "//Edit[@Name='Bank Account Code Row " + v + ", Not sorted.']", dataFile, "PurchaseOrders", "bankAccountCode" + v);
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
            enterData(pdcAccRowList.get(i), dataFile, "PurchaseOrders", "pdcAcc");
            enterData(amoutRowList.get(i), dataFile, "PurchaseOrders", "chequeAmount" + i);
            Assert.assertEquals(amoutInCompanyCurrencyRowList.get(i).getText(), common.getData(dataFile, "PurchaseOrders", "expectedPostDatedChequeAmount" + i), "expectedChequeAmountInCompanyCurrency mismatch");
            common.findWebElement("xpath", "//Edit[@Name='Cheque/EFT No * Row " + i + ", Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
            enterData(departmentRowList.get(i), dataFile, "PurchaseOrders", "department" + i);
            enterData(projectRowList.get(i), dataFile, "PurchaseOrders", "project" + i);
            enterData(profitCentreRowList.get(i), dataFile, "PurchaseOrders", "profitCentre" + i);
            enterData(costCentreRowList.get(i), dataFile, "PurchaseOrders", "costCentre" + i);
            enterData(commentsRowList.get(i), dataFile, "PurchaseOrders", "comments" + i);
        }

    }

    public void addChequesPDC() throws IOException, ParseException {
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseOrders", "productCount")); v++) {
            enterInput("xpath", "//Edit[@Name='Bank Account Code Row " + v + ", Not sorted.']", dataFile, "PurchaseOrders", "bankAccountCode" + v);

        }
        List<WebElement> chequeAmountRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> amountInCompnayCurrencyRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount In Company Currency Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < 6; i++) {
            enterData(chequeAmountRowList.get(i), dataFile, "PurchaseOrders", "chequeAmount" + i);
            Assert.assertEquals(amountInCompnayCurrencyRowList.get(i).getText(), common.getData(dataFile, "PurchaseOrders", "expectedPostDatedChequeAmount" + i), "expectedChequeAmountInCompanyCurrency mismatch");
            common.findWebElement("xpath", "//Edit[@Name='Cheque/EFT No * Row " + i + ", Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
            enterData(departmentRowList.get(i), dataFile, "PurchaseOrders", "department" + i);
            enterData(projectRowList.get(i), dataFile, "PurchaseOrders", "project" + i);
            enterData(profitCentreRowList.get(i), dataFile, "PurchaseOrders", "profitCentre" + i);
            enterData(costCentreRowList.get(i), dataFile, "PurchaseOrders", "costCentre" + i);
            enterData(commentsRowList.get(i), dataFile, "PurchaseOrders", "comments" + i);
        }
    }

    public void addTermsAndConditions() throws IOException, ParseException {
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "PurchaseOrders", "productCount")); v++) {
            enterInput("xpath", "//Edit[@Name='Term Type * Row " + v + ", Not sorted.']", dataFile, "PurchaseOrders", "termType" + v);
        }
        List<WebElement> termRowList = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Term * Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < 6; i++) {
            enterData(termRowList.get(i), dataFile, "PurchaseOrders", "term" + i);
            enterData(commentsRowList.get(i), dataFile, "PurchaseOrders", "comments" + i);
        }
    }

    public void addAllocations() throws IOException, ParseException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Allocations')]");
        enterInput("xpath", "//Edit[@Name='Department']", dataFile, "PurchaseOrders", "department0");
        enterInput("xpath", "//Edit[@Name='Project']", dataFile, "PurchaseOrders", "project0");
        enterInput("xpath", "//Edit[@Name='Profit Centre']", dataFile, "PurchaseOrders", "profitCentre0");
        enterInput("xpath", "//Edit[@Name='Cost Centre']", dataFile, "PurchaseOrders", "costCentre0");
    }

}
