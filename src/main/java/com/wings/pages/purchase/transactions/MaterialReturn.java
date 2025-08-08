package com.wings.pages.purchase.transactions;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaterialReturn extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked = false,IsAmountHeaderClicked=false,otherChargesGSTCheckBox=false,discountIsClicked=false;


    public MaterialReturn(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public String materialReturn(String receiptsNum) throws InterruptedException, IOException, ParseException, AWTException {
        long start = System.nanoTime();

        navigateToMastersWhen3Steps("Purchase","Receipts", "Material Returns");
        Thread.sleep(1000);
        long generalInfoStart=System.nanoTime();
        Thread.sleep(5000);
        String oldVoucherID =oldTTransactionID();
        enterVoucherType(dataFile,"GeneralInformation","VoucherType");
        EnterDate("//Edit[@Name='Date *']",dataFile,"GeneralInformation","Date");
        enterBranch(dataFile,"GeneralInformation","Branch");
        enterCurrency(dataFile,"GeneralInformation","TransactionCurrency");
        enterReceiptNum(receiptsNum);
        Thread.sleep(5000);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1000);
        enterCreditPeriod(dataFile,"GeneralInformation","CreditPeriod");
        enterPriceList(dataFile, "GeneralInformation", "PriceList");
        enterExecutive(dataFile,"GeneralInformation","Executive");
        enterRemarks(dataFile,"GeneralInformation","Remarks");

        long generalInfoEndTime = System.nanoTime() - generalInfoStart;
        FileUtil.writeTimeLogInMinutes("General information End:- ", generalInfoEndTime);

        //F3-Items
        long addProductStart=System.nanoTime();
        addProduct();
        long addProductEnd=System.nanoTime()-addProductStart;
        FileUtil.writeTimeLogInMinutes("Material Returns Add Products:- ",addProductEnd);
        //charges and deductions
        long chargesDeductionsStart =System.nanoTime();
        addChargesAndDeductions();
        long chargesDeductionsEnd=System.nanoTime()- chargesDeductionsStart;
        FileUtil.writeTimeLogInMinutes("Material Returns Charges and Deductions:- ",chargesDeductionsEnd);
        //Other Costs
        long otherCostsStart =System.nanoTime();
        addOtherCosts();
        long otherCostsEnd =System.nanoTime()- otherCostsStart;
        FileUtil.writeTimeLogInMinutes("Material Returns Other Costs:- ", otherCostsEnd);
        //Other info
        long otherInfoTabStart =System.nanoTime();
        otherInfo();
        long otherInfoTabEnd =System.nanoTime()- otherInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Material Returns Other Info Tab:- ", otherInfoTabEnd);
        //additional Info
        long additionalInfoTabStart =System.nanoTime();
        additionalInformation();
        long additionalInfoTabEnd =System.nanoTime()- additionalInfoTabStart;
        FileUtil.writeTimeLogInMinutes("Material Returns Additional Info Tab:- ", additionalInfoTabEnd);
        //terms and Cond
        long termsConditionsTabStart =System.nanoTime();
        termsAndCondition();
        long termsConditionsTabEnd =System.nanoTime()- termsConditionsTabStart;
        FileUtil.writeTimeLogInMinutes("Material Returns Terms and Conditions Tab:- ", termsConditionsTabEnd);

        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"Voucher Numbers are same. Check Transaction.");
        //end
        long salesInvoiceEnd = System.nanoTime() - start ;
        FileUtil.writeTimeLogInMinutes("Material Returns ended at:- ", salesInvoiceEnd );
        //IO
        String voucher = newVoucherID.replaceAll("\\d", "");
        String number = newVoucherID.replaceAll("\\D", "");
        Thread.sleep(2000);
        long iofIlesStart=System.nanoTime();
        exportIOFiles("Generate Input File", voucher,number);
        exportIOFiles("Generate Output File", voucher,number);
        long ioFilesEnd=System.nanoTime()-iofIlesStart;
        FileUtil.writeTimeLogInMinutes("Material Returns IO files ended at:- ", ioFilesEnd );

