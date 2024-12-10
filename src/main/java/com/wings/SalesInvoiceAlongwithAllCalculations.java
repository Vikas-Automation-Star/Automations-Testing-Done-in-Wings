package com.wings;

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

public class SalesInvoiceAlongwithAllCalculations extends Transaction {
        WindowsDriver driver;
        Common common;
        String dataFile;
        double quantity;
        double mrp,grossAmount,unitRate,voucherDiscountValue,partyDiscountValue,netAmount,grossMinusDiscount,gstValue,cessValue,taxableValue,taxableAmountCalculated;

        public SalesInvoiceAlongwithAllCalculations(WindowsDriver driver, String file) {
            super(driver);
            common = new Common(this.driver = driver);
            dataFile = file;
        }

        public void salesInvoice1() throws InterruptedException, IOException, ParseException, AWTException {
            navigateToSalesInvoiceMenu();
            Thread.sleep(1000);
            lastTransactionName();
            //branch selection
            common.clickElement("xpath","//Edit[@Name='Voucher Type']");
            selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
            common.clickElement("xpath", "//Edit[@Name='Branch *']");
            selectAndValidateData(common.getData(dataFile,"branch"),"xpath","//Edit[@Name='Branch *']");
            common.clickElement("xpath", "//Edit[@Name='Location *']");
            common.clickElement("xpath", "//Edit[@Name='Cash/Party Code']");
            selectAndValidateData(common.getData(dataFile,"partyCode"),"xpath","//Edit[@Name='Cash/Party Code']");
            Thread.sleep(1000);
            gstTransactionType("Registered Dealers");
            common.clickElement("xpath", "//Edit[@Name='Sales A/c Code']");
            common.clickElement("xpath", "//Edit[@Name='Sales Account']");
//        common.clickElement("xpath", "//CheckBox[@Name='Apply TCS']");
//        common.clickElement("xpath", "//Edit[@Name='TCS Trans Nature']");

            common.inputText("xpath", "//Edit[@Name='Invoice Type']", common.getData(dataFile,"invoice"));
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
            selectAndValidateData(common.getData(dataFile,"priceList"),"xpath", "//Edit[@Name='Price List']");
            common.clickElement("xpath","//Edit[@Name='Port Code']");
            selectOptionalMaster(common.getData(dataFile,"portCode"),"xpath","//Edit[@Name='Port Code']");
            common.clickElement("xpath","//Edit[@Name='Remarks']");
            selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
            //F3-Items

            super.enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']",dataFile, "productCode");
//        common.inputText("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']", common.getData(dataFile, "productCode"));
            common.clickElement("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']");
            super.enterData("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']", dataFile, "Quantity");

            quantity= Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Quantity Row 0, Not sorted.']").getText());

            WebElement element= common.findWebElement("xpath","//Edit[@Name='MRP Row 0, Not sorted.']");
            mrp= Double.parseDouble(element.getText());
            System.out.println("mrp:-"+mrp);

            WebElement mrpAmount = common.findWebElement("xpath", "//Edit[@Name='MRP Amount Row 0, Not sorted.']");
            String actualMrpAmountText = mrpAmount.getText().replace(",", "");
            double actualMrpAmount = Double.parseDouble(actualMrpAmountText);

            double expectedMrpAmount = quantity * mrp;
            System.out.println("actual:- "+actualMrpAmount+" -expectedMrp-"+expectedMrpAmount);
            Assert.assertEquals(actualMrpAmount, expectedMrpAmount, "Mismatch in MRP Amount");

            int offset = 700;
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",offset,0);

            unitRate= Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Unit Rate Row 0, Not sorted.']").getText());
            System.out.println("unitRate:-"+unitRate);

            WebElement gross = common.findWebElement("xpath", "//Edit[@Name='Gross Amount Row 0, Not sorted.']");
            String grossAmountText = gross.getText().replace(",", "");
            grossAmount = Double.parseDouble(grossAmountText);
            System.out.println("gross Amount:- "+ grossAmount);

            double grossExpected=unitRate * quantity;
            System.out.println("gross expected:-"+grossExpected);
            Assert.assertEquals(grossAmount,grossExpected,"Mismatch in Gross Amount");

            //discount
            super.enterData("xpath","//Edit[@Name='Voucher Disc % Row 0, Not sorted.']", dataFile,"voucherDiscount");

            WebElement voucher = common.findWebElement("xpath","//Edit[@Name='Voucher Disc Row 0, Not sorted.']");
            String voucherText = voucher.getText().replace(",", "");
            voucherDiscountValue = Double.parseDouble(voucherText);
            System.out.println("voucher Amount Value:- "+ voucherDiscountValue);

            super.enterData("xpath","//Edit[@Name='Party Disc % Row 0, Not sorted.']", dataFile,"partyDiscount");

            WebElement partyDisc= common.findWebElement("xpath","//Edit[@Name='Party Disc Row 0, Not sorted.']");
            String partyDiscText=partyDisc.getText().replace(",","");
            partyDiscountValue= Double.parseDouble(partyDiscText);
            System.out.println("party Discount Value: - " + partyDiscountValue);

            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",offset,0);


           grossMinusDiscount=(grossAmount-(voucherDiscountValue+partyDiscountValue));

            System.out.println("gross-disc is: " + grossMinusDiscount);

            WebElement net = common.findWebElement("xpath", "//Edit[@Name='Net Amount Row 0, Not sorted.']");
            String netAmountText = net.getText().replace(",", "");
            netAmount = Double.parseDouble(netAmountText);
            System.out.println("Net Amount:- "+ netAmount);
            Assert.assertEquals(grossMinusDiscount,netAmount);

            //GST
            WebElement gst= common.findWebElement("xpath","//Edit[@Name='GST Product Category Row 0, Not sorted.']");
            gstValue=StringUtil.extractNumber(gst.getText());
            System.out.println("gst percentage:- "+gstValue);

            WebElement cess= common.findWebElement("xpath","//Edit[@Name='CESS Product Category Row 0, Not sorted.']");
            cessValue= StringUtil.extractNumber(cess.getText());
            System.out.println("cess percentage:- "+cessValue);

            DecimalFormat decimalFormat = new DecimalFormat("#.##");

            double totalValue=gstValue+cessValue;
            System.out.println("total:-"+ totalValue);
            taxableValue=(grossMinusDiscount/(100+totalValue))*100;
            taxableValue= Double.parseDouble(decimalFormat.format(taxableValue));

            System.out.println("taxable value calculated:"+ taxableValue);
            WebElement taxable= common.findWebElement("xpath","//Edit[@Name='Taxable Value Row 0, Not sorted.']");
            String taxableAmount=taxable.getText().replace(",","");
            taxableAmountCalculated = Double.parseDouble(taxableAmount);
            System.out.println("taxable: "+taxableAmountCalculated);
            Assert.assertEquals(taxableAmountCalculated,taxableValue);

            double expectedGST=grossMinusDiscount-taxableAmountCalculated;
            expectedGST= Double.parseDouble(decimalFormat.format(expectedGST));
//            expectedGST=Math.round(expectedGST * 100.0) / 100.0;
            System.out.println("expected GST: "+ expectedGST);
            WebElement gst1= common.findWebElement("xpath","//Edit[@Name='GST Amount Row 0, Not sorted.']");
            String gstAmount= gst1.getText().replace(",","");
            double actualGST=Double.parseDouble(gstAmount);
            System.out.println("actual GST :"+ actualGST);
            Assert.assertEquals(actualGST,expectedGST);

            //save
//            transactionSave();
//            lastTransactionName();
        }
    }

