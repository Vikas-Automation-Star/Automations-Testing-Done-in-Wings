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

public class SRWIR_SIADUnRegInterInclusive extends Transaction {
        WindowsDriver driver;
        Common common;
        String dataFile;

        public SRWIR_SIADUnRegInterInclusive(WindowsDriver driver,String file){
            super(driver);
            this.driver=driver;
            common=new Common(driver);
            dataFile=file;
        }

        public void unRegInclusiveInterInvoiceRef_SIAD(String salesInvoiceVoucher) throws InterruptedException, IOException, ParseException, AWTException {
            long start = System.nanoTime();
            System.out.println("SRWIR_SIAD unRegInterInclusive executed in : " +start);
            Thread.sleep(100);

            navigateToSalesReturnWithInvoiceReferenceMenu();
            Thread.sleep(1000);
            String oldVoucherId=oldTTransactionID();
            System.out.println("old Transaction ID: " + oldVoucherId);
            //general info selection
            enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "srwirSalesInvoiceAgnstDeliveries", "branch");
            enterInput("xpath", "//Edit[@Name='Location *']", dataFile, "srwirSalesInvoiceAgnstDeliveries", "location");
//        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Branch *']"), common.getData(dataFile, "salesReturn", "branch"), "Branch is not validated");
//        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Location *']"), common.getData(dataFile, "salesReturn", "location"), "Location is not validated");

            common.findWebElement("xpath","//Edit[@Name='Sales Invoice No *']").sendKeys(salesInvoiceVoucher, Keys.TAB);
            Thread.sleep(1500);
            gstTransactionType("Inter State Sales Returns from Unregistered Dealers");
            Thread.sleep(2500);

            enterInput("xpath","//Edit[@Name='Sales Return A/c Code']",dataFile,"srwirSalesInvoiceAgnstDeliveries","salesReturnAccountCode");
//        Assert.assertEquals(common.getText("xpath","//Edit[@Name='Sales Return A/c']"),common.getData(dataFile,"salesReturn","salesReturnAccount"),"Sales Account Code is not validated");
//            generalInfoSliderHandle(250);
            enterInput("xpath", "//Edit[@Name='TCS Trans Nature']", dataFile,"srwirSalesInvoiceAgnstDeliveries", "tcsTransactionNature");
//        Assert.assertEquals(common.getText("xpath","//Edit[@Name='TCS Trans Nature']"),common.getData(dataFile,"salesReturn","tcsTransactionNature"),"TCS Nature is not validated");
            //items

            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
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
            verifyReport(newVoucherId,dataFile,"srwirSalesInvoiceAgnstDeliveries");

            long duration = System.nanoTime() - start;
//            System.out.println("endTime executed in :"+duration);
//            System.out.println("helperMethod1 executed in :" + duration / 1000000000 + " sec");
            FileUtil.writeTimeLog("SRWIR_SIAD UnRegInterInclusive",duration/1000000000);
        }
    }