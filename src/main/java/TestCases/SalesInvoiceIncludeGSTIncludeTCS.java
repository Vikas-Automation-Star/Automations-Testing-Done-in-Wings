package TestCases;

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

public class SalesInvoiceIncludeGSTIncludeTCS extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    double tcsAssesibleValue,tcsRate;
    double quantity,mrp,unitRate,grossAmount,calculateNet,partyDiscountValue,voucherDiscountValue,grossMinusDiscount,netAmount,gstValue,cessValue,taxableValue,taxableAmountCalculated,
            gstPercentage,cessPrecentage,totalNetValue,tcsCompanyCurrency,expectedIGST,expectedCESS,totalGST,actualGST,netAmountText;


    public SalesInvoiceIncludeGSTIncludeTCS(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }
    public void salesInvoiceIncludeGSTAndIncludeTax() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToSalesInvoiceMenu();
        Thread.sleep(1000);
        lastTransactionName();
//        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
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
        invoiceType();
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectAndValidateData(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Port Code']");
        inputTextWithValidation( "xpath", "//Edit[@Name='Port Code']", common.getData(dataFile,"portCode"));
//        common.clickElement("xpath", "//Edit[@Name='Remarks']");
//        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");

//        //F3-Items
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "productCount")); i++) {
            addProduct(i);
        }


        totalNetValue= Double.parseDouble(common.findWebElement("xpath","//Edit[@AutomationId='NetAmount']").getText().replace(",",""));
        common.clickElement("xpath","//TabItem[contains(@Name,'TCS')]");
        tcsAssesibleValue =Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Assessable Value']").getText().replace(",",""));
        Assert.assertEquals(totalNetValue,tcsAssesibleValue,"should be match");
        tcsRate = Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='TCS Rate']").getText());
        tcsCompanyCurrency =Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='TCS Amount']").getText());
        double expectedTCS = tcsAssesibleValue * tcsRate / 100;
        System.out.println("ActualAmount"+tcsCompanyCurrency+"ExpectedAmount"+expectedTCS);
        Assert.assertEquals(tcsCompanyCurrency,expectedTCS,"Calculations mismatch");

        //discount
//        super.enterData("xpath","//Edit[@Name='Voucher Disc % Row "+i+", Not sorted.']", dataFile,"voucherDiscount");
//
//        WebElement voucher = common.findWebElement("xpath","//Edit[@Name='Voucher Disc Row "+i+", Not sorted.']");
//        String voucherText = voucher.getText().replace(",", "");
//        voucherDiscountValue = Double.parseDouble(voucherText);
//        System.out.println("voucher Amount Value:- "+ voucherDiscountValue);
//
//        super.enterData("xpath","//Edit[@Name='Party Disc % Row "+i+", Not sorted.']", dataFile,"partyDiscount");
//
//        WebElement partyDisc= common.findWebElement("xpath","//Edit[@Name='Party Disc Row "+i+", Not sorted.']");
//        String partyDiscText=partyDisc.getText().replace(",","");
//        partyDiscountValue= Double.parseDouble(partyDiscText);
//        System.out.println("party Discount Value: - " + partyDiscountValue);
//
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",offset,0);
//
//
//        grossMinusDiscount=(grossAmount-(voucherDiscountValue+partyDiscountValue));

//        System.out.println("gross-disc is: " + grossMinusDiscount);



        //GST
//        WebElement gst= common.findWebElement("xpath","//Edit[@Name='GST Product Category Row "+i+", Not sorted.']");
//        gstValue= StringUtil.extractNumber(gst.getText());
//        System.out.println("gst percentage:- "+gstValue);
//
//        WebElement cess= common.findWebElement("xpath","//Edit[@Name='CESS Product Category Row "+i+", Not sorted.']");
//        cessValue= StringUtil.extractNumber(cess.getText());
//        System.out.println("cess percentage:- "+cessValue);

//        DecimalFormat decimalFormat = new DecimalFormat("#.##");

//        double totalValue=gstValue+cessValue;
//        System.out.println("total:-"+ totalValue);
//        taxableValue=(grossMinusDiscount/(100+totalValue))*100;
//        taxableValue= Double.parseDouble(decimalFormat.format(taxableValue));

