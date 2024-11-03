package com.wings.pages.sales.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.wings.utils.Common;

import java.io.IOException;

public class PriceList {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PriceList(WindowsDriver driver,String file){
        this.driver=driver;
        common=new Common(this.driver);
        dataFile=file;
    }
    public void priceList() throws InterruptedException, IOException, ParseException {
        common.clickElement("name","Sales");
        common.clickElement("name","Prices and Discounts");
        common.clickElement("xpath","//Menu[@Name='Prices and Discounts']/MenuItem[@Name='Sales Price Lists']");
        Thread.sleep(1000);
        WebElement allPriceList = common.findWebElement("xpath", "//TreeItem[@Name='Sales Price Lists']/TreeItem[@Name='All Sales Price Lists']");
        Actions actions = new Actions(driver);
        actions.contextClick(allPriceList).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(1000);
        common.inputText("xpath","//Edit[@Name='New Sales Price List *']",common.getData(dataFile,"priceList")+common.getRandom());
        Thread.sleep(2000);
        common.inputAndVerify("xpath","//Edit[@Name='Description']", common.getData(dataFile,"description"));
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Pane/Button[@Name='Cancel']");
        common.clickElement("xpath", "//TabItem[@Name='Sales Price Lists']/Button[@Name='Close']");


    }

}
