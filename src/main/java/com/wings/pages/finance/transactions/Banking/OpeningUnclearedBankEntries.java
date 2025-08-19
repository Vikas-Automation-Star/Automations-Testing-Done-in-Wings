package com.wings.pages.finance.transactions.Banking;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;

public class OpeningUnclearedBankEntries extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public OpeningUnclearedBankEntries(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void unclearedBankEntries() throws InterruptedException, IOException, ParseException{
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Finance","Banking","Opening Uncleared Bank Entries");
        Thread.sleep(1000);
        long generalInfoStart=System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        EnterData("//Edit[@Name='Bank Code']",dataFile,"GeneralInformation","BankAccountCode");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Deposit Post Dated Cheques General information End:- ", generalInfoEndTime);

        //F3-Uncleared Receipts
        long addUnclearedReceiptsStart =System.nanoTime();
        addUnclearedReceipts();
        long addUnclearedReceiptsEnd =System.nanoTime()- addUnclearedReceiptsStart;
        FileUtil.writeTimeLogInMinutes("Deposit Post Dated Cheques Add Deposits:- ", addUnclearedReceiptsEnd);
        //Uncleared Payments
        long addUnclearedPaymentsStart =System.nanoTime();
        addUnclearedPayments();
        long addUnclearedPaymentsEnd =System.nanoTime()- addUnclearedPaymentsStart;
        FileUtil.writeTimeLogInMinutes("Deposit Post Dated Cheques Add Deposits:- ", addUnclearedPaymentsEnd);
        //other Info
        long otherInfoStart = System.nanoTime();
        otherInfo();
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
        //IO
        String voucher = newVoucherID.replaceAll("\\d", "");
        String number = newVoucherID.replaceAll("\\D", "");
        Thread.sleep(2000);
        long iofIlesStart=System.nanoTime();
        exportIOFiles("Generate Input File", voucher,number);
        exportIOFiles("Generate Output File", voucher,number);
        long ioFilesEnd=System.nanoTime()-iofIlesStart;
        FileUtil.writeTimeLogInMinutes("Deposit Post Dated Cheques IO files ended at:- ", ioFilesEnd );

//        excelUtil.excelComparator("","",newVoucherID);
    }

    public void addUnclearedReceipts() throws IOException {
        List<String> chequesTab = readExcelData(dataFile, "UnclearedReceipts", "AccountCode");
        for (int i = 0; i < chequesTab.size(); i++) {
            addData("xpath", "//Edit[@Name='Account Code Row "+i+", Not sorted.']", dataFile, "UnclearedReceipts", "AccountCode", i);
        }
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='UnclearedReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='UnclearedReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> drawnOnBank = common.findWebElements("xpath", "//Table[@Name='UnclearedReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> receiptAmount = common.findWebElements("xpath", "//Table[@Name='UnclearedReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Receipt Amount * Row ')]");
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='UnclearedReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='UnclearedReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='UnclearedReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='UnclearedReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='UnclearedReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='UnclearedReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < chequesTab.size(); i++) {
            enterListData(chequeNo.get(i), dataFile, "UnclearedReceipts", "ChequeNo", i);
            enterListDate(chequeDate.get(i), dataFile, "UnclearedReceipts", "ChequeDate", i);
            enterListData(drawnOnBank.get(i), dataFile, "UnclearedReceipts", "DrawnOnBankAccount", i);
            enterListData(receiptAmount.get(i), dataFile, "UnclearedReceipts", "ReceiptAmount", i);
            enterListData(executive.get(i), dataFile, "UnclearedReceipts", "Executive", i);
            enterListData(departmentRowList.get(i), dataFile, "UnclearedReceipts", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "UnclearedReceipts", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "UnclearedReceipts", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "UnclearedReceipts", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "UnclearedReceipts", "Comments", i);
        }
    }

    public void addUnclearedPayments() throws IOException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Uncleared Payments ')]");
        List<String> chequesTab = readExcelData(dataFile, "UnclearedPayments", "AccountCode");
        for (int i = 0; i < chequesTab.size(); i++) {
            addData("xpath", "//Edit[@Name='Account Code Row "+i+", Not sorted.']", dataFile, "UnclearedReceipts", "AccountCode", i);
        }
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='UnclearedPayments']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='UnclearedPayments']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> drawnOnBank = common.findWebElements("xpath", "//Table[@Name='UnclearedPayments']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> receiptAmount = common.findWebElements("xpath", "//Table[@Name='UnclearedPayments']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Issued Amount * Row ')]");
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='UnclearedPayments']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='UnclearedPayments']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='UnclearedPayments']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='UnclearedPayments']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='UnclearedPayments']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='UnclearedPayments']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < chequesTab.size(); i++) {
            enterListData(chequeNo.get(i), dataFile, "UnclearedPayments", "ChequeNo", i);
            enterListDate(chequeDate.get(i), dataFile, "UnclearedPayments", "ChequeDate", i);
            enterListData(drawnOnBank.get(i), dataFile, "UnclearedPayments", "DrawnOnBankAccount", i);
            enterListData(receiptAmount.get(i), dataFile, "UnclearedPayments", "IssuedAmount", i);
            enterListData(executive.get(i), dataFile, "UnclearedPayments", "Executive", i);
            enterListData(departmentRowList.get(i), dataFile, "UnclearedPayments", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "UnclearedPayments", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "UnclearedPayments", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "UnclearedPayments", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "UnclearedPayments", "Comments", i);
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
