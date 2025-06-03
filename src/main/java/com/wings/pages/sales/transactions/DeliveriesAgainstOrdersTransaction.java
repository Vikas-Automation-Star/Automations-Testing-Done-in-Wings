package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class DeliveriesAgainstOrdersTransaction extends Transaction{
        WindowsDriver driver;
        Common common;
        String dataFile;

        public DeliveriesAgainstOrdersTransaction(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public String deliveriesAgainstOrders(String voucherNum) throws InterruptedException, IOException, ParseException, AWTException {
            long start = System.nanoTime();
            System.out.println("Deliveries Against Orders startTime executed in :"+start);
            navigateToDeliveriesAgainstOrdersMenu();
            Thread.sleep(2000);
            String oldVoucherID = oldTTransactionID();
            System.out.println("oldID: " + oldVoucherID);
            //branch selection
            enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "deliveriesAgainstOrders", "branch");
            enterInput("xpath", "//Edit[@Name='Location *']", dataFile, "deliveriesAgainstOrders", "location");
            enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "deliveriesAgainstOrders", "partyCode");
            Thread.sleep(2500);
            gstTransactionType("Inter State Sales to Registered Dealers");
            Thread.sleep(1000);
            selectPendingsSalesOrder(voucherNum, common.getData(dataFile,"deliveriesAgainstOrders","fyYear"));
            Thread.sleep(2000);
            //enter quantity
            for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"deliveriesAgainstOrders", "productCount")); i++) {
                addProduct(i);
            }
            enterChargesAndDeductions(dataFile,"deliveriesAgainstOrders");
            enterOtherCharges(dataFile,"deliveriesAgainstOrders");
            enterOtherInfo(dataFile,"deliveriesAgainstOrders");

            //verify all the fields in summary are fetching data
            navigateToOtherInfoTab();
            for (int j = 0; j < 2; j++) {
                Robot robot=new Robot();
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_RIGHT);
            }
            quantityPresentInSummary();
            grossAmountPresentInSummary();
            cessPresentInSummary();
            iGSTPresentInSummary();
            netAmountPresentInSummary();
            totalValuePresentInSummary();
            totalValueInCompanyCurrenyPresentInSummary();
            //terms
            termsAndConditions(dataFile,"deliveriesAgainstOrders");
            //save
            transactionSave();
            String originalID=newTransactionID(oldVoucherID);
            String newVoucherID =newTransactionID(oldVoucherID).replace(" ","");
            System.out.println("newID: "+newVoucherID);
            Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
            Thread.sleep(1000);
            common.clickElement("name", "Sales");
            common.clickElement("name", "Deliveries");
            common.clickElement("xpath", "//MenuItem[@Name='Deliveries against Orders'][2]");
            Thread.sleep(1000);
            common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
            Thread.sleep(1500);
//        verifyReport(newVoucherID,dataFile,"SalesOrder");
            return "";
        }

        public void addProduct(int i) throws InterruptedException, IOException, ParseException, AWTException {
            if (common.getData(dataFile,"SalesOrder", "productType" + i).equals("general")) {
                generalProductInDELO(dataFile,"SalesOrder", "productCode" + i, "quantity" + i, i);
            } else if (common.getData(dataFile,"SalesOrder", "productType" + i).equals("multiBatch")) {
                multiBatchProductInDELO(dataFile, "SalesOrder","productCode" + i, "quantity" + i,"freeQuantity" + i, i);
            }else if (common.getData(dataFile,"SalesOrder", "productType" + i).equals("serial")) {
                serialNumberProductInDELO(dataFile,"SalesOrder", "productCode" + i, i);
            }
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -300, 0);
        }
    }