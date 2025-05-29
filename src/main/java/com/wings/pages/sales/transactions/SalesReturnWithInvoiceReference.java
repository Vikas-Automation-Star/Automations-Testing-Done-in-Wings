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

public class SalesReturnWithInvoiceReference extends Transaction {
        WindowsDriver driver;
        Common common;
        String dataFile;

        public SalesReturnWithInvoiceReference(WindowsDriver driver,String file){
            super(driver);
            this.driver=driver;
            common=new Common(driver);
            dataFile=file;
        }

        public void salesReturnWithInvoiceReference(String salesInvoiceVoucher) throws InterruptedException, IOException, ParseException, AWTException {
            long start = System.nanoTime();
            System.out.println("SRWIR_SIAO: " +start);

            Thread.sleep(100);
            navigateToSalesReturnWithInvoiceReferenceMenu();
            Thread.sleep(1000);
            String oldVoucherId=oldTTransactionID();
            System.out.println("old Transaction ID: " + oldVoucherId);
            //general info selection
            enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "salesReturnWithInvoiceReference", "branch");
            enterInput("xpath", "//Edit[@Name='Location *']", dataFile, "salesReturnWithInvoiceReference", "location");
            common.findWebElement("xpath","//Edit[@Name='Sales Invoice No *']").sendKeys(salesInvoiceVoucher, Keys.TAB);
            Thread.sleep(1500);
            gstTransactionType("Inter State Sales Returns from Registered Dealers");
            Thread.sleep(2500);
            common.clickElement("xpath","//Button[@Name='OK']");
            enterInput("xpath","//Edit[@Name='Sales Return A/c Code']",dataFile,"salesReturnWithInvoiceReference","salesReturnAccountCode");
            enterInput("xpath", "//Edit[@Name='TCS Trans Nature']", dataFile,"salesReturnWithInvoiceReference", "tcsTransactionNature");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
            //select qty
            List<WebElement> items = common.findWebElements("xpath", "//Pane[@Name='  F3 Items  ']/Pane/Pane/Pane/Table[@Name='Items']/*[starts-with(@Name,'Row')]");
            System.out.println("items size: "+items.size());
            for (int i = 0; i < items.size(); i++) {
                WebElement productList = items.get(i);
                String value = productList.getAttribute("LegacyValue");

                if (!"(null)".equals(value) && !"(Create New)".equals(value)){
                    String pendingQty = common.findWebElement("xpath", "//Edit[@Name='Pending Quantity Row "+i+", Not sorted.']").getText();
                    WebElement quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']");
                    quantity.click();
                    quantity.sendKeys(pendingQty, Keys.TAB);
                }
            }
            common.deleteInvalidRows();
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 700, 0);
            double netValue=Double.parseDouble(common.findWebElement("xpath","//Edit[@AutomationId='NetAmount']").getText().replace(",",""));
            //charges and Deductions
            enterChargesAndDeductionsNoSpace(dataFile,"salesReturnWithInvoiceReference");
            enterOtherCharges(dataFile,"salesReturnWithInvoiceReference");

            tcsCalculations(netValue);
            navigateToBillsReceivablesTab();
            common.deleteInvalidRows();
            //collections
            enterCash(dataFile,"salesReturnWithInvoiceReference");
            enterChequesinSRWIRF(dataFile,"salesReturnWithInvoiceReference");
            enterPostDatedChequesInPurchase(dataFile,"salesReturnWithInvoiceReference");
            enterChequesPDCInPurchase(dataFile,"salesReturnWithInvoiceReference");
            //summary
            navigateToOtherInfoTab();
            for (int i = 0; i < 2; i++) {
                Robot robot=new Robot();
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
            totalValuePresentInSummary();
            totalValueInCompanyCurrenyPresentInSummary();
            //terms
            scrollRight(6);
            termsAndConditions(dataFile,"salesReturnWithInvoiceReference");
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
            verifyReport(newVoucherId,dataFile,"salesReturnWithInvoiceReference");

            long duration = System.nanoTime() - start;
            FileUtil.writeTimeLog("SRWIR_SIAO",duration/1000000000);
        }
    }