package com.wings.pages.purchase.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
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

public class PurchaseVouchersAgainstReceipt extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean IsAmountHeaderClicked = false,otherChargesGSTCheckBox = false,discountIsClicked = false,gstAmountClicked = false;

    public PurchaseVouchersAgainstReceipt(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String purchaseVouchersAgainstReceipt(String voucherNum,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Purchase","Invoices","Purchase Vouchers against Receipts");
        long generalInfoStart=System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        //Gen info
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterLocation(dataFile,"GeneralInformation","Location");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterPartyCode(dataFile,"GeneralInformation","PartyAccountCode");
        Thread.sleep(3000);
        gstTransactionType("Intra State Purchase from Registered Dealers");
        Thread.sleep(1000);
        selectPendingsSalesOrder(voucherNum, "20250401");
        Thread.sleep(1000);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        enterPurchaseAccountCode(dataFile,"GeneralInformation","PurchaseAccountCode");
        enterCreditPeriod(dataFile,"GeneralInformation","CreditPeriod");
        common.clickElement("xpath","//CheckBox[@Name='Apply TCS']");
        enterTcsTransNature(dataFile,"GeneralInformation","TCSTransactionNature");
        common.clickElement("xpath","//CheckBox[@Name='Deduct TDS']");
        enterTdsTransNature(dataFile,"GeneralInformation","TDSTransactionNature");
        common.findWebElement("xpath","//Edit[@Name='Supplier Bill No *']").sendKeys("PVAR12"+common.getRandom());
//        enterSupplierBillNumber(dataFile,"GeneralInformation","SupplierBillNo");
        EnterDate("//Edit[@Name='Supplier Bill Date *']",dataFile,"GeneralInformation","SupplierBillDate");
        enterPriceList(dataFile, "GeneralInformation", "PriceList");
        enterVoucherDiscount(dataFile,"GeneralInformation","VoucherDiscountPercentage");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Purchase Voucher against Receipt General information End:- ", generalInfoEndTime);

        //F3-Items
        long addProductStart=System.nanoTime();
        addProduct();
        long addProductEnd=System.nanoTime()-addProductStart;
        FileUtil.writeTimeLogInMinutes("Purchase Voucher against Receipt Add Products:- ",addProductEnd);
        //Services
        long addServicesStart =System.nanoTime();
        common.clickElement("xpath","//TabItem[contains(@Name,'Services')]");
        addServices();
        long addServicesEnd =System.nanoTime()- addServicesStart;
        FileUtil.writeTimeLogInMinutes("Purchase Voucher against Receipt Add Services:- ", addServicesEnd);
        //charges and deductions
        long chargesDeductionsStart =System.nanoTime();
        navigateToChargesAndDeductionsTab();
        addChargesAndDeductions();
        long chargesDeductionsEnd=System.nanoTime()- chargesDeductionsStart;
        FileUtil.writeTimeLogInMinutes("Purchase Voucher against Receipt Charges and Deductions:- ",chargesDeductionsEnd);
        //other charges
        long otherChargesStart =System.nanoTime();
        navigateToOtherChargesTab();
        addOtherCharges();
        long otherChargesEnd=System.nanoTime()- otherChargesStart;
        FileUtil.writeTimeLogInMinutes("Purchase Voucher against Receipt Other Charges:- ",otherChargesEnd);
        //bills recv
        navigateToBillsReceivablesTab();
        common.deleteInvalidRows();
        Thread.sleep(1000);
        //Other Costs
        long otherCostsStart =System.nanoTime();
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Other Costs ')]");
        System.out.println(elements.get(0).getText());
        elements.get(0).click();
        addOtherCosts();
        long otherCostsEnd =System.nanoTime()- otherCostsStart;
        FileUtil.writeTimeLogInMinutes("Purchase Voucher against Receipt Other Costs:- ", otherCostsEnd);
        //scroll
        List<WebElement> elements1=common.findWebElements("xpath","//TabItem[contains(@Name,'Other Costs ')]");
        System.out.println(elements1.get(0).getText());
        elements1.get(0).click();
        moveToRight(9);
        //cash
        long cashStart = System.nanoTime();
        navigateToCashTab();
        addCash();
        long cashEnd = System.nanoTime() - cashStart;
        FileUtil.writeTimeLogInMinutes("Purchase Voucher against Receipt Cash Tab:- ", cashEnd);
        //cheques
        long chequesStart = System.nanoTime();
        List<WebElement> elements2 = common.findWebElements("xpath", "//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements2.get(0).getText());
        elements2.get(0).click();
        addCheques();
        long chequesEnd = System.nanoTime() - chequesStart;
        FileUtil.writeTimeLogInMinutes("Purchase Voucher against Receipt Cheques Tab:- ", chequesEnd);
        //Post Dated Cheques
        long postDatedChequesStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'Post Dated Cheques')]");
        addPostDatedCheques();
        long postDatedChequesEnd = System.nanoTime() - postDatedChequesStart;
        FileUtil.writeTimeLogInMinutes("Purchase Voucher against Receipt Post Dated Cheques Tab:- ", postDatedChequesEnd);
        //chequesPDC
        long chequesPDCStart = System.nanoTime();
        List<WebElement> elements3 = common.findWebElements("xpath", "//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements3.get(2).getText());
        elements3.get(2).click();
        addChequesPDC();
        long chequesPDCEnd = System.nanoTime() - chequesPDCStart;
        FileUtil.writeTimeLogInMinutes("Purchase Voucher against Receipt Cheques PDC Tab:- ", chequesPDCEnd);
        //Other info
        long otherInfoTabStart =System.nanoTime();
        navigateToOtherInfoTab();
        otherInfo();
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Purchase Voucher against Receipt Other Info Tab:- ", otherInfoTabEnd);
        //additional Info
        long additionalInfoTabStart =System.nanoTime();
        common.clickElement("xpath","//TabItem[contains(@Name,'Additional Information  ')]");
        additionalInformation();
        long additionalInfoTabEnd =System.nanoTime()- additionalInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Purchase Voucher against Receipt Additional Info Tab:- ", additionalInfoTabEnd);
        //terms and Cond
        long termsConditionsTabStart =System.nanoTime();
        common.clickElement("xpath","//TabItem[contains(@Name,'Terms And Conditions')]");
        termsAndCondition();
        long termsConditionsTabEnd =System.nanoTime()- termsConditionsTabStart;
        FileUtil.writeTimeLogInMinutes("Purchase Voucher against Receipt Terms and Conditions Tab:- ", termsConditionsTabEnd);
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //API
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"purchaseVoucherAgainstReceipts");

        long pvarTransactionEnd=System.nanoTime()-start;
        FileUtil.writeTimeLogInMinutes("Purchase Voucher against Receipt Ended at:- ",pvarTransactionEnd);

        return newVoucherID;
    }

    public void addProduct() throws IOException, ParseException, InterruptedException {
        List<String> productCode = readExcelData(dataFile, "Items", "ProductCode");
        System.out.println("productCodes :" + productCode.size());
//        for (int i = 0; i < productCode.size(); i++) {
//            addData("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", dataFile, "Items", "ProductCode", i);
//        }
        List<WebElement> purchaseAccount = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Purchase Account * Row ')]");
        List<WebElement> uom = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
        List<WebElement> location = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Location * Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity Row ')]");
        List<WebElement> freeQuantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Free Quantity Row ')]");
        List<WebElement> noOfPacks = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'No Of Packs Row ')]");
        List<WebElement> mrp = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'MRP Row ')]");
        List<WebElement> unitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Unit Rate Row ')]");
        List<WebElement> editableGrossAmount = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Editable Gross Amount Row ')]");
        List<WebElement> voucherDiscount = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Voucher Disc % Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 700, 0);
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
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        List<WebElement> DiscountBasis3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Basis 3 Row ')]");
        List<WebElement> Discount3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc 3 Row ')]");

        List<WebElement> HSNCode = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'HSN Row ')]");
        List<WebElement> GSTProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'GST Product Category Row ')]");
        List<WebElement> CESSProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'CESS Product Category Row ')]");
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        List<WebElement> Info1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 1 Row ')]");
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
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 90, 0);
        List<WebElement> Bool1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 1 Row ')]");
        List<WebElement> Bool2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 2 Row ')]");
        List<WebElement> Bool3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1900, 0);

        for (int i = 0; i < productCode.size(); i++) {
            enterListData(purchaseAccount.get(i), dataFile, "Items", "PurchaseAccount", i);
            enterListData(uom.get(i), dataFile, "Items", "UOM", i);
            enterListData(location.get(i), dataFile, "Items", "Location", i);
            enterListData(storageBin.get(i),dataFile,"Items","StorageBin",i);
            enterListData(quantity.get(i), dataFile, "Items", "Quantity", i);
            enterListData(freeQuantity.get(i),dataFile,"Items","FreeQuantity",i);
            enterListData(noOfPacks.get(i), dataFile, "Items", "NoOfPacks", i);
            enterListData(mrp.get(i), dataFile, "Items", "MRP", i);
            enterListData(unitRate.get(i), dataFile, "Items", "UnitRate", i);
            enterListData(editableGrossAmount.get(i), dataFile, "Items", "EditableGrossAmount", i);
            enterListData(voucherDiscount.get(i), dataFile, "Items", "VoucherDiscountPercentage", i);
            enterListData(DiscountBasis1.get(i), dataFile, "Items", "DiscountBasis1", i);
            enterListData(Discount1.get(i), dataFile, "Items", "Discount1", i);
            enterListData(DiscountBasis2.get(i), dataFile, "Items", "DiscountBasis2", i);
            enterListData(Discount2.get(i), dataFile, "Items", "Discount2", i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
            enterListData(DiscountBasis3.get(i), dataFile, "Items", "DiscountBasis3", i);
            enterListData(Discount3.get(i), dataFile, "Items", "Discount3", i);
            enterListData(HSNCode.get(i), dataFile, "Items", "HSN", i);
            enterListData(GSTProductCategory.get(i), dataFile, "Items", "GSTProductCategory", i);
            enterListData(CESSProductCategory.get(i), dataFile, "Items", "CESSProductCategory", i);
            common.clickElement("xpath","//Button[@Name='Item Other Cost Row "+i+"']");
            Thread.sleep(1000);
            common.clickElement("xpath","//Window[@Name='Item Other Cost Details']/Table[@Name='Item Other Cost Details']/*[@Name='Data Panel']/*[@Name='NewItem Row']/*[@Name='ExpenseType newitem row']");
            EnterData("//Edit[@Name='Editing control']",dataFile,"ItemOtherCosts","ExpenseType",i);
            EnterData("//Edit[@Name='Editing control']",dataFile,"ItemOtherCosts","Vendor",i);
            EnterData("//Edit[@Name='Editing control']",dataFile,"ItemOtherCosts","Currency",i);
            EnterData("//Edit[@Name='Editing control']",dataFile,"ItemOtherCosts","OtherCostInCompanyCurrency",i);
            common.clickElement("xpath", "//Button[@Name='OK']");
            enterListData(Department.get(i), dataFile, "Items", "Department", i);
            enterListData(Project.get(i), dataFile, "Items", "Project", i);
            enterListData(ProfitCentre.get(i), dataFile, "Items", "ProfitCentre", i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 450, 0);
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

    public void addServices() throws IOException {
        List<String> productCode = readExcelData(dataFile, "Services", "ServiceCode");
        System.out.println("productCodes :" + productCode.size());
        for (int i = 0; i < productCode.size(); i++) {
            addData("xpath", "//Edit[@Name='Service Code Row "+i+", Not sorted.']", dataFile, "Services", "ServiceCode", i);
        }
        List<WebElement> purchaseAccount = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Purchase Account * Row ')]");
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity Row ')]");
        List<WebElement> rate = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Rate Row ')]");
        List<WebElement> HSNCode = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'HSN Row ')]");
        List<WebElement> GSTProductCategory = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'GST Product Category Row ')]");
        List<WebElement> CESSProductCategory = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'CESS Product Category Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Services']/*/Thumb[@Name='Position']", 700, 0);
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Services']/*/Thumb[@Name='Position']", 500, 0);
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        List<WebElement> Info1 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 1 Row ')]");
        List<WebElement> Info2 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 2 Row ')]");
        List<WebElement> Info3 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 3 Row ')]");
        List<WebElement> Info4 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 4 Row ')]");
        List<WebElement> Info5 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 5 Row ')]");
        List<WebElement> Value1 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 1 Row ')]");
        List<WebElement> Value2 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 2 Row ')]");
        List<WebElement> Value3 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 3 Row ')]");
        List<WebElement> Value4 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 4 Row ')]");
        List<WebElement> Value5 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 5 Row ')]");
        List<WebElement> Date1 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date 1 Row ')]");
        List<WebElement> Date2 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date 2 Row ')]");
        List<WebElement> Date3 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date 3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Services']/*/Thumb[@Name='Position']", 90, 0);
        List<WebElement> Bool1 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool1 Row ')]");
        List<WebElement> Bool2 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool2 Row ')]");
        List<WebElement> Bool3 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Services']/*/Thumb[@Name='Position']", -1900, 0);

        for (int i = 0; i < productCode.size(); i++) {
            enterListData(purchaseAccount.get(i), dataFile, "Services", "PurchaseAccount", i);
            enterListData(quantity.get(i), dataFile, "Services", "Quantity", i);
            enterListData(rate.get(i), dataFile, "Services", "Rate", i);
            enterListData(HSNCode.get(i), dataFile, "Services", "HSN", i);
            enterListData(GSTProductCategory.get(i), dataFile, "Services", "GSTProductCategory", i);
            enterListData(CESSProductCategory.get(i), dataFile, "Services", "CESSProductCategory", i);
            common.sliderHandling("xpath", "//Table[@Name='Services']/*/Thumb[@Name='Position']", 450, 0);
            enterListData(Department.get(i), dataFile, "Services", "Department", i);
            enterListData(Project.get(i), dataFile, "Services", "Project", i);
            enterListData(ProfitCentre.get(i), dataFile, "Services", "ProfitCentre", i);
            enterListData(CostCentre.get(i), dataFile, "Services", "CostCentre", i);
            enterListData(Comments.get(i), dataFile, "Services", "Comments", i);
            enterListData(Info1.get(i), dataFile, "Services", "Info1", i);
            enterListData(Info2.get(i), dataFile, "Services", "Info2", i);
            enterListData(Info3.get(i), dataFile, "Services", "Info3", i);
            enterListData(Info4.get(i), dataFile, "Services", "Info4", i);
            enterListData(Info5.get(i), dataFile, "Services", "Info5", i);
            enterListData(Value1.get(i), dataFile, "Services", "Value1", i);
            enterListData(Value2.get(i), dataFile, "Services", "Value2", i);
            enterListData(Value3.get(i), dataFile, "Services", "Value3", i);
            enterListData(Value4.get(i), dataFile, "Services", "Value4", i);
            enterListData(Value5.get(i), dataFile, "Services", "Value5", i);
            common.sliderHandling("xpath", "//Table[@Name='Services']/*/Thumb[@Name='Position']", 150, 0);
            enterListDate(Date1.get(i), dataFile, "Services", "Date1", i);
            enterListDate(Date2.get(i), dataFile, "Services", "Date2", i);
            enterListDate(Date3.get(i), dataFile, "Services", "Date3", i);
            common.sliderHandling("xpath", "//Table[@Name='Services']/*/Thumb[@Name='Position']", 50, 0);
            clickListData(Bool1.get(i));
            clickListData(Bool2.get(i));
            clickListData(Bool3.get(i));
            common.sliderHandling("xpath", "//Table[@Name='Services']/*/Thumb[@Name='Position']", -1000, 0);
        }
    }

    public void addChargesAndDeductions() throws IOException, InterruptedException {
        List<String> chargesAndDeductions=readExcelData(dataFile,"ChargesAndDeductions","ChargesOrDeductions");
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
            Thread.sleep(1500);
            enterListData(departmentRowList.get(i), dataFile, "ChargesAndDeductions", "Department" , i);
            enterListData(projectRowList.get(i), dataFile, "ChargesAndDeductions", "Project" , i);
            enterListData(profitCentreRowList.get(i), dataFile, "ChargesAndDeductions", "ProfitCentre" , i);
            enterListData(costCentreRowList.get(i), dataFile, "ChargesAndDeductions", "CostCentre" , i);
            enterListData(commentsRowList.get(i), dataFile, "ChargesAndDeductions", "Comments" , i);
        }
    }

    public void addOtherCharges() throws IOException, InterruptedException, ParseException {
        List<String> otherCharges=readExcelData(dataFile,"OtherCharges","AccountCode");
        for (int i = 0; i < otherCharges.size() ; i++) {
            addData("xpath","//Edit[@Name='Account Code Row "+i+", Not sorted.']",dataFile,"OtherCharges","AccountCode",i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> GSTProductCategory = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'GST Product Category Row ')]");
        List<WebElement> CESSProductCategory = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'CESS Product Category Row ')]");
        if (!otherChargesGSTCheckBox) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            otherChargesGSTCheckBox = true;
        }
        common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", 400, 0);
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", -400, 0);

        for (int i = 0; i < otherCharges.size(); i++) {
            enterListData(amountRowList.get(i), dataFile, "OtherCharges", "InclusiveAmount", i);
            enterListData(hsnCodeRowList.get(i), dataFile, "OtherCharges", "HSN",i);
            enterListData(GSTProductCategory.get(i), dataFile, "OtherCharges", "GSTProductCategory",i);
            enterListData(CESSProductCategory.get(i), dataFile, "OtherCharges", "CESSProductCategory",i);
            Thread.sleep(1000);
            enterListData(departmentRowList.get(i), dataFile, "OtherCharges", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "OtherCharges", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "OtherCharges", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "OtherCharges", "CostCentre" ,i);
            enterListData(commentsRowList.get(i), dataFile, "OtherCharges", "Comments" , i);
            common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", -550, 0);
        }
    }

    public void addOtherCosts() throws IOException, InterruptedException {
        List<String> otherCosts =readExcelData(dataFile,"OtherCosts","ExpenseTypeCode");
        for (int i = 0; i < otherCosts.size() ; i++) {
            addData("xpath","//Edit[@Name='Expense Type Code Row "+i+", Not sorted.']",dataFile,"OtherCosts","ExpenseTypeCode",i);
        }
        List<WebElement> vendorCode = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Vendor Code Row ')]");
        List<WebElement> currency = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Currency Row ')]");
        List<WebElement> otherCostRow = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Other Cost * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < otherCosts.size(); i++) {
            enterListData(vendorCode.get(i), dataFile, "OtherCosts", "VendorCode",i);
            enterListData(currency.get(i), dataFile, "OtherCosts", "Currency",i);
            enterListData(otherCostRow.get(i),dataFile,"OtherCosts","OtherCost",i);
            Thread.sleep(1000);
            enterListData(departmentRowList.get(i), dataFile, "OtherCosts", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "OtherCosts", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "OtherCosts", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "OtherCosts", "CostCentre" ,i);
            enterListData(commentsRowList.get(i), dataFile, "OtherCosts", "Comments" , i);
        }
    }

    public void addCash() throws IOException {
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
        List<String> chequesTab = readExcelData(dataFile, "Cheques", "BankAccountCode");
        for (int i = 0; i < chequesTab.size(); i++) {
            addData("xpath", "//Edit[@Name='Bank Account Code Row " + i + ", Not sorted.']", dataFile, "Cheques", "BankAccountCode", i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
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
        List<String> postDatedCheques = readExcelData(dataFile, "PostDatedCheques", "BankAccountCode");
        for (int i = 0; i < postDatedCheques.size(); i++) {
            addData("xpath", "//Edit[@Name='Bank Account Code Row "+i+", Not sorted.']", dataFile, "PostDatedCheques", "BankAccountCode", i);
        }
        List<WebElement> pdcAccount = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'PDC Account * Row ')]");
        List<WebElement> amoutRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < postDatedCheques.size(); i++) {
            enterListData(pdcAccount.get(i), dataFile, "PostDatedCheques", "PDCAccount", i);
            enterListData(amoutRowList.get(i), dataFile, "PostDatedCheques", "Amount", i);
            enterListData(chequeNo.get(i), dataFile, "PostDatedCheques", "ChequeNo", i);
            enterListDate(chequeDate.get(i), dataFile, "PostDatedCheques", "ChequeDate", i);
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
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < chequesPDC.size(); i++) {
            enterListData(chequeAmountRowList.get(i), dataFile, "PDC", "Amount", i);
            enterListData(chequeNo.get(i), dataFile, "PDC", "ChequeNo", i);
            enterListDate(chequeDate.get(i), dataFile, "PDC", "ChequeDate", i);
            enterListData(departmentRowList.get(i), dataFile, "PDC", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "PDC", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "PDC", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "PDC", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "PDC", "Comments", i);
        }
    }
    
    public void otherInfo() throws InterruptedException, IOException {
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(5000);
//        common.clickElement("xpath","//Window/Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }

    public void  additionalInformation() throws IOException {
        EnterData("//Edit[@Name='Info 1']",dataFile,"AdditionalInformation","Info1");
        EnterData("//Edit[@Name='Info 2']",dataFile,"AdditionalInformation","Info2");
        EnterData("//Edit[@Name='Info 3']",dataFile,"AdditionalInformation","Info3");
        EnterData("//Edit[@Name='Info 4']",dataFile,"AdditionalInformation","Info4");
        EnterData("//Edit[@Name='Info 5']",dataFile,"AdditionalInformation","Info5");
        EnterData("//Edit[@Name='Value 1']",dataFile,"AdditionalInformation","Value1");
        EnterData("//Edit[@Name='Value 2']",dataFile,"AdditionalInformation","Value2");
        EnterData("//Edit[@Name='Value 3']",dataFile,"AdditionalInformation","Value3");
        EnterData("//Edit[@Name='Value 4']",dataFile,"AdditionalInformation","Value4");
        EnterData("//Edit[@Name='Value 5']",dataFile,"AdditionalInformation","Value5");
        EnterDate("//Edit[@Name='Date 1']",dataFile,"AdditionalInformation","Date1");
        EnterDate("//Edit[@Name='Date 2']",dataFile,"AdditionalInformation","Date2");
        EnterDate("//Edit[@Name='Date 3']",dataFile,"AdditionalInformation","Date3");
        common.clickElement("xpath","//CheckBox[@Name='Bool 1']");
        common.clickElement("xpath","//CheckBox[@Name='Bool 2']");
        common.clickElement("xpath","//CheckBox[@Name='Bool 3']");
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
    
}
