package com.wings.pages.finance.transactions.OpeningBalances;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.awt.*;
import java.io.IOException;

public class OpeningReceiptsFromCreditCardCompanies extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public OpeningReceiptsFromCreditCardCompanies(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String openingReceipts() throws InterruptedException, IOException, ParseException {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Finance","Opening Balances","Opening Receipts from Credit Card Companies");
        Thread.sleep(1000);
        long generalInfoStart=System.nanoTime();
        Thread.sleep(5000);
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

        long addOtherInfoStart =System.nanoTime();
        otherInfo();
        long addOtherInfoEnd =System.nanoTime()- addOtherInfoStart;
        FileUtil.writeTimeLogInMinutes("Opening Receipts from CCC Other Info:- ", addOtherInfoEnd);

        //allocations
        long allocationsTabStart=System.nanoTime();
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
        //IO
        String voucher = newVoucherID.replaceAll("\\d", "");
        String number = newVoucherID.replaceAll("\\D", "");
        Thread.sleep(2000);
        long iofIlesStart=System.nanoTime();
        exportIOFiles("Generate Input File", voucher,number);
        exportIOFiles("Generate Output File", voucher,number);
        long ioFilesEnd=System.nanoTime()-iofIlesStart;
        FileUtil.writeTimeLogInMinutes("Opening Receipts from CCC IO files ended at:- ", ioFilesEnd );

//        excelUtil.excelComparator("","",newVoucherID);
        return newVoucherID;
    }

    public void addAccounts() throws IOException {
        java.util.List<String> cashTab = readExcelData(dataFile, "Accounts", "AccountCode");
        for (int i = 0; i < cashTab.size(); i++) {
            addData("xpath", "//Edit[@Name='Account Code Row " + i + ", Not sorted.']", dataFile, "Accounts", "AccountCode", i);
        }
        java.util.List<WebElement> swipeMachineType = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Swipe Machine Type * Row ')]");
        java.util.List<WebElement> swipeType = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Swipe Type * Row ')]");
        java.util.List<WebElement> amount = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        java.util.List<WebElement> cardNum = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Card No Row ')]");
        java.util.List<WebElement> expiryDate = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Expiry Date Row ')]");
        java.util.List<WebElement> approvalNum = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Approval No * Row ')]");
        java.util.List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        java.util.List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        java.util.List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        java.util.List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        java.util.List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

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

    public void otherInfo() throws InterruptedException, IOException {
        navigateToOtherInfoTab();
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(5000);
//        common.clickElement("xpath","//Window/Button[@Name='OK']");
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
