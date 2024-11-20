package com.wings.pages.finance.masters.CreditCards;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.wings.utils.Common;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SwipeMachineType {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SwipeMachineType(WindowsDriver driver, String file){
        this.driver=driver;
        common=new Common(this.driver);
        dataFile=file;
    }
    public void createSwipeMachineType() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Credit Cards");
        common.clickElement("name", "Swipe Machine Types");
        Thread.sleep(1000);
        WebElement AllBranch = common.findWebElement("xpath", "//TreeItem[@Name='Swipe Machine Types']/TreeItem[@Name='All Swipe Machine Types']");
        Actions actions = new Actions(driver);
        actions.contextClick(AllBranch).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='New Swipe Machine Type *']", common.getData(dataFile, "name")+common.getRandom());
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Credit Card Company']/Button[@Name='Open']");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1000);
        common.clickElement("xpath","//Text[@Name='Swipe Type Property']/following-sibling::Button[@Name='...']");
        Thread.sleep(1000);
        common.inputText("xpath","//Edit[@Name='Swipe Type Row 0, Not sorted.']", common.getData(dataFile,"machine type"));
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.inputText("xpath","//Edit[@Name='Discount Row 0, Not sorted.']", common.getData(dataFile,"discount"));
        common.clickElement("name","Ok");

        common.clickElement("xpath", "//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Cancel']");

        common.clickElement("xpath", "//TabItem[@Name='Swipe Machine Types']/Button[@Name='Close']");
    }

}
