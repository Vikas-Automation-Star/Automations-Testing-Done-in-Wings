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


    public void createMaster(String locatorType, String locator) {
        WebElement element = common.findWebElement(locatorType, locator);
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        common.clickElement("name", "New Master");
    }

    public void saveMaster() throws InterruptedException {
        common.clickElement("name", "Save");
        Thread.sleep(2000);
        common.clickElement("name", "OK");
        common.clickElement("xpath", "//Button[@Name='Close']");
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
            if (i.getText().equals(gstType)) {
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
    public void navigateToSummaryTab() {
//        navigateToOtherInfoTab();
//        navigateToPaytymTab();
//        for (int j = 0; j < 6; j++) {
//            Robot robot = new Robot();
//            robot.keyPress(KeyEvent.VK_RIGHT);
//            robot.keyRelease(KeyEvent.VK_RIGHT);
//        }
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

    public void navigateToTCSTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'TCS')]");
    }


    public void navigateToCashTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Cash')]");
    }

    public void navigateToCreditCardCompanyChargesTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Credit Card Company Charges')]");
    }

    public void navigateToMastersWhen2Steps(String menu, String menuItem) {
        common.clickElement("xpath", "//MenuItem[@Name='"+menu+"']");
        common.clickElement("xpath", "//MenuItem[@Name='"+menuItem+"']");
    }

    public void navigateToMastersWhen3Steps(String menu, String menuItem, String subMenuItem) {
        common.clickElement("xpath", "//MenuItem[@Name='"+menu+"']");
        common.clickElement("xpath", "//MenuItem[@Name='"+menuItem+"']");
        common.clickElement("xpath", "//MenuItem[@Name='"+subMenuItem+"']");
    }

    public void navigateWhen4Steps(String menu, String menuItem, String subMenuItem,String subMenuItem1) throws InterruptedException {
        common.clickElement("xpath", "//MenuItem[@Name='"+menu+"']");
        common.clickElement("xpath", "//MenuItem[@Name='"+menuItem+"']");
        common.clickElement("xpath", "//MenuItem[@Name='"+subMenuItem+"']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Button[@Name='"+subMenuItem1+"']");
    }

    public void navigateToMastersWhen4Steps(String menu, String secondMenu, String thirdMenu,String fourthMenu) {
        common.clickElement("xpath", "//MenuItem[@Name='"+menu+"']");
        common.clickElement("xpath", "//MenuItem[@Name='"+secondMenu+"']");
        common.clickElement("xpath", "//MenuItem[@Name='"+thirdMenu+"']");
        common.clickElement("xpath", "//MenuItem[@Name='"+fourthMenu+"']");
    }
    public void navigateToIncomesTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Incomes')]");
    }


    public void navigateToPaytymTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Paytm')]");
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

    public void navigateToOtherChargesTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Other Charges')]");
    }

    public void navigateToChargesAndDeductionsTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Charges And Deductions')]");
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
//        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Enquiries']").getText();
//        System.out.println("Screen Name:-" + pageValidation);
//        Assert.assertEquals(pageValidation, "Sales Enquiries");
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
        common.clickElement("xpath", "//MenuItem[@Name='Sales']");
        common.clickElement("xpath", "//MenuItem[@Name='Deliveries']");
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
//        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Invoices against Deliveries']").getText();
//        System.out.println("Screen Name:-" + pageValidation);
//        Assert.assertEquals(pageValidation, "Sales Invoices against Deliveries");
    }

    public void navigateToSalesInvoiceAgainstOrdersMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Invoices against Orders']");
