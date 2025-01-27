package com.wings.utils;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CompanyInitialisationGSTConfiguration {
    WindowsDriver driver;
    Common common;
    public CompanyInitialisationGSTConfiguration(WindowsDriver driver){
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
            //General
//        WebElement closeTransaction = driver.findElementByXPath("//Pane//CheckBox[@Name='Do not close the transaction after save If transaction tab has more than 200 Lines']");
//        String closeTransactionToggleState = closeTransaction.getAttribute("Toggle.ToggleState");
//        System.out.println("Close Transaction Toggle state:-" + closeTransactionToggleState);
//        if (closeTransactionToggleState.equals("0")) {
//            closeTransaction.click();
//            System.out.println("Close Transaction Checkbox was unchecked, now checked.");
//        } else if (closeTransactionToggleState.equals("1")){
//            System.out.println("Close Transaction Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //2
//        WebElement resetVoucher = driver.findElementByXPath("//Pane//CheckBox[@Name='Reset voucher series when financial year changes in all transactions.']");
//        String resetVoucherToggleState = resetVoucher.getAttribute("Toggle.ToggleState");
//        System.out.println("Reset Voucher Toggle state:-" + resetVoucherToggleState);
//        if (resetVoucherToggleState.equals("0")) {
//            resetVoucher.click();
//            System.out.println("Reset Voucher Checkbox was unchecked, now checked.");
//        } else if (resetVoucherToggleState.equals("1")){
//            System.out.println("Reset Voucher Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //3
//        WebElement saveDraft = driver.findElementByXPath("//Pane//CheckBox[@Name='Allow save draft for all users in all transactions.']");
//        String saveDraftToggleState = saveDraft.getAttribute("Toggle.ToggleState");
//        System.out.println("Save Draft Toggle state:-" + saveDraftToggleState);
//        if (saveDraftToggleState.equals("0")) {
//            saveDraft.click();
//            System.out.println("Save Draft Checkbox was unchecked, now checked.");
//        } else if (saveDraftToggleState.equals("1")){
//            System.out.println("Save Draft Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //4
//        WebElement barCodePanelCentre = driver.findElementByXPath("//Pane//CheckBox[@Name='Show barcode panel']");
//        String barcodeToggleState = barCodePanelCentre.getAttribute("Toggle.ToggleState");
//        System.out.println("Barcode Panel Toggle state:-" + barcodeToggleState);
//        if (barcodeToggleState.equals("0")) {
//            barCodePanelCentre.click();
//            System.out.println("Barcode Panel Checkbox was unchecked, now checked.");
//        } else if (barcodeToggleState.equals("1")){
//            System.out.println("Barcode Panel Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //5
//        WebElement fileBasedBarcode = driver.findElementByXPath("//Pane//CheckBox[@Name='Show file based barcode configuration.']");
//        String barcodeAttribute = fileBasedBarcode.getAttribute("Toggle.ToggleState");
//        System.out.println("Barcode Toggle state:-" + barcodeAttribute);
//        if (barcodeAttribute.equals("0")) {
//            fileBasedBarcode.click();
//            System.out.println("Barcode checkbox was unchecked, now checked.");
//        } else if (barcodeAttribute.equals("1")){
//            System.out.println("Barcode checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //6
//        WebElement toolBar = driver.findElementByXPath("//Pane//CheckBox[@Name='Toolbar:show text with icons in all transactions and reports.']");
//        String toolBarToggleState = toolBar.getAttribute("Toggle.ToggleState");
//        System.out.println("Toolbar Toggle state:-" + toolBarToggleState);
//        if (toolBarToggleState.equals("0")) {
//            toolBar.click();
//            System.out.println("Toolbar Checkbox was unchecked, now checked.");
//        } else if (toolBarToggleState.equals("1")){
//            System.out.println("Toolbar Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//            //Master
//        WebElement renameMaster = driver.findElementByXPath("//Pane//CheckBox[@Name='Allow renaming of masters by pressing F2 in master window.']");
//        String renameMasterAttribute = renameMaster.getAttribute("Toggle.ToggleState");
//        System.out.println("Rename Master Toggle state:-" + renameMasterAttribute);
//        if (renameMasterAttribute.equals("0")) {
//            renameMaster.click();
//            System.out.println("Rename Master Checkbox was unchecked, now checked.");
//        } else if (renameMasterAttribute.equals("1")){
//            System.out.println("Rename Master Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //2
//        WebElement moveMaster = driver.findElementByXPath("//Pane//CheckBox[@Name='Allow moving of masters and nodes by drag and drop in master window.']");
//        String moveMasterAttribute = moveMaster.getAttribute("Toggle.ToggleState");
//        System.out.println("Move Master Toggle state:-" + moveMasterAttribute);
//        if (moveMasterAttribute.equals("0")) {
//            moveMaster.click();
//            System.out.println("Move Master Checkbox was unchecked, now checked.");
//        } else if (moveMasterAttribute.equals("1")){
//            System.out.println("Move Master Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //3
//        WebElement closeWindowConfig = driver.findElementByXPath("//Pane//CheckBox[@Name='Confirmation for closing master window.']");
//        String closeWindowConfigAttribute = closeWindowConfig.getAttribute("Toggle.ToggleState");
//        System.out.println("Close Window Config Toggle state:-" + closeWindowConfigAttribute);
//        if (closeWindowConfigAttribute.equals("0")) {
//            closeWindowConfig.click();
//            System.out.println("Close Window Config Checkbox was unchecked, now checked.");
//        } else if (closeWindowConfigAttribute.equals("1")){
//            System.out.println("Close Window Config Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //4
//        WebElement propertiesMaster = driver.findElementByXPath("//Pane//CheckBox[@Name='Show properties in master creation window.']");
//        String propertiesMasterAttribute = propertiesMaster.getAttribute("Toggle.ToggleState");
//        System.out.println("Properties Master Toggle state:-" + propertiesMasterAttribute);
//        if (propertiesMasterAttribute.equals("0")) {
//            propertiesMaster.click();
//            System.out.println("Properties Master Checkbox was unchecked, now checked.");
//        } else if (propertiesMasterAttribute.equals("1")){
//            System.out.println("Properties Master Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
        //scroll down
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,40);
        // TRANSACTIONS
//        WebElement lookUpSettings = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable lookup settings in all transactions.']");
//        String lookUpSettingsAttribute = lookUpSettings.getAttribute("Toggle.ToggleState");
//        System.out.println("Look up Settings Toggle state:-" + lookUpSettingsAttribute);
//        if (lookUpSettingsAttribute.equals("0")) {
//            lookUpSettings.click();
//            System.out.println("Look up Settings Checkbox was unchecked, now checked.");
//        } else if (lookUpSettingsAttribute.equals("1")){
//            System.out.println("Look up Settings Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //2
//        WebElement masterCode = driver.findElementByXPath("//Pane//CheckBox[@Name='Show master code in master combos.']");
//        String masterCodeAttribute = masterCode.getAttribute("Toggle.ToggleState");
//        System.out.println("Master Code Toggle state:-" + masterCodeAttribute);
//        if (masterCodeAttribute.equals("0")) {
//            masterCode.click();
//            System.out.println("Master Code Checkbox was unchecked, now checked.");
//        } else if (masterCodeAttribute.equals("1")){
//            System.out.println("Master Code Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //3
//        WebElement description = driver.findElementByXPath("//Pane//CheckBox[@Name='Show description in master combos.']");
//        String descriptionAttribute = description.getAttribute("Toggle.ToggleState");
//        System.out.println("Description Toggle state:-" + descriptionAttribute);
//        if (descriptionAttribute.equals("0")) {
//            description.click();
//            System.out.println("Description Checkbox was unchecked, now checked.");
//        } else if (descriptionAttribute.equals("1")){
//            System.out.println("Description Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //4
//        WebElement masterNode = driver.findElementByXPath("//Pane//CheckBox[@Name='Show master node in master combos.']");
//        String masterNodeAttribute = masterNode.getAttribute("Toggle.ToggleState");
//        System.out.println("Master Node Toggle state:-" + masterNodeAttribute);
//        if (masterNodeAttribute.equals("0")) {
//            masterNode.click();
//            System.out.println("Master Node Checkbox was unchecked, now checked.");
//        } else if (masterNodeAttribute.equals("1")){
//            System.out.println("Master Node Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //5
//        WebElement transactionField = driver.findElementByXPath("//Pane//CheckBox[@Name='Show transaction field lookup configuration.']");
//        String transactionFieldAttribute = transactionField.getAttribute("Toggle.ToggleState");
//        System.out.println("Transaction Field Look up Toggle state:-" + transactionFieldAttribute);
//        if (transactionFieldAttribute.equals("0")) {
//            transactionField.click();
//            System.out.println("Transaction Field Look up Checkbox was unchecked, now checked.");
//        } else if (transactionFieldAttribute.equals("1")){
//            System.out.println("Transaction Field Look up Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //6
//        WebElement balances = driver.findElementByXPath("//Pane//CheckBox[@Name='Show balances']");
//        String balancesAttribute = balances.getAttribute("Toggle.ToggleState");
//        System.out.println("Balances Toggle state:-" + balancesAttribute);
//        if (balancesAttribute.equals("0")) {
//            balances.click();
//            System.out.println("Balances Checkbox was unchecked, now checked.");
//        } else if (balancesAttribute.equals("1")){
//            System.out.println("Balances Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //7
//        WebElement accountBalances = driver.findElementByXPath("//Pane//CheckBox[@Name='Show account balance in master combos.']");
//        String accountBalancesAttribute = accountBalances.getAttribute("Toggle.ToggleState");
//        System.out.println("Account Balances Toggle state:-" + accountBalancesAttribute);
//        if (accountBalancesAttribute.equals("0")) {
//            accountBalances.click();
//            System.out.println("Account Balances Checkbox was unchecked, now checked.");
//        } else if (accountBalancesAttribute.equals("1")){
//            System.out.println("Account Balances Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //8
//        WebElement stockBalances = driver.findElementByXPath("//Pane//CheckBox[@Name='Show stock balance in master combos.']");
//        String stockBalancesAttribute = stockBalances.getAttribute("Toggle.ToggleState");
//        System.out.println("Stock Balances Toggle state:-" + stockBalancesAttribute);
//        if (stockBalancesAttribute.equals("0")) {
//            stockBalances.click();
//            System.out.println("Stock Balances Checkbox was unchecked, now checked.");
//        } else if (stockBalancesAttribute.equals("1")){
//            System.out.println("Stock Balances Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //9
//        WebElement advanceOptions = driver.findElementByXPath("//Pane//CheckBox[@Name='Advanced options']");
//        String advanceOptionsAttribute = advanceOptions.getAttribute("Toggle.ToggleState");
//        System.out.println("Advance Options Toggle state:-" + advanceOptionsAttribute);
//        if (advanceOptionsAttribute.equals("0")) {
//            advanceOptions.click();
//            System.out.println("Advance Options Checkbox was unchecked, now checked.");
//        } else if (advanceOptionsAttribute.equals("1")){
//            System.out.println("Advance Options Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
        //scroll
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,40);
        //radio button
