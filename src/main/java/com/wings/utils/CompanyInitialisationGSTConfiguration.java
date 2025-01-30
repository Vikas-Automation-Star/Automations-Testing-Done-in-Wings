package com.wings.utils;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

public class CompanyInitialisationGSTConfiguration extends Transaction {
    WindowsDriver driver;
    Common common;
    public CompanyInitialisationGSTConfiguration(WindowsDriver driver){
        super(driver);
        common=new Common(driver);
        this.driver=driver;
    }

    public void cgstConfiguration() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Text[@Name='GST Configuration']/*[@Name='GST Configuration']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        List<WebElement> clickAll=common.findWebElements("xpath","//Table/*[contains(@Name,'Row')]/CheckBox[contains(@Name,'UseThis Row')]");
        for(WebElement i:clickAll){
            i.click();
        }
        common.clickElement("xpath","//Button[@Name='Next >']");
        WebElement element = common.findWebElement("xpath", "//Tree[1]/Group[@Name='Data Panel']/TreeItem[@Name='Node0']/*[@Name='Id row 0']");
        element.click();
        element.sendKeys(Keys.SPACE);
        WebElement element1 = common.findWebElement("xpath", "//Tree[2]/Group[@Name='Data Panel']/TreeItem[@Name='Node0']/*[@Name='Id row 0']");
        element1.click();
        element1.sendKeys(Keys.SPACE);
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        Thread.sleep(3000);
        WebElement element2=common.findWebElement("xpath","//ComboBox[@Name='GST Paid In Advance Account']/Button[@Name='Open']");
        element2.sendKeys(Keys.DOWN,Keys.ENTER);
        Thread.sleep(3000);
        WebElement element3=common.findWebElement("xpath","//ComboBox[@Name='CGST Paid Account']/Button[@Name='Open']");
        element3.sendKeys(Keys.DOWN,Keys.ENTER);
        Thread.sleep(3000);
        WebElement element4 =common.findWebElement("xpath","//ComboBox[@Name='CGST Collected Account']/Button[@Name='Open']");
        element4.sendKeys(Keys.DOWN,Keys.ENTER);
        Thread.sleep(1000);
        common.clickElement("xpath","//Button[@Name='Finish']");
        System.out.println("CGST configuration completed");
    }
    public void sgstConfiguration() throws InterruptedException {
        common.clickElement("xpath","//Text[@Name='GST Configuration']/*[@Name='GST Configuration']");
        common.clickElement("xpath","//TreeItem[@Name='SGST']/TreeItem[@Name='SGST']");

        common.clickElement("xpath","//Button[@Name='Next >']");

        List<WebElement> clickAll=common.findWebElements("xpath","//Table/*[contains(@Name,'Row')]/CheckBox[contains(@Name,'UseThis Row')]");
        for(WebElement i:clickAll){
            i.click();
        }

        common.clickElement("xpath","//Button[@Name='Next >']");
        WebElement element = common.findWebElement("xpath", "//Tree[1]/Group[@Name='Data Panel']/TreeItem[@Name='Node0']/*[@Name='Id row 0']");
        element.click();
        element.sendKeys(Keys.SPACE);
        WebElement element10 = common.findWebElement("xpath", "//Tree[2]/Group[@Name='Data Panel']/TreeItem[@Name='Node0']/*[@Name='Id row 0']");
        element10.click();
        element10.sendKeys(Keys.SPACE);
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        Thread.sleep(1000);
        WebElement element5 =common.findWebElement("xpath","//ComboBox[@Name='GST Paid in Advance Account']/Button[@Name='Open']");
        element5.sendKeys(Keys.DOWN,Keys.ENTER);
        Thread.sleep(1000);
        WebElement element6 =common.findWebElement("xpath","//ComboBox[@Name='SGST Paid Account']/Button[@Name='Open']");
        element6.sendKeys(Keys.DOWN,Keys.ENTER);
        Thread.sleep(1000);
        WebElement element7 =common.findWebElement("xpath","//ComboBox[@Name='SGST Collected Account']/Button[@Name='Open']");
        element7.sendKeys(Keys.DOWN,Keys.ENTER);
        common.clickElement("xpath","//Button[@Name='Finish']");
        System.out.println("SGST configuration completed");
    }
    public void igstConfiguration() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        common.clickElement("xpath","//Text[@Name='GST Configuration']/*[@Name='GST Configuration']");
        common.clickElement("xpath","//TreeItem[@Name='IGST']/TreeItem[@Name='IGST']");

        common.clickElement("xpath","//Button[@Name='Next >']");
        List<WebElement> clickAll=common.findWebElements("xpath","//Table/*[contains(@Name,'Row')]/CheckBox[contains(@Name,'UseThis Row')]");
        for(WebElement i:clickAll){
            i.click();
        }
        common.clickElement("xpath","//Button[@Name='Next >']");
        WebElement element = common.findWebElement("xpath", "//Tree[1]/Group[@Name='Data Panel']/TreeItem[@Name='Node0']/*[@Name='Id row 0']");
        element.click();
        element.sendKeys(Keys.SPACE);
        WebElement element11 = common.findWebElement("xpath", "//Tree[2]/Group[@Name='Data Panel']/TreeItem[@Name='Node0']/*[@Name='Id row 0']");
        element11.click();
        element11.sendKeys(Keys.SPACE);
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        Thread.sleep(4000);
        WebElement element8 =common.findWebElement("xpath","//ComboBox[@Name='GST Paid In Advance Account']/Button[@Name='Open']");
        element8.sendKeys(Keys.DOWN,Keys.ENTER);
        Thread.sleep(1000);
        WebElement element9 =common.findWebElement("xpath","//ComboBox[@Name='IGST Paid Account']/Button[@Name='Open']");
        element9.sendKeys(Keys.DOWN,Keys.ENTER);
        Thread.sleep(1000);
        WebElement element10 =common.findWebElement("xpath","//ComboBox[@Name='IGST Collected Account']/Button[@Name='Open']");
        element10.sendKeys(Keys.DOWN,Keys.ENTER);
        common.clickElement("xpath","//Button[@Name='Finish']");
        System.out.println("IGST configuration completed");
    }
    public void companyUnits() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Text[@Name='Select Company Units']/HyperLink[@Name='Select Company Units']");
        Thread.sleep(1000);
        //check boxes
        //branch
        WebElement branch = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable branch accounting in all transactions.']");
        String branchToggleState = branch.getAttribute("Toggle.ToggleState");
        System.out.println("Branch Toggle state:-" + branchToggleState);
        if (branchToggleState.equals("0")) {
            branch.click();
            System.out.println("Branch Checkbox was unchecked, now checked.");
        } else if (branchToggleState.equals("1")){
            System.out.println("Branch Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");
        //department
        WebElement department = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable department in all transactions.']");
        String departmentToggleState = department.getAttribute("Toggle.ToggleState");
        System.out.println("Department Toggle state:-" + departmentToggleState);
        if (departmentToggleState.equals("0")) {
            department.click();
            System.out.println("Department Checkbox was unchecked, now checked.");
        } else if (departmentToggleState.equals("1")){
            System.out.println("Department Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");
        //project
        WebElement project = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable project in all transactions.']");
        String projectToggleState = project.getAttribute("Toggle.ToggleState");
        System.out.println("Project Toggle state:-" + projectToggleState);
        if (projectToggleState.equals("0")) {
            project.click();
            System.out.println("Project Checkbox was unchecked, now checked.");
        } else if (projectToggleState.equals("1")){
            System.out.println("Project Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");
        //profit centre
        WebElement profitCentre = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable profit centre in all transactions.']");
        String profitCentreToggleState = profitCentre.getAttribute("Toggle.ToggleState");
        System.out.println("Profit Centre Toggle state:-" + profitCentreToggleState);
        if (profitCentreToggleState.equals("0")) {
            profitCentre.click();
            System.out.println("Profit Centre Checkbox was unchecked, now checked.");
        } else if (profitCentreToggleState.equals("1")){
            System.out.println("Profit Centre Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");
        //cost centre
        WebElement costCentre = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable cost centre in all transactions.']");
        String costCentreToggleState = costCentre.getAttribute("Toggle.ToggleState");
        System.out.println("Cost Centre Toggle state:-" + costCentreToggleState);
        if (costCentreToggleState.equals("0")) {
            costCentre.click();
            System.out.println("Cost Centre checkbox was unchecked, now checked.");
        } else if (costCentreToggleState.equals("1")){
            System.out.println("Cost Centre checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");
        //executive
        WebElement executive = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable executive in all transcations.']");
        String executiveToggleState = executive.getAttribute("Toggle.ToggleState");
        System.out.println("Executive Toggle state:-" + executiveToggleState);
        if (executiveToggleState.equals("0")) {
            executive.click();
            System.out.println("Executive Checkbox was unchecked, now checked.");
        } else if (executiveToggleState.equals("1")){
            System.out.println("Executive Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");
        //radio button
        common.clickElement("xpath","//RadioButton[@Name='Transaction wise']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");
    }
    public void addOns() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Text[@Name='Add-Ons']/HyperLink[@Name='Add-Ons']");
        Thread.sleep(1000);
        //check boxes
            //SMS
        common.clickElement("xpath","//CheckBox[@Name='Allow sending SMS messages from transactions and reports.']");
            //WhatsApp
        common.clickElement("xpath","//CheckBox[@Name='Allow sending WhatsApp Message from transactions and reports.']");
        common.clickElement("xpath","//CheckBox[@Name='WhatsApp web']");
        //scroll down
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,100);
        //Email
        common.clickElement("xpath","//CheckBox[@Name='Allow sending Emails from transactions and reports.']");
        common.clickElement("xpath","//CheckBox[@Name='Send template as Email body.']");
            //Document Management
        common.clickElement("xpath","//CheckBox[@Name='Enable document management.']");
            //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Button[@Name='OK']");
    }
    public void entryViewPrintSettings() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Text[@Name='Entry, View and Print Settings']/HyperLink[@Name='Entry, View and Print Settings']");
        Thread.sleep(1000);
        //check boxes
        enableCheckboxSelection("//Pane//CheckBox[@Name='Do not close the transaction after save If transaction tab has more than 200 Lines']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Reset voucher series when financial year changes in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Allow save draft for all users in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show barcode panel']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show file based barcode configuration.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Toolbar:show text with icons in all transactions and reports.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Allow renaming of masters by pressing F2 in master window.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Allow moving of masters and nodes by drag and drop in master window.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Confirmation for closing master window.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show properties in master creation window.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,40);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable lookup settings in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show master code in master combos.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show description in master combos.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show master node in master combos.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show transaction field lookup configuration.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show balances']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show account balance in master combos.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show stock balance in master combos.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Advanced options']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,40);
        common.clickElement("xpath","//RadioButton[@Name='Use advanced combo in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Confirmation for closing transaction window in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Confirmation for saving transaction in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show edit/view voucher option in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show borders for text fields in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Word wrap field names in header for all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Autowidth in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Allow entering transactions on future date in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Use date lock for previous entries in all transactions.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,28);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable Incremental Voucher No']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable chart of accounts in financial Accounting.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable voucher types in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable line comments in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show images in master combos.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable the automatic deletion of invalid rows for pending bills in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable reference bill details in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable other info in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Other Info 1']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Other Info 2']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Other Info 3']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Other Info 4']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,20);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Other Info 5']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,68);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Use international comma style in all transactions.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,40);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show branch name in header in all reports.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show zero values in all reports.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Supress currency prefix in all reports.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show horizontal lines in all reports.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Do not print serial number column in all reports.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show company address in all reports.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,30);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show report header in first page for all reports.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Confirmation for closing report window in all reports.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Refresh pendings.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,25);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Allow draft print in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Allow printing of unauthorised transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show form design footer in last page.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,45);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Add extended fields on extended field in invoice design.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable online print templates.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable quick printing in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable grapical invoice design and print templates.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable print and draft templates.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,30);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Restrict negative stock alerts in issues transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Product batch wise']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Storage bin wise']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Override alerts']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable reorder level alerts.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Override reorder level']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");
    }
    public void inventoryFlow() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        common.clickElement("xpath","//HyperLink[@Name='Inventory']");
        common.clickElement("xpath","//Button[@Name='Work Flow']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Opening Stock']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Stock Creation']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Stock Consumption']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Stock Conversion']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Physical Stock Verification']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");

        //Module Settings
        common.clickElement("xpath","//Button[@Name='Module Settings']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable products.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable services.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable non inventory items.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable stock management units in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable multiple stock management units in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable free units in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable free SKU units in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable save transaction with free quantity in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable number of packs in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable value per unit.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable cost price for material issues']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable company wise reorder level']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");

        //Policies
        common.clickElement("xpath","//Button[@Name='Policies']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable multiple locations under branch in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable storagebins under location in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable additional info fields in product creation window.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable additional value fields in product creation window.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable product attributes in product creation window.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Product categories']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Product sub categories']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Product types']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Product brands']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Product classes']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Product sub classes']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Seasons']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Styles']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");
        //close
        common.clickElement("xpath","//Window//Button[@Name='Close']");
    }
    public void productionFlow() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        common.clickElement("xpath","//HyperLink[@Name='Production']");
        common.clickElement("xpath","//Button[@Name='Work Flow']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable assign standard rates']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable simple production flow']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable detailed production flow']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");
        //Module settings
        common.clickElement("xpath","//Button[@Name='Module Settings']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable Bills of Material']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Disable batch pop up']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");
        //close
        common.clickElement("xpath","//Window//Button[@Name='Close']");
    }
    public void financeFlow() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        common.clickElement("xpath","//HyperLink[@Name='Finance']");
        common.clickElement("xpath","//Button[@Name='Work Flow']");

        enableCheckboxSelection("//Pane//CheckBox[@Name='Receipts']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Receipts from Parties']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Cash Receipts']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Bank Receipts']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Credit Card Receipts']");
        //payments
        enableCheckboxSelection("//Pane//CheckBox[@Name='Payments']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Payments to Parties']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Cash Payments']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Bank Payments']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Cash Transfers']");
        //banking
        enableCheckboxSelection("//Pane//CheckBox[@Name='Banking']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Cash Deposits and Withdrawals']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Inter Bank Fund Transfers']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Deposit Post Dated Cheques']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Received Cheque Bounce']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,100);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Issued Cheque Bounce']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Bank Reconciliation']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Opening Uncleared Bank Entries']");
        //Party Adjustments
        enableCheckboxSelection("//Pane//CheckBox[@Name='Party Adjustments']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Credit Note']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Debit Note']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Debit Note on Customer']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Credit Note on Customer']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Debit Note from Supplier']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Credit Note from Supplier']");
        //Journals
        enableCheckboxSelection("//Pane//CheckBox[@Name='Journal']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Journal Entries']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Book Incomes or Receivables']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Book Expenses or Payables']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,100);
        //Openings
        enableCheckboxSelection("//Pane//CheckBox[@Name='Openings']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Party Opening Balances']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Opening Balances']");
        //Reports
        enableCheckboxSelection("//Pane//CheckBox[@Name='Reports']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Profit and Loss T-Form']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Balance Sheet T-Form']");
        Thread.sleep(1000);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Ledger with Interest']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,100);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Day wise Ledger Summary']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Account Balances[Tree View]']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Account Groups for Reporting']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Cash and Bank Book']");
        //radioButton
        common.clickElement("xpath","//RadioButton[@Name='Cash and Bank Book with Day wise Balance']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable Balances Reports']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Customer Balances']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Supplier Balances']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Account Balances Summary']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Transfer Incomes and Expenses to PL']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Manual Stock valuation']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");
        //Module Settings
        common.clickElement("xpath","//Button[@Name='Module Settings']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable multi currency in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable bill wise accounting in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable bills receivables and bills payables tabs in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable discount in receipts and payments transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Use PDCs']");
        //payments
        enableCheckboxSelection("//Pane//CheckBox[@Name='Receipts']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Post Dated Cheques']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Cheques [PDC]']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Payments']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Payments']/parent::Pane/parent::Pane/parent::Pane/parent::Pane/following-sibling::List/Pane/Pane//CheckBox[@Name='Post Dated Cheques']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Payments']/parent::Pane/parent::Pane/parent::Pane/parent::Pane/following-sibling::List/Pane/Pane//CheckBox[@Name='Cheques [PDC]']");
        //bankin
        enableCheckboxSelection("//Pane//CheckBox[@Name='Bank Charges']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Bank Charges']/parent::Pane/parent::Pane/parent::Pane/parent::Pane/following-sibling::Pane/Pane//CheckBox[@Name='Receipts']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Bank Charges']/parent::Pane/parent::Pane/parent::Pane/parent::Pane/following-sibling::Pane/Pane//CheckBox[@Name='Payments']");
        //scroll
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,150);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable GST on advance receipts']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable calculate 18% IGST on advances.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Auto sum for advance set off.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='GST on advance receipts.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable invoiceno and invoicedate.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show cash details in all cash receipts transaction.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable other credits in  all  receipts transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable other debits in  all  payments transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable budgets']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable drawn on bank.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Disable book expenses or  payable transaction supplytype editable..']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");

        //Policies
        common.clickElement("xpath","//Button[@Name='Policies']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show account balance in chart of accounts window.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable manual stock valuation.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable do not edit bank transactions after reconciled']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Restrict negative cash alerts in payment transactions.']");
        common.clickElement("xpath","//RadioButton[@Name='Company wise']");
        //payments
        enableCheckboxSelection("//Pane//CheckBox[@Name='Override alerts']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable credit limits in all sales transactions.']");
        common.clickElement("xpath","//Pane//CheckBox[@Name='Enable credit limits in all sales transactions.']/parent::Pane/parent::Pane/parent::Pane/parent::Pane/following-sibling::List/Pane/Pane//RadioButton[@Name='Company wise']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Override limits']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable credit periods in all transactions']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Overdue bill alerts.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Overdue bill warnings']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='No of bills exceed warnings']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");
        common.clickElement("xpath","//Window//Button[@Name='Close']");
    }
    public void generalSettings() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        common.clickElement("xpath","//HyperLink[@Name='General']");
        Thread.sleep(1500);

        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable Customers.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable Suppliers.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Create auto codes for customers in all sales transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable party account code in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable party description in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable product code in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable product description in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable account code in all transctions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable account description in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show stock balance in product window.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show product history in stock search window.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show pending orders in stock search window.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Show stock search option to all users.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable consignor in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable consignor in finance transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable e-commerce in all transactions.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,100);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable Aadhaar number in party account properties.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable other charges in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable charges and deductions in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable maximum retail price [MRP] in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable round off']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable in all purchases transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable in all sales transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable in all finance transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable line wise net amount do not exceed MRP amount.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable default UOM in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable batches in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable batch and serial numbers in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Disable batch pop up.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable copy quantity in stock details form.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,100);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Editable gross amount for reverse calculation.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable terms and term types.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable reasons.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable user wise voucher series.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='User wise restrict transaction editing after.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable DOB and anniversary date']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable UPI Payments']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable sales terms and purchases term details in company property.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable Voucher Statistics Report.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable Deleted Transactions Report.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable Deleted and Void Transactions Report.']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");
    }
    public void taxesSettings() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        common.clickElement("xpath","//HyperLink[@Name='Taxes']");
        Thread.sleep(1500);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable Goods and Service Tax']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable in all purchase transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable in all sales transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable in all inventory transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Finance']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable in all receipt transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable in all payment transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable inclusive tax in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable SGST Common for all states.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable GST CESS.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable GST Service CESS.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable GST Service RCM CESS.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Basis for CESS Info.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable GST E-Invoice']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,70);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable RCM in all purchase and payment transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable RCM in all sales and receipts transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable RCM in other charges.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable GST rate on apparel and footwear on the basis of sale value.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable shipping address in sales transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable despatch address in sales transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable export shipping bill details.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Allow multiple GST Service Providers']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable GST additional tabs in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable GST additional columns in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable round off in HSN Code properties.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,70);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable abatement in HSN Code properties.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable all columns in GST Details.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable GST Category in all transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable HSN Code and Goods or Services in all transactions.']");
        //TDS
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable Tax Deducted at Source']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable in book expenses/payment transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable in book incomes/receipt transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable TDS amount read only in all payment transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable TDS for services/accounts tab in all payments transactions.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,70);
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable other charges in TDS payments transactions.']");
        //TCS
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable Tax Collected at Source']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable in purchase transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable in sales transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable in finance transactions.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable round off in TCS transaction nature properties.']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable other charges in TCS payments transactions.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,35);
        //E-way Bill
        enableCheckboxSelection("//Pane//CheckBox[@Name='Enable E-Way Bill']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='E-Way Bill Offline']");
        enableCheckboxSelection("//Pane//CheckBox[@Name='E-Way Bill Online']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");
    }
}