package com.wings.pages.purchase.transactions;

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
import java.io.IOException;
import java.util.List;

public class PurchaseVouchersAgainstOrder extends TransactionsBaseClass {
    WindowsDriver driver,rootDriver;
    Common common;
    String dataFile;

    boolean IsAmountHeaderClicked = false;
    boolean otherChargesGSTCheckBox = false;
    boolean gstAmountClicked = false;
    boolean discountIsClicked = false;

    public PurchaseVouchersAgainstOrder(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String purchaseVouchersAgainstOrder(String voucherNum,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long PVAO = System.nanoTime();

        navigateToMastersWhen3Steps("Purchase","Invoices","Purchase Vouchers against Orders");
        Thread.sleep(3000);
        long start1 = System.nanoTime();
        String oldVoucherID = oldTTransactionID();

        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranchName(dataFile,"GeneralInformation","Branch");
        enterLocation(dataFile,"GeneralInformation","Location");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterCashOrParty(dataFile,"GeneralInformation","PartyAccountCode");
        Thread.sleep(2000);
        gstTransactionType("Intra State Purchase from Registered Dealers");
        Thread.sleep(1500);
        selectPendingsSalesOrder(voucherNum,"20250401");
        Thread.sleep(7000);
        common.clickElement("xpath","//Button[@Name='OK']");
        enterCreditPeriod(dataFile,"GeneralInformation","CreditPeriod");
        enterPurchaseAccountCode(dataFile,"GeneralInformation","PurchaseAccountCode");
        enableCheckboxSelection("//CheckBox[@Name='Apply TCS']");
        enterTcsTransNature(dataFile,"GeneralInformation","TCSTransactionNature");
        enableCheckboxSelection("//CheckBox[@Name='Deduct TDS']");
        enterTdsTransNature(dataFile,"GeneralInformation","TDSTransactionNature");
        enterSuppliersBillNumber(dataFile,"GeneralInformation","SupplierBillNo");
        common.clickElement("xpath","//Button[@Name='OK']");
        common.inputText("xpath", "//Edit[@Name='Supplier Bill No *']", "supBillNum" + common.getRandom());
        enterSuppliersBillDate(dataFile,"GeneralInformation","SupplierBillDate");
        enterBatchPolicy(dataFile,"GeneralInformation","BatchPolicy");
        enterVoucherDisc(dataFile,"GeneralInformation","VoucherDiscountPercentage");
        enterPriceList(dataFile,"GeneralInformation","PriceList");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        long duration1 = System.nanoTime() - start1;
        FileUtil.writeTimeLogInMinutes("Purchase Vouchers Against Orders General information Purchase voucher:- ", duration1);

        long products = System.nanoTime();
        addProduct();
        long productsEnd = System.nanoTime() - products;
        FileUtil.writeTimeLogInMinutes("Purchase Vouchers Against Orders Add Products:- ", productsEnd);

        long services = System.nanoTime();
        navigateToServices();
        addServices();
        long servicesEnd = System.nanoTime() - services;
        FileUtil.writeTimeLogInMinutes("Purchase Vouchers Against Orders Add Enter services:- ", servicesEnd);

        long chargesAndDeductions = System.nanoTime();
        navigateToChargesAndDeductionsTab();
        addChargesAndDeductions();
        long chargesAndDeductionsEnd = System.nanoTime() - chargesAndDeductions;
        FileUtil.writeTimeLogInMinutes("Purchase Vouchers Against Orders Add Enter Charges And Deductions:- ", chargesAndDeductionsEnd);

        long otherCharges = System.nanoTime();
        navigateToOtherChargesTab();
        addOtherCharges();
        long otherChargesEnd = System.nanoTime() - otherCharges;
        FileUtil.writeTimeLogInMinutes("Purchase Vouchers Against Orders Add Enter Other:- ", otherChargesEnd);

        navigateToBillsReceivablesTab();
        common.deleteInvalidRows();

        long otherCosts = System.nanoTime();
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Other Costs ')]");
        elements.get(0).click();
        addOtherCosts();
        Thread.sleep(1000);
        List<WebElement> elements1=common.findWebElements("xpath","//TabItem[contains(@Name,'Other Costs ')]");
        elements1.get(1).click();
        long otherCostsEnd = System.nanoTime() - otherCosts;
        FileUtil.writeTimeLogInMinutes("Purchase Vouchers Against Orders Add Other Costs:- ", otherCostsEnd);
        elements.get(0).click();
        moveToRight(9);

        Thread.sleep(3000);

        long cash = System.nanoTime();
        navigateToCashTab();
        addCash();
        long cashEnd = System.nanoTime() - cash;
        FileUtil.writeTimeLogInMinutes("Purchase Vouchers Against Orders Add Cash:- ", cashEnd);

        long cheques = System.nanoTime();
        navigateToCheques();
        addCheques();
        long chequesEnd = System.nanoTime() - cheques;
        FileUtil.writeTimeLogInMinutes("Purchase Vouchers Against Orders Add Cheque:- ", chequesEnd);

        long postDatedCheques = System.nanoTime();
        navigateToPostdatedCheques();
        addPostDatedCheques();
        long postDatedChequesEnd = System.nanoTime() - postDatedCheques;
        FileUtil.writeTimeLogInMinutes("Purchase Vouchers Against Orders Add Post Dated Cheques:- ", postDatedChequesEnd);

        long chequesPDC = System.nanoTime();
        navigateToChequesPDC();
        addChequesPDC();
        long chequesPDCEnd = System.nanoTime() - chequesPDC;
        FileUtil.writeTimeLogInMinutes("Purchase Vouchers Against Orders Add Cheque PDC:- ", chequesPDCEnd);

        long otherInfo = System.nanoTime();
        navigateToOtherInfoTab();
        otherInfo();
        long otherInfoEnd = System.nanoTime() - otherInfo;
        FileUtil.writeTimeLogInMinutes("Purchase Vouchers Against Orders Add OtherInfo:- ", otherInfoEnd);

        long additionalInfo = System.nanoTime();
        navigateToAdditionalInfo();
        additionalInformation();
        long additionalInfoEnd = System.nanoTime() - additionalInfo;
        FileUtil.writeTimeLogInMinutes("Purchase Vouchers Against Orders Add Additional Information:- ", additionalInfoEnd);

        long termsAndConditions = System.nanoTime();
        navigateToTermsAndConditions();
        termsAndCondition();
        long termsAndConditionsEnd = System.nanoTime() - termsAndConditions;
        FileUtil.writeTimeLogInMinutes("Purchase Vouchers Against Orders Add Terms And Conditions:- ", termsAndConditionsEnd);

        transactionSave();
        String newVoucherID = newTransactionID(oldVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"PurchaseVouchersAgainstOrders");

        long PurchaseVouchersAgainstOrdersEnd = System.nanoTime() - PVAO;
        FileUtil.writeTimeLogInMinutes("Purchase Vouchers Against Orders Ended at:- ", PurchaseVouchersAgainstOrdersEnd);

        return newVoucherID;
    }

    public void addProduct() throws InterruptedException, IOException, ParseException, AWTException {
        List<String> productCode=readExcelData(dataFile,"Items","ProductCode");
        List<String> masterType=readExcelData(dataFile,"Items","MasterType");
//        System.out.println("productCodes :"+productCode.size());
        List<WebElement> productAccRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Purchase Account * Row')]");
        List<WebElement> productUOMRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 470, 0);
        List<WebElement> productQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity Row ')]");
        List<WebElement> freeQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Free Quantity Row')]");
        List<WebElement> numOfPacksRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'No Of Packs Row ')]");
        List<WebElement> mrpRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'MRP Row ')]");
        List<WebElement> unitRateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Unit Rate Row ')]");
        List<WebElement> editableGrossAmountList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Editable Gross Amount Row ')]");
        List<WebElement> voucherDiscountList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Voucher Disc % Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 400, 0);
        if (!discountIsClicked) {
            common.clickElement("xpath", "//Header[@Name='Disc Amount 1']");
            common.clickElement("xpath", "//Header[@Name='Disc Amount 2']");
            common.clickElement("xpath", "//Header[@Name='Disc Amount 3']");
            discountIsClicked = true;
        }
        List<WebElement> discountBasis1RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 1 Row ')]");
        List<WebElement> disount1RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 1 Row ')]");
        List<WebElement> disount2BasisRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 2 Row ')]");
        List<WebElement> disount2RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 2 Row ')]");
        List<WebElement> disount3BasisRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 3 Row ')]");
        List<WebElement> disount3RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 3 Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> gstProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 410, 0);
        List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 700, 0);
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        List<WebElement> infoRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 1 Row ')]");
        List<WebElement> infoRowList1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 2 Row ')]");
        List<WebElement> infoRowList2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 3 Row ')]");
        List<WebElement> infoRowList3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 4 Row ')]");
        List<WebElement> infoRowList4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 5 Row ')]");
        List<WebElement> valueRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 1 Row ')]");
        List<WebElement> valueRowList1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 2 Row ')]");
        List<WebElement> valueRowList2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 3 Row ')]");
        List<WebElement> valueRowList3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 4 Row ')]");
        List<WebElement> valueRowList4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 5 Row ')]");
        List<WebElement> dateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Date 1 Row ')]");
        List<WebElement> dateRowList1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Date 2 Row ')]");
        List<WebElement> dateRowList2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Date 3 Row ')]");
        List<WebElement> bool1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 1 Row')]");
        List<WebElement> bool2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 2 Row')]");
        List<WebElement> bool3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 3 Row ')]");

        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -2000, 0);

        for (int j = 0; j < productCode.size() ; j++) {
            enterListData(productAccRowList.get(j), dataFile, "Items", "PurchaseAccount",j);
            enterListData(productUOMRowList.get(j), dataFile, "Items", "UOM",j);
            enterListData(storageBin.get(j), dataFile, "Items", "StorageBin",j);
            if (masterType.get(j).equals("Products")) {
                enterListData(productQuantityRowList.get(j), dataFile, "Items", "Quantity",j);
            } else if (masterType.get(j).equals("Products - MultiBatch")){
                enterListData(productQuantityRowList.get(j), dataFile, "Items", "Quantity",j);
            }
            else if (masterType.get(j).equals("Products - Batches and Serial No")){
                Thread.sleep(1000);
                common.clickElement("xpath", "//Button[@Name='Serial Nos Row "+j+"']");
                Thread.sleep(500);
                List<WebElement> editfields=common.findWebElements("xpath","//Window[@Name='Serial No Details']//Pane/Edit/*");
                System.out.println("Serial number edit fields :"+editfields.size());
                editfields.get(1).sendKeys("start");
                editfields.get(2).sendKeys("2",Keys.ENTER);
                editfields.get(3).sendKeys("1",Keys.ENTER);
                common.clickElement("xpath", "//Button[@Name='OK']");
            }else {
                Assert.fail("No product present");
            }
            enterListData(freeQuantityRowList.get(j), dataFile, "Items", "FreeQuantity",j);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 40, 0);
            enterListData(numOfPacksRowList.get(j), dataFile, "Items", "NoOfPacks",j);
            enterListData(mrpRowList.get(j), dataFile, "Items", "MRP",j);
            enterListData(unitRateRowList.get(j), dataFile, "Items", "UnitRate",j);
            enterListData(editableGrossAmountList.get(j), dataFile, "Items", "EditableGrossAmount",j);
            enterListData(voucherDiscountList.get(j), dataFile, "Items", "VoucherDiscountPercentage",j);
            enterListData(discountBasis1RowList.get(j), dataFile, "Items", "DiscountBasis1" ,j);
            enterListData(disount1RowList.get(j), dataFile, "Items", "Discount1",j);
            enterListData(disount2BasisRowList.get(j), dataFile, "Items", "DiscountBasis2",j);
            enterListData(disount2RowList.get(j), dataFile, "Items", "Discount2",j);
            enterListData(disount3BasisRowList.get(j), dataFile, "Items", "DiscountBasis3",j);
            enterListData(disount3RowList.get(j), dataFile, "Items", "Discount3",j);
            enterListData(hsnCodeRowList.get(j), dataFile, "Items", "HSN",j);
            enterListData(gstProductCategoryRowList.get(j), dataFile, "Items", "GSTProductCategory",j);
            enterListData(cessProductCategoryRowList.get(j), dataFile, "Items", "CESSProductCategory",j);
            common.clickElement("xpath","//Button[@Name='Item Other Cost Row "+j+"']");
            Thread.sleep(1000);
            common.clickElement("xpath","//Window[@Name='Item Other Cost Details']/Table[@Name='Item Other Cost Details']/*[@Name='Data Panel']/*[@Name='NewItem Row']/*[@Name='ExpenseType newitem row']");
            EnterData("//Edit[@Name='Editing control']",dataFile,"ItemOtherCosts","ExpenseType",j);
            EnterData("//Edit[@Name='Editing control']",dataFile,"ItemOtherCosts","Vendor",j);
            EnterData("//Edit[@Name='Editing control']",dataFile,"ItemOtherCosts","Currency",j);
            EnterData("//Edit[@Name='Editing control']",dataFile,"ItemOtherCosts","OtherCostInCompanyCurrency",j);
            common.clickElement("xpath", "//Button[@Name='OK']");
            Thread.sleep(1500);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 700, 0);
            enterListData(departmentRowList.get(j), dataFile, "Items", "Department",j);
            enterListData(projectRowList.get(j), dataFile, "Items", "Project",j);
            enterListData(profitCentreRowList.get(j), dataFile, "Items", "ProfitCentre",j);
            enterListData(costCentreRowList.get(j), dataFile, "Items", "CostCentre",j);
            enterListData(commentsRowList.get(j), dataFile, "Items", "Comments",j);
            enterListData(infoRowList.get(j), dataFile, "Items", "Info1",j);
            enterListData(infoRowList1.get(j), dataFile, "Items", "Info2",j);
            enterListData(infoRowList2.get(j), dataFile, "Items", "Info3", j);
            enterListData(infoRowList3.get(j), dataFile, "Items", "Info4", j);
            enterListData(infoRowList4.get(j), dataFile, "Items", "Info5", j);
            enterListData(valueRowList.get(j), dataFile, "Items", "Value1",j);
            enterListData(valueRowList1.get(j), dataFile, "Items", "Value2",j);
            enterListData(valueRowList2.get(j), dataFile, "Items", "Value3",j);
            enterListData(valueRowList3.get(j), dataFile, "Items", "Value4",j);
            enterListData(valueRowList4.get(j), dataFile, "Items", "Value5",j);
            enterListDate(dateRowList.get(j),dataFile,"Items","Date1",j);
            enterListDate(dateRowList1.get(j),dataFile,"Items","Date2",j);
            enterListDate(dateRowList2.get(j),dataFile,"Items","Date3",j);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 20, 0);
            clickListData(bool1.get(j));
            clickListData(bool2.get(j));
            clickListData(bool3.get(j));
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1500, 0);
        }
    }

    public void addServices() throws IOException, InterruptedException {
        List<String> productCode=readExcelData(dataFile,"Services","ServiceCode");
//        System.out.println("productCodes :"+productCode.size());
        for (int i = 0; i < productCode.size() ; i++) {
            addData("xpath","//Edit[@Name='Service Code Row "+i+", Not sorted.']",dataFile,"Services","ServiceCode",i);
        }
        List<WebElement> servicesRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Purchase Account * Row')]");
        List<WebElement> quantityRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity Row ')]");
        List<WebElement> unitRateRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Rate Row ')]");
        List<WebElement> hsnRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> gstProdctCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
        if (!servicesGSTCheckBox) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            servicesGSTCheckBox = true;
        }
        common.sliderHandling("xpath", "//Table[@Name='Services']/*/Thumb[@Name='Position']", 800, 0);
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        List<WebElement> infoRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 1 Row ')]");
        List<WebElement> infoRowList1 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 2 Row ')]");
        List<WebElement> infoRowList2 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 3 Row ')]");
        List<WebElement> infoRowList3 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 4 Row ')]");
        List<WebElement> infoRowList4 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Info 5 Row ')]");
        List<WebElement> valueRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 1 Row ')]");
        List<WebElement> valueRowList1 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 2 Row ')]");
        List<WebElement> valueRowList2 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 3 Row ')]");
        List<WebElement> valueRowList3 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 4 Row ')]");
        List<WebElement> valueRowList4 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Value 5 Row ')]");
        List<WebElement> dateRowList = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Date 1 Row ')]");
        List<WebElement> dateRowList1 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Date 2 Row ')]");
        List<WebElement> dateRowList2 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Date 3 Row ')]");
        List<WebElement> bool1 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool1 Row')]");
        List<WebElement> bool2 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool2 Row')]");
        List<WebElement> bool3 = common.findWebElements("xpath", "//Table[@Name='Services']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool3 Row')]");
        common.sliderHandling("xpath", "//Table[@Name='Services']/*/Thumb[@Name='Position']", -1400, 0);

        for (int j = 0; j < productCode.size(); j++) {
            enterListData(servicesRowList.get(j), dataFile, "Services", "PurchaseAccount",j);
            enterListData(quantityRowList.get(j), dataFile, "Services", "Quantity",j);
            enterListData(unitRateRowList.get(j), dataFile, "Services", "Rate",j);
            enterListData(hsnRowList.get(j), dataFile, "Services", "HSN",j);
            enterListData(gstProdctCategoryRowList.get(j), dataFile, "Services", "GSTProductCategory",j);
            enterListData(cessProductCategoryRowList.get(j), dataFile, "Services", "CESSProductCategory",j);
            Thread.sleep(1500);
            enterListData(departmentRowList.get(j), dataFile, "Services", "Department",j);
            enterListData(projectRowList.get(j), dataFile, "Services", "Project",j);
            enterListData(profitCentreRowList.get(j), dataFile, "Services", "ProfitCentre",j);
            enterListData(costCentreRowList.get(j), dataFile, "Services", "CostCentre",j);
            enterListData(commentsRowList.get(j), dataFile, "Services", "Comments",j);
            enterListData(infoRowList.get(j), dataFile, "Services", "Info1",j);
            enterListData(infoRowList1.get(j), dataFile, "Services", "Info2",j);
            enterListData(infoRowList2.get(j), dataFile, "Services", "Info3", j);
            enterListData(infoRowList3.get(j), dataFile, "Services", "Info4", j);
            enterListData(infoRowList4.get(j), dataFile, "Services", "Info5", j);
            enterListData(valueRowList.get(j), dataFile, "Services", "Value1",j);
            enterListData(valueRowList1.get(j), dataFile, "Services", "Value2",j);
            enterListData(valueRowList2.get(j), dataFile, "Services", "Value3",j);
            enterListData(valueRowList3.get(j), dataFile, "Services", "Value4",j);
            enterListData(valueRowList4.get(j), dataFile, "Services", "Value5",j);
            enterListDate(dateRowList.get(j),dataFile,"Services","Date1",j);
            enterListDate(dateRowList1.get(j),dataFile,"Services","Date2",j);
            enterListDate(dateRowList2.get(j),dataFile,"Services","Date3",j);
            common.sliderHandling("xpath", "//Table[@Name='Services']/*/Thumb[@Name='Position']", 20, 0);
            clickListData(bool1.get(j));
            clickListData(bool2.get(j));
            clickListData(bool3.get(j));
            common.sliderHandling("xpath", "//Table[@Name='Services']/*/Thumb[@Name='Position']", -1400, 0);
        }
    }

    public void addChargesAndDeductions() throws IOException, ParseException {
        List<String> productCode=readExcelData(dataFile,"ChargesAndDeductions","ChargesOrDeductions");
//        System.out.println("productCodes :"+productCode.size());
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
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < productCode.size(); i++) {
            enterListData(accCodeRowList.get(i), dataFile, "ChargesAndDeductions", "AccountCode",i);
            enterListData(basisRowList.get(i), dataFile, "ChargesAndDeductions", "Basis",i);
            enterListData(percentageRowList.get(i), dataFile, "ChargesAndDeductions", "Percentage",i);
            enterListData(departmentRowList.get(i), dataFile, "ChargesAndDeductions", "Department",i);
            enterListData(projectRowList.get(i), dataFile, "ChargesAndDeductions", "Project",i);
            enterListData(profitCentreRowList.get(i), dataFile, "ChargesAndDeductions", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "ChargesAndDeductions", "CostCentre",i);
            enterListData(commentsRowList.get(i), dataFile, "ChargesAndDeductions", "Comments",i);
        }
    }

    public void addOtherCharges() throws IOException {
        List<String> productCode=readExcelData(dataFile,"OtherCharges","AccountCode");
//        System.out.println("productCodes :"+productCode.size());
        for (int i = 0; i < productCode.size() ; i++) {
            addData("xpath","//Edit[@Name='Account Code Row "+i+", Not sorted.']",dataFile,"OtherCharges","AccountCode",i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> gstProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
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

        for (int i = 0; i < productCode.size(); i++) {
            enterListData(amountRowList.get(i), dataFile, "OtherCharges", "Amount",i);
            enterListData(hsnCodeRowList.get(i), dataFile, "OtherCharges", "HSN",i);
            enterListData(gstProductCategoryRowList.get(i), dataFile, "OtherCharges", "GSTProductCategory",i);
            enterListData(cessProductCategoryRowList.get(i), dataFile, "OtherCharges", "CESSProductCategory",i);
            common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", 400, 0);
            enterListData(departmentRowList.get(i), dataFile, "OtherCharges", "Department",i);
            enterListData(projectRowList.get(i), dataFile, "OtherCharges", "Project",i);
            enterListData(profitCentreRowList.get(i), dataFile, "OtherCharges", "ProfitCentre",i);
            enterListData(costCentreRowList.get(i), dataFile, "OtherCharges", "CostCentre",i);
            enterListData(commentsRowList.get(i), dataFile, "OtherCharges", "Comments",i);
            common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", -550, 0);
        }
    }

    public void addOtherCosts() throws IOException {
        List<String> productCode=readExcelData(dataFile,"OtherCosts","ExpenseTypeCode");
//        System.out.println("productCodes :"+productCode.size());
        for (int i = 0; i < productCode.size() ; i++) {
            addData("xpath","//Edit[@Name='Expense Type Code Row "+i+", Not sorted.']",dataFile,"OtherCosts","ExpenseTypeCode",i);
        }
        List<WebElement> vendorCodeList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Vendor Code Row ')]");
        List<WebElement> currencyList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Currency Row ')]");
        List<WebElement> amountList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Other Cost * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < productCode.size(); i++) {
            enterListData(vendorCodeList.get(i), dataFile, "OtherCosts", "VendorCode",i);
            enterListData(currencyList.get(i), dataFile, "OtherCosts", "Currency",i);
            enterListData(amountList.get(i), dataFile, "OtherCosts", "OtherCost",i);
            enterListData(departmentRowList.get(i), dataFile, "OtherCosts", "Department",i);
            enterListData(projectRowList.get(i), dataFile, "OtherCosts", "Project",i);
            enterListData(profitCentreRowList.get(i), dataFile, "OtherCosts", "ProfitCentre",i);
            enterListData(costCentreRowList.get(i), dataFile, "OtherCosts", "CostCentre",i);
            enterListData(commentsRowList.get(i), dataFile, "OtherCosts", "Comments",i);
        }
    }

    public void addCash() throws IOException {
        List<String> cash=readExcelData(dataFile,"Cash","CashAccountCode");
        for (int i = 0; i< cash.size(); i++) {
            addData("xpath","//Edit[@Name='Cash Account Code Row "+i+", Not sorted.']",dataFile,"Cash","CashAccountCode",i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <cash.size() ; i++) {
            enterListData(amountRowList.get(i), dataFile, "Cash", "Amount",i);
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
        List<WebElement> chargesAccRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Account Code Row ')]");
        List<WebElement> chargesAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <cheque.size(); i++) {
            enterListData(amountRowList.get(i), dataFile, "Cheques", "Amount", i);
            enterListData(ChequeNo.get(i), dataFile, "Cheques", "ChequeNo" ,i);
            enterListDate(ChequeDate.get(i), dataFile, "Cheques", "ChequeDate" , i);
            enterListData(chargesAccRowList.get(i), dataFile, "Cheques", "ChargesAccountCode" , i);
            enterListData(chargesAmountRowList.get(i), dataFile, "Cheques", "Charges" , i);
            enterListData(departmentRowList.get(i),dataFile,"Cheques","Department",i);
            enterListData(projectRowList.get(i),dataFile,"Cheques","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"Cheques","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"Cheques","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"Cheques","Comments",i);
        }
    }

    public void addPostDatedCheques() throws IOException {
        List<String> postDateDCheques=readExcelData(dataFile,"PostDatedCheques","BankAccountCode");
        for (int i = 0; i< postDateDCheques.size(); i++) {
            addData("xpath","//Edit[@Name='Bank Account Code Row "+i+", Not sorted.']",dataFile,"PostDatedCheques","BankAccountCode",i);
        }
        List<WebElement> PDCAcc = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'PDC Account * Row ')]");
        List<WebElement> amoutRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> ChequeNo = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> ChequeDate = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <postDateDCheques.size() ; i++) {
            enterListData(PDCAcc.get(i), dataFile, "PostDatedCheques", "PDCAccount", i);
            enterListData(amoutRowList.get(i), dataFile, "PostDatedCheques", "Amount", i);
            enterListData(ChequeNo.get(i), dataFile, "PostDatedCheques", "ChequeNo" ,i);
            enterListDate(ChequeDate.get(i), dataFile, "PostDatedCheques", "ChequeDate" , i);
            enterListData(departmentRowList.get(i),dataFile,"PostDatedCheques","Department",i);
            enterListData(projectRowList.get(i),dataFile,"PostDatedCheques","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"PostDatedCheques","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"PostDatedCheques","CostCentre",i);
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
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i <chequesPDC.size() ; i++) {
            enterListData(chequeAmountRowList.get(i), dataFile, "PDC", "Amount",i);
            enterListData(ChequeNo.get(i), dataFile, "PDC", "ChequeNo" ,i);
            enterListDate(ChequeDate.get(i), dataFile, "PDC", "ChequeDate" , i);
            enterListData(departmentRowList.get(i),dataFile,"PDC","Department",i);
            enterListData(projectRowList.get(i),dataFile,"PDC","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"PDC","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"PDC","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"PDC","Comments",i);
        }
    }

    public void otherInfo() throws InterruptedException, IOException {
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(1500);
//        common.clickElement("xpath","//Button[@Name='OK']");
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
        common.clickElement("xpath", "//CheckBox[@Name='Bool 1']");
        common.clickElement("xpath", "//CheckBox[@Name='Bool 2']");
        common.clickElement("xpath", "//CheckBox[@Name='Bool 3']");
    }

    public  void termsAndCondition() throws IOException {
        java.util.List<String> termsAndConditions=readExcelData(dataFile,"TermsAndConditions","TermType");
        for (int i = 0; i < termsAndConditions.size() ; i++) {
            addData("xpath","//Edit[@Name='Term Type * Row "+i+", Not sorted.']",dataFile,"TermsAndConditions","TermType",i);
        }
        java.util.List<WebElement> term = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Term * Row ')]");
        List<WebElement> comments = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < termsAndConditions.size(); i++) {
            enterListData(term.get(i), dataFile, "TermsAndConditions", "Term", i);
            enterListData(comments.get(i), dataFile, "TermsAndConditions", "Comments", i);
        }
    }


}


