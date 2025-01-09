package com.wings.pages;

import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.IOException;

public class LandingPage {
    WindowsDriver driver;
    Common common;
    FileUtil file = new FileUtil();
    String file1 = "./src/main/resources/input_Data.json";

    public LandingPage(WindowsDriver driver) {
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void validateMenus() throws IOException, ParseException, InterruptedException {
        navigateToFileMenu();
        navigateToCompanyMenu();
        navigateToSalesMenu();
        navigateToPurchaseMenu();
        navigateToInventoryMenu();
        navigateToFinanceMenu();
//        navigateToProductionMenu();
        navigateToTaxesMenu();
        navigateToConfigureMenu();
        navigateToAuditMenu();
        navigateToToolsMenu();
        navigateToHelpMenu();
    }

    public void validateTabs() throws InterruptedException {
        navigateToConfigureTab();
        navigateToAnalyticsTab();
        navigateToWorkbenchTab();
        navigateToTodoListTab();
    }

    public void navigateToFileMenu() throws InterruptedException {
        common.clickElement("name", "FileUtil");
        Thread.sleep(1000);
        Assert.assertTrue(common.isDisplayed("name", "Exit"), "it should displayed");
    }

    public void navigateToCompanyMenu() throws IOException, ParseException, InterruptedException {
        common.clickElement("name", common.getData(file1, "companyName"));
        Thread.sleep(1000);
        Assert.assertTrue(common.isDisplayed("name", "Company"), "Compose Option Is displayed");
//        Assert.assertEquals("Company", file.getData(file1, "Company"));
    }

    public void navigateToSalesMenu() throws InterruptedException {
        common.clickElement("xpath", "//MenuItem[@Name='Sales']");
        Thread.sleep(1000);
        Assert.assertTrue(common.isDisplayed("name", "Orders"), "Customers isn't displayed");

    }

    public void navigateToPurchaseMenu() throws InterruptedException {
        common.clickElement("xpath", "//MenuItem[@Name='Purchase']");
        Thread.sleep(1000);

        Assert.assertTrue(common.isDisplayed("name", "Orders"), "Suppliers isn't displayed");
    }

    public void navigateToInventoryMenu() throws InterruptedException {
        common.clickElement("xpath", "//MenuItem[@Name='Inventory']");
        Thread.sleep(1000);

        Assert.assertTrue(common.isDisplayed("name", "Product"), "Product isn't displayed");
    }

    public void navigateToFinanceMenu() throws InterruptedException {
        common.clickElement("xpath", "//MenuItem[@Name='Finance']");
        Thread.sleep(1000);

        Assert.assertTrue(common.isDisplayed("name", "Chart of Accounts"), "Chart of Accounts isn't displayed");
    }

    //    public void navigateToProductionMenu() {
//        common.clickElement("xpath", "//MenuItem[@Name='Production']");
//        Assert.assertTrue(common.isDisplayed("name", "Bill of Material"), "Bill of Material isn't displayed");
//    }
    public void navigateToTaxesMenu() throws InterruptedException {
        common.clickElement("xpath", "//MenuItem[@Name='Taxes']");
        Thread.sleep(1000);

        Assert.assertTrue(common.isDisplayed("name", "GST"), "it should be displayed");
    }

    public void navigateToConfigureMenu() throws InterruptedException {
        common.clickElement("xpath", "//MenuItem[@Name='Configure']");
        Thread.sleep(1000);

        Assert.assertTrue(common.isDisplayed("name", "User Rights"), "User Rights isn't displayed");
    }

    public void navigateToAuditMenu() throws InterruptedException {
        common.clickElement("xpath", "//MenuItem[@Name='Audit']");
        Thread.sleep(1000);

        Assert.assertTrue(common.isDisplayed("name", "Login Status"), "Login Status isn't displayed");
    }

    public void navigateToToolsMenu() throws InterruptedException {
        common.clickElement("xpath", "//MenuItem[@Name='Tools']");
        Thread.sleep(1000);

        Assert.assertTrue(common.isDisplayed("name", "Search Masters"), "Search Masters isn't displayed");
    }

    public void navigateToHelpMenu() throws InterruptedException {
        common.clickElement("xpath", "//MenuItem[@Name='Help']");
        Thread.sleep(1000);

        Assert.assertTrue(common.isDisplayed("name", "About"), "About isn't displayed");
    }

    public void navigateToConfigureTab() throws InterruptedException {
        common.clickElement("xpath", "//TabItem[@Name='Configure']");
        Thread.sleep(1000);

        Assert.assertTrue(common.isDisplayed("name", "Settings"), "Settings is not displayed");
    }

    public void navigateToAnalyticsTab() throws InterruptedException {
        common.clickElement("xpath", "//TabItem[@Name='Analytics']");
        Thread.sleep(1000);
        Assert.assertTrue(common.isDisplayed("name", "Dashboard"), "dashboard is not displayed");
    }

    public void navigateToWorkbenchTab() throws InterruptedException {
        common.clickElement("xpath", "//TabItem[@Name='Workbench']");
        Thread.sleep(1000);
        Assert.assertTrue(common.isDisplayed("name", "SALES") && common.isDisplayed("name", "PURCHASES")
                , "sales & purchase r not displayed");
    }

    public void navigateToTodoListTab() {
        common.clickElement("xpath", "//TabItem[@Name='My Todo List']");

//        Assert.assertTrue(common.isDisplayed("name","Row 2"),"Status message is not displayed");
    }

    public void quit() {
        common.clickElement("xpath", "//Button[@Name='Close']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
//        common.quit();
    }
}
