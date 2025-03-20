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
import java.util.List;

public class Branches extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Branches(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void branch() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen2Steps("Company","Branches");
        Thread.sleep(1500);
        super.createMaster("xpath", "//TreeItem[@Name='Branches']/TreeItem[@Name='All Branches']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Edit[@Name='New Branch *']");
        inputTextWithValidation("xpath", "//Edit[@Name='New Branch *']", common.getData(dataFile, "newBranch") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Branch *']").getText();
        common.clickElement("xpath", "//Edit[@Name='Company *']");
        super.inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Address 1 *']", common.getData(dataFile, "address1"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Address 2']", common.getData(dataFile, "address2"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Address 3']", common.getData(dataFile, "address3"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='City *']", common.getData(dataFile, "city"));

        WebElement state = common.findWebElement("xpath", "//Edit[@Name='State']");
        state.clear();
        state.sendKeys(common.getData(dataFile, "state"), Keys.ENTER);
        common.clickElement("xpath", "//Edit[@Name='Country']");
        super.inputTextWithValidation("xpath", "//Edit[@Name='Zip']", common.getData(dataFile, "zip"));
        //GST registration
        common.clickElement("xpath", "//Pane[@Name='Contact Details']/Button[@Name='...']");
        Thread.sleep(1000);
        super.inputTextWithValidation("xpath", "//Edit[@Name='Telephone 1']", common.getData(dataFile, "Telephone1"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Telephone 2']", common.getData(dataFile, "Telephone2"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Telephone 3']", common.getData(dataFile, "Telephone3"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Telephone 4']", common.getData(dataFile, "Telephone4"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Fax']", common.getData(dataFile, "fax"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Email']", common.getData(dataFile, "email"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Website']", common.getData(dataFile, "website"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Contact Person']", common.getData(dataFile, "contactPersion"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Designation']", common.getData(dataFile, "contactPersonDesignation"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Telephone No']", common.getData(dataFile, "contactPersonTelephoneNo"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Mobile No']", common.getData(dataFile, "contactPersonMobileNo"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Email']", common.getData(dataFile, "contactPersonEmail"));
        common.clickElement("xpath", "//Button[@Name='Ok']");
//        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Bank Details']/following-sibling::Button[@Name='Open']");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.clickElement("xpath", "//Edit[@Name='GST Registration *']/following-sibling::Button[@Name='Open']");
        Thread.sleep(1000);
        List<WebElement> elementList = common.findWebElements("xpath", "//Window[@Name='Create New Master']/Window/Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("Size :  " + elementList.size());
        for (WebElement list : elementList) {
            System.out.println("elements list : " + list.getText());
        }
        Thread.sleep(1000);
        List<WebElement> newgst = common.findWebElements("xpath", "//Window[@Name='Create New Master']/Window/Pane/Button[@Name='New GST Registration']");
        System.out.println("size : " + newgst.size());
        WebElement newgstTest = common.findWebElement("xpath", "//Window[@Name='Create New Master']/Window/Pane/Text[@Name='Count : 0']");
        newgstTest.click();
        super.saveAfterMasterCreate();
        validateMastersAndInactive("Branches","All Branches",master,"Branches");
        Thread.sleep(1000);

    }
}

