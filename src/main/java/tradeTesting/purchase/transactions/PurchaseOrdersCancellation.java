package tradeTesting.purchase.transactions;

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

public class PurchaseOrdersCancellation extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean discountIsClicked = false;


    public PurchaseOrdersCancellation(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String purchaseOrderCancellation(String voucherNum,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long start=System.nanoTime();
        navigateToMastersWhen3Steps("Purchase", "Orders", "Purchase Order Cancellation");
        long genInfoStart=System.nanoTime();
        long generalInfoStart=System.nanoTime();
        Thread.sleep(3000);
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        //gen info
        enterVoucherType(dataFile, "GeneralInformation", "VoucherType");
        EnterDate("//Edit[@Name='Date *']", dataFile, "GeneralInformation", "Date");
        enterBranch(dataFile, "GeneralInformation", "Branch");
        enterLocation(dataFile,"GeneralInformation","Location");
        EnterData("//Edit[@Name='Supplier Code']",dataFile,"GeneralInformation","SupplierCode");
        Thread.sleep(1500);
        selectPendingsSalesOrder(voucherNum, "20250401");
        EnterData("//Edit[@Name='Division']",dataFile,"GeneralInformation","Division");
        enterCreditPeriod(dataFile, "GeneralInformation", "CreditPeriod");
        enterPriceList(dataFile, "GeneralInformation", "PriceList");
        EnterData("//Edit[@Name='Executive']",dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile, "GeneralInformation", "Remarks");
        long duration1 = System.nanoTime() - genInfoStart;
        FileUtil.writeTimeLogInMinutes("POC General information end:- ", duration1);


        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("POC General Information End:- ", generalInfoEndTime);

        long addProductStart = System.nanoTime();
        addProduct();
        long addProductEnd = System.nanoTime() - addProductStart;
        FileUtil.writeTimeLogInMinutes("POC Add Products:- ", addProductEnd);

        long otherInfoTabStart = System.nanoTime();
        otherInfo();
        long otherInfoTabEnd = System.nanoTime() - otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("POC Other Info:- ", otherInfoTabEnd);

        long termsConditionsTabStart = System.nanoTime();
        termsAndCondition();
        long termsConditionsTabEnd = System.nanoTime() - termsConditionsTabStart;
        FileUtil.writeTimeLogInMinutes("POC Terms And Conditions:- ", termsConditionsTabEnd);

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
//        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"purchaseOrderCancellation");

        deleteTransactionUsingVoucherNumber(newVoucherID);
//        deleteTransactionUsingVoucherNumber(voucherNum);

        long salesInvoiceEnd = System.nanoTime() - start;
        FileUtil.writeTimeLogInMinutes("POC ended at:- ", salesInvoiceEnd );
        return newVoucherID;
    }

    public void addProduct() throws IOException, ParseException {
        common.clickElement("xpath", "//Header[@Name='Cancel Qty']");
        common.clickElement("xpath", "//Header[@Name='Cancel Base Qty']");
        List<WebElement> cancelQty = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cancel Qty Row ')]");
        List<WebElement> cancelBaseQty = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cancel Base Qty Row ')]");

        List<WebElement> reason = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Reason Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        List<WebElement> discPercent = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc % Row ')]");
        List<WebElement> disc = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Row ')]");
        if (!discountIsClicked) {
            common.clickElement("xpath", "//Header[@Name='Discount Amount1']");
            common.clickElement("xpath", "//Header[@Name='Discount Amount2']");
            common.clickElement("xpath", "//Header[@Name='Discount Amount3']");
            discountIsClicked = true;
        }
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 570, 0);
        List<WebElement> discountAcc1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Account1 Row ')]");
        List<WebElement> discountBasis1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Basis1 Row ')]");
        List<WebElement> discount1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount1 Row ')]");
        List<WebElement> discountAcc2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Account2 Row ')]");
        List<WebElement> discountBasis2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Basis2 Row ')]");
        List<WebElement> discount2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount2 Row ')]");
        List<WebElement> discountAcc3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Account3 Row ')]");
        List<WebElement> discountBasis3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount Basis3 Row ')]");
        List<WebElement> discount3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Discount3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 570, 0);
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> gstProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'GST Product Category Row ')]");
        List<WebElement> cessProductCategoryRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'CESS Product Category Row ')]");
        List<WebElement> executives = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Executive Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1100, 0);
        //        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
