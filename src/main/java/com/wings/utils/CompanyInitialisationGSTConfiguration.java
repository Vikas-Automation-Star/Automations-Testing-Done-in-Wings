package com.wings.utils;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CompanyInitialisationGSTConfiguration {
    WindowsDriver driver;
    Common common;
    public CompanyInitialisationGSTConfiguration(WindowsDriver driver){
        common=new Common(driver);
        this.driver=driver;
    }

    public void cgstConfiguration() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        Thread.sleep(1500);
        common.clickElement("xpath","//Text[@Name='GST Configuration']/*[@Name='GST Configuration']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        List<WebElement> clickAll=common.findWebElements("xpath","//Table/*[contains(@Name,'Row')]/CheckBox[contains(@Name,'UseThis Row')]");
        for(WebElement i:clickAll){
            i.click();
        }
        common.clickElement("xpath","//Button[@Name='Next >']");
        WebElement element = common.findWebElement("xpath", "//Tree[1]/Group[@Name='Data Panel']/TreeItem[@Name='Node0']/*[@Name='Id row 0']");
        element.click();
        element.sendKeys(Keys.SPACE);
        WebElement element1 = common.findWebElement("xpath", "//Tree[2]/Group[@Name='Data Panel']/TreeItem[@Name='Node0']/*[@Name='Id row 0']");
        element1.click();
        element1.sendKeys(Keys.SPACE);
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        Thread.sleep(3000);
        WebElement element2=common.findWebElement("xpath","//ComboBox[@Name='GST Paid In Advance Account']/Button[@Name='Open']");
        element2.sendKeys(Keys.DOWN,Keys.ENTER);
        Thread.sleep(3000);
        WebElement element3=common.findWebElement("xpath","//ComboBox[@Name='CGST Paid Account']/Button[@Name='Open']");
        element3.sendKeys(Keys.DOWN,Keys.ENTER);
        Thread.sleep(3000);
        WebElement element4 =common.findWebElement("xpath","//ComboBox[@Name='CGST Collected Account']/Button[@Name='Open']");
        element4.sendKeys(Keys.DOWN,Keys.ENTER);
        Thread.sleep(1000);
        common.clickElement("xpath","//Button[@Name='Finish']");
        System.out.println("CGST configuration completed");
    }


    public void sgstConfiguration() throws InterruptedException {
        common.clickElement("xpath","//Text[@Name='GST Configuration']/*[@Name='GST Configuration']");
        common.clickElement("xpath","//TreeItem[@Name='SGST']/TreeItem[@Name='SGST']");

        common.clickElement("xpath","//Button[@Name='Next >']");

        List<WebElement> clickAll=common.findWebElements("xpath","//Table/*[contains(@Name,'Row')]/CheckBox[contains(@Name,'UseThis Row')]");
        for(WebElement i:clickAll){
            i.click();
        }

        common.clickElement("xpath","//Button[@Name='Next >']");
        WebElement element = common.findWebElement("xpath", "//Tree[1]/Group[@Name='Data Panel']/TreeItem[@Name='Node0']/*[@Name='Id row 0']");
        element.click();
        element.sendKeys(Keys.SPACE);
        WebElement element10 = common.findWebElement("xpath", "//Tree[2]/Group[@Name='Data Panel']/TreeItem[@Name='Node0']/*[@Name='Id row 0']");
        element10.click();
        element10.sendKeys(Keys.SPACE);
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        Thread.sleep(1000);
        WebElement element5 =common.findWebElement("xpath","//ComboBox[@Name='GST Paid in Advance Account']/Button[@Name='Open']");
        element5.sendKeys(Keys.DOWN,Keys.ENTER);
        Thread.sleep(1000);
        WebElement element6 =common.findWebElement("xpath","//ComboBox[@Name='SGST Paid Account']/Button[@Name='Open']");
        element6.sendKeys(Keys.DOWN,Keys.ENTER);
        Thread.sleep(1000);
        WebElement element7 =common.findWebElement("xpath","//ComboBox[@Name='SGST Collected Account']/Button[@Name='Open']");
        element7.sendKeys(Keys.DOWN,Keys.ENTER);
        common.clickElement("xpath","//Button[@Name='Finish']");
        System.out.println("SGST configuration completed");
    }
    public void igstConfiguration() throws InterruptedException {
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        common.clickElement("xpath","//Text[@Name='GST Configuration']/*[@Name='GST Configuration']");
        common.clickElement("xpath","//TreeItem[@Name='IGST']/TreeItem[@Name='IGST']");

        common.clickElement("xpath","//Button[@Name='Next >']");
        List<WebElement> clickAll=common.findWebElements("xpath","//Table/*[contains(@Name,'Row')]/CheckBox[contains(@Name,'UseThis Row')]");
        for(WebElement i:clickAll){
            i.click();
        }
        common.clickElement("xpath","//Button[@Name='Next >']");
        WebElement element = common.findWebElement("xpath", "//Tree[1]/Group[@Name='Data Panel']/TreeItem[@Name='Node0']/*[@Name='Id row 0']");
        element.click();
        element.sendKeys(Keys.SPACE);
        WebElement element11 = common.findWebElement("xpath", "//Tree[2]/Group[@Name='Data Panel']/TreeItem[@Name='Node0']/*[@Name='Id row 0']");
        element11.click();
        element11.sendKeys(Keys.SPACE);
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        Thread.sleep(4000);
        WebElement element8 =common.findWebElement("xpath","//ComboBox[@Name='GST Paid In Advance Account']/Button[@Name='Open']");
        element8.sendKeys(Keys.DOWN,Keys.ENTER);
        Thread.sleep(1000);
        WebElement element9 =common.findWebElement("xpath","//ComboBox[@Name='IGST Paid Account']/Button[@Name='Open']");
        element9.sendKeys(Keys.DOWN,Keys.ENTER);
        Thread.sleep(1000);
        WebElement element10 =common.findWebElement("xpath","//ComboBox[@Name='IGST Collected Account']/Button[@Name='Open']");
        element10.sendKeys(Keys.DOWN,Keys.ENTER);
        common.clickElement("xpath","//Button[@Name='Finish']");
        System.out.println("IGST configuration completed");
    }


}