//        common.clickElement("xpath","//RadioButton[@Name='Use advanced combo in all transactions.']");
//        //10
//        WebElement closingTransactionConfirmation = driver.findElementByXPath("//Pane//CheckBox[@Name='Confirmation for closing transaction window in all transactions.']");
//        String confirmationAttribute = closingTransactionConfirmation.getAttribute("Toggle.ToggleState");
//        System.out.println("Confirm Close Toggle state:-" + confirmationAttribute);
//        if (confirmationAttribute.equals("0")) {
//            closingTransactionConfirmation.click();
//            System.out.println("Confirm Close Checkbox was unchecked, now checked.");
//        } else if (confirmationAttribute.equals("1")){
//            System.out.println("Confirm Close Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //11
//        WebElement saveTransactionConfirmation = driver.findElementByXPath("//Pane//CheckBox[@Name='Confirmation for saving transaction in all transactions.']");
//        String saveTransactionConfirmationAttribute = saveTransactionConfirmation.getAttribute("Toggle.ToggleState");
//        System.out.println("Confirm Save Toggle state:-" + saveTransactionConfirmationAttribute);
//        if (saveTransactionConfirmationAttribute.equals("0")) {
//            saveTransactionConfirmation.click();
//            System.out.println("Confirm Save Checkbox was unchecked, now checked.");
//        } else if (saveTransactionConfirmationAttribute.equals("1")){
//            System.out.println("Confirm Save Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//
//        //12
//        WebElement editView = driver.findElementByXPath("//Pane//CheckBox[@Name='Show edit/view voucher option in all transactions.']");
//        String editViewAttributeAttribute = editView.getAttribute("Toggle.ToggleState");
//        System.out.println("Confirm Save Toggle state:-" + editViewAttributeAttribute);
//        if (editViewAttributeAttribute.equals("0")) {
//            editView.click();
//            System.out.println("Confirm Save Checkbox was unchecked, now checked.");
//        } else if (editViewAttributeAttribute.equals("1")){
//            System.out.println("Confirm Save Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //13
//        WebElement showBorders = driver.findElementByXPath("//Pane//CheckBox[@Name='Show borders for text fields in all transactions.']");
//        String showBordersAttribute = showBorders.getAttribute("Toggle.ToggleState");
//        System.out.println("Show Border Toggle state:-" + showBordersAttribute);
//        if (showBordersAttribute.equals("0")) {
//            showBorders.click();
//            System.out.println("Show Border Checkbox was unchecked, now checked.");
//        } else if (showBordersAttribute.equals("1")){
//            System.out.println("Show Border Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//            //14
//        WebElement wordWrap = driver.findElementByXPath("//Pane//CheckBox[@Name='Word wrap field names in header for all transactions.']");
//        String wordWrapAttribute = wordWrap.getAttribute("Toggle.ToggleState");
//        System.out.println("Word Wrap Toggle state:-" + wordWrapAttribute);
//        if (wordWrapAttribute.equals("0")) {
//            wordWrap.click();
//            System.out.println("Word Wrap Checkbox was unchecked, now checked.");
//        } else if (wordWrapAttribute.equals("1")){
//            System.out.println("Word Wrap Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //15
//        WebElement autoWidth = driver.findElementByXPath("//Pane//CheckBox[@Name='Autowidth in all transactions.']");
//        String autoWidthConfirmationAttribute = autoWidth.getAttribute("Toggle.ToggleState");
//        System.out.println("Auto Width Toggle state:-" + autoWidthConfirmationAttribute);
//        if (autoWidthConfirmationAttribute.equals("0")) {
//            autoWidth.click();
//            System.out.println("Auto width Checkbox was unchecked, now checked.");
//        } else if (autoWidthConfirmationAttribute.equals("1")){
//            System.out.println("Auto width Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //16
//        WebElement futureDateTrans = driver.findElementByXPath("//Pane//CheckBox[@Name='Allow entering transactions on future date in all transactions.']");
//        String futureTransaConfirmationAttribute = futureDateTrans.getAttribute("Toggle.ToggleState");
//        System.out.println("future Transaction Toggle state:-" + futureTransaConfirmationAttribute);
//        if (futureTransaConfirmationAttribute.equals("0")) {
//            futureDateTrans.click();
//            System.out.println("future Transaction Checkbox was unchecked, now checked.");
//        } else if (futureTransaConfirmationAttribute.equals("1")){
//            System.out.println("future Transaction Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //17
//        WebElement dateLock = driver.findElementByXPath("//Pane//CheckBox[@Name='Use date lock for previous entries in all transactions.']");
//        String dateLockAttribute = dateLock.getAttribute("Toggle.ToggleState");
//        System.out.println("Date Lock Toggle state:-" + dateLockAttribute);
//        if (dateLockAttribute.equals("0")) {
//            dateLock.click();
//            System.out.println("Date lock Checkbox was unchecked, now checked.");
//        } else if (dateLockAttribute.equals("1")){
//            System.out.println("date lock Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
        //scroll
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,28);
            //TRANSACTION-SET UP
