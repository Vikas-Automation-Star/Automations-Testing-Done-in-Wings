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
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class SalesReturnWithInvoiceReference extends TransactionsBaseClass {
    WindowsDriver driver,rootDriver;
    Common common;
    String dataFile;

    boolean gstAmountClicked = false,IsAmountHeaderClicked=false,otherChargesGSTCheckBox=false,discountIsClicked=false;

    public SalesReturnWithInvoiceReference(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String salesReturnWithInvoiceReference(String invoiceVoucher,String tempAPIBodyUpdate,String apiResponse,String outputFile) throws Exception {
        long salesRWIRStart = System.nanoTime();

        Thread.sleep(2000);
        navigateToMastersWhen3Steps("Sales","Invoices","Sales Return with Invoice Reference");
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterLocation(dataFile,"GeneralInformation","Location");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterExchangeRate(dataFile,"GeneralInformation","ExchangeRate");
//        enterSalesInvoiceNumber(dataFile,"GeneralInformation","SalesInvoiceNo");
        WebElement element= driver.findElementByXPath("//Edit[@Name='Sales Invoice No *']");
        element.sendKeys(invoiceVoucher, Keys.TAB);
//        enterPartyAcc(dataFile,"GeneralInformation","PartyAccountCode");
        Thread.sleep(3000);
        gstTransactionType("Intra State Sales Returns from Registered Dealers");
        Thread.sleep(5000);
        common.clickElement("xpath", "//Button[@Name='OK']");
        enterSalesReturnAccCode(dataFile,"GeneralInformation","SalesReturnsAccount");
        enterTcsTransNature(dataFile, "GeneralInformation", "TCSTransactionNature");
        enterPriceList(dataFile, "GeneralInformation", "PriceList");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");
        generalInfoSliderHandle(-500);


        //F3-Items
        long addProductStart=System.nanoTime();
        addPendingProducts();
        long addProductEnd=System.nanoTime()-addProductStart;
        FileUtil.writeTimeLogInMinutes("Sales returns with invoice reference End Add Products:- ",addProductEnd);

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

        navigateToBillsReceivablesTab();
        common.deleteInvalidRows();

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
        long invoiceDetails =System.nanoTime();
        navigateToInvoiceDetails();
        AddInvoiceDetails();
        long invoiceDetailsEnd =System.nanoTime()- invoiceDetails;
        FileUtil.writeTimeLogInMinutes("Cheques[PDC] Tab:- ", invoiceDetailsEnd);

        //Other info
        long otherInfoTabStart =System.nanoTime();
        otherInfo();
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Other Info Tab:- ", otherInfoTabEnd);

        //additional Info
        long additionalInfoTabStart =System.nanoTime();
        additionalInformation();
        long additionalInfoTabEnd =System.nanoTime()- additionalInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Additional Info Tab:- ", additionalInfoTabEnd);

        //Scroll
        navigateToAdditionalInfo();
        moveToRight(4);

        //shipping address
        long addShippingAddress =System.nanoTime();
        navigateToShippingAddress();
        addShippingAddress();
        long addShippingAddressEnd =System.nanoTime()- addShippingAddress;
        FileUtil.writeTimeLogInMinutes("Shipping Address:- ", addShippingAddressEnd);

        //terms and Cond
        long termsConditionsTabStart =System.nanoTime();
        termsAndCondition();
        long termsConditionsTabEnd =System.nanoTime()- termsConditionsTabStart;
        FileUtil.writeTimeLogInMinutes("Terms and Conditions Tab:- ", termsConditionsTabEnd);

        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID).replace(" ","");
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        APIClient.validateAPIWithExcel(newVoucherID,tempAPIBodyUpdate,apiResponse,outputFile,"SalesReturnsWithInvoiceReference");

        long salesRWIREnd = System.nanoTime() - salesRWIRStart;
        FileUtil.writeTimeLogInMinutes("Sales Returns with invoice reference ended at:- ", salesRWIREnd );
        return newVoucherID;
    }

    public void addPendingProducts() throws IOException, InterruptedException {
        List<String> productsOrderInExcel=getValuesByColumnHeader(dataFile,"Items","Product");
        List<String> productsCode=getValuesByColumnHeader(dataFile,"Items","ProductCode");
//        List<String> masterType=readExcelData(dataFile,"Items","MasterType");
//        List<String> productPendingQuantityExcel=readExcelData(dataFile,"Items","PendingQuantity");
//        List<String> productPendingFreeQuantityExel=readExcelData(dataFile,"Items","PendingFreeQuantity");
//        for (String element2:masterType){
//            System.out.println(element2+"masterType");
//        }
//        for (String element3:productsCode){
//            System.out.println(element3+"productCode in Excel");
//        }
//        for (String element:productPendingQuantityExcel){
//            System.out.println(element+"pending quantity in excel");
//        }
//        for (String element1:productPendingFreeQuantityExel){
//            System.out.println(element1+"pending  free quantity in excel");
//        }

//        List<String> quantityData=readExcelData(dataFile,"Items","Quantity");
//        List<String> freeQuantityData=readExcelData(dataFile,"Items","FreeQuantity");

        //read products order from app
        List<WebElement> productsOrderInApp=common.findWebElements("xpath","//Table[@Name='Items']/*[starts-with(@Name,'Row ')]/Edit[starts-with(@Name,'Product * Row ')]");
//        List<WebElement> appProduct = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Product * Row ')]");
//        List<WebElement> productCodeRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Product Code Row')]");
//        List<WebElement> productPendingQuantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Pending Quantity Row ')]");
//        for (WebElement element5:productPendingQuantity){
//            System.out.println(element5.getText()+"pending quantity in App");
//        }
//        List<WebElement> productPendingFreeQuantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Pending Free Quantity Row ')]");
//        for (WebElement element6:productPendingFreeQuantity){
//            System.out.println(element6.getText()+"free pending quantity in App");
//        }
        List<WebElement> productAccRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Sales Returns Account * Row ')]");
        List<WebElement> productUOMRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        List<WebElement> productQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
        List<WebElement> freeQuantityRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Free Quantity Row ')]");
        List<WebElement> mrpRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'MRP Row ')]");
        List<WebElement> unitRateRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Unit Rate Row ')]");
        List<WebElement> grossAmount = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Gross Amount Row ')]");
        List<WebElement> voucherDiscountList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Voucher Disc % Row ')]");
        List<WebElement> partyDiscountList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Party Disc % Row ')]");
        if (!discountIsClicked) {
            common.clickElement("xpath", "//Header[@Name='Disc Amount 1']");
            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 430, 0);
            common.clickElement("xpath", "//Header[@Name='Disc Amount 2']");
            common.clickElement("xpath", "//Header[@Name='Disc amount 3']");
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

