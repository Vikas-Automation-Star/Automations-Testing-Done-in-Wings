package com.wings.pages.inventory.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;

public class StockConversion extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public StockConversion(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String stockConversion() throws InterruptedException, IOException, ParseException {
        long start = System.nanoTime();
        navigateToMastersWhen2Steps("Inventory","Stock Conversion");
        Thread.sleep(1000);
        long generalInfoStart = System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterLocation(dataFile,"GeneralInformation","Location");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterExchangeRate(dataFile,"GeneralInformation","ExchangeRate");
        enterBatchPolicy(dataFile,"GeneralInformation","BatchPolicy");
        enterPriceList(dataFile,"GeneralInformation","PriceList");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Stock Conversion:- ", generalInfoEndTime);

        long addProductStart = System.nanoTime();
        addInputProduct();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("Stock Conversion Inputs:- ", addProductEnd);

        long addOutputsStart = System.nanoTime();
        common.clickElement("xpath","//TabItem[contains(@Name,'Out puts ')]");
        addOutputs();
        long addOutputsEnd = System.nanoTime() - addOutputsStart;
        FileUtil.writeTimeLogInMinutes("Stock Conversion Outputs:- ", addOutputsEnd);

        long otherInfoTabStart = System.nanoTime();
        otherInfo();
        long otherInfoTabEnd = System.nanoTime() - otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Stock Conversion Other Info:- ", otherInfoTabEnd);

        long allocationsTabStart = System.nanoTime();
        addAllocations();
        long allocationsTabEnd = System.nanoTime() - allocationsTabStart;
        FileUtil.writeTimeLogInMinutes("Stock Conversion Allocations:- ", allocationsTabEnd);

        //saving and IO generating
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long deliveriesEnd = System.nanoTime() - start;
        FileUtil.writeTimeLogInMinutes("Stock Conversion ended at:- ", deliveriesEnd);
        String prefix = newVoucherID.replaceAll("\\d", "");
        String number = newVoucherID.replaceAll("\\D", "");
        Thread.sleep(2000);
        long ioFIlesStart =System.nanoTime();
        exportIOFiles("Generate Input File",prefix,number);
        exportIOFiles("Generate Output File",prefix,number);
        long ioFIlesEnd =System.nanoTime()-ioFIlesStart;
        FileUtil.writeTimeLogInMinutes("Stock Conversion IO files end: ", ioFIlesEnd);
//        excelUtil.excelComparator("","",newVoucherID);
        return newVoucherID;
    }
    public void addInputProduct() throws IOException, ParseException, InterruptedException {
        List<String> productCode=readExcelData(dataFile,"Inputs","ProductCode");
        List<String> masterType=readExcelData(dataFile,"Inputs","MasterType");
        System.out.println(masterType.size());
        for (int i = 0; i < productCode.size() ; i++)   {
            addData("xpath","//Edit[@Name='Product Code Row "+i+", Not sorted.']",dataFile,"Inputs","ProductCode",i);
        }
        List<WebElement> uom = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity * Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Inputs']/*/Thumb[@Name='Position']", 350, 0);
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Inputs']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Inputs']/*/Thumb[@Name='Position']", -500, 0);
        for (int i = 0; i < productCode.size(); i++) {
            enterListData(uom.get(i), dataFile, "Inputs", "UOM", i);
            enterListData(storageBin.get(i),dataFile,"Inputs","StorageBin",i);
            if (masterType.get(i).equals("Products")){
                enterListData(quantity.get(i), dataFile, "Inputs", "Quantity",i);
            } else if (masterType.get(i).equals("Products - MultiBatch")) {
                common.clickElement("xpath", "//Button[@Name='Stock Details Row "+i+"']");
                Thread.sleep(500);
                EnterData("//Table[@Name='Batch Details']/*[@Name='Data Panel']/*[@Name='Row 1']/*[@Name='Quantity row 1']",dataFile,"Inputs","Quantity",i);
                Thread.sleep(1000);
                common.clickElement("xpath", "//Button[@Name='OK']");
            }else if (masterType.get(i).equals("Products - Batches and Serial No")){
                Thread.sleep(1000);
                common.clickElement("xpath", "//Button[@Name='Stock Details Row "+i+"']");
                Thread.sleep(2000);
                List<WebElement> editfields=common.findWebElements("xpath","//Table[@Name='Serial Numbers List']/*[@Name='Data Panel']/*[contains(@Name,'Row')]/*[contains(@Name,'Select row')]");
                System.out.println("Serial number edit fields :"+editfields.size());
                editfields.get(0).click();
                Thread.sleep(2000);
                common.clickElement("xpath", "//Button[@Name='OK']");
            }else {
                Assert.fail("No product present");
            }
            enterListData(Department.get(i),dataFile,"Inputs","Department",i);
            enterListData(Project.get(i),dataFile,"Inputs","Project",i);
            enterListData(ProfitCentre.get(i),dataFile,"Inputs","ProfitCentre",i);
            enterListData(CostCentre.get(i),dataFile,"Inputs","CostCentre",i);
            enterListData(Comments.get(i),dataFile,"Inputs","Comments",i);
            common.sliderHandling("xpath", "//Table[@Name='Inputs']/*/Thumb[@Name='Position']", -450, 0);
        }
    }

    public void addOutputs() throws IOException, ParseException, InterruptedException {
        List<String> productCode=readExcelData(dataFile,"Items","ProductCode");
        List<String> masterType=readExcelData(dataFile,"Items","MasterType");
        System.out.println(masterType.size());
        for (int i = 0; i < productCode.size() ; i++)   {
            addData("xpath","//Edit[@Name='Product Code Row "+i+", Not sorted.']",dataFile,"Items","ProductCode",i);
        }
        List<WebElement> uom = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity * Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -500, 0);
        for (int i = 0; i < productCode.size(); i++) {
            enterListData(uom.get(i), dataFile, "Items", "UOM", i);
            enterListData(storageBin.get(i),dataFile,"Items","StorageBin",i);
            if (masterType.get(i).equals("Products") || masterType.get(i).equals("Products - MultiBatch")){
                enterListData(quantity.get(i), dataFile, "Items", "Quantity",i);
            } else if (masterType.get(i).equals("Products - Batches and Serial No")) {
                Thread.sleep(1000);
                common.clickElement("xpath","//Button[@Name='Serial Nos Row "+i+"']");
                Thread.sleep(2000);
                WebElement increment = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
                increment.sendKeys(Keys.TAB,"STCK"+ Common.getRandomChar(), Keys.TAB, "1",Keys.ENTER);
                common.clickElement("xpath", "//Button[@Name='OK']");
            }else {
                Assert.fail("No product present");
            }
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
    common.clickElement("xpath","//Window/Button[@Name='OK']");
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
