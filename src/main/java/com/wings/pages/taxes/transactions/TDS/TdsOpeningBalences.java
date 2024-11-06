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
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath","//Edit[@Name='Branch *']");
        super.selectMaster(common.getData(dataFile,"branch"));
        common.clickElement("xpath","//Edit[@Name='Transaction Currency *']");
        common.clickElement("xpath","//Edit[@Name='Control Account *']");
        common.clickElement("xpath","//Edit[@Name='Executive *']");
        super.selectMaster(common.getData(dataFile,"executive"));
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        common.clickElement("xpath","//Edit[@Name='Account Code Row 0, Not sorted.']");
        super.selectMaster(common.getData(dataFile,"Pcode"));
        Thread.sleep(2000);
        common.clickElement("xpath","//Button[@Name='Continue']");
        common.clickElement("xpath","//Edit[@Name='TDS Assesse Type * Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='TDS Assesse Type * Row 0, Not sorted.']", common.getData(dataFile,"tdsAssesseType"));
        common.clickElement("xpath","//Edit[@Name='TDS Transaction Nature * Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='TDS Transaction Nature * Row 0, Not sorted.']", common.getData(dataFile,"TdsTransactionNature"));
        common.clickElement("xpath","//Edit[@Name='Sub Type * Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Sub Type * Row 0, Not sorted.']", common.getData(dataFile,"tdsSubType"));
        common.clickElement("xpath","//Edit[@Name='TDS Account * Row 0, Not sorted.']");
        common.clickElement("xpath","//Edit[@Name='TDS Account * Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='TDS Account * Row 0, Not sorted.']",common.getData(dataFile,"tdsAccount"));
        common.clickElement("xpath","//Edit[@Name='TDS Assessable Value Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='TDS Assessable Value Row 0, Not sorted.']", common.getData(dataFile,"tdsAssesableValue"));
        common.clickElement("xpath","//Edit[@Name='TDS Rate Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='TDS Assessable Value Row 0, Not sorted.']", common.getData(dataFile,"TdsRate"));
        common.clickElement("xpath","//Edit[@Name='TDS Amount * Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='TDS Amount * Row 0, Not sorted.']",common.getData(dataFile,"amount"));
        super.saveTransaction();
        Thread.sleep(1000);
        super.closeTransaction("TDS Opening Balances");
        Thread.sleep(2000);
    }
}
