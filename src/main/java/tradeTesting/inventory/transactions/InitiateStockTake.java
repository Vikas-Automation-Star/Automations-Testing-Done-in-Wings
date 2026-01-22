package tradeTesting.inventory.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.APIClient;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class InitiateStockTake extends TransactionsBaseClass{
    WindowsDriver driver;
    Common common;
    String dataFile;

    public InitiateStockTake(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String initiateStockTake() throws Exception {
        long start = System.nanoTime();
        navigateToMastersWhen3Steps("Inventory", "Inventory","Initiate Stock Take");
        Thread.sleep(1000);
        long generalInfoStart = System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterLocation(dataFile,"GeneralInformation","Location");
        EnterData("//Edit[@Name='Division']",dataFile,"GeneralInformation","Division");
        WebElement stockType = common.findWebElement("xpath", "//Edit[@Name='Node']");
        stockType.clear();
        stockType.sendKeys("Testing", Keys.ENTER);
        EnterData("//Edit[@Name='Physical Stock Bin']",dataFile,"GeneralInformation","PhysicalStockBin");
        common.clickElement("xpath","//Button[@Name='Get Stocks']");
        common.clickElement("xpath","//CheckBox[@Name='Select Row 0']");
        common.clickElement("xpath","//Button[@Name='OK']");
//        List<WebElement> elementList = common.findWebElements("xpath", "//Window[@Name='StorageBin Details']//Table/*[@Name='Data Panel']/*/*[starts-with(@Name,'StorageBin row ')]");
//        for (WebElement i : elementList) {
//            if (i.getText().equals("Location 1 Default Bin")) {
//                i.click();
//                common.clickElement("xpath","//Button[@Name='OK']");
////                i.sendKeys(Keys.LEFT, Keys.SPACE, Keys.ENTER, Keys.ENTER);
////                break;
//            }
//        }

        EnterData("//Edit[@Name='Executive']",dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Initiate Stock Take gen info:- ", generalInfoEndTime);

        long addProductStart = System.nanoTime();
        addProduct();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("Initiate Stock Take Add Products:- ", addProductEnd);

        long otherInfoTabStart = System.nanoTime();
        common.clickElement("xpath", "//TabItem[contains(@Name,'  OtherInfo  ')]");
        otherInfo();
        long otherInfoTabEnd = System.nanoTime() - otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Initiate Stock Take Other Info:- ", otherInfoTabEnd);

        //saving and IO generating
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"InitiateStockTake");

        long initiateStockTake = System.nanoTime() - start;
        FileUtil.writeTimeLogInMinutes("Initiate Stock Take ended at:- ", initiateStockTake);
        deleteTransactionUsingVoucherNumber(newVoucherID);

        return newVoucherID;
    }

    public void addProduct() throws Exception {
        List<String> productCode=readExcelData(dataFile,"InitiateStock","ProductCode");
        List<WebElement> executive = common.findWebElements("xpath", "//Table[@Name='InitiateStock']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='InitiateStock']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='InitiateStock']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='InitiateStock']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='InitiateStock']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='InitiateStock']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
        for (int i = 0; i < productCode.size(); i++) {
            enterListData(executive.get(i), dataFile, "Invoice", "Executive",i);
            enterListData(Department.get(i),dataFile,"Invoice","Department",i);
            enterListData(Project.get(i),dataFile,"Invoice","Project",i);
            enterListData(ProfitCentre.get(i),dataFile,"Invoice","ProfitCentre",i);
            enterListData(CostCentre.get(i),dataFile,"Invoice","CostCentre",i);
            enterListData(Comments.get(i),dataFile,"Invoice","Comments",i);
        }
    }

    public void otherInfo() throws InterruptedException, IOException {
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }

}
