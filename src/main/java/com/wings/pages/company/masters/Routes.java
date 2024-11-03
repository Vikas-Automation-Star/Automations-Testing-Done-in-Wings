package com.wings.pages.company.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.wings.utils.Common;

import java.io.IOException;

public class Routes {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Routes(WindowsDriver driver,String file){
        this.driver=driver;
        common=new Common(this.driver);
        dataFile=file;
    }
    public void findRoute() throws InterruptedException, IOException, ParseException {
        common.clickElement("name","Company");
        common.clickElement("name","Routes");
        Thread.sleep(1000);
        WebElement AllBranch = common.findWebElement("xpath", "//TreeItem[@Name='Routes']/TreeItem[@Name='All Routes']");
        Actions actions = new Actions(driver);
        actions.contextClick(AllBranch).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(1000);
        common.inputText("xpath","//Edit[@Name='New Route *']",common.getData(dataFile,"route")+common.getRandom());
        Thread.sleep(2000);
        common.inputText("xpath","//Edit[@Name='Description']", common.getData(dataFile,"description"));
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Cancel']");

        common.clickElement("xpath", "//TabItem[@Name='Routes']/Button[@Name='Close']");

    }
}
