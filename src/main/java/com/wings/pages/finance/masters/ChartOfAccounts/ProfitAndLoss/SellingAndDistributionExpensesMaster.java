package com.wings.pages.finance.masters.ChartOfAccounts.ProfitAndLoss;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.awt.*;
import java.io.IOException;

public class SellingAndDistributionExpensesMaster {
        WindowsDriver driver;
        Common common;
        String dataFile;

        public SellingAndDistributionExpensesMaster(WindowsDriver driver, String file) {
            this.driver = driver;
            common = new Common(this.driver);
            dataFile = file;
        }

        public void sellingDistribution() throws InterruptedException, IOException, ParseException, AWTException {
            common.clickElement("name", "Finance");
            common.clickElement("name", "Chart of Accounts");
            WebElement assets = common.findWebElement("xpath", "//TreeItem[@Name='Profit and Loss']");
            assets.click();
            assets.sendKeys(Keys.ARROW_RIGHT);
            WebElement liabilities = common.findWebElement("xpath", "//TreeItem[@Name='Profit and Loss Account']");
            liabilities.click();
            liabilities.sendKeys(Keys.ARROW_RIGHT);
            WebElement AllBranch = common.findWebElement("xpath", "//TreeItem[@Name='Selling and Distribution Expenses']");
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
            driver.findElementByXPath("//Edit[@Name='HSN Code']").click();
            common.clickElement("name", "Save");
            Thread.sleep(800);
            common.clickElement("name", "Yes");
            common.clickElement("name", "OK");
            common.clickElement("xpath", "//Button[@Name='Close']");
        }
    }
