package com.wings.pages.company.masters;

import com.wings.pages.Masters;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Transporters extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Transporters(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createTransporters() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Company");
        common.clickElement("name", "Transporters");
        Thread.sleep(1000);
        super.createMaster("xpath", "//TreeItem[@Name='Transporters']/TreeItem[@Name='All Transporters']");
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='New Transporter *']", common.getData(dataFile, "transporter") + common.getRandom());
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane[@Name='Address and Contact Details']/Button[@Name='...']");
        Thread.sleep(1000);
        super.inputTextWithValidation("xpath", "//Edit[@Name='Address1']", common.getData(dataFile, "Address1"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Address2']", common.getData(dataFile, "Address2"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Address3']", common.getData(dataFile, "Address3"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='City']", common.getData(dataFile, "city"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='State']", common.getData(dataFile, "state"));
        Thread.sleep(1000);
        WebElement country = common.findWebElement("xpath", "//Edit[@Name='Country']");
        country.clear();
        country.sendKeys(common.getData(dataFile, "country"), Keys.ENTER);
        super.inputTextWithValidation("xpath", "//Edit[@Name='Zip']", common.getData(dataFile, "zip"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Mobile No']", common.getData(dataFile, "mobileNumber"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Telephones1']", common.getData(dataFile, "Telephone1"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Telephones2']", common.getData(dataFile, "Telephone2"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Telephones3']", common.getData(dataFile, "Telephone3"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Fax']", common.getData(dataFile, "fax"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Email']", common.getData(dataFile, "email"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Website']", common.getData(dataFile, "website"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Contact Person']", common.getData(dataFile, "contactPersion"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Designation']", common.getData(dataFile, "contactPersonDesignation"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Telephone No']", common.getData(dataFile, "contactPersonTelephoneNo"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Mobile No']", common.getData(dataFile, "contactPersonMobileNo"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Email']", common.getData(dataFile, "contactPersonEmail"));
        common.clickElement("xpath", "//Button[@Name='Ok']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane[@Name='Registration']/Button[@Name='...']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Party Reg Type *']/Button[@Name='Open']");
        Thread.sleep(1000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.clickElement("xpath", "//Edit[@Name='GSTIN']");
        super.inputTextWithValidation("xpath", "//Edit[@Name='GSTIN']", common.getData(dataFile, "GstNo"));
        common.clickElement("xpath", "//Edit[@Name='PAN']");
        common.clickElement("xpath", "//Button[@Name='Verify GSTIN']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//TitleBar/Button[@Name='Close']");
        common.clickElement("xpath", "//Button[@Name='Ok']");
        super.saveAfterMasterCreate();
        super.closeMaster("Transporters");
        Thread.sleep(2000);

    }
}

