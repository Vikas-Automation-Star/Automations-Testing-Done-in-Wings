package tradeTesting.finance.transactions.banking;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class BankReconciliationTrade extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public BankReconciliationTrade(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void bankReconciliationTrade(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
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
        common.clickElement("xpath", "//TabItem[contains(@Name,'  OtherInfo  ')]");
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
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"BankReconciliation");
        deleteTransactionUsingVoucherNumber(newVoucherID);

    }

    public void addAccounts() throws IOException {
        common.sliderHandling("xpath", "//Table[@Name='Accounts']/*/Thumb[@Name='Position']", 0, -300);
        List<String> cashTab = readExcelData(dataFile, "Accounts", "Account");
        List<WebElement> clearingDate = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Clearing Date * Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        List<WebElement> towardsVoucherNum = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Towards VNo * Row ')]");
        System.out.println("Towards vouchers Size :" + towardsVoucherNum.size());
        for (int i = 0; i < towardsVoucherNum.size(); i++) {
            WebElement text = towardsVoucherNum.get(i);
//            System.out.println("Towards vouchers getTet :" + text.getText());
            if (text.getText().equals("SO 4")) {
                for (int j = 0; j < cashTab.size(); j++) {
                    common.clickElement("xpath", "//CheckBox[@Name='Clearing Status * Row " + j + "']");
                    enterListDate(clearingDate.get(i), dataFile, "Accounts", "ClearingDate", j);
                    enterListData(commentsRowList.get(i), dataFile, "Accounts", "Comments", j);
                    common.deleteInvalidRows();
                    break;
                }
            }
        }
    }

    public void otherInfo() throws InterruptedException, IOException, AWTException {
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }
}