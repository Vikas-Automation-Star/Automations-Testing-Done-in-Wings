package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PRWIR_RegInterRCMInclusive extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PRWIR_RegInterRCMInclusive(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void regPrwirInclusive(String voucherNum) throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps(common.getData(dataFile,"Invoice Reference","menu"),common.getData(dataFile,"Invoice Reference","menuItem"), common.getData(dataFile,"Invoice Reference","subMenuItem"));
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"Purchase Voucher","branch");
//        enterInput("xpath","//Edit[@Name='Location *']",dataFile,"location");
//        enterInput("xpath","//Edit[@Name='Trans Currency *']",dataFile,"currency")
        inputTextWithValidation("xpath", "//Edit[@Name='Purchase VNo *']",voucherNum);
        enterInput("xpath","//Edit[@Name='Purchase Return A/c Code']",dataFile,"Invoice Reference","purchaseReturnActCode");
        enableCheckboxSelection("//CheckBox[@Name='Apply TCS']");
        enterInput("xpath","//Edit[@Name='TCS Trans Nature']", dataFile,"Purchase Voucher","tcsNature");
        // Product codes to search for
        String productCode1 = common.getData(dataFile, "Purchase Voucher", "productCode0");
        String productCode2 = common.getData(dataFile, "Purchase Voucher", "productCode1");
        java.util.List<String> productCodes = Arrays.asList(productCode1, productCode2);
        List<WebElement> items = common.findWebElements("xpath", "//Pane[@Name='  F3 Items  ']/Pane/Pane/Pane/Table[@Name='Items']/*[starts-with(@Name,'Row')]");
        System.out.println("items size :"+items.size());
        Set<String> processedCodes = new HashSet<>();
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 400, 0);
        for (int i = 0; i < items.size(); i++) {
            WebElement productList = items.get(i);
            System.out.println("productList :"+productList.getText());
            String value = productList.getAttribute("LegacyValue");
            System.out.println("LValue :"+value);
            for (String productCode : productCodes) {
                if (value != null && value.contains(productCode) && !processedCodes.contains(productCode)) {
                    if (processedCodes.isEmpty()) {
                        // First matched product - full quantity and free quantity
                        String pendingQty = common.findWebElement("xpath", "//Edit[@Name='Pending Quantity In SKU Row " + i + ", Not sorted.']").getText();
                        WebElement quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']");
                        quantity.click();
                        quantity.sendKeys(pendingQty, Keys.TAB);

                        String pendingFreeQuantity = common.findWebElement("xpath", "//Edit[@Name='Pending Free Quantity In SKU Row " + i + ", Not sorted.']").getText();
                        WebElement freeQuantity = common.findWebElement("xpath", "//Edit[@Name='Free Quantity Row " + i + ", Not sorted.']");
                        freeQuantity.click();
                        freeQuantity.sendKeys(pendingFreeQuantity, Keys.TAB);
                    }
                    processedCodes.add(productCode);
                }
            }
            // Stop if both product codes have been processed
            if (processedCodes.size() == productCodes.size()) {
                break;
            }
        }

        common.deleteInvalidRows();
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 700, 0);
        double netValue=Double.parseDouble(common.findWebElement("xpath","//Edit[@AutomationId='NetAmount']").getText().replace(",",""));
        tcsCalculations(netValue);
        navigateToBillsPayablesTab();
        common.deleteInvalidRows();

        navigateToSummaryTab();
        quantityPresentInSummary();
        freeQuantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        cgstPresentInSummary();
        sgstPresentInSummary();
        cessPresentInSummary();
        netAmountPresentInSummary();
        tcsAmountPresentInSummary();
        tcsTaxableValuePresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();

        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID).replace(" ","");
        System.out.println("newID: "+newVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
        Thread.sleep(1000);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Returns with Invoice Reference'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
        Thread.sleep(1500);
        verifyReport(newVoucherID,dataFile,"Invoice Reference");
//        deleteSingleTransaction(newVoucherID);
    }
}
