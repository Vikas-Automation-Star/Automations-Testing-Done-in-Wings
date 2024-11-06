package com.wings.pages.finance.transactions.Payments;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PaymentToParties extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    double finalAmount=0.0;


    public PaymentToParties(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void paymentToParty() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Payments");
        common.clickElement("xpath", "//MenuItem[@Name='Payments to Parties']");
        Thread.sleep(1000);
        lastTransactionName();
        //enter data
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"),"xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        selectAndValidateData(common.getData(dataFile,"PartyCode"),"xpath", "//Edit[@Name='Party Code']");
        common.clickElement("xpath","//Edit[@Name='Discount Account']");
        selectOptionalMaster(common.getData(dataFile,"discountAccount"),"xpath","//Edit[@Name='Discount Account']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        //f3-items
        Thread.sleep(5000);
        enterDataAndValidate("xpath","//Edit[@Name='Cash Account * Row 0, Not sorted.']",dataFile,"cashAccount");
        Thread.sleep(1000);
        common.clickElement("xpath", "//CheckBox[@Name='Inclusive Tax Row 0']");
        //bills payable
        Thread.sleep(1500);
        navigateToBillsPayablesTab();
        finalAmount=singleCheckBoxSelection("xpath","//Table[@Name='BillsPayable']/*[starts-with(@Name,'Row')]","//Edit[starts-with(@Name,'Towards VNo *')]");
        System.out.println(finalAmount);
        //navigate back to f3-items and enter amount
        Thread.sleep(2000);
        navigateToCashTab();
        List<WebElement> amount = common.findWebElements("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']");
        for (WebElement i : amount) {
            i.click();
            i.sendKeys(String.valueOf(finalAmount), Keys.TAB);
        }
        //save
        transactionSave();
        lastTransactionName();
//        transactionClose(common.getData(dataFile,"close"));
    }
}