//        for (int i = 0; i < masterType.size(); i++) {
//            String type = masterType.get(i);
//            double pendingQty = Double.parseDouble(productPendingQuantity.get(i).getText());
//            double pendingFreeQty = Double.parseDouble(productPendingFreeQuantity.get(i).getText());
//            double excelPendingQty = Double.parseDouble(productPendingQuantityExcel.get(i));
//            double excelPendingFreeQty = Double.parseDouble(productPendingFreeQuantityExel.get(i));
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 200, 0);
//
//            if (type.equals("Products") || type.equals("Products - MultiBatch")) {
//                enterListData(productQuantityRowList.get(i), dataFile, "Items", "Quantity", i);
//                Thread.sleep(1000);
//                enterListData(freeQuantityRowList.get(i), dataFile, "Items", "FreeQuantity", i);
//            }
//            else if (type.equals("Products - Batches and Serial No")) {
//                if (pendingQty == 1.0 && pendingFreeQty == 0.0 && excelPendingQty == 1.0 && excelPendingFreeQty == 0.0) {
//                    enterListData(productQuantityRowList.get(i), dataFile, "Items", "Quantity", i);
//                    Thread.sleep(1000);
//                    enterListData(freeQuantityRowList.get(i), dataFile, "Items", "FreeQuantity", i);
//
//                } else if (pendingQty == 0.0 && pendingFreeQty == 1.0 && excelPendingQty == 0.0 && excelPendingFreeQty == 1.0) {
//                    enterListData(productQuantityRowList.get(i), dataFile, "Items", "Quantity", i);
//                    Thread.sleep(1000);
//                    enterListData(freeQuantityRowList.get(i), dataFile, "Items", "FreeQuantity", i);
//
//                } else {
//                    System.out.println("⚠ Skipping unmatched row: " + i +
//                            " | App Pending=" + pendingQty +
//                            ", Excel Pending=" + excelPendingQty +
//                            " | App Free=" + pendingFreeQty +
//                            ", Excel Free=" + excelPendingFreeQty);
//                    continue; // skip instead of fail
//                }
//            } else {
//                System.out.println("⚠ Skipping invalid master type for row: " + i);
//                continue;
//            }
//            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -400, 0);
//        }


