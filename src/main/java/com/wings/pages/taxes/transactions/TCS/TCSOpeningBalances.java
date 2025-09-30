package com.wings.pages.taxes.transactions.TCS;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;

public class TCSOpeningBalances extends TransactionsBaseClass {

        WindowsDriver driver;
        Common common;
        String dataFile;

        public TCSOpeningBalances(WindowsDriver driver, String file) {
            super(driver);
            common = new Common(this.driver = driver);
            dataFile = file;
        }

        public String tcsOpeningBalances(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
            long start=System.nanoTime();
            long genInfoStart=System.nanoTime();
            navigateToMastersWhen3Steps("Taxes","TCS","TCS Opening Balances");
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
            FileUtil.writeTimeLogInMinutes("TCS Opening Balances Gen info",genInfoEnd);

            long tcsAccountsStart =System.nanoTime();
            accounts();
            long tcsAccountsEnd =System.nanoTime()- tcsAccountsStart;
            FileUtil.writeTimeLogInMinutes("TCS Opening Balances - Accounts Ended at:- ", tcsAccountsEnd);

            long otherInfoStart =System.nanoTime();
            navigateToOtherInfoTab();
            otherInfo();
            long otherInfoEnd =System.nanoTime()- otherInfoStart;
            FileUtil.writeTimeLogInMinutes("TCS Opening Balances - Other Info:- ", otherInfoEnd);

            transactionSave();
            String newVoucherID =newTransactionID(oldVoucherID);
            System.out.println("newID: "+newVoucherID);
            Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");

            APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"tcsOpeningBalances");

            long tcsOpeningBalanceEnd = System.nanoTime() - start;
            FileUtil.writeTimeLogInMinutes("TCS Opening Balances ended at:- ", tcsOpeningBalanceEnd);
            return newVoucherID;

        }

        public void accounts() throws IOException {
            List<String> accountsRows = readExcelData(dataFile, "Accounts", "AccountCode");
            System.out.println("productCodes :" + accountsRows.size());
            for (int i = 0; i < accountsRows.size(); i++) {
                addData("xpath", "//Edit[@Name='Account Code Row "+i+", Not sorted.']", dataFile, "Accounts", "AccountCode", i);
            }
            List<WebElement> accCodeRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TCS Assesse Type * Row ')]");
            List<WebElement> tcsTransactionNature = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TCS Transaction Nature * Row ')]");
            List<WebElement> subType = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Sub Type * Row ')]");
            List<WebElement> tcsAccount = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TCS Account * Row ')]");
            List<WebElement> tcsAssessableValue = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TCS Assessable Value Row ')]");
            List<WebElement> tcsRate = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TCS Rate Row ')]");
            List<WebElement> tcsAmount = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TCS Amount * Row ')]");
            List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
            List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
            List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
            List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
            List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Accounts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

            for (int i = 0; i < accountsRows.size(); i++) {
                enterListData(accCodeRowList.get(i), dataFile, "Accounts", "TCSAssesseType", i);
                enterListData(tcsTransactionNature.get(i), dataFile, "Accounts", "TCSTransactionNature", i);
                enterListData(subType.get(i), dataFile, "Accounts", "SubType", i);
                enterListData(tcsAccount.get(i), dataFile, "Accounts", "TCSAccount", i);
                enterListData(tcsAssessableValue.get(i), dataFile, "Accounts", "TCSAssessableValue", i);
                enterListData(tcsRate.get(i), dataFile, "Accounts", "TCSRate", i);
                enterListData(tcsAmount.get(i), dataFile, "Accounts", "Amount", i);
                enterListData(departmentRowList.get(i), dataFile, "Accounts", "Department", i);
                enterListData(projectRowList.get(i), dataFile, "Accounts", "Project", i);
                enterListData(profitCentreRowList.get(i), dataFile, "Accounts", "ProfitCentre", i);
                enterListData(costCentreRowList.get(i), dataFile, "Accounts", "CostCentre", i);
                enterListData(commentsRowList.get(i), dataFile, "Accounts", "Comments", i);
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