package com.wings.pages.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SalesInvoice extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesInvoice(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void salesInvoice() throws InterruptedException, IOException, ParseException, AWTException {
       navigateToSalesInvoiceMenu();
        Thread.sleep(1000);
        //validate title
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
        int offset = 700;
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",offset,0);
        //save
        transactionSave();
        lastTransactionName();
    }
}
