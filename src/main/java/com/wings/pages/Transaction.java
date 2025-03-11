package com.wings.pages;

import com.wings.utils.Common;
import com.wings.utils.StringUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public abstract class Transaction {
    protected WindowsDriver driver;
    protected Common common;

    public Transaction(WindowsDriver driver) {
        this.driver = driver;
        this.common = new Common(this.driver);
    }
//public class Transaction {
//    WindowsDriver driver;
//    Common common;
//
//    public Transaction(WindowsDriver driver) {
//        this.driver = driver;
//        common = new Common(this.driver);
//    }


    //data input or output methods
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

    public void selectAndValidateData(String transaction, String locatorType, String locator) {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table[@Name='Lookup']/*/*[contains(@Name,'Master Row')]");
//        System.out.println("Size :" + elementList.size());
        for (WebElement element : elementList) {
//            System.out.println(element.getText());
            if (element.getText().contains(transaction)) {
                element.click();
                element.sendKeys(Keys.TAB);
            }
        }
        WebElement element = common.findWebElement(locatorType, locator);
        if (element.getText().equals(transaction)) {
//            System.out.println("successfully selected/opened:- " + element.getText());

        } else {
            Assert.fail(element.getText() + " is not selected " + transaction);
        }
    }

    public void selectAndValidateDataNew(String transaction, String locatorType, String locator) {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table[@Name='Lookup']/*/*[contains(@Name,'Master Row')]");
//        System.out.println("Size :" + elementList.size());
        for (WebElement element : elementList) {
//            System.out.println("focused element:- " + element.getText());
            WebElement element1 = common.findWebElement(locatorType, locator);
            if (!element1.getText().equals(transaction)) {
//                System.out.println("successfully selected/opened:- " + element1.getText());
                element.sendKeys(Keys.DOWN);
            } else {
                element.sendKeys(Keys.ENTER);
                break;
            }
        }
    }

    public void selectDropDown(String element) {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/*/*[contains(@Name,'MasterName row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().contains(element)) {
                i.click();
            }
        }
    }

    public void enterData(String locatorType, String locator, String fileName, String key) throws IOException, ParseException {
        List<WebElement> elementList = common.findWebElements(locatorType, locator);
//        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            i.click();
            i.sendKeys(common.getData(fileName, key), Keys.TAB);
            break;
        }
    }

    public void enterInput(String locatorType, String locator, String fileName, String key) throws IOException, ParseException {
        List<WebElement> elementList = common.findWebElements(locatorType, locator);
        for (WebElement i : elementList) {
            i.click();
            i.sendKeys(Keys.CONTROL + "a");
            i.sendKeys(Keys.BACK_SPACE);
            i.sendKeys(common.getData(fileName, key), Keys.TAB);
            break;
        }
    }

    public void enterDataAndValidate(String locatorType, String locator, String fileName, String key) throws IOException, ParseException {
        List<WebElement> elementList = common.findWebElements(locatorType, locator);
//        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
//            System.out.println(i.getText());
            i.click();
            i.sendKeys(common.getData(fileName, key), Keys.TAB);
        }
        WebElement element = common.findWebElement(locatorType, locator);
        if (element.getText().equals(common.getData(fileName, key))) {
//            System.out.println("successfully selected/opened:- " + element.getText());

        } else {
            Assert.fail(element.getText() + "is not selected");
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
            }
        }
    }

    public void gstTransactionType(String gstType) {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/*/*[contains(@Name,'GST Transaction Type row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
//                System.out.println(i.getText());
            if (i.getText().contains(gstType)) {
                i.click();
                i.sendKeys(Keys.LEFT, Keys.SPACE, Keys.ENTER, Keys.ENTER);
                break;
            }
        }
    }

    public void intraGSTRegistration(String gstType) {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/*/*[contains(@Name,'GST Transaction Type row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
//                System.out.println(i.getText());
            if (i.getText().equals(gstType)) {
                i.click();
                i.sendKeys(Keys.LEFT, Keys.SPACE, Keys.ENTER, Keys.ENTER);
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

    public void invoiceTypeWhenRegister() throws InterruptedException, AWTException {
        common.clickElement("xpath", "//Edit[@Name='Invoice Type']");
        Thread.sleep(1000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
//        robot.keyPress(KeyEvent.VK_DOWN);
//        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public void selectMasterWithValidation(String transaction, String locatorType, String locator) {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table[@Name='Lookup']/*/*[contains(@Name,'Master Row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement j : elementList) {
            System.out.println(j.getText());
            if (j.getText().contains(transaction)) {
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

    public void inputTextWithValidation(String locatorType, String locator, String inputText) {
        WebElement element = common.findWebElement(locatorType, locator);
        element.sendKeys(inputText);
        System.out.println(element.getText());
        if (element.getText().equals(inputText)) {
            System.out.println("entered currect Input :" + element.getText());
        } else {
            Assert.fail("wrong input");
        }
    }

    public void enterBranch(String dataFile, String key) throws IOException, ParseException {
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, key);
    }

    public void enterPartyCode(String dataFile, String key) throws IOException, ParseException {
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, key);
    }

    public void enterPriceList(String dataFile, String key) throws IOException, ParseException {
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, key);
    }

    public void enterExecutive(String dataFile, String key) throws IOException, ParseException {
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, key);
    }

    //navigation methods
    public void navigateToSummaryTab() throws AWTException {
//        navigateToOtherInfoTab();
        navigateToPaytymTab();
        for (int j = 0; j < 6; j++) {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_RIGHT);
            robot.keyRelease(KeyEvent.VK_RIGHT);
        }
        common.clickElement("xpath", "//TabItem[contains(@Name,'Summary')]");
    }

    public void navigateToBillsPayablesTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Bills Payable')]");
    }

    public void navigateToBillsReceivablesTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Bills Receivable')]");
    }

    public void navigateToAccountsTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Accounts')]");
    }

    public void navigateToEinvoiceTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'E-Invoice')]");
    }

    public void navigateToTDSTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'TDS')]");
    }

    public void navigateToTCSTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'TCS')]");
    }

    public void navigateToBillingAdressTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Billing Address')]");
    }

    public void navigateToShippingAddressTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Shipping Address')]");
    }

    public void navigateToInvoiceDetailsTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Invoice Details')]");
    }

    public void navigateToCashTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Cash')]");
    }

    public void navigateToCreditCardCompanyChargesTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Credit Card Company Charges')]");
    }

    public void navigateToBankTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Bank')]");
    }

    public void navigateToIncomesTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Incomes')]");
    }

    public void navigateToExpensesTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Expenses')]");
    }

    public void navigateToPaytymTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Paytm')]");
    }

    public void navigateToInputsTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Inputs')]");
    }

    public void navigateToOutputsTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Out puts')]");
    }

    public void navigateToBatchDetailsTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Batch Details')]");
    }

    public void navigateToPartiesTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Parties')]");
    }

    public void navigateToOtherInfoTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Other Info')]");
    }

    public void navigateToAllocationsTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Allocations  ')]");
    }

    public void navigateToSerialNoTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Serial Nos')]");
    }

    public void navigateToEwayBillTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'E Way Bill Details')]");
    }

    public void navigateToToLocationDetailsTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'To Location Details')]");
    }

    public void navigateToTermsAndConditionsTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Terms And Conditions')]");
    }

    public void navigateToOtherChargesTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Other Charges')]");
    }

    public void navigateToChargesAndDeductionsTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Charges And Deductions')]");
    }

    public void navigateToItemsTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Items')]");
    }

    public void navigateToUnclearedPayments() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Uncleared Payments')]");
    }

    public void navigateToMastersOrMenus(String menuItemName, String masterName, String subMasterName) {
        common.clickElement("name", menuItemName);
        common.clickElement("name", masterName);
        common.clickElement("xpath", subMasterName);
    }

    public void navigateToSalesEnquiryMenu() throws InterruptedException {
        common.clickElement("name", "Sales");
        Thread.sleep(1500);
        common.clickElement("name", "Enquiries");
        common.clickElement("xpath", "//Menu[@Name='Enquiries']/MenuItem[@Name='Sales Enquiries']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Enquiries']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Sales Enquiries");
    }

    public void navigateToSalesEnquiryCancellationMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Enquiries");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Enquiries Cancellation']");
        Assert.assertEquals(common.findWebElement("xpath", "//Pane/Text[@Name='Sales Enquiries Cancellation']").getText(), "Sales Enquiries Cancellation");
    }

    public void navigateToSalesQuotationsMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Quotations");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Quotations']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Quotations']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Sales Quotations");
    }

    public void navigateToSalesQuotationsCancellationMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Quotations");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Quotations Cancellations']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Quotations Cancellations']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Sales Quotations Cancellations");
    }

    public void navigateToSalesQuotationAgainstEnquiryMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Quotations");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Quotations against Enquiries']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Quotations against Enquiries']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Sales Quotations against Enquiries");
    }

    public void navigateToSalesOrderAgainstQuotationsMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Orders against Quotations']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Orders against Quotations']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Sales Orders against Quotations");
    }

    public void navigateToSalesOrderMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Orders']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Orders']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Sales Orders");
    }

    public void navigateToSalesOrderCancellaltionMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Orders Cancellation']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Orders Cancellation']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Sales Orders Cancellation");
    }

    public void navigateToDeliveriesMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Deliveries");
        common.clickElement("xpath", "//MenuItem[@Name='Deliveries']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Deliveries']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Deliveries");
    }

    public void navigateToDeliveriesAgainstOrdersMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Deliveries");
        common.clickElement("xpath", "//MenuItem[@Name='Deliveries against Orders']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Deliveries against Orders']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Deliveries against Orders");
    }

    public void navigateToDeliveryReturnsMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Deliveries");
        common.clickElement("xpath", "//MenuItem[@Name='Delivery Returns']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Delivery Returns']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Delivery Returns");
    }

    public void navigateToSalesInvoiceMenu() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Invoices']");
        Thread.sleep(3000);
