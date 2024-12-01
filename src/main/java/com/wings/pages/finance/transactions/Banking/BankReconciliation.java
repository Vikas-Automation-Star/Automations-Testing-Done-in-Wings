package com.wings.pages.finance.transactions.Banking;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.pages.Transaction;
import com.wings.utils.Common;

import java.awt.*;
import java.io.IOException;

public class BankReconciliation extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public BankReconciliation(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void bankReconciliation() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToBankReconciliationMenu();
        Thread.sleep(1000);
        lastTransactionName();
        //enter data
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"),"xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Bank Code']");
        selectAndValidateData(common.getData(dataFile, "bankCode"),"xpath", "//Edit[@Name='Bank Code']");
        common.clickElement("xpath", "//Edit[@Name='Bank Account *']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile, "executive"),"xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        Thread.sleep(3000);
        common.sliderHandling("xpath","//Thumb[@Name='Position']",0,-150);
        //f3-accounts
        common.clickElement("xpath","//CheckBox[@Name='Clearing Status * Row 0']");
        common.deleteInvalidRows();
        //save
        transactionSave();
        lastTransactionName();
    }
}
