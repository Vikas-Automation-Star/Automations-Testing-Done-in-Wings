package com.wings.pages.finance.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.wings.utils.Common;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class DrawnOnBank {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public DrawnOnBank(WindowsDriver driver, String file){
        this.driver=driver;
        common=new Common(this.driver);
        dataFile=file;
    }
    public void bankDraws() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Drawn On Banks");
        Thread.sleep(1000);
        WebElement AllBranch = common.findWebElement("xpath", "//TreeItem[@Name='Drawn On Banks']/TreeItem[@Name='All Drawn On Banks']");
        Actions actions = new Actions(driver);
        actions.contextClick(AllBranch).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='New Drawn On Banks *']", common.getData(dataFile, "name")+common.getRandom());
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Cancel']");
        common.clickElement("xpath", "//TabItem[@Name='Drawn On Banks']/Button[@Name='Close']");
    }
}