//        String validate = common.findWebElement("xpath", "//Text[@Name='Sales Invoices']").getText();
//        System.out.println("Screen Name:-" + validate);
//        Assert.assertEquals(validate, "Sales Invoices");
    }

    public void navigateToSalesInvoiceAgainstDeliveriesMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Invoices against Deliveries']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Invoices against Deliveries']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Sales Invoices against Deliveries");
    }

    public void navigateToSalesInvoiceAgainstOrdersMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Invoices against Orders']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Invoices against Orders']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Sales Invoices against Orders");
    }

    public void navigateToProformaSalesInvoiceMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Proforma Sales Invoices']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Proforma Sales Invoices']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Proforma Sales Invoices");
    }

    public void navigateToSalesReturnMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Returns']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Returns']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Sales Returns");
    }

    public void navigateToSalesReturnWithInvoiceReferenceMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Return with Invoice Reference']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Return with Invoice Reference']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Sales Return with Invoice Reference");
    }

    public void navigateToSalesTargetExecutiveWiseMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Targets");
        common.clickElement("name", "Define Sales Targets-Executive Wise");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Define Sales Targets-Executive Wise']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Define Sales Targets-Executive Wise");
    }

    public void navigateToPartyProductwiseDiscountMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("xpath", "//MenuItem[@Name='Prices and Discounts'][2]");
        common.clickElement("name", "Party and Product wise Discounts");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Party and Product wise Discounts']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Party and Product wise Discounts");
    }

    public void navigateToSalesPricesMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("xpath", "//MenuItem[@Name='Prices and Discounts'][2]");
        common.clickElement("name", "Sales Prices");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Prices']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Sales Prices");
    }

    public void navigateToInterLocationTransfersMenu() {
        common.clickElement("name", "Inventory");
        common.clickElement("xpath", "//MenuItem[@Name='Inter Location Transfers']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Inter Location Transfers']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Inter Location Transfers");
    }

    public void navigateToOpeningStockMenu() {
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Opening Stock");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Opening Stock']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Opening Stock");
    }

    public void navigateToStockConsumptionMenu() {
        common.clickElement("name", "Inventory");
        common.clickElement("xpath", "//MenuItem[@Name='Stock Consumption']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Stock Consumption']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Stock Consumption");
    }

    public void navigateToStockConversionMenu() {
        common.clickElement("name", "Inventory");
        common.clickElement("xpath", "//MenuItem[@Name='Stock Conversion']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Stock Conversion']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Stock Conversion");
    }

    public void navigateToStockCreationMenu() {
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Stock Creation");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Stock Creation']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Stock Creation");
    }

    public void navigateToBankReceiptsMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Bank Receipts']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Bank Receipts']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Bank Receipts");
    }

    public void navigateToCashReceiptsMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Cash Receipts']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Cash Receipts']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Cash Receipts");
    }

    public void navigateToCreditCardReceiptsMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Credit Card Receipts']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Credit Card Receipts']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Credit Card Receipts");
    }

    public void navigateToReceiptsFromCreditCardCompanyMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Receipts from Credit Card Companies']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Receipts from Credit Card Companies']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Receipts from Credit Card Companies");
    }

    public void navigateToReceiptsFromPartiesMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Receipts from Parties']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Receipts from Parties']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Receipts from Parties");
    }

    public void navigateToBankPaymentMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Payments");
        common.clickElement("xpath", "//MenuItem[@Name='Bank Payments']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Bank Payments']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Bank Payments");
    }

    public void navigateToCashPaymentsMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Payments");
        common.clickElement("xpath", "//MenuItem[@Name='Cash Payments']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Cash Payments']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Cash Payments");
    }

    public void navigateToCashTransfersMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Payments");
        common.clickElement("xpath", "//MenuItem[@Name='Cash Transfers']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Cash Transfers']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Cash Transfers");
    }

    public void navigateToPaymentToPartiesMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Payments");
        common.clickElement("xpath", "//MenuItem[@Name='Payments to Parties']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Payments to Parties']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Payments to Parties");
    }

    public void navigateToBookExpensesOrPayablesMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Journals");
        common.clickElement("xpath", "//MenuItem[@Name='Book Expenses or Payables']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Book Expenses or Payables']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Book Expenses or Payables");
    }

    public void navigateToBookIncomesOrReceivablesMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Journals");
        common.clickElement("xpath", "//MenuItem[@Name='Book Incomes or Receivables']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Book Incomes or Receivables']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Book Incomes or Receivables");
    }

    public void navigateToBookingOfOtherCosts() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Journals");
        common.clickElement("xpath", "//MenuItem[@Name='Booking Of Other Costs']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Booking Of Other Costs']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Booking Of Other Costs");
    }

    public void navigateToJournalEntriesMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Journals");
        common.clickElement("xpath", "//MenuItem[@Name='Journal Entries']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Journal Entries']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Journal Entries");
    }

    public void navigateToManualStockVerificationMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Opening Balances");
        common.clickElement("xpath", "//MenuItem[@Name='Manual Stock Valuation']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Manual Stock Valuation']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Manual Stock Valuation");
    }

    public void navigateToOpeningBalancesMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Opening Balances");
        common.clickElement("xpath", "//MenuItem[@Name='Opening Balances']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Opening Balances']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Opening Balances");
    }

    public void navigateToOpeningReceiptsFromCreditCardCompanyMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Opening Balances");
        common.clickElement("xpath", "//MenuItem[@Name='Opening Receipts from Credit Card Companies']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Opening Receipts from Credit Card Companies']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Opening Receipts from Credit Card Companies");
    }

    public void navigateToPartyOpeningBalancesMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Opening Balances");
        common.clickElement("xpath", "//MenuItem[@Name='Party Opening Balances']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Party Opening Balances']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Party Opening Balances");
    }

    public void navigateToTransferIncomesAndExpensesToPLMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Opening Balances");
        common.clickElement("xpath", "//MenuItem[@Name='Transfer Incomes and Expenses to PL']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Transfer Incomes and Expenses to PL']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Transfer Incomes and Expenses to PL");
    }

    public void navigateToBankReconciliationMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Bank Reconciliation']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Bank Reconciliation']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Bank Reconciliation");
    }

    public void navigateToCashDepositsAndWithdrawalsMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Cash Deposits and withdrawals']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Cash Deposits and withdrawals']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Cash Deposits and withdrawals");
    }

    public void navigateToDepositPostDatedChequesMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Deposit Post Dated Cheques']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Deposit Post Dated Cheques']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Deposit Post Dated Cheques");
    }

    public void navigateToInterBankFundTransferMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Inter Bank Fund Transfers']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Inter Bank Fund Transfers']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Inter Bank Fund Transfers");
    }

    public void navigateToOpeningUnclearedBankEntriesMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Opening Uncleared Bank Entries']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Opening Uncleared Bank Entries']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Opening Uncleared Bank Entries");
    }

    public void navigateToReceivedChequesBounceMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Received Cheques Bounce']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Received Cheques Bounce']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Received Cheques Bounce");
    }

    public void navigateToAdjustPartyBills() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Party Adjustments");
        common.clickElement("xpath", "//MenuItem[@Name='Adjust Party Bills']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Adjust Party Bills']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Adjust Party Bills");
    }

    public void navigateToCreditNoteFromSupplierMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Party Adjustments");
        common.clickElement("xpath", "//MenuItem[@Name='Credit Note from Suppliers']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Credit Note from Suppliers']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Credit Note from Suppliers");
    }

    public void navigateToCreditNoteOnCustomerMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Party Adjustments");
        common.clickElement("xpath", "//MenuItem[@Name='Credit Note on Customers']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Credit Note on Customers']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Credit Note on Customers");
    }

    public void navigateToCreditNoteMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Party Adjustments");
        common.clickElement("xpath", "//MenuItem[@Name='Credit Note']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Credit Note']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Credit Note");
    }

    public void navigateToDebitNoteFromSupplierMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Party Adjustments");
        common.clickElement("xpath", "//MenuItem[@Name='Debit Note from Suppliers']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Debit Note from Suppliers']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Debit Note from Suppliers");
    }

    public void navigateToDebitNoteOnCustomerMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Party Adjustments");
        common.clickElement("xpath", "//MenuItem[@Name='Debit Note on Customers']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Debit Note on Customers']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Debit Note on Customers");
    }

    public void navigateToDebitNoteMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Party Adjustments");
        common.clickElement("xpath", "//MenuItem[@Name='Debit Note']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Debit Note']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Debit Note");
    }

    public void navigateToPurchaseEnquiries() {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Enquiries");
        common.clickElement("xpath", "//Menu[@Name='Enquiries']/MenuItem[@Name='Purchase Enquiries']");
        String validate = common.findWebElement("xpath", "//Text[@Name='Purchase Enquiries']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Enquiries", validate);
    }

    public void navigateToPurchaseEnquiriesCancellation() {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Enquiries");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Enquiries Cancellation']");
        String validate = common.findWebElement("xpath", "//Text[@Name='Purchase Enquiries Cancellation']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Enquiries Cancellation", validate);
    }

    public void navigateToPurchaseQuotations() {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Quotations");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Quotations']");
        String validate = common.findWebElement("xpath", "//Text[@Name='Purchase Quotations']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Quotations", validate);
    }

    public void navigateToPurchaseQuotationsAgainstEnquiries() {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Quotations");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Quotations against Enquiries']");
        String validate = common.findWebElement("xpath", "//Text[@Name='Purchase Quotations against Enquiries']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Quotations against Enquiries", validate);
    }

    public void navigateToPurchaseOrdersAgainstQuotations() {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Orders against Quotations']");
        String validate = common.findWebElement("xpath", "//Text[@Name='Purchase Orders against Quotations']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Orders against Quotations", validate);
    }

    public void navigateToPurchaseOrders() {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Orders']");
        String validate = common.findWebElement("xpath", "//Text[@Name='Purchase Orders']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Orders", validate);
    }

    public void navigateToPurchaseOrdersaCancellation() {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Orders Cancellation']");
        String validate = common.findWebElement("xpath", "//Text[@Name='Purchase Orders Cancellation']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Orders Cancellation", validate);
    }

    public void navigateToMaterialReceipts() {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Material Receipts']");
        String validate = common.findWebElement("xpath", "//Text[@Name='Material Receipts']").getText();
        System.out.println(validate);
        Assert.assertEquals("Material Receipts", validate);
    }

    public void navigateToMaterialReceiptsAgainstOrders() {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Material Receipts against Orders']");
        String validate = common.findWebElement("xpath", "//Text[@Name='Material Receipts against Orders']").getText();
        System.out.println(validate);
        Assert.assertEquals("Material Receipts against Orders", validate);
    }

    public void navigateToMaterialReturns() {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Material Returns']");
        String validate = common.findWebElement("xpath", "//Text[@Name='Material Returns']").getText();
        System.out.println(validate);
        Assert.assertEquals("Material Returns", validate);
    }

    public void navigateToPurchasePrice() {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Price");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Prices']");
        String validate = common.findWebElement("xpath", "//Text[@Name='Purchase Prices']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Prices", validate);
    }

    public void navigateToPurchaseVouchers() {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Vouchers']");
        String validate = common.findWebElement("xpath", "//Text[@Name='Purchase Vouchers']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Vouchers", validate);
    }

    public void navigateToPurchaseVouchersAgainstOrders() {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Vouchers against Orders']");
        String validate = common.findWebElement("xpath", "//Text[@Name='Purchase Vouchers against Orders']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Vouchers against Orders", validate);
    }

    public void navigateToPurchaseVouchersAgainstReceipts() {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Vouchers against Receipts']");
        String validate = common.findWebElement("xpath", "//Text[@Name='Purchase Vouchers against Receipts']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Vouchers against Receipts", validate);
    }

    public void navigateToPurchaseVouchersWithInvoicesReference() {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Returns with Invoice Reference']");
        String validate = common.findWebElement("xpath", "//Text[@Name='Purchase Returns with Invoice Reference']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Returns with Invoice Reference", validate);
    }

    public void navigateToSalesEnquiryReport() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Enquiries");
        common.clickElement("xpath", "//Menu[@Name='Enquiries']/MenuItem[@Name='Sales Enquiries'][2]");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
    }

    //select products
    public void generalProduct(String filename, String product, String quantity, int i) throws IOException, ParseException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename, product);
        common.clickElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']");
        enterData("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']", filename, quantity);
    }

    public void multiBatchProduct(String filename, String product, String quantity, int i) throws IOException, ParseException, InterruptedException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename, product);
        common.clickElement("xpath", "//Button[@Name='Stock Details Row " + i + "']");
        Thread.sleep(3000);
        List<WebElement> rows = common.findWebElements("xpath", "//Table[@Name='Batch Details']/*[@Name='Data Panel']/*[@Name='Row 1']/*[@Name='Quantity row 1']");
        System.out.println("Row count: " + rows.size());
        for (WebElement k : rows) {
            k.click();
            k.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
            k.sendKeys(common.getData(filename, quantity), Keys.TAB);
        }
        common.clickElement("xpath", "//Button[@Name='OK']");
    }

    public void serialNumberProduct(String filename, String product, int i) throws IOException, ParseException, InterruptedException, AWTException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename, product);
        common.clickElement("xpath", "//Button[@Name='Stock Details Row " + i + "']");
        List<WebElement> rowss = common.findWebElements("xpath", "//Table[@Name='Serial Numbers List']/*[@Name='Data Panel']/*[contains(@Name,'Row')]/*[contains(@Name,'Select row')]");
        System.out.println("Row count: " + rowss.size());
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        for (int z = 0; z < 5; z++) {
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            Thread.sleep(1500);
        }
        common.clickElement("xpath", "//Button[@Name='OK']");
    }

    //calculations methods
    public void chargesAndDeductionsCalculations(String dataFile, String type, String accCode, String amount, String iterations) throws IOException, ParseException {
        navigateToChargesAndDeductionsTab();
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, iterations)); i++) {
            enterData("xpath", "//Edit[@Name='Charges Or Deductions * Row " + i + ", Not sorted.']", dataFile, type);
            enterData("xpath", "//Edit[@Name='Account Code Row " + i + ", Not sorted.']", dataFile, accCode);
            enterData("xpath", "//Edit[@Name='Amount * Row " + i + ", Not sorted.']", dataFile, amount);

            // Fetch values of Charges and Deductions fields
            String chargesValue = common.findWebElement("xpath", "//Edit[@Name='Charges Row " + i + ", Not sorted.']").getText();
            String deductionsValue = common.findWebElement("xpath", "//Edit[@Name='Deductions Row " + i + ", Not sorted.']").getText();

            if (type.equalsIgnoreCase("Charges")) {
                if (chargesValue.equals(amount) && (deductionsValue.equals("0.000") || deductionsValue.isEmpty())) {
                    System.out.println("Validation Passed: Amount correctly added to Charges field.");
                } else {
                    Assert.fail("Validation Failed: Charges field or Deductions field has incorrect data.");
                }
            } else if (type.equalsIgnoreCase("Deductions")) {
                if (deductionsValue.equals(amount) && (chargesValue.equals("0.000") || chargesValue.isEmpty())) {
                    System.out.println("Validation Passed: Amount correctly added to Deductions field.");
                } else {
                    Assert.fail("Validation Failed: Deductions field or Charges field has incorrect data.");
                }
            }
        }
    }

    public void tcsCalculations(double itemsNetValue) throws IOException, ParseException {
        double exchangeRate = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Exchange Rate *']").getText());
        System.out.println("exchangeRate :- " + exchangeRate);
//        double itemsNetValue= Double.parseDouble(common.findWebElement("xpath","//Edit[@AutomationId='NetAmount']").getText().replace(",",""));
        navigateToOtherChargesTab();
        //other charges Calculations
        String amountText = common.findWebElement("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']").getText();
        double otherChargesNetValue = 0.0;
        if ("(null)".equals(amountText) || amountText == (null)) {
            System.out.println("Other Charges not Available");
        } else {
//            OtherChargesCalculations(dataFile, accCode, amount,iterations);
            otherChargesNetValue = Double.parseDouble(common.findWebElement("xpath", "//Edit[@AutomationId='NetAmount']").getText().replace(",", ""));
        }
        double totalNetValue = itemsNetValue + otherChargesNetValue;
        navigateToTCSTab();
        double tcsAssessbleValue = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Assessable Value']").getText().replace(",", ""));
        Assert.assertEquals(totalNetValue, tcsAssessbleValue, "Net Amount and Assessable value should be a match");
        double tcsRate = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='TCS Rate']").getText());
        double expectedTCSAmount = tcsAssessbleValue * tcsRate / 100;
        double actualTCSAmount = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='TCS Amount']").getText().replace(",", ""));
        Assert.assertEquals(actualTCSAmount, expectedTCSAmount, "TCS amounts are not equal");
        double actualTCSCompanyCurrency = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='TCS Amount']").getText().replace(",", ""));
        double expectedTCSCompanyCurrency = actualTCSAmount * exchangeRate;
        System.out.println("Actual Currency: " + actualTCSCompanyCurrency + " Expected Currency: " + expectedTCSCompanyCurrency);
        Assert.assertEquals(actualTCSCompanyCurrency, expectedTCSCompanyCurrency, "Company Currency isn't matching");
    }

    public void OtherChargesCalculations(String dataFile, String accCode, String amount, String iterations) throws IOException, ParseException {
        boolean gstAmountClicked = false;
        navigateToOtherChargesTab();
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile, iterations)); i++) {
            // Enter Account Code and Amount
            enterData("xpath", "//Edit[@Name='Account Code Row " + i + ", Not sorted.']", dataFile, accCode);
            enterData("xpath", "//Edit[@Name='Amount * Row " + i + ", Not sorted.']", dataFile, amount);
            enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile, "HSNCode");

            WebElement gstElement = common.findWebElement("xpath", "//Edit[@Name='GST Product Category Row " + i + ", Not sorted.']");
            double gstValue = StringUtil.extractNumber(gstElement.getText());
            System.out.println("GST Percentage: " + gstValue);
            WebElement cessElement = common.findWebElement("xpath", "//Edit[@Name='CESS Product Category Row " + i + ", Not sorted.']");
            double cessValue = StringUtil.extractNumber(cessElement.getText());
            System.out.println("CESS Percentage: " + cessValue);
            // Sum of GST and CESS
            double totalTaxRate = gstValue + cessValue;
            System.out.println("Total Tax Rate: " + totalTaxRate);
            // Decimal Formatter for precision
            DecimalFormat decimalFormat = new DecimalFormat("#.###");
            // Check if Inclusive or Exclusive Tax
            String taxType = common.getData(dataFile, "TaxType"); // Assume "Inclusive" or "Exclusive" is stored in dataFile

            if (!gstAmountClicked) {
                common.clickElement("xpath", "//Header[@Name='GST Amount']");
                gstAmountClicked = true;
            }

            if (taxType.equalsIgnoreCase("Inclusive")) {
                common.clickElement("xpath", "//CheckBox[@Name='Inclusive Tax Row " + i + "']");
                double taxableValue = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']").getText());
                System.out.println("taxable: " + taxableValue);
                String gstTransType = common.findWebElement("xpath", "//Edit[@Name='GST Trans Type *']").getText().trim();
                if (gstTransType.equalsIgnoreCase("Inter State Sales to Registered Dealers")) {
                    double expectedIGST = Double.parseDouble(decimalFormat.format((taxableValue * gstValue) / 100));
                    double actualIGST = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']").getText().replace(",", ""));
                    System.out.println("Actual IGST: " + actualIGST);
                    System.out.println("Expected IGST: " + expectedIGST);
                    Assert.assertEquals(actualIGST, expectedIGST);

                    double expectedCESS = Double.parseDouble(decimalFormat.format((taxableValue * cessValue) / 100));
                    double actualCESS = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']").getText().replace(",", ""));
                    System.out.println("Actual CESS: " + actualCESS);
                    System.out.println("Expected CESS: " + expectedCESS);
                    Assert.assertEquals(actualCESS, expectedCESS);

                    double expectedGSTAmount = expectedIGST + expectedCESS;
                    double actualGSTAmount = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']").getText().replace(",", ""));
                    System.out.println("Expected GST Amount: " + decimalFormat.format(expectedGSTAmount));
                    System.out.println("Actual GST Amount: " + decimalFormat.format(actualGSTAmount));
                    Assert.assertEquals(decimalFormat.format(actualGSTAmount), decimalFormat.format(expectedGSTAmount));
                } else if (gstTransType.equalsIgnoreCase("Intra State Sales to Registered Dealers")) {
                    double expectedCGST = Double.parseDouble(decimalFormat.format(((taxableValue * gstValue) / 100) / 2));
                    double actualCGST = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='CGST Row " + i + ", Not sorted.']").getText().replace(",", ""));
                    System.out.println("Actual CGST: " + actualCGST);
                    System.out.println("Expected CGST: " + expectedCGST);
                    Assert.assertEquals(actualCGST, expectedCGST);

                    double expectedSGST = Double.parseDouble(decimalFormat.format(((taxableValue * gstValue) / 100) / 2));
                    double actualSGST = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='SGST Row " + i + ", Not sorted.']").getText().replace(",", ""));
                    System.out.println("Actual SGST: " + actualSGST);
                    System.out.println("Expected SGST: " + expectedSGST);
                    Assert.assertEquals(actualSGST, expectedSGST);

                    double expectedCESS = Double.parseDouble(decimalFormat.format((taxableValue * cessValue) / 100));
                    double actualCESS = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']").getText().replace(",", ""));
                    System.out.println("Actual CESS: " + actualCESS);
                    System.out.println("Expected CESS: " + expectedCESS);
                    Assert.assertEquals(actualCESS, expectedCESS);

                    double expectedGSTAmount = expectedCGST + expectedSGST + expectedCESS;
                    double actualGSTAmount = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']").getText().replace(",", ""));
                    System.out.println("Expected GST Amount: " + decimalFormat.format(expectedGSTAmount));
                    System.out.println("Actual GST Amount: " + decimalFormat.format(actualGSTAmount));
                    Assert.assertEquals(decimalFormat.format(actualGSTAmount), decimalFormat.format(expectedGSTAmount));
                } else {
                    throw new IllegalArgumentException("Invalid GST Trans Type: " + gstTransType);
                }
            } else if (taxType.equalsIgnoreCase("Exclusive")) {
                double taxableValue = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']").getText());
                System.out.println("taxable: " + taxableValue);
                // Exclusive Tax Calculations
                double calculatedGSTAmount = Double.parseDouble(decimalFormat.format(taxableValue * (totalTaxRate / 100)));
                System.out.println("calculatedGST: " + calculatedGSTAmount);
                double calculatedNetAmount = taxableValue + calculatedGSTAmount;
                System.out.println("calculated Net: " + calculatedNetAmount);

                // Fetch values from UI
                double actualGSTAmount = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']").getText());
                System.out.println("actual GST Amount :-" + actualGSTAmount);
                double actualNetAmount = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']").getText());
                System.out.println("actual net Amount :-" + actualNetAmount);
                // Validate GST Amount and Net Amount
                Assert.assertEquals(actualGSTAmount, calculatedGSTAmount, "GST Amount mismatch for row " + i);
                Assert.assertEquals(actualNetAmount, calculatedNetAmount, "Net Amount mismatch for row " + i);
            } else {
                throw new IllegalArgumentException("Invalid Tax Type: " + taxType);
            }
        }
    }

    //transaction related methods
    public void transactionSave() throws InterruptedException {
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        Thread.sleep(3000);
        common.clickElement("xpath", "//*[@Name='Transaction saved.']/Button[@Name='OK']");
    }

    public void lastTransactionName() {
        System.out.println("lastSaved :" + common.findWebElement("xpath", "//Text[@Name='Last Saved :']/following-sibling::Text").getAttribute("Name"));
    }

    public void closeTransaction(String transaction) {
        common.clickElement("xpath", "//TabItem[@Name='" + transaction + "']/Button[@Name='Close']");
    }

    public String oldTTransactionID() {
        String oldID = common.findWebElement("xpath", "//Text[@Name='Last Saved :']/following-sibling::Text").getAttribute("Name");
        return oldID;
    }

    public String newTransactionID(String oldID) {
        String newID = common.findWebElement("xpath", "//Text[@Name='Last Saved :']/following-sibling::Text").getAttribute("Name");
        return newID;
    }

    public void oldTTransaction() {
        System.out.println("Recent Transaction Id :" + common.findWebElement("xpath", "//Text[@Name='Last Saved :']/following-sibling::Text").getAttribute("Name"));
    }

    public void newTransaction() {
        System.out.println("New Transaction ID  :" + common.findWebElement("xpath", "//Text[@Name='Last Saved :']/following-sibling::Text").getAttribute("Name"));
    }

    public String getNewTransactionId() {
        String transactionId = common.findWebElement("xpath", "//Text[@Name='Last Saved :']/following-sibling::Text").getAttribute("Name");
        return transactionId;
    }

    //general methods
    public void saveProperties() throws InterruptedException {
        common.clickElement("xpath", "//Button[@Name='Save']");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//Button[@Name='OK']"))).click();
        Thread.sleep(2000);
        common.clickElement("xpath", "//Button[@Name='OK']");
    }
    public void saveMasterOrProperty(){
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
    }

    public void sliderHandle() {
        int offset = 350;
        WebElement slider = common.findWebElement("xpath", "//Table[@Name='Items']/ScrollBar[@Name='Horizontal']/Thumb[@Name='Position']");
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(offset, 0).release().perform();
    }

    public void generalInfoSliderHandle(int offset) {
        WebElement slider = common.findWebElement("xpath", "//ScrollBar[@Name='Horizontal']/Thumb[@Name='Position']");
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(offset, 0).release().perform();
    }

    public void checkBoxSelectionBillsReceivables(String locatorType, String rowLocator, String voucherLocator, String checkBoxLocator) {
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

    public void billsPayable(String locatorType, String locator, String fileName, String key) throws IOException, ParseException, InterruptedException {
        navigateToBillsPayablesTab();
        List<WebElement> elementList = common.findWebElements(locatorType, locator);
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            i.click();
            i.sendKeys(common.getData(fileName, key), Keys.TAB);
            break;
        }
        Thread.sleep(1000);
        common.clickElement("xpath", "//Table[@Name='BillsPayable']/*[@Name='Row 0']/Edit[@Name=' Row 0, Not sorted.']");
        WebElement rightClick = common.findWebElement("xpath", "//Table[@Name='BillsPayable']/*[@Name='Row 0']/Edit[@Name=' Row 0, Not sorted.']");
        Actions actions = new Actions(driver);
        actions.contextClick(rightClick).sendKeys(Keys.DOWN, Keys.DOWN, Keys.ENTER).perform();
    }

    public void checkBoxSelectionBillsPayable(String locatorType, String rowLocator, String voucherLocator, String checkBoxLocator) {
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

    public void deleteSingleTransaction(String voucherID) throws InterruptedException {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        for (WebElement i : elementList) {
            if (i.getText().contains(voucherID)) {
                WebElement element = i.findElement(By.xpath("//DataItem[contains(@Name,'Voucher No row')]"));
                element.click();
                Actions actions = new Actions(driver);
                actions.contextClick(element).perform();
                break;
            }
        }
        Thread.sleep(1000);
        common.clickElement("xpath", "//MenuItem[@Name='View Transaction']");
        Thread.sleep(10000);
        common.clickElement("xpath", "//Button[@Name='Tools']");
        common.clickElement("xpath", "//Button[@Name='Delete']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        common.clickElement("xpath", "//Window[@Name='Close']/Button[@Name='Yes']");
        //validate
        common.clickElement("xpath", "//ToolBar/Button[@Name='Refresh']");
        Thread.sleep(1500);
        List<WebElement> voucherList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        for (WebElement i : voucherList) {
            if (i.getText().contains(voucherID)) {
                Assert.fail("New Transaction isn't deleted");
            }
        }
        System.out.println("Transaction is Deleted Successfully");
    }

    public void enableCheckboxSelection(String locatorXpath) {
        WebElement element = driver.findElementByXPath(locatorXpath);
        String checkBoxToggleState = element.getAttribute("Toggle.ToggleState");
//        System.out.println("Check Box Toggle state:-" + checkBoxToggleState);
        if (checkBoxToggleState.equals("0")) {
            element.click();
            String checkBoxToggleState1 = element.getAttribute("Toggle.ToggleState");
//            System.out.println("Check Box Toggle state:-" + checkBoxToggleState1);
            if (checkBoxToggleState1.equals("1")) {
                System.out.println("Checkbox was unchecked, now checked.");
            } else if (checkBoxToggleState1.equals("0")) {
                element.click();
            } else Assert.fail("Check Box it not selected");
        } else if (checkBoxToggleState.equals("1")) {
            System.out.println("Checkbox is already checked, no action needed.");
        } else Assert.fail("Element Not Found");

    }

    public String checkboxSelection(String locatorXpath) {
        WebElement element = driver.findElementByXPath(locatorXpath);
        String text = element.getText();
        String checkBoxToggleState = element.getAttribute("Toggle.ToggleState");
//        System.out.println("Check Box Toggle state:-" + checkBoxToggleState);
        if (checkBoxToggleState.equals("0")) {
            element.click();
            String checkBoxToggleState1 = element.getAttribute("Toggle.ToggleState");
//            System.out.println("Check Box Toggle state:-" + checkBoxToggleState1);
            if (checkBoxToggleState1.equals("1")) {
                System.out.println("Checkbox was unchecked, now checked.");
            } else if (checkBoxToggleState1.equals("0")) {
                element.click();
            } else Assert.fail("Check Box it not selected");
        } else if (checkBoxToggleState.equals("1")) {
            System.out.println("Checkbox is already checked, no action needed.");
        } else Assert.fail("Element Not Found");
        return text;
    }

    public String uncheckCheckBox(String locatorXpath) {
        WebElement element = driver.findElementByXPath(locatorXpath);
        String text = element.getText();
        String checkBoxToggleState = element.getAttribute("Toggle.ToggleState");
//        System.out.println("Check Box Toggle state:-" + checkBoxToggleState);
        if (checkBoxToggleState.equals("1")) {
            System.out.println("Checkbox is already checked, do uncheck");
            element.click();
            String checkBoxToggleState1 = element.getAttribute("Toggle.ToggleState");
//            System.out.println("Check Box Toggle state:-" + checkBoxToggleState1);
            if (checkBoxToggleState1.equals("0")) {
                System.out.println("Checkbox was unchecked");
            } else if (checkBoxToggleState1.equals("1")) {
                System.out.println("Checkbox is already checked, no action needed.");
            } else Assert.fail("Check Box it not selected");
        } else if (checkBoxToggleState.equals("0")) {
            System.out.println("Checkbox was unchecked,no need action");
        } else Assert.fail("Element Not Found");
        return text;
    }

    public double billsReceivable(String locatorType, String rowLocator, String voucherLocator, String checkBoxLocator, String pendingAmountLocator) {
        double finalAmount = 0.0;
        List<WebElement> rows = common.findWebElements(locatorType, rowLocator);
        System.out.println("Row count :" + rows.size());

        for (WebElement row : rows) {
            WebElement voucher = row.findElement(By.xpath(voucherLocator));
            // Check if the voucher element is present and get its value
            String value = voucher.getText();
            System.out.println("Voucher value: " + value);
            if (value == (null) || "(null)".equals(value)) {
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

    public void checkBoxSelection(String locatorType, String rowLocator, String voucherLocator, String checkBoxLocator) {
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
            } else {
                // If the voucher has a valid value, find the checkbox and click it
                WebElement checkBox = row.findElement(By.xpath(checkBoxLocator));
                checkBox.click();
                System.out.println("Checkbox clicked for voucher: " + value);
            }
        }
    }

    public void refresh() {
        common.clickElement("xpath", "//MenuItem[@Name='Tools']");
        common.clickElement("xpath", "//MenuItem[@Name='Clear Cache']");
    }

    //report related methods
    public void bulkVerifyReport(String transaction) {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().contains(transaction)) {
                Assert.assertTrue(true);
            }
        }
    }

    public void verifyReport(String transaction, String dataFile) throws IOException, ParseException {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println("text :" + i.getText());
            if (i.getText().contains(transaction)) {
                System.out.println("verifyingRow.....");
                bulkVerifyReportData(i.getText(), dataFile);
            }
        }
    }

    public void bulkVerifyReportData(String text, String dataFile) throws IOException, ParseException {
        String[] columns = text.split(";");
        System.out.println("columSize :"+columns.length);
        for (int i = 0; i < columns.length; i++) {
            if (i >=2 && !common.getData(dataFile, "column" + (i + 1)).equals("")) {
                Assert.assertEquals(columns[i], common.getData(dataFile, "column" + (i + 1)),"columns mismatch");
            }
            System.out.println(columns[i]);
        }
        System.out.println("Report verified Successfully");
    }

    public void closeReport(String reportName) {
        common.clickElement("xpath", "//TabItem[@Name='" + reportName + "']/Button[@Name='Close']");
    }


    //validating in summary
    public void validateCGSTAmountTabIsEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'CGST')]");
        String value = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (!(value == (null) || "(null)".equals(value))) {
            Assert.fail("CGST field is not empty");
        }
    }

    public void validateSGSTAmountTabIsEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'SGST')]");
        String value1 = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (!(value1 == (null) || "(null)".equals(value1))) {
            Assert.fail("SGST field is not empty");
        }
    }

    public void validateIGSTAmountTabIsEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'IGST')]");
        String value2 = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (!(value2 == (null) || "(null)".equals(value2))) {
            Assert.fail("IGST field is not empty");
        }
    }

    public void validateCESSAmountTabIsEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'CESS')]");
        String value = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (!(value == (null) || "(null)".equals(value))) {
            Assert.fail("CESS field is not empty");
        }
    }

    public void validateCGSTAmountTabIsNotEmpty() {
        common.clickElement("xpath", "//TabItem[@Name='  F8 CGST  ']");
        String value = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (value == (null) || "(null)".equals(value)) {
            Assert.fail("CGST field is  empty");
        }
    }

    public void validateSGSTAmountTabIsNotEmpty() {
        common.clickElement("xpath", "//TabItem[@Name='  F9 SGST  ']");
        String value1 = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (value1 == (null) || "(null)".equals(value1)) {
            Assert.fail("SGST field is empty");
        }
    }

    public void validateIGSTAmountTabIsNotEmpty() {
        common.clickElement("xpath", "//TabItem[@Name='  F11 IGST  ']");
        String value2 = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (value2 == (null) || "(null)".equals(value2)) {
            Assert.fail("IGST field is empty");
        }
    }

    public void validateCESSAmountTabIsNotEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'CESS')]");
        String value = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (value == (null) || "(null)".equals(value)) {
            Assert.fail("CESS field is empty");
        }
    }

    public void quantityPresentInSummary() {
        WebElement Quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity']");
        String quantityText = Quantity.getText();
        if ((quantityText == (null) || "(null)".equals(quantityText))) {
            Assert.fail("Quantity field is empty");
        }
    }

    public void grossAmountPresentInSummary() {
        WebElement grossAmount = common.findWebElement("xpath", "//Edit[@Name='Gross Amount']");
        String grossAmountText1 = grossAmount.getText();
        if ((grossAmountText1 == (null) || "(null)".equals(grossAmountText1))) {
            Assert.fail("grossAmount field is empty");
        }
    }

    public void totalValuePresentInSummary() {
        WebElement totalValue = common.findWebElement("xpath", "//Edit[@Name='Total Value']");
        String totalValueText = totalValue.getText();
        if (totalValueText == (null) || "(null)".equals(totalValueText)) {
            Assert.fail("Total value field is Empty");
        }
    }

    public void totalValueInCompanyCurrenyPresentInSummary() {
        WebElement totalValueCompanyCurreny = common.findWebElement("xpath", "//Edit[@Name='Total Value In Company Currency']");
        String totalValuecurrencyText = totalValueCompanyCurreny.getText();
        if (totalValuecurrencyText == (null) || "(null)".equals(totalValuecurrencyText)) {
            Assert.fail("Total value in Company Curreny field is Empty");
        }
    }

    public void receivableAmountPresentInSummary() {
        WebElement receivableAmount = common.findWebElement("xpath", "//Edit[@Name='Receivable Amount']");
        String receivableAmountText = receivableAmount.getText();
        if (receivableAmountText == (null) || "(null)".equals(receivableAmountText)) {
            Assert.fail("Receivable Amount field is empty");
        }
    }

    public void tcsTaxableValuePresentInSummary() {
        WebElement netAmount = common.findWebElement("xpath", "//Edit[@Name='TCS Taxable Value']");
        String netAmountText1 = netAmount.getText();
        if ((netAmountText1 == (null) || "(null)".equals(netAmountText1))) {
            Assert.fail(" tcsTaxableValuePresentInSummary field is empty");
        }
    }

    public void tcsAmountPresentInSummary() {
        WebElement netAmount = common.findWebElement("xpath", "//Edit[@Name='TCS Amount']");
        String netAmountText1 = netAmount.getText();
        if ((netAmountText1 == (null) || "(null)".equals(netAmountText1))) {
            Assert.fail("TCS Amount field is empty");
        }
    }

    public void netAmountPresentInSummary() {
        WebElement netAmount = common.findWebElement("xpath", "//Edit[@Name='Net Amount']");
        String netAmountText1 = netAmount.getText();
        if ((netAmountText1 == (null) || "(null)".equals(netAmountText1))) {
            Assert.fail("netAmount field is empty");
        }
    }

    public void chargesPresentInSummary() {
        WebElement netAmount = common.findWebElement("xpath", "//Edit[@Name='Charges']");
        String netAmountText1 = netAmount.getText();
        if ((netAmountText1 == (null) || "(null)".equals(netAmountText1))) {
            Assert.fail("netAmount field is empty");
        }
    }

    public void otherChargesPresentInSummary() {
        WebElement netAmount = common.findWebElement("xpath", "//Edit[@Name='Other Charges']");
        String netAmountText1 = netAmount.getText();
        if ((netAmountText1 == (null) || "(null)".equals(netAmountText1))) {
            Assert.fail("netAmount field is empty");
        }
    }

    public void grossMinusDiscountPresentInSummary() {
        WebElement grossDiscountAmount = common.findWebElement("xpath", "//Edit[@Name='Gross - Disc']");
        String grossMinusDiscountAmount = grossDiscountAmount.getText();
        if ((grossMinusDiscountAmount == (null) || "(null)".equals(grossMinusDiscountAmount))) {
            Assert.fail("grossDiscountAmount field is empty");
        }
    }

    public void iGSTPresentInSummary() {
        WebElement grossDiscountAmount = common.findWebElement("xpath", "//Edit[@Name='IGST']");
        String grossMinusDiscountAmount = grossDiscountAmount.getText();
        if ((grossMinusDiscountAmount == (null) || "(null)".equals(grossMinusDiscountAmount))) {
            Assert.fail("grossDiscountAmount field is empty");
        }
    }

    public void cessPresentInSummary() {
        WebElement grossDiscountAmount = common.findWebElement("xpath", "//Edit[@Name='CESS']");
        String grossMinusDiscountAmount = grossDiscountAmount.getText();
        if ((grossMinusDiscountAmount == (null) || "(null)".equals(grossMinusDiscountAmount))) {
            Assert.fail("grossDiscountAmount field is empty");
        }
    }

    public void otherChargesCESSPresentInSummary() {
        WebElement grossDiscountAmount = common.findWebElement("xpath", "//Edit[@Name='Other Charges CESS']");
        String grossMinusDiscountAmount = grossDiscountAmount.getText();
        if ((grossMinusDiscountAmount == (null) || "(null)".equals(grossMinusDiscountAmount))) {
            Assert.fail("grossDiscountAmount field is empty");
        }
    }

    public void otherChargesIGSTPresentInSummary() {
        WebElement grossDiscountAmount = common.findWebElement("xpath", "//Edit[@Name='Other Charges IGST']");
        String grossMinusDiscountAmount = grossDiscountAmount.getText();
        if ((grossMinusDiscountAmount == (null) || "(null)".equals(grossMinusDiscountAmount))) {
            Assert.fail("grossDiscountAmount field is empty");
        }
    }
}