//        System.out.println("taxable value calculated:"+ taxableValue);
//        WebElement taxable= common.findWebElement("xpath","//Edit[@Name='Taxable Value Row "+i+", Not sorted.']");
//        String taxableAmount=taxable.getText().replace(",","");
//        taxableAmountCalculated = Double.parseDouble(taxableAmount);
//        System.out.println("taxable: "+taxableAmountCalculated);
//        Assert.assertEquals(taxableAmountCalculated,taxableValue);

//        double expectedGST=grossMinusDiscount-taxableAmountCalculated;
//        expectedGST= Double.parseDouble(decimalFormat.format(expectedGST));
////            expectedGST=Math.round(expectedGST * 100.0) / 100.0;
//        System.out.println("expected GST: "+ expectedGST);
//        WebElement gst1= common.findWebElement("xpath","//Edit[@Name='GST Amount Row "+i+", Not sorted.']");
//        String gstAmount= gst1.getText().replace(",","");
//        double actualGST=Double.parseDouble(gstAmount);
//        System.out.println("actual GST :"+ actualGST);
//        Assert.assertEquals(actualGST,expectedGST);









        //verifying data not present in tabs
//        validateCGSTAmountTabWhenNull();
//        validateSGSTAmountTabWhenNull();
//        validateToCESSAmountTabWhenNull();
//        //Tcs calculations
//        common.clickElement("xpath","//TabItem[contains(@Name,'TCS')]");
//        common.clickElement("xpath","//TabItem[contains(@Name,'TCS')]");
//        WebElement assessibleValueAmount =common.findWebElement("xpath","//Edit[@Name='Assessable Value']");
//        tcsAssesibleValue = Double.parseDouble(assessibleValueAmount.getText().trim().replace(",",""));
//        WebElement tcsPercent =common.findWebElement("xpath","//Edit[@Name='TCS Rate']");
//        tcsRate = Double.parseDouble(tcsPercent.getText().trim());
//        WebElement tcsCompanyCurrency =common.findWebElement("xpath","//Edit[@Name='TCS Amount In Company Currency']");
//        double tcsAmountInCompnayCurrency = Double.parseDouble(tcsCompanyCurrency.getText().trim());
//        double expectedTCS = tcsAssesibleValue*tcsRate/100;
//        System.out.println("ActualAmount"+tcsAmountInCompnayCurrency+"ExpectedAmount"+expectedTCS);
//        Assert.assertEquals(tcsAmountInCompnayCurrency,expectedTCS,"Calculations mismatch");
//
//        //verify SUMMARY TAB
//        navigateToSummaryTab();

    }

    public void addProduct(int i) throws IOException, ParseException, InterruptedException, AWTException {
        if (common.getData(dataFile, "productType" + i).equals("general")) {
            generalProduct(dataFile, "productCode" + i, "quantity" + i, i);
        } else if (common.getData(dataFile, "productType" + i).equals("multiBatch")) {
            multiBatchProduct(dataFile, "productCode" + i, "quantity" + i, i);
        } else if (common.getData(dataFile, "productType" + i).equals("serial")) {
            serialNumberProduct(dataFile, "productCode" + i, i);
        }

        quantity= Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Quantity Row "+i+", Not sorted.']").getText());
        System.out.println("quantity"+quantity);

        mrp= Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='MRP Row "+i+", Not sorted.']").getText().replace(",",""));
//        mrp= Double.parseDouble(element.getText().replace(",",""));
        System.out.println("mrp:-"+mrp);

        WebElement mrpAmount = common.findWebElement("xpath", "//Edit[@Name='MRP Amount Row "+i+", Not sorted.']");
        String actualMrpAmountText = mrpAmount.getText().replace(",", "");
        double actualMrpAmount = Double.parseDouble(actualMrpAmountText);

        double expectedMrpAmount = quantity * mrp;
        System.out.println("actual:- "+actualMrpAmount+" -expectedMrp-"+expectedMrpAmount);
        Assert.assertEquals(actualMrpAmount, expectedMrpAmount, "Mismatch in MRP Amount");

        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",450,0);

        unitRate= Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Unit Rate Row "+i+", Not sorted.']").getText());
        System.out.println("unitRate:-"+unitRate);

         grossAmount = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Gross Amount Row "+i+", Not sorted.']").getText().replace(",", ""));
