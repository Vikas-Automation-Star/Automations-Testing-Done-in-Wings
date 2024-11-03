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
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Deposit Post Dated Cheques']");
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
        super.selectAndValidateData(common.getData(dataFile,"bankCode"),"xpath", "//Edit[@Name='Bank Code']" );

        common.clickElement("xpath", "//Edit[@Name='Cheques Received Account *']");
        super.selectAndValidateData(common.getData(dataFile,"cheques"),"xpath", "//Edit[@Name='Cheques Received Account *']" );
        common.clickElement("xpath","//CheckBox[@Name='Select Row 0']");
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        super.selectMaster(common.getData(dataFile, "executive"));
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        //f3-accounts
        common.clickElement("xpath","//CheckBox[@Name='Deposited * Row 0']");
        //f7 summary
        common.clickElement("xpath","//TabItem[@Name='  F7 Summary  ']");

        //save
        super.transactionSave();
        super.lastTransactionName();
        super.transactionClose(common.getData(dataFile,"close"));



    }
}
