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

public class InterStatesGSTIncludingTCSIncluding_04 extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;
    double quantity, mrp, unitRate, grossAmount, calculateNet, netAmountTextt, netAmount, gstValue, cessValue, taxableValue, taxableAmountCalculated,
            expectedIGST, cessPrecentage, gstPercentage, expectedCESS, totalGST, actualGST;


    public InterStatesGSTIncludingTCSIncluding_04(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void interStatesGSTIncludingTCSIncluding_04() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToSalesInvoiceMenu();
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        //        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectAndValidateData(common.getData(dataFile, "location"), "xpath", "//Edit[@Name='Location *']");
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
        inputTextWithValidation("xpath", "//Edit[@Name='Port Code']", common.getData(dataFile, "portCode"));
//        common.clickElement("xpath", "//Edit[@Name='Remarks']");
//        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");

//        //F3-Items
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "productCount")); i++) {
            addProduct(i);
        }
        netAmountTextt = Double.parseDouble(common.findWebElement("xpath", "//Edit[@AutomationId='NetAmount']").getText().replace(",", ""));
        System.out.println("Net Amount :- " + netAmountTextt);
        tcsCalculations(netAmountTextt);
        navigateToBillsPayablesTab();
        common.deleteInvalidRows();
        navigateToSummaryTab();
        Thread.sleep(2000);
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        tcsTaxableValuePresentInSummary();
        tcsAmountPresentInSummary();
        netAmountPresentInSummary();
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

    }

    public void addProduct(int i) throws IOException, ParseException, InterruptedException, AWTException {
        if (common.getData(dataFile, "productType" + i).equals("general")) {
            generalProduct(dataFile, "productCode" + i, "quantity" + i, i);
        } else if (common.getData(dataFile, "productType" + i).equals("multiBatch")) {
            multiBatchProduct(dataFile, "productCode" + i, "quantity" + i, i);
        } else if (common.getData(dataFile, "productType" + i).equals("serial")) {
            serialNumberProduct(dataFile, "productCode" + i, i);
        }

        quantity = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']").getText());
        System.out.println("quantity" + quantity);

        mrp = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='MRP Row " + i + ", Not sorted.']").getText().replace(",", ""));
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

        grossAmount = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']").getText().replace(",", ""));
        System.out.println("gross Amount:- " + grossAmount);

        double grossExpected = unitRate * quantity;
        System.out.println("gross expected:-" + grossExpected);
        Assert.assertEquals(grossAmount, grossExpected, "Mismatch in Gross Amount");
        enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile, "HSNCode");

        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true; // Set the flag to true after clicking
        }

        WebElement element1 = common.findWebElement("xpath", "//Edit[@Name='GST Product Category Row " + i + ", Not sorted.']");

        if (!(element1 == null) && common.getData(dataFile, "priceList").equals("AT_Exclusive Sales Price List 1")) {
            //GSt
            DecimalFormat decimalFormat = new DecimalFormat("#.###");

            taxableValue = quantity * unitRate;
            taxableValue = Double.parseDouble(decimalFormat.format(taxableValue));
            System.out.println("Gross Amount && taxable Value " + taxableValue);
            Thread.sleep(2000);

            taxableAmountCalculated = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']").getText().replace(",", ""));
            System.out.println("Actual taxableValue: " + taxableAmountCalculated);
            Assert.assertEquals(taxableAmountCalculated, taxableValue);

            Thread.sleep(1000);
            gstPercentage = StringUtil.extractNumber(common.findWebElement("xpath", "//Edit[@Name='GST Product Category Row " + i + ", Not sorted.']").getText());
            System.out.println("GST Percentage" + gstPercentage);

            cessPrecentage = StringUtil.extractNumber(common.findWebElement("xpath", "//Edit[@Name='CESS Product Category Row " + i + ", Not sorted.']").getText());
            System.out.println("CESS Percentage" + gstPercentage);

            expectedIGST = Double.parseDouble(decimalFormat.format(taxableAmountCalculated * gstPercentage / 100));
            System.out.println("expected GSTAmount: " + expectedIGST);

            expectedCESS = Double.parseDouble(decimalFormat.format(taxableAmountCalculated * cessPrecentage / 100));
            System.out.println("expected CESSAmount: " + expectedCESS);

            gstValue = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']").getText().replace(",", ""));
            System.out.println("ActualGst amount:- " + gstValue);
            Assert.assertEquals(gstValue, expectedIGST, "calculations mismatch");

            cessValue = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']").getText().replace(",", ""));
            System.out.println("ActualCess amount:- " + cessValue);
            Assert.assertEquals(cessValue, expectedCESS, "calculations mismatch");
            totalGST = gstValue + cessValue;
            System.out.println("total:-" + totalGST);


            actualGST = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']").getText().replace(",", ""));
            System.out.println("actual GST :" + actualGST);
            Assert.assertEquals(actualGST, totalGST, "calculations mismatch");

            calculateNet = taxableValue + totalGST;
            netAmount = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']").getText().replace(",", ""));
            Assert.assertEquals(calculateNet, netAmount, "check calculations once");
            System.out.println("Net Amount:- " + netAmount);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 450, 0);
        } else {
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
