package tradeTesting.sales.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class SalesOrderCancellationTrade  extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesOrderCancellationTrade(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String salesOrderCancellationTrade(String voucherNum) throws Exception {
        long start=System.nanoTime();
        navigateToMastersWhen3Steps("Sales", "Orders", "Sales Orders Cancellation");
        long genInfoStart=System.nanoTime();
        long generalInfoStart=System.nanoTime();
        Thread.sleep(3000);
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        //gen info
        enterVoucherType(dataFile, "GeneralInformation", "VoucherType");
        EnterDate("//Edit[@Name='Date *']", dataFile, "GeneralInformation", "Date");
        enterBranch(dataFile, "GeneralInformation", "Branch");
        EnterData("//Edit[@Name='Route']", dataFile, "GeneralInformation", "Route");
        EnterData("//Edit[@Name='Division']", dataFile, "GeneralInformation", "Division");
        EnterData("//Edit[@Name='Customer *']", dataFile, "GeneralInformation", "Customer");
        selectPendingsSalesOrder(voucherNum, "20250401");
        EnterData("//Edit[@Name='Sales Executive *']",dataFile,"GeneralInformation","SalesExecutive");
        enterRemarks(dataFile, "GeneralInformation", "Remarks");
        long duration1 = System.nanoTime() - genInfoStart;
        FileUtil.writeTimeLogInMinutes("SOC General information end:- ", duration1);


        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("SOC General Information End:- ", generalInfoEndTime);

        long addProductStart = System.nanoTime();
        addProduct();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("SOC Add Products:- ", addProductEnd);

        long otherInfoTabStart = System.nanoTime();
        otherInfo();
        long otherInfoTabEnd = System.nanoTime() - otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("SOC Other Info:- ", otherInfoTabEnd);

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long salesInvoiceEnd = System.nanoTime() - start;
        FileUtil.writeTimeLogInMinutes("SOC ended at:- ", salesInvoiceEnd );
        //api
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"purchaseOrderCancellation");
        deleteTransactionUsingVoucherNumber(newVoucherID);
        deleteTransactionUsingVoucherNumber(voucherNum);

        return newVoucherID;
    }

    public void addProduct() throws IOException, ParseException {
        common.clickElement("xpath", "//Header[@Name='Cancel Qty']");
        common.clickElement("xpath", "//Header[@Name='Cancel Qty In Base Unit']");
        List<WebElement> cancelQty = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cancel Qty Row ')]");
        List<WebElement> cancelQtyInBaseUnit = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cancel Qty In Base Unit Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        List<WebElement> cancelFreeQty = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cancel Free Qty Row ')]");
        List<WebElement> cancelFreeQtyInBaseUnit = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cancel Free Qty Base Unit Row ')]");
        List<WebElement> reason = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Reason Row ')]");
        List<WebElement> executives = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -950, 0);

        for (int i = 0; i < reason.size()-1; i++) {
            enterListData(cancelQty.get(i),dataFile,"Items","CancelQty",i );
            enterListData(cancelQtyInBaseUnit.get(i),dataFile,"Items","CancelQtyInBaseUnit",i );
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 450, 0);
            enterListData(cancelFreeQty.get(i),dataFile,"Items","CancelFreeQty",i );
            enterListData(cancelFreeQtyInBaseUnit.get(i),dataFile,"Items","CancelFreeQtyBaseUnit",i );
            enterListData(reason.get(i),dataFile,"Items","Reason",i );
            enterListData(executives.get(i),dataFile,"Items","LatestExecutive",i );
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 450, 0);
            enterListData(departmentRowList.get(i),dataFile,"Items","Department",i );
            enterListData(projectRowList.get(i),dataFile,"Items","Project",i );
            enterListData(profitCentreRowList.get(i),dataFile,"Items","ProfitCentre",i );
            enterListData(costCentreRowList.get(i),dataFile,"Items","CostCentre",i );
            enterListData(commentsRowList.get(i),dataFile,"Items","Comments",i );
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1050, 0);
        }
    }

    public void otherInfo() throws InterruptedException, IOException, AWTException {
        common.clickElement("xpath", "//TabItem[contains(@Name,'  OtherInfo  ')]");
        EnterData("//Edit[@Name='Reference Bill No']", dataFile, "OtherInfo", "ReferenceBillNo");
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        EnterDate("//Edit[@Name='Reference Bill Date']", dataFile, "OtherInfo", "ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']", dataFile, "OtherInfo", "OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']", dataFile, "OtherInfo", "OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']", dataFile, "OtherInfo", "OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']", dataFile, "OtherInfo", "OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']", dataFile, "OtherInfo", "OtherInfo5");
    }
}