//        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Invoices against Orders']").getText();
//        System.out.println("Screen Name:-" + pageValidation);
//        Assert.assertEquals(pageValidation, "Sales Invoices against Orders");
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
//        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Returns']").getText();
//        System.out.println("Screen Name:-" + pageValidation);
//        Assert.assertEquals(pageValidation, "Sales Returns");
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
    public void navigateToAccountGroups(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Account Groups");
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
        List<WebElement> rows = common.findWebElements("xpath", "//Table[@Name='Serial Numbers List']/*[@Name='Data Panel']/*[contains(@Name,'Row')]/*[contains(@Name,'Select row')]");
        System.out.println("Row count: " + rows.size());
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        for (int z = 0; z < Integer.parseInt(common.getData(filename,"numOfSerialProducts")); z++) {
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            Thread.sleep(1500);
        }
        List<WebElement> free=common.findWebElements("xpath","//Table[@Name='Selected Serial Numbers']/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("free elements size: "+free.size());
        if (Boolean.parseBoolean(common.getData(filename,"enableFreeQuantity"))){
            for (int j = 1; j <=Integer.parseInt(common.getData(filename,"numOfSerialProductsFree")); j++) {
                String rowXPath = "//Table[@Name='Selected Serial Numbers']/*[@Name='Data Panel']/*[@Name='Row "+j+"']/*[@Name='FreeQuantity row "+j+"']";
                // Find the element based on the dynamic XPath
                WebElement button = common.findWebElement("xpath", rowXPath);
                button.click();
            }
        }
        common.clickElement("xpath", "//Button[@Name='OK']");
    }
    //over-load freeqty
    public void generalProduct(String filename, String product, String quantity,String freeQuantity, int i) throws IOException, ParseException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename, product);
        common.clickElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']");
        enterData("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']", filename, quantity);
        if (Boolean.parseBoolean(common.getData(filename,"enableFreeQuantity"))){
            enterData("xpath","//Edit[@Name='Free Quantity Row " + i + ", Not sorted.']",filename,freeQuantity);
        }
    }

    public void multiBatchProductPurchase(String filename, String product, String quantity,String freeQuantity, int i) throws IOException, ParseException, InterruptedException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename, product);
        enterData("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']",filename,quantity);
        if (Boolean.parseBoolean(common.getData(filename,"enableFreeQuantity"))){
            enterInput("xpath","//Edit[@Name='Free Quantity Row " + i + ", Not sorted.']",filename,freeQuantity);
        }
    }

    public void multiBatchProduct(String filename, String product, String quantity,String freeQuantity, int i) throws IOException, ParseException, InterruptedException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename, product);
        common.clickElement("xpath", "//Button[@Name='Stock Details Row " + i + "']");
        Thread.sleep(3000);
        List<WebElement> rows = common.findWebElements("xpath", "//Table[@Name='Batch Details']/*[@Name='Data Panel']/*[@Name='Row 1']/*[@Name='Quantity row 1']");
        System.out.println("Row count: " + rows.size());
        for (WebElement k : rows) {
            k.click();
            k.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
            k.sendKeys(common.getData(filename, quantity), Keys.TAB);
            if (Boolean.parseBoolean(common.getData(filename,"enableFreeQuantity"))){
                k.sendKeys(common.getData(filename,freeQuantity),Keys.TAB);
            }
        }
        common.clickElement("xpath", "//Button[@Name='OK']");
    }

    //over load with dataset
    public void generalProduct(String filename,String dataSet, String product, String quantity,String freeQuantity, int i) throws IOException, ParseException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename,dataSet, product);
        common.clickElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']");
        enterData("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']", filename,dataSet, quantity);
        if (Boolean.parseBoolean(common.getData(filename,dataSet,"enableFreeQuantity"))){
            enterData("xpath","//Edit[@Name='Free Quantity Row " + i + ", Not sorted.']",filename,dataSet,freeQuantity);
        }
    }
    public void multiBatchProduct(String filename,String dataSet, String product, String quantity,String freeQuantity, int i) throws IOException, ParseException, InterruptedException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename, dataSet,product);
        common.clickElement("xpath", "//Button[@Name='Stock Details Row " + i + "']");
        Thread.sleep(3000);
        List<WebElement> rows = common.findWebElements("xpath", "//Table[@Name='Batch Details']/*[@Name='Data Panel']/*[@Name='Row 1']/*[@Name='Quantity row 1']");
        System.out.println("Row count: " + rows.size());
        for (WebElement k : rows) {
            k.click();
            k.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
            k.sendKeys(common.getData(filename,dataSet, quantity), Keys.TAB);
            if (Boolean.parseBoolean(common.getData(filename,dataSet,"enableFreeQuantity"))){
                k.sendKeys(common.getData(filename,dataSet,freeQuantity),Keys.TAB);
            }
        }
        common.clickElement("xpath", "//Button[@Name='OK']");
    }
    public void serialNumberProduct(String filename,String dataSet,String product, int i) throws IOException, ParseException, InterruptedException, AWTException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename,dataSet, product);
        common.clickElement("xpath", "//Button[@Name='Stock Details Row " + i + "']");
        List<WebElement> rows = common.findWebElements("xpath", "//Table[@Name='Serial Numbers List']/*[@Name='Data Panel']/*[contains(@Name,'Row')]/*[contains(@Name,'Select row')]");
        System.out.println("Row count: " + rows.size());
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        for (int z = 0; z < Integer.parseInt(common.getData(filename,dataSet,"numOfSerialProducts")); z++) {
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            Thread.sleep(1500);
        }
        List<WebElement> free=common.findWebElements("xpath","//Table[@Name='Selected Serial Numbers']/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("free elements size: "+free.size());
        if (Boolean.parseBoolean(common.getData(filename,dataSet,"enableFreeQuantity"))){
            for (int j = 1; j <=Integer.parseInt(common.getData(filename,dataSet,"numOfSerialProductsFree")); j++) {
                String rowXPath = "//Table[@Name='Selected Serial Numbers']/*[@Name='Data Panel']/*[@Name='Row "+j+"']/*[@Name='FreeQuantity row "+j+"']";
                // Find the element based on the dynamic XPath
                WebElement button = common.findWebElement("xpath", rowXPath);
                button.click();
            }
        }
        common.clickElement("xpath", "//Button[@Name='OK']");
    }
    public void multiBatchProductPurchase(String filename,String dataset, String product, String quantity,String freeQuantity, int i) throws IOException, ParseException, InterruptedException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename,dataset, product);
        enterData("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']",filename,dataset,quantity);
        if (Boolean.parseBoolean(common.getData(filename,dataset,"enableFreeQuantity"))){
            enterInput("xpath","//Edit[@Name='Free Quantity Row " + i + ", Not sorted.']",filename,dataset,freeQuantity);
        }
    }

    public void serialNumberProductInPurchase(String filename,String dataSet, String product,String serialText,String serialQuantity,String freeQuantity, int i) throws IOException, ParseException, InterruptedException, AWTException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename,dataSet, product);
        common.clickElement("xpath", "//Button[@Name='Serial Nos Row "+i+"']");
        WebElement increment = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
        increment.sendKeys(Keys.TAB,common.getData(filename,dataSet, serialText)+ Common.getRandomChar(), Keys.TAB, common.getData(filename,dataSet, serialQuantity),Keys.ENTER);
        if(Boolean.parseBoolean(common.getData(filename,dataSet,"enableFreeQuantity"))) {
//            System.out.println("true u can provide quantity and free");
            WebElement freeQ = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
            freeQ.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, common.getData(filename,dataSet, freeQuantity),Keys.ENTER);
        }
        common.clickElement("xpath", "//Button[@Name='OK']");
    }

    public void serialNumberForMRAO(String filename,String dataSet,String serialText,String serialQuantity,String freeQuantity) throws IOException, ParseException{
        common.clickElement("xpath", "//Button[@Name='Serial Nos Row 2']");
        WebElement increment = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
        increment.sendKeys(Keys.TAB,common.getData(filename,dataSet, serialText)+ Common.getRandomChar(), Keys.TAB, common.getData(filename,dataSet, serialQuantity),Keys.ENTER);
        if(Boolean.parseBoolean(common.getData(filename,dataSet,"enableFreeQuantity"))) {
//            System.out.println("true u can provide quantity and free");
            WebElement freeQ = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
            freeQ.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, common.getData(filename,dataSet, freeQuantity),Keys.ENTER);
        }
        common.clickElement("xpath", "//Button[@Name='OK']");
    }
    public void serialNumberProductInPurchase(String filename, String product,String serialText,String serialQuantity,String freeQuantity, int i) throws IOException, ParseException, InterruptedException, AWTException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename, product);
        common.clickElement("xpath", "//Button[@Name='Serial Nos Row "+i+"']");
        WebElement increment = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
        increment.sendKeys(Keys.TAB,common.getData(filename, serialText)+ Common.getRandomChar(), Keys.TAB, common.getData(filename, serialQuantity),Keys.ENTER);
        if(Boolean.parseBoolean(common.getData(filename,"enableFreeQuantity"))) {
//            System.out.println("true u can provide quantity and free");
            WebElement freeQ = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
            freeQ.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, common.getData(filename, freeQuantity),Keys.ENTER);
        }
        common.clickElement("xpath", "//Button[@Name='OK']");
    }

    public void multiBatchProduct_New(String filename,String dataSet,String transType,String product,String quantity,String freeQuantity,int i) throws IOException, ParseException {
        if (common.getData(filename,dataSet,transType).equalsIgnoreCase("Sales Order") || common.getData(filename,dataSet,transType).equalsIgnoreCase("Purchase Order")||common.getData(filename,dataSet,transType).equalsIgnoreCase("Purchase Voucher")||common.getData(filename,dataSet,transType).contains("Sales")) {
            enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename, dataSet, product);
            common.clickElement("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']");
            enterData("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']", filename, dataSet, quantity);
            if (Boolean.parseBoolean(common.getData(filename, dataSet, "enableFreeQuantity"))) {
                enterData("xpath", "//Edit[@Name='Free Quantity Row " + i + ", Not sorted.']", filename, dataSet, freeQuantity);
            }
        }
    }
    public void generalProduct_New(String filename,String dataSet, String product, String quantity,String freeQuantity, int i) throws IOException, ParseException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename,dataSet, product);
        common.clickElement("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']");
        enterData("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']", filename,dataSet, quantity);
        if (Boolean.parseBoolean(common.getData(filename,dataSet,"enableFreeQuantity"))){
            enterData("xpath","//Edit[@Name='Free Quantity Row " + i + ", Not sorted.']",filename,dataSet,freeQuantity);
        }
    }

    public void serialNumProduct_New(String filename,String dataSet,String transType,String product,String quantity,String freeQuantity,int i) throws IOException, ParseException {
        if (common.getData(filename,dataSet,transType).equalsIgnoreCase("Sales Order") || common.getData(filename,dataSet,transType).equalsIgnoreCase("Purchase Order") || common.getData(filename,dataSet,transType).contains("Sales")) {
            enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename, dataSet, product);
            common.clickElement("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']");
            enterData("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']", filename, dataSet, quantity);
            if (Boolean.parseBoolean(common.getData(filename, dataSet, "enableFreeQuantity"))) {
                enterData("xpath", "//Edit[@Name='Free Quantity Row " + i + ", Not sorted.']", filename, dataSet, freeQuantity);
            }
        }
    }

    public void serialNumProductDirectQuantity(String filename,String dataSet,String product,String quantity,String freeQuantity,int i) throws IOException, ParseException {
            enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename, dataSet, product);
            common.clickElement("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']");
            enterData("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']", filename, dataSet, quantity);
            if (Boolean.parseBoolean(common.getData(filename, dataSet, "enableFreeQuantity"))) {
                enterData("xpath", "//Edit[@Name='Free Quantity Row " + i + ", Not sorted.']", filename, dataSet, freeQuantity);
            }
        }

    public void multiBatchProductDirectQuantity(String filename,String dataSet,String product,String quantity,String freeQuantity,int i) throws IOException, ParseException {
            enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename, dataSet, product);
            common.clickElement("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']");
            enterData("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']", filename, dataSet, quantity);
            if (Boolean.parseBoolean(common.getData(filename, dataSet, "enableFreeQuantity"))) {
                enterData("xpath", "//Edit[@Name='Free Quantity Row " + i + ", Not sorted.']", filename, dataSet, freeQuantity);
            }
        }
    //deliveries against orders
    public void generalProductInDELO(String filename,String dataset, String product, String quantity, int i) throws IOException, ParseException {
        common.clickElement("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']");
        enterData("xpath", "//Edit[@Name='Quantity Row " + i + ", Not sorted.']", filename,dataset, quantity);
    }
    public void multiBatchProductInDELO(String filename,String dataSet, String product, String quantity,String freeQuantity, int i) throws IOException, ParseException, InterruptedException {
        common.clickElement("xpath", "//Button[@Name='Stock Details Row " + i + "']");
        Thread.sleep(3000);
        List<WebElement> rows = common.findWebElements("xpath", "//Table[@Name='Batch Details']/*[@Name='Data Panel']/*[@Name='Row 1']/*[@Name='Quantity row 1']");
        System.out.println("Row count: " + rows.size());
        for (WebElement k : rows) {
            k.click();
            k.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
            k.sendKeys(common.getData(filename,dataSet, quantity), Keys.TAB);
            if (Boolean.parseBoolean(common.getData(filename,dataSet,"enableFreeQuantity"))){
                k.sendKeys(common.getData(filename,dataSet,freeQuantity),Keys.TAB);
            }
        }
        common.clickElement("xpath", "//Button[@Name='OK']");
    }
    public void serialNumberProductInDELO(String filename,String dataSet,String product, int i) throws IOException, ParseException, InterruptedException, AWTException {
        common.clickElement("xpath", "//Button[@Name='Stock Details Row " + i + "']");
        List<WebElement> rows = common.findWebElements("xpath", "//Table[@Name='Serial Numbers List']/*[@Name='Data Panel']/*[contains(@Name,'Row')]/*[contains(@Name,'Select row')]");
        System.out.println("Row count: " + rows.size());
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        for (int z = 0; z < Integer.parseInt(common.getData(filename,dataSet,"numOfSerialProducts")); z++) {
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            Thread.sleep(1500);
        }
        List<WebElement> free=common.findWebElements("xpath","//Table[@Name='Selected Serial Numbers']/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("free elements size: "+free.size());
        if (Boolean.parseBoolean(common.getData(filename,dataSet,"enableFreeQuantity"))){
            for (int j = 1; j <=Integer.parseInt(common.getData(filename,dataSet,"numOfSerialProductsFree")); j++) {
                String rowXPath = "//Table[@Name='Selected Serial Numbers']/*[@Name='Data Panel']/*[@Name='Row "+j+"']/*[@Name='FreeQuantity row "+j+"']";
                // Find the element based on the dynamic XPath
                WebElement button = common.findWebElement("xpath", rowXPath);
                button.click();
            }
        }
        common.clickElement("xpath", "//Button[@Name='OK']");
    }



//    public void serialNumberProduct(String filename, String product,String freeQuantity, int i) throws IOException, ParseException, InterruptedException, AWTException {
//        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename, product);
//        common.clickElement("xpath", "//Button[@Name='Stock Details Row " + i + "']");
//        List<WebElement> rowss = common.findWebElements("xpath", "//Table[@Name='Serial Numbers List']/*[@Name='Data Panel']/*[contains(@Name,'Row')]/*[contains(@Name,'Select row')]");
//        System.out.println("Row count: " + rowss.size());
//        Robot robot = new Robot();
//        robot.keyPress(KeyEvent.VK_TAB);
//        robot.keyRelease(KeyEvent.VK_TAB);
//        for (int z = 0; z < Integer.parseInt(common.getData(filename,"numOfSerialProducts")); z++) {
//            robot.keyPress(KeyEvent.VK_SPACE);
//            robot.keyRelease(KeyEvent.VK_SPACE);
//            robot.keyPress(KeyEvent.VK_DOWN);
//            robot.keyRelease(KeyEvent.VK_DOWN);
//            Thread.sleep(1500);
//        }
//        if (Boolean.parseBoolean(common.getData(filename,"enableFreeQuantity"))){
////            List<WebElement> freeQtyRows=common.findWebElements("xpath","//Table[@Name='Selected Serial Numbers']/*[@Name='Data Panel']/*[contains(@Name,'Row')]/*contains(@Name.'FreeQuantity row)]");
////            System.out.println("free qty rows: "+freeQtyRows.size());
//            for (int j = 0; j < Integer.parseInt(common.getData(filename,"numOfSerialProductsFree")); j++) {
//                common.clickElement("xpath","//Table[@Name='Selected Serial Numbers']/*[@Name='Data Panel']/*[contains(@Name,'Row')]/Item[@Name='FreeQuantity row " + i + "']");
//            }
//        }
//        common.clickElement("xpath", "//Button[@Name='OK']");
//    }


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
    public void OtherChargesCalculations(String dataFile,String dataSet, String accCode, String amount, String iterations,String HSNCode,String TaxType) throws IOException, ParseException {
        boolean gstAmountClicked = false;
        navigateToOtherChargesTab();
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,dataSet, iterations)); i++) {
            // Enter Account Code and Amount
            enterData("xpath", "//Edit[@Name='Account Code Row " + i + ", Not sorted.']", dataFile,dataSet, accCode);
            enterData("xpath", "//Edit[@Name='Amount * Row " + i + ", Not sorted.']", dataFile,dataSet, amount);
            enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile, dataSet,HSNCode);

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
            String taxType = common.getData(dataFile, dataSet,TaxType); // Assume "Inclusive" or "Exclusive" is stored in dataFile

            if (!gstAmountClicked) {
                common.clickElement("xpath", "//Header[@Name='GST Amount']");
                gstAmountClicked = true;
            }

            if (taxType.equalsIgnoreCase("Inclusive")) {
                common.clickElement("xpath", "//CheckBox[@Name='Inclusive Tax Row " + i + "']");
                double taxableValue = Double.parseDouble(common.findWebElement("xpath", "//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']").getText());
                System.out.println("taxable: " + taxableValue);
                String gstTransType = common.findWebElement("xpath", "//Edit[@Name='GST Trans Type *']").getText().trim();
                if (gstTransType.equalsIgnoreCase("Inter State Purchase from Registered Dealers")) {
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
                } else if (gstTransType.equalsIgnoreCase("Inter State Purchase from Registered Dealers")) {
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
            }else {
                throw new IllegalArgumentException("Invalid Tax Type: " + taxType);
            }
        }
    }

    public void UnRegOtherChargesCalculations(String dataFile,String dataSet, String accCode, String amount, String iterations,String HSNCode,String TaxType,String expectedTaxibleValue,String expectedGSTValue,String expectedNetAmountValue) throws IOException, ParseException {
        boolean gstAmountClicked = false;
        navigateToOtherChargesTab();
        for (int i = 0; i < Integer.parseInt(common.getData(dataFile,dataSet, iterations)); i++) {
            enterData("xpath", "//Edit[@Name='Account Code Row " + i + ", Not sorted.']", dataFile,dataSet, accCode);
            enterData("xpath", "//Edit[@Name='Amount * Row " + i + ", Not sorted.']", dataFile,dataSet, amount);
            enterData("xpath", "//Edit[@Name='HSN Row " + i + ", Not sorted.']", dataFile, dataSet,HSNCode);
            WebElement gstElement = common.findWebElement("xpath", "//Edit[@Name='GST Product Category Row " + i + ", Not sorted.']");
            double gstValue = StringUtil.extractNumber(gstElement.getText());
            System.out.println("GST Percentage: " + gstValue);
            WebElement cessElement = common.findWebElement("xpath", "//Edit[@Name='CESS Product Category Row " + i + ", Not sorted.']");
            double cessValue = StringUtil.extractNumber(cessElement.getText());
            System.out.println("CESS Percentage: " + cessValue);
            // Sum of GST and CESS
            double totalTaxRate = gstValue + cessValue;
            System.out.println("Total Tax Rate: " + totalTaxRate);
            // Check if Inclusive or Exclusive Tax
            String taxType = common.getData(dataFile, dataSet,TaxType); // Assume "Inclusive" or "Exclusive" is stored in dataFile
            if (!gstAmountClicked) {
                common.clickElement("xpath", "//Header[@Name='GST Amount']");
                gstAmountClicked = true;
            }
            if (taxType.equalsIgnoreCase("Inclusive")) {
                common.clickElement("xpath", "//CheckBox[@Name='Inclusive Tax Row " + i + "']");
                String taxableValue = common.findWebElement("xpath", "//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']").getText();
                System.out.println("taxable: " + taxableValue);
                String TaxableValue =common.findWebElement("xpath", "//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']").getText();
                System.out.println("taxable: " + taxableValue);
                String actualGSTAmount =common.findWebElement("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']").getText();
                System.out.println("actual GST Amount :-" + actualGSTAmount);
                String actualNetAmount = common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']").getText();
                System.out.println("actual net Amount :-" + actualNetAmount);
                Assert.assertEquals(TaxableValue,common.getData(dataFile,dataSet,expectedTaxibleValue), "GST Amount mismatch for row " + i);
                Assert.assertEquals(actualGSTAmount, common.getData(dataFile,dataSet,expectedGSTValue), "Net Amount mismatch for row " + i);
                Assert.assertEquals(actualNetAmount, common.getData(dataFile,dataSet,expectedNetAmountValue), "GST Amount mismatch for row " + i);
            } else if (taxType.equalsIgnoreCase("Exclusive")) {
                String taxableValue =common.findWebElement("xpath", "//Edit[@Name='Taxable Value Row " + i + ", Not sorted.']").getText();
                System.out.println("taxable: " + taxableValue);
                String actualGSTAmount = common.findWebElement("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']").getText();
                System.out.println("actual GST Amount :-" + actualGSTAmount);
                String actualNetAmount = common.findWebElement("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']").getText();
                System.out.println("actual net Amount :-" + actualNetAmount);
                Assert.assertEquals(taxableValue,common.getData(dataFile,dataSet,expectedTaxibleValue), "GST Amount mismatch for row " + i);
                Assert.assertEquals(actualGSTAmount,common.getData(dataFile,dataSet, expectedGSTValue), "Net Amount mismatch for row " + i);
                Assert.assertEquals(actualNetAmount,common.getData(dataFile,dataSet, expectedNetAmountValue), "GST Amount mismatch for row " + i);
            } else {
                throw new IllegalArgumentException("Invalid Tax Type: " + taxType);
            }
        }
    }

    //transaction related methods
    public void transactionSave() throws InterruptedException {
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        WebDriverWait wait=new WebDriverWait(driver,40);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@Name='Transaction saved.']/Button[@Name='OK']"))).click();
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

    public void navigateToTcs() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'TCS')]");
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

