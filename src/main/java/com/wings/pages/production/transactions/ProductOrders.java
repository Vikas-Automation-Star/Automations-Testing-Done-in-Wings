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

public class ProductOrders extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public ProductOrders(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String productOrders(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        navigateToMastersWhen3Steps("Production","Standard","Production Orders");
        Thread.sleep(3000);
        String oldVoucherID =oldTTransactionID();

        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        EnterData("//Edit[@Name='Finished Product *']",dataFile,"GeneralInformation","FinishedProduct");
        EnterData("//Edit[@Name='BOM *']",dataFile,"GeneralInformation","BOM");
        EnterData("//Edit[@Name='Order Quantity *']",dataFile,"GeneralInformation","OrderQuantity");
        EnterData("//Edit[@Name='Standard Rate Per Unit']",dataFile,"GeneralInformation","StandardRate");
        EnterData("//Edit[@Name='Executive *']",dataFile,"GeneralInformation","Executive");
        EnterData("//Edit[@Name='Department']",dataFile,"GeneralInformation","Department");
        EnterData("//Edit[@Name='Project']",dataFile,"GeneralInformation","Project");
        EnterData("//Edit[@Name='Profit Centre']",dataFile,"GeneralInformation","ProfitCentre");
        EnterData("//Edit[@Name='Cost Centre']",dataFile,"GeneralInformation","CostCentre");
        EnterData("//Edit[@Name='Remarks']",dataFile,"GeneralInformation","Remarks");

        inputs();
        otherInfo();

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"ProductionOrder");
        return newVoucherID;
    }
    public void inputs() throws IOException {
//        List<String> productCode=readExcelData(dataFile,"Inputs","ProductCode");
//        System.out.println("productCodes :"+productCode.size());
//        for (int i = 0; i < productCode.size() ; i++) {
//            addData("xpath","//Edit[@Name='Product Code Row "+i+", Not sorted.']",dataFile,"Inputs","ProductCode",i);
//        }
        List<WebElement> ProductCode = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product Code Row ')]");
        List<WebElement> uom = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Quantity * Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        for (int i = 0; i < ProductCode.size()-1; i++) {
            enterListData(ProductCode.get(i),dataFile,"Inputs","ProductCode",i);
            enterListData(uom.get(i),dataFile,"Inputs","UOM",i);
            enterListData(quantity.get(i),dataFile,"Inputs","Quantity",i);
            enterListData(Comments.get(i),dataFile,"Inputs","Comments",i);
            enterListData(Department.get(i),dataFile,"Inputs","Department",i);
            enterListData(Project.get(i),dataFile,"Inputs","Project",i);
            enterListData(ProfitCentre.get(i),dataFile,"Inputs","ProfitCentre",i);
            enterListData(CostCentre.get(i),dataFile,"Inputs","CostCentre",i);
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
