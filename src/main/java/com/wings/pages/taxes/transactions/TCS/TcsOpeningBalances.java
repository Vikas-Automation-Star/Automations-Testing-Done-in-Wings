package com.wings.pages.taxes.transactions.TCS;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class TcsOpeningBalances extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public TcsOpeningBalances(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void tcsOpeningBalances() throws InterruptedException, IOException, ParseException {

        common.clickElement("name", "Taxes");
        common.clickElement("name", "TCS");
        common.clickElement("name", "TCS Opening Balances");
        Thread.sleep(1000);
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMaster(common.getData(dataFile, "branch"));
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        super.selectMaster(common.getData(dataFile,"currency"));
        common.clickElement("xpath", "//Edit[@Name='Control Account *']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        super.selectAndValidateDataNew(common.getData(dataFile, "executive"),"xpath","//Edit[@Name='Executive *']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        common.clickElement("xpath", "//Edit[@Name='Account Code Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='Account Code Row 0, Not sorted.']",common.getData(dataFile, "Pcode"));

        common.clickElement("xpath", "//Edit[@Name='TCS Assesse Type * Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='TCS Assesse Type * Row 0, Not sorted.']",common.getData(dataFile, "tcsAsseseeType"));
        common.clickElement("xpath", "//Edit[@Name='TCS Transaction Nature * Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='TCS Transaction Nature * Row 0, Not sorted.']",common.getData(dataFile, "tcsTransNature"));
        Thread.sleep(2000);

        common.clickElement("xpath", "//Edit[@Name='Sub Type * Row 0, Not sorted.']");

        WebElement subType = common.findWebElement("xpath", "//Edit[@Name='Sub Type * Row 0, Not sorted.']");
        Actions actions = new Actions(driver);
        actions.doubleClick(subType).perform();
        subType.sendKeys(common.getData(dataFile, "tcsSubType"));

        common.clickElement("xpath", "//Edit[@Name='TCS Account * Row 0, Not sorted.']");
        WebElement TcsAct = common.findWebElement("xpath", "//Edit[@Name='TCS Account * Row 0, Not sorted.']");
        Actions actions3 = new Actions(driver);
        actions3.doubleClick(TcsAct).perform();
        TcsAct.sendKeys(common.getData(dataFile, "tcsAccount"));

        common.clickElement("xpath", "//Edit[@Name='TCS Assessable Value Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='TCS Assessable Value Row 0, Not sorted.']", common.getData(dataFile, "tcsAssesableValue"));
        common.clickElement("xpath", "//Edit[@Name='TCS Rate Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='TCS Assessable Value Row 0, Not sorted.']", common.getData(dataFile, "TcsRate"));
        common.clickElement("xpath", "//Edit[@Name='TCS Amount * Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='TCS Amount * Row 0, Not sorted.']", common.getData(dataFile,"amount"));
        transactionSave();
        Thread.sleep(1000);
        super.closeTransaction("TCS Opening Balances");
        Thread.sleep(2000);
    }
}
