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

public class GiftVoucherReceipts extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public GiftVoucherReceipts(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void giftVoucherReceipts(String payableVoucher,String tempAPIBodyUpdate, String apiResponse, String outputFile) throws Exception {
        long BankReceiptsStart = System.nanoTime();
        navigateToMastersWhen3Steps("Finance","Receipts","Gift Voucher Receipts");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        EnterDate("//Edit[@Name='From Date *']",dataFile,"GeneralInformation","FromDate");
        EnterDate("//Edit[@Name='To Date *']",dataFile,"GeneralInformation","ToDate");
        enterBranch(dataFile,"GeneralInformation","Branch");
        EnterData("//Edit[@Name='GV Control Account *']",dataFile,"GeneralInformation","GVControlAccount");
        EnterData("//Edit[@Name='Division *']",dataFile,"GeneralInformation","Division");
        EnterData("//Edit[@Name='Executive']",dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");


//        Parties
        long partiesStart =System.nanoTime();
        parties();
        long partiesEnd =System.nanoTime()- partiesStart;
        FileUtil.writeTimeLogInMinutes("Other Info Tab:- ", partiesEnd);

//        bills Receivables
        long billsReceivablesStart =System.nanoTime();
        billsReceivables();
        long billsReceivablesEnd=System.nanoTime()- billsReceivablesStart;
        FileUtil.writeTimeLogInMinutes("bills Receivables Tab:- ",billsReceivablesEnd);

//        bills Payable
        long billsPayableStart =System.nanoTime();
        billsPayable(payableVoucher);
        long billsPayableEnd=System.nanoTime()- billsPayableStart;
        FileUtil.writeTimeLogInMinutes("bills Receivables Tab:- ",billsPayableEnd);

//        Other info
        long otherInfoTabStart =System.nanoTime();
        otherInfo();
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Other Info Tab:- ", otherInfoTabEnd);


        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"GiftVoucherReceipts");

//        deleteTransactionUsingVoucherNumber(newVoucherID);
        long BankReceiptsEnd = System.nanoTime()- BankReceiptsStart;
        FileUtil.writeTimeLogInMinutes("GiftVoucherReceipts end at:- ", BankReceiptsEnd );
    }

    public void parties() throws IOException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'  Parties  ')]");
        List<String> cashTab =readExcelData(dataFile,"Parties","PartyAccountCode");
        for (int i = 0; i < cashTab.size() ; i++) {
            addData("xpath","//Edit[@Name='Party Code Row "+i+", Not sorted.']",dataFile,"Parties","PartyAccountCode",i);
        }
        List<WebElement> voucherNum = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Gift Voucher No * Row ')]");
        List<WebElement>  voucherType= common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Voucher Type Row ')]");
        List<WebElement>  denominations= common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Denomination Row ')]");
        List<WebElement>  qty= common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity Row ')]");
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement>  division= common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < cashTab.size() ; i++) {
            enterListData(voucherNum.get(i),dataFile,"Parties","GiftVoucherNo",i);
            enterListData(voucherType.get(i),dataFile,"Parties","VoucherType",i);
            enterListData(denominations.get(i),dataFile,"Parties","Denomination",i);
            enterListData(qty.get(i),dataFile,"Parties","Quantity",i);
            List<WebElement> findReceivable = common.findWebElements("xpath", "//Window[@Name='Bills Receivable']/Table/*[starts-with(@Name,'Data Panel')]/*[contains(@Name,'Row ')]/*[contains(@Name,'Voucher No row ')]");
            System.out.println("receivableCount"+findReceivable.size());
            for (WebElement find : findReceivable) {
                if (find.getText().equals("SI 2")) {
                    find.click();
                    common.clickElement("xpath", "//Button[@Name='Ok']");
                    break;
                }
            }
            enterListData(executive.get(i),dataFile,"Parties","Executive",i);
            enterListData(division.get(i),dataFile,"Parties","Division",i);
            enterListData(departmentRowList.get(i),dataFile,"Parties","Department",i);
            enterListData(projectRowList.get(i),dataFile,"Parties","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"Parties","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"Parties","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"Parties","Comments",i);
        }
    }

    public void billsReceivables() throws IOException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'  BillsReceivable  ')]");
        List<WebElement> adjustAmount = common.findWebElements("xpath", "//Table[@Name='BillsReceivable']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount Adjusted * Row ')]");
        for (int i = 0; i < adjustAmount.size() ; i++) {
            enterListData(adjustAmount.get(i),dataFile,"BillsReceivable","AmountAdjusted",i);
        }
    }

    public void billsPayable(String payableVoucher) throws IOException, InterruptedException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'  BillsPayable  ')]");
        adjustAmountInBillsPayable(dataFile,payableVoucher);
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
