package tradeTesting.purchase.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PurchaseReturnsWithInvoiceReference extends TransactionsBaseClass {
    WindowsDriver driver,rootDriver;
    Common common;
    String dataFile;

    boolean IsAmountHeaderClicked = false;

    public PurchaseReturnsWithInvoiceReference(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }


    public String purchaseReturnsWithInvoiceReference(String voucherNum,String payableVoucher,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long prwir = System.nanoTime();

        navigateToMastersWhen3Steps("Purchase","Returns","Purchase Returns With Invoice Reference");
        Thread.sleep(3000);
        long start1 = System.nanoTime();
        String oldVoucherID = oldTTransactionID();

        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
//        WebElement date =common.findWebElement("xpath","//Edit[@Name='Date *']");
//        date.clear();
//        date.sendKeys("30-03-2026");
        enterBranchName(dataFile,"GeneralInformation","Branch");
        enterLocation(dataFile,"GeneralInformation","Location");
        WebElement element=common.findWebElement("xpath","//Edit[@Name='Purchase Invoice No']");
        element.sendKeys(voucherNum);
        EnterData("//Edit[@Name='Division *']",dataFile,"GeneralInformation","Division");
        enterCreditPeriod(dataFile,"GeneralInformation","CreditPeriod");
        enterPriceList(dataFile,"GeneralInformation","PriceList");
        EnterData("//Edit[@Name='Price Type']",dataFile,"GeneralInformation","PriceType");
        EnterData("//Edit[@Name='Purchase Return Account Code']",dataFile,"GeneralInformation","PurchaseReturnAccountCode");
        enableCheckboxSelection("//CheckBox[@Name='Deduct TDS']");
        EnterData("//Edit[@Name='TDS Transaction Nature']",dataFile,"GeneralInformation","TDSTransactionNature");
        EnterData("//Edit[@Name='Executive']",dataFile,"GeneralInformation","Executive");
//        enableCheckboxSelection("//CheckBox[@Name='Shipping Address']");
//        EnterData("//Edit[@Name='Shipping Addressis Differentfrom Billing Address']",dataFile,"GeneralInformation","ShippingAddressisDifferentfromBillingAddress");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        long duration1 = System.nanoTime() - start1;
        FileUtil.writeTimeLogInMinutes("Purchase Returns With Invoice Reference General info :- ", duration1);

        long products = System.nanoTime();
        addProduct();
        long productsEnd = System.nanoTime() - products;
        FileUtil.writeTimeLogInMinutes("Purchase Returns With Invoice Reference Add Products:- ", productsEnd);

        long chargesDeductionsStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  ChargesAndDeductions  ')]");
        addChargesAndDeductions();
        long chargesDeductionsEnd = System.nanoTime() - chargesDeductionsStart;
        FileUtil.writeTimeLogInMinutes("PRWIR Charges And Deductions:- ", chargesDeductionsEnd);

        long otherChargesStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  TaxableOtherCharges  ')]");
        addTaxableOtherCharges();
        long otherChargesEnd = System.nanoTime() - otherChargesStart;
        FileUtil.writeTimeLogInMinutes("PRWIR Other Charges:- ", otherChargesEnd);

        long cashStart = System.nanoTime();
        navigateToCashTab();
        addCash();
        long cashEnd = System.nanoTime() - cashStart;
        FileUtil.writeTimeLogInMinutes("PRWIR Cash Tab:- ", cashEnd);

        long chequesStart = System.nanoTime();
        List<WebElement> elements = common.findWebElements("xpath", "//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(0).getText());
        elements.get(0).click();
        addCheques();
        long chequesEnd = System.nanoTime() - chequesStart;
        FileUtil.writeTimeLogInMinutes("PRWIR Cheques Tab:- ", chequesEnd);

        long postDatedChequesStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  PostDatedCheques  ')]");
        addPostDatedCheques();
        long postDatedChequesEnd = System.nanoTime() - postDatedChequesStart;
        FileUtil.writeTimeLogInMinutes("PRWIR Post Dated Cheques Tab:- ", postDatedChequesEnd);

        long chequesPDCStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  PDC  ')]");
        addChequesPDC();
        long chequesPDCEnd = System.nanoTime() - chequesPDCStart;
        FileUtil.writeTimeLogInMinutes("PRWIR Cheques PDC Tab:- ", chequesPDCEnd);

        common.clickElement("xpath", "//TabItem[contains(@Name,'  BillsPayable  ')]");
        adjustAmountInBillsPayable(dataFile,payableVoucher);
        //        common.deleteInvalidRows();

        common.clickElement("xpath", "//TabItem[contains(@Name,'  BillsReceivable  ')]");
        common.deleteInvalidRows();

        long otherInfoTabStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  OtherInfo  ')]");
        otherInfo();
        long otherInfoTabEnd = System.nanoTime() - otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("PRWIR Other Info:- ", otherInfoTabEnd);


        long termsConditionsTabStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  TermsAndConditions  ')]");
        termsAndCondition();
        long termsConditionsTabEnd = System.nanoTime() - termsConditionsTabStart;
        FileUtil.writeTimeLogInMinutes("PRWIR Terms And Conditions:- ", termsConditionsTabEnd);

        long shippingAddressTabStart = System.nanoTime();
        shippingAddress();
        long shippingAddressTabEnd = System.nanoTime() - shippingAddressTabStart;
        FileUtil.writeTimeLogInMinutes("PRWIR Shipping Address:- ", shippingAddressTabEnd);

        long allocationsTabStart=System.nanoTime();
        addAllocations();
        long allocationsTabEnd=System.nanoTime() - allocationsTabStart;
        FileUtil.writeTimeLogInMinutes("PRWIR Allocations Tab:- ", allocationsTabEnd);

        transactionSave();
        String newVoucherID = newTransactionID(oldVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"PurchaseReturnsWithInvoiceReference");

        deleteTransactionUsingVoucherNumber(newVoucherID);
        long PurchaseVoucherEnd = System.nanoTime() - prwir;
        FileUtil.writeTimeLogInMinutes("PurchaseReturns With Invoice Reference End", PurchaseVoucherEnd);
        return newVoucherID;
    }

    public void addProduct() throws InterruptedException, IOException, ParseException, AWTException {
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        List<WebElement> priceType = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Price Type Row ')]");
        List<WebElement> unitRateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Unit Rate Row ')]");
        List<WebElement> copyQtyAsReturn = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[starts-with(@Name,'Copy Qty As Return Qty Row ')]");
        List<WebElement> returnQty = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Return Qty Row ')]");
        List<WebElement> baseUnitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Base Unit Rate Row ')]");
        List<WebElement> copyQuantityInBaseUnitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[starts-with(@Name,'Copy Qty In Base Unit As Return Base Qty Row ')]");
        List<WebElement> returnQtyInBaseUnitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Return Qty In Base Unit Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        List<WebElement> copyReturnQtyAsFreeQtyRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[starts-with(@Name,'Copy Free Qty As Return Free Qty Row ')]");
        List<WebElement> returnFreeQty = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Return Free Qty Row ')]");
        List<WebElement> copyFreeBaseUnitQtyAsReturnFreeQtyInBase  = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[starts-with(@Name,'Copy Free Base Unit Qty As Return Free Qty In Base Unit Row ')]");
        List<WebElement> returnFreeQtyInBaseUnit = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Return Free Qty In Base Unit Row ')]");
        List<WebElement> mrp = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'MRP Row ')]");
        List<WebElement> cashDiscountBasis = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cash Discount Basis Row ')]");
        List<WebElement> cashDisc = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cash Disc % Row ')]");
        List<WebElement> discountAcc1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Account1 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        List<WebElement> discountBasis1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Basis1 Row ')]");
        List<WebElement> discount1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount1 Row ')]");
        List<WebElement> discountAcc2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Account2 Row ')]");
        List<WebElement> discountBasis2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Basis2 Row ')]");
        List<WebElement> discount2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount2 Row ')]");
        List<WebElement> discountAcc3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Account3 Row ')]");
        List<WebElement> discountBasis3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Basis3 Row ')]");
        List<WebElement> discount3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 370, 0);
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> gstProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 700, 0);
        List<WebElement> reason = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Reason Row ')]");
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> division = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
//        common.clickElement("xpath", "//Header[@Name='Info 5']");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 160, 0);
//        List<WebElement> infoRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 1 Row ')]");
//        List<WebElement> infoRowList1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 2 Row ')]");
//        List<WebElement> infoRowList2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 3 Row ')]");
//        List<WebElement> infoRowList3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 4 Row ')]");
//        common.clickElement("xpath", "//Header[@Name='Value 5']");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 160, 0);
//        List<WebElement> infoRowList4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 5 Row ')]");
//        List<WebElement> valueRowList1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 2 Row ')]");
//        List<WebElement> valueRowList2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 3 Row ')]");
//        List<WebElement> valueRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 1 Row ')]");
//        common.clickElement("xpath", "//Header[@Name='Date 3']");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 160, 0);
//        List<WebElement> valueRowList3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 4 Row ')]");
//        List<WebElement> valueRowList4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 5 Row ')]");
//        List<WebElement> dateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Date 1 Row ')]");
//        List<WebElement> dateRowList1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Date 2 Row ')]");
//        common.clickElement("xpath", "//Header[@Name='Bool 3']");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 160, 0);
//        List<WebElement> dateRowList2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Date 3 Row ')]");
//        List<WebElement> bool1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 1 Row ')]");
//        List<WebElement> bool2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 2 Row ')]");
//        List<WebElement> bool3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1500, 0);

        for (int j = 0; j < storageBin.size()-1; j++) {
            enterListData(storageBin.get(j), dataFile, "Items", "StorageBin", j);
            enterListData(priceType.get(j), dataFile, "Items", "PriceType", j);
            enterListData(unitRateRowList.get(j), dataFile, "Items", "UnitRate", j);
            clickListData(copyQtyAsReturn.get(j));
            enterListData(returnQty.get(j), dataFile, "Items", "ReturnQty", j);
            enterListData(baseUnitRate.get(j), dataFile, "Items", "BaseUnitRate", j);
            clickListData(copyQuantityInBaseUnitRate.get(j));
            enterListData(returnQtyInBaseUnitRate.get(j), dataFile, "Items", "ReturnQtyInBaseUnit", j);
            if (j==3) {
                Thread.sleep(500);
                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -50, 0);
            }
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -50, 0);
            clickListData(copyReturnQtyAsFreeQtyRate.get(j));
            enterListData(returnFreeQty.get(j), dataFile, "Items", "ReturnFreeQty", j);
            clickListData(copyFreeBaseUnitQtyAsReturnFreeQtyInBase.get(j));
            enterListData(returnFreeQtyInBaseUnit.get(j), dataFile, "Items", "ReturnFreeQtyInBaseUnit", j);
            enterListData(mrp.get(j), dataFile, "Items", "MRP", j);
            enterListData(cashDiscountBasis.get(j), dataFile, "Items", "CashDiscountBasis", j);
            enterListData(cashDisc.get(j), dataFile, "Items", "CashDiscount", j);
            enterListData(discountAcc1.get(j), dataFile, "Items", "DiscountAccount1", j);
            enterListData(discountBasis1.get(j), dataFile, "Items", "DiscountBasis1", j);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 360, 0);
            enterListData(discount1.get(j), dataFile, "Items", "Discount1", j);
            enterListData(discountAcc2.get(j), dataFile, "Items", "DiscountAccount2", j);
            enterListData(discountBasis2.get(j), dataFile, "Items", "DiscountBasis2", j);
            enterListData(discount2.get(j), dataFile, "Items", "Discount2", j);
            enterListData(discountAcc3.get(j), dataFile, "Items", "DiscountAccount3", j);
            enterListData(discountBasis3.get(j), dataFile, "Items", "DiscountBasis3", j);
            enterListData(discount3.get(j), dataFile, "Items", "Discount3", j);
            Thread.sleep(500);
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
            enterListData(hsnCodeRowList.get(j), dataFile, "Items", "HSN", j);
            enterListData(gstProductCategoryRowList.get(j), dataFile, "Items", "GSTProductCategory",j);
            enterListData(cessProductCategoryRowList.get(j), dataFile, "Items", "CESSProductCategory",j);
            Thread.sleep(500);
            enterListData(reason.get(j), dataFile, "Items", "Reason",j);
            enterListData(executive.get(j), dataFile, "Items", "Executive",j);
            enterListData(division.get(j), dataFile, "Items", "Division",j);
            enterListData(departmentRowList.get(j), dataFile, "Items", "Department",j);
            enterListData(projectRowList.get(j), dataFile, "Items", "Project",j);
            enterListData(profitCentreRowList.get(j), dataFile, "Items", "ProfitCentre",j);
            enterListData(costCentreRowList.get(j), dataFile, "Items", "CostCentre",j);
            enterListData(commentsRowList.get(j), dataFile, "Items", "Comments",j);
