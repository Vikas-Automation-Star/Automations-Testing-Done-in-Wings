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
    double tcsAssesibleValue,tcsRate,tcsAmountInCompnayCurrency;
    double quantity,mrp,unitRate,grossAmount,partyDiscountValue,voucherDiscountValue,grossMinusDiscount,netAmount;

    public SalesInvoiceExclussiveGstAndInclusiveTCS(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void salesInvoiceExclussiveGstAndInclusiveTcs() throws InterruptedException, IOException, ParseException, AWTException,NumberFormatException {
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
        for (int i = 0; i <Integer.parseInt(common.getData(dataFile,"productCount")); i++) {
            if ( common.getData(dataFile,"generalProduct").equals("AT_Product 1")) {
                generalProduct(dataFile,"generalProduct","ProductQuantity");
                quantity= Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Quantity Row 0, Not sorted.']").getText());

                WebElement element= common.findWebElement("xpath","//Edit[@Name='MRP Row 0, Not sorted.']");
                mrp= Double.parseDouble(element.getText().replace(",",""));
                System.out.println("mrp:-"+mrp);

                WebElement mrpAmount = common.findWebElement("xpath", "//Edit[@Name='MRP Amount Row 0, Not sorted.']");
                String actualMrpAmountText = mrpAmount.getText().replace(",", "");
                double actualMrpAmount = Double.parseDouble(actualMrpAmountText);

                double expectedMrpAmount = quantity * mrp;
                System.out.println("actual:- "+actualMrpAmount+" -expectedMrp-"+expectedMrpAmount);
                Assert.assertEquals(actualMrpAmount, expectedMrpAmount, "Mismatch in MRP Amount");
                int offset = 400;
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
//                super.enterData("xpath","//Edit[@Name='Voucher Disc % Row 0, Not sorted.']", dataFile,"voucherDiscount");

//                WebElement voucher = common.findWebElement("xpath","//Edit[@Name='Voucher Disc Row 0, Not sorted.']");
//                String voucherText = voucher.getText().replace(",", "");
//                voucherDiscountValue = Double.parseDouble(voucherText);
//                System.out.println("voucher Amount Value:- "+ voucherDiscountValue);

//                super.enterData("xpath","//Edit[@Name='Party Disc % Row 0, Not sorted.']", dataFile,"partyDiscount");
//
//                WebElement partyDisc= common.findWebElement("xpath","//Edit[@Name='Party Disc Row 0, Not sorted.']");
//                String partyDiscText=partyDisc.getText().replace(",","");
//                partyDiscountValue= Double.parseDouble(partyDiscText);
//                System.out.println("party Discount Value: - " + partyDiscountValue);

//                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",offset,0);


                grossMinusDiscount=(grossAmount-(voucherDiscountValue+partyDiscountValue));

                System.out.println("gross-disc is: " + grossMinusDiscount);

                WebElement net = common.findWebElement("xpath", "//Edit[@Name='Net Amount Row 0, Not sorted.']");
                String netAmountText = net.getText().replace(",", "");
                netAmount = Double.parseDouble(netAmountText);
                System.out.println("Net Amount:- "+ netAmount);
                Assert.assertEquals(grossMinusDiscount,netAmount);




            }
            if (common.getData(dataFile,"multiBatch").equals("AT_Multi batch Product 1")) {
                multiBatchProduct(dataFile,"multiBatch","multiBatchQuantity");
                quantity= Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Quantity Row 0, Not sorted.']").getText());

                WebElement element= common.findWebElement("xpath","//Edit[@Name='MRP Row 0, Not sorted.']");
                mrp= Double.parseDouble(element.getText().replace(",",""));
                System.out.println("mrp:-"+mrp);

                WebElement mrpAmount = common.findWebElement("xpath", "//Edit[@Name='MRP Amount Row 0, Not sorted.']");
                String actualMrpAmountText = mrpAmount.getText().replace(",", "");
                double actualMrpAmount = Double.parseDouble(actualMrpAmountText);

                double expectedMrpAmount = quantity * mrp;
                System.out.println("actual:- "+actualMrpAmount+" -expectedMrp-"+expectedMrpAmount);
                Assert.assertEquals(actualMrpAmount, expectedMrpAmount, "Mismatch in MRP Amount");
                int offset = 400;
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

                grossMinusDiscount=(grossAmount-(voucherDiscountValue+partyDiscountValue));

                System.out.println("gross-disc is: " + grossMinusDiscount);

                WebElement net = common.findWebElement("xpath", "//Edit[@Name='Net Amount Row 0, Not sorted.']");
                String netAmountText = net.getText().replace(",", "");
                netAmount = Double.parseDouble(netAmountText);
                System.out.println("Net Amount:- "+ netAmount);
                Assert.assertEquals(grossMinusDiscount,netAmount);

            }
            if (common.getData(dataFile,"serialBatch").equals("AT_Product With SN 1")) {
                serialNumberProduct(dataFile,"serialBatch");
                quantity= Double.parseDouble(common.findWebElement("xpath","//Edit[@Name='Quantity Row 0, Not sorted.']").getText());

                WebElement element= common.findWebElement("xpath","//Edit[@Name='MRP Row 0, Not sorted.']");
                mrp= Double.parseDouble(element.getText().replace(",",""));
                System.out.println("mrp:-"+mrp);

                WebElement mrpAmount = common.findWebElement("xpath", "//Edit[@Name='MRP Amount Row 0, Not sorted.']");
                String actualMrpAmountText = mrpAmount.getText().replace(",", "");
                double actualMrpAmount = Double.parseDouble(actualMrpAmountText);

                double expectedMrpAmount = quantity * mrp;
                System.out.println("actual:- "+actualMrpAmount+" -expectedMrp-"+expectedMrpAmount);
                Assert.assertEquals(actualMrpAmount, expectedMrpAmount, "Mismatch in MRP Amount");
                int offset = 400;
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

                grossMinusDiscount=(grossAmount-(voucherDiscountValue+partyDiscountValue));

                System.out.println("gross-disc is: " + grossMinusDiscount);

                WebElement net = common.findWebElement("xpath", "//Edit[@Name='Net Amount Row 0, Not sorted.']");
                String netAmountText = net.getText().replace(",", "");
                netAmount = Double.parseDouble(netAmountText);
                System.out.println("Net Amount:- "+ netAmount);
                Assert.assertEquals(grossMinusDiscount,netAmount);

            }
        }
        //verifying data not present in tabs
        validateCGSTAmountTabWhenNull();
        validateSGSTAmountTabWhenNull();
        validateIGSTAmountTabWhenNull();
        validateToCESSAmountTabWhenNull();

        common.clickElement("xpath","//TabItem[contains(@Name,'TCS')]");
        WebElement assessibleValueAmount =common.findWebElement("xpath","//Edit[@Name='Assessable Value']");
        tcsAssesibleValue = Double.parseDouble(assessibleValueAmount.getText().trim());
        WebElement tcsPercent =common.findWebElement("xpath","//Edit[@Name='TCS Rate']");
        tcsRate = Double.parseDouble(tcsPercent.getText().trim());
        WebElement tcsCompanyCurrency =common.findWebElement("xpath","//Edit[@Name='TCS Amount In Company Currency']");
        tcsAmountInCompnayCurrency = Double.parseDouble(tcsPercent.getText().trim());
        // Calculate the expected TCS amount (5% of transaction amount)
        double expectedTCS = tcsAssesibleValue*tcsRate/100;
        Assert.assertEquals(tcsAmountInCompnayCurrency,expectedTCS);

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