//        WebElement voucherNo = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable Incremental Voucher No']");
//        String voucherNoAttributeAttribute = voucherNo.getAttribute("Toggle.ToggleState");
//        System.out.println("Voucher No Toggle state:-" + voucherNoAttributeAttribute);
//        if (voucherNoAttributeAttribute.equals("0")) {
//            voucherNo.click();
//            System.out.println("Voucher No Checkbox was unchecked, now checked.");
//        } else if (voucherNoAttributeAttribute.equals("1")){
//            System.out.println("Voucher No Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //2
//        WebElement chartOfAcc = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable chart of accounts in financial Accounting.']");
//        String chartOfAccAttribute = chartOfAcc.getAttribute("Toggle.ToggleState");
//        System.out.println("COA Toggle state:-" + chartOfAccAttribute);
//        if (chartOfAccAttribute.equals("0")) {
//            chartOfAcc.click();
//            System.out.println("COA Checkbox was unchecked, now checked.");
//        } else if (chartOfAccAttribute.equals("1")){
//            System.out.println("COA Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //3
//        WebElement voucherType = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable voucher types in all transactions.']");
//        String voucherTypeAttribute = voucherType.getAttribute("Toggle.ToggleState");
//        System.out.println("Voucher Type Toggle state:-" + voucherTypeAttribute);
//        if (voucherTypeAttribute.equals("0")) {
//            voucherType.click();
//            System.out.println("Voucher Type Checkbox was unchecked, now checked.");
//        } else if (voucherTypeAttribute.equals("1")){
//            System.out.println("Voucher Type Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //4
//        WebElement lineComments = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable line comments in all transactions.']");
//        String lineCommentsAttribute = lineComments.getAttribute("Toggle.ToggleState");
//        System.out.println("Line Comments Toggle state:-" + lineCommentsAttribute);
//        if (lineCommentsAttribute.equals("0")) {
//            lineComments.click();
//            System.out.println("Line Comments Checkbox was unchecked, now checked.");
//        } else if (lineCommentsAttribute.equals("1")){
//            System.out.println("Line Comments Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //5
//        WebElement images = driver.findElementByXPath("//Pane//CheckBox[@Name='Show images in master combos.']");
//        String imagesAttribute = images.getAttribute("Toggle.ToggleState");
//        System.out.println("Image Toggle state:-" + imagesAttribute);
//        if (imagesAttribute.equals("0")) {
//            images.click();
//            System.out.println("Image Checkbox was unchecked, now checked.");
//        } else if (imagesAttribute.equals("1")){
//            System.out.println("Image Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //6
//        WebElement autoDelete = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable the automatic deletion of invalid rows for pending bills in all transactions.']");
//        String autoDeleteAttribute = autoDelete.getAttribute("Toggle.ToggleState");
//        System.out.println("Auto Delete Toggle state:-" + autoDeleteAttribute);
//        if (autoDeleteAttribute.equals("0")) {
//            autoDelete.click();
//            System.out.println("Auto Delete Checkbox was unchecked, now checked.");
//        } else if (autoDeleteAttribute.equals("1")){
//            System.out.println("Auto Delete Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //7
//        WebElement referenceBill = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable reference bill details in all transactions.']");
//        String referenceBillAttribute = referenceBill.getAttribute("Toggle.ToggleState");
//        System.out.println("Reference Bill Toggle state:-" + referenceBillAttribute);
//        if (referenceBillAttribute.equals("0")) {
//            referenceBill.click();
//            System.out.println("Reference Bill Checkbox was unchecked, now checked.");
//        } else if (referenceBillAttribute.equals("1")){
//            System.out.println("Reference Bill Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //8
//        WebElement otherInfoEnable = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable other info in all transactions.']");
//        String otherInfoEnableAttribute = otherInfoEnable.getAttribute("Toggle.ToggleState");
//        System.out.println("Other Info Toggle state:-" + otherInfoEnableAttribute);
//        if (otherInfoEnableAttribute.equals("0")) {
//            otherInfoEnable.click();
//            System.out.println("Other Info Checkbox was unchecked, now checked.");
//        } else if (otherInfoEnableAttribute.equals("1")){
//            System.out.println("Other Info Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //9
//        WebElement otherInfoEnable1 = driver.findElementByXPath("//Pane//CheckBox[@Name='Other Info 1']");
//        String otherInfo1 = otherInfoEnable1.getAttribute("Toggle.ToggleState");
//        System.out.println("Other Info 1 Toggle state:-" + otherInfo1);
//        if (otherInfo1.equals("0")) {
//            otherInfoEnable1.click();
//            System.out.println("Other Info 1 Checkbox was unchecked, now checked.");
//        } else if (otherInfo1.equals("1")){
//            System.out.println("Other Info 1 Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //10
//        WebElement otherInfoEnable2 = driver.findElementByXPath("//Pane//CheckBox[@Name='Other Info 2']");
//        String otherInfo2 = otherInfoEnable2.getAttribute("Toggle.ToggleState");
//        System.out.println("Other Info 2 Toggle state:-" + otherInfo2);
//        if (otherInfo2.equals("0")) {
//            otherInfoEnable2.click();
//            System.out.println("Other Info 2 Checkbox was unchecked, now checked.");
//        } else if (otherInfo2.equals("1")){
//            System.out.println("Other Info  2 Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //11
//        WebElement otherInfoEnable3 = driver.findElementByXPath("//Pane//CheckBox[@Name='Other Info 3']");
//        String otherInfo3 = otherInfoEnable3.getAttribute("Toggle.ToggleState");
//        System.out.println("Other Info 3 Toggle state:-" + otherInfo3);
//        if (otherInfo3.equals("0")) {
//            otherInfoEnable3.click();
//            System.out.println("Other Info 3 Checkbox was unchecked, now checked.");
//        } else if (otherInfo3.equals("1")){
//            System.out.println("Other Info 3 Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //12
//        WebElement otherInfoEnable4 = driver.findElementByXPath("//Pane//CheckBox[@Name='Other Info 4']");
//        String otherInfo4 = otherInfoEnable4.getAttribute("Toggle.ToggleState");
//        System.out.println("Other Info 4 Toggle state:-" + otherInfo4);
//        if (otherInfo4.equals("0")) {
//            otherInfoEnable4.click();
//            System.out.println("Other Info 4 Checkbox was unchecked, now checked.");
//        } else if (otherInfo4.equals("1")){
//            System.out.println("Other Info4  Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
        //scroll
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,20);
        //13
