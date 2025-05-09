package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;

public class SalesQuotationAgainstEnquiry extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesQuotationAgainstEnquiry(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String quotationAgainstEnquiry(String voucherNum) throws InterruptedException, IOException, ParseException {
        long start = System.nanoTime();
        System.out.println("sales quotation against enquiry startTime executed in :"+start);
        navigateToSalesQuotationAgainstEnquiryMenu();
        Thread.sleep(100);
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        //branch selection
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "salesQuotationAgainstEnquiry", "branch");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "salesQuotationAgainstEnquiry", "partyCode");
        Thread.sleep(1000);
        gstTransactionType("Inter State Sales to Registered Dealers");
        Thread.sleep(1000);
        selectPendingsSalesOrder(voucherNum, common.getData(dataFile,"salesQuotationAgainstEnquiry","fyYear"));
        common.clickElement("xpath","//Button[@Name='OK']");
        //select pending quantity
        List<WebElement> items = common.findWebElements("xpath", "//Pane[@Name='  F3 Items  ']/Pane/Pane/Pane/Table[@Name='Items']/*[starts-with(@Name,'Row')]");
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
        //charges and deductions
        enterChargesAndDeductions(dataFile,"salesQuotationAgainstEnquiry");
        validateIGSTAmountTabIsNotEmpty();
        validateCESSAmountTabIsNotEmpty();
        //summary
        common.clickElement("xpath","//TabItem[@Name='  Ctrl-F5 Summary  ']");
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        //terms and Conditions
        termsAndConditions(dataFile,"salesQuotationAgainstEnquiry");
        //save
        transactionSave();
        String newVoucherID = newTransactionID(oldVoucherID);
        System.out.println("newID: " + newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID, "Voucher Numbers are same. Check Transaction.");
        Thread.sleep(1000);
        common.clickElement("name", "Sales");
        common.clickElement("name", "Quotations");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Quotations against Enquiries'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID, dataFile, "salesQuotationAgainstEnquiry");
        return newVoucherID;
    }
}