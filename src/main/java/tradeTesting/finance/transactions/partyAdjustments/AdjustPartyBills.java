package tradeTesting.finance.transactions.partyAdjustments;

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

    public void adjustPartyBills(String payableVoucher1,String payableVoucher2,String payableVouchers,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long adjustPartyBillsStarts = System.nanoTime();

        navigateToMastersWhen3Steps("Finance","Party Adjustments","Adjust Party Bills");
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
        billsReceivables(payableVoucher1,payableVoucher2);
        long BillsReceivablesEnd =System.nanoTime()- BillsReceivablesStart;
        FileUtil.writeTimeLogInMinutes("BillsReceivables Tab End:- ", BillsReceivablesEnd);

        //BillsPayable
        long BillsPayableStart =System.nanoTime();
        billsPayable(payableVouchers);
        long BillsPayableEnd =System.nanoTime()- BillsPayableStart;
        FileUtil.writeTimeLogInMinutes("BillsPayables Tab End:- ", BillsPayableEnd);

        //Other info
        long otherInfoTabStart =System.nanoTime();
        otherInfo();
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Other Info Tab:- ", otherInfoTabEnd);

        transactionSave();
        String newVoucherID = newTransactionID(oldVoucherID);
        System.out.println("newID: " + newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID, "Voucher Numbers are same. Check Transaction.");
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"AdjustPartyBills");

        deleteTransactionUsingVoucherNumber(newVoucherID);
        long adjustPartyBillsEnd = System.nanoTime()- adjustPartyBillsStarts;
        FileUtil.writeTimeLogInMinutes("Adjust party bills End at:- ", adjustPartyBillsEnd );
    }

    public void billsReceivables(String receivableVoucher1,String receivableVoucher2) throws InterruptedException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Receivables  ')]");
        Thread.sleep(2000);
        List<WebElement> towardsVoucherNum = common.findWebElements("xpath", "//Table[@Name='BillsReceivable']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Towards VNo * Row')]");
        System.out.println("Towards vouchers Size :"+towardsVoucherNum.size());
        boolean voucherFound=false;
        for (int i=0;i< towardsVoucherNum.size();i++){
            WebElement text=towardsVoucherNum.get(i);
            System.out.println("Towards vouchers getTet :"+text.getText());
            if (text.getText().equals(receivableVoucher1)){
                voucherFound = true;
                text.click();
                text.sendKeys(Keys.TAB, Keys.TAB,Keys.SPACE);
                EnterData("//Edit[@Name='Amount Adjusted * Row "+i+", Not sorted.']",dataFile,"BillsReceivable","AmountAdjusted");
                break;
            }
        }

        List<WebElement> towardsVoucherNum1 = common.findWebElements("xpath", "//Table[@Name='BillsReceivable']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Towards VNo * Row')]");
        System.out.println("Towards vouchers Size :"+towardsVoucherNum1.size());
        boolean voucherFound1=false;
        for (int i=0;i< towardsVoucherNum1.size();i++){
            WebElement text=towardsVoucherNum1.get(i);
            System.out.println("Towards vouchers getTet :"+text.getText());
            if (text.getText().equals(receivableVoucher2)){
                voucherFound1 = true;
                text.click();
                text.sendKeys(Keys.TAB,Keys.TAB,Keys.SPACE);
                EnterData("//Edit[@Name='Amount Adjusted * Row "+i+", Not sorted.']",dataFile,"BillsReceivable","AmountAdjusted");
                WebElement element = common.findWebElement("xpath", "//Edit[@Name=' Row 3, Not sorted.']");
                Actions actions = new Actions(driver);
                actions.contextClick(element).perform();
                common.clickElement("xpath", "//MenuItem[@Name='Delete Invalid Rows']");
                break;
            }
            else if(!text.getText().equals(receivableVoucher2)){
                towardsVoucherNum.get(i).click();
                towardsVoucherNum.get(i).sendKeys(Keys.DOWN);
            }
            else{
                System.out.println("Towards voucher number is not found");
            }
        }
    }

    public void billsPayable(String adjustPayable) throws InterruptedException, IOException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'  BillsPayable  ')]");
        Thread.sleep(2000);
        adjustAmountInBillsPayable(dataFile,adjustPayable);
    }

    public void otherInfo() throws InterruptedException, IOException {
        Thread.sleep(1000);
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
