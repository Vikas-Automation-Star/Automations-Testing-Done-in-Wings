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

public class SalesInvoiceIncludingGST_TC02 extends Transaction {
        WindowsDriver driver;
        Common common;
        String dataFile;

        public SalesInvoiceIncludingGST_TC02(WindowsDriver driver, String file) {
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
            common.clickElement("xpath","//Edit[@Name='Voucher Type']");
            selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
            common.clickElement("xpath", "//Edit[@Name='Branch *']");
            selectAndValidateData(common.getData(dataFile,"branch"),"xpath","//Edit[@Name='Branch *']");
            common.clickElement("xpath", "//Edit[@Name='Location *']");
            common.clickElement("xpath", "//Edit[@Name='Cash/Party Code']");
            selectAndValidateDataNew(common.getData(dataFile,"partyCode"),"xpath","//Edit[@Name='Cash/Party Code']");
            Thread.sleep(2500);
            gstTransactionType("Registered Dealers");
            Thread.sleep(1000);
            common.clickElement("xpath", "//Edit[@Name='Sales A/c Code']");
            selectAndValidateData(common.getData(dataFile,"salesAccountCode"),"xpath", "//Edit[@Name='Sales A/c Code']");
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
//        //F3-Items
            //for product (EXCLUSIVE TAX)
            super.enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']",dataFile, "product");
            common.clickElement("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']");
            super.enterData("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']", dataFile, "ProductQuantity");
//        common.clickElement("xpath", "//Edit[@Name='MRP Row 0, Not sorted.']");
            super.enterData("xpath", "//Edit[@Name='MRP Row 0, Not sorted.']", dataFile, "productMRP");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",500,0);
//        common.clickElement("xpath", "//Edit[@Name='Minimum Rate * Row 0, Not sorted.']");
            super.enterData("xpath", "//Edit[@Name='Minimum Rate * Row 0, Not sorted.']", dataFile, "minumumRate");
//        common.clickElement("xpath", "//Edit[@Name='Maximum Rate * Row 0, Not sorted.']");
            Thread.sleep(2500);
            super.enterData("xpath", "//Edit[@Name='Maximum Rate * Row 0, Not sorted.']", dataFile, "maximumRate");
//        common.clickElement("xpath", "//Edit[@Name='Unit Rate Row 0, Not sorted.']");
            super.enterData("xpath", "//Edit[@Name='Unit Rate Row 0, Not sorted.']", dataFile, "unitRate");

            // for multibatch
            super.enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row 1, Not sorted.']",dataFile, "multiBatch");
            common.clickElement("xpath","//Button[@Name='Stock Details Row 1']");
            Thread.sleep(3000);

            //click on stockDetails
            for (int i = 0; i < 9; i++) {
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
            }
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);


            //* click on check-box through stock in quantity
//        WebElement stock=common.findWebElement("xpath","//Window[@Name='Batch Details']/Table[@Name='Batch Details']/*[@Name='Data Panel']/ListItem[@Name='Row 1']/Item[@Name='Batch Name row 1']");
//        stock.click();
//        stock.sendKeys(Keys.TAB,Keys.TAB,Keys.SPACE);

            common.clickElement("xpath","//Button[@Name='OK']");
//        common.clickElement("xpath", "//Edit[@Name='MRP Row 1, Not sorted.']");
            super.enterData("xpath", "//Edit[@Name='MRP Row 1, Not sorted.']", dataFile, "productMRP");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",500,0);
//        common.clickElement("xpath", "//Edit[@Name='Minimum Rate * Row 1, Not sorted.']");
            super.enterData("xpath", "//Edit[@Name='Minimum Rate * Row 1, Not sorted.']", dataFile, "minumumRate");
//        common.clickElement("xpath", "//Edit[@Name='Maximum Rate * Row 1, Not sorted.']");
            super.enterData("xpath", "//Edit[@Name='Maximum Rate * Row 1, Not sorted.']", dataFile, "maximumRate");
//        common.clickElement("xpath", "//Edit[@Name='Unit Rate Row 1, Not sorted.']");
            super.enterData("xpath", "//Edit[@Name='Unit Rate Row 1, Not sorted.']", dataFile, "unitRate");
            //for serialNo
            super.enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row 2, Not sorted.']",dataFile, "serialBatch");
            common.clickElement("xpath","//Button[@Name='Stock Details Row 2']");

            int numRowsToSelect = 15;

            for (int i = 0; i < 15; i++) {  //select the loop based on the no.of item you want to select
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
                robot.keyPress(KeyEvent.VK_SPACE);
                robot.keyRelease(KeyEvent.VK_SPACE);
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);
            }
            common.clickElement("xpath","//Button[@Name='OK']");

