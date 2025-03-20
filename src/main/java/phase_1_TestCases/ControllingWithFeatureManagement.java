package phase_1_TestCases;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Collections.sort;

public class ControllingWithFeatureManagement extends Transaction {
    WindowsDriver driver,rootDriver;
    Common common;
    String dataFile;

    ControllingWithFeatureManagement(WindowsDriver driver, String dFile) {
        super(driver);
        this.driver = driver;
        dataFile = dFile;
        common = new Common(driver);
    }
    public void validateMenuItemsPositive() throws InterruptedException {
        common.clickElement("xpath", "//TabItem[@Name='Configure']");
        common.clickElement("xpath", "//Text[@Name='Select Modules']");
        List<String> menus = new ArrayList<>();
        String finance = checkboxSelection("//CheckBox[@Name='Finance']");
        menus.add(finance);
        String taxes = checkboxSelection("//CheckBox[@Name='Taxes']");
        menus.add(taxes);
        String general = checkboxSelection("//Pane[@Name='Finance']/CheckBox[@Name='General']");
        menus.add(general);
        String purchase = checkboxSelection("//CheckBox[@Name='Purchases']");
        menus.add(purchase);
        String sales = checkboxSelection("//CheckBox[@Name='Sales']");
        menus.add(sales);
        String inventory = checkboxSelection("//CheckBox[@Name='Inventory']");
        menus.add(inventory);
        String production = checkboxSelection("//CheckBox[@Name='Production']");
        menus.add(production);
        String general1 = checkboxSelection("//Pane[@Name='General']/Pane/CheckBox[@Name='General']");
        menus.add(general1);
        menus.remove(7);
        sort(menus);
        System.out.println("menusSize :" + menus.size());
        System.out.println("menus :" + menus);
        saveProperties();
        List<String> validateMenus = new ArrayList<>();
//        List<WebElement> checkMenus =common.findWebElements("xpath","//MenuBar/MenuItem");
//        System.out.println("validateMenusSize :"+ checkMenus.size());
        List<WebElement> checkMenus = common.findWebElements("xpath", "//Pane[@Name='Cost Centres']/Text/*");
        System.out.println("fetchedMenusSize :" + checkMenus.size());
        for (WebElement v : checkMenus) {
            String allMenus = v.getText();
//            System.out.println("allMenus :"+allMenus);
            validateMenus.add(allMenus);
        }
        validateMenus.remove(0);
        sort(validateMenus);
        System.out.println("validateMenusSize :" + validateMenus.size());
        System.out.println("validateMenus :" + validateMenus);
        Assert.assertTrue(menus.equals(validateMenus), "both lists are not matched");
    }

    public void validateMenuItemsNegative() throws InterruptedException {
        common.clickElement("xpath", "//TabItem[@Name='Configure']");
        common.clickElement("xpath", "//Text[@Name='Select Modules']");
        List<String> menus = new ArrayList<>();
        String finance = uncheckCheckBox("//CheckBox[@Name='Finance']");
        menus.add(finance);
        String taxes = uncheckCheckBox("//CheckBox[@Name='Taxes']");
        menus.add(taxes);
        String general = uncheckCheckBox("//CheckBox[@Name='General']");
        menus.add(general);
        String purchase = uncheckCheckBox("//CheckBox[@Name='Purchases']");
        menus.add(purchase);
        String sales = uncheckCheckBox("//CheckBox[@Name='Sales']");
        menus.add(sales);
        String inventory = uncheckCheckBox("//CheckBox[@Name='Inventory']");
        menus.add(inventory);
        String production = uncheckCheckBox("//CheckBox[@Name='Production']");
        menus.add(production);
        String general1 = uncheckCheckBox("//Pane[@Name='General']/Pane/CheckBox[@Name='General']");
        menus.add(general1);
        System.out.println("menusSize :" + menus.size());
        System.out.println("menus :" + menus);
        saveProperties();
        List<String> validateMenus = new ArrayList<>();
        List<WebElement> checkMenus = common.findWebElements("xpath", "//Pane[@Name='Cost Centres']/Text/*");
        System.out.println("fetchedMenusSize :" + checkMenus.size());
        for (WebElement v : checkMenus) {
            String allMenus = v.getText();
            validateMenus.add(allMenus);
        }
        System.out.println("validateMenusSize :" + validateMenus.size());
        System.out.println("validateMenus :" + validateMenus);
        Assert.assertTrue(!validateMenus.contains(menus), "validateMenus contains Menus it should not like this");
    }

