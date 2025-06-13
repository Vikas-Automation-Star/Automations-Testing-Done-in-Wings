package com.wings.pages.sales.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Customer extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Customer(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void newCustomer() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen2Steps(common.getData(dataFile,"menu"), common.getData(dataFile,"subMenu") );
        Thread.sleep(1500);
        createMaster("xpath","//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
        Thread.sleep(3000);
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
        common.rowDropDown(common.getData(dataFile, "purchasePriceList"));
        Thread.sleep(1200);
        common.clickElement("xpath", "//Edit[@Name='Transport']/Button[@Name='Open']");
        common.rowDropDown(common.getData(dataFile, "transport"));
        Thread.sleep(1200);
        common.clickElement("xpath", "//Edit[@Name='Transporter']/Button[@Name='Open']");
        common.rowDropDown(common.getData(dataFile, "transporter"));
        Thread.sleep(1200);
        common.clickElement("xpath", "//Edit[@Name='Type Of Customer']/Button[@Name='Open']");
        common.rowDropDown("Type of Customer");
        common.sliderHandling("xpath","//*/Thumb[@Name='Position']",0,200);
        common.inputText("xpath","//Edit[@Name='Aadhaar No']",common.getData(dataFile,"aadhar"));
        common.inputText("xpath","//Pane[@Name='PartyDescription']/Edit[@Name='Description']",common.getData(dataFile,"description"));
        Thread.sleep(1500);
        common.clickElement("xpath", "//Pane[@Name='Address and Contact Details']/Button[@Name='...']");
        Thread.sleep(1000);
        inputTextWithValidation("xpath", "//Edit[@Name='Address 1']", common.getData(dataFile, "Address1"));
        inputTextWithValidation("xpath", "//Edit[@Name='Address 2']", common.getData(dataFile, "Address2"));
         inputTextWithValidation("xpath", "//Edit[@Name='Address 3']", common.getData(dataFile, "Address3"));
         inputTextWithValidation("xpath", "//Edit[@Name='City']", common.getData(dataFile, "city"));
         inputTextWithValidation("xpath", "//Edit[@Name='State']", common.getData(dataFile, "state"));
        Thread.sleep(1000);
        WebElement country = common.findWebElement("xpath", "//Edit[@Name='Country']");
        country.clear();
        country.sendKeys(common.getData(dataFile, "country"), Keys.ENTER);
         inputTextWithValidation("xpath", "//Edit[@Name='Zip']", common.getData(dataFile, "zip"));
         inputTextWithValidation("xpath", "//Edit[@Name='Telephones 1']", common.getData(dataFile, "Telephone1"));
         inputTextWithValidation("xpath", "//Edit[@Name='Telephones 2']", common.getData(dataFile, "Telephone2"));
         inputTextWithValidation("xpath", "//Edit[@Name='Telephones 3']", common.getData(dataFile, "Telephone3"));
         inputTextWithValidation("xpath", "//Edit[@Name='Telephones 4']", common.getData(dataFile, "Telephone4"));
         inputTextWithValidation("xpath", "//Edit[@Name='Fax']", common.getData(dataFile, "fax"));
         inputTextWithValidation("xpath", "//Edit[@Name='Email']", common.getData(dataFile, "email"));
         inputTextWithValidation("xpath", "//Edit[@Name='Website']", common.getData(dataFile, "website"));
         inputTextWithValidation("xpath", "//Edit[@Name='Contact Person']", common.getData(dataFile, "contactPersion"));
         inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Designation']", common.getData(dataFile, "contactPersonDesignation"));
         inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Telephone No']", common.getData(dataFile, "contactPersonTelephoneNo"));
         inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Mobile No']", common.getData(dataFile, "contactPersonMobileNo"));
         inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Email']", common.getData(dataFile, "contactPersonEmail"));
        common.clickElement("xpath", "//Button[@Name='Ok']");
        common.sliderHandling("xpath","//*/Thumb[@Name='Position']",0,190);
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
        Thread.sleep(2500);
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
        common.clickElement("name", "Ok");
        Thread.sleep(3000);
        //scroll down
        common.sliderHandling("name", "Position", 0, 450);
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
        common.sliderHandling("name", "Position", 0, 95);
        common.clickElement("xpath","//Text[@Name='Shipping Address GST']/following-sibling::Button[@Name='...']");
        common.clickElement("xpath", "//Edit[@Name='GSTIN Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='GSTIN Row 0, Not sorted.']", common.getData(dataFile, "gst"));
        common.clickElement("xpath", "//Edit[@Name='Address Name * Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='Address Name * Row 0, Not sorted.']", common.getData(dataFile, "shippingAdressName"));
        common.clickElement("xpath", "//Edit[@Name='Address 1 * Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='Address 1 * Row 0, Not sorted.']", common.getData(dataFile, "ShippingAdress1"));
        common.inputText("xpath", "//Edit[@Name='Address 2 Row 0, Not sorted.']", common.getData(dataFile, "ShippingAdress2"));
        common.inputText("xpath", "//Edit[@Name='Address 3 Row 0, Not sorted.']", common.getData(dataFile, "ShippingAdress3"));
        common.clickElement("xpath", "//Edit[@Name='City * Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='City * Row 0, Not sorted.']", common.getData(dataFile, "Shippingcity"));
        WebElement state = common.findWebElement("xpath", "//Edit[@Name='State * Row 0, Not sorted.']");
        state.click();
        state.sendKeys(common.getData(dataFile, "ShippingState"), Keys.ENTER);
        Thread.sleep(3000);
        //verify this
            int offset = 300;
            WebElement slider = common.findWebElement("xpath", "//ScrollBar[@Name='Horizontal']/Thumb[@Name='Position']");
            Actions actions = new Actions(driver);
            actions.clickAndHold(slider).moveByOffset(offset, 0).release().perform();

        common.clickElement("xpath", "//Edit[@Name='Zip/PostalCode * Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='Zip/PostalCode * Row 0, Not sorted.']", common.getData(dataFile, "stateZipcode"));
        common.clickElement("xpath", "//Edit[@Name='Country * Row 0, Not sorted.']");
        WebElement country1 = common.findWebElement("xpath", "//Edit[@Name='Country * Row 0, Not sorted.']");
        country1.click();
        country1.sendKeys(common.getData(dataFile, "country"), Keys.ENTER);
        Thread.sleep(2000);
        common.clickElement("xpath", "//Button[@Name='Ok']");
        Thread.sleep(2000);
//        common.clickElement("xpath", "//Text[@Name='Consignor']/following-sibling::Button[@Name='...']");
//        WebElement consignor = common.findWebElement("xpath", "//Edit[@Name='Applicable Consignor Row 0, Not sorted.']");
//        consignor.click();
//        consignor.sendKeys(common.getData(dataFile, "consignor"), Keys.ENTER);
//        common.clickElement("xpath", "//Button[@Name='Ok']");
       //save
        saveAfterMasterCreate();
        //close
        closeMaster(common.getData(dataFile,"close"));
        refresh();
        //validate
        navigateToMastersWhen2Steps(common.getData(dataFile,"menu"), common.getData(dataFile,"subMenu") );
        validateAndInactivate(common.getData(dataFile,"close"),common.getData(dataFile,"newAccount") );

    }
}