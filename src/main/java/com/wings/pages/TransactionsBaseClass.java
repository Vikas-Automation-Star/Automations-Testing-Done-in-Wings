package com.wings.pages;

import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import java.io.IOException;

public class TransactionsBaseClass extends Transaction {
    public TransactionsBaseClass(WindowsDriver driver) {
        super(driver);
    }

    public void enterBranchName(String dataFile,String sheetName,String branch){
        EnterData("//Edit[@Name='Branch *']",dataFile,sheetName,branch);
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
    }

    public void enterLocation(String dataFile,String sheetName,String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Location *']",dataFile,sheetName,key);
    }

    public void enterToLocation(String dataFile,String sheetName,String key) {
        EnterData("//Edit[@Name='To Location *']",dataFile,sheetName,key);
    }

    public void enterCurrency(String dataFile,String sheetName,String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Trans Currency *'] | //Edit[@Name='Transaction Currency *']",dataFile,sheetName,key);
    }
    public void enterReceiptNum(String receiptNum){
        common.findWebElement("xpath","//Edit[@Name='Receipt No']").sendKeys(receiptNum,Keys.TAB);
    }
    public void enterSalesInvoiceNo(String dataFile,String sheetName,String key) {
        EnterData("//Edit[@Name='Sales Invoice No *']",dataFile,sheetName,key);
    }
    public void enterSalesReturnAccountCode(String dataFile,String sheetName,String key) {
        EnterData("//Edit[@Name='Sales Return A/c Code']",dataFile,sheetName,key);
    }
    public void enterExchangeRate(String dataFile,String sheetName,String key) throws IOException {
        EnterData("//Edit[@Name='Exchange Rate *']",dataFile,sheetName,key);
    }

    public void enterPartyCode(String dataFile,String sheetName, String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Party Code'] | //Edit[@Name='Cash/Party Code'] | //Edit[@Name='Party Account *']",dataFile,sheetName,key);
    }

    public void enterOpeningStockAccount(String dataFile,String sheetName, String key) {
        EnterData("//Edit[@Name='Opening Stock Account *']",dataFile,sheetName,key);
    }
    public void enterOpeningStockAccountAsset(String dataFile,String sheetName, String key) {
        EnterData("//Edit[@Name='Opening Stock Account Asset *']",dataFile,sheetName,key);
    }
    public void enterStockAccount(String dataFile,String sheetName, String key) {
        EnterData("//Edit[@Name='Stock Account']",dataFile,sheetName,key);
    }

    public void enterCustomerEmail(String dataFile,String sheetName,String key)  {
        EnterData("//Edit[@Name='Customer Email']",dataFile,sheetName,key);
    }

    public void enterShippingBillNo(String dataFile,String sheetName,String key) {
        EnterData("//Edit[@Name='Shipping Bill No']",dataFile,sheetName, key);
    }

    public void enterSupplierBillNumber(String dataFile,String sheetName,String key) {
        EnterData("//Edit[@Name='Supplier Bill No *']",dataFile,sheetName, key);
    }

    public void enterPortCode(String dataFile,String sheetName,String key) {
        EnterData("//Edit[@Name='Port Code']",dataFile,sheetName,key);
    }

    public void enterRemarks(String dataFile,String sheetName,String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Remarks']",dataFile,sheetName,key);
//        enterInput("xpath","//Edit[@Name='Remarks']",dataFile,dataSet, remarks);
    }

    public void enterSalesAccountCode(String dataFile,String sheetName,String key) {
        EnterData("//Edit[@Name='Sales A/c Code']",dataFile,sheetName,key);
    }
    public void enterCustomerMobileNum(String dataFile,String sheetName,String key)  {
        EnterData("//Edit[@Name='Customer Mobile Number']",dataFile,sheetName,key);
    }

    public void enterCashOrParty(String dataFile,String sheetName,String key){
        EnterData("//Edit[@Name='Cash/Party Code' or @Name='Party Code']",dataFile,sheetName,key);
    }

    public void enterConsigner(String dataFile,String sheetName,String key)  {
        EnterData("//Edit[@Name='Cash/Party Code']",dataFile,sheetName,key);
    }

    public void enterCreditPeriod(String dataFile,String sheetName,String key) throws IOException, ParseException {
        decimalPrecision("//Edit[@Name='Credit Period']",dataFile,sheetName,key);
    }

    public void enterPurchaseAccountCode(String dataFile,String sheetName,String key) {
        EnterData("//Edit[@Name='Purchase A/c Code'  or @Name='Purchase A/C Code']",dataFile,sheetName,key);
    }

    public void enterSuppliersBillNumber() {
        inputTextWithValidation("xpath","//Edit[@Name='Supplier Bill No *']",String.valueOf(common.getRandom()));
    }
    public void enterSuppliersBillDate()  {
        inputTextWithValidation("xpath","//Edit[@Name='Supplier Bill Date *']", Time.timeStamp());
    }
    public void enterBatchPolicy(String dataFile,String sheetName,String key) {
        EnterData("//Edit[@Name='Batch Policy']",dataFile,sheetName,key);
    }
    public void enterTcsTransNature(String dataFile,String sheetName,String key) {
        EnterData("//Edit[@Name='TCS Trans Nature']",dataFile,sheetName,key);
    }
    public void enterTdsTransNature(String dataFile,String sheetName,String key) {
        EnterData("//Edit[@Name='TDS Trans Nature']",dataFile,sheetName,key);
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
