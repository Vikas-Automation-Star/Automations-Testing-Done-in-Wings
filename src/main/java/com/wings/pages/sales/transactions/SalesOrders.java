package com.wings.pages.sales.transactions;

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

public class SalesOrders extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;
    int otherChargesInclusive=0;
    boolean IsAmountHeaderClicked=false;
    boolean otherChargesGSTCheckBox=false;
    boolean discountIsClicked=false;

    public SalesOrders(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String salesOrders() throws InterruptedException, IOException, ParseException, AWTException {
        long salesOrderStart = System.nanoTime();
        System.out.println("Sales Order started executed in :" + salesOrderStart);

        long generalInfoStart = System.nanoTime();
        System.out.println("Sales Order general Info started executed in :" + generalInfoStart);
        navigateToSalesOrderMenu();
        Thread.sleep(5000);

        String oldVoucherID = oldTTransactionID();
        System.out.println("oldID: " + oldVoucherID);
        enterDate();
        enterBranchName(dataFile, "salesOrder", "branch");
        enterLocation(dataFile, "salesOrder", "location");
        enterCurrency(dataFile, "salesOrder", "currency");
        enterCashOrParty(dataFile, "salesOrder", "partyCode");
        Thread.sleep(1500);
        gstTransactionType("Inter State Sales to Registered Dealers");
        Thread.sleep(1000);
        enterCustomerEmail(dataFile, "salesOrder", "email");
        enterCustomerMobileNum(dataFile, "salesOrder", "mobileNum");
        enterCreditPeriod(dataFile,"salesOrder","creditPeriod");
        enterPriceList(dataFile, "salesOrder", "priceList");
        enterExecutive(dataFile, "salesOrder", "executive");
        common.clickElement("xpath","//CheckBox[@Name='Advance Receipts']");
        enterRemarks(dataFile, "salesOrder", "remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("General information End:- ", generalInfoEndTime);
        //F3-Items
        long addProductStart=System.nanoTime();
        addProductSalesOrder();
        long addProductEnd=System.nanoTime()-addProductStart;
        FileUtil.writeTimeLogInMinutes("Sales Order Add Products:- ",addProductEnd);
        //charges and deductions
        long chargesDeductionsStart =System.nanoTime();
        addChargesAndDeductionsSalesOrder();
        long chargesDeductionsEnd=System.nanoTime()- chargesDeductionsStart;
        FileUtil.writeTimeLogInMinutes("Sales Order Charges and Deductions:- ",chargesDeductionsEnd);
        //other charges
        long otherChargesStart =System.nanoTime();
        addOtherChargesSalesOrder();
        long otherChargesEnd=System.nanoTime()- otherChargesStart;
        FileUtil.writeTimeLogInMinutes("Sales Order Other Charges:- ",otherChargesEnd);
        //validate gst
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        //cash
        long cashTabStart =System.nanoTime();
        addCashSalesOrder();
        long cashTabEnd =System.nanoTime()- cashTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Order Cash Tab:- ", cashTabEnd);
        //cheques
        long chequesTabStart =System.nanoTime();
        addChequesSalesOrder();
        long chequesTabEnd =System.nanoTime()- chequesTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Order Cheques Tab:- ", chequesTabEnd);
        //post dated cheques
        long postDatedChequesTabStart =System.nanoTime();
        addPostDatedChequesSalesOrder();
        long postDatedChequesTabEnd =System.nanoTime()- postDatedChequesTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Order Post Dated Cheques Tab:- ", postDatedChequesTabEnd);
        //cheques[pdc]
        long chequesPDCTabStart =System.nanoTime();
        addChequesPDCSalesOrder();
        long chequesPDCTabEnd =System.nanoTime()- chequesPDCTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Order Cheques[PDC] Tab:- ", chequesPDCTabEnd);
        //credit card
        long creditCardTabStart =System.nanoTime();
        addCreditCardSalesOrder();
        long creditCardTabEnd =System.nanoTime()- creditCardTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Order Credit Card Tab:- ", creditCardTabEnd);
        //other Info
        long otherInfoTabStart =System.nanoTime();
        enterOtherInfo(dataFile,"salesOrder");
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Order Other Info Tab:- ", otherInfoTabEnd);
        //additional Info
        long additionalInfoTabStart =System.nanoTime();
        enterAdditionalInfo(dataFile,"salesOrder");
        long additionalInfoTabEnd =System.nanoTime()- additionalInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Order Additional Info Tab:- ", additionalInfoTabEnd);
        //scroll
        scrollRight(8);
        //terms and Cond
        long termsConditionsTabStart =System.nanoTime();
        addTermsAndConditions();
        long termsConditionsTabEnd =System.nanoTime()- termsConditionsTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Order Terms and Conditions Tab:- ", termsConditionsTabEnd);
        //allocations
        long allocationsTabStart=System.nanoTime();
        addAllocations();
        long allocationsTabEnd=System.nanoTime() - allocationsTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Order Allocations Tab:- ", allocationsTabEnd);
        //summary
        long summaryStart=System.nanoTime();
        common.clickElement("xpath","//TabItem[@Name='  Shift-F8 Summary  ']");
        java.util.List<WebElement> summary=common.findWebElements("xpath","//Pane[@Name='']//Edit/*");
        System.out.println("Summary size: "+ summary.size());
        //validate summary
        assertSummaryFields(summary,"Quantity *", common.getData(dataFile,"salesOrder","expectedQuantityInSummary"));
        assertSummaryFields(summary,"Quantity In SKU *", common.getData(dataFile,"salesOrder","expectedQuantityInSKUSummary"));
        assertSummaryFields(summary,"Gross Amount", common.getData(dataFile,"salesOrder","expectedGrossAmount"));
        assertSummaryFields(summary,"Discount1", common.getData(dataFile,"salesOrder","expectedDiscount1"));
        assertSummaryFields(summary,"Discount2", common.getData(dataFile,"salesOrder","expectedDiscount2"));
        assertSummaryFields(summary,"Discount3", common.getData(dataFile,"salesOrder","expectedDiscount3"));
        assertSummaryFields(summary,"Discount", common.getData(dataFile,"salesOrder","expectedDiscount"));
        assertSummaryFields(summary,"Gross - Disc", common.getData(dataFile,"salesOrder","expectedGrossMinusDiscount"));
        assertSummaryFields(summary,"IGST", common.getData(dataFile,"salesOrder","expectedIGST"));
        assertSummaryFields(summary,"CESS", common.getData(dataFile,"salesOrder","expectedCESS"));
        assertSummaryFields(summary,"Net Amount", common.getData(dataFile,"salesOrder","expectedNetAmount"));
        assertSummaryFields(summary,"Charges", common.getData(dataFile,"salesOrder","expectedCharges"));
        assertSummaryFields(summary,"Deductions", common.getData(dataFile,"salesOrder","expectedDeductions"));
        assertSummaryFields(summary,"Other Charges", common.getData(dataFile,"salesOrder","expectedOtherCharges"));
        assertSummaryFields(summary,"Other Charges IGST", common.getData(dataFile,"salesOrder","expectedOtherChargesIGST"));
        assertSummaryFields(summary,"Other Charges CESS", common.getData(dataFile,"salesOrder","expectedOtherChargesCESS"));
        assertSummaryFields(summary,"Total Value", common.getData(dataFile,"salesOrder","expectedTotalValue"));
        assertSummaryFields(summary,"Cash", common.getData(dataFile,"salesOrder","expectedCash"));
        assertSummaryFields(summary,"Cheques", common.getData(dataFile,"salesOrder","expectedCheques"));
        assertSummaryFields(summary,"Post Dated Cheques", common.getData(dataFile,"salesOrder","expectedPostDatedCheques"));
        assertSummaryFields(summary,"Cheques [PDC]", common.getData(dataFile,"salesOrder","expectedChequesPDC"));
        assertSummaryFields(summary,"Credit Card", common.getData(dataFile,"salesOrder","expectedCreditCard"));
        assertSummaryFields(summary,"Payment Received", common.getData(dataFile,"salesOrder","expectedReceivableAmount"));
        long summaryEnd=System.nanoTime()-summaryStart;
        FileUtil.writeTimeLogInMinutes("Sales Order Summary End:- ", summaryEnd);

        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");

        //end
        long salesOrderEnd = System.nanoTime() - salesOrderStart;
        FileUtil.writeTimeLogInMinutes("Sales Order ended at:- ", salesOrderEnd );

        return newVoucherID;
    }
    //save

//        Thread.sleep(1000);
//        common.clickElement("name", "Sales");
//        common.clickElement("name", "Orders");
//        common.clickElement("name", "Sales Book");
//        Thread.sleep(1000);
//        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
//        Thread.sleep(1500);
//        verifyReport(newVoucherID,dataFile,"salesOrder");
//
//        long duration = System.nanoTime() - start;
//        FileUtil.writeTimeLogInMinutes("Sales Order", duration);
//
//    }
    public void addProductSalesOrder() throws InterruptedException, IOException, ParseException, AWTException {
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesOrder", "productCount")); v++) {
            enterData("xpath","//Edit[@Name='Product Code Row "+v+", Not sorted.']",dataFile,"salesOrder","productCode" + v);
        }
        java.util.List<WebElement> productUOMRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row')]");
        java.util.List<WebElement> productQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity * Row ')]");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        java.util.List<WebElement> mrpRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'MRP Row ')]");
        java.util.List<WebElement> mrpAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'MRP Amount Row ')]");
        java.util.List<WebElement> minimumRateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Minimum Rate * Row ')]");
        java.util.List<WebElement> maximumRateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Maximum Rate * Row ')]");
        java.util.List<WebElement> unitRateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Unit Rate Row ')]");
        java.util.List<WebElement> grossAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Gross Amount Row ')]");
        java.util.List<WebElement> voucherDiscountList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Voucher Disc % Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        java.util.List<WebElement> voucherDiscountAmountList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Voucher Disc Row ')]");
        java.util.List<WebElement> partyDiscountList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Party Disc % Row ')]");
        java.util.List<WebElement> partyDiscountAmountList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Party Disc Row ')]");
        if (!discountIsClicked) {
            common.clickElement("xpath", "//Header[@Name='Disc Amount 1']");
            common.clickElement("xpath", "//Header[@Name='Disc Amount 2']");
            common.clickElement("xpath", "//Header[@Name='Disc Amount 3']");
            discountIsClicked = true;
        }
        java.util.List<WebElement> discountBasis1RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 1 Row ')]");
        java.util.List<WebElement> disount1RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 1 Row ')]");
        java.util.List<WebElement> disountAmount1RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Amount 1 Row ')]");
        java.util.List<WebElement> disount2BasisRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 2 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 430, 0);
        java.util.List<WebElement> disount2RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 2 Row ')]");
        java.util.List<WebElement> disountAmount2RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Amount 2 Row ')]");
        java.util.List<WebElement> disount3BasisRowList= common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 3 Row ')]");
        java.util.List<WebElement> disount3RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 3 Row ')]");
        java.util.List<WebElement> disountAmount3RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Amount 3 Row ')]");
        java.util.List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        java.util.List<WebElement> taxableValueRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Taxable Value Row ')]");
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",350, 0);
        java.util.List<WebElement> igstAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'IGST Row ')]");
        java.util.List<WebElement> cessAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Row ')]");
        java.util.List<WebElement> gstAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Amount Row ')]");
        java.util.List<WebElement> netAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount Row ')]");
        java.util.List<WebElement> netInCompnayCurrencyAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount In Company Currency Row ')]");
        java.util.List<WebElement> tcsTaxableAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TCS Taxable Value Row ')]");
        java.util.List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        java.util.List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        java.util.List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        java.util.List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        java.util.List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        java.util.List<WebElement> infoRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 1 Row ')]");
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 50, 0);
        java.util.List<WebElement> valueRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 1 Row ')]");
        java.util.List<WebElement> dateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Date 1 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1600, 0);
        for (int j = 0; j < 6 && j <productUOMRowList.size() ; j++) {
            enterData(productUOMRowList.get(j),dataFile,"salesOrder","uom"+j);
            enterData(productQuantityRowList.get(j),dataFile,"salesOrder","quantity"+j);

            enterData(mrpRowList.get(j),dataFile,"salesOrder","mrp"+j);
//            Assert.assertEquals(mrpAmountRowList.get(j).getText(), common.getData(dataFile, "salesOrder", "mrpAmount" + j), "MRP Amount mismatch");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",430, 0);
            enterData(minimumRateRowList.get(j),dataFile,"salesOrder","minRate"+j);
            enterData(maximumRateRowList.get(j),dataFile,"salesOrder","maxRate"+j);
            enterData(unitRateRowList.get(j),dataFile,"salesOrder","unitRate"+j);
//            Assert.assertEquals(grossAmountRowList.get(j).getText(), common.getData(dataFile, "salesOrder", "grossAmount" + j), "Gross Amount mismatch");
            enterData(voucherDiscountList.get(j),dataFile,"salesOrder","voucherDiscount"+j);
//            Assert.assertEquals(voucherDiscountAmountList.get(j).getText(), common.getData(dataFile, "salesOrder", "voucherDiscAmount" + j), "Voucher discount Amount mismatch");
            enterData(partyDiscountList.get(j),dataFile,"salesOrder","partyDiscount"+j);
//            Assert.assertEquals(partyDiscountAmountList.get(j).getText(), common.getData(dataFile, "salesOrder", "partyDiscAmount" + j), "Party discount Amount mismatch");
            enterData(discountBasis1RowList.get(j),dataFile,"salesOrder","Discount1B"+j);
            enterData(disount1RowList.get(j),dataFile,"salesOrder","disc1Row"+j);
//            Assert.assertEquals(disountAmount1RowList.get(j).getText(), common.getData(dataFile, "salesOrder", "Disc1Amount" + j), "Disc 1 amount is mismatch");
            Thread.sleep(1000);
            enterData(disount2BasisRowList.get(j),dataFile,"salesOrder","Discount2B"+j);
            enterData(disount2RowList.get(j),dataFile,"salesOrder","disc2Row"+j);
//            Assert.assertEquals(disountAmount2RowList.get(j).getText(), common.getData(dataFile, "salesOrder", "Disc2Amount" + j), "Disc 2 amount is mismatch");
            enterData(disount3BasisRowList.get(j),dataFile,"salesOrder","Discount3B"+j);
            enterData(disount3RowList.get(j),dataFile,"salesOrder","disc3Row"+j);
//            Assert.assertEquals(disountAmount3RowList.get(j).getText(), common.getData(dataFile, "salesOrder", "Disc3Amount" + j), "Disc 3 amount is mismatch");
            enterData(hsnCodeRowList.get(j),dataFile,"salesOrder","HSNCode"+j);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",530, 0);
//            Assert.assertEquals(taxableValueRowList.get(j).getText(), common.getData(dataFile, "salesOrder", "taxableValue" + j), "Taxable value mismatch");
//            Assert.assertEquals(igstAmountRowList.get(j).getText(), common.getData(dataFile, "salesOrder", "igstAmount" + j), "IGST mismatch");
//            Assert.assertEquals(cessAmountRowList.get(j).getText(), common.getData(dataFile, "salesOrder", "cessAmount" + j), "CESS mismatch");
//            Assert.assertEquals(gstAmountRowList.get(j).getText(), common.getData(dataFile, "salesOrder", "expectedGStExclusive" + j), "GST Amount mismatch");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 700, 0);
//            Assert.assertEquals(netAmountRowList.get(j).getText(), common.getData(dataFile, "salesOrder", "netAmount" + j), "Net Amount mismatch");
//            Assert.assertEquals(netInCompnayCurrencyAmountRowList.get(j).getText(), common.getData(dataFile, "salesOrder", "netAmountcompanyCurrency" + j), "Net Amount in Company currency mismatch");
            enterData(departmentRowList.get(j),dataFile,"salesOrder","department"+j);
            enterData(projectRowList.get(j),dataFile,"salesOrder","project"+j);
            enterData(profitCentreRowList.get(j),dataFile,"salesOrder","profitCentre"+j);
            enterData(costCentreRowList.get(j),dataFile,"salesOrder","costCentre"+j);
            enterData(commentsRowList.get(j),dataFile,"salesOrder","comments"+j);
            enterData(infoRowList.get(j),dataFile,"salesOrder","info"+j);
            enterData(valueRowList.get(j),dataFile,"salesOrder","value"+j);
            common.findWebElement("xpath", "//Edit[@Name='Date 1 Row " + j + ", Not sorted.']").sendKeys(Time.timeStamp());
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1400, 0);
        }
    }

    public  void addChargesAndDeductionsSalesOrder() throws IOException, ParseException {
        navigateToChargesAndDeductionsTab();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesOrder", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Charges Or Deductions * Row "+v+", Not sorted.']", dataFile,"salesOrder", "charges"+v);
        }
        java.util.List<WebElement> accCodeRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Account Code Row ')]");
        if(!IsAmountHeaderClicked){
            common.clickElement("xpath","//Header[@Name='Amount *']");
            IsAmountHeaderClicked=true;
        }
        java.util.List<WebElement> basisRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Basis Row ')]");
        java.util.List<WebElement> percentageRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Percentage Row ')]");
        java.util.List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        java.util.List<WebElement> chargesRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Row ')]");
        java.util.List<WebElement> deductionsRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Deductions Row ')]");
        java.util.List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        java.util.List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        java.util.List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        java.util.List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        java.util.List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < 6; i++) {
            enterData(accCodeRowList.get(i),dataFile,"salesOrder","chargesAccount"+i);
            enterData(basisRowList.get(i),dataFile,"salesOrder","chargesBasis"+i);
            enterData(percentageRowList.get(i),dataFile,"salesOrder","chargesPercentage"+i);
//            Assert.assertTrue(amountRowList.get(i).getText().equals(chargesRowList.get(i).getText())|| amountRowList.get(i).getText().equals(deductionsRowList.get(i).getText()),"Amount doesn't match Charges or Deductions for row " +i+". Actual: " + amountRowList.get(i).getText() + ", Charges: " + chargesRowList.get(i).getText() + ", Deductions: " + deductionsRowList.get(i).getText());
            enterData(departmentRowList.get(i),dataFile,"salesOrder","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesOrder","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesOrder","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesOrder","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesOrder","comments"+i);
        }
    }
    public void addOtherChargesSalesOrder() throws IOException, ParseException, InterruptedException {
        navigateToOtherChargesTab();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesOrder", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Account Code Row "+v+", Not sorted.']", dataFile,"salesOrder", "otherChargesAccount"+v);
        }
        java.util.List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        java.util.List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        java.util.List<WebElement> taxableValueRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Taxable Value Row ')]");
        if (!otherChargesGSTCheckBox) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            otherChargesGSTCheckBox = true;
        }
        common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", 400, 0);
        java.util.List<WebElement> igstAmountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'IGST Row ')]");
        java.util.List<WebElement> cessAmountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Row ')]");
        java.util.List<WebElement> gstAmountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Amount Row ')]");
        java.util.List<WebElement> netAmountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount Row ')]");
        java.util.List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        java.util.List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        java.util.List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        java.util.List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        java.util.List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < 6; i++) {
            if (otherChargesInclusive < 3) {
                common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", -400, 0);
                common.clickElement("xpath", "//CheckBox[@Name='Inclusive Tax Row " + i + "']");
                otherChargesInclusive++;
            }
            enterData(amountRowList.get(i),dataFile,"salesOrder","otherChargesAmount"+i);
            enterData(hsnCodeRowList.get(i),dataFile,"salesOrder","HSNCode"+i);
            Thread.sleep(1000);
//            Assert.assertEquals(taxableValueRowList.get(i).getText(), common.getData(dataFile, "salesOrder","expectedOtherChargesTaxableValue" + i),"taxable value mismatch");
//            Assert.assertEquals(igstAmountRowList.get(i).getText(), common.getData(dataFile, "salesOrder","expectedOtherChargesIGSTAmount" + i),"IGST mismatch");
//            Assert.assertEquals(cessAmountRowList.get(i).getText(), common.getData(dataFile,"salesOrder", "expectedOtherChargesCESSAmount" +i),"CESS mismatch");
//            Assert.assertEquals(gstAmountRowList.get(i).getText(), common.getData(dataFile,"salesOrder", "expectedOtherChargesGSTAmount" +i), "GST Amount mismatch");
//            Assert.assertEquals(netAmountRowList.get(i).getText(), common.getData(dataFile, "salesOrder","expectedOtherChargesNetAmount" +i), "Net Amount mismatch");
            enterData(departmentRowList.get(i),dataFile,"salesOrder","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesOrder","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesOrder","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesOrder","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesOrder","comments"+i);
            common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", -550, 0);
        }
    }
    public void addCashSalesOrder() throws IOException, ParseException {
        navigateToCashTab();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesOrder", "productCount")); v++) {
            enterInput("xpath","//Edit[@Name='Cash Account Code Row "+v+", Not sorted.']",dataFile,"salesOrder","cashAccount"+v);
        }
        java.util.List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        java.util.List<WebElement> amountRowInCompanyCurrencyList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount In Company Currency Row ')]");
        java.util.List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        java.util.List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        java.util.List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        java.util.List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        java.util.List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <6 ; i++) {
            enterData(amountRowList.get(i), dataFile, "salesOrder", "cashAmount"+i);
            Assert.assertEquals(amountRowInCompanyCurrencyList.get(i).getText(), common.getData(dataFile, "salesOrder","expectedAmountInCompanyCurrency" +i), "Amount In Company Currency mismatch");
            enterData(departmentRowList.get(i),dataFile,"salesOrder","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesOrder","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesOrder","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesOrder","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesOrder","comments"+i);
        }
    }
    public void addChequesSalesOrder() throws IOException, ParseException {
        java.util.List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(0).getText());
        elements.get(0).click();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesOrder", "productCount")); v++) {
            enterInput("xpath","//Edit[@Name='Bank Account Code Row "+v+", Not sorted.']",dataFile,"salesOrder","bankAccount"+v);
        }
        java.util.List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        java.util.List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        java.util.List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        java.util.List<WebElement> chargesAccRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Account Code Row ')]");
        java.util.List<WebElement> chargesAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Row ')]");
        java.util.List<WebElement> netAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount Row ')]");
        java.util.List<WebElement> netAmountINCompanyCurrencyRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount In Company Currency Row ')]");
        java.util.List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        java.util.List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        java.util.List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        java.util.List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        java.util.List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <6 ; i++) {
            enterData(amountRowList.get(i), dataFile, "salesOrder", "chequeAmount" + i);
            common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row "+i+", Not sorted.']").sendKeys(String.valueOf(common.getRandom()),Keys.TAB,Time.timeStamp());
//            common.clickElement("xpath","//Edit[@Name='Cheque Date * Row "+i+", Not sorted.']");
//            common.findWebElement("xpath","//Edit[@Name='Cheque Date * Row "+i+", Not sorted.']").sendKeys(Time.timeStamp());
            enterData(drawnOnRowList.get(i), dataFile, "salesOrder", "drawnOn"+i);
            enterData(drawnOnBranchBranchRowList.get(i), dataFile, "salesOrder", "drawnOnBranch"+i);
            enterData(chargesAccRowList.get(i), dataFile, "salesOrder", "onlyCharges" + i);
            enterData(chargesAmountRowList.get(i), dataFile, "salesOrder", "chargesAmount" + i);
//            Assert.assertEquals(netAmountRowList.get(i).getText(), common.getData(dataFile, "salesOrder","expectedChequeNetAmount"+i),"expectedChequeAmount mismatch");
//            Assert.assertEquals(netAmountINCompanyCurrencyRowList.get(i).getText(), common.getData(dataFile, "salesOrder","expectedChequeAmountInCompanyCurrency"+i),"expectedChequeAmountInCompanyCurrency mismatch");
            enterData(departmentRowList.get(i),dataFile,"salesOrder","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesOrder","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesOrder","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesOrder","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesOrder","comments"+i);
        }

    }
    public void addPostDatedChequesSalesOrder() throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Post Dated Cheques')]");
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesOrder", "productCount")); v++) {
            enterInput("xpath","//Edit[@Name='PDC Account Code Row "+v+", Not sorted.']",dataFile,"salesOrder","pdcAccount"+v);
        }
        java.util.List<WebElement> amoutRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        java.util.List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        java.util.List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        java.util.List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        java.util.List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        java.util.List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        java.util.List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        java.util.List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <6 ; i++) {
            enterData(amoutRowList.get(i), dataFile, "salesOrder", "chequeAmount" + i);
            common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row "+i+", Not sorted.']").sendKeys(String.valueOf(common.getRandom()),Keys.TAB,Time.timeStamp());
//            common.clickElement("xpath","//Edit[@Name='Cheque Date * Row "+i+", Not sorted.']");
//            common.findWebElement("xpath","//Edit[@Name='Cheque Date * Row "+i+", Not sorted.']").sendKeys(Time.timeStamp());
            enterData(drawnOnRowList.get(i), dataFile, "salesOrder", "drawnOn"+i);
            enterData(drawnOnBranchBranchRowList.get(i), dataFile, "salesOrder", "drawnOnBranch"+i);
            enterData(departmentRowList.get(i),dataFile,"salesOrder","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesOrder","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesOrder","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesOrder","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesOrder","comments"+i);
        }
    }
    public void addChequesPDCSalesOrder() throws IOException, ParseException {
        java.util.List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(2).getText());
        elements.get(2).click();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesOrder", "productCount")); v++) {
            enterInput("xpath","//Edit[@Name='Bank Account Code Row "+v+", Not sorted.']",dataFile,"salesOrder","bankAccount"+v);

        }
        java.util.List<WebElement> chequeAmountRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        java.util.List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        java.util.List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        java.util.List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        java.util.List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        java.util.List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        java.util.List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        java.util.List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i <6 ; i++) {
            enterData(chequeAmountRowList.get(i), dataFile, "salesOrder", "chequeAmount"+i);
            common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row "+i+", Not sorted.']").sendKeys(String.valueOf(common.getRandom()),Keys.TAB,Time.timeStamp());
//            common.clickElement("xpath","//Edit[@Name='Cheque Date * Row "+i+", Not sorted.']");
//            common.findWebElement("xpath","//Edit[@Name='Cheque Date * Row "+i+", Not sorted.']").sendKeys(Time.timeStamp());
            enterData(drawnOnRowList.get(i), dataFile, "salesOrder", "drawnOn"+i);
            enterData(drawnOnBranchBranchRowList.get(i), dataFile, "salesOrder", "drawnOnBranch"+i);
            enterData(departmentRowList.get(i),dataFile,"salesOrder","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesOrder","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesOrder","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesOrder","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesOrder","comments"+i);
        }
    }
    public void addCreditCardSalesOrder() throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Credit Card')]");
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesOrder", "productCount")); v++) {
            enterInput("xpath","//Edit[@Name='Swipe Machine Type * Row "+v+", Not sorted.']",dataFile,"salesOrder","swipeMachineType"+v);

        }
        java.util.List<WebElement> swipeTypeRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Swipe Type * Row ')]");
        java.util.List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        java.util.List<WebElement> executiveRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        java.util.List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        java.util.List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        java.util.List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        java.util.List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        java.util.List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <6 ; i++) {
            enterData(swipeTypeRowList.get(i), dataFile, "salesOrder", "swipeType"+i);
            enterData(amountRowList.get(i), dataFile, "salesOrder", "creditCardAmount"+i);
            common.findWebElement("xpath","//Edit[@Name='Card No Row "+i+", Not sorted.']").sendKeys("852741"+common.getRandom(),Keys.TAB,Time.timeStamp(),Keys.TAB,"75241"+common.getRandom());
//            common.clickElement("xpath","//Edit[@Name='Approval No * Row "+i+", Not sorted.']");
//            common.findWebElement("xpath","//Edit[@Name='Approval No * Row "+i+", Not sorted.']").sendKeys("75241"+common.getRandom());
            enterData(executiveRowList.get(i),dataFile,"salesOrder","executive"+i);
            enterData(departmentRowList.get(i),dataFile,"salesOrder","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesOrder","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesOrder","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesOrder","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesOrder","comments"+i);
        }
    }
    public void addTermsAndConditions() throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Terms And Conditions')]");
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesOrder", "productCount")); v++) {
            enterInput("xpath","//Edit[@Name='Term Type * Row "+v+", Not sorted.']",dataFile,"salesOrder","termType"+v);
        }
        java.util.List<WebElement> termRowList = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Term * Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < 6; i++) {
            enterData(termRowList.get(i), dataFile, "salesOrder", "term"+i);
            enterData(commentsRowList.get(i), dataFile, "salesOrder", "comments"+i);
        }
    }
    public void addAllocations() throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Allocations')]");
        enterInput("xpath","//Edit[@Name='Department']",dataFile,"salesOrder","department0");
        enterInput("xpath","//Edit[@Name='Project']",dataFile,"salesOrder","project0");
        enterInput("xpath","//Edit[@Name='Profit Centre']",dataFile,"salesOrder","profitCentre0");
        enterInput("xpath","//Edit[@Name='Cost Centre']",dataFile,"salesOrder","costCentre0");
    }
}