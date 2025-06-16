package com.wings.pages.sales.transactions;

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
import java.awt.*;
import java.util.List;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SalesInvoice extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;
    int otherChargesInclusive=0;
    boolean IsAmountHeaderClicked=false;
    boolean otherChargesGSTCheckBox=false;
    boolean discountIsClicked=false;

    public SalesInvoice(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;

    }

    public String salesInvoice() throws InterruptedException, IOException, ParseException, AWTException {
        long start = System.nanoTime();
        System.out.println("Sales Invoice startTime executed in :" + start);
        navigateToSalesInvoiceMenu();
        Thread.sleep(2000);

        String oldVoucherID = oldTTransactionID();
        System.out.println("oldID: " + oldVoucherID);
        enterDate();
        enterBranchName(dataFile, "salesInvoice", "branch");
        enterLocation(dataFile, "salesInvoice", "location");
        enterCurrency(dataFile, "salesInvoice", "currency");
        enterCashOrParty(dataFile, "salesInvoice", "partyCode");
        Thread.sleep(1500);
        gstTransactionType("Inter State Sales to Registered Dealers");
        Thread.sleep(1000);
        enterCustomerEmail(dataFile, "salesInvoice", "email");
        enterCustomerMobileNum(dataFile, "salesInvoice", "mobileNum");
        enterSalesAccountCode(dataFile, "salesInvoice", "salesAccountCode");
        generalInfoSliderHandle(250);
        enterTcsTransNature(dataFile, "salesInvoice", "tcsTransactionNature");
        common.inputText("xpath", "//Edit[@Name='Invoice Type']", common.getData(dataFile, "salesInvoice", "invoice"));
        Thread.sleep(1000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        enterPriceList(dataFile, "salesInvoice", "priceList");
        generalInfoSliderHandle(400);
        enterExecutive(dataFile, "salesInvoice", "executive");
        enterShippingBillNo(dataFile, "salesInvoice", "shippingBillNo");
        enterShippingDate();
        enterPortCode(dataFile, "salesInvoice", "portCode");
        enterRemarks(dataFile, "salesInvoice", "remarks");
        generalInfoSliderHandle(-500);
        //F3-Items
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount")); i++) {
//            addProduct1(i);
        }
//        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount")); i++) {
//            enterChargesAndDeductionsSalesInvoice(dataFile, "salesInvoice",i);
//        }
//        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount")); i++) {
//            enterOtherChargesSalesInvoice(dataFile, "salesInvoice", i);
//        }
//        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount")); i++) {
//            enterCashSalesInvoice(dataFile, "salesInvoice",i);
//        }
//        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount")); i++) {
//            enterChequesSalesInvoice(dataFile, "salesInvoice",i);
//        }
//        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount")); i++) {
//            enterPostDatedChequesSalesInvoice(dataFile, "salesInvoice",i);
//        }
//        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount")); i++) {
//            enterChequesPDCSalesInvoice(dataFile, "salesInvoice",i);
//        }
//        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount")); i++) {
//                    enterCreditCardSalesInvoie(dataFile, "salesInvoice",i);
//        }
//        scrollRight(10);
//        enterOtherInfo(dataFile,"salesInvoice");
//        scrollRight(7);
//        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount")); i++) {
//            termsAndConditions(dataFile,"salesInvoice",i);
//        }
//        enterAllocations(dataFile,"salesInvoice");
//        transactionSave();

        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Sales Invoice ended at", duration / 1000000000);
        return "";
    }

