package com.wings.pages;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class MasterConfig extends Transaction{
    WindowsDriver driver;
    Common common;
    String dataFile;

    public MasterConfig(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String newCustomer() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(3000);
        WebElement allCustomer = common.findWebElement("xpath", "//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
        Actions actions = new Actions(driver);
        actions.contextClick(allCustomer).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(3500);
        common.inputText("xpath", "//Edit[@Name='New Account *']", common.getData(dataFile, "newAccount"));
        WebElement element = common.findWebElement("xpath", "//Edit[@Name='New Account *']");
        common.inputText("xpath", "//Edit[@Name='Account Code']", "" + common.getRandom());
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        common.clickElement("xpath", "//Pane[@Name='Bank Details']/Button[@Name='...']");
        Thread.sleep(1200);
        common.inputAndVerify("xpath", "//Window[@Name='Bank Details']/Pane/Pane/Edit[@Name='Account No']", common.getData(dataFile, "AccNo"));
        common.inputAndVerify("xpath", "//Window[@Name='Bank Details']/Pane/Pane/Edit[@Name='Bank']", common.getData(dataFile, "Bank"));
        common.inputAndVerify("xpath", "//Window[@Name='Bank Details']/Pane/Pane/Edit[@Name='Bank Branch']", common.getData(dataFile, "BankBranch"));
        common.inputAndVerify("xpath", "//Window[@Name='Bank Details']/Pane/Pane/Edit[@Name='IFSC Code']", common.getData(dataFile, "IFSC"));
        common.clickElement("name", "Ok");
        //DD's
        common.clickElement("xpath", "//Edit[@Name='Executive']/Button[@Name='Open']");
        common.rowDropDown(common.getData(dataFile, "Executive"));
        Thread.sleep(500);
        common.clickElement("xpath", "//Edit[@Name='Route']/Button[@Name='Open']");
        common.rowDropDown(common.getData(dataFile, "Route"));
        Thread.sleep(500);
        common.clickElement("xpath", "//Edit[@Name='Purchase Price List']/Button[@Name='Open']");
        common.rowDropDown(common.getData(dataFile, "priceList"));
        Thread.sleep(500);
        common.clickElement("xpath", "//Edit[@Name='Transport']/Button[@Name='Open']");
        common.rowDropDown(common.getData(dataFile, "transport"));
        Thread.sleep(500);
        common.clickElement("xpath", "//Edit[@Name='Transporter']/Button[@Name='Open']");
        common.rowDropDown(common.getData(dataFile, "transporter"));
        Thread.sleep(500);
        common.clickElement("xpath", "//Edit[@Name='Type Of Customer']/Button[@Name='Open']");
        common.rowDropDown("Type of Customer");
        common.sliderHandling("xpath", "//*/Thumb[@Name='Position']", 0, 190);
        common.clickElement("xpath", "//Pane[@Name='Registration']/Button[@Name='...']");
        Thread.sleep(500);
        common.inputText("xpath", "//Window[@Name='Registration']/Pane/Pane/Edit[@Name='Party Reg Type *']", "registered");
        Thread.sleep(500);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.inputText("xpath", "//Edit[@Name='GSTIN']", common.getData(dataFile, "gst"));
        common.clickElement("xpath", "//Edit[@Name='PAN']");
        common.clickElement("xpath", "//Button[@Name='Verify GSTIN']");
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
        common.clickElement("name", "Ok");
        Thread.sleep(3000);
        //scroll down
        common.sliderHandling("name", "Position", 0, 450);
        common.clickElement("xpath", "//Pane/Pane[@Name='Contact Details']/Button[@Name='...']");
        Thread.sleep(2000);
        common.inputAndVerify("xpath", "//Edit[@Name='Telephones 1']", common.getData(dataFile, "tel1"));
        common.inputAndVerify("xpath", "//Edit[@Name='Fax']", common.getData(dataFile, "fax"));
        common.inputAndVerify("xpath", "//Edit[@Name='Email']", common.getData(dataFile, "email"));
        common.inputAndVerify("xpath", "//Edit[@Name='Website']", common.getData(dataFile, "website"));
        common.inputAndVerify("xpath", "//Edit[@Name='Contact Person']", common.getData(dataFile, "cp"));
        common.inputAndVerify("xpath", "//Edit[@Name='Contact Person Designation']", common.getData(dataFile, "cpd"));
        common.inputAndVerify("xpath", "//Edit[@Name='Contact Person Telephone No']", common.getData(dataFile, "cptn"));
        common.inputAndVerify("xpath", "//Edit[@Name='Contact Person Mobile No']", common.getData(dataFile, "cpmn"));
        common.inputAndVerify("xpath", "//Edit[@Name='Contact Person Email']", common.getData(dataFile, "cpe"));
        common.clickElement("name", "Ok");
        common.clickElement("xpath", "//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Cancel']");

        common.clickElement("xpath", "//TabItem[@Name='Customers']/Button[@Name='Close']");

        //refresh
        common.clickElement("xpath", "//MenuItem[@Name='Tools']");
        common.clickElement("xpath", "//MenuItem[@Name='Clear Cache']");

        return common.getData(dataFile, "newAccount");
    }
    public void customerRename(String customerName) throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(2500);
        common.clickElement("xpath", "//TreeItem[@Name='All Customers']");
        // First loop: Check for the customer and perform actions
        List<WebElement> listElements = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of elements under List: " + listElements.size());
        boolean customerFound = false; // Flag to check if the customer was found
        for (int i = 0; i < listElements.size(); i++) {
            WebElement element = listElements.get(i);
            System.out.println("Element Text: " + element.getText());
            if (element.getText().equals(customerName)) {
                System.out.println("Master is created successfully");
                // Perform actions on the element
                Actions actions = new Actions(driver);
                actions.contextClick(element).perform();
                common.clickElement("xpath", "//MenuItem[@Name='Change']");
                common.clickElement("xpath", "//MenuItem[@Name='Name']");
                common.inputText("xpath", "//Edit[@Name='New Name']", common.getData(dataFile, "newName"));
                common.clickElement("xpath", "//Button[@Name='OK']");
                common.clickElement("xpath", "//Button[@Name='OK']");
                customerFound = true; // Set flag to true when the customer is found
                break;  // Exit the loop once the desired element is found and processed
            }
        }
        // Fail the test if no matching customer was found
        if (!customerFound) {
            Assert.fail("Created Master is not present");
        }
        // Close the current customer view and navigate to the customers section again
        common.clickElement("xpath", "//TabItem[@Name='Customers']/Button[@Name='Close']");
        Thread.sleep(1500);
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(2500);
        common.clickElement("xpath", "//TreeItem[@Name='All Customers']");
        // Second loop: Verify the renamed master
        List<WebElement> listElements1 = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of elements after rename: " + listElements1.size());
        boolean renamedCustomerFound = false; // Flag to check if the renamed customer was found

        for (int i = 0; i < listElements1.size(); i++) {
            WebElement element1 = listElements1.get(i);
            System.out.println("Element Text: " + element1.getText());
            // Check if the new name matches
            if (element1.getText().equals(common.getData(dataFile, "newName"))) {
                System.out.println("Master Renamed Successfully");
                renamedCustomerFound = true; // Set flag to true when the renamed customer is found
                break;  // Exit the loop once the renamed customer is found
            }
        }
        // Fail the test if no renamed customer was found
        if (!renamedCustomerFound) {
            Assert.fail("Renamed Master is not present after the update");
        }
    }
    public void masterInactive(String masterName) throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(2500);
        common.clickElement("xpath", "//TreeItem[@Name='All Customers']");
        WebElement element = common.findWebElement("xpath", "//Text[@Name='" + masterName + "']");
        String master = element.getText();
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        //click on active
        common.clickElement("xpath", "//MenuItem[@Name='Inactivate']");
        common.clickElement("name", "OK");
        //search in inactive
     WebElement clickDown=   common.findWebElement("xpath", "//Button[@Name='  Options  ']");
     clickDown.click();
     clickDown.sendKeys(Keys.DOWN,Keys.ENTER);
        Thread.sleep(5000);     //for now click manually
//        common.clickElement("xpath","//Pane[@Name='Desktop 1']/*/Menu/*[@Name='Show Inactive']");
        List<WebElement> listElements = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of elements under List: " + listElements.size());
        boolean customerFound = false;
        for (int i = 0; i < listElements.size(); i++) {
            WebElement element1 = listElements.get(i);
            System.out.println("Element Text: " + element1.getText());
            if (element1.getText().equals(master)) {
                element1.click();
                System.out.println("Master is inactivated succesfully");
                customerFound = true;
                break;
            }
        }
        for (int i = 0; i < listElements.size(); i++) {
            WebElement element1 = listElements.get(i);
            if (element1.getText().equals(master)) {
                Actions actions1 = new Actions(driver);
                actions1.contextClick(element1).perform();
                common.clickElement("xpath", "//MenuItem[@Name='Active']");
            }
        }
        if (!customerFound) {
            Assert.fail("Inactivated Master is not present");
        }
        common.clickElement("xpath", "//TabItem[@Name='Customers']/Button[@Name='Close']");
        //validate activate master
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(2500);
        common.clickElement("xpath", "//TreeItem[@Name='All Customers']");
        List<WebElement> activeElements = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of active elements: " + activeElements.size());
        boolean customerActivate = false;
        for (int i = 0; i < activeElements.size(); i++) {
            WebElement activeElement = activeElements.get(i);
            System.out.println("Active Element Text: " + activeElement.getText());
            if (activeElement.getText().equals(master)) {
                activeElement.click();
                System.out.println("Master is activated succesfully");
                customerActivate = true;
                break;
            }
        }
        if (!customerActivate) {
            Assert.fail("Activated Master is not present");
        }
//        common.clickElement("name", "Sales");
//        common.clickElement("name", "Customers");
//        Thread.sleep(2500);
//        common.clickElement("xpath", "//TreeItem[@Name='All Customers']");
//        WebElement element = common.findWebElement("xpath", "//Text[@Name='" + masterName + "']");
//        String master = element.getText();
//        Actions actions = new Actions(driver);
//        actions.contextClick(element).perform();
//        //click on active
//        common.clickElement("xpath", "//MenuItem[@Name='Inactivate']");
//        common.clickElement("name", "OK");
//        //search in inactive
//        common.clickElement("xpath", "//Button[@Name='  Options  ']");
//        Thread.sleep(5000);
//
//
//        DesiredCapabilities rootcapabilities = new DesiredCapabilities();
//        rootcapabilities.setCapability("app", "Root");
//        rootcapabilities.setCapability("deviceName", "WindowsPC");
//        rootdriver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), rootcapabilities);
//        Thread.sleep(10000);
//        WebElement login = rootdriver.findElement(By.name("Wings Accounting 24DNP - PRO [ WingsAutomtionTesting1 ; 01-04-2024 To 31-03-2025 ; Super User ]"));
//        //the windowHandle which we receive is integer, so we need to convert it into hexadecimal string
//        String nativeWindow = login.getAttribute("NativeWindowHandle");
//        String hexloginid = Integer.toHexString(Integer.parseInt(nativeWindow));
//        System.out.println("window id: " + hexloginid);
//        rootdriver.findElementByXPath("//CheckBox[@Name='Show Inactive']").click();
//
//        DesiredCapabilities webClientCapabilities = new DesiredCapabilities();
//        webClientCapabilities.setCapability("ms:waitforAppLaunch", 15); //wait for 15seconds( mention time in secs)
//        //appTopLevelWindow- it helps us to take control of specific window which we want to handle
//        webClientCapabilities.setCapability("appTopLevelWindow", hexloginid);
//        driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), webClientCapabilities);
//        List<WebElement> listElements = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
//        System.out.println("Size of elements under List: " + listElements.size());
//        boolean customerFound = false;
//        for (int i = 0; i < listElements.size(); i++) {
//            WebElement element1 = listElements.get(i);
//            System.out.println("Element Text: " + element1.getText());
//            if (element1.getText().equals(master)) {
//                element1.click();
//                System.out.println("Master is inactivated succesfully");
//                customerFound = true;
//                break;
//            }
//        }
//        for (int i = 0; i < listElements.size(); i++) {
//            WebElement element1 = listElements.get(i);
//            if (element1.getText().equals(master)) {
//                Actions actions1 = new Actions(driver);
//                actions1.contextClick(element1).perform();
//                driver.findElementByXPath("//MenuItem[@Name='Active']");
//            }
//        }
//        if (!customerFound) {
//            Assert.fail("Inactivated Master is not present");
//        }
//        common.clickElement("xpath", "//TabItem[@Name='Customers']/Button[@Name='Close']");
//        //validate activate master
//        common.clickElement("name", "Sales");
//        common.clickElement("name", "Customers");
//        Thread.sleep(2500);
//        common.clickElement("xpath", "//TreeItem[@Name='All Customers']");
//        List<WebElement> activeElements = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
//        System.out.println("Size of active elements: " + activeElements.size());
//        boolean customerActivate = false;
//        for (int i = 0; i < activeElements.size(); i++) {
//            WebElement activeElement = activeElements.get(i);
//            System.out.println("Active Element Text: " + activeElement.getText());
//            if (activeElement.getText().equals(master)){
//                activeElement.click();
//                System.out.println("Master is activated succesfully");
//                customerActivate = true;
//                break;
//            }
//        }
//        if (!customerActivate) {
//            Assert.fail("Activated Master is not present");
//        }
    }
    public void searchMaster(String searchType) throws IOException, ParseException {
        common.clickElement("xpath", "//MenuItem[@Name='Tools']");
        common.clickElement("xpath", "//MenuItem[@Name='Search Masters']");
        if (searchType.equals("name")) {
            common.clickElement("xpath", "//RadioButton[@Name='Master Name']");
            common.inputText("xpath", "//Edit[@AutomationId='searchTextBox']", common.getData(dataFile, "masterName"));
            common.clickElement("xpath", "//Button[@Name='Search']");
            String master = common.findWebElement("xpath", "//DataItem[@Name='Master Name row 1']").getText();
            System.out.println("Searched master:- " + common.getData(dataFile, "masterName"));
            Assert.assertEquals(common.getData(dataFile, "masterName"), master, "Master you searched for is not Found");
            System.out.println("Found master:- " + master);
            System.out.println("Master is successfully found");
        } else if (searchType.equals("code")) {
            common.clickElement("xpath", "//RadioButton[@Name='Master Code']");
            common.inputText("xpath", "//Edit[@AutomationId='searchTextBox']", common.getData(dataFile, "masterCode"));
            common.clickElement("xpath", "//Button[@Name='Search']");
            String code = common.findWebElement("xpath", "//DataItem[@Name='Master Code row 1']").getText();
            System.out.println("Searched master:- " + common.getData(dataFile, "masterCode"));
            Assert.assertEquals(common.getData(dataFile, "masterCode"), code, "Master you searched for is not Found");
            System.out.println("Found master:- " + code);
            System.out.println("Master is successfully found");
        } else {
            Assert.fail("Please enter correct input");
        }
    }



    public void SalesModuleConfiguration() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        common.clickElement("xpath","//Text[@Name='Sales']/*[@Name='Sales']");
        common.clickElement("xpath","//Button[@Name='Work Flow']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Sales Enquiries']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Sales Quotations']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Sales Orders']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Deliveries']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Sales Invoices']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Proforma Sales']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Sales Returns']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable with invoice reference']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable without invoice reference']");
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        common.clickElement("xpath","//Button[@Name='OK']");
        System.out.println(" SALES WORKFLOW CHECKBOX SELECTION DONE  :-"+new String(Character.toChars(0x1F600)));
        common.clickElement("xpath","//Button[@Name='Module Settings']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable product wise discounts in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Discount 1']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Discount 2']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Discount 3']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Voucher Discount']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Party and Produt wise Discount']");
        Thread.sleep(500);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable product wise accounts in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Sales Account']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Sales Return Account']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable product wise columns.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Product History']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Number of Packs']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Delivery Date']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable cost of goods sold in sales return transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable auto fill quantity in sales invoices against deliveries transactions.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/*[@Name='Position']",0,72);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable collections in sales transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Cash']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Cheque']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Post Dated Cheques']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Cheques [PDC]']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Credit Card']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Batches']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable batches and serial number pop up in sales transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable stock details in sales transactions UOM.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable landed cost in batch pop up.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable batch text in batch pop up.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable auto fill quantity in material issues to production.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable product batches based on expiry date.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable Routes.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable lock customer.']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/*[@Name='Position']",0,71);
        Thread.sleep(1500);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable sales targets-executive wise.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable material dispatch address details in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable line wise additional info in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Info 1']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Info 2']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Info 3']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Info 4']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Info 5']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable line wise additional values in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Value 1']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Value 2']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Value 3']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Value 4']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Value 5']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable line wise additional dates in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Date 1']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/*[@Name='Position']",0,73);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Date 2']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Date 3']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable line wise additional bools in all transactions.']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Bool 1']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Bool 2']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Bool 3']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable header additional info in all transactions.']");
        Thread.sleep(1000);
//        configureCheckboxSelection("//Pane//CheckBox[@Name='Info 1']");
//        configureCheckboxSelection("//Pane//CheckBox[@Name='Info 2']");
//        configureCheckboxSelection("//Pane//CheckBox[@Name='Info 3']");
//        configureCheckboxSelection("//Pane//CheckBox[@Name='Info 4']");
//        configureCheckboxSelection("//Pane//CheckBox[@Name='Info 5']");
        common.clickElement("xpath","//Pane/*/*[@Name='Info 1']");
        common.clickElement("xpath","//Pane/*/*[@Name='Info 2']");
        common.clickElement("xpath","//Pane/*/*[@Name='Info 3']");
        common.clickElement("xpath","//Pane/*/*[@Name='Info 4']");
        common.clickElement("xpath","//Pane/*/*[@Name='Info 5']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable header additional values in all transactions.']");