//        WebElement otherInfoEnable5 = driver.findElementByXPath("//Pane//CheckBox[@Name='Other Info 5']");
//        String otherInfo5 = otherInfoEnable5.getAttribute("Toggle.ToggleState");
//        System.out.println("Other Info 5 Toggle state:-" + otherInfo5);
//        if (otherInfo5.equals("0")) {
//            otherInfoEnable5.click();
//            System.out.println("Other Info 5 Checkbox was unchecked, now checked.");
//        } else if (otherInfo5.equals("1")){
//            System.out.println("Other Info 5 Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
        //scroll bar
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,60);
        //REPORTS
//        WebElement branchName = driver.findElementByXPath("//Pane//CheckBox[@Name='Show branch name in header in all reports.']");
//        String branchNameAttribute = branchName.getAttribute("Toggle.ToggleState");
//        System.out.println("Branch Name Toggle state:-" + branchNameAttribute);
//        if (branchNameAttribute.equals("0")) {
//            branchName.click();
//            System.out.println("Branch Name Checkbox was unchecked, now checked.");
//        } else if (branchNameAttribute.equals("1")){
//            System.out.println("Branch Name Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //2
//        WebElement zeroValues = driver.findElementByXPath("//Pane//CheckBox[@Name='Show zero values in all reports.']");
//        String zeroValuesAttribute = zeroValues.getAttribute("Toggle.ToggleState");
//        System.out.println("Zero Value Toggle state:-" + zeroValuesAttribute);
//        if (zeroValuesAttribute.equals("0")) {
//            zeroValues.click();
//            System.out.println("Zero Value Checkbox was unchecked, now checked.");
//        } else if (zeroValuesAttribute.equals("1")){
//            System.out.println("Zero Value Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //3
//        WebElement currencyPrefix = driver.findElementByXPath("//Pane//CheckBox[@Name='Supress currency prefix in all reports.']");
//        String currencyPrefixAttribute = currencyPrefix.getAttribute("Toggle.ToggleState");
//        System.out.println("Currency Prefix Toggle state:-" + currencyPrefixAttribute);
//        if (currencyPrefixAttribute.equals("0")) {
//            currencyPrefix.click();
//            System.out.println("Currency Prefix Checkbox was unchecked, now checked.");
//        } else if (currencyPrefixAttribute.equals("1")){
//            System.out.println("Currency Prefix Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //4
//        WebElement horizontalLines = driver.findElementByXPath("//Pane//CheckBox[@Name='Show horizontal lines in all reports.']");
//        String horizontalLinesAttribute = horizontalLines.getAttribute("Toggle.ToggleState");
//        System.out.println("horizontal Lines Toggle state:-" + horizontalLinesAttribute);
//        if (horizontalLinesAttribute.equals("0")) {
//            horizontalLines.click();
//            System.out.println("horizontal Lines Checkbox was unchecked, now checked.");
//        } else if (horizontalLinesAttribute.equals("1")){
//            System.out.println("horizontal Lines Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //5
//        WebElement serialNum = driver.findElementByXPath("//Pane//CheckBox[@Name='Do not print serial number column in all reports.']");
//        String serialNumAttribute = serialNum.getAttribute("Toggle.ToggleState");
//        System.out.println("Serial Num Toggle state:-" + serialNumAttribute);
//        if (serialNumAttribute.equals("0")) {
//            serialNum.click();
//            System.out.println("Serial Num Checkbox was unchecked, now checked.");
//        } else if (serialNumAttribute.equals("1")){
//            System.out.println("Serial Num Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //6
//        WebElement companyAddress = driver.findElementByXPath("//Pane//CheckBox[@Name='Show company address in all reports.']");
//        String companyAddressAttribute = companyAddress.getAttribute("Toggle.ToggleState");
//        System.out.println("Company Address Toggle state:-" + companyAddressAttribute);
//        if (companyAddressAttribute.equals("0")) {
//            companyAddress.click();
//            System.out.println("Company Address Checkbox was unchecked, now checked.");
//        } else if (companyAddressAttribute.equals("1")){
//            System.out.println("Company Address Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
        //scroll bar
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,30);
        //7