//        navigateToBillsPayablesTab();
//        common.deleteInvalidRows();
//        //collections
//        enterCashinSIAO(dataFile, "salesInvoice");
//        enterChequesinSIAO(dataFile, "salesInvoice");
//        enterPostDatedChequesinSIAO(dataFile, "salesInvoice");
//        enterChequesPDCinSIAO(dataFile, "salesInvoice");
//        enterCreditCardinSIAO(dataFile, "salesInvoice");
//        //verify all the fields in summary are fetching data
//        navigateToPaytymTab();
//        for (int j = 0; j < 2; j++) {
//            robot.keyPress(KeyEvent.VK_RIGHT);
//            robot.keyRelease(KeyEvent.VK_RIGHT);
//        }
//        enterOtherInfo(dataFile,"salesInvoice");
//        //summary
//        navigateToOtherInfoTab();
//        for (int i = 0; i < 5; i++) {
//            robot.keyPress(KeyEvent.VK_RIGHT);
//            robot.keyRelease(KeyEvent.VK_RIGHT);
//        }
//        quantityPresentInSummary();
//        grossAmountPresentInSummary();
//        grossMinusDiscountPresentInSummary();
//        netAmountPresentInSummary();
//        cessPresentInSummary();
//        iGSTPresentInSummary();
//        tcsAmountPresentInSummary();
//        tcsTaxableValuePresentInSummary();
//        totalValuePresentInSummary();
//        totalValueInCompanyCurrenyPresentInSummary();
//        //save
//        transactionSave();
//        String newVoucherID =newTransactionID(oldVoucherID);
//        System.out.println("newID: "+newVoucherID);
//        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
//        Thread.sleep(1000);
//        common.clickElement("name", "Sales");
//        common.clickElement("name", "Invoices");
//        common.clickElement("name", "Sales Book");
//        Thread.sleep(1000);
//        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
//        Thread.sleep(1500);
//        verifyReport(newVoucherID,dataFile,"salesInvoice");
//
//        long duration = System.nanoTime() - start;
//        FileUtil.writeTimeLog("Sales Invoice", duration / 1000000000);
//
//        return newVoucherID;
//    }
//
//    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
//        if (common.getData(dataFile, "salesInvoice", "productType" + i).equals("general")) {
//            generalProduct(dataFile, "salesInvoice", "productCode" + i, "quantity" + i, "freeQuantity" + i, i);
//        } else if (common.getData(dataFile, "salesInvoice", "productType" + i).equals("multiBatch")) {
//            multiBatchProduct(dataFile, "salesInvoice", "productCode" + i, "quantity" + i, "freeQuantity" + i, i);
//        } else if (common.getData(dataFile, "salesInvoice", "productType" + i).equals("serial")) {
//            serialNumberProduct(dataFile, "salesInvoice", "productCode" + i, i);
//        }

        public void addProduct() throws InterruptedException, IOException, ParseException, AWTException {
            for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"PurchaseVoucher", "productCount")); v++) {
                enterData("xpath","//Edit[@Name='Product Code Row "+v+", Not sorted.']",dataFile,"PurchaseVoucher","productCode" + v);
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
            List<WebElement>  disount3BasisRowList= common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 3 Row ')]");
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
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",350, 0);
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
//            for (int j = 0; j < 6 && j <productAccRowList.size() ; j++) {
//                enterData(productAccRowList.get(j),dataFile,"PurchaseVoucher","purchaseAccount"+j);
//                enterData(productUOMRowList.get(j),dataFile,"PurchaseVoucher","uom"+j);
//                if (j<4){
//                    enterData(productQuantityRowList.get(j),dataFile,"PurchaseVoucher","quantity"+j);
//                }
//                else {
//                    common.clickElement("xpath", "//Button[@Name='Serial Nos Row "+j+"']");
//                    WebElement increment = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
//                    increment.sendKeys(Keys.TAB,common.getData(dataFile,"PurchaseVoucher", "serialText"+j)+ Common.getRandomChar(), Keys.TAB, common.getData(dataFile,"PurchaseVoucher", "quantity"+j),Keys.ENTER);
//                    if(Boolean.parseBoolean(common.getData(dataFile,"PurchaseVoucher","enableFreeQuantity"))) {
//                        WebElement freeQ = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
//                        freeQ.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, common.getData(dataFile,"PurchaseVoucher", "freeQuantity"+j),Keys.ENTER);
//                    }
//                    common.clickElement("xpath", "//Button[@Name='OK']");
//                }
//                enterData(freeQuantityRowList.get(j),dataFile,"PurchaseVoucher","freeQuantity"+j);
//                enterData(numOfPacksRowList.get(j),dataFile,"PurchaseVoucher","noOfPacks"+j);
//                enterData(mrpRowList.get(j),dataFile,"PurchaseVoucher","mrpAmount"+j);
//                Assert.assertEquals(mrpAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedMrpAmount" + j), "MRP Amount mismatch");
//                enterData(unitRateRowList.get(j),dataFile,"PurchaseVoucher","unitRate"+j);
//                enterData(editableGrossAmountList.get(j),dataFile,"PurchaseVoucher","editableGrossAmount"+j);
//                Assert.assertEquals(grossAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedGrossAmount" + j), "Gross Amount mismatch");
//                enterData(voucherDiscountList.get(j),dataFile,"PurchaseVoucher","voucherDiscount"+j);
//                Assert.assertEquals(voucherDiscountAmountList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedVoucherDiscount" + j), "Voucher discount Amount mismatch");
//                enterData(discountBasis1RowList.get(j),dataFile,"PurchaseVoucher","discount1Basis"+j);
//                enterData(disount1RowList.get(j),dataFile,"PurchaseVoucher","discount1Value"+j);
//                Assert.assertEquals(disountAmount1RowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedDiscount1Value" + j), "Disc 1 amount is mismatch");
//                enterData(disount2BasisRowList.get(j),dataFile,"PurchaseVoucher","discount2Basis"+j);
//                enterData(disount2RowList.get(j),dataFile,"PurchaseVoucher","discount2Value"+j);
//                Assert.assertEquals(disountAmount2RowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedDiscount2Value" + j), "Disc 2 amount is mismatch");
//                enterData(disount3BasisRowList.get(j),dataFile,"PurchaseVoucher","discount3Basis"+j);
//                enterData(disount3RowList.get(j),dataFile,"PurchaseVoucher","discount3Value"+j);
//                Assert.assertEquals(disountAmount3RowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedDiscount3Value" + j), "Disc 3 amount is mismatch");
//                enterData(hsnCodeRowList.get(j),dataFile,"PurchaseVoucher","HSNCode"+j);
//                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",Integer.parseInt(common.getData(dataFile, "PurchaseVoucher", "slideHandleSecond" + j)), 0);
//                enterData(gstProductCategoryRowList.get(j),dataFile,"PurchaseVoucher","GSTPercentage"+j);
//                enterData(cessProductCategoryRowList.get(j),dataFile,"PurchaseVoucher","CESSPercentage"+j);
//                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",430, 0);
//                Assert.assertEquals(taxableValueRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedTaxableValue" + j), "Taxable value mismatch");
//                Assert.assertEquals(igstAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedIGSTAmount" + j), "IGST mismatch");
//                Assert.assertEquals(cessAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedCESSAmount" + j), "CESS mismatch");
//                Assert.assertEquals(gstAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedGSTAmount" + j), "GST Amount mismatch");
//                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 700, 0);
//                Assert.assertEquals(netAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedNetAmount" + j), "Net Amount mismatch");
//                Assert.assertEquals(netInCompnayCurrencyAmountRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedNetAmountInCompanyCurrency" + j), "Net Amount in Company currency mismatch");
//                Assert.assertEquals(tcsTaxableAmountRowList.get(j).getText(),common.getData(dataFile, "PurchaseVoucher", "expectedTCSTaxableValue" + j), "Tcs Taxable value mismatch");
//                enterItemsOtherCosts(dataFile, "PurchaseVoucher",j);
//                Assert.assertEquals(totalItemOtherCostRowList.get(j).getText(), common.getData(dataFile, "PurchaseVoucher", "expectedItemsOtherCosts" + j), "Items other costs mismatch");
//                enterData(departmentRowList.get(j),dataFile,"PurchaseVoucher","department"+j);
//                enterData(projectRowList.get(j),dataFile,"PurchaseVoucher","project"+j);
//                enterData(profitCentreRowList.get(j),dataFile,"PurchaseVoucher","profitCentre"+j);
//                enterData(costCentreRowList.get(j),dataFile,"PurchaseVoucher","costCentre"+j);
//                enterData(commentsRowList.get(j),dataFile,"PurchaseVoucher","comments"+j);
//                enterData(infoRowList.get(j),dataFile,"PurchaseVoucher","info"+j);
//                enterData(valueRowList.get(j),dataFile,"PurchaseVoucher","value"+j);
//                common.findWebElement("xpath", "//Edit[@Name='Date 1 Row " + j + ", Not sorted.']").sendKeys(Time.timeStamp());
//                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1400, 0);
//            }
    }
}