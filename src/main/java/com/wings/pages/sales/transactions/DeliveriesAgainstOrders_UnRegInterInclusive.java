package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class DeliveriesAgainstOrders_UnRegInterInclusive extends Transaction{

    WindowsDriver driver;
    Common common;
    String dataFile;

    public DeliveriesAgainstOrders_UnRegInterInclusive(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String[] interInclusiveUnRegDeliveries(String voucherNum) throws InterruptedException, IOException, ParseException, AWTException {
        navigateToDeliveriesAgainstOrdersMenu();
        Thread.sleep(2000);
        String oldVoucherID = oldTTransactionID();
        System.out.println("oldID: " + oldVoucherID);
        //branch selection
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "deliveriesAgainstOrders", "branch");
        enterInput("xpath", "//Edit[@Name='Location *']", dataFile, "deliveriesAgainstOrders", "location");
//        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Branch *']"), common.getData(dataFile, "deliveriesAgainstOrders", "branch"), "Branch is not validated");
//        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Location *']"), common.getData(dataFile, "deliveriesAgainstOrders", "location"), "Location is not validated");
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, "deliveriesAgainstOrders", "partyCode");
        Thread.sleep(2500);
        gstTransactionType("Inter State Sales to Unregistered Dealers");
        Thread.sleep(1000);
        selectPendingsSalesOrder(voucherNum, common.getData(dataFile,"deliveriesAgainstOrders","FYyear"));
        Thread.sleep(2000);
//        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Party Account *']"), common.getData(dataFile, "deliveriesAgainstOrders", "partyName"));
        //enter quantity
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,"SalesOrder", "productCount")); i++) {
            addProduct(i);
        }

        validateIGSTAmountTabIsNotEmpty();;
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
        cessPresentInSummary();
        iGSTPresentInSummary();
        netAmountPresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();
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
        return new String[]{newVoucherID,originalID};
//            return "DELO Completed SuccessFully";
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