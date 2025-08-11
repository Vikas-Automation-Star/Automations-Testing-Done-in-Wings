package com.wings.pages.sales.transactions;

import com.wings.pages.TransactionsBaseClass;
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
import java.util.List;

public class SalesOrders extends TransactionsBaseClass {
    WindowsDriver driver,rootDriver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;
    boolean IsAmountHeaderClicked=false;
    boolean otherChargesGSTCheckBox=false;
    boolean discountIsClicked=false;

    public SalesOrders(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String salesOrders() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToSalesOrderMenu();
        Thread.sleep(3000);
        String oldVoucherID = oldTTransactionID();
        System.out.println("oldID: " + oldVoucherID);
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterExchangeRate(dataFile,"GeneralInformation","ExchangeRate");
        enterPartyCode(dataFile,"GeneralInformation","PartyAccountCode");
        Thread.sleep(3000);
        gstTransactionType("Intra State Sales to Registered Dealers");
        enterVoucherDisc(dataFile,"GeneralInformation","VoucherDiscountPercentage");
        common.clickElement("xpath","//CheckBox[@Name='Advance Receipts']");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        //F3-Items
        long addProductStart=System.nanoTime();
        addProductSalesOrder();
        long addProductEnd=System.nanoTime()-addProductStart;
        FileUtil.writeTimeLogInMinutes("Sales Order Add Products:- ",addProductEnd);


        long start1 = System.nanoTime();
        navigateToChargesAndDeductionsTab();
        addChargesAndDeductions();
        long duration1 = System.nanoTime() - start1;
        FileUtil.writeTimeLogInMinutes("Charges And Deductions ", duration1);


        //other charges
        long otherChargesStart =System.nanoTime();
        navigateToOtherChargesTab();
        addOtherCharges();
        long otherChargesEnd=System.nanoTime()- otherChargesStart;
        FileUtil.writeTimeLogInMinutes("Other Charges:- ",otherChargesEnd);

        //cash
        long cashTabStart =System.nanoTime();
        navigateToCashTab();
        addCash();
        long cashTabEnd =System.nanoTime()- cashTabStart;
        FileUtil.writeTimeLogInMinutes("Cash Tab:- ", cashTabEnd);

        //cheques
        long chequesTabStart =System.nanoTime();
        List<WebElement> cheques=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(cheques.get(0).getText());
        cheques.get(0).click();
        addCheques();
        long chequesTabEnd =System.nanoTime()- chequesTabStart;
        FileUtil.writeTimeLogInMinutes("Cheques Tab:- ", chequesTabEnd);
        //post dated cheques

        long postDatedChequesTabStart =System.nanoTime();
        List<WebElement> postDatedCheques=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(postDatedCheques.get(1).getText());
        postDatedCheques.get(1).click();
        addPostDatedCheques();
        long postDatedChequesTabEnd =System.nanoTime()- postDatedChequesTabStart;
        FileUtil.writeTimeLogInMinutes("Post Dated Cheques Tab:- ", postDatedChequesTabEnd);

        //cheques[pdc]
        long chequesPDCTabStart =System.nanoTime();
        List<WebElement> chequesPDC=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(chequesPDC.get(2).getText());
        chequesPDC.get(2).click();
        addChequesPDC();
        long chequesPDCTabEnd =System.nanoTime()- chequesPDCTabStart;
        FileUtil.writeTimeLogInMinutes("Cheques[PDC] Tab:- ", chequesPDCTabEnd);

        //credit card
        long creditCardTabStart =System.nanoTime();
        navigateToCreditCard();
        addCreditCard();
        long creditCardTabEnd =System.nanoTime()- creditCardTabStart;
        FileUtil.writeTimeLogInMinutes("Credit Card Tab:- ", creditCardTabEnd);


        //other Info
        long pineLabTab =System.nanoTime();
        navigateToPineLib();
        pineLab();
        long pineLabTabEnd =System.nanoTime()- pineLabTab;
        FileUtil.writeTimeLogInMinutes("Other Info Tab:- ", pineLabTabEnd);

        //other Info
        long start3 = System.nanoTime();
        navigateToOtherInfoTab();
        otherInfo();
        long duration3 = System.nanoTime() - start3;
        FileUtil.writeTimeLogInMinutes("OtherInfo ", duration3);

        //additional Info
        long start4 = System.nanoTime();
        navigateToAdditionalInfo();
        additionalInformation();
        long duration4 = System.nanoTime() - start4;
        FileUtil.writeTimeLogInMinutes("Additional Information ", duration4);
        //scroll
        navigateToAdditionalInfo();
        moveToRight(6);

        //Terms And Conditions
        long start5 = System.nanoTime();
        navigateToTermsAndConditions();
        termsAndCondition();
        long duration5 = System.nanoTime() - start5;
        FileUtil.writeTimeLogInMinutes("Terms And Conditions ", duration5);

        //Allocations
        long start12 = System.nanoTime();
        navigateToAllocations();
        addAllocations();
        long duration12 = System.nanoTime() - start12;
        FileUtil.writeTimeLogInMinutes("Enter Allocations ", duration12);

        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
//        String prefix = newVoucherID.replaceAll("\\d", "");
//        String number = newVoucherID.replaceAll("\\D", "");
//        Thread.sleep(2000);
//        navigateToMastersWhen3Steps("Tools","Automated Testing","Generate Input File");
//        rootDriver=common.initializeDriver("Root");
//        Thread.sleep(3000);
//        common.findWebElement("xpath", "//Window[@Name='Export Transaction Postings']/Pane/Edit[@Name='Voucher Series']").sendKeys(prefix);
//        common.findWebElement("xpath","//Window[@Name='Export Transaction Postings']/Pane/Edit[@Name='Voucher Number']").sendKeys(number);
//        common.clickElement("xpath","//Button[@Name='OK']");
//        Thread.sleep(2000);
//        common.clickElement("xpath","//Window[@Name='Export Transaction Postings']/Window[@Name='Export to Excel']/Button[@Name='OK']");
//        Thread.sleep(1500);
//        if (common.findWebElement("xpath","//Text").getText().equals("Data Exported successfully!")) {
//            common.clickElement("xpath", "//Button[@Name='OK']");
//        }
//        else if(common.findWebElement("xpath","//Text").getText().equals("Transactionno doesnot exist.")){
//            Assert.fail("Transaction does not exists");
//            common.clickElement("xpath", "//Button[@Name='OK']");
//        }
//
//        Thread.sleep(2000);
//        navigateToMastersWhen3Steps("Tools","Automated Testing","Generate Output File");
//        rootDriver=common.initializeDriver("Root");
//        Thread.sleep(3000);
//        common.findWebElement("xpath", "//Window[@Name='Export Transaction Postings']/Pane/Edit[@Name='Voucher Series']").sendKeys(prefix);
//        common.findWebElement("xpath","//Window[@Name='Export Transaction Postings']/Pane/Edit[@Name='Voucher Number']").sendKeys(number);
//        common.clickElement("xpath","//Button[@Name='OK']");
//        Thread.sleep(2000);
//        common.clickElement("xpath","//Window[@Name='Export Transaction Postings']/Window[@Name='Export to Excel']/Button[@Name='OK']");
//        Thread.sleep(1500);
//        if (common.findWebElement("xpath","//Text").getText().equals("Data Exported successfully!")) {
//            common.clickElement("xpath", "//Button[@Name='OK']");
//        }
//        else if(common.findWebElement("xpath","//Text").getText().equals("Transactionno doesnot exist.")){
//            Assert.fail("Transaction does not exists");
//            common.clickElement("xpath", "//Button[@Name='OK']");
//        }
        exportIOFiles(newVoucherID,rootDriver);
        return newVoucherID;
    }


