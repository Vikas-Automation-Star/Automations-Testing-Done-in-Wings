package tradeTesting.finance.transactions.receipts;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class CashWiseCollection extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CashWiseCollection(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void cashWiseCollection(String receivableVoucher,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long BankReceiptsStart = System.nanoTime();
        navigateToMastersWhen3Steps("Finance","Receipts","Cash Wise Collection");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        Thread.sleep(1000);
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        EnterData("//Edit[@Name='Route']",dataFile,"GeneralInformation","Route");
        EnterData("//Edit[@Name='Party Account *']",dataFile,"GeneralInformation","PartyAccount");
        EnterData("//Edit[@Name='Cash Account Code']",dataFile,"GeneralInformation","CashAccountCode");
//        gstTransactionType("Inter State Sales to Unregistered Dealers");
        EnterData("//Edit[@Name='Sales Executive *']",dataFile,"GeneralInformation","SalesExecutive");
        EnterData("//Edit[@Name='Discount Account']",dataFile,"GeneralInformation","DiscountAccount");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        EnterData("//Edit[@Name='Cash Amount *']",dataFile,"GeneralInformation","CashAmount");
//        common.clickElement("xpath","//CheckBox[@Name='Auto Adjust']");


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
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"CashWiseCollections");

        deleteTransactionUsingVoucherNumber(newVoucherID);
        long BankReceiptsEnd = System.nanoTime()- BankReceiptsStart;
        FileUtil.writeTimeLogInMinutes("CashWiseCollections end at:- ", BankReceiptsEnd );

    }

    public void billsReceivables(String receivableVoucher){
        common.clickElement("xpath", "//TabItem[contains(@Name,'  BillsReceivable  ')]");
        List<WebElement> towardsVoucherNum1 = common.findWebElements("xpath", "//Table[@Name='BillsReceivable']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Towards VNo * Row ')]");
        System.out.println("Towards vouchers Size :"+ towardsVoucherNum1.size());
        boolean voucherFound1=false;
        for (int i = 0; i< towardsVoucherNum1.size(); i++){
            WebElement text= towardsVoucherNum1.get(i);
            System.out.println("Towards vouchers getTet :"+text.getText());
            if (text.getText().equals(receivableVoucher)){
                voucherFound1 = true;
                text.click();
                text.sendKeys(Keys.TAB,Keys.SPACE);
                EnterData("//Edit[@Name='Amount Adjusted * Row "+i+", Not sorted.']",dataFile,"BillsReceivable","AmountAdjusted");
//                EnterData("//Edit[@Name='Discount Row "+i+", Not sorted.']",dataFile,"BillsReceivable","Discount");
                WebElement element = common.findWebElement("xpath", "//Edit[@Name=' Row 2, Not sorted.']");
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

}
