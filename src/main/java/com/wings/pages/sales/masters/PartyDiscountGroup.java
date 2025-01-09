package com.wings.pages.sales.masters;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class PartyDiscountGroup {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PartyDiscountGroup(WindowsDriver driver, String file) {
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void partyGroup() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Prices and Discounts");
        common.clickElement("xpath", "//Menu[@Name='Prices and Discounts']/MenuItem[@Name='Party Discount Groups']");
        Thread.sleep(1000);
        WebElement allPartyDiscount = common.findWebElement("xpath", "//TreeItem[@Name='Party Discount Groups']/TreeItem[@Name='All Party Discount Groups']");
        Actions actions = new Actions(driver);
        actions.contextClick(allPartyDiscount).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(2500);
        common.clickElement("xpath", "//Edit[@Name='New Party Discount Group *']");
        common.inputText("xpath", "//Edit[@Name='New Party Discount Group *']", common.getData(dataFile, "partyGroup") + common.getRandom());
        common.inputAndVerify("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        common.clickElement("xpath", "//Text[@Name='Applicable Party Nodes']/following-sibling::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='Applicable Node Row 0, Not sorted.']", common.getData(dataFile, "applicable"));
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.clickElement("name", "Ok");
        common.clickElement("xpath", "//Pane/Button[@Name='Save']");
        Thread.sleep(500);
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
        Thread.sleep(500);
        common.clickElement("xpath", "//Pane/Button[@Name='Cancel']");
        common.clickElement("xpath", "//TabItem[@Name='Party Discount Groups']/Button[@Name='Close']");
    }
}
