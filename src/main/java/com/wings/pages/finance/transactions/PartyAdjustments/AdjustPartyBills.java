package com.wings.pages.finance.transactions.PartyAdjustments;

import com.wings.pages.Transaction;
import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class AdjustPartyBills extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public AdjustPartyBills(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void executeAdjustPartyBills() throws InterruptedException, IOException, ParseException {
        navigateToAdjustPartyBills();
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        Thread.sleep(1000);
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterPartyCode(dataFile,"GeneralInformation","PartyAccountCode");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");


        //Bills Receivables
        long BillsReceivablesStart =System.nanoTime();
        billsReceivables();
        long BillsReceivablesEnd =System.nanoTime()- BillsReceivablesStart;
        FileUtil.writeTimeLogInMinutes("BillsReceivables Tab End:- ", BillsReceivablesEnd);

        //BillsPayables
        long BillsPayablesStart =System.nanoTime();
        billsPayables();
        long BillsPayablesEnd =System.nanoTime()- BillsPayablesStart;
        FileUtil.writeTimeLogInMinutes("BillsPayables Tab End:- ", BillsPayablesEnd);

        //Other info
        long otherInfoTabStart =System.nanoTime();
        otherInfo();
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Other Info Tab:- ", otherInfoTabEnd);

        transactionSave();
        String newVoucherID = newTransactionID(oldVoucherID);
        System.out.println("newID: " + newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID, "Voucher Numbers are same. Check Transaction.");

    }

    public void billsReceivables(){
        common.clickElement("xpath", "//TabItem[contains(@Name,'Bills Payable  ')]");

    }

    public void billsPayables(){
        common.clickElement("xpath", "//TabItem[contains(@Name,'Bills Payable  ')]");

    }

    public void otherInfo() throws InterruptedException, IOException {
        navigateToOtherInfoTab();
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(3500);
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }
}
