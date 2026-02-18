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
import  java.util.List;

public class SalesInvoiceTrade extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean isGSTAmountClicked = false, discountIsClicked = false, IsAmountHeaderClicked = false,infoHeaderclicked=false;

    public SalesInvoiceTrade(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String salesInvoiceTrade() throws InterruptedException, IOException, ParseException, AWTException {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Sales", "Invoices", "Sales Invoices");
        Thread.sleep(1000);
        long genInfoStart = System.nanoTime();
        String oldVoucherID = oldTTransactionID();
        enterVoucherType(dataFile, "GeneralInformation", "VoucherType");
        EnterDate("//Edit[@Name='Date *']", dataFile, "GeneralInformation", "Date");
        enterBranch(dataFile, "GeneralInformation", "Branch");
        EnterData("//Edit[@Name='Location *']", dataFile, "GeneralInformation", "Location");
        EnterData("//Edit[@Name='Storage Bin']", dataFile, "GeneralInformation", "StorageBin");
        EnterData("//Edit[@Name='Division']", dataFile, "GeneralInformation", "Division");
        EnterData("//Edit[@Name='Route']", dataFile, "GeneralInformation", "Route");
        EnterData("//Edit[@Name='Cash/Party Code']", dataFile, "GeneralInformation", "CustomerCode");
        EnterData("//Edit[@Name='Sales Executive *']", dataFile, "GeneralInformation", "SalesExecutive");
        enterCreditPeriod(dataFile, "GeneralInformation", "CreditPeriod");
        enterPriceList(dataFile, "GeneralInformation", "PriceList");
        EnterData("//Edit[@Name='Price Type']", dataFile, "GeneralInformation", "PriceType");
        EnterData("//Edit[@Name='Sales Account Code']", dataFile, "GeneralInformation", "SalesAccountCode");
        generalInfoSliderHandle(500);
        EnterData("//Edit[@Name='Cash Discount Basis']", dataFile, "GeneralInformation", "CashDiscountBasis");
        EnterData("//Edit[@Name='Cash Disc %']", dataFile, "GeneralInformation", "CashDiscount");
//        common.clickElement("xpath", "//CheckBox[@Name='Apply Simple Scheme']");
        common.clickElement("xpath", "//CheckBox[@Name='Apply Combo Scheme']");
        EnterData("//Edit[@Name='TCS Transaction Nature']", dataFile, "GeneralInformation", "TCSTransactionNature");
        enterDropDownData("//Edit[@Name='Sub Type']","Supply");
        Thread.sleep(1000);
        enterDropDownData("//Edit[@Name='Supply Type']","Outward");
        Thread.sleep(1000);
        enterDropDownData("//Edit[@Name='DocumentT Ype']","Tax Invoice");
        Thread.sleep(1000);
        enterDropDownData("//Edit[@Name='TransactionType']","Regular");
        Thread.sleep(1000);
        enterDropDownData("//Edit[@Name='Invoice Type']","Regular");
        Thread.sleep(1000);
        enterRemarks(dataFile, "GeneralInformation", "Remarks");
        long genInfoEnd=System.nanoTime() - genInfoStart;
        FileUtil.writeTimeLogInMinutes("SI Gen Info Ended at:- ", genInfoEnd);
        //items
        long addProductStart = System.nanoTime();
        addProduct();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("SI Add Products:- ", addProductEnd);

        long chargesDeductionsStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  ChargesAndDeductions  ')]");
        addChargesAndDeductions();
        long chargesDeductionsEnd = System.nanoTime() - chargesDeductionsStart;
        FileUtil.writeTimeLogInMinutes("SI Charges And Deductions:- ", chargesDeductionsEnd);

        long otherChargesStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  TaxableOtherCharges  ')]");
        addTaxableOtherCharges();
        long otherChargesEnd = System.nanoTime() - otherChargesStart;
        FileUtil.writeTimeLogInMinutes("SI Taxable Other Charges:- ", otherChargesEnd);

        long cashStart = System.nanoTime();
        navigateToCashTab();
        addCash();
        long cashEnd = System.nanoTime() - cashStart;
        FileUtil.writeTimeLogInMinutes("SI Cash Tab:- ", cashEnd);

        long chequesStart = System.nanoTime();
        List<WebElement> elements = common.findWebElements("xpath", "//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(0).getText());
        elements.get(0).click();
        addCheques();
        long chequesEnd = System.nanoTime() - chequesStart;
        FileUtil.writeTimeLogInMinutes("SI Cheques Tab:- ", chequesEnd);

        long postDatedChequesStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  PostDatedCheques  ')]");
        addPostDatedCheques();
        long postDatedChequesEnd = System.nanoTime() - postDatedChequesStart;
        FileUtil.writeTimeLogInMinutes("SI Post Dated Cheques Tab:- ", postDatedChequesEnd);

        long chequesPDCStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  PDC  ')]");
        addChequesPDC();
        long chequesPDCEnd = System.nanoTime() - chequesPDCStart;
        FileUtil.writeTimeLogInMinutes("SI Cheques PDC Tab:- ", chequesPDCEnd);

        long creditCardReceiptsStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  CreditCardReceipts  ')]");
        addCreditCardReceipts();
        long creditCardReceiptEnd = System.nanoTime() - creditCardReceiptsStart;
        FileUtil.writeTimeLogInMinutes("SI Credit Card Receipts Tab:- ", creditCardReceiptEnd);

        long addEWayBillsStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  EWayBillDetails  ')]");
        addEWayBillDetails();
        scrollRight(6);
        long addEWayBillEnd = System.nanoTime() - addEWayBillsStart;
        FileUtil.writeTimeLogInMinutes("SI E-Way Bill Tab:- ", addEWayBillEnd);

        long otherInfoTabStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  OtherInfo  ')]");
        otherInfo();
        long otherInfoTabEnd = System.nanoTime() - otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("SI Other Info:- ", otherInfoTabEnd);

        long shippingAddressTabStart = System.nanoTime();
        common.clickElement("xpath","//TabItem[contains(@Name,'  ShippingAddress  ')]");
        shippingAddress();
        long shippingAddressTabEnd = System.nanoTime() - shippingAddressTabStart;
        FileUtil.writeTimeLogInMinutes("SI Shipping Address:- ", shippingAddressTabEnd);

        long termsConditionsTabStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  TermsAndConditions  ')]");
        termsAndCondition();
        long termsConditionsTabEnd = System.nanoTime() - termsConditionsTabStart;
        FileUtil.writeTimeLogInMinutes("SI Terms And Conditions:- ", termsConditionsTabEnd);

        long allocationsTabStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'Allocations')]");
        addAllocations();
        long allocationsTabEnd = System.nanoTime() - allocationsTabStart;
        FileUtil.writeTimeLogInMinutes("SI Enter Allocations:- ", allocationsTabEnd);

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long salesOrderEnd = System.nanoTime() - start;
        FileUtil.writeTimeLogInMinutes("SI ended at:- ", salesOrderEnd);
        //API
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"purchaseOrders");
        deleteTransactionUsingVoucherNumber(newVoucherID);


        return newVoucherID;
    }

    public void addProduct() throws IOException, ParseException, InterruptedException {
        List<String> productCode = readExcelData(dataFile, "Items", "ProductCode");
        System.out.println("productCodes :" + productCode.size());
        for (int i = 0; i < productCode.size(); i++) {
            addData("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", dataFile, "Items", "ProductCode", i);
        }
        List<WebElement> productBatch = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Batch * Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        List<WebElement> uUnitQty = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'U Unit Qty Row ')]");
        List<WebElement> unitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Unit Rate Row ')]");
        List<WebElement> lUnitQty = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'L Unit Qty Row ')]");
        List<WebElement> baseUnitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Base Unit Rate Row ')]");
        List<WebElement> freeQuantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Free Qty Row ')]");
        List<WebElement> freeQuantityInBaseUnit = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Free Qty In Base Unit Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 800, 0);
        List<WebElement> mrp = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'MRP Row ')]");
        if (!discountIsClicked) {
            common.clickElement("xpath", "//Header[@Name='Discount Amount1']");
            common.clickElement("xpath", "//Header[@Name='Discount Amount2']");
            common.clickElement("xpath", "//Header[@Name='Discount Amount3']");
            discountIsClicked = true;
        }
        List<WebElement> discountAccount1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Account1 Row ')]");
        List<WebElement> discountBasis1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Basis1 Row ')]");
        List<WebElement> discount1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount1 Row ')]");
        List<WebElement> discountAmount1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Amount1 Row ')]");
        List<WebElement> discountAccount2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Account2 Row ')]");
        List<WebElement> discountBasis2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Basis2 Row ')]");
        List<WebElement> discount2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount2 Row ')]");
        List<WebElement> discountAmount2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Amount2 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 800, 0);
        List<WebElement> discountAccount3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Account3 Row ')]");
        List<WebElement> discountBasis3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Basis3 Row ')]");
        List<WebElement> discount3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount3 Row ')]");
        List<WebElement> discountAmount3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Amount3 Row ')]");
        List<WebElement> cashDiscAmount = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cash Disc %/Amt Row ')]");

        if (!isGSTAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            isGSTAmountClicked = true;
        }
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        List<WebElement> HSNCode = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'HSN Row ')]");
        List<WebElement> GSTProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'GST Product Category Row ')]");
        List<WebElement> CESSProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'CESS Product Category Row ')]");
        List<WebElement> executives = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Executive Row ')]");
        List<WebElement> division = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Division * Row ')]");
