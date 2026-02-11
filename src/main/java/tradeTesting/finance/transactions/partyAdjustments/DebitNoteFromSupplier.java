package tradeTesting.finance.transactions.partyAdjustments;

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

public class DebitNoteFromSupplier extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public DebitNoteFromSupplier(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void debitNoteFromSupplier(String purchaseVoucher,String payableVoucher,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long creditNoteFromSupplierStarts = System.nanoTime();

        navigateToMastersWhen3Steps("Finance","Party Adjustments","Debit Note from Suppliers");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        Thread.sleep(1000);
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        common.findWebElement("xpath","//Edit[@Name='Purchase VNo *']").sendKeys(purchaseVoucher);
//        enterPurchaseVoucherNum(dataFile,"GeneralInformation","PurchaseVoucherNo");
        EnterDate("//Edit[@Name='Voucher Date *']",dataFile,"GeneralInformation","VoucherDate");
        enterPartyCode(dataFile,"GeneralInformation","PartyAccountCode");
//        common.clickElement("xpath","//CheckBox[@Name='Apply TCS']");
//        enterTcsTransNature(dataFile, "GeneralInformation", "TCSTransactionNature");
        enterSupplierBillNumber(dataFile, "GeneralInformation", "SupplierBillNo");
        common.clickElement("xpath","//Button[@Name='OK']");
//        common.findWebElement("xpath","//Edit[@Name='Supplier Bill No *']").sendKeys("billNUm"+common.getRandom());
//        EnterDate ("//Edit[@Name='Shipping Bill Number']",dataFile,"GeneralInformation","ShippingBillDate");
        enterSuppliersBillDate(dataFile, "GeneralInformation", "SupplierBillDate");
        enterCreditPeriod(dataFile,"GeneralInformation","CreditPeriod");
        common.clickElement ("xpath","//Edit[@Name='Reason For Issuing Document']");
        WebElement dropdown= common.findWebElement ("xpath","//Edit[@Name='Reason For Issuing Document']");
        dropdown.sendKeys("01-Sales Return");
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        //Items
        long itemsStart =System.nanoTime();
        items();
        long itemsEnd =System.nanoTime()- itemsStart;
        FileUtil.writeTimeLogInMinutes("items tab End:- ", itemsEnd);


        //Accounts
        long accountsStart =System.nanoTime();
        accounts();
        long accountsStartEnd =System.nanoTime()- accountsStart;
        FileUtil.writeTimeLogInMinutes("Accounts tab End:- ", accountsStartEnd);

        //BillsPayable
        long BillsPayableStart =System.nanoTime();
        billsReceivable(payableVoucher);
        long BillsPayableEnd =System.nanoTime()- BillsPayableStart;
        FileUtil.writeTimeLogInMinutes("BillsPayable Tab End:- ", BillsPayableEnd);

        //Other info
        long otherInfoTabStart =System.nanoTime();
        otherInfo();
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Other Info Tab:- ", otherInfoTabEnd);


        long allocationsTabStart=System.nanoTime();
        addAllocations();
        long allocationsTabEnd=System.nanoTime() - allocationsTabStart;
        FileUtil.writeTimeLogInMinutes("Allocations Tab:- ", allocationsTabEnd);

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"CreditNoteFromSuppliers");

        deleteTransactionUsingVoucherNumber(newVoucherID);
        long creditNoteFromSuppliersEnd = System.nanoTime()- creditNoteFromSupplierStarts;
        FileUtil.writeTimeLogInMinutes("Credit note from suppliers End at:- ", creditNoteFromSuppliersEnd );
    }
    public void items() throws IOException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'  Items  ')]");
        java.util.List<String> cashTab = readExcelData(dataFile, "Items", "ProductCode");
        for (int i = 0; i < cashTab.size(); i++) {
            addData("xpath", "//Edit[@Name='Product Code Row "+i+", Not sorted.']", dataFile, "Items", "ProductCode", i);
        }
        List<WebElement> gstProductCategoryRow  = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'GST Product Category Row ')]");
        List<WebElement> hsnRow  = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'HSN Row ')]");
        List<WebElement>  qty = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Quantity Row ')]");
        List<WebElement> creditForUnitRow  = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Debit For Unit Row ')]");
        for (int i = 0; i < cashTab.size(); i++) {
            enterListData(gstProductCategoryRow.get(i), dataFile, "Items", "GSTProductCategory", i);
            enterListData(hsnRow.get(i), dataFile, "Items", "HSN", i);
            enterListData(qty.get(i), dataFile, "Items", "Quantity", i);
            enterListData(creditForUnitRow.get(i), dataFile, "Items", "DebitForUnit", i);
        }
    }



    public void accounts() throws IOException, InterruptedException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'  Accounts  ')]");
        java.util.List<String> cashTab = readExcelData(dataFile, "Accounts", "AccountCode");
        for (int i = 0; i < cashTab.size(); i++) {
            addData("xpath", "//Edit[@Name='Account Code Row "+i+", Not sorted.']", dataFile, "Accounts", "AccountCode", i);
        }
        java.util.List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        java.util.List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        java.util.List<WebElement> gstProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        java.util.List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Accounts']/*/Thumb[@Name='Position']", 800, 0);
