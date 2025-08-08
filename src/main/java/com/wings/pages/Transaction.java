package com.wings.pages;

import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import com.wings.utils.StringUtil;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public  class Transaction {
    protected WindowsDriver driver;
    protected Common common;
    boolean IsDiscountClicked=false;
    boolean discountIsClicked=false;
    boolean gstAmountClicked=false;
    protected boolean servicesGSTCheckBox=false;
    boolean otherChargesGSTCheckBox=false;
    boolean otherDeductionsGSTCheckBox=false;
    int servicesInclusive = 0;
    int otherChargesInclusive=0;
    int otherDeductionsInclusive=0;
    boolean IsAmountHeaderClicked=false;
    public Transaction(WindowsDriver driver) {
        this.driver = driver;
        this.common = new Common(this.driver);
    }

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
        List<WebElement> elementList = common.findWebElements("xpath", "//Window[@Name='GST Transaction Type']//Table/*[@Name='Data Panel']/*/*[starts-with(@Name,'GST Transaction Type row ')]");
//        System.out.println("Size :" + elementList.size());
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
        System.out.println("Text "+element.getText());
        if (element.getText().equals(inputText)) {
//            System.out.println("entered currect Input :" + element.getText());
        } else {
            Assert.fail("wrong input");
        }
    }

    public void enterPartyCode(String dataFile, String key) throws IOException, ParseException {
        enterInput("xpath", "//Edit[@Name='Party Code']", dataFile, key);
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

    public void navigateToOtherDeductionsTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Other Deductions')]");
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
//        common.clickElement("name", "Sales");
//        common.clickElement("name", "Enquiries");
//         common.clickElement("xpath", "//Menu[@Name='Enquiries']/MenuItem[@Name='Sales Enquiries']");
//        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Enquiries']").getText();
//        System.out.println("Screen Name:-" + pageValidation);
//        Assert.assertEquals(pageValidation, "Sales Enquiries");
        common.clickElement("name", "Reports");
        common.clickElement("name", "Enquiries");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Enquiries']");
    }

    public void navigateToSalesEnquiryCancellationMenu() {
        common.clickElement("name", "Reports");
        common.clickElement("name", "Enquiries");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Enquiries Cancellation']");
        Assert.assertEquals(common.findWebElement("xpath", "//Pane/Text[@Name='Sales Enquiries Cancellation']").getText(), "Sales Enquiries Cancellation");
    }

    public void navigateToSalesQuotationsMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Quotations");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Quotations']");
