package com.wings.pages.taxes.transactions.TCS;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class TCSPayments extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public TCSPayments(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void tcsPayments(String voucher,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long tcsPaymentsStart=System.nanoTime();
        navigateToMastersWhen3Steps("Taxes","TCS","TCS Payments");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        Thread.sleep(1000);
        long tcsPaymentsGenInfostart=System.nanoTime();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        decimalPrecision("//Edit[@Name='From Year And Month *']",dataFile,"GeneralInformation","FromYearAndMonth");
        decimalPrecision("//Edit[@Name='To Year And Month *']",dataFile,"GeneralInformation","ToYearAndMonth");
        EnterData("//Edit[@Name='TCS Sub Type *']",dataFile,"GeneralInformation","TCSSubType");
        Thread.sleep(1500);
        selectPendingsSalesOrder(voucher,"20250401");
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        EnterData("//Edit[@Name='TCS Account *']",dataFile,"GeneralInformation","TCSAccount");
        EnterData("//Edit[@Name='BSR Code']",dataFile,"GeneralInformation","BSRCode");
        EnterData("//Edit[@Name='Bank Challan No']",dataFile,"GeneralInformation","BankChallanNo");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        long genInfoEnd=System.nanoTime()-tcsPaymentsGenInfostart;
        FileUtil.writeTimeLogInMinutes("TCS Payments Gen info",genInfoEnd);

        long tdsDetailsStart =System.nanoTime();
        addTCSDetails(voucher);
        long tdsDetailsEnd =System.nanoTime()- tdsDetailsStart;
        FileUtil.writeTimeLogInMinutes("TCS Payments - TCS Details:- ", tdsDetailsEnd);

        long chequesStart =System.nanoTime();
        navigateToCheques();
        cheques();
        long chequesEnd =System.nanoTime()- chequesStart;
        FileUtil.writeTimeLogInMinutes("TCS Payments - Cheques:- ", chequesEnd);

        long otherChargesStart =System.nanoTime();
        navigateToOtherChargesTab();
        otherCharges();
        long otherChargesEnd =System.nanoTime()- otherChargesStart;
        FileUtil.writeTimeLogInMinutes("TCS Payments - Other Charges:- ", otherChargesEnd);

        long otherInfoStart =System.nanoTime();
        navigateToOtherInfoTab();
        otherInfo();
        long otherInfoEnd =System.nanoTime()- otherInfoStart;
        FileUtil.writeTimeLogInMinutes("TCS Payments - Other Info:- ", otherInfoEnd);

        common.clickElement("xpath","//TabItem[contains(@Name,'Summary ')]");
        common.findWebElement("xpath","//Edit[@Name='Adjusted Amount']").sendKeys("25.90", Keys.TAB);
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long tdsPaymentsEnd = System.nanoTime() - tcsPaymentsStart ;
        FileUtil.writeTimeLogInMinutes("TCS Payments ended at:- ", tdsPaymentsEnd);
        //API
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"tcsPayments");
        deleteTransactionUsingVoucherNumber(newVoucherID);
        deleteTransactionUsingVoucherNumber(voucher);

    }

    public void addTCSDetails(String voucherNumber) throws IOException {
        List<String> productCode=readExcelData(dataFile,"TCSDetails","Customer");
        List<WebElement> tcsPaidCheckBox =common.findWebElements("xpath","//CheckBox[starts-with(@Name,'TCS Paid Row ')]");
        List<WebElement> tcsSalesReturnsCheckBox =common.findWebElements("xpath","//CheckBox[starts-with(@Name,'TCS Sale Return Row ')]");
        List<WebElement> tcsPaidAmount =common.findWebElements("xpath","//Edit[starts-with(@Name,'TCS Paid Amount Row ')]");
        List<WebElement> tcsSalesReturnAmount =common.findWebElements("xpath","//Edit[starts-with(@Name,'TCS Sale Return Amount Row ')]");
        List<WebElement> bsrCodeRow=common.findWebElements("xpath","//Edit[contains(@Name,'BSR Code Row ')]");
        List<WebElement> dateRow=common.findWebElements("xpath","//Edit[contains(@Name,'Date Row ')]");
        List<WebElement> bankChallanRow=common.findWebElements("xpath","//Edit[contains(@Name,'Bank Challan No Row ')]");
        List<WebElement> tdsVouchers=common.findWebElements("xpath","//Table[@Name='TCSDetails']/*[starts-with(@Name,'Row ')]/*[starts-with(@Name,'Towards VNo * Row ')]");
        System.out.println("no.of vouchers: "+tdsVouchers.size());
        for (int i = 0; i < productCode.size(); i++) {
            if (tdsVouchers.get(i).getText().startsWith(voucherNumber)){
                if (i<3) {
                    clickListData(tcsPaidCheckBox.get(i));
                } else if (i>2 && i<5) {
                    clickListData(tcsSalesReturnsCheckBox.get(i));
                }
            }
            enterListData(tcsPaidAmount.get(i), dataFile, "TCSDetails", "TCSPaidAmount", i);
            enterListData(tcsSalesReturnAmount.get(i), dataFile, "TCSDetails", "TCSSaleReturnAmount", i);
            enterListData(bsrCodeRow.get(i), dataFile, "TCSDetails", "BSRCode", i);
            enterListDate(dateRow.get(i), dataFile, "TCSDetails", "Date", i);
            enterListData(bankChallanRow.get(i), dataFile, "TCSDetails", "BankChallanNo", i);
        }
    }

    public void cheques() throws IOException {
        List<String> bankAccountRow = readExcelData(dataFile, "Cheques", "BankAccountCode");
        System.out.println("productCodes :" + bankAccountRow.size());
        for (int i = 0; i < bankAccountRow.size(); i++) {
            addData("xpath", "//Edit[@Name='Bank Account Code Row "+i+", Not sorted.']", dataFile, "Cheques", "BankAccountCode", i);
        }
        List<WebElement> amountList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNumber = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < bankAccountRow.size(); i++) {
            enterListData(amountList.get(i), dataFile, "Cheques", "Amount", i);
            enterListData(chequeNumber.get(i), dataFile, "Cheques", "ChequeNo", i);
            enterListDate(chequeDate.get(i), dataFile, "Cheques", "ChequeDate", i);
            enterListData(departmentRowList.get(i), dataFile, "Cheques", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "Cheques", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "Cheques", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "Cheques", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "Cheques", "Comments", i);
        }
    }

    public void otherCharges() throws IOException {
        List<String> otherChargesRows = readExcelData(dataFile, "OtherCharges", "AccountCode");
        System.out.println("productCodes :" + otherChargesRows.size());
        for (int i = 0; i < otherChargesRows.size(); i++) {
            addData("xpath", "//Edit[@Name='Account Code Row "+i+", Not sorted.']", dataFile, "OtherCharges", "AccountCode", i);
        }
        List<WebElement> bankAccCode = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Bank Account Code Row ')]");
        List<WebElement> chequeNumber = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> amountRow = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount Row ')]");
        List<WebElement> executiveRow = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < otherChargesRows.size(); i++) {
            enterListData(bankAccCode.get(i), dataFile, "OtherCharges", "BankAccountCode", i);
            enterListData(chequeNumber.get(i), dataFile, "OtherCharges", "ChequeNo", i);
            enterListDate(chequeDate.get(i), dataFile, "OtherCharges", "ChequeDate", i);
            enterListData(amountRow.get(i), dataFile, "OtherCharges", "Amount", i);
            enterListData(executiveRow.get(i), dataFile, "OtherCharges", "Executive", i);
            enterListData(departmentRowList.get(i), dataFile, "OtherCharges", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "OtherCharges", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "OtherCharges", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "OtherCharges", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "OtherCharges", "Comments", i);
        }
    }

    public void otherInfo() throws InterruptedException, IOException {
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(5000);
//        common.clickElement("xpath","//Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='OtherI nfo 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }
}
