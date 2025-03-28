package com.wings.pages.sales.transactions;
// changes done, need to exec 2
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

public class SRTWIR_UnRegIntraExclusive extends Transaction {
        WindowsDriver driver;
        Common common;
        String dataFile;

        public SRTWIR_UnRegIntraExclusive(WindowsDriver driver,String file){
            super(driver);
            this.driver=driver;
            common=new Common(driver);
            dataFile=file;
        }

        public void UnRegExclusiveInvoiceReference(String salesInvoiceVoucher) throws InterruptedException, IOException, ParseException, AWTException {
            navigateToSalesReturnWithInvoiceReferenceMenu();
            Thread.sleep(1000);
            String oldVoucherId=oldTTransactionID();
            System.out.println("old Transaction ID: " + oldVoucherId);
            //general info selection
            enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"salesReturn","branch");
            common.clickElement("xpath", "//Edit[@Name='Location *']");
            common.findWebElement("xpath","//Edit[@Name='Sales Invoice No *']").sendKeys(salesInvoiceVoucher, Keys.TAB);
            Thread.sleep(1500);
//            gstTransactionType("Intra State Sales Returns from Unregistered Dealers");
//            Thread.sleep(2500);
            common.clickElement("xpath","//Edit[@Name='Sales Return A/c']");
            enterInput("xpath","//Edit[@Name='TCS Trans Nature']",dataFile,"salesInvoice","tcsTransactionNature");
            //items
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 400, 0);
            String pendingQty=common.findWebElement("xpath","//Edit[@Name='Pending Quantity In SKU Row 0, Not sorted.']").getText();
            WebElement quantity=common.findWebElement("xpath","//Edit[@Name='Quantity Row 0, Not sorted.']");
            quantity.click();
            quantity.sendKeys(pendingQty);
            //enter full free quantity
            String pendingFreeQuantity=common.findWebElement("xpath","//Edit[@Name='Pending Free Quantity In SKU Row 0, Not sorted.']").getText();
            WebElement freeQuantity= common.findWebElement("xpath","//Edit[@Name='Free Quantity Row 0, Not sorted.']");
            freeQuantity.click();
            freeQuantity.sendKeys(pendingFreeQuantity,Keys.TAB);
            //enter partial quantity and free quantity
            String pendingQty1=common.findWebElement("xpath","//Edit[@Name='Pending Quantity In SKU Row 1, Not sorted.']").getText();
            double actualPendingQuantity=Double.parseDouble(pendingQty1)-1;
            WebElement quantity1=common.findWebElement("xpath","//Edit[@Name='Quantity Row 1, Not sorted.']");
            quantity1.click();
            quantity1.sendKeys(String.valueOf(actualPendingQuantity),Keys.TAB);
            //enter partial free quantity
            String pendingFreeQuantity1=common.findWebElement("xpath","//Edit[@Name='Pending Free Quantity In SKU Row 1, Not sorted.']").getText();
            double actualFreeQuant=Double.parseDouble(pendingFreeQuantity1)-1;
            WebElement freeQuantity1= common.findWebElement("xpath","//Edit[@Name='Free Quantity Row 1, Not sorted.']");
            freeQuantity1.click();
            freeQuantity1.sendKeys(String.valueOf(actualFreeQuant),Keys.TAB);

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