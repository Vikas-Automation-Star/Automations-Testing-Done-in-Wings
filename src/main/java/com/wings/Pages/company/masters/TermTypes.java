package com.wings.pages.company.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.wings.utils.Common;

import java.io.IOException;

public class TermTypes {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public TermTypes(WindowsDriver driver, String file){
        this.driver=driver;
        common=new Common(this.driver);
        dataFile=file;
    }
    public void termType() throws InterruptedException, IOException, ParseException {
        common.clickElement("name","Company");
        common.clickElement("name","Terms");
        common.clickElement("xpath","//MenuItem[@Name='Term Types']");
        Thread.sleep(1000);
        WebElement AllBranch = common.findWebElement("xpath", "//TreeItem[@Name='Term Types']/TreeItem[@Name='All Term Types']");
        Actions actions = new Actions(driver);
        actions.contextClick(AllBranch).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(1000);
        common.inputText("xpath","//Edit[@Name='New Term Type *']",common.getData(dataFile,"newTermType")+common.getRandom());
        Thread.sleep(2000);
        common.inputText("xpath","//Edit[@Name='Description']", common.getData(dataFile,"description"));
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Cancel']");

        common.clickElement("xpath", "//TabItem[@Name='Term Types']/Button[@Name='Close']");
    }
}
