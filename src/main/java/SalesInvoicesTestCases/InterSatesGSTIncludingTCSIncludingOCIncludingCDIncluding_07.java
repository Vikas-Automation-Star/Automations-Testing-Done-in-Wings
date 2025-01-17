package SalesInvoicesTestCases;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.StringUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;

public class InterSatesGSTIncludingTCSIncludingOCIncludingCDIncluding_07 extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;
    double quantity,mrp, grossAmount, unitRate, voucherDiscountValue, partyDiscountValue, netAmount, grossMinusDiscount, gstValue, cessValue, taxableValue, taxableAmountCalculated, expectedGSTAmount, itemValue;

    public InterSatesGSTIncludingTCSIncludingOCIncludingCDIncluding_07(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void interSatesGSTIncludingTCSIncludingOCIncludingCDIncluding_07() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToSalesInvoiceMenu();
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"branch");
        enterInput("xpath","//Edit[@Name='Location *']",dataFile,"location");
        enterInput("xpath","//Edit[@Name='Cash/Party Code']",dataFile,"partyCode");
        Thread.sleep(2500);
        gstTransactionType("Registered Dealers");
        Thread.sleep(1000);
        enterInput("xpath","//Edit[@Name='Sales A/c Code']",dataFile,"salesAccountCode");
