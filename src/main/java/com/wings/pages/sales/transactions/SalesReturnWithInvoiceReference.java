package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class SalesReturnWithInvoiceReference extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesReturnWithInvoiceReference(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(driver);
        dataFile = file;
    }

    public void salesReturnWithInvoiceReference() throws InterruptedException, IOException, ParseException {
        navigateToSalesReturnWithInvoiceReferenceMenu();
        Thread.sleep(3000);
        lastTransactionName();
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectAndValidateData(common.getData(dataFile, "location"), "xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Trans Currency *']");
//        common.clickElement("xpath","//Edit[@Name='Sales Invoice No *']");
        common.inputText("xpath", "//Edit[@Name='Sales Invoice No *']", common.getData(dataFile, "invoiceNo"));
        Thread.sleep(1500);
        gstTransactionType(common.getData(dataFile, "gstType"));
        common.clickElement("xpath", "//Edit[@Name='Sales Return A/c Code']");
        common.clickElement("xpath", "//CheckBox[@Name='Apply TCS']");
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        //f3-items
        common.sliderHandling("name", "Position", 100, 0);
        enterData("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']", dataFile, "quantity");
        //bills receivable
        navigateToBillsReceivablesTab();
        common.deleteInvalidRows();
        //save
        transactionSave();
        lastTransactionName();
    }
}