//        configureCheckboxSelection("//Pane//CheckBox[@Name='Value 1']");
//        configureCheckboxSelection("//Pane//CheckBox[@Name='Value 2']");
//        configureCheckboxSelection("//Pane//CheckBox[@Name='Value 3']");
//        configureCheckboxSelection("//Pane//CheckBox[@Name='Value 4']");
//        configureCheckboxSelection("//Pane//CheckBox[@Name='Value 5']");
        common.clickElement("xpath","//Pane/*/*[@Name='Value 1']");
        common.clickElement("xpath","//Pane/*/*[@Name='Value 2']");
        common.clickElement("xpath","//Pane/*/*[@Name='Value 3']");
        common.clickElement("xpath","//Pane/*/*[@Name='Value 4']");
        common.clickElement("xpath","//Pane/*/*[@Name='Value 5']");
        common.sliderHandling("xpath","//ScrollBar[@Name='Vertical']/*[@Name='Position']",0,70);
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable header additional dates in all transactions.']");
//        configureCheckboxSelection("//Pane//CheckBox[@Name='Date 1']");
//        configureCheckboxSelection("//Pane//CheckBox[@Name='Date 2']");
//        configureCheckboxSelection("//Pane//CheckBox[@Name='Date 3']");
        common.clickElement("xpath","//Pane/*/*[@Name='Date 1']");
        common.clickElement("xpath","//Pane/*/*[@Name='Date 2']");
        common.clickElement("xpath","//Pane/*/*[@Name='Date 3']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Enable header additional bools in all transactions.']");
