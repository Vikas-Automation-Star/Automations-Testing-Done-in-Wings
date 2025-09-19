package com.wings.pages.sales.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import java.awt.*;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;

public class DefineSalesTargetExecutiveWise extends TransactionsBaseClass {
    WindowsDriver driver,rootDriver;
    Common common;
    String dataFile;

    public DefineSalesTargetExecutiveWise(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String salesTargetExecutiveWise(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long start = System.nanoTime();
        System.out.println("Define Sales Target Executive Wise started in :"+start);
        Thread.sleep(100);
        long generalInfoStart=System.nanoTime();
        navigateToMastersWhen3Steps("Sales","Targets","Define Sales Targets-Executive Wise" );
        Thread.sleep(3000);
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        //branch selection

        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterMonthSalesTarget(dataFile,"GeneralInformation","Month");
        enterYearSalesTarget(dataFile,"GeneralInformation","Year");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Define Sales Target Executive Wise General Information End:- ", generalInfoEndTime);

        long addProductStart = System.nanoTime();
        addProduct();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("Define Sales Target Executive Wise Add Products:- ", addProductEnd);

        long otherInfoTabStart = System.nanoTime();
        otherInfo();
        long otherInfoTabEnd = System.nanoTime() - otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Define Sales Target Executive Wise Other Info:- ", otherInfoTabEnd);

        //saving and IO generating
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //API
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"defineSalesTarget");

        return newVoucherID;
    }

    public void addProduct() throws Exception {
        List<String> productCode=readExcelData(dataFile,"Items","SalesExecutive");
        for (int i = 0; i < productCode.size() ; i++)   {
            addData("xpath","//Edit[@Name='Sales Executive * Row "+i+", Not sorted.']",dataFile,"Items","SalesExecutive",i);
        }
        List<WebElement> productSalesTargetGroup = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product Sales Target Group * Row ')]");
        List<WebElement> targetQuantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Target Quantity Row ')]");
        List<WebElement> amount = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Amount Row ')]");
        for (int i = 0; i < productCode.size() ; i++) {
            enterListData(productSalesTargetGroup.get(i), dataFile, "Items", "ProductSalesTargetGroup", i);
            enterListData(targetQuantity.get(i), dataFile, "Items", "TargetQuantity", i);
            enterListData(amount.get(i), dataFile, "Items", "Amount", i);
        }
    }
    public void otherInfo() throws InterruptedException, IOException {
        navigateToOtherInfoTab();
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(5000);
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='OtherInfo 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='OtherInfo 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='OtherInfo 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='OtherInfo 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='OtherInfo 5']",dataFile,"OtherInfo","OtherInfo5");
    }
}
