package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SalesInvoiceAgainstOrders extends Transaction {

        WindowsDriver driver;
        Common common;
        String dataFile;

        public SalesInvoiceAgainstOrders(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public String invoiceAgainstOrders(String voucherNum) throws InterruptedException, IOException, ParseException, AWTException {
            long start = System.nanoTime();
            System.out.println("SIAO startTime executed in :"+start);
            Thread.sleep(100);

            navigateToSalesInvoiceAgainstOrdersMenu();
            Thread.sleep(4000);

            String oldVoucherID =oldTTransactionID();
            System.out.println("oldID: "+ oldVoucherID);
            //branch selection
            enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "salesInvoiceAgainstOrders", "branch");
            enterInput("xpath", "//Edit[@Name='Location *']", dataFile, "salesInvoiceAgainstOrders", "location");
            enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "salesInvoiceAgainstOrders", "partyCode");
            Thread.sleep(1000);
            gstTransactionType("Inter State Sales to Registered Dealers");
            Thread.sleep(2500);
            selectPendingsSalesOrder(voucherNum,"20250401");
            common.clickElement("xpath","//Button[@Name='OK']");
            Thread.sleep(1000);
            enterInput("xpath", "//Edit[@Name='Sales A/c Code']",dataFile,"salesInvoiceAgainstOrders", "salesAccountCode");
            enterInput("xpath", "//Edit[@Name='TCS Trans Nature']", dataFile, "salesInvoiceAgainstOrders", "tcsTransactionNature");
            enterInput("xpath", "//Edit[@Name='Price List']",dataFile,"salesInvoiceAgainstOrders", "priceList");
            //select pending quantity
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 250, 0);
            //select qty
            for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"salesEnquiry", "productCount")); i++) {
                addProduct(i);
            }
            enterChargesAndDeductions(dataFile,"salesInvoiceAgainstOrders");
            enterOtherCharges(dataFile,"salesInvoiceAgainstOrders");
            //bills Paybale
            navigateToBillsPayablesTab();
            common.deleteInvalidRows();
            //collections
            enterCashinSIAO(dataFile,"salesInvoiceAgainstOrders");
            enterChequesinSIAO(dataFile,"salesInvoiceAgainstOrders");
            enterPostDatedChequesinSIAO(dataFile,"salesInvoiceAgainstOrders");
            enterChequesPDCinSIAO(dataFile,"salesInvoiceAgainstOrders");
            enterCreditCardinSIAO(dataFile,"salesInvoiceAgainstOrders");
            //summary
            navigateToPaytymTab();
            for (int j = 0; j < 5; j++) {
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
            //terms
            scrollRight(5);
            termsAndConditions(dataFile,"salesInvoiceAgainstOrders");
            //save
            transactionSave();
            String newVoucherID = newTransactionID(oldVoucherID);
            System.out.println("newID: " + newVoucherID);
            Assert.assertNotEquals(newVoucherID, oldVoucherID, "Voucher Numbers are same. Check Transaction.");
            Thread.sleep(1000);
            common.clickElement("name", "Sales");
            common.clickElement("name", "Invoices");
            common.clickElement("xpath", "//MenuItem[@Name='Sales Invoices against Orders'][2]");
            Thread.sleep(1000);
            common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
            Thread.sleep(1500);
            verifyReport(newVoucherID, dataFile, "salesInvoiceAgainstOrders");

            long duration = System.nanoTime() - start;
            FileUtil.writeTimeLog("Sales Invoice Against Orders",duration/1000000000);

            return newVoucherID;
        }
    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile,"salesEnquiry", "productType" + i).equals("general")) {
            generalProductInDELO(dataFile,"salesEnquiry", "productCode" + i, "quantity" + i, i);
        } else if (common.getData(dataFile,"salesEnquiry", "productType" + i).equals("multiBatch")) {
            multiBatchProductInDELO(dataFile, "salesEnquiry","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        }else if (common.getData(dataFile,"salesEnquiry", "productType" + i).equals("serial")) {
            serialNumberProductInDELO(dataFile,"salesEnquiry", "productCode" + i, i);
        }
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -300, 0);
    }
    }