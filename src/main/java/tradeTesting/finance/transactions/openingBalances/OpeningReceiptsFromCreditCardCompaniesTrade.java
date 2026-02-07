package tradeTesting.finance.transactions.openingBalances;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.awt.*;
import java.util.List;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class OpeningReceiptsFromCreditCardCompaniesTrade  extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public OpeningReceiptsFromCreditCardCompaniesTrade(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String openingReceiptsFromCreditCardCompaniesTrade(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Finance","Opening Balances","Opening Receipts from Credit Card Companies");
        Thread.sleep(1000);
        long generalInfoStart=System.nanoTime();
        Thread.sleep(2500);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterControlAccount(dataFile,"GeneralInformation","ControlAccount");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Opening Receipts from CCC General information End:- ", generalInfoEndTime);

        //F3-Parties
        long addProductStart=System.nanoTime();
        addAccounts();
        long addProductEnd=System.nanoTime()-addProductStart;
        FileUtil.writeTimeLogInMinutes("Opening Receipts from CCC Add Accounts:- ",addProductEnd);
        //other info
        long addOtherInfoStart =System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  OtherInfo  ')]");
        otherInfo();
        long addOtherInfoEnd =System.nanoTime()- addOtherInfoStart;
        FileUtil.writeTimeLogInMinutes("Opening Receipts from CCC Other Info:- ", addOtherInfoEnd);
        //allocations
        long allocationsTabStart=System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  Allocations  ')]");
        addAllocations();
        long allocationsTabEnd=System.nanoTime() - allocationsTabStart;
        FileUtil.writeTimeLogInMinutes("Opening Receipts from CCC Allocations Tab:- ", allocationsTabEnd);
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long salesInvoiceEnd = System.nanoTime() - start ;
        FileUtil.writeTimeLogInMinutes("Opening Receipts from CCC ended at:- ", salesInvoiceEnd );
        //API
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"OpeningReceiptsFromCCC");
        deleteTransactionUsingVoucherNumber(newVoucherID);
        return newVoucherID;
    }

    public void addAccounts() throws IOException {
        List<String> cashTab = readExcelData(dataFile, "Accounts", "AccountCode");
        for (int i = 0; i < cashTab.size(); i++) {
            addData("xpath", "//Edit[@Name='Account Code Row " + i + ", Not sorted.']", dataFile, "Accounts", "AccountCode", i);
        }
        List<WebElement> swipeMachineType = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Swipe Machine Type * Row ')]");
        List<WebElement> swipeType = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Swipe Type * Row ')]");
        List<WebElement> amount = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> cardNum = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Card No Row ')]");
        List<WebElement> expiryDate = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Expiry Date Row ')]");
        List<WebElement> approvalNum = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Approval No * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < cashTab.size(); i++) {
            enterListData(swipeMachineType.get(i), dataFile, "Accounts", "SwipeMachineType", i);
            enterListData(swipeType.get(i), dataFile, "Accounts", "SwipeType", i);
            enterListDate(amount.get(i), dataFile, "Accounts", "Amount", i);
            enterListData(cardNum.get(i), dataFile, "Accounts", "CardNo", i);
            enterListDate(expiryDate.get(i), dataFile, "Accounts", "ExpiryDate", i);
            enterListDate(approvalNum.get(i), dataFile, "Accounts", "ApprovalNo", i);
            enterListData(departmentRowList.get(i), dataFile, "Accounts", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "Accounts", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "Accounts", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "Accounts", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "Accounts", "Comments", i);

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

    public void addAllocations() {
        EnterData( "//Edit[@Name='Department']", dataFile, "Allocations", "Department");
        EnterData( "//Edit[@Name='Project']", dataFile, "Allocations", "Project");
        EnterData("//Edit[@Name='Profit Centre']", dataFile, "Allocations", "ProfitCentre");
        EnterData( "//Edit[@Name='Cost Centre']", dataFile, "Allocations", "CostCentre");
    }
}