//    public void selectPendingsSalesOrder(String voucherNum,String financialYearNum){
//        List<WebElement> pendings = common.findWebElements("xpath", "//Window[@Name='Open Transactions']//Pane/Table/*[starts-with(@Name,'Row ')]");
//        System.out.println("pendings size: " + pendings.size());
//
//        boolean voucherFound=false;
//        for (int i = 0; i < pendings.size(); i++) {
//            WebElement userRow = pendings.get(i);
////            System.out.println("row text: "+userRow.getText());
////            System.out.println("Row " + i + ": " + userRow.getAttribute("LegacyValue"));
//
//            WebElement voucherNo = userRow.findElement(By.xpath(".//*[starts-with(@Name, 'TowardsVNo Row')]"));
//            WebElement financialYear = userRow.findElement(By.xpath(".//*[starts-with(@Name, 'FinancialYear Row')]"));
//
//            String voucherNoText = voucherNo.getText();
//            String financialYearText = financialYear.getText();
//
//            if (!(voucherNoText.equals(voucherNum) && financialYearText.equals(financialYearNum))) {
//                voucherNo.click();
//                voucherNo.sendKeys(Keys.DOWN);
//            }else {
//                voucherNo.sendKeys(Keys.LEFT,Keys.SPACE);
//                voucherFound=true;
//                break;
//            }
//        }
//        if(!voucherFound) {
//            Assert.fail("Pending Transaction not found. pls check");
//        }
//        common.clickElement("xpath", "//Button[@Name='Ok']");
//    }
public void selectPendingsSalesOrder(String voucherNum, String financialYearNum) {
    // Try to detect the popup
    List<WebElement> popupWindow = common.findWebElements("xpath", "//Window[@Name='Open Transactions']");
    if (popupWindow.isEmpty()) {
        System.out.println("No popup found, moving on.");
        return; // Skip this method's logic if no popup is present
    }
    // Popup found, continue with existing logic
    List<WebElement> pendings = common.findWebElements("xpath", "//Window[@Name='Open Transactions']//Pane/Table/*[starts-with(@Name,'Row ')]");
    System.out.println("pendings size: " + pendings.size());
    boolean voucherFound = false;
    for (int i = 0; i < pendings.size(); i++) {
        WebElement userRow = pendings.get(i);

        WebElement voucherNo = userRow.findElement(By.xpath(".//*[starts-with(@Name, 'TowardsVNo Row')]"));
        WebElement financialYear = userRow.findElement(By.xpath(".//*[starts-with(@Name, 'FinancialYear Row')]"));

        String voucherNoText = voucherNo.getText();
        String financialYearText = financialYear.getText();

        if (!(voucherNoText.equals(voucherNum) && financialYearText.equals(financialYearNum))) {
            voucherNo.click();
            voucherNo.sendKeys(Keys.DOWN);
        } else {
            voucherNo.sendKeys(Keys.LEFT, Keys.SPACE);
            voucherFound = true;
            break;
        }
    }
    if (!voucherFound) {
        Assert.fail("Pending Transaction not found. Please check.");
    }
    common.clickElement("xpath", "//Button[@Name='Ok']");
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
        Thread.sleep(1500);
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

    //overloaded deleted Transaction method
    public void deleteTransactionBasedOnYear(String voucherID) throws InterruptedException {
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
        Thread.sleep(5000);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//Window[@Name='View Transaction")));

            if (element.isDisplayed()) {
                System.out.println("Element is visible.");
            } else {
                System.out.println("Element is present but not visible.");
            }
        } catch (Exception e) {
            System.out.println("Element did not appear within the time limit.");
        }

        common.clickElement("xpath", "//Button[@Name='Tools']");
        common.clickElement("xpath", "//Button[@Name='Delete']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        Thread.sleep(1500);
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

    public void validateAndInactivate(String menuItem,String startWith) throws IOException, ParseException {
        WebElement allGroups = common.findWebElement("xpath", "//TreeItem[@Name='"+menuItem+"']/TreeItem[@Name='All "+ menuItem +"']");
        allGroups.click();
        List<WebElement> listItems = common.findWebElements("xpath", "//Pane[@Name='"+menuItem+"']/Pane/Pane/Pane/Pane/Pane/List/*");
        boolean masterValidation=false;
        for (WebElement items : listItems){
            System.out.println(items.getText());
            if (items.getText().startsWith(startWith)){
                masterValidation=true;
                System.out.println("Master is created successfully - " + items.getText());
                //inactivate it
                items.click();
                Actions actions1 =new Actions(driver);
                actions1.contextClick(items).perform();
                common.clickElement("xpath","//MenuItem[@Name='Inactivate']");
                WebDriverWait wait=new WebDriverWait(driver,5);
                WebElement okButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//Button[@Name='OK']")));
                okButton.click();
                System.out.println("Master Inactivated successfully");
                closeTransaction(menuItem);
            }
        }
        if (!masterValidation) Assert.fail("Master is not validated");
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
        String cgst = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (!(cgst == (null) || "(null)".equals(cgst))) {
            Assert.fail("CGST field is not empty");
        }
    }

    public void validateSGSTAmountTabIsEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'SGST')]");
        String sgst = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (!(sgst == (null) || "(null)".equals(sgst))) {
            Assert.fail("SGST field is not empty");
        }
    }

    public void validateIGSTAmountTabIsEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'IGST')]");
        String igst = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (!(igst == (null) || "(null)".equals(igst))) {
            Assert.fail("IGST field is not empty");
        }
    }

    public void validateCESSAmountTabIsEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'CESS')]");
        String cess = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (!(cess == (null) || "(null)".equals(cess))) {
            Assert.fail("CESS field is not empty");
        }
    }

    public void validateCGSTAmountTabIsNotEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'CGST')]");
        String cgst = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (cgst == (null) || "(null)".equals(cgst)) {
            Assert.fail("CGST field is  empty");
        }
    }
    public void validateRCMCGSTAmountTabIsNotEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'RCM CGST')]");
        String rcmCGST = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (rcmCGST == ("0.000")) {
            Assert.fail("RCMCGST field is  empty");
        }
    }

    public void validateSGSTAmountTabIsNotEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'SGST')]");
        String sgst = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (sgst == (null) || "(null)".equals(sgst)) {
            Assert.fail("SGST field is empty");
        }
    }
    public void validateRCMSGSTAmountTabIsNotEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'RCM SGST')]");
        String rcmSGST = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (rcmSGST == ("0.000")) {
            Assert.fail("RCMSGST field is empty");
        }
    }
    public void sgstPresentInSummary() {
        String sgstPresent = common.findWebElement("xpath", "//Edit[@Name='SGST']").getText();
        if ((sgstPresent == (null) || "(null)".equals(sgstPresent))) {
            Assert.fail("SGST field is empty");
        }
    }
    public void RCMSGSTPresentInSummary() {
        String rcmSGST = common.findWebElement("xpath", "//Edit[@Name='RCM SGST']").getText();
        if ((rcmSGST == ("0.000"))) {
            Assert.fail("RCMSGST field is empty");
        }
    }
    public void cgstPresentInSummary() {
        String cgstPresent = common.findWebElement("xpath", "//Edit[@Name='CGST']").getText();
        if ((cgstPresent == (null) || "(null)".equals(cgstPresent))) {
            Assert.fail("CGST field is empty");
        }
    }
    public void RCMCGSTPresentInSummary() {
        String  rcmCGST = common.findWebElement("xpath", "//Edit[@Name='RCM CGST']").getText();
        if ((rcmCGST == ("0.000"))) {
            Assert.fail("RCMCGST field is empty");
        }
    }

    public void validateIGSTAmountTabIsNotEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'IGST')]");
        String igst = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (igst == ("0.000")) {
            Assert.fail("IGST field is empty");
        }
    }
    public void validateRCMIGSTAmountTabIsNotEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'RCM IGST')]");
        String rcmIGST = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (rcmIGST == ("0.000")) {
            Assert.fail("RCM IGST field is empty");
        }
    }

    public void validateCESSAmountTabIsNotEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'CESS')]");
        String cess = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (cess == (null) || "(null)".equals(cess)) {
            Assert.fail("CESS field is empty");
        }
    }
    public void validateRCMCESSAmountTabIsNotEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'RCM CESS')]");
        String rcmCESS = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (rcmCESS == ("0.000")) {
            Assert.fail("RCM CESS field is empty");
        }
    }

    public void quantityPresentInSummary() {
        String quantity = common.findWebElement("xpath", "//Edit[contains(@Name,'Quantity')]").getText();
        if ((quantity == (null) || "(null)".equals(quantity))) {
            Assert.fail("Quantity field is empty");
        }
    }

    public void grossAmountPresentInSummary() {
        String grossAmount = common.findWebElement("xpath", "//Edit[contains(@Name,'Gross Amount')]").getText();
        if ((grossAmount == (null) || "(null)".equals(grossAmount))) {
            Assert.fail("GrossAmount field is empty");
        }
    }

    public void totalValuePresentInSummary() {
        String  totalValue = common.findWebElement("xpath", "//Edit[@Name='Total Value']").getText();
        if (totalValue == (null) || "(null)".equals(totalValue)) {
            Assert.fail("Total value field is Empty");
        }
    }

    public void totalValueInCompanyCurrenyPresentInSummary() {
        String totalValueCompanyCurrency = common.findWebElement("xpath", "//Edit[@Name='Total Value In Company Currency']").getText();
        if (totalValueCompanyCurrency == (null) || "(null)".equals(totalValueCompanyCurrency)) {
            Assert.fail("Total value in Company Currency field is Empty");
        }
    }

    public void receivableAmountPresentInSummary() {
        String receivableAmount = common.findWebElement("xpath", "//Edit[@Name='Receivable Amount']").getText();
        if (receivableAmount == (null) || "(null)".equals(receivableAmount)) {
            Assert.fail("Receivable Amount field is empty");
        }
    }

    public void tcsTaxableValuePresentInSummary() {
        String tcsTaxableValue = common.findWebElement("xpath", "//Edit[@Name='TCS Taxable Value']").getText();
        if ((tcsTaxableValue == (null) || "(null)".equals(tcsTaxableValue))) {
            Assert.fail(" TCSTaxableValue Present In Summary field is empty");
        }
    }

    public void tcsAmountPresentInSummary() {
        String tcsAmount = common.findWebElement("xpath", "//Edit[@Name='TCS Amount']").getText();
        if ((tcsAmount == (null) || "(null)".equals(tcsAmount))) {
            Assert.fail("TCS Amount field is empty");
        }
    }

    public void netAmountPresentInSummary() {
        String netAmount = common.findWebElement("xpath", "//Edit[@Name='Net Amount']").getText();
        if ((netAmount == (null) || "(null)".equals(netAmount))) {
            Assert.fail("NetAmount field is empty");
        }
    }

    public void chargesPresentInSummary() {
        String charges = common.findWebElement("xpath", "//Edit[@Name='Charges']").getText();
        if ((charges == (null) || "(null)".equals(charges))) {
            Assert.fail("Charges field is empty");
        }
    }

    public void otherChargesPresentInSummary() {
        String otherCharges = common.findWebElement("xpath", "//Edit[@Name='Other Charges']").getText();
        if ((otherCharges == (null) || "(null)".equals(otherCharges))) {
            Assert.fail("Other Charges field is empty");
        }
    }

    public void otherChargesSGSTPresentInSummary() {
        String otherChargesSGST = common.findWebElement("xpath", "//Edit[@Name='Other Charges SGST']").getText();
        if ((otherChargesSGST == (null) || "(null)".equals(otherChargesSGST))) {
            Assert.fail("Other Charges SGST field is empty");
        }
    }

    public void otherChargesCGSTPresentInSummary() {
        String otherChargesCGST = common.findWebElement("xpath", "//Edit[@Name='Other Charges CGST']").getText();
        if ((otherChargesCGST == (null) || "(null)".equals(otherChargesCGST))) {
            Assert.fail("Other Charges CGST field is empty");
        }
    }


    public void grossMinusDiscountPresentInSummary() {
        String grossMinusDiscountAmount = common.findWebElement("xpath", "//Edit[contains(@Name,'Gross - Disc')]").getText();
        if ((grossMinusDiscountAmount == (null) || "(null)".equals(grossMinusDiscountAmount))) {
            Assert.fail("grossMinusDiscountAmount field is empty");
        }
    }

    public void iGSTPresentInSummary() {
        String igstPresent = common.findWebElement("xpath", "//Edit[@Name='IGST']").getText();
        if ((igstPresent == (null) || "(null)".equals(igstPresent))) {
            Assert.fail("IGST Present field is empty");
        }
    }
    public void RCMIGSTPresentInSummary() {
        String rcmIGST = common.findWebElement("xpath", "//Edit[@Name='RCM IGST']").getText();
        if ((rcmIGST == ("0.000"))) {
            Assert.fail("RCM IGST field is empty");
        }
    }

    public void cessPresentInSummary() {
        String cess = common.findWebElement("xpath", "//Edit[@Name='CESS']").getText();
        if ((cess == (null) || "(null)".equals(cess))) {
            Assert.fail("CESS Present field is empty");
        }
    }
    public void RCMCESSPresentInSummary() {
        String rcmCESS = common.findWebElement("xpath", "//Edit[@Name='RCM CESS']").getText();
        if ((rcmCESS == ("0.000"))) {
            Assert.fail("RCM CESS field is empty");
        }
    }
    public void freeQuantityPresentInSummary() {
        String freeQuantity = common.findWebElement("xpath", "//Edit[@Name='Free Quantity']").getText();
        if ((freeQuantity == (null))) {
            Assert.fail("FreeQuantity field is empty");
        }
    }
    public void otherChargesCESSPresentInSummary() {
        String otherChargesCESS = common.findWebElement("xpath", "//Edit[@Name='Other Charges CESS']").getText();
        if ((otherChargesCESS == (null) || "(null)".equals(otherChargesCESS))) {
            Assert.fail("Other Charges CESS field is empty");
        }
    }

    public void otherChargesIGSTPresentInSummary() {
        String  otherChargesIGST = common.findWebElement("xpath", "//Edit[@Name='Other Charges IGST']").getText();
        if ((otherChargesIGST == (null) || "(null)".equals(otherChargesIGST))) {
            Assert.fail("Other Charges IGST field is empty");
        }
    }

    //over-loaded methods
    public void enterData(String locatorType, String locator, String fileName,String dataset, String key) throws IOException, ParseException {
        List<WebElement> elementList = common.findWebElements(locatorType, locator);
//        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            i.click();
            i.sendKeys(common.getData(fileName,dataset, key), Keys.TAB);
            break;
        }
    }
    public void enterInput(String locatorType, String locator, String fileName,String dataset, String key) throws IOException, ParseException {
        List<WebElement> elementList = common.findWebElements(locatorType, locator);
        for (WebElement i : elementList) {
            i.click();
            i.sendKeys(Keys.CONTROL + "a");
            i.sendKeys(Keys.BACK_SPACE);
            i.sendKeys(common.getData(fileName,dataset, key), Keys.TAB);
            break;
        }
    }
    public void enterDataAndValidate(String locatorType, String locator, String fileName, String dataset,String key) throws IOException, ParseException {
        List<WebElement> elementList = common.findWebElements(locatorType, locator);
//        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
//            System.out.println(i.getText());
            i.click();
            i.sendKeys(common.getData(fileName,dataset, key), Keys.TAB);
        }
        WebElement element = common.findWebElement(locatorType, locator);
        if (element.getText().equals(common.getData(fileName,dataset, key))) {
//            System.out.println("successfully selected/opened:- " + element.getText());
        } else {
            Assert.fail(element.getText() + "is not selected");
        }
    }

    public void verifyReport(String transaction, String dataFile,String dataset) throws IOException, ParseException {
        String newTransaction=transaction.replace(" ", "");
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
//            System.out.println("text :" + i.getText());
            if (i.getText().contains(newTransaction)) {
                System.out.println("verifyingRow :");
                bulkVerifyReportData(i.getText(), dataFile,dataset);
            }
        }
    }
    public void bulkVerifyReportData(String text, String dataFile,String dataset) throws IOException, ParseException {
        String[] columns = text.split(";");
        for (int i = 0; i < columns.length; i++) {
            if (i > 2 && !common.getData(dataFile, dataset,"column" + (i + 1)).equals("")) {
                Assert.assertEquals(columns[i], common.getData(dataFile, dataset,"column" + (i + 1)));
            }
            System.out.println(columns[i]);
        }
        System.out.println("Report verified Successfully");
    }
    public void enterBranch(String dataFile,String dataset,String key) throws IOException, ParseException {
        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile,dataset, key);
    }

    public void enterPartyCode(String dataFile,String dataset, String key) throws IOException, ParseException {
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile,dataset, key);
    }

    public void enterPriceList(String dataFile,String dataset, String key) throws IOException, ParseException {
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile,dataset, key);
    }

    public void enterExecutive(String dataFile,String dataset, String key) throws IOException, ParseException {
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile,dataset, key);
    }
}