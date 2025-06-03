package com.wings.pages;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

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

    public void enterBranchName(String dataFile,String dataSet,String branch) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,dataSet,branch);
    }
    public void enterDate(String dataFile,String dataSet,String date) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Date *']",dataFile,dataSet,date);
    }
    public void enterVoucherType(String dataFile,String dataSet,String voucherType) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Date *']",dataFile,dataSet,voucherType);
    }

    public void enterLocation(String dataFile,String dataSet,String location) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Location *']",dataFile,dataSet,location);
    }
    public void enterCurrency(String dataFile,String dataSet,String currency) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Trans Currency *']",dataFile,dataSet,currency);
    }

    public void enterCashOrParty(String dataFile,String dataSet,String cashOrParty) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Cash/Party Code']",dataFile,dataSet,cashOrParty);
    }

    public void enterPurchaseAccountCode(String dataFile,String dataSet,String purchaseAccountCode) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Purchase A/c Code']",dataFile,dataSet,purchaseAccountCode);
    }

    public void enterSuppliersBillNumber(String dataFile,String dataSet,String suppliersBillNumber) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Supplier Bill No *']",dataFile,dataSet,suppliersBillNumber);
    }
    public void enterSuppliersBillDate(String dataFile,String dataSet,String suppliersBillDate) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Supplier Bill Date *']",dataFile,dataSet,suppliersBillDate);
    }
    public void enterBatchPolicy(String dataFile,String dataSet,String batchPolicy) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Batch Policy']",dataFile,dataSet,batchPolicy);
    }
    public void enterTcsTransNature(String dataFile,String dataSet,String tcsTransactionNature) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='TCS Trans Nature']",dataFile,dataSet,tcsTransactionNature);
    }
    public void enterTdsTransNature(String dataFile,String dataSet,String tdsTransNature) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='TDS Trans Nature']",dataFile,dataSet,tdsTransNature);
    }

    public void enterPriceList(String dataFile,String dataSet,String priceList) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Price List']",dataFile,dataSet,priceList);
    }
    public void enterExecutive(String dataFile,String dataSet,String executive) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Executive *']",dataFile,dataSet,executive);
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
