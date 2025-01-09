package com.wings.pages.finance.transactions.PartyAdjustments;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class AdjustPartyBills extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public AdjustPartyBills(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void executeAdjustPartyBills() throws InterruptedException, IOException, ParseException {
        System.out.println("-----Party Bills Start--------");
        navigateToAdjustPartyBills();
        lastTransactionName();
        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        selectAndValidateData(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Party Code']");
        common.clickElement("xpath", "//Edit[@Name='Party Account *']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        Thread.sleep(2000);
        //f3- Receivables
        navigateToBillsReceivablesTab();
        checkBoxSelection("xpath", "//Table[@Name='BillsReceivable']/*[starts-with(@Name,'Row')]", "//Edit[starts-with(@Name,'Towards VNo *')]", "//CheckBox[starts-with(@Name,'Adjust Row')]");
        //f5- Payable
        navigateToBillsPayablesTab();
        checkBoxSelection("xpath", "//Table[@Name='BillsPayable']/*[starts-with(@Name,'Row')]", "//Edit[starts-with(@Name,'Towards VNo *')]", "//CheckBox[starts-with(@Name,'Adjust Row')]");
        //f8- summary
        common.clickElement("xpath", "//TabItem[@Name='  F8 Summary  ']");
        WebElement receivable = common.findWebElement("xpath", "//Edit[@Name='Receivables Adj *']");
        WebElement payable = common.findWebElement("xpath", "//Edit[@Name='Payables Adj *']");
        System.out.println(receivable.getText() + "," + payable.getText());
        if (!receivable.getText().equals(payable.getText())) {
            Assert.fail("pls check the amount");
        }
        //save
        transactionSave();
        lastTransactionName();
        System.out.println("------Party Bills End--------");
    }
}
