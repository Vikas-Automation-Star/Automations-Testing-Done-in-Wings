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

public class Deliveries extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false;

    public Deliveries(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String salesDeliveries() throws InterruptedException, IOException, ParseException, AWTException {
        long start = System.nanoTime();
        System.out.println("deliveries startTime executed in :" + start);
        Thread.sleep(100);

        navigateToDeliveriesMenu();
        Thread.sleep(3000);

        String oldVoucherID = oldTTransactionID();
        System.out.println("oldID: " + oldVoucherID);
        //branch selection
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "deliveries", "branch");
        enterInput("xpath", "//Edit[@Name='Location *']", dataFile, "deliveries", "location");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "deliveries", "partyCode");
        Thread.sleep(1000);
        gstTransactionType("Inter State Sales to Registered Dealers");
        Thread.sleep(2000);
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, "deliveries", "priceList");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "deliveries", "executive");

        //items
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"deliveries", "productCount")); i++) {
            addProduct(i);
        }
        navigateToChargesAndDeductionsTab();
        enterData("xpath", "//Edit[@Name='Charges Or Deductions * Row 0, Not sorted.']", dataFile,"deliveries", "chargesDeductions");
        enterData("xpath", "//Edit[@Name='Account Code Row 0, Not sorted.']", dataFile,"deliveries", "chargesAccount");
        enterData("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']", dataFile,"deliveries", "chargesAmount");
        //other charges
        navigateToOtherChargesTab();
        enterData("xpath", "//Edit[@Name='Account Code Row 0, Not sorted.']", dataFile,"deliveries", "chargesAccount");
        enterData("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']", dataFile, "deliveries","chargesAmount");
        enterData("xpath", "//Edit[@Name='HSN Row 0, Not sorted.']", dataFile, "deliveries","HSNCode");

        validateIGSTAmountTabIsNotEmpty();;
        validateCESSAmountTabIsNotEmpty();
        //summary
        common.clickElement("xpath","//TabItem[@Name='  Ctrl-F8 Summary  ']");
        quantityPresentInSummary();
        freeQuantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        netAmountPresentInSummary();
        cessPresentInSummary();
        iGSTPresentInSummary();
        chargesPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        Thread.sleep(2000);
        common.clickElement("xpath", "//MenuItem[@Name='Sales']");
        common.clickElement("xpath", "//MenuItem[@Name='Deliveries']");
        common.clickElement("xpath", "//Menu[@Name='Deliveries']/MenuItem[@Name='Deliveries'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID,dataFile,"deliveries");

        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("deliveries",duration/1000000000);

        return newVoucherID;
//        return " ";
    }

    public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
        if (common.getData(dataFile,"deliveries", "productType" + i).equals("general")) {
            generalProduct(dataFile,"deliveries", "productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        } else if (common.getData(dataFile,"deliveries", "productType" + i).equals("multiBatch")) {
            multiBatchProduct(dataFile, "deliveries","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
        }else if (common.getData(dataFile,"deliveries", "productType" + i).equals("serial")) {
            serialNumberProduct(dataFile,"deliveries", "productCode" + i, i);
        }

        //validate
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 650, 0);
        //MRP and gross Amount
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='MRP Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"deliveries", "mrpAmount" + i),"MRP Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "deliveries","grossAmount" + i),"Gross Amount mismatch");

        enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile,"deliveries", "HSNCode");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 250, 0);

        //taxable
        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']"),common.getData(dataFile,"deliveries","taxableValue"+i),"Taxable value mismatch");

        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }

        //check tax and net Amount
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']"), common.getData(dataFile, "deliveries","igstAmount" + i),"IGST mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']"), common.getData(dataFile,"deliveries", "cessAmount" +i),"CESS mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"deliveries", "expectedGStExclusive" +i), "GST Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "deliveries","netAmount" +i), "Net Amount mismatch");
    }
}