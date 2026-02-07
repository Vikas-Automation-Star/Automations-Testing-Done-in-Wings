package tradeTesting.sales.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class SalesReturnWithInvoiceReferenceTrade  extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean returnFreeQuantityClicked = false, IsAmountHeaderClicked = false,isReturnQuantityClicked=false;

    public SalesReturnWithInvoiceReferenceTrade(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String salesReturnWinInvoiceRefTrade(String voucherNum) throws InterruptedException, IOException, ParseException, AWTException {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Sales", "Returns", "Sales Return With Invoice Reference");
        Thread.sleep(1000);
        long genInfoStart = System.nanoTime();
        String oldVoucherID = oldTTransactionID();
        enterVoucherType(dataFile, "GeneralInformation", "VoucherType");
        EnterDate("//Edit[@Name='Date *']", dataFile, "GeneralInformation", "Date");
        enterBranch(dataFile, "GeneralInformation", "Branch");
        EnterData("//Edit[@Name='Location *']", dataFile, "GeneralInformation", "Location");
        WebElement invoiceNum=common.findWebElement("xpath","//Edit[@Name='Sales Invoice No *']");
        invoiceNum.sendKeys(voucherNum);
        Thread.sleep(1500);
        EnterData("//Edit[@Name='Division']", dataFile, "GeneralInformation", "Division");
        enterCreditPeriod(dataFile, "GeneralInformation", "CreditPeriod");
        EnterData("//Edit[@Name='Price Type']", dataFile, "GeneralInformation", "PriceType");
//        EnterData("//Edit[@Name='Cash Discount Basis']", dataFile, "GeneralInformation", "CashDiscountBasis");
//        EnterData("//Edit[@Name='Cash Discount']", dataFile, "GeneralInformation", "CashDiscount");
        EnterData("//Edit[@Name='Sales Returns Acc *']", dataFile, "GeneralInformation", "SalesReturnsAccount");
        enterRemarks(dataFile, "GeneralInformation", "Remarks");
        long genInfoEnd=System.nanoTime() - genInfoStart;
        FileUtil.writeTimeLogInMinutes(" SRWIRF Gen Info Ended at:- ", genInfoEnd);
        //items
        long addProductStart = System.nanoTime();
        addProduct();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes(" SRWIRF Add Products:- ", addProductEnd);

        long chargesDeductionsStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  ChargesAndDeductions  ')]");
        addChargesAndDeductions();
        long chargesDeductionsEnd = System.nanoTime() - chargesDeductionsStart;
        FileUtil.writeTimeLogInMinutes(" SRWIRF Charges And Deductions:- ", chargesDeductionsEnd);

        long otherChargesStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  TaxableOtherCharges  ')]");
        addTaxableOtherCharges();
        long otherChargesEnd = System.nanoTime() - otherChargesStart;
        FileUtil.writeTimeLogInMinutes(" SRWIRF Taxable Other Charges:- ", otherChargesEnd);

        long shippingAddressTabStart = System.nanoTime();
        common.clickElement("xpath","//TabItem[contains(@Name,'  ShippingAddress  ')]");
        shippingAddress();
        long shippingAddressTabEnd = System.nanoTime() - shippingAddressTabStart;
        FileUtil.writeTimeLogInMinutes(" SRWIRF Shipping Address:- ", shippingAddressTabEnd);

        long otherInfoTabStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  OtherInfo  ')]");
        otherInfo();
        long otherInfoTabEnd = System.nanoTime() - otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes(" SRWIRF Other Info:- ", otherInfoTabEnd);

        long termsConditionsTabStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  TermsAndConditions  ')]");
        termsAndCondition();
        long termsConditionsTabEnd = System.nanoTime() - termsConditionsTabStart;
        FileUtil.writeTimeLogInMinutes(" SRWIRF Terms And Conditions:- ", termsConditionsTabEnd);

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long salesOrderEnd = System.nanoTime() - start;
        FileUtil.writeTimeLogInMinutes(" SRWIRF ended at:- ", salesOrderEnd);
        //API
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"purchaseOrders");
        deleteTransactionUsingVoucherNumber(newVoucherID);


        return newVoucherID;
    }

    public void addProduct() throws IOException, ParseException, InterruptedException {
        List<String> productCode = readExcelData(dataFile, "Items", "Product");
        System.out.println("productCodes :" + productCode.size());
//        for (int i = 0; i < productCode.size(); i++) {
//            addData("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", dataFile, "Items", "ProductCode", i);
//        }
        List<WebElement> salesReturnAccount = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Sales Returns Acc * Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin Row ')]");
        List<WebElement> priceType = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Price Type Row ')]");
        if (!isReturnQuantityClicked) {
            common.clickElement("xpath", "//Header[@Name='Return Qty']");
            common.clickElement("xpath", "//Header[@Name='Return Qty In Base Unit']");
            isReturnQuantityClicked = true;
        }
        List<WebElement> salesUnitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Sale Unit Rate Row ')]");
        List<WebElement> returnQuantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Return Qty Row ')]");
        List<WebElement> invoiceBaseUnit = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Invoice Base Unit Rate Row ')]");
        List<WebElement> returnQtyInBaseUnit = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Return Qty In Base Unit Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        if (!returnFreeQuantityClicked) {
            common.clickElement("xpath", "//Header[@Name='Return Free Qty In Base Unit']");
            returnFreeQuantityClicked = true;
        }
        List<WebElement> freeQuantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Return Free Qty Row ')]");
        List<WebElement> freeQuantityInBaseUnit = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Return Free Qty In Base Unit Row ')]");
        List<WebElement> discountAccount1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Account1 Row ')]");
        List<WebElement> discountBasis1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Basis1 Row ')]");
        List<WebElement> discount1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount1 Row ')]");
        List<WebElement> discountAccount2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Account2 Row ')]");
        List<WebElement> discountBasis2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Basis2 Row ')]");
        List<WebElement> discount2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount2 Row ')]");
        List<WebElement> discountAccount3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Account3 Row ')]");
        List<WebElement> discountBasis3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Basis3 Row ')]");
        List<WebElement> discount3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        List<WebElement> inclusiveTax = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Inclusive Tax Row ')]");
        List<WebElement> HSNCode = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'HSN Row ')]");
        List<WebElement> GSTProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'GST Product Category Row ')]");
        List<WebElement> CESSProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'CESS Product Category Row ')]");
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        common.clickElement("xpath", "//Header[@Name='Info5']");
        List<WebElement> Info1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info1 Row ')]");
        List<WebElement> Info2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info2 Row ')]");
        List<WebElement> Info3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info3 Row ')]");
        List<WebElement> Info4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info4 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
        List<WebElement> Info5 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info5 Row ')]");
        common.clickElement("xpath", "//Header[@Name='Value5']");
        List<WebElement> Value1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value1 Row ')]");
        List<WebElement> Value2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value2 Row ')]");
        List<WebElement> Value3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
        List<WebElement> Value4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value4 Row ')]");
        List<WebElement> Value5 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value5 Row ')]");
        common.clickElement("xpath", "//Header[@Name='Date3']");
        List<WebElement> Date1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date1 Row ')]");
        List<WebElement> Date2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date2 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
        List<WebElement> Date3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date3 Row ')]");
        common.clickElement("xpath", "//Header[@Name='Bool3']");
        List<WebElement> Bool1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool1 Row ')]");
        List<WebElement> Bool2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool2 Row ')]");
        List<WebElement> Bool3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -2500, 0);
        for (int i = 0; i < productCode.size(); i++) {
            enterListDate(salesReturnAccount.get(i), dataFile, "Items", "SalesReturnsAccount", i);
            enterListDate(storageBin.get(i), dataFile, "Items", "StorageBin", i);
            enterListDate(priceType.get(i), dataFile, "Items", "PriceType", i);
            enterListDate(salesUnitRate.get(i), dataFile, "Items", "SaleUnitRate", i);
            enterListData(returnQuantity.get(i), dataFile, "Items", "ReturnQty", i);
            enterListData(invoiceBaseUnit.get(i), dataFile, "Items", "InvoiceBaseUnitRate", i);
            enterListData(returnQtyInBaseUnit.get(i), dataFile, "Items", "ReturnQtyInBaseUnit", i);
            enterListData(freeQuantity.get(i), dataFile, "Items", "ReturnFreeQty", i);
            enterListData(freeQuantityInBaseUnit.get(i), dataFile, "Items", "ReturnFreeQtyInBaseUnit", i);
            enterListData(discountAccount1.get(i), dataFile, "Items", "DiscountAccount1", i);
            enterListData(discountBasis1.get(i), dataFile, "Items", "DiscountBasis1", i);
            enterListData(discount1.get(i), dataFile, "Items", "Discount1", i);
            enterListData(discountAccount2.get(i), dataFile, "Items", "DiscountAccount2", i);
            enterListData(discountBasis2.get(i), dataFile, "Items", "DiscountBasis2", i);
            enterListData(discount2.get(i), dataFile, "Items", "Discount2", i);
            enterListData(discountAccount3.get(i), dataFile, "Items", "DiscountAccount3", i);
            enterListData(discountBasis3.get(i), dataFile, "Items", "DiscountBasis3", i);
            enterListData(discount3.get(i), dataFile, "Items", "Discount3", i);
//            clickListData(inclusiveTax.get(i));
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -50, 0);
            enterListData(HSNCode.get(i), dataFile, "Items", "HSN", i);
            enterListData(GSTProductCategory.get(i), dataFile, "Items", "GSTProductCategory", i);
            enterListData(CESSProductCategory.get(i), dataFile, "Items", "CESSProductCategory", i);
            enterListData(Department.get(i), dataFile, "Items", "Department", i);
            enterListData(Project.get(i), dataFile, "Items", "Project", i);
            enterListData(ProfitCentre.get(i), dataFile, "Items", "ProfitCentre", i);
            enterListData(CostCentre.get(i), dataFile, "Items", "CostCentre", i);
            enterListData(Comments.get(i), dataFile, "Items", "Comments", i);
            enterListData(Info1.get(i), dataFile, "Items", "Info1", i);
            enterListData(Info2.get(i), dataFile, "Items", "Info2", i);
            enterListData(Info3.get(i), dataFile, "Items", "Info3", i);
            enterListData(Info4.get(i), dataFile, "Items", "Info4", i);
            enterListData(Info5.get(i), dataFile, "Items", "Info5", i);
            enterListData(Value1.get(i), dataFile, "Items", "Value1", i);
            enterListData(Value2.get(i), dataFile, "Items", "Value2", i);
            enterListData(Value3.get(i), dataFile, "Items", "Value3", i);
            enterListData(Value4.get(i), dataFile, "Items", "Value4", i);
            enterListData(Value5.get(i), dataFile, "Items", "Value5", i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 150, 0);
            enterListDate(Date1.get(i), dataFile, "Items", "Date1", i);
            enterListDate(Date2.get(i), dataFile, "Items", "Date2", i);
            enterListDate(Date3.get(i), dataFile, "Items", "Date3", i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 50, 0);
            clickListData(Bool1.get(i));
            clickListData(Bool2.get(i));
            clickListData(Bool3.get(i));
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1500, 0);
        }
    }

    public void addChargesAndDeductions() throws IOException {
        List<String> chargesAndDeductions = readExcelData(dataFile, "ChargesAndDeductions", "ChargesOrDeductions");
        for (int i = 0; i < chargesAndDeductions.size(); i++) {
            addData("xpath", "//Edit[@Name='Charges Or Deductions * Row " + i + ", Not sorted.']", dataFile, "ChargesAndDeductions", "ChargesOrDeductions", i);
        }
        List<WebElement> accCodeRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Account Code Row ')]");
        if (!IsAmountHeaderClicked) {
            common.clickElement("xpath", "//Header[@Name='Amount *']");
            IsAmountHeaderClicked = true;
        }
        List<WebElement> basisRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Basis Row ')]");
        List<WebElement> percentageRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Percentage Row ')]");
        List<WebElement> amount = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
//        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> division = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < chargesAndDeductions.size(); i++) {
            enterListData(accCodeRowList.get(i), dataFile, "ChargesAndDeductions", "AccountCode", i);
            enterListData(basisRowList.get(i), dataFile, "ChargesAndDeductions", "Basis", i);
            enterListData(percentageRowList.get(i), dataFile, "ChargesAndDeductions", "Percentage", i);
            enterListData(amount.get(i), dataFile, "ChargesAndDeductions", "Amount", i);
//            enterListData(executive.get(i), dataFile, "ChargesAndDeductions", "Executive", i);
            enterListData(division.get(i), dataFile, "ChargesAndDeductions", "Division", i);
            enterListData(departmentRowList.get(i), dataFile, "ChargesAndDeductions", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "ChargesAndDeductions", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "ChargesAndDeductions", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "ChargesAndDeductions", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "ChargesAndDeductions", "Comments", i);
        }
    }

    public void addTaxableOtherCharges() throws IOException, InterruptedException {
        List<String> otherCharges = readExcelData(dataFile, "TaxableOtherCharges", "AccountCode");
        for (int i = 0; i < otherCharges.size(); i++) {
            addData("xpath", "//Edit[@Name='Account Code Row " + i + ", Not sorted.']", dataFile, "TaxableOtherCharges", "AccountCode", i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='TaxableOtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='TaxableOtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> GSTProductCategory = common.findWebElements("xpath", "//Table[@Name='TaxableOtherCharges']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'GST Product Category Row ')]");
        List<WebElement> CESSProductCategory = common.findWebElements("xpath", "//Table[@Name='TaxableOtherCharges']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'CESS Product Category Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='TaxableOtherCharges']/*/Thumb[@Name='Position']", 300, 0);
//        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='TaxableOtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> division = common.findWebElements("xpath", "//Table[@Name='TaxableOtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='TaxableOtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='TaxableOtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='TaxableOtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='TaxableOtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='TaxableOtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='TaxableOtherCharges']/*/Thumb[@Name='Position']", -300, 0);
        for (int i = 0; i < otherCharges.size(); i++) {
            enterListData(amountRowList.get(i), dataFile, "TaxableOtherCharges", "InclusiveAmount", i);
            enterListData(hsnCodeRowList.get(i), dataFile, "TaxableOtherCharges", "HSN", i);
            enterListData(GSTProductCategory.get(i), dataFile, "TaxableOtherCharges", "GSTProductCategory", i);
            enterListData(CESSProductCategory.get(i), dataFile, "TaxableOtherCharges", "CESSProductCategory", i);
            Thread.sleep(1000);
            common.sliderHandling("xpath", "//Table[@Name='TaxableOtherCharges']/*/Thumb[@Name='Position']", 400, 0);
//            enterListData(executive.get(i), dataFile, "TaxableOtherCharges", "Executive", i);
            enterListData(division.get(i), dataFile, "TaxableOtherCharges", "Division", i);
            enterListData(departmentRowList.get(i), dataFile, "TaxableOtherCharges", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "TaxableOtherCharges", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "TaxableOtherCharges", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "TaxableOtherCharges", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "TaxableOtherCharges", "Comments", i);
            common.sliderHandling("xpath", "//Table[@Name='TaxableOtherCharges']/*/Thumb[@Name='Position']", -550, 0);
        }
    }

    public void otherInfo() throws InterruptedException, IOException, AWTException {
        EnterData("//Edit[@Name='Reference Bill No']", dataFile, "OtherInfo", "ReferenceBillNo");
        EnterDate("//Edit[@Name='Reference Bill Date']", dataFile, "OtherInfo", "ReferenceBillDate");
        EnterData("//Edit[@Name='LR No']", dataFile, "OtherInfo", "LRNo");
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        EnterDate("//Edit[@Name='LR Date']", dataFile, "OtherInfo", "LRDate");
        EnterData("//Edit[@Name='Other Info 1']", dataFile, "OtherInfo", "OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']", dataFile, "OtherInfo", "OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']", dataFile, "OtherInfo", "OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']", dataFile, "OtherInfo", "OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']", dataFile, "OtherInfo", "OtherInfo5");
    }

    public void shippingAddress(){
        EnterData("//Edit[@Name='Party Name']",dataFile,"ShippingAddress","PartyAccount");
        EnterData("//Edit[@Name='GSTIN']",dataFile,"ShippingAddress","GSTIN");
        EnterData("//Edit[@Name='Address1']",dataFile,"ShippingAddress","Address1");
        EnterData("//Edit[@Name='Address2']",dataFile,"ShippingAddress","Address2");
        EnterData("//Edit[@Name='Address3']",dataFile,"ShippingAddress","Address3");
        EnterData("//Edit[@Name='City']",dataFile,"ShippingAddress","City");
        EnterData("//Edit[@Name='State *']",dataFile,"ShippingAddress","State");
        EnterData("//Edit[@Name='Country *']",dataFile,"ShippingAddress","Country");
        EnterData("//Edit[@Name='Zip']",dataFile,"ShippingAddress","Zip");
        EnterData("//Edit[@Name='Telephone No']",dataFile,"ShippingAddress","TelephoneNo");
        EnterData("//Edit[@Name='Mobile No']",dataFile,"ShippingAddress","MobileNo");

    }

    public void termsAndCondition() throws IOException {
        List<String> termsAndConditions = readExcelData(dataFile, "TermsAndConditions", "TermType");
        for (int i = 0; i < termsAndConditions.size(); i++) {
            addData("xpath", "//Edit[@Name='Term Type * Row " + i + ", Not sorted.']", dataFile, "TermsAndConditions", "TermType", i);
        }
        List<WebElement> term = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Term * Row ')]");
        List<WebElement> comments = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < termsAndConditions.size(); i++) {
            enterListData(term.get(i), dataFile, "TermsAndConditions", "Term", i);
            enterListData(comments.get(i), dataFile, "TermsAndConditions", "Comments", i);
        }
    }
}