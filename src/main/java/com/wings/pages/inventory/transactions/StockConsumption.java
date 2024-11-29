package com.wings.pages.inventory.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;

public class StockConsumption extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public StockConsumption(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void stockConsumption() throws InterruptedException, AWTException, IOException, ParseException {
        navigateToStockConsumptionMenu();
        lastTransactionName();
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
        Thread.sleep(5000);
        WebElement gst= common.findWebElement("xpath","//Window[@Name='GST Transaction Type']");
        gst.click();
        gstTransactionTypeNew("Registered");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Edit[@Name='Stock Consumption Account']");
        selectAndValidateDataNew(common.getData(dataFile, "consumptionAccount"), "xpath", "//Edit[@Name='Stock Consumption Account']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectAndValidateData(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        //F3-Items
        common.clickElement("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        selectAndValidateData(common.getData(dataFile, "productCode"), "xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']");
        enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", dataFile, "Quantity");
        common.sliderHandling("name", "Position", 500, 0);
        //save
        transactionSave();
        lastTransactionName();
    }
}
