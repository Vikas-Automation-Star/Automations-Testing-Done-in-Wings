package com.wings.pages.finance.transactions.Journals;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class JournalEntries extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public JournalEntries(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void journalEntries(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws InterruptedException, IOException, ParseException, AWTException {
        long start = System.nanoTime();
        System.out.println("Journal Entries  started in :" + start);

        navigateToJournalEntriesMenu();
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        Thread.sleep(1000);
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        accounts();
        otherInfo();

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");

        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"JournalEntries");
        long JeEnd = System.nanoTime() -start ;
        FileUtil.writeTimeLogInMinutes("Journal Entries ended at:- ", JeEnd );
    }

    public void accounts() throws IOException {
        List<String> productCode=readExcelData(dataFile,"Accounts","DebitAccountCode");
        System.out.println("productCodes :"+productCode.size());
        for (int i = 0; i < productCode.size() ; i++) {
            addData("xpath","//Edit[@Name='Debit Account Code Row "+i+", Not sorted.']",dataFile,"Accounts","DebitAccountCode",i);
        }
        List<WebElement>  creditAccCode= common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Credit Account Code Row ')]");
        List<WebElement> amount = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < productCode.size(); i++) {
            enterListData(creditAccCode.get(i), dataFile, "Accounts", "CreditAccountCode" , i);
            enterListData(amount.get(i), dataFile, "Accounts", "Amount" , i);
            enterListData(departmentRowList.get(i), dataFile, "Accounts", "Department" , i);
            enterListData(projectRowList.get(i), dataFile, "Accounts", "Project" , i);
            enterListData(profitCentreRowList.get(i), dataFile, "Accounts", "ProfitCentre" , i);
            enterListData(costCentreRowList.get(i), dataFile, "Accounts", "CostCentre" , i);
            enterListData(commentsRowList.get(i), dataFile, "Accounts", "Comments" , i);
        }
    }

    public void otherInfo() throws InterruptedException, IOException {
        navigateToOtherInfoTab();
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(2000);
        common.clickElement("xpath","//Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }

}
