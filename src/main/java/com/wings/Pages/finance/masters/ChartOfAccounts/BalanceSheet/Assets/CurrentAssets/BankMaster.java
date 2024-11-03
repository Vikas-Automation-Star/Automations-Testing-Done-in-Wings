package com.wings.pages.finance.masters.ChartOfAccounts.BalanceSheet.Assets.CurrentAssets;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.wings.utils.Common;

public class BankMaster {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public BankMaster(WindowsDriver driver, String file) {
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void bank() throws InterruptedException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Chart of Accounts");
        WebElement assets= common.findWebElement("xpath","//TreeItem[@Name='Balance Sheet']/TreeItem[@Name='Assets']");
        assets.click();
        assets.sendKeys(Keys.ARROW_RIGHT);
//        assets.

//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", assets, "ExpandCollapse.ExpandCollapseState", "Expanded");
//        assets.getAttribute("ExpandCollapse.ExpandCollapseState")="Expanded";
//        System.out.println(assets.getAttribute("ExpandCollapse.ExpandCollapseState"));
//        assets.click();
//        Thread.sleep(10000);
//        System.out.println(assets.getAttribute("ExpandCollapse.ExpandCollapseState"));

//        WebElement current= common.findWebElement("xpath","//TreeItem[@Name='Assets']/TreeItem[@Name='Current Assets, Loans and Advances']");
//        Actions actions=new Actions(driver);
//        actions.doubleClick(assets).perform();
//        actions.doubleClick(current).perform(); //unable to click this

    }
}
