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

public class Transporters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Transporters(WindowsDriver driver, String file){
        this.driver=driver;
        common=new Common(this.driver);
        dataFile=file;
    }

    public void navigateToMaster() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Company");
        common.clickElement("name", "Transporters");
        Thread.sleep(1000);
        WebElement AllBranch = common.findWebElement("xpath", "//TreeItem[@Name='Transporters']/TreeItem[@Name='All Transporters']");
        Actions actions = new Actions(driver);
        actions.contextClick(AllBranch).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='New Transporter *']", common.getData(dataFile, "transporter")+common.getRandom());
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane[@Name='Address and Contact Details']/Button[@Name='...']");
        //enter address details
        WebElement address=driver.findElementByXPath("//Window[@Name='Address and Contact Details']/Pane/Pane/Edit[@Name='Address1']");
        address.sendKeys(common.getData(dataFile,"address1"), Keys.TAB,common.getData(dataFile,"address2"),Keys.TAB, common.getData(dataFile,"address3"),Keys.TAB, common.getData(dataFile,"city"),Keys.TAB, common.getData(dataFile,"state"),Keys.TAB, common.getData(dataFile,"country"),Keys.ENTER, common.getData(dataFile,"zip"),Keys.TAB,common.getData(dataFile,"mobno"),Keys.TAB, common.getData(dataFile,"tel1"),Keys.TAB, common.getData(dataFile,"tel2"),Keys.TAB, common.getData(dataFile,"tel3"),Keys.TAB, common.getData(dataFile,"fax"),Keys.TAB, common.getData(dataFile,"email"),Keys.TAB, common.getData(dataFile,"website"),Keys.TAB, common.getData(dataFile,"cp"),Keys.TAB, common.getData(dataFile,"cpd"),Keys.TAB, common.getData(dataFile,"cptn"),Keys.TAB, common.getData(dataFile,"cpmn"), Keys.TAB, common.getData(dataFile,"cpe"),Keys.TAB,Keys.ENTER);

        common.clickElement("xpath","//Pane[@Name='Registration']/Button[@Name='...']");
        //enter GST details
        common.inputText("xpath","//Window[@Name='Registration']/Pane/Pane/Edit[@Name='Party Reg Type *']","registered");
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        WebElement gst=driver.findElementByXPath("//Edit[@Name='GSTIN']");
        gst.sendKeys(common.getData(dataFile,"gst"),Keys.TAB,common.getData(dataFile,"pan"),Keys.TAB,Keys.TAB,common.getData(dataFile,"LName"),Keys.TAB,common.getData(dataFile,"Trade name"),Keys.TAB,common.getData(dataFile,"Add1"),Keys.TAB,common.getData(dataFile,"Add2"),Keys.TAB,common.getData(dataFile,"Add3"),Keys.TAB,common.getData(dataFile,"City"));
        common.inputText("xpath","//Edit[@Name='ZIP *']", common.getData(dataFile,"Zip1"));
        common.clickElement("name","Ok");
        common.clickElement("xpath","//Pane/Button[@Name='Save']");
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Cancel']");

        common.clickElement("xpath", "//TabItem[@Name='Transporters']/Button[@Name='Close']");

}
    }
