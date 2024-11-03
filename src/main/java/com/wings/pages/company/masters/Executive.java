package com.wings.pages.company.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;

import java.io.IOException;

public class Executive {
    WindowsDriver driver;
    Common common;
    String dataFile;
    FileUtil file=new FileUtil();

    public Executive(WindowsDriver driver, String file){
        this.driver=driver;
        common=new Common(this.driver);
        dataFile=file;
    }

    public void exec() throws InterruptedException, IOException, ParseException {
        common.clickElement("name","Company");
        common.clickElement("name","Executives");
        Thread.sleep(1200);
        WebElement AllBranch = common.findWebElement("xpath", "//TreeItem[@Name='Executives']/TreeItem[@Name='All Executives']");
        Actions actions = new Actions(driver);
        actions.contextClick(AllBranch).perform();
        common.clickElement("name", "New Master");

      Thread.sleep(2000);
        common.inputText("xpath","//Edit[@Name='New Executive *']", common.getData(dataFile,"execName")+common.getRandom());
        common.inputText("xpath","//Edit[@Name='Executive Code']",String.valueOf(common.getRandom()));
//        common.inputText("xpath","//Edit{@Name='Description']",file.getData(dataFile,"description"));
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        common.clickElement("xpath","//Pane[@Name='Address and Contact Details']/Button[@Name='...']");
        Thread.sleep(1200);

        WebElement address=driver.findElementByXPath("//Window[@Name='Address and Contact Details']/Pane/Pane/Edit[@Name='Address 1']");
        address.sendKeys(common.getData(dataFile,"address1"), Keys.TAB,common.getData(dataFile,"address2"),Keys.TAB, common.getData(dataFile,"address3"),Keys.TAB, common.getData(dataFile,"city"),Keys.TAB, common.getData(dataFile,"state"),Keys.TAB, common.getData(dataFile,"country"),Keys.ENTER, common.getData(dataFile,"zip"),Keys.TAB, common.getData(dataFile,"tel1"),Keys.TAB, common.getData(dataFile,"tel2"),Keys.TAB, common.getData(dataFile,"tel3"),Keys.TAB,common.getData(dataFile,"tel4"),Keys.TAB, common.getData(dataFile,"fax"),Keys.TAB, common.getData(dataFile,"email"),Keys.TAB, common.getData(dataFile,"website"),Keys.TAB, common.getData(dataFile,"cp"),Keys.TAB, common.getData(dataFile,"cpd"),Keys.TAB, common.getData(dataFile,"cptn"),Keys.TAB, common.getData(dataFile,"cpmn"), Keys.TAB, common.getData(dataFile,"cpe"),Keys.TAB,Keys.ENTER);

        common.clickElement("xpath","//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Cancel']");

        common.clickElement("xpath", "//TabItem[@Name='Executives']/Button[@Name='Close']");

    }
}
