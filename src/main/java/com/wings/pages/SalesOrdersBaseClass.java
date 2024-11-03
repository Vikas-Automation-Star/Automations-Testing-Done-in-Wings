package com.wings.pages;

import io.appium.java_client.windows.WindowsDriver;
import net.bytebuddy.implementation.attribute.MethodAttributeAppender;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.wings.utils.Common;

import java.io.IOException;

public class SalesOrdersBaseClass extends Transaction{
    WindowsDriver baseClassdriver;
    Common common;
    String dataFile;

    public SalesOrdersBaseClass(WindowsDriver driver,String file){
        super(driver);
        baseClassdriver=driver;
        common=new Common(baseClassdriver);
        dataFile=file;
    }

    public void voucherType() throws IOException, ParseException {
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
            super.selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
    }

    public void branch_baseClass() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectAndValidateData(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
    }

    public void transCurrency() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        super.selectAndValidateData(common.getData(dataFile, "transaction"),"xpath", "//Edit[@Name='Trans Currency *']");
    }

    public void partyCodes() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        super.selectAndValidateData(common.getData(dataFile, "partyCode"),"xpath", "//Edit[@Name='Party Code']");
    }

    public void priceList_baseClass() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        super.selectAndValidateData(common.getData(dataFile,"priceList"),"xpath", "//Edit[@Name='Price List']");
    }

    public void executives() throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        super.selectAndValidateData(common.getData(dataFile,"executive"),"xpath", "//Edit[@Name='Executive *']");
    }

    public void remarks_baseClass() throws IOException, ParseException {
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
    }
}
