package TestCases;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class TC_05 extends Transaction {
        WindowsDriver driver;
        Common common;
        String dataFile;
        double quantity;
        double mrp, grossAmount, unitRate, voucherDiscountValue, partyDiscountValue, netAmount, grossMinusDiscount, gstValue, cessValue, taxableValue, taxableAmountCalculated;


        public TC_05(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public void testCase5() throws InterruptedException, IOException, ParseException, AWTException {
            navigateToSalesInvoiceMenu();
            Thread.sleep(1000);
            lastTransactionName();
            //branch selection
            common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
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


            common.inputText("xpath", "//Edit[@Name='Invoice Type']", common.getData(dataFile, "invoice"));
            Thread.sleep(1000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
//        super.validateElements("xpath","//Edit[@Name='Invoice Type']", common.getData(dataFile,"invoice"));
            common.clickElement("xpath", "//Edit[@Name='Price List']");
            selectAndValidateData(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
            common.clickElement("xpath", "//Edit[@Name='Port Code']");
            selectOptionalMaster(common.getData(dataFile, "portCode"), "xpath", "//Edit[@Name='Port Code']");
            common.clickElement("xpath", "//Edit[@Name='Remarks']");
            selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
            //F3-Items

            for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "productCount")); i++) {
                addProduct(i);
            }

            common.clickElement("xpath", "//TabItem[@Name='  F8 CGST  ']");
            WebElement tax = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']");
            String value = tax.getText();
            if (value == (null) || "(null)".equals(value)) {
                Assert.fail("CGST field is  empty");
            }

            common.clickElement("xpath", "//TabItem[@Name='  F9 SGST  ']");
            WebElement tax1 = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']");
            String value1 = tax1.getText();
            if (value1 == (null) || "(null)".equals(value1)) {
                Assert.fail("SGST field is empty");

            }
            common.clickElement("xpath", "//TabItem[@Name='  F11 IGST  ']");
            WebElement IGSTtax = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']");
            String value2 = IGSTtax.getText();
            if (value2 == (null) || "(null)".equals(value2)) {
                Assert.fail("IGST field is empty");
            }

            //verify all the fields in summary are fetching data
            navigateToOtherInfoTab();
            for (int j = 0; j < 4; j++) {
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_RIGHT);
            }

            WebElement Quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity']");
            String quantityText = Quantity.getText();
            if ((quantityText == (null) || "(null)".equals(quantityText))) {
                Assert.fail("Quantity field is empty");
            }

            WebElement grossAmount = common.findWebElement("xpath", "//Edit[@Name='Gross Amount']");
            String grossAmountText1 = grossAmount.getText();
            if ((grossAmountText1 == (null) || "(null)".equals(grossAmountText1))) {
                Assert.fail("grossAmount field is empty");
            }

            WebElement netAmount = common.findWebElement("xpath", "//Edit[@Name='Net Amount']");
            String netAmountText1 = netAmount.getText();
            if ((netAmountText1 == (null) || "(null)".equals(netAmountText1))) {
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
            mrp = Double.parseDouble(element.getText().replace(",",""));
            System.out.println("mrp:-" + mrp);

            WebElement mrpAmount = common.findWebElement("xpath", "//Edit[@Name='MRP Amount Row " + i + ", Not sorted.']");
            String actualMrpAmountText = mrpAmount.getText().replace(",", "");
            double actualMrpAmount = Double.parseDouble(actualMrpAmountText);

            double expectedMrpAmount = quantity * mrp;
            System.out.println("actual:- " + actualMrpAmount + " -expectedMrp-" + expectedMrpAmount);
            Assert.assertEquals(actualMrpAmount, expectedMrpAmount, "Mismatch in MRP Amount");

            int offset = 450;
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", offset, 0);

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

            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", offset, 0);


            grossMinusDiscount = (grossAmount - (voucherDiscountValue + partyDiscountValue));

            System.out.println("gross-disc is: " + grossMinusDiscount);


            WebElement net = common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']");
            String netAmountText = net.getText().replace(",", "");
            netAmount = Double.parseDouble(netAmountText);
            System.out.println("Net Amount:- " + netAmount);
            Assert.assertEquals(grossMinusDiscount, netAmount);
        }
    }