//            enterListData(infoRowList.get(j), dataFile, "Items", "Info1",j);
//            enterListData(infoRowList1.get(j), dataFile, "Items", "Info2",j);
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 80, 0);
//            enterListData(infoRowList2.get(j), dataFile, "Items", "Info3", j);
//            enterListData(infoRowList3.get(j), dataFile, "Items", "Info4", j);
//            enterListData(infoRowList4.get(j), dataFile, "Items", "Info5", j);
//            enterListData(valueRowList1.get(j), dataFile, "Items", "Value2",j);
//            enterListData(valueRowList2.get(j), dataFile, "Items", "Value3",j);
//            enterListData(valueRowList.get(j), dataFile, "Items", "Value1",j);
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 50, 0);
//            enterListData(valueRowList3.get(j), dataFile, "Items", "Value4",j);
//            enterListData(valueRowList4.get(j), dataFile, "Items", "Value5",j);
//            enterListDate(dateRowList.get(j),dataFile,"Items","Date1",j);
//            enterListDate(dateRowList1.get(j),dataFile,"Items","Date2",j);
//            enterListDate(dateRowList2.get(j),dataFile,"Items","Date3",j);
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 30, 0);
//            clickListData(bool1.get(j));
//            clickListData(bool2.get(j));
//            clickListData(bool3.get(j));
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1400, 0);
        }
    }

    public void addChargesAndDeductions() throws IOException, ParseException {
        List<String> productCode=readExcelData(dataFile,"ChargesAndDeductions","ChargesOrDeductions");
        System.out.println("productCodes :"+productCode.size());
        for (int i = 0; i < productCode.size() ; i++) {
            addData("xpath","//Edit[@Name='Charges Or Deductions * Row "+i+", Not sorted.']",dataFile,"ChargesAndDeductions","ChargesOrDeductions",i);
        }
        List<WebElement> accCodeRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Account Code Row ')]");
        if (!IsAmountHeaderClicked) {
            common.clickElement("xpath", "//Header[@Name='Amount *']");
            IsAmountHeaderClicked = true;
        }
        List<WebElement> basisRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Basis Row ')]");
        List<WebElement> percentageRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Percentage Row ')]");
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> division = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < productCode.size(); i++) {
            enterListData(accCodeRowList.get(i), dataFile, "ChargesAndDeductions", "AccountCode",i);
            enterListData(basisRowList.get(i), dataFile, "ChargesAndDeductions", "Basis",i);
            enterListData(percentageRowList.get(i), dataFile, "ChargesAndDeductions", "Percentage",i);
            enterListData(executive.get(i), dataFile, "ChargesAndDeductions", "Executive", i);
            enterListData(division.get(i), dataFile, "ChargesAndDeductions", "Division", i);
            enterListData(departmentRowList.get(i), dataFile, "ChargesAndDeductions", "Department",i);
            enterListData(projectRowList.get(i), dataFile, "ChargesAndDeductions", "Project",i);
            enterListData(profitCentreRowList.get(i), dataFile, "ChargesAndDeductions", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "ChargesAndDeductions", "CostCentre",i);
            enterListData(commentsRowList.get(i), dataFile, "ChargesAndDeductions", "Comments",i);
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
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='TaxableOtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> division = common.findWebElements("xpath", "//Table[@Name='TaxableOtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division * Row ')]");
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
            enterListData(executive.get(i), dataFile, "TaxableOtherCharges", "Executive", i);
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
        List<String> cash=readExcelData(dataFile,"Cash","CashAccountCode");
        for (int i = 0; i< cash.size(); i++) {
            addData("xpath","//Edit[@Name='Cash Account Code Row "+i+", Not sorted.']",dataFile,"Cash","CashAccountCode",i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> division = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <cash.size() ; i++) {
            enterListData(amountRowList.get(i), dataFile, "Cash", "Amount",i);
            enterListData(executive.get(i), dataFile, "Cash", "Executive", i);
            enterListData(division.get(i), dataFile, "Cash", "Division", i);
            enterListData(departmentRowList.get(i),dataFile,"Cash","Department",i);
            enterListData(projectRowList.get(i),dataFile,"Cash","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"Cash","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"Cash","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"Cash","Comments",i);
        }
    }

    public void addCheques() throws IOException {
        List<String> cheque=readExcelData(dataFile,"Cheques","BankAccountCode");
        for (int i = 0; i< cheque.size(); i++) {
            addData("xpath","//Edit[@Name='Bank Account Code Row "+i+", Not sorted.']",dataFile,"Cheques","BankAccountCode",i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> ChequeNo = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> ChequeDate = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> drawnOnBranch = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> chargesAccRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Account Code Row ')]");
        List<WebElement> chargesAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Row ')]");
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> division = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <cheque.size(); i++) {
            enterListData(amountRowList.get(i), dataFile, "Cheques", "Amount", i);
            enterListData(ChequeNo.get(i), dataFile, "Cheques", "ChequeNo" ,i);
            enterListDate(ChequeDate.get(i), dataFile, "Cheques", "ChequeDate" , i);
            enterListData(drawnOnBranch.get(i), dataFile, "PDC", "DrawnOnBankBranch", i);
            enterListData(chargesAccRowList.get(i), dataFile, "Cheques", "ChargesAccountCode" , i);
            enterListData(chargesAmountRowList.get(i), dataFile, "Cheques", "Charges" , i);
            enterListData(executive.get(i), dataFile, "Cheques", "Executive", i);
            enterListData(division.get(i), dataFile, "Cheques", "Division", i);
            enterListData(departmentRowList.get(i),dataFile,"Cheques","Department",i);
            enterListData(projectRowList.get(i),dataFile,"Cheques","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"Cheques","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"Cheques","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"Cheques","Comments",i);
        }
    }

    public void addPostDatedCheques() throws IOException {
        List<String> postDateDCheques=readExcelData(dataFile,"PostDatedCheques","PDCAccountCode");
        for (int i = 0; i< postDateDCheques.size(); i++) {
            addData("xpath","//Edit[@Name='PDC Account Code Row "+i+", Not sorted.']",dataFile,"PostDatedCheques","PDCAccountCode",i);
        }
        List<WebElement> PDCAcc = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'PDC Account * Row ')]");
        List<WebElement> amoutRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> ChequeNo = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> ChequeDate = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> drawnOnBranch = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> division = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <postDateDCheques.size() ; i++) {
            enterListData(PDCAcc.get(i), dataFile, "PostDatedCheques", "PDCccount", i);
            enterListData(amoutRowList.get(i), dataFile, "PostDatedCheques", "Amount", i);
            enterListData(ChequeNo.get(i), dataFile, "PostDatedCheques", "ChequeNo" ,i);
            enterListDate(ChequeDate.get(i), dataFile, "PostDatedCheques", "ChequeDate" , i);
            enterListData(drawnOnBranch.get(i), dataFile, "PostDatedCheques", "DrawnOnBankBranch", i);
            enterListData(executive.get(i), dataFile, "PostDatedCheques", "Executive", i);
            enterListData(division.get(i), dataFile, "PostDatedCheques", "Division", i);
            enterListData(departmentRowList.get(i),dataFile,"PostDatedCheques","Department",i);
            enterListData(projectRowList.get(i),dataFile,"PostDatedCheques","Project",i);
            enterListData(costCentreRowList.get(i),dataFile,"PostDatedCheques","CostCentre",i);
            enterListData(profitCentreRowList.get(i),dataFile,"PostDatedCheques","ProfitCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"PostDatedCheques","Comments",i);
        }
    }

    public void addChequesPDC() throws IOException {
        List<String> chequesPDC=readExcelData(dataFile,"PDC","BankAccountCode");
        for (int i = 0; i< chequesPDC.size(); i++) {
            addData("xpath","//Edit[@Name='Bank Account Code Row "+i+", Not sorted.']",dataFile,"PDC","BankAccountCode",i);
        }
        List<WebElement> chequeAmountRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> ChequeNo = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> ChequeDate = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> drawnOnBranch = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> division = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Division * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i <chequesPDC.size() ; i++) {
            enterListData(chequeAmountRowList.get(i), dataFile, "PDC", "Amount",i);
            enterListData(ChequeNo.get(i), dataFile, "PDC", "ChequeNo" ,i);
            enterListDate(ChequeDate.get(i), dataFile, "PDC", "ChequeDate" , i);
            enterListData(drawnOnBranch.get(i), dataFile, "PDC", "DrawnOnBankBranch", i);
            enterListData(executive.get(i), dataFile, "PDC", "Executive", i);
            enterListData(division.get(i), dataFile, "PDC", "Division", i);
            enterListData(departmentRowList.get(i),dataFile,"PDC","Department",i);
            enterListData(projectRowList.get(i),dataFile,"PDC","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"PDC","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"PDC","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"PDC","Comments",i);
        }
    }

    public void otherInfo() throws InterruptedException, IOException, AWTException {
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='LR No']",dataFile,"OtherInfo","LRNo");
        common.clickElement("xpath","//Button[@Name='OK']");
        EnterDate("//Edit[@Name='LR Date']",dataFile,"OtherInfo","LRDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }

    public void shippingAddress(){
        common.clickElement("xpath","//TabItem[contains(@Name,'  ShippingAddress  ')]");
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


    public  void termsAndCondition() throws IOException {
        List<String> termsAndConditions=readExcelData(dataFile,"TermsAndConditions","TermType");
        for (int i = 0; i < termsAndConditions.size() ; i++) {
            addData("xpath","//Edit[@Name='Term Type * Row "+i+", Not sorted.']",dataFile,"TermsAndConditions","TermType",i);
        }
        List<WebElement> term = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Term * Row ')]");
        List<WebElement> comments = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < termsAndConditions.size(); i++) {
            enterListData(term.get(i), dataFile, "TermsAndConditions", "Term", i);
            enterListData(comments.get(i), dataFile, "TermsAndConditions", "Comments", i);
        }
    }

    public void addAllocations() {
        common.clickElement("xpath","//TabItem[contains(@Name,'  Allocations  ')]");
        EnterData( "//Edit[@Name='Department']", dataFile, "Allocations", "Department");
        EnterData( "//Edit[@Name='Project']", dataFile, "Allocations", "Project");
        EnterData("//Edit[@Name='Profit Centre']", dataFile, "Allocations", "ProfitCentre");
        EnterData( "//Edit[@Name='Cost Centre']", dataFile, "Allocations", "CostCentre");
    }

}
