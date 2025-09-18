package com.wings.pages.finance.transactions.Payments;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;

public class BankPayment extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public BankPayment(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String bankPayment(String voucherNumber,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Finance", "Payments", "Bank Payments");
        Thread.sleep(1000);
        long generalInfoStart=System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterBankAccountCode(dataFile,"GeneralInformation","BankAccountCode");
        enterDiscountAccountCode(dataFile,"GeneralInformation","DiscountAccount");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Bank Payment General information End:- ", generalInfoEndTime);

        //F3-Parties
        long addProductStart=System.nanoTime();
        addParties();
        long addProductEnd=System.nanoTime()-addProductStart;
        FileUtil.writeTimeLogInMinutes("Bank Payment Add Parties:- ",addProductEnd);
        //bills Payable
        long billsPayableStart =System.nanoTime();
        adjustAmountInPayablesAndReceivables(dataFile,voucherNumber);
        long billsPayableEnd =System.nanoTime()- billsPayableStart;
        FileUtil.writeTimeLogInMinutes("Bank Payments Bills Payable:- ", billsPayableEnd);
        //other Info
        long otherInfoStart = System.nanoTime();
        otherInfo();
        long otherInfoEnd = System.nanoTime() - otherInfoStart;
        FileUtil.writeTimeLogInMinutes("Bank Payments OtherInfo Tab:- ", otherInfoEnd);
        //allocations
        long allocationsTabStart=System.nanoTime();
        addAllocations();
        long allocationsTabEnd=System.nanoTime() - allocationsTabStart;
        FileUtil.writeTimeLogInMinutes("Bank Payment Allocations Tab:- ", allocationsTabEnd);
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //API
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"bankPayments");
        return newVoucherID;
    }

    public void addParties() throws IOException{
        List<String> productCode = readExcelData(dataFile, "Parties", "AccountCode");
        System.out.println("productCodes :" + productCode.size());
        for (int i = 0; i < productCode.size(); i++) {
            addData("xpath", "//Edit[@Name='Account Code Row "+i+", Not sorted.']", dataFile, "Parties", "AccountCode", i);
        }
        List<WebElement> chequeNumber = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cheque Date * Row ')]");
        List<WebElement> amount = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> tdsTransactionNature = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> chargesAccountCode = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Account Code Row ')]");
        List<WebElement> charges = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Parties']/*/Thumb[@Name='Position']", 500, 0);
        List<WebElement> discountAmount = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Row ')]");
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Parties']/*/Thumb[@Name='Position']", -500, 0);

        for (int i = 0; i < productCode.size(); i++) {
            enterListData(chequeNumber.get(i), dataFile, "Parties", "ChequeNo", i);
            enterListDate(chequeDate.get(i), dataFile, "Parties", "ChequeDate", i);
            enterListData(amount.get(i), dataFile, "Parties", "Amount", i);
            if (i==0 || i==3) {
                common.clickElement("xpath", "//CheckBox[@Name='Deduct TDS Row " + i + "']");
            }
            enterListData(tdsTransactionNature.get(i), dataFile, "Parties", "TDSTransactionNature", i);
            enterListData(chargesAccountCode.get(i), dataFile, "Parties", "ChargesAccountCode", i);
            enterListData(charges.get(i), dataFile, "Parties", "Charges", i);
            enterListData(discountAmount.get(i), dataFile, "Parties", "Discount", i);
            enterListData(Department.get(i), dataFile, "Parties", "Department", i);
            enterListData(Project.get(i), dataFile, "Parties", "Project", i);
            enterListData(ProfitCentre.get(i), dataFile, "Parties", "ProfitCentre", i);
            enterListData(CostCentre.get(i), dataFile, "Parties", "CostCentre", i);
            enterListData(Comments.get(i), dataFile, "Parties", "Comments", i);
            common.sliderHandling("xpath", "//Table[@Name='Parties']/*/Thumb[@Name='Position']", -500, 0);

        }
    }

    public void adjustAmountInPayablesAndReceivables(String dataFile,String desiredVoucher){
        navigateToBillsPayablesTab();
        List<WebElement> towardsVoucherNum= common.findWebElements("xpath","//Table[@Name='BillsPayable']/*[starts-with(@Name,'Row ')]/Edit[starts-with(@Name,'Towards VNo * Row')]");
        System.out.println("Towards vouchers Size :"+towardsVoucherNum.size());

//        List<WebElement> adjustedAmountRows = common.findWebElements("xpath", "//Table[@Name='BillsReceivable']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount Adjusted * Row')]");
//        System.out.println("AdjustedAmount size :"+adjustedAmountRows.size());
        boolean voucherFound=false;
        for (int i=0;i< towardsVoucherNum.size();i++){
            WebElement text=towardsVoucherNum.get(i);
//            System.out.println("Towards vouchers getTet :"+text.getText());
            if (text.getText().equals(desiredVoucher)) {
                voucherFound = true;
                text.click();
                text.sendKeys(Keys.TAB, Keys.TAB, Keys.SPACE);
                EnterData("//Edit[@Name='Amount Adjusted * Row " + i + ", Not sorted.']", dataFile, "BillsPayable", "AmountAdjusted");
                common.sliderHandling("xpath", "//Table[@Name='BillsPayable']//ScrollBar[@Name='Vertical']//Thumb[@Name='Position']", 0, -100);
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
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(5000);
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }

    public void addAllocations() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Allocations')]");
        EnterData( "//Edit[@Name='Department']", dataFile, "Allocations", "Department");
        EnterData( "//Edit[@Name='Project']", dataFile, "Allocations", "Project");
        EnterData("//Edit[@Name='Profit Centre']", dataFile, "Allocations", "ProfitCentre");
        EnterData( "//Edit[@Name='Cost Centre']", dataFile, "Allocations", "CostCentre");
    }
}
