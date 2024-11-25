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

public class UnsecuredLoansMaster {

        WindowsDriver driver;
        Common common;
        String dataFile;

        public UnsecuredLoansMaster(WindowsDriver driver, String file) {
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public void unSecuredLoans() throws InterruptedException, IOException, ParseException, AWTException {
            common.clickElement("name", "Finance");
            common.clickElement("name", "Chart of Accounts");
            WebElement assets = common.findWebElement("xpath", "//TreeItem[@Name='Balance Sheet']/TreeItem[@Name='Liabilities']");
            assets.click();
            assets.sendKeys(Keys.ARROW_RIGHT);
            WebElement AllBranch = common.findWebElement("xpath", "//TreeItem[@Name='Unsecured Loans']");
            Actions actions = new Actions(driver);
            actions.contextClick(AllBranch).perform();
            common.clickElement("name", "New Master");
            Thread.sleep(1500);
            common.inputText("xpath", "//Edit[@Name='New Account *']", common.getData(dataFile, "name") + common.getRandom());
            Thread.sleep(1500);
            common.inputText("xpath", "//Edit[@Name='Account Code']", String.valueOf(common.getRandom()));
            common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
            common.clickElement("xpath", "//Button[@Name='Save  Show Properties']");
            Thread.sleep(1200);
            common.clickElement("xpath","//Pane[@Name='Registration']/Button[@Name='...']");
            common.inputText("xpath","//Window[@Name='Registration']/Pane/Pane/Edit[@Name='Party Reg Type *']","registered");
            Thread.sleep(5000);
            Robot robot =new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            common.inputText("xpath","//Edit[@Name='GSTIN']",common.getData(dataFile,"gst"));
            common.clickElement("xpath","//Edit[@Name='PAN']");
            common.clickElement("xpath","//Button[@Name='Verify GSTIN']");
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            common.clickElement("name","Ok");
            common.clickElement("name", "Save");
            Thread.sleep(800);
            common.clickElement("name", "Yes");
            common.clickElement("name", "OK");
            common.clickElement("xpath", "//Button[@Name='Close']");
        }
    }
