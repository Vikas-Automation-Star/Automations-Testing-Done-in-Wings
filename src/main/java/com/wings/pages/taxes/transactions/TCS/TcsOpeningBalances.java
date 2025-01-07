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
//        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
//        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectMaster(common.getData(dataFile, "branch"));
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        selectMaster(common.getData(dataFile,"currency"));
        common.clickElement("xpath", "//Edit[@Name='Control Account *']");
        selectAndValidateData(common.getData(dataFile,"controlAccount"),"xpath","//Edit[@Name='Control Account *']" );
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateDataNew(common.getData(dataFile, "executive"),"xpath","//Edit[@Name='Executive *']");
//        common.clickElement("xpath","//Edit[@Name='Remarks']");
//        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        enterData("xpath","//Edit[@Name='Account Code Row 0, Not sorted.']",dataFile,"PCode");
        enterData("xpath","//Edit[@Name='TCS Assesse Type * Row 0, Not sorted.']",dataFile,"tcsAssesType");
        enterData("xpath","//Edit[@Name='TCS Transaction Nature * Row 0, Not sorted.']",dataFile,"tcsTransNature");
        Thread.sleep(2000);
        enterData("xpath","//Edit[@Name='Sub Type * Row 0, Not sorted.']",dataFile,"tcsSubType");
        enterData("xpath","//Edit[@Name='TCS Account * Row 0, Not sorted.']",dataFile,"tcsAccount");
        enterData("xpath","//Edit[@Name='TCS Assessable Value Row 0, Not sorted.']",dataFile,"tcsAssesableValue");
        enterData("xpath","//Edit[@Name='TCS Rate Row 0, Not sorted.']",dataFile,"TcsRate");
//        enterData("xpath","//Edit[@Name='TCS Amount * Row 0, Not sorted.']",dataFile,"amount");
        transactionSave();
        Thread.sleep(1000);
    }
}
