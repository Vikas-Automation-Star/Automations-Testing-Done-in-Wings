package com.wings.pages.taxes.transactions.TDS;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;


import java.io.IOException;

public class TdsOpeningBalences extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public TdsOpeningBalences(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void tdsOpeningBalences() throws InterruptedException, IOException, ParseException {

        common.clickElement("name", "Taxes");
        common.clickElement("name", "TDS");
        common.clickElement("name", "TDS Opening Balances");
        Thread.sleep(1000);
//        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
//        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectMaster(common.getData(dataFile, "branch"));
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Control Account *']");
        selectAndValidateData(common.getData(dataFile, "controlAccount"), "xpath", "//Edit[@Name='Control Account *']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        super.selectAndValidateDataNew(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
//        common.clickElement("xpath","//Edit[@Name='Remarks']");
//        selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        Thread.sleep(1000);
        enterData("xpath", "//Edit[@Name='Account Code Row 0, Not sorted.']", dataFile, "PCode");
        enterData("xpath", "//Edit[@Name='TDS Assesse Type * Row 0, Not sorted.']", dataFile, "TDSAssessType");
        enterData("xpath", "//Edit[@Name='TDS Transaction Nature * Row 0, Not sorted.']", dataFile, "TdsTransactionNature");
        enterData("xpath", "//Edit[@Name='Sub Type * Row 0, Not sorted.']", dataFile, "tdsSubType");
        enterData("xpath", "//Edit[@Name='TDS Account * Row 0, Not sorted.']", dataFile, "tdsAccount");
        enterData("xpath", "//Edit[@Name='TDS Assessable Value Row 0, Not sorted.']", dataFile, "tdsAssesbleValue");
        enterData("xpath", "//Edit[@Name='TDS Rate Row 0, Not sorted.']", dataFile, "TdsRate");
//        enterData("xpath","//Edit[@Name='TDS Amount * Row 0, Not sorted.']",dataFile,"amount");
        transactionSave();
        Thread.sleep(1000);
        closeTransaction("TDS Opening Balances");
        Thread.sleep(2000);
    }
}
