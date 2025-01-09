package com.wings.pages.sales.transactions;

import com.wings.pages.SalesOrdersBaseClass;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class SalesOrderValidation extends SalesOrdersBaseClass {
    protected String dataFile;

    public SalesOrderValidation(WindowsDriver driver, String file) {
        super(driver, file);
        dataFile = file;
    }

    public void navigateToSalesOrder() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Orders']");
    }

    public void voucherType() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        super.selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
    }

    public void branch_baseClass() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectOptionalMaster(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
    }

    public void transCurrency() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        super.selectOptionalMaster(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Trans Currency *']");
    }

    public void partyCodes() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        super.selectOptionalMaster(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Party Code']");
    }

    public void priceList_baseClass() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        super.selectOptionalMaster(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
    }

    public void executives() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        super.selectOptionalMaster(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
    }

    public void remarks_baseClass() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
    }


    public void transactionSaveCheck() throws InterruptedException {
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        Thread.sleep(2500);
        common.clickElement("xpath", "//Window[@Name='Error']/Button[@Name='OK']");
    }

}
