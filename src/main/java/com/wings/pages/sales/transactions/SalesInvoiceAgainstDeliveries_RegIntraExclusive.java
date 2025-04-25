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

public class SalesInvoiceAgainstDeliveries_RegIntraExclusive extends Transaction {
        WindowsDriver driver;
        Common common;
        String dataFile;

        public SalesInvoiceAgainstDeliveries_RegIntraExclusive(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public void RegIntraExclusiveSIAD(String voucherNum) throws InterruptedException, IOException, ParseException, AWTException {
            long start = System.nanoTime();
            System.out.println("SIAD startTime executed in :"+start);
            Thread.sleep(100);

            navigateToSalesInvoiceAgainstDeliveriesMenu();
            Thread.sleep(4000);

            String oldVoucherID =oldTTransactionID();
            System.out.println("oldID: "+ oldVoucherID);
            //branch selection
            enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "salesInvoiceAgainstDeliveries", "branch");
            enterInput("xpath", "//Edit[@Name='Location *']", dataFile, "salesInvoiceAgainstDeliveries", "location");
            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Branch *']"), common.getData(dataFile, "salesInvoiceAgainstDeliveries", "branch"), "Branch is not validated");
            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Location *']"), common.getData(dataFile, "salesInvoiceAgainstDeliveries", "location"), "Location is not validated");
            enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "salesInvoiceAgainstDeliveries", "partyCode");
            Thread.sleep(1000);
            gstTransactionType("Intra State Sales to Registered Dealers");
            Thread.sleep(2500);
            selectPendingsSalesOrder(voucherNum, common.getData(dataFile,"salesInvoiceAgainstDeliveries","fyYear"));
            Thread.sleep(3000);
            enterInput("xpath", "//Edit[@Name='Sales A/c Code']",dataFile,"salesInvoiceAgainstDeliveries", "salesAccountCode");
            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Sales A/c Code']"), common.getData(dataFile, "salesInvoiceAgainstDeliveries", "salesAccountCode"), "Sales Account code is not validated");
            enterInput("xpath", "//Edit[@Name='Price List']",dataFile,"salesInvoiceAgainstDeliveries", "priceList");
            Assert.assertEquals(common.getText("xpath","//Edit[@Name='Price List']"),common.getData(dataFile,"salesInvoiceAgainstDeliveries","priceList"),"Price List isn't validated");
            generalInfoSliderHandle(350);
            enterInput("xpath", "//Edit[@Name='TCS Trans Nature']", dataFile, "salesInvoiceAgainstDeliveries", "tcsTransactionNature");
            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='TCS Trans Nature']"), common.getData(dataFile, "salesInvoiceAgainstDeliveries", "tcsTransactionNature"), "TCS Nature is not validated");
            enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "salesInvoiceAgainstDeliveries","executive");
            Assert.assertEquals(common.getText("xpath","//Edit[@Name='Executive *']"),common.getData(dataFile,"salesInvoiceAgainstDeliveries","executive"),"Executive isn't validated");
            generalInfoSliderHandle(-400);
            //select pending quantity
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
            //select qty
            List<WebElement> items = common.findWebElements("xpath", "//Pane[@Name='  F3 Items  ']/Pane/Pane/Pane/Table[@Name='Items']/*[starts-with(@Name,'Row')]");
            System.out.println("items size: "+items.size());
            for (int i = 0; i < items.size(); i++) {
                WebElement productList = items.get(i);
                String value = productList.getAttribute("LegacyValue");
//            System.out.println("value: "+value);

                if (!"(null)".equals(value) && !"(Create New)".equals(value)){
                    String pendingQty = common.findWebElement("xpath", "//Edit[@Name='Pending Quantity Row "+i+", Not sorted.']").getText();
                    WebElement quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']");
                    quantity.click();
                    quantity.sendKeys(pendingQty, Keys.TAB);

                    //no free quantity in sales order as of now
//                        String pendingFreeQuantity = common.findWebElement("xpath", "//Edit[@Name='Pending Free Quantity In SKU Row " + i + ", Not sorted.']").getText();
//                        WebElement freeQuantity = common.findWebElement("xpath", "//Edit[@Name='Free Quantity Row " + i + ", Not sorted.']");
//                        freeQuantity.click();
//                        freeQuantity.sendKeys(pendingFreeQuantity, Keys.TAB);
                }
            }
            //bills Paybale
            navigateToBillsPayablesTab();
            common.deleteInvalidRows();
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
            sgstPresentInSummary();
            cgstPresentInSummary();
            tcsAmountPresentInSummary();
            tcsTaxableValuePresentInSummary();
            totalValuePresentInSummary();
            totalValueInCompanyCurrenyPresentInSummary();
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

        }
    }