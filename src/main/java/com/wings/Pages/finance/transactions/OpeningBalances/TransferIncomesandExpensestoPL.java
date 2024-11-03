package com.wings.pages.finance.transactions.OpeningBalances;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;

public class TransferIncomesandExpensestoPL extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public TransferIncomesandExpensestoPL(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void incomeAndExpenses() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Opening Balances");
        common.clickElement("xpath", "//MenuItem[@Name='Transfer Incomes and Expenses to PL']");
        Thread.sleep(1000);
        super.lastTransactionName();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMaster(common.getData(dataFile, "branch"));
        common.clickElement("xpath", "//Edit[@Name='Reserves And Surplus Account *']");
        super.selectMaster(common.getData(dataFile, "reserves"));
        //f5 - incomes
        common.clickElement("xpath","//TabItem[@Name='  F5 Incomes  ']");
        //f8 summary
        common.clickElement("xpath","//TabItem[@Name='  F8 Summary  ']");

        //save
        super.transactionSave();
        super.lastTransactionName();
        super.transactionClose(common.getData(dataFile,"close"));


    }
}
