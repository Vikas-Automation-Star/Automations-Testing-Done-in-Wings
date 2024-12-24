package com.wings.pages.finance.transactions.Banking;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;

public class InterBankFundTransfers extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public InterBankFundTransfers(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void bankFundTransfer() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToInterBankFundTransferMenu();
        Thread.sleep(1000);
        lastTransactionName();
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"),"xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='From Bank Code']");
        selectAndValidateData(common.getData(dataFile, "fromBank"),"xpath", "//Edit[@Name='From Bank Code']");
        common.clickElement("xpath", "//Edit[@Name='To Bank Code']");
        selectAndValidateData(common.getData(dataFile, "toBank"),"xpath", "//Edit[@Name='To Bank Code']");
        common.inputText("xpath","//Edit[@Name='Amount *']", common.getData(dataFile,"amount"));
        common.inputText("xpath","//Edit[@Name='Cheque/EFT No *']", common.getData(dataFile,"cheque"));
        common.clickElement("xpath","//Edit[@Name='Charges Account Code']");
        common.inputText("xpath","//Edit[@Name='Transfered Charges']", common.getData(dataFile,"charges"));
        //slider
        common.sliderHandling("xpath","//Thumb[@Name='Position']", 550,0);
        common.inputText("xpath","//Edit[@Name='Supplier Bill No *']", common.getData(dataFile,"supplier Bill"));
        common.inputText("xpath","//Edit[@Name='Supplier Bill Date *']", common.getData(dataFile,"supplierDate"));
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        Thread.sleep(2000);
        navigateToSummaryTab();
        //save
        transactionSave();
        lastTransactionName();
    }
}