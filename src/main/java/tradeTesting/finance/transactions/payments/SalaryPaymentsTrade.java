package tradeTesting.finance.transactions.payments;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class SalaryPaymentsTrade  extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalaryPaymentsTrade(WindowsDriver driver, String file) {
        super(driver);
        common = new Common( this.driver = driver);
        dataFile = file;
    }

    public String salaryPaymentsTrade(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Finance","Payments", "Salary Payments");
        Thread.sleep(1000);
        long generalInfoStart=System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterBankAccountCode(dataFile,"GeneralInformation","BankAccountCode");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Salary Payments General information End:- ", generalInfoEndTime);

        //F3-Parties
        long addProductStart=System.nanoTime();
        addParties();
        long addProductEnd=System.nanoTime()-addProductStart;
        FileUtil.writeTimeLogInMinutes("Salary Payments Add Products:- ",addProductEnd);
        //other Info
        long otherInfoTabStart =System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  OtherInfo  ')]");
        otherInfo();
        long otherInfoTabEnd=System.nanoTime() - otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Salary Payments Other Info Tab:- ", otherInfoTabEnd);
        //allocations
        long allocationsTabStart=System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  Allocations  ')]");
        addAllocations();
        long allocationsTabEnd=System.nanoTime() - allocationsTabStart;
        FileUtil.writeTimeLogInMinutes("Salary Payments Allocations Tab:- ", allocationsTabEnd);
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //api
        long salesInvoiceEnd = System.nanoTime() - start ;
        FileUtil.writeTimeLogInMinutes("Salary Payments ended at:- ", salesInvoiceEnd );

//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"SalaryPayments");
        deleteTransactionUsingVoucherNumber(newVoucherID);

        return newVoucherID;
    }

    public void addParties() throws IOException {
        List<String> productCode = readExcelData(dataFile, "Parties", "AccountCode");
        System.out.println("productCodes :" + productCode.size());
        for (int i = 0; i < productCode.size(); i++) {
            addData("xpath", "//Edit[@Name='Account Code Row "+i+", Not sorted.']", dataFile, "Parties", "AccountCode", i);
        }
        List<WebElement> chequeNumber = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cheque Date * Row ')]");
        List<WebElement> amount = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Parties']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");

        for (int i = 0; i < productCode.size(); i++) {
            enterListData(chequeNumber.get(i), dataFile, "Parties", "ChequeNo", i);
            enterListDate(chequeDate.get(i), dataFile, "Parties", "ChequeDate", i);
            enterListData(amount.get(i), dataFile, "Parties", "Amount", i);
            enterListData(Department.get(i), dataFile, "Parties", "Department", i);
            enterListData(Project.get(i), dataFile, "Parties", "Project", i);
            enterListData(ProfitCentre.get(i), dataFile, "Parties", "ProfitCentre", i);
            enterListData(CostCentre.get(i), dataFile, "Parties", "CostCentre", i);
            enterListData(Comments.get(i), dataFile, "Parties", "Comments", i);
        }
    }

    public void otherInfo() throws InterruptedException, IOException, AWTException {
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }

    public void addAllocations() {
        EnterData( "//Edit[@Name='Department']", dataFile, "Allocations", "Department");
        EnterData( "//Edit[@Name='Project']", dataFile, "Allocations", "Project");
        EnterData("//Edit[@Name='Profit Centre']", dataFile, "Allocations", "ProfitCentre");
        EnterData( "//Edit[@Name='Cost Centre']", dataFile, "Allocations", "CostCentre");
    }

}