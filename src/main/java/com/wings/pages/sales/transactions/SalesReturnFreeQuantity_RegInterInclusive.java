package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.StringUtil;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;

public class SalesReturnFreeQuantity_RegInterInclusive extends Transaction{
        WindowsDriver driver;
        Common common;
        String dataFile;
        boolean gstAmountClicked = false;
        double mrp, grossAmount, unitRate, quantity,freeQuantity,
                voucherDiscountValue, partyDiscountValue, netAmount, grossMinusDiscount, gstValue, cessValue, taxableValue, taxableAmountCalculated;

        public SalesReturnFreeQuantity_RegInterInclusive(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public void regInterInclusiveSalesReturn(String voucherID) throws InterruptedException, IOException, ParseException, AWTException {
            long testMainMethodStart=System.currentTimeMillis();
            navigateToSalesReturnMenu();
            Thread.sleep(2000);
            String oldVoucherID =oldTTransactionID();
            System.out.println("oldID: "+ oldVoucherID);
            //branch selection
            common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
            selectOptionalMaster(common.getData(dataFile,"salesInvoice", "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
            enterInput("xpath", "//Edit[@Name='Branch *']",dataFile,"salesInvoice", "branch");
            common.clickElement("xpath", "//Edit[@Name='Location *']");
            WebElement salesInvoiceNum=common.findWebElement("xpath","//Edit[@Name='Sales Invoice No']");
            salesInvoiceNum.click();
            salesInvoiceNum.sendKeys(voucherID, Keys.TAB, Time.timeStamp(),Keys.TAB);
            enterInput("xpath", "//Edit[@Name='Cash/Party Code']",dataFile, "salesInvoice","partyCode");
            Thread.sleep(2500);
            gstTransactionType("Inter State Sales Returns from Registered Dealers");
            Thread.sleep(1000);
            enterInput( "xpath", "//Edit[@Name='Sales Return A/c Code']",dataFile,"salesReturn", "salesReturnAccountCode");
            common.clickElement("xpath", "//Edit[@Name='TCS Trans Nature']");
            common.clickElement("xpath","//Edit[@Name='Batch Policy']");
            enterInput("xpath", "//Edit[@Name='Price List']",dataFile, "salesInvoice","priceList");
            generalInfoSliderHandle(-100);
            //F3-Items
            for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice","productCount")); i++) {
                addProduct(i);
            }
            //verify FQ in Items tab
            //formatting both to 1 decimal
            Assert.assertEquals(
                    String.format("%.1f", Double.parseDouble(common.findWebElement("xpath", "//Edit[@AutomationId='FreeQuantity']").getText())),
                    String.format("%.1f", freeQuantity), "Free Quantity mismatch in Items tab");
            double itemsNetValue = Double.parseDouble(common.findWebElement("xpath", "//Edit[@AutomationId='NetAmount']").getText().replace(",", ""));
            tcsCalculations(itemsNetValue);
            validateCGSTAmountTabIsEmpty();
            validateSGSTAmountTabIsEmpty();
            validateIGSTAmountTabIsNotEmpty();
            validateCESSAmountTabIsNotEmpty();

            navigateToBillsReceivablesTab();
            common.deleteInvalidRows();

            //check for Sales Invoice total value
            System.out.println("dataFile: "+common.getData(dataFile,"salesInvoice","column28").replace(",",""));
            common.clickElement("xpath","//TabItem[contains(@Name,'Invoice Details')]");
            Assert.assertEquals(String.format("%.1f",Double.parseDouble(common.getData(dataFile,"salesInvoice","column28").replace(",",""))),
                    String.format("%.1f",Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Invoice Value *']").getText().replace(",",""))));
            System.out.println("getText: "+common.findWebElement("xpath","//Edit[@Name='Invoice Value *']").getText().replace(",",""));
            //verify all the fields in summary are fetching data
            navigateToOtherInfoTab();
            for (int j = 0; j < 2; j++) {
                Robot robot=new Robot();
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_RIGHT);
            }
            quantityPresentInSummary();
            //freeQty in summary
            double freeQtyInSummary =Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Free Quantity']").getText());
            Assert.assertEquals(freeQuantity, freeQtyInSummary,"Free Quantity is not same");
            grossAmountPresentInSummary();
            netAmountPresentInSummary();
            totalValuePresentInSummary();
            totalValueInCompanyCurrenyPresentInSummary();
            receivableAmountPresentInSummary();
            cessPresentInSummary();
            tcsAmountPresentInSummary();
            tcsTaxableValuePresentInSummary();
            //save
            transactionSave();
            String newVoucherID =newTransactionID(oldVoucherID).replace(" ","");
            System.out.println("newID: "+newVoucherID);
            Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
//        Thread.sleep(2500);
            common.clickElement("name", "Sales");
            common.clickElement("name", "Invoices");
            common.clickElement("xpath", "//MenuItem[@Name='Sales Returns'][2]");
            Thread.sleep(1000);
            common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
            Thread.sleep(1500);
            verifyReport(newVoucherID,dataFile,"salesReturn");
            //calculate time taken
            long testMainMethodEnd=System.currentTimeMillis();
            System.out.println("exec time for main method: "+(testMainMethodEnd-testMainMethodStart)/1000);

        }

        public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
            long addProductStart=System.currentTimeMillis();
            if (common.getData(dataFile, "salesInvoice","productType" + i).equals("general")) {
                generalProduct(dataFile, "salesInvoice","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
            } else if (common.getData(dataFile,"salesInvoice", "productType" + i).equals("multiBatch")) {
                multiBatchProductPurchase(dataFile,"salesInvoice", "productCode" + i, "quantity" + i,"freeQuantity" + i, i);
            }else if (common.getData(dataFile, "salesInvoice","productType" + i).equals("serial")) {
                serialNumberProductInPurchase(dataFile, "salesInvoice","productCode" + i,"serialText","quantity" + i,"freeQuantity" + i, i);
            }

            quantity = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']").getText());
            freeQuantity+=Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Free Quantity Row " + i + ", Not sorted.']").getText());

            WebElement element = common.findWebElement("xpath", "//Edit[@Name='MRP Row " + i + ", Not sorted.']");
            mrp = Double.parseDouble(element.getText().replace(",", ""));
            System.out.println("mrp:-" + mrp);

            WebElement mrpAmount = common.findWebElement("xpath", "//Edit[@Name='MRP Amount Row " + i + ", Not sorted.']");
            String actualMrpAmountText = mrpAmount.getText().replace(",", "");
            double actualMrpAmount = Double.parseDouble(actualMrpAmountText);

            double expectedMrpAmount = quantity * mrp;
            System.out.println("actual:- " + actualMrpAmount + " -expectedMrp-" + expectedMrpAmount);
            Assert.assertEquals(actualMrpAmount, expectedMrpAmount, "Mismatch in MRP Amount");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 400, 0);

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
            enterData("xpath", "//Edit[@Name='Voucher Disc % Row " + i + ", Not sorted.']", dataFile, "salesInvoice","voucherDiscount" + i);

            WebElement voucher = common.findWebElement("xpath", "//Edit[@Name='Voucher Disc Row " + i + ", Not sorted.']");
            String voucherText = voucher.getText().replace(",", "");
            voucherDiscountValue = Double.parseDouble(voucherText);
            System.out.println("voucher Amount Value:- " + voucherDiscountValue);

            enterData("xpath", "//Edit[@Name='Party Disc % Row " + i + ", Not sorted.']", dataFile,"salesInvoice", "partyDiscount" + i);

            WebElement partyDisc = common.findWebElement("xpath", "//Edit[@Name='Party Disc Row " + i + ", Not sorted.']");
            String partyDiscText = partyDisc.getText().replace(",", "");
            partyDiscountValue = Double.parseDouble(partyDiscText);
            System.out.println("party Discount Value: - " + partyDiscountValue);

            enterDataAndValidate("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile,"salesInvoice", "HSNCode");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 400, 0);

            //GST
            WebElement gst = common.findWebElement("xpath", "//Edit[@Name='GST Product Category Row " + i + ", Not sorted.']");
            gstValue = StringUtil.extractNumber(gst.getText());
            System.out.println("gst percentage:- " + gstValue);

            WebElement cess = common.findWebElement("xpath", "//Edit[@Name='CESS Product Category Row " + i + ", Not sorted.']");
            cessValue = StringUtil.extractNumber(cess.getText());
            System.out.println("cess percentage:- " + cessValue);

            //check for inclusive or exclusive sales price list
            grossMinusDiscount = (grossAmount - (voucherDiscountValue + partyDiscountValue));
            System.out.println("gross-disc is: " + grossMinusDiscount);
            if (common.getData(dataFile,"salesInvoice", "priceList").contains("Inclusive")) {
                taxableValue=(grossMinusDiscount/(100+ (gstValue+cessValue)))*100;
                System.out.println("taxable value for inclusive is: "+taxableValue);
                netAmount = Double.parseDouble((common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']").getText().replace(",", "")));
                System.out.println("Net Amount:- " + netAmount);
                Assert.assertEquals(grossMinusDiscount, netAmount);
            } else {
                taxableValue = grossMinusDiscount;
                System.out.println("taxable value for exclusive is:" + taxableValue);
                taxableAmountCalculated = Double.parseDouble((common.findWebElement("xpath", "//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']").getText().replace(",", "")));
                System.out.println("taxable: " + taxableAmountCalculated);
                Assert.assertEquals(grossMinusDiscount, taxableValue);
            }

            DecimalFormat decimalFormat = new DecimalFormat("#.###");

            double totalValue = gstValue + cessValue;
            System.out.println("total:-" + totalValue);

            if (!gstAmountClicked) {
                common.clickElement("xpath", "//Header[@Name='GST Amount']");
                gstAmountClicked = true;
            }
            String gstTransType = common.findWebElement("xpath", "//Edit[@Name='GST Trans Type *']").getText().trim();

            if (gstTransType.equalsIgnoreCase("Inter State Sales Returns from Registered Dealers")) {
                double expectedIGST = Double.parseDouble(decimalFormat.format((taxableValue * gstValue) / 100));
                double actualIGST = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']").getText().replace(",", ""));
                System.out.println("Actual IGST: " + actualIGST);
                System.out.println("Expected IGST: " + expectedIGST);
                Assert.assertEquals(actualIGST, expectedIGST);

                double expectedCESS = Double.parseDouble(decimalFormat.format((taxableValue * cessValue) / 100));
                double actualCESS = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']").getText().replace(",", ""));
                System.out.println("Actual CESS: " + actualCESS);
                System.out.println("Expected CESS: " + expectedCESS);
                Assert.assertEquals(actualCESS, expectedCESS);

                double expectedGSTAmount = expectedIGST + expectedCESS;
                double actualGSTAmount = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']").getText().replace(",", ""));
                System.out.println("Expected GST Amount: " + decimalFormat.format(expectedGSTAmount));
                System.out.println("Actual GST Amount: " + decimalFormat.format(actualGSTAmount));
                Assert.assertEquals(decimalFormat.format(actualGSTAmount), decimalFormat.format(expectedGSTAmount));

            } else if (gstTransType.equalsIgnoreCase("Intra State Sales Returns from Registered Dealers")) {
                double expectedCGST = Double.parseDouble(decimalFormat.format(((taxableValue * gstValue) / 100) / 2));
                double actualCGST = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='CGST Row " + i + ", Not sorted.']").getText().replace(",", ""));
                System.out.println("Actual CGST: " + actualCGST);
                System.out.println("Expected CGST: " + expectedCGST);
                Assert.assertEquals(actualCGST, expectedCGST);

                double expectedSGST = Double.parseDouble(decimalFormat.format(((taxableValue * gstValue) / 100) / 2));
                double actualSGST = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='SGST Row " + i + ", Not sorted.']").getText().replace(",", ""));
                System.out.println("Actual SGST: " + actualSGST);
                System.out.println("Expected SGST: " + expectedSGST);
                Assert.assertEquals(actualSGST, expectedSGST);

                double expectedCESS = Double.parseDouble(decimalFormat.format((taxableValue * cessValue) / 100));
                double actualCESS = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']").getText().replace(",", ""));
                System.out.println("Actual CESS: " + actualCESS);
                System.out.println("Expected CESS: " + expectedCESS);
                Assert.assertEquals(actualCESS, expectedCESS);

                double expectedGSTAmount = expectedCGST + expectedSGST + expectedCESS;
                double actualGSTAmount = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']").getText().replace(",", ""));
                System.out.println("Expected GST Amount: " + decimalFormat.format(expectedGSTAmount));
                System.out.println("Actual GST Amount: " + decimalFormat.format(actualGSTAmount));
                Assert.assertEquals(decimalFormat.format(actualGSTAmount), decimalFormat.format(expectedGSTAmount));
            } else {
                throw new IllegalArgumentException("Invalid GST Trans Type: " + gstTransType);
            }
            long addProductEnd=System.currentTimeMillis();
            System.out.println("Total time for Add Product: "+ (addProductEnd-addProductStart)/1000);
        }
    }