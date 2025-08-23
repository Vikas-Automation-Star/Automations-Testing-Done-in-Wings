package com.wings.pages.purchase.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import java.io.IOException;
import java.util.List;

public class PurchaseQuotationsAgainstEnquiries extends TransactionsBaseClass {
    WindowsDriver driver,rootDriver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false,IsAmountHeaderClicked=false,otherChargesGSTCheckBox=false,discountIsClicked=false;

    public PurchaseQuotationsAgainstEnquiries(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String purchaseQuotationsAgainstEnquiry(String voucherNum,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long start1 = System.nanoTime();
        System.out.println("Purchase quotations against enquiries start at :"+start1);


        navigateToMastersWhen3Steps("Purchase","Quotations","Purchase Quotations against Enquiries");
        Thread.sleep(3000);
        String oldVoucherID = oldTTransactionID();

        long generalInfoStart = System.nanoTime();
        System.out.println("Sales Invoice general Info started executed in :" + generalInfoStart);

        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterPartyCode(dataFile,"GeneralInformation","PartyAccountCode");
        Thread.sleep(2000);
        gstTransactionType("Intra State Purchase from Registered Dealers");
        Thread.sleep(2000);
        selectPendingsSalesOrder(voucherNum,"20250401");
        Thread.sleep(7000);
        common.clickElement("xpath","//Button[@Name='OK']");
        enterCreditPeriod(dataFile,"GeneralInformation","CreditPeriod");
        enterPriceList(dataFile,"GeneralInformation","PriceList");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Purchase Quotations Against Enquiries General information End:- ", generalInfoEndTime);


        //F3-Items
        long addProductStart=System.nanoTime();
        addProducts();
        long addProductEnd=System.nanoTime()-addProductStart;
        FileUtil.writeTimeLogInMinutes("Add Products:- ",addProductEnd);

        //charges and deductions
        long chargesDeductionsStart =System.nanoTime();
        addChargesAndDeductions();
        long chargesDeductionsEnd=System.nanoTime()- chargesDeductionsStart;
        FileUtil.writeTimeLogInMinutes("Charges and Deductions:- ",chargesDeductionsEnd);

        //Other info
        long otherInfoTabStart =System.nanoTime();
        otherInfo();
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Other Info Tab:- ", otherInfoTabEnd);

        //additional Info
        long additionalInfoTabStart =System.nanoTime();
        additionalInformation();
        long additionalInfoTabEnd =System.nanoTime()- additionalInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Additional Info Tab:- ", additionalInfoTabEnd);

        //terms and Cond
        long termsConditionsTabStart =System.nanoTime();
        termsAndCondition();
        long termsConditionsTabEnd =System.nanoTime()- termsConditionsTabStart;
        FileUtil.writeTimeLogInMinutes("Terms and Conditions Tab:- ", termsConditionsTabEnd);

        transactionSave();
        String transactionId = newTransactionID(oldVoucherID);
        Thread.sleep(1000);
//        exportIOFiles(transactionId,rootDriver);

        APIClient.validateAPIWithExcel(transactionId,tempAPIBodyUpdate,apiResponse,outputFile,"PurchaseQuotationsAgainstEnquiries");
        long duration1 = System.nanoTime() - start1;
        FileUtil.writeTimeLog("purchaseQuotationsAgainstEnquiry validating Tab Items UpTo summary",duration1/1000000000);

        return transactionId;
    }

    public void addProducts() throws InterruptedException, IOException {
        List<String> productCode=readExcelData(dataFile,"Items","ProductCode");
        List<WebElement> productUOMRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row')]");
        List<WebElement> productQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity * Row ')]");
        List<WebElement> deliverDate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Delivery Date Row ')]");
        List<WebElement> mrpRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'MRP Row ')]");
        List<WebElement> unitRateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Unit Rate Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
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
        List<WebElement>  disount3BasisRowList= common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 3 Row ')]");
        List<WebElement> disount3RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 3 Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> GSTProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'GST Product Category Row ')]");
        List<WebElement> CESSProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'CESS Product Category Row ')]");
        if (!gstAmountClicked) {
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 800, 0);
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
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
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -2000, 0);

        for (int j = 0; j < productCode.size() ; j++) {
            enterListData(productUOMRowList.get(j), dataFile, "Items", "UOM",j);
            enterListData(productQuantityRowList.get(j), dataFile, "Items", "Quantity",j);
            enterListDate(deliverDate.get(j),dataFile,"Items","DeliveryDate",j);
            enterListData(mrpRowList.get(j),dataFile,"Items","MRP",j);
            enterListData(unitRateRowList.get(j),dataFile,"Items","UnitRate",j);
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",470, 0);
            enterListData(discountBasis1RowList.get(j),dataFile,"Items","DiscountBasis1",j);
            enterListData(disount1RowList.get(j),dataFile,"Items","Discount1",j);
            enterListData(disount2BasisRowList.get(j),dataFile,"Items","DiscountBasis2",j);
            enterListData(disount2RowList.get(j),dataFile,"Items","Discount2",j);
            enterListData(disount3BasisRowList.get(j),dataFile,"Items","DiscountBasis3",j);
            enterListData(disount3RowList.get(j),dataFile,"Items","Discount3",j);
            enterListData(hsnCodeRowList.get(j),dataFile,"Items","HSN",j);
            enterListData(GSTProductCategory.get(j),dataFile,"Items","GSTProductCategory",j);
            Thread.sleep(1500);
            enterListData(CESSProductCategory.get(j),dataFile,"Items","CESSProductCategory",j);
            enterListData(departmentRowList.get(j),dataFile,"Items","Department",j);
            enterListData(projectRowList.get(j),dataFile,"Items","Project",j);
            enterListData(profitCentreRowList.get(j),dataFile,"Items","ProfitCentre",j);
            enterListData(costCentreRowList.get(j),dataFile,"Items","CostCentre",j);
            enterListData(commentsRowList.get(j),dataFile,"Items","Comments",j);
            enterListData(Info1.get(j),dataFile,"Items","Info1",j);
            enterListData(Info2.get(j),dataFile,"Items","Info2",j);
            enterListData(Info3.get(j),dataFile,"Items","Info3",j);
            enterListData(Info4.get(j),dataFile,"Items","Info4",j);
            enterListData(Info5.get(j),dataFile,"Items","Info5",j);
            enterListData(Value1.get(j),dataFile,"Items","Value1",j);
            enterListData(Value2.get(j),dataFile,"Items","Value2",j);
            enterListData(Value3.get(j),dataFile,"Items","Value3",j);
            enterListData(Value4.get(j),dataFile,"Items","Value4",j);
            enterListData(Value5.get(j),dataFile,"Items","Value5",j);
            enterListDate(Date1.get(j),dataFile,"Items","Date1",j);
            enterListDate(Date2.get(j),dataFile,"Items","Date2",j);
            enterListDate(Date3.get(j),dataFile,"Items","Date3",j);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 70, 0);
            clickListData(Bool1.get(j));
            clickListData(Bool2.get(j));
            clickListData(Bool3.get(j));
            if (j<4) {
                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -2500, 0);
            }
        }
    }

    public void addChargesAndDeductions() throws IOException {
        navigateToChargesAndDeductionsTab();
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

    public void otherInfo() throws InterruptedException, IOException {
        navigateToOtherInfoTab();
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(1000);
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }

    public void  additionalInformation() throws IOException {
        navigateToAdditionalInfo();
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
        navigateToTermsAndConditions();
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