//        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Quotations']").getText();
//        System.out.println("Screen Name:-" + pageValidation);
//        Assert.assertEquals(pageValidation, "Sales Quotations");
//        common.clickElement("name", "Reports");
//        common.clickElement("name", "Quotations");
//        common.clickElement("xpath", "//MenuItem[@Name='Sales Quotations']");
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
//        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Orders']").getText();
//        System.out.println("Screen Name:-" + pageValidation);
//        Assert.assertEquals(pageValidation, "Sales Orders");
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
//        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Deliveries']").getText();
//        System.out.println("Screen Name:-" + pageValidation);
//        Assert.assertEquals(pageValidation, "Deliveries");
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
//        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Delivery Returns']").getText();
//        System.out.println("Screen Name:-" + pageValidation);
//        Assert.assertEquals(pageValidation, "Delivery Returns");
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
//        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Prices']").getText();
//        System.out.println("Screen Name:-" + pageValidation);
//        Assert.assertEquals(pageValidation, "Sales Prices");
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

    public void navigateToCashTransfersMenu() {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Payments");
        common.clickElement("xpath", "//MenuItem[@Name='Cash Transfers']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Cash Transfers']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Cash Transfers");
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

    public void navigateToPurchaseVouchersAgainstOrders() {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Vouchers against Orders']");
        String validate = common.findWebElement("xpath", "//Text[@Name='Purchase Vouchers against Orders']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Vouchers against Orders", validate);
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
    public void serialNumberProductInStockConsumption(String filename, String dataSet ,String product, int i) throws IOException, ParseException, InterruptedException, AWTException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename,dataSet, product);
        if(common.getData(filename,dataSet,"StockDetails").equals("yes")){
            common.clickElement("xpath", "//Button[@Name='Stock Details Row " + i + "']");
        }
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
        Thread.sleep(500);
        enterInput("xpath","//Edit[@Name='Storage Bin * Row "+i+", Not sorted.']",filename,dataSet,"storageBin");
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

    public void multiBatchProductInStockConsumption(String filename,String dataSet, String product, String quantity,String freeQuantity, int i) throws IOException, ParseException, InterruptedException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename, dataSet,product);
        Thread.sleep(3000);
        if(common.getData(filename,dataSet,"StockDetails").equals("yes")){
            common.clickElement("xpath", "//Button[@Name='Stock Details Row " + i + "']");
        }
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
        Thread.sleep(500);
        enterInput("xpath","//Edit[@Name='Storage Bin * Row "+i+", Not sorted.']",filename,dataSet,"storageBin");
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

    public void serialNumberProductInPurchase(String filename,String dataSet, String product,String serialText,String serialQuantity,String freeQuantity, int i) throws IOException, ParseException{
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename,dataSet, product);
        common.clickElement("xpath", "//Button[@Name='Serial Nos Row "+i+"']");
        WebElement increment = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
        increment.sendKeys(Keys.TAB,common.getData(filename,dataSet, serialText)+ Common.getRandomChar(), Keys.TAB, common.getData(filename,dataSet, serialQuantity),Keys.ENTER);
        if(Boolean.parseBoolean(common.getData(filename,dataSet,"enableFreeQuantity"))) {
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
            WebElement freeQ = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
            freeQ.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, common.getData(filename, freeQuantity),Keys.ENTER);
        }
        common.clickElement("xpath", "//Button[@Name='OK']");
    }

    public void generalProduct_New(String filename,String dataSet, String product, String quantity,String freeQuantity, int i) throws IOException, ParseException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename,dataSet, product);
        common.clickElement("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']");
        enterData("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']", filename,dataSet, quantity);
        if (Boolean.parseBoolean(common.getData(filename,dataSet,"enableFreeQuantity"))){
            enterData("xpath","//Edit[@Name='Free Quantity Row " + i + ", Not sorted.']",filename,dataSet,freeQuantity);
        }
    }

    public void enterProductInOpeningStock(String filename, String dataSet, String product, String quantity, String freeQuantity, int i) throws IOException, ParseException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename,dataSet, product);
        enterInput("xpath","//Edit[@Name='Storage Bin * Row "+i+", Not sorted.']",filename,dataSet,"storageBin");
        common.clickElement("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']");
        enterData("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']", filename,dataSet, quantity);
        if (Boolean.parseBoolean(common.getData(filename,dataSet,"enableFreeQuantity"))){
            enterData("xpath","//Edit[@Name='Free Quantity Row " + i + ", Not sorted.']",filename,dataSet,freeQuantity);
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

    public void multiBatchProductInOpeningStock(String filename,String dataSet,String product,String quantity,String freeQuantity,int i) throws IOException, ParseException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename, dataSet, product);
        enterInput("xpath","//Edit[@Name='Storage Bin * Row "+i+", Not sorted.']",filename,dataSet,"storageBin");
        common.clickElement("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']");
        enterData("xpath", "//Edit[@Name='Quantity * Row "+i+", Not sorted.']", filename, dataSet, quantity);
        if (Boolean.parseBoolean(common.getData(filename, dataSet, "enableFreeQuantity"))) {
            enterData("xpath", "//Edit[@Name='Free Quantity Row " + i + ", Not sorted.']", filename, dataSet, freeQuantity);
        }
    }

    public void serialNumberProductInOpeningStock(String filename,String dataSet,String product,String serialText,String freeQuantity,String serialQuantity, int i) throws IOException, ParseException, InterruptedException, AWTException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename,dataSet, product);
        enterInput("xpath","//Edit[@Name='Storage Bin * Row "+i+", Not sorted.']",filename,dataSet,"storageBin");
        common.clickElement("xpath", "//Button[@Name='Serial Nos Row "+i+"']");
        Thread.sleep(2000);
        WebElement increment = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
        increment.sendKeys(Keys.TAB,common.getData(filename,dataSet, serialText)+ Common.getRandomChar(), Keys.TAB, common.getData(filename,dataSet, serialQuantity),Keys.ENTER);
        if(Boolean.parseBoolean(common.getData(filename,dataSet,"enableFreeQuantity"))) {
            WebElement freeQ = common.findWebElement("xpath", "//CheckBox[@Name='Exclude Box Barcode']");
            freeQ.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, common.getData(filename,dataSet, freeQuantity),Keys.ENTER);
        }
        common.clickElement("xpath", "//Button[@Name='OK']");
    }

    public void serialNumProductInProformaPurchaseVouchers(String filename,String dataSet,String product,String quantity,String freeQuantity,int i) throws IOException, ParseException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row " + i + ", Not sorted.']", filename, dataSet, product);
        enterData("xpath", "//Edit[@Name='Quantity Row "+i+", Not sorted.']", filename, dataSet, quantity);
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

    //calculations methods

    public void enterChargesAndDeductions(String dataFile,String dataset) throws IOException, ParseException {
        navigateToChargesAndDeductionsTab();
        //charges
        enterData("xpath", "//Edit[@Name='Charges Or Deductions * Row 0, Not sorted.']", dataFile,dataset, "charges");
        enterData("xpath", "//Edit[@Name='Account Code Row 0, Not sorted.']", dataFile,dataset, "chargesAccount");
        enterData("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']", dataFile,dataset, "chargesAmount");
        //deductions
        enterData("xpath", "//Edit[@Name='Charges Or Deductions * Row 1, Not sorted.']", dataFile,dataset, "deductions");
        enterData("xpath", "//Edit[@Name='Account Code Row 1, Not sorted.']", dataFile,dataset, "deductionsAccount");
        enterData("xpath", "//Edit[@Name='Amount * Row 1, Not sorted.']", dataFile,dataset, "deductionsAmount");
    }

    public void enterChargesAndDeductionsNoSpace(String dataFile,String dataset) throws IOException, ParseException {
        navigateToChargesAndDeductionsTab();
        //charges
        enterData("xpath", "//Edit[@Name='Chargesor Deductions * Row 0, Not sorted.']", dataFile,dataset, "charges");
        enterData("xpath", "//Edit[@Name='Account Code Row 0, Not sorted.']", dataFile,dataset, "chargesAccount");
        enterData("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']", dataFile,dataset, "chargesAmount");
        //deductions
        enterData("xpath", "//Edit[@Name='Chargesor Deductions * Row 1, Not sorted.']", dataFile,dataset, "deductions");
        enterData("xpath", "//Edit[@Name='Account Code Row 1, Not sorted.']", dataFile,dataset, "deductionsAccount");
        enterData("xpath", "//Edit[@Name='Amount * Row 1, Not sorted.']", dataFile,dataset, "deductionsAmount");
    }

    public void enterOtherCharges(String dataFile,String dataset) throws IOException, ParseException {
        navigateToOtherChargesTab();
        enterData("xpath", "//Edit[@Name='Account Code Row 0, Not sorted.']", dataFile,dataset, "otherChargesAccount");
        enterData("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']", dataFile, dataset,"chargesAmount");
        enterData("xpath", "//Edit[@Name='HSN Row 0, Not sorted.']", dataFile, dataset,"HSNCode");
    }

    public void enterOtherCharges(String dataFile,String dataset,int i) throws IOException, ParseException {
        navigateToOtherChargesTab();
        enterData("xpath", "//Edit[@Name='Account Code Row "+i+", Not sorted.']", dataFile,dataset, "otherChargesAccount"+i);
        if (otherChargesInclusive < 3) {
            common.clickElement("xpath", "//CheckBox[@Name='Inclusive Tax Row " + i + "']");
            otherChargesInclusive++;
        }
        enterData("xpath", "//Edit[@Name='Amount * Row "+i+", Not sorted.']", dataFile, dataset,"otherChargesAmount"+i);
        enterData("xpath", "//Edit[@Name='HSN Row "+i+", Not sorted.']", dataFile, dataset,"HSNCode"+i);
        enterData("xpath", "//Edit[@Name='GST Product Category Row "+i+", Not sorted.']", dataFile,dataset, "GSTPercentage"+i);
        enterData("xpath", "//Edit[@Name='CESS Product Category Row "+i+", Not sorted.']", dataFile,dataset, "CESSPercentage"+i);
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Taxable Value Row "+i+", Not sorted.']"), common.getData(dataFile, dataset,"expectedOtherChargesTaxableValue" + i),"taxable value mismatch");
        if (!otherChargesGSTCheckBox) {
            common.clickElement("xpath", "//Header[@Name='GST Amount']");
            otherChargesGSTCheckBox = true;
        }
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='IGST Row " + i + ", Not sorted.']"), common.getData(dataFile, dataset,"expectedOtherChargesIGSTAmount" + i),"IGST mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='CESS Row " + i + ", Not sorted.']"), common.getData(dataFile,dataset, "expectedOtherChargesCESSAmount" +i),"CESS mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='GST Amount Row " + i + ", Not sorted.']"), common.getData(dataFile,dataset, "expectedOtherChargesGSTAmount" +i), "GST Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Net Amount Row " + i + ", Not sorted.']"), common.getData(dataFile, dataset,"expectedOtherChargesNetAmount" +i), "Net Amount mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='Net Amount In Company Currency Row "+i+", Not sorted.']"), common.getData(dataFile, dataset,"expectedOtherChargesNetAmountInCompanyCurrency" +i), "Net Amount in Company currency mismatch");
        Assert.assertEquals(common.getText("xpath", "//Edit[@Name='TCS Taxable Value Row "+i+", Not sorted.']"), common.getData(dataFile, dataset,"expectedOtherChargesTCSTaxableValue" +i), "Tcs Taxable value mismatch");

        enterData("xpath", "//Edit[@Name='Department Row "+i+", Not sorted.']", dataFile,dataset, "department"+i);
        enterData("xpath", "//Edit[@Name='Project Row "+i+", Not sorted.']", dataFile,dataset, "project"+i);
        enterData("xpath", "//Edit[@Name='Profit Centre Row "+i+", Not sorted.']", dataFile,dataset, "profitCentre"+i);
        enterData("xpath", "//Edit[@Name='Cost Centre Row "+i+", Not sorted.']", dataFile,dataset, "costCentre"+i);
        enterData("xpath", "//Edit[@Name='Comments Row "+i+", Not sorted.']", dataFile,dataset, "comments"+i);
    }


    public void termsAndConditions(String dataFile,String dataset) throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Terms And Conditions')]");
        enterInput("xpath","//Edit[@Name='Term Type * Row 0, Not sorted.']",dataFile,dataset,"termType");
        enterInput("xpath","//Edit[@Name='Term * Row 0, Not sorted.']",dataFile,dataset,"term");
        enterInput("xpath","//Edit[@Name='Comments Row 0, Not sorted.']",dataFile,dataset,"comments");
    }
    public void termsAndConditions(String dataFile,String dataset,int i) throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Terms And Conditions')]");
        enterInput("xpath","//Edit[@Name='Term Type * Row "+i+", Not sorted.']",dataFile,dataset,"termType"+i);
        enterInput("xpath","//Edit[@Name='Term * Row "+i+", Not sorted.']",dataFile,dataset,"term"+i);
        enterInput("xpath","//Edit[@Name='Comments Row "+i+", Not sorted.']",dataFile,dataset,"comments");
    }
    public void enterOtherInfo(){
        navigateToOtherInfoTab();
        common.findWebElement("xpath","//Edit[@Name='Reference Bill No']").sendKeys("89"+common.getRandom());
        common.findWebElement("xpath","//Edit[@Name='Reference Bill Date']").sendKeys(Time.timeStamp());
    }
    public void enterCash(String dataFile,String dataset) throws IOException, ParseException {
        navigateToCashTab();
        enterInput("xpath","//Edit[@Name='Cash Account Code Row 0, Not sorted.']",dataFile,dataset,"cashAccount");
        enterInput("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,dataset,"cashAmount");
    }
    public void enterCashinSIAO(String dataFile,String dataset) throws IOException, ParseException {
        navigateToCashTab();
        enterInput("xpath","//Edit[@Name='Cash Account Code Row 0, Not sorted.']",dataFile,dataset,"cashAccount");
        enterInput("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,dataset,"chargesAmount");
        enterInput("xpath","//Edit[@Name='TDS Transaction Nature Row 0, Not sorted.']",dataFile,dataset,"tdsTransactionNature");
        enterInput("xpath","//Edit[@Name='TDS Account Row 0, Not sorted.']",dataFile,dataset,"tdsAccount");
        enterInput("xpath","//Edit[@Name='TDS Amount Row 0, Not sorted.']",dataFile,dataset,"tdsAmount");

    }

    public void enterPostDatedCheques(String dataFile,String dataset) throws IOException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(1).getText());
        elements.get(1).click();
        enterInput("xpath","//Edit[@Name='PDC Account Code Row 0, Not sorted.']",dataFile,dataset,"pdcAccount");
        enterInput("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,dataset,"postDatedChequeAmount");
        common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
        enterInput("xpath","//Edit[@Name='Drawn On Bank * Row 0, Not sorted.']",dataFile,dataset,"drawnOn");
        enterInput("xpath","//Edit[@Name='Drawn On Bank Branch Row 0, Not sorted.']",dataFile,dataset,"drawnOnBranch");
    }

    public  void navigateToItemsOtherCosts(String dataFile,String dataSet) throws IOException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Other Costs')]");
        elements.get(1).click();
        WebElement sixthEdit = common.findWebElement("xpath", "//Edit[contains(@AutomationId,'OtherCost')]");
        String legacyValue = sixthEdit.getAttribute("LegacyValue");
//        System.out.println("6th edit legacy value: " + legacyValue);
        Assert.assertEquals(legacyValue, common.getData(dataFile, dataSet, "expectedItemsOtherCostsAmount"), "ItemsOtherCostsAmount Mismatch");
//        System.out.println("worked this assertion for Items Other Charges");
    }

    public  void navigateToItemsOtherCosts() throws IOException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Other Costs')]");
//        System.out.println(elements.get(1).getText());
        elements.get(1).click();
