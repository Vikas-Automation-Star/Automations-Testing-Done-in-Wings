package com.wings.pages.finance.transactions.Receipts;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;

public class ReceiptsFromCreditCardCompanies extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public ReceiptsFromCreditCardCompanies(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void creditCardCompanyReceipt() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToReceiptsFromCreditCardCompanyMenu();
        Thread.sleep(1000);
        lastTransactionName();
        //enter data
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"),"xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Credit Card Company *']");
        selectAndValidateData(common.getData(dataFile,"ccc"),"xpath", "//Edit[@Name='Credit Card Company *']" );
        intraGSTRegistration(common.getData(dataFile,"gstType"));
        Thread.sleep(2500);
        common.clickElement("xpath","//CheckBox[@Name='Select Row 0']");
        common.clickElement("name","Ok");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Bank Account *']");
        Thread.sleep(1000);
        common.inputAndVerify("xpath","//Edit[@Name='Cheque/EFT No *']", common.getData(dataFile,"cheque"));
        common.clickElement("xpath", "//Edit[@Name='Drawn On Bank *']");
        common.clickElement("xpath","//Edit[@Name='Drawn On Bank Branch']");
        selectOptionalMaster(common.getData(dataFile,"Drawn On Bank Branch"),"xpath","//Edit[@Name='Drawn On Bank Branch']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile, "executive"),"xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        //f3-accounts
        Thread.sleep(5000);
        common.clickElement("xpath","//CheckBox[@Name='Received * Row 0']");
        //f5-credit card company charges
        navigateToCreditCardCompanyChargesTab();
        enterDataAndValidate("xpath","//Edit[@Name='Account Code Row 0, Not sorted.']",dataFile,"accountCode");
        enterData("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,"amount");
        //summary
        navigateToSummaryTab();
        //save
        transactionSave();
        lastTransactionName();
//        transactionClose(common.getData(dataFile,"close"));
    }
}
