package tradeTesting.sales.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class SalesInvoiceAgainstOrderTrade extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false,IsAmountHeaderClicked=false,infoHeaderclicked=false,discountIsClicked=false;

    public SalesInvoiceAgainstOrderTrade(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String invoiceAgainstOrdersTrade(String voucherNum,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long start = System.nanoTime();
        System.out.println("SIAO startTime executed in :"+start);
        Thread.sleep(100);
        navigateToMastersWhen3Steps("Sales","Invoices","Sales Invoice against Orders");
        long generalInfoStart = System.nanoTime();
        Thread.sleep(5000);

        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        //branch selection
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterLocation(dataFile,"GeneralInformation","Location");
        EnterData("//Edit[@Name='Storage Bin']", dataFile, "GeneralInformation", "StorageBin");
        EnterData("//Edit[@Name='Route *']", dataFile, "GeneralInformation", "Route");
        EnterData("//Edit[@Name='Party Account *']", dataFile, "GeneralInformation", "PartyAccount");
        Thread.sleep(2500);
        gstTransactionType("Inter State Sales to Registered Dealers");
        Thread.sleep(5000);
        selectPendingsSalesOrder(voucherNum, "20250401");
        Thread.sleep(1000);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1000);
        EnterData("//Edit[@Name='Division']", dataFile, "GeneralInformation", "Division");
        EnterData("//Edit[@Name='Sales Executive *']", dataFile, "GeneralInformation", "SalesExecutive");
        enterCreditPeriod(dataFile, "GeneralInformation", "CreditPeriod");
        EnterData("//Edit[@Name='Price List']", dataFile, "GeneralInformation", "PriceList");
        EnterData("//Edit[@Name='Price Type']", dataFile, "GeneralInformation", "PriceType");
        EnterData("//Edit[@Name='Sales Acc Code']", dataFile, "GeneralInformation", "SalesAccountCode");
        generalInfoSliderHandle(300);
        EnterData("//Edit[@Name='Cash Account']", dataFile, "GeneralInformation", "CashAccount");
        EnterData("//Edit[@Name='Cash Discount Basis']", dataFile, "GeneralInformation", "CashDiscountBasis");
        EnterData("//Edit[@Name='Cash Disc %']", dataFile, "GeneralInformation", "CashDiscount");
//        common.clickElement("xpath", "//CheckBox[@Name='Apply Simple Scheme']");
        common.clickElement("xpath", "//CheckBox[@Name='Apply Combo Scheme']");
        common.clickElement("xpath", "//CheckBox[@Name='CalculateTCS']");
        EnterData("//Edit[@Name='TCS Transaction Nature']", dataFile, "GeneralInformation", "TCSTransactionNature");
        generalInfoSliderHandle(300);
        enterDropDownData("//Edit[@Name='Sub Type']","Supply");
        Thread.sleep(1000);
        enterDropDownData("//Edit[@Name='SupplyT Ype']","Outward");
        Thread.sleep(1000);
        enterDropDownData("//Edit[@Name='DoucmentT Ype']","Tax Invoice");
        Thread.sleep(1000);
        enterDropDownData("//Edit[@Name='TransactionType']","Regular");
        Thread.sleep(1000);
        enterDropDownData("//Edit[@Name='Invoice Type']","Regular");
        Thread.sleep(1000);
        enterRemarks(dataFile, "GeneralInformation", "remarks");
        generalInfoSliderHandle(-700);
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Orders General information End:- ", generalInfoEndTime);

        //select pending quantity
        long addProductStart=System.nanoTime();
        addProduct();
        long addProductEnd=System.nanoTime()-addProductStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Orders Add Products:- ",addProductEnd);
        //charges and deductions
        long chargesDeductionsStart =System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  ChargesAndDeductions  ')]");
        addChargesAndDeductions();
        long chargesDeductionsEnd=System.nanoTime()- chargesDeductionsStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Orders Charges and Deductions:- ",chargesDeductionsEnd);
        //other charges
        long otherChargesStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  TaxableOtherCharges  ')]");
        addTaxableOtherCharges();
        long otherChargesEnd = System.nanoTime() - otherChargesStart;
        FileUtil.writeTimeLogInMinutes("SI Taxable Other Charges:- ", otherChargesEnd);
        //cash
        long cashTabStart =System.nanoTime();
        navigateToCashTab();
        addCash();
        long cashTabEnd =System.nanoTime()- cashTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Orders Cash Tab:- ", cashTabEnd);
        //cheques
        long chequesTabStart =System.nanoTime();
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(0).getText());
        elements.get(0).click();
        addCheques();
        long chequesTabEnd =System.nanoTime()- chequesTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Orders Cheques Tab:- ", chequesTabEnd);
        //post dated cheques
        long postDatedChequesTabStart =System.nanoTime();
        common.clickElement("xpath","//TabItem[contains(@Name,'  PostDatedCheques  ')]");
        addPostDatedCheques();
        long postDatedChequesTabEnd =System.nanoTime()- postDatedChequesTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Orders Post Dated Cheques Tab:- ", postDatedChequesTabEnd);
        //cheques[pdc]
        long chequesPDCTabStart =System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  PDC  ')]");
        addChequesPDC();
        long chequesPDCTabEnd =System.nanoTime()- chequesPDCTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Orders Cheques[PDC] Tab:- ", chequesPDCTabEnd);
        //credit card
        long creditCardTabStart =System.nanoTime();
        common.clickElement("xpath","//TabItem[contains(@Name,'  CreditCardReceipts  ')]");
        addCreditCard();
        long creditCardTabEnd =System.nanoTime()- creditCardTabStart;
        scrollRight(6);
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Orders Credit Card Tab:- ", creditCardTabEnd);
        //Other info
        long otherInfoTabStart =System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  OtherInfo  ')]");
        otherInfo();
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Orders Other Info Tab:- ", otherInfoTabEnd);
        //material despatch
        long materialDispatchAddress = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  MaterialDespatchAddress  ')]");
        materialDispatchAddress();
        long materialDispatchAddressEnd = System.nanoTime() - materialDispatchAddress;
        FileUtil.writeTimeLogInMinutes("Sales Invoice Against Orders Material Dispatch address:- ", materialDispatchAddressEnd);
        //shipping Address
        long shippingAddressTabStart=System.nanoTime();
        common.clickElement("xpath","//TabItem[contains(@Name,'  ShippingAddress  ')]");
        shippingAddress();
        long shippingAddressTabEnd=System.nanoTime()-shippingAddressTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice Against Orders Shipping Address Tab: ", shippingAddressTabEnd);
        //terms and Cond
        long termsConditionsTabStart =System.nanoTime();
        common.clickElement("xpath","//TabItem[contains(@Name,'  TermsAndConditions  ')]");
        termsAndCondition();
        long termsConditionsTabEnd =System.nanoTime()- termsConditionsTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Orders Terms and Conditions Tab:- ", termsConditionsTabEnd);
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //api
        long invoiceAgainstOrdersEnd = System.nanoTime() - start;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Orders transaction Ended at:- ", invoiceAgainstOrdersEnd);
        //        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"salesInvoiceAgainstOrder");

        deleteTransactionUsingVoucherNumber(newVoucherID);

        return newVoucherID;
    }

    public void addProduct() throws Exception {
        List<String> productCode = readExcelData(dataFile, "Items", "Product");
        System.out.println("productCodes :" + productCode.size());
        List<WebElement> productBatch = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product Batch * Row ')]");
        List<WebElement> salesAccount = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Sales Account * Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        List<WebElement> orderRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Order Rate Row ')]");
        List<WebElement> copyOrderInSalesUnit = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Copy Ord Qty In Sale Unit Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 400, 0);
        List<WebElement> orderBaseUnitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Order Base Unit Rate Row ')]");
        List<WebElement> copyOrderInBaseUnit = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Copy Ord Qty In Base Unit Row ')]");
        List<WebElement> freeQuantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Free Qty Row ')]");
        List<WebElement> freeQuantityInBaseUnit = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Free Qty In Base Unit Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 400, 0);
        if (!discountIsClicked) {
            common.clickElement("xpath", "//Header[@Name='Discount Amount1']");
            common.clickElement("xpath", "//Header[@Name='Discount Amount2']");
            common.clickElement("xpath", "//Header[@Name='Discount Amount3']");
            discountIsClicked = true;
        }
        List<WebElement> discountAccount1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Account1 Row ')]");
        List<WebElement> discountBasis1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Basis1 Row ')]");
        List<WebElement> discount1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount1 Row ')]");
        List<WebElement> discountAccount2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Account2 Row ')]");
        List<WebElement> discountBasis2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Basis2 Row ')]");
        List<WebElement> discount2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount2 Row ')]");
        List<WebElement> discountAccount3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Account3 Row ')]");
        List<WebElement> discountBasis3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount Basis3 Row ')]");
        List<WebElement> discount3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Discount3 Row ')]");
