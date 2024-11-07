package com.wings.pages;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import com.wings.utils.Common;
import java.io.IOException;
import java.util.List;

public class Transaction {
    WindowsDriver driver;
    Common common;

    public Transaction(WindowsDriver driver) {
        this.driver = driver;
        common = new Common(this.driver);
    }
    public void selectMaster(String transaction) {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table[@Name='Lookup']/*/*[contains(@Name,'Master Row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().contains(transaction)) {
                i.click();
                i.sendKeys(Keys.TAB);
            }
        }
    }

    public void selectAndValidateData(String transaction,String locatorType,String locator) {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table[@Name='Lookup']/*/*[contains(@Name,'Master Row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement element : elementList) {
            System.out.println(element.getText());
            if (element.getText().contains(transaction)) {
                element.click();
                element.sendKeys(Keys.TAB);
            }
        }
        WebElement element = common.findWebElement(locatorType, locator);
        if (element.getText().equals(transaction)) {
            System.out.println("successfully selected/opened:- " + element.getText());

        } else {
            Assert.fail(element.getText() + " is not selected " + transaction);
        }
    }

    public void selectAndValidateDataNew(String transaction,String locatorType,String locator) {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table[@Name='Lookup']/*/*[contains(@Name,'Master Row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement element : elementList) {
            System.out.println("focused element:- " + element.getText());
            WebElement element1 = common.findWebElement(locatorType, locator);
            if (!element1.getText().equals(transaction)) {
                System.out.println("successfully selected/opened:- " + element1.getText());
                element.sendKeys(Keys.DOWN);
            }
            else {
                break;
            }
        }
        WebElement element = common.findWebElement(locatorType, locator);
        if (element.getText().equals(transaction)) {
            System.out.println("successfully selected/opened:- " + element.getText());

        } else {
            Assert.fail(element.getText() + " is not selected " + transaction);
        }
    }

    public void selectDropDown(String element){
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/*/*[contains(@Name,'MasterName row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().contains(element)) {
                i.click();
            }
        }
    }

    public void enterData(String locatorType, String locator,String fileName,String key) throws IOException, ParseException {
        List<WebElement> elementList = common.findWebElements(locatorType,locator);
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
//            System.out.println(i.getText());
            i.click();
            i.sendKeys(common.getData(fileName, key),Keys.TAB);
        }
    }

