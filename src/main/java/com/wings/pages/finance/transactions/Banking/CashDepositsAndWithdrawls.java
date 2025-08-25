package com.wings.pages.finance.transactions.Banking;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.utils.Common;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;
import java.io.IOException;

public class CashDepositsAndWithdrawls extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CashDepositsAndWithdrawls(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void depositAndWithdrawal(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Finance","Banking","Cash Deposits and withdrawals");
        Thread.sleep(1000);
        long generalInfoStart=System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterBankAccountCode(dataFile,"GeneralInformation","BankAccountCode");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Cash Deposits and Withdrawals General information End:- ", generalInfoEndTime);

        //F3-Parties
        long addDepositsStart =System.nanoTime();
        addDeposits();
        long addDepositsEnd =System.nanoTime()- addDepositsStart;
        FileUtil.writeTimeLogInMinutes("Cash Deposits and Withdrawals Add Deposits:- ", addDepositsEnd);
        //withdrawals
        long addWithdrawalsStart =System.nanoTime();
        addWithdrawals();
        long addWithdrawalsEnd =System.nanoTime()- addWithdrawalsStart;
        FileUtil.writeTimeLogInMinutes("Cash Deposits and Withdrawals Add Withdrawals:- ", addWithdrawalsEnd);
        //other Info
        long otherInfoStart = System.nanoTime();
        otherInfo();
        long otherInfoEnd = System.nanoTime() - otherInfoStart;
        FileUtil.writeTimeLogInMinutes("Cash Deposits and Withdrawals OtherInfo Tab:- ", otherInfoEnd);
        //allocations
        long allocationsTabStart=System.nanoTime();
        addAllocations();
        long allocationsTabEnd=System.nanoTime() - allocationsTabStart;
        FileUtil.writeTimeLogInMinutes("Cash Deposits and Withdrawals Allocations Tab:- ", allocationsTabEnd);
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long salesInvoiceEnd = System.nanoTime() - start ;
        FileUtil.writeTimeLogInMinutes("Cash Deposits and Withdrawals ended at:- ", salesInvoiceEnd );
        //API
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"CashDepositAndWithdrawal");

//        excelUtil.excelComparator("","",newVoucherID);
    }

    public void addDeposits() throws IOException {
        List<String> cashTab = readExcelData(dataFile, "Deposits", "CashAccountCode");
        for (int i = 0; i < cashTab.size(); i++) {
            addData("xpath", "//Edit[@Name='Cash A/c Code Row "+i+", Not sorted.']", dataFile, "Deposits", "CashAccountCode", i);
        }
        List<WebElement> amount = common.findWebElements("xpath", "//Table[@Name='Deposits']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Deposits']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Deposits']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Deposits']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Deposits']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Deposits']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < cashTab.size(); i++) {
            enterListData(amount.get(i), dataFile, "Deposits", "Amount", i);
            enterListData(departmentRowList.get(i), dataFile, "Deposits", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "Deposits", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "Deposits", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "Deposits", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "Deposits", "Comments", i);
        }
    }

    public void addWithdrawals() throws IOException {
       common.clickElement("xpath","//TabItem[contains(@Name,'Withdrawal')]");
        List<String> chequesTab = readExcelData(dataFile, "Withdrawal", "CashAccountCode");
        for (int i = 0; i < chequesTab.size(); i++) {
            addData("xpath", "//Edit[@Name='Cash A/c Code Row "+i+", Not sorted.']", dataFile, "Withdrawal", "CashAccountCode", i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Withdrawal']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='Withdrawal']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='Withdrawal']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Withdrawal']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Withdrawal']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Withdrawal']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Withdrawal']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Withdrawal']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < chequesTab.size(); i++) {
            enterListData(amountRowList.get(i), dataFile, "Withdrawal", "Amount", i);
            enterListData(chequeNo.get(i), dataFile, "Withdrawal", "ChequeNo", i);
            enterListDate(chequeDate.get(i), dataFile, "Withdrawal", "ChequeDate", i);
            enterListData(departmentRowList.get(i), dataFile, "Withdrawal", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "Withdrawal", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "Withdrawal", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "Withdrawal", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "Withdrawal", "Comments", i);
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

    public void addAllocations() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Allocations')]");
        EnterData( "//Edit[@Name='Department']", dataFile, "Allocations", "Department");
        EnterData( "//Edit[@Name='Project']", dataFile, "Allocations", "Project");
        EnterData("//Edit[@Name='Profit Centre']", dataFile, "Allocations", "ProfitCentre");
        EnterData( "//Edit[@Name='Cost Centre']", dataFile, "Allocations", "CostCentre");
    }
}
