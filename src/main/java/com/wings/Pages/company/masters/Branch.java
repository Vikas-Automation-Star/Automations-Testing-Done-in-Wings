package com.wings.pages.company.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.wings.utils.Common;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Branch {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Branch(WindowsDriver driver, String file){
        this.driver=driver;
        common=new Common(this.driver);
        dataFile=file;
    }

    public void branch() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Company");
        common.clickElement("name", "Branches");
        Thread.sleep(2500);
        WebElement AllBranch = common.findWebElement("xpath", "//TreeItem[@Name='Branches']/TreeItem[@Name='All Branches']");
        Actions actions = new Actions(driver);
        actions.contextClick(AllBranch).perform();
        common.clickElement("name", "New Master");
        common.findWebElement("name", "Create New Branch");
        common.inputText("xpath", "//Edit[@Name='New Branch *']",common.getData(dataFile,"newBranch")+common.getRandom());
        common.inputAndVerify("xpath", "//Edit[@Name='Address 1 *']",(common.getData(dataFile,"Address1")));
        common.inputAndVerify("xpath", "//Edit[@Name='Address 2']",(common.getData(dataFile,"Address2")));
        common.inputAndVerify("xpath", "//Edit[@Name='Address 3']",(common.getData(dataFile,"Address3")));
        common.inputAndVerify("xpath", "//Edit[@Name='City *']",(common.getData(dataFile,"City")));
        //State DD
        common.findWebElement("xpath", "//Edit[@Name='State']/Button[@Name='Open']").sendKeys(common.getData(dataFile,"State"));
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.findWebElement("xpath", "//Edit[@Name='Bank Details']/Button[@Name='Open']").sendKeys(common.getData(dataFile,"State"));
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.findWebElement("xpath", "//Edit[@Name='GST Registration *']/Button[@Name='Open']").sendKeys(common.getData(dataFile,"State"));
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        common.inputAndVerify("xpath", "//Edit[@Name='Zip']",common.getData(dataFile,"Zip"));
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Cancel']");
    }
    }

