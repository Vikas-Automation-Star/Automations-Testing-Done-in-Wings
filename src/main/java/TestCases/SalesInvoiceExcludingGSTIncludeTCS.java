package TestCases;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;

public class SalesInvoiceExcludingGSTIncludeTCS extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    double tcsAssesibleValue,tcsRate;
    double quantity,mrp,unitRate,grossAmount,partyDiscountValue,voucherDiscountValue,grossMinusDiscount,netAmount,gstValue,cessValue,taxableValue,taxableAmountCalculated;

    public SalesInvoiceExcludingGSTIncludeTCS(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void salesInvoiceExcludeGstAndIncludeTcs() throws InterruptedException, IOException, ParseException, AWTException,NumberFormatException {
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
        //for product (EXCLUSIVE GST)

        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "productCount")); i++) {
            addProduct(i);
        }

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
        validateCGSTAmountTabWhenNull();
        validateSGSTAmountTabWhenNull();
        validateToCESSAmountTabWhenNull();
        //Tcs calculations
        common.clickElement("xpath","//TabItem[contains(@Name,'TCS')]");
        common.clickElement("xpath","//TabItem[contains(@Name,'TCS')]");
        WebElement assessibleValueAmount =common.findWebElement("xpath","//Edit[@Name='Assessable Value']");
        tcsAssesibleValue = Double.parseDouble(assessibleValueAmount.getText().trim().replace(",",""));
        WebElement tcsPercent =common.findWebElement("xpath","//Edit[@Name='TCS Rate']");
        tcsRate = Double.parseDouble(tcsPercent.getText().trim());
        WebElement tcsCompanyCurrency =common.findWebElement("xpath","//Edit[@Name='TCS Amount In Company Currency']");
        double tcsAmountInCompnayCurrency = Double.parseDouble(tcsCompanyCurrency.getText().trim());
        double expectedTCS = tcsAssesibleValue*tcsRate/100;
        System.out.println("ActualAmount"+tcsAmountInCompnayCurrency+"ExpectedAmount"+expectedTCS);
        Assert.assertEquals(tcsAmountInCompnayCurrency,expectedTCS,"Calculations mismatch");

        //verify all the fields in summary are fetching data
        //verify SUMMARY TAB
        navigateToSummaryTab();
        WebElement Quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity']");
        String quantityText = Quantity.getText();
        if ((quantityText == (null) || "(null)".equals(quantityText))) {
            Assert.fail("Quantity field is empty");
        }

        WebElement grossAmount = common.findWebElement("xpath", "//Edit[@Name='Gross Amount']");
        String grossAmoun = grossAmount.getText();
        if ((grossAmoun == (null) || "(null)".equals(grossAmoun))) {
            Assert.fail("grossAmount field is empty");
        }

        WebElement netAmount = common.findWebElement("xpath", "//Edit[@Name='Net Amount']");
        String netAmountText1 = netAmount.getText();
        if ((netAmountText1 == (null) || "(null)".equals(netAmountText1))) {
            Assert.fail("netAmount field is empty");
        }

        WebElement totalValuee = common.findWebElement("xpath", "//Edit[@Name='Total Value']");
        String totalValueText = totalValuee.getText();;
        if (totalValueText == (null) || "(null)".equals(totalValueText)) {
            Assert.fail("Total value field is Empty");
        }

        WebElement totalValueCompanyCurreny = common.findWebElement("xpath", "//Edit[@Name='Total Value In Company Currency']");
        String totalValuecurrencyText = totalValueCompanyCurreny.getText();
        if (totalValuecurrencyText == (null) || "(null)".equals(totalValuecurrencyText)) {
            Assert.fail("Total value in Company Curreny field is Empty");
        }

        WebElement receivableAmount = common.findWebElement("xpath", "//Edit[@Name='Receivable Amount']");
        String receivableAmountText = receivableAmount.getText();
        if (receivableAmountText == (null) || "(null)".equals(receivableAmountText)) {
            Assert.fail("Receivable Amount field is empty");
        }

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

        WebElement element= common.findWebElement("xpath","//Edit[@Name='MRP Row "+i+", Not sorted.']");
        mrp= Double.parseDouble(element.getText().replace(",",""));
        System.out.println("mrp:-"+mrp);

        WebElement mrpAmount = common.findWebElement("xpath", "//Edit[@Name='MRP Amount Row "+i+", Not sorted.']");
        String actualMrpAmountText = mrpAmount.getText().replace(",", "");
        double actualMrpAmount = Double.parseDouble(actualMrpAmountText);

        double expectedMrpAmount = quantity * mrp;
        System.out.println("actual:- "+actualMrpAmount+" -expectedMrp-"+expectedMrpAmount);
        Assert.assertEquals(actualMrpAmount, expectedMrpAmount, "Mismatch in MRP Amount");

        int offset = 500;
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

        WebElement net = common.findWebElement("xpath", "//Edit[@Name='Net Amount Row "+i+", Not sorted.']");
        String netAmountText = net.getText().replace(",", "");
        netAmount = Double.parseDouble(netAmountText);
        System.out.println("Net Amount:- "+ netAmount);
        Assert.assertEquals(grossAmount,netAmount);
    }

//        transactionSave();
//        lastTransactionName();
    }


