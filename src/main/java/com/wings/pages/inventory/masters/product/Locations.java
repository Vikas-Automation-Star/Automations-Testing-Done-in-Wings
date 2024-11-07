package com.wings.pages.inventory.masters.product;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.wings.pages.Masters;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class Locations extends Masters {
    WindowsDriver driver;
    Common common;
    String filepath;

    public Locations(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(driver);
        filepath = file;
    }

    public void locationMastercreation() throws InterruptedException, AWTException, IOException, ParseException {
        common.clickElement("name", "Inventory");
        common.clickElement("name", "Product");
        common.clickElement("name", "Locations");
        super.actionsMaster("xpath","//TreeItem[@Name='Locations']/TreeItem[@Name='All Locations']","//MenuItem[@Name='New Master']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Location *']", common.getData(filepath, "location") + common.getRandom());
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMasterItem(common.getData(filepath, "branch"));
        common.inputAndVerify("xpath","//Edit[@Name='Address 1 *']", common.getData(filepath,"address1"));
        common.inputAndVerify("xpath","//Edit[@Name='City *']", common.getData(filepath,"city"));
        common.inputText("xpath","//Edit[@Name='GSTIN']", common.getData(filepath,"gst"));
        common.inputAndVerify("xpath","//Edit[@Name='State *']", common.getData(filepath,"state"));
        common.inputAndVerify("xpath","//Edit[@Name='ZIP/PostalCode']", common.getData(filepath,"zip"));
        Thread.sleep(5000);
        common.clickElement("xpath","//Pane[@Name='AddressAndContactDetails']/Pane[@Name='Address and Contact Details']");
        List<WebElement> button=  common.findWebElements("xpath","//Pane[@Name='AddressAndContactDetails']/Pane[@Name='Address and Contact Details']/Button[@Name='...']");
        System.out.println("button size:-"+button.size());
        button.get(0).click();
        Thread.sleep(1000);

        common.inputAndVerify("xpath","//Edit[@Name='Address 1']", common.getData(filepath,"address1"));
        common.inputAndVerify("xpath","//Edit[@Name='Address 2']", common.getData(filepath,"address2"));
        common.inputAndVerify("xpath","//Edit[@Name='Address 3']", common.getData(filepath,"address3"));
        common.inputAndVerify("xpath","//Edit[@Name='City']", common.getData(filepath,"city"));
        common.inputAndVerify("xpath","//Edit[@Name='State']", common.getData(filepath,"state"));
        common.inputAndVerify("xpath","//Edit[@Name='Country']", common.getData(filepath,"country"));
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1500);
        common.clickElement("xpath","//Edit[@Name='Zip']");
        common.inputAndVerify("xpath","//Edit[@Name='Zip']", common.getData(filepath,"zip"));
        common.inputAndVerify("xpath","//Edit[@Name='Telephones 1']",common.getData(filepath,"tel1"));
        common.inputAndVerify("xpath","//Edit[@Name='Telephones 2']",common.getData(filepath,"tel2"));
        common.inputAndVerify("xpath","//Edit[@Name='Telephones 3']",common.getData(filepath,"tel3"));
        common.inputAndVerify("xpath","//Edit[@Name='Telephones 4']",common.getData(filepath,"tel4"));
        common.inputAndVerify("xpath","//Edit[@Name='Fax']",common.getData(filepath,"fax"));
        common.inputAndVerify("xpath","//Edit[@Name='Email']",common.getData(filepath,"email"));
        common.inputAndVerify("xpath","//Edit[@Name='Website']",common.getData(filepath,"website"));
        common.inputAndVerify("xpath","//Edit[@Name='Contact Person']",common.getData(filepath,"cp"));
        common.inputAndVerify("xpath","//Edit[@Name='Contact Person Designation']",common.getData(filepath,"cpd"));
        common.inputAndVerify("xpath","//Edit[@Name='Contact Person Telephone No']",common.getData(filepath,"cptn"));
        common.inputAndVerify("xpath","//Edit[@Name='Contact Person Mobile No']", common.getData(filepath,"cpmn"));
        common.inputAndVerify("xpath","//Edit[@Name='Contact Person Email']",common.getData(filepath,"cpe"));
        common.clickElement("name","Ok");
        super.saveMaster();
        super.closeMaster("Locations");
        System.out.println("Locations created successfully");
        Thread.sleep(2000);
    }
}
