package tradeTesting.finance.transactions.partyAdjustments;

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

public class ProductWiseCreditNote extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean discountIsClicked=false;

    public ProductWiseCreditNote(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void productWiseCreditNote(String invoiceNum,String receivableVouchers,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long creditNoteStarts = System.nanoTime();

        navigateToMastersWhen3Steps("Finance","Party Adjustments","Product Wise Credit Note");
        Thread.sleep(1000);
        String oldVoucherID = oldTTransactionID();
        Thread.sleep(1000);
        enterVoucherType(dataFile, "GeneralInformation", "VoucherType");
        EnterDate("//Edit[@Name='Date *']", dataFile, "GeneralInformation", "Date");
        enterBranch(dataFile, "GeneralInformation", "Branch");
        EnterData("//Edit[@Name='Division']",dataFile,"GeneralInformation","Division");
        EnterData("//Edit[@Name='Route']",dataFile,"GeneralInformation","Route");
        enterCurrency(dataFile, "GeneralInformation", "TransactionCurrency");
        EnterData("//Edit[@Name='Exchange Rate *']", dataFile, "GeneralInformation", "ExchangeRate");
        WebElement element=common.findWebElement("xpath","//Edit[@Name='Invoice No *']");
        element.sendKeys(invoiceNum);
//        EnterData("//Edit[@Name='Invoice No *']", dataFile, "GeneralInformation", invoiceNum);
        enterPartyCode(dataFile, "GeneralInformation", "PartyAccountCode");
        enterCreditPeriod(dataFile, "GeneralInformation", "CreditPeriod");
        EnterData("//Edit[@Name='Price List']", dataFile, "GeneralInformation", "PriceList");
        EnterData("//Edit[@Name='Price Type']", dataFile, "GeneralInformation", "PriceType");
        EnterData("//Edit[@Name='Sales Returns Account Code']", dataFile, "GeneralInformation", "SalesReturnsAccountCode");
        common.clickElement("xpath", "//Edit[@Name='Invoice Type']");
        Thread.sleep(1000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
//        common.clickElement("xpath","//CheckBox[@Name='CalculateTCS']");
        EnterData("//Edit[@Name='TCS Transaction Nature']", dataFile, "GeneralInformation", "TCSTransactionNature");
        EnterData("//Edit[@Name='Executive']",dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile, "GeneralInformation", "Remarks");

        //Items
        long itemsStart = System.nanoTime();
        items();
        long itemsEnd = System.nanoTime() - itemsStart;
        FileUtil.writeTimeLogInMinutes("Items tab End:- ", itemsEnd);

        //Accounts
        long accountsStart =System.nanoTime();
        accounts();
        long accountsStartEnd =System.nanoTime()- accountsStart;
        FileUtil.writeTimeLogInMinutes("Accounts tab End:- ", accountsStartEnd);

        //Bills Receivables
        long BillsReceivablesStart = System.nanoTime();
        billsReceivables(receivableVouchers);
        long BillsReceivablesEnd = System.nanoTime() - BillsReceivablesStart;
        FileUtil.writeTimeLogInMinutes("BillsReceivables Tab End:- ", BillsReceivablesEnd);

        //Other info
        long otherInfoTabStart =System.nanoTime();
        otherInfo();
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Other Info Tab:- ", otherInfoTabEnd);

        //InvoiceDetails
        long invoiceDetails = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  InvoiceDetails  ')]");
        InvoiceDetails();
        long invoiceDetailsEnd = System.nanoTime() - invoiceDetails;
        FileUtil.writeTimeLogInMinutes("InvoiceDetails:- ", invoiceDetailsEnd);


        long shippingAddressTabStart = System.nanoTime();
        shippingAddress();
        long shippingAddressTabEnd = System.nanoTime() - shippingAddressTabStart;
        FileUtil.writeTimeLogInMinutes("Deliveries Shipping Address:- ", shippingAddressTabEnd);

        long allocationsTabStart=System.nanoTime();
        addAllocations();
        long allocationsTabEnd=System.nanoTime() - allocationsTabStart;
        FileUtil.writeTimeLogInMinutes("Allocations Tab:- ", allocationsTabEnd);


        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"CreditNote");

        deleteTransactionUsingVoucherNumber(newVoucherID);
        long creditNoteEnd = System.nanoTime()- creditNoteStarts;
        FileUtil.writeTimeLogInMinutes("Credit note End at:- ", creditNoteEnd );
    }

    public void items() throws InterruptedException, IOException, AWTException {
        List<String> productCode = readExcelData(dataFile, "Items", "ProductCode");
        for (int i = 0; i < productCode.size(); i++) {
            addData("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", dataFile, "Items", "ProductCode", i);
        }
        List<WebElement> salesReturnAcc = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Sales Returns Account * Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Storage Bin * Row ')]");
        List<WebElement> priceType = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Price Type Row ')]");
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name, 'Qty Row ')]");
        List<WebElement> unitRow = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name, 'Unit Rate Row ')]");
        List<WebElement> quantityBaseUnit = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name, 'Qty In Base Unit Row ')]");
        List<WebElement> baseUnitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name, 'Base Unit Rate Row ')]");
        List<WebElement> freQty = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name, 'Free Qty Row ')]");
        List<WebElement> freQtyInBaseUnit = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name, 'Free Qty In Base Unit Row ')]");
