package com.wings.pages.purchase.transactions;


import com.wings.pages.Transaction;
import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class MaterialReceiptsAgainstOrder extends TransactionsBaseClass {
    WindowsDriver driver,rootDriver;
    Common common;
    String dataFile;

    int otherChargesInclusive = 0;
    boolean IsAmountHeaderClicked = false;
    boolean otherChargesGSTCheckBox = false;
    boolean gstAmountClicked = false;
    boolean discountIsClicked = false;

    public MaterialReceiptsAgainstOrder(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String materialReceiptsAgainstOrder(String voucherNum) throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Purchase","Receipts","Material Receipts against Orders");
        Thread.sleep(3000);
        String oldVoucherID = oldTTransactionID();
        enterVoucherType(dataFile,"MaterialReceiptsAgainstOrders","voucherType");
        enterDate();
        enterBranchName(dataFile,"MaterialReceiptsAgainstOrders","branch");
        enterLocation(dataFile,"MaterialReceiptsAgainstOrders","location");
        enterCurrency(dataFile,"MaterialReceiptsAgainstOrders","currency");
        enterPartyCode(dataFile,"MaterialReceiptsAgainstOrders","partyCode");
        Thread.sleep(1000);
        selectPendingsSalesOrder(voucherNum,common.getData(dataFile,"MaterialReceiptsAgainstOrders","FYear"));
        Thread.sleep(4000);
        common.clickElement("xpath","//Button[@Name='OK']");
        enterCreditPeriod(dataFile,"MaterialReceiptsAgainstOrders","creditPeriod");
        enterBatchPolicy(dataFile,"MaterialReceiptsAgainstOrders","batchPolicy");
        enterPriceList(dataFile,"MaterialReceiptsAgainstOrders","priceList");
        enterExecutive(dataFile,"MaterialReceiptsAgainstOrders","executive");
        enterRemarks(dataFile,"MaterialReceiptsAgainstOrders","remarks");

        long start = System.nanoTime();
        enterPendings();
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Enter Pendings in MRAO include Assertions", duration /1000000000);

        long start3 = System.nanoTime();
        addChargesAndDeductions();
        long duration3 = System.nanoTime() - start3;
        FileUtil.writeTimeLog("Enter Charges And Deductions include Assertions", duration3 /1000000000);

        long start4 = System.nanoTime();
        addOtherCharges();
        long duration4 = System.nanoTime() - start4;
        FileUtil.writeTimeLog("Enter Other Charges include Assertions", duration4 /1000000000);

        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();

        long start6 = System.nanoTime();
        addOtherCosts();
        long duration6 = System.nanoTime() - start6;
        FileUtil.writeTimeLog("Enter Other Costs include Assertions", duration6 /1000000000);

        long start13 = System.nanoTime();
        enterOtherInfo(dataFile,"MaterialReceiptsAgainstOrders");
        long duration13 = System.nanoTime() - start13;
        FileUtil.writeTimeLog("Enter Other Info", duration13 /1000000000);

        long start14 = System.nanoTime();
        enterAdditionalInfo(dataFile,"MaterialReceiptsAgainstOrders");
        long duration14 = System.nanoTime() - start14;
        FileUtil.writeTimeLog("Enter Additional Information", duration14 /1000000000);

        long start11 = System.nanoTime();
        addTermsAndConditions();
        long duration11 = System.nanoTime() - start11;
        FileUtil.writeTimeLog("Enter Terms And Conditions", duration11 /1000000000);

        navigateToSummaryTab();
        List<WebElement> summary = common.findWebElements("xpath", "//Pane[@Name='Summary']//Edit/*");
        System.out.println("Size " + summary.size());
        assertSummaryFields(summary,"Quantity",common.getData(dataFile,"MaterialReceiptsAgainstOrders","expectedQuantity"));
        assertSummaryFields(summary,"Quantity In SKU",common.getData(dataFile,"MaterialReceiptsAgainstOrders","expectedQuantityInSKU"));
        assertSummaryFields(summary,"Free Quantity",common.getData(dataFile,"MaterialReceiptsAgainstOrders","expectedFreeQuantity"));
        assertSummaryFields(summary,"Free Quantity In SKU",common.getData(dataFile,"MaterialReceiptsAgainstOrders","expectedFreeQuantityInSKU"));
        assertSummaryFields(summary,"Gross Amount",common.getData(dataFile,"MaterialReceiptsAgainstOrders","expectedGrossAmount"));
        assertSummaryFields(summary,"Discount",common.getData(dataFile,"MaterialReceiptsAgainstOrders","expectedDiscount"));
        assertSummaryFields(summary,"Gross - Disc",common.getData(dataFile,"MaterialReceiptsAgainstOrders","expectedGrossMinusDiscount"));
        assertSummaryFields(summary,"IGST",common.getData(dataFile,"MaterialReceiptsAgainstOrders","expectedIGST"));
        assertSummaryFields(summary,"CESS",common.getData(dataFile,"MaterialReceiptsAgainstOrders","expectedCESS"));
        assertSummaryFields(summary,"Net Amount",common.getData(dataFile,"MaterialReceiptsAgainstOrders","expectedNetAmount"));
        assertSummaryFields(summary,"Charges",common.getData(dataFile,"MaterialReceiptsAgainstOrders","expectedChargesAmount"));
        assertSummaryFields(summary,"Deductions",common.getData(dataFile,"MaterialReceiptsAgainstOrders","expectedDeductionsAmount"));
        assertSummaryFields(summary,"Other Charges",common.getData(dataFile,"MaterialReceiptsAgainstOrders","expectedOtherChargesAmount"));
        assertSummaryFields(summary,"Other Charges IGST",common.getData(dataFile,"MaterialReceiptsAgainstOrders","expectedOtherChargesIGST"));
        assertSummaryFields(summary,"Other Charges CESS",common.getData(dataFile,"MaterialReceiptsAgainstOrders","expectedOtherChargesCESS"));
        assertSummaryFields(summary,"Other Cost Amount",common.getData(dataFile,"MaterialReceiptsAgainstOrders","expectedOtherCostAmount"));
        assertSummaryFields(summary,"Total Value",common.getData(dataFile,"MaterialReceiptsAgainstOrders","expectedTotalValueAmount"));
        assertSummaryFields(summary,"Total Value In Company Currency",common.getData(dataFile,"MaterialReceiptsAgainstOrders","expectedTotalValueInCompanyCurrency"));


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
//        Thread.sleep(2000);
//        if (common.findWebElement("xpath","//Text").getText().equals("Data Exported successfully!")) {
//            common.clickElement("xpath", "//Button[@Name='OK']");
//        }
//        else if(common.findWebElement("xpath","//Text").getText().equals("Transactionno doesnot exist.")){
//            Assert.fail("Transaction does not exists");
//            common.clickElement("xpath", "//Button[@Name='OK']");
//        }
//
//        Thread.sleep(3000);
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

    public void enterPendings() throws IOException, ParseException, InterruptedException {
        List<WebElement> productUOMRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row')]");
        List<WebElement> productQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 625, 0);
        List<WebElement> freeQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Free Quantity Row')]");
        List<WebElement> mrpRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'MRP Row ')]");
        List<WebElement> mrpAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'MRP Amount Row ')]");
        List<WebElement> unitRateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Unit Rate Row ')]");
        List<WebElement> grossAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Gross Amount Row ')]");

        if (!discountIsClicked) {
            common.clickElement("xpath", "//Header[@Name='Disc Amount 1']");
            common.clickElement("xpath", "//Header[@Name='Disc Amount 2']");
            common.clickElement("xpath", "//Header[@Name='Disct Amount 3']");
            discountIsClicked = true;
        }

        List<WebElement> discountBasis1RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 1 Row ')]");
        List<WebElement> disount1RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 1 Row ')]");
        List<WebElement> disountAmount1RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Amount 1 Row ')]");
        List<WebElement> disount2BasisRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 2 Row ')]");
        List<WebElement> disount2RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 2 Row ')]");
        List<WebElement> disountAmount2RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Amount 2 Row ')]");
        List<WebElement> disount3BasisRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 550, 0);
        List<WebElement> disount3RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 3 Row ')]");
        List<WebElement> disountAmount3RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disct Amount 3 Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
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
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 250, 0);
        List<WebElement> netInCompnayCurrencyAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount In Company Currency Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        List<WebElement> infoRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 1 Row ')]");
        List<WebElement> valueRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 1 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1200, 0);


        for (int j = 0; j < 6 && j < productUOMRowList.size(); j++) {
            enterData(productUOMRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "uom" + j);
            if (j < 4) {
                enterData(productQuantityRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "quantity" + j);
            } else {
                common.clickElement("xpath", "//Button[@Name='Serial Nos Row " + j + "']");
                WebElement increment = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
                increment.sendKeys(Keys.TAB, common.getData(dataFile, "MaterialReceiptsAgainstOrders", "serialText" +j) + Common.getRandomChar(), Keys.TAB, common.getData(dataFile, "MaterialReceiptsAgainstOrders", "quantity" + j), Keys.ENTER);
                if (Boolean.parseBoolean(common.getData(dataFile, "MaterialReceiptsAgainstOrders", "enableFreeQuantity"))) {
                    WebElement freeQ = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
                    freeQ.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, common.getData(dataFile, "MaterialReceiptsAgainstOrders", "freeQuantity" + j), Keys.ENTER);
                }
                common.clickElement("xpath", "//Button[@Name='OK']");
            }
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 550, 0);
            enterData(freeQuantityRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "freeQuantity" + j);
            enterData(mrpRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "mrpAmount" + j);
            Assert.assertEquals(mrpAmountRowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedMrpAmount" + j), "MRP Amount mismatch");
            enterData(unitRateRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "unitRate" + j);
            Assert.assertEquals(grossAmountRowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedGrossAmount" + j), "Gross Amount mismatch");
            enterData(discountBasis1RowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "discount1Basis" + j);
            enterData(disount1RowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "discount1Value" + j);
            Assert.assertEquals(disountAmount1RowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedDiscount1Value" + j), "Disc 1 amount is mismatch");

            enterData(disount2BasisRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "discount2Basis" + j);
            enterData(disount2RowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "discount2Value" + j);
            Assert.assertEquals(disountAmount2RowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedDiscount2Value" + j), "Disc 2 amount is mismatch");

            enterData(disount3BasisRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "discount3Basis" + j);
            enterData(disount3RowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "discount3Value" + j);
            Assert.assertEquals(disountAmount3RowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedDiscount3Value" + j), "Disc 3 amount is mismatch");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", Integer.parseInt(common.getData(dataFile, "MaterialReceiptsAgainstOrders", "slideHandleFirst" + j)), 0);
            enterData(hsnCodeRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "HSNCode" + j);
            enterData(gstProductCategoryRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "GSTPercentage" + j);
            enterData(cessProductCategoryRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "CESSPercentage" + j);
            Assert.assertEquals(taxableValueRowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedTaxableValue" + j), "Taxable value mismatch");
            Assert.assertEquals(igstAmountRowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedIGSTAmount" + j), "IGST mismatch");
            Thread.sleep(2000);
            Assert.assertEquals(cessAmountRowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedCESSAmount" + j), "CESS mismatch");
            Assert.assertEquals(gstAmountRowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedGSTAmount" + j), "GST Amount mismatch");
            Assert.assertEquals(netAmountRowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedNetAmount" + j), "Net Amount mismatch");
            Assert.assertEquals(netInCompnayCurrencyAmountRowList.get(j).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedNetAmountInCompanyCurrency" + j), "Net Amount in Company currency mismatch");
            enterData(departmentRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "department" + j);
            enterData(projectRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "project" + j);
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
            enterData(profitCentreRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "profitCentre" + j);
            enterData(costCentreRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "costCentre" + j);
            enterData(commentsRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "comments" + j);
            enterData(infoRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "info" + j);
            enterData(valueRowList.get(j), dataFile, "MaterialReceiptsAgainstOrders", "value" + j);
            common.findWebElement("xpath", "//Edit[@Name='Date 1 Row " + j + ", Not sorted.']").sendKeys(Time.timeStamp());
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1400, 0);
        }

    }

    public void addChargesAndDeductions() throws IOException, ParseException {
        navigateToChargesAndDeductionsTab();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "MaterialReceiptsAgainstOrders", "productCount")); v++) {
                enterData("xpath", "//Edit[@Name='Charges Or Deductions * Row " + v + ", Not sorted.']", dataFile, "MaterialReceiptsAgainstOrders", "chargesOrDeductions" + v);
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
                enterData(accCodeRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "chargesOrDeductionsAcc" + i);
                enterData(basisRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "chargesOrDeductionsBasis" + i);
                enterData(percentageRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "chargesOrDeductionsPercentage" + i);
                Assert.assertTrue(amountRowList.get(i).getText().equals(chargesRowList.get(i).getText()) || amountRowList.get(i).getText().equals(deductionsRowList.get(i).getText()), "Amount doesn't match Charges or Deductions for row " + i + ". Actual: " + amountRowList.get(i).getText() + ", Charges: " + chargesRowList.get(i).getText() + ", Deductions: " + deductionsRowList.get(i).getText());
                enterData(departmentRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "department" + i);
                enterData(projectRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "project" + i);
                enterData(profitCentreRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "profitCentre" + i);
                enterData(costCentreRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "costCentre" + i);
                enterData(commentsRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "comments" + i);
            }
        }

    public void addOtherCharges() throws IOException, ParseException {
        navigateToOtherChargesTab();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "MaterialReceiptsAgainstOrders", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Account Code Row " + v + ", Not sorted.']", dataFile, "MaterialReceiptsAgainstOrders", "otherChargesAccount" + v);
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
            enterData(amountRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "otherChargesAmount" + i);
            enterData(hsnCodeRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "HSNCode" + i);
            enterData(gstProductCategoryRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "GSTPercentage" + i);
            enterData(cessProductCategoryRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "CESSPercentage" + i);
            Assert.assertEquals(taxableValueRowList.get(i).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedOtherChargesTaxableValue" + i), "taxable value mismatch");
            Assert.assertEquals(igstAmountRowList.get(i).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedOtherChargesIGSTAmount" + i), "IGST mismatch");
            Assert.assertEquals(cessAmountRowList.get(i).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedOtherChargesCESSAmount" + i), "CESS mismatch");
            Assert.assertEquals(gstAmountRowList.get(i).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedOtherChargesGSTAmount" + i), "GST Amount mismatch");
            Assert.assertEquals(netAmountRowList.get(i).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedOtherChargesNetAmount" + i), "Net Amount mismatch");
            Assert.assertEquals(netInCompnayCurrencyAmountRowList.get(i).getText(), common.getData(dataFile, "MaterialReceiptsAgainstOrders", "expectedOtherChargesNetAmountInCompanyCurrency" + i), "Net Amount in Company currency mismatch");
            enterData(departmentRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "department" + i);
            common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", 80, 0);
            enterData(projectRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "project" + i);
            enterData(profitCentreRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "profitCentre" + i);
            enterData(costCentreRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "costCentre" + i);
            enterData(commentsRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "comments" + i);
            common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", -550, 0);
        }
    }

    public void addOtherCosts() throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Other Costs ')]");
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "MaterialReceiptsAgainstOrders", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Expense Type Code Row " + v + ", Not sorted.']", dataFile, "MaterialReceiptsAgainstOrders", "expenseCode" + v);
        }
        List<WebElement> vendorCodeList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Vendor Code Row ')]");
        List<WebElement> amountList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Other Cost * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < 6; i++) {
            enterData(vendorCodeList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "vendor");
            enterData(amountList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "otherCostAmount" + i);
            enterData(departmentRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "department" + i);
            enterData(projectRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "project" + i);
            enterData(profitCentreRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "profitCentre" + i);
            enterData(costCentreRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "costCentre" + i);
            enterData(commentsRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "comments" + i);
        }
    }

    public void addTermsAndConditions() throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Terms And Conditions')]");
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile, "MaterialReceiptsAgainstOrders", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Term Type * Row " + v + ", Not sorted.']", dataFile, "MaterialReceiptsAgainstOrders", "termType" + v);
        }
        List<WebElement> termRowList = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Term * Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < 6; i++) {
            enterData(termRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "term" + i);
            enterData(commentsRowList.get(i), dataFile, "MaterialReceiptsAgainstOrders", "comments" + i);
        }
    }




}

