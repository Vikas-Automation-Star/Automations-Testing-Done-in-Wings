package com.wings.pages.finance.masters.ManageCurrencies;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class SetExchangeRate {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public SetExchangeRate(WindowsDriver driver, String file) {
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void exchangeRate() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Manage Currencies");
        common.clickElement("xpath", "//MenuItem[@Name='Set Exchange Rates']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//ComboBox[@Name='Select']/Button[@Name='Open']");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.clickElement("name", "Go");

        List<WebElement> elementList = common.findWebElements("xpath", "//Table[@Name='Exchange Rates']/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().contains("ZMW")) {
                i.click();
                i.sendKeys("60", Keys.TAB, Keys.TAB, "60"); //it is taking wrong inputs and the date pane has no nome to send keys into it.
            }
        }
//        common.clickElement("xpath","OK");

//        common.clickElement("name","Submit");
//        common.clickElement("name","OK");
    }
}
