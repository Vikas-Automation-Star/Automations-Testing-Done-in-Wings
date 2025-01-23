package com.wings.pages;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class MasterConfig {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public MasterConfig(WindowsDriver driver, String file) {
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

    public void masterActive(){

    }
}