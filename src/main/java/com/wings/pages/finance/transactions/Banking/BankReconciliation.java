package com.wings.pages.finance.transactions.Banking;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.utils.Common;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;

public class BankReconciliation extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public BankReconciliation(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void bankReconciliation(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Finance","Banking","Bank Reconciliation");
        Thread.sleep(1000);
        long generalInfoStart=System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        EnterData("//Edit[@Name='Bank Code']",dataFile,"GeneralInformation","BankAccountCode");
        EnterDate("//Edit[@Name='As At *']",dataFile,"GeneralInformation","AsAt");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Bank Reconciliation General information End:- ", generalInfoEndTime);

        //F3-Parties
        long addAccountsStart =System.nanoTime();
        addAccounts();
        long addAccountsEnd =System.nanoTime()- addAccountsStart;
        FileUtil.writeTimeLogInMinutes("Bank Reconciliation Add Deposits:- ", addAccountsEnd);
        //other Info
        long otherInfoStart = System.nanoTime();
        otherInfo();
        long otherInfoEnd = System.nanoTime() - otherInfoStart;
        FileUtil.writeTimeLogInMinutes("Bank Reconciliation OtherInfo Tab:- ", otherInfoEnd);
        common.clickElement("xpath","//TabItem[contains(@Name,'Summary ')]");
        EnterData("//Edit[@Name='Opening Balance As Per Bank Book']",dataFile,"Summary","OpeningBalanceAsPerBankBook");
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long salesInvoiceEnd = System.nanoTime() - start ;
        FileUtil.writeTimeLogInMinutes("Bank Reconciliation ended at:- ", salesInvoiceEnd );
        //API
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"BankReconciliation");

    }

    public void addAccounts() throws IOException {
        java.util.List<String> cashTab = readExcelData(dataFile, "Accounts", "Account");
        java.util.List<WebElement> clearingDate = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Clearing Date * Row ')]");
        java.util.List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < cashTab.size(); i++) {
          common.clickElement("xpath","//CheckBox[@Name='Clearing Status * Row "+i+"']");
            enterListDate(clearingDate.get(i), dataFile, "Accounts", "ClearingDate", i);
            enterListData(commentsRowList.get(i), dataFile, "Accounts", "Comments", i);
        }
        common.deleteInvalidRows();
    }

    public void otherInfo() throws InterruptedException, IOException {
        navigateToOtherInfoTab();
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(5000);
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }
}