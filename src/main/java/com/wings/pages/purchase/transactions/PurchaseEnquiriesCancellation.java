package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PurchaseEnquiriesCancellation extends TransactionsBaseClass {
    WindowsDriver driver,rootDriver;
    Common common;
    String dataFile;

    public PurchaseEnquiriesCancellation(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchaseEnquiriesCancellation(String voucherNum,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws InterruptedException, IOException, ParseException {
        long start = System.nanoTime();
        System.out.println("Purchase Enquiries Cancellation Starts"+start);

        navigateToMastersWhen3Steps("Purchase","Enquiries","Purchase Enquiries Cancellation");
        Thread.sleep(2000);
        String oldVoucherID = oldTTransactionID();
        long generalInfoStart = System.nanoTime();
        System.out.println("Purchase Voucher General information started executed in :" + generalInfoStart);

        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterPartyCode(dataFile,"GeneralInformation","PartyAccountCode");
        selectPendingsSalesOrder(voucherNum,"20250401");
        Thread.sleep(1000);
        common.clickElement("xpath","//Button[@Name='OK']");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Purchase Voucher General information End:- ", generalInfoEndTime);


        long addProductStart=System.nanoTime();
        addProducts();
        long addProductEnd=System.nanoTime()-addProductStart;
        FileUtil.writeTimeLogInMinutes("Add Products:- ",addProductEnd);

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

        long termsConditionsTabStart =System.nanoTime();
        termsAndCondition();
        long termsConditionsTabEnd =System.nanoTime()- termsConditionsTabStart;
        FileUtil.writeTimeLogInMinutes("Terms and Conditions Tab:- ", termsConditionsTabEnd);

        transactionSave();
        String transactionId = newTransactionID(oldVoucherID);

        long PurchaseEnquiriesEnd = System.nanoTime() - start;
        FileUtil.writeTimeLog("purchaseEnquiries validating Tab Items UpTo summary", PurchaseEnquiriesEnd /1000000000);
//        exportIOFiles(transactionId,rootDriver);

        APIClient.validateAPIWithExcel(transactionId,tempAPIBodyUpdate,apiResponse,outputFile,"PurchaseEnquiriesCancellation");

    }

    public void addProducts() throws  IOException {
        List<String> productCode=readExcelData(dataFile,"Items","ProductCode");
        List<WebElement> productUOMRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row')]");
        List<WebElement> productQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity * Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        List<WebElement> Info1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 1 Row ')]");
        List<WebElement> Info2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 2 Row ')]");
        List<WebElement> Info3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 3 Row ')]");
        List<WebElement> Info4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 4 Row ')]");
        List<WebElement> Info5 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 5 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
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
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -500, 0);

        for (int j = 0; j < productCode.size() ; j++) {
            enterListData(productUOMRowList.get(j), dataFile, "Items", "UOM",j);
            enterListData(productQuantityRowList.get(j), dataFile, "Items", "Quantity",j);
            enterListData(commentsRowList.get(j),dataFile,"Items","Comments",j);
            enterListData(Info1.get(j),dataFile,"Items","Info1",j);
            enterListData(Info2.get(j),dataFile,"Items","Info2",j);
            enterListData(Info3.get(j),dataFile,"Items","Info3",j);
            enterListData(Info4.get(j),dataFile,"Items","Info4",j);
            enterListData(Info5.get(j),dataFile,"Items","Info5",j);
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 150, 0);
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
                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -600, 0);
            }
        }
    }

    public void otherInfo() throws InterruptedException, IOException {
        navigateToOtherInfoTab();
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(500);
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='OtherInfo 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='OtherInfo 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='OtherInfo 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='OtherInfo 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='OtherInfo 5']",dataFile,"OtherInfo","OtherInfo5");
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

