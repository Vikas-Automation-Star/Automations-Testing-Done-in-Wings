package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class PurchaseEnquiries extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public PurchaseEnquiries(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchaseEnquires() throws InterruptedException, IOException, ParseException {
        long start = System.nanoTime();
        System.out.println("startTime executed in :"+start);
        Thread.sleep(100);
        navigateToPurchaseEnquiriesCancellation();
        navigateToPurchaseQuotations();
        navigateToPurchaseOrders();
        navigateToPurchaseOrdersaCancellation();
        navigateToPurchaseEnquiries();
        long duration = System.nanoTime() - start;
        System.out.println("endTime executed in :"+duration);
        System.out.println("helperMethod1 executed in :" + duration / 1_000_000_000 + " sec");
        FileUtil.writeTimeLog("PurchaseEnquiries",duration/1_000_000_000);






















//        long startTime= Instant.now().getEpochSecond();
//        System.out.println("startTime :"+startTime);
//        navigateToPurchaseEnquiries();
//        Thread.sleep(3000);
//        common.clickElement("xpath", "//Edit[@Name='Branch *']");
//        selectMasterWithValidation(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
//        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
//        selectMasterWithValidation(common.getData(dataFile, "currency"), "xpath", "//Edit[@Name='Transaction Currency *']");
//        common.clickElement("xpath", "//Edit[@Name='Party Code']");
//        selectAndValidateDataNew(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Party Code']");
//        common.clickElement("xpath", "//Edit[@Name='Price List']");
//        selectMasterWithValidation(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
//        common.clickElement("xpath", "//Edit[@Name='Executive *']");
//        selectMasterWithValidation(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
//        enterData("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']", dataFile, "productCode");
//        common.clickElement("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']");
//        common.inputText("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", common.getData(dataFile, "quantity"));
//        common.clickElement("xpath", "//Edit[@Name='Unit Rate Row 0, Not sorted.']");
//        common.inputText("xpath", "//Edit[@Name='Unit Rate Row 0, Not sorted.']", common.getData(dataFile, "unitRate"));
//        Thread.sleep(2000);
//        transactionSave();
//        Thread.sleep(1500);
//        String transactionId = getNewTransactionId();
//        System.out.println("New Transaction ID: " + transactionId);
//        closeTransaction("Purchase Enquiries");
//        navigateToPurchaseEnquiriesCancellation();
//        navigateToPurchaseQuotations();
//        navigateToPurchaseOrders();
//        navigateToPurchaseOrdersaCancellation();
//        long endTime=Instant.now().getEpochSecond();
//        System.out.println("endTime :"+endTime);
//        System.out.println((endTime-startTime));
//        FileUtil.writeTimeLog("PurchaseEnquiries",(endTime-startTime));

    }
}

