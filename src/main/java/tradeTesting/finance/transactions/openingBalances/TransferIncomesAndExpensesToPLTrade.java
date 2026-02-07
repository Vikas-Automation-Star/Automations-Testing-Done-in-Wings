package tradeTesting.finance.transactions.openingBalances;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.awt.*;
import java.util.List;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class TransferIncomesAndExpensesToPLTrade extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public TransferIncomesAndExpensesToPLTrade(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void transferIncomesAndExpensesToPLTrade() throws InterruptedException, IOException, ParseException, AWTException {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Finance","Opening Balances","Transfer Incomes and Expenses to PL");
        Thread.sleep(1000);
        long generalInfoStart=System.nanoTime();
        Thread.sleep(2500);
        String oldVoucherID =oldTTransactionID();
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterReservesAndSurplusAccount(dataFile,"GeneralInformation","ReservesAndSurplusAccount");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Transfer Incomes and Expenses General information End:- ", generalInfoEndTime);

        //F3-Parties
        long addExpensesStart =System.nanoTime();
        addExpenses();
        long addExpensesEnd =System.nanoTime()- addExpensesStart;
        FileUtil.writeTimeLogInMinutes("Transfer Incomes and Expenses expenses:- ", addExpensesEnd);

        long addIncomeStart =System.nanoTime();
        common.clickElement("xpath","//TabItem[contains(@Name,'  Incomes  ')]");
        addIncomes();
        long addIncomeEnd =System.nanoTime()- addIncomeStart;
        FileUtil.writeTimeLogInMinutes("Transfer Incomes and Expenses incomes:- ", addIncomeEnd);

        long otherInfoStart = System.nanoTime();
        common.clickElement("xpath","//TabItem[contains(@Name,'  OtherInfo  ')]");
        otherInfo();
        long otherInfoEnd = System.nanoTime() - otherInfoStart;
        FileUtil.writeTimeLogInMinutes("Transfer Incomes and Expenses OtherInfo Tab:- ", otherInfoEnd);
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long transferIncomes = System.nanoTime() - start ;
        FileUtil.writeTimeLogInMinutes("Transfer Incomes and Expenses ended at:- ", transferIncomes);

        //API
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"PartyOpeningBalances");
        deleteTransactionUsingVoucherNumber(newVoucherID);
    }

    public void addExpenses() throws IOException {
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Expenses']/*[starts-with(@Name,'Row ')]/*[starts-with(@Name,'Comments Row ')]");

        List<String> expensesTab = readExcelData(dataFile, "Expenses", "Account");
        for (int i = 0; i < expensesTab.size(); i++) {
                enterListData(commentsRowList.get(i), dataFile, "Expenses", "Comments", i);
            }
        }

    public void addIncomes() throws IOException {
        List<String> bankTab = readExcelData(dataFile, "Incomes", "Account");

        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Incomes']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < bankTab.size(); i++) {
            enterListData(commentsRowList.get(i), dataFile, "Incomes", "Comments", i);
        }
    }

    public void otherInfo() throws InterruptedException, IOException, AWTException {
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info5']",dataFile,"OtherInfo","OtherInfo5");
    }
}