//        List<WebElement> mrp = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name, 'MRP Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 450, 0);
        if (!discountIsClicked) {
            common.clickElement("xpath", "//Header[@Name='Disc']");
            common.clickElement("xpath", "//Header[@Name='Discount Amount1']");
            common.clickElement("xpath", "//Header[@Name='Discount Amount2']");
            common.clickElement("xpath", "//Header[@Name='Discount Amount3']");
            discountIsClicked = true;
        }
        List<WebElement> discPercent = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc % Row ')]");
//        List<WebElement> disc = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Row ')]");
        List<WebElement> discountAcc1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Account1 Row ')]");
        List<WebElement> discountBasis1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Basis1 Row ')]");
        List<WebElement> discount1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount1 Row ')]");
        List<WebElement> discountAcc2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Account2 Row ')]");
        List<WebElement> discountBasis2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Basis2 Row ')]");
        List<WebElement> discount2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount2 Row ')]");
        List<WebElement> discountAcc3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Account3 Row ')]");
        List<WebElement> discountBasis3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Basis3 Row ')]");
        List<WebElement> discount3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> gstProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
//        List<WebElement> reason = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Reason Row ')]");
        List<WebElement> executives = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> division = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1100, 0);

        for (int i = 0; i < priceType.size()-1; i++) {
            enterListData(salesReturnAcc.get(i), dataFile, "Items", "SalesReturnsAccount", i);
            enterListData(storageBin.get(i), dataFile, "Items", "StorageBin", i);
            enterListData(priceType.get(i), dataFile, "Items", "PriceType", i);
            enterListData(quantity.get(i), dataFile, "Items", "Qty", i);
            enterListData(unitRow.get(i), dataFile, "Items", "UnitRate", i);
            enterListData(quantityBaseUnit.get(i), dataFile, "Items", "QtyInBaseUnit", i);
            enterListData(baseUnitRate.get(i), dataFile, "Items", "UnitRateInBaseUnit", i);
            enterListData(freQty.get(i), dataFile, "Items", "FreeQty", i);
            enterListData(freQtyInBaseUnit.get(i), dataFile, "Items", "FreeQtyBaseUnit", i);
//            enterListData(mrp.get(i), dataFile, "Items", "MRP", i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 450, 0);
            enterListData(discPercent.get(i), dataFile, "Items", "DiscountPercentage", i);
//            enterListData(disc.get(i), dataFile, "Items", "", i);
            enterListData(discountAcc1.get(i), dataFile, "Items", "DiscountBasis1", i);
            enterListData(discountBasis1.get(i), dataFile, "Items", "DiscountBasis1", i);
            enterListData(discount1.get(i), dataFile, "Items", "Discount1", i);
            enterListData(discountAcc2.get(i), dataFile, "Items", "DiscountAccount2", i);
            enterListData(discountBasis2.get(i), dataFile, "Items", "DiscountBasis2", i);
            enterListData(discount2.get(i), dataFile, "Items", "Discount2", i);
            enterListData(discountAcc3.get(i), dataFile, "Items", "DiscountAccount3", i);
            enterListData(discountBasis3.get(i), dataFile, "Items", "DiscountBasis3", i);
            enterListData(discount3.get(i), dataFile, "Items", "Discount3", i);
            Thread.sleep(1000);
            enterListData(hsnCodeRowList.get(i), dataFile, "Items", "HSN", i);
            enterListData(gstProductCategoryRowList.get(i), dataFile, "Items", "GSTProductCategory", i);
            enterListData(cessProductCategoryRowList.get(i), dataFile, "Items", "CESSProductCategory", i);
//            enterListData(reason.get(i), dataFile, "Items", "Reason", i);
            enterListData(executives.get(i), dataFile, "Items", "Executive", i);
            enterListData(division.get(i), dataFile, "Items", "Division", i);
            enterListData(departmentRowList.get(i), dataFile, "Items", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "Items", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "Items", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "Items", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "Items", "Comments", i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1100, 0);
        }
    }

    public void accounts() throws IOException, InterruptedException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'  Accounts  ')]");
        java.util.List<String> cashTab = readExcelData(dataFile, "Accounts", "AccountCode");
        for (int i = 0; i < cashTab.size(); i++) {
            addData("xpath", "//Edit[@Name='Account Code Row "+i+", Not sorted.']", dataFile, "Accounts", "AccountCode", i);
        }
        List<WebElement> uom = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> gstProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Accounts']/*/Thumb[@Name='Position']", 800, 0);
