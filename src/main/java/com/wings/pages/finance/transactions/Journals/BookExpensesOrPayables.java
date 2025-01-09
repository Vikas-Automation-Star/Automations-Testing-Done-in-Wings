package com.wings.pages.finance.transactions.Journals;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class BookExpensesOrPayables extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    double finalAmount = 0.0;

    public BookExpensesOrPayables(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void payables() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToBookExpensesOrPayablesMenu();
        Thread.sleep(1000);
        lastTransactionName();
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Account Code']");
        selectAndValidateData(common.getData(dataFile, "accountCode"), "xpath", "//Edit[@Name='Account Code']");
        common.clickElement("xpath", "//Edit[@Name='Account *']");
        common.inputText("xpath", "//Edit[@Name='Supplier Bill No *']", String.valueOf(common.getRandom()));
        common.inputText("xpath", "//Edit[@Name='Supplier Bill Date *']", common.getData(dataFile, "billDate"));
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        //f3- accounts
        enterData("xpath", "//Edit[@Name='Account Code Row 0, Not sorted.']", dataFile, "accountCode2");
        //f12-bills Payable
        navigateToBillsReceivablesTab();
        finalAmount = singleCheckBoxSelection("xpath", "//Table[@Name='BillsReceivable']/*[starts-with(@Name,'Row')]", "//Edit[starts-with(@Name,'Towards VNo *')]");
        //navigate back to accounts and enter data
        navigateToAccountsTab();
        List<WebElement> amount = common.findWebElements("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']");
        System.out.println("Size :" + amount.size());
        for (WebElement i : amount) {
            i.click();
            i.sendKeys(String.valueOf(finalAmount), Keys.TAB);
        }
        //summary
        navigateToSummaryTab();
        //save
        transactionSave();
        lastTransactionName();
    }
}