//        excelUtil.excelComparator("","",newVoucherID);
        return newVoucherID;
    }

    //    public void addProduct() throws IOException, ParseException, InterruptedException {
//        List<String> productsOrderInExcel=getValuesByColumnHeader(dataFile,"Items","Product");
//        //read products order from excel
////        System.out.println(productsOrderInExcel.size()+"excel products order");
////        for (String product : productsOrderInExcel) {
////            System.out.println(product);
////        }
//        //billed or free
//        List<String> productTypeInExcel=getValuesByColumnHeader(dataFile,"Items","ProductType");
////        for (String productType : productTypeInExcel) {
////            System.out.println("billed or free" +productType);
////        }
//        //read products order from app
//        List<WebElement> productsOrderInApp=common.findWebElements("xpath","//Table[@Name='Items']/*[starts-with(@Name,'Row ')]/Edit[starts-with(@Name,'Product * Row ')]");
////        System.out.println(productsOrderInApp.size()+"application products order");
////        for (WebElement element:productsOrderInApp){
////            System.out.println(element.getText());
////        }
//        List<WebElement> productTypeInApp=common.findWebElements("xpath","//Table[@Name='Items']/*[starts-with(@Name,'Row ')]/Edit[starts-with(@Name,'Product Type Row ')]");
////        System.out.println(productsOrderInApp.size()+"product type");
////        for (WebElement element:productTypeInApp){
////            System.out.println(element.getText());
////        }
//        List<String> productCode=readExcelData(dataFile,"Items","ProductCode");
//        List<String> masterType=readExcelData(dataFile,"Items","MasterType");
//        List<WebElement> uom = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
//        List<WebElement> location = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Location * Row ')]");
//        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 700, 0);
//        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity Row ')]");
//        List<WebElement> freeQuantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Free Quantity Row ')]");
//        List<WebElement> numOfPacksRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'No Of Packs Row ')]");
//        List<WebElement> Mrp = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'MRP Row ')]");
//        List<WebElement> UnitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Unit Rate Row ')]");
//
//        if (!discountIsClicked) {
//            common.clickElement("xpath", "//Header[@Name='Disc Amount 1']");
//            common.clickElement("xpath", "//Header[@Name='Disc Amount 2']");
//            common.clickElement("xpath", "//Header[@Name='Disc Amount 3']");
//            discountIsClicked = true;
//        }
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 600, 0);
//        List<WebElement> DiscountBasis1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Basis 1 Row ')]");
//        List<WebElement> Discount1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc 1 Row ')]");
//        List<WebElement> DiscountBasis2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Basis 2 Row ')]");
//        List<WebElement> Discount2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc 2 Row')]");
//        List<WebElement> DiscountBasis3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Basis 3 Row ')]");
//        List<WebElement> Discount3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc 3 Row ')]");
//        List<WebElement> HSNCode = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'HSN Row ')]");
//        List<WebElement> GSTProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'GST Product Category Row ')]");
//        List<WebElement> CESSProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'CESS Product Category Row ')]");
//        if (!gstAmountClicked) {
//            common.clickElement("xpath", "//Header[@Name='GST Amount']");
//            gstAmountClicked = true;
//        }
//        List<WebElement> reason = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Reason Row ')]");
//        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
//        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
//        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 650, 0);
//        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
//        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
//        List<WebElement> Info1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 1 Row ')]");
//        List<WebElement> Info2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 2 Row ')]");
//        List<WebElement> Info3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 3 Row ')]");
//        List<WebElement> Info4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 4 Row ')]");
//        List<WebElement> Info5 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Info 5 Row ')]");
//        List<WebElement> Value1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 1 Row ')]");
//        List<WebElement> Value2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 2 Row ')]");
//        List<WebElement> Value3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 3 Row ')]");
//        List<WebElement> Value4 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 4 Row ')]");
//        List<WebElement> Value5 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Value 5 Row ')]");
//        List<WebElement> Date1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date 1 Row ')]");
//        List<WebElement> Date2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date 2 Row ')]");
//        List<WebElement> Date3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Date 3 Row ')]");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 50, 0);
//        List<WebElement> Bool1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 1 Row ')]");
//        List<WebElement> Bool2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 2 Row ')]");
//        List<WebElement> Bool3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/CheckBox[contains(@Name,'Bool 3 Row ')]");
//        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1300, 0);
//
//        Set<Integer> usedAppRows = new HashSet<>();
//        for (int i = 0; i < productCode.size(); i++) {
//            String excelProduct = productsOrderInExcel.get(i);
//            String excelProductType = productTypeInExcel.get(i).trim();
//
//
//            for (int j = 0; j < productsOrderInApp.size(); j++) {
//                if (usedAppRows.contains(j)) continue;
//
//                String appProduct = productsOrderInApp.get(j).getText().trim();
//                String appProductType = productTypeInApp.get(j).getText().trim();
//
//                if (excelProduct.equalsIgnoreCase(appProduct) && excelProductType.equalsIgnoreCase(appProductType)) {
//                    System.out.println("Matching Excel Product: " + excelProduct + " | Type: " + excelProductType);
//                    System.out.println("Against App Product: " + appProduct + " | Type: " + appProductType);
//
//                    enterListData(uom.get(i), dataFile, "Items", "UOM", j);
//                    enterListData(location.get(i), dataFile, "Items", "Location", j);
//                    enterListData(storageBin.get(i), dataFile, "Items", "StorageBin", j);
//                    common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 150, 0);
//                    System.out.println("Excel Product Type at i=" + i + ": '" + excelProductType + "'");
//                    System.out.println("Quantity value at i=" + i + ": " + quantity.get(i));
//
//                    if(excelProductType.equalsIgnoreCase("Billed"))  {
//                        System.out.println("Entering Quantity: " + quantity.get(i) + " at App row: " + j);
//                        enterListData(quantity.get(i), dataFile, "Items", "Quantity", j);
//                    }
//                    else if (excelProductType.equalsIgnoreCase("Free")) {
//                        System.out.println("FreeQuantity value at i=" + i + ": " + freeQuantity.get(i));
//                        enterListData(freeQuantity.get(i), dataFile, "Items", "FreeQuantity", j);
//                    }
//                    enterListData(numOfPacksRowList.get(i), dataFile, "Items", "NoOfPacks", j);
//                    enterListData(Mrp.get(i), dataFile, "Items", "MRP", j);
//                    enterListData(UnitRate.get(i), dataFile, "Items", "UnitRate", j);
//                    enterListData(DiscountBasis1.get(i), dataFile, "Items", "DiscountBasis1", j);
//                    enterListData(Discount1.get(i), dataFile, "Items", "Discount1", j);
//                    enterListData(DiscountBasis2.get(i), dataFile, "Items", "DiscountBasis2", j);
//                    enterListData(Discount2.get(i), dataFile, "Items", "Discount2", j);
//                    enterListData(DiscountBasis3.get(i), dataFile, "Items", "DiscountBasis3", j);
//                    enterListData(Discount3.get(i), dataFile, "Items", "Discount3", j);
//                    enterListData(HSNCode.get(i), dataFile, "Items", "HSN", j);
//                    enterListData(GSTProductCategory.get(i), dataFile, "Items", "GSTProductCategory", j);
//                    enterListData(CESSProductCategory.get(i), dataFile, "Items", "CESSProductCategory", j);
//                    enterListData(reason.get(i), dataFile, "Items", "Reason", j);
//                    enterListData(Department.get(i), dataFile, "Items", "Department", j);
//                    enterListData(Project.get(i), dataFile, "Items", "Project", j);
//                    enterListData(ProfitCentre.get(i), dataFile, "Items", "ProfitCentre", j);
//                    enterListData(CostCentre.get(i), dataFile, "Items", "CostCentre", j);
//                    enterListData(Comments.get(i), dataFile, "Items", "Comments", j);
//                    enterListData(Info1.get(i), dataFile, "Items", "Info1", j);
//                    enterListData(Info2.get(i), dataFile, "Items", "Info2", j);
//                    enterListData(Info3.get(i), dataFile, "Items", "Info3", j);
//                    enterListData(Info4.get(i), dataFile, "Items", "Info4", j);
//                    enterListData(Info5.get(i), dataFile, "Items", "Info5", j);
//                    enterListData(Value1.get(i), dataFile, "Items", "Value1", j);
//                    enterListData(Value2.get(i), dataFile, "Items", "Value2", j);
//                    enterListData(Value3.get(i), dataFile, "Items", "Value3", j);
//                    enterListData(Value4.get(i), dataFile, "Items", "Value4", j);
//                    enterListData(Value5.get(i), dataFile, "Items", "Value5", j);
//                    common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 38, 0);
//                    enterListDate(Date1.get(i), dataFile, "Items", "Date1", j);
//                    enterListDate(Date2.get(i), dataFile, "Items", "Date2", j);
//                    enterListDate(Date3.get(i), dataFile, "Items", "Date3", j);
//                    common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 70, 0);
//                    clickListData(Bool1.get(i));
//                    clickListData(Bool2.get(i));
//                    clickListData(Bool3.get(i));
//                    common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1150, 0);
//                    usedAppRows.add(j);
//                    break; // go to next Excel row once matched
//                }
//            }
//        }
//
//    }
    public void addProduct() throws IOException, ParseException, InterruptedException {
        List<String> productsOrderInExcel=getValuesByColumnHeader(dataFile,"Items","Product");
        //read products order from excel
        System.out.println(productsOrderInExcel.size()+"excel products order");
        for (String product : productsOrderInExcel) {
            System.out.println(product);
        }
        //read products order from app
        List<WebElement> productsOrderInApp=common.findWebElements("xpath","//Table[@Name='Items']/*[starts-with(@Name,'Row ')]/Edit[starts-with(@Name,'Product * Row ')]");
        System.out.println(productsOrderInApp.size()+"application products order");
        for (WebElement element:productsOrderInApp){
            System.out.println(element.getText());
        }
        List<String> productCode=readExcelData(dataFile,"Items","ProductCode");
        List<String> masterType=readExcelData(dataFile,"Items","MasterType");
        List<WebElement> uom = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'UOM * Row ')]");
        List<WebElement> location = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Location * Row ')]");
        List<WebElement> storageBin = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Storage Bin * Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 700, 0);
        List<WebElement> quantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Quantity Row ')]");
        List<WebElement> freeQuantity = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Free Quantity Row ')]");
        List<WebElement> numOfPacksRowList = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'No Of Packs Row ')]");
        List<WebElement> Mrp = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'MRP Row ')]");
        List<WebElement> UnitRate = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Unit Rate Row ')]");

        if (!discountIsClicked) {
            common.clickElement("xpath", "//Header[@Name='Disc Amount 1']");
            common.clickElement("xpath", "//Header[@Name='Disc Amount 2']");
            common.clickElement("xpath", "//Header[@Name='Disc Amount 3']");
            discountIsClicked = true;
        }
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 490, 0);
        List<WebElement> DiscountBasis1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Basis 1 Row ')]");
        List<WebElement> Discount1 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc 1 Row ')]");
        List<WebElement> DiscountBasis2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Basis 2 Row ')]");
        List<WebElement> Discount2 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc 2 Row')]");
        List<WebElement> DiscountBasis3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc Basis 3 Row ')]");
        List<WebElement> Discount3 = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Disc 3 Row ')]");
        List<WebElement> HSNCode = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'HSN Row ')]");
        List<WebElement> GSTProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'GST Product Category Row ')]");
        List<WebElement> CESSProductCategory = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'CESS Product Category Row ')]");
        if (!gstAmountClicked) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            gstAmountClicked = true;
        }
        List<WebElement> reason = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Reason Row ')]");
        List<WebElement> Department = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Department Row ')]");
        List<WebElement> Project = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Project Row ')]");
        List<WebElement> ProfitCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Profit Centre Row ')]");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 650, 0);
        List<WebElement> CostCentre = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Cost Centre Row ')]");
        List<WebElement> Comments = common.findWebElements("xpath", "//Table[@Name='Items']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Comments Row ')]");
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
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1300, 0);
        List<String> excelProductTypes = getValuesByColumnHeader(dataFile, "Items", "ProductType"); // e.g., Billed / Free

        Set<Integer> usedAppRows = new HashSet<>();
        for (int j = 0; j < productsOrderInApp.size(); j++) {
            String appProduct = productsOrderInApp.get(j).getText().trim();

            for (int i = 0; i < productsOrderInExcel.size(); i++) {
                String excelProduct = productsOrderInExcel.get(i).trim();
                String productType=excelProductTypes.get(i).trim();
                System.out.println("Checking AppProduct: " + appProduct + " against ExcelProduct: " + excelProduct + ", Type: " + productType);

                if (appProduct.equalsIgnoreCase(excelProduct) && !usedAppRows.contains(i)) {
                    enterListData(uom.get(j), dataFile, "Items", "UOM", i);
                    enterListData(location.get(j), dataFile, "Items", "Location", i);
                    enterListData(storageBin.get(j), dataFile, "Items", "StorageBin", i);
                    common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 150, 0);
                    Thread.sleep(1500);
                    if(productType.equalsIgnoreCase("Billed")) {
                        System.out.println("Quantity value to enter: " + quantity.get(j));

                        System.out.println("Entering Billed Quantity at row: " + i + " for Product: " + excelProduct);

                        enterListData(quantity.get(j), dataFile, "Items", "Quantity", i);
                    }else if(productType.equalsIgnoreCase("Free")) {
                        System.out.println("free Quantity value to enter: " + freeQuantity.get(j));

                        enterListData(freeQuantity.get(j), dataFile, "Items", "FreeQuantity", i);
                    }
                    enterListData(numOfPacksRowList.get(j), dataFile, "Items", "NoOfPacks", i);
                    enterListData(Mrp.get(j), dataFile, "Items", "MRP", i);
                    enterListData(UnitRate.get(j), dataFile, "Items", "UnitRate", i);
                    enterListData(DiscountBasis1.get(j), dataFile, "Items", "DiscountBasis1", i);
                    enterListData(Discount1.get(j), dataFile, "Items", "Discount1", i);
                    enterListData(DiscountBasis2.get(j), dataFile, "Items", "DiscountBasis2", i);
                    enterListData(Discount2.get(j), dataFile, "Items", "Discount2", i);
                    enterListData(DiscountBasis3.get(j), dataFile, "Items", "DiscountBasis3", i);
                    enterListData(Discount3.get(j), dataFile, "Items", "Discount3", i);
                    enterListData(HSNCode.get(j), dataFile, "Items", "HSN", i);
                    enterListData(GSTProductCategory.get(j), dataFile, "Items", "GSTProductCategory", i);
                    enterListData(CESSProductCategory.get(j), dataFile, "Items", "CESSProductCategory", i);
                    enterListData(reason.get(j), dataFile, "Items", "Reason", i);
                    enterListData(Department.get(j), dataFile, "Items", "Department", i);
                    enterListData(Project.get(j), dataFile, "Items", "Project", i);
                    enterListData(ProfitCentre.get(j), dataFile, "Items", "ProfitCentre", i);
                    enterListData(CostCentre.get(j), dataFile, "Items", "CostCentre", i);
                    enterListData(Comments.get(j), dataFile, "Items", "Comments", i);
                    enterListData(Info1.get(j), dataFile, "Items", "Info1", i);
                    enterListData(Info2.get(j), dataFile, "Items", "Info2", i);
                    enterListData(Info3.get(j), dataFile, "Items", "Info3", i);
                    enterListData(Info4.get(j), dataFile, "Items", "Info4", i);
                    enterListData(Info5.get(j), dataFile, "Items", "Info5", i);
                    enterListData(Value1.get(j), dataFile, "Items", "Value1", i);
                    enterListData(Value2.get(j), dataFile, "Items", "Value2", i);
                    enterListData(Value3.get(j), dataFile, "Items", "Value3", i);
                    enterListData(Value4.get(j), dataFile, "Items", "Value4", i);
                    enterListData(Value5.get(j), dataFile, "Items", "Value5", i);
                    common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 38, 0);
                    enterListDate(Date1.get(j), dataFile, "Items", "Date1", i);
                    enterListDate(Date2.get(j), dataFile, "Items", "Date2", i);
                    enterListDate(Date3.get(j), dataFile, "Items", "Date3", i);
                    common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 70, 0);
                    clickListData(Bool1.get(j));
                    clickListData(Bool2.get(j));
                    clickListData(Bool3.get(j));
                    common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -1150, 0);
                    usedAppRows.add(i);
                    break; // go to next Excel row once matched
                }
            }
        }

    }

    public void addChargesAndDeductions() throws IOException {
        navigateToChargesAndDeductionsTab();
        List<String> chargesAndDeductions=readExcelData(dataFile,"ChargesAndDeductions","ChargesOrDeductions");
//        System.out.println("productCodes :"+chargesAndDeductions.size());
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

    public void addOtherCosts() throws IOException, InterruptedException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Other Costs ')]");
        System.out.println(elements.get(0).getText());
        elements.get(0).click();
        List<String> otherCosts =readExcelData(dataFile,"OtherCosts","ExpenseTypeCode");
        for (int i = 0; i < otherCosts.size() ; i++) {
            addData("xpath","//Edit[@Name='Expense Type Code Row "+i+", Not sorted.']",dataFile,"OtherCosts","ExpenseTypeCode",i);
        }
        List<WebElement> vendorCode = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Vendor Code Row ')]");
        List<WebElement> vendorRow = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Vendor Row ')]");
        List<WebElement> otherCostRow = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[contains(@Name,'Other Cost * Row ')]");
        List<WebElement> departmentRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Department Row ')]");
        List<WebElement> projectRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Project Row ')]");
        List<WebElement> costCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Cost Centre Row ')]");
        List<WebElement> profitCentreRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Profit Centre Row ')]");
        List<WebElement> commentsRowList = common.findWebElements("xpath", "//Table[@Name='OtherCosts']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Comments Row ')]");

        for (int i = 0; i < otherCosts.size(); i++) {
            enterListData(vendorCode.get(i), dataFile, "OtherCosts", "VendorCode",i);
            enterListData(vendorRow.get(i), dataFile, "OtherCosts", "Vendor",i);
            enterListData(otherCostRow.get(i),dataFile,"OtherCosts","OtherCost",i);
            enterListData(departmentRowList.get(i), dataFile, "OtherCosts", "Department", i);
            enterListData(projectRowList.get(i), dataFile, "OtherCosts", "Project", i);
            enterListData(profitCentreRowList.get(i), dataFile, "OtherCosts", "ProfitCentre", i);
            enterListData(costCentreRowList.get(i), dataFile, "OtherCosts", "CostCentre" ,i);
            enterListData(commentsRowList.get(i), dataFile, "OtherCosts", "Comments" , i);
        }
    }

    public void otherInfo() throws InterruptedException, IOException {
        navigateToOtherInfoTab();
        EnterData("//Edit[@Name='Reference Bill No']",dataFile,"OtherInfo","ReferenceBillNo");
        Thread.sleep(5000);
//        common.clickElement("xpath","//Window/Button[@Name='OK']");
        EnterDate("//Edit[@Name='Reference Bill Date']",dataFile,"OtherInfo","ReferenceBillDate");
        EnterData("//Edit[@Name='Other Info 1']",dataFile,"OtherInfo","OtherInfo1");
        EnterData("//Edit[@Name='Other Info 2']",dataFile,"OtherInfo","OtherInfo2");
        EnterData("//Edit[@Name='Other Info 3']",dataFile,"OtherInfo","OtherInfo3");
        EnterData("//Edit[@Name='Other Info 4']",dataFile,"OtherInfo","OtherInfo4");
        EnterData("//Edit[@Name='Other Info 5']",dataFile,"OtherInfo","OtherInfo5");
    }

    public void  additionalInformation() throws IOException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Additional Information  ')]");
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