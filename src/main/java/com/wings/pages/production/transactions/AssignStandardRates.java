package com.wings.pages.production.transactions;

import com.wings.pages.Transaction;
import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import net.bytebuddy.asm.Advice;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class AssignStandardRates extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public AssignStandardRates(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void assignStandardRates(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        navigateToMastersWhen2Steps("Production","Assign Standard Rates");
//        common.clickElement("name", "Production");
//        common.clickElement("name", "Assign Standard Rates");
        Thread.sleep(2000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        EnterDate("//Edit[@Name='With Effect From *']",dataFile,"GeneralInformation","WithEffectFrom");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        items();

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
//        closeTransaction("Assign Standard Rates");

        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"JournalEntries");
        deleteRecentTransaction();
    }
    public void items() throws IOException {
            List<String> productCode=readExcelData(dataFile,"Items","ProductCode");
            System.out.println("productCodes :"+productCode.size());
            for (int i = 0; i < productCode.size() ; i++) {
                addData("xpath","//Edit[@Name='Product Code Row "+i+", Not sorted.']",dataFile,"Items","ProductCode",i);
            }
            List<WebElement>  rate= common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Rate * Row ')]");
            for (int i = 0; i < productCode.size(); i++) {
                enterListData(rate.get(i), dataFile, "Items", "Rate" , i);
            }
    }
}
