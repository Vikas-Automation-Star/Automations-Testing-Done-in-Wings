package com.wings.pages.sales.transactions;

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

public class SalesOrder_UnRegInterInclusive extends Transaction{

        WindowsDriver driver;
        Common common;
        String dataFile;
        boolean gstAmountClicked = false;
        double mrp, grossAmount, unitRate, quantity,
                voucherDiscountValue, partyDiscountValue, netAmount, grossMinusDiscount, gstValue, cessValue, taxableValue, taxableAmountCalculated;

        public SalesOrder_UnRegInterInclusive(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public String interInclusiveUnReg() throws InterruptedException, IOException, ParseException, AWTException {
            long testMainMethodStart=System.currentTimeMillis();
            navigateToSalesOrderMenu();
            Thread.sleep(2000);
            String oldVoucherID =oldTTransactionID();
            System.out.println("oldID: "+ oldVoucherID);
            //branch selection
            common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
            selectOptionalMaster(common.getData(dataFile, "SalesOrder","voucher"), "xpath", "//Edit[@Name='Voucher Type']");
            enterInput("xpath", "//Edit[@Name='Branch *']",dataFile, "SalesOrder","branch");
            enterInput("xpath", "//Edit[@Name='Location *']",dataFile, "SalesOrder","location");
            enterInput("xpath", "//Edit[@Name='Party Code']",dataFile, "SalesOrder","partyCode");
            Thread.sleep(2500);
            gstTransactionType("Unregistered Dealers");
            Thread.sleep(1000);
            enterInput("xpath", "//Edit[@Name='Price List']",dataFile,"SalesOrder", "priceList");
            common.clickElement("xpath","//CheckBox[@Name='Advance Receipts']");
            common.clickElement("xpath", "//Edit[@Name='Remarks']");
            selectOptionalMaster(common.getData(dataFile,"SalesOrder", "remarks"), "xpath", "//Edit[@Name='Remarks']");
            //F3-Items
            for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"SalesOrder", "productCount")); i++) {
                addProduct(i);
            }
            validateCGSTAmountTabIsEmpty();
            validateSGSTAmountTabIsEmpty();
            validateIGSTAmountTabIsNotEmpty();;
            validateCESSAmountTabIsNotEmpty();
            //verify all the fields in summary are fetching data
            navigateToOtherInfoTab();
            for (int j = 0; j < 2; j++) {
                Robot robot=new Robot();
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_RIGHT);
            }
            WebElement Quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity *']");
            String quantityText = Quantity.getText();
            if ((quantityText == (null) || "(null)".equals(quantityText))) {
                Assert.fail("Quantity field is empty");
            }
            grossAmountPresentInSummary();
            netAmountPresentInSummary();
            totalValuePresentInSummary();
            totalValueInCompanyCurrenyPresentInSummary();
            //save
            transactionSave();
            String newVoucherID =newTransactionID(oldVoucherID).replace(" ","");
            System.out.println("newID: "+newVoucherID);
            Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
            Thread.sleep(1000);
            common.clickElement("name", "Sales");
            common.clickElement("name", "Orders");
            common.clickElement("xpath", "//MenuItem[@Name='Sales Orders'][2]");
            Thread.sleep(1000);
            common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
            Thread.sleep(1500);
            verifyReport(newVoucherID,dataFile,"SalesOrder");
            //calculate time taken
            long testMainMethodEnd=System.currentTimeMillis();
            System.out.println("exec time for main method: "+(testMainMethodEnd-testMainMethodStart)/1000);
            return newVoucherID;
        }

        public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
            long addProductStart=System.currentTimeMillis();
            if (common.getData(dataFile,"SalesOrder", "productType" + i).equals("general")) {
                generalProduct_New(dataFile,"SalesOrder", "productCode" + i, "quantity" + i,"freeQuantity" + i, i);
            } else if (common.getData(dataFile,"SalesOrder", "productType" + i).equals("multiBatch")) {
                multiBatchProduct_New(dataFile, "SalesOrder","transType","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
            }else if (common.getData(dataFile,"SalesOrder", "productType" + i).equals("serial")) {
                serialNumProduct_New(dataFile,"SalesOrder", "transType","productCode" + i,"quantity" + i,"freeQuantity" + i, i);
            }

            quantity = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']").getText());

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
            enterData("xpath", "//Edit[@Name='Voucher Disc % Row " + i + ", Not sorted.']", dataFile,"SalesOrder", "voucherDiscount" + i);

            WebElement voucher = common.findWebElement("xpath", "//Edit[@Name='Voucher Disc Row " + i + ", Not sorted.']");
            String voucherText = voucher.getText().replace(",", "");
            voucherDiscountValue = Double.parseDouble(voucherText);
            System.out.println("voucher Amount Value:- " + voucherDiscountValue);

            enterData("xpath", "//Edit[@Name='Party Disc % Row " + i + ", Not sorted.']", dataFile,"SalesOrder", "partyDiscount" + i);

            WebElement partyDisc = common.findWebElement("xpath", "//Edit[@Name='Party Disc Row " + i + ", Not sorted.']");
            String partyDiscText = partyDisc.getText().replace(",", "");
            partyDiscountValue = Double.parseDouble(partyDiscText);
            System.out.println("party Discount Value: - " + partyDiscountValue);

            enterDataAndValidate("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile,"SalesOrder", "HSNCode");
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
            if (common.getData(dataFile,"SalesOrder", "priceList").contains("Inclusive")) {
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

            if (gstTransType.contains("Inter State Sales")) {
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

            } else if (gstTransType.contains("Intra State Sales")) {
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