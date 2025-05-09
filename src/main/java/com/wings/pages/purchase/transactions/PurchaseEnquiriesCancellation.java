package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PurchaseEnquiriesCancellation extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchaseEnquiriesCancellation(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchaseEnquiriesCancellation(String voucherNum) throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps("Purchase","Enquiries","Purchase Enquiries Cancellation");
        Thread.sleep(3000);
        String oldVoucherID = oldTTransactionID();
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "Purchase Enquiries", "branch");
//        enterInput("xpath", "//Edit[@Name='Transaction Currency *']", dataFile, "PurchaseEnquiriesCancellation", "currency");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "Purchase Enquiries", "partyCode");
        selectPendingsSalesOrder(voucherNum,common.getData(dataFile,"PurchaseEnquiriesCancellation","FYear"));
        common.clickElement("xpath","//Button[@Name='OK']");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "Purchase Enquiries", "executive");
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
        navigateToOtherInfoTab();
        inputTextWithValidation("xpath", "//Edit[@Name='Reference Bill Date']", common.getData(dataFile,"Purchase Enquiries","billRefDate")+ Time.timeStamp());
        termsAndConditions(dataFile,"Purchase Enquiries");
        navigateToSummaryTab();
        quantityPresentInSummary();
        transactionSave();
        String transactionId = newTransactionID(oldVoucherID);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Enquiries");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Enquiries Cancellation'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
//        verifyReport(transactionId,dataFile,"PurchaseEnquiriesCancellation");
    }
}

