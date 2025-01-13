package com.wings.pages.finance.transactions.PartyAdjustments;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class CreditNoteFromSupplier extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    double finalAmount = 0.0;


    public CreditNoteFromSupplier(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void creditNoteFromSupplier() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToCreditNoteFromSupplierMenu();
        Thread.sleep(1000);
        lastTransactionName();
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.inputText("xpath", "//Edit[@Name='Purchase VNo *']", common.getData(dataFile, "voucherNo"));
        common.inputText("xpath", "//Edit[@Name='Voucher Date *']", common.getData(dataFile, "voucherDate"));
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        selectAndValidateData(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Party Code']");
        common.inputText("xpath", "//Edit[@Name='Supplier Bill No *']", String.valueOf(common.getRandom()));
        common.inputText("xpath", "//Edit[@Name='Supplier Bill Date *']", common.getData(dataFile, "supplierDate"));
//            Thread.sleep(20000);
        common.clickElement("xpath", "//Edit[@Name='Reason For Issuing Document']/Button[@Name='Open']");
        Thread.sleep(7000);
        selectDropDown(common.getData(dataFile, "reason"));
        Thread.sleep(20000);
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        //f3-accounts
        enterData("xpath", "//Edit[@Name='Account Code Row 0, Not sorted.']", dataFile, "accountCode");
        common.clickElement("xpath", "//CheckBox[@Name='Inclusive Tax Row 0']");
        //f5-bills payable
        navigateToBillsPayablesTab();
        enterData("xpath","//Edit[@Name='Amount Adjusted * Row 0, Not sorted.']",dataFile,"adjustedAmount");
        common.deleteInvalidRows();
        //navigate back to accounts and enter amount
        navigateToAccountsTab();
        List<WebElement> amount = common.findWebElements("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']");
        System.out.println("Size :" + amount.size());
        for (WebElement i : amount) {
            i.click();
            i.sendKeys(common.getData(dataFile,"adjustedAmount"), Keys.TAB);
        }
        //check summary
        common.clickElement("xpath", "//TabItem[contains(@Name,'Summary')]");
        //check net and payable amount --net>payable
        WebElement netAmount = common.findWebElement("xpath", "//Edit[@Name='Net Amount']");
        String net = netAmount.getText();
        System.out.println(net);
        WebElement payable = common.findWebElement("xpath", "//Edit[@Name='Bills Payable Adj']");
        String payableAmount = payable.getText();
        System.out.println(payableAmount);
        try {
            double netValue = Double.parseDouble(net);
            double payableValue = Double.parseDouble(payableAmount);

            if (netValue < payableValue) {
                Assert.fail("Net amount is less than pending amount, enter correct amount");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error parsing amounts: " + e.getMessage());
        }
        //save
        transactionSave();
        lastTransactionName();
    }
}
