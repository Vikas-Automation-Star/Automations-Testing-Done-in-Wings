package com.wings.pages.taxes.transactions.TDS;

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

public class TDSPayments extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public TDSPayments(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void tdsPayments(String voucher,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long tdsPaymemtsStart=System.nanoTime();
        navigateToMastersWhen3Steps("Taxes","TDS","TDS Payments");
        Thread.sleep(3000);
        String oldVoucherID =oldTTransactionID();
        Thread.sleep(1000);
        long tdsPaymentsGenInfostart=System.nanoTime();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        decimalPrecision("//Edit[@Name='From Year And Month *']",dataFile,"GeneralInformation","FromYearAndMonth");
        decimalPrecision("//Edit[@Name='To Year And Month *']",dataFile,"GeneralInformation","ToYearAndMonth");
        EnterData("//Edit[@Name='TDS Sub Type *']",dataFile,"GeneralInformation","TDSSubType");
        //reference bil no code
        referenceBillNumberTDSPayments(voucher);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        EnterData("//Edit[@Name='TDS Account *']",dataFile,"GeneralInformation","TDSAccount");
        EnterData("//Edit[@Name='BSR Code']",dataFile,"GeneralInformation","BSRCode");
        EnterData("//Edit[@Name='Bank Challan No']",dataFile,"GeneralInformation","BankChallanNo");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        long genInfoEnd=System.nanoTime()-tdsPaymentsGenInfostart;
        FileUtil.writeTimeLogInMinutes("TDS Payments Gen info",genInfoEnd);

        long tdsDetailsStart =System.nanoTime();
        addTDSDetails(voucher);
        long tdsDetailsEnd =System.nanoTime()- tdsDetailsStart;
        FileUtil.writeTimeLogInMinutes("TDS Payments - TDS Details:- ", tdsDetailsEnd);

        long chequesStart =System.nanoTime();
        navigateToCheques();
        cheques();
        long chequesEnd =System.nanoTime()- chequesStart;
        FileUtil.writeTimeLogInMinutes("TDS Payments - Cheques:- ", chequesEnd);

        long otherChargesStart =System.nanoTime();
        navigateToOtherChargesTab();
        otherCharges();
        long otherChargesEnd =System.nanoTime()- otherChargesStart;
        FileUtil.writeTimeLogInMinutes("TDS Payments - Other Charges:- ", otherChargesEnd);

        long otherInfoStart =System.nanoTime();
        navigateToOtherInfoTab();
        otherInfo();
        long otherInfoEnd =System.nanoTime()- otherInfoStart;
        FileUtil.writeTimeLogInMinutes("TDS Payments - Other Info:- ", otherInfoEnd);

        common.clickElement("xpath","//TabItem[contains(@Name,'Summary ')]");
        common.findWebElement("xpath","//Edit[@Name='Adjusted Amount']").sendKeys("25.90", Keys.TAB);
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long tdsPaymentsEnd = System.nanoTime() - tdsPaymemtsStart ;
        FileUtil.writeTimeLogInMinutes("TDS Payments ended at:- ", tdsPaymentsEnd);
        //API
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"tdsPayments");
        deleteTransactionUsingVoucherNumber(newVoucherID);
        deleteTransactionUsingVoucherNumber(voucher);

    }

    public void addTDSDetails(String voucherNumber) throws IOException {
        List<String> productCode=readExcelData(dataFile,"TDSDetails","PartyAccount");
        List<WebElement> tdsPaidCheckBox=common.findWebElements("xpath","//CheckBox[starts-with(@Name,'TDS Paid * Row ')]");
        List<WebElement> bsrCodeRow=common.findWebElements("xpath","//Edit[contains(@Name,'BSR Code Row ')]");
        List<WebElement> dateRow=common.findWebElements("xpath","//Edit[contains(@Name,'Date Row ')]");
        List<WebElement> bankChallanRow=common.findWebElements("xpath","//Edit[contains(@Name,'Bank Challan No Row ')]");
        List<WebElement> tdsVouchers=common.findWebElements("xpath","//Table[@Name='TDSDetails']/*[starts-with(@Name,'Row ')]/*[starts-with(@Name,'Towards VNo * Row ')]");
        System.out.println("no.of vouchers: "+tdsVouchers.size());
        for (int i = 0; i < productCode.size(); i++) {
            if (tdsVouchers.get(i).getText().startsWith(voucherNumber)){
                clickListData(tdsPaidCheckBox.get(i));
                enterListData(bsrCodeRow.get(i), dataFile, "TDSDetails", "BSRCode", i);
                enterListDate(dateRow.get(i), dataFile, "TDSDetails", "Date", i);
                enterListData(bankChallanRow.get(i), dataFile, "TDSDetails", "BankChallanNo", i);
            }
        }
        common.deleteInvalidRows();
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
        List<WebElement> chequeNumber = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque No * Row ')]");
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
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }

}
