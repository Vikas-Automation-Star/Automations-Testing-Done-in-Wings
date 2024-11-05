package com.wings.pages.taxes.transactions.TCS;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.apache.poi.ss.formula.functions.T;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class TcsPayments extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public TcsPayments(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void tcsPayments() throws InterruptedException, IOException, ParseException {

        common.clickElement("name", "Taxes");
        common.clickElement("name", "TCS");
        common.clickElement("name", "TCS Payments");
        Thread.sleep(1000);
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMaster(common.getData(dataFile, "branch"));
        WebElement startingYear = common.findWebElement("xpath", "//Edit[@Name='From Year And Month *']");
        startingYear.clear();
        startingYear.sendKeys(common.getData(dataFile, "startingYear"));
        WebElement endingYear = common.findWebElement("xpath", "//Edit[@Name='To Year And Month *']");
        endingYear.clear();
        endingYear.sendKeys(common.getData(dataFile, "endingYear"));
        WebElement element1=common.findWebElement("xpath", "//Edit[@Name='TCS Sub Type *']");
        element1.click();
        common.clickElement("xpath","//Edit[@Name='TCS Account *']");
        common.clickElement("xpath","//CheckBox[@Name='Select Row 0']");
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.inputText("xpath", "//Edit[@Name='Bank Challan No']", common.getData(dataFile, "challanNo"));
        common.inputText("xpath", "//Edit[@Name='BSR Code']", common.getData(dataFile, "bsrCode"));
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        super.selectMaster(common.getData(dataFile, "executive"));
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        Thread.sleep(2000);
        common.clickElement("xpath","//CheckBox[@Name='TCS Paid Row 0']");
        common.clickElement("xpath","//CheckBox[@Name='TCS Sale Return Row 0']");
//        common.clickElement("xpath", "//CheckBox[@Name='TDS Paid * Row 0']");
        common.clickElement("xpath", "//TabItem[@Name='  F5 Cheques  ']");
        common.clickElement("xpath", "//Edit[@Name='Bank Account Code Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Bank Account Code Row 0, Not sorted.']",common.getData(dataFile, "bankCode"));
        common.clickElement("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']", common.getData(dataFile, "amount"));
        common.clickElement("xpath", "//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']", common.getData(dataFile, "checkNO"));
        common.clickElement("xpath", "//Edit[@Name='Cheque Date * Row 0, Not sorted.']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//TabItem[@Name='  F9 Summary  ']");
        super.saveTransaction();
        Thread.sleep(1000);
        super.closeTransaction("TCS Payments");
        Thread.sleep(2000);
    }
}
