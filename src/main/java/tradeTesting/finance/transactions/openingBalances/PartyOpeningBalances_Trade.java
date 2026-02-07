package tradeTesting.finance.transactions.openingBalances;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;

public class PartyOpeningBalances_Trade extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PartyOpeningBalances_Trade(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String partyOpeningBalancesTrade(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Finance","Opening Balances","Party Opening Balances");
        Thread.sleep(1000);
        long generalInfoStart=System.nanoTime();
        Thread.sleep(2500);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterControlAccount(dataFile,"GeneralInformation","ControlAccount");
        EnterData("//Edit[@Name='Division *']",dataFile,"GeneralInformation","Division");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Party Opening Balances General information End:- ", generalInfoEndTime);

        //F3-Parties
        long addProductStart=System.nanoTime();
        addAccounts();
        long addProductEnd=System.nanoTime()-addProductStart;
        FileUtil.writeTimeLogInMinutes("Party Opening Balances Add Accounts:- ",addProductEnd);

        //allocations
        long allocationsTabStart=System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'Allocations')]");
        addAllocations();
        long allocationsTabEnd=System.nanoTime() - allocationsTabStart;
        FileUtil.writeTimeLogInMinutes("Party Opening Balances Allocations Tab:- ", allocationsTabEnd);
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long salesInvoiceEnd = System.nanoTime() - start ;
        FileUtil.writeTimeLogInMinutes("Party Opening Balances ended at:- ", salesInvoiceEnd );
        //API
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"PartyOpeningBalances");
        deleteTransactionUsingVoucherNumber(newVoucherID);

        return newVoucherID;
    }

    public void addAccounts() throws IOException {
        List<String> cashTab = readExcelData(dataFile, "Accounts", "Account");
        for (int i = 0; i < cashTab.size(); i++) {
            addData("xpath", "//Edit[@Name='Account Code Row " + i + ", Not sorted.']", dataFile, "Accounts", "Account", i);
        }
        List<WebElement> debit = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Debit Row ')]");
        List<WebElement> creditList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Credit Row ')]");
        List<WebElement> dueDate = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Due Date Row ')]");
        List<WebElement> referenceBillNo = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Reference Bill No Row ')]");
        List<WebElement> referenceBillDate = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Reference Bill Date Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        List<WebElement> Info1 = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Other Info 1 Row ')]");
        List<WebElement> Info2 = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Other Info 2 Row ')]");
        List<WebElement> Info3 = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Other Info 3 Row ')]");
        List<WebElement> Info4 = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Other Info 4 Row ')]");
        List<WebElement> Info5 = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Other Info 5 Row ')]");
        for (int i = 0; i < cashTab.size(); i++) {
            enterListData(debit.get(i), dataFile, "Accounts", "Debit", i);
            enterListData(creditList.get(i), dataFile, "Accounts", "Credit", i);
            enterListDate(dueDate.get(i), dataFile, "Accounts", "DueDate", i);
            enterListData(referenceBillNo.get(i), dataFile, "Accounts", "ReferenceBillNo", i);
            enterListDate(referenceBillDate.get(i), dataFile, "Accounts", "ReferenceBillDate", i);
            enterListData(departmentRowList.get(i), dataFile, "Accounts", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "Accounts", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "Accounts", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "Accounts", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "Accounts", "Comments", i);
            enterListData(Info1.get(i), dataFile, "Accounts", "OtherInfo1", i);
            enterListData(Info2.get(i), dataFile, "Accounts", "OtherInfo2", i);
            enterListData(Info3.get(i), dataFile, "Accounts", "OtherInfo3", i);
            enterListData(Info4.get(i), dataFile, "Accounts", "OtherInfo4", i);
            enterListData(Info5.get(i), dataFile, "Accounts", "OtherInfo5", i);

        }
    }

    public void addAllocations() {
        EnterData( "//Edit[@Name='Department']", dataFile, "Allocations", "Department");
        EnterData( "//Edit[@Name='Project']", dataFile, "Allocations", "Project");
        EnterData("//Edit[@Name='Profit Centre']", dataFile, "Allocations", "ProfitCentre");
        EnterData( "//Edit[@Name='Cost Centre']", dataFile, "Allocations", "CostCentre");
    }
}