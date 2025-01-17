package SalesInvoicesTestCases;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class InterStatesGSTExcludingCDIncludingOCIncluding_08 extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    double mrp, quantity, grossAmount, unitRate, netAmount;

    public InterStatesGSTExcludingCDIncludingOCIncluding_08(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void interStatesGSTExcludingCDIncludingOCIncluding_08() throws InterruptedException, IOException, ParseException, AWTException {
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
        double itemsNetValue = Double.parseDouble(common.findWebElement("xpath", "//Edit[@AutomationId='NetAmount']").getText().replace(",", ""));
        System.out.println("Items NetAmount :- " + itemsNetValue);
        chargesAndDeductionsCalculations(dataFile, "chargesOrDeductions", "chargesOrDeductionsCode", "amount", "rowCount");
        OtherChargesCalculations(dataFile, "otherChargesCode", "amount", "rowCount");
        validateCGSTAmountTabIsEmpty();
        validateSGSTAmountTabIsEmpty();
        validateCESSAmountTabIsNotEmpty();
        validateIGSTAmountTabIsNotEmpty();
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

        //verifying data not present in GST tabs
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
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 450, 0);
        WebElement net = common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']");
        String netAmountText = net.getText().replace(",", "");
        netAmount = Double.parseDouble(netAmountText);
        System.out.println("Net Amount:- " + netAmount);
        Assert.assertEquals(grossAmount, netAmount, "calculations MisMatch");
    }
}
