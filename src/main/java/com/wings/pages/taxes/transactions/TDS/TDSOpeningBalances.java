package com.wings.pages.taxes.transactions.TDS;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;

public class TDSOpeningBalances extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public TDSOpeningBalances(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String tdsOpeningBalances(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long start=System.nanoTime();
        long genInfoStart=System.nanoTime();
        navigateToMastersWhen3Steps("Taxes","TDS","TDS Opening Balances");
        Thread.sleep(3000);
        String oldVoucherID =oldTTransactionID();
        Thread.sleep(1000);
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        EnterData("//Edit[@Name='Control Account *']",dataFile,"GeneralInformation","ControlAccount");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        long genInfoEnd=System.nanoTime()-genInfoStart;
        FileUtil.writeTimeLogInMinutes("TDS Opening Balances Gen info",genInfoEnd);

        long tdsDeductedByUsStart=System.nanoTime();
        tdsDeductedByUs();
        long tdsDeductedByUsEnd =System.nanoTime()-tdsDeductedByUsStart;
        FileUtil.writeTimeLogInMinutes("TDS Opening Balances - TDS Deducted By Us Ended at:- ", tdsDeductedByUsEnd);

        long tdsDeductedByOthersStart =System.nanoTime();
        common.clickElement("xpath","//TabItem[contains(@Name,'TDS Deducted By Others')]");
        tdsDeductedByOthers();
        long tdsDeductedByOthersEnd =System.nanoTime()- tdsDeductedByOthersStart;
        FileUtil.writeTimeLogInMinutes("TDS Opening Balances - TDS Deducted By Others Ended at:- ", tdsDeductedByOthersEnd);

        long otherInfoStart =System.nanoTime();
        navigateToOtherInfoTab();
        otherInfo();
        long otherInfoEnd =System.nanoTime()- otherInfoStart;
        FileUtil.writeTimeLogInMinutes("TDS Opening Balances - Other Info:- ", otherInfoEnd);

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");

        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"tdsOpeningBalances");

        long tdsOpeningBalanceEnd = System.nanoTime() - start;
        FileUtil.writeTimeLogInMinutes("TDS Opening Balances ended at:- ", tdsOpeningBalanceEnd);

        return newVoucherID;

    }

    public void tdsDeductedByUs() throws IOException {
        List<String> tdsDedcutedByUsRows = readExcelData(dataFile, "TDSDeductedByUs", "AccountCode");
        System.out.println("productCodes :" + tdsDedcutedByUsRows.size());
        for (int i = 0; i < tdsDedcutedByUsRows.size(); i++) {
            addData("xpath", "//Edit[@Name='Account Code Row "+i+", Not sorted.']", dataFile, "TDSDeductedByUs", "AccountCode", i);
        }
        List<WebElement> tdsAssesseType = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByUs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Assesse Type * Row ')]");
        List<WebElement> tdsTransactionNature = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByUs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature * Row ')]");
        List<WebElement> subType = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByUs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Sub Type * Row ')]");
        List<WebElement> tdsAccount = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByUs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account * Row ')]");
        List<WebElement> tdsAssessableValue = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByUs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Assessable Value Row ')]");
        List<WebElement> tdsRate = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByUs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Rate Row ')]");
        List<WebElement> tdsAmount = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByUs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByUs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByUs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByUs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByUs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByUs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < tdsDedcutedByUsRows.size(); i++) {
            enterListData(tdsAssesseType.get(i), dataFile, "TDSDeductedByUs", "TDSAssesseType", i);
            enterListData(tdsTransactionNature.get(i), dataFile, "TDSDeductedByUs", "TDSTransactionNature", i);
            enterListData(subType.get(i), dataFile, "TDSDeductedByUs", "SubType", i);
            enterListData(tdsAccount.get(i), dataFile, "TDSDeductedByUs", "TDSAccount", i);
            enterListData(tdsAssessableValue.get(i), dataFile, "TDSDeductedByUs", "TDSAssessableValue", i);
            enterListData(tdsRate.get(i), dataFile, "TDSDeductedByUs", "TDSRate", i);
            enterListData(tdsAmount.get(i), dataFile, "TDSDeductedByUs", "Amount", i);
            enterListData(departmentRowList.get(i), dataFile, "TDSDeductedByUs", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "TDSDeductedByUs", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "TDSDeductedByUs", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "TDSDeductedByUs", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "TDSDeductedByUs", "Comments", i);
        }
    }

    public void tdsDeductedByOthers() throws IOException {
        List<String> tdsDedcutedByOthersRows = readExcelData(dataFile, "TDSDeductedByUs", "AccountCode");
        System.out.println("productCodes :" + tdsDedcutedByOthersRows.size());
        for (int i = 0; i < tdsDedcutedByOthersRows.size(); i++) {
            addData("xpath", "//Edit[@Name='Account Code Row "+i+", Not sorted.']", dataFile, "TDSDeductedByOthers", "AccountCode", i);
        }
        List<WebElement> tdsTransactionNature = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByOthers']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature * Row ')]");
        List<WebElement> tdsDeductedByOthers = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByOthers']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Deducted by Others * Row ')]");
        List<WebElement> tdsAmount = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByOthers']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByOthers']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByOthers']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByOthers']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByOthers']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='TDSDeductedByOthers']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < tdsDedcutedByOthersRows.size(); i++) {
            enterListData(tdsTransactionNature.get(i), dataFile, "TDSDeductedByOthers", "TDSTransactionNature", i);
            enterListData(tdsDeductedByOthers.get(i), dataFile, "TDSDeductedByOthers", "TDSAccount", i);
            enterListData(tdsAmount.get(i), dataFile, "TDSDeductedByOthers", "Amount", i);
            enterListData(departmentRowList.get(i), dataFile, "TDSDeductedByOthers", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "TDSDeductedByOthers", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "TDSDeductedByOthers", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "TDSDeductedByOthers", "CostCentre", i);
            enterListData(commentsRowList.get(i), dataFile, "TDSDeductedByOthers", "Comments", i);
        }
    }

    public void otherInfo() throws InterruptedException, IOException {
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(5000);
        common.clickElement("xpath","//Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info5']",dataFile,"OtherInfo","OtherInfo5");
    }

}
