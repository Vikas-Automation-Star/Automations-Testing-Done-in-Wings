package TestCases;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;

public class SalesInvoiceExclussiveGstAndInclusiveTCS extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesInvoiceExclussiveGstAndInclusiveTCS(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void salesInvoiceExclussiveGstAndInclusiveTcs() throws InterruptedException, IOException, ParseException, AWTException {
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
        invoiceType();
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectAndValidateData(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Port Code']");
        selectOptionalMaster(common.getData(dataFile, "portCode"), "xpath", "//Edit[@Name='Port Code']");
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
//        //F3-Items
        //for product (EXCLUSIVE GST)
        for (int i = 0; i <Integer.parseInt(common.getData(dataFile,"rows")); i++) {
            if ( common.getData(dataFile,"addProduct").equals("AT_Product 1")) {
                generalProduct(dataFile,"addProduct","ProductQuantity",i);
            }
            if (common.getData(dataFile,"multiBatch").equals("AT_Multi batch Product 1")) {
                multiBatchProduct(dataFile,"multiBatch","multiBatchQuantity",i);
            }
            if (common.getData(dataFile,"serialBatch").equals("AT_Product With SN 1")) {
                serialNumberProduct(dataFile,"serialBatch",i);
            }
        }
        //verifying data not present in tabs
        validateCGSTAmountTabWhenNull();
        validateSGSTAmountTabWhenNull();
        validateIGSTAmountTabWhenNull();
        validateToCESSAmountTabWhenNull();
        validateTcsAmount();
        //verify all the fields in summary are fetching data
        //verify SUMMARY TAB
        navigateToSummaryTab();
        WebElement Quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity']");
        String quantityText = Quantity.getText();
        if ((quantityText == (null) || "(null)".equals(quantityText))) {
            Assert.fail("Quantity field is empty");
        }

        WebElement grossAmount = common.findWebElement("xpath", "//Edit[@Name='Gross Amount']");
        String grossAmountText = grossAmount.getText();
        if ((grossAmountText == (null) || "(null)".equals(grossAmountText))) {
            Assert.fail("grossAmount field is empty");
        }

        WebElement netAmount = common.findWebElement("xpath", "//Edit[@Name='Net Amount']");
        String netAmountText = netAmount.getText();
        if ((netAmountText == (null) || "(null)".equals(netAmountText))) {
            Assert.fail("netAmount field is empty");
        }

        WebElement totalValue = common.findWebElement("xpath", "//Edit[@Name='Total Value']");
        String totalValueText = totalValue.getText();
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

//        transactionSave();
//        lastTransactionName();
    }
}
