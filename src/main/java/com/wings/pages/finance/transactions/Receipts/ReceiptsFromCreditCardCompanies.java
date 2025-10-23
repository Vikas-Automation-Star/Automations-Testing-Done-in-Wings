package com.wings.pages.finance.transactions.Receipts;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ReceiptsFromCreditCardCompanies extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public ReceiptsFromCreditCardCompanies(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void creditCardCompanyReceipt(String voucherNum1,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long receiptsFromCreditCardCompanies = System.nanoTime();

        navigateToMastersWhen3Steps("Finance","Receipts","Receipts from Credit Card Companies");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        Thread.sleep(1000);
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        EnterData("//Edit[@Name='Credit Card Company *']", dataFile,"GeneralInformation", "CreditCardCompany");
        Thread.sleep(2000);
        gstTransactionType("Intra State Purchase from Registered Dealers");
        Thread.sleep(2000);
//        selectMultiplePendings(voucherNum1,voucherNum2,"20250401");
        selectPendingsSalesOrder(voucherNum1,"20250401");
        EnterData("//Edit[@Name='Bank Account Code']", dataFile,"GeneralInformation", "BankAccountCode");
        EnterData("//Edit[@Name='Cheque/EFT No *']", dataFile,"GeneralInformation", "ChequeNo");
        EnterDate("//Edit[@Name='Cheque Date *']", dataFile,"GeneralInformation", "ChequeDate");
        EnterData("//Edit[@Name='Drawn On Bank *']", dataFile,"GeneralInformation", "DrawnOnBankAccount");
        EnterData("//Edit[@Name='Drawn On Bank Branch']", dataFile,"GeneralInformation", "DrawnOnBankBranch");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");


        //Accounts
        long accountsStart =System.nanoTime();
        accounts();
        long accountsStartEnd =System.nanoTime()- accountsStart;
        FileUtil.writeTimeLogInMinutes("Accounts tab End:- ", accountsStartEnd);

        //credit card Company charges
        long creditCardCompanyChargesStart =System.nanoTime();
        creditCardCompanyCharges();
        long creditCardCompanyChargesStartEnd =System.nanoTime()- creditCardCompanyChargesStart;
        FileUtil.writeTimeLogInMinutes("creditCardCompanyCharges tab End:- ", creditCardCompanyChargesStartEnd);

        //credit card
        long otherDebitsStart =System.nanoTime();
        otherDebit();
        long otherDebitsEnd =System.nanoTime()- otherDebitsStart;
        FileUtil.writeTimeLogInMinutes("Other DebitsEnd Tab:- ", otherDebitsEnd);

        //Other info
        long otherInfoTabStart =System.nanoTime();
        otherInfo();
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Other Info Tab:- ", otherInfoTabEnd);


        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"ReceiptsFromCreditCardCompanies");

        long deleteReceiptsVouchers =System.nanoTime();
        deleteTransactionUsingVoucherNumber(newVoucherID);
        deleteTransactionUsingVoucherNumber("PREC 4");
        deleteTransactionUsingVoucherNumber("CR 3");
        deleteTransactionUsingVoucherNumber("BR 5");
        deleteTransactionUsingVoucherNumber("CCR 3");
        long AllReceiptsDeletions =System.nanoTime()- deleteReceiptsVouchers;
        FileUtil.writeTimeLogInMinutes("Delete Voucher Of All Receipts:- ", AllReceiptsDeletions);
        long receiptsFromCreditCardCompaniesEnd = System.nanoTime()- receiptsFromCreditCardCompanies;
        FileUtil.writeTimeLogInMinutes("Receipts from credit card companies End at:- ", receiptsFromCreditCardCompaniesEnd );
    }

    public void accounts() throws IOException, InterruptedException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Accounts  ')]");
        List<WebElement> receivedRow = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/CheckBox[starts-with(@Name,'Received * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int j = 0; j < departmentRowList.size()-1; j++) {
            clickListData(receivedRow.get(j));
            enterListData(departmentRowList.get(j), dataFile, "Accounts", "Department", j);
            enterListData(projectRowList.get(j), dataFile, "Accounts", "Project", j);
            enterListData(profitCentreRowList.get(j), dataFile, "Accounts", "ProfitCentre", j);
            enterListData(costCentreRowList.get(j), dataFile, "Accounts", "CostCentre", j);
            enterListData(commentsRowList.get(j), dataFile, "Accounts", "Comments", j);
        }
    }

    public void creditCardCompanyCharges() throws IOException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Credit Card Company Charges')]");
        List<String> debitAcc =readExcelData(dataFile,"CreditCardCompanyCharges","AccountCode");
        for (int i = 0; i < debitAcc.size(); i++) {
            addData("xpath","//Edit[@Name='Account Code Row "+i+", Not sorted.']",dataFile,"CreditCardCompanyCharges","AccountCode",i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardCompanyCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardCompanyCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> GSTProductCategory = common.findWebElements("xpath", "//Table[@Name='CreditCardCompanyCharges']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'GST Product Category Row ')]");
        List<WebElement> CESSProductCategory = common.findWebElements("xpath", "//Table[@Name='CreditCardCompanyCharges']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'CESS Product Category Row ')]");
        common.clickElement("xpath", "//Header[@Name='GST Amount']");

        for (int i = 0; i < debitAcc.size() ; i++) {
            enterListData(amountRowList.get(i), dataFile, "CreditCardCompanyCharges", "Amount",i);
            enterListData(hsnCodeRowList.get(i),dataFile,"CreditCardCompanyCharges","HSN",i);
            enterListData(GSTProductCategory.get(i),dataFile,"CreditCardCompanyCharges","GSTProductCategory",i);
            enterListData(CESSProductCategory.get(i),dataFile,"CreditCardCompanyCharges","CESSProductCategory",i);
        }
    }

    public void otherDebit() throws IOException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Other Debits')]");
        List<String> debitAcc =readExcelData(dataFile,"OtherDebits","AccountCode");
        for (int i = 0; i < debitAcc.size(); i++) {
            addData("xpath","//Edit[@Name='Account Code Row "+i+", Not sorted.']",dataFile,"OtherDebits","AccountCode",i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='OtherDebits']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='OtherDebits']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='OtherDebits']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherDebits']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherDebits']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='OtherDebits']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < debitAcc.size() ; i++) {
            enterListData(amountRowList.get(i), dataFile, "OtherDebits", "Amount",i);
            enterListData(departmentRowList.get(i),dataFile,"OtherDebits","Department",i);
            enterListData(projectRowList.get(i),dataFile,"OtherDebits","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"OtherDebits","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"OtherDebits","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"OtherDebits","Comments",i);
        }
    }

    public void otherInfo() throws InterruptedException, IOException {
        navigateToOtherInfoTab();
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(3500);
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='OtherInfo 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='OtherInfo 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='OtherInfo 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='OtherInfo 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='OtherInfo 5']",dataFile,"OtherInfo","OtherInfo5");
    }

}
