package com.wings.pages.taxes.transactions.GST;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class Gstr1MonthEnd extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Gstr1MonthEnd(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void Gstr1MonthEnd() throws InterruptedException, IOException, ParseException {

        common.clickElement("name", "Taxes");
        common.clickElement("name", "GST");
        common.clickElement("name", "GSTR1");
        common.clickElement("name", "GSTR1 Month End");
        Thread.sleep(1000);
//        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
//        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        WebElement date = common.findWebElement("xpath", "//Edit[@Name='Date *']");
        date.clear();
        date.sendKeys(common.getData(dataFile, "date"));
        common.clickElement("xpath", "//Edit[@Name='GST Registration *']");
        selectMaster(common.getData(dataFile, "branch"));
        common.clickElement("xpath", "//Edit[@Name='GSTIN *']");
        WebElement element = driver.findElement("xpath", "//Edit[@Name='Year And Month *']");
        element.clear();
        element.sendKeys(common.getData(dataFile, "yearAndMonth"));
        common.clickElement("xpath", "//Edit[@Name='Month']");
        Thread.sleep(2000);
//        common.clickElement("xpath","//Edit[@Name='Remarks']");
//        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        common.clickElement("xpath", "//Edit[@Name='GSTIN / UIN of Recipient * Row 0, Not sorted.']");
        transactionSave();
        Thread.sleep(1000);
        super.closeTransaction("GSTR1 Month End");
    }
}