//        List<WebElement>  cashDiscount = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cash Disc % Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 450, 0);
        List<WebElement> HSNCode = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'HSN Row ')]");
        List<WebElement> GSTProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'GST Product Category Row ')]");
        List<WebElement> CESSProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'CESS Product Category Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 650, 0);
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Executive Row ')]");
        List<WebElement> division = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Division * Row ')]");
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        if (!infoHeaderclicked) {
            common.clickElement("xpath", "//Header[@Name='Info5']");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
            common.clickElement("xpath", "//Header[@Name='Value5']");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
            common.clickElement("xpath", "//Header[@Name='Date3']");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
            common.clickElement("xpath", "//Header[@Name='Bool3']");
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
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 30, 0);
        List<WebElement> Bool1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool1 Row ')]");
        List<WebElement> Bool2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool2 Row ')]");
        List<WebElement> Bool3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -2200, 0);

        for (int i = 0; i <productCode.size() ; i++) {
            enterListData(productBatch.get(i), dataFile, "Items", "ProductBatchId", i);
            enterListData(salesAccount.get(i), dataFile, "Items", "SalesAccount", i);
            enterListData(storageBin.get(i), dataFile, "Items", "StorageBin", i);
            enterListData(orderRate.get(i), dataFile, "Items", "OrderRate", i);
            clickListData(copyOrderInSalesUnit.get(i));
            enterListData(orderBaseUnitRate.get(i), dataFile, "Items", "OrderBaseUnitRate", i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 300, 0);
            clickListData(copyOrderInBaseUnit.get(i));
            enterListData(freeQuantity.get(i), dataFile, "Items", "FreeQty", i);
            enterListData(freeQuantityInBaseUnit.get(i), dataFile, "Items", "FreeQtyInBaseUnit", i);
            if(i==0 || i==1 || i==2){
                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -100, 0);
                enterListData(discountAccount1.get(i), dataFile, "Items", "DiscountAccount1", i);
            }
            else {
                enterListData(discountAccount1.get(i), dataFile, "Items", "DiscountAccount1", i);
            }
            enterListData(discountBasis1.get(i), dataFile, "Items", "DiscountBasis1", i);
            enterListData(discount1.get(i), dataFile, "Items", "Discount1", i);
            enterListData(discountAccount2.get(i), dataFile, "Items", "DiscountAccount2", i);
            enterListData(discountBasis2.get(i), dataFile, "Items", "DiscountBasis2", i);
            enterListData(discount2.get(i), dataFile, "Items", "Discount2", i);
            enterListData(discountAccount3.get(i), dataFile, "Items", "DiscountAccount3", i);
            enterListData(discountBasis3.get(i), dataFile, "Items", "DiscountBasis3", i);
            enterListData(discount3.get(i), dataFile, "Items", "Discount3", i);
