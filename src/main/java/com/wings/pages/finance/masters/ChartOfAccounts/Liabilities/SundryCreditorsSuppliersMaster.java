package com.wings.pages.finance.masters.ChartOfAccounts.Liabilities;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SundryCreditorsSuppliersMaster {

        WindowsDriver driver;
        Common common;
        String dataFile;

        public SundryCreditorsSuppliersMaster(WindowsDriver driver, String file) {
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public void sundryCreditors() throws InterruptedException, IOException, ParseException, AWTException {
            common.clickElement("name", "Finance");
            common.clickElement("name", "Chart of Accounts");
            WebElement assets = common.findWebElement("xpath", "//TreeItem[@Name='Balance Sheet']/TreeItem[@Name='Liabilities']");
            assets.click();
            assets.sendKeys(Keys.ARROW_RIGHT);
            WebElement liabilities = common.findWebElement("xpath", "//TreeItem[@Name='Liabilities']/TreeItem[@Name='Current Liabilities and Provisions']");
            liabilities.click();
            liabilities.sendKeys(Keys.ARROW_RIGHT);
            WebElement AllBranch = common.findWebElement("xpath", "//TreeItem[@Name='Sundry Creditors/Suppliers']");
            Actions actions = new Actions(driver);
            actions.contextClick(AllBranch).perform();
            common.clickElement("name", "New Master");
            Thread.sleep(1500);
            common.inputText("xpath", "//Edit[@Name='New Account *']", common.getData(dataFile, "name") + common.getRandom());
            Thread.sleep(1500);
            common.inputText("xpath", "//Edit[@Name='Account Code']", String.valueOf(common.getRandom()));
            common.inputText("xpath","//Edit[@Name='Account Type *']", common.getData(dataFile,"accType"));
            common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
            common.clickElement("xpath", "//Button[@Name='Save  Show Properties']");
            Thread.sleep(500);
            common.clickElement("name", "Save");
            Thread.sleep(800);
            common.clickElement("name", "Yes");
            common.clickElement("name", "OK");
            common.clickElement("xpath", "//Button[@Name='Close']");
        }
    }
