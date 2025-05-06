package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class DeliveryReturns extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public DeliveryReturns(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(driver);
        dataFile = file;
    }

    public void deliveryreturns(String voucherNum) throws InterruptedException, IOException, ParseException, IOException, ParseException {
        long start = System.nanoTime();
        System.out.println("delivery Returns startTime executed in :"+start);
        Thread.sleep(100);
        navigateToDeliveryReturnsMenu();
        Thread.sleep(3000);

        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        //branch selection
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "deliveryReturns", "branch");
//        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Branch *']"), common.getData(dataFile, "deliveryReturns", "branch"), "Branch is not validated");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "deliveryReturns", "partyCode");
        Thread.sleep(1000);
        gstTransactionType("Inter State Sales to Registered Dealers");
        Thread.sleep(2500);
        selectPendingsSalesOrder(voucherNum,"20250401");
        Thread.sleep(3000);

        //select pending quantity
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        //select qty
        List<WebElement> items = common.findWebElements("xpath", "//Pane[@Name='  F3 Items  ']/Pane/Pane/Pane/Table[@Name='Items']/*[starts-with(@Name,'Row')]");
        System.out.println("items size: "+items.size());
        for (int i = 0; i < items.size(); i++) {
            WebElement productList = items.get(i);
            String value = productList.getAttribute("LegacyValue");
//            System.out.println("value: "+value);
            String billType=common.getText("xpath","//Edit[@Name='Pending Type Row "+i+", Not sorted.']");
//            System.out.println("billType: "+ billType);

            if (!"(null)".equals(value) && !"(Create New)".equals(value) && billType.equals("Billed")) {
                String pendingQty = common.getText("xpath", "//Edit[@Name='Pending Quantity Row " + i + ", Not sorted.']");
                WebElement quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']");
                quantity.click();
                quantity.sendKeys(pendingQty, Keys.TAB);
            } else if (!"(null)".equals(value) && !"(Create New)".equals(value) && billType.equals("Free")) {
                String pendingQty2 = common.getText("xpath", "//Edit[@Name='Pending Quantity Row " + i + ", Not sorted.']");
                WebElement freeQuantity = common.findWebElement("xpath", "//Edit[@Name='Free Quantity Row " + i + ", Not sorted.']");
                freeQuantity.click();
                freeQuantity.sendKeys(pendingQty2, Keys.TAB);
            }
        }
        //summary
        common.clickElement("xpath","//TabItem[@Name='  Ctrl-F5 Summary  ']");
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        netAmountPresentInSummary();
        cessPresentInSummary();
        iGSTPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        //save
        transactionSave();
        String newVoucherID = newTransactionID(oldVoucherID);
        System.out.println("newID: " + newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID, "Voucher Numbers are same. Check Transaction.");
        Thread.sleep(1000);
        common.clickElement("xpath", "//MenuItem[@Name='Sales']");
        common.clickElement("xpath", "//MenuItem[@Name='Deliveries']");
        common.clickElement("xpath", "//MenuItem[@Name='Delivery Returns'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID, dataFile, "deliveryReturns");

    }
}