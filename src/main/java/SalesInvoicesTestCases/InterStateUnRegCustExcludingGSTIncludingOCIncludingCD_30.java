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

public class InterStateUnRegCustExcludingGSTIncludingOCIncludingCD_30 extends Transaction {
        WindowsDriver driver;
        Common common;
        String dataFile;
        double mrp, grossAmount, unitRate, quantity, voucherDiscountValue, partyDiscountValue, netAmount, grossMinusDiscount;

        public InterStateUnRegCustExcludingGSTIncludingOCIncludingCD_30(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public void testCase30() throws InterruptedException, IOException, ParseException, AWTException {
            navigateToSalesInvoiceMenu();
            Thread.sleep(1000);
            String oldVoucherID =oldTTransactionID();
            System.out.println("oldID: "+ oldVoucherID);
            common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
            selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
            common.clickElement("xpath", "//Edit[@Name='Branch *']");
            selectAndValidateDataNew(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
            common.clickElement("xpath", "//Edit[@Name='Location *']");
            enterInput("xpath", "//Edit[@Name='Cash/Party Code']",dataFile, "partyCode");
            Thread.sleep(2500);
            gstTransactionType("Unregistered Dealers");
            Thread.sleep(1000);
            common.clickElement("xpath", "//Edit[@Name='Sales A/c Code']");
            selectAndValidateDataNew(common.getData(dataFile, "salesAccountCode"), "xpath", "//Edit[@Name='Sales A/c Code']");
            common.clickElement("xpath","//CheckBox[@Name='Apply TCS']");

            common.inputText("xpath", "//Edit[@Name='Invoice Type']", common.getData(dataFile, "invoice"));
            Thread.sleep(1000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            common.clickElement("xpath", "//Edit[@Name='Price List']");
            selectAndValidateDataNew(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
            generalInfoSliderHandle(800);
            common.clickElement("xpath", "//Edit[@Name='Port Code']");
            selectOptionalMaster(common.getData(dataFile, "portCode"), "xpath", "//Edit[@Name='Port Code']");
            common.clickElement("xpath", "//Edit[@Name='Remarks']");
            selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
            generalInfoSliderHandle(-500);

            for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "productCount")); i++) {
                addProduct(i);
            }

            OtherChargesCalculations(dataFile, "accountCode", "amount", "otherChargesCount");
            //charges and deductions
            chargesAndDeductionsCalculations(dataFile, "type", "accountCode", "amount", "rowCount");

            validateCGSTAmountTabIsEmpty();
            validateSGSTAmountTabIsEmpty();
            validateIGSTAmountTabIsNotEmpty();
            validateCESSAmountTabIsNotEmpty();

            navigateToBatchDetailsTab();
            navigateToBillsPayablesTab();
            common.deleteInvalidRows();

            navigateToPaytymTab();
            for (int j = 0; j < 6; j++) {
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_RIGHT);
            }
            //validate summary
            quantityPresentInSummary();
            grossAmountPresentInSummary();
            grossMinusDiscountPresentInSummary();
            totalValuePresentInSummary();
            totalValueInCompanyCurrenyPresentInSummary();
            receivableAmountPresentInSummary();

            //save
            transactionSave();
            String newVoucherID =newTransactionID(oldVoucherID).replace(" ","");
            System.out.println("newID: "+newVoucherID);
            Assert.assertNotEquals(newVoucherID, oldVoucherID,"No New Transactions found");
            Thread.sleep(1000);
            common.clickElement("name", "Sales");
            common.clickElement("name", "Invoices");
            common.clickElement("name", "Sales Book");
            Thread.sleep(1000);
            common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
            Thread.sleep(15000);
            verifyReport(newVoucherID,dataFile);
            deleteSingleTransaction(newVoucherID);
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

            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 450, 0);

            grossMinusDiscount = (grossAmount - (voucherDiscountValue + partyDiscountValue));

            System.out.println("gross-disc is: " + grossMinusDiscount);

            WebElement net = common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']");
            String netAmountText = net.getText().replace(",", "");
            netAmount = Double.parseDouble(netAmountText);
            System.out.println("Net Amount:- " + netAmount);
            Assert.assertEquals(grossMinusDiscount, netAmount);
        }
    }