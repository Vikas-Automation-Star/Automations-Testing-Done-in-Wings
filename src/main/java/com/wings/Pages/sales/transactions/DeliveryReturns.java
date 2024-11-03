package com.wings.pages.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.pages.Transaction;
import com.wings.utils.Common;

import java.io.IOException;

public class DeliveryReturns extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public DeliveryReturns(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(driver);
        dataFile = file;
    }

    public void deliveryreturns() throws InterruptedException, IOException, ParseException, IOException, ParseException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Deliveries");
        common.clickElement("xpath", "//MenuItem[@Name='Delivery Returns']");
        Thread.sleep(3000);
        lastTransactionName();
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        selectMaster(common.getData(dataFile, "partyCode"));
        gstTransactionType(common.getData(dataFile, "gstType"));
        Thread.sleep(1500);
        common.clickElement("xpath", "//CheckBox[@Name='Select Row 1']");
        common.clickElement("xpath", "//Button[@Name='Ok']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        //items
        common.sliderHandling("name","Position",200,0);
        enterData("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']", dataFile, "quantity");
        //save
        transactionSave();
        lastTransactionName();
    }
}
