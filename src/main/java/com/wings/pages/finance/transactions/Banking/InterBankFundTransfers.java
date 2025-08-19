package com.wings.pages.finance.transactions.Banking;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.utils.Common;
import org.testng.Assert;
import java.io.IOException;

public class InterBankFundTransfers extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public InterBankFundTransfers(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void bankFundTransfer() throws InterruptedException, IOException, ParseException {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Finance","Banking","Inter Bank Fund Transfers");
        Thread.sleep(1000);
        long generalInfoStart=System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterExchangeRate(dataFile,"GeneralInformation","ExchangeRate");
        EnterData("//Edit[@Name='From Bank Code']",dataFile,"GeneralInformation","FromBankAccountCode");
        EnterData("//Edit[@Name='To Bank Code']",dataFile,"GeneralInformation","ToBankAccountCode");
        Thread.sleep(1500);
        gstTransactionType("Intra State Purchase from Registered Dealers");
        Thread.sleep(1000);
        EnterData("//Edit[@Name='Amount *']",dataFile,"GeneralInformation","Amount");
        EnterData("//Edit[@Name='Cheque/EFT No *']",dataFile,"GeneralInformation","ChequeNo");
        EnterDate("//Edit[@Name='Cheque Date *']",dataFile,"GeneralInformation","ChequeDate");
        EnterData("//Edit[@Name='Charges Account Code']",dataFile,"GeneralInformation","ChargesAccountCode");
        EnterData("//Edit[@Name='Transfered Charges']",dataFile,"GeneralInformation","TransferedCharges");
        EnterData("//Edit[@Name='HSN']",dataFile,"GeneralInformation","HSN");
        EnterData("//Edit[@Name='GST Product Category']",dataFile,"GeneralInformation","GSTProductCategory");
        EnterData("//Edit[@Name='CESS Product Category']",dataFile,"GeneralInformation","CESSProductCategory");
        EnterData("//Edit[@Name='Receipt Charges']",dataFile,"GeneralInformation","ReceiptCharges");
        EnterData("//Edit[@Name='Supplier Bill No']",dataFile,"GeneralInformation","SupplierBillNo1");
        EnterDate("//Edit[@Name='Supplier Bill Date']",dataFile,"GeneralInformation","SupplierBillDate1");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        EnterData("//Edit[@Name='Department']",dataFile,"GeneralInformation","Department");
        EnterData("//Edit[@Name='Project']",dataFile,"GeneralInformation","Project");
        EnterData("//Edit[@Name='Profit Centre']",dataFile,"GeneralInformation","ProfitCentre");
        EnterData("//Edit[@Name='Cost Centre']",dataFile,"GeneralInformation","CostCentre");
        EnterData("//Edit[@Name='Remarks']",dataFile,"GeneralInformation","Remarks");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Cash Payments General information End:- ", generalInfoEndTime);

        //other Info
        long otherInfoStart = System.nanoTime();
        otherInfo();
        long otherInfoEnd = System.nanoTime() - otherInfoStart;
        FileUtil.writeTimeLogInMinutes("Cash Payments OtherInfo Tab:- ", otherInfoEnd);
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long salesInvoiceEnd = System.nanoTime() - start ;
        FileUtil.writeTimeLogInMinutes("Cash Payments ended at:- ", salesInvoiceEnd );
        //IO
        String voucher = newVoucherID.replaceAll("\\d", "");
        String number = newVoucherID.replaceAll("\\D", "");
        Thread.sleep(2000);
        long iofIlesStart=System.nanoTime();
        exportIOFiles("Generate Input File", voucher,number);
        exportIOFiles("Generate Output File", voucher,number);
        long ioFilesEnd=System.nanoTime()-iofIlesStart;
        FileUtil.writeTimeLogInMinutes("Cash Payments IO files ended at:- ", ioFilesEnd );

//        excelUtil.excelComparator("","",newVoucherID);
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
}