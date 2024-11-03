package com.wings.pages.sales.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.wings.utils.Common;

import java.awt.*;
import java.io.IOException;

public class TypeOfCustomer {
    WindowsDriver driver;
    Common common;
    String file;

    public TypeOfCustomer(WindowsDriver driver,String dataFile){
        this.driver=driver;
        common=new Common(this.driver);
        file=dataFile;
    }

    public void typecust() throws InterruptedException, IOException, ParseException {
        common.clickElement("name","Sales");
        common.clickElement("name","Types of Customer");
        Thread.sleep(1000);
        WebElement AllBranch = common.findWebElement("xpath", "//TreeItem[@Name='Types of Customer']/TreeItem[@Name='All Types of Customer']");
        Actions actions = new Actions(driver);
        actions.contextClick(AllBranch).perform();
        common.clickElement("name", "New Master");
        common.inputText("xpath","//Edit[@Name='New Type Of Customer *']", common.getData(file,"newCust")+common.getRandom());
        common.inputAndVerify("xpath","//Edit[@Name='Prefix *']", common.getData(file,"prefix"));
        common.inputAndVerify("xpath","//Edit[@Name='No Of Digits *']", common.getData(file,"noOfDigits"));
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Cancel']");
        common.clickElement("xpath", "//TabItem[@Name='Types of Customer']/Button[@Name='Close']");
    }
}
