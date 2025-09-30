package com.wings.pages.sales.transactions;

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
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SalesInvoiceAgainstDeliveries extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false,IsAmountHeaderClicked=false,otherChargesGSTCheckBox=false,discountIsClicked=false;

    public SalesInvoiceAgainstDeliveries(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver=driver);
        dataFile = file;
    }

    public String salesInvoiceAgainstDeliveries(String voucherNum,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long invoiceAgainstDeliveriesStart = System.nanoTime();
        System.out.println("SIAD startTime  in :" + invoiceAgainstDeliveriesStart);
        Thread.sleep(100);
        navigateToMastersWhen3Steps("Sales","Invoices","Sales Invoices against Deliveries");
        long generalInfoStart = System.nanoTime();
        System.out.println("Sales Invoice against Deliveries general Info started  in :" + generalInfoStart);
        Thread.sleep(7000);
        String oldVoucherID = oldTTransactionID();
        System.out.println("oldID: " + oldVoucherID);
        //branch selection
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterLocation(dataFile,"GeneralInformation","Location");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterPartyCode(dataFile,"GeneralInformation","PartyAccountCode");
        Thread.sleep(1500);
        gstTransactionType("Intra State Sales to Registered Dealers");
        Thread.sleep(5000);
        selectPendingsSalesOrder(voucherNum, "20250401");
        Thread.sleep(1000);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        enterCustomerEmail(dataFile, "GeneralInformation", "CustomerEmail");
        enterCustomerMobileNum(dataFile, "GeneralInformation", "CustomerMobileNumber");
        enterSalesAccountCode(dataFile, "GeneralInformation", "salesAccountCode");
        generalInfoSliderHandle(300);
        enterCreditPeriod(dataFile, "GeneralInformation", "CreditPeriod");
        enterPriceList(dataFile, "GeneralInformation", "PriceList");
        enterTcsTransNature(dataFile, "GeneralInformation", "tcsTransactionNature");
        enterVoucherDiscount(dataFile, "GeneralInformation", "VoucherDiscountPercentage");
        enterExecutive(dataFile, "GeneralInformation", "executive");
        enterRemarks(dataFile, "GeneralInformation", "remarks");
        generalInfoSliderHandle(-500);
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("General information End:- ", generalInfoEndTime);

        //select pending quantity
        long addProductStart=System.nanoTime();
        addProduct();
        long addProductEnd=System.nanoTime()-addProductStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Deliveries Add Products:- ",addProductEnd);
        //charges and deductions
        long chargesDeductionsStart =System.nanoTime();
        addChargesAndDeductions();
        long chargesDeductionsEnd=System.nanoTime()- chargesDeductionsStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Deliveries Charges and Deductions:- ",chargesDeductionsEnd);
        //other charges
        long otherChargesStart =System.nanoTime();
        addOtherCharges();
        long otherChargesEnd=System.nanoTime()- otherChargesStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Deliveries Other Charges:- ",otherChargesEnd);
        //validate gst
        navigateToBillsPayablesTab();
        common.deleteInvalidRows();
        //cash
        long cashTabStart =System.nanoTime();
        addCash();
        long cashTabEnd =System.nanoTime()- cashTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Deliveries Cash Tab:- ", cashTabEnd);
        //cheques
        long chequesTabStart =System.nanoTime();
        addCheques();
        long chequesTabEnd =System.nanoTime()- chequesTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Deliveries Cheques Tab:- ", chequesTabEnd);
        //post dated cheques
        long postDatedChequesTabStart =System.nanoTime();
        addPostDatedCheques();
        long postDatedChequesTabEnd =System.nanoTime()- postDatedChequesTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Deliveries Post Dated Cheques Tab:- ", postDatedChequesTabEnd);
        //cheques[pdc]
        long chequesPDCTabStart =System.nanoTime();
        addChequesPDC();
        long chequesPDCTabEnd =System.nanoTime()- chequesPDCTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Deliveries Cheques[PDC] Tab:- ", chequesPDCTabEnd);
        //credit card
        long creditCardTabStart =System.nanoTime();
        addCreditCard();
        long creditCardTabEnd =System.nanoTime()- creditCardTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Deliveries Credit Card Tab:- ", creditCardTabEnd);
        //scroll
        common.clickElement("xpath", "//TabItem[contains(@Name,'Paytm')]");
        moveToRight(11);
        //Other info
        long otherInfoTabStart =System.nanoTime();
        otherInfo();
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Deliveries Other Info Tab:- ", otherInfoTabEnd);
        //additional Info
        long additionalInfoTabStart =System.nanoTime();
        additionalInformation();
        long additionalInfoTabEnd =System.nanoTime()- additionalInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Deliveries Additional Info Tab:- ", additionalInfoTabEnd);
        //shipping Address
        long shippingAddressTabStart=System.nanoTime();
        shippingAddress();
        long shippingAddressTabEnd=System.nanoTime()-shippingAddressTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice Against Deliveries Shipping Address Tab: ", shippingAddressTabEnd);
        //dispatch Address
        long dispatchAddressTabStart=System.nanoTime();
        dispatchAddress();
        long dispatchAddressTabEnd=System.nanoTime()-dispatchAddressTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Deliveries Dispatch Address Tab: ", dispatchAddressTabEnd);
        //terms and Cond
        long termsConditionsTabStart =System.nanoTime();
        termsAndCondition();
        long termsConditionsTabEnd =System.nanoTime()- termsConditionsTabStart;
        FileUtil.writeTimeLogInMinutes("Sales Invoice against Deliveries Terms and Conditions Tab:- ", termsConditionsTabEnd);
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //api
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"salesInvoiceAgainstDeliveries");

        return newVoucherID;


    }

    public void addProduct() throws Exception {
        List<String> productsOrderInExcel=getValuesByColumnHeader(dataFile,"Items","Product");
        //read products order from excel
        System.out.println(productsOrderInExcel.size()+"excel products order");
        for (String product : productsOrderInExcel) {
            System.out.println(product);
        }
        //read products order from app
        List<WebElement> productsOrderInApp=common.findWebElements("xpath","//Table[@Name='Items']/*[starts-with(@Name,'Row ')]/Edit[starts-with(@Name,'Product * Row ')]");
        System.out.println(productsOrderInApp.size()+"application products order");
        for (WebElement element:productsOrderInApp){
            System.out.println(element.getText());
        }
        List<String> productCode=readExcelData(dataFile,"Items","ProductCode");
        List<String> masterType=readExcelData(dataFile,"Items","MasterType");
        List<WebElement> salesAccount = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Sales Account * Row ')]");
        List<WebElement> uom = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
        List<WebElement> location = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Location * Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity Row ')]");
        List<WebElement> freeQuantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Free Quantity Row ')]");
        List<WebElement> numOfPacksRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'No Of Packs Row ')]");
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
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 300, 0);
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
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 650, 0);
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        List<WebElement> Info1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 1 Row ')]");
        List<WebElement> Info2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 2 Row ')]");
        List<WebElement> Info3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 3 Row ')]");
        List<WebElement> Info4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 4 Row ')]");
        List<WebElement> Info5 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 5 Row ')]");
        List<WebElement> Value1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value1 Row ')]");
        List<WebElement> Value2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value2 Row ')]");
        List<WebElement> Value3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value3 Row ')]");
        List<WebElement> Value4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value4 Row ')]");
        List<WebElement> Value5 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value5 Row ')]");
        List<WebElement> Date1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date 1 Row ')]");
        List<WebElement> Date2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date 2 Row ')]");
        List<WebElement> Date3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date 3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 50, 0);
        List<WebElement> Bool1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 1 Row ')]");
        List<WebElement> Bool2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool2 Row ')]");
        List<WebElement> Bool3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1300, 0);

        Set<Integer> usedAppRows = new HashSet<>();
        for (int i = 0; i < productCode.size(); i++) {
            String excelProduct = productsOrderInExcel.get(i);

            for (int j = 0; j < productsOrderInApp.size(); j++) {
                String appProduct = productsOrderInApp.get(j).getText().trim();

                if (excelProduct.equalsIgnoreCase(appProduct) && !usedAppRows.contains(j)) {
                    enterListData(salesAccount.get(i), dataFile, "Items", "SalesAccount", j);
                    enterListData(uom.get(i), dataFile, "Items", "UOM", j);
                    enterListData(location.get(i), dataFile, "Items", "Location", j);
                    enterListData(storageBin.get(i), dataFile, "Items", "StorageBin", j);
                    common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 150, 0);
                    enterListData(quantity.get(i), dataFile, "Items", "Quantity", j);
                    enterListData(freeQuantity.get(i), dataFile, "Items", "FreeQuantity", j);
                    enterListData(numOfPacksRowList.get(i), dataFile, "Items", "NoOfPacks", j);
                    enterListData(Mrp.get(i), dataFile, "Items", "MRP", j);
                    common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
                    enterListData(minimumRate.get(i),dataFile,"Items","MinimumRate",j );
                    enterListData(maximunRate.get(i),dataFile,"Items","MaximumRate",j );
                    enterListData(UnitRate.get(i), dataFile, "Items", "UnitRate", j);
                    enterListData(voucherDiscount.get(i), dataFile, "Items", "VoucherDiscountPercentage", j);
                    enterListData(partyDiscount.get(i), dataFile, "Items", "PartyDiscountPercentage", j);
                    enterListData(DiscountBasis1.get(i), dataFile, "Items", "DiscountBasis1", j);
                    enterListData(Discount1.get(i), dataFile, "Items", "Discount1", j);
                    enterListData(DiscountBasis2.get(i), dataFile, "Items", "DiscountBasis2", j);
                    enterListData(Discount2.get(i), dataFile, "Items", "Discount2", j);
                    enterListData(DiscountBasis3.get(i), dataFile, "Items", "DiscountBasis3", j);
                    enterListData(Discount3.get(i), dataFile, "Items", "Discount3", j);
                    enterListData(HSNCode.get(i), dataFile, "Items", "HSN", j);
                    enterListData(GSTProductCategory.get(i), dataFile, "Items", "GSTProductCategory", j);
                    enterListData(CESSProductCategory.get(i), dataFile, "Items", "CESSProductCategory", j);
                    common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 225, 0);
                    enterListData(Department.get(i), dataFile, "Items", "Department", j);
                    enterListData(Project.get(i), dataFile, "Items", "Project", j);
                    enterListData(ProfitCentre.get(i), dataFile, "Items", "ProfitCentre", j);
                    enterListData(CostCentre.get(i), dataFile, "Items", "CostCentre", j);
                    enterListData(Comments.get(i), dataFile, "Items", "Comments", j);
                    enterListData(Info1.get(i), dataFile, "Items", "Info1", j);
                    enterListData(Info2.get(i), dataFile, "Items", "Info2", j);
                    enterListData(Info3.get(i), dataFile, "Items", "Info3", j);
                    enterListData(Info4.get(i), dataFile, "Items", "Info4", j);
                    enterListData(Info5.get(i), dataFile, "Items", "Info5", j);
                    enterListData(Value1.get(i), dataFile, "Items", "Value1", j);
                    enterListData(Value2.get(i), dataFile, "Items", "Value2", j);
                    enterListData(Value3.get(i), dataFile, "Items", "Value3", j);
                    enterListData(Value4.get(i), dataFile, "Items", "Value4", j);
                    enterListData(Value5.get(i), dataFile, "Items", "Value5", j);
                    common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 38, 0);
                    enterListDate(Date1.get(i), dataFile, "Items", "Date1", j);
                    enterListDate(Date2.get(i), dataFile, "Items", "Date2", j);
                    enterListDate(Date3.get(i), dataFile, "Items", "Date3", j);
                    common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 70, 0);
                    clickListData(Bool1.get(i));
                    clickListData(Bool2.get(i));
                    clickListData(Bool3.get(i));
                    common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1250, 0);
                    usedAppRows.add(j);
                    break;
                }
            }
        }
    }

    public void addChargesAndDeductions() throws IOException {
        navigateToChargesAndDeductionsTab();
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

    public void addOtherCharges() throws IOException, InterruptedException, ParseException {
        navigateToOtherChargesTab();
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
            enterListData(amountRowList.get(i), dataFile, "OtherCharges", "Amount", i);
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

    public void addCash() throws IOException {
        navigateToCashTab();
        List<String> cashTab =readExcelData(dataFile,"Cash","CashAccountCode");
        for (int i = 0; i < cashTab.size() ; i++) {
            addData("xpath","//Edit[@Name='Cash Account Code Row "+i+", Not sorted.']",dataFile,"Cash","CashAccountCode",i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
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
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(0).getText());
        elements.get(0).click();
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
        common.clickElement("xpath","//TabItem[contains(@Name,'Post Dated Cheques')]");
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
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(2).getText());
        elements.get(2).click();
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
        common.clickElement("xpath","//TabItem[contains(@Name,'Credit Card')]");
        List<String> creditCards =readExcelData(dataFile,"CreditCard","SwipeMachineType");
        for (int i = 0; i < creditCards.size(); i++) {
            addData("xpath","//Edit[@Name='Swipe Machine Type * Row "+i+", Not sorted.']",dataFile,"CreditCard","SwipeMachineType",i);
        }
        List<WebElement> swipeTypeRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Swipe Type * Row ')]");
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> cardNo = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Card No Row ')]");
        List<WebElement> expiryDate = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Expiry Date Row ')]");
        List<WebElement> approvalNo = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Approval No * Row ')]");
        List<WebElement> chargesAccRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Account Code Row ')]");
        List<WebElement> chargesPercentageRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Percentage Row ')]");
        List<WebElement> executiveRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='CreditCard']/*/Thumb[@Name='Position']", 500, 0);
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='CreditCard']/*/Thumb[@Name='Position']", -500, 0);
        for (int i = 0; i < creditCards.size() ; i++) {
            enterListData(swipeTypeRowList.get(i), dataFile, "CreditCard", "SwipeType",i);
            enterListData(amountRowList.get(i), dataFile, "CreditCard", "Amount",i);
            enterListData(tdsTransNatureRowList.get(i), dataFile, "CreditCard", "TDSTransactionNature",i);
            enterListData(tdsAccountRowList.get(i), dataFile, "CreditCard", "TDSAccount",i);
            enterListData(tdsAmountRowList.get(i), dataFile, "CreditCard", "TDSAmount",i);
            enterListData(cardNo.get(i), dataFile, "CreditCard", "CardNo",i);
            enterListDate(expiryDate.get(i), dataFile, "CreditCard", "ExpiryDate",i);
            enterListData(approvalNo.get(i), dataFile, "CreditCard", "ApprovalNo",i);
            enterListData(chargesAccRowList.get(i), dataFile, "CreditCard", "ChargesAccountCode" , i);
            enterListData(chargesPercentageRowList.get(i), dataFile, "CreditCard", "Percentage" , i);
            enterListData(executiveRowList.get(i),dataFile,"CreditCard","Executive",i);
            enterListData(departmentRowList.get(i),dataFile,"CreditCard","Department",i);
            enterListData(projectRowList.get(i),dataFile,"CreditCard","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"CreditCard","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"CreditCard","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"CreditCard","Comments",i);
            common.sliderHandling("xpath", "//Table[@Name='CreditCard']/*/Thumb[@Name='Position']", -600, 0);
        }
    }

    public void otherInfo() throws InterruptedException, IOException {
        navigateToOtherInfoTab();
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
        common.clickElement("xpath","//TabItem[contains(@Name,'Additional Information  ')]");
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
//        common.clickElement("xpath","//CheckBox[@Name='Bool 3']");
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

    public void dispatchAddress(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Despatch Address  ')]");
        EnterData("//Edit[@Name='Address1']",dataFile,"ShippingAddress","Address1");
        EnterData("//Edit[@Name='Address2']",dataFile,"ShippingAddress","Address2");
        EnterData("//Edit[@Name='Address3']",dataFile,"ShippingAddress","Address3");
        EnterData("//Edit[@Name='City']",dataFile,"ShippingAddress","City");
        EnterData("//Edit[@Name='State']",dataFile,"ShippingAddress","State");
        EnterData("//Edit[@Name='Country']",dataFile,"ShippingAddress","Country");
        EnterData("//Edit[@Name='Zip']",dataFile,"ShippingAddress","Zip");
        EnterData("//Edit[@Name='Telephone No']",dataFile,"ShippingAddress","TelephoneNo");
        EnterData("//Edit[@Name='Mobile No']",dataFile,"ShippingAddress","MobileNo");
    }

    public  void termsAndCondition() throws IOException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Terms And Conditions')]");
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