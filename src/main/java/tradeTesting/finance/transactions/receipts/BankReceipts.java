package tradeTesting.finance.transactions.receipts;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class BankReceipts extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public BankReceipts(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void bankReceipts(String receivableVoucher, String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long BankReceiptsStart = System.nanoTime();

        navigateToMastersWhen3Steps("Finance","Receipts","Bank Receipts");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        Thread.sleep(1000);
        EnterData("//Edit[@Name='Voucher Type']",dataFile,"GeneralInformation","voucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterBankAccountCode(dataFile,"GeneralInformation","BankAccountCode");
        EnterData("//Edit[@Name='Discount Account']", dataFile,"GeneralInformation", "DiscountAccount");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");


        //Parties
        long partiesStart =System.nanoTime();
        parties();
        long partiesEnd =System.nanoTime()- partiesStart;
        FileUtil.writeTimeLogInMinutes("Other Info Tab:- ", partiesEnd);

        //bills Receivables
        long billsReceivablesStart =System.nanoTime();
        billsReceivables(receivableVoucher);
        long billsReceivablesEnd=System.nanoTime()- billsReceivablesStart;
        FileUtil.writeTimeLogInMinutes("bills Receivables Tab:- ",billsReceivablesEnd);

        //other info
        long otherInfoTabStart =System.nanoTime();
        otherInfo();
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Other Info Tab:- ", otherInfoTabEnd);

        // allocations
        long allocationsTabStart=System.nanoTime();
        addAllocations();
        long allocationsTabEnd=System.nanoTime() - allocationsTabStart;
        FileUtil.writeTimeLogInMinutes("Allocations Tab:- ", allocationsTabEnd);

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"BankReceipts");

//        deleteTransactionUsingVoucherNumber(newVoucherID);
        long BankReceiptsEnd = System.nanoTime()- BankReceiptsStart;
        FileUtil.writeTimeLogInMinutes("Bank Receipts end at:- ", BankReceiptsEnd );
    }

    public void parties() throws IOException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'  Parties  ')]");
        List<String> cashTab =readExcelData(dataFile,"Parties","PartyAccountCode");
        for (int i = 0; i < cashTab.size() ; i++) {
            addData("xpath","//Edit[@Name='Party Code Row "+i+", Not sorted.']",dataFile,"Parties","PartyAccountCode",i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        common.clickElement("xpath","//Header[@Name='Charges']");
        common.sliderHandling("xpath", "//Table[@Name='Parties']/*/Thumb[@Name='Position']", 400, 0);
        List<WebElement> chargesAccRowList = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Account Code Row ')]");
        List<WebElement> chargesAmountRowList = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Row ')]");
        List<WebElement> discount = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Parties']/*/Thumb[@Name='Position']", -400, 0);
        for (int i = 0; i < cashTab.size() ; i++) {
            enterListData(amountRowList.get(i), dataFile, "Parties", "Amount",i);
            enterListData(chequeNo.get(i), dataFile, "Parties", "ChequeNo" ,i);
            enterListDate(chequeDate.get(i),dataFile,"Parties","ChequeDate",i);
            enterListData(drawnOnRowList.get(i), dataFile, "Parties", "DrawnOnBankAccount",i);
            enterListData(drawnOnBranchBranchRowList.get(i), dataFile, "Parties", "DrawnOnBankBranch",i);
            enterListData(tdsTransNatureRowList.get(i), dataFile, "Parties", "TDSTransactionNature",i);
            enterListData(tdsAccountRowList.get(i), dataFile, "Parties", "TDSAccount",i);
            enterListData(tdsAmountRowList.get(i), dataFile, "Parties", "TDSAmount",i);
            enterListData(chargesAccRowList.get(i), dataFile, "Parties", "ChargesAccount" , i);
            enterListData(chargesAmountRowList.get(i), dataFile, "Parties", "Charges" , i);
            enterListData(discount.get(i), dataFile, "Parties", "Discount",i);
            common.sliderHandling("xpath", "//Table[@Name='Parties']/*/Thumb[@Name='Position']", 300, 0);
            enterListData(departmentRowList.get(i),dataFile,"Parties","Department",i);
            enterListData(projectRowList.get(i),dataFile,"Parties","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"Parties","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"Parties","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"Parties","Comments",i);
            common.sliderHandling("xpath", "//Table[@Name='Parties']/*/Thumb[@Name='Position']", -500, 0);
        }
    }

    public void billsReceivables(String receivableVoucher){
        common.clickElement("xpath", "//TabItem[contains(@Name,'  BillsReceivable  ')]");
//        adjustAmountInBillsReceivables(dataFile,receivableVoucher);
        List<WebElement> towardsVoucherNum1 = common.findWebElements("xpath", "//Table[@Name='BillsReceivable']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Towards VNo * Row')]");
        System.out.println("Towards vouchers Size :"+towardsVoucherNum1.size());
        boolean voucherFound1=false;
        for (int i=0;i< towardsVoucherNum1.size();i++){
            WebElement text=towardsVoucherNum1.get(i);
            System.out.println("Towards vouchers getTet :"+text.getText());
            if (text.getText().equals(receivableVoucher)){
                voucherFound1 = true;
                text.click();
                text.sendKeys(Keys.TAB,Keys.TAB,Keys.SPACE);
                EnterData("//Edit[@Name='Amount Adjusted * Row "+i+", Not sorted.']",dataFile,"BillsReceivable","AmountAdjusted");
                WebElement element = common.findWebElement("xpath", "//Edit[@Name=' Row 13, Not sorted.']");
                Actions actions = new Actions(driver);
                actions.contextClick(element).perform();
                common.clickElement("xpath", "//MenuItem[@Name='Delete Invalid Rows']");
                break;
            }
            else if(!text.getText().equals(receivableVoucher)){
                towardsVoucherNum1.get(i).click();
                towardsVoucherNum1.get(i).sendKeys(Keys.DOWN);
            }
            else{
                System.out.println("Towards voucher number is not found");
            }
        }
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

    public void addAllocations() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'  Allocations  ')]");
        EnterData( "//Edit[@Name='Department']", dataFile, "Allocations", "Department");
        EnterData( "//Edit[@Name='Project']", dataFile, "Allocations", "Project");
        EnterData("//Edit[@Name='Profit Centre']", dataFile, "Allocations", "ProfitCentre");
        EnterData( "//Edit[@Name='Cost Centre']", dataFile, "Allocations", "CostCentre");
    }
}