//        common.clickElement("xpath", "//Header[@Name='GST Amount']");
        List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> division = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Accounts']/*/Thumb[@Name='Position']", -1000, 0);
        for (int i = 0; i < cashTab.size(); i++) {
            enterListData(uom.get(i), dataFile, "Accounts", "UOM", i);
            enterListData(amountRowList.get(i), dataFile, "Accounts", "InclusiveAmount", i);
            enterListData(hsnCodeRowList.get(i), dataFile, "Accounts", "HSN", i);
            enterListData(gstProductCategoryRowList.get(i), dataFile, "Accounts", "GSTProductCategory", i);
            enterListData(cessProductCategoryRowList.get(i), dataFile, "Accounts", "CESSProductCategory", i);
            enterListData(tdsTransNatureRowList.get(i), dataFile, "Accounts", "TDSTransactionNature",i);
            enterListData(executive.get(i), dataFile, "Accounts", "Executive", i);
            enterListData(division.get(i), dataFile, "Accounts", "Division", i);
            enterListData(departmentRowList.get(i), dataFile, "Accounts", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "Accounts", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "Accounts", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "Accounts", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "Accounts", "Comments", i);
            common.sliderHandling("xpath", "//Table[@Name='Accounts']/*/Thumb[@Name='Position']", -1000, 0);
        }

    }

    public void billsReceivables(String receivableVouchers){
        common.clickElement("xpath", "//TabItem[contains(@Name,'  BillsReceivable  ')]");
//        adjustAmountInBillsReceivables(dataFile,receivableVouchers);
        common.deleteInvalidRows();
    }

    public void InvoiceDetails() throws IOException {
        EnterData("//Edit[@Name='Invoice Value']",dataFile,"InvoiceDetails","InvoiceValue");
        EnterDate("//Edit[@Name='Invoice Date']",dataFile,"InvoiceDetails","InvoiceDate");
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

    public void shippingAddress(){
        common.clickElement("xpath","//TabItem[contains(@Name,'  ShippingAddress  ')]");
        EnterData("//Edit[@Name='Party Account']",dataFile,"ShippingAddress","PartyAccount");
        EnterData("//Edit[@Name='GSTIN']",dataFile,"ShippingAddress","GSTIN");
        EnterData("//Edit[@Name='Address 1']",dataFile,"ShippingAddress","Address1");
        EnterData("//Edit[@Name='Address 2']",dataFile,"ShippingAddress","Address2");
        EnterData("//Edit[@Name='Address 3']",dataFile,"ShippingAddress","Address3");
        EnterData("//Edit[@Name='City']",dataFile,"ShippingAddress","City");
        EnterData("//Edit[@Name='State']",dataFile,"ShippingAddress","State");
        EnterData("//Edit[@Name='State Code']",dataFile,"ShippingAddress","StateCode");
        EnterData("//Edit[@Name='Country']",dataFile,"ShippingAddress","Country");
        EnterData("//Edit[@Name='Zip']",dataFile,"ShippingAddress","Zip");
        EnterData("//Edit[@Name='Telephone No']",dataFile,"ShippingAddress","TelephoneNo");
        EnterData("//Edit[@Name='Mobile No']",dataFile,"ShippingAddress","MobileNo");
    }

    public void addAllocations() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'  Allocations  ')]");
        EnterData( "//Edit[@Name='Division *']", dataFile, "Allocations", "Division");
        EnterData( "//Edit[@Name='Department']", dataFile, "Allocations", "Department");
        EnterData( "//Edit[@Name='Project']", dataFile, "Allocations", "Project");
        EnterData("//Edit[@Name='Profit Centre']", dataFile, "Allocations", "ProfitCentre");
        EnterData( "//Edit[@Name='Cost Centre']", dataFile, "Allocations", "CostCentre");
    }
}
