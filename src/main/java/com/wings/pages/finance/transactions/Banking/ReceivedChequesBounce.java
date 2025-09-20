package com.wings.pages.finance.transactions.Banking;

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

public class ReceivedChequesBounce extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public ReceivedChequesBounce(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String receivedCheckBounce(String voucherNumber, String tempAPIBodyUpdate, String apiResponse, String outputFile) throws Exception {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Finance", "Banking", "Received Cheques Bounce");
        Thread.sleep(3000);
        long generalInfoStart = System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID = oldTTransactionID();
        enterVoucherType(dataFile, "GeneralInformation", "VoucherType");
        EnterDate("//Edit[@Name='Date *']", dataFile, "GeneralInformation", "Date");
        enterBranch(dataFile, "GeneralInformation", "Branch");
        enterCurrency(dataFile, "GeneralInformation", "TransactionCurrency");
        EnterData("//Edit[@Name='Bank Code']", dataFile, "GeneralInformation", "BankAccountCode");
        EnterData("//Edit[@Name='Account Code']", dataFile, "GeneralInformation", "AccountCode");
        Thread.sleep(1500);
        selectPendingsRCB(voucherNumber);
        Thread.sleep(1500);
        gstTransactionType("Intra State Sales Returns from Registered Dealers");
        EnterData("//Edit[@Name='Amount *']", dataFile, "GeneralInformation", "Amount");
        EnterData("//Edit[@Name='Cheque/EFT No *']", dataFile, "GeneralInformation", "ChequeNo");
        EnterDate("//Edit[@Name='Cheque Date *']", dataFile, "GeneralInformation", "ChequeDate");
        EnterData("//Edit[@Name='Reason']", dataFile, "GeneralInformation", "Reason");
        EnterData("//Edit[@Name='Charges Account Code']", dataFile, "GeneralInformation", "ChargesAccountCode");
        EnterData("//Edit[@Name='Charges']", dataFile, "GeneralInformation", "Charges");
        EnterData("//Edit[@Name='HSN']", dataFile, "GeneralInformation", "HSN");
        generalInfoSliderHandle(500);
        EnterData("//Edit[@Name='GST Product Category']", dataFile, "GeneralInformation", "GSTProductCategory");
        EnterData("//Edit[@Name='CESS Product Category']", dataFile, "GeneralInformation", "CESSProductCategory");
        EnterData("//Edit[@Name='Supplier Bill No *']", dataFile, "GeneralInformation", "SupplierBillNo");
        EnterDate("//Edit[@Name='Supplier Bill Date *']", dataFile, "GeneralInformation", "SupplierBillDate");
        enterExecutive(dataFile, "GeneralInformation", "Executive");
        EnterData("//Edit[@Name='Department']", dataFile, "GeneralInformation", "Department");
        EnterData("//Edit[@Name='Project']", dataFile, "GeneralInformation", "Project");
        EnterData("//Edit[@Name='Profit Centre']", dataFile, "GeneralInformation", "ProfitCentre");
        EnterData("//Edit[@Name='Cost Centre']", dataFile, "GeneralInformation", "CostCentre");
        enterRemarks(dataFile, "GeneralInformation", "Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Received Cheques Bounce General information End:- ", generalInfoEndTime);

        //bills Receivable
        long billsReceivablesStart = System.nanoTime();
        adjustAmountInPayablesAndReceivables(dataFile, voucherNumber);
        long billsReceivablesEnd = System.nanoTime() - billsReceivablesStart;
        FileUtil.writeTimeLogInMinutes("Received Cheques Bounce Bills Receivables:- ", billsReceivablesEnd);
        //other info
        long otherInfoTabStart = System.nanoTime();
        otherInfo();
        long otherInfoTabEnd = System.nanoTime() - otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Received Cheques Bounce Other Info:- ", otherInfoTabEnd);

        //saving and IO generating
        transactionSave();
        String newVoucherID = newTransactionID(oldVoucherID);
        System.out.println("newID: " + newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID, "Voucher Numbers are same. Check Transaction.");
        //api
        APIClient.validateAPIWithExcel(newVoucherID, tempAPIBodyUpdate, apiResponse, outputFile, "receivedChequesBounce");

        return newVoucherID;

    }

    public void adjustAmountInPayablesAndReceivables(String dataFile, String desiredVoucher) {
        List<WebElement> towardsVoucherNum = common.findWebElements("xpath", "//Table[@Name='BillsPayable']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Voucherentity No * Row ')]");
        System.out.println("Towards vouchers Size :" + towardsVoucherNum.size());
        boolean voucherFound = false;
        for (int i = 0; i < towardsVoucherNum.size(); i++) {
            WebElement text = towardsVoucherNum.get(i);
//            System.out.println("Towards vouchers getTet :"+text.getText());
            if (text.getText().equals(desiredVoucher)) {
                voucherFound = true;
                text.click();
                text.sendKeys(Keys.TAB, Keys.TAB, Keys.SPACE);
                EnterData("//Edit[@Name='Amount Adjusted * Row " + i + ", Not sorted.']", dataFile, "BillsPayable", "AmountAdjusted");
                common.deleteInvalidRows();
                break;
            } else if (!text.getText().equals(desiredVoucher)) {
                towardsVoucherNum.get(i).click();
                towardsVoucherNum.get(i).sendKeys(Keys.DOWN);
            } else {
                System.out.println("Towards voucher number is not found");
            }
        }
    }

    public void otherInfo() throws InterruptedException, IOException {
        navigateToOtherInfoTab();
        EnterData("//Edit[@Name='Reference Bill No']", dataFile, "OtherInfo", "ReferenceBillNo");
        Thread.sleep(5000);
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']", dataFile, "OtherInfo", "ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']", dataFile, "OtherInfo", "OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']", dataFile, "OtherInfo", "OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']", dataFile, "OtherInfo", "OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']", dataFile, "OtherInfo", "OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']", dataFile, "OtherInfo", "OtherInfo5");
    }
}
