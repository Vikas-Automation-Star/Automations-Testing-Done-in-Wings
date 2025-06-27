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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class DeliveriesAgainstOrdersTransaction extends TransactionsBaseClass {
    WindowsDriver driver,rootDriver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;
    int otherChargesInclusive=0;
    boolean IsAmountHeaderClicked=false;
    boolean otherChargesGSTCheckBox=false;
    boolean discountIsClicked=false;

    public DeliveriesAgainstOrdersTransaction(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String deliveriesAgainstOrders(String voucherNum) throws InterruptedException, IOException, ParseException, AWTException {
        long deliveriesAgainstOrderStart = System.nanoTime();
        System.out.println("Deliveries Against Orders startTime executed in :"+ deliveriesAgainstOrderStart);
        navigateToDeliveriesAgainstOrdersMenu();
        Thread.sleep(2000);
        long generalInfoStart = System.nanoTime();
        System.out.println("deliveries against Order general Info started executed in :" + generalInfoStart);
        String oldVoucherID = oldTTransactionID();
        System.out.println("oldID: " + oldVoucherID);
        //branch selection
        enterDate();
        enterBranchName(dataFile, "deliveriesAgainstOrders", "branch");
        enterLocation(dataFile, "deliveriesAgainstOrders", "location");
        enterCurrency(dataFile, "deliveriesAgainstOrders", "currency");
        enterCashOrParty(dataFile, "deliveriesAgainstOrders", "partyCode");
        Thread.sleep(1500);
        gstTransactionType("Inter State Sales to Registered Dealers");
        Thread.sleep(2000);
        selectPendingsSalesOrder(voucherNum,"20250401");
        WebDriverWait wait=new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//Window[contains(@Name,'Wings Finance - PRO ')]/*/Button[@Name='OK']"))).click();
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("General information End:- ", generalInfoEndTime);
        //F3-Items
        long addProductStart=System.nanoTime();
        addProductdeliveriesAgainstOrders();
        long addProductEnd=System.nanoTime()-addProductStart;
        FileUtil.writeTimeLogInMinutes("deliveries Against Orders Add Products:- ",addProductEnd);
        //charges and deductions
        long chargesDeductionsStart =System.nanoTime();
        addChargesAndDeductionsdeliveriesAgainstOrders();
        long chargesDeductionsEnd=System.nanoTime()- chargesDeductionsStart;
        FileUtil.writeTimeLogInMinutes("deliveries Against Orders Charges and Deductions:- ",chargesDeductionsEnd);
        //other charges
        long otherChargesStart =System.nanoTime();
        addOtherChargesdeliveriesAgainstOrders();
        long otherChargesEnd=System.nanoTime()- otherChargesStart;
        FileUtil.writeTimeLogInMinutes("deliveries Against Orders Other Charges:- ",otherChargesEnd);
        //validate gst
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        //other Info
        long otherInfoTabStart =System.nanoTime();
        enterOtherInfo(dataFile,"deliveriesAgainstOrders");
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("deliveries Against Orders Other Info Tab:- ", otherInfoTabEnd);
        //additional Info
        long additionalInfoTabStart =System.nanoTime();
        enterAdditionalInfo(dataFile,"deliveriesAgainstOrders");
        long additionalInfoTabEnd =System.nanoTime()- additionalInfoTabStart;
        FileUtil.writeTimeLogInMinutes("deliveries Against Orders Additional Info Tab:- ", additionalInfoTabEnd);
        //terms and Cond
        long termsConditionsTabStart =System.nanoTime();
        addTermsAndConditions();
        long termsConditionsTabEnd =System.nanoTime()- termsConditionsTabStart;
        FileUtil.writeTimeLogInMinutes("deliveries Against Orders Terms and Conditions Tab:- ", termsConditionsTabEnd);
        //summary
        long summaryStart=System.nanoTime();
        common.clickElement("xpath","//TabItem[@Name='  Ctrl-F8 Summary  ']");
        java.util.List<WebElement> summary=common.findWebElements("xpath","//Pane[@Name='']//Edit/*");
        System.out.println("Summary size: "+ summary.size());
        //validate summary
        assertSummaaryFields(summary,"Quantity", common.getData(dataFile,"deliveriesAgainstOrders","expectedQuantityInSummary"));
        assertSummaaryFields(summary,"Quantity In SKU", common.getData(dataFile,"deliveriesAgainstOrders","expectedQuantityInSKUSummary"));
        assertSummaaryFields(summary,"Gross Amount", common.getData(dataFile,"deliveriesAgainstOrders","expectedGrossAmount"));
        assertSummaaryFields(summary,"Discount", common.getData(dataFile,"deliveriesAgainstOrders","expectedDiscount"));
        assertSummaaryFields(summary,"Gross - Disc", common.getData(dataFile,"deliveriesAgainstOrders","expectedGrossMinusDiscount"));
        assertSummaaryFields(summary,"IGST", common.getData(dataFile,"deliveriesAgainstOrders","expectedIGST"));
        assertSummaaryFields(summary,"CESS", common.getData(dataFile,"deliveriesAgainstOrders","expectedCESS"));
        assertSummaaryFields(summary,"Net Amount", common.getData(dataFile,"deliveriesAgainstOrders","expectedNetAmount"));
        assertSummaaryFields(summary,"Charges", common.getData(dataFile,"deliveriesAgainstOrders","expectedCharges"));
        assertSummaaryFields(summary,"Deductions", common.getData(dataFile,"deliveriesAgainstOrders","expectedDeductions"));
        assertSummaaryFields(summary,"Other Charges", common.getData(dataFile,"deliveriesAgainstOrders","expectedOtherCharges"));
        assertSummaaryFields(summary,"Other Charges IGST", common.getData(dataFile,"deliveriesAgainstOrders","expectedOtherChargesIGST"));
        assertSummaaryFields(summary,"Other Charges CESS", common.getData(dataFile,"deliveriesAgainstOrders","expectedOtherChargesCESS"));
        assertSummaaryFields(summary,"Total Value", common.getData(dataFile,"deliveriesAgainstOrders","expectedTotalValue"));
        assertSummaaryFields(summary,"Total Value In Company Currency", common.getData(dataFile,"deliveriesAgainstOrders","expectedTotalValueInCompanyCurrency"));

        long summaryEnd=System.nanoTime()-summaryStart;
        FileUtil.writeTimeLogInMinutes("deliveries Against Orders Summary End:- ", summaryEnd);
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long deliveriesAgainstOrderEnd = System.nanoTime() - deliveriesAgainstOrderStart;
        FileUtil.writeTimeLogInMinutes("deliveries against Order ended at:- ", deliveriesAgainstOrderEnd);

        return newVoucherID;
    }
    public void addProductdeliveriesAgainstOrders() throws InterruptedException, IOException, ParseException, AWTException {
        java.util.List<WebElement> productUOMRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 400, 0);
        java.util.List<WebElement> productQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity Row ')]");
        List<WebElement> freeQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Free Quantity Row')]");

        java.util.List<WebElement> mrpRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'MRP Row ')]");
        java.util.List<WebElement> mrpAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'MRP Amount Row ')]");
        java.util.List<WebElement> unitRateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Unit Rate Row ')]");
        java.util.List<WebElement> grossAmountRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Gross Amount Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
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
            enterData(productUOMRowList.get(j),dataFile,"deliveriesAgainstOrders","uom"+j);

            if (j<2){
                enterData(productQuantityRowList.get(j),dataFile,"deliveriesAgainstOrders","quantity"+j);
                enterData(freeQuantityRowList.get(j),dataFile,"salesInvoice","freeQuantity"+j);
            } else if (j<4){
                common.clickElement("xpath", "//Button[@Name='Stock Details Row " + j + "']");
                Thread.sleep(3000);
                List<WebElement> rows = common.findWebElements("xpath", "//Table[@Name='Batch Details']/*[@Name='Data Panel']/*[@Name='Row 1']/*[@Name='Quantity row 1']");
                System.out.println("Row count: " + rows.size());
                for (WebElement k : rows) {
                    k.click();
                    k.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
                    k.sendKeys(common.getData(dataFile,"deliveriesAgainstOrders","quantity"+j), Keys.TAB);
                    if (Boolean.parseBoolean(common.getData(dataFile,"deliveriesAgainstOrders","enableFreeQuantity"))){
                        k.sendKeys(common.getData(dataFile,"deliveriesAgainstOrders","freeQuantity"+j),Keys.TAB);
                    }
                }
                common.clickElement("xpath", "//Button[@Name='OK']");
            } else {
                common.clickElement("xpath", "//Button[@Name='Stock Details Row " + j + "']");
                List<WebElement> rows = common.findWebElements("xpath", "//Table[@Name='Serial Numbers List']/*[@Name='Data Panel']/*[contains(@Name,'Row')]/*[contains(@Name,'Select row')]");
                System.out.println("Row count: " + rows.size());
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
                for (int z = 0; z < Integer.parseInt(common.getData(dataFile,"deliveriesAgainstOrders","numOfSerialProducts")); z++) {
                    robot.keyPress(KeyEvent.VK_SPACE);
                    robot.keyRelease(KeyEvent.VK_SPACE);
                    robot.keyPress(KeyEvent.VK_DOWN);
                    robot.keyRelease(KeyEvent.VK_DOWN);
                    Thread.sleep(1500);
                }
                List<WebElement> free=common.findWebElements("xpath","//Table[@Name='Selected Serial Numbers']/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
                System.out.println("free elements size: "+free.size());
                if (Boolean.parseBoolean(common.getData(dataFile,"deliveriesAgainstOrders","enableFreeQuantity"))){
                    for (int k = 1; k <=Integer.parseInt(common.getData(dataFile,"deliveriesAgainstOrders","numOfSerialProductsFree"+j)); k++) {
                        String rowXPath = "//Table[@Name='Selected Serial Numbers']/*[@Name='Data Panel']/*[@Name='Row "+k+"']/*[@Name='FreeQuantity row "+k+"']";
                        // Find the element based on the dynamic XPath
                        WebElement button = common.findWebElement("xpath", rowXPath);
                        button.click();
                    }
                }
                common.clickElement("xpath", "//Button[@Name='OK']");
            }
            Thread.sleep(2500);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",400, 0);
            enterData(mrpRowList.get(j),dataFile,"deliveriesAgainstOrders","mrp"+j);
//            Assert.assertEquals(mrpAmountRowList.get(j).getText(), common.getData(dataFile, "deliveriesAgainstOrders", "mrpAmount" + j), "MRP Amount mismatch");
            enterData(unitRateRowList.get(j),dataFile,"deliveriesAgainstOrders","unitRate"+j);
//            Assert.assertEquals(grossAmountRowList.get(j).getText(), common.getData(dataFile, "deliveriesAgainstOrders", "grossAmount" + j), "Gross Amount mismatch");
            enterData(partyDiscountList.get(j),dataFile,"deliveriesAgainstOrders","partyDiscount"+j);
//            Assert.assertEquals(partyDiscountAmountList.get(j).getText(), common.getData(dataFile, "deliveriesAgainstOrders", "partyDiscAmount" + j), "Party discount Amount mismatch");
            enterData(discountBasis1RowList.get(j),dataFile,"deliveriesAgainstOrders","Discount1B"+j);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",400, 0);
            enterData(disount1RowList.get(j),dataFile,"deliveriesAgainstOrders","disc1Row"+j);
//            Assert.assertEquals(disountAmount1RowList.get(j).getText(), common.getData(dataFile, "deliveriesAgainstOrders", "Disc1Amount" + j), "Disc 1 amount is mismatch");
            Thread.sleep(1000);
            enterData(disount2BasisRowList.get(j),dataFile,"deliveriesAgainstOrders","Discount2B"+j);
            enterData(disount2RowList.get(j),dataFile,"deliveriesAgainstOrders","disc2Row"+j);
//            Assert.assertEquals(disountAmount2RowList.get(j).getText(), common.getData(dataFile, "deliveriesAgainstOrders", "Disc2Amount" + j), "Disc 2 amount is mismatch");
            enterData(disount3BasisRowList.get(j),dataFile,"deliveriesAgainstOrders","Discount3B"+j);
            enterData(disount3RowList.get(j),dataFile,"deliveriesAgainstOrders","disc3Row"+j);
//            Assert.assertEquals(disountAmount3RowList.get(j).getText(), common.getData(dataFile, "deliveriesAgainstOrders", "Disc3Amount" + j), "Disc 3 amount is mismatch");
            enterData(hsnCodeRowList.get(j),dataFile,"deliveriesAgainstOrders","HSNCode"+j);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",530, 0);
//            Assert.assertEquals(taxableValueRowList.get(j).getText(), common.getData(dataFile, "deliveriesAgainstOrders", "taxableValue" + j), "Taxable value mismatch");
//            Assert.assertEquals(igstAmountRowList.get(j).getText(), common.getData(dataFile, "deliveriesAgainstOrders", "igstAmount" + j), "IGST mismatch");
//            Assert.assertEquals(cessAmountRowList.get(j).getText(), common.getData(dataFile, "deliveriesAgainstOrders", "cessAmount" + j), "CESS mismatch");
//            Assert.assertEquals(gstAmountRowList.get(j).getText(), common.getData(dataFile, "deliveriesAgainstOrders", "expectedGStExclusive" + j), "GST Amount mismatch");
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 700, 0);
//            Assert.assertEquals(netAmountRowList.get(j).getText(), common.getData(dataFile, "deliveriesAgainstOrders", "netAmount" + j), "Net Amount mismatch");
//            Assert.assertEquals(netInCompnayCurrencyAmountRowList.get(j).getText(), common.getData(dataFile, "deliveriesAgainstOrders", "netAmountcompanyCurrency" + j), "Net Amount in Company currency mismatch");
            enterData(departmentRowList.get(j),dataFile,"deliveriesAgainstOrders","department"+j);
            enterData(projectRowList.get(j),dataFile,"deliveriesAgainstOrders","project"+j);
            enterData(profitCentreRowList.get(j),dataFile,"deliveriesAgainstOrders","profitCentre"+j);
            enterData(costCentreRowList.get(j),dataFile,"deliveriesAgainstOrders","costCentre"+j);
            enterData(commentsRowList.get(j),dataFile,"deliveriesAgainstOrders","comments"+j);
            enterData(infoRowList.get(j),dataFile,"deliveriesAgainstOrders","info"+j);
            enterData(valueRowList.get(j),dataFile,"deliveriesAgainstOrders","value"+j);
            common.findWebElement("xpath", "//Edit[@Name='Date 1 Row " + j + ", Not sorted.']").sendKeys(Time.timeStamp());
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1400, 0);
        }
    }

    public  void addChargesAndDeductionsdeliveriesAgainstOrders() throws IOException, ParseException {
        navigateToChargesAndDeductionsTab();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"deliveriesAgainstOrders", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Charges Or Deductions * Row "+v+", Not sorted.']", dataFile,"deliveriesAgainstOrders", "charges"+v);
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
            enterData(accCodeRowList.get(i),dataFile,"deliveriesAgainstOrders","chargesAccount"+i);
            enterData(basisRowList.get(i),dataFile,"deliveriesAgainstOrders","chargesBasis"+i);
            enterData(percentageRowList.get(i),dataFile,"deliveriesAgainstOrders","chargesPercentage"+i);
            Assert.assertTrue(amountRowList.get(i).getText().equals(chargesRowList.get(i).getText())|| amountRowList.get(i).getText().equals(deductionsRowList.get(i).getText()),"Amount doesn't match Charges or Deductions for row " +i+". Actual: " + amountRowList.get(i).getText() + ", Charges: " + chargesRowList.get(i).getText() + ", Deductions: " + deductionsRowList.get(i).getText());
            enterData(departmentRowList.get(i),dataFile,"deliveriesAgainstOrders","department"+i);
            enterData(projectRowList.get(i),dataFile,"deliveriesAgainstOrders","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"deliveriesAgainstOrders","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"deliveriesAgainstOrders","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"deliveriesAgainstOrders","comments"+i);
        }
    }
    public void addOtherChargesdeliveriesAgainstOrders() throws IOException, ParseException, InterruptedException {
        navigateToOtherChargesTab();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"deliveriesAgainstOrders", "productCount")); v++) {
            enterData("xpath", "//Edit[@Name='Account Code Row "+v+", Not sorted.']", dataFile,"deliveriesAgainstOrders", "otherChargesAccount"+v);
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
            enterData(amountRowList.get(i),dataFile,"deliveriesAgainstOrders","otherChargesAmount"+i);
            enterData(hsnCodeRowList.get(i),dataFile,"deliveriesAgainstOrders","HSNCode"+i);
            Thread.sleep(1000);
            Assert.assertEquals(taxableValueRowList.get(i).getText(), common.getData(dataFile, "deliveriesAgainstOrders","expectedOtherChargesTaxableValue" + i),"taxable value mismatch");
            Assert.assertEquals(igstAmountRowList.get(i).getText(), common.getData(dataFile, "deliveriesAgainstOrders","expectedOtherChargesIGSTAmount" + i),"IGST mismatch");
            Assert.assertEquals(cessAmountRowList.get(i).getText(), common.getData(dataFile,"deliveriesAgainstOrders", "expectedOtherChargesCESSAmount" +i),"CESS mismatch");
            Assert.assertEquals(gstAmountRowList.get(i).getText(), common.getData(dataFile,"deliveriesAgainstOrders", "expectedOtherChargesGSTAmount" +i), "GST Amount mismatch");
            Assert.assertEquals(netAmountRowList.get(i).getText(), common.getData(dataFile, "deliveriesAgainstOrders","expectedOtherChargesNetAmount" +i), "Net Amount mismatch");
            enterData(departmentRowList.get(i),dataFile,"deliveriesAgainstOrders","department"+i);
            enterData(projectRowList.get(i),dataFile,"deliveriesAgainstOrders","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"deliveriesAgainstOrders","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"deliveriesAgainstOrders","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"deliveriesAgainstOrders","comments"+i);
            common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", -550, 0);
        }
    }
    public void addTermsAndConditions() throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Terms And Conditions')]");
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"deliveriesAgainstOrders", "productCount")); v++) {
            enterInput("xpath","//Edit[@Name='Term Type * Row "+v+", Not sorted.']",dataFile,"deliveriesAgainstOrders","termType"+v);
        }
        java.util.List<WebElement> termRowList = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Term * Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < 6; i++) {
            enterData(termRowList.get(i), dataFile, "deliveriesAgainstOrders", "term"+i);
            enterData(commentsRowList.get(i), dataFile, "deliveriesAgainstOrders", "comments"+i);
        }
    }
}