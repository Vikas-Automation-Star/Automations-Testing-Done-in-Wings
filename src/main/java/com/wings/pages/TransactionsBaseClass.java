package com.wings.pages;

import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//public class SalesOrdersBaseClass extends Transaction{
//    WindowsDriver baseClassdriver;
//    Common common;
//    String dataFile;
//
//    public SalesOrdersBaseClass(WindowsDriver driver,String file){
//        super(driver);
//        baseClassdriver=driver;
//        common=new Common(baseClassdriver);
//        dataFile=file;
//    }


public class TransactionsBaseClass extends Transaction {
    public TransactionsBaseClass(WindowsDriver driver) {
        super(driver);
    }

    public void enterBranchName(String dataFile,String sheetName,String branch) throws IOException, ParseException {
        EnterData("//Edit[@Name='Branch *']",dataFile,sheetName,branch);
//        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,dataSet,branch);
    }
    public void enterDate() throws IOException {
        inputTextWithValidation("xpath","//Edit[@Name='Date *']",Time.timeStamp());
    }

    public void EnterDate(String locator,String datFile, String sheetName, String column) throws IOException {
        String rawDate = getValueByColumnHeader(datFile, sheetName, column);
//        System.out.println("📅 Date from Excel column: " + rawDate);
        if (rawDate != null) {
            common.findWebElement("xpath", locator).sendKeys(rawDate);
        } else {
            throw new RuntimeException("❌ No value found under column: " + column);
        }
    }

      public void enterVoucherType(String dataFile,String sheetName,String voucherType) throws IOException, ParseException {
        EnterData("//Edit[@Name='Voucher Type']",dataFile,sheetName,voucherType);
//        enterInput("xpath","//Edit[@Name='Voucher Type']",dataFile,dataSet,voucherType);
    }

    public void enterLocation(String dataFile,String sheetName,String key) throws IOException, ParseException {
      EnterData("//Edit[@Name='Location *']",dataFile,sheetName,key);
//        enterInput("xpath","//Edit[@Name='Location *']",dataFile,dataSet,location);
    }
    public void enterCurrency(String dataFile,String sheetName,String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Trans Currency *'] | //Edit[@Name='Transaction Currency *']",dataFile,sheetName,key);
//        enterInput("xpath","//Edit[@Name='Trans Currency *']",dataFile,dataSet,currency);
    }

    public void enterExchangeRate(String dataFile,String sheetName,String key) throws IOException {
        EnterData("//Edit[@Name='Exchange Rate *']",dataFile,sheetName,key);
    }

    public void enterPartyCode(String dataFile,String sheetName, String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Party Code']",dataFile,sheetName,key);
        //        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile,dataset, key);
    }

    public void enterCustomerEmail(String dataFile,String dataSet,String email) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Customer Email']",dataFile,dataSet,email);
    }

    public void enterShippingBillNo(String dataFile,String dataSet,String shippingBillNo) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Shipping Bill No']",dataFile,dataSet, shippingBillNo);
    }

    public void enterPortCode(String dataFile,String dataSet,String portCode) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Port Code']",dataFile,dataSet, portCode);
    }

    public void enterRemarks(String dataFile,String sheetName,String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Remarks']",dataFile,sheetName,key);
//        enterInput("xpath","//Edit[@Name='Remarks']",dataFile,dataSet, remarks);
    }

    public void enterSalesAccountCode(String dataFile,String dataSet,String salesAccountCode) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Sales A/c Code']",dataFile,dataSet,salesAccountCode);
    }
    public void enterCustomerMobileNum(String dataFile,String dataSet,String mobileNum) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Customer Mobile Number']",dataFile,dataSet, mobileNum);
    }

    public void enterCashOrParty(String dataFile,String sheetName,String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Cash/Party Code' or @Name='Party Code']",dataFile,sheetName,key);
//        enterInput("xpath","//Edit[@Name='Cash/Party Code'] | //Edit[@Name='Party Code']",dataFile,dataSet,cashOrParty);
    }

    public void enterConsigner(String dataFile,String sheetName,String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Cash/Party Code']",dataFile,sheetName,key);
//        enterInput("xpath","//Edit[@Name='Cash/Party Code']",dataFile,dataSet,Consigner);
    }

    public void enterCreditPeriod(String dataFile,String sheetName,String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Credit Period']",dataFile,sheetName,key);
//        enterInput("xpath","//Edit[@Name='Credit Period']",dataFile,dataSet,creditPeriod);
    }

    public void enterPurchaseAccountCode(String dataFile,String sheetName,String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Purchase A/c Code'  or @Name='Purchase A/C Code']",dataFile,sheetName,key);
//        enterInput("xpath","//Edit[@Name='Purchase A/c Code'  or @Name='Purchase A/C Code']",dataFile,dataSet,purchaseAccountCode);
    }

    public void enterSuppliersBillNumber() {
        inputTextWithValidation("xpath","//Edit[@Name='Supplier Bill No *']",String.valueOf(common.getRandom()));
    }
    public void enterSuppliersBillDate()  {
        inputTextWithValidation("xpath","//Edit[@Name='Supplier Bill Date *']", Time.timeStamp());
    }
    public void enterBatchPolicy(String dataFile,String sheetName,String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Batch Policy']",dataFile,sheetName,key);
//        enterInput("xpath","//Edit[@Name='Batch Policy']",dataFile,dataSet,batchPolicy);
    }
    public void enterTcsTransNature(String dataFile,String sheetName,String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='TCS Trans Nature']",dataFile,sheetName,key);
//        enterInput("xpath","//Edit[@Name='TCS Trans Nature']",dataFile,dataSet,tcsTransactionNature);
    }
    public void enterTdsTransNature(String dataFile,String sheetName,String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='TDS Trans Nature']",dataFile,sheetName,key);
//        enterInput("xpath","//Edit[@Name='TDS Trans Nature']",dataFile,dataSet,tdsTransNature);
    }







//    public void voucherType() throws IOException, ParseException {
//        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
//        super.selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
//    }
//
//    public void branch_baseClass() throws IOException, ParseException {
//        common.clickElement("xpath", "//Edit[@Name='Branch *']");
//        super.selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
//    }
//
//    public void transCurrency() throws IOException, ParseException {
//        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
//        super.selectAndValidateData(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Trans Currency *']");
//    }
//
//    public void partyCodes() throws IOException, ParseException {
//        common.clickElement("xpath", "//Edit[@Name='Party Code']");
//        super.selectAndValidateData(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Party Code']");
//    }
//
//    public void priceList_baseClass() throws IOException, ParseException {
//        common.clickElement("xpath", "//Edit[@Name='Price List']");
//        super.selectAndValidateData(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
//    }
//
//    public void executives() throws IOException, ParseException {
//        common.clickElement("xpath", "//Edit[@Name='Executive *']");
//        super.selectAndValidateData(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
//    }
//
//    public void remarks_baseClass() throws IOException, ParseException {
//        common.clickElement("xpath", "//Edit[@Name='Remarks']");
//        super.selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
//    }


}