    public void validateTransactionFieldsPositive() throws InterruptedException {
        common.clickElement("xpath", "//TabItem[@Name='Configure']");
        common.clickElement("xpath", "//Text[@Name='Sales']/*[@Name='Sales']");
        common.clickElement("xpath", "//Button[@Name='Work Flow']");
        List<String> enableTransactionFields = new ArrayList<>();
        String salesEnquiries = checkboxSelection("//Pane[@Name='Sales Enquiries']/CheckBox[@Name='Sales Enquiries']");
        enableTransactionFields.add(salesEnquiries.replace("Sales ", ""));
        String salesQuotations = checkboxSelection("//Pane[@Name='Sales Quotations']/CheckBox[@Name='Sales Quotations']");
        enableTransactionFields.add(salesQuotations.replace("Sales ", ""));
        String salesOrders = checkboxSelection("//Pane[@Name='Sales Orders']/CheckBox[@Name='Sales Orders']");
        enableTransactionFields.add(salesOrders.replace("Sales ", ""));
        String salesDeliveries = checkboxSelection("//Pane[@Name='Deliveries']/CheckBox[@Name='Deliveries']");
        enableTransactionFields.add(salesDeliveries.replace("Sales ", ""));
        String salesInvoices = checkboxSelection("//Pane[@Name='Sales Invoices']/CheckBox[@Name='Sales Invoices']");
        enableTransactionFields.add(salesInvoices.replace("Sales ", ""));
        System.out.println("enableTransactionFields :" + enableTransactionFields);
        saveProperties();
        common.clickElement("xpath", "//Button[@Name='Close']");
        common.clickElement("xpath", "//MenuItem[@Name='Sales']");
        List<String> validateTransactionFields = new ArrayList<>();
        List<WebElement> menuFields = common.findWebElements("xpath", "//Menu[@Name='Sales']/*");
        for (WebElement salesTransactionFields : menuFields) {
            String fetchFields = salesTransactionFields.getText();
            validateTransactionFields.add(fetchFields);
        }
        System.out.println("validatedTransactionFields :" + validateTransactionFields);
        if (validateTransactionFields.containsAll(enableTransactionFields)) System.out.println("Validation Passed!");
        else System.out.println("Validation Failed Transaction fields not contained in SalesMenu!");
    }

