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
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Bank Reconciliation']");
        Thread.sleep(1000);
        super.lastTransactionName();
        //enter data
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        super.selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMaster(common.getData(dataFile, "branch"));
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        super.selectMaster(common.getData(dataFile, "transaction"));
        common.clickElement("xpath", "//Edit[@Name='Bank Code']");
        super.selectMaster(common.getData(dataFile, "bankCode"));
        common.clickElement("xpath", "//Edit[@Name='Bank Account *']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        super.selectMaster(common.getData(dataFile, "executive"));
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        Thread.sleep(3000);
        common.sliderHandling("xpath","//Thumb[@Name='Position']",0,-150);
        //f3-accounts
        common.clickElement("xpath","//CheckBox[@Name='Clearing Status * Row 0']");
        common.deleteInvalidRows();
        //save
        super.transactionSave();
        super.lastTransactionName();
        super.transactionClose("Bank Reconciliation");

//        super.checkBoxSelection("xpath","//Table[@Name='Accounts']/*[starts-with(@Name,'Row')]","//Edit[starts-with(@Name,'Towards VNo *')]","//CheckBox[starts-with(@Name,'Clearing Status *')]");


    }
}
