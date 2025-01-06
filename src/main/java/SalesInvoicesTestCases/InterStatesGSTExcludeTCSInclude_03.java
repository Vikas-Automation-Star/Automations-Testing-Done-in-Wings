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
    double netAmountTextt;
    double quantity,mrp,unitRate,grossAmount,partyDiscountValue,voucherDiscountValue,grossMinusDiscount,netAmount,gstValue,cessValue,taxableValue,taxableAmountCalculated;

    public InterStatesGSTExcludeTCSInclude_03(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void interStatesGSTExcludeTCSInclude_03() throws InterruptedException, IOException, ParseException, AWTException,NumberFormatException {
        navigateToSalesInvoiceMenu();
        Thread.sleep(1000);
        lastTransactionName();
//        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectAndValidateData(common.getData( dataFile,"location"),"xpath","//Edit[@Name='Location *']");
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
        common.clickElement("xpath","//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile,"executive"),"xpath","//Edit[@Name='Executive *']" );
        selectAndValidateData(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Port Code']");
        inputTextWithValidation( "xpath", "//Edit[@Name='Port Code']", common.getData(dataFile,"portCode"));
//        common.clickElement("xpath", "//Edit[@Name='Remarks']");
//        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");

//        //F3-Items
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "productCount")); i++) {
            addProduct(i);
        }
        validateIGSTAmountTabIsEmpty();
        validateCESSAmountTabIsEmpty();
        netAmountTextt=Double.parseDouble(common.findWebElement("xpath","//Edit[@AutomationId='NetAmount']").getText().replace(",",""));
        System.out.println("Net Amount :- "+netAmountTextt);
        //Tcs calculations
        tcsCalculations(dataFile,"code","amount","rowCount",netAmountTextt);
        navigateToSummaryTab();
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        tcsTaxableValuePresentInSummary();
        tcsAmountPresentInSummary();
        netAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        receivableAmountPresentInSummary();
    }

    public void addProduct(int i) throws IOException, ParseException, InterruptedException, AWTException {
        if (common.getData(dataFile, "productType" + i).equals("general")) {
            generalProduct(dataFile, "productCode" + i, "quantity" + i,i);
        } else if (common.getData(dataFile, "productType" + i).equals("multiBatch")) {
            multiBatchProduct(dataFile, "productCode" + i, "quantity" + i,i);
        } else if (common.getData(dataFile, "productType" + i).equals("serial")) {
            serialNumberProduct(dataFile, "productCode" + i,i );
        }

        quantity= Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Quantity Row "+i+", Not sorted.']").getText());

        mrp=Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='MRP Row "+i+", Not sorted.']").getText().replace(",",""));
        System.out.println("mrp:-"+mrp);

        WebElement mrpAmount = common.findWebElement("xpath", "//Edit[@Name='MRP Amount Row "+i+", Not sorted.']");
        String actualMrpAmountText = mrpAmount.getText().replace(",", "");
        double actualMrpAmount = Double.parseDouble(actualMrpAmountText);

        double expectedMrpAmount = quantity * mrp;
        System.out.println("actual:- "+actualMrpAmount+" -expectedMrp-"+expectedMrpAmount);
        Assert.assertEquals(actualMrpAmount, expectedMrpAmount, "Mismatch in MRP Amount");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",500,0);

        unitRate= Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Unit Rate Row "+i+", Not sorted.']").getText());
        System.out.println("unitRate:-"+unitRate);

        grossAmount = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Gross Amount Row "+i+", Not sorted.']").getText().replace(",", ""));
        System.out.println("gross Amount:- "+ grossAmount);

        double grossExpected=unitRate * quantity;
        System.out.println("gross expected:-"+grossExpected);
        Assert.assertEquals(grossAmount,grossExpected,"Mismatch in Gross Amount");

        netAmount = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Net Amount Row "+i+", Not sorted.']").getText().replace(",", ""));
        System.out.println("Net Amount:- "+ netAmount);
        Assert.assertEquals(grossAmount,netAmount);
        sliderHandle();

    }

}
