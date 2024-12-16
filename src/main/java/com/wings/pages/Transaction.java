package com.wings.pages;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import com.wings.utils.Common;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
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
                element.sendKeys(Keys.ENTER);
                break;
            }
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

    public void navigateToSummaryTab() throws AWTException {
//        common.clickElement("xpath","//TabItem[contains(@Name,'Summary')]");
        navigateToOtherInfoTab();
        for (int j = 0; j < 4; j++) {
            Robot robot=new Robot();
            robot.keyPress(KeyEvent.VK_RIGHT);
            robot.keyRelease(KeyEvent.VK_RIGHT);
        }
        WebElement Quantity = common.findWebElement("xpath", "//Edit[@Name='Quantity']");
//        String quantityText = Quantity.getText();
//        if ((quantityText == (null) || "(null)".equals(quantityText))) {
//            Assert.fail("Quantity field is empty");
//        }
//
//        WebElement grossAmount = common.findWebElement("xpath", "//Edit[@Name='Gross Amount']");
//        String grossAmoun = grossAmount.getText();
//        if ((grossAmoun == (null) || "(null)".equals(grossAmoun))) {
//            Assert.fail("grossAmount field is empty");
//        }
//
//        WebElement netAmount = common.findWebElement("xpath", "//Edit[@Name='Net Amount']");
//        String netAmountText1 = netAmount.getText();
//        if ((netAmountText1 == (null) || "(null)".equals(netAmountText1))) {
//            Assert.fail("netAmount field is empty");
//        }
//
//        WebElement totalValuee = common.findWebElement("xpath", "//Edit[@Name='Total Value']");
//        String totalValueText = totalValuee.getText();;
//        if (totalValueText == (null) || "(null)".equals(totalValueText)) {
//            Assert.fail("Total value field is Empty");
//        }
//
//        WebElement totalValueCompanyCurreny = common.findWebElement("xpath", "//Edit[@Name='Total Value In Company Currency']");
//        String totalValuecurrencyText = totalValueCompanyCurreny.getText();
//        if (totalValuecurrencyText == (null) || "(null)".equals(totalValuecurrencyText)) {
//            Assert.fail("Total value in Company Curreny field is Empty");
//        }
//
//        WebElement receivableAmount = common.findWebElement("xpath", "//Edit[@Name='Receivable Amount']");
//        String receivableAmountText = receivableAmount.getText();
//        if (receivableAmountText == (null) || "(null)".equals(receivableAmountText)) {
//            Assert.fail("Receivable Amount field is empty");
//        }

    }

    public void validateTCSAmount(double tcsAssesibleValue,double tcsRate){
        common.clickElement("xpath","//TabItem[contains(@Name,'TCS')]");
        common.clickElement("xpath","//TabItem[contains(@Name,'TCS')]");
        WebElement assessibleValueAmount =common.findWebElement("xpath","//Edit[@Name='Assessable Value']");
        tcsAssesibleValue = Double.parseDouble(assessibleValueAmount.getText().trim().replace(",",""));
        WebElement tcsPercent =common.findWebElement("xpath","//Edit[@Name='TCS Rate']");
        tcsRate = Double.parseDouble(tcsPercent.getText().trim());
        WebElement tcsCompanyCurrency =common.findWebElement("xpath","//Edit[@Name='TCS Amount In Company Currency']");
        double tcsAmountInCompnayCurrency = Double.parseDouble(tcsCompanyCurrency.getText().trim());
        double expectedTCS = tcsAssesibleValue*tcsRate/100;
        System.out.println("ActualAmount"+tcsAmountInCompnayCurrency+"ExpectedAmount"+expectedTCS);
        Assert.assertEquals(tcsAmountInCompnayCurrency,expectedTCS,"Calculations mismatch");
    }

    public void validateCGSTAmountTabWhenNull(){
        common.clickElement("xpath","//TabItem[contains(@Name,'CGST')]");
        WebElement tax=common.findWebElement("xpath","//Edit[@Name='Tax Amount Row 0, Not sorted.']");
        String value=tax.getText();
        if (!(value==(null) || "(null)".equals(value))){
            Assert.fail("CGST field is not empty");
        }
    }
    public void validateSGSTAmountTabWhenNull(){
        common.clickElement("xpath", "//TabItem[contains(@Name,'SGST')]");
        WebElement tax1 = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']");
        String value1 = tax1.getText();
        if (!(value1 == (null) || "(null)".equals(value1))) {
            Assert.fail("SGST field is not empty");
        }
    }
    public void validateIGSTAmountTabWhenNull(){
        common.clickElement("xpath", "//TabItem[contains(@Name,'IGST')]");
        WebElement IGSTtax = common.findWebElement("xpath", "//Edit[@Name='Tax Amount Row 0, Not sorted.']");
        String value2 = IGSTtax.getText();
        if (!(value2 == (null) || "(null)".equals(value2))) {
            Assert.fail("IGST field is not empty");
        }
    }

    public void validateToCESSAmountTabWhenNull(){
        common.clickElement("xpath","//TabItem[contains(@Name,'CESS')]");
        WebElement tax=common.findWebElement("xpath","//Edit[@Name='Tax Amount Row 0, Not sorted.']");
        String value=tax.getText();
        if (!(value==(null) || "(null)".equals(value))){
            Assert.fail("CESS field is not empty");
        }
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
    public void navigateToUnclearedPayments(){
        common.clickElement("xpath","//TabItem[contains(@Name,'Uncleared Payments')]");
    }







    public void navigateToSalesEnquiryMenu(){
        common.clickElement("name","Sales");
        common.clickElement("name","Enquiries");
        common.clickElement("xpath","//Menu[@Name='Enquiries']/MenuItem[@Name='Sales Enquiries']");
        String pageValidation=common.findWebElement("xpath","//Pane/Text[@Name='Sales Enquiries']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Sales Enquiries");
    }

    public void navigateToSalesEnquiryCancellationMenu(){
        common.clickElement("name","Sales");
        common.clickElement("name","Enquiries");
        common.clickElement("xpath","//MenuItem[@Name='Sales Enquiries Cancellation']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Sales Enquiries Cancellation']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Sales Enquiries Cancellation");
    }

    public void navigateToSalesQuotationsMenu(){
        common.clickElement("name", "Sales");
        common.clickElement("name", "Quotations");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Quotations']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Sales Quotations']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Sales Quotations");
    }

    public void navigateToSalesQuotationsCancellationMenu(){
        common.clickElement("name", "Sales");
        common.clickElement("name", "Quotations");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Quotations Cancellations']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Sales Quotations Cancellations']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Sales Quotations Cancellations");
    }
    public void navigateToSalesQuotationAgainstEnquiryMenu(){
        common.clickElement("name","Sales");
        common.clickElement("name","Quotations");
        common.clickElement("xpath","//MenuItem[@Name='Sales Quotations against Enquiries']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Sales Quotations against Enquiries']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Sales Quotations against Enquiries");
    }
    public void navigateToSalesOrderAgainstQuotationsMenu(){
        common.clickElement("name", "Sales");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Orders against Quotations']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Sales Orders against Quotations']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Sales Orders against Quotations");
    }

    public void navigateToSalesOrderMenu(){
        common.clickElement("name", "Sales");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Orders']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Sales Orders']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Sales Orders");
    }
    public void navigateToSalesOrderCancellaltionMenu(){
        common.clickElement("name", "Sales");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Orders Cancellation']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Sales Orders Cancellation']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Sales Orders Cancellation");
    }

    public void navigateToDeliveriesMenu(){
        common.clickElement("name", "Sales");
        common.clickElement("name", "Deliveries");
        common.clickElement("xpath", "//MenuItem[@Name='Deliveries']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Deliveries']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Deliveries");
    }

    public void navigateToDeliveriesAgainstOrdersMenu(){
        common.clickElement("name", "Sales");
        common.clickElement("name", "Deliveries");
        common.clickElement("xpath", "//MenuItem[@Name='Deliveries against Orders']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Deliveries against Orders']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Deliveries against Orders");
    }

    public void navigateToDeliveryReturnsMenu(){
        common.clickElement("name", "Sales");
        common.clickElement("name", "Deliveries");
        common.clickElement("xpath", "//MenuItem[@Name='Delivery Returns']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Delivery Returns']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Delivery Returns");
    }

    public void navigateToSalesInvoiceMenu() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Invoices']");
        Thread.sleep(3500);
        String validate=common.findWebElement("xpath","//Text[@Name='Sales Invoices']").getText();
        System.out.println("Screen Name:-"+validate);
        Assert.assertEquals(validate,"Sales Invoices");
    }

    public void navigateToSalesInvoiceAgainstDeliveriesMenu() {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Invoices against Deliveries']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Sales Invoices against Deliveries']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Sales Invoices against Deliveries");
    }

    public void navigateToSalesInvoiceAgainstOrdersMenu(){
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Invoices against Orders']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Sales Invoices against Orders']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Sales Invoices against Orders");
    }

    public void navigateToProformaSalesInvoiceMenu(){
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Proforma Sales Invoices']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Proforma Sales Invoices']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Proforma Sales Invoices");
    }

    public void navigateToSalesReturnMenu(){
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Returns']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Sales Returns']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Sales Returns");
    }

    public void navigateToSalesReturnWithInvoiceReferenceMenu(){
        common.clickElement("name", "Sales");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Sales Return with Invoice Reference']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Sales Return with Invoice Reference']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Sales Return with Invoice Reference");
    }

    public void navigateToSalesTargetExecutiveWiseMenu(){
        common.clickElement("name", "Sales");
        common.clickElement("name", "Targets");
        common.clickElement("name", "Define Sales Targets-Executive Wise");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Define Sales Targets-Executive Wise']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Define Sales Targets-Executive Wise");
    }

    public void navigateToPartyProductwiseDiscountMenu(){
        common.clickElement("name", "Sales");
        common.clickElement("xpath", "//MenuItem[@Name='Prices and Discounts'][2]");
        common.clickElement("name", "Party and Product wise Discounts");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Party and Product wise Discounts']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Party and Product wise Discounts");
    }

    public void navigateToSalesPricesMenu(){
        common.clickElement("name","Sales");
        common.clickElement("xpath","//MenuItem[@Name='Prices and Discounts'][2]");
        common.clickElement("name","Sales Prices");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Sales Prices']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Sales Prices");
    }

    public void navigateToInterLocationTransfersMenu(){
        common.clickElement("name", "Inventory");
        common.clickElement("xpath", "//MenuItem[@Name='Inter Location Transfers']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Inter Location Transfers']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Inter Location Transfers");
    }

    public void navigateToOpeningStockMenu(){
        common.clickElement("name", "Inventory");
        common.clickElement("name","Opening Stock");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Opening Stock']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Opening Stock");
    }

    public void navigateToStockConsumptionMenu(){
        common.clickElement("name", "Inventory");
        common.clickElement("xpath", "//MenuItem[@Name='Stock Consumption']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Stock Consumption']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Stock Consumption");
    }

    public void navigateToStockConversionMenu(){
        common.clickElement("name", "Inventory");
        common.clickElement("xpath", "//MenuItem[@Name='Stock Conversion']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Stock Conversion']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Stock Conversion");
    }

    public void navigateToStockCreationMenu(){
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Stock Creation");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Stock Creation']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Stock Creation");
    }

    public void navigateToBankReceiptsMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Bank Receipts']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Bank Receipts']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Bank Receipts");
    }

    public void navigateToCashReceiptsMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Cash Receipts']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Cash Receipts']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Cash Receipts");
    }
    public void navigateToCreditCardReceiptsMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Credit Card Receipts']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Credit Card Receipts']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Credit Card Receipts");
    }

    public void navigateToReceiptsFromCreditCardCompanyMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Receipts from Credit Card Companies']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Receipts from Credit Card Companies']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Receipts from Credit Card Companies");
    }

    public void navigateToReceiptsFromPartiesMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Receipts from Parties']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Receipts from Parties']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Receipts from Parties");
    }

    public void navigateToBankPaymentMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Payments");
        common.clickElement("xpath", "//MenuItem[@Name='Bank Payments']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Bank Payments']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Bank Payments");
    }

    public void navigateToCashPaymentsMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Payments");
        common.clickElement("xpath", "//MenuItem[@Name='Cash Payments']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Cash Payments']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Cash Payments");
    }

    public void navigateToCashTransfersMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Payments");
        common.clickElement("xpath", "//MenuItem[@Name='Cash Transfers']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Cash Transfers']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Cash Transfers");
    }

    public void navigateToPaymentToPartiesMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Payments");
        common.clickElement("xpath", "//MenuItem[@Name='Payments to Parties']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Payments to Parties']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Payments to Parties");
    }

    public void navigateToBookExpensesOrPayablesMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Journals");
        common.clickElement("xpath", "//MenuItem[@Name='Book Expenses or Payables']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Book Expenses or Payables']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Book Expenses or Payables");
    }

    public void navigateToBookIncomesOrReceivablesMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Journals");
        common.clickElement("xpath", "//MenuItem[@Name='Book Incomes or Receivables']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Book Incomes or Receivables']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Book Incomes or Receivables");
    }

    public void navigateToBookingOfOtherCosts(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Journals");
        common.clickElement("xpath", "//MenuItem[@Name='Booking Of Other Costs']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Booking Of Other Costs']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Booking Of Other Costs");
    }

    public void navigateToJournalEntriesMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Journals");
        common.clickElement("xpath", "//MenuItem[@Name='Journal Entries']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Journal Entries']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Journal Entries");
    }

    public void navigateToManulStockVerificationMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Opening Balances");
        common.clickElement("xpath", "//MenuItem[@Name='Manual Stock Valuation']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Manual Stock Valuation']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Manual Stock Valuation");
    }

    public void navigateToOpeningBalancesMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Opening Balances");
        common.clickElement("xpath", "//MenuItem[@Name='Opening Balances']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Opening Balances']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Opening Balances");
    }

    public void navigateToOpeningReceiptsFromCreditCardCompanyMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Opening Balances");
        common.clickElement("xpath", "//MenuItem[@Name='Opening Receipts from Credit Card Companies']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Opening Receipts from Credit Card Companies']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Opening Receipts from Credit Card Companies");
    }

    public void navigateToPartyOpeningBalancesMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Opening Balances");
        common.clickElement("xpath", "//MenuItem[@Name='Party Opening Balances']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Party Opening Balances']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Party Opening Balances");
    }

    public void navigateToTransferIncomesAndExpensesToPLMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Opening Balances");
        common.clickElement("xpath", "//MenuItem[@Name='Transfer Incomes and Expenses to PL']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Transfer Incomes and Expenses to PL']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Transfer Incomes and Expenses to PL");
    }

    public void navigateToBankReconciliationMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Bank Reconciliation']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Bank Reconciliation']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Bank Reconciliation");
    }

    public void navigateToCashDepositsAndWithdrawalsMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Cash Deposits and withdrawals']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Cash Deposits and withdrawals']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Cash Deposits and withdrawals");
    }

    public void navigateToDepositPostDatedChequesMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Deposit Post Dated Cheques']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Deposit Post Dated Cheques']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Deposit Post Dated Cheques");
    }

    public void navigateToInterBankFundTransferMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Inter Bank Fund Transfers']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Inter Bank Fund Transfers']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Inter Bank Fund Transfers");
    }

    public void navigateToOpeningUnclearedBankEntriesMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Opening Uncleared Bank Entries']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Opening Uncleared Bank Entries']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Opening Uncleared Bank Entries");
    }

    public void navigateToReceivedChequesBounceMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Received Cheques Bounce']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Received Cheques Bounce']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Received Cheques Bounce");
    }

    public void navigateToAdjustPartyBills(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Party Adjustments");
        common.clickElement("xpath", "//MenuItem[@Name='Adjust Party Bills']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Adjust Party Bills']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Adjust Party Bills");
    }

    public void navigateToCreditNoteFromSupplierMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Party Adjustments");
        common.clickElement("xpath", "//MenuItem[@Name='Credit Note from Suppliers']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Credit Note from Suppliers']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Credit Note from Suppliers");
    }
    public void navigateToCreditNoteOnCustomerMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Party Adjustments");
        common.clickElement("xpath", "//MenuItem[@Name='Credit Note on Customers']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Credit Note on Customers']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Credit Note on Customers");
    }
    public void navigateToCreditNoteMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Party Adjustments");
        common.clickElement("xpath", "//MenuItem[@Name='Credit Note']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Credit Note']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Credit Note");
    }

    public void navigateToDebitNoteFromSupplierMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Party Adjustments");
        common.clickElement("xpath", "//MenuItem[@Name='Debit Note from Suppliers']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Debit Note from Suppliers']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Debit Note from Suppliers");
    }

    public void navigateToDebitNoteOnCustomerMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Party Adjustments");
        common.clickElement("xpath", "//MenuItem[@Name='Debit Note on Customers']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Debit Note on Customers']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Debit Note on Customers");
    }
    public void navigateToDebitNoteMenu(){
        common.clickElement("name", "Finance");
        common.clickElement("name", "Party Adjustments");
        common.clickElement("xpath", "//MenuItem[@Name='Debit Note']");
        String pageValidation =common.findWebElement("xpath","//Pane/Text[@Name='Debit Note']").getText();
        System.out.println("Screen Name:-"+pageValidation);
        Assert.assertEquals(pageValidation,"Debit Note");
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


    public void navigateToPurchaseEnquiries(){
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Enquiries");
        common.clickElement("xpath", "//Menu[@Name='Enquiries']/MenuItem[@Name='Purchase Enquiries']");
        String validate=common.findWebElement("xpath","//Text[@Name='Purchase Enquiries']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Enquiries",validate);
    }
    public void navigateToPurchaseEnquiriesCancellation(){
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Enquiries");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Enquiries Cancellation']");
        String validate=common.findWebElement("xpath","//Text[@Name='Purchase Enquiries Cancellation']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Enquiries Cancellation",validate);
    }
    public void navigateToPurchaseQuotations(){
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Quotations");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Quotations']");
        String validate=common.findWebElement("xpath","//Text[@Name='Purchase Quotations']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Quotations",validate);
    }
    public void navigateToPurchaseQuotationsAgainstEnquiries(){
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Quotations");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Quotations against Enquiries']");
        String validate=common.findWebElement("xpath","//Text[@Name='Purchase Quotations against Enquiries']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Quotations against Enquiries",validate);
    }
    public void navigateToPurchaseOrdersAgainstQuotations(){
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Orders against Quotations']");
        String validate=common.findWebElement("xpath","//Text[@Name='Purchase Orders against Quotations']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Orders against Quotations",validate);
    }
    public void navigateToPurchaseOrders(){
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Orders']");
        String validate=common.findWebElement("xpath","//Text[@Name='Purchase Orders']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Orders",validate);
    }
    public void navigateToPurchaseOrdersaCancellation(){
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Orders Cancellation']");
        String validate=common.findWebElement("xpath","//Text[@Name='Purchase Orders Cancellation']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Orders Cancellation",validate);
    }
    public void navigateToMaterialReceipts(){
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Material Receipts']");
        String validate=common.findWebElement("xpath","//Text[@Name='Material Receipts']").getText();
        System.out.println(validate);
        Assert.assertEquals("Material Receipts",validate);
    }
    public void navigateToMaterialReceiptsAgainstOrders(){
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Material Receipts against Orders']");
        String validate=common.findWebElement("xpath","//Text[@Name='Material Receipts against Orders']").getText();
        System.out.println(validate);
        Assert.assertEquals("Material Receipts against Orders",validate);
    }
    public void navigateToMaterialReturns(){
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Receipts");
        common.clickElement("xpath", "//MenuItem[@Name='Material Returns']");
        String validate=common.findWebElement("xpath","//Text[@Name='Material Returns']").getText();
        System.out.println(validate);
        Assert.assertEquals("Material Returns",validate);
    }
    public void navigateToPurchasePrice(){
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Price");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Prices']");
        String validate=common.findWebElement("xpath","//Text[@Name='Purchase Prices']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Prices",validate);
    }
    public void navigateToPurchaseVouchers(){
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Vouchers']");
        String validate=common.findWebElement("xpath","//Text[@Name='Purchase Vouchers']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Vouchers",validate);
    }
    public void navigateToPurchaseVouchersAgainstOrders(){
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Vouchers against Orders']");
        String validate=common.findWebElement("xpath","//Text[@Name='Purchase Vouchers against Orders']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Vouchers against Orders",validate);
    }
    public void navigateToPurchaseVouchersAgainstReceipts(){
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Vouchers against Receipts']");
        String validate=common.findWebElement("xpath","//Text[@Name='Purchase Vouchers against Receipts']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Vouchers against Receipts",validate);
    }
    public void navigateToPurchaseVouchersWithInvoicesReference(){
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Invoices");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Returns with Invoice Reference']");
        String validate=common.findWebElement("xpath","//Text[@Name='Purchase Returns with Invoice Reference']").getText();
        System.out.println(validate);
        Assert.assertEquals("Purchase Returns with Invoice Reference",validate);
    }

    public void generalProduct(String filname,String product,String quantity,int i) throws IOException, ParseException, InterruptedException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row "+i+", Not sorted.']", filname, product);
        common.clickElement("xpath", "//Edit[@Name='Quantity Row "+i+", Not sorted.']");
        enterData("xpath", "//Edit[@Name='Quantity Row "+i+", Not sorted.']", filname, quantity);
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 1000, 0);
        Thread.sleep(1500);
        common.clickElement("xpath","//Header[@Name='GST Amount']");
    }
    public void multiBatchProduct(String filename,String product,String quantity,int i) throws IOException, ParseException, InterruptedException, AWTException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row "+i+", Not sorted.']", filename, product);
        common.clickElement("xpath", "//Button[@Name='Stock Details Row "+i+"']");
        Thread.sleep(3000);
        List<WebElement> rows = common.findWebElements("xpath", "//Table[@Name='Batch Details']/*[@Name='Data Panel']/*[@Name='Row 1']/*[@Name='Quantity row 1']");
        System.out.println("Row count: " + rows.size());
        for (WebElement k : rows) {
            k.click();
            Thread.sleep(1000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_DELETE);
            robot.keyRelease(KeyEvent.VK_DELETE);
            k.sendKeys(common.getData(filename, quantity));
        }
        common.clickElement("xpath", "//Button[@Name='OK']");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
    }

    public void serialNumberProduct(String filename,String product,int i) throws IOException, ParseException, InterruptedException, AWTException {
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row "+i+", Not sorted.']", filename,product );
        common.clickElement("xpath", "//Button[@Name='Stock Details Row "+i+"']");
        List<WebElement> rowss = common.findWebElements("xpath", "//Table[@Name='Serial Numbers List']/*[@Name='Data Panel']/*[contains(@Name,'Row')]/*[contains(@Name,'Select row')]");
        System.out.println("Row count: " + rowss.size());
        for (int z = 0; z < 30; z++) {
            Robot robot=new Robot();
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);
            Thread.sleep(1000);
        }
        common.clickElement("xpath", "//Button[@Name='OK']");
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", 500, 0);
    }

    public void invoiceType() throws InterruptedException, AWTException {
        common.clickElement("xpath", "//Edit[@Name='Invoice Type']");
        Thread.sleep(1000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
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
