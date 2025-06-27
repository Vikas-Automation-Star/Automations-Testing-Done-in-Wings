package com.wings.pages.sales.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class SalesInvoiceAgainstDeliveries extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;
    int otherChargesInclusive=0;
    boolean IsAmountHeaderClicked=false;
    boolean otherChargesGSTCheckBox=false;
    boolean discountIsClicked=false;

    public SalesInvoiceAgainstDeliveries(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver);
        dataFile = file;
    }

    public String salesInvoiceAgainstDeliveries(String voucherNum) throws InterruptedException, IOException, ParseException, AWTException {
        long invoiceAgainstDeliveriesStart = System.nanoTime();
        System.out.println("SIAD startTime  in :" + invoiceAgainstDeliveriesStart);
        Thread.sleep(100);
        navigateToSalesInvoiceAgainstDeliveriesMenu();
        long generalInfoStart = System.nanoTime();
        System.out.println("Sales Invoice against Deliveries general Info started  in :" + generalInfoStart);
        Thread.sleep(4000);
        String oldVoucherID = oldTTransactionID();
        System.out.println("oldID: " + oldVoucherID);
        //branch selection
        enterBranchName(dataFile, "salesInvoiceAgainstDeliveries", "branch");
        enterLocation(dataFile, "salesInvoiceAgainstDeliveries", "location");
        enterCurrency(dataFile, "salesInvoiceAgainstDeliveries", "currency");
        enterCashOrParty(dataFile, "salesInvoiceAgainstDeliveries", "partyCode");
        Thread.sleep(1500);
        gstTransactionType("Inter State Sales to Registered Dealers");
        Thread.sleep(1000);
        selectPendingsSalesOrder(voucherNum, common.getData(dataFile, "salesInvoiceAgainstDeliveries", "fyYear"));
        Thread.sleep(5000);

        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

//        common.findWebElement("xpath","//Window[@Name='Wings Finance - PRO [ 24D Books Fin AT ; 01-04-2025 To 31-03-2026 ; Super User ]']/Window/Button[@Name='OK']");
//        WebDriverWait wait=new WebDriverWait(driver, 30);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//Window[@Name='Wings Finance - PRO [ 24D Books Fin AT ; 01-04-2025 To 31-03-2026 ; Super User ]']/Window/Button[@Name='OK']"))).click();

        enterCustomerEmail(dataFile, "salesInvoiceAgainstDeliveries", "email");
        enterCustomerMobileNum(dataFile, "salesInvoiceAgainstDeliveries", "mobileNum");
        enterSalesAccountCode(dataFile, "salesInvoiceAgainstDeliveries", "salesAccountCode");
        generalInfoSliderHandle(300);
        enterPriceList(dataFile, "salesInvoiceAgainstDeliveries", "priceList");
        enterTcsTransNature(dataFile, "salesInvoiceAgainstDeliveries", "tcsTransactionNature");
        enterExecutive(dataFile, "salesInvoiceAgainstDeliveries", "executive");
        enterRemarks(dataFile, "salesInvoiceAgainstDeliveries", "remarks");
        generalInfoSliderHandle(-500);
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("General information End:- ", generalInfoEndTime);
        //select pending quantity
        long addProductStart=System.nanoTime();
        addProductSalesInvoice();
        long addProductEnd=System.nanoTime()-addProductStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice Add Products:- ",addProductEnd);
















        long duration = System.nanoTime() - invoiceAgainstDeliveriesStart;
        FileUtil.writeTimeLog("Sales Invoice against Deliveries", duration / 1000000000);
        return "";
    }
    public void addProductSalesInvoice() throws InterruptedException, IOException, ParseException, AWTException {
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesInvoice", "productCount")); v++) {
            enterData("xpath","//Edit[@Name='Product Code Row "+v+", Not sorted.']",dataFile,"salesInvoice","productCode" + v);
        }
        List<WebElement> productAccRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Sales Account * Row')]");
        List<WebElement> productUOMRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        List<WebElement> productQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity Row ')]");
        List<WebElement> numOfPacksRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'No Of Packs Row ')]");
        List<WebElement> mrpRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'MRP Row ')]");
        List<WebElement> mrpAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'MRP Amount Row ')]");
        List<WebElement> minimumRateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Minimum Rate * Row ')]");
        List<WebElement> maximumRateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Maximum Rate * Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        List<WebElement> unitRateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Unit Rate Row ')]");
        List<WebElement> grossAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Gross Amount Row ')]");
        List<WebElement> voucherDiscountList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Voucher Disc % Row ')]");
        List<WebElement> voucherDiscountAmountList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Voucher Disc Row ')]");
        List<WebElement> partyDiscountList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Party Disc % Row ')]");
        List<WebElement> partyDiscountAmountList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Party Disc Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
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
        List<WebElement>  disount3BasisRowList= common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 3 Row ')]");
        List<WebElement> disount3RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 3 Row ')]");
        List<WebElement> disountAmount3RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Amount 3 Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
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
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        List<WebElement> infoRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 1 Row ')]");
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 50, 0);
        List<WebElement> valueRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 1 Row ')]");
        List<WebElement> dateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Date 1 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -2000, 0);
        for (int j = 0; j <productAccRowList.size() ; j++) {
            enterData(productAccRowList.get(j),dataFile,"salesInvoice","salesAccount"+j);
            enterData(productUOMRowList.get(j),dataFile,"salesInvoice","uom"+j);
                enterData(productQuantityRowList.get(j),dataFile,"salesInvoice","quantity"+j);
            enterData(numOfPacksRowList.get(j),dataFile,"salesInvoice","noOfPacks"+j);
            enterData(mrpRowList.get(j),dataFile,"salesInvoice","mrp"+j);
//            Assert.assertEquals(mrpAmountRowList.get(j).getText(), common.getData(dataFile, "salesInvoice", "mrpAmount" + j), "MRP Amount mismatch");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",430, 0);
            enterData(minimumRateRowList.get(j),dataFile,"salesInvoice","minRate"+j);
            enterData(maximumRateRowList.get(j),dataFile,"salesInvoice","maxRate"+j);
            enterData(unitRateRowList.get(j),dataFile,"salesInvoice","unitRate"+j);
//            Assert.assertEquals(grossAmountRowList.get(j).getText(), common.getData(dataFile, "salesInvoice", "grossAmount" + j), "Gross Amount mismatch");
            enterData(voucherDiscountList.get(j),dataFile,"salesInvoice","voucherDiscount"+j);
//            Assert.assertEquals(voucherDiscountAmountList.get(j).getText(), common.getData(dataFile, "salesInvoice", "voucherDiscAmount" + j), "Voucher discount Amount mismatch");
            enterData(partyDiscountList.get(j),dataFile,"salesInvoice","partyDiscount"+j);
//            Assert.assertEquals(partyDiscountAmountList.get(j).getText(), common.getData(dataFile, "salesInvoice", "partyDiscAmount" + j), "Party discount Amount mismatch");
            enterData(discountBasis1RowList.get(j),dataFile,"salesInvoice","Discount1B"+j);
            enterData(disount1RowList.get(j),dataFile,"salesInvoice","disc1Row"+j);
//            Assert.assertEquals(disountAmount1RowList.get(j).getText(), common.getData(dataFile, "salesInvoice", "Disc1Amount" + j), "Disc 1 amount is mismatch");
            Thread.sleep(1000);
            enterData(disount2BasisRowList.get(j),dataFile,"salesInvoice","Discount2B"+j);
            enterData(disount2RowList.get(j),dataFile,"salesInvoice","disc2Row"+j);
//            Assert.assertEquals(disountAmount2RowList.get(j).getText(), common.getData(dataFile, "salesInvoice", "Disc2Amount" + j), "Disc 2 amount is mismatch");
            enterData(disount3BasisRowList.get(j),dataFile,"salesInvoice","Discount3B"+j);
            enterData(disount3RowList.get(j),dataFile,"salesInvoice","disc3Row"+j);
//            Assert.assertEquals(disountAmount3RowList.get(j).getText(), common.getData(dataFile, "salesInvoice", "Disc3Amount" + j), "Disc 3 amount is mismatch");
            enterData(hsnCodeRowList.get(j),dataFile,"salesInvoice","HSNCode"+j);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",530, 0);
//            Assert.assertEquals(taxableValueRowList.get(j).getText(), common.getData(dataFile, "salesInvoice", "taxableValue" + j), "Taxable value mismatch");
//            Assert.assertEquals(igstAmountRowList.get(j).getText(), common.getData(dataFile, "salesInvoice", "igstAmount" + j), "IGST mismatch");
//            Assert.assertEquals(cessAmountRowList.get(j).getText(), common.getData(dataFile, "salesInvoice", "cessAmount" + j), "CESS mismatch");
//            Assert.assertEquals(gstAmountRowList.get(j).getText(), common.getData(dataFile, "salesInvoice", "expectedGStExclusive" + j), "GST Amount mismatch");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 700, 0);
//            Assert.assertEquals(netAmountRowList.get(j).getText(), common.getData(dataFile, "salesInvoice", "netAmount" + j), "Net Amount mismatch");
//            Assert.assertEquals(netInCompnayCurrencyAmountRowList.get(j).getText(), common.getData(dataFile, "salesInvoice", "netAmountcompanyCurrency" + j), "Net Amount in Company currency mismatch");
            enterData(departmentRowList.get(j),dataFile,"salesInvoice","department"+j);
            enterData(projectRowList.get(j),dataFile,"salesInvoice","project"+j);
            enterData(profitCentreRowList.get(j),dataFile,"salesInvoice","profitCentre"+j);
            enterData(costCentreRowList.get(j),dataFile,"salesInvoice","costCentre"+j);
            enterData(commentsRowList.get(j),dataFile,"salesInvoice","comments"+j);
            enterData(infoRowList.get(j),dataFile,"salesInvoice","info"+j);
            enterData(valueRowList.get(j),dataFile,"salesInvoice","value"+j);
            common.findWebElement("xpath", "//Edit[@Name='Date 1 Row " + j + ", Not sorted.']").sendKeys(Time.timeStamp());
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1400, 0);
        }
    }
    public  void addChargesAndDeductionsSalesInvoice() throws IOException, ParseException {
        navigateToChargesAndDeductionsTab();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesInvoice", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Charges Or Deductions * Row "+v+", Not sorted.']", dataFile,"salesInvoice", "charges"+v);
        }
        List<WebElement> accCodeRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Account Code Row ')]");
        if(!IsAmountHeaderClicked){
            common.clickElement("xpath","//Header[@Name='Amount *']");
            IsAmountHeaderClicked=true;
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
            enterData(accCodeRowList.get(i),dataFile,"salesInvoice","chargesAccount"+i);
            enterData(basisRowList.get(i),dataFile,"salesInvoice","chargesBasis"+i);
            enterData(percentageRowList.get(i),dataFile,"salesInvoice","chargesPercentage"+i);
            Assert.assertTrue(amountRowList.get(i).getText().equals(chargesRowList.get(i).getText())|| amountRowList.get(i).getText().equals(deductionsRowList.get(i).getText()),"Amount doesn't match Charges or Deductions for row " +i+". Actual: " + amountRowList.get(i).getText() + ", Charges: " + chargesRowList.get(i).getText() + ", Deductions: " + deductionsRowList.get(i).getText());
            enterData(departmentRowList.get(i),dataFile,"salesInvoice","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesInvoice","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesInvoice","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesInvoice","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesInvoice","comments"+i);
        }
    }
    public void addOtherChargesSalesInvoice() throws IOException, ParseException, InterruptedException {
        navigateToOtherChargesTab();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesInvoice", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Account Code Row "+v+", Not sorted.']", dataFile,"salesInvoice", "otherChargesAccount"+v);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
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
            enterData(amountRowList.get(i),dataFile,"salesInvoice","otherChargesAmount"+i);
            enterData(hsnCodeRowList.get(i),dataFile,"salesInvoice","HSNCode"+i);
            Thread.sleep(1000);
            Assert.assertEquals(taxableValueRowList.get(i).getText(), common.getData(dataFile, "salesInvoice","expectedOtherChargesTaxableValue" + i),"taxable value mismatch");
            Assert.assertEquals(igstAmountRowList.get(i).getText(), common.getData(dataFile, "salesInvoice","expectedOtherChargesIGSTAmount" + i),"IGST mismatch");
            Assert.assertEquals(cessAmountRowList.get(i).getText(), common.getData(dataFile,"salesInvoice", "expectedOtherChargesCESSAmount" +i),"CESS mismatch");
            Assert.assertEquals(gstAmountRowList.get(i).getText(), common.getData(dataFile,"salesInvoice", "expectedOtherChargesGSTAmount" +i), "GST Amount mismatch");
            Assert.assertEquals(netAmountRowList.get(i).getText(), common.getData(dataFile, "salesInvoice","expectedOtherChargesNetAmount" +i), "Net Amount mismatch");
            enterData(departmentRowList.get(i),dataFile,"salesInvoice","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesInvoice","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesInvoice","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesInvoice","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesInvoice","comments"+i);
            common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", -550, 0);
        }
    }
    public void addCashSalesInvoice() throws IOException, ParseException {
        navigateToCashTab();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesInvoice", "productCount")); v++) {
            enterInput("xpath","//Edit[@Name='Cash Account Code Row "+v+", Not sorted.']",dataFile,"salesInvoice","cashAccount"+v);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <6 ; i++) {
            enterData(amountRowList.get(i), dataFile, "salesInvoice", "cashAmount"+i);
            enterData(tdsTransNatureRowList.get(i), dataFile, "salesInvoice", "tdsTransactionNature"+i);
            enterData(tdsAccountRowList.get(i), dataFile, "salesInvoice", "tdsAccount"+i);
            enterData(tdsAmountRowList.get(i), dataFile, "salesInvoice", "tdsAmount"+i);
            enterData(departmentRowList.get(i),dataFile,"salesInvoice","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesInvoice","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesInvoice","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesInvoice","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesInvoice","comments"+i);
        }
    }

    public void addChequesSalesInvoice() throws IOException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(0).getText());
        elements.get(0).click();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesInvoice", "productCount")); v++) {
            enterInput("xpath","//Edit[@Name='Bank Account Code Row "+v+", Not sorted.']",dataFile,"salesInvoice","bankAccount"+v);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> chargesAccRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Account Code Row ')]");
        List<WebElement> chargesAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Row ')]");
        List<WebElement> netAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount Row ')]");
        List<WebElement> netAmountINCompanyCurrencyRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount In Company Currency Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <6 ; i++) {
            enterData(amountRowList.get(i), dataFile, "salesInvoice", "chequeAmount" + i);
            common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row "+i+", Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
            common.findWebElement("xpath","//Edit[@Name='Cheque Date * Row "+i+", Not sorted.']").sendKeys(Time.timeStamp());
            enterData(drawnOnRowList.get(i), dataFile, "salesInvoice", "drawnOn"+i);
            enterData(drawnOnBranchBranchRowList.get(i), dataFile, "salesInvoice", "drawnOnBranch");
            enterData(tdsTransNatureRowList.get(i), dataFile, "salesInvoice", "tdsTransactionNature"+i);
            enterData(tdsAccountRowList.get(i), dataFile, "salesInvoice", "tdsAccount"+i);
            enterData(tdsAmountRowList.get(i), dataFile, "salesInvoice", "tdsAmount"+i);
            enterData(chargesAccRowList.get(i), dataFile, "salesInvoice", "onlyCharges" + i);
            enterData(chargesAmountRowList.get(i), dataFile, "salesInvoice", "chargesAmount" + i);
            Assert.assertEquals(netAmountRowList.get(i).getText(), common.getData(dataFile, "salesInvoice","expectedChequeNetAmount"+i),"expectedChequeAmount mismatch");
            Assert.assertEquals(netAmountINCompanyCurrencyRowList.get(i).getText(), common.getData(dataFile, "salesInvoice","expectedChequeAmountInCompanyCurrency"+i),"expectedChequeAmountInCompanyCurrency mismatch");
            enterData(departmentRowList.get(i),dataFile,"salesInvoice","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesInvoice","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesInvoice","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesInvoice","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesInvoice","comments"+i);
            common.sliderHandling("xpath", "//Table[@Name='Cheques']/*/Thumb[@Name='Position']", -600, 0);

        }

    }
    public void addPostDatedChequesSalesInvoice() throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Post Dated Cheques')]");
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesInvoice", "productCount")); v++) {
            enterInput("xpath","//Edit[@Name='PDC Account Code Row "+v+", Not sorted.']",dataFile,"salesInvoice","pdcAccount"+v);
        }
        List<WebElement> amoutRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <6 ; i++) {
            enterData(amoutRowList.get(i), dataFile, "salesInvoice", "chequeAmount" + i);
            common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row "+i+", Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
            enterData(drawnOnRowList.get(i), dataFile, "salesInvoice", "drawnOn"+i);
            enterData(drawnOnBranchBranchRowList.get(i), dataFile, "salesInvoice", "drawnOnBranch");
            enterData(tdsTransNatureRowList.get(i), dataFile, "salesInvoice", "tdsTransactionNature"+i);
            enterData(tdsAccountRowList.get(i), dataFile, "salesInvoice", "tdsAccount"+i);
            enterData(tdsAmountRowList.get(i), dataFile, "salesInvoice", "tdsAmount"+i);
            enterData(departmentRowList.get(i),dataFile,"salesInvoice","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesInvoice","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesInvoice","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesInvoice","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesInvoice","comments"+i);
        }
    }
    public void addChequesPDCSalesInvoice() throws IOException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(2).getText());
        elements.get(2).click();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesInvoice", "productCount")); v++) {
            enterInput("xpath","//Edit[@Name='Bank Account Code Row "+v+", Not sorted.']",dataFile,"salesInvoice","bankAccount"+v);

        }
        List<WebElement> chequeAmountRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i <6 ; i++) {
            enterData(chequeAmountRowList.get(i), dataFile, "salesInvoice", "chequeAmount"+i);
            common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row "+i+", Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
            enterData(drawnOnRowList.get(i), dataFile, "salesInvoice", "drawnOn"+i);
            enterData(drawnOnBranchBranchRowList.get(i), dataFile, "salesInvoice", "drawnOnBranch");
            enterData(tdsTransNatureRowList.get(i), dataFile, "salesInvoice", "tdsTransactionNature"+i);
            enterData(tdsAccountRowList.get(i), dataFile, "salesInvoice", "tdsAccount"+i);
            enterData(tdsAmountRowList.get(i), dataFile, "salesInvoice", "tdsAmount"+i);
            enterData(departmentRowList.get(i),dataFile,"salesInvoice","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesInvoice","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesInvoice","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesInvoice","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesInvoice","comments"+i);
        }
    }
    public void addCreditCardSalesInvoice() throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Credit Card')]");
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesInvoice", "productCount")); v++) {
            enterInput("xpath","//Edit[@Name='Swipe Machine Type * Row "+v+", Not sorted.']",dataFile,"salesInvoice","swipeMachineType"+v);

        }
        List<WebElement> swipeTypeRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Swipe Type * Row ')]");
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> chargesAccRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Account Code Row ')]");
        List<WebElement> chargesPercentageRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Percentage Row ')]");
        List<WebElement> executiveRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='CreditCard']/*/Thumb[@Name='Position']", 500, 0);
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='CreditCard']/*/Thumb[@Name='Position']", -500, 0);
        for (int i = 0; i <6 ; i++) {
            enterData(swipeTypeRowList.get(i), dataFile, "salesInvoice", "swipeType"+i);
            enterData(amountRowList.get(i), dataFile, "salesInvoice", "creditCardAmount"+i);
            enterData(tdsTransNatureRowList.get(i), dataFile, "salesInvoice", "tdsTransactionNature"+i);
            enterData(tdsAccountRowList.get(i), dataFile, "salesInvoice", "tdsAccount"+i);
            enterData(tdsAmountRowList.get(i), dataFile, "salesInvoice", "tdsAmount"+i);
            common.findWebElement("xpath","//Edit[@Name='Card No Row "+i+", Not sorted.']").sendKeys("852741"+common.getRandom());
            common.clickElement("xpath","//Edit[@Name='Approval No * Row "+i+", Not sorted.']");
            common.findWebElement("xpath","//Edit[@Name='Approval No * Row "+i+", Not sorted.']").sendKeys("75241"+common.getRandom());
            enterData(chargesAccRowList.get(i), dataFile, "salesInvoice", "onlyCharges" + i);
            enterData(chargesPercentageRowList.get(i), dataFile, "salesInvoice", "chargesPercentage" + i);
            enterData(executiveRowList.get(i),dataFile,"salesInvoice","executive"+i);
            enterData(departmentRowList.get(i),dataFile,"salesInvoice","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesInvoice","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesInvoice","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesInvoice","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesInvoice","comments"+i);
            common.sliderHandling("xpath", "//Table[@Name='CreditCard']/*/Thumb[@Name='Position']", -600, 0);
        }
    }
    public void addTermsAndConditions() throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Terms And Conditions')]");
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesInvoice", "productCount")); v++) {
            enterInput("xpath","//Edit[@Name='Term Type * Row "+v+", Not sorted.']",dataFile,"salesInvoice","termType"+v);
        }
        List<WebElement> termRowList = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Term * Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < 6; i++) {
            enterData(termRowList.get(i), dataFile, "salesInvoice", "term"+i);
            enterData(commentsRowList.get(i), dataFile, "salesInvoice", "comments"+i);
        }
    }
    public void addAllocations() throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Allocations')]");
        enterInput("xpath","//Edit[@Name='Department']",dataFile,"salesInvoice","department0");
        enterInput("xpath","//Edit[@Name='Project']",dataFile,"salesInvoice","project0");
        enterInput("xpath","//Edit[@Name='Profit Centre']",dataFile,"salesInvoice","profitCentre0");
        enterInput("xpath","//Edit[@Name='Cost Centre']",dataFile,"salesInvoice","costCentre0");
    }
}