//            enterListData(cashDiscount.get(i), dataFile, "Items", "CashDiscountPercentage", i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 100, 0);
            enterListData(HSNCode.get(i),dataFile,"Items","HSN",i);
            enterListData(GSTProductCategory.get(i),dataFile,"Items","GSTProductCategory",i);
            enterListData(CESSProductCategory.get(i),dataFile,"Items","CESSProductCategory",i);
            enterListData(executive.get(i), dataFile, "Items", "StorageBin", i);
            enterListData(division.get(i), dataFile, "Items", "Division", i);
            enterListData(Department.get(i),dataFile,"Items","Department",i);
            enterListData(Project.get(i),dataFile,"Items","Project",i);
            enterListData(ProfitCentre.get(i),dataFile,"Items","ProfitCentre",i);
            enterListData(CostCentre.get(i),dataFile,"Items","CostCentre",i);
            enterListData(Comments.get(i),dataFile,"Items","Comments",i);
            enterListData(Info1.get(i),dataFile,"Items","Info1",i);
            enterListData(Info2.get(i),dataFile,"Items","Info2",i);
            enterListData(Info3.get(i),dataFile,"Items","Info3",i);
            enterListData(Info4.get(i),dataFile,"Items","Info4",i);
            enterListData(Info5.get(i),dataFile,"Items","Info5",i);
            enterListData(Value1.get(i),dataFile,"Items","Value1",i);
            enterListData(Value2.get(i),dataFile,"Items","Value2",i);
            enterListData(Value3.get(i),dataFile,"Items","Value3",i);
            enterListData(Value4.get(i),dataFile,"Items","Value4",i);
            enterListData(Value5.get(i),dataFile,"Items","Value5",i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 50, 0);
            enterListDate(Date1.get(i),dataFile,"Items","Date1",i);
            enterListDate(Date2.get(i),dataFile,"Items","Date2",i);
            enterListDate(Date3.get(i),dataFile,"Items","Date3",i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 30, 0);
            clickListData(Bool1.get(i));
            clickListData(Bool2.get(i));
            clickListData(Bool3.get(i));
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1500, 0);
        }
    }

    public void addChargesAndDeductions() throws IOException {
        List<String> chargesAndDeductions=readExcelData(dataFile,"ChargesAndDeductions","ChargesOrDeductions");
//        System.out.println("productCodes :"+chargesAndDeductions.size());
        for (int i = 0; i < chargesAndDeductions.size() ; i++) {
            addData("xpath","//Edit[@Name='Charges Or Deductions * Row "+i+", Not sorted.']",dataFile,"ChargesAndDeductions","ChargesOrDeductions",i);
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
            enterListData(accCodeRowList.get(i), dataFile, "ChargesAndDeductions", "AccountCode" , i);
            enterListData(basisRowList.get(i), dataFile, "ChargesAndDeductions", "Basis" , i);
            enterListData(percentageRowList.get(i), dataFile, "ChargesAndDeductions", "Percentage" , i);
            enterListData(departmentRowList.get(i), dataFile, "ChargesAndDeductions", "Department" , i);
            enterListData(projectRowList.get(i), dataFile, "ChargesAndDeductions", "Project" , i);
            enterListData(profitCentreRowList.get(i), dataFile, "ChargesAndDeductions", "ProfitCentre" , i);
            enterListData(costCentreRowList.get(i), dataFile, "ChargesAndDeductions", "CostCentre" , i);
            enterListData(commentsRowList.get(i), dataFile, "ChargesAndDeductions", "Comments" , i);
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
        List<String> cashTab =readExcelData(dataFile,"Cash","CashAccountCode");
        for (int i = 0; i < cashTab.size() ; i++) {
            addData("xpath","//Edit[@Name='Cash Account Code Row "+i+", Not sorted.']",dataFile,"Cash","CashAccountCode",i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount Row ')]");
        List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < cashTab.size() ; i++) {
            enterListData(amountRowList.get(i), dataFile, "Cash", "Amount",i);
            enterListData(tdsTransNatureRowList.get(i), dataFile, "Cash", "TDSTransactionNature",i);
            enterListData(tdsAccountRowList.get(i), dataFile, "Cash", "TDSAccount",i);
            enterListData(tdsAmountRowList.get(i), dataFile, "Cash", "TDSAmount",i);
            enterListData(departmentRowList.get(i),dataFile,"Cash","Department",i);
            enterListData(projectRowList.get(i),dataFile,"Cash","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"Cash","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"Cash","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"Cash","Comments",i);
        }
    }

    public void addCheques() throws IOException {
        List<String> chequesTab=readExcelData(dataFile,"Cheques","BankAccountCode");
        for (int i = 0; i < chequesTab.size(); i++) {
            addData("xpath","//Edit[@Name='Bank Account Code Row "+i+", Not sorted.']",dataFile,"Cheques","BankAccountCode",i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> chargesAccRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Account Code Row ')]");
        List<WebElement> chargesAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < chequesTab.size() ; i++) {
            enterListData(amountRowList.get(i), dataFile, "Cheques", "Amount" ,i);
            enterListData(chequeNo.get(i), dataFile, "Cheques", "ChequeNo" ,i);
            enterListDate(chequeDate.get(i),dataFile,"Cheques","ChequeDate",i);
            enterListData(drawnOnRowList.get(i), dataFile, "Cheques", "DrawnOnBankAccount",i);
            enterListData(drawnOnBranchBranchRowList.get(i), dataFile, "Cheques", "DrawnOnBankBranch",i);
            enterListData(tdsTransNatureRowList.get(i), dataFile, "Cheques", "TDSTransactionNature",i);
            enterListData(tdsAccountRowList.get(i), dataFile, "Cheques", "TDSAccount",i);
            enterListData(tdsAmountRowList.get(i), dataFile, "Cheques", "TDSAmount",i);
            enterListData(chargesAccRowList.get(i), dataFile, "Cheques", "ChargesAccount" , i);
            enterListData(chargesAmountRowList.get(i), dataFile, "Cheques", "Charges" , i);
            enterListData(departmentRowList.get(i),dataFile,"Cheques","Department",i);
            enterListData(projectRowList.get(i),dataFile,"Cheques","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"Cheques","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"Cheques","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"Cheques","Comments",i);
            common.sliderHandling("xpath", "//Table[@Name='Cheques']/*/Thumb[@Name='Position']", -600, 0);

        }
    }

    public void addPostDatedCheques() throws IOException {
        List<String> postDatedCheques=readExcelData(dataFile,"PostDatedCheques","PDCAccountCode");
        for (int i = 0; i < postDatedCheques.size(); i++) {
            addData("xpath","//Edit[@Name='PDC Account Code Row "+i+", Not sorted.']",dataFile,"PostDatedCheques","PDCAccountCode",i);
        }
        List<WebElement> amoutRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < postDatedCheques.size() ; i++) {
            enterListData(amoutRowList.get(i), dataFile, "PostDatedCheques", "Amount" ,i);
            enterListData(chequeNo.get(i), dataFile, "PostDatedCheques", "ChequeNo" ,i);
            enterListDate(chequeDate.get(i),dataFile,"PostDatedCheques","ChequeDate",i);
            enterListData(drawnOnRowList.get(i), dataFile, "PostDatedCheques", "DrawnOnBankAccount",i);
            enterListData(drawnOnBranchBranchRowList.get(i), dataFile, "PostDatedCheques", "DrawnOnBankBranch",i);
            enterListData(tdsTransNatureRowList.get(i), dataFile, "PostDatedCheques", "TDSTransactionNature",i);
            enterListData(tdsAccountRowList.get(i), dataFile, "PostDatedCheques", "TDSAccount",i);
            enterListData(tdsAmountRowList.get(i), dataFile, "PostDatedCheques", "TDSAmount",i);
            enterListData(departmentRowList.get(i),dataFile,"PostDatedCheques","Department",i);
            enterListData(projectRowList.get(i),dataFile,"PostDatedCheques","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"PostDatedCheques","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"PostDatedCheques","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"PostDatedCheques","Comments",i);
        }
    }

    public void addChequesPDC() throws IOException {
        List<String> chequesPDC =readExcelData(dataFile,"PDC","BankAccountCode");
        for (int i = 0; i < chequesPDC.size(); i++) {
            addData("xpath","//Edit[@Name='Bank Account Code Row "+i+", Not sorted.']",dataFile,"PDC","BankAccountCode",i);
        }
        List<WebElement> chequeAmountRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < chequesPDC.size() ; i++) {
            enterListData(chequeAmountRowList.get(i), dataFile, "PDC", "Amount",i);
            enterListData(chequeNo.get(i), dataFile, "PDC", "ChequeNo" ,i);
            enterListDate(chequeDate.get(i), dataFile, "PDC", "ChequeDate" ,i);
            enterListData(drawnOnRowList.get(i), dataFile, "PDC", "DrawnOnBankAccount",i);
            enterListData(drawnOnBranchBranchRowList.get(i), dataFile, "PDC", "DrawnOnBankBranch",i);
            enterListData(tdsTransNatureRowList.get(i), dataFile, "PDC", "TDSTransactionNature",i);
            enterListData(tdsAccountRowList.get(i), dataFile, "PDC", "TDSAccount",i);
            enterListData(tdsAmountRowList.get(i), dataFile, "PDC", "TDSAmount",i);
            enterListData(departmentRowList.get(i),dataFile,"PDC","Department",i);
            enterListData(projectRowList.get(i),dataFile,"PDC","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"PDC","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"PDC","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"PDC","Comments",i);
        }
    }

    public void addCreditCard() throws IOException {
        List<String> creditCards =readExcelData(dataFile,"CreditCardReceipts","SwipeMachineType");
        for (int i = 0; i < creditCards.size(); i++) {
            addData("xpath","//Edit[@Name='Swipe Machine Type * Row "+i+", Not sorted.']",dataFile,"CreditCardReceipts","SwipeMachineType",i);
        }
        List<WebElement> swipeTypeRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Swipe Type * Row ')]");
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> cardNo = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Card No Row ')]");
        List<WebElement> expiryDate = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Expiry Date Row ')]");
        List<WebElement> approvalNo = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Approval No * Row ')]");
        List<WebElement> chargesAccRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Account Code Row ')]");
        List<WebElement> chargesPercentageRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Percentage Row ')]");
        List<WebElement> executiveRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='CreditCardReceipts']/*/Thumb[@Name='Position']", 500, 0);
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='CreditCardReceipts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='CreditCardReceipts']/*/Thumb[@Name='Position']", -500, 0);
        for (int i = 0; i < creditCards.size() ; i++) {
            enterListData(swipeTypeRowList.get(i), dataFile, "CreditCardReceipts", "SwipeType",i);
            enterListData(amountRowList.get(i), dataFile, "CreditCardReceipts", "Amount",i);
            enterListData(tdsTransNatureRowList.get(i), dataFile, "CreditCardReceipts", "TDSTransactionNature",i);
            enterListData(tdsAccountRowList.get(i), dataFile, "CreditCardReceipts", "TDSAccount",i);
            enterListData(tdsAmountRowList.get(i), dataFile, "CreditCardReceipts", "TDSAmount",i);
            enterListData(cardNo.get(i), dataFile, "CreditCardReceipts", "CardNo",i);
            enterListDate(expiryDate.get(i), dataFile, "CreditCardReceipts", "ExpiryDate",i);
            enterListData(approvalNo.get(i), dataFile, "CreditCardReceipts", "ApprovalNo",i);
            enterListData(chargesAccRowList.get(i), dataFile, "CreditCardReceipts", "ChargesAccountCode" , i);
            enterListData(chargesPercentageRowList.get(i), dataFile, "CreditCardReceipts", "Percentage" , i);
            enterListData(executiveRowList.get(i),dataFile,"CreditCardReceipts","Executive",i);
            enterListData(departmentRowList.get(i),dataFile,"CreditCardReceipts","Department",i);
            enterListData(projectRowList.get(i),dataFile,"CreditCardReceipts","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"CreditCardReceipts","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"CreditCardReceipts","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"CreditCardReceipts","Comments",i);
            common.sliderHandling("xpath", "//Table[@Name='CreditCardReceipts']/*/Thumb[@Name='Position']", -600, 0);
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
        EnterData("//Edit[@Name='Transporter']", dataFile, "OtherInfo", "Transporter");
        EnterData("//Edit[@Name='Mode Of Transport']", dataFile, "OtherInfo", "ModeOfTransport");
        EnterDate("//Edit[@Name='Removal Of Goods Date']", dataFile, "OtherInfo", "RemovalOfGoodsDate");
        EnterData("//Edit[@Name='Removal Of Goods Time']", dataFile, "OtherInfo", "RemovalOfGoodsTime");
        EnterData("//Edit[@Name='Invoice Preparation Time']", dataFile, "OtherInfo", "InvoicePreparationTime");
        EnterData("//Edit[@Name='Other Info 1']", dataFile, "OtherInfo", "OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']", dataFile, "OtherInfo", "OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']", dataFile, "OtherInfo", "OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']", dataFile, "OtherInfo", "OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']", dataFile, "OtherInfo", "OtherInfo5");
    }

    public void materialDispatchAddress(){
        EnterData("//Edit[@Name='Address 1']", dataFile, "MaterialDespatchAddress", "Address1");
        EnterData("//Edit[@Name='Address 2']", dataFile, "MaterialDespatchAddress", "Address2");
        EnterData("//Edit[@Name='Address 3']", dataFile, "MaterialDespatchAddress", "Address3");
        EnterData("//Edit[@Name='City']", dataFile, "MaterialDespatchAddress", "City");
        EnterData("//Edit[@Name='State']", dataFile, "MaterialDespatchAddress", "State");
        EnterData("//Edit[@Name='Country']", dataFile, "MaterialDespatchAddress", "Country");
        EnterData("//Edit[@Name='Zip']", dataFile, "MaterialDespatchAddress", "Zip");
        EnterData("//Edit[@Name='Fax']", dataFile, "MaterialDespatchAddress", "Fax");
        EnterData("//Edit[@Name='Email']", dataFile, "MaterialDespatchAddress", "Email");
        EnterData("//Edit[@Name='Contact Person']", dataFile, "MaterialDespatchAddress", "ContactPersonName");
        EnterData("//Edit[@Name='Contact Person Designation']", dataFile, "MaterialDespatchAddress", "ContactPersonDesignation");
        EnterData("//Edit[@Name='Contact Person Telephone No']", dataFile, "MaterialDespatchAddress", "ContactPersonTelephoneNo");
        EnterData("//Edit[@Name='Contact Person Mobile No']", dataFile, "MaterialDespatchAddress", "ContactPersonMobileNo");
        EnterData("//Edit[@Name='Contact Person Email']", dataFile, "MaterialDespatchAddress", "ContactPersonEmail");
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
        for (int i = 0; i < termsAndConditions.size(); i++) {
            enterListData(term.get(i), dataFile, "TermsAndConditions", "Term", i);
        }
    }
}