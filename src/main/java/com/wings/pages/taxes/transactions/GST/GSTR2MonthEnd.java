package com.wings.pages.taxes.transactions.GST;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.Assert;

public class GSTR2MonthEnd extends TransactionsBaseClass {
        WindowsDriver driver;
        Common common;
        String dataFile;

        public GSTR2MonthEnd(WindowsDriver driver, String file) {
            super(driver);
            common = new Common(this.driver = driver);
            dataFile = file;
        }

        public void gstr2MonthEnd(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
            long gstr2MonthEndTransactionStart =System.nanoTime();
            navigateToMastersWhen4Steps("Taxes","GST","GSTR2","GSTR2 Month End");
            Thread.sleep(1000);
            String oldVoucherID =oldTTransactionID();
            Thread.sleep(1000);
            enterVoucherType(dataFile,"GeneralInformation","VoucherType");
            EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
            EnterData("//Edit[@Name='GST Registration *']",dataFile,"GeneralInformation","GSTRegistration");
            decimalPrecision("//Edit[@Name='Year And Month *']",dataFile,"GeneralInformation","YearAndMonth");
            enterRemarks(dataFile,"GeneralInformation","Remarks");
            Thread.sleep(1500);

            //no editable data in tabs
            //we need to click get buttons to fetch data. but as of now no records are found, and unable to save transaction bcz of "unable to read/request server"


            transactionSave();
            String newVoucherID =newTransactionID(oldVoucherID);
            System.out.println("newID: "+newVoucherID);
            Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
            //end
            long gstr2MonthEnd = System.nanoTime() - gstr2MonthEndTransactionStart;
            FileUtil.writeTimeLogInMinutes("GSTR2 Month End ended at:- ", gstr2MonthEnd);
            //API
            APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"gstr2MonthEnd");
        }
    }