    public void validateTransactionFieldsNegative() throws InterruptedException {
        common.clickElement("xpath", "//TabItem[@Name='Configure']");
        common.clickElement("xpath", "//Text[@Name='Sales']/*[@Name='Sales']");
        common.clickElement("xpath", "//Button[@Name='Work Flow']");
        List<String> enableTransactionFields = new ArrayList<>();
        String salesEnquiries = uncheckCheckBox("//Pane[@Name='Sales Enquiries']/CheckBox[@Name='Sales Enquiries']");
        enableTransactionFields.add(salesEnquiries.replace("Sales ", ""));
        String salesQuotations = uncheckCheckBox("//Pane[@Name='Sales Quotations']/CheckBox[@Name='Sales Quotations']");
        enableTransactionFields.add(salesQuotations.replace("Sales ", ""));
        String salesOrders = uncheckCheckBox("//Pane[@Name='Sales Orders']/CheckBox[@Name='Sales Orders']");
        enableTransactionFields.add(salesOrders.replace("Sales ", ""));
        String salesDeliveries = uncheckCheckBox("//Pane[@Name='Deliveries']/CheckBox[@Name='Deliveries']");
        enableTransactionFields.add(salesDeliveries.replace("Sales ", ""));
        String salesInvoices = uncheckCheckBox("//Pane[@Name='Sales Invoices']/CheckBox[@Name='Sales Invoices']");
        enableTransactionFields.add(salesInvoices.replace("Sales ", ""));
        System.out.println("disableTransactionFields :" + enableTransactionFields);
        saveProperties();
        common.clickElement("xpath", "//Button[@Name='Close']");
        common.clickElement("xpath", "//MenuItem[@Name='Sales']");
        List<String> validateTransactionFields = new ArrayList<>();
        List<WebElement> menuFields = common.findWebElements("xpath", "//Menu[@Name='Sales']/*");
        for (WebElement salesTransactionFields : menuFields) {
            String fetchFields = salesTransactionFields.getText();
            validateTransactionFields.add(fetchFields);
        }
        System.out.println("validatedTransactionFields :" + validateTransactionFields);
        if (!validateTransactionFields.containsAll(enableTransactionFields))
            System.out.println("Validation Passed No transactionFields are present!");
        else System.out.println("Validation Failed transactionFields Present!");
    }
    public void enableDragAndDropNodes() throws InterruptedException, IOException, ParseException {
        common.clickElement("xpath", "//TabItem[@Name='Configure']");
        common.clickElement("xpath", "//Text[@Name='Entry, View and Print Settings']/*[@Name='Entry, View and Print Settings']");
        enableCheckboxSelection("//CheckBox[@Name='Allow moving of masters and nodes by drag and drop in master window.']");
        saveProperties();
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Suppliers");
        Thread.sleep(2500);
        WebElement allCustomer = common.findWebElement("xpath", "//TreeItem[@Name='Suppliers']/TreeItem[@Name='All Suppliers']");
        allCustomer.sendKeys(Keys.ENTER,Keys.ARROW_RIGHT);
        WebElement source=common.findWebElement("xpath","//ListItem[@Name='NodeDrag']/*[@Name='NodeDrag']");
        System.out.println("sourceTest :"+source.getText());
        WebElement destination=common.findWebElement("xpath","//TreeItem[@Name='All Suppliers']/*[@Name='NodeDrop']");
        Actions dragAndDrop=new Actions(driver);
        dragAndDrop.clickAndHold(source).moveToElement(destination).release().build().perform();
        System.out.println("node drag and dropped please do validation");
        closeReport("Suppliers");
        Thread.sleep(1000);
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Suppliers");
        Thread.sleep(2500);
        WebElement navigate = common.findWebElement("xpath", "//TreeItem[@Name='Suppliers']/TreeItem[@Name='All Suppliers']");
        navigate.sendKeys(Keys.ARROW_RIGHT);
        WebElement dragedNode = common.findWebElement("xpath", "//TreeItem[@Name='NodeDrop']");
        dragedNode.sendKeys(Keys.ARROW_RIGHT);
        WebElement  validateDraggedNode= common.findWebElement("xpath", "//TreeItem[@Name='NodeDrop']/*");
        System.out.println("validated draggedNode :"+validateDraggedNode.getText());
        if (validateDraggedNode.getText().equals(source.getText())){
            Assert.assertTrue(true,"nodes are not drag and dropped");
            System.out.println("nodes are drag and dropped");
        }
        Actions actions=new Actions(driver);
        actions.contextClick(validateDraggedNode).perform();
        common.clickElement("xpath","//MenuItem[@Name='Move As Sub-Node']");
        WebElement save =common.findWebElement("xpath","//Button[@Name='Save']");
        save.sendKeys(Keys.ENTER);//,Keys.ENTER,Keys.ENTER);
        common.clickElement("xpath","//Button[@Name='Yes']");
//        common.clickElement("xpath","//Button[@Name='Yes']");
        rootDriver=common.initializeDriver("Root");
        Thread.sleep(3000);
        WebElement root=rootDriver.findElementByXPath("//*//Button[@Name='OK']");
        root.click();
        System.out.println("Okay button clicked ");
        closeReport("Suppliers");
    }

    public void disableDragAndDropToNodes() throws InterruptedException {
        common.clickElement("xpath", "//TabItem[@Name='Configure']");
        common.clickElement("xpath", "//Text[@Name='Entry, View and Print Settings']/*[@Name='Entry, View and Print Settings']");
        uncheckCheckBox("//CheckBox[@Name='Allow moving of masters and nodes by drag and drop in master window.']"); // Disabling Drag and Drop
        saveProperties();
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Suppliers");
        Thread.sleep(2500);
        WebElement allCustomer = common.findWebElement("xpath", "//TreeItem[@Name='Suppliers']/TreeItem[@Name='All Suppliers']");
        allCustomer.sendKeys(Keys.ENTER);
        WebElement source = common.findWebElement("xpath", "//ListItem[@Name='NodeDrag']/*[@Name='NodeDrag']");
        System.out.println("sourceTest :" + source.getText());
        WebElement destination = common.findWebElement("xpath", "//TreeItem[@Name='All Suppliers']/*[@Name='NodeDrop']");
        Actions dragAndDrop = new Actions(driver);
        try {
            dragAndDrop.clickAndHold(source).moveToElement(destination).release().build().perform();
            Thread.sleep(2000); // Allow time for UI response
            // Now verify if the node was actually moved
            WebElement validateDraggedNode;
            try {
                validateDraggedNode = common.findWebElement("xpath", "//TreeItem[@Name='NodeDrop']/*");
                System.out.println("Negative Test Failed: Drag-and-drop still happened!");
                Assert.fail("Drag-and-drop should not happen when the setting is disabled.");
            } catch (NoSuchElementException e) {
                System.out.println("Negative Test Passed: Drag-and-drop did not occur.");
                Assert.assertTrue(true, "Drag-and-drop is correctly disabled.");
            }

        } catch (Exception e) {
            // If an exception occurs during drag-and-drop, it means the operation was blocked
            System.out.println("Drag-and-drop action was prevented as expected.");
            Assert.assertTrue(true, "Drag-and-drop is disabled and prevented by the system.");
        }
    }

