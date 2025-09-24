package com.wings.pages.production.transactions;

import com.wings.pages.Transaction;
import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class CloseProductionOrder extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CloseProductionOrder(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void closeProductionOrder(String orderNum,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        navigateToMastersWhen3Steps("Production","Standard","Close Production Order");
        Thread.sleep(3000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        EnterData("//Edit[@Name='Finished Product Code']",dataFile,"GeneralInformation","FinishedProductCode");
        selectPendingsSalesOrder(orderNum,"20250401");
        EnterData("//Edit[@Name='Executive *']",dataFile,"GeneralInformation","Executive");
        EnterData("//Edit[@Name='Remarks']",dataFile,"GeneralInformation","Remarks");

        pendingStockToBeIssuedToProduction();
        pendingStockToBeReceivedFromProduction();
        byProducts();
        otherInfo();

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"CloseProductionOrder");

    }
    public void pendingStockToBeIssuedToProduction() throws InterruptedException {
        Thread.sleep(1000);
        common.clickElement("xpath","//TabItem[contains(@Name,'Pending Stock To Be Issued To Production  ')]");

    }
    public void pendingStockToBeReceivedFromProduction() throws InterruptedException {
        Thread.sleep(1000);
        common.clickElement("xpath","//TabItem[contains(@Name,'Pending Stock To Be Received From Production  ')]");

    }
    public void byProducts() throws InterruptedException, IOException {
        Thread.sleep(1000);
        common.clickElement("xpath","//TabItem[contains(@Name,'By Products  ')]");
        List<String> productCode=readExcelData(dataFile,"ByProducts","ProductCode");
        System.out.println("OverHeads Size :"+productCode.size());
        for (int i = 0; i < productCode.size() ; i++) {
            addData("xpath","//Edit[@Name='Product Code Row "+i+", Not sorted.']",dataFile,"ByProducts","ProductCode",i);
        }
        List<WebElement> uom = common.findWebElements("xpath", "//Table[@Name='ByProducts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='ByProducts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='ByProducts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Quantity * Row ')]");
        List<WebElement> unitRate = common.findWebElements("xpath", "//Table[@Name='ByProducts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Rate Row ')]");
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='ByProducts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='ByProducts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='ByProducts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='ByProducts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='ByProducts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        for (int i = 0; i < productCode.size(); i++) {
            enterListData(uom.get(i),dataFile,"ByProducts","UOM",i);
            enterListData(storageBin.get(i),dataFile,"ByProducts","StorageBin",i);
            enterListData(quantity.get(i),dataFile,"ByProducts","Quantity",i);
            Thread.sleep(1000);
            enterListData(unitRate.get(i),dataFile,"ByProducts","Rate",i);
            enterListData(Department.get(i),dataFile,"ByProducts","Department",i);
            enterListData(Project.get(i),dataFile,"ByProducts","Project",i);
            enterListData(ProfitCentre.get(i),dataFile,"ByProducts","ProfitCentre",i);
            enterListData(CostCentre.get(i),dataFile,"ByProducts","CostCentre",i);
            enterListData(Comments.get(i),dataFile,"ByProducts","Comments",i);
        }
    }
    public void otherInfo() throws InterruptedException, IOException {
        navigateToOtherInfoTab();
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(5000);
        common.clickElement("xpath","//Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }

}
