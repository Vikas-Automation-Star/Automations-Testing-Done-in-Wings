package com.wings.pages;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class PurchaseOrderBaseClass extends Transaction {
    protected String dataFile;

    public PurchaseOrderBaseClass(WindowsDriver driver, String file) {
        super(driver);
        dataFile = file;
    }

    public void voucherType() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
    }

    public void branchSelection() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMasterWithValidation(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
    }

    public void location() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        selectOptionalMaster(common.getData(dataFile, "location"), "xpath", "//Edit[@Name='Location *']");
    }

    public void chooseCurrency() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        super.selectMasterWithValidation(common.getData(dataFile, "currency"), "xpath", "//Edit[@Name='Trans Currency *']");
    }

    public void partyCode() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        super.selectMasterWithValidation(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Party Code']");
    }

    public void executive() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        super.selectMasterWithValidation(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
    }

    public void remarks() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
    }

}
