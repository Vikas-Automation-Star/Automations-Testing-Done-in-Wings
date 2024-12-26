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
    double quantity;
    boolean gstAmountClicked = false;
    double mrp,grossAmount,unitRate,voucherDiscountValue,partyDiscountValue,netAmount,grossMinusDiscount,gstValue,cessValue,taxableValue,taxableAmountCalculated,expectedGSTAmount;

    public InterSatesGSTIncludingTCSIncludingOCIncludingCDIncluding_07(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void interSatesGSTIncludingTCSIncludingOCIncludingCDIncluding_07() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToSalesInvoiceMenu();
        Thread.sleep(1000);
        lastTransactionName();
        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Cash/Party Code']");
        selectAndValidateDataNew(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Cash/Party Code']");
        Thread.sleep(2500);
        gstTransactionType("Registered Dealers");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Sales A/c Code']");
        selectAndValidateData(common.getData(dataFile, "salesAccountCode"), "xpath", "//Edit[@Name='Sales A/c Code']");
//        common.clickElement("xpath", "//CheckBox[@Name='Apply TCS']");
        common.clickElement("xpath", "//Edit[@Name='TCS Trans Nature']");
        selectAndValidateData(common.getData(dataFile, "tcsNature"), "xpath", "//Edit[@Name='TCS Trans Nature']");
        Thread.sleep(2000);
        common.sliderHandling("xpath", "//ScrollBar[@Name='Horizontal']/Thumb[@Name='Position']", 500, 0);
        invoiceTypeWhenRegister();
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectAndValidateData(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Port Code']");
        inputTextWithValidation( "xpath", "//Edit[@Name='Port Code']", common.getData(dataFile,"portCode"));
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectAndValidateData(common.getData(dataFile,"priceList"),"xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath","//Edit[@Name='Port Code']");
        selectOptionalMaster(common.getData(dataFile,"portCode"),"xpath","//Edit[@Name='Port Code']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");

        //F3-Items
//        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"productCount")); i++) {
//            addProduct(i);
//        }
        navigateToChargesAndDeductionsTab();
        chargesAndDeductionsCalculations(dataFile,"chargesOrDeductions","chargesOrDeductionsCode","amount","rowCount");
//        tcsCalculations(dataFile,"otherChargesCode","amount","iterations");

        //calculating TCS
//        DecimalFormat decimalFormat=new DecimalFormat("#.###");
//        totalNetValue= Double.parseDouble(common.findWebElement("xpath","//Edit[@AutomationId='NetAmount']").getText().replace(",",""));
//        System.out.println("totalNetAmount :- "+totalNetValue);
//        common.clickElement("xpath","//TabItem[contains(@Name,'TCS')]");
//        tcsAssesibleValue =Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Assessable Value']").getText().replace(",",""));
//        System.out.println("assesibleValue :- "+tcsAssesibleValue);
//        Assert.assertEquals(tcsAssesibleValue,totalNetValue,"should be match");
//
//        tcsRate = Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='TCS Rate']").getText());
//        tcsCompanyCurrency =Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='TCS Amount']").getText());
//        double expectedTCS = tcsAssesibleValue * tcsRate / 100;
//        System.out.println("ActualAmount :- "+tcsCompanyCurrency+" ExpectedAmount :- "+decimalFormat.format(expectedTCS));
//        Assert.assertEquals(decimalFormat.format(tcsCompanyCurrency),decimalFormat.format(expectedTCS),"Calculations mismatch");
    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException{
        if ( common.getData(dataFile,"productType"+i).equals("general")) {
            generalProduct(dataFile,"productCode"+i,"quantity"+i,i);
        }
        else if (common.getData(dataFile,"productType"+i).equals("multiBatch")) {
            multiBatchProduct(dataFile,"productCode"+i,"quantity"+i,i);
        }
        else if (common.getData(dataFile,"productType"+i).equals("serial")) {
            serialNumberProduct(dataFile,"productCode"+i,i);
        }

        quantity= Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Quantity Row "+i+", Not sorted.']").getText());

        WebElement element= common.findWebElement("xpath","//Edit[@Name='MRP Row "+i+", Not sorted.']");
        String mrpGivenAmount= element.getText().replace(",","");
        mrp= Double.parseDouble(mrpGivenAmount);
        System.out.println("mrp:-"+mrp);

        WebElement mrpAmount = common.findWebElement("xpath", "//Edit[@Name='MRP Amount Row "+i+", Not sorted.']");
        String actualMrpAmountText = mrpAmount.getText().replace(",", "");
        double actualMrpAmount = Double.parseDouble(actualMrpAmountText);

        double expectedMrpAmount = quantity * mrp;
        System.out.println("actual:- "+actualMrpAmount+" -expectedMrp-"+expectedMrpAmount);
        Assert.assertEquals(actualMrpAmount, expectedMrpAmount, "Mismatch in MRP Amount");

        int offset = 450;
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",offset,0);

        unitRate= Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Unit Rate Row "+i+", Not sorted.']").getText());
        System.out.println("unitRate:-"+unitRate);

        WebElement gross = common.findWebElement("xpath", "//Edit[@Name='Gross Amount Row "+i+", Not sorted.']");
        String grossAmountText = gross.getText().replace(",", "");
        grossAmount = Double.parseDouble(grossAmountText);
        System.out.println("gross Amount:- "+ grossAmount);

        double grossExpected=unitRate * quantity;
        System.out.println("gross expected:-"+grossExpected);
        Assert.assertEquals(grossAmount,grossExpected,"Mismatch in Gross Amount");

        //discount
        super.enterData("xpath","//Edit[@Name='Voucher Disc % Row "+i+", Not sorted.']", dataFile,"voucherDiscount"+i);

        WebElement voucher = common.findWebElement("xpath","//Edit[@Name='Voucher Disc Row "+i+", Not sorted.']");
        String voucherText = voucher.getText().replace(",", "");
        voucherDiscountValue = Double.parseDouble(voucherText);
        System.out.println("voucher Amount Value:- "+ voucherDiscountValue);

        super.enterData("xpath","//Edit[@Name='Party Disc % Row "+i+", Not sorted.']", dataFile,"partyDiscount"+i);

        WebElement partyDisc= common.findWebElement("xpath","//Edit[@Name='Party Disc Row "+i+", Not sorted.']");
        String partyDiscText=partyDisc.getText().replace(",","");
        partyDiscountValue= Double.parseDouble(partyDiscText);
        System.out.println("party Discount Value: - " + partyDiscountValue);

        enterDataAndValidate("xpath","//Edit[@Name='HSN Row "+i+", Not sorted.']",dataFile,"HSNCode");

        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",offset,0);

        grossMinusDiscount=(grossAmount-(voucherDiscountValue+partyDiscountValue));

        System.out.println("gross-disc is: " + grossMinusDiscount);
        if(common.getData(dataFile,"priceList").contains("Inclusive")) {
            netAmount = Double.parseDouble((common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']").getText().replace(",","")));
            System.out.println("Net Amount:- " + netAmount);
            Assert.assertEquals(grossMinusDiscount, netAmount,"mis match netAmountCalculations");
        }
        else {
            taxableValue=grossMinusDiscount;
            System.out.println("taxable value for exclusive is:"+ taxableValue);
            taxableAmountCalculated= Double.parseDouble((common.findWebElement("xpath","//Edit[@Name='Taxable Value Row "+i+", Not sorted.']").getText().replace(",","")));
            System.out.println("taxable: "+taxableAmountCalculated);
            Assert.assertEquals(taxableAmountCalculated,taxableValue,"mis match Taxible");
        }

        //GST
        WebElement gst= common.findWebElement("xpath","//Edit[@Name='GST Product Category Row "+i+", Not sorted.']");
        gstValue= StringUtil.extractNumber(gst.getText());
        System.out.println("gst percentage:- "+gstValue);

        WebElement cess= common.findWebElement("xpath","//Edit[@Name='CESS Product Category Row "+i+", Not sorted.']");
        cessValue= StringUtil.extractNumber(cess.getText());
        System.out.println("cess percentage:- "+cessValue);

        DecimalFormat decimalFormat = new DecimalFormat("#.###");

        double totalValue=gstValue+cessValue;
        System.out.println("total:-"+ totalValue);

        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true; // Set the flag to true after clicking
        }

        double expectedIGST = Double.parseDouble(decimalFormat.format((taxableValue*gstValue)/100));
        double actualIGST= Double.parseDouble((common.findWebElement("xpath","//Edit[@Name='IGST Row "+i+", Not sorted.']").getText().replace(",","")));
        System.out.println("Actual IGST: "+actualIGST);
        System.out.println("Expected IGST: "+expectedIGST);
        Assert.assertEquals(actualIGST,expectedIGST);

        double expectedCESS = Double.parseDouble(decimalFormat.format((taxableValue*cessValue)/100));
        double actualCESS= Double.parseDouble((common.findWebElement("xpath","//Edit[@Name='CESS Row "+i+", Not sorted.']").getText().replace(",","")));
        System.out.println("Actual CESS: "+ actualCESS);
        System.out.println("Expected CESS: "+expectedCESS);
        Assert.assertEquals(actualCESS,expectedCESS);

        expectedGSTAmount= expectedIGST+expectedCESS;
        double actualGSTAmount= Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='GST Amount Row "+i+", Not sorted.']").getText().replace(",",""));
        System.out.println("expected GST Amount: "+decimalFormat.format(expectedGSTAmount));
        System.out.println("Actual GST Amount: "+decimalFormat.format(actualGSTAmount));
        Assert.assertEquals(decimalFormat.format(actualGSTAmount),decimalFormat.format(expectedGSTAmount));

    }
}
