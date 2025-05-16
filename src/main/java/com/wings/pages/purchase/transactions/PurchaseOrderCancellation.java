package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PurchaseOrderCancellation extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchaseOrderCancellation(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchaseOrderCancellation(String voucherNum) throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Purchase","Orders","Purchase Orders Cancellation");
        Thread.sleep(3000);
        String oldVoucherID = oldTTransactionID();
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "PurchaseOrdersCancellation", "branch");
//        enterInput("xpath", "//Edit[@Name='Transaction Currency *']", dataFile, "PurchaseOrdersCancellation", "currency");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "PurchaseOrdersCancellation", "partyCode");
        selectPendingsSalesOrder(voucherNum,common.getData(dataFile,"PurchaseOrdersCancellation","FYear"));
        common.clickElement("xpath","//Button[@Name='OK']");
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
        inputTextWithValidation("xpath", "//Edit[@Name='Reference Bill Date']", common.getData(dataFile,"PurchaseOrdersCancellation","billRefDate")+ Time.timeStamp());
        termsAndConditions(dataFile,"PurchaseOrdersCancellation");
        navigateToSummaryTab();
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        netAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        transactionSave();
        String transactionId = newTransactionID(oldVoucherID);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Orders Cancellation'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        List<String> products = Arrays.asList("AT_Product 1", "AT_Multi batch Product 1", "AT_Product With SN 2");
        verifyReportProductWise(transactionId,dataFile,"PurchaseOrdersCancellation", Collections.singletonList(products.get(0)));
        verifyReportProductWise(transactionId,dataFile,"PurchaseOrdersCancellationMultiBatchProduct", Collections.singletonList(products.get(1)));
        verifyReportProductWise(transactionId,dataFile,"PurchaseOrdersCancellationSerialNumberProduct", Collections.singletonList(products.get(2)));
        System.out.println("All reports are verified");
    }

}

