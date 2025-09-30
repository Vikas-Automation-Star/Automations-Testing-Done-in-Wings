package com.wings.pages.taxes.transactions.GST;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GSTR1MonthEnd extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public GSTR1MonthEnd(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void gstr1MonthEnd(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long gstr1MonthEndTransactionStart=System.nanoTime();
        navigateToMastersWhen4Steps("Taxes","GST","GSTR1","GSTR1 Month End");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        Thread.sleep(1000);
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        EnterData("//Edit[@Name='GST Registration *']",dataFile,"GeneralInformation","GSTRegistration");
//        EnterData("//Edit[@Name='GSTIN *']",dataFile,"GeneralInformation","GSTIN");
        decimalPrecision("//Edit[@Name='Year And Month *']",dataFile,"GeneralInformation","YearAndMonth");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        Thread.sleep(1500);
        //no editable data in tabs

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long gstr1MonthEnd = System.nanoTime() - gstr1MonthEndTransactionStart ;
        FileUtil.writeTimeLogInMinutes("GSTR1 Month End ended at:- ", gstr1MonthEnd);
        //API
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"gstr1MonthEnd");
    }
}