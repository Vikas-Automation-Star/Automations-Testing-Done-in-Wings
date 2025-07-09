package com.wings.pages.sales.transactions;

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

public class SalesEnquiryCancellation extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesEnquiryCancellation(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void salesEnquiryCancellation(String voucherNum) throws InterruptedException, IOException, ParseException, AWTException {
        navigateToSalesEnquiryCancellationMenu();
        Thread.sleep(4000);
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);

        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterPartyCode(dataFile,"GeneralInformation","PartyAccountCode");
        selectPendingsSalesOrder(voucherNum,common.getData(dataFile,"PurchaseEnquiriesCancellation","FYear"));
        common.clickElement("xpath","//Button[@Name='OK']");
        enterExchangeRate(dataFile,"GeneralInformation","ExchangeRate");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");




        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "salesEnquiryCancellation", "branch");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "salesEnquiryCancellation", "partyCode");
        Thread.sleep(1000);
        gstTransactionType("Inter State Sales to Registered Dealers");
        Thread.sleep(2500);
        selectPendingsSalesOrder(voucherNum, common.getData(dataFile,"salesEnquiryCancellation","fyYear"));
        Thread.sleep(1000);
        common.clickElement("xpath","//Button[@Name='OK']");
        //select pending quantity
        List<WebElement> items = common.findWebElements("xpath", "//Pane[@Name='  F3 Items  ']/Pane/Pane/Pane/Pane/Table[@Name='Items']/*[starts-with(@Name,'Row')]");
        System.out.println("items size: "+items.size());
        for (int i = 0; i < items.size(); i++) {
            WebElement productList = items.get(i);
            String value = productList.getAttribute("LegacyValue");
            if (!"(null)".equals(value) && !"(Create New)".equals(value)){
                String pendingQty = common.findWebElement("xpath", "//Edit[@Name='Pending Quantity * Row "+i+", Not sorted.']").getText();
                WebElement quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']");
                quantity.click();
                quantity.sendKeys(pendingQty, Keys.TAB);
            }
        }
        //summary
        common.clickElement("xpath","//TabItem[@Name='  F8 Summary  ']");
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        //save
        transactionSave();
        String newVoucherID = newTransactionID(oldVoucherID);
        System.out.println("newID: " + newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID, "Voucher Numbers are same. Check Transaction.");
        Thread.sleep(1000);

//        common.clickElement("name", "Sales");
//        common.clickElement("name", "Enquiries");
//        common.clickElement("xpath", "//MenuItem[@Name='Sales Enquiry Cancellations']");
//        Thread.sleep(1000);
//        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
//        Thread.sleep(1500);
//        verifyReport(newVoucherID, dataFile, "salesEnquiryCancellation");
    }
}