//        Assert.assertEquals(common.findWebElement("xpath", "//Pane//following-sibling::edit[6]").getAttribute("LegacyValue"), common.getData(dataFile, dataSet, "expectedItemsOtherCostsAmount"), "ItemsOtherCostsAmount Mismatch");
//        System.out.println("worked this assertion");
    }

    public void enterItemsOtherCosts(String dataFile,String dataset,int i) throws IOException, ParseException, InterruptedException {
        common.clickElement("xpath","//Button[@Name='Item Other Cost Row "+i+"']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Window[@Name='Item Other Cost Details']/Table[@Name='Item Other Cost Details']/*[@Name='Data Panel']/*[@Name='NewItem Row']/*[@Name='ExpenseType newitem row']");

//        List<WebElement> expenseCodeRowList = common.findWebElements("xpath", "//Table[@Name='itemsOtherCostAmount']/Edit[contains(@Name,'Item Other Cost Details ')]/Edit[starts-with(@Name,'Editing control')]");
//        List<WebElement> vendorRowList = common.findWebElements("xpath", "//Table[@Name='itemsOtherCostAmount']/Edit[contains(@Name,'Item Other Cost Details ')]/Edit[starts-with(@Name,'Editing control')]");
//        List<WebElement> otherCostCurrencyRowList = common.findWebElements("xpath", "//Table[@Name='itemsOtherCostAmount']/Edit[contains(@Name,'Item Other Cost Details ')]/Edit[starts-with(@Name,'Editing control')]");
//        List<WebElement> itemsOtherCostsRowList = common.findWebElements("xpath", "//Table[@Name='itemsOtherCostAmount']/Edit[contains(@Name,'Item Other Cost Details ')]/Edit[starts-with(@Name,'Editing control')]");
//        enterData(expenseCodeRowList.get(i),dataFile,dataset,"expenseCode"+i);
//        enterData(expenseCodeRowList.get(i),dataFile,dataset,"vendor");
//        enterData(expenseCodeRowList.get(i),dataFile,dataset,"otherCostCurrency");
//        enterData(expenseCodeRowList.get(i),dataFile,dataset,"itemsOtherCostAmount"+i);

        driver.findElementByXPath("//Edit[@Name='Editing control']").sendKeys(common.getData(dataFile,dataset,"expenseCode"+i), Keys.TAB);
        driver.findElementByXPath("//Edit[@Name='Editing control']").sendKeys(common.getData(dataFile,dataset,"vendor"), Keys.TAB);
        driver.findElementByXPath("//Edit[@Name='Editing control']").sendKeys(common.getData(dataFile,dataset,"otherCostCurrency"), Keys.TAB);
        driver.findElementByXPath("//Edit[@Name='Editing control']").sendKeys(common.getData(dataFile,dataset,"itemsOtherCostAmount"+i), Keys.TAB);

//        enterData("xpath", "//Edit[@Name='Editing control']", dataFile,dataset, "expenseCode"+i);
//        enterData("xpath", "//Edit[@Name='Editing control']", dataFile, dataset,"vendor");
//        enterData("xpath", "//Edit[@Name='Editing control']", dataFile, dataset,"otherCostCurrency");
//        enterData("xpath", "//Edit[@Name='Editing control']", dataFile, dataset,"itemsOtherCostAmount"+i);
        common.clickElement("xpath","//Button[@Name='OK']");
    }
    public void enterPostDatedChequesinSIAO(String dataFile,String dataset) throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Post Dated Cheques')]");
        enterInput("xpath","//Edit[@Name='PDC Account Code Row 0, Not sorted.']",dataFile,dataset,"pdcAccount");
        enterInput("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,dataset,"chargesAmount");
        common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
        enterInput("xpath","//Edit[@Name='Drawn On Bank * Row 0, Not sorted.']",dataFile,dataset,"drawnOn");
        enterInput("xpath","//Edit[@Name='Drawn On Bank Branch Row 0, Not sorted.']",dataFile,dataset,"drawnOnBranch");
        enterInput("xpath","//Edit[@Name='TDS Transaction Nature Row 0, Not sorted.']",dataFile,dataset,"tdsTransactionNature");
        enterInput("xpath","//Edit[@Name='TDS Account Row 0, Not sorted.']",dataFile,dataset,"tdsAccount");
        enterInput("xpath","//Edit[@Name='TDS Amount Row 0, Not sorted.']",dataFile,dataset,"tdsAmount");
    }

    public void enterCheques(String dataFile,String dataset) throws IOException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(0).getText());
        elements.get(0).click();
        enterInput("xpath","//Edit[@Name='Bank Account Code Row 0, Not sorted.']",dataFile,dataset,"bankAccount");
        enterInput("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,dataset,"chequeAmount");
        common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
        enterInput("xpath","//Edit[@Name='Drawn On Bank * Row 0, Not sorted.']",dataFile,dataset,"drawnOn");
        enterInput("xpath","//Edit[@Name='Drawn On Bank Branch Row 0, Not sorted.']",dataFile,dataset,"drawnOnBranch");
        enterInput("xpath","//Edit[@Name='Charges Account Code Row 0, Not sorted.']",dataFile,dataset,"chargesAccount");
        enterInput("xpath","//Edit[@Name='Charges Row 0, Not sorted.']",dataFile,dataset,"chargesAmount");
    }

    public void enterChequesinSRWIRF(String dataFile,String dataset) throws IOException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(0).getText());
        elements.get(0).click();
        enterInput("xpath","//Edit[@Name='Bank Account Code Row 0, Not sorted.']",dataFile,dataset,"bankAccount");
        enterInput("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,dataset,"chequeAmount");
        common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
        enterInput("xpath","//Edit[@Name='Charges Account Code Row 0, Not sorted.']",dataFile,dataset,"chargesAccount");
        enterInput("xpath","//Edit[@Name='Charges Row 0, Not sorted.']",dataFile,dataset,"chargesAmount");
    }
    public void enterChequesinSIAO(String dataFile,String dataset) throws IOException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(0).getText());
        elements.get(0).click();
        enterInput("xpath","//Edit[@Name='Bank Account Code Row 0, Not sorted.']",dataFile,dataset,"bankAccount");
        enterInput("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,dataset,"chargesAmount");
        common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
        enterInput("xpath","//Edit[@Name='Drawn On Bank * Row 0, Not sorted.']",dataFile,dataset,"drawnOn");
        enterInput("xpath","//Edit[@Name='Drawn On Bank Branch Row 0, Not sorted.']",dataFile,dataset,"drawnOnBranch");
        enterInput("xpath","//Edit[@Name='TDS Transaction Nature Row 0, Not sorted.']",dataFile,dataset,"tdsTransactionNature");
        enterInput("xpath","//Edit[@Name='TDS Account Row 0, Not sorted.']",dataFile,dataset,"tdsAccount");
        enterInput("xpath","//Edit[@Name='TDS Amount Row 0, Not sorted.']",dataFile,dataset,"tdsAmount");
        enterInput("xpath","//Edit[@Name='Charges Account Code Row 0, Not sorted.']",dataFile,dataset,"chargesAccount");
        enterInput("xpath","//Edit[@Name='Charges Row 0, Not sorted.']",dataFile,dataset,"chargesAmount");
    }

    public void enterChequesPDCInPurchaseReturns(String dataFile,String key) throws IOException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(2).getText());
        elements.get(2).click();
        enterInput("xpath","//Edit[@Name='Bank Account Code Row 0, Not sorted.']",dataFile,key,"bankAccount");
        enterInput("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,key,"chequesPDCAmount");
        common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
        enterInput("xpath","//Edit[@Name='Drawn On Bank * Row 0, Not sorted.']",dataFile,key,"drawnOn");
        enterInput("xpath","//Edit[contains(@Name,'Drawn On Bank Branch Row 0, Not sorted.')]",dataFile,key,"drawnOnBranch");
    }

    public void enterChequesPDCinSIAO(String dataFile,String dataset) throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Cheques [PDC]')]");
        enterInput("xpath","//Edit[@Name='Bank Account Code Row 0, Not sorted.']",dataFile,dataset,"bankAccountCode");
        enterInput("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,dataset,"chargesAmount");
        common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
        enterInput("xpath","//Edit[@Name='Drawn On Bank * Row 0, Not sorted.']",dataFile,dataset,"drawnOn");
        enterInput("xpath","//Edit[@Name='Drawn On Bank Branch Row 0, Not sorted.']",dataFile,dataset,"drawnOnBranch");
        enterInput("xpath","//Edit[@Name='TDS Transaction Nature Row 0, Not sorted.']",dataFile,dataset,"tdsTransactionNature");
        enterInput("xpath","//Edit[@Name='TDS Account Row 0, Not sorted.']",dataFile,dataset,"tdsAccount");
        enterInput("xpath","//Edit[@Name='TDS Amount Row 0, Not sorted.']",dataFile,dataset,"tdsAmount");
    }

    public void enterCreditCardinSIAO(String dataFile,String dataset) throws IOException, ParseException, InterruptedException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Credit Card')]");
        enterInput("xpath","//Edit[@Name='Swipe Machine Type * Row 0, Not sorted.']",dataFile,dataset,"swipeMachineType");
        enterInput("xpath","//Edit[@Name='Swipe Type * Row 0, Not sorted.']",dataFile,dataset,"swipeType");
        enterInput("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,dataset,"chargesAmount");
        enterInput("xpath","//Edit[@Name='TDS Transaction Nature Row 0, Not sorted.']",dataFile,dataset,"tdsTransactionNature");
        enterInput("xpath","//Edit[@Name='TDS Account Row 0, Not sorted.']",dataFile,dataset,"tdsAccount");
        enterInput("xpath","//Edit[@Name='TDS Amount Row 0, Not sorted.']",dataFile,dataset,"tdsAmount");
        common.findWebElement("xpath","//Edit[@Name='Card No Row 0, Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
        Thread.sleep(1500);
        WebElement approvalNo=common.findWebElement("xpath","//Edit[@Name='Approval No * Row 0, Not sorted.']");
        approvalNo.click();
        approvalNo.sendKeys(String.valueOf(common.getRandom()));
    }

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
        Thread.sleep(1000);
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
//        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//Window[@Name='Message']/Button[@Name='OK']"))).click();
//        common.clickElement("xpath", "//Window[@Name='Message']/Button[@Name='OK']");
    }
    public void saveMasterOrProperty(){
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
    }
    public void scrollRight(int iterations){
        for (int i=0;i<iterations;i++){
            common.clickElement("xpath","//Button[@Name='Scroll Right']");
        }
    }

    public void moveToRight(int iterations) throws AWTException {
        for (int i=0;i<iterations;i++){
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_RIGHT);
            robot.keyRelease(KeyEvent.VK_RIGHT);
        }
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