//        configureCheckboxSelection("//Pane//CheckBox[@Name='Bool 1']");
//        configureCheckboxSelection("//Pane//CheckBox[@Name='Bool 2']");
//        configureCheckboxSelection("//Pane//CheckBox[@Name='Bool 3']");
        common.clickElement("xpath","//Pane/*/*[@Name='Bool 1']");
        common.clickElement("xpath","//Pane/*/*[@Name='Bool 2']");
        common.clickElement("xpath","//Pane/*/*[@Name='Bool 3']");
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(500);
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        System.out.println("SALES MODULE FLOW CHECKBOX SELECTION DONE  :-"+new String(Character.toChars(0x1F601)));
        common.clickElement("xpath","//Button[@Name='Policies']");
        configureCheckboxSelection("//Pane//CheckBox[@Name='Pricing']");
        common.clickElement("xpath","//Pane/RadioButton[@Name='Enable standard sales price in all transactions.']");
//        common.clickElement("xpath","//Pane/RadioButton[@Name='Enable sales price list in all transactions.']");
        configureCheckboxSelection("//Pane/CheckBox[@Name='Enable party default sales price list in all transactions.']");
        configureCheckboxSelection("//Pane/CheckBox[@Name='Enable existing sales price in all transactions.']");
        configureCheckboxSelection("//Pane/CheckBox[@Name='Enable last sales price']");
        configureCheckboxSelection("//Pane/CheckBox[@Name='Sales rate not less than last puchase rate in all sales transactions.']");
        configureCheckboxSelection("//Pane/CheckBox[@Name='Enable minimum rate and maximum rate alerts']");
        configureCheckboxSelection("//Pane/CheckBox[@Name='Override minimum rate and maximum rate alerts']");
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='OK']");
        Thread.sleep(500);
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        common.clickElement("xpath","//TitleBar/Button[@Name='Close']");
        System.out.println("SALES MODULE CONFIGURATION COMPLETED  :-"+new String(Character.toChars(0x1F60D)));
        System.out.println(new String(Character.toChars(0x1F981)));

    }









    public void createNode() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(2500);
        WebElement allCustomer = common.findWebElement("xpath", "//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
        Actions actions = new Actions(driver);
        actions.contextClick(allCustomer).perform();
        common.clickElement("name", "New Node");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane[@Name='GeneralInformation']/*[@Name='New Node *']/Edit[@Name='New Node *']");
        common.inputText("xpath","//Pane[@Name='GeneralInformation']/*[@Name='New Node *']/Edit[@Name='New Node *']",common.getData(dataFile,"node"));
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        WebElement enter=common.findWebElement("xpath", "//Button[@Name='Yes']");
        enter.sendKeys(Keys.ENTER,Keys.ENTER,Keys.ESCAPE);
        common.clickElement("xpath","//TabItem[@Name='Customers']/Button[@Name='Close']");
        Thread.sleep(1000);
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        common.clickElement("xpath", "//TreeItem[@Name='Customers']");
        common.clickElement("xpath", "//TreeItem[@Name='All Customers']");
        List<WebElement> listElements = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of elements and List: " + listElements.size());
        for (int i = 0; i < listElements.size(); i++) {
            WebElement element1= listElements.get(i);
            System.out.println("Element Text: " + element1.getText());
            String newnode=element1.getText();
            // Check if the new name matches
            if (element1.getText().equals(newnode)) {
                System.out.println("Node Created Successfully  :"+common.getData(dataFile,"node"));
                common.clickElement("xpath","//TabItem[@Name='Customers']/Button[@Name='Close']");
                break;
            }
        }
    }
    public void renameNode() throws IOException, ParseException, InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(1500);
        common.clickElement("xpath", "//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
        List<WebElement> listElements1 = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of elements and List: " + listElements1.size());
        for (int i = 0; i < listElements1.size(); i++) {
            WebElement element2 = listElements1.get(i);
            System.out.println("Element Text: " + element2.getText());
            // Check if the new name matches
            if (element2.getText().equals(common.getData(dataFile, "node"))) {
                System.out.println("offToRenamingNode :"+common.getData(dataFile,"node"));
                element2.click();
                Actions actions1 = new Actions(driver);
                actions1.contextClick(element2).perform();
                common.clickElement("xpath","//MenuItem[@Name='Rename']");
                common.clickElement("xpath","//Edit[@Name='New Name']");
                common.inputText("xpath","//Edit[@Name='New Name']", common.getData(dataFile,"rename"));
                common.clickElement("xpath","//Button[@Name='OK']");
                common.clickElement("xpath","//Window/Button[@Name='OK']");
                System.out.println("Node is Renamed");
                common.clickElement("xpath","//TabItem[@Name='Customers']/Button[@Name='Close']");
                break;
            }
        }
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        common.clickElement("xpath", "//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
        Thread.sleep(1500);
        List<WebElement> listElements2 = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of elements and List: " + listElements2.size());
        for (int i = 0; i < listElements2.size(); i++) {
            WebElement element3 = listElements2.get(i);
            System.out.println("Element Text: " + element3.getText());
            String renameNode=element3.getText();
            // Check if the new name matches
            if (element3.getText().equalsIgnoreCase(renameNode)){
                System.out.println("Renamed Node Successfully : "+common.getData(dataFile,"renamedNode"));
                common.clickElement("xpath","//TabItem[@Name='Customers']/Button[@Name='Close']");
                break;
            }
        }
    }
    public void moveAsSubNodeAndMainNode() throws InterruptedException, IOException, ParseException {
        Thread.sleep(2000);
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(1500);
        common.clickElement("xpath", "//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
        List<WebElement> listElements3 = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of elements and List: " + listElements3.size());
        for (int i = 0; i < listElements3.size(); i++) {
            WebElement element3 = listElements3.get(i);
            System.out.println("Element Text: " + element3.getText());
            String moveToSubNode= element3.getText();
            if (element3.getText().equals(moveToSubNode)) {
                element3.click();
                Actions actions1 = new Actions(driver);
                actions1.contextClick(element3).perform();
                common.clickElement("xpath","//MenuItem[@Name='Move As Sub-Node']");
                Thread.sleep(1500);
                common.clickElement("xpath","//Button[@Name='Save']");
                common.clickElement("xpath","//Window[@Name='Transaction']/*/Button[@Name='Yes']");
                WebElement enter=common.findWebElement("xpath", "//Window[@Name='Transaction']/*/Button[@Name='Yes']");
                enter.sendKeys(Keys.ENTER,Keys.ENTER,Keys.ESCAPE);
                common.clickElement("xpath","//TabItem[@Name='Customers']/Button[@Name='Close']");
                Thread.sleep(1000);
                common.clickElement("name", "Sales");
                common.clickElement("name", "Customers");
                common.clickElement("xpath", "//TreeItem[@Name='All Customers']");
                Thread.sleep(1000);
                WebElement right=common.findWebElement("xpath","//TreeItem[@Name='All Customers']");
                right.sendKeys(Keys.ARROW_RIGHT,Keys.ARROW_RIGHT);
                WebElement readText=common.findWebElement("xpath","//TreeItem[@Name='All Customers']/TreeItem[@Name='NewNodeRename']");
                String movedNode=readText.getText();
                System.out.println("MovedNode Text :"+movedNode);
                if(readText.getText().equalsIgnoreCase(moveToSubNode)) {
                    System.out.println("node moved Successfully :"+moveToSubNode);
                }
                Thread.sleep(1000);
                Actions actions=new Actions(driver);
                actions.contextClick(readText).perform();
                common.clickElement("xpath","//MenuItem[@Name='Move As Main Node']");
                common.clickElement("xpath","//Button[@Name='OK']");
                System.out.println("Node moved to Main Node");
                List<WebElement> mainNode=common.findWebElements("xpath","//Tree/TreeItem[@Name='Customers']/*[contains(@Name,'NewNodeRename')]");
                System.out.println("allNodesText :"+mainNode.size());
                for (WebElement v:mainNode){
                    System.out.println("printing all MainNodes :"+v.getText());
                    if(v.getText().equalsIgnoreCase(movedNode)){
                        System.out.println("Great subNode is MovedToMainNode :"+movedNode);
                    }
                }
                break;
            }
        }
    }
    public void movingMastersBetweenNodes() throws InterruptedException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(1500);
        common.clickElement("xpath", "//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
        WebElement selectingMaster= common.findWebElement("xpath","//ListItem[@Name='AT_Cus_Reg_Inter']/Text[@Name='AT_Cus_Reg_Inter']");
        String selectMasterTomoveAnotherNode=selectingMaster.getText();
        Actions actions = new Actions(driver);
        actions.contextClick(selectingMaster).perform();
        common.clickElement("xpath","//MenuItem[@Name='Change']");
        WebElement coose=common.findWebElement("xpath","//MenuItem[@Name='Node']");
        coose.click();
        coose.sendKeys(Keys.DOWN,Keys.ENTER);
        common.clickElement("xpath", "//Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Button[@Name='Yes']");
        WebElement enter=common.findWebElement("xpath", "//Button[@Name='Yes']");
        enter.sendKeys(Keys.ENTER,Keys.ENTER,Keys.ESCAPE);
        common.clickElement("xpath","//TabItem[@Name='Customers']/Button[@Name='Close']");
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(1000);
        WebElement right=common.findWebElement("xpath","//TreeItem[@Name='All Customers']");
        right.sendKeys(Keys.ARROW_RIGHT,Keys.ARROW_RIGHT);
        common.clickElement("xpath","//TreeItem[@Name='NewNodeRename']");
        List<WebElement> listElement = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of elements and List: " + listElement.size());
        for (WebElement s:listElement){
            System.out.println("movedMasterText :"+s.getText());
            if(s.getText().equalsIgnoreCase(selectMasterTomoveAnotherNode)){
                System.out.println("Great masterMoved To The Some Other Node :"+selectMasterTomoveAnotherNode);
            }
        }


    }
}