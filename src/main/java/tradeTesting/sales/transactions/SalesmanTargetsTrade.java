package tradeTesting.sales.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public class SalesmanTargetsTrade extends TransactionsBaseClass {
    WindowsDriver driver,rootDriver;
    Common common;
    String dataFile;

    public SalesmanTargetsTrade(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String salesmanTargetsTrade(String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long start = System.nanoTime();
        System.out.println("SalesmanTargetsTrade:"+start);
        Thread.sleep(100);
        long generalInfoStart=System.nanoTime();
        navigateToMastersWhen3Steps("Sales","Targets","Salesman Targets" );
        Thread.sleep(3000);
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        //branch selection

        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterYearSalesTarget(dataFile,"GeneralInformation","TargetYear");
        EnterData("//Edit[@Name='Month *']",dataFile,"GeneralInformation","TargetMonth");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("Salesman Targets Trade General Information End:- ", generalInfoEndTime);

        long addProductStart = System.nanoTime();
        addProduct();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("Salesman Targets Trade Add Products:- ", addProductEnd);

        //saving and IO generating
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //API
//            APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"defineSalesTarget");
        long defineSalesTargetEnd = System.nanoTime() - start;
        FileUtil.writeTimeLogInMinutes("SalesmanTargetsTrade Transaction Ended at:- ", defineSalesTargetEnd);
        deleteTransactionUsingVoucherNumber(newVoucherID);

        return newVoucherID;
    }

    public void addProduct() throws Exception {
        List<String> productCode=readExcelData(dataFile,"Targets","SalesMan");
        for (int i = 0; i < productCode.size() ; i++)   {
            addData("xpath","//Edit[@Name='Sales Man Row "+i+", Not sorted.']",dataFile,"Targets","SalesMan",i);
        }
        List<WebElement> division = common.findWebElements("xpath", "//Table[@Name='Targets']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Division Row ')]");
        List<WebElement> productCategory = common.findWebElements("xpath", "//Table[@Name='Targets']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Product Category Row ')]");
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Targets']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Quantity Row ')]");
        List<WebElement> value = common.findWebElements("xpath", "//Table[@Name='Targets']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value Row ')]");
        List<WebElement> comments = common.findWebElements("xpath", "//Table[@Name='Targets']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");

        for (int i = 0; i < productCode.size() ; i++) {
            enterListData(division.get(i), dataFile, "Targets", "Division", i);
            enterListData(productCategory.get(i), dataFile, "Targets", "ProductCategory", i);
            enterListData(quantity.get(i), dataFile, "Targets", "Quantity", i);
            enterListData(value.get(i), dataFile, "Targets", "Value", i);
            enterListData(comments.get(i), dataFile, "Targets", "Comments", i);
        }
    }
}