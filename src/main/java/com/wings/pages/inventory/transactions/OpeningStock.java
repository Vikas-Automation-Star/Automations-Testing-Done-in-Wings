package com.wings.pages.inventory.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;

public class OpeningStock extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public OpeningStock(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String openingStock(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws InterruptedException, IOException, ParseException, AWTException {
        long start = System.nanoTime();
        navigateToMastersWhen2Steps("Inventory","Opening Stock");
        long generalInfoStart = System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterLocation(dataFile,"GeneralInformation","Location");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterExchangeRate(dataFile,"GeneralInformation","ExchangeRate");
        enterOpeningStockAccount(dataFile,"GeneralInformation","OpeningStockAccount");
        enterOpeningStockAccountAsset(dataFile,"GeneralInformation","OpeningStockAccountAsset");
        enterBatchPolicy(dataFile,"GeneralInformation","BatchPolicy");
        enterPriceList(dataFile,"GeneralInformation","PriceList");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Opening Stock:- ", generalInfoEndTime);

        long addProductStart = System.nanoTime();
        addProduct();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("Opening Stock Add Products:- ", addProductEnd);

        long otherInfoTabStart = System.nanoTime();
        otherInfo();
        long otherInfoTabEnd = System.nanoTime() - otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Opening Stock Other Info:- ", otherInfoTabEnd);

        long allocationsTabStart = System.nanoTime();
        addAllocations();
        long allocationsTabEnd = System.nanoTime() - allocationsTabStart;
        FileUtil.writeTimeLogInMinutes("Opening Stock Allocations:- ", allocationsTabEnd);

        //saving and IO generating
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //API
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"OpeningStock");

        return newVoucherID;
    }
    public void addProduct() throws IOException, ParseException, InterruptedException {
        java.util.List<String> productCode=readExcelData(dataFile,"Items","ProductCode");
        java.util.List<String> masterType=readExcelData(dataFile,"Items","MasterType");
        System.out.println(masterType.size());
        for (int i = 0; i < productCode.size() ; i++)   {
            addData("xpath","//Edit[@Name='Product Code Row "+i+", Not sorted.']",dataFile,"Items","ProductCode",i);
        }
        java.util.List<WebElement> uom = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
        java.util.List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        java.util.List<WebElement> ageInDays = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Age In Days Row ')]");
        java.util.List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity * Row ')]");
        java.util.List<WebElement> numOfPacksRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'No Of Packs Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
        java.util.List<WebElement> editableGrossAmount = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Editable Gross Amount Row ')]");
        java.util.List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        java.util.List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        java.util.List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        java.util.List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        java.util.List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -500, 0);


        for (int i = 0; i < productCode.size(); i++) {
            enterListData(uom.get(i), dataFile, "Items", "UOM", i);
            enterListData(storageBin.get(i),dataFile,"Items","StorageBin",i);
            enterListData(ageInDays.get(i),dataFile,"Items","Age",i);
            if (masterType.get(i).equals("Products") || masterType.get(i).equals("Products - MultiBatch")){
                enterListData(quantity.get(i), dataFile, "Items", "Quantity",i);
            } else if (masterType.get(i).equals("Products - Batches and Serial No")) {
                Thread.sleep(1000);
                common.clickElement("xpath","//Button[@Name='Serial Nos Row "+i+"']");
                Thread.sleep(2000);
                WebElement increment = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
                increment.sendKeys(Keys.TAB,"OSABC"+ Common.getRandomChar(), Keys.TAB, "1",Keys.ENTER);
                common.clickElement("xpath", "//Button[@Name='OK']");
            }else {
                Assert.fail("No product present");
            }
            enterListData(numOfPacksRowList.get(i),dataFile,"Items","NoOfPacks",i);
            enterListData(editableGrossAmount.get(i),dataFile,"Items","EditableGrossAmount",i);
            enterListData(Department.get(i),dataFile,"Items","Department",i);
            enterListData(Project.get(i),dataFile,"Items","Project",i);
            enterListData(ProfitCentre.get(i),dataFile,"Items","ProfitCentre",i);
            enterListData(CostCentre.get(i),dataFile,"Items","CostCentre",i);
            enterListData(Comments.get(i),dataFile,"Items","Comments",i);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -450, 0);
        }
    }


public void otherInfo() throws InterruptedException, IOException {
    navigateToOtherInfoTab();
    EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
    Thread.sleep(5000);
//    common.clickElement("xpath","//Window/Button[@Name='OK']");
    EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
    EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
    EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
    EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
    EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
    EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
}

public void addAllocations() {
    common.clickElement("xpath", "//TabItem[contains(@Name,'Allocations')]");
    EnterData( "//Edit[@Name='Department']", dataFile, "Allocations", "Department");
    EnterData( "//Edit[@Name='Project']", dataFile, "Allocations", "Project");
    EnterData("//Edit[@Name='Profit Centre']", dataFile, "Allocations", "ProfitCentre");
    EnterData( "//Edit[@Name='Cost Centre']", dataFile, "Allocations", "CostCentre");
}

}
