package com.wings.pages.finance.transactions.OpeningBalances;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
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
        navigateToTransferIncomesAndExpensesToPLMenu();
        Thread.sleep(1000);
        lastTransactionName();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Reserves And Surplus Account *']");
        selectAndValidateData(common.getData(dataFile, "reserves"), "xpath", "//Edit[@Name='Reserves And Surplus Account *']");
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        //f5 - incomes
        navigateToIncomesTab();
        //f8 summary
        navigateToSummaryTab();
        //save
        transactionSave();
        lastTransactionName();
    }
}