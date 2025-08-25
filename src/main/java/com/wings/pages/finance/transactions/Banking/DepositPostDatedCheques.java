package com.wings.pages.finance.transactions.Banking;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.utils.Common;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;

public class DepositPostDatedCheques extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public DepositPostDatedCheques(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void postDatedChques(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Finance","Banking","Deposit Post Dated Cheques");
        Thread.sleep(1000);
        long generalInfoStart=System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        List<WebElement> elementList = common.findWebElements("xpath","//Edit[@Name='Bank Code']");
        for (WebElement i : elementList) {
//            i.click();
            i.sendKeys(Keys.CONTROL + "a");
            i.sendKeys(Keys.BACK_SPACE);
            i.sendKeys("AT_Bank Acc 1", Keys.TAB);
            break;
        }
//        EnterData("//Edit[@Name='Bank Code']",dataFile,"GeneralInformation","BankAccountCode");
        EnterData("//Edit[@Name='Cheques Received Account *']",dataFile,"GeneralInformation","ChequesReceivedAccount");
        Thread.sleep(1500);
        selectPendingsDPDC("SO 13","SI 3");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Deposit Post Dated Cheques General information End:- ", generalInfoEndTime);

        //F3-Parties
        long addAccountsStart =System.nanoTime();
        addAccounts();
        long addAccountsEnd =System.nanoTime()- addAccountsStart;
        FileUtil.writeTimeLogInMinutes("Deposit Post Dated Cheques Add Deposits:- ", addAccountsEnd);
        //other Info
        long otherInfoStart = System.nanoTime();
//        otherInfo();
        long otherInfoEnd = System.nanoTime() - otherInfoStart;
        FileUtil.writeTimeLogInMinutes("Deposit Post Dated Cheques OtherInfo Tab:- ", otherInfoEnd);
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long salesInvoiceEnd = System.nanoTime() - start ;
        FileUtil.writeTimeLogInMinutes("Deposit Post Dated Cheques ended at:- ", salesInvoiceEnd );
        //API
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"DepositPostDatedCheques");


//        excelUtil.excelComparator("","",newVoucherID);
    }

    public void addAccounts() throws IOException {
        java.util.List<String> cashTab = readExcelData(dataFile, "Accounts", "Account");
        java.util.List<WebElement> depositedOn = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Deposited On * Row ')]");
        java.util.List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        java.util.List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        java.util.List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        java.util.List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < cashTab.size(); i++) {
            common.clickElement("xpath","//CheckBox[@Name='Deposited * Row "+i+"']");
            enterListDate(depositedOn.get(i), dataFile, "Accounts", "DepositedOn", i);
            enterListData(Department.get(i),dataFile,"Accounts","Department",i);
            enterListData(Project.get(i),dataFile,"Accounts","Project",i);
            enterListData(ProfitCentre.get(i),dataFile,"Accounts","ProfitCentre",i);
            enterListData(CostCentre.get(i),dataFile,"Accounts","CostCentre",i);
            enterListData(commentsRowList.get(i), dataFile, "Accounts", "Comments", i);
        }
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