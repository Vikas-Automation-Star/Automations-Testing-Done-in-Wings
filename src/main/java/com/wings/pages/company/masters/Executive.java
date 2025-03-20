package com.wings.pages.company.masters;


import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class Executive extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Executive(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(driver);
        dataFile = file;
    }

    public void Executive() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Company","Executives");
        Thread.sleep(2500);
        super.createMaster("xpath", "//TreeItem[@Name='Executives']/TreeItem[@Name='All Executives']");
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='New Executive *']", common.getData(dataFile, "Executive") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Executive *']").getText();
        common.inputText("xpath", "//Edit[@Name='Executive Code']", common.getData(dataFile, "eCode") + common.getRandom());
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        common.clickElement("xpath", "//Pane[@Name='Address and Contact Details']/Button[@Name='...']");
        Thread.sleep(1000);
        super.inputTextWithValidation("xpath", "//Edit[@Name='Address 1']", common.getData(dataFile, "Address1"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Address 2']", common.getData(dataFile, "Address2"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Address 3']", common.getData(dataFile, "Address3"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='City']", common.getData(dataFile, "city"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='State']", common.getData(dataFile, "state"));
        Thread.sleep(1000);
        WebElement country = common.findWebElement("xpath", "//Edit[@Name='Country']");
        country.clear();
        country.sendKeys(common.getData(dataFile, "country"), Keys.ENTER);
        super.inputTextWithValidation("xpath", "//Edit[@Name='Zip']", common.getData(dataFile, "zip"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Telephones 1']", common.getData(dataFile, "Telephone1"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Telephones 2']", common.getData(dataFile, "Telephone2"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Telephones 3']", common.getData(dataFile, "Telephone3"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Telephones 4']", common.getData(dataFile, "Telephone4"));
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
        super.saveAfterMasterCreate();
        validateMastersAndInactive("Executives","All Executives",master,"Executives");
    }

}