    public void enterDataAndValidate(String locatorType, String locator, String fileName, String key) throws IOException, ParseException {
        List<WebElement> elementList = common.findWebElements(locatorType,locator);
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
//            System.out.println(i.getText());
            i.click();
            i.sendKeys(common.getData(fileName, key), Keys.TAB);
        }
        WebElement element = common.findWebElement(locatorType, locator);
        if (element.getText().equals(common.getData(fileName,key))) {
            System.out.println("successfully selected/opened:- " + element.getText());

        } else {
            Assert.fail(element.getText() + "is not selected");
        }
    }

    public double billsReceivable(String locatorType,String rowLocator,String voucherLocator,String checkBoxLocator,String pendingAmountLocator) {
        double finalAmount = 0.0;
        List<WebElement> rows = common.findWebElements(locatorType, rowLocator);
        System.out.println("Row count :" + rows.size());

        for (WebElement row : rows) {
            WebElement voucher = row.findElement(By.xpath(voucherLocator));
            // Check if the voucher element is present and get its value
            String value = voucher.getText();
            System.out.println("Voucher value: " + value);
            if (value == (null) ||  "(null)".equals(value)) {
                if (rows.size() == 1) {
                    Assert.fail("No Accounts found in row: " + row.getAttribute("outerHTML"));
                }
                // If the value is null, throw an assertion error
                Assert.fail("No Bills Receivable/Payable found in row: " + row.getAttribute("outerHTML"));
            } else {
                // If the voucher has a valid value, find the checkbox and click it
                WebElement checkBox = row.findElement(By.xpath(checkBoxLocator));
                checkBox.click();
                System.out.println("Checkbox clicked for voucher: " + value);
            }
            // Locate the pending amount element within the same row
            WebElement pending = row.findElement(By.xpath(pendingAmountLocator));
            System.out.println("Pending amount: " + pending.getText());

            String pendingAmountText = pending.getText().trim();
            double pendingAmount = 0.0;

            // Parse the pending amount, removing commas if necessary
            pendingAmount = Double.parseDouble(pendingAmountText.replace(",", ""));
            // Add to the final amount
            finalAmount += pendingAmount;
        }

        // Print the final amount after iterating through all rows
        System.out.println("Final Amount: " + finalAmount);
        return finalAmount;
    }

    public void checkBoxSelection(String locatorType,String rowLocator,String voucherLocator,String checkBoxLocator) {
        List<WebElement> rows = common.findWebElements(locatorType, rowLocator);
        System.out.println("Row count :" + rows.size());
        for (WebElement row : rows) {
            WebElement voucher = row.findElement(By.xpath(voucherLocator));
            String value = voucher.getText();
            System.out.println("Voucher value: " + value);
            if (value == (null) || "(null)".equals(value)) {                // value.isEmpty() ||
                // If the value is null, throw an assertion error
                if (rows.size() == 1) {
                    Assert.fail("No Accounts found in row: " + row.getAttribute("outerHTML"));
                }
            }else {
                // If the voucher has a valid value, find the checkbox and click it
                WebElement checkBox = row.findElement(By.xpath(checkBoxLocator));
                checkBox.click();
                System.out.println("Checkbox clicked for voucher: " + value);
            }
        }
    }

    public void validateElements(String locatorType,String locator, String existingValue){
        WebElement element = common.findWebElement(locatorType, locator);
        if (element.getText().equals(existingValue)) {
            System.out.println("successfully selected/opened:- " + element.getText());

        } else {
            Assert.fail(element.getText() + "is not seleced");
        }
    }

    public void gstTransactionType(String gstType){
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/*/*[contains(@Name,'GST Transaction Type row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
//                System.out.println(i.getText());
            if (i.getText().contains(gstType)) {
                i.click();
                i.sendKeys(Keys.LEFT, Keys.SPACE,Keys.ENTER,Keys.ENTER);
                break;
            }
        }
    }

    public void intraGSTRegistration(String gstType){
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/*/*[contains(@Name,'GST Transaction Type row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
//                System.out.println(i.getText());
            if (i.getText().equals(gstType)) {
                i.click();
                i.sendKeys(Keys.LEFT, Keys.SPACE,Keys.ENTER,Keys.ENTER);
                break;
            }
        }
    }

    public void gstTransactionTypeNew(String gstType) {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/*/*[contains(@Name,'GST Transaction Type row')]");
        System.out.println("Size :" + elementList.size());
        for (int index = 0; index < elementList.size(); index++) {
            // Re-fetch the elements to avoid StaleElementReferenceException
            elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/*/*[contains(@Name,'GST Transaction Type row')]");
            WebElement i = elementList.get(index);
            if (i.getText().contains(gstType)) {
                i.click();
                i.sendKeys(Keys.LEFT, Keys.SPACE, Keys.ENTER, Keys.ENTER);
                break; // Exit the loop once the match is found
            }
        }
    }

    public void selectOptionalMaster(String transaction, String locatorType, String locator) {
        if (transaction != null) {
            List<WebElement> elementList = common.findWebElements("xpath", "//Table[@Name='Lookup']/*/*[contains(@Name,'Master Row')]");
            System.out.println("Size :" + elementList.size());
            for (WebElement j : elementList) {
                if (j.getText().contains(transaction)) {
                    System.out.println(j.getText());
                    j.click();
                    j.sendKeys(Keys.ENTER);
                }
            }
            WebElement ele = common.findWebElement(locatorType, locator);
            if (ele.getText().equals(transaction)) {
                System.out.println("element Selected :" + ele.getText());
            } else {
                Assert.fail("pls select the element");
                System.out.println("commit to bitbucket");
            }
        }
    }

    public double singleCheckBoxSelection(String locatorType,String rowLocator,String voucherLocator){
        List<WebElement> rows = common.findWebElements(locatorType, rowLocator);
        System.out.println("Row count :" + rows.size());
        for (WebElement row : rows) {
            WebElement voucher = row.findElement(By.xpath(voucherLocator));
            String value = voucher.getText();
            System.out.println("Voucher value: " + value);
            if (value == (null) || "(null)".equals(value)) {
                // value.isEmpty() ||
                // If the value is null, throw an assertion error
                if (rows.size() == 1) {
                    Assert.fail("No Accounts found in row: " + row.getAttribute("outerHTML"));
                }
            }else {
                // If the voucher has a valid value, find the checkbox and click it
                common.clickElement("xpath","//CheckBox[@Name='Adjust Row 0']");
                common.deleteInvalidRows();
                System.out.println("Checkbox clicked for voucher: " + value);
                break;
            }
        }
        double finalAmount=0.0;
        WebElement pending= common.findWebElement("xpath","//Edit[@Name='Amount Adjusted * Row 0, Not sorted.']");
        System.out.println("Pending amount: " + pending.getText());
        String pendingAmountText = pending.getText().trim();
        finalAmount=  Double.parseDouble(pendingAmountText.replace(",", ""));
        return finalAmount;
    }

    public void navigateToSummaryTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Summary')]");
    }
    public void navigateToBillsPayablesTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Bills Payable')]");
    }
    public void navigateToBillsReceivablesTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Bills Receivable')]");
    }
    public void navigateToAccountsTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Accounts')]");
    }
    public void navigateToEinvoiceTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'E-Invoice')]");
    }
    public void navigateToTDSTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'TDS')]");
    }
    public void navigateToTCSTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'TCS')]");
    }
    public void navigateToBillingAdressTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Billing Address')]");
    }
    public void navigateToShippingAddressTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Shipping Address')]");
    }
    public void navigateToInvoiceDetailsTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Invoice Details')]");
    }
    public void navigateToCashTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Cash')]");
    }
    public void navigateToCreditCardCompanyChargesTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Credit Card Company Charges')]");
    }
    public void navigateToBankTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Bank')]");
    }
    public void navigateToIncomesTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Incomes')]");
    }
    public void navigateToExpensesTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Expenses')]");
    }
    public void navigateToPaytymTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Paytm')]");
    }
    public void navigateToInputsTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Inputs')]");
    }
    public void navigateToOutputsTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Out puts')]");
    }
    public void navigateToBatchDetailsTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Batch Details')]");
    }
    public void navigateToPartiesTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Parties')]");
    }
    public void navigateToOtherInfoTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Other Info')]");
    }
    public void navigateToAllocationsTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Allocations  ')]");
    }
    public void navigateToSerialNoTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Serial Nos')]");
    }
    public void navigateToEwayBillTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'E Way Bill Details')]");
    }
    public void navigateToToLocationDetailsTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'To Location Details')]");
    }
    public void navigateToTermsAndConditionsTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Terms And Conditions')]");
    }
    public void navigateToOtherChargesTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Other Charges')]");
    }
    public void navigateToChargesAndDeductionsTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Charges And Deductions')]");
    }
    public void navigateToItemsTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Items')]");
    }





    public void transactionSave() throws InterruptedException {
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='Yes']");
        Thread.sleep(2500);
        common.clickElement("xpath","//Window[@Name='Transaction saved.']/Button[@Name='OK']");
    }
    public void lastTransactionName(){
        System.out.println(common.findWebElement("xpath","//Text[@Name='Last Saved :']/following-sibling::Text").getAttribute("Name"));
    }
    public void transactionClose(String screenName){
        common.clickElement("xpath", "//TabItem[@Name='"+ screenName +"']/Button[@Name='Close']");

    }

    public void closeTransaction (String transaction ){
        common.clickElement("xpath", "//TabItem[@Name='" + transaction + "']/Button[@Name='Close']");
    }

    public void oldTTransaction () {
        System.out.println("Recent Transaction Id :" + common.findWebElement("xpath", "//Text[@Name='Last Saved :']/following-sibling::Text").getAttribute("Name"));
    }

    public void newTransaction () {
        System.out.println("New Transaction ID  :" + common.findWebElement("xpath", "//Text[@Name='Last Saved :']/following-sibling::Text").getAttribute("Name"));
    }

    public void saveTransaction () throws InterruptedException {
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Button[@Name='OK']");
    }

    public void selectMasterWithValidation(String transaction, String locatorType, String locator) {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table[@Name='Lookup']/*/*[contains(@Name,'Master Row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement j : elementList) {
            if (j.getText().contains(transaction)) {
                System.out.println(j.getText());
                j.click();
                j.sendKeys(Keys.ENTER);
            }
        }
        WebElement ele = common.findWebElement(locatorType, locator);
        if (ele.getText().equals(transaction)) {
            System.out.println("element Selected :" + ele.getText());
        } else {
            Assert.fail("pls select the element");
        }
    }

    public void inputTextWithValidation(String locatorType,String locator,String inputText) {
        WebElement element = common.findWebElement(locatorType, locator);
        element.sendKeys(inputText);
        System.out.println(element.getText());
        if (element.getText().equals(inputText)) {
            System.out.println("entered currect Input :" + element.getText());
        } else {
            Assert.fail("wrong input");
        }
    }

    public void sliderHandle () {
        int offset = 550;
        WebElement slider = common.findWebElement("xpath", "//Table[@Name='Items']/ScrollBar[@Name='Horizontal']/Thumb[@Name='Position']");
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(offset, 0).release().perform();
    }

    public void gstSelectionWhenBothRegisteredDealers() {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/*/*[contains(@Name,'GST Transaction Type row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement j : elementList) {
            System.out.println(j.getText());
            if (j.getText().equals("Intra State Purchase from Registered Dealers")) {
                j.click();
                j.sendKeys(Keys.LEFT, Keys.SPACE, Keys.ENTER, Keys.ENTER);
                break;
            }
        }
    }

    public void partyCodeGstSelection () {
        List<WebElement> elementList1 = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/*/*[contains(@Name,'GST Transaction Type row')]");
        System.out.println("Size :" + elementList1.size());
        for (WebElement j : elementList1) {
            System.out.println(j.getText());
            if (j.getText().contains("Registered Dealers")) {
                j.click();
                j.sendKeys(Keys.LEFT, Keys.SPACE, Keys.ENTER, Keys.ENTER);
                break;
            }
        }
    }


    public void generalInfoSliderHandle () {
        int offset = 800;
        WebElement slider = common.findWebElement("xpath", "//ScrollBar[@Name='Horizontal']/Thumb[@Name='Position']");
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(offset, 0).release().perform();
    }
    public void genaralInfoNegativeSliderHandle () {
        int offset = -500;
        WebElement slider = common.findWebElement("xpath", "//ScrollBar[@Name='Horizontal']/Thumb[@Name='Position']");
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(offset, 0).release().perform();
    }

    public void checkBoxSelectionBillsReceivables (String locatorType, String rowLocator, String voucherLocator, String checkBoxLocator){
        List<WebElement> rows = common.findWebElements(locatorType, rowLocator);
        System.out.println("Row count :" + rows.size());
        for (WebElement row : rows) {
            WebElement voucher = row.findElement(By.xpath(voucherLocator));
            String value = voucher.getText();
            System.out.println("Voucher value: " + value);
            if (value == (null) || "(null)".equals(value)) {
                // If the value is null, throw an assertion error// value.isEmpty() ||
                if (rows.size() == 1) {
                    Assert.fail("No Accounts found in row: " + row.getAttribute("outerHTML"));
                }
            } else {
                // If the voucher has a valid value, find the checkbox and click it
                WebElement checkBox = row.findElement(By.xpath(checkBoxLocator));
                checkBox.click();
                System.out.println("Checkbox clicked for voucher: " + value);
            }
        }
    }


    public void checkBoxSelectionBillsPayable (String locatorType, String rowLocator, String voucherLocator, String checkBoxLocator){
        List<WebElement> rows = common.findWebElements(locatorType, rowLocator);
        System.out.println("Row count :" + rows.size());
        for (WebElement row : rows) {
            WebElement voucher = row.findElement(By.xpath(voucherLocator));
            String value = voucher.getText();
            System.out.println("Voucher value: " + value);
            if (value == (null) || "(null)".equals(value)) {
                // If the value is null, throw an assertion error// value.isEmpty() ||
                if (rows.size() == 1) {
                    Assert.fail("No Accounts found in row: " + row.getAttribute("outerHTML"));
                }
            } else {
                // If the voucher has a valid value, find the checkbox and click it
                WebElement checkBox = row.findElement(By.xpath(checkBoxLocator));
                checkBox.click();
                System.out.println("Checkbox clicked for voucher: " + value);
            }
        }
    }

}