//        common.clickElement("xpath", "//CheckBox[@Name='Apply TCS']");
        enterInput("xpath","//Edit[@Name='TCS Trans Nature']",dataFile,"tcsNature");
        common.sliderHandling("xpath", "//ScrollBar[@Name='Horizontal']/Thumb[@Name='Position']", 500, 0);
        Thread.sleep(2000);
        invoiceTypeWhenRegister();
        enterInput("xpath","//Edit[@Name='Price List']",dataFile,"priceList");
        enterInput("xpath","//Edit[@Name='Executive *']",dataFile,"executive");
        enterInput("xpath","//Edit[@Name='Port Code']",dataFile,"portCode");
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");


        //F3-Items
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "productCount")); i++) {
            addProduct(i);
        }
        itemValue = Double.parseDouble(common.findWebElement("xpath", "//Edit[@AutomationId='NetAmount']").getText().replace(",", ""));
        System.out.println("Net Amount :- " + itemValue);
        chargesAndDeductionsCalculations(dataFile, "chargesOrDeductions", "chargesOrDeductionsCode", "amount", "rowCount");
        OtherChargesCalculations(dataFile,"otherChargesCode","amount","rowCount");
        tcsCalculations(itemValue);
        navigateToBillsPayablesTab();
        common.deleteInvalidRows();
        navigateToSummaryTab();
        Thread.sleep(1000);
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        iGSTPresentInSummary();
        cessPresentInSummary();
        netAmountPresentInSummary();
        chargesPresentInSummary();
        otherChargesPresentInSummary();
        otherChargesIGSTPresentInSummary();
        otherChargesCESSPresentInSummary();
        tcsTaxableValuePresentInSummary();
        tcsAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        receivableAmountPresentInSummary();
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID).replace(" ","");
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
        Thread.sleep(1000);
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("name", "Sales Book");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID,dataFile);
        deleteSingleTransaction(newVoucherID);
    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile, "productType" + i).equals("general")) {
            generalProduct(dataFile, "productCode" + i, "quantity" + i, i);
        } else if (common.getData(dataFile, "productType" + i).equals("multiBatch")) {
            multiBatchProduct(dataFile, "productCode" + i, "quantity" + i, i);
        } else if (common.getData(dataFile, "productType" + i).equals("serial")) {
            serialNumberProduct(dataFile, "productCode" + i, i);
        }

        quantity = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']").getText());

        WebElement element = common.findWebElement("xpath", "//Edit[@Name='MRP Row " + i + ", Not sorted.']");
        String mrpGivenAmount = element.getText().replace(",", "");
        mrp = Double.parseDouble(mrpGivenAmount);
        System.out.println("mrp:-" + mrp);

        WebElement mrpAmount = common.findWebElement("xpath", "//Edit[@Name='MRP Amount Row " + i + ", Not sorted.']");
        String actualMrpAmountText = mrpAmount.getText().replace(",", "");
        double actualMrpAmount = Double.parseDouble(actualMrpAmountText);

        double expectedMrpAmount = quantity * mrp;
        System.out.println("actual:- " + actualMrpAmount + " -expectedMrp-" + expectedMrpAmount);
        Assert.assertEquals(actualMrpAmount, expectedMrpAmount, "Mismatch in MRP Amount");

        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 450, 0);

        unitRate = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Unit Rate Row " + i + ", Not sorted.']").getText());
        System.out.println("unitRate:-" + unitRate);

        WebElement gross = common.findWebElement("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']");
        String grossAmountText = gross.getText().replace(",", "");
        grossAmount = Double.parseDouble(grossAmountText);
        System.out.println("gross Amount:- " + grossAmount);

        double grossExpected = unitRate * quantity;
        System.out.println("gross expected:-" + grossExpected);
        Assert.assertEquals(grossAmount, grossExpected, "Mismatch in Gross Amount");

        //discount
        super.enterData("xpath", "//Edit[@Name='Voucher Disc % Row " + i + ", Not sorted.']", dataFile, "voucherDiscount" + i);

        WebElement voucher = common.findWebElement("xpath", "//Edit[@Name='Voucher Disc Row " + i + ", Not sorted.']");
        String voucherText = voucher.getText().replace(",", "");
        voucherDiscountValue = Double.parseDouble(voucherText);
        System.out.println("voucher Amount Value:- " + voucherDiscountValue);

        super.enterData("xpath", "//Edit[@Name='Party Disc % Row " + i + ", Not sorted.']", dataFile, "partyDiscount" + i);

        WebElement partyDisc = common.findWebElement("xpath", "//Edit[@Name='Party Disc Row " + i + ", Not sorted.']");
        String partyDiscText = partyDisc.getText().replace(",", "");
        partyDiscountValue = Double.parseDouble(partyDiscText);
        System.out.println("party Discount Value: - " + partyDiscountValue);

        enterDataAndValidate("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile, "HSNCode");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 450, 0);

        grossMinusDiscount = (grossAmount - (voucherDiscountValue + partyDiscountValue));
        System.out.println("gross-disc is: " + grossMinusDiscount);
        if (common.getData(dataFile, "priceList").contains("Inclusive")) {
            netAmount = Double.parseDouble((common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']").getText().replace(",", "")));
            System.out.println("Net Amount:- " + netAmount);
            Assert.assertEquals(grossMinusDiscount, netAmount, "mis match netAmountCalculations");
        } else {
            taxableValue = grossMinusDiscount;
            System.out.println("taxable value for exclusive is:" + taxableValue);
            taxableAmountCalculated = Double.parseDouble((common.findWebElement("xpath", "//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']").getText().replace(",", "")));
            System.out.println("taxable: " + taxableAmountCalculated);
            Assert.assertEquals(taxableAmountCalculated, taxableValue, "mis match Taxible");
        }

        //GST
        WebElement gst = common.findWebElement("xpath", "//Edit[@Name='GST Product Category Row " + i + ", Not sorted.']");
        gstValue = StringUtil.extractNumber(gst.getText());
        System.out.println("gst percentage:- " + gstValue);

        WebElement cess = common.findWebElement("xpath", "//Edit[@Name='CESS Product Category Row " + i + ", Not sorted.']");
        cessValue = StringUtil.extractNumber(cess.getText());
        System.out.println("cess percentage:- " + cessValue);

        DecimalFormat decimalFormat = new DecimalFormat("#.###");

        double totalValue = gstValue + cessValue;
        System.out.println("total:-" + totalValue);

        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true; // Set the flag to true after clicking
        }

        double expectedIGST = Double.parseDouble(decimalFormat.format((taxableValue * gstValue) / 100));
        double actualIGST = Double.parseDouble((common.findWebElement("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']").getText().replace(",", "")));
        System.out.println("Actual IGST: " + actualIGST);
        System.out.println("Expected IGST: " + expectedIGST);
        Assert.assertEquals(actualIGST, expectedIGST);

        double expectedCESS = Double.parseDouble(decimalFormat.format((taxableValue * cessValue) / 100));
        double actualCESS = Double.parseDouble((common.findWebElement("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']").getText().replace(",", "")));
        System.out.println("Actual CESS: " + actualCESS);
        System.out.println("Expected CESS: " + expectedCESS);
        Assert.assertEquals(actualCESS, expectedCESS);

        expectedGSTAmount = expectedIGST + expectedCESS;
        double actualGSTAmount = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']").getText().replace(",", ""));
        System.out.println("expected GST Amount: " + decimalFormat.format(expectedGSTAmount));
        System.out.println("Actual GST Amount: " + decimalFormat.format(actualGSTAmount));
        Assert.assertEquals(decimalFormat.format(actualGSTAmount), decimalFormat.format(expectedGSTAmount));

    }
}
