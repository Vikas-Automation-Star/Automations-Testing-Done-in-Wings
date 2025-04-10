package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SalesOrder_UnRegIntraExclusive extends Transaction {
        WindowsDriver driver;
        Common common;
        String dataFile;
        boolean gstAmountClicked = false;

        public SalesOrder_UnRegIntraExclusive(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public String intraExclusiveUnReg() throws InterruptedException, IOException, ParseException, AWTException {
            navigateToSalesOrderMenu();
            Thread.sleep(2000);
            String oldVoucherID =oldTTransactionID();
            System.out.println("oldID: "+ oldVoucherID);
            //branch selection
            enterInput("xpath", "//Edit[@Name='Branch *']",dataFile, "SalesOrder","branch");
            enterInput("xpath", "//Edit[@Name='Location *']",dataFile, "SalesOrder","location");
            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Branch *']"), common.getData(dataFile, "SalesOrder", "branch"), "Branch is not validated");
            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Location *']"), common.getData(dataFile, "SalesOrder", "location"), "Location is not validated");
            enterInput("xpath", "//Edit[@Name='Party Code']",dataFile, "SalesOrder","partyCode");
            Thread.sleep(2500);
            gstTransactionType("Intra State Sales to Unregistered Dealers");
            Thread.sleep(1000);
            Assert.assertEquals(common.getText("xpath","//Edit[@Name='Party Account *']"),common.getData(dataFile,"SalesOrder","partyName"));
            enterInput("xpath", "//Edit[@Name='Price List']",dataFile,"SalesOrder", "priceList");
            Assert.assertEquals(common.getText("xpath","//Edit[@Name='Price List']"),common.getData(dataFile,"SalesOrder","priceList"),"Price List isn't validated");
            enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "SalesOrder","executive");
            Assert.assertEquals(common.getText("xpath","//Edit[@Name='Executive *']"),common.getData(dataFile,"SalesOrder","executive"),"Executive isn't validated");
            generalInfoSliderHandle(-500);
            common.clickElement("xpath","//CheckBox[@Name='Advance Receipts']");
            //F3-Items
            for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"SalesOrder", "productCount")); i++) {
                addProduct(i);
            }
            validateCGSTAmountTabIsNotEmpty();
            validateSGSTAmountTabIsNotEmpty();
            validateCESSAmountTabIsNotEmpty();
            //verify all the fields in summary are fetching data
            navigateToOtherInfoTab();
            for (int j = 0; j < 2; j++) {
                Robot robot=new Robot();
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_RIGHT);
            }
            quantityPresentInSummary();
            grossAmountPresentInSummary();
            netAmountPresentInSummary();
            cgstPresentInSummary();
            sgstPresentInSummary();
            cessPresentInSummary();
            totalValuePresentInSummary();
            totalValueInCompanyCurrenyPresentInSummary();
            //save
            transactionSave();
            String newVoucherID =newTransactionID(oldVoucherID).replace(" ","");
            System.out.println("newID: "+newVoucherID);
            Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
            Thread.sleep(1000);
            common.clickElement("name", "Sales");
            common.clickElement("name", "Orders");
            common.clickElement("xpath", "//MenuItem[@Name='Sales Orders'][2]");
            Thread.sleep(1000);
            common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
            Thread.sleep(1500);
            verifyReport(newVoucherID,dataFile,"SalesOrder");
            return newVoucherID;
        }

        public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
            if (common.getData(dataFile,"SalesOrder", "productType" + i).equals("general")) {
                generalProduct_New(dataFile,"SalesOrder", "productCode" + i, "quantity" + i,"freeQuantity" + i, i);
            } else if (common.getData(dataFile,"SalesOrder", "productType" + i).equals("multiBatch")) {
                multiBatchProduct_New(dataFile, "SalesOrder","transType","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
            }else if (common.getData(dataFile,"SalesOrder", "productType" + i).equals("serial")) {
                serialNumProduct_New(dataFile,"SalesOrder", "transType","productCode" + i,"quantity" + i,"freeQuantity" + i, i);
            }

            //validate
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 450, 0);
            //MRP and gross Amount
            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='MRP Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"SalesOrder", "mrpAmount" + i),"MRP Amount mismatch");
            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Gross Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "SalesOrder","grossAmount" + i),"Gross Amount mismatch");
            //discount
            enterData("xpath", "//Edit[@Name='Voucher Disc % Row " + i + ", Not sorted.']", dataFile,"SalesOrder", "voucherDiscount" + i);
            enterData("xpath", "//Edit[@Name='Party Disc % Row " + i + ", Not sorted.']", dataFile,"SalesOrder", "partyDiscount" + i);
            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Voucher Disc Row " + i +", Not sorted.']"),common.getData(dataFile,"SalesOrder","voucherDiscAmount"+i),"Voucher discount value mismatch");
            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Party Disc Row "+ i +", Not sorted.']"),common.getData(dataFile,"SalesOrder","partyDiscAmount"+i),"Party discount value mismatch");

            enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile,"SalesOrder", "HSNCode");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 250, 0);

            //taxable
            Assert.assertEquals(common.getText("xpath","//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']"),common.getData(dataFile,"SalesOrder","taxableValue"+i),"Taxable value mismatch");

            if (!gstAmountClicked) {
                common.clickElement("xpath", "//Header[@Name='GST Amount']");
                gstAmountClicked = true;
            }

            //check tax and net Amount
            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='CGST Row " + i + ", Not sorted.']"), common.getData(dataFile, "SalesOrder","cgstAmount" + i),"SGST mismatch");
            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='SGST Row " + i + ", Not sorted.']"), common.getData(dataFile, "SalesOrder","sgstAmount" + i),"SGST mismatch");
            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']"), common.getData(dataFile,"SalesOrder", "cessAmount" +i),"CESS mismatch");
            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,"SalesOrder", "expectedGStExclusive" +i), "GST Amount mismatch");
            Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, "SalesOrder","netAmount" +i), "Net Amount mismatch");
        }
    }