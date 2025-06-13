package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SalesInvoice extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;

    public SalesInvoice(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;

    }

    public String salesInvoice() throws InterruptedException, IOException, ParseException, AWTException {
        long start = System.nanoTime();
        System.out.println("Sales Invoice startTime executed in :" + start);
        navigateToSalesInvoiceMenu();
        Thread.sleep(2000);

        String oldVoucherID = oldTTransactionID();
        System.out.println("oldID: " + oldVoucherID);
        enterDate();
        enterBranchName(dataFile, "salesInvoice", "branch");
        enterLocation(dataFile, "salesInvoice", "location");
        enterCurrency(dataFile, "salesInvoice", "currency");
        enterCashOrParty(dataFile, "salesInvoice", "partyCode");
        Thread.sleep(1500);
        gstTransactionType("Inter State Sales to Registered Dealers");
        Thread.sleep(1000);
        enterCustomerEmail(dataFile, "salesInvoice", "email");
        enterCustomerMobileNum(dataFile, "salesInvoice", "mobileNum");
        enterSalesAccountCode(dataFile, "salesInvoice", "salesAccountCode");
        generalInfoSliderHandle(250);
        enterTcsTransNature(dataFile, "salesInvoice", "tcsTransactionNature");
        common.inputText("xpath", "//Edit[@Name='Invoice Type']", common.getData(dataFile, "salesInvoice", "invoice"));
        Thread.sleep(1000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        enterPriceList(dataFile, "salesInvoice", "priceList");
        generalInfoSliderHandle(400);
        enterExecutive(dataFile, "salesInvoice", "executive");
        enterShippingBillNo(dataFile, "salesInvoice", "shippingBillNo");
        enterShippingDate();
        enterPortCode(dataFile, "salesInvoice", "portCode");
        enterRemarks(dataFile, "salesInvoice", "remarks");
        generalInfoSliderHandle(-500);
        //F3-Items
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount")); i++) {
            addProduct1(i);
        }
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount")); i++) {
            enterChargesAndDeductionsSalesInvoice(dataFile, "salesInvoice",i);
        }
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount")); i++) {
            enterOtherChargesSalesInvoice(dataFile, "salesInvoice", i);
        }
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount")); i++) {
            enterCashSalesInvoice(dataFile, "salesInvoice",i);
        }
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount")); i++) {
            enterChequesSalesInvoice(dataFile, "salesInvoice",i);
        }
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount")); i++) {
            enterPostDatedChequesSalesInvoice(dataFile, "salesInvoice",i);
        }
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount")); i++) {
            enterChequesPDCSalesInvoice(dataFile, "salesInvoice",i);
        }
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount")); i++) {
                    enterCreditCardSalesInvoie(dataFile, "salesInvoice",i);
        }
        scrollRight(10);
        enterOtherInfo(dataFile,"salesInvoice");
        scrollRight(7);
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount")); i++) {
            termsAndConditions(dataFile,"salesInvoice",i);
        }
        enterAllocations(dataFile,"salesInvoice");
        transactionSave();

        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Sales Invoice ended at", duration / 1000000000);
        return "";
    }

//        navigateToBillsPayablesTab();
//        common.deleteInvalidRows();
//        //collections
//        enterCashinSIAO(dataFile, "salesInvoice");
//        enterChequesinSIAO(dataFile, "salesInvoice");
//        enterPostDatedChequesinSIAO(dataFile, "salesInvoice");
//        enterChequesPDCinSIAO(dataFile, "salesInvoice");
//        enterCreditCardinSIAO(dataFile, "salesInvoice");
//        //verify all the fields in summary are fetching data
//        navigateToPaytymTab();
//        for (int j = 0; j < 2; j++) {
//            robot.keyPress(KeyEvent.VK_RIGHT);
//            robot.keyRelease(KeyEvent.VK_RIGHT);
//        }
//        enterOtherInfo(dataFile,"salesInvoice");
//        //summary
//        navigateToOtherInfoTab();
//        for (int i = 0; i < 5; i++) {
//            robot.keyPress(KeyEvent.VK_RIGHT);
//            robot.keyRelease(KeyEvent.VK_RIGHT);
//        }
//        quantityPresentInSummary();
//        grossAmountPresentInSummary();
//        grossMinusDiscountPresentInSummary();
//        netAmountPresentInSummary();
//        cessPresentInSummary();
//        iGSTPresentInSummary();
//        tcsAmountPresentInSummary();
//        tcsTaxableValuePresentInSummary();
//        totalValuePresentInSummary();
//        totalValueInCompanyCurrenyPresentInSummary();
//        //save
//        transactionSave();
//        String newVoucherID =newTransactionID(oldVoucherID);
//        System.out.println("newID: "+newVoucherID);
//        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
//        Thread.sleep(1000);
//        common.clickElement("name", "Sales");
//        common.clickElement("name", "Invoices");
//        common.clickElement("name", "Sales Book");
//        Thread.sleep(1000);
//        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
//        Thread.sleep(1500);
//        verifyReport(newVoucherID,dataFile,"salesInvoice");
//
//        long duration = System.nanoTime() - start;
//        FileUtil.writeTimeLog("Sales Invoice", duration / 1000000000);
//
//        return newVoucherID;
//    }
//
//    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
//        if (common.getData(dataFile, "salesInvoice", "productType" + i).equals("general")) {
//            generalProduct(dataFile, "salesInvoice", "productCode" + i, "quantity" + i, "freeQuantity" + i, i);
//        } else if (common.getData(dataFile, "salesInvoice", "productType" + i).equals("multiBatch")) {
//            multiBatchProduct(dataFile, "salesInvoice", "productCode" + i, "quantity" + i, "freeQuantity" + i, i);
//        } else if (common.getData(dataFile, "salesInvoice", "productType" + i).equals("serial")) {
//            serialNumberProduct(dataFile, "salesInvoice", "productCode" + i, i);
//        }

    public void addProduct1(int i) throws InterruptedException, IOException, ParseException, AWTException {
//            int productCount = Integer.parseInt(common.getData(dataFile, "salesInvoice", "productCount"));
//            for (int i = 0; i < productCount; i++) {
//                enterInput("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", dataFile, "salesInvoice", "productCode" + i);
//            }
//            for (int i = 0; i < productCount; i++) {
//                enterInput("xpath", "//Edit[@Name='Sales Account * Row " + i + ", Not sorted.']", dataFile, "salesInvoice", "salesAccount" + i);
//            }
//        }
//        enterInput("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", dataFile,"salesInvoice", "productCode"+i);
//        enterInput("xpath", "//Edit[@Name='Sales Account * Row "+i+", Not sorted.']",  dataFile,"salesInvoice", "salesAccount" + i);
        if (common.getData(dataFile, "salesInvoice", "productType" + i).equals("general")) {
            generalProductSalesInvoice(dataFile, "salesInvoice", i);
        } else if (common.getData(dataFile, "salesInvoice", "productType" + i).equals("multiBatch")) {
            multiBatchProductSalesInvoice(dataFile, "salesInvoice", i);
        } else if (common.getData(dataFile, "salesInvoice", "productType" + i).equals("serial")) {
            serialNumberProductSalesInvoice(dataFile, "salesInvoice", i);
        }
    }
}