//        List<WebElement> rows = common.findWebElements("xpath", "//Table[@Name='Serial Numbers List']/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
//        System.out.println("Row count: " + rows.size());
//        int rowsToIterate = Math.min(numRowsToSelect, rows.size());
//        for (int i = 0; i < rowsToIterate; i++) {
//            WebElement row = rows.get(i); // Get the i-th row in the list
//                WebElement checkBox = row.findElement(By.xpath("//Item[contains(@Name,'Select row')])"));
//                checkBox.click();
//            }
//        common.clickElement("xpath", "//Edit[@Name='MRP Row 2, Not sorted.']");
            super.enterData("xpath", "//Edit[@Name='MRP Row 2, Not sorted.']", dataFile, "productMRP");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",500,0);
//        common.clickElement("xpath", "//Edit[@Name='Minimum Rate * Row 2, Not sorted.']");
            super.enterData("xpath", "//Edit[@Name='Minimum Rate * Row 2, Not sorted.']", dataFile, "minumumRate");
//        common.clickElement("xpath", "//Edit[@Name='Maximum Rate * Row 2, Not sorted.']");
            super.enterData("xpath", "//Edit[@Name='Maximum Rate * Row 2, Not sorted.']", dataFile, "maximumRate");
//        common.clickElement("xpath", "//Edit[@Name='Unit Rate Row 2, Not sorted.']");
            super.enterData("xpath", "//Edit[@Name='Unit Rate Row 2, Not sorted.']", dataFile, "unitRate");

            //verifying data not present in GST tabs
            common.clickElement("xpath","//TabItem[@Name='  F8 CGST  ']");
            WebElement tax=common.findWebElement("xpath","//Edit[@Name='Tax Amount Row 0, Not sorted.']");
            String value=tax.getText();
            if ((value==(null) || "(null)".equals(value))){
                Assert.fail("CGST field is not empty");

            }
            common.clickElement("xpath","//TabItem[@Name='  F9 SGST  ']");
            WebElement tax1 =common.findWebElement("xpath","//Edit[@Name='Tax Amount Row 0, Not sorted.']");
            String value1= tax1.getText();
            if ((value1==(null) || "(null)".equals(value1))){
                Assert.fail("SGST field is not empty");

            }
            common.clickElement("xpath","//TabItem[@Name='  F11 IGST  ']");
            WebElement IGSTtax =common.findWebElement("xpath","//Edit[@Name='Tax Amount Row 0, Not sorted.']");
            String value2= IGSTtax.getText();
            if ((value2==(null) || "(null)".equals(value2))){
                Assert.fail("IGST field is not empty");
            }

            //verify all the fields in summary are fetching data
            navigateToOtherInfoTab();
            for (int i = 0; i < 4; i++) {
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_RIGHT);
            }

            //verify SUMMARY TAB
            WebElement Quantity =common.findWebElement("xpath","//Edit[@Name='Quantity']");
            String quantityText= Quantity.getText();
            if ((quantityText==(null) || "(null)".equals(quantityText))){
                Assert.fail("Quantity field is empty");
            }

            WebElement grossAmount =common.findWebElement("xpath","//Edit[@Name='Gross Amount']");
            String grossAmountText= grossAmount.getText();
            if ((grossAmountText==(null) || "(null)".equals(grossAmountText))){
                Assert.fail("grossAmount field is empty");
            }

            WebElement netAmount =common.findWebElement("xpath","//Edit[@Name='Net Amount']");
            String netAmountText= netAmount.getText();
            if ((netAmountText==(null) || "(null)".equals(netAmountText))){
                Assert.fail("netAmount field is empty");
            }

            WebElement totalValue= common.findWebElement("xpath","//Edit[@Name='Total Value']");
            String totalValueText=totalValue.getText();
            if (totalValueText==(null) || "(null)".equals(totalValueText)){
                Assert.fail("Total value field is Empty");
            }

            WebElement totalValueCompanyCurreny= common.findWebElement("xpath","//Edit[@Name='Total Value In Company Currency']");
            String totalValuecurrencyText=totalValueCompanyCurreny.getText();
            if (totalValuecurrencyText==(null) || "(null)".equals(totalValuecurrencyText)){
                Assert.fail("Total value in Company Curreny field is Empty");
            }

            WebElement receivableAmount=common.findWebElement("xpath","//Edit[@Name='Receivable Amount']");
            String receivableAmountText=receivableAmount.getText();
            if (receivableAmountText==(null) || "(null)".equals(receivableAmountText)){
                Assert.fail("Receivable Amount field is empty");
            }

            //save
//        transactionSave();
//        lastTransactionName();
        }
    }

