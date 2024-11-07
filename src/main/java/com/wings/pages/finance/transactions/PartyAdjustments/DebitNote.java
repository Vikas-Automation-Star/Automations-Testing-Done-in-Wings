package com.wings.pages.finance.transactions.PartyAdjustments;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;

public class DebitNote extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public DebitNote(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void debitNote() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Party Adjustments");
        common.clickElement("xpath", "//MenuItem[@Name='Debit Note']");
        Thread.sleep(1000);
        lastTransactionName();
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"),"xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        selectAndValidateData(common.getData(dataFile, "partyCode"),"xpath", "//Edit[@Name='Party Code']");
        common.clickElement("xpath", "//Edit[@Name='Party Account *']");
        Thread.sleep(2000);
        common.clickElement("xpath","//Edit[@Name='Invoice Type']/Button[@Name='Open']");
        selectDropDown("Regular");
        Thread.sleep(10000);
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        //f3-accounts
        enterDataAndValidate("xpath","//Edit[@Name='Account Code Row 0, Not sorted.']",dataFile,"accountCode");
        enterData("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,"amount");
        enterDataAndValidate("xpath","//Edit[@Name='TDS Transaction Nature Row 0, Not sorted.']",dataFile,"TDSNature");
        enterDataAndValidate("xpath","//Edit[@Name='TDS Account Row 0, Not sorted.']",dataFile,"TDSAccount");
        enterData("xpath","//Edit[@Name='TDS Amount Row 0, Not sorted.']",dataFile,"TDSAmount");
        //f5-bills payable
        navigateToBillsPayablesTab();
        checkBoxSelection("xpath","//Table[@Name='BillsPayable']/*[starts-with(@Name,'Row')]","//Edit[starts-with(@Name,'Towards VNo *')]","//CheckBox[starts-with(@Name,'Adjust Row')]");
        //f8- summary
        navigateToSummaryTab();
        //check net and payable amount --net>payable
        WebElement netAmount= common.findWebElement("xpath","//Edit[@Name='Net Amount']");
        String net=netAmount.getText();
        System.out.println(net);
        WebElement payable= common.findWebElement("xpath","//Edit[@Name='Bills Payable Adj']");
        String payableAmount= payable.getText();
        System.out.println(payableAmount);
            try {
                double netValue = Double.parseDouble(net);
                double payableValue = Double.parseDouble(payableAmount);

                if (netValue < payableValue) {
                    Assert.fail("Net amount is less than pending amount, enter correct amount");
                }
            }catch (NumberFormatException e) {
                System.out.println("Error parsing amounts: " + e.getMessage());
            }

        //save
        transactionSave();
        lastTransactionName();
    }
}
