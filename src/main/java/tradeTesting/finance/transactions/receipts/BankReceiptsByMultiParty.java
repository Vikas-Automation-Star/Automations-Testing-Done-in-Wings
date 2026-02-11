package tradeTesting.finance.transactions.receipts;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class BankReceiptsByMultiParty extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public BankReceiptsByMultiParty(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void bankReceiptsByMultiParty(String tempAPIBodyUpdate, String apiResponse,String outputFile) throws Exception {
        long BankReceiptsStart = System.nanoTime();

        navigateToMastersWhen3Steps("Finance","Receipts","Bank Receipts By Multi Party[SalesExecutive]");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        Thread.sleep(1000);
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
//        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        EnterDate("//Edit[@Name='From Date *']",dataFile, "GeneralInformation","FromDate");
        EnterDate("//Edit[@Name='To Date *']",dataFile,"GeneralInformation","ToDate");
        enterBranch(dataFile,"GeneralInformation","Branch");
        EnterData("//Edit[@Name='Bank Account Code']",dataFile,"GeneralInformation","BankAccountCode");
        EnterData("//Edit[@Name='Sales Executive *']",dataFile,"GeneralInformation","SalesExecutive");
        EnterData("//Edit[@Name='Division *']",dataFile,"GeneralInformation","Division");
        EnterData("//Edit[@Name='Discount Account']",dataFile,"GeneralInformation","DiscountAccount");
        enterRemarks(dataFile,"GeneralInformation","Remarks");


        //Accounts
        long accountsStart =System.nanoTime();
        accounts();
        long accountsStartEnd =System.nanoTime()- accountsStart;
        FileUtil.writeTimeLogInMinutes("Accounts tab End:- ", accountsStartEnd);

        //Other Accounts
        long otherAccountsStart =System.nanoTime();
        otherAccount();
        long otherAccountsEnd =System.nanoTime()- otherAccountsStart;
        FileUtil.writeTimeLogInMinutes("Other Accounts tab End:- ", otherAccountsEnd);

        //Incomes And Expenses
        long incomesAndExpensesStart =System.nanoTime();
        incomeAndExpenses();
        long incomesAndExpensesEnd =System.nanoTime()- incomesAndExpensesStart;
        FileUtil.writeTimeLogInMinutes("Incomes And Expenses tab End:- ", incomesAndExpensesEnd);

        //Other info
        long otherInfoTabStart =System.nanoTime();
        otherInfo();
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Other Info Tab:- ", otherInfoTabEnd);



        //bills Receivables
        long billsReceivablesStart =System.nanoTime();
//        billsReceivables(desiredVoucher);
        long billsReceivablesEnd=System.nanoTime()- billsReceivablesStart;
        FileUtil.writeTimeLogInMinutes("bills Receivables Tab:- ",billsReceivablesEnd);


        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"BankReceiptsByMultiParty");

//        deleteTransactionUsingVoucherNumber(newVoucherID);
        long BankReceiptsEnd = System.nanoTime()- BankReceiptsStart;
        FileUtil.writeTimeLogInMinutes("BankReceiptsByMultiParty end at:- ", BankReceiptsEnd );
    }

    public void accounts() throws IOException, InterruptedException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'  Accounts  ')]");
        java.util.List<String> postDatedCheques=readExcelData(dataFile,"Accounts","AccountCode");
        for (int i = 0; i < postDatedCheques.size(); i++) {
            addData("xpath","//Edit[@Name='Party Account Code Row "+i+", Not sorted.']",dataFile,"Accounts","AccountCode",i);
        }
        java.util.List<WebElement> amoutRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        java.util.List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        java.util.List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        java.util.List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        java.util.List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        java.util.List<WebElement>  hsn= common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Accounts']/*/Thumb[@Name='Position']", 550, 0);
        java.util.List<WebElement>  gstProductCategory= common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        java.util.List<WebElement>  cessProductCategory= common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
        java.util.List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        java.util.List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        java.util.List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        java.util.List<WebElement>  chargesAcctCode= common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Account Code Row ')]");
        java.util.List<WebElement>  chargesAmt= common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Row ')]");
        java.util.List<WebElement>  incomesAcct= common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Income Account Row ')]");
        java.util.List<WebElement>  income= common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Income Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Accounts']/*/Thumb[@Name='Position']", 550, 0);
        java.util.List<WebElement> discount = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Row ')]");
        java.util.List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        java.util.List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        java.util.List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        java.util.List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        java.util.List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        java.util.List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Accounts']/*/Thumb[@Name='Position']", -750, 0);
        for (int i = 0; i < postDatedCheques.size() ; i++) {
            enterListData(amoutRowList.get(i), dataFile, "Accounts", "InclusiveAmount",i);
            Thread.sleep(1000);
            List<WebElement> findReceivable = common.findWebElements("xpath", "//Window[@Name='Bills Receivable']/Table/*[starts-with(@Name,'Data Panel')]/*[contains(@Name,'Row ')]/*[contains(@Name,'Voucher No row ')]");
            System.out.println("receivableCount"+findReceivable.size());
//            for (WebElement find:findReceivable){
////                System.out.println(findReceivable.get(i).getText());
//                if (find.getText().equals("SI 2"))
//                {
//                    find.click();common.clickElement("xpath","//Button[@Name='Ok']");
//                }
//            }
            for (WebElement find : findReceivable) {
                if (find.getText().equals("SI 2")) {
                    find.click();
                    common.clickElement("xpath", "//Button[@Name='Ok']");
                    break;
                }
            }

            enterListData(chequeNo.get(i), dataFile, "Accounts", "ChequeNo" ,i);
            enterListDate(chequeDate.get(i),dataFile,"Accounts","ChequeDate",i);
            enterListData(drawnOnRowList.get(i), dataFile, "Accounts", "DrawnOnBankAccount",i);
            enterListData(drawnOnBranchBranchRowList.get(i), dataFile, "Accounts", "DrawnOnBankBranch",i);
            enterListData(hsn.get(i), dataFile, "Accounts", "HSN",i);
            enterListData(gstProductCategory.get(i), dataFile, "Accounts", "GSTProductCategory",i);
            enterListData(cessProductCategory.get(i), dataFile, "Accounts", "CESSProductCategory",i);
            enterListData(tdsTransNatureRowList.get(i), dataFile, "Accounts", "TDSTransactionNature",i);
            enterListData(tdsAccountRowList.get(i), dataFile, "Accounts", "TDSAccount",i);
            enterListData(tdsAmountRowList.get(i), dataFile, "Accounts", "TDSAmount",i);
            enterListData(chargesAcctCode.get(i), dataFile, "Accounts", "ChargesAccountCode",i);
            common.sliderHandling("xpath", "//Table[@Name='Accounts']/*/Thumb[@Name='Position']", 300, 0);
            enterListData(chargesAmt.get(i), dataFile, "Accounts", "Charges",i);
            enterListData(incomesAcct.get(i), dataFile, "Accounts", "IncomeAccount",i);
            enterListData(income.get(i), dataFile, "Accounts", "Income",i);
            enterListData(discount.get(i), dataFile, "Accounts", "Discount" , i);
            enterListData(executive.get(i),dataFile,"Accounts","Executive",i);
            enterListData(departmentRowList.get(i),dataFile,"Accounts","Department",i);
            enterListData(projectRowList.get(i),dataFile,"Accounts","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"Accounts","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"Accounts","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"Accounts","Comments",i);
            common.sliderHandling("xpath", "//Table[@Name='Accounts']/*/Thumb[@Name='Position']", -700, 0);
        }
    }

    public void otherAccount() throws IOException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'  OtherAccounts  ')]");
        java.util.List<String> postDatedCheques=readExcelData(dataFile,"OtherAccounts","AccountCode");
        for (int i = 0; i < postDatedCheques.size(); i++) {
            addData("xpath","//Edit[@Name='Account Code Row "+i+", Not sorted.']",dataFile,"OtherAccounts","AccountCode",i);
        }

        java.util.List<WebElement> state = common.findWebElements("xpath", "//Table[@Name='OtherAccounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'State Row ')]");
        java.util.List<WebElement>  country= common.findWebElements("xpath", "//Table[@Name='OtherAccounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Country Row ')]");
        java.util.List<WebElement>  placeOfSupply= common.findWebElements("xpath", "//Table[@Name='OtherAccounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Place Of Supply Row ')]");
        java.util.List<WebElement>  gstTransactionType= common.findWebElements("xpath", "//Table[@Name='OtherAccounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Transaction Type Row ')]");
        java.util.List<WebElement> amoutRowList = common.findWebElements("xpath", "//Table[@Name='OtherAccounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        java.util.List<WebElement>  hsn= common.findWebElements("xpath", "//Table[@Name='OtherAccounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        java.util.List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='OtherAccounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        java.util.List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='OtherAccounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        java.util.List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='OtherAccounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        java.util.List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='OtherAccounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        java.util.List<WebElement>  gstProductCategory= common.findWebElements("xpath", "//Table[@Name='OtherAccounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        java.util.List<WebElement>  cessProductCategory= common.findWebElements("xpath", "//Table[@Name='OtherAccounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='OtherAccounts']/*/Thumb[@Name='Position']", 550, 0);
        java.util.List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='OtherAccounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        java.util.List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='OtherAccounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        java.util.List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='OtherAccounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        java.util.List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherAccounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        java.util.List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherAccounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        java.util.List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='OtherAccounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='OtherAccounts']/*/Thumb[@Name='Position']", -550, 0);
        for (int i = 0; i < postDatedCheques.size() ; i++) {
            enterListData(state.get(i), dataFile, "OtherAccounts", "State" ,i);
            enterListData(country.get(i), dataFile, "OtherAccounts", "Country" ,i);
            enterListData(placeOfSupply.get(i), dataFile, "OtherAccounts", "PlaceOfSupply" ,i);
            enterListData(gstTransactionType.get(i), dataFile, "OtherAccounts", "GSTTransactionType" ,i);
            enterListData(amoutRowList.get(i), dataFile, "OtherAccounts", "InclusiveAmount" ,i);
            enterListData(hsn.get(i), dataFile, "OtherAccounts", "HSN",i);
            enterListData(chequeNo.get(i), dataFile, "OtherAccounts", "ChequeNo" ,i);
            enterListDate(chequeDate.get(i),dataFile,"OtherAccounts","ChequeDate",i);
            enterListData(drawnOnRowList.get(i), dataFile, "OtherAccounts", "DrawnOnBankAccount",i);
            enterListData(drawnOnBranchBranchRowList.get(i), dataFile, "OtherAccounts", "DrawnOnBankBranch",i);
            enterListData(gstProductCategory.get(i), dataFile, "OtherAccounts", "GSTProductCategory",i);
            enterListData(cessProductCategory.get(i), dataFile, "OtherAccounts", "CESSProductCategory",i);
            enterListData(executive.get(i),dataFile,"OtherAccounts","Executive",i);
            enterListData(departmentRowList.get(i),dataFile,"OtherAccounts","Department",i);
            enterListData(projectRowList.get(i),dataFile,"OtherAccounts","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"OtherAccounts","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"OtherAccounts","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"OtherAccounts","Comments",i);
            common.sliderHandling("xpath", "//Table[@Name='OtherAccounts']/*/Thumb[@Name='Position']", -750, 0);
        }
    }

    public void incomeAndExpenses() throws IOException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'  IncomesAndExpenses  ')]");
        java.util.List<String> postDatedCheques=readExcelData(dataFile,"IncomesAndExpenses","AccountCode");
        for (int i = 0; i < postDatedCheques.size(); i++) {
            addData("xpath","//Edit[@Name='Account Code Row "+i+", Not sorted.']",dataFile,"IncomesAndExpenses","AccountCode",i);
        }
        java.util.List<WebElement> debitAcct = common.findWebElements("xpath", "//Table[@Name='IncomesAndExpenses']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Debit Amount Row ')]");
        java.util.List<WebElement>  creditAcct= common.findWebElements("xpath", "//Table[@Name='IncomesAndExpenses']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Credit Amount Row ')]");
        java.util.List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='IncomesAndExpenses']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        java.util.List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='IncomesAndExpenses']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        java.util.List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='IncomesAndExpenses']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        java.util.List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='IncomesAndExpenses']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        java.util.List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='IncomesAndExpenses']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        java.util.List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='IncomesAndExpenses']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < postDatedCheques.size() ; i++) {
            enterListData(debitAcct.get(i),dataFile,"IncomesAndExpenses","DebitAmount",i);
            enterListData(creditAcct.get(i),dataFile,"IncomesAndExpenses","CreditAmount",i);
            enterListData(executive.get(i),dataFile,"IncomesAndExpenses","Executive",i);
            enterListData(departmentRowList.get(i),dataFile,"IncomesAndExpenses","Department",i);
            enterListData(projectRowList.get(i),dataFile,"IncomesAndExpenses","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"IncomesAndExpenses","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"IncomesAndExpenses","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"IncomesAndExpenses","Comments",i);
        }
    }

    public void billsReceivables(String desiredVoucher){
        common.clickElement("xpath", "//TabItem[contains(@Name,'  BillsReceivable  ')]");
        adjustAmountInBillsReceivables(dataFile,desiredVoucher);
    }

    public void otherInfo() throws InterruptedException, IOException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'  OtherInfo  ')]");
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
//        Thread.sleep(3500);
//        common.clickElement("xpath","//Window/Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }

}
