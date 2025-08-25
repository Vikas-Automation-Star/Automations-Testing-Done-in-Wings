package com.wings.pages.inventory.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PhysicalStockTake extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PhysicalStockTake(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String physicalStockTake(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws InterruptedException, AWTException, IOException, ParseException {
        long start=System.nanoTime();
        navigateToMastersWhen2Steps("Inventory", "Physical Stock Take");
        long generalInfoStart = System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterLocation(dataFile,"GeneralInformation","Location");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Physical Stock Take General Information:- ", generalInfoEndTime);

        long addProductStart = System.nanoTime();
        addStockDetails();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("Physical Stock Take Add Products:- ", addProductEnd);

        long otherInfoTabStart = System.nanoTime();
        otherInfo();
        long otherInfoTabEnd = System.nanoTime() - otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Physical Stock Take Other Info:- ", otherInfoTabEnd);

        //saving and IO generating
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //api
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"physicalStockTake");


//        excelUtil.excelComparator("","",newVoucherID);
        return newVoucherID;
    }

    public void addStockDetails() throws IOException, ParseException, InterruptedException {
        List<String> productCode=readExcelData(dataFile,"PhysicalStock","ProductCode");
        for (int i = 0; i < productCode.size() ; i++)   {
            addData("xpath","//Edit[@Name='Product Code Row "+i+", Not sorted.']",dataFile,"PhysicalStock","ProductCode",i);
        }
        List<WebElement> productBatch = common.findWebElements("xpath", "//Table[@Name='PhysicalStock']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product Batch * Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='PhysicalStock']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        List<WebElement> physicalStockBin = common.findWebElements("xpath", "//Table[@Name='PhysicalStock']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Physical Stock Bin * Row ')]");
        List<WebElement> physicalStock = common.findWebElements("xpath", "//Table[@Name='PhysicalStock']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Physical Stock Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='PhysicalStock']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");

        for (int i = 0; i < productCode.size(); i++) {
            enterListData(productBatch.get(i), dataFile, "PhysicalStock", "ProductBatch", i);
            enterListData(storageBin.get(i), dataFile, "PhysicalStock", "StorageBin", i);
            enterListData(physicalStockBin.get(i), dataFile, "PhysicalStock", "PhysicalStockBin", i);
            common.clickElement("xpath","//CheckBox[@Name='Audit * Row "+i+"']");
            enterListData(physicalStock.get(i),dataFile,"PhysicalStock","PhysicalStock",i);
            enterListData(Comments.get(i),dataFile,"PhysicalStock","Comments",i);
        }
        common.deleteInvalidRows();
    }

    public void otherInfo() throws InterruptedException, IOException {
        navigateToOtherInfoTab();
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(5000);
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }
}
