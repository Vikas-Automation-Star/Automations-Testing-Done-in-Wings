package com.wings.pages.finance.transactions.Banking;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;

public class DepositPostDatedCheques extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public DepositPostDatedCheques(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void postDatedChques() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToDepositPostDatedChequesMenu();
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
        selectAndValidateData(common.getData(dataFile,"bankCode"),"xpath", "//Edit[@Name='Bank Code']" );
        common.clickElement("xpath", "//Edit[@Name='Cheques Received Account *']");
        selectAndValidateDataNew(common.getData(dataFile,"cheques"), "xpath", "//Edit[@Name='Cheques Received Account *']");
//        enterDataAndValidate("xpath", "//Edit[@Name='Cheques Received Account *']",dataFile,"cheques");
        Thread.sleep(1500);
        common.clickElement("xpath","//CheckBox[@Name='Select Row 0']");
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile, "executive"),"xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        //f3-accounts
        common.clickElement("xpath","//CheckBox[@Name='Deposited * Row 0']");
        //f7 summary

        common.clickElement("xpath", "//TabItem[contains(@Name,'Summary')]");
        //save
        transactionSave();
        lastTransactionName();
    }
}