    public void addProductSalesOrder() throws  IOException {
        List<String> productCode=readExcelData(dataFile,"Items","ProductCode");
        System.out.println("productCodes :"+productCode.size());
        for (int i = 0; i < productCode.size() ; i++) {
            addData("xpath","//Edit[@Name='Product Code Row "+i+", Not sorted.']",dataFile,"Items","ProductCode",i);
        }
        List<WebElement> uom = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Quantity * Row ')]");
        List<WebElement> DeliveryDate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Delivery Date Row ')]");
        List<WebElement> Mrp = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'MRP Row ')]");
        List<WebElement> minRate= common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Minimum Rate * Row ')]");
        List<WebElement> maxRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Maximum Rate * Row ')]");
        List<WebElement> UnitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Unit Rate Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 630, 0);
        List<WebElement> voucherDisc = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Voucher Disc % Row ')]");
        List<WebElement> partyDisc = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Party Disc % Row ')]");
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
        List<WebElement> GSTProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'GST Product Category Row ')]");
        List<WebElement> CESSProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'CESS Product Category Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 640, 0);
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
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
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 80, 0);
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
            enterListData(minRate.get(i),dataFile,"Items","MinimumRate",i);
            enterListData(maxRate.get(i),dataFile,"Items","MaximumRate",i);
            enterListData(UnitRate.get(i),dataFile,"Items","UnitRate",i);
            enterListData(voucherDisc.get(i),dataFile,"Items","VoucherDiscountPercentage",i);
            enterListData(partyDisc.get(i),dataFile,"Items","PartyDiscountPercentage",i);
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
            enterListDate(Date1.get(i),dataFile,"Items","Date1",i);
            enterListDate(Date2.get(i),dataFile,"Items","Date2",i);
            enterListDate(Date3.get(i),dataFile,"Items","Date3",i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 100, 0);
            clickListData(Bool1.get(i));
            clickListData(Bool2.get(i));
            clickListData(Bool3.get(i));
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1500, 0);
        }
    }

    public void addChargesAndDeductions() throws IOException {
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
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
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

    public void addOtherCharges() throws IOException, InterruptedException {
        List<String> otherChargesProductCode=readExcelData(dataFile,"OtherCharges","AccountCode");
        for (int i = 0; i<otherChargesProductCode.size() ; i++) {
            addData("xpath","//Edit[@Name='Account Code Row "+i+", Not sorted.']",dataFile,"OtherCharges","AccountCode",i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> gstProductCategory = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        List<WebElement> cessProductCategory = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
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

        for (int i = 0; i < otherChargesProductCode.size(); i++) {
            enterListData(amountRowList.get(i),dataFile,"OtherCharges","Amount",i);
            enterListData(hsnCodeRowList.get(i),dataFile,"OtherCharges","HSN",i);
            Thread.sleep(1000);
            enterListData(gstProductCategory.get(i),dataFile,"OtherCharges","GSTProductCategory",i);
            enterListData(cessProductCategory.get(i),dataFile,"OtherCharges","CESSProductCategory",i);
            enterListData(departmentRowList.get(i),dataFile,"OtherCharges","Department",i);
            enterListData(projectRowList.get(i),dataFile,"OtherCharges","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"OtherCharges","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"OtherCharges","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"OtherCharges","Comments",i);
            common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", -550, 0);
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
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
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
            enterListData(drawnOnRowList.get(i), dataFile, "Cheques", "DrawnOnBank",i);
            enterListData(drawnOnBranchBranchRowList.get(i), dataFile, "Cheques", "DrawnOnBankBranch",i);
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
        List<String> postDateDCheques=readExcelData(dataFile,"PostDatedCheques","PDCAccountCode");
        for (int i = 0; i< postDateDCheques.size(); i++) {
            addData("xpath","//Edit[@Name='PDC Account Code Row "+i+", Not sorted.']",dataFile,"PostDatedCheques","PDCAccountCode",i);
        }
        List<WebElement> amoutRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> ChequeNo = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> ChequeDate = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <postDateDCheques.size() ; i++) {
            enterListData(amoutRowList.get(i), dataFile, "PostDatedCheques", "Amount", i);
            enterListData(ChequeNo.get(i), dataFile, "PostDatedCheques", "ChequeNo" ,i);
            enterListDate(ChequeDate.get(i), dataFile, "PostDatedCheques", "ChequeDate" , i);
            enterListDate(drawnOnRowList.get(i), dataFile, "PostDatedCheques", "DrawnOnBankAccount" , i);
            enterListData(drawnOnBranchBranchRowList.get(i), dataFile, "PostDatedCheques", "DrawnOnBank",i);
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
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
        List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i <chequesPDC.size() ; i++) {
            enterListData(chequeAmountRowList.get(i), dataFile, "PDC", "Amount",i);
            enterListData(ChequeNo.get(i), dataFile, "PDC", "ChequeNo" ,i);
            enterListDate(ChequeDate.get(i), dataFile, "PDC", "ChequeDate" , i);
            enterListData(drawnOnRowList.get(i), dataFile, "PDC", "DrawnOnBank",i);
            enterListData(drawnOnBranchBranchRowList.get(i), dataFile, "PDC", "DrawnOnBankBranch",i);
            enterListData(departmentRowList.get(i),dataFile,"PDC","Department",i);
            enterListData(projectRowList.get(i),dataFile,"PDC","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"PDC","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"PDC","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"PDC","Comments",i);
        }
    }

    public void addCreditCard() throws IOException {
        List<String> creditCard=readExcelData(dataFile,"CreditCard","SwipeMachineType");
        for (int i = 0; i< creditCard.size(); i++) {
            addData("xpath","//Edit[@Name='Swipe Machine Type * Row "+i+", Not sorted.']",dataFile,"CreditCard","SwipeMachineType",i);
        }

        List<WebElement> swipeTypeRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Swipe Type * Row ')]");
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement>  cardNo= common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Card No Row ')]");
        List<WebElement>  expiryDate= common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Expiry Date Row ')]");
        List<WebElement> approvalNo = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Approval No * Row ')]");
        List<WebElement> executiveRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='CreditCard']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i <creditCard.size() ; i++) {
            enterListData(swipeTypeRowList.get(i), dataFile, "CreditCard", "SwipeType",i);
            enterListData(amountRowList.get(i), dataFile, "CreditCard", "Amount",i);
            enterListData(cardNo.get(i), dataFile, "CreditCard", "CardNo",i);
            enterListDate(expiryDate.get(i), dataFile, "CreditCard", "ExpiryDate",i);
            enterListData(approvalNo.get(i), dataFile, "CreditCard", "ApprovalNo",i);
            enterListData(executiveRowList.get(i),dataFile,"CreditCard","Executive",i);
            enterListData(departmentRowList.get(i),dataFile,"CreditCard","Department",i);
            enterListData(projectRowList.get(i),dataFile,"CreditCard","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"CreditCard","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"CreditCard","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"CreditCard","Comments",i);
        }
    }

    public void pineLab() throws IOException {
        List<String> MachineTypePineLabAcct=readExcelData(dataFile,"PineLab","SwipeMachineType");
        for (int i = 0; i< MachineTypePineLabAcct.size(); i++) {
            addData("xpath","//Edit[@Name='Swipe Machine Type Row "+i+", Not sorted.']",dataFile,"PineLab","SwipeMachineType",i);
        }
        List<WebElement> swipeType = common.findWebElements("xpath", "//Table[@Name='PineLab']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Swipe Type Row ')]");
        List<WebElement>  amountRow= common.findWebElements("xpath", "//Table[@Name='PineLab']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> approvalNo = common.findWebElements("xpath", "//Table[@Name='PineLab']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Approval No * Row ')]");
        List<WebElement> cardNo = common.findWebElements("xpath", "//Table[@Name='PineLab']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Card Number Row ')]");
        for (int i = 0; i <MachineTypePineLabAcct.size() ; i++) {
            enterListData(swipeType.get(i), dataFile, "PineLab", "SwipeType",i);
            enterListData(amountRow.get(i), dataFile, "PineLab", "Amount",i);
            enterListData(approvalNo.get(i), dataFile, "PineLab", "ApprovalCode",i);
            enterListData(cardNo.get(i), dataFile, "PineLab", "CardNumber",i);
        }
    }

    public void otherInfo() throws InterruptedException, IOException {
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(2500);
        common.clickElement("xpath","//Button[@Name='OK']");
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
        EnterData( "//Edit[@Name='Department']", dataFile, "Allocations", "Department");
        EnterData( "//Edit[@Name='Project']", dataFile, "Allocations", "Project");
        EnterData("//Edit[@Name='Profit Centre']", dataFile, "Allocations", "ProfitCentre");
        EnterData( "//Edit[@Name='Cost Centre']", dataFile, "Allocations", "CostCentre");
    }
}