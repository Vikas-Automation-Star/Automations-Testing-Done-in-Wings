package com.wings.pages;

import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import com.wings.utils.Time;
import io.appium.java_client.windows.WindowsDriver;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public  class Transaction {
    protected WindowsDriver driver,rootDriver;
    protected Common common;
    protected boolean servicesGSTCheckBox=false;
    int servicesInclusive = 0;

    public Transaction(WindowsDriver driver) {
        this.common = new Common(this.driver = driver);
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
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().equals(gstType)) {
                i.click();
                i.sendKeys(Keys.LEFT, Keys.SPACE, Keys.ENTER, Keys.ENTER);
                break;
            }
        }
    }

    public void referenceBillNumberTDSPayments(String voucherNum) throws InterruptedException, MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "Root");
        rootDriver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), capabilities);
        Thread.sleep(1500);
        List<WebElement> panes = By.tagName("Window").findElements(rootDriver);
        Thread.sleep(2500);
        if (panes.isEmpty()) {
            System.out.println("No windows found.");
        } else {
            for (WebElement i : panes) {
                String name = i.getAttribute("Name");
                System.out.println("Name:- " + name);
                if (name.equals("Reference Bill No Details")) {
                    // Find rows inside the table
                    List<WebElement> elementList = common.findWebElements("xpath", "//Window[@Name='Reference Bill No Details']//Table/*[starts-with(@Name,'Row')]/*[starts-with(@Name,'VoucherNo Row ')]");
                    System.out.println("Size :" + elementList.size());
                    boolean voucherFound = false;
                    if (!elementList.isEmpty()) {
                        WebElement firstRow = elementList.get(0);
                        firstRow.click(); // Focus the table

                        for (int iRow = 0; iRow < elementList.size(); iRow++) {
                            WebElement currentRow = elementList.get(iRow);
                            String rowText = currentRow.getAttribute("LegacyValue");
//                            System.out.println("Row Text: " + rowText);
                            if (rowText.equals(voucherNum)){
                                voucherFound=true;
                                currentRow.click();
                                currentRow.sendKeys(Keys.TAB,Keys.TAB,Keys.SPACE);
                            } else {
                                currentRow.click();
                                currentRow.sendKeys(Keys.DOWN);
                            }
                        }
                    }
                    if (!voucherFound) {
                        System.out.println("Voucher not found in Reference Bill No Details. Failing test.");
                        Assert.fail("Voucher number " + voucherNum + " not found in Reference Bill No Details.");
                    }
                    common.clickElement("xpath", "//Button[@Name='Ok']");
                    break; // Exit after handling the correct window
                }
            }
        }
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


    public void navigateToTCSTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'TCS')]");
    }


    public void navigateToCashTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Cash')]");
    }

    public void navigateToParties() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Parties  ')]");
    }

    public void navigateToInvoiceDetails() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Invoice Details  ')]");
    }

    public void navigateToCreditCard() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Credit Card  ')]");
    }

    public void navigateToPineLib() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Pine Lab  ')]");
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

    public void navigateToShippingAddress() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Shipping Address  ')]");
    }

    public void navigateToCheques() {
        List<WebElement> Cheques=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(Cheques.get(0).getText());
        Cheques.get(0).click();
    }

    public void navigateToPostdatedCheques() {
        List<WebElement> PostdatedCheques=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(PostdatedCheques.get(1).getText());
        PostdatedCheques.get(1).click();
    }

    public void navigateToChequesPDC() {
        List<WebElement> ChequesPDC=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
        System.out.println(ChequesPDC.get(2).getText());
        ChequesPDC.get(2).click();
    }

    public void navigateToAdditionalInfo() {
        common.clickElement("xpath","//TabItem[contains(@Name,'Additional Information  ')]");
    }

    public void navigateToEWayBill  () {
        common.clickElement("xpath","//TabItem[contains(@Name,'E-Way Bill ')]");
    }


    public void navigateToTermsAndConditions(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Terms And Conditions')]");
    }

    public void navigateToDispatchDetails(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Despatch Address  ')]");
    }

    public void navigateToAllocations(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Allocations  ')]");
    }

    public void navigateToOtherChargesTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Other Charges')]");
    }

    public void navigateToOtherDeductionsTab() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Other Deductions')]");
    }

    public void navigateToServices() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'Services')]");
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
        common.clickElement("name", "Enquiries");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Enquiries']");
    }

    public void navigateToSalesEnquiryCancellationMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Enquiries");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Enquiries Cancellation']");
    }


    public void navigateToSalesQuotationAgainstEnquiryMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Quotations");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Quotations against Enquiries']");
