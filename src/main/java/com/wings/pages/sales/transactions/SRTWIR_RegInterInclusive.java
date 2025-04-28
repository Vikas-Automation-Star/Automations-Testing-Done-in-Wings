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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SRTWIR_RegInterInclusive extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SRTWIR_RegInterInclusive(WindowsDriver driver,String file){
        super(driver);
        this.driver=driver;
        common=new Common(driver);
        dataFile=file;
    }

    public void RegInclusiveInvoiceReference(String salesInvoiceVoucher) throws InterruptedException, IOException, ParseException, AWTException {
        navigateToSalesReturnWithInvoiceReferenceMenu();
        Thread.sleep(1000);
        String oldVoucherId=oldTTransactionID();
        System.out.println("old Transaction ID: " + oldVoucherId);
        //general info selection
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "salesReturn", "branch");
        enterInput("xpath", "//Edit[@Name='Location *']", dataFile, "salesReturn", "location");
//        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Branch *']"), common.getData(dataFile, "salesReturn", "branch"), "Branch is not validated");
//        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Location *']"), common.getData(dataFile, "salesReturn", "location"), "Location is not validated");

        common.findWebElement("xpath","//Edit[@Name='Sales Invoice No *']").sendKeys(salesInvoiceVoucher, Keys.TAB);
        Thread.sleep(1500);
        gstTransactionType("Inter State Sales Returns from Registered Dealers");
        Thread.sleep(2500);

        enterInput("xpath","//Edit[@Name='Sales Return A/c Code']",dataFile,"salesReturn","salesReturnAccountCode");
//        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Sales Return A/c']"),common.getData(dataFile,"salesReturn","salesReturnAccount"),"Sales Account Code is not validated");
        generalInfoSliderHandle(250);
        enterInput("xpath", "//Edit[@Name='TCS Trans Nature']", dataFile,"salesReturn", "tcsTransactionNature");
//        Assert.assertEquals(common.getText("xpath","//Edit[@Name='TCS Trans Nature']"),common.getData(dataFile,"salesReturn","tcsTransactionNature"),"TCS Nature is not validated");
        //items

        // Product codes to search for
        String productCode1 = common.getData(dataFile, "salesInvoice", "productCode0");
        String productCode2 = common.getData(dataFile, "salesInvoice", "productCode1");

        List<String> productCodes = Arrays.asList(productCode1, productCode2);
        List<WebElement> items = common.findWebElements("xpath", "//Pane[@Name='  F3 Items  ']/Pane/Pane/Pane/Table[@Name='Items']/*[starts-with(@Name,'Row')]");
        Set<String> processedCodes = new HashSet<>();
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 400, 0);
        for (int i = 0; i < items.size(); i++) {
            WebElement productList = items.get(i);
            String value = productList.getAttribute("LegacyValue");

            for (String productCode : productCodes) {
                if (value != null && value.contains(productCode) && !processedCodes.contains(productCode)) {
                    if (processedCodes.isEmpty()) {
                        // First matched product - full quantity and free quantity
                        String pendingQty = common.findWebElement("xpath", "//Edit[@Name='Pending Quantity In SKU Row " + i + ", Not sorted.']").getText();
                        WebElement quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']");
                        quantity.click();
                        quantity.sendKeys(pendingQty,Keys.TAB);

                        String pendingFreeQuantity = common.findWebElement("xpath", "//Edit[@Name='Pending Free Quantity In SKU Row " + i + ", Not sorted.']").getText();
                        WebElement freeQuantity = common.findWebElement("xpath", "//Edit[@Name='Free Quantity Row " + i + ", Not sorted.']");
                        freeQuantity.click();
                        freeQuantity.sendKeys(pendingFreeQuantity, Keys.TAB);
                    }
//                    else {
//                        // Second matched product - pending quantity - 1 and free quantity - 1
//                        String pendingQty = common.findWebElement("xpath", "//Edit[@Name='Pending Quantity In SKU Row " + i + ", Not sorted.']").getText();
//                        double actualPendingQuantity = Double.parseDouble(pendingQty) - 1;
//                        WebElement quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']");
//                        quantity.click();
//                        quantity.sendKeys(String.valueOf(actualPendingQuantity), Keys.TAB);
//
//                        String pendingFreeQuantity = common.findWebElement("xpath", "//Edit[@Name='Pending Free Quantity In SKU Row " + i + ", Not sorted.']").getText();
//                        double actualFreeQuant = Double.parseDouble(pendingFreeQuantity) - 1;
//                        WebElement freeQuantity = common.findWebElement("xpath", "//Edit[@Name='Free Quantity Row " + i + ", Not sorted.']");
//                        freeQuantity.click();
//                        freeQuantity.sendKeys(String.valueOf(actualFreeQuant), Keys.TAB);
//                    }
                    processedCodes.add(productCode);
                }
            }
            if (processedCodes.size() == productCodes.size()) {
                break;
            }
        }
            common.deleteInvalidRows();
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 700, 0);
            double netValue=Double.parseDouble(common.findWebElement("xpath","//Edit[@AutomationId='NetAmount']").getText().replace(",",""));
            tcsCalculations(netValue);
            navigateToBillsReceivablesTab();
            common.deleteInvalidRows();
            //summary
            navigateToOtherInfoTab();
            for (int i = 0; i < 2; i++) {
                Robot robot=new Robot();
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_RIGHT);
            }
            quantityPresentInSummary();
            cessPresentInSummary();
            netAmountPresentInSummary();
            totalValuePresentInSummary();
            totalValueInCompanyCurrenyPresentInSummary();
            //save
            transactionSave();
            String newVoucherId =newTransactionID(oldVoucherId).replace(" ","");
            System.out.println("new Transaction ID: "+ newVoucherId);
            Assert.assertNotEquals(oldVoucherId, newVoucherId,"Voucher Num isn't updated");
            //navigate to report
            common.clickElement("name", "Sales");
            common.clickElement("name", "Invoices");
            common.clickElement("xpath", "//MenuItem[@Name='Sales Returns with Invoice Reference']");
            Thread.sleep(1000);
            common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
            Thread.sleep(1500);
            verifyReport(newVoucherId,dataFile,"salesReturn");
    }
}