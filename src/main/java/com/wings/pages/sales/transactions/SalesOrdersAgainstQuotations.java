package com.wings.pages.sales.transactions;

import com.wings.pages.TransactionsBaseClass;
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
import java.util.List;

public class SalesOrdersAgainstQuotations extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean discountIsClicked = false,gstAmountClicked=false,IsAmountHeaderClicked = false,otherChargesGSTCheckBox=false;

    public SalesOrdersAgainstQuotations(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String salesOrderAgainstQuotation(String voucherNum) throws InterruptedException, IOException, ParseException, AWTException {
        long start = System.nanoTime();
//        System.out.println("Sales order against quotation startTime executed in :"+start);
        long generalInfoStart = System.nanoTime();
        navigateToSalesOrderAgainstQuotationsMenu();
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        //gen info
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterExchangeRate(dataFile,"GeneralInformation","ExchangeRate");
        enterPartyCode(dataFile,"GeneralInformation","PartyAccountCode");
        Thread.sleep(3000);
        gstTransactionType("Intra State Sales to Registered Dealers");
        Thread.sleep(1000);
        selectPendingsSalesOrder(voucherNum, "20250401");
        Thread.sleep(2500);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("SOQA General Information End:- ", generalInfoEndTime);

        long addProductStart = System.nanoTime();
        addProduct();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("Add Products:- ", addProductEnd);

        long chargesDedudctionStart = System.nanoTime();
        addChargesAndDeductions();
        long chargesDeductionsEnd = System.nanoTime() - chargesDedudctionStart;
        FileUtil.writeTimeLogInMinutes("Charges And Deductions:- ", chargesDeductionsEnd);

        long otherChargesStart = System.nanoTime();
        addOtherCharges();
        long otherChargesEnd = System.nanoTime() - otherChargesStart;
        FileUtil.writeTimeLogInMinutes("Other Charges:- ", otherChargesEnd);

        long cashStart=System.nanoTime();
        addCashSalesInvoice();
        long cashEnd=System.nanoTime() - cashStart;
        FileUtil.writeTimeLogInMinutes("Cash Tab:- ",cashEnd);

        long chequesStart=System.nanoTime();
        addChequesSalesInvoice();
        long chequesEnd=System.nanoTime()- chequesStart;
        FileUtil.writeTimeLogInMinutes("Cheques Tab:- ",chequesEnd);

        long postDatedChequesStart=System.nanoTime();
        addPostDatedChequesSalesInvoice();
        long postDatedChequesEnd=System.nanoTime() - postDatedChequesStart;
        FileUtil.writeTimeLogInMinutes("Post Dated Cheques Tab:- ",postDatedChequesEnd);

        long chequesPDCStart=System.nanoTime();
        addChequesPDCSalesInvoice();
        long chequesPDCEnd=System.nanoTime() - chequesPDCStart;
        FileUtil.writeTimeLogInMinutes("Cheques PDC Tab:- ", chequesPDCEnd);

        long creditCardStart= System.nanoTime();
        addCreditCardSalesInvoice();
        long creditCardEnd=System.nanoTime() - creditCardStart;
        FileUtil.writeTimeLogInMinutes("Credit Card Tab:- ", creditCardEnd);

        long otherInfoStart = System.nanoTime();
        otherInfo();
        long otherInfoEnd = System.nanoTime() - otherInfoStart;
        FileUtil.writeTimeLogInMinutes("OtherInfo Tab:- ", otherInfoEnd);

        long additionalInformationStart = System.nanoTime();
        additionalInformation();
        long additionalInformationEnd = System.nanoTime() - additionalInformationStart;
        FileUtil.writeTimeLogInMinutes("Additional Information:- ", additionalInformationEnd);

        long termsConditionsStart = System.nanoTime();
        termsAndCondition();
        long termsConditionsEnd = System.nanoTime() - termsConditionsStart;
        FileUtil.writeTimeLogInMinutes("Terms And Conditions:- ", termsConditionsEnd);

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");

        long transEnd=System.nanoTime()-start;
        FileUtil.writeTimeLogInMinutes("SOAQ ended at ",transEnd);
        return newVoucherID;
    }

    public void addProduct() throws IOException, ParseException {
        List<String> productCode=readExcelData(dataFile,"Items","ProductCode");
        System.out.println("productCodes :"+productCode.size());

//        for (int i = 0; i < productCode.size() ; i++) {
//            addData("xpath","//Edit[@Name='Product Code Row "+i+", Not sorted.']",dataFile,"Items","ProductCode",i);
//        }
        List<WebElement> uom = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Quantity * Row ')]");
        List<WebElement> DeliveryDate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Delivery Date Row ')]");
        List<WebElement> Mrp = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'MRP Row ')]");
        List<WebElement> UnitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Unit Rate Row ')]");
        List<WebElement> voucherDiscount = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Voucher Disc % Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 400, 0);
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
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 700, 0);
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
        List<WebElement> Bool1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 1 Row ')]");
        List<WebElement> Bool2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 2 Row ')]");
        List<WebElement> Bool3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1500, 0);

        for (int i = 0; i <productCode.size() ; i++) {
            enterListData(uom.get(i),dataFile,"Items","UOM",i);
            enterListData(quantity.get(i),dataFile,"Items","Quantity",i);
            enterListDate(DeliveryDate.get(i),dataFile,"Items","DeliveryDate",i);
            enterListData(Mrp.get(i),dataFile,"Items","MRP",i);
            enterListData(UnitRate.get(i),dataFile,"Items","UnitRate",i);
            enterListData(voucherDiscount.get(i),dataFile,"Items","voucherDiscount",i);
            enterListData(partyDiscount.get(i),dataFile,"Items","partyDiscount",i);


            if (!discountIsClicked) {
                common.clickElement("xpath", "//Header[@Name='Disc Amount 1']");
                common.clickElement("xpath", "//Header[@Name='Disc Amount 2']");
                common.clickElement("xpath", "//Header[@Name='Disc Amount 3']");
                discountIsClicked = true;
            }
            enterListData(DiscountBasis1.get(i),dataFile,"Items","DiscountBasis1",i);
            enterListData(Discount1.get(i),dataFile,"Items","Discount1",i);
            enterListData(DiscountBasis2.get(i),dataFile,"Items","DiscountBasis2",i);
            enterListData(Discount2.get(i),dataFile,"Items","Discount2",i);
            enterListData(DiscountBasis3.get(i),dataFile,"Items","DiscountBasis3",i);
            enterListData(Discount3.get(i),dataFile,"Items","Discount3",i);
            enterListData(HSNCode.get(i),dataFile,"Items","HSN",i);
            enterListData(GSTProductCategory.get(i),dataFile,"Items","GSTProductCategory",i);
            enterListData(CESSProductCategory.get(i),dataFile,"Items","CESSProductCategory",i);
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
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 150, 0);
            enterListDate(Date1.get(i),dataFile,"Items","Date1",i);
            enterListDate(Date2.get(i),dataFile,"Items","Date2",i);
            enterListDate(Date3.get(i),dataFile,"Items","Date3",i);
            clickListData(Bool1.get(i));
            clickListData(Bool2.get(i));
            clickListData(Bool3.get(i));
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1500, 0);
        }
    }

    public void addChargesAndDeductions() throws IOException {
        navigateToChargesAndDeductionsTab();
        List<String> chargesAndDeductions=readExcelData(dataFile,"ChargesAndDeductions","ChargesOrDeductions");
        System.out.println("productCodes :"+chargesAndDeductions.size());
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
        List<WebElement> taxableValueRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Taxable Value Row ')]");
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
            enterListData(hsnCodeRowList.get(i), dataFile, "OtherCharges", "HSNCode",i);
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


    public void addCashSalesInvoice() throws IOException, ParseException {
        navigateToCashTab();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesInvoice", "productCount")); v++) {
            enterInput("xpath","//Edit[@Name='Cash Account Code Row "+v+", Not sorted.']",dataFile,"salesInvoice","cashAccount"+v);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <6 ; i++) {
            enterData(amountRowList.get(i), dataFile, "salesInvoice", "cashAmount"+i);
            enterData(departmentRowList.get(i),dataFile,"salesInvoice","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesInvoice","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesInvoice","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesInvoice","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesInvoice","comments"+i);
        }
    }

    public void addChequesSalesInvoice() throws IOException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(0).getText());
        elements.get(0).click();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesInvoice", "productCount")); v++) {
            enterInput("xpath","//Edit[@Name='Bank Account Code Row "+v+", Not sorted.']",dataFile,"salesInvoice","bankAccount"+v);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> chargesAccRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Account Code Row ')]");
        List<WebElement> chargesAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Row ')]");
        List<WebElement> netAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount Row ')]");
        List<WebElement> netAmountINCompanyCurrencyRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Net Amount In Company Currency Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <6 ; i++) {
            enterData(amountRowList.get(i), dataFile, "salesInvoice", "chequeAmount" + i);
            common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row "+i+", Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
            common.findWebElement("xpath","//Edit[@Name='Cheque Date * Row "+i+", Not sorted.']").sendKeys(Time.timeStamp());
            enterData(drawnOnRowList.get(i), dataFile, "salesInvoice", "drawnOn"+i);
            enterData(drawnOnBranchBranchRowList.get(i), dataFile, "salesInvoice", "drawnOnBranch");
            enterData(chargesAccRowList.get(i), dataFile, "salesInvoice", "onlyCharges" + i);
            enterData(chargesAmountRowList.get(i), dataFile, "salesInvoice", "chargesAmount" + i);
            Assert.assertEquals(netAmountRowList.get(i).getText(), common.getData(dataFile, "salesInvoice","expectedChequeNetAmount"+i),"expectedChequeAmount mismatch");
            Assert.assertEquals(netAmountINCompanyCurrencyRowList.get(i).getText(), common.getData(dataFile, "salesInvoice","expectedChequeAmountInCompanyCurrency"+i),"expectedChequeAmountInCompanyCurrency mismatch");
            enterData(departmentRowList.get(i),dataFile,"salesInvoice","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesInvoice","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesInvoice","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesInvoice","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesInvoice","comments"+i);
            common.sliderHandling("xpath", "//Table[@Name='Cheques']/*/Thumb[@Name='Position']", -600, 0);

        }

    }
    public void addPostDatedChequesSalesInvoice() throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Post Dated Cheques')]");
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesInvoice", "productCount")); v++) {
            enterInput("xpath","//Edit[@Name='PDC Account Code Row "+v+", Not sorted.']",dataFile,"salesInvoice","pdcAccount"+v);
        }
        List<WebElement> amoutRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <6 ; i++) {
            enterData(amoutRowList.get(i), dataFile, "salesInvoice", "chequeAmount" + i);
            common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row "+i+", Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
            enterData(drawnOnRowList.get(i), dataFile, "salesInvoice", "drawnOn"+i);
            enterData(drawnOnBranchBranchRowList.get(i), dataFile, "salesInvoice", "drawnOnBranch");
            enterData(departmentRowList.get(i),dataFile,"salesInvoice","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesInvoice","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesInvoice","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesInvoice","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesInvoice","comments"+i);
        }
    }
    public void addChequesPDCSalesInvoice() throws IOException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(2).getText());
        elements.get(2).click();
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesInvoice", "productCount")); v++) {
            enterInput("xpath","//Edit[@Name='Bank Account Code Row "+v+", Not sorted.']",dataFile,"salesInvoice","bankAccount"+v);

        }
        List<WebElement> chequeAmountRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBranchRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i <6 ; i++) {
            enterData(chequeAmountRowList.get(i), dataFile, "salesInvoice", "chequeAmount"+i);
            common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row "+i+", Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
            enterData(drawnOnRowList.get(i), dataFile, "salesInvoice", "drawnOn"+i);
            enterData(drawnOnBranchRowList.get(i), dataFile, "salesInvoice", "drawnOnBranch");
            enterData(departmentRowList.get(i),dataFile,"salesInvoice","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesInvoice","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesInvoice","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesInvoice","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesInvoice","comments"+i);
        }
    }
    public void addCreditCardSalesInvoice() throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Credit Card')]");
        for (int v = 0; v < Integer.parseInt(common.getData(dataFile,"salesInvoice", "productCount")); v++) {
            enterInput("xpath","//Edit[@Name='Swipe Machine Type * Row "+v+", Not sorted.']",dataFile,"salesInvoice","swipeMachineType"+v);

        }
        List<WebElement> swipeTypeRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Swipe Type * Row ')]");
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> executiveRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <6 ; i++) {
            enterData(swipeTypeRowList.get(i), dataFile, "salesInvoice", "swipeType"+i);
            enterData(amountRowList.get(i), dataFile, "salesInvoice", "creditCardAmount"+i);
            common.findWebElement("xpath","//Edit[@Name='Card No Row "+i+", Not sorted.']").sendKeys("852741"+common.getRandom());
            common.clickElement("xpath","//Edit[@Name='Approval No * Row "+i+", Not sorted.']");
            common.findWebElement("xpath","//Edit[@Name='Approval No * Row "+i+", Not sorted.']").sendKeys("75241"+common.getRandom());
            enterData(executiveRowList.get(i),dataFile,"salesInvoice","executive"+i);
            enterData(departmentRowList.get(i),dataFile,"salesInvoice","department"+i);
            enterData(projectRowList.get(i),dataFile,"salesInvoice","project"+i);
            enterData(profitCentreRowList.get(i),dataFile,"salesInvoice","profitCentre"+i);
            enterData(costCentreRowList.get(i),dataFile,"salesInvoice","costCentre"+i);
            enterData(commentsRowList.get(i),dataFile,"salesInvoice","comments"+i);
        }
    }

    public void otherInfo() throws InterruptedException, IOException {
        navigateToOtherInfoTab();
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(5000);
        common.clickElement("xpath","//Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill No']",dataFile,"","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }

    public void  additionalInformation() throws IOException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Additional Information  ')]");
        EnterData("//Edit[@Name='Info 1']",dataFile,"AdditionalInformation","Info1");
        EnterData("//Edit[@Name='Info 2']",dataFile,"AdditionalInformation","Info");
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