package com.wings.pages.finance.transactions.OpeningBalances;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;

public class TransferIncomesAndExpensestoPL extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public TransferIncomesAndExpensestoPL(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void incomeAndExpenses() throws InterruptedException, IOException, ParseException {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Finance","Opening Balances","Transfer Incomes and Expenses to PL");
        Thread.sleep(1000);
        long generalInfoStart=System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterReservesAndSurplusAccount(dataFile,"GeneralInformation","ReservesAndSurplusAccount");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Opening Balances General information End:- ", generalInfoEndTime);

        //F3-Parties
        long addExpensesStart =System.nanoTime();
        addExpenses();
        long addExpensesEnd =System.nanoTime()- addExpensesStart;
        FileUtil.writeTimeLogInMinutes("Opening Balances Cash:- ", addExpensesEnd);

        long addIncomeStart =System.nanoTime();
//        addIncomes();
        long addIncomeEnd =System.nanoTime()- addIncomeStart;
        FileUtil.writeTimeLogInMinutes("Opening Balances Bank:- ", addIncomeEnd);

        long otherInfoStart = System.nanoTime();
//        otherInfo();
        long otherInfoEnd = System.nanoTime() - otherInfoStart;
        FileUtil.writeTimeLogInMinutes("Opening Balances OtherInfo Tab:- ", otherInfoEnd);
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long transferIncomes = System.nanoTime() - start ;
        FileUtil.writeTimeLogInMinutes("Opening Balances ended at:- ", transferIncomes);
        //IO
        String voucher = newVoucherID.replaceAll("\\d", "");
        String number = newVoucherID.replaceAll("\\D", "");
        Thread.sleep(2000);
        long iofIlesStart=System.nanoTime();
        exportIOFiles("Generate Input File", voucher,number);
        exportIOFiles("Generate Output File", voucher,number);
        long ioFilesEnd=System.nanoTime()-iofIlesStart;
        FileUtil.writeTimeLogInMinutes("Opening Balances IO files ended at:- ", ioFilesEnd );

//        excelUtil.excelComparator("","",newVoucherID);
    }

    public void addExpenses() throws IOException {

        java.util.List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Expenses']/*[starts-with(@Name,'Row ')]/*[starts-with(@Name,'Account * Row ')]");
        System.out.println(commentsRowList.size()+"application products order");
        for (WebElement element:commentsRowList){
            System.out.println(element.getText());
        }
        for (int i = 0; i < commentsRowList.size(); i++) {
            String comment = commentsRowList.get(i).getText();
            if (comment != "(null)") {
                enterListData(commentsRowList.get(i), dataFile, "Expenses", "Comments", i);
            }
        }
    }

    public void addIncomes() throws IOException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Bank')]");
        java.util.List<String> bankTab = readExcelData(dataFile, "Incomes", "BankAccountCode");

        java.util.List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Incomes']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < bankTab.size(); i++) {
            enterListData(commentsRowList.get(i), dataFile, "Incomes", "Comments", i);
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