package com.wings.pages.finance.transactions.Receipts;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.util.List;
import java.io.IOException;

public class CashReceipts extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    double finalAmount=0.0;


    public CashReceipts(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void cashReceipt() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Cash Receipts']");
        Thread.sleep(1000);
        lastTransactionName();
        //enter data
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"),"xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Cash A/c Code']");
        common.clickElement("xpath", "//Edit[@Name='Cash Account *']");
        common.clickElement("xpath","//Edit[@Name='Discount Account']");
        selectOptionalMaster(common.getData(dataFile,"discountAccount"),"xpath","//Edit[@Name='Discount Account']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile,"executive"), "xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        //f3-items
        Thread.sleep(5000);
        enterDataAndValidate("xpath", "//Edit[@Name='Party Code Row 0, Not sorted.']", dataFile,"partyCode");
        //check for bills receivable
        Thread.sleep(2500);
        navigateToBillsReceivablesTab();
        finalAmount=singleCheckBoxSelection("xpath","//Table[@Name='BillsReceivable']/*[starts-with(@Name,'Row')]","//Edit[starts-with(@Name,'Towards VNo *')]");
        //enter amount
        navigateToPartiesTab();
        List<WebElement> amount = common.findWebElements("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']");
        System.out.println("Size :" + amount.size());
        for (WebElement i : amount) {
            i.click();
            i.sendKeys(String.valueOf(finalAmount), Keys.TAB);
        }
        //check summary
        navigateToSummaryTab();
        //save
        transactionSave();
        lastTransactionName();
//        transactionClose(common.getData(dataFile,"close"));
    }
}
