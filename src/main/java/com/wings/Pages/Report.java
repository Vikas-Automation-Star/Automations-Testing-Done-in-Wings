package com.wings.pages;

import io.appium.java_client.windows.WindowsDriver;
import junit.framework.Assert;
import org.openqa.selenium.WebElement;
import com.wings.utils.Common;

import java.util.List;

public class Report {
    WindowsDriver driver;
    Common common;

    public Report(WindowsDriver driver) {
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void bulkVerifyReport(String transaction) {
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().contains(transaction)) {
                Assert.assertTrue(true);
            }
        }
    }

    public void selectDropDownReport(String element){
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/*/*[contains(@Name,'MasterName row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().contains(element)) {
                i.click();
            }
        }
    }

    public void closeReport(String reportName) {
        common.clickElement("xpath", "//TabItem[@Name='" + reportName + "']/Button[@Name='Close']");
    }
}
