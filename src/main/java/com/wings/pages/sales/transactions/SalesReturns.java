package com.wings.pages.sales.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.*;
import io.appium.java_client.windows.WindowsDriver;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class SalesReturns extends TransactionsBaseClass {
    WindowsDriver driver,rootDriver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false,IsAmountHeaderClicked=false,otherChargesGSTCheckBox=false,discountIsClicked=false;

    public SalesReturns(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(driver);
        dataFile = file;
    }

    public String  salesReturns(String invoiceNumber,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long salesReturnsStart = System.nanoTime();
        System.out.println("Sales returns started in :" + salesReturnsStart);

        navigateToSalesReturnMenu();
        Thread.sleep(2000);
        long salesReturns = System.nanoTime();
        System.out.println("Sales Returns started in :" + salesReturns);
        long generalInfoStart = System.nanoTime();
        System.out.println("Sales Returns general Info started executed in :" + generalInfoStart);
        String oldVoucherID = oldTTransactionID();
        System.out.println("oldID: " + oldVoucherID);

        //Gen info
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterLocation(dataFile,"GeneralInformation","Location");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterExchangeRate(dataFile,"GeneralInformation","ExchangeRate");
        WebElement invoiceNum=common.findWebElement("xpath","//Edit[@Name='Sales Invoice No' or @Name='Sales Invoice No *']");
        invoiceNum.sendKeys(invoiceNumber);
//        enterSalesInvoiceNumber(dataFile,"GeneralInformation","SalesInvoiceNo");
        enterSalesInvoiceDate(dataFile,"GeneralInformation","SalesInvoiceDate");
        enterPartyCode(dataFile,"GeneralInformation","PartyAccountCode");
        Thread.sleep(3000);
        gstTransactionType("Intra State Sales Returns from Registered Dealers");
        Thread.sleep(1000);
//        common.clickElement("xpath", "//Edit[@Name='Invoice Type']");
//        Thread.sleep(1000);
//        Robot robot = new Robot();
//        robot.keyPress(KeyEvent.VK_DOWN);
//        robot.keyRelease(KeyEvent.VK_DOWN);
//        robot.keyPress(KeyEvent.VK_DOWN);
//        robot.keyRelease(KeyEvent.VK_DOWN);
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
        enterCreditPeriod(dataFile,"GeneralInformation","CreditPeriod");
        enterSalesReturnAccCode(dataFile,"GeneralInformation","SalesReturnsAccountCode");
        enterTcsTransNature(dataFile, "GeneralInformation", "TCSTransactionNature");
        enterBatchPolicy(dataFile,"GeneralInformation","BatchPolicy");
        enterPriceList(dataFile, "GeneralInformation", "PriceList");
        enterVoucherDisc(dataFile,"GeneralInformation","VoucherDiscountPercentage");
        generalInfoSliderHandle(400);
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        //F3-Items
        long addProductStart=System.nanoTime();
        addProducts();
        long addProductEnd=System.nanoTime()-addProductStart;
        FileUtil.writeTimeLogInMinutes("Sales Returns Add Products:- ",addProductEnd);

        //charges and deductions
        long chargesDeductionsStart =System.nanoTime();
        addChargesAndDeductions();
        long chargesDeductionsEnd=System.nanoTime()- chargesDeductionsStart;
        FileUtil.writeTimeLogInMinutes("Charges and Deductions:- ",chargesDeductionsEnd);

        //other charges
        long otherChargesStart =System.nanoTime();
        addOtherCharges();
        long otherChargesEnd=System.nanoTime()- otherChargesStart;
        FileUtil.writeTimeLogInMinutes("Other Charges:- ",otherChargesEnd);

        //cash
        long cashTabStart =System.nanoTime();
        addCash();
        long cashTabEnd =System.nanoTime()- cashTabStart;
        FileUtil.writeTimeLogInMinutes("Cash Tab:- ", cashTabEnd);

        //cheques
        long chequesTabStart =System.nanoTime();
        addCheques();
        long chequesTabEnd =System.nanoTime()- chequesTabStart;
        FileUtil.writeTimeLogInMinutes("Cheques Tab:- ", chequesTabEnd);

        //post dated cheques
        long postDatedChequesTabStart =System.nanoTime();
        addPostDatedCheques();
        long postDatedChequesTabEnd =System.nanoTime()- postDatedChequesTabStart;
        FileUtil.writeTimeLogInMinutes("Post Dated Cheques Tab:- ", postDatedChequesTabEnd);

        //cheques[pdc]
        long chequesPDCTabStart =System.nanoTime();
        addChequesPDC();
        long chequesPDCTabEnd =System.nanoTime()- chequesPDCTabStart;
        FileUtil.writeTimeLogInMinutes("Cheques[PDC] Tab:- ", chequesPDCTabEnd);

        //cheques[pdc]
        long invoiceDetailsStart =System.nanoTime();
        addInvoiceDetails();
        long invoiceDetailsEnd =System.nanoTime()- invoiceDetailsStart;
        FileUtil.writeTimeLogInMinutes("Cheques[PDC] Tab:- ", invoiceDetailsEnd);

        //Other info
        long otherInfoTabStart =System.nanoTime();
        otherInfo();
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Other Info Tab:- ", otherInfoTabEnd);

        navigateToOtherInfoTab();
        moveToRight(6);

        //additional Info
        long additionalInfoTabStart =System.nanoTime();
        additionalInformation();
        long additionalInfoTabEnd =System.nanoTime()- additionalInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Additional Info Tab:- ", additionalInfoTabEnd);

        //shippingAddress
        long shippingAddressStart =System.nanoTime();
        addShippingAddress();
        long shippingAddressEnd =System.nanoTime()- shippingAddressStart;
        FileUtil.writeTimeLogInMinutes("Additional Info Tab:- ", shippingAddressEnd);

        //terms and Cond
        long termsConditionsTabStart =System.nanoTime();
        termsAndCondition();
        long termsConditionsTabEnd =System.nanoTime()- termsConditionsTabStart;
        FileUtil.writeTimeLogInMinutes("Terms and Conditions Tab:- ", termsConditionsTabEnd);

//        allocations
//        long allocationsTabStart=System.nanoTime();
//        addAllocations();
//        long allocationsTabEnd=System.nanoTime() - allocationsTabStart;
//        FileUtil.writeTimeLogInMinutes("Allocations Tab:- ", allocationsTabEnd);

        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
//        exportIOFiles(newVoucherID,rootDriver);

        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"SalesReturns");

        long salesReturnsEnd = System.nanoTime() - salesReturnsStart;
        FileUtil.writeTimeLogInMinutes("Sales returns ended at:- ", salesReturnsEnd );

        return newVoucherID;

    }

    public void addProducts() throws InterruptedException, IOException {
        List<String> productCode=readExcelData(dataFile,"Items","ProductCode");
        List<String> masterType=readExcelData(dataFile,"Items","MasterType");
        System.out.println(masterType.size());
        for (int i = 0; i < productCode.size() ; i++)   {
            addData("xpath","//Edit[@Name='Product Code Row "+i+", Not sorted.']",dataFile,"Items","ProductCode",i);
        }
        List<WebElement> productAccRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Sales Returns Account * Row ')]");
        List<WebElement> productUOMRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        List<WebElement> productQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity Row ')]");
        List<WebElement> freeQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Free Quantity Row')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 520, 0);
        List<WebElement> numOfPacksRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'No Of Packs Row ')]");
        List<WebElement> mrpRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'MRP Row ')]");
        List<WebElement> unitRateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Unit Rate Row ')]");
        List<WebElement> grossAmount = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Gross Amount Row ')]");
        List<WebElement> voucherDiscountList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Voucher Disc % Row ')]");
        List<WebElement> partyDiscountList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Party Disc % Row ')]");
        if (!discountIsClicked) {
            common.clickElement("xpath", "//Header[@Name='Disc Amount 1']");
            common.clickElement("xpath", "//Header[@Name='Disc Amount 2']");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
            common.clickElement("xpath", "//Header[@Name='Disc Amount 3']");
            discountIsClicked = true;
        }
        List<WebElement> discountBasis1RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 1 Row ')]");
        List<WebElement> disount1RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 1 Row ')]");
        List<WebElement> disount2BasisRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 2 Row ')]");
        List<WebElement> disount2RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 2 Row ')]");
        List<WebElement>  disount3BasisRowList= common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc Basis 3 Row ')]");
        List<WebElement> disount3RowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Disc 3 Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> GSTProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'GST Product Category Row ')]");
        List<WebElement> CESSProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'CESS Product Category Row ')]");
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",500, 0);
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        List<WebElement> Info1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 1 Row ')]");
        List<WebElement> Info2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 2 Row ')]");
        List<WebElement> Info3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 3 Row ')]");
        List<WebElement> Info4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 4 Row ')]");
        List<WebElement> Info5 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 5 Row ')]");
        List<WebElement> Value1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 1 Row ')]");
        List<WebElement> Value2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 2 Row ')]");
        List<WebElement> Value3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 3 Row ')]");
        List<WebElement> Value4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 4 Row ')]");
        List<WebElement> Value5 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 5 Row ')]");
        List<WebElement> Date1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date 1 Row ')]");
        List<WebElement> Date2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date 2 Row ')]");
        List<WebElement> Date3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date 3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 50, 0);
        List<WebElement> Bool1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 1 Row ')]");
        List<WebElement> Bool2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 2 Row ')]");
        List<WebElement> Bool3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 3 Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -2500, 0);

        for (int j = 0; j < productCode.size() ; j++) {
            enterListData(productAccRowList.get(j), dataFile, "Items", "SalesReturnsAccount",j);
            enterListData(productUOMRowList.get(j), dataFile, "Items", "UOM",j);
            enterListData(storageBin.get(j), dataFile, "Items", "StorageBin",j);
            if (masterType.get(j).equals("Products")){
                enterListData(productQuantityRowList.get(j), dataFile, "Items", "Quantity",j);
            } else if (masterType.get(j).equals("Products - MultiBatch")) {
                enterListData(productQuantityRowList.get(j), dataFile, "Items", "Quantity",j);
            } else if (masterType.get(j).equals("Products - Batches and Serial No")){
                Thread.sleep(1000);
                common.clickElement("xpath", "//Button[@Name='Serial Nos Row "+j+"']");
//                List<WebElement> editfields=common.findWebElements("xpath","//Window[@Name='Serial No Details']//Pane/Edit/*");
//                System.out.println("Serial number edit fields :"+editfields.size());
//                enterListData(editfields.get(2),dataFile,"Items","Quantity",j);
//                enterListData(editfields.get(3),dataFile,"Items","Quantity",j);
                WebElement increment = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
                increment.sendKeys(Keys.TAB,"int"+ Common.getRandomChar(), Keys.TAB,"2",Keys.ENTER);
                increment.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB,"1",Keys.ENTER);
                common.clickElement("xpath", "//Button[@Name='OK']");
            }
            if (!masterType.get(j).equals("Products - Batches and Serial No")){
            enterListData(freeQuantityRowList.get(j),dataFile,"Items","FreeQuantity",j);
            }
            if (j==3){
                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",50, 0);
                Thread.sleep(500);
            }
            enterListData(numOfPacksRowList.get(j),dataFile,"Items","NoOfPacks",j);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']",420, 0);
            enterListData(mrpRowList.get(j),dataFile,"Items","MRP",j);
            enterListData(unitRateRowList.get(j),dataFile,"Items","UnitRate",j);
            enterListData(grossAmount.get(j),dataFile,"Items","GrossAmount",j);
            enterListData(voucherDiscountList.get(j),dataFile,"Items","VoucherDiscountPercentage",j);
            enterListData(partyDiscountList.get(j),dataFile,"Items","PartyDiscountPercentage",j);
            enterListData(discountBasis1RowList.get(j),dataFile,"Items","DiscountBasis1",j);
            enterListData(disount1RowList.get(j),dataFile,"Items","Discount1",j);
            Thread.sleep(1000);
            enterListData(disount2BasisRowList.get(j),dataFile,"Items","DiscountBasis2",j);
            enterListData(disount2RowList.get(j),dataFile,"Items","Discount2",j);
            Thread.sleep(1500);
            enterListData(disount3BasisRowList.get(j),dataFile,"Items","DiscountBasis3",j);
            enterListData(disount3RowList.get(j),dataFile,"Items","Discount3",j);
            Thread.sleep(1500);
            enterListData(hsnCodeRowList.get(j),dataFile,"Items","HSN",j);
            enterListData(GSTProductCategory.get(j),dataFile,"Items","GSTProductCategory",j);
            enterListData(CESSProductCategory.get(j),dataFile,"Items","CESSProductCategory",j);
            enterListData(departmentRowList.get(j),dataFile,"Items","Department",j);
            enterListData(projectRowList.get(j),dataFile,"Items","Project",j);
            enterListData(profitCentreRowList.get(j),dataFile,"Items","ProfitCentre",j);
            enterListData(costCentreRowList.get(j),dataFile,"Items","CostCentre",j);
            enterListData(commentsRowList.get(j),dataFile,"Items","Comments",j);
            enterListData(Info1.get(j),dataFile,"Items","Info1",j);
            enterListData(Info2.get(j),dataFile,"Items","Info2",j);
            enterListData(Info3.get(j),dataFile,"Items","Info3",j);
            enterListData(Info4.get(j),dataFile,"Items","Info4",j);
            enterListData(Info5.get(j),dataFile,"Items","Info5",j);
            enterListData(Value1.get(j),dataFile,"Items","Value1",j);
            enterListData(Value2.get(j),dataFile,"Items","Value2",j);
            enterListData(Value3.get(j),dataFile,"Items","Value3",j);
            enterListData(Value4.get(j),dataFile,"Items","Value4",j);
            enterListData(Value5.get(j),dataFile,"Items","Value5",j);
            enterListDate(Date1.get(j),dataFile,"Items","Date1",j);
            enterListDate(Date2.get(j),dataFile,"Items","Date2",j);
            enterListDate(Date3.get(j),dataFile,"Items","Date3",j);
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 70, 0);
            clickListData(Bool1.get(j));
            clickListData(Bool2.get(j));
            clickListData(Bool3.get(j));
            if (j<4) {
                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -2500, 0);
            }
        }
    }

    public void addChargesAndDeductions() throws IOException {
        navigateToChargesAndDeductionsTab();
        List<String> chargesAndDeductions=readExcelData(dataFile,"ChargesAndDeductions","ChargesOrDeductions");
        for (int i = 0; i < chargesAndDeductions.size() ; i++) {
            addData("xpath","//Edit[@Name='Charges Or Deductions * Row "+i+", Not sorted.']",dataFile,"ChargesAndDeductions","ChargesOrDeductions",i);
        }
        List<WebElement> accCodeRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Account Code Row ')]");
        if (!IsAmountHeaderClicked) {
            common.clickElement("xpath", "//Header[@Name='Amount *']");
            IsAmountHeaderClicked = true;
        }
        List<WebElement> basisRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Basis Row ')]");
        List<WebElement> percentageRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Percentage Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='ChargesAndDeductions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < chargesAndDeductions.size(); i++) {
            enterListData(accCodeRowList.get(i), dataFile, "ChargesAndDeductions", "AccountCode" , i);
            enterListData(basisRowList.get(i), dataFile, "ChargesAndDeductions", "Basis" , i);
            enterListData(percentageRowList.get(i), dataFile, "ChargesAndDeductions", "Percentage" , i);
            enterListData(departmentRowList.get(i), dataFile, "ChargesAndDeductions", "Department" , i);
            enterListData(projectRowList.get(i), dataFile, "ChargesAndDeductions", "Project" , i);
            enterListData(profitCentreRowList.get(i), dataFile, "ChargesAndDeductions", "ProfitCentre" , i);
            enterListData(costCentreRowList.get(i), dataFile, "ChargesAndDeductions", "CostCentre" , i);
            enterListData(commentsRowList.get(i), dataFile, "ChargesAndDeductions", "Comments" , i);
        }
    }

    public void addOtherCharges() throws IOException, InterruptedException{
        navigateToOtherChargesTab();
        List<String> otherCharges=readExcelData(dataFile,"OtherCharges","AccountCode");
        for (int i = 0; i < otherCharges.size() ; i++) {
            addData("xpath","//Edit[@Name='Account Code Row "+i+", Not sorted.']",dataFile,"OtherCharges","AccountCode",i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> hsnCodeRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'HSN Row ')]");
        List<WebElement> GSTProductCategory = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'GST Product Category Row ')]");
        List<WebElement> CESSProductCategory = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'CESS Product Category Row ')]");
        if (!otherChargesGSTCheckBox) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            otherChargesGSTCheckBox = true;
        }
        common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", 400, 0);
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", -400, 0);

        for (int i = 0; i < otherCharges.size(); i++) {
            enterListData(amountRowList.get(i), dataFile, "OtherCharges", "Amount", i);
            enterListData(hsnCodeRowList.get(i), dataFile, "OtherCharges", "HSN",i);
            enterListData(GSTProductCategory.get(i), dataFile, "OtherCharges", "GSTProductCategory",i);
            enterListData(CESSProductCategory.get(i), dataFile, "OtherCharges", "CESSProductCategory",i);
            Thread.sleep(1000);
            enterListData(departmentRowList.get(i), dataFile, "OtherCharges", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "OtherCharges", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "OtherCharges", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "OtherCharges", "CostCentre" ,i);
            enterListData(commentsRowList.get(i), dataFile, "OtherCharges", "Comments" , i);
            common.sliderHandling("xpath", "//Table[@Name='OtherCharges']/*/Thumb[@Name='Position']", -550, 0);
        }
    }

    public void addCash() throws IOException {
        navigateToCashTab();
        List<String> cashTab =readExcelData(dataFile,"Cash","CashAccountCode");
        for (int i = 0; i < cashTab.size() ; i++) {
            addData("xpath","//Edit[@Name='Cash Account Code Row "+i+", Not sorted.']",dataFile,"Cash","CashAccountCode",i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < cashTab.size() ; i++) {
            enterListData(amountRowList.get(i), dataFile, "Cash", "Amount",i);
            enterListData(departmentRowList.get(i),dataFile,"Cash","Department",i);
            enterListData(projectRowList.get(i),dataFile,"Cash","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"Cash","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"Cash","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"Cash","Comments",i);
        }
    }

    public void addCheques() throws IOException {
        navigateToCheques();
        List<String> chequesTab=readExcelData(dataFile,"Cheques","BankAccountCode");
        for (int i = 0; i < chequesTab.size(); i++) {
            addData("xpath","//Edit[@Name='Bank Account Code Row "+i+", Not sorted.']",dataFile,"Cheques","BankAccountCode",i);
        }
        List<WebElement> amountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> chargesAccRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Account Code Row ')]");
        List<WebElement> chargesAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Charges Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < chequesTab.size() ; i++) {
            enterListData(amountRowList.get(i), dataFile, "Cheques", "Amount" ,i);
            enterListData(chequeNo.get(i), dataFile, "Cheques", "ChequeNo" ,i);
            enterListDate(chequeDate.get(i),dataFile,"Cheques","ChequeDate",i);
//            enterListData(chequeDate.get(i), dataFile, "Cheques", "ChequeDate" ,i);
            enterListData(chargesAccRowList.get(i), dataFile, "Cheques", "ChargesAccount" , i);
            enterListData(chargesAmountRowList.get(i), dataFile, "Cheques", "Charges" , i);
            enterListData(departmentRowList.get(i),dataFile,"Cheques","Department",i);
            enterListData(projectRowList.get(i),dataFile,"Cheques","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"Cheques","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"Cheques","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"Cheques","Comments",i);
        }
    }

    public void addPostDatedCheques() throws IOException {
        navigateToPostdatedCheques();
        List<String> postDatedCheques=readExcelData(dataFile,"PostDatedCheques","BankAccountCode");
        for (int i = 0; i < postDatedCheques.size(); i++) {
            addData("xpath","//Edit[@Name='Bank Account Code Row "+i+", Not sorted.']",dataFile,"PostDatedCheques","BankAccountCode",i);
        }
        List<WebElement> pdcAcc = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'PDC Account * Row ')]");
        List<WebElement> amoutRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < postDatedCheques.size() ; i++) {
            enterListData(pdcAcc.get(i), dataFile, "PostDatedCheques", "PDCAccount" ,i);
            enterListData(amoutRowList.get(i), dataFile, "PostDatedCheques", "Amount" ,i);
            enterListData(chequeNo.get(i), dataFile, "PostDatedCheques", "ChequeNo" ,i);
            enterListDate(chequeDate.get(i),dataFile,"PostDatedCheques","ChequeDate",i);
            enterListData(departmentRowList.get(i),dataFile,"PostDatedCheques","Department",i);
            enterListData(projectRowList.get(i),dataFile,"PostDatedCheques","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"PostDatedCheques","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"PostDatedCheques","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"PostDatedCheques","Comments",i);
        }
    }

    public void addChequesPDC() throws IOException {
        navigateToChequesPDC();
        List<String> chequesPDC =readExcelData(dataFile,"PDC","BankAccountCode");
        for (int i = 0; i < chequesPDC.size(); i++) {
            addData("xpath","//Edit[@Name='Bank Account Code Row "+i+", Not sorted.']",dataFile,"PDC","BankAccountCode",i);
        }
        List<WebElement> chequeAmountRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < chequesPDC.size() ; i++) {
            enterListData(chequeAmountRowList.get(i), dataFile, "PDC", "Amount",i);
            enterListData(chequeNo.get(i), dataFile, "PDC", "ChequeNo" ,i);
            enterListDate(chequeDate.get(i), dataFile, "PDC", "ChequeDate" ,i);
            enterListData(departmentRowList.get(i),dataFile,"PDC","Department",i);
            enterListData(projectRowList.get(i),dataFile,"PDC","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"PDC","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"PDC","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"PDC","Comments",i);
        }
    }

    public void addInvoiceDetails() throws InterruptedException {
        Thread.sleep(500);
        common.clickElement("xpath","//TabItem[contains(@Name,'Invoice Details ')]");
        EnterData("//Edit[@Name='Invoice Value *']",dataFile,"InvoiceDetails","InvoiceValue");
        EnterData("//Edit[@Name='Invoice Date *']",dataFile,"InvoiceDetails","InvoiceDate");
    }

    public void otherInfo() throws InterruptedException, IOException {
        navigateToOtherInfoTab();
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(3000);
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }

    public void  additionalInformation() throws IOException {
        navigateToAdditionalInfo();
        EnterData("//Edit[@Name='Info 1']",dataFile,"AdditionalInformation","Info1");
        EnterData("//Edit[@Name='Info 2']",dataFile,"AdditionalInformation","Info2");
        EnterData("//Edit[@Name='Info 3']",dataFile,"AdditionalInformation","Info3");
        EnterData("//Edit[@Name='Info 4']",dataFile,"AdditionalInformation","Info4");
        EnterData("//Edit[@Name='Info 5']",dataFile,"AdditionalInformation","Info5");
        EnterData("//Edit[@Name='Value 1']",dataFile,"AdditionalInformation","Value1");
        EnterData("//Edit[@Name='Value 2']",dataFile,"AdditionalInformation","Value2");
        EnterData("//Edit[@Name='Value 3']",dataFile,"AdditionalInformation","Value3");
        EnterData("//Edit[@Name='Value 4']",dataFile,"AdditionalInformation","Value4");
        EnterData("//Edit[@Name='Value 5']",dataFile,"AdditionalInformation","Value5");
        EnterDate("//Edit[@Name='Date 1']",dataFile,"AdditionalInformation","Date1");
        EnterDate("//Edit[@Name='Date 2']",dataFile,"AdditionalInformation","Date2");
        EnterDate("//Edit[@Name='Date 3']",dataFile,"AdditionalInformation","Date3");
        common.clickElement("xpath","//CheckBox[@Name='Bool 1']");
        common.clickElement("xpath","//CheckBox[@Name='Bool 2']");
        common.clickElement("xpath","//CheckBox[@Name='Bool 3']");
    }

    public void addShippingAddress() throws InterruptedException {
        navigateToShippingAddress();
        EnterData("//Edit[@Name='Party Name']",dataFile,"ShippingAddress","PartyAccount");
        EnterData("//Edit[@Name='Address 1 *']",dataFile,"ShippingAddress","Address1");
        EnterData("//Edit[@Name='Address 2']",dataFile,"ShippingAddress","Address2");
        EnterData("//Edit[@Name='Address 3']",dataFile,"ShippingAddress","Address3");
        EnterData("//Edit[@Name='City *']",dataFile,"ShippingAddress","City");
        EnterData("//Edit[@Name='State *']",dataFile,"ShippingAddress","State");
//        EnterData("//Edit[@Name='State Code *']",dataFile,"ShippingAddress","StateCode");
        EnterData("//Edit[@Name='Country *']",dataFile,"ShippingAddress","Country");
        EnterData("//Edit[@Name='Zip *']",dataFile,"ShippingAddress","Zip");
        EnterData("//Edit[@Name='Telephone No']",dataFile,"ShippingAddress","TelephoneNo");
        Thread.sleep(1000);
        EnterData("//Edit[@Name='Mobile No']/ancestor::Edit[@Name='Mobile No']",dataFile,"ShippingAddress","MobileNo");
    }

    public  void termsAndCondition() throws IOException {
        navigateToTermsAndConditions();
        List<String> termsAndConditions=readExcelData(dataFile,"TermsAndConditions","TermType");
        for (int i = 0; i < termsAndConditions.size() ; i++) {
            addData("xpath","//Edit[@Name='Term Type * Row "+i+", Not sorted.']",dataFile,"TermsAndConditions","TermType",i);
        }
        List<WebElement> term = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Term * Row ')]");
        List<WebElement> comments = common.findWebElements("xpath", "//Table[@Name='TermsAndConditions']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < termsAndConditions.size(); i++) {
            enterListData(term.get(i), dataFile, "TermsAndConditions", "Term", i);
            enterListData(comments.get(i), dataFile, "TermsAndConditions", "Comments", i);
        }
    }

//    public void addAllocations() {
//        navigateToAllocations();
//        EnterData( "//Edit[@Name='Department']", dataFile, "Allocations", "Department");
//        EnterData( "//Edit[@Name='Project']", dataFile, "Allocations", "Project");
//        EnterData("//Edit[@Name='Profit Centre']", dataFile, "Allocations", "ProfitCentre");
//        EnterData( "//Edit[@Name='Cost Centre']", dataFile, "Allocations", "CostCentre");
//    }

}

