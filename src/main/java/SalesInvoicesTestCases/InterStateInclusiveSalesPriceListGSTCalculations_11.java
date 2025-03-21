package SalesInvoicesTestCases;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.StringUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;

public class InterStateInclusiveSalesPriceListGSTCalculations_11 extends Transaction {
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;
    double mrp, grossAmount, unitRate, quantity, voucherDiscountValue, partyDiscountValue, netAmount, grossMinusDiscount, gstValue, cessValue, taxableValue, taxableAmountCalculated;


    public InterStateInclusiveSalesPriceListGSTCalculations_11(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void interStateExclusiveSalesPriceListGSTCalculations_11() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToSalesInvoiceMenu();
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        Thread.sleep(1000);
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
        generalInfoSliderHandle(-500);

        //F3-Items
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "productCount")); i++) {
            addProduct(i);
        }
        validateCGSTAmountTabIsEmpty();
        validateSGSTAmountTabIsEmpty();
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        //verify all the fields in summary are fetching data
        navigateToBillsPayablesTab();
        common.deleteInvalidRows();
        navigateToSummaryTab();
        Thread.sleep(2000);
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
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
        deleteSingleTransaction(newVoucherID);
        closeReport("Sales Book");
        closeTransaction("Sales Invoices");
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
        mrp = Double.parseDouble(element.getText().replace(",", ""));
        System.out.println("mrp:-" + mrp);

        WebElement mrpAmount = common.findWebElement("xpath", "//Edit[@Name='MRP Amount Row " + i + ", Not sorted.']");
        String actualMrpAmountText = mrpAmount.getText().replace(",", "");
        double actualMrpAmount = Double.parseDouble(actualMrpAmountText);

        double expectedMrpAmount = quantity * mrp;
        System.out.println("actual:- " + actualMrpAmount + " -expectedMrp-" + expectedMrpAmount);
        Assert.assertEquals(actualMrpAmount, expectedMrpAmount, "Mismatch in MRP Amount");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 800, 0);

        unitRate = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Unit Rate Row " + i + ", Not sorted.']").getText());
        System.out.println("unitRate:-" + unitRate);

        WebElement gross = common.findWebElement("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']");
        String grossAmountText = gross.getText().replace(",", "");
        grossAmount = Double.parseDouble(grossAmountText);
        System.out.println("gross Amount:- " + grossAmount);

        double grossExpected = unitRate * quantity;
        System.out.println("gross expected:-" + grossExpected);
        Assert.assertEquals(grossAmount, grossExpected, "Mismatch in Gross Amount");
        enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile, "HSNCode");

        //GST
        WebElement gst = common.findWebElement("xpath", "//Edit[@Name='GST Product Category Row " + i + ", Not sorted.']");
        gstValue = StringUtil.extractNumber(gst.getText());
        System.out.println("gst percentage:- " + gstValue);

        WebElement cess = common.findWebElement("xpath", "//Edit[@Name='CESS Product Category Row " + i + ", Not sorted.']");
        cessValue = StringUtil.extractNumber(cess.getText());
        System.out.println("cess percentage:- " + cessValue);
        double totalValue = gstValue + cessValue;
        System.out.println("total:-" + totalValue);
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        System.out.println("decimal formate : "+decimalFormat);

        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }

        String gstTransType = common.findWebElement("xpath", "//Edit[@Name='GST Trans Type *']").getText().trim();
        if (gstTransType.equalsIgnoreCase("Inter State Sales to Registered Dealers")) {
            double actualIGST = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']").getText().replace(",", ""));
            System.out.println("Actual IGST: " + actualIGST);
            double actualCESS = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']").getText().replace(",", ""));
            System.out.println("Actual CESS: " + actualCESS);
            double totalGSTAmount = actualIGST + actualCESS;
            System.out.println("total GSTAmount: "+totalGSTAmount);
            double inclisiveTaxibleValue = Double.parseDouble(decimalFormat.format(grossAmount - totalGSTAmount));
            System.out.println("inclusive Taxible Value :- " + inclisiveTaxibleValue);

            double expectedIGST = Double.parseDouble(decimalFormat.format((inclisiveTaxibleValue * gstValue) / 100));
            System.out.println("Expected IGST: " + expectedIGST);
//            Assert.assertEquals(actualIGST, common.getData(dataFile,"expectedIGST"), "calculations MisMatch");
            double expectedCESS = Double.parseDouble(decimalFormat.format((inclisiveTaxibleValue * cessValue) / 100));
            System.out.println("Expected CESS: " + expectedCESS);
//            Assert.assertEquals(actualCESS, expectedCESS, "calculations MisMatch");

            double expectedGSTAmount = expectedIGST + expectedCESS;
            double actualGSTAmount = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']").getText().replace(",", ""));
            System.out.println("Expected GST Amount: " + decimalFormat.format(expectedGSTAmount));
            System.out.println("Actual GST Amount: " + decimalFormat.format(actualGSTAmount));
//            Assert.assertEquals(decimalFormat.format(actualGSTAmount), decimalFormat.format(expectedGSTAmount), "calculations MisMatch");

        } else if (gstTransType.equalsIgnoreCase("Intra State Sales to Registered Dealers")) {
            double expectedCGST = Double.parseDouble(decimalFormat.format(((taxableValue * gstValue) / 100) / 2));
            double actualCGST = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='CGST Row " + i + ", Not sorted.']").getText().replace(",", ""));
            System.out.println("Actual CGST: " + actualCGST);
            System.out.println("Expected CGST: " + expectedCGST);
//            Assert.assertEquals(actualCGST, expectedCGST);

            double expectedSGST = Double.parseDouble(decimalFormat.format(((taxableValue * gstValue) / 100) / 2));
            double actualSGST = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='SGST Row " + i + ", Not sorted.']").getText().replace(",", ""));
            System.out.println("Actual SGST: " + actualSGST);
            System.out.println("Expected SGST: " + expectedSGST);
//            Assert.assertEquals(actualSGST, expectedSGST);

            double expectedCESS = Double.parseDouble(decimalFormat.format((taxableValue * cessValue) / 100));
            double actualCESS = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']").getText().replace(",", ""));
            System.out.println("Actual CESS: " + actualCESS);
            System.out.println("Expected CESS: " + expectedCESS);
//            Assert.assertEquals(actualCESS, expectedCESS);

            double expectedGSTAmount = expectedCGST + expectedSGST + expectedCESS;
            double actualGSTAmount = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']").getText().replace(",", ""));
            System.out.println("Expected GST Amount: " + decimalFormat.format(expectedGSTAmount));
            System.out.println("Actual GST Amount: " + decimalFormat.format(actualGSTAmount));
//            Assert.assertEquals(decimalFormat.format(actualGSTAmount), decimalFormat.format(expectedGSTAmount));
        } else {
            throw new IllegalArgumentException("Invalid GST Trans Type: " + gstTransType);
        }

        if (common.getData(dataFile, "priceList").contains("Inclusive")) {
            netAmount = Double.parseDouble((common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']").getText().replace(",", "")));
            System.out.println("Net Amount:- " + netAmount);
            Assert.assertEquals(netAmount, grossAmount, "calculations mismatch");
        } else if (common.getData(dataFile, "priceList").contains("Exclusive")) {
            taxableValue = grossAmount;
            System.out.println("taxable value for exclusive is:" + taxableValue);
            taxableAmountCalculated = Double.parseDouble((common.findWebElement("xpath", "//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']").getText().replace(",", "")));
            System.out.println("taxable: " + taxableAmountCalculated);
            Assert.assertEquals(taxableAmountCalculated, taxableValue);
        }

    }
}
