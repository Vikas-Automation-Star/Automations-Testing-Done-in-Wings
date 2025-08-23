package com.wings.pages.finance.transactions.Journals;

import com.wings.pages.Transaction;
import com.wings.pages.TransactionsBaseClass;
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

public class BookIncomesOrReceivables extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;
    double finalAmount = 0.0;

    public BookIncomesOrReceivables(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void receivables() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToBookIncomesOrReceivablesMenu();
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        Thread.sleep(1000);
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        EnterData("//Edit[@Name='Account Code']", dataFile,"GeneralInformation", "Account");
        gstTransactionType("Intra State Sales to Registered Dealers");
        enterCreditPeriod(dataFile,"GeneralInformation","CreditPeriod");
        common.clickElement("xpath","//CheckBox[@Name='Apply TCS']");
        enterTcsTransNature(dataFile, "GeneralInformation", "TCSTransactionNature");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");



        //Accounts
        long accountsStart =System.nanoTime();
        accounts();
        long accountsStartEnd =System.nanoTime()- accountsStart;
        FileUtil.writeTimeLogInMinutes("Accounts tab End:- ", accountsStartEnd);

        //otherCredits
        long otherCreditsStart =System.nanoTime();
        otherCredits();
        long otherCreditsEnd =System.nanoTime()- otherCreditsStart;
        FileUtil.writeTimeLogInMinutes("otherCredits Tab End:- ",otherCreditsEnd);

        navigateToBillsPayablesTab();

        //Other info
        long otherInfoTabStart =System.nanoTime();
        otherInfo();
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Other Info Tab:- ", otherInfoTabEnd);

        long shippingAddressTabStart = System.nanoTime();
        shippingAddress();
        long shippingAddressTabEnd = System.nanoTime() - shippingAddressTabStart;
        FileUtil.writeTimeLogInMinutes("Deliveries Shipping Address:- ", shippingAddressTabEnd);

        //allocations
//        long allocationsTabStart=System.nanoTime();
//        addAllocations();
//        long allocationsTabEnd=System.nanoTime() - allocationsTabStart;
//        FileUtil.writeTimeLogInMinutes("Allocations Tab:- ", allocationsTabEnd);

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");

    }

    public void accounts() throws IOException, InterruptedException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Accounts')]");
        List<String> cashTab = readExcelData(dataFile, "Accounts", "AccountCode");
        for (int i = 0; i < cashTab.size(); i++) {
            addData("xpath", "//Edit[@Name='Account Code Row "+i+", Not sorted.']", dataFile, "Accounts", "AccountCode", i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> gstProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Cash']/*/Thumb[@Name='Position']", 800, 0);
        common.clickElement("xpath", "//Header[@Name='GST Amount']");
        List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
        List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
        List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Accounts']/*/Thumb[@Name='Position']", -1000, 0);
        for (int i = 0; i < cashTab.size(); i++) {
            enterListData(amountRowList.get(i), dataFile, "Accounts", "InclusiveAmount", i);
            enterListData(hsnCodeRowList.get(i), dataFile, "Accounts", "HSN", i);
            enterListData(gstProductCategoryRowList.get(i), dataFile, "Accounts", "GSTProductCategory", i);
            enterListData(cessProductCategoryRowList.get(i), dataFile, "Accounts", "CESSProductCategory", i);
            common.clickElement("xpath","//CheckBox[@Name='Deduct TDS Row "+i+"']");
            enterListData(tdsTransNatureRowList.get(i), dataFile, "Parties", "TDSTransactionNature",i);
            enterListData(tdsAccountRowList.get(i), dataFile, "Parties", "TDSAccount",i);
            enterListData(tdsAmountRowList.get(i), dataFile, "Parties", "TDSAmount",i);
            enterListData(departmentRowList.get(i), dataFile, "Accounts", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "Accounts", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "Accounts", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "Accounts", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "Accounts", "Comments", i);
            common.sliderHandling("xpath", "//Table[@Name='Accounts']/*/Thumb[@Name='Position']", -1000, 0);
        }

    }

    public void otherCredits() throws IOException {
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
        Thread.sleep(3500);
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
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

//    public void addAllocations() {
//        navigateToAllocations();
//        EnterData( "//Edit[@Name='Department']", dataFile, "Allocations", "Department");
//        EnterData( "//Edit[@Name='Project']", dataFile, "Allocations", "Project");
//        EnterData("//Edit[@Name='Profit Centre']", dataFile, "Allocations", "ProfitCentre");
//        EnterData( "//Edit[@Name='Cost Centre']", dataFile, "Allocations", "CostCentre");
//    }

}