//        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Quotations against Enquiries']").getText();
//        System.out.println("Screen Name:-" + pageValidation);
//        Assert.assertEquals(pageValidation, "Sales Quotations against Enquiries");
    }

    public void navigateToSalesOrderAgainstQuotationsMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Orders against Quotations']");
        String pageValidation = common.findWebElement("xpath", "//Pane/Text[@Name='Sales Orders against Quotations']").getText();
        System.out.println("Screen Name:-" + pageValidation);
        Assert.assertEquals(pageValidation, "Sales Orders against Quotations");
    }

    public void navigateToDeliveriesAgainstOrdersMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Deliveries");
        common.clickElement("xpath", "//MenuItem[@Name='Deliveries against Orders']");
    }

    public void navigateToSalesInvoiceMenu() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Invoices']");
        Thread.sleep(3000);
    }

    public void navigateToSalesReturnMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Returns']");
    }

    public void navigateToSalesReturnWithInvoiceReferenceMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Return with Invoice Reference']");
    }

    public void navigateToPartyProductwiseDiscountMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("xpath", "//MenuItem[@Name='Prices and Discounts'][2]");
        common.clickElement("name", "Party and Product wise Discounts");
    }

    public void navigateToSalesPricesMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("xpath", "//MenuItem[@Name='Prices and Discounts'][2]");
        common.clickElement("name", "Sales Prices");
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


    public void navigateToPurchaseVouchersAgainstOrders() throws InterruptedException {
        Thread.sleep(1000);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Vouchers against Orders']");
//        String validate = common.findWebElement("xpath", "//Text[@Name='Purchase Vouchers against Orders']").getText();
//        System.out.println(validate);
//        Assert.assertEquals("Purchase Vouchers against Orders", validate);
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

    public void enterOtherCharges(String dataFile,String dataset) throws IOException, ParseException {
        navigateToOtherChargesTab();
        enterData("xpath", "//Edit[@Name='Account Code Row 0, Not sorted.']", dataFile,dataset, "otherChargesAccount");
        enterData("xpath", "//Edit[@Name='Amount * Row 0, Not sorted.']", dataFile, dataset,"chargesAmount");
        enterData("xpath", "//Edit[@Name='HSN Row 0, Not sorted.']", dataFile, dataset,"HSNCode");
    }


    public void termsAndConditions(String dataFile,String dataset) throws IOException, ParseException {
        common.clickElement("xpath","//TabItem[contains(@Name,'Terms And Conditions')]");
        enterInput("xpath","//Edit[@Name='Term Type * Row 0, Not sorted.']",dataFile,dataset,"termType");
        enterInput("xpath","//Edit[@Name='Term * Row 0, Not sorted.']",dataFile,dataset,"term");
        enterInput("xpath","//Edit[@Name='Comments Row 0, Not sorted.']",dataFile,dataset,"comments");
    }

    public void enterCash(String dataFile,String dataset) throws IOException, ParseException {
        navigateToCashTab();
        enterInput("xpath","//Edit[@Name='Cash Account Code Row 0, Not sorted.']",dataFile,dataset,"cashAccount");
        enterInput("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,dataset,"cashAmount");
    }


    public  void navigateToItemsOtherCosts() throws IOException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Other Costs')]");
//        System.out.println(elements.get(1).getText());
        elements.get(1).click();
//        Assert.assertEquals(common.findWebElement("xpath", "//Pane//following-sibling::edit[6]").getAttribute("LegacyValue"), common.getData(dataFile, dataSet, "expectedItemsOtherCostsAmount"), "ItemsOtherCostsAmount Mismatch");
//        System.out.println("worked this assertion");
    }


    public void selectPendingsRCB(String voucherNum) {
        List<WebElement> elementList = common.findWebElements("xpath", "//Window[@Name='Cheque Details']//Table/*[@Name='Data Panel']/*/*[@Name='Voucher No row 1']");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println(i.getText());
//            if (i.getText().equals(voucherNum)) {
                i.click();
                i.sendKeys(Keys.LEFT, Keys.SPACE, Keys.ENTER, Keys.ENTER);
                break;
//            }
        }
    }


    //transaction related methods
    public void transactionSave() throws InterruptedException {
        common.clickElement("xpath", "//Button[@Name='Save']");
        Thread.sleep(3000);
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

    public String oldTTransactionID() throws InterruptedException {
        Thread.sleep(5000);
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

    public void selectPendingsDPDC() {
        List<WebElement> popupWindow = common.findWebElements("xpath", "//Window[@Name='Open Transactions']");
        if (popupWindow.isEmpty()) {
            System.out.println("No popup found, moving on.");
            return;
        }
        List<WebElement> pendings = common.findWebElements("xpath", "//Window[@Name='Open Transactions']//Pane/Table/*[starts-with(@Name,'Row ')]");
        System.out.println("pendings size: " + pendings.size());
        for (int i = 0; i < 5; i++) {
            WebElement userRow = pendings.get(i);
            WebElement voucherNo = userRow.findElement(By.xpath(".//*[starts-with(@Name, 'TowardsVNo Row')]"));
            voucherNo.click();
            voucherNo.sendKeys(Keys.LEFT, Keys.SPACE);
        }
        common.clickElement("xpath", "//Button[@Name='Ok']");
    }

    public void selectPendingsDPDC(String... vouchersToSelect) {
        List<String> voucherList = Arrays.asList(vouchersToSelect);

        // same logic
        List<WebElement> popupWindow = common.findWebElements("xpath", "//Window[@Name='Open Transactions']");
        if (popupWindow.isEmpty()) {
            System.out.println("No popup found, moving on.");
            return;
        }

        List<WebElement> pendings = common.findWebElements("xpath",
                "//Window[@Name='Open Transactions']//Pane/Table/*[starts-with(@Name,'Row ')]");

        for (int i=0;i< pendings.size();i++) {
            WebElement userRow = pendings.get(i);
            WebElement voucherNo = userRow.findElement(By.xpath(".//*[starts-with(@Name, 'TowardsVNo Row')]"));
            String voucherText = voucherNo.getText();
            if (!(voucherList.contains(voucherText))) {
                voucherNo.click();
                voucherNo.sendKeys(Keys.DOWN);
            } else {
                System.out.println("Selecting voucher: " + voucherText);
                voucherNo.sendKeys(Keys.LEFT, Keys.SPACE);
            }
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
                // If the value is nall, throw an assertion error// value.isEmpty() ||
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

    public void deleteRecentTransaction() throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> links = driver.findElementsByXPath("//Text[@Name='Last Saved :']/following-sibling::Text//HyperLink");
        System.out.println("Total links found: " + links.size());
        for (WebElement l : links) {
            System.out.println("Found link: " + l.getAttribute("Name"));
            l.click();
        }
        Thread.sleep(1500);
        common.clickElement("xpath","//Window//Button[@Name='View']");
        WebDriverWait wait=new WebDriverWait(driver,30);
        WebElement tools=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//Button[@Name='Tools']")));
        tools.click();
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Delete']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Button[@Name='Yes']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Button[@Name='OK']");
    }

    public void deleteTransactionUsingVoucherNumber(String voucherID) throws InterruptedException, IOException {
        long deletingTransactionStartAt=System.nanoTime();
        String voucherSeries = voucherID.replaceAll("\\d", "");
        System.out.println("VoucherString :" + voucherSeries);
        String voucherNumber = voucherID.replaceAll("\\D", "");
        System.out.println("VoucherNumber :" + voucherNumber);
        common.clickElement("xpath", "//MenuItem[@Name='Tools']");
        common.clickElement("xpath", "//MenuItem[@Name='Vouchers']");
        common.clickElement("xpath", "//MenuItem[@Name='Delete']");
        //enter data
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "Root");
        rootDriver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), capabilities);
        Thread.sleep(2500);
        List<WebElement> panes = By.tagName("Window").findElements(rootDriver);
        Thread.sleep(2500);
        if (panes.isEmpty()) {
            System.out.println("No windows found.");
        } else {
            for (WebElement i : panes) {
                String name = i.getAttribute("Name");
                System.out.println("Name:- " + name);
                if (("Delete Transaction").equals(name)) {
                    Thread.sleep(3000);
                    WebElement seriesInput = rootDriver.findElementByXPath("//Pane/Text[@Name='Document Series']/following-sibling::Edit");
                    seriesInput.sendKeys(voucherSeries);
                    WebElement fromNumInput = rootDriver.findElementByXPath("//Pane/Text[@Name='Document No']/following-sibling::Edit");
                    fromNumInput.sendKeys(voucherNumber);
                    rootDriver.findElementByXPath("//Button[@Name='Delete']").click();
                    WebDriverWait wait = new WebDriverWait(rootDriver, 10);
                    // After clicking the Delete button:
                    try {
                        // Wait for Yes button to appear - means deletion possible
                        WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//Button[@Name='Yes']")));
                        yesButton.click();
                        // After clicking Yes, wait for success message
                        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//Text[@Name='Transactions deleted Successfully.']")));
                        System.out.println("Transaction deleted successfully." + voucherID);
                    } catch (TimeoutException e) {
                        // Yes button did not appear, check if "Cannot delete transaction" message is shown
                        try {
                            WebElement failureMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                    By.xpath("//Text[contains(@Name,\"Can't Delete Transaction\")]")));
                            System.out.println("Can't delete transaction. Transaction doesn't exist." + voucherID);
                        } catch (TimeoutException ex) {
                            System.out.println("No expected popup message appeared.");
                        }
                    }
                    try {
                        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//Button[@Name='OK']")));
                        okButton.click();
                    } catch (TimeoutException e) {
                        System.out.println("OK button not found or not clickable.");
                    }
                }
                break;
            }
        }
        long deletingTransactionEnd=System.nanoTime()-deletingTransactionStartAt;
        FileUtil.writeTimeLogInMinutes("Delete Transaction Using voucher:- ",deletingTransactionEnd);
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

    public void enterChequesPDCInPurchase(String dataFile,String key) throws IOException, ParseException {
        List<WebElement> elements=common.findWebElements("xpath","//TabItem[contains(@Name,'Cheques')]");
//        System.out.println(elements.get(2).getText());
        elements.get(2).click();
//        common.clickElement("xpath","//TabItem[contains(@Name,'Cheques [PDC]')]");
        enterInput("xpath","//Edit[@Name='Bank Account Code Row 0, Not sorted.']",dataFile,key,"bankAccountCode");
        enterInput("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']",dataFile,key,"chequeAmount");
        common.findWebElement("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']").sendKeys(String.valueOf(common.getRandom()));
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
    public void enterChequesInPRWIR(String dataFile,String dataSet) throws Exception {
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

    public void validateIGSTAmountTabIsNotEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'IGST')]");
        String igst = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (igst == ("0.000")) {
            Assert.fail("IGST field is empty");
        }
    }

    public void validateCESSAmountTabIsNotEmpty() {
        common.clickElement("xpath", "//TabItem[contains(@Name,'CESS')]");
        String cess = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']").getText();
        if (cess == (null) || "(null)".equals(cess)) {
            Assert.fail("CESS field is empty");
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


    public void grossAmountPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String grossAmount = common.findWebElement("xpath", "//Edit[contains(@Name,'Gross Amount')]").getText();
        if ((grossAmount == (null) || "(null)".equals(grossAmount))) {
            Assert.fail("GrossAmount field is empty");
        }
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

    public void totalValueInCompanyCurrenyPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String totalValueCompanyCurrency = common.findWebElement("xpath", "//Edit[@Name='Total Value In Company Currency']").getText();
        if (totalValueCompanyCurrency == (null) || "(null)".equals(totalValueCompanyCurrency)) {
            Assert.fail("Total value in Company Currency field is Empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Total value in company currency",duration/1000000000);
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

    public void tcsAmountPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String tcsAmount = common.findWebElement("xpath", "//Edit[@Name='TCS Amount']").getText();
        if ((tcsAmount == (null) || "(null)".equals(tcsAmount))) {
            Assert.fail("TCS Amount field is empty");
        }
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

    public void chargesPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String charges = common.findWebElement("xpath", "//Edit[@Name='Charges']").getText();
        if ((charges == (null) || "(null)".equals(charges))) {
            Assert.fail("Charges field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Charges",duration/1000000000);
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


    public void grossMinusDiscountPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String grossMinusDiscountAmount = common.findWebElement("xpath", "//Edit[contains(@Name,'Gross - Disc')]").getText();
        if ((grossMinusDiscountAmount == (null) || "(null)".equals(grossMinusDiscountAmount))) {
            Assert.fail("grossMinusDiscountAmount field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Gross-Discount",duration/1000000000);
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

    public void paymentsValuePresentInSummary() throws IOException {
        long start = System.nanoTime();
        String paymentsValue = common.findWebElement("xpath", "//Edit[@Name='Payments Value']").getText();
        if ((paymentsValue == "0.000")) {
            Assert.fail("Charges field is empty");
        }
        long duration = System.nanoTime() - start;
        FileUtil.writeTimeLog("Payments value",duration/1000000000);
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

    public void otherChargesCESSPresentInSummary() throws IOException {
        long start = System.nanoTime();
        String otherChargesCESS = common.findWebElement("xpath", "//Edit[@Name='Other Charges CESS']").getText();
        if ((otherChargesCESS == (null) || "(null)".equals(otherChargesCESS))) {
            Assert.fail("Other Charges CESS field is empty");
        }
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
//        element.sendKeys(Keys.DELETE);
        List<String> columnData = readExcelData(dataFile, sheetName, key);
//        System.out.println("size"+columnData.size());
//        System.out.println("excel element"+columnData.get(0));
        if (!columnData.isEmpty()) {
            element.sendKeys(columnData.get(0),Keys.TAB);
        }
    }

    public void EnterData(String locator,String dataFile,String sheetName,String key,int i) {
        WebElement element=common.findWebElement("xpath",locator);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(Keys.DELETE);
        List<String> columnData = readExcelData(dataFile, sheetName, key);
//        System.out.println("size"+columnData.size());
//        System.out.println("excel element"+columnData.get(0));
        if (!columnData.isEmpty()) {
            element.sendKeys(columnData.get(i),Keys.TAB);
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

    public void addDataStockConsumption(String locatorType, String locator, String fileName, String sheetName, String key, int j) {
        WebElement element = common.findWebElement(locatorType, locator);
        if (element != null) {
            element.click();
            List<String> columnData = readExcelData(fileName, sheetName, key);
            if (!columnData.isEmpty()) {
                element.sendKeys(columnData.get(j), Keys.TAB);

                // Wait briefly for popup
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}

                // Check & handle popup immediately
                List<WebElement> popup = driver.findElements(By.xpath("//Window[@Name='Serial Number Details'] | //Window[@Name='Batch Details']"));
                if (!popup.isEmpty()) {
                    driver.findElement(By.name("Cancel")).click();
                }
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

    public void decimalPrecision(String locator,String dataFile,String sheetName,String key) {
        WebElement element=common.findWebElement("xpath",locator);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.BACK_SPACE);
        List<String> columnData = decimalNumberData(dataFile, sheetName, key);
        if (!columnData.isEmpty()) {
            element.sendKeys(columnData.get(0),Keys.TAB);
        }
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

    public void clickListData(WebElement element){
        element.click();
    }

    public void exportIOFiles(String voucherNum,WindowsDriver rootDriver) throws InterruptedException, IOException {
        String prefix = voucherNum.replaceAll("\\d", "");
        String number = voucherNum.replaceAll("\\D", "");
        Thread.sleep(2000);
        navigateToMastersWhen3Steps("Tools","Automated Testing","Generate Input File");
        rootDriver=common.initializeDriver("Root");
        Thread.sleep(3000);
        common.findWebElement("xpath", "//Window[@Name='Export Transaction Postings']/Pane/Edit[@Name='Voucher Series']").sendKeys(prefix);
        common.findWebElement("xpath","//Window[@Name='Export Transaction Postings']/Pane/Edit[@Name='Voucher Number']").sendKeys(number);
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(2000);
        common.clickElement("xpath","//Window[@Name='Export Transaction Postings']/Window[@Name='Export to Excel']/Button[@Name='OK']");
        Thread.sleep(1500);
        if (common.findWebElement("xpath","//Text").getText().equals("Data Exported successfully!")) {
            common.clickElement("xpath", "//Button[@Name='OK']");
        }
        else if(common.findWebElement("xpath","//Text").getText().equals("Transactionno doesnot exist.")){
            Assert.fail("Transaction does not exists");
            common.clickElement("xpath", "//Button[@Name='OK']");
        }
        Thread.sleep(1000);
        navigateToMastersWhen3Steps("Tools","Automated Testing","Generate Output File");
        rootDriver=common.initializeDriver("Root");
        Thread.sleep(3000);
        common.findWebElement("xpath", "//Window[@Name='Export Transaction Postings']/Pane/Edit[@Name='Voucher Series']").sendKeys(prefix);
        common.findWebElement("xpath","//Window[@Name='Export Transaction Postings']/Pane/Edit[@Name='Voucher Number']").sendKeys(number);
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(2500);
        common.clickElement("xpath","//Window[@Name='Export Transaction Postings']/Window[@Name='Export to Excel']/Button[@Name='OK']");
        Thread.sleep(1500);
        if (common.findWebElement("xpath","//Text").getText().equals("Data Exported successfully!")) {
            common.clickElement("xpath", "//Button[@Name='OK']");
        }
        else if(common.findWebElement("xpath","//Text").getText().equals("Transactionno doesnot exist.")){
            Assert.fail("Transaction does not exists");
            common.clickElement("xpath", "//Button[@Name='OK']");
        }
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

    public void enterServices(String dataFile,String dataset) throws Exception {
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

    public void enterMasterType(String dataFile,String sheetName, String key){
        EnterData("//Edit[@Name='Master Type']",dataFile,sheetName,key);
    }

    public void enterBasisSalesPrice(String dataFile,String sheetName, String key){
        EnterData("//Edit[@Name='Basis']",dataFile,sheetName,key);
    }


    public void enterAmountSalesPrice(String dataFile,String sheetName, String key){
        EnterData("//Edit[@Name='Amount']",dataFile,sheetName,key);
    }
    public void enterControlAccount(String dataFile,String sheetName, String key) {
        EnterData("//Edit[@Name='Control Account *']",dataFile,sheetName,key);
    }
    public void enterExecutive(String dataFile,String sheetName, String key) throws IOException, ParseException {
        EnterData("//Edit[@Name='Executive *']",dataFile,sheetName,key);
    }

    public void validateStockLedger(String fileName,String dataSet) throws Exception {

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
    public void adjustAmountInBillsReceivables(String dataFile,String desiredVoucher){
        List<WebElement> towardsVoucherNum = common.findWebElements("xpath", "//Table[@Name='BillsReceivable']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Towards VNo * Row')]");
        System.out.println("Towards vouchers Size :"+towardsVoucherNum.size());
//        List<WebElement> adjustedAmountRows = common.findWebElements("xpath", "//Table[@Name='BillsReceivable']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Amount Adjusted * Row')]");
//        System.out.println("AdjustedAmount size :"+adjustedAmountRows.size());
        boolean voucherFound=false;
        for (int i=0;i< towardsVoucherNum.size();i++){
            WebElement text=towardsVoucherNum.get(i);
            System.out.println("Towards vouchers getTet :"+text.getText());
            if (text.getText().equals(desiredVoucher)){
                voucherFound = true;
                text.click();
                text.sendKeys(Keys.TAB,Keys.TAB,Keys.SPACE);
                EnterData("//Edit[@Name='Amount Adjusted * Row "+i+", Not sorted.']",dataFile,"BillsReceivable","AmountAdjusted");
                common.deleteInvalidRows();
                break;
            }else if(!text.getText().equals(desiredVoucher)){
                towardsVoucherNum.get(i).click();
                towardsVoucherNum.get(i).sendKeys(Keys.DOWN);
            }
            else{
                System.out.println("Towards voucher number is not found");
            }
        }
    }

    public void adjustAmountInBillsPayable(String dataFile,String desiredVoucher) throws InterruptedException, IOException {
        List<WebElement> towardsVoucherNum = common.findWebElements("xpath", "//Table[@Name='BillsPayable']/*[contains(@Name,'Row ')]/Edit[starts-with(@Name,'Towards VNo * Row')]");
        System.out.println("Towards vouchers Size :"+towardsVoucherNum.size());
        boolean voucherFound=false;
        for (int i=0;i< towardsVoucherNum.size();i++){
            WebElement text=towardsVoucherNum.get(i);
            System.out.println("Towards vouchers getTet :"+text.getText());
            if (text.getText().equals(desiredVoucher)){
                voucherFound = true;
                text.click();
                text.sendKeys(Keys.TAB,Keys.TAB,Keys.SPACE);
                EnterData("//Edit[@Name='Amount Adjusted * Row "+i+", Not sorted.']",dataFile,"BillsPayable","AmountAdjusted");
//                common.clickElement("xpath","//Edit[@Name='Amount Adjusted * Row "+i+", Not sorted.']");
                Thread.sleep(1500);
                WebElement element = common.findWebElement("xpath", "//Edit[@Name=' Row "+i+", Not sorted.']");
                element.click();
                Actions actions = new Actions(driver);
                actions.contextClick(element).perform();
                rootDriver = common.initializeDriver("Root");
                System.out.println("root navigation done");
                Thread.sleep(1500);
                WebElement click=driver.findElementByXPath( "//MenuItem[@Name='Delete Invalid Rows']");
                click.click();
                break;
            }else if(!text.getText().equals(desiredVoucher)){
                towardsVoucherNum.get(i).click();
                towardsVoucherNum.get(i).sendKeys(Keys.DOWN);
            }
            else{
                System.out.println("Towards voucher number is not found");
            }
        }
    }


    public void selectMultiplePendings(String voucherNum1,String voucherNum2, String financialYearNum) {
        // Try to detect the popup
        List<WebElement> popupWindow = common.findWebElements("xpath", "//Window[@Name='Open Transactions']");
        if (popupWindow.isEmpty()) {
            System.out.println("No popup found, moving on.");
            return; // Skip this method's logic if no popup is present
        }
        // Popup found, continue with existing logic
        List<WebElement> pendings = common.findWebElements("xpath", "//Window[@Name='Open Transactions']//Pane/Table/*[starts-with(@Name,'Row ')]");
        System.out.println("pendings size: " + pendings.size());
//        Set<String> expectedPairs = new HashSet<>();
//        expectedPairs.add(voucherNum1);
//        expectedPairs.add(voucherNum2);
//
//        int matchCount = 0;
//
//        for (WebElement userRow : pendings) {
//            WebElement voucherNo = userRow.findElement(By.xpath(".//*[starts-with(@Name, 'TowardsVNo Row')]"));
//            WebElement financialYear = userRow.findElement(By.xpath(".//*[starts-with(@Name, 'FinancialYear Row')]"));
//
//            String voucherNoText = voucherNo.getText().trim();
//            String financialYearText = financialYear.getText().trim();
//
//            String combinedKey = voucherNoText;
//
//            if (expectedPairs.equals(combinedKey)&&financialYearText.equals(financialYearNum)) {
//                voucherNo.sendKeys(Keys.LEFT, Keys.SPACE); // select the row
//                expectedPairs.remove(combinedKey); // remove to prevent duplicate selection
//                matchCount++;
//
//                if (matchCount == 2) {
//                    break; // Exit once 2 vouchers are selected
//                }
//            }
//        }
//
//        if (matchCount < 2) {
//            Assert.fail("Could not find both expected pending transactions.");
//        }
//
//        common.clickElement("xpath", "//Button[@Name='Ok']");
        List<String> voucherNumList = List.of(voucherNum1, voucherNum2);
        System.out.println(voucherNumList+" :-vouchers List");
        int matchCount = 0;
        for (int i = 0; i < pendings.size(); i++) {
            WebElement userRow = pendings.get(i);

            WebElement voucherNo = userRow.findElement(By.xpath(".//*[starts-with(@Name, 'TowardsVNo Row')]"));
            WebElement financialYear = userRow.findElement(By.xpath(".//*[starts-with(@Name, 'FinancialYear Row')]"));

            String voucherNoText = voucherNo.getText();
            String financialYearText = financialYear.getText();

            // Match against required values (you may want a list of voucherNum/financialYearNum pairs)
            if (voucherNoText.equals(voucherNumList.get(0)) && financialYearText.equals(financialYearNum)) {
                voucherNo.sendKeys(Keys.LEFT, Keys.SPACE);
                matchCount++;
            }
            if (voucherNoText.equals(voucherNumList.get(1)) && financialYearText.equals(financialYearNum)) {
                voucherNo.sendKeys(Keys.LEFT, Keys.SPACE);
                matchCount++;
                if (matchCount == 2) {
                    break; // Exit after selecting two vouchers
                }
            }else {
                voucherNo.click();
                voucherNo.sendKeys(Keys.DOWN);
            }
        }

        if (matchCount < 2) {
            Assert.fail("Less than two matching pending transactions found. Please check.");
        }

        common.clickElement("xpath", "//Button[@Name='Ok']");
    }

}