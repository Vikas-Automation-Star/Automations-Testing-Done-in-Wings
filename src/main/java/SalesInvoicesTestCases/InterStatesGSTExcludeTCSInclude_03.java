package SalesInvoicesTestCases;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;

public class InterStatesGSTExcludeTCSInclude_03 extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    double exchangeRate,tcsAssesibleValue,tcsRate,netAmountTextt;
    double quantity,mrp,unitRate,grossAmount,partyDiscountValue,voucherDiscountValue,grossMinusDiscount,netAmount,gstValue,cessValue,taxableValue,taxableAmountCalculated;

    public InterStatesGSTExcludeTCSInclude_03(WindowsDriver driver, String file) {
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
        invoiceTypeWhenRegister();
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectAndValidateData(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Port Code']");
        inputTextWithValidation( "xpath", "//Edit[@Name='Port Code']", common.getData(dataFile,"portCode"));
//        exchangeRate=Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='exchangeRate'").getText());
//        System.out.println("exchange rate ;-"+exchangeRate);
//        common.clickElement("xpath", "//Edit[@Name='Remarks']");
//        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");

//        //F3-Items
            for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "productCount")); i++) {
            addProduct(i);
        }
        netAmountTextt=Double.parseDouble(common.findWebElement("xpath","//Edit[@AutomationId='NetAmount']").getText().replace(",",""));
        System.out.println("Net Amount :- "+netAmountTextt);
        validateIGSTAmountTabIsEmpty();
        validateCESSAmountTabIsEmpty();
        //Tcs calculations
        tcsCalculations(dataFile,"code","amount","rowCount",netAmountTextt);


//        common.clickElement("xpath","//TabItem[contains(@Name,'TCS')]");
//        tcsAssesibleValue =Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Assessable Value']").getText().replace(",",""));
//        System.out.println("tcsAssesible value :- "+tcsAssesibleValue);
//        Assert.assertEquals(netAmountTextt,tcsAssesibleValue,"net Amount should be match with tcsAssesibleValue");
//        tcsRate =Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='TCS Rate']").getText());
//        System.out.println("tcsRate :- "+tcsRate);
//        double tcsAmount = Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='TCS Amount']").getText());
//        System.out.println("tcs Amount :-"+tcsAmount);
//        double expectedTCSAmount = tcsAssesibleValue*tcsRate/100;
//        System.out.println("ActualAmount :- "+tcsAmount+"ExpectedAmount :- "+ expectedTCSAmount);
//        Assert.assertEquals(tcsAmount, expectedTCSAmount,"Calculations mismatch");
//        double tcsCompanyCurrency = Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='TCS Amount In Company Currency']").getText());
//        System.out.println("tcsAmount in company Currency :-"+tcsCompanyCurrency);
//        double expectedCompanyCurrencyAmount=tcsCompanyCurrency*exchangeRate;
//        Assert.assertEquals(tcsCompanyCurrency,expectedCompanyCurrencyAmount,"should match with company currency");

        //verify SUMMARY TAB
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
        sliderHandle();

    }

}