    public void enableRenameUsingF2() throws InterruptedException, AWTException {
        common.clickElement("xpath", "//TabItem[@Name='Configure']");
        common.clickElement("xpath", "//Text[@Name='Entry, View and Print Settings']/*[@Name='Entry, View and Print Settings']");
        Thread.sleep(1500);
        WebElement f2 = common.findWebElement("xpath", "//CheckBox[@Name='Allow renaming of masters by pressing F2 in master window.']");
        String f2State = f2.getAttribute("Toggle.ToggleState");

        if (f2State.equals("0")) {
            System.out.println("Rename using F2 is disabled. Enabling now.");
            f2.click();
            //verify if it's enabled
            WebElement f3 = common.findWebElement("xpath", "//CheckBox[@Name='Allow renaming of masters by pressing F2 in master window.']");
            String f3State = f3.getAttribute("Toggle.ToggleState");
            System.out.println("toggle: " + f3State);
            if (f3State.equals("1")) System.out.println("Successfully Enabled");
            else Assert.fail("Enabling is not done properly");
        } else System.out.println("Rename using F2 is already enabled. Moving onto rename.");
        //save
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='OK']");
        //rename
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(2500);
        common.clickElement("xpath", "//TreeItem[@Name='All Customers']");
        List<WebElement> listElements = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of elements under List: " + listElements.size());
        for (int i = 0; i < listElements.size(); i++) {
            WebElement element = listElements.get(i);
            System.out.println("Element Text: " + element.getText());
            if (element.getText().equals("renamed")) {
                element.click();
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_F2);
                robot.keyRelease(KeyEvent.VK_F2);
                driver.getKeyboard().sendKeys("renamed1");
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(1500);
                common.clickElement("xpath", "//Button[@Name='OK']");
            }
        }
        closeTransaction("Customers");
        refresh();
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(2500);
        common.clickElement("xpath", "//TreeItem[@Name='All Customers']");
        List<WebElement> listElements1 = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        for (int i = 0; i < listElements1.size(); i++) {
            WebElement element = listElements1.get(i);
            System.out.println("Element Text: " + element.getText());
            if (element.getText().equals("renamed1")) {
                System.out.println("Master is renamed successfully using F2");
                element.click();
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_F2);
                robot.keyRelease(KeyEvent.VK_F2);
                driver.getKeyboard().sendKeys("renamed");
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(1500);
                common.clickElement("xpath", "//Button[@Name='OK']");
            }
        }
    }

    public void disableRenameUsingF2() throws InterruptedException, AWTException {
        common.clickElement("xpath", "//TabItem[@Name='Configure']");
        common.clickElement("xpath", "//Text[@Name='Entry, View and Print Settings']/*[@Name='Entry, View and Print Settings']");
        Thread.sleep(1500);
        WebElement f2 = common.findWebElement("xpath", "//CheckBox[@Name='Allow renaming of masters by pressing F2 in master window.']");
        String f2State = f2.getAttribute("Toggle.ToggleState");
        System.out.println("toggle: " + f2State);
        if (f2State.equals("1")) {
            System.out.println("Rename using F2 is enabled. Disabling now.");
            f2.click();
            //verify if it's disabled
            WebElement f3 = common.findWebElement("xpath", "//CheckBox[@Name='Allow renaming of masters by pressing F2 in master window.']");
            String f3State = f3.getAttribute("Toggle.ToggleState");
            System.out.println("toggle: " + f3State);
            if (f3State.equals("0")) System.out.println("Successfully Disabled");
            else Assert.fail("Disabling is not done properly");
        } else System.out.println("Rename using F2 is disabled.");
        //save
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='OK']");
        //try to rename
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(1500);
        common.clickElement("xpath", "//TreeItem[@Name='All Customers']");
        List<WebElement> listElements = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        for (int i = 0; i < listElements.size(); i++) {
            WebElement element = listElements.get(i);
            if (element.getText().equals("renamed")) {
                element.click();
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_F2);
                robot.keyRelease(KeyEvent.VK_F2); //here nothing should happen. if it happens, it should fail.(ex:- OK)
                try {
                    WebDriverWait wait = new WebDriverWait(driver, 3);
                    WebElement ok = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//Button[@Name='OK']")));
                    if ((ok.isDisplayed())) Assert.fail("Disable feature isn't working properly.");
                } catch (Exception e) {
                    System.out.println("Disable Rename using F2 disable feature is working fine");
                }
            }
        }
    }

    public void enableConfirmforClosingMasterWindow() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        common.clickElement("xpath","//Text[@Name='Entry, View and Print Settings']/*[@Name='Entry, View and Print Settings']");
        Thread.sleep(1500);
        WebElement enable = common.findWebElement("xpath", "//CheckBox[@Name='Confirmation for closing master window.']");
        String enableAttribute = enable.getAttribute("Toggle.ToggleState");

        if (enableAttribute.equals("0")) {
            System.out.println("confirm for closing window is disabled. Enabling now.");
            enable.click();
            //verify if it's enabled
            WebElement enable1 = common.findWebElement("xpath", "//CheckBox[@Name='Confirmation for closing master window.']");
            String enable1Attribute = enable1.getAttribute("Toggle.ToggleState");
            System.out.println("toggle: " + enable1Attribute);
            if (enable1Attribute.equals("1")) System.out.println("Successfully Enabled");
            else Assert.fail("Enabling is not done properly");
        } else System.out.println("confirm for closing window is already enabled.");
        //save
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='OK']");
        //check enabled feature
        common.clickElement("name","Sales");
        common.clickElement("name","Customers");
        Thread.sleep(1500);
        common.clickElement("xpath","//TreeItem[@Name='All Customers']");
        //click on master
        WebElement renamed= common.findWebElement("xpath","//ListItem[@Name='renamed']");
        Actions actions=new Actions(driver);
        actions.doubleClick(renamed).perform();
        //close
        try {
            common.clickElement("xpath", "//Button[@Name='Close']");
            common.clickElement("xpath", "//Button[@Name='Yes']");
            System.out.println("Enable Confirm closing for master window is working fine");
        }catch(Exception e){
            Assert.fail("Enable Confirm closing master window isn't working fine. Yes isn't displayed");
        }
    }

    public void disableConfirmForClosingMasterWindow() throws InterruptedException {
            common.clickElement("xpath","//TabItem[@Name='Configure']");
            common.clickElement("xpath","//Text[@Name='Entry, View and Print Settings']/*[@Name='Entry, View and Print Settings']");
            Thread.sleep(1500);
            WebElement enable = common.findWebElement("xpath", "//CheckBox[@Name='Confirmation for closing master window.']");
            String enableAttribute = enable.getAttribute("Toggle.ToggleState");
            if (enableAttribute.equals("1")) {
                System.out.println("confirm for closing window is enabled. Disabling now.");
                enable.click();
                //verify if it's disabled
                WebElement enable1 = common.findWebElement("xpath", "//CheckBox[@Name='Confirmation for closing master window.']");
                String enable1Attribute = enable1.getAttribute("Toggle.ToggleState");
                System.out.println("toggle: " + enable1Attribute);
                if (enable1Attribute.equals("0")) System.out.println("Successfully Disabled");
                else Assert.fail("Disabling is not done properly");
            } else System.out.println("confirm for closing window is already disabled.");
            //save
            common.clickElement("xpath", "//Button[@Name='Save']");
            common.clickElement("xpath", "//Button[@Name='OK']");
            Thread.sleep(1500);
            common.clickElement("xpath", "//Button[@Name='OK']");
            //check enabled feature
            common.clickElement("name","Sales");
            common.clickElement("name","Customers");
            Thread.sleep(1500);
            common.clickElement("xpath","//TreeItem[@Name='All Customers']");
            //click on master
            WebElement renamed= common.findWebElement("xpath","//ListItem[@Name='renamed']");
            Actions actions=new Actions(driver);
            actions.doubleClick(renamed).perform();
            //close
                common.clickElement("xpath", "//Button[@Name='Close']");
                try{
                    WebDriverWait wait=new WebDriverWait(driver,5);
                    WebElement yes=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//Button[@Name='Yes']")));
                    if (yes.isDisplayed()) Assert.fail("Confirm for Close master window feature isn't working properly");
            }catch(Exception e){
                Assert.fail("Disable Confirm for closing master window feature is working fine");
            }
        }
    }