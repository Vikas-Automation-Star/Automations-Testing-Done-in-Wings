package com.wings.utils;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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
        configureCheckboxSelection("//Pane//CheckBox[@Name='Do not close the transaction after save If transaction tab has more than 200 Lines']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Reset voucher series when financial year changes in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Allow save draft for all users in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show barcode panel']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show file based barcode configuration.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Toolbar:show text with icons in all transactions and reports.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Allow renaming of masters by pressing F2 in master window.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Allow moving of masters and nodes by drag and drop in master window.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Confirmation for closing master window.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show properties in master creation window.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,40);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable lookup settings in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show master code in master combos.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show description in master combos.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show master node in master combos.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show transaction field lookup configuration.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show balances']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show account balance in master combos.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show stock balance in master combos.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Advanced options']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,40);
        common.clickElement("xpath","//RadioButton[@Name='Use advanced combo in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Confirmation for closing transaction window in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Confirmation for saving transaction in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show edit/view voucher option in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show borders for text fields in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Word wrap field names in header for all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Autowidth in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Allow entering transactions on future date in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Use date lock for previous entries in all transactions.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,28);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable Incremental Voucher No']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable chart of accounts in financial Accounting.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable voucher types in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable line comments in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show images in master combos.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable the automatic deletion of invalid rows for pending bills in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable reference bill details in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable other info in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Other Info 1']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Other Info 2']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Other Info 3']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Other Info 4']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,20);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Other Info 5']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,100);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show branch name in header in all reports.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show zero values in all reports.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Supress currency prefix in all reports.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show horizontal lines in all reports.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Do not print serial number column in all reports.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show company address in all reports.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,30);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show report header in first page for all reports.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Confirmation for closing report window in all reports.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Refresh pendings.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,25);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Allow draft print in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Allow printing of unauthorised transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show form design footer in last page.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,45);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Add extended fields on extended field in invoice design.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable online print templates.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable quick printing in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable grapical invoice design and print templates.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable print and draft templates.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,30);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Restrict negative stock alerts in issues transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Product batch wise']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Storage bin wise']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Override alerts']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable reorder level alerts.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Override reorder level']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");
    }
    public void inventoryWorkFlow() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        common.clickElement("xpath","//HyperLink[@Name='Inventory']");
        common.clickElement("xpath","//Button[@Name='Work Flow']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Opening Stock']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Stock Creation']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Stock Consumption']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Stock Conversion']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Physical Stock Verification']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");

        inventoryModuleSettings();
        inventoryPolicies();
    }
    public void inventoryModuleSettings() throws InterruptedException {
//        common.clickElement("xpath","//TabItem[@Name='Configure']");
//        common.clickElement("xpath","//HyperLink[@Name='Inventory']");
        common.clickElement("xpath","//Button[@Name='Module Settings']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable products.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable services.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable non inventory items.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable stock management units in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable multiple stock management units in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable free units in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable free SKU units in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable save transaction with free quantity in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable number of packs in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable value per unit.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable cost price for material issues']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable company wise reorder level']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");
        //close
//        common.clickElement("xpath","//Window//Button[@Name='Close']");
    }
    public void inventoryPolicies() throws InterruptedException {
//        common.clickElement("xpath","//TabItem[@Name='Configure']");
//        common.clickElement("xpath","//HyperLink[@Name='Inventory']");
        common.clickElement("xpath","//Button[@Name='Policies']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable multiple locations under branch in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable storagebins under location in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable additional info fields in product creation window.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable additional value fields in product creation window.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable product attributes in product creation window.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Product categories']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Product sub categories']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Product types']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Product brands']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Product classes']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Product sub classes']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Seasons']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Styles']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");
        //close
        common.clickElement("xpath","//Window//Button[@Name='Close']");
    }
    public void productionWorkFlow() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        common.clickElement("xpath","//HyperLink[@Name='Production']");
        common.clickElement("xpath","//Button[@Name='Work Flow']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable assign standard rates']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable simple production flow']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable detailed production flow']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");
        //close
