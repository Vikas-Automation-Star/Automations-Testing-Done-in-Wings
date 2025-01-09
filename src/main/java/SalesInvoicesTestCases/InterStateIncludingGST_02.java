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

public class InterStateIncludingGST_02 extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;
    double mrp, grossAmount, unitRate, quantity, voucherDiscountValue, partyDiscountValue, netAmount, grossMinusDiscount, gstValue, cessValue, taxableValue, taxableAmountCalculated;


    public InterStateIncludingGST_02(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void testCase2() throws InterruptedException, IOException, ParseException, AWTException {
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
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectAndValidateData(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        generalInfoSliderHandle(800);
        common.clickElement("xpath", "//Edit[@Name='Port Code']");
        selectOptionalMaster(common.getData(dataFile, "portCode"), "xpath", "//Edit[@Name='Port Code']");
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        generalInfoSliderHandle(-800);
//        //F3-Items
        //for product (EXCLUSIVE TAX)
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "productCount")); i++) {
            addProduct(i);
        }
        validateCGSTAmountTabIsEmpty();
        validateSGSTAmountTabIsEmpty();
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();


        navigateToOtherInfoTab();
        for (int j = 0; j < 4; j++) {
            robot.keyPress(KeyEvent.VK_RIGHT);
            robot.keyRelease(KeyEvent.VK_RIGHT); //for summary tab
        }

        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        netAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        receivableAmountPresentInSummary();
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
        if (common.getData(dataFile, "priceList").contains("Inclusive")) {
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

        //GST
        WebElement gst = common.findWebElement("xpath", "//Edit[@Name='GST Product Category Row " + i + ", Not sorted.']");
        gstValue = StringUtil.extractNumber(gst.getText());
        System.out.println("gst percentage:- " + gstValue);

        WebElement cess = common.findWebElement("xpath", "//Edit[@Name='CESS Product Category Row " + i + ", Not sorted.']");
        cessValue = StringUtil.extractNumber(cess.getText());
        System.out.println("cess percentage:- " + cessValue);

        DecimalFormat decimalFormat = new DecimalFormat("#.###");

        double totalValue = gstValue + cessValue;
        System.out.println("total:-" + totalValue);

        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
//            double expectedIGST = Double.parseDouble(decimalFormat.format((taxableValue*gstValue)/100));
//            double actualIGST= Double.parseDouble((common.findWebElement("xpath","//Edit[@Name='IGST Row "+i+", Not sorted.']").getText().replace(",","")));
//            System.out.println("Actual IGST: "+actualIGST);
//            System.out.println("Expected IGST: "+expectedIGST);
//            Assert.assertEquals(actualIGST,expectedIGST);
//
//            double expectedCESS = Double.parseDouble(decimalFormat.format((taxableValue*cessValue)/100));
//            double actualCESS= Double.parseDouble((common.findWebElement("xpath","//Edit[@Name='CESS Row "+i+", Not sorted.']").getText().replace(",","")));
//            System.out.println("Actual CESS: "+ actualCESS);
//            System.out.println("Expected CESS: "+expectedCESS);
//            Assert.assertEquals(actualCESS,expectedCESS);
//
//            double expectedGSTAmount= expectedIGST+expectedCESS;
//            double actualGSTAmount= Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='GST Amount Row "+i+", Not sorted.']").getText().replace(",",""));
//            System.out.println("expected GST Amount: "+decimalFormat.format(expectedGSTAmount));
//            System.out.println("Actual GST Amount: "+decimalFormat.format(actualGSTAmount));
//            Assert.assertEquals(decimalFormat.format(actualGSTAmount),decimalFormat.format(expectedGSTAmount));

        // Fetch the GST Trans type
        String gstTransType = common.findWebElement("xpath", "//Edit[@Name='GST Trans Type *']").getText().trim();

        if (gstTransType.equalsIgnoreCase("Inter State Sales to Registered Dealers")) {
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

        } else if (gstTransType.equalsIgnoreCase("Intra State Sales to Registered Dealers")) {
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
//            enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']",dataFile, "product");
//            common.clickElement("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']");
//            enterData("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']", dataFile, "ProductQuantity");
////        common.clickElement("xpath", "//Edit[@Name='MRP Row 0, Not sorted.']");
//            super.enterData("xpath", "//Edit[@Name='MRP Row 0, Not sorted.']", dataFile, "productMRP");
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",500,0);
////        common.clickElement("xpath", "//Edit[@Name='Minimum Rate * Row 0, Not sorted.']");
//            super.enterData("xpath", "//Edit[@Name='Minimum Rate * Row 0, Not sorted.']", dataFile, "minumumRate");
////        common.clickElement("xpath", "//Edit[@Name='Maximum Rate * Row 0, Not sorted.']");
//            Thread.sleep(2500);
//            super.enterData("xpath", "//Edit[@Name='Maximum Rate * Row 0, Not sorted.']", dataFile, "maximumRate");
////        common.clickElement("xpath", "//Edit[@Name='Unit Rate Row 0, Not sorted.']");
//            super.enterData("xpath", "//Edit[@Name='Unit Rate Row 0, Not sorted.']", dataFile, "unitRate");
//
//            // for multibatch
//            super.enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row 1, Not sorted.']",dataFile, "multiBatch");
//            common.clickElement("xpath","//Button[@Name='Stock Details Row 1']");
//            Thread.sleep(3000);
//
//            //click on stockDetails
//            for (int i = 0; i < 9; i++) {
//                robot.keyPress(KeyEvent.VK_TAB);
//                robot.keyRelease(KeyEvent.VK_TAB);
//            }
//            robot.keyPress(KeyEvent.VK_SPACE);
//            robot.keyRelease(KeyEvent.VK_SPACE);
//
//
//
//            common.clickElement("xpath","//Button[@Name='OK']");
////        common.clickElement("xpath", "//Edit[@Name='MRP Row 1, Not sorted.']");
//            super.enterData("xpath", "//Edit[@Name='MRP Row 1, Not sorted.']", dataFile, "productMRP");
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",500,0);
////        common.clickElement("xpath", "//Edit[@Name='Minimum Rate * Row 1, Not sorted.']");
//            super.enterData("xpath", "//Edit[@Name='Minimum Rate * Row 1, Not sorted.']", dataFile, "minumumRate");
////        common.clickElement("xpath", "//Edit[@Name='Maximum Rate * Row 1, Not sorted.']");
//            super.enterData("xpath", "//Edit[@Name='Maximum Rate * Row 1, Not sorted.']", dataFile, "maximumRate");
////        common.clickElement("xpath", "//Edit[@Name='Unit Rate Row 1, Not sorted.']");
//            super.enterData("xpath", "//Edit[@Name='Unit Rate Row 1, Not sorted.']", dataFile, "unitRate");
//            //for serialNo
//            super.enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row 2, Not sorted.']",dataFile, "serialBatch");
//            common.clickElement("xpath","//Button[@Name='Stock Details Row 2']");
//
//            int numRowsToSelect = 15;
//
//            for (int i = 0; i < 15; i++) {  //select the loop based on the no.of item you want to select
//                robot.keyPress(KeyEvent.VK_TAB);
//                robot.keyRelease(KeyEvent.VK_TAB);
//                robot.keyPress(KeyEvent.VK_SPACE);
//                robot.keyRelease(KeyEvent.VK_SPACE);
//                robot.keyPress(KeyEvent.VK_DOWN);
//                robot.keyRelease(KeyEvent.VK_DOWN);
//            }
//            common.clickElement("xpath","//Button[@Name='OK']");
//
////        List<WebElement> rows = common.findWebElements("xpath", "//Table[@Name='Serial Numbers List']/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
////        System.out.println("Row count: " + rows.size());
////        int rowsToIterate = Math.min(numRowsToSelect, rows.size());
////        for (int i = 0; i < rowsToIterate; i++) {
////            WebElement row = rows.get(i); // Get the i-th row in the list
////                WebElement checkBox = row.findElement(By.xpath("//Item[contains(@Name,'Select row')])"));
////                checkBox.click();
////            }
////        common.clickElement("xpath", "//Edit[@Name='MRP Row 2, Not sorted.']");
//            super.enterData("xpath", "//Edit[@Name='MRP Row 2, Not sorted.']", dataFile, "productMRP");
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",500,0);
////        common.clickElement("xpath", "//Edit[@Name='Minimum Rate * Row 2, Not sorted.']");
//            super.enterData("xpath", "//Edit[@Name='Minimum Rate * Row 2, Not sorted.']", dataFile, "minumumRate");
////        common.clickElement("xpath", "//Edit[@Name='Maximum Rate * Row 2, Not sorted.']");
//            super.enterData("xpath", "//Edit[@Name='Maximum Rate * Row 2, Not sorted.']", dataFile, "maximumRate");
////        common.clickElement("xpath", "//Edit[@Name='Unit Rate Row 2, Not sorted.']");
//            super.enterData("xpath", "//Edit[@Name='Unit Rate Row 2, Not sorted.']", dataFile, "unitRate");

        //verifying data not present in GST tabs


        //save
//        transactionSave();
//        lastTransactionName();
    }
}