//        common.clickElement("xpath", "//Header[@Name='GST Amount']");
        java.util.List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
//        java.util.List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
//        java.util.List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        java.util.List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        java.util.List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        java.util.List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        java.util.List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Accounts']/*/Thumb[@Name='Position']", -1000, 0);
        for (int i = 0; i < cashTab.size(); i++) {
            enterListData(amountRowList.get(i), dataFile, "Accounts", "InclusiveAmount", i);
            enterListData(hsnCodeRowList.get(i), dataFile, "Accounts", "HSN", i);
            enterListData(gstProductCategoryRowList.get(i), dataFile, "Accounts", "GSTProductCategory", i);
            enterListData(cessProductCategoryRowList.get(i), dataFile, "Accounts", "CESSProductCategory", i);
            common.sliderHandling("xpath", "//Table[@Name='Accounts']/*/Thumb[@Name='Position']", 150, 0);
            enterListData(tdsTransNatureRowList.get(i), dataFile, "Accounts", "TDSTransactionNature",i);
//            enterListData(tdsAccountRowList.get(i), dataFile, "Accounts", "TDSAccount",i);
//            enterListData(tdsAmountRowList.get(i), dataFile, "Accounts", "TDSAmount",i);
            Thread.sleep(1000);
            enterListData(departmentRowList.get(i), dataFile, "Accounts", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "Accounts", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "Accounts", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "Accounts", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "Accounts", "Comments", i);
            common.sliderHandling("xpath", "//Table[@Name='Accounts']/*/Thumb[@Name='Position']", -1000, 0);
        }

    }

    public void billsReceivable(String payableVoucher) throws IOException, InterruptedException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'  BillsReceivable  ')]");
        adjustAmountInBillsPayable(dataFile,payableVoucher);
    }

    public void otherInfo() throws InterruptedException, IOException {
        Thread.sleep(510);
        common.clickElement("xpath", "//TabItem[contains(@Name,'  OtherInfo  ')]");
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
//        Thread.sleep(3500);
//        common.clickElement("xpath","//Window/Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info5']",dataFile,"OtherInfo","OtherInfo5");
    }

    public void addAllocations() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'  Allocations  ')]");
        EnterData( "//Edit[@Name='Department']", dataFile, "Allocations", "Department");
        EnterData( "//Edit[@Name='Project']", dataFile, "Allocations", "Project");
        EnterData("//Edit[@Name='Profit Centre']", dataFile, "Allocations", "ProfitCentre");
        EnterData( "//Edit[@Name='Cost Centre']", dataFile, "Allocations", "CostCentre");
    }

}
