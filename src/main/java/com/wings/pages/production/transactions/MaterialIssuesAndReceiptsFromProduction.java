package com.wings.pages.production.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class MaterialIssuesAndReceiptsFromProduction extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public MaterialIssuesAndReceiptsFromProduction(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void materialIssuesAndReceiptsFromProduction(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
//        common.clickElement("name", "Production");
//        common.clickElement("name", "Simple");
//        common.clickElement("name", "Material Issues and Receipts from Production");
        navigateToMastersWhen3Steps("Production","Simple","Material Issues and Receipts from Production");
        Thread.sleep(3000);
        String oldVoucherID =oldTTransactionID();



        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"MaterialIssuesAndReceiptsFromProduction");

    }
}
