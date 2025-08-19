package com.wings.pages.finance.transactions.Payments;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import com.wings.utils.Common;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;

public class PaymentToParties extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked=false;

    public PaymentToParties(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String paymentToParty() throws InterruptedException, IOException, ParseException {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Finance", "Payments", "Payments to Parties");
        Thread.sleep(1000);
        long generalInfoStart=System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterPartyCode(dataFile,"GeneralInformation","AccountCode");
        Thread.sleep(3000);
        gstTransactionType("Intra State Purchase from Registered Dealers");
        enterDiscountAccountCode(dataFile,"GeneralInformation","DiscountAccount");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Payment to Parties General information End:- ", generalInfoEndTime);

        //F3-Parties
        long addProductStart=System.nanoTime();
        addCash();
        long addProductEnd=System.nanoTime()-addProductStart;
        FileUtil.writeTimeLogInMinutes("Payment to Parties Add Parties:- ",addProductEnd);

        long chequesStart=System.nanoTime();
        addCheques();
        long chequesEnd=System.nanoTime()- chequesStart;
        FileUtil.writeTimeLogInMinutes("Payment to Parties Cheques Tab:- ",chequesEnd);

        long postDatedChequesStart=System.nanoTime();
        addPostDatedCheques();
        long postDatedChequesEnd=System.nanoTime() - postDatedChequesStart;
        FileUtil.writeTimeLogInMinutes("Payment to Parties Post Dated Cheques Tab:- ",postDatedChequesEnd);

        long chequesPDCStart=System.nanoTime();
        addChequesPDC();
        long chequesPDCEnd=System.nanoTime() - chequesPDCStart;
        FileUtil.writeTimeLogInMinutes("Payment to Parties Cheques PDC Tab:- ", chequesPDCEnd);

        long otherCreditsStart =System.nanoTime();
        addOtherCredits();
        long otherCreditsEnd =System.nanoTime() - otherCreditsStart;
        FileUtil.writeTimeLogInMinutes("Payment to Parties Cheques PDC Tab:- ", otherCreditsEnd);

        long otherInfoStart = System.nanoTime();
        otherInfo();
        long otherInfoEnd = System.nanoTime() - otherInfoStart;
        FileUtil.writeTimeLogInMinutes("Payment to Parties OtherInfo Tab:- ", otherInfoEnd);

        //allocations
        long allocationsTabStart=System.nanoTime();
        addAllocations();
        long allocationsTabEnd=System.nanoTime() - allocationsTabStart;
        FileUtil.writeTimeLogInMinutes("Payment to Parties Allocations Tab:- ", allocationsTabEnd);
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long salesInvoiceEnd = System.nanoTime() - start ;
        FileUtil.writeTimeLogInMinutes("Payment to Parties ended at:- ", salesInvoiceEnd );
        //IO
        String voucher = newVoucherID.replaceAll("\\d", "");
        String number = newVoucherID.replaceAll("\\D", "");
        Thread.sleep(2000);
        long iofIlesStart=System.nanoTime();
        exportIOFiles("Generate Input File", voucher,number);
        exportIOFiles("Generate Output File", voucher,number);
        long ioFilesEnd=System.nanoTime()-iofIlesStart;
        FileUtil.writeTimeLogInMinutes("Payment to Parties IO files ended at:- ", ioFilesEnd );

//        excelUtil.excelComparator("","",newVoucherID);
        return newVoucherID;
    }

    public void addCash() throws IOException {
        navigateToCashTab();
        List<String> cashTab = readExcelData(dataFile, "Cash", "CashAccountCode");
        for (int i = 0; i < cashTab.size(); i++) {
            addData("xpath", "//Edit[@Name='Cash Account Code Row " + i + ", Not sorted.']", dataFile, "Cash", "CashAccountCode", i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> gstProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Cash']/*/Thumb[@Name='Position']", 800, 0);
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        List<WebElement> tdsTransactionNature = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Trans Nature Row ')]");
        List<WebElement> discountAmount = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Cash']/*/Thumb[@Name='Position']", -1000, 0);
        for (int i = 0; i < cashTab.size(); i++) {
            enterListData(amountRowList.get(i), dataFile, "Cash", "InclusiveAmount", i);
            enterListData(hsnCodeRowList.get(i), dataFile, "Cash", "HSN", i);
            enterListData(gstProductCategoryRowList.get(i), dataFile, "Cash", "GSTProductCategory", i);
            enterListData(cessProductCategoryRowList.get(i), dataFile, "Cash", "CESSProductCategory", i);
            common.clickElement("xpath","//CheckBox[@Name='Deduct TDS Row "+i+"']");
            enterListData(tdsTransactionNature.get(i), dataFile, "Cash", "TDSTransactionNature", i);
            enterListData(discountAmount.get(i), dataFile, "Cash", "Discount", i);
            enterListData(departmentRowList.get(i), dataFile, "Cash", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "Cash", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "Cash", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "Cash", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "Cash", "Comments", i);
            common.sliderHandling("xpath", "//Table[@Name='Cash']/*/Thumb[@Name='Position']", -1000, 0);
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
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Account * Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> gstProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Cheques']/*/Thumb[@Name='Position']", 600, 0);
        List<WebElement> tdsTransactionNature = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Trans Nature Row ')]");
        List<WebElement> chargesAccRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Account Code Row ')]");
        List<WebElement> chargesAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Row ')]");
        List<WebElement> discountAmount = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Cheques']/*/Thumb[@Name='Position']", -900, 0);
        for (int i = 0; i < chequesTab.size(); i++) {
            enterListData(amountRowList.get(i), dataFile, "Cheques", "InclusiveAmount", i);
            enterListData(chequeNo.get(i), dataFile, "Cheques", "ChequeNo", i);
            enterListDate(chequeDate.get(i), dataFile, "Cheques", "ChequeDate", i);
            enterListData(drawnOnRowList.get(i), dataFile, "Cheques", "DrawnOnBankAccount",i);
            enterListData(hsnCodeRowList.get(i), dataFile, "Cheques", "HSN", i);
            enterListData(gstProductCategoryRowList.get(i), dataFile, "Cheques", "GSTProductCategory", i);
            enterListData(cessProductCategoryRowList.get(i), dataFile, "Cheques", "CESSProductCategory", i);
            common.sliderHandling("xpath", "//Table[@Name='Cheques']/*/Thumb[@Name='Position']", 600, 0);
            common.clickElement("xpath","//CheckBox[@Name='Deduct TDS Row "+i+"']");
            enterListData(tdsTransactionNature.get(i), dataFile, "Cheques", "TDSTransactionNature", i);
            enterListData(chargesAccRowList.get(i), dataFile, "Cheques", "ChargesAccount", i);
            enterListData(chargesAmountRowList.get(i), dataFile, "Cheques", "Charges", i);
            enterListData(discountAmount.get(i), dataFile, "Cheques", "Discount", i);
            enterListData(departmentRowList.get(i), dataFile, "Cheques", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "Cheques", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "Cheques", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "Cheques", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "Cheques", "Comments", i);
            common.sliderHandling("xpath", "//Table[@Name='Cheques']/*/Thumb[@Name='Position']", -900, 0);
        }
    }

    public void addPostDatedCheques() throws IOException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Post Dated Cheques')]");
        List<String> postDatedCheques = readExcelData(dataFile, "PostDatedCheques", "BankAccountCode");
        for (int i = 0; i < postDatedCheques.size(); i++) {
            addData("xpath", "//Edit[@Name='Bank Account Code Row "+i+", Not sorted.']", dataFile, "PostDatedCheques", "BankAccountCode", i);
        }
        List<WebElement> pdcAccount = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'PDC Account * Row ')]");
        List<WebElement> amoutRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Account * Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> gstProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='PostDatedCheques']/*/Thumb[@Name='Position']", 600, 0);
        List<WebElement> tdsTransactionNature = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Trans Nature Row ')]");
        List<WebElement> discountAmount = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='PostDatedCheques']/*/Thumb[@Name='Position']", -900, 0);

        for (int i = 0; i < postDatedCheques.size(); i++) {
            enterListData(pdcAccount.get(i), dataFile, "PostDatedCheques", "PDCAccount", i);
            enterListData(amoutRowList.get(i), dataFile, "PostDatedCheques", "InclusiveAmount", i);
            enterListData(chequeNo.get(i), dataFile, "PostDatedCheques", "ChequeNo", i);
            enterListDate(chequeDate.get(i), dataFile, "PostDatedCheques", "ChequeDate", i);
            enterListData(drawnOnRowList.get(i), dataFile, "PostDatedCheques", "DrawnOnBankAccount",i);
            enterListData(hsnCodeRowList.get(i), dataFile, "PostDatedCheques", "HSN", i);
            enterListData(gstProductCategoryRowList.get(i), dataFile, "PostDatedCheques", "GSTProductCategory", i);
            enterListData(cessProductCategoryRowList.get(i), dataFile, "PostDatedCheques", "CESSProductCategory", i);
            common.sliderHandling("xpath", "//Table[@Name='PostDatedCheques']/*/Thumb[@Name='Position']", 600, 0);
            common.clickElement("xpath","//CheckBox[@Name='Deduct TDS Row "+i+"']");
            enterListData(tdsTransactionNature.get(i), dataFile, "PostDatedCheques", "TDSTransactionNature", i);
            enterListData(discountAmount.get(i), dataFile, "PostDatedCheques", "Discount", i);
            enterListData(departmentRowList.get(i), dataFile, "PostDatedCheques", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "PostDatedCheques", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "PostDatedCheques", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "PostDatedCheques", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "PostDatedCheques", "Comments", i);
            common.sliderHandling("xpath", "//Table[@Name='PostDatedCheques']/*/Thumb[@Name='Position']", -900, 0);
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
        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Account * Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> gstProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='PDC']/*/Thumb[@Name='Position']", 600, 0);
        List<WebElement> tdsTransactionNature = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Trans Nature Row ')]");
        List<WebElement> discountAmount = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='PDC']/*/Thumb[@Name='Position']", -900, 0);

        for (int i = 0; i < chequesPDC.size(); i++) {
            enterListData(chequeAmountRowList.get(i), dataFile, "PDC", "InclusiveAmount", i);
            enterListData(chequeNo.get(i), dataFile, "PDC", "ChequeNo", i);
            enterListDate(chequeDate.get(i), dataFile, "PDC", "ChequeDate", i);
            enterListData(drawnOnRowList.get(i), dataFile, "PDC", "DrawnOnBankAccount",i);
            enterListData(hsnCodeRowList.get(i), dataFile, "PDC", "HSN", i);
            enterListData(gstProductCategoryRowList.get(i), dataFile, "PDC", "GSTProductCategory", i);
            enterListData(cessProductCategoryRowList.get(i), dataFile, "PDC", "CESSProductCategory", i);
            common.sliderHandling("xpath", "//Table[@Name='PDC']/*/Thumb[@Name='Position']", 600, 0);
            common.clickElement("xpath","//CheckBox[@Name='Deduct TDS Row "+i+"']");
            enterListData(tdsTransactionNature.get(i), dataFile, "PDC", "TDSTransactionNature", i);
            enterListData(discountAmount.get(i), dataFile, "PDC", "Discount", i);
            enterListData(departmentRowList.get(i), dataFile, "PDC", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "PDC", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "PDC", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "PDC", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "PDC", "Comments", i);
            common.sliderHandling("xpath", "//Table[@Name='PDC']/*/Thumb[@Name='Position']", -900, 0);
        }
    }

    public void addOtherCredits() throws IOException {
       common.clickElement("xpath","//TabItem[contains(@Name,'Other Credits')]");
        List<String> accountCodeTab = readExcelData(dataFile, "OtherCredits", "AccountCode");
        for (int i = 0; i < accountCodeTab.size(); i++) {
            addData("xpath", "//Edit[@Name='Account Code Row "+i+", Not sorted.']", dataFile, "OtherCredits", "AccountCode", i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCredits']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='OtherCredits']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='OtherCredits']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCredits']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCredits']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='OtherCredits']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < accountCodeTab.size(); i++) {
            enterListData(amountRowList.get(i), dataFile, "OtherCredits", "CreditAmount", i);
            enterListData(departmentRowList.get(i), dataFile, "OtherCredits", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "OtherCredits", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "OtherCredits", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "OtherCredits", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "OtherCredits", "Comments", i);
        }
    }

    public void otherInfo() throws InterruptedException, IOException {
        navigateToOtherInfoTab();
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(5000);
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }

    public void addAllocations() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Allocations')]");
        EnterData( "//Edit[@Name='Department']", dataFile, "Allocations", "Department");
        EnterData( "//Edit[@Name='Project']", dataFile, "Allocations", "Project");
        EnterData("//Edit[@Name='Profit Centre']", dataFile, "Allocations", "ProfitCentre");
        EnterData( "//Edit[@Name='Cost Centre']", dataFile, "Allocations", "CostCentre");
    }
}