//    public void exportTransaction(WindowsDriver rootDriver, String stepName, String prefix, String number) throws Exception {
//        navigateToMastersWhen3Steps("Tools", "Automated Testing", stepName);
//        rootDriver = common.initializeDriver("Root");
//        Thread.sleep(3000);
//
//        common.findWebElement("xpath", "//Window[@Name='Export Transaction Postings']/Pane/Edit[@Name='Voucher Series']").sendKeys(prefix);
//        common.findWebElement("xpath", "//Window[@Name='Export Transaction Postings']/Pane/Edit[@Name='Voucher Number']").sendKeys(number);
//        common.clickElement("xpath", "//Button[@Name='OK']");
//        Thread.sleep(2000);
//
//        common.clickElement("xpath", "//Window[@Name='Export Transaction Postings']/Window[@Name='Export to Excel']/Button[@Name='OK']");
//        Thread.sleep(1500);
//
//        String message = common.findWebElement("xpath", "//Text").getText();
//        if (message.equals("Data Exported successfully!")) {
//            common.clickElement("xpath", "//Button[@Name='OK']");
//        } else if (message.equals("Transactionno doesnot exist.")) {
//            common.clickElement("xpath", "//Button[@Name='OK']");
//            Assert.fail("Transaction does not exist");
//        }
//    }
//
//    public void generateInputAndOutputFiles(String prefix, String number) throws Exception {
//        exportTransaction("Generate Input File", prefix, number);
//        Thread.sleep(2000);
//        exportTransaction("Generate Output File", prefix, number);
//    }


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
        System.out.println("Size :"+elementList.size());
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
//                System.out.println("Checkbox was unchecked, now checked.");
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
            } else Assert.fail("Check Box is not selected");
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
            if (value == (null) || "(null)".equals(value)) {
                // value.isEmpty() ||
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
    public void analysisReport( String dataFile,String dataSet) throws IOException, ParseException {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println("text :" + i.getText());
            bulkVerifyAnalysisReport(i.getText(),dataFile,dataSet);
        }
    }
    public void bulkVerifyAnalysisReport(String text, String dataFile,String dataSet) throws IOException, ParseException {
        String[] columns = text.split(";");
        System.out.println("columSize :"+columns.length);
        for (int i = 0; i < columns.length; i++) {
            if (i >=1 && !common.getData(dataFile, dataSet,"column" + (i + 1)).equals("")) {
                Assert.assertEquals(columns[i], common.getData(dataFile,dataSet, "column" + (i + 1)),"columns mismatch");
            }
            System.out.println(columns[i]);
        }
        System.out.println("Report verified Successfully");
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

    public void validateBatchDetailsTabIsNotEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Batch Details')]");
        String batchDetails = common.findWebElement("xpath", "//Edit[@Name='Batch * Row 0, Not sorted.']").getText();
        if (batchDetails == (null) || "(null)".equals(batchDetails)) {
            Assert.fail("Batch details tab item empty");
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
    public void grossAmountMinusDiscountPresentInSummary() {
        String grossAmountMinusDiscountAmount = common.findWebElement("xpath", "//Edit[contains(@Name,'Gross Minus Discount')]").getText();
        if ((grossAmountMinusDiscountAmount == (null) || "(null)".equals(grossAmountMinusDiscountAmount))) {
            Assert.fail("grossMinusDiscountAmount field is empty");
        }
    }
    public void enterOtherInfo(String dataFile,String dataset) throws IOException, ParseException, InterruptedException {
        navigateToOtherInfoTab();
        common.findWebElement("xpath","//Edit[@Name='Reference Bill No']").sendKeys(String.valueOf(common.getRandom()));
        common.findWebElement("xpath","//Edit[@Name='Reference Bill Date']").sendKeys(Time.timeStamp());
        Thread.sleep(1000);
        enterInput("xpath", "//Edit[@Name='Other Info 1']", dataFile, dataset, "otherInfo1");
        enterInput("xpath", "//Edit[@Name='Other Info 2']", dataFile, dataset, "otherInfo2");
    }

    public void enterAdditionalInfo(String dataFile,String dataset) throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Additional Information  ')]");
        enterInput("xpath", "//Edit[@Name='Info 1']", dataFile, dataset, "otherInfo1");
        enterInput("xpath", "//Edit[@Name='Value 1']", dataFile, dataset, "value0");
        common.findWebElement("xpath","//Edit[@Name='Date 1']").sendKeys(Time.timeStamp());
    }

    public void selectPendingPurchaseOrder(String voucherNum, String financialYearNum) {
        // Try to detect the popup
        List<WebElement> popupWindow = common.findWebElements("xpath", "//Window[@Name='Purchase Quotations']");
        if (popupWindow.isEmpty()) {
            System.out.println("No popup found, moving on.");
            return; // Skip this method's logic if no popup is present
        }
        // Popup found, continue with existing logic
        List<WebElement> pendings = common.findWebElements("xpath", "//Window[@Name='Purchase Quotations']//Pane/Table/*[starts-with(@Name,'Row ')]");
        System.out.println("pendings size: " + pendings.size());
        boolean voucherFound = false;
        for (int i = 0; i < pendings.size(); i++) {
            WebElement userRow = pendings.get(i);
            WebElement voucherNo = userRow.findElement(By.xpath(".//*[starts-with(@Name,'TowardsVNo Row')]"));
            String voucherNoText = voucherNo.getText();
            System.out.println(voucherNoText);
            if (!(voucherNoText.equals(voucherNum) )) {
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
    public void enterPostDatedChequesInPurchase(String dataFile,String dataSet) throws IOException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
//        System.out.println(elements.get(1).getText());
        elements.get(1).click();
//        common.clickElement("xpath","//TabItem[contains(@Name,'Post Dated Cheques')]");
        enterInput("xpath","//Edit[@Name='Bank Account Code Row 0, Not sorted.']",dataFile,dataSet,"bankAccountCode");
        enterInput("xpath","//Edit[@Name='PDC Account * Row 0, Not sorted.']",dataFile,dataSet,"pdcAccount");
        enterInput("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,dataSet,"chequeAmount");
        common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
    }

    public void enterPostDatedChequesInPRWIR(String dataFile,String dataSet) throws IOException, ParseException, InterruptedException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(1).getText());
        elements.get(1).click();
        Thread.sleep(1000);
        enterInput("xpath","//Edit[@Name='PDC Account Code Row 0, Not sorted.']",dataFile,dataSet,"pdcAccount");
        enterInput("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,dataSet,"chequeAmount");
        common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
        Thread.sleep(1000);
        enterInput("xpath","//Edit[@Name='Drawn On Bank * Row 0, Not sorted.']",dataFile,dataSet,"drawnBankAccCode");
    }


    public void enterChequesPDCInPurchase(String dataFile,String key) throws IOException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
//        System.out.println(elements.get(2).getText());
        elements.get(2).click();
//        common.clickElement("xpath","//TabItem[contains(@Name,'Cheques [PDC]')]");
        enterInput("xpath","//Edit[@Name='Bank Account Code Row 0, Not sorted.']",dataFile,key,"bankAccountCode");
        enterInput("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,key,"chequeAmount");
        common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
    }

    public void enterChequesPDCInPRWIR(String dataFile,String dataSet) throws IOException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(2).getText());
        elements.get(2).click();
        enterInput("xpath","//Edit[@Name='Bank Account Code Row 0, Not sorted.']",dataFile,dataSet,"bankAccountCode");
        enterInput("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,dataSet,"chequeAmount");
        common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
        enterInput("xpath","//Edit[@Name='Drawn On Bank * Row 0, Not sorted.']",dataFile,dataSet,"drawnBankAccCode");
    }
    public void enterChequesInPurchase(String dataFile,String dataSet) throws IOException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
//        System.out.println(elements.get(0).getText());
        elements.get(0).click();
        enterInput("xpath","//Edit[@Name='Bank Account Code Row 0, Not sorted.']",dataFile,dataSet,"bankAccountCode");
        enterInput("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,dataSet,"chequeAmount");
        common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
        enterInput("xpath","//Edit[@Name='Charges Account Code Row 0, Not sorted.']",dataFile,dataSet,"chargesAcc");
        enterInput("xpath","//Edit[@Name='Charges Row 0, Not sorted.']",dataFile,dataSet,"chargesAmount");
    }
    public void enterChequesInPRWIR(String dataFile,String dataSet) throws IOException, ParseException, InterruptedException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(elements.get(0).getText());
        elements.get(0).click();
        enterInput("xpath","//Edit[@Name='Bank Account Code Row 0, Not sorted.']",dataFile,dataSet,"bankAccountCode");
        enterInput("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,dataSet,"chequeAmount");
        common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
        enterInput("xpath","//Edit[@Name='Charges Account Code Row 0, Not sorted.']",dataFile,dataSet,"chargesAcc");
        enterInput("xpath","//Edit[@Name='Charges Row 0, Not sorted.']",dataFile,dataSet,"chargesAmount");
        Thread.sleep(1000);
        enterInput("xpath","//Edit[@Name='Drawn On Bank * Row 0, Not sorted.']",dataFile,dataSet,"drawnBankAccCode");
    }
    public void deductionsPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String deductions = common.findWebElement("xpath", "//Edit[@Name='Deductions']").getText();
        if ((deductions == (null) || "(null)".equals(deductions))) {
            Assert.fail("Charges field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Deductions",duration/1000000000);
    }
    public void deductionsPresentInSummary(String expectedDeductionsAmount) throws IOException {
        long start = System.nanoTime();
        Assert.assertEquals(common.findWebElement("xpath", "//Edit[@Name='Deductions']").getText(),expectedDeductionsAmount,"Deduction Amount mismatch in summary");
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Deduction",duration/1000000000);
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

    public void quantityPresentInSummary() throws IOException {

        long start = System.nanoTime();
        String quantity = common.findWebElement("xpath", "//Edit[contains(@Name,'Quantity')]").getText();
        if ((quantity == (null) || "(null)".equals(quantity))) {
            Assert.fail("Quantity field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("quantity",duration/1000000000);
    }

    public void quantityPresentInSummary(String expectedQuantity) throws IOException {
        long start = System.nanoTime();
        Assert.assertEquals(common.findWebElement("xpath", "//Edit[contains(@Name,'Quantity')]").getText(),expectedQuantity,"Quantity mismatch in summary");
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Quantity",duration/1000000000);
    }

    public void inputQuantityPresentInSummary() {
        String inputQuantity = common.findWebElement("xpath", "//Edit[contains(@Name,'Input Quantity *')]").getText();
        if ((inputQuantity == (null) || "(null)".equals(inputQuantity))) {
            Assert.fail("inputQuantity field is empty");
        }
    }

    public void grossAmountPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String grossAmount = common.findWebElement("xpath", "//Edit[contains(@Name,'Gross Amount')]").getText();
        if ((grossAmount == (null) || "(null)".equals(grossAmount))) {
            Assert.fail("GrossAmount field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Gross amount",duration/1000000000);
    }

    public void grossAmountPresentInSummary(String expectedGrossAmount) throws IOException {
        long start = System.nanoTime();
        Assert.assertEquals(common.findWebElement("xpath", "//Edit[contains(@Name,'Gross Amount')]").getText(),expectedGrossAmount,"GrossAmount mismatch in summary");
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Gross amount",duration/1000000000);
    }

    public void totalValuePresentInSummary() throws IOException {
        long start = System.nanoTime();
        String  totalValue = common.findWebElement("xpath", "//Edit[@Name='Total Value']").getText();
        if (totalValue == (null) || "(null)".equals(totalValue)) {
            Assert.fail("Total value field is Empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Total value",duration/1000000000);
    }

    public void totalValuePresentInSummary(String expectedTotalValueAmount) throws IOException {
        long start = System.nanoTime();
        Assert.assertEquals(common.findWebElement("xpath", "//Edit[@Name='Total Value']").getText(),expectedTotalValueAmount,"Total value mismatch in summary");
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Total value",duration/1000000000);
    }

    public void totalValueInCompanyCurrenyPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String totalValueCompanyCurrency = common.findWebElement("xpath", "//Edit[@Name='Total Value In Company Currency']").getText();
        if (totalValueCompanyCurrency == (null) || "(null)".equals(totalValueCompanyCurrency)) {
            Assert.fail("Total value in Company Currency field is Empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Total value in company currency",duration/1000000000);
    }

    public void receivableAmountPresentInSummary() {
        String receivableAmount = common.findWebElement("xpath", "//Edit[@Name='Receivable Amount']").getText();
        if (receivableAmount == (null) || "(null)".equals(receivableAmount)) {
            Assert.fail("Receivable Amount field is empty");
        }
    }

    public void tcsTaxableValuePresentInSummary() throws IOException {
        long start = System.nanoTime();
        String tcsTaxableValue = common.findWebElement("xpath", "//Edit[@Name='TCS Taxable Value']").getText();
        if ((tcsTaxableValue == (null) || "(null)".equals(tcsTaxableValue))) {
            Assert.fail(" TCSTaxableValue Present In Summary field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Taxable value",duration/1000000000);
    }

    public void tcsTaxableValuePresentInSummary(String expectedTCSTaxableValue) throws IOException {
        long start = System.nanoTime();
        Assert.assertEquals(common.findWebElement("xpath", "//Edit[@Name='TCS Taxable Value']").getText(),expectedTCSTaxableValue,"TCS taxable value mismatch in summary");
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("TCS taxable value",duration/1000000000);
    }

    public void tcsAmountPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String tcsAmount = common.findWebElement("xpath", "//Edit[@Name='TCS Amount']").getText();
        if ((tcsAmount == (null) || "(null)".equals(tcsAmount))) {
            Assert.fail("TCS Amount field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("TCS amount",duration/1000000000);
    }

    public void tcsAmountPresentInSummary(String expectedTCSAmount) throws IOException {
        long start = System.nanoTime();
        Assert.assertEquals(common.findWebElement("xpath", "//Edit[@Name='TCS Amount']").getText(),expectedTCSAmount,"TCS amount mismatch in summary");
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("TCS amount",duration/1000000000);
    }

    public void tdsAmountPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String tdsAmount = common.findWebElement("xpath", "//Edit[@Name='TDS Amount']").getText();
        if ((tdsAmount == (null) || "(null)".equals(tdsAmount))) {
            Assert.fail("TCS Amount field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("TDS amount",duration/1000000000);
    }

    public void payableAfterTdsPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String payableAfterTds = common.findWebElement("xpath", "//Edit[@Name='Payable After TDS']").getText();
        if ((payableAfterTds == "0.000")) {
            Assert.fail("Payable After TDS Amount field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Payable after TDS",duration/1000000000);
    }

    public void netAmountPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String netAmount = common.findWebElement("xpath", "//Edit[@Name='Net Amount']").getText();
        if ((netAmount == (null) || "(null)".equals(netAmount))) {
            Assert.fail("NetAmount field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("NetAmount",duration/1000000000);
    }

    public void netAmountPresentInSummary(String expectedNetAmount) throws IOException {
        long start = System.nanoTime();
        Assert.assertEquals(common.findWebElement("xpath", "//Edit[@Name='Net Amount']").getText(),expectedNetAmount,"NetAmount mismatch in summary");
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("NetAmount",duration/1000000000);
    }

    public void chargesPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String charges = common.findWebElement("xpath", "//Edit[@Name='Charges']").getText();
        if ((charges == (null) || "(null)".equals(charges))) {
            Assert.fail("Charges field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Charges",duration/1000000000);
    }

    public void chargesPresentInSummary(String expectedChargesAmount) throws IOException {
        long start = System.nanoTime();
        Assert.assertEquals(common.findWebElement("xpath", "//Edit[@Name='Charges']").getText(),expectedChargesAmount,"Charges amount mismatch");
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Charges amount mismatch",duration/1000000000);
    }

    public void otherChargesPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String otherCharges = common.findWebElement("xpath", "//Edit[@Name='Other Charges']").getText();
        if ((otherCharges == "0.000" )) {
            Assert.fail("Other Charges field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Other Charges",duration/1000000000);
    }

    public void otherChargesPresentInSummary(String expectedOtherChargesAmount) throws IOException {
        long start = System.nanoTime();
        Assert.assertEquals(common.findWebElement("xpath", "//Edit[@Name='Other Charges']").getText(),expectedOtherChargesAmount,"Other charges mismatch in summary");
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Other Charges",duration/1000000000);
    }

    public void otherChargesSGSTPresentInSummary() {
        String otherChargesSGST = common.findWebElement("xpath", "//Edit[@Name='Other Charges SGST']").getText();
        if ((otherChargesSGST == (null) || "(null)".equals(otherChargesSGST))) {
            Assert.fail("Other Charges SGST field is empty");
        }
    }

    public void servicesAmountPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String serviceAmount = common.findWebElement("xpath", "//Edit[@Name='Service Amount']").getText();
        if ((serviceAmount == "0.000" )) {
            Assert.fail("Service Amount  field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Service amount",duration/1000000000);
    }

    public void servicesIGSTPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String serviceIGST = common.findWebElement("xpath", "//Edit[@Name='Services IGST']").getText();
        if ((serviceIGST == "0.000" )) {
            Assert.fail("Service IGST  field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Services IGST",duration/1000000000);
    }

    public void servicesCESSPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String servicesCESS = common.findWebElement("xpath", "//Edit[@Name='Services CESS']").getText();
        if ((servicesCESS == "0.000" )) {
            Assert.fail("Service CESS  field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Services CESS",duration/1000000000);
    }

    public void otherCostsAmountPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String otherCosts = common.findWebElement("xpath", "//Edit[@Name='Other Cost Amount']").getText();
        if ((otherCosts == "0.000" )) {
            Assert.fail("Service CESS  field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Other Costs Amount",duration/1000000000);
    }

    public void otherCostsPresentInSummary() {
        String otherCosts = common.findWebElement("xpath", "//Edit[@Name='Other Costs']").getText();
        if ((otherCosts == "0.000" )) {
            Assert.fail("Service CESS  field is empty");
        }
    }

    public void otherChargesCGSTPresentInSummary() {
        String otherChargesCGST = common.findWebElement("xpath", "//Edit[@Name='Other Charges CGST']").getText();
        if ((otherChargesCGST == (null) || "(null)".equals(otherChargesCGST))) {
            Assert.fail("Other Charges CGST field is empty");
        }
    }

    public void grossMinusDiscountPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String grossMinusDiscountAmount = common.findWebElement("xpath", "//Edit[contains(@Name,'Gross - Disc')]").getText();
        if ((grossMinusDiscountAmount == (null) || "(null)".equals(grossMinusDiscountAmount))) {
            Assert.fail("grossMinusDiscountAmount field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Gross-Discount",duration/1000000000);
    }

    public void grossMinusDiscountPresentInSummary(String expectedGrossMinusDiscount) throws IOException {
        long start = System.nanoTime();
        Assert.assertEquals(common.findWebElement("xpath", "//Edit[contains(@Name,'Gross - Disc')]").getText(),expectedGrossMinusDiscount,"Gross minus discount mismatch in summary");
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Gross minus discount ",duration/1000000000);
    }

    public void iGSTPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String igstPresent = common.findWebElement("xpath", "//Edit[@Name='IGST']").getText();
        if ((igstPresent == (null) || "(null)".equals(igstPresent))) {
            Assert.fail("IGST Present field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("IGST",duration/1000000000);
    }

    public void iGSTPresentInSummary(String expectedIGST) throws IOException {
        long start = System.nanoTime();
        Assert.assertEquals(common.findWebElement("xpath", "//Edit[@Name='IGST']").getText(),expectedIGST,"IGST mismatch in summary");
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("IGST",duration/1000000000);
    }

    public void RCMIGSTPresentInSummary() {
        String rcmIGST = common.findWebElement("xpath", "//Edit[@Name='RCM IGST']").getText();
        if ((rcmIGST == ("0.000"))) {
            Assert.fail("RCM IGST field is empty");
        }
    }

    public void initiateStockQuantityPresentInSummary() {
        String initiateStockQuantity = common.findWebElement("xpath", "//Edit[@Name='Initiate Stock Quantity *']").getText();
        if ((initiateStockQuantity == "0.000" )) {
            Assert.fail("initiateStockQuantity field is empty");
        }
    }

    public void physicalStockQuantityPresentInSummary() {
        String physicalStockQuantity = common.findWebElement("xpath", "//Edit[@Name='Physical Stock Quantity']").getText();
        if ((physicalStockQuantity == "0.000" )) {
            Assert.fail("physicalStockQuantity field is empty");
        }
    }

    public void cashPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String cash = common.findWebElement("xpath", "//Edit[@Name='Cash']").getText();
        if ((cash == "0.000" )) {
            Assert.fail("Charges field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Cash",duration/1000000000);
    }

    public void chequesPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String cheques = common.findWebElement("xpath", "//Edit[@Name='Cheques']").getText();
        if ((cheques == "0.000" )) {
            Assert.fail("Charges field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Cheques",duration/1000000000);
    }

    public void chequesPDCPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String chequesPDC = common.findWebElement("xpath", "//Edit[@Name='Cheques [PDC]']").getText();
        if ((chequesPDC == "0.000" )) {
            Assert.fail("Charges field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Cheques PDC",duration/1000000000);
    }

    public void paymentsPresentInSummary() {
        String payments = common.findWebElement("xpath", "//Edit[@Name='Payment']").getText();
        if ((payments == (null) || "(null)".equals(payments))) {
            Assert.fail("Charges field is empty");
        }
    }
    public void paymentsValuePresentInSummary() throws IOException {
        long start = System.nanoTime();
        String paymentsValue = common.findWebElement("xpath", "//Edit[@Name='Payments Value']").getText();
        if ((paymentsValue == "0.000")) {
            Assert.fail("Charges field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Payments value",duration/1000000000);
    }

    public void receiptsValuePresentInSummary() {
        String receiptsValue = common.findWebElement("xpath", "//Edit[@Name='Receipts Value']").getText();
        if ((receiptsValue == "0.000")) {
            Assert.fail("Charges field is empty");
        }
    }

    public void payableAMountPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String  payableAMount = common.findWebElement("xpath", "//Edit[@Name='Payable Amount']").getText();
        if (( payableAMount == "0.000")) {
            Assert.fail("Charges field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Payable amount",duration/1000000000);
    }

    public void receivableMountPresentInSummary() {
        String  receivableMount = common.findWebElement("xpath", "//Edit[@Name='Receivable Amount']").getText();
        if (( receivableMount == "0.000")) {
            Assert.fail("Charges field is empty");
        }
    }

    public void grossAmountInCompanyCurrencyPresentInSummary() {
        String grossAmount = common.findWebElement("xpath", "//Edit[contains(@Name,'Gross Amount In Company Currency')]").getText();
        if ((grossAmount == "0.000")) {
            Assert.fail("GrossAmount field is empty");
        }
    }
    public void taxableOtherChargesPresentInSummary() {
        String taxableOtherCharges = common.findWebElement("xpath", "//Edit[@Name='Taxable Other Charges']").getText();
        if ((taxableOtherCharges == (null) || "(null)".equals(taxableOtherCharges))) {
            Assert.fail("Other Charges field is empty");
        }
    }
    public void postDatedChequesPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String postDatedCheques = common.findWebElement("xpath", "//Edit[@Name='Post Dated Cheques']").getText();
        if ((postDatedCheques== "0.000")) {
            Assert.fail("Charges field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Post dated cheques",duration/1000000000);
    }

    public void cessPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String cess = common.findWebElement("xpath", "//Edit[@Name='CESS']").getText();
        if ((cess == (null) || "(null)".equals(cess))) {
            Assert.fail("CESS Present field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("CESS",duration/1000000000);
    }

    public void cessPresentInSummary(String expectedCESS) throws IOException {
        long start = System.nanoTime();
        Assert.assertEquals(common.findWebElement("xpath", "//Edit[@Name='CESS']").getText(),expectedCESS,"CESS mismatch in summary");
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("CESS",duration/1000000000);
    }

    public void RCMCESSPresentInSummary() {
        String rcmCESS = common.findWebElement("xpath", "//Edit[@Name='RCM CESS']").getText();
        if ((rcmCESS == ("0.000"))) {
            Assert.fail("RCM CESS field is empty");
        }
    }
    public void freeQuantityPresentInSummary(String expectedFreeQuantity) throws IOException {
        long start = System.nanoTime();
        Assert.assertEquals(common.findWebElement("xpath", "//Edit[@Name='Free Quantity']").getText(),expectedFreeQuantity,"FreeQuantity mismatch in summary");
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("FreeQuantity",duration/1000000000);
    }
    public void freeQuantityPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String freeQ=common.findWebElement("xpath", "//Edit[@Name='Free Quantity']").getText();
        Assert.assertEquals(freeQ,"expectedFreeQuantity mismatch in summary");
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("expectedFreeQuantity",duration/1000000000);
    }
    public void otherChargesCESSPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String otherChargesCESS = common.findWebElement("xpath", "//Edit[@Name='Other Charges CESS']").getText();
        if ((otherChargesCESS == (null) || "(null)".equals(otherChargesCESS))) {
            Assert.fail("Other Charges CESS field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Other Charges CESS",duration/1000000000);
    }

    public void otherChargesCESSPresentInSummary(String expectedOtherChargesCESS) throws IOException {
        long start = System.nanoTime();
        Assert.assertEquals(common.findWebElement("xpath", "//Edit[@Name='Other Charges CESS']").getText(),expectedOtherChargesCESS,"Other Charges CESS mismatch in summary");
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Other Charges CESS",duration/1000000000);
    }
    public void otherChargesIGSTPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String  otherChargesIGST = common.findWebElement("xpath", "//Edit[@Name='Other Charges IGST']").getText();
        if ((otherChargesIGST == (null) || "(null)".equals(otherChargesIGST))) {
            Assert.fail("Other Charges IGST field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Other Charges IGST",duration/1000000000);
    }

    public void enterData( WebElement element,String fileName,String sheetName, String key) throws IOException, ParseException {
        element.click();
        element.sendKeys(common.getData(fileName,sheetName, key), Keys.TAB);
    }

    // after move to Excel
    public static List<String> readExcelData(String filePath, String sheetName, String columnName) {
        List<String> columnData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            Workbook workbook;
            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (filePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis);
            } else {
                System.out.println("Invalid file format. Please provide an .xls or .xlsx file.");
                return columnData;
            }
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.out.println("Sheet not found: " + sheetName);
                return columnData;
            }
            Row headerRow = sheet.getRow(0);
            int columnIndex = -1;
            // Find the index of the specified column
            for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                Cell cell = headerRow.getCell(i);
                if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
                    columnIndex = i;
                    break;
                }
            }
            if (columnIndex == -1) {
                System.out.println("Column not found: " + columnName);
                return columnData;
            }
            // Read the data from the specified column
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell cell = row.getCell(columnIndex);
                    if (cell != null) {
                        columnData.add(cell.toString());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return columnData;
    }

    public static List<String> decimalNumberData(String filePath, String sheetName, String columnName) {
        List<String> columnData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            Workbook workbook;
            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (filePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis);
            } else {
                System.out.println("Invalid file format. Please provide an .xls or .xlsx file.");
                return columnData;
            }
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.out.println("Sheet not found: " + sheetName);
                return columnData;
            }

            Row headerRow = sheet.getRow(0);
            int columnIndex = -1;
            // Find the column index by header name
            for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                Cell cell = headerRow.getCell(i);
                if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
                    columnIndex = i;
                    break;
                }
            }
            if (columnIndex == -1) {
                System.out.println("Column not found: " + columnName);
                return columnData;
            }
            // Read the column values properly formatted
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell cell = row.getCell(columnIndex);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING:
                                columnData.add(cell.getStringCellValue().trim());
                                break;
                            case NUMERIC:
                                // Format as integer if there's no decimal, else keep 2 decimal places
                                double val = cell.getNumericCellValue();
                                if (val == Math.floor(val)) {
                                    columnData.add(String.valueOf((int) val));  // "90"
                                } else {
                                    columnData.add(String.format("%.2f", val)); // "90.25"
                                }
                                break;
                            case BOOLEAN:
                                columnData.add(String.valueOf(cell.getBooleanCellValue()));
                                break;
                            case FORMULA:
                                columnData.add(cell.getCellFormula());
                                break;
                            default:
                                columnData.add(""); // Or handle as needed
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return columnData;
    }

    public String getValueByColumnHeader(String filePath, String sheetName, String headerName) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            System.out.println("❌ Sheet not found: " + sheetName);
            workbook.close();
            return null;
        }
        // Read the header row (assumed to be the first row: index 0)
        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            System.out.println("❌ Header row is empty");
            workbook.close();
            return null;
        }
        int targetColumnIndex = -1;
        // Find the column index for the header name
        for (Cell cell : headerRow) {
            cell.setCellType(CellType.STRING);
            if (cell.getStringCellValue().trim().equalsIgnoreCase(headerName.trim())) {
                targetColumnIndex = cell.getColumnIndex();
                break;
            }
        }
        if (targetColumnIndex == -1) {
            System.out.println("❌ Column not found for header: " + headerName);
            workbook.close();
            return null;
        }
        // Get the first data row (assumed to be row 1)
        Row dataRow = sheet.getRow(1);
        if (dataRow == null) {
            System.out.println("❌ Data row is empty");
            workbook.close();
            return null;
        }
        Cell valueCell = dataRow.getCell(targetColumnIndex);
        if (valueCell == null) {
            System.out.println("⚠️ Value cell is null under header: " + headerName);
            workbook.close();
            return null;
        }
        String value = "";
        if (valueCell.getCellType() == CellType.STRING) {
            value = valueCell.getStringCellValue();
        } else if (valueCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(valueCell)) {
            value = new SimpleDateFormat("dd-MM-yyyy").format(valueCell.getDateCellValue());
        } else {
            value = valueCell.toString();
        }
        workbook.close();
        return value;
    }

    public List<String> getValuesByColumnHeader(String filePath, String sheetName, String headerName) throws IOException {
        List<String> values = new ArrayList<>();

        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            System.out.println("❌ Sheet not found: " + sheetName);
            workbook.close();
            return values;
        }

        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            System.out.println("❌ Header row is empty");
            workbook.close();
            return values;
        }

        int targetColumnIndex = -1;

        for (Cell cell : headerRow) {
            cell.setCellType(CellType.STRING);
            if (cell.getStringCellValue().trim().equalsIgnoreCase(headerName.trim())) {
                targetColumnIndex = cell.getColumnIndex();
                break;
            }
        }

        if (targetColumnIndex == -1) {
            System.out.println("❌ Column not found for header: " + headerName);
            workbook.close();
            return values;
        }

        // Start from row 1 (skip header)
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Cell valueCell = row.getCell(targetColumnIndex);
            if (valueCell == null) continue;

            String value;
            if (valueCell.getCellType() == CellType.STRING) {
                value = valueCell.getStringCellValue();
            } else if (valueCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(valueCell)) {
                value = new SimpleDateFormat("dd-MM-yyyy").format(valueCell.getDateCellValue());
            } else {
                value = valueCell.toString();
            }

            values.add(value.trim());
        }

        workbook.close();
        return values;
    }

    public void EnterData(String locator,String dataFile,String sheetName,String key) {
        WebElement element=common.findWebElement("xpath",locator);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.BACK_SPACE);
        List<String> columnData = readExcelData(dataFile, sheetName, key);
        if (!columnData.isEmpty()) {
            element.sendKeys(columnData.get(0),Keys.TAB);
        }
    }

    public void EnterData(String locator,String dataFile,String sheetName,String key,int j) {
        WebElement element=common.findWebElement("xpath",locator);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.BACK_SPACE);
        List<String> columnData = readExcelData(dataFile, sheetName, key);
        if (!columnData.isEmpty()) {
            element.sendKeys(columnData.get(j),Keys.TAB);
        }
    }

    public void decimalPrecision(String locator,String dataFile,String sheetName,String key) {
        WebElement element=common.findWebElement("xpath",locator);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.BACK_SPACE);
        List<String> columnData = decimalNumberData(dataFile, sheetName, key);
        if (!columnData.isEmpty()) {
            element.sendKeys(columnData.get(0),Keys.TAB);
        }
    }


    public void addData( String locatorType,String locator, String fileName,String sheetName, String key,int j) {
        List<WebElement> elementList = common.findWebElements(locatorType, locator);
        for (WebElement i : elementList) {
            i.click();
            List<String> columnData = readExcelData(fileName, sheetName,key);
            if (!columnData.isEmpty()) {
                i.sendKeys(columnData.get(j),Keys.TAB);
            }
        }
    }

    public void enterListData( WebElement element,String fileName,String sheetName, String columnName,int j) throws IOException {
        List<String> dataList = readExcelData(fileName, sheetName, columnName);
        if (!dataList.isEmpty()) {
            element.click();
            element.sendKeys(dataList.get(j),Keys.TAB);
        }
    }

    public void enterListDate( WebElement element,String fileName,String sheetName, String columnName,int j) throws IOException {
        List<String> dataList = getValuesByColumnHeader(fileName, sheetName, columnName);
        System.out.println(dataList.size());
        if (!dataList.isEmpty()) {
            element.click();
            element.sendKeys(dataList.get(j),Keys.TAB);
        }
    }

    public void clickListData(WebElement element){
        element.click();
    }

    //over-loaded methods
    public void enterData(String locatorType, String locator, String fileName,String dataset, String key) throws IOException, ParseException {
        List<WebElement> elementList = common.findWebElements(locatorType, locator);
        for (WebElement i : elementList) {
            i.click();
            i.sendKeys(common.getData(fileName,dataset, key), Keys.TAB);
            break;
        }
    }

    public void enterInput(String locatorType, String locator, String fileName,String dataset, String key) throws IOException, ParseException {
        List<WebElement> elementList = common.findWebElements(locatorType, locator);
        for (WebElement i : elementList) {
//            i.click();
            i.sendKeys(Keys.CONTROL + "a");
            i.sendKeys(Keys.BACK_SPACE);
            i.sendKeys(common.getData(fileName,dataset, key), Keys.TAB);
            break;
        }
    }

    public void enterServices(String dataFile,String dataset) throws IOException, ParseException, InterruptedException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Services')]");
        enterData("xpath", "//Edit[@Name='Service Code Row 0, Not sorted.']", dataFile,dataset, "servicesCode");
        enterData("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']", dataFile, dataset,"servicesQuantity");
        Thread.sleep(1000);
        enterData("xpath", "//Edit[@Name='Rate Row 0, Not sorted.']", dataFile, dataset,"servicesRate");
        enterData("xpath", "//Edit[@Name='HSN Row 0, Not sorted.']", dataFile, dataset,"HSNCode");
    }

    public void chargesAndDeductionsCalculations1(String dataFile, String dataSet,String type,String type2, String chargesAccCode,String deductionsAccCode,String chargesAmount, String deductionsAmount, String iterations) throws IOException, ParseException {
        navigateToChargesAndDeductionsTab();
        int totalIterations = Integer.parseInt(common.getData(dataFile, dataSet, iterations));
        for (int i = 0; i < totalIterations; i++) {
            // Enter Charges Amount if not empty
            if (chargesAmount != null && !chargesAmount.trim().isEmpty()) {
                enterData("xpath", "//Edit[@Name='Account Code Row " + i + ", Not sorted.']", dataFile, dataSet, chargesAccCode);
                enterData("xpath", "//Edit[@Name='Charges Or Deductions * Row " + i + ", Not sorted.']", dataFile, dataSet, type);
                enterData("xpath", "//Edit[@Name='Amount * Row " + i + ", Not sorted.']", dataFile, dataSet, chargesAmount);
                String chargeAmount = common.getData(dataFile, dataSet, chargesAmount);
                String chargesValue = common.findWebElement("xpath", "//Edit[@Name='Charges Row "+i+", Not sorted.']").getText();
                Assert.assertEquals(chargeAmount,chargesValue,"Amount Mismatch");
            }
            // Enter Deductions Amount if not empty
            if (deductionsAmount != null && !deductionsAmount.trim().isEmpty()) {
                // Move to next row for deductions
                i++;
                enterData("xpath", "//Edit[@Name='Account Code Row " + i + ", Not sorted.']", dataFile, dataSet, deductionsAccCode);
                enterData("xpath", "//Edit[@Name='Charges Or Deductions * Row " + i + ", Not sorted.']", dataFile, dataSet, type2);
                enterData("xpath", "//Edit[@Name='Amount * Row " + i + ", Not sorted.']", dataFile, dataSet, deductionsAmount);
                String deductAmount = common.getData(dataFile, dataSet, deductionsAmount);
                String deductionsValue = common.findWebElement("xpath", "//Edit[@Name='Deductions Row "+i+", Not sorted.']").getText();
                Assert.assertEquals(deductAmount,deductionsValue,"Amount Mismatch");
            }
        }
    }
    public void validateTDS(String dataFile,String dataSet) throws IOException, ParseException {
        navigateToTDSTab();
        Assert.assertEquals(common.findWebElement("xpath", "//Edit[@Name='Assessable Value']").getText(), common.getData(dataFile, dataSet, "expectedTdsAssessableValue"), "TDS Assessable value Mismatch");
        Assert.assertEquals(common.findWebElement("xpath", "//Edit[@Name='TDS Amount']").getText(), common.getData(dataFile, dataSet, "expectedTdsAmount"), "TDS amount Mismatch");
    }

    public void navigateToTDSTab(){
        common.clickElement("xpath","//TabItem[contains(@Name,'TDS  ')]");

    }
    public void enterOtherCosts(String dataFile,String dataset) throws IOException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Other Costs ')]");
        System.out.println(elements.get(0).getText());
        elements.get(0).click();
        enterData("xpath", "//Edit[@Name='Expense Type Code Row 0, Not sorted.']", dataFile,dataset, "expenseCode");
        enterData("xpath", "//Edit[@Name='Vendor Code Row 0, Not sorted.']", dataFile, dataset,"vendor");
        enterData("xpath", "//Edit[@Name='Currency Row 0, Not sorted.']", dataFile, dataset,"otherCostCurrency");
        enterData("xpath", "//Edit[@Name='Other Cost * Row 0, Not sorted.']", dataFile, dataset,"otherCostAmount");
    }
    public void validateTCS(String dataFile,String dataSet) throws IOException, ParseException {
        navigateToTCSTab();
        Assert.assertEquals(common.findWebElement("xpath","//Edit[@Name='Assessable Value']").getText(),common.getData(dataFile, dataSet, "expectedTCsAssessableAmount"),"TCS Assessable Mismatch");
        Assert.assertEquals(common.findWebElement("xpath","//Edit[@Name='TCS Amount']").getText(),common.getData(dataFile, dataSet, "expectedTcsAmount"),"TCS Mismatch");
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

    public void verifyReportProductWise(String transaction, String dataFile, String dataset, List<String> products) throws IOException, ParseException {
        String newTransaction = transaction.replace(" ", "");
        System.out.println("Transaction ID: " + newTransaction);
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("Report Rows Found: " + elementList.size());

        for (String product : products) {
            boolean found = false;
            for (WebElement element : elementList) {
                String rowText = element.getText();
                System.out.println("Row Text: " + rowText);

                if (rowText.contains(newTransaction) && rowText.contains(product)) {
                    System.out.println("Verifying row for product: " + product);
                    bulkVerifyReportData(rowText, dataFile, dataset);
                    found = true;
                    break; // Remove this `break` if multiple rows per product need checking
                }
            }
            if (!found) System.out.println("No report row found for product: " + product);
        }
    }

    public void verifyAnalysisReportProductWise(String dataFile,String dataset, List<String> products) throws IOException, ParseException {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("Report Rows Found: " + elementList.size());
        for (String product:products) {
            boolean found = false;
            for (WebElement element : elementList) {
                String rowText = element.getText();
//                System.out.println(rowText);
                if (rowText.contains(product)) {
                    System.out.println("Verifying row for product: " + product);
                    bulkVerifyReportData(rowText, dataFile, dataset);
                    found = true;
                    break; // Remove this `break` if multiple rows per product need checking
                }
            }
            if (!found) System.out.println("No report row found for product: " + product);
        }

    }

    public void bulkVerifyReportData(String text, String dataFile,String dataset) throws IOException, ParseException {
        String[] columns = text.split(";");
        System.out.println(columns);
        for (int i = 0; i < columns.length; i++) {
            if (i > 2 && !common.getData(dataFile, dataset,"column" + (i + 1)).equals("")) {
                Assert.assertEquals(columns[i], common.getData(dataFile, dataset,"column" + (i + 1)));
            }
            System.out.println(columns[i]);
        }
        System.out.println("Report verified Successfully");
    }

    public void enterBranch(String dataFile,String sheetName,String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Branch *']", dataFile,sheetName, key);
//        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile,dataset, key);
    }

    public void enterMonthDerived(String xpath, String value) {
        WebElement element = driver.findElement(By.xpath(xpath));
        element.clear();
        element.sendKeys(value);
    }

    public void enterYearSalesTarget(String dataFile, String sheetName, String key) throws InterruptedException, AWTException, IOException {
        String yearStr = getValueByColumnHeader(dataFile, sheetName, key);

        try {
            double yearDouble = Double.parseDouble(yearStr); // handles 2025.0
            int year = (int) yearDouble;                     // converts to 2025
            enterMonthDerived("//Edit[@Name='Year *']", String.valueOf(year));
            Thread.sleep(1500); // wait for UI to process entry
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid year format in Excel: " + yearStr);
        }
    }
    public void enterMonthSalesTarget(String dataFile,String sheetName,String key) throws IOException {
        String monthNumberStr = getValueByColumnHeader(dataFile, sheetName, key);
        try {
            double monthDouble = Double.parseDouble(monthNumberStr);
            int monthNumber = (int) monthDouble;

            if (monthNumber >= 1 && monthNumber <= 12) {
                String[] months = {
                        "January", "February", "March", "April", "May", "June",
                        "July", "August", "September", "October", "November", "December"
                };
                String monthName = months[monthNumber - 1];
                enterMonthDerived("//Edit[@Name='Month *']",monthName); // or your original method with UI locator
                Thread.sleep(1500);
                Robot robot=new Robot();
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            } else {
                System.out.println("Invalid month number: " + monthNumber);
            }
        } catch (Exception e) {
            System.out.println("Invalid month format in Excel: " + monthNumberStr);
        }
    }

    public void exportIOFiles(String inputOrOutput,String voucherSeries, String voucherNumber) throws IOException, InterruptedException {
        navigateToMastersWhen3Steps("Tools", "Automated Testing", inputOrOutput);
        driver=common.initializeDriver("Root");
        Thread.sleep(3000);
        common.findWebElement("xpath","//Edit[@Name='Voucher Series']").sendKeys(voucherSeries);
        common.findWebElement("xpath","//Edit[@Name='Voucher Number']").sendKeys(voucherNumber);
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(3000);
//        common.clickElement("xpath","//Window[@Name='Export Transaction Postings']/Window[@Name='Export to Excel']/Button[@Name='OK']");
        Thread.sleep(1500);
        if (common.findWebElement("xpath","//Text").getText().equals("Data Exported successfully!")) {
            common.findWebElement("xpath","//Button[@Name='OK']").click();
        } else if(common.findWebElement("xpath","//Text").getText().equals("Transactionno doesnot exist.")){
            Assert.fail("Transaction does not exists");
            common.clickElement("xpath", "//Button[@Name='OK']");
        }
    }


    public void enterVoucherDiscount(String dataFile,String sheetName,String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Voucher Disc %']", dataFile,sheetName, key);
//        enterInput("xpath", "//Edit[@Name='Branch *']", dataFile,dataset, key);
    }

    public void enterPriceList(String dataFile,String sheetName, String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Price List'] | //Edit[@Name='Price List *']",dataFile,sheetName,key);
    }

    public void enterMasterType(String dataFile,String sheetName, String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Master Type']",dataFile,sheetName,key);
    }

    public void enterBasisSalesPrice(String dataFile,String sheetName, String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Basis']",dataFile,sheetName,key);
    }


    public void enterAmountSalesPrice(String dataFile,String sheetName, String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Amount']",dataFile,sheetName,key);
    }

    public void enterExecutive(String dataFile,String sheetName, String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Executive *']",dataFile,sheetName,key);
//        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile,dataset, key);
    }

    public void validateStockLedger(String fileName,String dataSet) throws IOException, ParseException, InterruptedException {

        for (int i=0; i < Integer.parseInt(common.getData(fileName,dataSet,"productCount")); i++){
            navigateToMastersWhen3Steps("Inventory","Stock","Stock Ledger");
            Thread.sleep(1000);
            enterInput("xpath","//Tab[@Name='Stock Ledger']/Pane/Pane/Pane/Pane/Edit",fileName,dataSet,"product"+i);
            common.clickElement("xpath","//TabItem[@Name='Branch']");
            common.clickElement("xpath","//Tab[@Name='Stock Ledger']/Pane/Pane/Pane/List[@Name=' All ']/RadioButton[@Name=' One ']");
            enterInput("xpath","//Tab[@Name='Stock Ledger']/Pane/Pane/Pane/Pane/Edit",fileName,dataSet,"branch");
            common.clickElement("xpath","//TabItem[@Name='Location']");
            common.clickElement("xpath","//Tab[@Name='Stock Ledger']/Pane/Pane/Pane/List[@Name=' All ']/RadioButton[@Name=' One ']");
            enterInput("xpath","//Tab[@Name='Stock Ledger']/Pane/Pane/Pane/Pane/Edit",fileName,dataSet,"location");
            common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
//            Thread.sleep(1500);
//            closeReport("Stock Ledger");
        }
    }

    public void reportDesigner(WindowsDriver rootDriver,String option,String operator,String value) throws IOException, InterruptedException {
        common.clickElement("xpath", "//Button[@Name='Designer']");
        common.clickElement("xpath", "//TabItem[@Name='Data']");
        common.clickElement("xpath", "//Button[@Name='Filter']");
        rootDriver=common.initializeDriver("Root");
        System.out.println("root navigated");
        Thread.sleep(2000);
        List<WebElement> columnsList=common.findWebElements("xpath","//Window[@Name='Filter']/Table/*[@Name='Data Panel']/ListItem[starts-with(@Name,'Row ')]");
        System.out.println("size :"+columnsList.size());
        for (int i=0;i<=8;i++){
            WebElement v=columnsList.get(i);
            System.out.println(v.getText());
            if (v.getText().contains(option)){
                v.click();
                v.sendKeys(Keys.ARROW_RIGHT,operator,Keys.ENTER,Keys.ARROW_RIGHT,value);
            }
        }
        common.clickElement("xpath", "//Button[@Name='Apply']");
        System.out.println("completed");
    }

    public void enterCustomerEmail(String dataFile,String dataSet,String email) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Customer Email']",dataFile,dataSet,email);
    }

    public void enterShippingBillNo(String dataFile,String dataSet,String shippingBillNo) throws IOException, ParseException {
        enterInput("xpath","//Edit[@Name='Shipping Bill No']",dataFile,dataSet, shippingBillNo);
    }

    public void enterShippingDate(String dataFile,String sheetName,String key) throws IOException, ParseException {
        EnterData ("//Edit[@Name='Shipping Bill Date']",dataFile,sheetName, key);
    }

    public void assertSummaryFields(List<WebElement> summary, String targetName, String expectedData) {
        Optional<WebElement> targetElement = summary.stream().filter(el -> targetName.equals(el.getAttribute("Name"))).findFirst();
        Assert.assertEquals(targetElement.get().getText(),expectedData,targetName+"mismatch in summary");
    }

//    public String getValueByKeyFromSheet(String filePath, String sheetName, String key) throws IOException {
//        FileInputStream fileInputStream = new FileInputStream(filePath);
//        Workbook workbook = WorkbookFactory.create(fileInputStream);
//        Sheet sheet = workbook.getSheet(sheetName);
//        if (sheet == null) {
//            workbook.close();
//            throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in the Excel file.");
//        }
//        Row headerRow = sheet.getRow(0); // Header row
//        Row valueRow = sheet.getRow(1);  // First data row
//        if (headerRow == null || valueRow == null) {
//            workbook.close();
//            throw new IllegalArgumentException("Sheet format is invalid. Expected header at row 0 and data at row 1.");
//        }
//        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
//            Cell headerCell = headerRow.getCell(i);
//            if (headerCell != null && headerCell.getStringCellValue().equalsIgnoreCase(key)) {
//                Cell valueCell = valueRow.getCell(i);
//                workbook.close();
//                return valueCell != null ? valueCell.toString() : null;
//            }
//        }
//        workbook.close();
//        throw new IllegalArgumentException("Key '" + key + "' not found in sheet '" + sheetName + "'.");
//    }

}