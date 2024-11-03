package com.wings.pages.company.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.wings.utils.Common;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Terms {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Terms(WindowsDriver driver, String file){
        this.driver=driver;
        common=new Common(this.driver);
        dataFile=file;
    }

    public void term() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name","Company");
        common.clickElement("name","Terms");
        common.clickElement("xpath","//MenuItem[@Name='Terms']");
        Thread.sleep(1000);
        WebElement AllBranch = common.findWebElement("xpath", "//TreeItem[@Name='Terms']/TreeItem[@Name='All Terms']");
        Actions actions = new Actions(driver);
        actions.contextClick(AllBranch).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(1000);
        common.inputText("xpath","//Edit[@Name='New Term *']",common.getData(dataFile,"newTerm")+common.getRandom());
        Thread.sleep(2000);
        common.clickElement("xpath","//Edit[@Name='Term Type *']/Button[@Name='Open']");
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1000);
        common.inputText("xpath","//Edit[@Name='Description']", common.getData(dataFile,"description"));
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Cancel']");

        common.clickElement("xpath", "//TabItem[@Name='Terms']/Button[@Name='Close']");
    }
}
