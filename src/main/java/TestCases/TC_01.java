package TestCases;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class TC_01 extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public TC_01(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void testCase1() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToSalesInvoiceMenu();
        Thread.sleep(1000);
        lastTransactionName();
        //branch selection
//        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
//        selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
//        common.clickElement("xpath", "//Edit[@Name='Branch *']");
//        selectAndValidateData(common.getData(dataFile,"branch"),"xpath","//Edit[@Name='Branch *']");
//        common.clickElement("xpath", "//Edit[@Name='Location *']");
//        common.clickElement("xpath", "//Edit[@Name='Cash/Party Code']");
//        selectAndValidateDataNew(common.getData(dataFile,"partyCode"),"xpath","//Edit[@Name='Cash/Party Code']");
//        Thread.sleep(2500);
//        gstTransactionType("Registered Dealers");
//        Thread.sleep(1000);
//        common.clickElement("xpath", "//Edit[@Name='Sales A/c Code']");
//        selectAndValidateData(common.getData(dataFile,"salesAccountCode"),"xpath", "//Edit[@Name='Sales A/c Code']");
////        common.clickElement("xpath", "//CheckBox[@Name='Apply TCS']");
////        common.clickElement("xpath", "//Edit[@Name='TCS Trans Nature']");
//
//        common.inputText("xpath", "//Edit[@Name='Invoice Type']", common.getData(dataFile,"invoice"));
//        Thread.sleep(1000);
//        Robot robot = new Robot();
//        robot.keyPress(KeyEvent.VK_DOWN);
//        robot.keyRelease(KeyEvent.VK_DOWN);
//        robot.keyPress(KeyEvent.VK_DOWN);
//        robot.keyRelease(KeyEvent.VK_DOWN);
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
////        super.validateElements("xpath","//Edit[@Name='Invoice Type']", common.getData(dataFile,"invoice"));
//        common.clickElement("xpath", "//Edit[@Name='Price List']");
//        selectAndValidateData(common.getData(dataFile,"priceList"),"xpath", "//Edit[@Name='Price List']");
//        common.clickElement("xpath","//Edit[@Name='Port Code']");
//        selectOptionalMaster(common.getData(dataFile,"portCode"),"xpath","//Edit[@Name='Port Code']");
//        common.clickElement("xpath","//Edit[@Name='Remarks']");
//        selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
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
        Thread.sleep(10000);

        //* click on check-box through stock in quantity
//        WebElement stock=common.findWebElement("xpath","//Window[@Name='Batch Details']/Pane/Pane/Table[@Name='Batch Details']/*[@Name='Data Panel']/ListItem/Item[@Name='Expiry Date row 1']");
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

        List<WebElement> rows = common.findWebElements("xpath", "//Table[@Name='Serial Numbers List']/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("Row count: " + rows.size());
        int rowsToIterate = Math.min(numRowsToSelect, rows.size());
        for (int i = 0; i < rowsToIterate; i++) {
            WebElement row = rows.get(i); // Get the i-th row in the list
                WebElement checkBox = row.findElement(By.xpath("//Item[contains(@Name,'Select row')])"));
                checkBox.click();
            }
//        common.clickElement("xpath", "//Edit[@Name='MRP Row 2, Not sorted.']");
        super.enterData("xpath", "//Edit[@Name='MRP Row 2, Not sorted.']", dataFile, "productMRP");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",500,0);
//        common.clickElement("xpath", "//Edit[@Name='Minimum Rate * Row 2, Not sorted.']");
        super.enterData("xpath", "//Edit[@Name='Minimum Rate * Row 2, Not sorted.']", dataFile, "minumumRate");
//        common.clickElement("xpath", "//Edit[@Name='Maximum Rate * Row 2, Not sorted.']");
        super.enterData("xpath", "//Edit[@Name='Maximum Rate * Row 2, Not sorted.']", dataFile, "maximumRate");
//        common.clickElement("xpath", "//Edit[@Name='Unit Rate Row 2, Not sorted.']");
        super.enterData("xpath", "//Edit[@Name='Unit Rate Row 2, Not sorted.']", dataFile, "unitRate");

        //save
//        transactionSave();
//        lastTransactionName();
    }
}
