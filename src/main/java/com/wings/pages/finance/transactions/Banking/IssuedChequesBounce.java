package com.wings.pages.finance.transactions.Banking;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class IssuedChequesBounce extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public IssuedChequesBounce(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void issuedChequesBounce() throws InterruptedException, IOException, ParseException {

        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Issued Cheques Bounce']");
        Thread.sleep(3000);
        super.oldTTransaction();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMasterWithValidation(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        super.selectMasterWithValidation(common.getData(dataFile, "currency"),"xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath","//Edit[@Name='Bank Code']");
        super.selectMasterWithValidation(common.getData(dataFile,"bankCode"),"xpath","//Edit[@Name='Bank Code']");
        common.clickElement("xpath","//Edit[@Name='Bank Account *']");
        common.clickElement("xpath","//Edit[@Name='Account Code']");
        super.selectMasterWithValidation(common.getData(dataFile,"accountCode"),"xpath","//Edit[@Name='Account Code']");
        common.clickElement("xpath","//Edit[@Name='Account *']");
        Thread.sleep(4000);
        super.partyCodeGstSelection();
        common.clickElement("xpath","//Edit[@Name='Cheque/EFT No *']");
        common.inputText("xpath","//Edit[@Name='Cheque/EFT No *']",common.getData(dataFile,"chequeNo"));
        common.clickElement("xpath","//Edit[@Name='Drawn On Bank *']");
        super.selectMasterWithValidation(common.getData(dataFile,"drawnOnBank"),"xpath","//Edit[@Name='Drawn On Bank *']");
        super.generalInfoSliderHandle();
        common.clickElement("xpath","//Edit[@Name='Executive *']");
        super.selectMasterWithValidation(common.getData(dataFile,"executive"),"xpath","//Edit[@Name='Executive *']");
        Thread.sleep(3000);
        super.checkBoxSelectionBillsReceivables("xpath","//Table[@Name='BillsReceivable']/*[starts-with(@Name,'Row')]","//Edit[starts-with(@Name,'Towards VNo * Row')]","//CheckBox[starts-with(@Name,'Adjust Row ')]");
        Thread.sleep(3000);
        common.clickElement("xpath","//TabItem[@Name='  F11 Summary  ']");
        WebElement adjustedAmount=common.findWebElement("xpath","//Edit[@Name='Biils Receivable Adjusted']");
        adjustedAmount.click();
        System.out.println("Adjusted Amount :"+adjustedAmount.getText());
        Thread.sleep(2000);
        super.genaralInfoNegativeSliderHandle();
        Thread.sleep(2000);
        WebElement amount=common.findWebElement("xpath","//Edit[@Name='Amount *']");
        amount.click();
        amount.sendKeys(common.getData(dataFile,"amount"));
        System.out.println("Amount :"+amount.getText());
        try {
            double amountValue = Double.parseDouble(amount.getText());
            double adjustedAmountValue = Double.parseDouble(adjustedAmount.getText());

            if (amountValue >= adjustedAmountValue) {
                common.clickElement("xpath","//TabItem[@Name='  F11 Summary  ']");
            }
        } catch (NumberFormatException e) {
            System.out.println("Exception Handled");
            System.out.println("Adjusted amount is greater than or equals to payable amount..now you can save the transaction");
        }
        super.saveTransaction();
        super.newTransaction();
        super.closeTransaction("Issued Cheques Bounce");
        Thread.sleep(2000);

    }
}
