package com.wings.pages;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class Masters {
    WindowsDriver driver;
    Common common;

    public Masters(WindowsDriver driver) {
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void selectMasterItem(String transaction) {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().contains(transaction)) {
                i.click();
                i.sendKeys(Keys.TAB);
            }
        }
    }

    public void selectDropDownMaster(String element) throws InterruptedException {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/*/*[contains(@Name,'MasterName row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().contains(element)) {
                i.click();
                break;
            }
        }
    }

    public void saveMaster() throws InterruptedException {
        common.clickElement("name", "Save");
        Thread.sleep(2000);
        common.clickElement("name", "OK");
        common.clickElement("xpath", "//Button[@Name='Close']");
    }

    public void closeMaster(String masterName) {
        common.clickElement("xpath", "//TabItem[@Name='" + masterName + "']/Button[@Name='Close']");
    }

    public void inputTextWithValidation(String locatorType, String locator, String inputText) {
        WebElement element = common.findWebElement(locatorType, locator);
        element.sendKeys(inputText);
        System.out.println(element.getText());
        if (element.getText().equals(inputText)) {
            System.out.println("entered currect Input :" + element.getText());
        } else {
            Assert.fail("wrong input");
        }
    }

    public void createMaster(String locatorType, String locator) {
        WebElement element = common.findWebElement(locatorType, locator);
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        common.clickElement("name", "New Master");
    }

    public void saveAfterMasterCreate() throws InterruptedException {
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Button[@Name='Close']");
    }

    public void validateAndInactivate(String menuItem,String startWith) throws IOException, ParseException {
        WebElement allGroups = common.findWebElement("xpath", "//TreeItem[@Name='"+menuItem+"']/TreeItem[@Name='All "+ menuItem +"']");
        allGroups.click();
        List<WebElement> listItems = common.findWebElements("xpath", "//Pane[@Name='"+menuItem+"']/Pane/Pane/Pane/Pane/Pane/List/*");
        boolean masterValidation=false;
        for (WebElement items : listItems){
            System.out.println(items.getText());
            if (items.getText().startsWith(startWith)){
                masterValidation=true;
                System.out.println("Master is created successfully - " + items.getText());
                //inactivate it
                items.click();
                Actions actions1 =new Actions(driver);
                actions1.contextClick(items).perform();
                common.clickElement("xpath","//MenuItem[@Name='Inactivate']");
                WebDriverWait wait=new WebDriverWait(driver,5);
                WebElement okButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//Button[@Name='OK']")));
                okButton.click();
                System.out.println("Master Inactivated successfully");
                closeMaster(menuItem);
                break;
            }
        }
        if (!masterValidation) Assert.fail("Master is not validated");
    }

    public void refresh() {
        common.clickElement("xpath", "//MenuItem[@Name='Tools']");
        common.clickElement("xpath", "//MenuItem[@Name='Clear Cache']");
    }

    public void navigateToMastersWhen2Steps(String menu, String menuItem) {
        common.clickElement("xpath", "//MenuItem[@Name='"+menu+"']");
        common.clickElement("xpath", "//MenuItem[@Name='"+menuItem+"']");
    }

    public void navigateToMastersWhen3Steps(String menu, String menuItem, String subMenuItem) {
        common.clickElement("xpath", "//MenuItem[@Name='"+menu+"']");
        common.clickElement("xpath", "//MenuItem[@Name='"+menuItem+"']");
        common.clickElement("xpath", "//MenuItem[@Name='"+subMenuItem+"']");
    }

    public void navigateToMastersWhen4Steps(String menu, String secondMenu, String thirdMenu,String fourthMenu) {
        common.clickElement("xpath", "//MenuItem[@Name='"+menu+"']");
        common.clickElement("xpath", "//MenuItem[@Name='"+secondMenu+"']");
        common.clickElement("xpath", "//MenuItem[@Name='"+thirdMenu+"']");
        common.clickElement("xpath", "//MenuItem[@Name='"+fourthMenu+"']");
    }

    public void validateMastersAndInactive(String menuItem,String master){
        common.clickElement("xpath","//TreeItem[@Name='"+menuItem+"']");
        common.clickElement("xpath","//TreeItem[@Name='All "+ menuItem +"']");
        List<WebElement> fetchMasters=common.findWebElements("xpath","//List//ListItem");
        for (WebElement v:fetchMasters){
            if (v.getText().equals(master)) {
                System.out.println("masterName :"+v.getText());
                System.out.println("Master validated do Inactive");
                Actions actions=new Actions(driver);
                actions.contextClick(v).perform();
                common.clickElement("xpath","//MenuItem[@Name='Inactivate']");
                common.clickElement("xpath","//*/Button[@Name='OK']");
                System.out.println("Master inactive successfully");
                closeMaster(menuItem);
                break;
            }
            else {
                System.out.println("check another Master");
            }
        }

//        WebElement fetchMaster=common.findWebElement(listXpath,listLocator);
//        Actions actions=new Actions(driver);
//        actions.contextClick(fetchMaster).perform();
//        common.clickElement("xpath","//MenuItem[@Name='Inactivate']");
//        common.clickElement("xpath","//*/Button[@Name='OK']");
//        System.out.println("Master inactive successfully");
    }
}






