//        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
//        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
//        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
//        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        if (!infoHeaderclicked) {
            common.clickElement("xpath", "//Header[@Name='Info5']");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
            common.clickElement("xpath", "//Header[@Name='Value5']");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
            common.clickElement("xpath", "//Header[@Name='Date3']");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
            common.clickElement("xpath", "//Header[@Name='Bool3']");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
            infoHeaderclicked = true;
        }
        List<WebElement> Info1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info1 Row ')]");
        List<WebElement> Info2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info2 Row ')]");
        List<WebElement> Info3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info3 Row ')]");
        List<WebElement> Info4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info4 Row ')]");
        List<WebElement> Info5 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info5 Row ')]");
        List<WebElement> Value1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value1 Row ')]");
        List<WebElement> Value2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value2 Row ')]");
        List<WebElement> Value3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value3 Row ')]");
        List<WebElement> Value4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value4 Row ')]");
        List<WebElement> Value5 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value5 Row ')]");
        List<WebElement> Date1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date1 Row ')]");
        List<WebElement> Date2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date2 Row ')]");
        List<WebElement> Date3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date3 Row ')]");
        List<WebElement> Bool1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool1 Row ')]");
        List<WebElement> Bool2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool2 Row ')]");
        List<WebElement> Bool3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1900, 0);

        for (int i = 0; i < productCode.size(); i++) {
            enterListData(productBatch.get(i), dataFile, "Items", "Batch", i);
            enterListData(storageBin.get(i), dataFile, "Items", "StorageBin", i);
            enterListDate(uUnitQty.get(i), dataFile, "Items", "Qty", i);
            Thread.sleep(5000);
            enterListData(unitRate.get(i), dataFile, "Items", "UnitRate", i);
            enterListData(lUnitQty.get(i), dataFile, "Items", "QtyInBaseUnit", i);
            Thread.sleep(5000);
            enterListData(baseUnitRate.get(i), dataFile, "Items", "BaseUnitRate", i);
            Thread.sleep(5000);
            enterListData(freeQuantity.get(i), dataFile, "Items", "FreeQty", i);
            enterListData(freeQuantityInBaseUnit.get(i), dataFile, "Items", "FreeQtyInBaseUnit", i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
            enterListData(mrp.get(i), dataFile, "Items", "MRP", i);
            enterListData(discountAccount1.get(i), dataFile, "Items", "DiscountAccount1", i);
            enterListData(discountBasis1.get(i), dataFile, "Items", "DiscountBasis1", i);
            enterListData(discount1.get(i), dataFile, "Items", "Discount1", i);
            enterListData(discountAmount1.get(i), dataFile, "Items", "DiscountAmount1", i);
            enterListData(discountAccount2.get(i), dataFile, "Items", "DiscountAccount2", i);
            enterListData(discountBasis2.get(i), dataFile, "Items", "DiscountBasis2", i);
            enterListData(discount2.get(i), dataFile, "Items", "Discount2", i);
            enterListData(discountAmount2.get(i), dataFile, "Items", "DiscountAmount2", i);
            enterListData(discountAccount3.get(i), dataFile, "Items", "DiscountAccount3", i);
            enterListData(discountBasis3.get(i), dataFile, "Items", "DiscountBasis3", i);
            enterListData(discount3.get(i), dataFile, "Items", "Discount3", i);
            enterListData(discountAmount3.get(i), dataFile, "Items", "DiscountAmount3", i);
            if (i<=2) {
                enterListData(cashDiscAmount.get(i), dataFile, "Items", "CashDiscount", i);
            }
            else {
                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -100, 0);
                enterListData(cashDiscAmount.get(i), dataFile, "Items", "CashDiscount", i);
            }
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
            Thread.sleep(1000);
            if(i==0) {
                enterListData(HSNCode.get(i), dataFile, "Items", "HSN", i);
            } else {
                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -50, 0);
                enterListData(HSNCode.get(i), dataFile, "Items", "HSN", i);
            }
            enterListData(GSTProductCategory.get(i), dataFile, "Items", "GSTProductCategory", i);
            enterListData(CESSProductCategory.get(i), dataFile, "Items", "CESSProductCategory", i);
            enterListData(executives.get(i), dataFile, "Items", "Executive", i);
            enterListData(division.get(i), dataFile, "Items", "Division", i);
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
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1550, 0);
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

    public void addCash() throws IOException {
        List<String> cashTab = readExcelData(dataFile, "Cash", "CashAccountCode");
        for (int i = 0; i < cashTab.size(); i++) {
            addData("xpath", "//Edit[@Name='Cash Account Code Row " + i + ", Not sorted.']", dataFile, "Cash", "CashAccountCode", i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount Row ')]");
        List<WebElement> tdsTransactionNature = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> tdsAccount = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        List<WebElement> tdsAmount = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> divisions = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < cashTab.size(); i++) {
            enterListData(amountRowList.get(i), dataFile, "Cash", "Amount", i);
            enterListData(tdsTransactionNature.get(i), dataFile, "Cash", "TDSTransactionNature", i);
            enterListData(tdsAccount.get(i), dataFile, "Cash", "TDSAccount", i);
            enterListData(tdsAmount.get(i), dataFile, "Cash", "TDSAmount", i);
            enterListData(executive.get(i), dataFile, "Cash", "Executive", i);
            enterListData(divisions.get(i), dataFile, "Cash", "Division", i);
            enterListData(departmentRowList.get(i), dataFile, "Cash", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "Cash", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "Cash", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "Cash", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "Cash", "Comments", i);
        }
    }

    public void addCheques() throws IOException {
        List<String> chequesTab = readExcelData(dataFile, "Cheques", "BankAccountCode");
        for (int i = 0; i < chequesTab.size(); i++) {
            addData("xpath", "//Edit[@Name='Bank Account Code Row " + i + ", Not sorted.']", dataFile, "Cheques", "BankAccountCode", i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> drawnOnBank = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBranch = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> tdsTransactionNature = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> tdsAccount = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        List<WebElement> tdsAmount = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> chargesAccRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Account Code Row ')]");
        List<WebElement> chargesAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Cheques']/*/Thumb[@Name='Position']", 300, 0);
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> divisions = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Cheques']/*/Thumb[@Name='Position']", -350, 0);

        for (int i = 0; i < chequesTab.size(); i++) {
            enterListData(amountRowList.get(i), dataFile, "Cheques", "Amount", i);
            enterListData(chequeNo.get(i), dataFile, "Cheques", "ChequeNo", i);
            enterListDate(chequeDate.get(i), dataFile, "Cheques", "ChequeDate", i);
            enterListData(drawnOnBank.get(i), dataFile, "Cheques", "DrawnOnBankAccount", i);
            enterListData(drawnOnBranch.get(i), dataFile, "Cheques", "DrawnOnBankBranch", i);
            enterListData(tdsTransactionNature.get(i), dataFile, "Cheques", "TDSTransactionNature", i);
            enterListData(tdsAccount.get(i), dataFile, "Cheques", "TDSAccount", i);
            enterListData(tdsAmount.get(i), dataFile, "Cheques", "TDSAmount", i);
            enterListData(chargesAccRowList.get(i), dataFile, "Cheques", "ChargesAccount", i);
            enterListData(chargesAmountRowList.get(i), dataFile, "Cheques", "Charges", i);
            common.sliderHandling("xpath", "//Table[@Name='Cheques']/*/Thumb[@Name='Position']", 300, 0);
            enterListData(executive.get(i), dataFile, "Cheques", "Executive", i);
            enterListData(divisions.get(i), dataFile, "Cheques", "Division", i);
            enterListData(departmentRowList.get(i), dataFile, "Cheques", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "Cheques", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "Cheques", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "Cheques", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "Cheques", "Comments", i);
            common.sliderHandling("xpath", "//Table[@Name='Cheques']/*/Thumb[@Name='Position']", -300, 0);
        }
    }

    public void addPostDatedCheques() throws IOException {
        List<String> postDatedCheques = readExcelData(dataFile, "PostDatedCheques", "PDCAccountCode");
        for (int i = 0; i < postDatedCheques.size(); i++) {
            addData("xpath", "//Edit[@Name='PDC Account Code Row "+i+", Not sorted.']", dataFile, "PostDatedCheques", "PDCAccountCode", i);
        }
        List<WebElement> amoutRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> drawnOnBank = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBankBranch = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> tdsTransactionNature = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> tdsAccount = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        List<WebElement> tdsAmount = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> divisions = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < postDatedCheques.size(); i++) {
//            enterListData(bankAcct.get(i), dataFile, "PostDatedCheques", "BankAccountCode", i);
            enterListData(amoutRowList.get(i), dataFile, "PostDatedCheques", "Amount", i);
            enterListData(chequeNo.get(i), dataFile, "PostDatedCheques", "ChequeNo", i);
            enterListDate(chequeDate.get(i), dataFile, "PostDatedCheques", "ChequeDate", i);
            enterListData(drawnOnBank.get(i), dataFile, "PostDatedCheques", "DrawnOnBankAccount", i);
            enterListData(drawnOnBankBranch.get(i), dataFile, "PostDatedCheques", "DrawnOnBankBranch", i);
            enterListData(tdsTransactionNature.get(i), dataFile, "PostDatedCheques", "TDSTransactionNature", i);
            enterListData(tdsAccount.get(i), dataFile, "PostDatedCheques", "TDSAccount", i);
            enterListData(tdsAmount.get(i), dataFile, "PostDatedCheques", "TDSAmount", i);
            enterListData(executive.get(i), dataFile, "PostDatedCheques", "Executive", i);
            enterListData(divisions.get(i), dataFile, "PostDatedCheques", "Division", i);
            enterListData(departmentRowList.get(i), dataFile, "PostDatedCheques", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "PostDatedCheques", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "PostDatedCheques", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "PostDatedCheques", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "PostDatedCheques", "Comments", i);
        }
    }

    public void addChequesPDC() throws IOException {
        List<String> chequesPDC = readExcelData(dataFile, "PDC", "BankAccountCode");
        for (int i = 0; i < chequesPDC.size(); i++) {
            addData("xpath", "//Edit[@Name='Bank Account Code Row " + i + ", Not sorted.']", dataFile, "PDC", "BankAccountCode", i);
        }
        List<WebElement> chequeAmountRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> drawnOnBank = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBankBranch = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> tdsTransactionNature = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> tdsAccount = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        List<WebElement> tdsAmount = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> executives = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> divisions = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < chequesPDC.size(); i++) {
            enterListData(chequeAmountRowList.get(i), dataFile, "PDC", "Amount", i);
            enterListData(chequeNo.get(i), dataFile, "PDC", "ChequeNo", i);
            enterListDate(chequeDate.get(i), dataFile, "PDC", "ChequeDate", i);
            enterListDate(drawnOnBank.get(i), dataFile, "PDC", "DrawnOnBankAccount", i);
            enterListDate(drawnOnBankBranch.get(i), dataFile, "PDC", "DrawnOnBankBranch", i);
            enterListData(tdsTransactionNature.get(i), dataFile, "PDC", "TDSTransactionNature", i);
            enterListData(tdsAccount.get(i), dataFile, "PDC", "TDSAccount", i);
            enterListData(tdsAmount.get(i), dataFile, "PDC", "TDSAmount", i);
            enterListDate(executives.get(i), dataFile, "PDC", "Executive", i);
            enterListData(divisions.get(i), dataFile, "PDC", "Division", i);
            enterListData(departmentRowList.get(i), dataFile, "PDC", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "PDC", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "PDC", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "PDC", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "PDC", "Comments", i);
        }
    }

    public void addCreditCardReceipts() throws IOException {
        List<String> creditCardReceipts = readExcelData(dataFile, "CreditCardReceipts", "SwipeMachineType");
        for (int i = 0; i < creditCardReceipts.size(); i++) {
            addData("xpath", "//Edit[@Name='Swipe Machine Type * Row "+i+", Not sorted.']", dataFile, "CreditCardReceipts", "SwipeMachineType", i);
        }
        List<WebElement> swipeType = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Swipe Type * Row ')]");
        List<WebElement> amount = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> tdsTransactionNature = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> tdsAccount = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        List<WebElement> tdsAmount = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> cardNum = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Card No Row ')]");
        List<WebElement> expiryDate = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Expiry Date Row ')]");
        List<WebElement> approvalNum = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Approval No * Row ')]");
        List<WebElement> chargesAccRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Account Code Row ')]");
        List<WebElement> percentage = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Percentage Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='CreditCardReceipts']/*/Thumb[@Name='Position']", 300, 0);
        List<WebElement> executives = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> divisions = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='CreditCardReceipts']/*/Thumb[@Name='Position']", -350, 0);


        for (int i = 0; i < creditCardReceipts.size(); i++) {
            enterListData(swipeType.get(i), dataFile, "CreditCardReceipts", "SwipeType", i);
            enterListData(amount.get(i), dataFile, "CreditCardReceipts", "Amount", i);
            enterListData(tdsTransactionNature.get(i), dataFile, "CreditCardReceipts", "TDSTransactionNature", i);
            enterListData(tdsAccount.get(i), dataFile, "CreditCardReceipts", "TDSAccount", i);
            enterListData(tdsAmount.get(i), dataFile, "CreditCardReceipts", "TDSAmount", i);
            enterListData(cardNum.get(i), dataFile, "CreditCardReceipts", "CardNo", i);
            enterListDate(expiryDate.get(i), dataFile, "CreditCardReceipts", "ExpiryDate", i);
            enterListData(approvalNum.get(i), dataFile, "CreditCardReceipts", "ApprovalNo", i);
            enterListData(chargesAccRowList.get(i), dataFile, "CreditCardReceipts", "ChargesAccountCode", i);
            enterListData(percentage.get(i), dataFile, "CreditCardReceipts", "Percentage", i);
            common.sliderHandling("xpath", "//Table[@Name='CreditCardReceipts']/*/Thumb[@Name='Position']", 350, 0);
            enterListData(executives.get(i), dataFile, "CreditCardReceipts", "Executive", i);
            enterListData(divisions.get(i), dataFile, "CreditCardReceipts", "Division", i);
            enterListData(departmentRowList.get(i), dataFile, "CreditCardReceipts", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "CreditCardReceipts", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "CreditCardReceipts", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "CreditCardReceipts", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "CreditCardReceipts", "Comments", i);
            common.sliderHandling("xpath", "//Table[@Name='CreditCardReceipts']/*/Thumb[@Name='Position']", -400, 0);
        }
    }

    public void addEWayBillDetails() throws IOException {
        EnterData("//Edit[@Name='Transporter']",dataFile,"EWayBillDetails","Transporter");
        EnterData("//Edit[@Name='Mode Of Transport']",dataFile,"EWayBillDetails","ModeOfTransport");
        EnterData("//Edit[@Name='Distance']",dataFile,"EWayBillDetails","Distance");
        EnterData("//Edit[@Name='Trans Doc No']",dataFile,"EWayBillDetails","TransDocNo");
        EnterDate("//Edit[@Name='Trans Date']",dataFile,"EWayBillDetails","TransDate");
        EnterData("//Edit[@Name='Vehicle No']",dataFile,"EWayBillDetails","VehicleNo");
    }

    public void otherInfo() throws InterruptedException, IOException, AWTException {
        EnterData("//Edit[@Name='Reference Bill No']", dataFile, "OtherInfo", "ReferenceBillNo");
        EnterDate("//Edit[@Name='Reference Bill Date']", dataFile, "OtherInfo", "ReferenceBillDate");
        EnterData("//Edit[@Name='LR No']", dataFile, "OtherInfo", "LRNo");
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        EnterDate("//Edit[@Name='LR Date']", dataFile, "OtherInfo", "LRDate");
        EnterDate("//Edit[@Name='Removal Of Goods Date']", dataFile, "OtherInfo", "RemovalOfGoodsDate");
        EnterData("//Edit[@Name='Removal Of Goods Time']", dataFile, "OtherInfo", "RemovalOfGoodsTime");
        EnterData("//Edit[@Name='Invoice Preparation Time']", dataFile, "OtherInfo", "InvoicePreparationTime");
        EnterData("//Edit[@Name='Other Info 1']", dataFile, "OtherInfo", "OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']", dataFile, "OtherInfo", "OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']", dataFile, "OtherInfo", "OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']", dataFile, "OtherInfo", "OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']", dataFile, "OtherInfo", "OtherInfo5");
    }

    public void shippingAddress(){
        EnterData("//Edit[@Name='Party Name']",dataFile,"ShippingAddress","PartyAccount");
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

    public void addAllocations() throws IOException, ParseException {
        EnterData("//Edit[@Name='Department']", dataFile, "Allocations", "Department");
        EnterData("//Edit[@Name='Project']", dataFile, "Allocations", "Project");
        EnterData("//Edit[@Name='Profit Centre']", dataFile, "Allocations", "ProfitCentre");
        EnterData("//Edit[@Name='Cost Centre']", dataFile, "Allocations", "CostCentre");
    }
}