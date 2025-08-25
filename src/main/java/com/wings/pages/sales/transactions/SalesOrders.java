package com.wings.pages.sales.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class SalesOrders extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false, IsAmountHeaderClicked = false, otherChargesGSTCheckBox = false, discountIsClicked = false;


    public SalesOrders(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String salesOrder(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws InterruptedException, IOException, ParseException, AWTException {
        long start = System.nanoTime();
        System.out.println("Sales Order startTime in :" + start);

        long generalInfoStart = System.nanoTime();
        System.out.println("Sales Order general Info started in :" + generalInfoStart);

        navigateToSalesOrderMenu();
        Thread.sleep(3500);
        String oldVoucherID = oldTTransactionID();
        //gen info
        enterVoucherType(dataFile, "GeneralInformation", "VoucherType");
        EnterDate("//Edit[@Name='Date *']", dataFile, "GeneralInformation", "Date");
        enterBranch(dataFile, "GeneralInformation", "Branch");
        enterLocation(dataFile,"GeneralInformation","Location");
        enterCurrency(dataFile, "GeneralInformation", "TransactionCurrency");
        enterExchangeRate(dataFile, "GeneralInformation", "ExchangeRate");
        enterPartyCode(dataFile, "GeneralInformation", "PartyAccountCode");
        Thread.sleep(3000);
        gstTransactionType("Intra State Sales to Registered Dealers");
        Thread.sleep(1000);
        enterCustomerEmail(dataFile, "GeneralInformation", "CustomerEmail");
        enterCustomerMobileNum(dataFile, "GeneralInformation", "CustomerMobileNumber");
        enterCreditPeriod(dataFile, "GeneralInformation", "CreditPeriod");
        enterPriceList(dataFile, "GeneralInformation", "PriceList");
        enterVoucherDiscount(dataFile, "GeneralInformation", "VoucherDiscountPercentage");
        enterExecutive(dataFile, "GeneralInformation", "Executive");
        common.clickElement("xpath","//CheckBox[@Name='Advance Receipts']");
        enterRemarks(dataFile, "GeneralInformation", "Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("SO General Information End:- ", generalInfoEndTime);
        //items
        long addProductStart = System.nanoTime();
        addProduct();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("SO Add Products:- ", addProductEnd);

        long chargesDeductionsStart = System.nanoTime();
        addChargesAndDeductions();
        long chargesDeductionsEnd = System.nanoTime() - chargesDeductionsStart;
        FileUtil.writeTimeLogInMinutes("SO Charges And Deductions:- ", chargesDeductionsEnd);

        long otherChargesStart = System.nanoTime();
        addOtherCharges();
        long otherChargesEnd = System.nanoTime() - otherChargesStart;
        FileUtil.writeTimeLogInMinutes("SO Other Charges:- ", otherChargesEnd);

        long cashStart = System.nanoTime();
        addCashSalesOrder();
        long cashEnd = System.nanoTime() - cashStart;
        FileUtil.writeTimeLogInMinutes("SO Cash Tab:- ", cashEnd);

        long chequesStart = System.nanoTime();
        addCheques();
        long chequesEnd = System.nanoTime() - chequesStart;
        FileUtil.writeTimeLogInMinutes("SO Cheques Tab:- ", chequesEnd);

        long postDatedChequesStart = System.nanoTime();
        addPostDatedCheques();
        long postDatedChequesEnd = System.nanoTime() - postDatedChequesStart;
        FileUtil.writeTimeLogInMinutes("SO Post Dated Cheques Tab:- ", postDatedChequesEnd);

        long chequesPDCStart = System.nanoTime();
        addChequesPDC();
        long chequesPDCEnd = System.nanoTime() - chequesPDCStart;
        FileUtil.writeTimeLogInMinutes("SO Cheques PDC Tab:- ", chequesPDCEnd);

        long creditCardStart = System.nanoTime();
        addCreditCard();
        long creditCardEnd = System.nanoTime() - creditCardStart;
        FileUtil.writeTimeLogInMinutes("SO Credit Card Tab:- ", creditCardEnd);

        long payTymTabStart = System.nanoTime();
        addPayTm();
        long payTymTabEnd = System.nanoTime() - payTymTabStart;
        FileUtil.writeTimeLogInMinutes("SO PayTym Tab:- ", payTymTabEnd);
        //pinelab
        long pineLabTabStart = System.nanoTime();
        addPineLab();
        long pineLabTabEnd = System.nanoTime() - pineLabTabStart;
        FileUtil.writeTimeLogInMinutes("SO pineLab Tab:- ", pineLabTabEnd);

        long otherInfoTabStart = System.nanoTime();
        otherInfo();
        long otherInfoTabEnd = System.nanoTime() - otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("SO Other Info:- ", otherInfoTabEnd);
        //scroll
        common.clickElement("xpath", "//TabItem[contains(@Name,'Additional Information  ')]");
        moveToRight(6);
        //additional Info
        long additionalInfoTabStart = System.nanoTime();
        additionalInformation();
        long additionalInfoTabEnd = System.nanoTime() - additionalInfoTabStart;
        FileUtil.writeTimeLogInMinutes("SO Additional Information:- ", additionalInfoTabEnd);
        //shipping Address
        long shippingAddressTabStart=System.nanoTime();
        shippingAddress();
        long shippingAddressTabEnd=System.nanoTime()-shippingAddressTabStart;
        FileUtil.writeTimeLogInMinutes("SO  Shipping address tab: ",shippingAddressTabEnd);
        long termsConditionsTabStart = System.nanoTime();
        termsAndCondition();
        long termsConditionsTabEnd = System.nanoTime() - termsConditionsTabStart;
        FileUtil.writeTimeLogInMinutes("SO Terms And Conditions:- ", termsConditionsTabEnd);

        long allocationsTabStart = System.nanoTime();
        addAllocations();
        long allocationsTabEnd = System.nanoTime() - allocationsTabStart;
        FileUtil.writeTimeLogInMinutes("SO Enter Allocations:- ", allocationsTabEnd);

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long salesInvoiceEnd = System.nanoTime() - start;
        FileUtil.writeTimeLogInMinutes("Sales Order ended at:- ", salesInvoiceEnd );
        //API
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"salesOrder");

        return newVoucherID;
    }

    public void addProduct() throws IOException, ParseException {
        List<String> productCode = readExcelData(dataFile, "Items", "ProductCode");
        System.out.println("productCodes :" + productCode.size());
        for (int i = 0; i < productCode.size(); i++) {
            addData("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", dataFile, "Items", "ProductCode", i);
        }
        List<WebElement> uom = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Quantity * Row ')]");
        List<WebElement> DeliveryDate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Delivery Date Row ')]");
        List<WebElement> Mrp = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'MRP Row ')]");
        List<WebElement> minimumRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Minimum Rate * Row ')]");
        List<WebElement> maximunRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Maximum Rate * Row ')]");
        List<WebElement> UnitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Unit Rate Row ')]");
        List<WebElement> voucherDiscount = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Voucher Disc % Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        List<WebElement> partyDiscount = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Party Disc % Row ')]");

        if (!discountIsClicked) {
            common.clickElement("xpath", "//Header[@Name='Disc Amount 1']");
            common.clickElement("xpath", "//Header[@Name='Disc Amount 2']");
            common.clickElement("xpath", "//Header[@Name='Disc Amount 3']");
            discountIsClicked = true;
        }
        List<WebElement> DiscountBasis1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Basis 1 Row ')]");
        List<WebElement> Discount1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc 1 Row ')]");
        List<WebElement> DiscountBasis2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Basis 2 Row ')]");
        List<WebElement> Discount2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc 2 Row')]");
        List<WebElement> DiscountBasis3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Basis 3 Row ')]");
        List<WebElement> Discount3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc 3 Row ')]");
        List<WebElement> HSNCode = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'HSN Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        List<WebElement> GSTProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'GST Product Category Row ')]");
        List<WebElement> CESSProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'CESS Product Category Row ')]");
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        List<WebElement> Info1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 1 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        List<WebElement> Info2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 2 Row ')]");
        List<WebElement> Info3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 3 Row ')]");
        List<WebElement> Info4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 4 Row ')]");
        List<WebElement> Info5 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 5 Row ')]");
        List<WebElement> Value1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 1 Row ')]");
        List<WebElement> Value2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 2 Row ')]");
        List<WebElement> Value3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 3 Row ')]");
        List<WebElement> Value4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 4 Row ')]");
        List<WebElement> Value5 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 5 Row ')]");
        List<WebElement> Date1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date 1 Row ')]");
        List<WebElement> Date2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date 2 Row ')]");
        List<WebElement> Date3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date 3 Row ')]");
        List<WebElement> Bool1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 1 Row ')]");
        List<WebElement> Bool2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 2 Row ')]");
        List<WebElement> Bool3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1900, 0);

        for (int i = 0; i < productCode.size(); i++) {
            enterListData(uom.get(i), dataFile, "Items", "UOM", i);
            enterListData(quantity.get(i), dataFile, "Items", "Quantity", i);
            enterListDate(DeliveryDate.get(i), dataFile, "Items", "DeliveryDate", i);
            enterListData(Mrp.get(i), dataFile, "Items", "MRP", i);
            enterListData(minimumRate.get(i),dataFile,"Items","MinimumRate",i );
            enterListData(maximunRate.get(i),dataFile,"Items","MaximumRate",i );
            enterListData(UnitRate.get(i), dataFile, "Items", "UnitRate", i);
            enterListData(voucherDiscount.get(i), dataFile, "Items", "VoucherDiscountPercentage", i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 250, 0);
            enterListData(partyDiscount.get(i),dataFile,"Items","PartyDiscountPercentage",i );
            enterListData(DiscountBasis1.get(i), dataFile, "Items", "DiscountBasis1", i);
            enterListData(Discount1.get(i), dataFile, "Items", "Discount1", i);
            enterListData(DiscountBasis2.get(i), dataFile, "Items", "DiscountBasis2", i);
            enterListData(Discount2.get(i), dataFile, "Items", "Discount2", i);
            enterListData(DiscountBasis3.get(i), dataFile, "Items", "DiscountBasis3", i);
            enterListData(Discount3.get(i), dataFile, "Items", "Discount3", i);
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
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1550, 0);
        }
    }

    public void addChargesAndDeductions() throws IOException {
        navigateToChargesAndDeductionsTab();
        List<String> chargesAndDeductions = readExcelData(dataFile, "ChargesAndDeductions", "ChargesOrDeductions");
//        System.out.println("productCodes :"+chargesAndDeductions.size());
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
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < chargesAndDeductions.size(); i++) {
            enterListData(accCodeRowList.get(i), dataFile, "ChargesAndDeductions", "AccountCode", i);
            enterListData(basisRowList.get(i), dataFile, "ChargesAndDeductions", "Basis", i);
            enterListData(percentageRowList.get(i), dataFile, "ChargesAndDeductions", "Percentage", i);
            enterListData(departmentRowList.get(i), dataFile, "ChargesAndDeductions", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "ChargesAndDeductions", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "ChargesAndDeductions", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "ChargesAndDeductions", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "ChargesAndDeductions", "Comments", i);
        }
    }

    public void addOtherCharges() throws IOException, InterruptedException {
        navigateToOtherChargesTab();
        List<String> otherCharges = readExcelData(dataFile, "OtherCharges", "AccountCode");
        for (int i = 0; i < otherCharges.size(); i++) {
            addData("xpath", "//Edit[@Name='Account Code Row " + i + ", Not sorted.']", dataFile, "OtherCharges", "AccountCode", i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> GSTProductCategory = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'GST Product Category Row ')]");
        List<WebElement> CESSProductCategory = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'CESS Product Category Row ')]");
        if (!otherChargesGSTCheckBox) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            otherChargesGSTCheckBox = true;
        }
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < otherCharges.size(); i++) {
            enterListData(amountRowList.get(i), dataFile, "OtherCharges", "Amount", i);
            enterListData(hsnCodeRowList.get(i), dataFile, "OtherCharges", "HSN", i);
            enterListData(GSTProductCategory.get(i), dataFile, "OtherCharges", "GSTProductCategory", i);
            enterListData(CESSProductCategory.get(i), dataFile, "OtherCharges", "CESSProductCategory", i);
            Thread.sleep(1000);
            enterListData(departmentRowList.get(i), dataFile, "OtherCharges", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "OtherCharges", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "OtherCharges", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "OtherCharges", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "OtherCharges", "Comments", i);
            common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", -550, 0);
        }
    }

    public void addCashSalesOrder() throws IOException {
        navigateToCashTab();
        List<String> cashTab = readExcelData(dataFile, "Cash", "CashAccountCode");
        for (int i = 0; i < cashTab.size(); i++) {
            addData("xpath", "//Edit[@Name='Cash Account Code Row " + i + ", Not sorted.']", dataFile, "Cash", "CashAccountCode", i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < cashTab.size(); i++) {
            enterListData(amountRowList.get(i), dataFile, "Cash", "Amount", i);
            enterListData(departmentRowList.get(i), dataFile, "Cash", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "Cash", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "Cash", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "Cash", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "Cash", "Comments", i);
        }
    }

    public void addCheques() throws IOException {
        List<WebElement> elements = common.findWebElements("xpath", "//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(0).getText());
        elements.get(0).click();
        List<String> chequesTab = readExcelData(dataFile, "Cheques", "BankAccountCode");
        for (int i = 0; i < chequesTab.size(); i++) {
            addData("xpath", "//Edit[@Name='Bank Account Code Row " + i + ", Not sorted.']", dataFile, "Cheques", "BankAccountCode", i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> chargesAccRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Account Code Row ')]");
        List<WebElement> chargesAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < chequesTab.size(); i++) {
            enterListData(amountRowList.get(i), dataFile, "Cheques", "Amount", i);
            enterListData(chequeNo.get(i), dataFile, "Cheques", "ChequeNo", i);
            enterListDate(chequeDate.get(i), dataFile, "Cheques", "ChequeDate", i);
            enterListData(drawnOnRowList.get(i), dataFile, "Cheques", "DrawnOnBankAccount", i);
            enterListData(drawnOnBranchBranchRowList.get(i), dataFile, "Cheques", "DrawnOnBankBranch", i);
            enterListData(chargesAccRowList.get(i), dataFile, "Cheques", "ChargesAccount", i);
            enterListData(chargesAmountRowList.get(i), dataFile, "Cheques", "Charges", i);
            enterListData(departmentRowList.get(i), dataFile, "Cheques", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "Cheques", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "Cheques", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "Cheques", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "Cheques", "Comments", i);
        }
    }

    public void addPostDatedCheques() throws IOException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Post Dated Cheques')]");
        List<String> postDatedCheques = readExcelData(dataFile, "PostDatedCheques", "PDCAccountCode");
        for (int i = 0; i < postDatedCheques.size(); i++) {
            addData("xpath", "//Edit[@Name='PDC Account Code Row " + i + ", Not sorted.']", dataFile, "PostDatedCheques", "PDCAccountCode", i);
        }
        List<WebElement> amoutRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < postDatedCheques.size(); i++) {
            enterListData(amoutRowList.get(i), dataFile, "PostDatedCheques", "Amount", i);
            enterListData(chequeNo.get(i), dataFile, "PostDatedCheques", "ChequeNo", i);
            enterListDate(chequeDate.get(i), dataFile, "PostDatedCheques", "ChequeDate", i);
            enterListData(drawnOnRowList.get(i), dataFile, "PostDatedCheques", "DrawnOnBankAccount", i);
            enterListData(drawnOnBranchBranchRowList.get(i), dataFile, "PostDatedCheques", "DrawnOnBankBranch", i);
            enterListData(departmentRowList.get(i), dataFile, "PostDatedCheques", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "PostDatedCheques", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "PostDatedCheques", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "PostDatedCheques", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "PostDatedCheques", "Comments", i);
        }
    }

    public void addChequesPDC() throws IOException {
        List<WebElement> elements = common.findWebElements("xpath", "//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(2).getText());
        elements.get(2).click();
        List<String> chequesPDC = readExcelData(dataFile, "PDC", "BankAccountCode");
        for (int i = 0; i < chequesPDC.size(); i++) {
            addData("xpath", "//Edit[@Name='Bank Account Code Row " + i + ", Not sorted.']", dataFile, "PDC", "BankAccountCode", i);
        }
        List<WebElement> chequeAmountRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < chequesPDC.size(); i++) {
            enterListData(chequeAmountRowList.get(i), dataFile, "PDC", "Amount", i);
            enterListData(chequeNo.get(i), dataFile, "PDC", "ChequeNo", i);
            enterListDate(chequeDate.get(i), dataFile, "PDC", "ChequeDate", i);
            enterListData(drawnOnRowList.get(i), dataFile, "PDC", "DrawnOnBankAccount", i);
            enterListData(drawnOnBranchBranchRowList.get(i), dataFile, "PDC", "DrawnOnBankBranch", i);
            enterListData(departmentRowList.get(i), dataFile, "PDC", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "PDC", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "PDC", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "PDC", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "PDC", "Comments", i);
        }
    }

    public void addCreditCard() throws IOException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Credit Card')]");
        List<String> creditCards = readExcelData(dataFile, "CreditCard", "SwipeMachineType");
        for (int i = 0; i < creditCards.size(); i++) {
            addData("xpath", "//Edit[@Name='Swipe Machine Type * Row " + i + ", Not sorted.']", dataFile, "CreditCard", "SwipeMachineType", i);
        }
        List<WebElement> swipeTypeRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Swipe Type * Row ')]");
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> cardNo = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Card No Row ')]");
        List<WebElement> expiryDate = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Expiry Date Row ')]");
        List<WebElement> approvalNo = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Approval No * Row ')]");
        List<WebElement> executiveRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < creditCards.size(); i++) {
            enterListData(swipeTypeRowList.get(i), dataFile, "CreditCard", "SwipeType", i);
            enterListData(amountRowList.get(i), dataFile, "CreditCard", "Amount", i);
            enterListData(cardNo.get(i), dataFile, "CreditCard", "CardNo", i);
            enterListDate(expiryDate.get(i), dataFile, "CreditCard", "ExpiryDate", i);
            enterListData(approvalNo.get(i), dataFile, "CreditCard", "ApprovalNo", i);
            enterListData(executiveRowList.get(i), dataFile, "CreditCard", "Executive", i);
            enterListData(departmentRowList.get(i), dataFile, "CreditCard", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "CreditCard", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "CreditCard", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "CreditCard", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "CreditCard", "Comments", i);
        }
    }

    public void addPayTm() throws IOException {
        navigateToPaytymTab();
        List<String> creditCards = readExcelData(dataFile, "Paytm", "SwipeMachineType");
        for (int i = 0; i < creditCards.size(); i++) {
            addData("xpath", "//Edit[@Name='Swipe Machine Type Row " + i + ", Not sorted.']", dataFile, "Paytm", "SwipeMachineType", i);
        }
        List<WebElement> swipeTypeRowList = common.findWebElements("xpath", "//Table[@Name='Paytm']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Swipe Type Row ')]");
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Paytm']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> approvalNo = common.findWebElements("xpath", "//Table[@Name='Paytm']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Approval No * Row ')]");
        for (int i = 0; i < creditCards.size(); i++) {
            enterListData(swipeTypeRowList.get(i), dataFile, "Paytm", "SwipeType", i);
            enterListData(amountRowList.get(i), dataFile, "Paytm", "Amount", i);
            enterListData(approvalNo.get(i), dataFile, "Paytm", "ApprovalNo", i);

        }

    }

    public void addPineLab() throws IOException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Pine Lab')]");
        List<String> pineLab = readExcelData(dataFile, "PineLab", "SwipeMachineType");
        for (int i = 0; i < pineLab.size(); i++) {
            addData("xpath", "//Edit[@Name='Swipe Machine Type Row " + i + ", Not sorted.']", dataFile, "PineLab", "SwipeMachineType", i);
        }
        List<WebElement> swipeTypeRowList = common.findWebElements("xpath", "//Table[@Name='PineLab']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Swipe Type Row ')]");
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='PineLab']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> approvalNo = common.findWebElements("xpath", "//Table[@Name='PineLab']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Approval No * Row ')]");
        List<WebElement> cardNumber = common.findWebElements("xpath", "//Table[@Name='PineLab']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Card Number Row ')]");
        for (int i = 0; i < pineLab.size(); i++) {
            enterListData(swipeTypeRowList.get(i), dataFile, "PineLab", "SwipeType", i);
            enterListData(amountRowList.get(i), dataFile, "PineLab", "Amount", i);
            enterListData(approvalNo.get(i), dataFile, "PineLab", "ApprovalCode", i);
            enterListData(cardNumber.get(i), dataFile, "PineLab", "CardNumber", i);
        }
    }

    public void otherInfo() throws InterruptedException, IOException {
        navigateToOtherInfoTab();
        EnterData("//Edit[@Name='Reference Bill No']", dataFile, "OtherInfo", "ReferenceBillNo");
        Thread.sleep(5000);
        common.clickElement("xpath", "//Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']", dataFile, "OtherInfo", "ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']", dataFile, "OtherInfo", "OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']", dataFile, "OtherInfo", "OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']", dataFile, "OtherInfo", "OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']", dataFile, "OtherInfo", "OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']", dataFile, "OtherInfo", "OtherInfo5");
    }

    public void additionalInformation() throws IOException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Additional Information  ')]");
        EnterData("//Edit[@Name='Info 1']", dataFile, "AdditionalInformation", "Info1");
        EnterData("//Edit[@Name='Info 2']", dataFile, "AdditionalInformation", "Info2");
        EnterData("//Edit[@Name='Info 3']", dataFile, "AdditionalInformation", "Info3");
        EnterData("//Edit[@Name='Info 4']", dataFile, "AdditionalInformation", "Info4");
        EnterData("//Edit[@Name='Info 5']", dataFile, "AdditionalInformation", "Info5");
        EnterData("//Edit[@Name='Value 1']", dataFile, "AdditionalInformation", "Value1");
        EnterData("//Edit[@Name='Value 2']", dataFile, "AdditionalInformation", "Value2");
        EnterData("//Edit[@Name='Value 3']", dataFile, "AdditionalInformation", "Value3");
        EnterData("//Edit[@Name='Value 4']", dataFile, "AdditionalInformation", "Value4");
        EnterData("//Edit[@Name='Value 5']", dataFile, "AdditionalInformation", "Value5");
        EnterDate("//Edit[@Name='Date 1']", dataFile, "AdditionalInformation", "Date1");
        EnterDate("//Edit[@Name='Date 2']", dataFile, "AdditionalInformation", "Date2");
        EnterDate("//Edit[@Name='Date 3']", dataFile, "AdditionalInformation", "Date3");
        common.clickElement("xpath", "//CheckBox[@Name='Bool 1']");
        common.clickElement("xpath", "//CheckBox[@Name='Bool 2']");
        common.clickElement("xpath", "//CheckBox[@Name='Bool 3']");
    }

    public void shippingAddress(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Shipping Address  ')]");
        EnterData("//Edit[@Name='Party Name']",dataFile,"ShippingAddress","PartyAccount");
        EnterData("//Edit[@Name='GSTIN']",dataFile,"ShippingAddress","GSTIN");
        EnterData("//Edit[@Name='Address 1 *']",dataFile,"ShippingAddress","Address1");
        EnterData("//Edit[@Name='Address 2']",dataFile,"ShippingAddress","Address2");
        EnterData("//Edit[@Name='Address 3']",dataFile,"ShippingAddress","Address3");
        EnterData("//Edit[@Name='City *']",dataFile,"ShippingAddress","City");
        EnterData("//Edit[@Name='State *']",dataFile,"ShippingAddress","State");
        EnterData("//Edit[@Name='State Code *']",dataFile,"ShippingAddress","StateCode");
        EnterData("//Edit[@Name='Country *']",dataFile,"ShippingAddress","Country");
        EnterData("//Edit[@Name='Zip *']",dataFile,"ShippingAddress","Zip");
        EnterData("//Edit[@Name='Telephone No']",dataFile,"ShippingAddress","TelephoneNo");
        EnterData("//Edit[@Name='Mobile No']",dataFile,"ShippingAddress","MobileNo");

    }

    public void termsAndCondition() throws IOException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Terms And Conditions')]");
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
        common.clickElement("xpath", "//TabItem[contains(@Name,'Allocations')]");
        EnterData("//Edit[@Name='Department']", dataFile, "Allocations", "Department");
        EnterData("//Edit[@Name='Project']", dataFile, "Allocations", "Project");
        EnterData("//Edit[@Name='Profit Centre']", dataFile, "Allocations", "ProfitCentre");
        EnterData("//Edit[@Name='Cost Centre']", dataFile, "Allocations", "CostCentre");
    }
}