package com.wings.pages.purchase.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class PVAPO_PRWIR_UnRegInterInclusive extends Transaction {

    WindowsDriver driver;
    Common common;
    String dataFile;
    boolean gstAmountClicked=false;
    public PVAPO_PRWIR_UnRegInterInclusive(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void pvapo_PRWIR_UnRegIntraInclusive(String voucherNum) throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps(common.getData(dataFile,"PVAPO_PRWIR UNRegInter","menu"),common.getData(dataFile,"PVAPO_PRWIR UNRegInter","menuItem"), common.getData(dataFile,"PVAPO_PRWIR UNRegInter","subMenuItem"));
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        System.out.println("oldID: "+ oldVoucherID);
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"Purchase Order","branch");
        inputTextWithValidation("xpath", "//Edit[@Name='Purchase VNo *']",voucherNum);
        enterInput("xpath","//Edit[@Name='Purchase Return A/c Code']",dataFile,"PVAPO_PRWIR UNRegInter","purchaseReturnActCode");
        enableCheckboxSelection("//CheckBox[@Name='Apply TCS']");
        enterInput("xpath","//Edit[@Name='TCS Trans Nature']", dataFile,"PVAPO_PRWIR UNRegInter","tcsNature");

        List<WebElement> items = common.findWebElements("xpath", "//Pane[@Name='  F3 Items  ']/Pane/Pane/Pane/Table[@Name='Items']/*[starts-with(@Name,'Row')]");
        System.out.println("items size: "+items.size());
        for (int i = 0; i < items.size(); i++) {
            WebElement productList = items.get(i);
            String value = productList.getAttribute("LegacyValue");
            if (!"(null)".equals(value) && !"(Create New)".equals(value)){
                String pendingQty = common.findWebElement("xpath", "//Edit[@Name='Pending Quantity Row "+i+", Not sorted.']").getText();
                WebElement quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']");
                quantity.click();
                quantity.sendKeys(pendingQty, Keys.TAB);
                common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", -500, 0);

            }
        }
        common.deleteInvalidRows();
        double itemsNetValue = Double.parseDouble(common.findWebElement("xpath", "//Edit[@AutomationId='NetAmount']").getText().replace(",", ""));
        System.out.println("Items NetAmount :- " + itemsNetValue);
        common.deleteInvalidRows();
        tcsCalculations(itemsNetValue);
        navigateToBillsPayablesTab();
        common.deleteInvalidRows();
        navigateToSummaryTab();
        quantityPresentInSummary();
        freeQuantityPresentInSummary();
        grossAmountPresentInSummary();
        grossMinusDiscountPresentInSummary();
        netAmountPresentInSummary();
        tcsAmountPresentInSummary();
        tcsTaxableValuePresentInSummary();
        totalValuePresentInSummary();
        totalValueInCompanyCurrenyPresentInSummary();

        //save
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
        verifyReport(newVoucherID,dataFile,"PVAPO_PRWIR UNRegInter");
        deleteSingleTransaction(newVoucherID);
    }
}
