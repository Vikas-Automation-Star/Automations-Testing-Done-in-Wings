package tradeTesting.finance.transactions.receipts;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class ChequeWiseCollection extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public ChequeWiseCollection(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void chequeWiseCollections(String receivableVoucher,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long BankReceiptsStart = System.nanoTime();

        navigateToMastersWhen3Steps("Finance","Receipts","Cheque Wise Collection");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        Thread.sleep(1000);
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        EnterData("//Edit[@Name='Route']",dataFile,"GeneralInformation","Route");
        EnterData("//Edit[@Name='Party Account *']",dataFile,"GeneralInformation","PartyAccount");
        EnterData("//Edit[@Name='Bank Account *']",dataFile,"GeneralInformation","BankAccount");
        EnterData("//Edit[@Name='Sales Executive *']",dataFile,"GeneralInformation","SalesExecutive");
        EnterData("//Edit[@Name='Discount Account']",dataFile,"GeneralInformation","DiscountAccount");
        EnterDate("//Edit[@Name='Cheque Date *']",dataFile,"GeneralInformation","ChequeDate");
        EnterData("//Edit[@Name='Cheque No *']",dataFile,"GeneralInformation","ChequeNo");
        EnterData("//Edit[@Name='Cheque Amount *']",dataFile,"GeneralInformation","ChequeAmount");
//        common.clickElement("xpath","//CheckBox[@Name='Auto Adjust']");
        EnterData("//Edit[@Name='Drawn On Bank']",dataFile,"GeneralInformation","BankOnWhichDrawn");
        EnterData("//Edit[@Name='Bank Branch *']",dataFile,"GeneralInformation","BankBranch");
        enterRemarks(dataFile,"GeneralInformation","Remarks");


        //bills Receivables
        long billsReceivablesStart =System.nanoTime();
        billsReceivables(receivableVoucher);
        long billsReceivablesEnd=System.nanoTime()- billsReceivablesStart;
        FileUtil.writeTimeLogInMinutes("bills Receivables Tab:- ",billsReceivablesEnd);

        //Other info
        long otherInfoTabStart =System.nanoTime();
        otherInfo();
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Other Info Tab:- ", otherInfoTabEnd);


        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"ChequesWiseCollections");

        deleteTransactionUsingVoucherNumber(newVoucherID);
        long BankReceiptsEnd = System.nanoTime()- BankReceiptsStart;
        FileUtil.writeTimeLogInMinutes("ChequesWiseCollections end at:- ", BankReceiptsEnd );
    }


    public void billsReceivables(String receivableVoucher){
        common.clickElement("xpath", "//TabItem[contains(@Name,'  BillsReceivable  ')]");
        List<WebElement> towardsVoucherNum = common.findWebElements("xpath", "//Table[@Name='BillsReceivable']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Towards VNo * Row')]");
        System.out.println("Towards vouchers Size :"+towardsVoucherNum.size());
        boolean voucherFound=false;
        for (int i=0;i< towardsVoucherNum.size();i++){
            WebElement text=towardsVoucherNum.get(i);
            System.out.println("Towards vouchers getTet :"+text.getText());
            if (text.getText().equals(receivableVoucher)){
                voucherFound = true;
                text.click();
                text.sendKeys(Keys.TAB,Keys.SPACE);
                EnterData("//Edit[@Name='Amount Adjusted * Row "+i+", Not sorted.']",dataFile,"BillsReceivable","AmountAdjusted");
                common.deleteInvalidRows();
                break;
            }else if(!text.getText().equals(receivableVoucher)){
                towardsVoucherNum.get(i).click();
                towardsVoucherNum.get(i).sendKeys(Keys.DOWN);
            }
            else{
                System.out.println("Towards voucher number is not found");
            }
        }
        adjustAmountInBillsReceivables(dataFile,receivableVoucher);
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