//        for (int i = 0; i < productsCode.size(); i++) {
//            enterListData(productAccRowList.get(i),dataFile,"Items","SalesReturnsAccount",i);
//            enterListData(productUOMRowList.get(i),dataFile,"Items","UOM",i);
//            enterListData(storageBin.get(i),dataFile,"Items","StorageBin",i);
//            if (masterType.get(i).equals("Products")){
//                enterListData(productQuantityRowList.get(i), dataFile, "Items", "Quantity",i);
//                Thread.sleep(2000);
//                enterListData(freeQuantityRowList.get(i),dataFile,"Items","FreeQuantity",i);
//            } else if (masterType.get(i).equals("Products - MultiBatch")) {
//                enterListData(productQuantityRowList.get(i), dataFile, "Items", "Quantity",i);
//                Thread.sleep(2000);
//                enterListData(freeQuantityRowList.get(i),dataFile,"Items","FreeQuantity",i);
//            }else if (masterType.get(i).equals("Products - Batches and Serial No") && productPendingQuantity.get(i).getText().equals("1.000")&&
//                    productPendingFreeQuantity.get(i).getText().equals("0.000")&& productPendingQuantityExcel.get(i).equals("1.0")&& productPendingFreeQuantityExel.get(i).equals("0.0")){
//                    Thread.sleep(1000);
//                enterListData(productQuantityRowList.get(i), dataFile, "Items", "Quantity",i);
//                Thread.sleep(2000);
//                enterListData(freeQuantityRowList.get(i),dataFile,"Items","FreeQuantity",i);
//            }else if (masterType.get(i).equals("Products - Batches and Serial No") && productPendingQuantity.get(i).getText().equals("0.000")&&
//            productPendingFreeQuantity.get(i).getText().equals("1.000")&&productPendingQuantityExcel.get(i).equals("0.0")&& productPendingFreeQuantityExel.get(i).equals("1.0")){
//                enterListData(productQuantityRowList.get(i), dataFile, "Items", "Quantity",i);
//                Thread.sleep(2000);
//                enterListData(freeQuantityRowList.get(i),dataFile,"Items","FreeQuantity",i);
//            }else {
//                Assert.fail("No product present");
//            }
//            enterListData(mrpRowList.get(i), dataFile, "Items", "MRP", i);
//            enterListData(unitRateRowList.get(i), dataFile, "Items", "UnitRate", i);
////            enterListData(grossAmount.get(i), dataFile, "Items", "GrossAmount", i);
////            enterListData(voucherDiscountList.get(i), dataFile, "Items", "VoucherDiscountPercentage", i);
////            enterListData(partyDiscountList.get(i), dataFile, "Items", "PartyDiscountPercentage", i);
////            enterListData(discountBasis1RowList.get(i), dataFile, "Items", "DiscountBasis1", i);
////            enterListData(disount1RowList.get(i), dataFile, "Items", "Discount1", i);
////            Thread.sleep(1000);
////            enterListData(disount2BasisRowList.get(i), dataFile, "Items", "DiscountBasis2", i);
////            enterListData(disount2RowList.get(i), dataFile, "Items", "Discount2", i);
////            enterListData(disount3BasisRowList.get(i), dataFile, "Items", "DiscountBasis3", i);
////            enterListData(disount3RowList.get(i), dataFile, "Items", "Discount3", i);
////            enterListData(hsnCodeRowList.get(i), dataFile, "Items", "HSN", i);
////            enterListData(GSTProductCategory.get(i), dataFile, "Items", "GSTProductCategory", i);
////            enterListData(CESSProductCategory.get(i), dataFile, "Items", "CESSProductCategory", i);
////            enterListData(departmentRowList.get(i), dataFile, "Items", "Department", i);
////            enterListData(projectRowList.get(i), dataFile, "Items", "Project", i);
////            enterListData(profitCentreRowList.get(i), dataFile, "Items", "ProfitCentre", i);
////            enterListData(costCentreRowList.get(i), dataFile, "Items", "CostCentre", i);
////            enterListData(commentsRowList.get(i), dataFile, "Items", "Comments", i);
////            enterListData(Info1.get(i), dataFile, "Items", "Info1", i);
////            enterListData(Info2.get(i), dataFile, "Items", "Info2", i);
////            enterListData(Info3.get(i), dataFile, "Items", "Info3", i);
////            enterListData(Info4.get(i), dataFile, "Items", "Info4", i);
////            enterListData(Info5.get(i), dataFile, "Items", "Info5", i);
////            enterListData(Value1.get(i), dataFile, "Items", "Value1", i);
////            enterListData(Value2.get(i), dataFile, "Items", "Value2", i);
////            enterListData(Value3.get(i), dataFile, "Items", "Value3", i);
////            enterListData(Value4.get(i), dataFile, "Items", "Value4", i);
////            enterListData(Value5.get(i), dataFile, "Items", "Value5", i);
////            enterListDate(Date1.get(i), dataFile, "Items", "Date1", i);
////            enterListDate(Date2.get(i), dataFile, "Items", "Date2", i);
////            enterListDate(Date3.get(i), dataFile, "Items", "Date3", i);
////            common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 70, 0);
////            clickListData(Bool1.get(i));
////            clickListData(Bool2.get(i));
////            clickListData(Bool3.get(i));
//            if (i < 6) {
//                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -2500, 0);
//            }
//        }

        Set<Integer> usedAppRows = new HashSet<>();
        for (int i = 0; i < productsCode.size(); i++) {
            String excelProduct = productsOrderInExcel.get(i);
            for (int j = 0; j < productsOrderInApp.size(); j++) {
                String AppProduct = productsOrderInApp.get(j).getText().trim();
                if (excelProduct.equalsIgnoreCase(AppProduct) && !usedAppRows.contains(j)) {
                    Thread.sleep(1000);
                    enterListData(productAccRowList.get(j),dataFile,"Items","SalesReturnsAccount",j);
                    enterListData(productUOMRowList.get(j),dataFile,"Items","UOM",j);
                    enterListData(storageBin.get(j),dataFile,"Items","StorageBin",j);
                    enterListData(productQuantityRowList.get(j),dataFile,"Items","Quantity",j);
                    Thread.sleep(2000);
                    enterListData(freeQuantityRowList.get(j),dataFile,"Items","FreeQuantity",j);
                    enterListData(mrpRowList.get(j), dataFile, "Items", "MRP", j);
                    enterListData(unitRateRowList.get(j), dataFile, "Items", "UnitRate", j);
                    enterListData(grossAmount.get(j), dataFile, "Items", "GrossAmount", j);
                    enterListData(voucherDiscountList.get(j), dataFile, "Items", "VoucherDiscountPercentage", j);
                    enterListData(partyDiscountList.get(j), dataFile, "Items", "PartyDiscountPercentage", j);
                    enterListData(discountBasis1RowList.get(j), dataFile, "Items", "DiscountBasis1", j);
                    enterListData(disount1RowList.get(j), dataFile, "Items", "Discount1", j);
                    Thread.sleep(1000);
                    enterListData(disount2BasisRowList.get(j), dataFile, "Items", "DiscountBasis2", j);
                    enterListData(disount2RowList.get(j), dataFile, "Items", "Discount2", j);
                    enterListData(disount3BasisRowList.get(j), dataFile, "Items", "DiscountBasis3", j);
                    enterListData(disount3RowList.get(j), dataFile, "Items", "Discount3", j);
                    enterListData(hsnCodeRowList.get(j), dataFile, "Items", "HSN", j);
                    enterListData(GSTProductCategory.get(j), dataFile, "Items", "GSTProductCategory", j);
                    enterListData(CESSProductCategory.get(j), dataFile, "Items", "CESSProductCategory", j);
                    enterListData(departmentRowList.get(j), dataFile, "Items", "Department", j);
                    enterListData(projectRowList.get(j), dataFile, "Items", "Project", j);
                    enterListData(profitCentreRowList.get(j), dataFile, "Items", "ProfitCentre", j);
                    enterListData(costCentreRowList.get(j), dataFile, "Items", "CostCentre", j);
                    enterListData(commentsRowList.get(j), dataFile, "Items", "Comments", j);
                    enterListData(Info1.get(j), dataFile, "Items", "Info1", j);
                    enterListData(Info2.get(j), dataFile, "Items", "Info2", j);
                    enterListData(Info3.get(j), dataFile, "Items", "Info3", j);
                    enterListData(Info4.get(j), dataFile, "Items", "Info4", j);
                    enterListData(Info5.get(j), dataFile, "Items", "Info5", j);
                    enterListData(Value1.get(j), dataFile, "Items", "Value1", j);
                    enterListData(Value2.get(j), dataFile, "Items", "Value2", j);
                    enterListData(Value3.get(j), dataFile, "Items", "Value3", j);
                    enterListData(Value4.get(j), dataFile, "Items", "Value4", j);
                    enterListData(Value5.get(j), dataFile, "Items", "Value5", j);
                    enterListDate(Date1.get(j), dataFile, "Items", "Date1", j);
                    enterListDate(Date2.get(j), dataFile, "Items", "Date2", j);
                    enterListDate(Date3.get(j), dataFile, "Items", "Date3", j);
                    common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 70, 0);
                    clickListData(Bool1.get(j));
                    clickListData(Bool2.get(j));
                    clickListData(Bool3.get(j));
                    if (j < 6) {
                        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -2500, 0);
                    }
                    usedAppRows.add(j);
                    break; // go to next Excel row once matched
                }
            }
        }

    }

    public void addChargesAndDeductions() throws IOException {
        navigateToChargesAndDeductionsTab();
        List<String> chargesAndDeductions=readExcelData(dataFile,"ChargesAndDeductions","ChargesOrDeductions");
        for (int i = 0; i < chargesAndDeductions.size() ; i++) {
            addData("xpath","//Edit[@Name='Chargesor Deductions * Row "+i+", Not sorted.']",dataFile,"ChargesAndDeductions","ChargesorDeductions",i);
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
        List<WebElement> uomList = common.findWebElements("xpath", "//Table[@Name='OtherCharges']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'UOM * Row ')]");
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
            enterListData(uomList.get(i), dataFile, "OtherCharges", "UOM", i);
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
//        List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
//        List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
//        List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='Cash']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");
        for (int i = 0; i < cashTab.size() ; i++) {
            enterListData(amountRowList.get(i), dataFile, "Cash", "Amount",i);
//            enterListData(tdsTransNatureRowList.get(i), dataFile, "Cash", "TDSTransactionNature",i);
//            enterListData(tdsAccountRowList.get(i), dataFile, "Cash", "TDSAccount",i);
//            enterListData(tdsAmountRowList.get(i), dataFile, "Cash", "TDSAmount",i);
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
//        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
//        List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
//        List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
//        List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
//        List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='Cheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
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
//            enterListData(drawnOnRowList.get(i), dataFile, "Cheques", "DrawnOnBankAccount",i);
//            enterListData(drawnOnBranchBranchRowList.get(i), dataFile, "Cheques", "DrawnOnBankBranch",i);
//            enterListData(tdsTransNatureRowList.get(i), dataFile, "Cheques", "TDSTransactionNature",i);
//            enterListData(tdsAccountRowList.get(i), dataFile, "Cheques", "TDSAccount",i);
//            enterListData(tdsAmountRowList.get(i), dataFile, "Cheques", "TDSAmount",i);
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
//        navigateToPostdatedCheques();
        List<WebElement> PostdatedCheques=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(PostdatedCheques.get(1).getText());
        PostdatedCheques.get(1).click();
        List<String> postDatedCheques=readExcelData(dataFile,"PostDatedCheques","BankAccountCode");
        for (int i = 0; i < postDatedCheques.size(); i++) {
            addData("xpath","//Edit[@Name='Bank Account Code Row "+i+", Not sorted.']",dataFile,"PostDatedCheques","BankAccountCode",i);
        }
        List<WebElement> pdcAcc = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'PDC Account * Row ')]");
        List<WebElement> amoutRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount * Row ')]");
        List<WebElement> chequeNo = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque/EFT No * Row ')]");
        List<WebElement> chequeDate = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cheque Date * Row ')]");
//        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
//        List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
//        List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
//        List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
//        List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='PostDatedCheques']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
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
//            enterListData(drawnOnRowList.get(i), dataFile, "PostDatedCheques", "DrawnOnBankAccount",i);
//            enterListData(drawnOnBranchBranchRowList.get(i), dataFile, "PostDatedCheques", "DrawnOnBankBranch",i);
//            enterListData(tdsTransNatureRowList.get(i), dataFile, "PostDatedCheques", "TDSTransactionNature",i);
//            enterListData(tdsAccountRowList.get(i), dataFile, "PostDatedCheques", "TDSAccount",i);
//            enterListData(tdsAmountRowList.get(i), dataFile, "PostDatedCheques", "TDSAmount",i);
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
//        List<WebElement> drawnOnRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank * Row ')]");
//        List<WebElement> drawnOnBranchBranchRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Drawn On Bank Branch Row ')]");
//        List<WebElement> tdsTransNatureRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Transaction Nature Row ')]");
//        List<WebElement> tdsAccountRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Account Row ')]");
//        List<WebElement> tdsAmountRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'TDS Amount Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='PDC']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < chequesPDC.size() ; i++) {
            enterListData(chequeAmountRowList.get(i), dataFile, "PDC", "Amount",i);
            enterListData(chequeNo.get(i), dataFile, "PDC", "ChequeNo" ,i);
            enterListDate(chequeDate.get(i), dataFile, "PDC", "ChequeDate" ,i);
//            enterListData(drawnOnRowList.get(i), dataFile, "PDC", "DrawnOnBankAccount",i);
//            enterListData(drawnOnBranchBranchRowList.get(i), dataFile, "PDC", "DrawnOnBankBranch",i);
//            enterListData(tdsTransNatureRowList.get(i), dataFile, "PDC", "TDSTransactionNature",i);
//            enterListData(tdsAccountRowList.get(i), dataFile, "PDC", "TDSAccount",i);
//            enterListData(tdsAmountRowList.get(i), dataFile, "PDC", "TDSAmount",i);
            enterListData(departmentRowList.get(i),dataFile,"PDC","Department",i);
            enterListData(projectRowList.get(i),dataFile,"PDC","Project",i);
            enterListData(profitCentreRowList.get(i),dataFile,"PDC","ProfitCentre",i);
            enterListData(costCentreRowList.get(i),dataFile,"PDC","CostCentre",i);
            enterListData(commentsRowList.get(i),dataFile,"PDC","Comments",i);
        }
    }

    public void AddInvoiceDetails() throws IOException {
        EnterData("//Edit[@Name='Invoice Value *']",dataFile,"InvoiceDetails","InvoiceValue");
        EnterDate("//Edit[@Name='Invoice Date *']",dataFile,"InvoiceDetails","InvoiceDate");
    }

    public void otherInfo() throws IOException, AWTException {
        navigateToOtherInfoTab();
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
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

    public void addShippingAddress(){
        navigateToShippingAddress();
        EnterData("//Edit[@Name='Party Name']",dataFile,"ShippingAddress","PartyAccount");
        EnterData("//Edit[@Name='GSTIN']",dataFile,"ShippingAddress","GSTIN");
        EnterData("//Edit[@Name='Address 1 *']",dataFile,"ShippingAddress","Address1");
        EnterData("//Edit[@Name='Address 2']",dataFile,"ShippingAddress","Address2");
        EnterData("//Edit[@Name='Address 3']",dataFile,"ShippingAddress","Address3");
        EnterData("//Edit[@Name='City *']",dataFile,"ShippingAddress","City");
        EnterData("//Edit[@Name='State *']",dataFile,"ShippingAddress","State");
        EnterData("//Edit[@Name='State Code *']",dataFile,"ShippingAddress","StateCode");
        EnterData("//Edit[@Name='Country *']",dataFile,"ShippingAddress","Country");
        EnterData("//Edit[@Name='Zip *']",dataFile,"ShippingAddress","Zip");
        EnterData("//Edit[@Name='Telephone No']",dataFile,"ShippingAddress","TelephoneNo");
        EnterData("//Edit[@Name='Mobile No']/ancestor::Edit[@Name='Mobile No']",dataFile,"ShippingAddress","MobileNo");
    }

    public  void termsAndCondition() throws IOException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Terms And Conditions')]");
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

}