//        String grossAmountText = gross.getText().replace(",", "");
//        grossAmount = Double.parseDouble(grossAmountText);
        System.out.println("gross Amount:- "+ grossAmount);

        double grossExpected=unitRate * quantity;
        System.out.println("gross expected:-"+grossExpected);
        Assert.assertEquals(grossAmount,grossExpected,"Mismatch in Gross Amount");
        enterData("xpath","//Edit[@Name='HSN Row "+i+", Not sorted.']",dataFile,"HSNCode");

        WebElement element1=common.findWebElement("xpath","//Edit[@Name='GST Product Category Row "+i+", Not sorted.']");

        if(!(element1 ==null) && common.getData(dataFile,"priceList").equals("AT_Exclusive Sales Price List 1")) {
            //GSt
            DecimalFormat decimalFormat = new DecimalFormat("#.##");

            taxableValue = quantity * unitRate;
            taxableValue = Double.parseDouble(decimalFormat.format(taxableValue));
            System.out.println("Gross Amount && taxable Value" + taxableValue);
            Thread.sleep(2000);

            taxableAmountCalculated = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']").getText().replace(",",""));
//            String taxableAmount = taxable.getText().replace(",", "");
//            taxableAmountCalculated = Double.parseDouble(taxableAmount);
            System.out.println("Actual taxableValue: " + taxableAmountCalculated);
            Assert.assertEquals(taxableAmountCalculated, taxableValue);




            Thread.sleep(1000);
            gstPercentage =StringUtil.extractNumber(common.findWebElement("xpath","//Edit[@Name='GST Product Category Row "+i+", Not sorted.']").getText());
//            gstPercentage= StringUtil.extractNumber(gstelementt.getText());
//            gstPercentage=Double.parseDouble(gstPrecent);
            System.out.println("GST Percentage"+gstPercentage);

            cessPrecentage =StringUtil.extractNumber(common.findWebElement("xpath","//Edit[@Name='CESS Product Category Row "+i+", Not sorted.']").getText());
//            cessPrecentage = StringUtil.extractNumber(cessElementt.getText());
//            cessPrecentage=Double.parseDouble(cessPrecent);
            System.out.println("CESS Percentage"+gstPercentage);

            expectedIGST =Double.parseDouble(decimalFormat.format(taxableAmountCalculated * gstPercentage/100));
//            expectedIGST = Double.parseDouble(decimalFormat.format(expectedIGST));
            System.out.println("expected GST: " + expectedIGST);

            expectedCESS = Double.parseDouble(decimalFormat.format(taxableAmountCalculated * cessPrecentage/100));
//            expectedCESS = Double.parseDouble(decimalFormat.format(expectedCESS));
            System.out.println("expected GST: " + expectedCESS);

            gstValue = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']").getText());
//            gstValue = StringUtil.extractNumber(gst.getText());
            System.out.println("gst percentage:- " + gstValue);

            cessValue =Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']").getText());
//            cessValue = StringUtil.extractNumber(cess.getText());
            System.out.println("cess percentage:- " + cessValue);
            totalGST = gstValue + cessValue;
            System.out.println("total:-" + totalGST);


            actualGST = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']").getText().replace(",", ""));
//            String gstAmount = gst1.getText().replace(",", "");
//            double actualGST = Double.parseDouble(gstAmount);
            System.out.println("actual GST :" + actualGST);
            Assert.assertEquals(actualGST, totalGST);

            calculateNet = taxableValue + totalGST;
            netAmountText = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']").getText().replace(",", ""));
//            String netAmountText = net.getText().replace(",", "");
//            netAmount = Double.parseDouble(netAmountText);
            Assert.assertEquals(calculateNet, netAmountText, "check calculations once");
            System.out.println("Net Amount:- " + netAmount);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
        }
        else{
            System.out.println("choose HSN-Code then calculate GST");
            WebElement net = common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']");
            String netAmountText = net.getText().replace(",", "");
            netAmount = Double.parseDouble(netAmountText);
            Assert.assertEquals(calculateNet, netAmount, "check calculations once");
            System.out.println("Net Amount:- " + netAmount);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
        }

    }
}