//        common.clickElement("xpath","//Window//Button[@Name='Close']");
        //Module settings
        productionModuleSettings();
    }
    public void productionModuleSettings() throws InterruptedException {
//        common.clickElement("xpath","//TabItem[@Name='Configure']");
//        common.clickElement("xpath","//HyperLink[@Name='Production']");
        common.clickElement("xpath","//Button[@Name='Module Settings']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable Bills of Material']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Disable batch pop up']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");
        //close
        common.clickElement("xpath","//Window//Button[@Name='Close']");
    }
    public void financeWorkFlow() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        common.clickElement("xpath","//HyperLink[@Name='Finance']");
        common.clickElement("xpath","//Button[@Name='Work Flow']");

        configureCheckboxSelection("//Pane//CheckBox[@Name='Receipts']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Receipts from Parties']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Cash Receipts']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Bank Receipts']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Credit Card Receipts']");
        //payments
        configureCheckboxSelection("//Pane//CheckBox[@Name='Payments']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Payments to Parties']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Cash Payments']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Bank Payments']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Cash Transfers']");
        //banking
        configureCheckboxSelection("//Pane//CheckBox[@Name='Banking']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Cash Deposits and Withdrawals']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Inter Bank Fund Transfers']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Deposit Post Dated Cheques']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Received Cheque Bounce']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,100);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Issued Cheque Bounce']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Bank Reconciliation']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Opening Uncleared Bank Entries']");
        //Party Adjustments
        configureCheckboxSelection("//Pane//CheckBox[@Name='Party Adjustments']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Credit Note']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Debit Note']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Debit Note on Customer']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Credit Note on Customer']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Debit Note from Supplier']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Credit Note from Supplier']");
        //Journals
        configureCheckboxSelection("//Pane//CheckBox[@Name='Journal']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Journal Entries']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Book Incomes or Receivables']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Book Expenses or Payables']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,100);
        //Openings
        configureCheckboxSelection("//Pane//CheckBox[@Name='Openings']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Party Opening Balances']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Opening Balances']");
        //Reports
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,20);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Reports']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Profit and Loss T-Form']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Balance Sheet T-Form']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,20);
        Thread.sleep(1000);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Ledger with Interest']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Day wise Ledger Summary']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Account Balances[Tree View]']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Account Groups for Reporting']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Cash and Bank Book']");
        //radioButton
        common.clickElement("xpath","//RadioButton[@Name='Cash and Bank Book with Day wise Balance']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable Balances Reports']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Customer Balances']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,10);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Supplier Balances']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Account Balances Summary']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Transfer Incomes and Expenses to PL']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Manual Stock valuation']");
        //save
//        common.clickElement("xpath","//Button[@Name='Save']");
//        common.clickElement("xpath","//Button[@Name='OK']");
//        Thread.sleep(1500);
//        common.clickElement("xpath","//Button[@Name='OK']");
    }
    public void financeModuleSettings(){
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        common.clickElement("xpath","//HyperLink[@Name='Finance']");
        common.clickElement("xpath","//Button[@Name='Module Settings']");

        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable multi currency in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable bill wise accounting in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable bills receivables and bills payables tabs in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable discount in receipts and payments transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Use PDCs']");
        //payments
        configureCheckboxSelection("//Pane//CheckBox[@Name='Receipts']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Post Dated Cheques']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Cheques [PDC]']");
//        common.clickElement("xpath", "//Edit[@Name='Bank Details']/following-sibling::Button[@Name='Open']");

        configureCheckboxSelection("//Pane//CheckBox[@Name='Payments']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Post Dated Cheques']");
        //bankin
        configureCheckboxSelection("//Pane//CheckBox[@Name='Cheques [PDC]']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Bank Charges']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Receipts']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Payments']");
        //scroll
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,150);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable GST on advance receipts']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable calculate 18% IGST on advances.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Auto sum for advance set off.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='GST on advance receipts.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable invoiceno and invoicedate.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show cash details in all cash receipts transaction.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable other credits in  all  receipts transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable other debits in  all  payments transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable budgets']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable drawn on bank.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Disable book expenses or  payable transaction supplytype editable..']");
        //save
