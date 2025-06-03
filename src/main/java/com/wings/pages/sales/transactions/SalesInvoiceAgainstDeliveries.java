package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class SalesInvoiceAgainstDeliveries extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesInvoiceAgainstDeliveries(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String salesInvoiceAgainstDeliveries(String voucherNum) throws InterruptedException, IOException, ParseException, AWTException {
        long start = System.nanoTime();
        System.out.println("SIAD startTime executed in :" + start);
        Thread.sleep(100);
        navigateToSalesInvoiceAgainstDeliveriesMenu();
        Thread.sleep(4000);
        String oldVoucherID = oldTTransactionID();
        System.out.println("oldID: " + oldVoucherID);
        //branch selection
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "salesInvoiceAgainstDeliveries", "branch");
        enterInput("xpath", "//Edit[@Name='Location *']", dataFile, "salesInvoiceAgainstDeliveries", "location");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "salesInvoiceAgainstDeliveries", "partyCode");
        Thread.sleep(1000);
        gstTransactionType("Inter State Sales to Registered Dealers");
        Thread.sleep(2500);
        selectPendingsSalesOrder(voucherNum, common.getData(dataFile, "salesInvoiceAgainstDeliveries", "fyYear"));
        Thread.sleep(3000);
        common.clickElement("xpath", "//Button[@Name='OK']");
        enterInput("xpath", "//Edit[@Name='Sales A/c Code']", dataFile, "salesInvoiceAgainstDeliveries", "salesAccountCode");
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, "salesInvoiceAgainstDeliveries", "priceList");
        generalInfoSliderHandle(350);
        enterInput("xpath", "//Edit[@Name='TCS Trans Nature']", dataFile, "salesInvoiceAgainstDeliveries", "tcsTransactionNature");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "salesInvoiceAgainstDeliveries", "executive");
        generalInfoSliderHandle(-400);
        //select pending quantity
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
        //select qty
        List<WebElement> items = common.findWebElements("xpath", "//Pane[@Name='  F3 Items  ']/Pane/Pane/Pane/Table[@Name='Items']/*[starts-with(@Name,'Row')]");
        System.out.println("items size: " + items.size());
        for (int i = 0; i < items.size(); i++) {
            WebElement productList = items.get(i);
            String value = productList.getAttribute("LegacyValue");

            if (!"(null)".equals(value) && !"(Create New)".equals(value)) {
                String pendingQty = common.findWebElement("xpath", "//Edit[@Name='Pending Quantity Row " + i + ", Not sorted.']").getText();
                WebElement quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']");
                quantity.click();
                quantity.sendKeys(pendingQty, Keys.TAB);
            }
        }
        enterChargesAndDeductions(dataFile, "salesInvoiceAgainstDeliveries");
        enterOtherCharges(dataFile, "salesInvoiceAgainstDeliveries");
        //bills Paybale
        navigateToBillsPayablesTab();
        common.deleteInvalidRows();
        //collection tabs
        enterCashinSIAO(dataFile, "salesInvoiceAgainstDeliveries");
        enterChequesinSIAO(dataFile, "salesInvoiceAgainstDeliveries");
        enterPostDatedChequesinSIAO(dataFile, "salesInvoiceAgainstDeliveries");
        enterChequesPDCinSIAO(dataFile, "salesInvoiceAgainstDeliveries");
        enterCreditCardinSIAO(dataFile, "salesInvoiceAgainstDeliveries");
        //summary
        navigateToPaytymTab();
        for (int j = 0; j < 6; j++) {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_RIGHT);
            robot.keyRelease(KeyEvent.VK_RIGHT);
        }
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        netAmountPresentInSummary();
        cessPresentInSummary();
        iGSTPresentInSummary();
        tcsAmountPresentInSummary();
        tcsTaxableValuePresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        //scroll
        scrollRight(6);
        termsAndConditions(dataFile, "salesInvoiceAgainstDeliveries");
        //save
        transactionSave();
        String newVoucherID = newTransactionID(oldVoucherID).replace(" ", "");
        System.out.println("newID: " + newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID, "Voucher Numbers are same. Check Transaction.");
        Thread.sleep(1000);
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Invoices against Deliveries'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID, dataFile, "salesInvoiceAgainstDeliveries");

        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Sales Invoice against Deliveries", duration / 1000000000);
        return newVoucherID;
    }
}