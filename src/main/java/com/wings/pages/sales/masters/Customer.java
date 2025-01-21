package com.wings.pages.sales.masters;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Customer {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Customer(WindowsDriver driver, String file) {
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void newCustomer() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(5000);
        WebElement allCustomer = common.findWebElement("xpath", "//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
        Actions actions = new Actions(driver);
        actions.contextClick(allCustomer).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(5000);
        common.inputText("xpath", "//Edit[@Name='New Account *']", common.getData(dataFile, "newAccount") + common.getRandom());
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
        Thread.sleep(1200);
        common.clickElement("xpath", "//Edit[@Name='Route']/Button[@Name='Open']");
        common.rowDropDown(common.getData(dataFile, "Route"));
        Thread.sleep(1200);
        common.clickElement("xpath", "//Edit[@Name='Purchase Price List']/Button[@Name='Open']");
        common.rowDropDown(common.getData(dataFile, "priceList"));
        Thread.sleep(1200);
        common.clickElement("xpath", "//Edit[@Name='Transport']/Button[@Name='Open']");
        common.rowDropDown(common.getData(dataFile, "transport"));
        Thread.sleep(1200);
        common.clickElement("xpath", "//Edit[@Name='Transporter']/Button[@Name='Open']");
        common.rowDropDown(common.getData(dataFile, "transporter"));
        Thread.sleep(1200);
        common.clickElement("xpath", "//Edit[@Name='Type Of Customer']/Button[@Name='Open']");
        common.rowDropDown("Type of Customer");
        common.sliderHandling("xpath","//*/Thumb[@Name='Position']",0,250);
        Thread.sleep(1000);
        common.inputAndVerify("xpath", "//Edit[@Name='Aadhaar No']", common.getData(dataFile, "aadhar"));
        common.clickElement("xpath", "//Pane[@Name='Registration']/Button[@Name='...']");
        Thread.sleep(2500);
        common.inputText("xpath", "//Window[@Name='Registration']/Pane/Pane/Edit[@Name='Party Reg Type *']", "registered");
        Thread.sleep(5000);
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
        common.sliderHandling("name", "Position", 0, 250);
        common.clickElement("xpath", "//Pane/Pane[@Name='Contact Details']/Button[@Name='...']");
        common.inputAndVerify("xpath", "//Edit[@Name='Telephones 1']", common.getData(dataFile, "tel1"));
        common.inputAndVerify("xpath", "//Edit[@Name='Telephones 2']", common.getData(dataFile, "tel2"));
        common.inputAndVerify("xpath", "//Edit[@Name='Telephones 3']", common.getData(dataFile, "tel3"));
        common.inputAndVerify("xpath", "//Edit[@Name='Telephones 4']", common.getData(dataFile, "tel4"));
        common.inputAndVerify("xpath", "//Edit[@Name='Fax']", common.getData(dataFile, "fax"));
        common.inputAndVerify("xpath", "//Edit[@Name='Email']", common.getData(dataFile, "email"));
        common.inputAndVerify("xpath", "//Edit[@Name='Website']", common.getData(dataFile, "website"));
        common.inputAndVerify("xpath", "//Edit[@Name='Contact Person']", common.getData(dataFile, "cp"));
        common.inputAndVerify("xpath", "//Edit[@Name='Contact Person Designation']", common.getData(dataFile, "cpd"));
        common.inputAndVerify("xpath", "//Edit[@Name='Contact Person Telephone No']", common.getData(dataFile, "cptn"));
        common.inputAndVerify("xpath", "//Edit[@Name='Contact Person Mobile No']", common.getData(dataFile, "cpmn"));
        common.inputAndVerify("xpath", "//Edit[@Name='Contact Person Email']", common.getData(dataFile, "cpe"));
        common.clickElement("name", "Ok");

        //TDS
        common.clickElement("xpath", "//CheckBox[@Name='Apply TDS']");
        common.clickElement("xpath", "//Edit[@Name='TDS Assessee Type']");
        common.clickElement("xpath", "//CheckBox[@Name='Apply TCS']");
        common.clickElement("xpath", "//Edit[@Name='TCS Assessee Type']");

        common.clickElement("xpath", "//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Cancel']");
//        super.closeMaster(common.getData(dataFile,"close"));
    }
}
