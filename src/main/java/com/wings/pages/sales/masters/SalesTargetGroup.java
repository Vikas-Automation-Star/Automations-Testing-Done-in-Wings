package com.wings.pages.sales.masters;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class SalesTargetGroup {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesTargetGroup(WindowsDriver driver, String file) {
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void salesTarget() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Sales Target");
        common.clickElement("xpath", "//Menu[@Name='Sales Target']/MenuItem[@Name='Product Sales Target Groups']");
        Thread.sleep(1000);
        WebElement allSalesTarget = common.findWebElement("xpath", "//TreeItem[@Name='Product Sales Target Groups']/TreeItem[@Name='All Product Sales Target Groups']");
        Actions actions = new Actions(driver);
        actions.contextClick(allSalesTarget).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='New Product Sales Target Group *']", common.getData(dataFile, "salesGroup") + common.getRandom());
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Cancel']");
        common.clickElement("xpath", "//TabItem[@Name='Product Sales Target Groups']/Button[@Name='Close']");
    }
}
