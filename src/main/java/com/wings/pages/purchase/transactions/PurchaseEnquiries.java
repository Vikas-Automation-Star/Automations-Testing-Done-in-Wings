package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.awt.*;
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

    public String purchaseEnquires() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps("Purchase","Enquiries","Purchase Enquiries");
        Thread.sleep(3000);
        String oldVoucherID = oldTTransactionID();
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "Purchase Enquiries", "branch");
//        enterInput("xpath", "//Edit[@Name='Transaction Currency *']", dataFile, "Purchase Enquiries", "currency");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "Purchase Enquiries", "partyCode");
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, "Purchase Enquiries", "priceList");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "Purchase Enquiries", "executive");

        long start = System.nanoTime();

        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, "Purchase Enquiries", "productCount")); i++) {
            addProduct(i);
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("PurchaseEnquiries Enter Products:",duration/1000000000);

        long start1 = System.nanoTime();

        enterOtherInfo(dataFile,"Purchase Enquiries");
        termsAndConditions(dataFile,"Purchase Enquiries");
        navigateToSummaryTab();
        quantityPresentInSummary();
        grossAmountPresentInSummary();
        grossAmountInCompanyCurrencyPresentInSummary();
        transactionSave();
        String transactionId = newTransactionID(oldVoucherID);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Enquiries");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Enquiries'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        verifyReport(transactionId,dataFile,"Purchase Enquiries");

        long duration1 = System.nanoTime() - start1;
        FileUtil.writeTimeLog("purchaseEnquiries validating Tab Items UpTo summary", duration1 /1000000000);

        return transactionId;
    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile,"Purchase Enquiries", "productType" + i).equals("general")) {
            generalProduct_New(dataFile,"Purchase Enquiries", "productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        } else if (common.getData(dataFile,"Purchase Enquiries", "productType" + i).equals("multiBatch")) {
            serialNumProductDirectQuantity(dataFile, "Purchase Enquiries","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        }else if (common.getData(dataFile,"Purchase Enquiries", "productType" + i).equals("serial")) {
            serialNumProductDirectQuantity(dataFile,"Purchase Enquiries", "productCode" + i,"quantity" + i,"freeQuantity" + i, i);
        }
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "Purchase Enquiries","grossAmount" + i),"Gross Amount mismatch");
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Gross Amount In Company Currency Row "+i+", Not sorted.']"),common.getData(dataFile,"Purchase Enquiries","grossInCompanyCurrency"+i),"grossInCompanyCurrency mismatch");
    }

}