//        WebElement reportHeader = driver.findElementByXPath("//Pane//CheckBox[@Name='Show report header in first page for all reports.']");
//        String reportHeaderAttribute = reportHeader.getAttribute("Toggle.ToggleState");
//        System.out.println("Report Header Toggle state:-" + reportHeaderAttribute);
//        if (reportHeaderAttribute.equals("0")) {
//            reportHeader.click();
//            System.out.println("Report Header Checkbox was unchecked, now checked.");
//        } else if (reportHeaderAttribute.equals("1")){
//            System.out.println("Report Header Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //8
//        WebElement closeWindowConfirm = driver.findElementByXPath("//Pane//CheckBox[@Name='Confirmation for closing report window in all reports.']");
//        String closeWindowConfirmAttribute = closeWindowConfirm.getAttribute("Toggle.ToggleState");
//        System.out.println("Close Window Toggle state:-" + closeWindowConfirmAttribute);
//        if (closeWindowConfirmAttribute.equals("0")) {
//            closeWindowConfirm.click();
//            System.out.println("Close Window Checkbox was unchecked, now checked.");
//        } else if (closeWindowConfirmAttribute.equals("1")){
//            System.out.println("Close Window Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
//        //9
//        WebElement refreshPendings = driver.findElementByXPath("//Pane//CheckBox[@Name='Refresh pendings.']");
//        String refreshPendingsAttribute = refreshPendings.getAttribute("Toggle.ToggleState");
//        System.out.println("Refresh Pendings Toggle state:-" + refreshPendingsAttribute);
//        if (refreshPendingsAttribute.equals("0")) {
//            refreshPendings.click();
//            System.out.println("Refresh Pendings Checkbox was unchecked, now checked.");
//        } else if (refreshPendingsAttribute.equals("1")){
//            System.out.println("Refresh Pendings Checkbox is already checked, no action needed.");
//        }
//        else Assert.fail("Element Not Found");
        //scroll
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,25);
            //PRINTING
        WebElement draftPrint = driver.findElementByXPath("//Pane//CheckBox[@Name='Allow draft print in all transactions.']");
        String draftPrintAttribute = draftPrint.getAttribute("Toggle.ToggleState");
        System.out.println("Draft Print Toggle state:-" + draftPrintAttribute);
        if (draftPrintAttribute.equals("0")) {
            draftPrint.click();
            System.out.println("Draft Print Checkbox was unchecked, now checked.");
        } else if (draftPrintAttribute.equals("1")){
            System.out.println("Draft Print Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");
        //2
        WebElement unauthorisedTransaction = driver.findElementByXPath("//Pane//CheckBox[@Name='Allow printing of unauthorised transactions.']");
        String unauthorisedTransactionAttribute = unauthorisedTransaction.getAttribute("Toggle.ToggleState");
        System.out.println("Unauthorised Transaction Toggle state:-" + unauthorisedTransactionAttribute);
        if (unauthorisedTransactionAttribute.equals("0")) {
            unauthorisedTransaction.click();
            System.out.println("Unauthorised Transaction Checkbox was unchecked, now checked.");
        } else if (unauthorisedTransactionAttribute.equals("1")){
            System.out.println("Unauthorised Transaction Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");

        //3
        WebElement formDesign = driver.findElementByXPath("//Pane//CheckBox[@Name='Show form design footer in last page.']");
        String formDesignAttribute = formDesign.getAttribute("Toggle.ToggleState");
        System.out.println("form design Toggle state:-" + formDesignAttribute);
        if (formDesignAttribute.equals("0")) {
            formDesign.click();
            System.out.println("form design Checkbox was unchecked, now checked.");
        } else if (formDesignAttribute.equals("1")){
            System.out.println("form design Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");

        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,45);

        //4
        WebElement extendedFields = driver.findElementByXPath("//Pane//CheckBox[@Name='Add extended fields on extended field in invoice design.']");
        String extendedFieldsAttribute = extendedFields.getAttribute("Toggle.ToggleState");
        System.out.println("extended fields Toggle state:-" + extendedFieldsAttribute);
        if (extendedFieldsAttribute.equals("0")) {
            extendedFields.click();
            System.out.println("extended fields Checkbox was unchecked, now checked.");
        } else if (extendedFieldsAttribute.equals("1")){
            System.out.println("extended fields Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");
        //5
        WebElement onlinePrint = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable online print templates.']");
        String onlinePrintAttribute = onlinePrint.getAttribute("Toggle.ToggleState");
        System.out.println("online print Toggle state:-" + onlinePrintAttribute);
        if (onlinePrintAttribute.equals("0")) {
            onlinePrint.click();
            System.out.println("online print Checkbox was unchecked, now checked.");
        } else if (onlinePrintAttribute.equals("1")){
            System.out.println("online print Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");
        //6
        WebElement quickPrinting = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable quick printing in all transactions.']");
        String quickPrintingAttribute = quickPrinting.getAttribute("Toggle.ToggleState");
        System.out.println("quick printing Toggle state:-" + quickPrintingAttribute);
        if (quickPrintingAttribute.equals("0")) {
            quickPrinting.click();
            System.out.println("quick printing Checkbox was unchecked, now checked.");
        } else if (quickPrintingAttribute.equals("1")){
            System.out.println("quick printing Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");
        //7
        WebElement grapicalInvoice = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable grapical invoice design and print templates.']");
        String grapicalInvoiceAttribute = grapicalInvoice.getAttribute("Toggle.ToggleState");
        System.out.println("grapical invoice Toggle state:-" + grapicalInvoiceAttribute);
        if (grapicalInvoiceAttribute.equals("0")) {
            grapicalInvoice.click();
            System.out.println("grapical invoice Checkbox was unchecked, now checked.");
        } else if (grapicalInvoiceAttribute.equals("1")){
            System.out.println("grapical invoice Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");
        //8
        WebElement printDraft = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable print and draft templates.']");
        String printDraftAttribute = printDraft.getAttribute("Toggle.ToggleState");
        System.out.println("print and draft Toggle state:-" + printDraftAttribute);
        if (printDraftAttribute.equals("0")) {
            printDraft.click();
            System.out.println("print and draft Checkbox was unchecked, now checked.");
        } else if (printDraftAttribute.equals("1")){
            System.out.println("print and draft Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");
        //scroll
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/Thumb[@Name='Position']",0,30);
        //INVENTORY
        WebElement negativeStock = driver.findElementByXPath("//Pane//CheckBox[@Name='Restrict negative stock alerts in issues transactions.']");
        String negativeStockAttribute = negativeStock.getAttribute("Toggle.ToggleState");
        System.out.println("negative stock Toggle state:-" + negativeStockAttribute);
        if (negativeStockAttribute.equals("0")) {
            negativeStock.click();
            System.out.println("negative stock Checkbox was unchecked, now checked.");
        } else if (negativeStockAttribute.equals("1")){
            System.out.println("negative stock Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");
        //2
        WebElement productBatch = driver.findElementByXPath("//Pane//CheckBox[@Name='Product batch wise']");
        String productBatchAttribute = productBatch.getAttribute("Toggle.ToggleState");
        System.out.println("Product batch Toggle state:-" + productBatchAttribute);
        if (productBatchAttribute.equals("0")) {
            productBatch.click();
            System.out.println("Product batch Checkbox was unchecked, now checked.");
        } else if (productBatchAttribute.equals("1")){
            System.out.println("Product batch Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");
        //3
        WebElement storageBin = driver.findElementByXPath("//Pane//CheckBox[@Name='Storage bin wise']");
        String storageBinAttribute = storageBin.getAttribute("Toggle.ToggleState");
        System.out.println("Storage bin Toggle state:-" + storageBinAttribute);
        if (storageBinAttribute.equals("0")) {
            storageBin.click();
            System.out.println("Storage bin Checkbox was unchecked, now checked.");
        } else if (storageBinAttribute.equals("1")){
            System.out.println("Storage bin Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");
        //4
        WebElement override = driver.findElementByXPath("//Pane//CheckBox[@Name='Override alerts']");
        String overrideAttribute = override.getAttribute("Toggle.ToggleState");
        System.out.println("Override Toggle state:-" + overrideAttribute);
        if (overrideAttribute.equals("0")) {
            override.click();
            System.out.println("Override Checkbox was unchecked, now checked.");
        } else if (overrideAttribute.equals("1")){
            System.out.println("Override Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");
        //5
        WebElement reorderLevel = driver.findElementByXPath("//Pane//CheckBox[@Name='Enable reorder level alerts.']");
        String reorderLevelAttribute = reorderLevel.getAttribute("Toggle.ToggleState");
        System.out.println("reorder level Toggle state:-" + reorderLevelAttribute);
        if (reorderLevelAttribute.equals("0")) {
            reorderLevel.click();
            System.out.println("reorder level Checkbox was unchecked, now checked.");
        } else if (reorderLevelAttribute.equals("1")){
            System.out.println("reorder level Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");
        //6
        WebElement overrideReorder = driver.findElementByXPath("//Pane//CheckBox[@Name='Override reorder level']");
        String overrideReorderAttribute = overrideReorder.getAttribute("Toggle.ToggleState");
        System.out.println("Override Toggle state:-" + overrideReorderAttribute);
        if (overrideReorderAttribute.equals("0")) {
            overrideReorder.click();
            System.out.println("Override Checkbox was unchecked, now checked.");
        } else if (overrideReorderAttribute.equals("1")){
            System.out.println("Override Checkbox is already checked, no action needed.");
        }
        else Assert.fail("Element Not Found");
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Button[@Name='OK']");
    }
}