//        common.clickElement("xpath","//Button[@Name='Save']");
//        common.clickElement("xpath","//Button[@Name='OK']");
//        Thread.sleep(1500);
//        common.clickElement("xpath","//Button[@Name='OK']");
    }
    public void financePolicies() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        common.clickElement("xpath","//HyperLink[@Name='Finance']");
        common.clickElement("xpath","//Button[@Name='Policies']");

        configureCheckboxSelection("//Pane//CheckBox[@Name='Show account balance in chart of accounts window.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable manual stock valuation.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable do not edit bank transactions after reconciled']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Restrict negative cash alerts in payment transactions.']");
        common.clickElement("xpath","//RadioButton[@Name='Company wise']");
        //payments
        configureCheckboxSelection("//Pane//CheckBox[@Name='Override alerts']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable credit limits in all sales transactions.']");
        common.clickElement("xpath","//RadioButton[@Name='Company wise']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Override limits']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable credit periods in all transactions']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Overdue bill alerts.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Overdue bill warnings']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='No of bills exceed warnings']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");
        //close
        common.clickElement("xpath","//Window//Button[@Name='Close']");
    }

    public void generalSettings() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        common.clickElement("xpath","//HyperLink[@Name='General']");
        Thread.sleep(1500);

        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable Customers.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable Suppliers.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Create auto codes for customers in all sales transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable party account code in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable party description in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable product code in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable product description in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable account code in all transctions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable account description in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show stock balance in product window.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show product history in stock search window.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show pending orders in stock search window.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Show stock search option to all users.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable consignor in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable consignor in finance transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable e-commerce in all transactions.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,100);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable Aadhaar number in party account properties.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable other charges in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable charges and deductions in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable maximum retail price [MRP] in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable round off']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable in all purchases transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable in all sales transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable in all finance transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable line wise net amount do not exceed MRP amount.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable default UOM in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable batches in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable batch and serial numbers in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Disable batch pop up.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable copy quantity in stock details form.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,100);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Editable gross amount for reverse calculation.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable terms and term types.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable reasons.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable user wise voucher series.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='User wise restrict transaction editing after.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable DOB and anniversary date']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable UPI Payments']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable sales terms and purchases term details in company property.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable Voucher Statistics Report.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable Deleted Transactions Report.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable Deleted and Void Transactions Report.']");
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

        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable Goods and Service Tax']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable in all purchase transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable in all sales transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable in all inventory transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Finance']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable in all receipt transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable in all payment transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable inclusive tax in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable SGST Common for all states.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable GST CESS.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable GST Service CESS.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable GST Service RCM CESS.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Basis for CESS Info.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable GST E-Invoice']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,70);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable RCM in all purchase and payment transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable RCM in all sales and receipts transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable RCM in other charges.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable GST rate on apparel and footwear on the basis of sale value.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable shipping address in sales transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable despatch address in sales transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable export shipping bill details.']");

        configureCheckboxSelection("//Pane//CheckBox[@Name='Allow multiple GST Service Providers']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable GST additional tabs in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable GST additional columns in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable round off in HSN Code properties.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,70);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable abatement in HSN Code properties.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable all columns in GST Details.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable GST Category in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable HSN Code and Goods or Services in all transactions.']");
        //TDS
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable Tax Deducted at Source']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable in book expenses/payment transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable in book incomes/receipt transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable TDS amount read only in all payment transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable TDS for services/accounts tab in all payments transactions.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,70);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable other charges in TDS payments transactions.']");
        //TCS
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable Tax Collected at Source']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable in purchase transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable in sales transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable in finance transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable round off in TCS transaction nature properties.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable other charges in TCS payments transactions.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,35);
        //E-way Bill
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable E-Way Bill']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='E-Way Bill Offline']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='E-Way Bill Online']");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");
    }
}