//        common.clickElement("xpath", "//Header[@Name='Info5']");
//        List<WebElement> Info1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info1 Row ')]");
//        List<WebElement> Info2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info2 Row ')]");
//        List<WebElement> Info3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info3 Row ')]");
//        List<WebElement> Info4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info4 Row ')]");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
//        List<WebElement> Info5 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info5 Row ')]");
//        common.clickElement("xpath", "//Header[@Name='Value5']");
//        List<WebElement> Value1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value1 Row  ')]");
//        List<WebElement> Value2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value2 Row ')]");
//        List<WebElement> Value3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value3 Row ')]");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
//        List<WebElement> Value4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value4 Row ')]");
//        List<WebElement> Value5 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value5 Row ')]");
//        common.clickElement("xpath", "//Header[@Name='Date3']");
//        List<WebElement> Date1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date1 Row ')]");
//        List<WebElement> Date2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date2 Row ')]");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 350, 0);
//        List<WebElement> Date3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date3 Row ')]");
//        common.clickElement("xpath", "//Header[@Name='Bool3']");
//        List<WebElement> Bool1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 1 Row ')]");
//        List<WebElement> Bool2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 2 Row ')]");
//        List<WebElement> Bool3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 3 Row ')]");

        for (int i = 0; i < reason.size()-1; i++) {
            enterListData(cancelQty.get(i),dataFile,"Items","CancelQty",i );
            enterListData(cancelBaseQty.get(i),dataFile,"Items","CancelBaseQty",i );
            enterListData(reason.get(i),dataFile,"Items","Reason",i );
            enterListData(discPercent.get(i),dataFile,"Items","DiscountPercentage",i );
            enterListData(disc.get(i),dataFile,"Items","Discount",i );
            enterListData(discountAcc1.get(i),dataFile,"Items","DiscountAccount1",i );
            enterListData(discountBasis1.get(i),dataFile,"Items","DiscountBasis1",i );
            enterListData(discount1.get(i),dataFile,"Items","Discount1",i );
            enterListData(discountAcc2.get(i),dataFile,"Items","DiscountAccount2",i );
            enterListData(discountBasis2.get(i),dataFile,"Items","DiscountBasis2",i );
            enterListData(discount2.get(i),dataFile,"Items","Discount2",i );
            enterListData(discountAcc3.get(i),dataFile,"Items","DiscountAccount3",i );
            enterListData(discountBasis3.get(i),dataFile,"Items","DiscountBasis3",i );
            enterListData(discount3.get(i),dataFile,"Items","Discount3",i );
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 300, 0);
            enterListData(hsnCodeRowList.get(i),dataFile,"Items","HSN",i );
            enterListData(gstProductCategoryRowList.get(i),dataFile,"Items","GSTProductCategory",i );
            enterListData(cessProductCategoryRowList.get(i),dataFile,"Items","CESSProductCategory",i );
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 450, 0);
            enterListData(executives.get(i),dataFile,"Items","LatestExecutive",i );
            enterListData(departmentRowList.get(i),dataFile,"Items","Department",i );
            enterListData(projectRowList.get(i),dataFile,"Items","Project",i );
            enterListData(profitCentreRowList.get(i),dataFile,"Items","ProfitCentre",i );
            enterListData(costCentreRowList.get(i),dataFile,"Items","CostCentre",i );
            enterListData(commentsRowList.get(i),dataFile,"Items","Comments",i );
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1050, 0);
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
//            enterListData(Comments.get(i), dataFile, "Items", "Comments", i);
//            enterListData(Info1.get(i), dataFile, "Items", "Info1", i);
//            enterListData(Info2.get(i), dataFile, "Items", "Info2", i);
//            enterListData(Info3.get(i), dataFile, "Items", "Info3", i);
//            enterListData(Info4.get(i), dataFile, "Items", "Info4", i);
//            enterListData(Info5.get(i), dataFile, "Items", "Info5", i);
//            enterListData(Value1.get(i), dataFile, "Items", "Value1", i);
//            enterListData(Value2.get(i), dataFile, "Items", "Value2", i);
//            enterListData(Value3.get(i), dataFile, "Items", "Value3", i);
//            enterListData(Value4.get(i), dataFile, "Items", "Value4", i);
//            enterListData(Value5.get(i), dataFile, "Items", "Value5", i);
////            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 150, 0);
//            enterListDate(Date1.get(i), dataFile, "Items", "Date1", i);
//            enterListDate(Date2.get(i), dataFile, "Items", "Date2", i);
//            enterListDate(Date3.get(i), dataFile, "Items", "Date3", i);
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 50, 0);
//            clickListData(Bool1.get(i));
//            clickListData(Bool2.get(i));
//            clickListData(Bool3.get(i));
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

    public  void termsAndCondition() throws IOException {
        common.clickElement("xpath","//TabItem[contains(@Name,'  TermsAndConditions  ')]");
        java.util.List<String> termsAndConditions=readExcelData(dataFile,"TermsAndConditions","TermType");
        for (int i = 0; i < termsAndConditions.size() ; i++) {
            addData("xpath","//Edit[@Name='Term Type * Row "+i+", Not sorted.']",dataFile,"TermsAndConditions","TermType",i);
        }
        java.util.List<WebElement> term = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Term * Row ')]");
        List<WebElement> comments = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < termsAndConditions.size(); i++) {
            enterListData(term.get(i), dataFile, "TermsAndConditions", "Term", i);
            enterListData(comments.get(i), dataFile, "TermsAndConditions", "Comments", i);
        }
    }
}
